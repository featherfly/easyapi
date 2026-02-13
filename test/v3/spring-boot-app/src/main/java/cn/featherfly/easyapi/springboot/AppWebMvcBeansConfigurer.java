package cn.featherfly.easyapi.springboot;

import cn.featherfly.web.pagination.RequestParameterPageFactory;
import cn.featherfly.web.spring.method.support.PageHandlerMethodArgumentResolver;
import cn.featherfly.web.spring.returnvaluehandler.ResponseBodyWrapFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;

/**
 * The type AdminWebMvcBeansConfigurer.
 *
 * @author zhongj
 */
@Configuration
@Order(0)
public class AppWebMvcBeansConfigurer {

    @Bean
    public ResponseBodyWrapFactoryBean responseBodyWrapFactoryBean() {
        return new ResponseBodyWrapFactoryBean();
    }

    @Bean
    public PageHandlerMethodArgumentResolver pageHandlerMethodArgumentResolver(ContentNegotiationManager mvcContentNegotiationManager) {
        RequestParameterPageFactory pageFactory = new RequestParameterPageFactory();
        pageFactory.setPageSizeName("p");
        pageFactory.setPageNumberName("ps");
        pageFactory.setAllowMaxPageSize(100);
        pageFactory.setAllowDaynmicPageSize(true);

        PageHandlerMethodArgumentResolver pageHandlerMethodArgumentResolver = new PageHandlerMethodArgumentResolver();
        pageHandlerMethodArgumentResolver.setContentNegotiationManager(mvcContentNegotiationManager);
        pageHandlerMethodArgumentResolver.setPageFactory(pageFactory);
        return pageHandlerMethodArgumentResolver;
    }


}
