package cn.featherfly.easyapi.codegen.v3;

import cn.featherfly.easyapi.codegen.GenerateCode;
import cn.featherfly.easyapi.codegen.Constants;
import cn.featherfly.easyapi.codegen.v3.typescript.TypeScriptAxiosCodegen;

/**
 * The type TypeScriptAxiosCodegenTest.
 *
 * @author zhongj
 */
public class TypeScriptAxiosCodegenTest {

    public static void api(String yaml, String module) {
        //        TypeScriptAxiosCodegen codegen = new TypeScriptAxiosCodegen(module);
        TypeScriptAxiosCodegen codegen = new TypeScriptAxiosCodegen();

        GenerateCode code = new GenerateCode();
        code.setSpec(yaml);
        //        code.setConfigFile("src/test/resources/api/admin-config.json");
        code.setConfigFile("api/admin-config.json");
        code.setOutput(Constants.TS_AXIOS_OUT_DIR);
        code.setSourceFolder("ts-gen-api");
        code.setGenerateApiDocs(false);
        code.setGenerateModelDocs(false);
        code.setGenerateTests(false);

        code.setGenerateApis(true);
        code.setGenerateModels(true);
        //        code.setVerbose(true)

        code.setMergeDoc(true);
        code.setWrapResponse(true);
        code.setModule(module);
        code.setSecondModule("secondmodule");

        code.run(codegen);
    }

    public static void main(String[] args) {
        api("api/user.yaml", "user");
        api("api/advertising.yaml", "ad");
    }
}
