package cn.featherfly.easyapi.codegen;

import static java.util.ServiceLoader.load;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashSet;
import java.util.ServiceLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.io.FileUtils;
import cn.featherfly.common.lang.ClassLoaderUtils;
import io.swagger.codegen.v3.CliOption;
import io.swagger.codegen.v3.ClientOptInput;
import io.swagger.codegen.v3.ClientOpts;
import io.swagger.codegen.v3.CodegenConfig;
import io.swagger.codegen.v3.CodegenConstants;
import io.swagger.codegen.v3.config.Config;
import io.swagger.codegen.v3.config.ConfigParser;
import io.swagger.codegen.v3.generators.DefaultCodegenConfig;
import io.swagger.parser.OpenAPIParser;
import io.swagger.v3.parser.core.models.SwaggerParseResult;

/**
 * The type Generate code 2.
 */
public class GenerateCode implements EnableExtParameters, WrapResponseAbility, ModuleAbility {

    /**
     * The constant LOG.
     */
    public static final Logger LOG = LoggerFactory.getLogger(GenerateCode.class);

    private boolean wrapResponse;

    private String module;

    private String secondModule;

    private boolean verbose;

    //    private String output = "../mh-server/mh-web-api/src/v2gen/";

    private String output = "";

    private String spec = "";

    private boolean generateTests;

    private boolean generateModelDocs;

    private boolean generateApiDocs;

    private boolean generateSwaggerMetadata;

    private boolean generateModels = true;

    private boolean generateApis = true;

    private boolean generateSupportingFiles;

    //    private String templateDir = "src/main/resources/spring-mvc/myServerCodegen";

    private String templateDir = null;

    private String systemProperties;

    //    private String configFile = "";

    private String configFile = "";

    private String apiPath;

    /**
     * The Source folder.
     */
    public String sourceFolder = "java";

    private boolean mergeDoc = true;

    /**
     * The Ext parameters.
     */
    protected Collection<ExtParameter> extParameters = new HashSet<>();

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

    public void run(DefaultCodegenConfig codegen) {
        if (codegen instanceof WrapResponseAbility) {
            WrapResponseAbility wrapResponseAbility = (WrapResponseAbility) codegen;
            wrapResponseAbility.setWrapResponse(wrapResponse);
        }
        if (codegen instanceof ModuleAbility) {
            ModuleAbility moduleAbility = (ModuleAbility) codegen;
            moduleAbility.setModule(module);
            moduleAbility.setSecondModule(secondModule);
        }

        if (mergeDoc) {
            String fileName = getSpec();

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

            setSpec(newFileName);
        }

        verbosed(verbose);

        setSystemProperties();

        ClientOptInput input = new ClientOptInput();

        codegen.setOutputDir(new File(output).getAbsolutePath());

        if (codegen instanceof EnableExtParameters) {
            ((EnableExtParameters) codegen).getExtParameters().addAll(extParameters);
        }

        if (codegen instanceof EasyapiAbstractJavaCodegen) {
            ((EasyapiAbstractJavaCodegen) codegen).setSourceFolder(sourceFolder);
        }

        System.setProperty(CodegenConstants.GENERATE_APIS, generateApis + "");

        System.setProperty(CodegenConstants.API_DOCS, generateApiDocs + "");

        System.setProperty(CodegenConstants.API_TESTS, generateTests + "");

        System.setProperty(CodegenConstants.GENERATE_MODELS, generateModels + "");

        System.setProperty(CodegenConstants.MODEL_DOCS, generateModelDocs + "");

        System.setProperty(CodegenConstants.MODEL_TESTS, generateTests + "");

        System.setProperty(CodegenConstants.SUPPORTING_FILES, generateSupportingFiles + "");

        if (null != templateDir) {
            System.out.println("templateDir: " + templateDir);
            codegen.additionalProperties().put(CodegenConstants.TEMPLATE_DIR, templateDir);
            if (apiPath != null && !"".equals(apiPath)) {
                codegen.additionalProperties().put("apiPath", apiPath);
            }
        }

        if (null != configFile) {
            URL configFileUrl = ClassLoaderUtils.getResource(configFile, this.getClass());
            System.out.println("configFile: " + configFileUrl);
            if (configFileUrl == null) {
                throw new IllegalArgumentException("configFile is not found with " + configFile);
            }
            Config genConfig = ConfigParser.read(configFileUrl.getPath());
            if (null != genConfig) {
                for (CliOption langCliOption : codegen.cliOptions()) {
                    if (genConfig.hasOption(langCliOption.getOpt())) {
                        if ("modelPackage".equals(langCliOption.getOpt())) {
                            codegen.setModelPackage(genConfig.getOption(langCliOption.getOpt()));
                            codegen.additionalProperties().put(langCliOption.getOpt(), codegen.modelPackage());
                        } else if ("apiPackage".equals(langCliOption.getOpt())) {
                            codegen.setApiPackage(genConfig.getOption(langCliOption.getOpt()));
                            codegen.additionalProperties().put(langCliOption.getOpt(), codegen.apiPackage());
                        } else {
                            codegen.additionalProperties().put(langCliOption.getOpt(),
                                    genConfig.getOption(langCliOption.getOpt()));
                        }
                    }
                }
                if (genConfig.hasOption("title")) {
                    if (codegen instanceof EasyapiAbstractJavaCodegen) {
                        ((EasyapiAbstractJavaCodegen) codegen).setTitle(genConfig.getOption("title"));
                    }
                }
            }
        }

        URL specUrl = ClassLoaderUtils.getResource(spec, this.getClass());
        System.out.println("spec: " + specUrl);
        SwaggerParseResult swaggerParseResult = new OpenAPIParser().readLocation(specUrl.toString(),
                input.getAuthorizationValues(), null);
        EasyapiDefaultGenerator generator = new EasyapiDefaultGenerator();
        //        generator.setGenerateControllers(generateControllers);
        generator.setGenerateSwaggerMetadata(generateSwaggerMetadata);
        generator.opts(input.opts(new ClientOpts()).config(codegen).openAPI(swaggerParseResult.getOpenAPI()))
                .generate();
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

    /**
     * With path prefix generate code 2.
     *
     * @param apiPath the api path
     * @return the generate code 2
     */
    public GenerateCode withPathPrefix(String apiPath) {
        this.apiPath = apiPath;
        return this;
    }

    /**
     * Sets spec.
     *
     * @param spec the spec
     */
    public void setSpec(String spec) {
        this.spec = spec;
    }

    /**
     * Sets output.
     *
     * @param output the output
     */
    public void setOutput(String output) {
        this.output = output;
    }

    /**
     * Sets config file.
     *
     * @param configFile the config file
     */
    public void setConfigFile(String configFile) {
        this.configFile = configFile;
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
     * Setter for property 'verbose'.
     *
     * @param verbose Value to set for property 'verbose'.
     */
    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    /**
     * Getter for property 'verbose'.
     *
     * @return Value for property 'verbose'.
     */
    public boolean isVerbose() {
        return verbose;
    }

    /**
     * Getter for property 'output'.
     *
     * @return Value for property 'output'.
     */
    public String getOutput() {
        return output;
    }

    /**
     * Getter for property 'spec'.
     *
     * @return Value for property 'spec'.
     */
    public String getSpec() {
        return spec;
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
     * Getter for property 'configFile'.
     *
     * @return Value for property 'configFile'.
     */
    public String getConfigFile() {
        return configFile;
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
     * Getter for property 'sourceFolder'.
     *
     * @return Value for property 'sourceFolder'.
     */
    public String getSourceFolder() {
        return sourceFolder;
    }

    /**
     * Setter for property 'sourceFolder'.
     *
     * @param sourceFolder Value to set for property 'sourceFolder'.
     */
    public void setSourceFolder(String sourceFolder) {
        this.sourceFolder = sourceFolder;
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

    @Override
    public Collection<ExtParameter> getExtParameters() {
        return extParameters;
    }

    @Override
    public EnableExtParameters addExtParameter(ExtParameter extParameter) {
        if (extParameter != null) {
            extParameters.add(extParameter);
        }
        return this;
    }

    public boolean isMergeDoc() {
        return mergeDoc;
    }

    public void setMergeDoc(boolean mergeDoc) {
        this.mergeDoc = mergeDoc;
    }

    @Override
    public boolean isWrapResponse() {
        return wrapResponse;
    }

    @Override
    public void setWrapResponse(boolean wrapResponse) {
        this.wrapResponse = wrapResponse;
    }

    @Override
    public String toModelFilename(String name, String templateName) {
        return name;
    }

    @Override
    public String getModule() {
        return module;
    }

    @Override
    public void setModule(String module) {
        this.module = module;
    }

    @Override
    public String getSecondModule() {
        return secondModule;
    }

    @Override
    public void setSecondModule(String secondModule) {
        this.secondModule = secondModule;
    }

}
