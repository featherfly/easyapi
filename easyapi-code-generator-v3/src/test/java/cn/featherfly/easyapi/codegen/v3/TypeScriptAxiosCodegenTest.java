package cn.featherfly.easyapi.codegen.v3;

import cn.featherfly.easyapi.codegen.EasyapiGenerator;
import cn.featherfly.easyapi.codegen.Constants;
import cn.featherfly.easyapi.codegen.v3.typescript.TypeScriptAxiosCodegen;

/**
 * The type TypeScriptAxiosCodegenTest.
 *
 * @author zhongj
 */
public class TypeScriptAxiosCodegenTest {

    public static void api(String yaml, String module) {
        final String configFile = "api/admin-config.json";
        //        TypeScriptAxiosCodegen codegen = new TypeScriptAxiosCodegen(module);
        TypeScriptAxiosCodegen codegen = new TypeScriptAxiosCodegen();
        codegen.setOutputDir(Constants.TS_AXIOS_OUT_DIR);
        codegen.setWrapResponse(true);
        codegen.setModule(module);
        codegen.setSecondModule("secondmodule");
        codegen.setOutputDir(Constants.TS_AXIOS_OUT_DIR);

        EasyapiGenerator code = new EasyapiGenerator(codegen);
        //        code.setConfigFile("src/test/resources/api/admin-config.json");
//        code.setOutput(Constants.TS_AXIOS_OUT_DIR);
//        code.setSourceFolder("ts-gen-api");
        code.setGenerateApiDocs(false);
        code.setGenerateModelDocs(false);
        code.setGenerateTests(false);

        code.setGenerateApis(true);
        code.setGenerateModels(true);
        //        code.setVerbose(true)

        code.setMergeDoc(true);
//        code.setWrapResponse(true);
//        code.setModule(module);
//        code.setSecondModule("secondmodule");
        code.generate(yaml, configFile);
    }

    public static void main(String[] args) {
        api("api/user.yaml", "user");
        api("api/advertising.yaml", "ad");
    }
}
