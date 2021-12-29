package cn.featherfly.easyapi.codegen;

import cn.featherfly.easyapi.codegen.android.AndroidCodegen;

public class AndroidGenerateCodeTest {

    public static void main(String[] args) {
        GenerateCode code = new GenerateCode();
        code.setConfigFile("api/admin-config.json");
        code.addExtParameter(new ExtParameter("@LoginAdmin", new ExtCodegenParameter(true, false),
                "cn.featherfly.jbox.web.admin.permission.AdminLoginInfo", "AdminLoginInfo", "loginInfo"));
        code.addExtParameter(new ExtParameter("@Login", new ExtCodegenParameter(true, false),
                "cn.featherfly.jbox.user.domain.User", "User", "user"));
        code.setOutput(Constants.ANDROID_OUT_DIR);
        code.setSourceFolder("java");
        code.setGenerateApiDocs(false);
        code.setGenerateModelDocs(false);
        code.setGenerateModels(true);
        code.setGenerateApis(true);
        code.setGenerateSupportingFiles(true);
        //        code.setVerbose(true);

        //        code.setTemplateDir("/codegen/JavaSpring");

        //        code.setSpec("api/account-api.yaml");
        //        code.run(new SpringCloundMvcCodegen("account"));

        code.setWrapResponse(true);
        code.setSpec("api/questionnaire.yaml");
        code.setModule("user");
        code.run(new AndroidCodegen());
    }
}
