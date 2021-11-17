package cn.featherfly.easyapi;

import cn.featherfly.common.http.HttpClients;
import cn.featherfly.easyapi.client.HttpRequest;

/**
 * The Class ApiUtils.
 */
public final class ApiUtils {

    private static HttpRequestManager httpRequestManager;

    private static DomainManager domainManager;

    private static Environment environment;

    private static HttpClients httpClient;

    /**
     * Gets the default http request.
     *
     * @param apiType the api type
     * @return the default http request
     */
    public static HttpRequest getDefaultHttpRequest(Class<?> apiType) {
        return getHttpRequestManager().getHttpRequest(apiType);
    }

    public static HttpRequestManager getHttpRequestManager() {
        if (httpRequestManager == null) {
            throw new IllegalArgumentException("defaultHttpRequestManager not set");
        }
        return httpRequestManager;
    }

    /**
     * Gets the default domain manager.
     *
     * @return the default domain manager
     */
    public static DomainManager getDomainManager() {
        if (domainManager == null) {
            throw new IllegalArgumentException("defaultDomainManager not set");
        }
        return domainManager;
    }

    /**
     * Sets the http request manager.
     *
     * @param httpRequestManager the new http request manager
     */
    public static void setHttpRequestManager(HttpRequestManager httpRequestManager) {
        ApiUtils.httpRequestManager = httpRequestManager;
    }

    /**
     * Sets the default domain manager.
     *
     * @param domainManager the new default domain manager
     */
    public static void setDomainManager(DomainManager domainManager) {
        ApiUtils.domainManager = domainManager;
    }

    /**
     * Gets the environment.
     *
     * @return the environment
     */
    public static Environment getEnvironment() {
        if (environment == null) {
            throw new IllegalArgumentException("environment not set");
        }
        return environment;
    }

    /**
     * Sets the environment.
     *
     * @param environment the new environment
     */
    public static void setEnvironment(Environment environment) {
        ApiUtils.environment = environment;
    }

    /**
     * Gets the default http client.
     *
     * @return the default http client
     */
    public static HttpClients getDefaultHttpClient() {
        if (httpClient == null) {
            throw new IllegalArgumentException("defaultHttpRequestConfig not set");
        }
        return httpClient;
    }

    /**
     * Sets the http request config.
     *
     * @param httpClient the new http request config
     */
    public static void setHttpClient(HttpClients httpClient) {
        ApiUtils.httpClient = httpClient;
    }
}
