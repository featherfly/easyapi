package cn.featherfly.easyapi.codegen.v3;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cn.featherfly.common.model.app.Platform;
import cn.featherfly.conversion.codegen.BeanCodegen;
import cn.featherfly.conversion.codegen.BeanCodegenImpl;
import cn.featherfly.easyapi.PlatformToStringConvertorCodegen;
import cn.featherfly.easyapi.PlatformToStringPropertyCodegen;
import cn.featherfly.easyapi.codegen.Constants;
import cn.featherfly.easyapi.codegen.EasyapiGenerator;
import cn.featherfly.easyapi.codegen.ExtCodegenParameter;
import cn.featherfly.easyapi.codegen.ExtParameter;
import cn.featherfly.easyapi.codegen.v3.spring.AbstractSpringCodegen;
import cn.featherfly.easyapi.codegen.v3.spring.EasyapiSpringMvcCodegen;

public class SpringMvcCodegenTest {

    public void generate(String configFile, String yaml, String module) {
        AbstractSpringCodegen codegen = new EasyapiSpringMvcCodegen();
        codegen.addExtParameter(new ExtParameter("Login", new ExtCodegenParameter(true, false),
                "cn.featherfly.jbox.web.admin.permission.AdminLoginInfo", "AdminLoginInfo", "loginInfo", "当前登录用户信息"));
//        code.addExtParameter(new ExtParameter("@LoginAdmin", new ExtCodegenParameter(true, false),
//                "cn.featherfly.jbox.web.admin.permission.AdminLoginInfo", "AdminLoginInfo", "loginInfo"));
//        code.addExtParameter(new ExtParameter("@Login", new ExtCodegenParameter(true, false),
//                "cn.featherfly.jbox.user.domain.User", "User", "user"));
        codegen.setSourceFolder(".");
        codegen.setOutputDir(Constants.SPRINGMVC_OUT_DIR_V3);
        codegen.setModule(module);
        codegen.setSecondModule("secondmodule");

        BeanCodegen beanCodegen = BeanCodegenImpl.builder()
                .setIndentStart(1)
                .addPropertyCodegen(Platform.class, String.class, new PlatformToStringPropertyCodegen())
                .addPropertyCodegen(String.class, Platform.class, new PlatformToStringPropertyCodegen(true))
                .addConvertorCodegen(Platform.class, String.class, new PlatformToStringConvertorCodegen())
                .addConvertorCodegen(String.class, Platform.class, new PlatformToStringConvertorCodegen(true))
                .build();
        codegen.setBeanCodegen(beanCodegen);

        EasyapiGenerator generator = new EasyapiGenerator(codegen);
        generator.setGenerateApis(generateApis);
        generator.setGenerateModels(generateModels);
        generator.setGenerateApiDocs(generateApiDocs);
        generator.setGenerateModelDocs(generateModelDocs);
        generator.setGenerateTests(true);
        generator.setGenerateSupportingFiles(false);
        generator.setGenerateSwaggerMetadata(false);
//        generator.setMergeDoc(false);
        generator.setDebug(false);
        generator.setVerbose(false);

        // code.setTemplateDir("/codegen/JavaSpring");

//        codegen.setModule(module);
//        codegen.setSecondModule("secondmodule");
//        codegen.setSourceFolder("java");
//        codegen.setOutputDir(Constants.SPRINGMVC_OUT_DIR_V3);
//        codegen.addExtParameter(new ExtParameter("Login", new ExtCodegenParameter(true, false),
//                "cn.featherfly.jbox.web.admin.permission.AdminLoginInfo", "AdminLoginInfo", "loginInfo"));
        generator.generate(yaml, configFile);
    }

    private boolean generateApis = true;
    private boolean generateModels = true;
    private boolean generateApiDocs = true;
    private boolean generateModelDocs = true;

    @BeforeMethod
    public void beforeMethod() {
        generateApis = true;
        generateModels = true;
        generateApiDocs = true;
        generateModelDocs = true;
    }

    @Test
    public void genrateAll() {
        final String configFile = "api/admin-config.json";
        generate(configFile, "api/user.yaml", "user");
        generate(configFile, "api/order-api.yaml", "order");
//        generate("jbox/config.json", "jbox/rbac-application-api.yaml", "rbac");
//        generate("jbox/config.json", "jbox/rbac-component-api.yaml", "rbac");
//        generate("jbox/config.json", "jbox/message.yaml", "rbac");
    }

    @Test
    public void generateDtoOnly() {
        generateApis = false;
        final String configFile = "api/admin-config.json";
//        generate(configFile, "api/order-dto.yaml", "order");
//        generate(configFile, "api/rbac-role-api.yaml", "rbac");
        generate(configFile, "api/user.yaml", "user");
    }
}