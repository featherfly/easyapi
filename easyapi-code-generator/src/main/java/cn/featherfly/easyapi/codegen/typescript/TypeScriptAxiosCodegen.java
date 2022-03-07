package cn.featherfly.easyapi.codegen.typescript;

import static io.swagger.codegen.v3.generators.handlebars.ExtensionHelper.getBooleanValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

import cn.featherfly.easyapi.codegen.EnableExtParameters;
import cn.featherfly.easyapi.codegen.ExtParameter;
import cn.featherfly.easyapi.codegen.ModuleAbility;
import cn.featherfly.easyapi.codegen.WrapResponseAbility;
import io.swagger.codegen.v3.CodegenConstants;
import io.swagger.codegen.v3.CodegenModel;
import io.swagger.codegen.v3.CodegenOperation;
import io.swagger.codegen.v3.CodegenProperty;
import io.swagger.codegen.v3.SupportingFile;
import io.swagger.codegen.v3.generators.typescript.AbstractTypeScriptClientCodegen;

/**
 * The type TypescriptAxiosCodegen.
 *
 * @author zhongj
 */
public class TypeScriptAxiosCodegen extends AbstractTypeScriptClientCodegen
        implements EnableExtParameters, ModuleAbility, WrapResponseAbility {

    public static final String NPM_NAME = "npmName";
    public static final String NPM_REPOSITORY = "npmRepository";
    public static final String DEFAULT_API_PACKAGE = "apis";
    public static final String DEFAULT_MODEL_PACKAGE = "models";

    protected String npmRepository = null;

    private String tsModelPackage = "";

    private boolean wrapResponse = true;

    protected Collection<ExtParameter> extParameters = new HashSet<>();

    protected String module;

    protected String secondModule;

    protected String apiImportPath = "easyapi-ts";

    public TypeScriptAxiosCodegen() {
        super();
        //        setModule(module);
        importMapping.clear();
        outputFolder = "generated-code/typescript-axios";
    }

    @Override
    public String getName() {
        return "typescript-axios";
    }

    @Override
    public String getHelp() {
        return "Generates a TypeScript Axios client library.";
    }

    public String getNpmRepository() {
        return npmRepository;
    }

    public void setNpmRepository(String npmRepository) {
        this.npmRepository = npmRepository;
    }

    private static String getRelativeToRoot(String path) {
        StringBuilder sb = new StringBuilder();
        int slashCount = path.split("/").length;
        if (slashCount == 0) {
            sb.append("./");
        } else {
            for (int i = 0; i < slashCount; ++i) {
                sb.append("../");
            }
        }
        return sb.toString();
    }

    @Override
    public void processOpts() {
        super.processOpts();
        if (StringUtils.isBlank(apiPackage)) {
            apiPackage = DEFAULT_API_PACKAGE;
        }
        //        if (StringUtils.isBlank(modelPackage)) {
        //            modelPackage = DEFAULT_MODEL_PACKAGE;
        //        }
        modelPackage = apiPackage;

        String baseApiPackage = apiPackage;
        additionalProperties.put("baseApiPackage", baseApiPackage);

        if (StringUtils.isNotBlank(module)) {
            // 加入模块支持
            apiPackage = apiPackage + "/" + module;
            modelPackage = modelPackage + "/" + module;
        }

        tsModelPackage = modelPackage.replaceAll("\\.", "/");
        String tsApiPackage = apiPackage.replaceAll("\\.", "/");

        String modelRelativeToRoot = getRelativeToRoot(tsModelPackage);
        String apiRelativeToRoot = getRelativeToRoot(tsApiPackage);

        if (StringUtils.isBlank(apiImportPath)) {
            apiImportPath = apiRelativeToRoot + baseApiPackage + "/api";
        }

        //        if (StringUtils.isBlank(requestImportPath)) {
        //            requestImportPath = apiRelativeToRoot + baseApiPackage + "/request";
        //        }

        additionalProperties.put("apiImportPath", apiImportPath);
        //        additionalProperties.put("requestImportPath", requestImportPath);

        additionalProperties.put("tsModelPackage", tsModelPackage);
        additionalProperties.put("tsApiPackage", tsApiPackage);
        additionalProperties.put("apiRelativeToRoot", apiRelativeToRoot);
        additionalProperties.put("modelRelativeToRoot", modelRelativeToRoot);

        supportingFiles.add(new SupportingFile("index.mustache", "", "index.ts"));
        supportingFiles.add(new SupportingFile("baseApi.mustache", "", "base.ts"));
        supportingFiles.add(new SupportingFile("api.mustache", "", "api.ts"));
        supportingFiles.add(new SupportingFile("configuration.mustache", "", "configuration.ts"));
        supportingFiles.add(new SupportingFile("git_push.sh.mustache", "", "git_push.sh"));
        supportingFiles.add(new SupportingFile("gitignore", "", ".gitignore"));
        supportingFiles.add(new SupportingFile("npmignore", "", ".npmignore"));

        modelTemplateFiles.put("model.mustache", ".d.ts");
        apiTemplateFiles.put("apiInner.mustache", ".ts");
        supportingFiles.add(new SupportingFile("modelIndex.mustache", tsModelPackage, "index.ts"));

        supportingFiles.add(new SupportingFile("README.mustache", "", "README.md"));
        supportingFiles.add(new SupportingFile("package.mustache", "", "package.json"));
        supportingFiles.add(new SupportingFile("tsconfig.mustache", "", "tsconfig.json"));

        if (wrapResponse) {
            modelTemplateFiles.put("model-response.mustache", ".ts");
        }
    }

    @Override
    public Map<String, Object> postProcessOperations(Map<String, Object> operations) {
        boolean hasImports = operations.get("hasImport") != null
                && Boolean.parseBoolean(operations.get("hasImport").toString());
        if (hasImports) {
            @SuppressWarnings("unchecked")
            List<Map<String, String>> imports = (List<Map<String, String>>) operations.get("imports");

            for (Map<String, String> importMap : imports) {
                final String importValue = importMap.get("import");
                if (StringUtils.isNotBlank(importValue) && importValue.contains(".")) {
                    int index = importValue.indexOf(".");
                    importMap.put("import", importValue.substring(index + 1));
                }
            }
        }
        return operations;
    }

    @Override
    public Map<String, Object> postProcessOperationsWithModels(Map<String, Object> objs, List<Object> allModels) {
        objs = super.postProcessOperationsWithModels(objs, allModels);
        @SuppressWarnings("unchecked")
        Map<String, Object> vals = (Map<String, Object>) objs.getOrDefault("operations", new HashMap<>());
        @SuppressWarnings("unchecked")
        List<CodegenOperation> operations = (List<CodegenOperation>) vals.getOrDefault("operation", new ArrayList<>());
        /*
            Filter all the operations that are multipart/form-data operations and set the vendor extension flag
            'multipartFormData' for the template to work with.
         */
        operations.stream().filter(op -> getBooleanValue(op, CodegenConstants.HAS_CONSUMES_EXT_NAME))
                .filter(op -> op.consumes.stream()
                        .anyMatch(opc -> opc.values().stream().anyMatch("multipart/form-data"::equals)))
                .forEach(op -> op.vendorExtensions.putIfAbsent("multipartFormData", true));
        return objs;
    }

    @Override
    public Map<String, Object> postProcessAllModels(Map<String, Object> objs) {
        Map<String, Object> result = super.postProcessAllModels(objs);
        //        for (Map.Entry<String, Object> entry : result.entrySet()) {
        //            @SuppressWarnings("unchecked")
        //            Map<String, Object> inner = (Map<String, Object>) entry.getValue();
        //            @SuppressWarnings("unchecked")
        //            List<Map<String, Object>> models = (List<Map<String, Object>>) inner.get("models");
        //            for (Map<String, Object> model : models) {
        //                CodegenModel codegenModel = (CodegenModel) model.get("model");
        //todo: model.put("hasAllOf", codegenModel.allOf.size() > 0);
        //todo: model.put("hasOneOf", codegenModel.oneOf.size() > 0);
        //            }
        //        }
        return result;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Map<String, Object> postProcessModels(Map<String, Object> objs) {
        List<Object> models = (List<Object>) postProcessModelsEnum(objs).get("models");

        for (Object _mo : models) {
            Map<String, Object> mo = (Map<String, Object>) _mo;
            CodegenModel cm = (CodegenModel) mo.get("model");

            // Deduce the model file name in kebab case
            cm.classFilename = cm.classname.replaceAll("([a-z0-9])([A-Z])", "$1-$2").toLowerCase(Locale.ROOT);

            //processed enum names
            cm.imports = new TreeSet(cm.imports);
            // name enum with model name, e.g. StatusEnum => PetStatusEnum
            for (CodegenProperty var : cm.vars) {
                if (getBooleanValue(var, CodegenConstants.IS_ENUM_EXT_NAME)) {
                    var.datatypeWithEnum = var.datatypeWithEnum.replace(var.enumName, cm.classname + var.enumName);
                    var.enumName = var.enumName.replace(var.enumName, cm.classname + var.enumName);
                }
            }
            if (cm.parent != null) {
                for (CodegenProperty var : cm.allVars) {
                    if (getBooleanValue(var, CodegenConstants.IS_ENUM_EXT_NAME)) {
                        var.datatypeWithEnum = var.datatypeWithEnum.replace(var.enumName, cm.classname + var.enumName);
                        var.enumName = var.enumName.replace(var.enumName, cm.classname + var.enumName);
                    }
                }
            }
        }

        // Apply the model file name to the imports as well
        for (Map<String, String> m : (List<Map<String, String>>) objs.get("imports")) {
            String javaImport = m.get("import").substring(modelPackage.length() + 1);
            String tsImport = tsModelPackage + "/" + javaImport;
            m.put("tsImport", tsImport);
            m.put("class", javaImport);
            m.put("filename", javaImport.replaceAll("([a-z0-9])([A-Z])", "$1-$2").toLowerCase(Locale.ROOT));
        }
        return objs;
    }

    /**
     * Overriding toRegularExpression() to avoid escapeText() being called, as
     * it would return a broken regular expression if any escaped character /
     * metacharacter were present.
     */
    @Override
    public String toRegularExpression(String pattern) {
        return addRegularExpressionDelimiter(pattern);
    }

    @Override
    public String toModelFilename(String name) {
        //        return super.toModelFilename(name).replaceAll("([a-z0-9])([A-Z])", "$1-$2").toLowerCase(Locale.ROOT);
        return super.toModelFilename(name);
    }

    @Override
    public String toApiFilename(String name) {
        //        return super.toApiFilename(name).replaceAll("([a-z0-9])([A-Z])", "$1-$2").toLowerCase(Locale.ROOT);
        return super.toApiFilename(name);
    }

    @Override
    public String getDefaultTemplateDir() {
        return "easyapi-typescript-axios";
    }

    // ------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------------------

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

    @Override
    public String toModelFilename(String name, String templateName) {
        if (templateName.equals("model-response.mustache")) {
            name += "Response";
        }
        return name;
    }

    @Override
    public String getModule() {
        return module;
    }

    @Override
    public void setModule(String module) {
        this.module = module;
        additionalProperties.put("module", module);
    }

    @Override
    public boolean isWrapResponse() {
        return wrapResponse;
    }

    @Override
    public void setWrapResponse(boolean wrapResponse) {
        this.wrapResponse = wrapResponse;
        if (wrapResponse) {
            modelTemplateFiles.put("model-response.mustache", ".java");
        }
    }

    /**
     * Getter for property 'apiImportPath'.
     *
     * @return Value for property 'apiImportPath'.
     */
    public String getApiImportPath() {
        return apiImportPath;
    }

    /**
     * Setter for property 'apiImportPath'.
     *
     * @param apiImportPath Value to set for property 'apiImportPath'.
     */
    public void setApiImportPath(String apiImportPath) {
        this.apiImportPath = apiImportPath;
    }

    /**
     * get secondModule value
     *
     * @return secondModule
     */
    @Override
    public String getSecondModule() {
        return secondModule;
    }

    /**
     * set secondModule value
     *
     * @param secondModule secondModule
     */
    @Override
    public void setSecondModule(String secondModule) {
        this.secondModule = secondModule;
    }
}
