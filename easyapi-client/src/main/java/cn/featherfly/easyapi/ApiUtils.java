package cn.featherfly.easyapi;

import cn.featherfly.common.http.HttpClient;
import cn.featherfly.easyapi.client.HttpRequest;

/**
 * The Class ApiUtils.
 */
public final class ApiUtils {

    private static HttpRequest defaultHttpRequest;

    private static DomainManager defaultDomainManager;

    private static Environment defaultEnvironment;

    private static HttpClient httpClient;

    /**
     * Gets the default http request.
     *
     * @param apiType the api type
     * @return the default http request
     */
    public static HttpRequest getDefaultHttpRequest(Class<?> apiType) {
        return getDefaultHttpRequest();
    }

    /**
     * Gets the default http request.
     *
     * @return the default http request
     */
    public static HttpRequest getDefaultHttpRequest() {
        if (defaultHttpRequest == null) {
            throw new IllegalArgumentException("defaultHttpRequest未设置");
        }
        return defaultHttpRequest;
    }

    /**
     * Gets the default domain manager.
     *
     * @return the default domain manager
     */
    public static DomainManager getDefaultDomainManager() {
        if (defaultDomainManager == null) {
            throw new IllegalArgumentException("defaultDomainManager未设置");
        }
        return defaultDomainManager;
    }

    /**
     * Sets the default http request.
     *
     * @param defaultHttpRequest the new default http request
     */
    public static void setDefaultHttpRequest(HttpRequest defaultHttpRequest) {
        ApiUtils.defaultHttpRequest = defaultHttpRequest;
    }

    /**
     * Sets the default domain manager.
     *
     * @param defaultDomainManager the new default domain manager
     */
    public static void setDefaultDomainManager(DomainManager defaultDomainManager) {
        ApiUtils.defaultDomainManager = defaultDomainManager;
    }

    /**
     * Gets the default environment.
     *
     * @return the default environment
     */
    public static Environment getDefaultEnvironment() {
        if (defaultEnvironment == null) {
            throw new IllegalArgumentException("defaultEnvironment未设置");
        }
        return defaultEnvironment;
    }

    /**
     * Sets the default environment.
     *
     * @param defaultEnvironment the new default environment
     */
    public static void setDefaultEnvironment(Environment defaultEnvironment) {
        ApiUtils.defaultEnvironment = defaultEnvironment;
    }

    /**
     * Gets the default http client.
     *
     * @return the default http client
     */
    public static HttpClient getDefaultHttpClient() {
        if (httpClient == null) {
            throw new IllegalArgumentException("defaultHttpRequestConfig未设置");
        }
        return httpClient;
    }

    /**
     * Sets the default http request config.
     *
     * @param httpClient the new default http request config
     */
    public static void setDefaultHttpRequestConfig(HttpClient httpClient) {
        ApiUtils.httpClient = httpClient;
    }
}
