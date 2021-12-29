package cn.featherfly.easyapi.codegen;

import cn.featherfly.easyapi.codegen.springmvc.SpringCloundApiCodegen;
import cn.featherfly.easyapi.codegen.springmvc.SpringCloundClientCodegen;
import cn.featherfly.easyapi.codegen.springmvc.SpringCloundMvcCodegen;

/**
 * Created by zj on 2017/4/27.
 */
public class SpringCloundCodegenTest {

    static String apiSpec = "api/order-api.yaml";

    public static void api(String module) {
        //        SpringCloundApiCodegen codegen = new SpringCloundApiCodegen("account2");
        SpringCloundApiCodegen codegen = new SpringCloundApiCodegen();
        GenerateCode code = new GenerateCode();
        //        code.setSpec("src/test/resources/api/account-api.yaml");
        code.setSpec(apiSpec);
        //        code.setConfigFile("src/test/resources/api/admin-config.json");
        code.setConfigFile("api/admin-config.json");
        code.setOutput(Constants.SPRING_CLOUND_OUT_DIR);
        code.setSourceFolder("java-api");
        code.setGenerateTests(false);
        code.setGenerateApiDocs(true);
        code.setGenerateModelDocs(true);
        //        code.setVerbose(true);
        code.setModule(module);
        code.run(codegen);
    }

    public static void client(String module) {
        //        SpringCloundClientCodegen codegen = new SpringCloundClientCodegen("account2");
        SpringCloundClientCodegen codegen = new SpringCloundClientCodegen();
        GenerateCode code = new GenerateCode();
        //        code.setSpec("src/test/resources/api/account-api.yaml");
        code.setSpec(apiSpec);
        //        code.setConfigFile("src/test/resources/api/admin-config.json");
        code.setConfigFile("api/admin-config.json");
        code.addExtParameter(new ExtParameter("@LoginAdmin", new ExtCodegenParameter(true, false),
                "cn.featherfly.jbox.web.admin.permission.AdminLoginInfo", "AdminLoginInfo", "loginInfo"));
        code.setOutput(Constants.SPRING_CLOUND_OUT_DIR);
        code.setSourceFolder("java-client");
        code.setGenerateApiDocs(false);
        code.setGenerateModelDocs(false);
        code.setGenerateModels(true);
        code.setGenerateApis(true);
        //        code.setVerbose(true);
        code.setModule(module);
        code.run(codegen);
    }

    public static void server(String module) {
        //        SpringCloundMvcCodegen codegen = new SpringCloundMvcCodegen("account2");
        SpringCloundMvcCodegen codegen = new SpringCloundMvcCodegen();
        codegen.setGenerateApi(true);
        GenerateCode code = new GenerateCode();
        //        code.setSpec("src/test/resources/api/account-api.yaml");
        code.setSpec(apiSpec);
        //        code.setConfigFile("src/test/resources/api/admin-config.json");
        code.setConfigFile("api/admin-config.json");
        code.addExtParameter(new ExtParameter("@LoginAdmin", new ExtCodegenParameter(true, false),
                "cn.featherfly.jbox.web.admin.permission.AdminLoginInfo", "AdminLoginInfo", "loginInfo"));
        code.setOutput(Constants.SPRING_CLOUND_OUT_DIR);
        code.setSourceFolder("java-server");
        code.setGenerateApiDocs(false);
        code.setGenerateModelDocs(false);
        code.setGenerateModels(true);
        code.setGenerateApis(true);
        //        code.setGenerateControllers(true);
        //        code.setVerbose(true);
        code.setMergeDoc(false);
        code.setModule(module);
        code.run(codegen);
    }

    public static void main(String[] args) {
        String module = "order";
        //        api(module);
        server(module);
        client(module);
    }
}