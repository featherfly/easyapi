package cn.featherfly.easyapi.springboot;

import cn.featherfly.common.exception.UnsupportedException;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.spring.converter.StringToDateConverterFactory;
import cn.featherfly.common.spring.converter.StringToEnumConverterFactory;
import cn.featherfly.common.structure.HashChainMap;
import cn.featherfly.web.pagination.RequestParameterPageFactory;
import cn.featherfly.web.spring.handlerexception.BindExceptionHandlerExceptionResolver;
import cn.featherfly.web.spring.handlerexception.ExceptionHandlerExceptionResolver;
import cn.featherfly.web.spring.handlerexception.MethodArgumentNotValidExceptionHandlerExceptionResolver;
import cn.featherfly.web.spring.messageconverter.ExcelHttpMessageConverter;
import cn.featherfly.web.spring.messageconverter.JxlsHttpMessageConverter;
import cn.featherfly.web.spring.method.support.PageHandlerMethodArgumentResolver;
import cn.featherfly.web.spring.servlet.view.json.ObjectMapperConfiguration;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.core.util.Json;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverters;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.JacksonJsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.json.JsonMapper;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * The type Admin web mvc configurer.
 *
 * @author zhongj
 */
@Configuration
@Order(1)
public class AppWebMvcConfigurer implements WebMvcConfigurer {

    private PageHandlerMethodArgumentResolver pageHandlerMethodArgumentResolver;

    public AppWebMvcConfigurer() {
        RequestParameterPageFactory pageFactory = new RequestParameterPageFactory();
        pageFactory.setPageSizeName("p");
        pageFactory.setPageNumberName("ps");
        pageFactory.setAllowMaxPageSize(100);
        pageFactory.setAllowDaynmicPageSize(true);

        pageHandlerMethodArgumentResolver = new PageHandlerMethodArgumentResolver();
//        pageHandlerMethodArgumentResolver.setContentNegotiationManager(mvcContentNegotiationManager);
        pageHandlerMethodArgumentResolver.setPageFactory(pageFactory);
    }

    // --------------------------------------------------------------------------------------------------
    // 配置异常处理
    public MethodArgumentNotValidExceptionHandlerExceptionResolver methodArgumentNotValidExceptionHandlerExceptionResolver() {
        MethodArgumentNotValidExceptionHandlerExceptionResolver resolver = new MethodArgumentNotValidExceptionHandlerExceptionResolver();
        resolver.setHttpStatus(400);
        resolver.setOrder(0);
        return resolver;
    }


    public BindExceptionHandlerExceptionResolver bindExceptionHandlerExceptionResolver() {
        BindExceptionHandlerExceptionResolver resolver = new BindExceptionHandlerExceptionResolver();
        resolver.setHttpStatus(400);
        resolver.setOrder(1);
        return resolver;
    }


    public ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver() {
        ExceptionHandlerExceptionResolver resolver = new ExceptionHandlerExceptionResolver();
        resolver.setHttpStatus(400);
        resolver.setOrder(2);
        resolver.setExceptionHttpStatusMap(new HashChainMap<Class<?>, Integer>()
                .putChain(UnsupportedException.class, 401)
        );
        return resolver;
    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
//        System.out.println(resolvers.size());
//        resolvers.add(0, methodArgumentNotValidExceptionHandlerExceptionResolver());
//        resolvers.add(1, bindExceptionHandlerExceptionResolver());
//        resolvers.add(2, exceptionHandlerExceptionResolver());
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(0, methodArgumentNotValidExceptionHandlerExceptionResolver());
        resolvers.add(1, bindExceptionHandlerExceptionResolver());
        resolvers.add(2, exceptionHandlerExceptionResolver());
    }

    // 配置异常处理
    // --------------------------------------------------------------------------------------------------

    // --------------------------------------------------------------------------------------------------
    // 配置转换器

    @Override
    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverterFactory(new StringToEnumConverterFactory());
//        StringToDateConverterFactory stringToDateConverterFactory = new StringToDateConverterFactory();
//        stringToDateConverterFactory.setPatterns((String[]) ArrayUtils.concat(new String[]{"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"}, stringToDateConverterFactory.getPatterns()));
//        registry.addConverterFactory(stringToDateConverterFactory);
    }

//    private JacksonJsonHttpMessageConverter customerMappingJackson2HttpMessageConverter() {
//        JacksonJsonHttpMessageConverter messageConverter = new JacksonJsonHttpMessageConverter(JsonMapper.builder()
//                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false)
//                .defaultDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
//        ObjectMapperConfiguration configuration = new ObjectMapperConfiguration();
////        configuration.setWriteEnumsUseingIndex(true);
//        configuration.setWriteDatesAsTimestamps(false);
////        configuration.setDateFormat("yyyy-MM-dd HH:mm:ss");
//        configuration.setInclude(JsonInclude.Include.NON_EMPTY);
//        configuration.setFailOnUnknownProperties(false);
////        messageConverter.setObjectMapper(configuration.create());
//        return messageConverter;
//    }

    private JacksonJsonHttpMessageConverter customerMappingJackson2HttpMessageConverter() {
        JacksonJsonHttpMessageConverter messageConverter =
                new JacksonJsonHttpMessageConverter(JsonMapper.builder()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false)
                .defaultDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                ).build());
//        ObjectMapperConfiguration configuration = new ObjectMapperConfiguration();
////        configuration.setWriteEnumsUseingIndex(true);
//        configuration.setWriteDatesAsTimestamps(false);
////        configuration.setDateFormat("yyyy-MM-dd HH:mm:ss");
//        configuration.setInclude(JsonInclude.Include.NON_EMPTY);
//        configuration.setFailOnUnknownProperties(false);
//        messageConverter.setObjectMapper(configuration.create());

        return messageConverter;
    }
//    private MappingJackson2HttpMessageConverter customerMappingJackson2HttpMessageConverter() {
//        MappingJackson2HttpMessageConverter messageConverter =
//                new MappingJackson2HttpMessageConverter(JsonMapper.builder()
//                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false)
//                .defaultDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).build());
//        ObjectMapperConfiguration configuration = new ObjectMapperConfiguration();
////        configuration.setWriteEnumsUseingIndex(true);
//        configuration.setWriteDatesAsTimestamps(false);
////        configuration.setDateFormat("yyyy-MM-dd HH:mm:ss");
//        configuration.setInclude(JsonInclude.Include.NON_EMPTY);
//        configuration.setFailOnUnknownProperties(false);
////        messageConverter.setObjectMapper(configuration.create());
//
//
//        return messageConverter;
//    }

    private StringHttpMessageConverter stringHttpMessageConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter();
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.valueOf("application/json;charset=UTF-8"));
        converter.setSupportedMediaTypes(mediaTypes);
        return converter;
    }

    private JxlsHttpMessageConverter jxlsHttpMessageConverter() {
        JxlsHttpMessageConverter jxls = new JxlsHttpMessageConverter();
        jxls.setExtNames(new String[]{"xlsx"});
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(new MediaType("application", "jxls"));
        jxls.setSupportedMediaTypes(mediaTypes);
        jxls.setTemplateBasePath("jxls_template");
        // excel使用的数据的搜索路径
        jxls.setResolverPath("data");
        jxls.setResolverPathKey("_rp");
        return jxls;
    }

    private ExcelHttpMessageConverter excelHttpMessageConverter() {
        ExcelHttpMessageConverter excel = new ExcelHttpMessageConverter();
        excel.setExtNames(new String[]{"xls"});
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(new MediaType("application", "excel"));
        excel.setSupportedMediaTypes(mediaTypes);
        // excel使用的数据的搜索路径
        excel.setResolverPath("data");
        excel.setResolverPathKey("_rp");
        return excel;
    }

    @Override
    public void configureMessageConverters(HttpMessageConverters.ServerBuilder builder) {
                builder.addCustomConverter(customerMappingJackson2HttpMessageConverter());
        builder.addCustomConverter(stringHttpMessageConverter());
        builder.addCustomConverter(jxlsHttpMessageConverter());
        builder.addCustomConverter(excelHttpMessageConverter());

//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(customerMappingJackson2HttpMessageConverter());
//        converters.add(stringHttpMessageConverter());
//        converters.add(jxlsHttpMessageConverter());
//        converters.add(excelHttpMessageConverter());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.ignoreAcceptHeader(false);
//        configurer.favorPathExtension(true);
        configurer.favorParameter(true);
        configurer.parameterName("_f");
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
        configurer.mediaTypes(new HashChainMap<String, MediaType>()
                        .putChain("json", MediaType.APPLICATION_JSON)
                        .putChain("xml", MediaType.APPLICATION_XML)
                        .putChain("xls", MediaType.valueOf("application/jxls"))
                        .putChain("xlsx", MediaType.valueOf("application/excel"))
        );
//        final ContentNegotiationManagerFactoryBean factory = new ContentNegotiationManagerFactoryBean();
//        factory.setIgnoreAcceptHeader(false);
//        factory.setFavorParameter(true);
//        factory.setParameterName("_f");
//        factory.setDefaultContentType(MediaType.APPLICATION_JSON);
//        factory.addMediaTypes(new HashChainMap<String, MediaType>()
//                .putChain("json", MediaType.APPLICATION_JSON)
//                .putChain("xml", MediaType.APPLICATION_XML)
//                .putChain("xls", MediaType.valueOf("application/jxls"))
//                .putChain("xlsx", MediaType.valueOf("application/excel"))
//        );
        final Field field = ClassUtils.getField(configurer.getClass(), "factory");
        field.setAccessible(true);
        final ContentNegotiationManagerFactoryBean factory;
        try {
            factory = (ContentNegotiationManagerFactoryBean) field.get(configurer);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        pageHandlerMethodArgumentResolver.setContentNegotiationManager(factory.build());
    }

    // 配置转换器
    // --------------------------------------------------------------------------------------------------

    // --------------------------------------------------------------------------------------------------
    // 配置拦截器

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authenticatedCheckerInterceptor).addPathPatterns("/api/v1/**", "/v1/api/**")
//                .excludePathPatterns(adminConfig.getAuthorizedExcludesUrl());
//        registry.addInterceptor(authorityCheckerInterceptor).addPathPatterns("/api/v1/**", "/v1/api/**")
//                .excludePathPatterns(adminConfig.getAuthorizedExcludesUrl());

//        registry.addInterceptor(new CrosHostInterceptor()).addPathPatterns("/api/v1/**", "/v1/api/**");

//        registry.addInterceptor()
    }

    // 配置拦截器
    // --------------------------------------------------------------------------------------------------

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        // ContentNegotiationManager 内部创建后，这里拿不到，要想办法内部拿到
        // 外部定义Bean也不行，因为此类的各种配置执行完了才

        resolvers.add(pageHandlerMethodArgumentResolver);

//        LoginUserFromRequestHandlerMethodArgumentResolver loginUserFromRequestHandlerMethodArgumentResolver =
//                new LoginUserFromRequestHandlerMethodArgumentResolver();
//        resolvers.add(loginUserFromRequestHandlerMethodArgumentResolver);

//        LoginInfoHandlerMethodArgumentResolver loginInfoHandlerMethodArgumentResolver =
//                new LoginInfoHandlerMethodArgumentResolver();
//        loginInfoHandlerMethodArgumentResolver.setLoginManager(loginManager);
//        resolvers.add(loginInfoHandlerMethodArgumentResolver);
    }

//    @Bean
//    public FormattingConversionServiceFactoryBean conversionService() {
//        FormattingConversionServiceFactoryBean conversionServiceFactoryBean = new FormattingConversionServiceFactoryBean();
//        Set<ConverterFactory> converters = new HashSet<>();
//        converters.add(new StringToEnumConverterFactory());
//        converters.add(new StringToDateConverterFactory());
//        conversionServiceFactoryBean.setConverters(converters);
//        return conversionServiceFactoryBean;
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedHeaders("*")
//                .allowedMethods("GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH")
//                .allowCredentials(true)
//                .maxAge(3600)
//                .allowedOriginPatterns("http://*:8000","http://*:8882");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");

        registry.addResourceHandler("/statics/**")
                .addResourceLocations("classpath:/statics/");

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/swagger/**")
                .addResourceLocations("classpath:/static/swagger/");
    }
}
