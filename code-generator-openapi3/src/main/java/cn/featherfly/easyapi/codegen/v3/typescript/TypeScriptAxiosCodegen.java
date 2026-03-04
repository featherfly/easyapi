package cn.featherfly.easyapi.codegen.v3.typescript;

import cn.featherfly.common.lang.Lang;
import cn.featherfly.easyapi.codegen.EnableExtParameters;
import cn.featherfly.easyapi.codegen.ExtParameter;
import cn.featherfly.easyapi.codegen.ModuleAbility;
import cn.featherfly.easyapi.codegen.WrapResponseAbility;
import io.swagger.codegen.v3.*;
import io.swagger.codegen.v3.generators.typescript.AbstractTypeScriptClientCodegen;
import io.swagger.codegen.v3.generators.typescript.TypeScriptAxiosClientCodegen;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.*;

import static io.swagger.codegen.v3.generators.handlebars.ExtensionHelper.getBooleanValue;

/**
 * The type TypescriptAxiosCodegen.
 *
 * @author zhongj
 */
public class TypeScriptAxiosCodegen extends TypeScriptAxiosClientCodegen
        implements EnableExtParameters, ModuleAbility, WrapResponseAbility {


    private String tsModelPackage = "";

    private boolean wrapResponse = true;

    protected Collection<ExtParameter> extParameters = new HashSet<>();

    protected String module;

    protected String secondModule;

    protected String apiImportPath = "@featherfly-yufei/easyapi-ts";

    public TypeScriptAxiosCodegen() {
        super();

        customTemplateDir = "handlebars/easyapi/typescript-axios";
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
            apiPackage += "/" + module;
            modelPackage += "/" + module;
            if (Lang.isNotEmpty(secondModule)) {
                apiPackage += "/" + secondModule;
                modelPackage += "/" + secondModule;
            }
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

        supportingFiles.add(new SupportingFile("modelIndex.mustache", tsModelPackage, "index.ts"));

        if (wrapResponse) {
            modelTemplateFiles.put("model-response.mustache", ".ts");
        }
    }

//    @Override
//    public String apiFilename(String templateName, String tag) {
//        String suffix = apiTemplateFiles().get(templateName);
//        return apiFileFolder() + File.separator + toApiFilename(tag) + suffix;
//    }
//
//    @Override
//    public String toModelFilename(String name) {
////        return super.toModelFilename(name);
//        return super.toModelName(name);
//    }
//
//    @Override
//    public String toApiFilename(String name) {
    ////        return super.toApiFilename(name);
//        return super.toApiName(name);
//    }

    @Override
    public String getDefaultTemplateDir() {
        return super.getDefaultTemplateDir();
//        return "v3/easyapi-typescript-axios";
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
