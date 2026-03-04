package cn.featherfly.easyapi.codegen.v3;

import cn.featherfly.easyapi.codegen.v3.spring.EasyapiSpringMvcCodegen;
import io.swagger.codegen.v3.CliOption;

/**
 * The type Show.
 *
 * @author zhongj
 */
public class Show {

    public static void main(String[] args) {
        EasyapiSpringMvcCodegen codegen = new EasyapiSpringMvcCodegen();
        System.out.println("----------------------------");
        for (CliOption langCliOption : codegen.cliOptions()) {
            System.out.println(langCliOption.getOpt() + " : " + langCliOption.getType() + " : " + langCliOption.getDefault() + " : " + langCliOption.getOptionHelp());
        }
        System.out.println("----------------------------");

//        SwaggerCodegen.main(new String[]{"generate",
//                "-c", new File(ClassLoaderUtils.getResource("api/admin-config.json").getPath()).getAbsolutePath(),
//                "-i", new File(ClassLoaderUtils.getResource("api/order-api.yaml").getPath()).getAbsolutePath(),
//                "-l", "spring",
//                "-o", new File(ClassLoaderUtils.getResource("").getPath()).getAbsolutePath()
//        });
    }
}
