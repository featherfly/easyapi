package cn.featherfly.easyapi.v3.springmvc;

import cn.featherfly.easyapi.WrapResponseAbility;

public class SpringCloundClientCodegen extends SpringCloundCodegen implements WrapResponseAbility {

    private boolean wrapResponse = true;

    //    public SpringCloundClientCodegen(String module) {
    //        super(module);
    public SpringCloundClientCodegen() {
        super();

        setTitle("easyapi spring clound client side");
        apiTemplateFiles.put("api_client.mustache", ".java");
        apiTemplateFiles.put("apiFeignClient.mustache", ".java");
        modelTemplateFiles.put("model-response.mustache", ".java");

    }

    @Override
    public String apiFilename(String templateName, String tag) {
        String result = super.apiFilename(templateName, tag);
        if (templateName.endsWith("FeignClient.mustache")) {
            int ix = result.lastIndexOf('.');
            result = result.substring(0, ix) + "FeignClient.java";
        }
        return result;
    }

    @Override
    public void processOpts() {
        super.processOpts();
        apiTemplateFiles.remove("api.mustache");
    }

    @Override
    public boolean isWrapResponse() {
        return wrapResponse;
    }

    @Override
    public void setWrapResponse(boolean wrapResponse) {
        this.wrapResponse = wrapResponse;
    }

    @Override
    public String toModelFilename(String name, String templateName) {
        if (templateName.equals("model-response.mustache")) {
            name += "Response";
        }
        return name;
    }
}
