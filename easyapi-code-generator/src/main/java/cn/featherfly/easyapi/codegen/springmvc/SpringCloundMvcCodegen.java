package cn.featherfly.easyapi.codegen.springmvc;

import cn.featherfly.common.structure.page.Page;
import cn.featherfly.common.structure.page.Pagination;
import cn.featherfly.easyapi.codegen.ExtCodegenParameter;
import cn.featherfly.easyapi.codegen.ExtParameter;

public class SpringCloundMvcCodegen extends SpringCloundCodegen {

    private boolean generateApi = true;

    //    public SpringCloundMvcCodegen(String module) {
    //        super(module);
    public SpringCloundMvcCodegen() {
        super();

        setTitle("easyapi spring clound server side");
        apiTemplateFiles.put("apiController.mustache", ".java");
        //        apiTemplateFiles.put("apiDelegate.mustache", ".java");

        addExtParameter(new ExtParameter("@Page", new ExtCodegenParameter(false, true), Page.class.getName(),
                Page.class.getSimpleName(), "page"));

        addExtParameter(new ExtParameter("@Pagination", new ExtCodegenParameter(false, true),
                Pagination.class.getName(), Pagination.class.getSimpleName(), "pagination"));// TODO 后续去掉

        addExtParameter(new ExtParameter("@Request", new ExtCodegenParameter(false, true),
                "javax.servlet.http.HttpServletRequest", "HttpServletRequest", "request"));

        addExtParameter(new ExtParameter("@Response", new ExtCodegenParameter(false, true),
                "javax.servlet.http.HttpServletResponse", "HttpServletResponse", "response"));

        addExtParameter(new ExtParameter("@MultipartFile", new ExtCodegenParameter(false, true),
                "org.springframework.web.multipart.MultipartFile", "MultipartFile", "file"));

        addExtParameter(new ExtParameter("@MultipartHttpServletRequest", new ExtCodegenParameter(false, true),
                "org.springframework.web.multipart.MultipartHttpServletRequest", "MultipartHttpServletRequest",
                "multipartRequest"));
    }

    @Override
    public String apiFilename(String templateName, String tag) {

        String result = super.apiFilename(templateName, tag);

        if (templateName.endsWith("Controller.mustache")) {
            int ix = result.lastIndexOf('.');
            result = result.substring(0, ix) + "Controller.java";
        } else if (templateName.endsWith("Delegate.mustache")) {
            int ix = result.lastIndexOf('.');
            result = result.substring(0, ix) + "Delegate.java";
        }

        return result;
    }

    @Override
    public void processOpts() {
        super.processOpts();
        if (!generateApi) {
            apiTemplateFiles.remove("api.mustache");
        }
    }

    /**
     * Getter for property 'generateApi'.
     *
     * @return Value for property 'generateApi'.
     */
    public boolean isGenerateApi() {
        return generateApi;
    }

    /**
     * Setter for property 'generateApi'.
     *
     * @param generateApi Value to set for property 'generateApi'.
     */
    public void setGenerateApi(boolean generateApi) {
        this.generateApi = generateApi;
    }
}
