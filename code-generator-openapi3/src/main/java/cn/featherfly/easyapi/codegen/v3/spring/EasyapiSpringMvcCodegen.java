package cn.featherfly.easyapi.codegen.v3.spring;

import cn.featherfly.common.structure.page.Page;
import cn.featherfly.common.structure.page.Pagination;
import cn.featherfly.conversion.codegen.BeanCodegen;
import cn.featherfly.easyapi.codegen.ExtCodegenParameter;
import cn.featherfly.easyapi.codegen.ExtParameter;

public class EasyapiSpringMvcCodegen extends AbstractSpringCodegen {

    //    public SpringCloundMvcCodegen(String module) {
    //        super(module);
    public EasyapiSpringMvcCodegen() {
        super();

        addExtParameter(new ExtParameter("Request", new ExtCodegenParameter(false, true),
                "jakarta.servlet.http.HttpServletRequest", "HttpServletRequest", "request", "http servlet request"));

        addExtParameter(new ExtParameter("Response", new ExtCodegenParameter(false, true),
                "jakarta.servlet.http.HttpServletResponse", "HttpServletResponse", "response", "http servlet response"));

        addExtParameter(new ExtParameter("MultipartFile", new ExtCodegenParameter(false, true),
                "org.springframework.web.multipart.MultipartFile", "MultipartFile", "file", "multipart file"));

        addExtParameter(new ExtParameter("MultipartHttpServletRequest", new ExtCodegenParameter(false, true),
                "org.springframework.web.multipart.MultipartHttpServletRequest", "MultipartHttpServletRequest",
                "multipartRequest", "multipart http servlet request"));

        addExtParameter(new ExtParameter("Page", new ExtCodegenParameter(false, true), Page.class.getName(),
                Page.class.getSimpleName(), "page", "auto generate page with request parameters"));

        addExtParameter(new ExtParameter("Pagination", new ExtCodegenParameter(false, true),
                Pagination.class.getName(), Pagination.class.getSimpleName(), "pagination", "auto generate pagination with request parameters"));
    }

    @Override
    public void processOpts() {
        super.processOpts();

        setTitle("easyapi spring clound server side");
        //apiTemplateFiles.put("api.mustache", ".java");
        //apiTemplateFiles.put("apiController.mustache", ".java");
        //apiTemplateFiles.put("apiDelegate.mustache", ".java");


        apiTemplateFiles.remove("api.mustache");
    }

    @Override
    public String apiFilename(String templateName, String tag) {
        /*
        加上上面的 apiTemplateFiles.remove("api.mustache")和这里的逻辑，使用apiDeletegate代替api定义
        */

        String result = super.apiFilename(templateName, tag);
        if (templateName.endsWith("Delegate.mustache")) {
            int ix = result.lastIndexOf("Delegate");
            result = result.substring(0, ix) + ".java";
        }
        return result;
    }
}
