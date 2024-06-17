package cn.featherfly.easyapi.v3.springmvc;

public class SpringCloundApiCodegen extends SpringCloundCodegen {

    //    public SpringCloundApiCodegen(String module) {
    //        super(module);
    public SpringCloundApiCodegen() {
        super();
        setTitle("easyapi spring clound api side");
        apiTemplateFiles.put("api.mustache", ".java");
    }
}
