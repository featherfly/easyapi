package cn.featherfly.easyapi.codegen;

import cn.featherfly.common.io.FileUtils;
import cn.featherfly.common.lang.ClassLoaderUtils;
import com.github.jknack.handlebars.Handlebars;
import com.samskivert.mustache.Mustache;
import io.swagger.codegen.v3.*;
import io.swagger.codegen.v3.config.Config;
import io.swagger.codegen.v3.config.ConfigParser;
import io.swagger.codegen.v3.generators.DefaultCodegenConfig;
import io.swagger.codegen.v3.templates.TemplateEngine;
import io.swagger.codegen.v3.utils.ImplementationVersion;
import io.swagger.codegen.v3.utils.URLPathUtil;
import io.swagger.parser.OpenAPIParser;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.parser.core.models.SwaggerParseResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.*;

import static java.util.ServiceLoader.load;

/**
 * The type Generate code.
 */
public class EasyapiGenerator {

    /**
     * The constant LOG.
     */
    public static final Logger LOG = LoggerFactory.getLogger(EasyapiGenerator.class);

    private boolean verbose;

//    private String specFolder;

    private boolean generateApis = true;

    private boolean generateApiDocs;

    private boolean generateModels = true;

    private boolean generateModelDocs;

    private boolean generateTests;

    private boolean generateSwaggerMetadata;

    private boolean generateSupportingFiles;

    private boolean useOas2Option;

    private boolean debug = true;

    //    private String templateDir = "src/main/resources/spring-mvc/myServerCodegen";

    private String templateDir = null;

    private String systemProperties;

    private String apiPath;

    private boolean mergeDoc = true;

    private final DefaultCodegenConfig codegenConfig;

    private final EasyapiDefaultGenerator generator = new EasyapiDefaultGenerator();

    public EasyapiGenerator(DefaultCodegenConfig codegenConfig) {
        this.codegenConfig = codegenConfig;
    }

    /**
     * Tries to load config class with SPI first, then with class name directly
     * from classpath
     *
     * @param name name of config, or full qualified class name in classpath
     * @return config class
     */
    private static CodegenConfig forName(String name) {
        ServiceLoader<CodegenConfig> loader = load(CodegenConfig.class);
        for (CodegenConfig config : loader) {
            if (config.getName().equals(name)) {
                return config;
            }
        }

        // else try to load directly
        try {
            return (CodegenConfig) Class.forName(name).newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Can't load config class with name ".concat(name), e);
        }
    }

    public void generate(String spec, String configFile) {
        String specFileName = StringUtils.substringAfterLast(new File(spec).getAbsolutePath(), File.separator);

        // 合并文档定义文件，用于正确生成引用了其他文档定义文件中的对象定义
        if (mergeDoc) {
            String fileName = spec;
            String newFileName = fileName.replaceAll(".yaml", ".merged.yaml");
            String newFilePath = ClassLoaderUtils.getResource(".").getPath() + newFileName;
            File newFile = new File(newFilePath);
            FileUtils.makeDirectory(newFile);
            try {
                MergeDocs.mergeSchemas(fileName,
                        new OutputStreamWriter(new FileOutputStream(new File(newFilePath)), StandardCharsets.UTF_8));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            spec = newFileName;
        }

        if (null != templateDir) {
            if (debug) {
                System.out.println("templateDir: " + templateDir);
            }
            codegenConfig.additionalProperties().put(CodegenConstants.TEMPLATE_DIR, templateDir);
            if (apiPath != null && !"".equals(apiPath)) {
                codegenConfig.additionalProperties().put("apiPath", apiPath);
            }
        }

        // preprocess config file
        if (null != configFile) {
            URL configFileUrl = ClassLoaderUtils.getResource(configFile, this.getClass());
            if (debug) {
                System.out.println("configFile: " + configFileUrl);
            }
            if (configFileUrl == null) {
                throw new IllegalArgumentException("configFile is not found with " + configFile);
            }
        }

        verbosed(verbose);

//        run();

//        setVerbose(verbose);

        setSystemProperties();

        configureGeneratorProperties();

        processConfigFile(configFile);

        URL specUrl = ClassLoaderUtils.getResource(spec, this.getClass());
        if (debug) {
            System.out.println("spec: " + specUrl);
        }

        // 如果设置了specFolder，则代表需要复制spec
//        if (Lang.isNotEmpty(specFolder)) {
//            try {
//                File f = new File(UriUtils.linkUri(output, specFolder, specFileName));
//                if (f.exists()) {
//                    FileUtils.delete(f);
//                } else {
//                    FileUtils.makeDirectory(f);
//                }
//                System.out.println("specOutput: " + f.getAbsolutePath());
//                IOUtils.copy(specUrl, f);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }

        ClientOptInput input = new ClientOptInput();
        SwaggerParseResult swaggerParseResult = new OpenAPIParser().readLocation(specUrl.toString(),
                input.getAuthorizationValues(), null);
        EasyapiDefaultGenerator generator = new EasyapiDefaultGenerator();
        //        generator.setGenerateControllers(generateControllers);
        generator.setGenerateSwaggerMetadata(generateSwaggerMetadata);
        List<File> files = generator
                .opts(input.opts(new ClientOpts()).config(codegenConfig).openAPI(swaggerParseResult.getOpenAPI())).generate();
        if (debug) {
            for (File file : files) {
                System.out.println("generate file : " + file.getAbsolutePath());
            }
        }
    }

    private void processConfigFile(String configFile) {
        if (null != configFile) {
            URL configFileUrl = ClassLoaderUtils.getResource(configFile, this.getClass());
            System.out.println("configFile: " + configFileUrl);
            if (configFileUrl == null) {
                throw new IllegalArgumentException("configFile is not found with " + configFile);
            }
            Config genConfig = ConfigParser.read(configFileUrl.getPath());
            if (null != genConfig) {
                for (CliOption langCliOption : codegenConfig.cliOptions()) {
                    if (genConfig.hasOption(langCliOption.getOpt())) {
                        if ("modelPackage".equals(langCliOption.getOpt())) {
                            codegenConfig.setModelPackage(genConfig.getOption(langCliOption.getOpt()));
                            codegenConfig.additionalProperties().put(langCliOption.getOpt(), codegenConfig.modelPackage());
                        } else if ("apiPackage".equals(langCliOption.getOpt())) {
                            codegenConfig.setApiPackage(genConfig.getOption(langCliOption.getOpt()));
                            codegenConfig.additionalProperties().put(langCliOption.getOpt(), codegenConfig.apiPackage());
                        } else {
                            codegenConfig.additionalProperties().put(langCliOption.getOpt(),
                                    genConfig.getOption(langCliOption.getOpt()));
                        }
                    }
                }
//                if (genConfig.hasOption("title")) {
//                    if (codegenConfig instanceof EasyapiAbstractJavaCodegen) {
//                        ((EasyapiAbstractJavaCodegen) codegen).setTitle(genConfig.getOption("title"));
//                    }
//                }
            }
        }
    }

    private void configureGeneratorProperties() {
        System.setProperty(CodegenConstants.GENERATE_APIS, generateApis + "");

        System.setProperty(CodegenConstants.API_DOCS, generateApiDocs + "");
        System.setProperty(CodegenConstants.API_DOCS_OPTION, generateApiDocs + "");
        System.setProperty(CodegenConstants.GENERATE_API_DOCS, generateApiDocs + "");

        System.setProperty(CodegenConstants.API_TESTS, generateTests + "");
        System.setProperty(CodegenConstants.API_TESTS_OPTION, generateTests + "");
        System.setProperty(CodegenConstants.GENERATE_API_TESTS, generateTests + "");

        System.setProperty(CodegenConstants.GENERATE_MODELS, generateModels + "");

        System.setProperty(CodegenConstants.MODEL_DOCS, generateModelDocs + "");
        System.setProperty(CodegenConstants.MODEL_DOCS_OPTION, generateModelDocs + "");
        System.setProperty(CodegenConstants.GENERATE_MODEL_DOCS, generateModelDocs + "");

        System.setProperty(CodegenConstants.MODEL_TESTS, generateTests + "");
        System.setProperty(CodegenConstants.MODEL_TESTS_OPTION, generateTests + "");
        System.setProperty(CodegenConstants.GENERATE_MODEL_TESTS, generateTests + "");

        System.setProperty(CodegenConstants.SUPPORTING_FILES, generateSupportingFiles + "");

        System.setProperty(CodegenConstants.USE_OAS2_OPTION, useOas2Option + "");
        System.setProperty(CodegenConstants.USE_OAS2, useOas2Option + "");

        if (debug) {
            System.setProperty("debugSwagger", debug + "");
        }
    }

    private void setSystemProperties() {
        if (systemProperties != null && systemProperties.length() > 0) {
            for (String property : systemProperties.split(",")) {
                int ix = property.indexOf('=');
                if (ix > 0 && ix < property.length() - 1) {
                    System.setProperty(property.substring(0, ix), property.substring(ix + 1));
                }
            }
        }
    }

    /**
     * With path prefix generate code 2.
     *
     * @param apiPath the api path
     * @return the generate code 2
     */
    public EasyapiGenerator withPathPrefix(String apiPath) {
        this.apiPath = apiPath;
        return this;
    }

    /**
     * Sets api path.
     *
     * @param apiPath the api path
     */
    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }

    /**
     * Sets template dir.
     *
     * @param templateDir the template dir
     */
    public void setTemplateDir(String templateDir) {
        this.templateDir = templateDir;
    }


    /**
     * Getter for property 'generateTests'.
     *
     * @return Value for property 'generateTests'.
     */
    public boolean isGenerateTests() {
        return generateTests;
    }

    /**
     * Getter for property 'generateModelDocs'.
     *
     * @return Value for property 'generateModelDocs'.
     */
    public boolean isGenerateModelDocs() {
        return generateModelDocs;
    }

    /**
     * Setter for property 'generateModelDocs'.
     *
     * @param generateModelDocs Value to set for property 'generateModelDocs'.
     */
    public void setGenerateModelDocs(boolean generateModelDocs) {
        this.generateModelDocs = generateModelDocs;
    }

    /**
     * Getter for property 'templateDir'.
     *
     * @return Value for property 'templateDir'.
     */
    public String getTemplateDir() {
        return templateDir;
    }

    /**
     * Getter for property 'systemProperties'.
     *
     * @return Value for property 'systemProperties'.
     */
    public String getSystemProperties() {
        return systemProperties;
    }

    /**
     * Setter for property 'systemProperties'.
     *
     * @param systemProperties Value to set for property 'systemProperties'.
     */
    public void setSystemProperties(String systemProperties) {
        this.systemProperties = systemProperties;
    }

    /**
     * Getter for property 'apiPath'.
     *
     * @return Value for property 'apiPath'.
     */
    public String getApiPath() {
        return apiPath;
    }

    /**
     * Setter for property 'generateTests'.
     *
     * @param generateTests Value to set for property 'generateTests'.
     */
    public void setGenerateTests(boolean generateTests) {
        this.generateTests = generateTests;
    }

    /**
     * Getter for property 'generateApiDocs'.
     *
     * @return Value for property 'generateApiDocs'.
     */
    public boolean isGenerateApiDocs() {
        return generateApiDocs;
    }

    /**
     * Setter for property 'generateApiDocs'.
     *
     * @param generateApiDocs Value to set for property 'generateApiDocs'.
     */
    public void setGenerateApiDocs(boolean generateApiDocs) {
        this.generateApiDocs = generateApiDocs;
    }

    /**
     * Getter for property 'generateSwaggerMetadata'.
     *
     * @return Value for property 'generateSwaggerMetadata'.
     */
    public boolean isGenerateSwaggerMetadata() {
        return generateSwaggerMetadata;
    }

    /**
     * Setter for property 'generateSwaggerMetadata'.
     *
     * @param generateSwaggerMetadata Value to set for property
     *                                'generateSwaggerMetadata'.
     */
    public void setGenerateSwaggerMetadata(boolean generateSwaggerMetadata) {
        this.generateSwaggerMetadata = generateSwaggerMetadata;
    }

    /**
     * Is generate models boolean.
     *
     * @return the boolean
     */
    public boolean isGenerateModels() {
        return generateModels;
    }

    /**
     * Sets generate models.
     *
     * @param generateModels the generate models
     */
    public void setGenerateModels(boolean generateModels) {
        this.generateModels = generateModels;
    }

    public boolean isGenerateApis() {
        return generateApis;
    }

    public void setGenerateApis(boolean generateApis) {
        this.generateApis = generateApis;
    }

    /**
     * Getter for property 'generateSupportingFiles'.
     *
     * @return Value for property 'generateSupportingFiles'.
     */
    public boolean isGenerateSupportingFiles() {
        return generateSupportingFiles;
    }

    /**
     * Setter for property 'generateSupportingFiles'.
     *
     * @param generateSupportingFiles Value to set for property
     *                                'generateSupportingFiles'.
     */
    public void setGenerateSupportingFiles(boolean generateSupportingFiles) {
        this.generateSupportingFiles = generateSupportingFiles;
    }

    //    public boolean isGenerateControllers() {
    //        return generateControllers;
    //    }

    //    public void setGenerateControllers(boolean generateControllers) {
    //        this.generateControllers = generateControllers;
    //    }

    public boolean isMergeDoc() {
        return mergeDoc;
    }

    public void setMergeDoc(boolean mergeDoc) {
        this.mergeDoc = mergeDoc;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public void setUseOas2Option(boolean useOas2Option) {
        this.useOas2Option = useOas2Option;
    }

    public boolean isUseOas2Option() {
        return useOas2Option;
    }

    public boolean isDebug() {
        return debug;
    }

    /**
     * If true parameter, adds system properties which enables debug mode in
     * generator
     *
     * @param verbose - if true, enables debug mode
     */
    private void verbosed(boolean verbose) {
        if (!verbose) {
            return;
        }
        LOG.info("\nVERBOSE MODE: ON. Additional debug options are injected"
                + "\n - [debugSwagger] prints the swagger specification as interpreted by the codegen"
                + "\n - [debugModels] prints models passed to the template engine"
                + "\n - [debugOperations] prints operations passed to the template engine"
                + "\n - [debugSupportingFiles] prints additional data passed to the template engine");

        System.setProperty("debugSwagger", "");
        System.setProperty("debugModels", "");
        System.setProperty("debugOperations", "");
        System.setProperty("debugSupportingFiles", "");
    }

    // -------------------------------
}
