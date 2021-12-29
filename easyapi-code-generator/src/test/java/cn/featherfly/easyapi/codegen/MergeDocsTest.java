package cn.featherfly.easyapi.codegen;

import java.io.IOException;

import cn.featherfly.easyapi.codegen.springmvc.SpringCloundMvcCodegen;

/**
 * The type MergeDocsTest.
 *
 * @author zhongj
 */
public class MergeDocsTest {

    public static void g(String yaml, String module) throws IOException {
        //        String fileName = "api/user.yaml";
        //        String fileName = yaml;
        //        String newFileName = yaml.replaceAll(".yaml", ".merged.yaml");
        //
        //        String newFilePath = ClassLoaderUtils.getResource(".").getPath() + newFileName;
        //
        //        File newFile = new File(newFilePath);
        //        FileUtils.makeDirectory(newFile);
        //
        //        MergeDocs.mergeSchemas(fileName, new OutputStreamWriter(new FileOutputStream(
        //                new File(newFilePath)),
        //                StandardCharsets.UTF_8));

        GenerateCode code = new GenerateCode();
        code.setConfigFile("api/admin-config.json");
        code.addExtParameter(new ExtParameter("@LoginAdmin", new ExtCodegenParameter(true, false),
                "cn.featherfly.jbox.web.admin.permission.AdminLoginInfo", "AdminLoginInfo", "loginInfo"));
        code.addExtParameter(new ExtParameter("@Login", new ExtCodegenParameter(true, false),
                "cn.featherfly.jbox.user.domain.User", "User", "user"));
        //        code.setOutput("../base-commons/api-code-gen/src/gen");
        code.setOutput(Constants.GEN_OUT_DIR);
        code.setSourceFolder("java-gen-springmvc");
        code.setGenerateApiDocs(false);
        code.setGenerateModelDocs(false);
        code.setGenerateModels(true);
        code.setGenerateApis(true);
        code.setGenerateSupportingFiles(true);
        //        code.setVerbose(true);

        //        code.setTemplateDir("/codegen/JavaSpring");

        //        code.setSpec("api/account-api.yaml");
        //        code.run(new SpringCloundMvcCodegen("account"));

        //        code.setSpec("api/user.yaml");
        //        code.setSpec(newFileName);
        code.setMergeDoc(true);
        code.setSpec(yaml);
        code.setModule(module);
        code.run(new SpringCloundMvcCodegen());
    }

    public static void main(String[] args) throws IOException {
        //        System.err.println(MergeDocs.mergeSchemas("api/user.yaml"));

        g("api/user.yaml", "user");

        g("api/advertising.yaml", "ad");

        // TODO allOf: - $ref: 合并还有问题

    }
}
