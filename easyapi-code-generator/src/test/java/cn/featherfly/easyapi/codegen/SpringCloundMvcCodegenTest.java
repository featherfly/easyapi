package cn.featherfly.easyapi.codegen;

import cn.featherfly.easyapi.codegen.springmvc.SpringCloundMvcCodegen;

/**
 * Created by zj on 2017/4/27.
 */
public class SpringCloundMvcCodegenTest {

    public static void g(String yaml, String module) {
        GenerateCode code = new GenerateCode();
        code.setConfigFile("api/admin-config.json");
        code.addExtParameter(new ExtParameter("@Login", new ExtCodegenParameter(true, false),
                "cn.featherfly.jbox.web.admin.permission.AdminLoginInfo", "AdminLoginInfo", "loginInfo"));
        //        code.addExtParameter(new ExtParameter("@LoginAdmin", new ExtCodegenParameter(true, false),
        //                "cn.featherfly.jbox.web.admin.permission.AdminLoginInfo", "AdminLoginInfo", "loginInfo"));
        //        code.addExtParameter(new ExtParameter("@Login", new ExtCodegenParameter(true, false),
        //                "cn.featherfly.jbox.user.domain.User", "User", "user"));
        code.setOutput(Constants.SPRINGMVC_OUT_DIR);
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
        g("api/user.yaml", "user");
        g("api/order-api.yaml", "order");
    }
}