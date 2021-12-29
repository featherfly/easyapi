package cn.featherfly.easyapi.codegen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import cn.featherfly.common.io.FileUtils;
import cn.featherfly.common.lang.ClassLoaderUtils;

/**
 * The type MergeDocsTest.
 *
 * @author zhongj
 */
public class MergeDocsTest2 {

    public static void g(String yaml, String module) throws IOException {
        //        String fileName = "api/user.yaml";
        String fileName = yaml;
        String newFileName = yaml.replaceAll(".yaml", ".merged.yaml");

        String newFilePath = ClassLoaderUtils.getResource(".").getPath() + newFileName;

        File newFile = new File(newFilePath);
        FileUtils.makeDirectory(newFile);

        MergeDocs.mergeSchemas(fileName,
                new OutputStreamWriter(new FileOutputStream(new File(newFilePath)), StandardCharsets.UTF_8));

    }

    public static void main(String[] args) throws IOException {
        g("api/user.yaml", "user");

        g("api/advertising.yaml", "ad");

    }
}
