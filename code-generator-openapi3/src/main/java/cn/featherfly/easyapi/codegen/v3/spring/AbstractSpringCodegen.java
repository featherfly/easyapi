package cn.featherfly.easyapi.codegen.v3.spring;

import cn.featherfly.common.bean.BeanUtils;
import cn.featherfly.common.lang.Str;
import cn.featherfly.easyapi.codegen.EasyapiModuleJavaCodegen;
import cn.featherfly.easyapi.codegen.EnableExtParameters;
import cn.featherfly.easyapi.codegen.ExtParameter;
import cn.featherfly.easyapi.codegen.v3.handlebars.HandlebarMulitiTemplateEngine;
import io.swagger.codegen.v3.*;
import io.swagger.codegen.v3.generators.java.SpringCodegen;
import io.swagger.codegen.v3.templates.MustacheTemplateEngine;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public abstract class AbstractSpringCodegen extends SpringCodegen
        implements EasyapiModuleJavaCodegen {

    static Logger LOGGER = LoggerFactory.getLogger(AbstractSpringCodegen.class);

    //
    protected String module;

    protected String secondModule;

    protected String defaultApiPath = "/v1/api";

    protected Set<ExtParameter> extParameters = new LinkedHashSet<>();

    public AbstractSpringCodegen() {
        super();
        modelTemplateFiles.put("model.mustache", ".java");
        title = "easyapi java code";
        apiDocPath = apiDocPath + "/" + module;
        modelDocPath = modelDocPath + "/" + module;
        apiPackage = "cn.featherfly.easyapi.api";
        modelPackage = "cn.featherfly.easyapi.model";
        invokerPackage = "cn.featherfly.easyapi.api";
        configPackage = "cn.featherfly.easyapi.configuration";
//        templateDir = "codegen/" + getDefaultTemplateDir();
//        customTemplateDir = getDefaultTemplateDir();
//        customTemplateDir = "JavaSpring";
    }

    public void processOpts() {
        super.processOpts();

        customTemplateDir = "handlebars/easyapi/JavaSrping";
    }

    @Override
    public Map<String, Object> postProcessOperations(Map<String, Object> objs) {
        objs = super.postProcessOperations(objs);
        Map<String, Object> operations = (Map<String, Object>) objs.get("operations");
        if (operations != null) {
            List<CodegenOperation> ops = (List<CodegenOperation>) operations.get("operation");
            for (final CodegenOperation operation : ops) {
                if ("Void".equals(operation.returnType)) {
                    operation.returnType = null;
                }
            }
        }
        return objs;
    }

    @Override
    public Map<String, Object> postProcessAllModels(Map<String, Object> objs) {
        return postProcessAllModels0(super.postProcessAllModels(objs));
    }

    protected void setTemplateEngine() {
        String templateEngineKey = additionalProperties.get(CodegenConstants.TEMPLATE_ENGINE) != null ? additionalProperties.get(CodegenConstants.TEMPLATE_ENGINE).toString() : null;

        if (templateEngineKey == null) {
            templateEngine = new HandlebarMulitiTemplateEngine(this);
        } else {
            if (CodegenConstants.HANDLEBARS_TEMPLATE_ENGINE.equalsIgnoreCase(templateEngineKey)) {
                templateEngine = new HandlebarMulitiTemplateEngine(this);
            } else {
                templateEngine = new MustacheTemplateEngine(this);
            }
        }
    }

    public void addParams(EasyapiModuleJavaCodegen codegen, CodegenContent codegenContent,
                          List<CodegenParameter> codegenParameters) {
        ((AbstractSpringCodegen) codegen).addParameters(codegenContent, codegenParameters);
    }

    @Override
    public CodegenOperation fromOperation(String path, String httpMethod, Operation operation,
                                          Map<String, Schema> schemas, OpenAPI openAPI) {
        CodegenOperation op = super.fromOperation(path, httpMethod, operation, schemas, openAPI);
        return fromOperation(op, this::addParams, path, httpMethod, operation, schemas, openAPI);
    }

//    @Override
//    public Map<String, Object> postProcessOperations(Map<String, Object> objs) {
//        @SuppressWarnings("unchecked")
//        Map<String, Object> operations = (Map<String, Object>) objs.get("operations");
//        if (operations != null) {
//            @SuppressWarnings("unchecked")
//            List<CodegenOperation> ops = (List<CodegenOperation>) operations.get("operation");
//            for (CodegenOperation operation : ops) {
//                if (operation.returnType != null) {
//                    if (operation.returnType.startsWith("Pagination")) {
//                        String rt = operation.returnType;
//                        operation.returnType = "PaginationResults<" + rt.substring("Pagination".length(), rt.length())
//                                + ">";
//                        EasyapiModuleJavaCodegen.addExtParameterImports(objs, "cn.featherfly.common.structure.page" +
//                                ".PaginationResults");
//                    }
//                }
//                if (!processServerParameter(operation, objs)) {
//                    String notes = operation.notes;
//                    if (notes != null) {
//                        extParameters.forEach(extParameter -> {
//                            if (notes.contains(extParameter.getName())) {
//                                addAddtionalParameter(extParameter.getCodegenParameter().copy(), operation,
//                                        extParameter.getParamTypeDefined(), extParameter.getParamName());

    /// /                                EasyapiModuleJavaCodegen.addExtParameterImports(objs, extParameter.getParamTypeImport());
//                            }
//                        });
//                    }
//                }
//            }
//        }
//        return objs;
//    }
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

    public String getDefaultApiPath() {
        return defaultApiPath;
    }

    public void setDefaultApiPath(String defaultApiPath) {
        this.defaultApiPath = defaultApiPath;
        additionalProperties.put("apiPath", defaultApiPath);
    }

    @Override
    public String getDefaultTemplateDir() {
        return super.getDefaultTemplateDir();
//        return "v3/easyapi-spring";
    }

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
    public void setApiPackage(String apiPackage) {
        this.apiPackage = Str.format(apiPackage, BeanUtils.toMap(this));
    }

    @Override
    public void setModelPackage(String modelPackage) {
        this.modelPackage = Str.format(modelPackage, BeanUtils.toMap(this));
    }

    @Override
    public void setInvokerPackage(String invokerPackage) {
        this.invokerPackage = Str.format(invokerPackage, BeanUtils.toMap(this));
    }

    @Override
    public void setConfigPackage(String configPackage) {
        this.configPackage = Str.format(configPackage, BeanUtils.toMap(this));
    }
}
