
/*
 * All rights Reserved, Designed By zhongj
 * @Title: HttpRequestManagerImpl.java
 * @Package cn.featherfly.easyapi
 * @Description: HttpRequestManagerImpl
 * @author: zhongj
 * @date: 2021-11-17 15:13:17
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.easyapi;

import java.util.HashMap;
import java.util.Map;

import cn.featherfly.easyapi.client.HttpRequest;

/**
 * HttpRequestManagerImpl.
 *
 * @author zhongj
 */
public class HttpRequestManagerImpl implements HttpRequestManager {

    private HttpRequest httpRequest;

    private final Map<Class<?>, HttpRequest> apiTypeHttpRequests = new HashMap<>(0);

    /**
     * Instantiates a new http request manager impl.
     *
     * @param httpRequest the http request
     */
    public HttpRequestManagerImpl(HttpRequest httpRequest) {
        super();
        this.httpRequest = httpRequest;
    }

    /**
     * Adds the api type.
     *
     * @param apiType     the api type
     * @param httpRequest the http request
     * @return the http request manager impl
     */
    public HttpRequestManagerImpl addApiType(Class<?> apiType, HttpRequest httpRequest) {
        apiTypeHttpRequests.put(apiType, httpRequest);
        return this;
    }

    /**
     * Contains api type.
     *
     * @param apiType the api type
     * @return true, if successful
     */
    public boolean containsApiType(Class<?> apiType) {
        return apiTypeHttpRequests.containsKey(apiType);
    }

    /**
     * Gets the domain by group.
     *
     * @param apiType the api type
     * @return the domain by group
     */
    public HttpRequest getHttpRequestByApiType(Class<?> apiType) {
        return apiTypeHttpRequests.get(apiType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpRequest getHttpRequest(Class<?> apiType) {
        HttpRequest request = getHttpRequestByApiType(apiType);
        if (request != null) {
            return request;
        } else {
            return httpRequest;
        }
    }

}
