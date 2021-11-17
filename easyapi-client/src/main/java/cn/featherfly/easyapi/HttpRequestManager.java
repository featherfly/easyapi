package cn.featherfly.easyapi;

import cn.featherfly.easyapi.client.HttpRequest;

/**
 * HttpRequestManager.
 *
 * @author zhongj
 */
public interface HttpRequestManager {

    /**
     * Gets the http request.
     *
     * @param apiType the api type
     * @return the http request
     */
    HttpRequest getHttpRequest(Class<?> apiType);
}
