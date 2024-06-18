package cn.featherfly.easyapi.codegen.v3.spring;

public class SpringCloundApiCodegen extends SpringCloundCodegen {

    //    public SpringCloundApiCodegen(String module) {
    //        super(module);
    public SpringCloundApiCodegen() {
        super();
        library = SPRING_CLOUD_LIBRARY;
        setTitle("easyapi spring clound api side");
        apiTemplateFiles.put("api.mustache", ".java");
    }
}
