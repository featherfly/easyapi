package cn.featherfly.easyapi.codegen.v3;

import cn.featherfly.easyapi.codegen.Constants;
import cn.featherfly.easyapi.codegen.ExtCodegenParameter;
import cn.featherfly.easyapi.codegen.ExtParameter;
import cn.featherfly.easyapi.codegen.GenerateCode;
import cn.featherfly.easyapi.codegen.v3.spring.SpringCloundMvcCodegen;

public class SpringCloundMvcCodegenTest {

    public static void generate(String yaml, String module) {
        GenerateCode code = new GenerateCode();
        code.setConfigFile("api/admin-config.json");
        code.addExtParameter(new ExtParameter("@Login", new ExtCodegenParameter(true, false),
                "cn.featherfly.jbox.web.admin.permission.AdminLoginInfo", "AdminLoginInfo", "loginInfo"));
        //        code.addExtParameter(new ExtParameter("@LoginAdmin", new ExtCodegenParameter(true, false),
        //                "cn.featherfly.jbox.web.admin.permission.AdminLoginInfo", "AdminLoginInfo", "loginInfo"));
        //        code.addExtParameter(new ExtParameter("@Login", new ExtCodegenParameter(true, false),
        //                "cn.featherfly.jbox.user.domain.User", "User", "user"));
        code.setOutput(Constants.SPRINGMVC_OUT_DIR_V3);
        code.setSourceFolder("java");
        code.setGenerateApiDocs(false);
        code.setGenerateModelDocs(false);
        code.setGenerateModels(true);
        code.setGenerateApis(true);
        //        code.setGenerateSupportingFiles(true);
        //        code.setVerbose(true);

        //        code.setTemplateDir("/codegen/JavaSpring");

        //        code.setSpec("api/account-api.yaml");
        //        code.run(new SpringCloundMvcCodegen("account"));
        code.setSpec(yaml);
        //        code.run(new SpringCloundMvcCodegen(module));
        code.setModule(module);
        code.setSecondModule("secondmodule");
        code.run(new SpringCloundMvcCodegen());
    }

    public static void main(String[] args) {
        generate("api/user.yaml", "user");
        generate("api/order-api.yaml", "order");
    }
}