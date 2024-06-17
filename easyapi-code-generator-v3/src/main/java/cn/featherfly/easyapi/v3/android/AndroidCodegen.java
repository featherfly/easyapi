package cn.featherfly.easyapi.v3.android;

import java.util.Arrays;
import java.util.HashSet;

import cn.featherfly.easyapi.EasyapiAbstractJavaCodegen;
import cn.featherfly.easyapi.WrapResponseAbility;
import io.swagger.codegen.v3.CodegenType;

public class AndroidCodegen extends EasyapiAbstractJavaCodegen implements WrapResponseAbility {

    private boolean wrapResponse = true;

    public AndroidCodegen() {
        super();
        setDateLibrary("legacy");
        super.processOpts();
        outputFolder = "generated-code/android";
        // 清除默认的model.mustache
        modelTemplateFiles.clear();
        //		modelTemplateFiles.put("model-jackson.mustache", ".java");
        if (wrapResponse) {
            modelTemplateFiles.put("model-response.mustache", ".java");
            //		modelTemplateFiles.put("model-results.mustache", ".java");
        }
        //		apiTemplateFiles.put("apiController.mustache", ".java");
        // 清除api.mustache
        apiTemplateFiles.clear();
        apiTemplateFiles.put("api-okhttp.mustache", ".java");
        //		templateDir = "meihuoandroid";
        //		apiPackage = "io.swagger.api";
        //		modelPackage = "io.swagger.model";
        configPackage = "io.swagger.configuration";

        setInvokerPackage(invokerPackage);
        setGroupId(groupId);
        setArtifactId(artifactId);
        setArtifactVersion(artifactVersion);
        setTitle(title);
        setConfigPackage(configPackage);
        setDefaultApiPath(defaultApiPath);
        additionalProperties.put("apiPackage", apiPackage());
        additionalProperties.put("modelPackage", modelPackage());
        languageSpecificPrimitives = new HashSet<>(
                Arrays.asList("String", "boolean", "Boolean", "Double", "Integer", "Long", "Float"));
    }

    @Override
    public io.swagger.codegen.v3.CodegenType getTag() {
        return CodegenType.CLIENT;
    }

    @Override
    public String getName() {
        return "easyapi-android-client";
    }

    @Override
    public String getHelp() {
        return "Generates a android Client application using the SpringFox integration.";
    }

    @Override
    public void processOpts() {
        super.processOpts();
        //		modelTemplateFiles.remove("model.mustache");
    }

    @Override
    public String getDefaultTemplateDir() {
        return "v3/easyapi-android";
    }

    @Override
    public String toModelFilename(String name) {
        return toModelName(name);
    }

    //	@Override
    //	public String toApiFilename(String name) {
    //		return "Abstract" + toApiName(name);
    //	}

    @Override
    public String toModelFilename(String name, String templateName) {
        if (templateName.equals("model-response.mustache")) {
            name += "Response";
        }
        return name;
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
}
