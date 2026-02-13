package cn.featherfly.easyapi.codegen.v3;

import cn.featherfly.easyapi.codegen.ExtCodegenParameter;
import cn.featherfly.easyapi.codegen.ExtParameter;
import cn.featherfly.easyapi.codegen.EasyapiGenerator;
import cn.featherfly.easyapi.codegen.Constants;
import cn.featherfly.easyapi.codegen.v3.android.AndroidCodegen;

public class AndroidGenerateCodeTest {

    public static void main(String[] args) {
        final String configFile = "api/admin-config.json";
        AndroidCodegen codegen = new AndroidCodegen();
        codegen.addExtParameter(new ExtParameter("@LoginAdmin", new ExtCodegenParameter(true, false),
                "cn.featherfly.jbox.web.admin.permission.AdminLoginInfo", "AdminLoginInfo", "loginInfo"));
        codegen.addExtParameter(new ExtParameter("@Login", new ExtCodegenParameter(true, false),
                "cn.featherfly.jbox.user.domain.User", "User", "user"));
        codegen.setSourceFolder(Constants.ANDROID_OUT_DIR);
        codegen.setWrapResponse(true);
        codegen.setModule("user");

        EasyapiGenerator code = new EasyapiGenerator(codegen);
//        code.addExtParameter(new ExtParameter("@LoginAdmin", new ExtCodegenParameter(true, false),
//                "cn.featherfly.jbox.web.admin.permission.AdminLoginInfo", "AdminLoginInfo", "loginInfo"));
//        code.addExtParameter(new ExtParameter("@Login", new ExtCodegenParameter(true, false),
//                "cn.featherfly.jbox.user.domain.User", "User", "user"));
//        code.setOutput(Constants.ANDROID_OUT_DIR);
//        code.setSourceFolder("java");
        code.setGenerateApiDocs(false);
        code.setGenerateModelDocs(false);
        code.setGenerateModels(true);
        code.setGenerateApis(true);
        code.setGenerateSupportingFiles(true);
        //        code.setVerbose(true);
        //        code.setTemplateDir("/codegen/JavaSpring");
        code.generate("api/questionnaire.yaml", configFile);
    }
}
