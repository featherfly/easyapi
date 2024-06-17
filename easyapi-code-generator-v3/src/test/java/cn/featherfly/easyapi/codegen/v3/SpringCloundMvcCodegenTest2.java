package cn.featherfly.easyapi.codegen.v3;

import cn.featherfly.easyapi.codegen.Constants;
import cn.featherfly.easyapi.v3.springmvc.SpringCodegen;
import io.swagger.codegen.v3.ClientOptInput;
import io.swagger.codegen.v3.DefaultGenerator;

public class SpringCloundMvcCodegenTest2 {

    public static void generate(String yaml, String module) {
        DefaultGenerator generator = new DefaultGenerator();
        ClientOptInput optInput = new ClientOptInput();

        SpringCodegen code = new SpringCodegen();
        //code.setConfigFile("api/admin-config.json");
        //code.addExtParameter(new ExtParameter("@Login", new ExtCodegenParameter(true, false),
//                "cn.featherfly.jbox.web.admin.permission.AdminLoginInfo", "AdminLoginInfo", "loginInfo"));
        //        code.addExtParameter(new ExtParameter("@LoginAdmin", new ExtCodegenParameter(true, false),
        //                "cn.featherfly.jbox.web.admin.permission.AdminLoginInfo", "AdminLoginInfo", "loginInfo"));
        //        code.addExtParameter(new ExtParameter("@Login", new ExtCodegenParameter(true, false),
        //                "cn.featherfly.jbox.user.domain.User", "User", "user"));
        code.setOutputDir(Constants.SPRINGMVC_OUT_DIR_V3);
        code.setSourceFolder("java");

        code.setInputSpec(yaml);
        code.setDelegatePattern(true);
        //        code.run(new SpringCloundMvcCodegen(module));
        //code.setModule(module);
        //code.setSecondModule("secondmodule");

        optInput.config(code);
        generator.opts(optInput).generate();
    }

    public static void main(String[] args) {
        generate("api/user.yaml", "user");
        generate("api/order-api.yaml", "order");
    }
}