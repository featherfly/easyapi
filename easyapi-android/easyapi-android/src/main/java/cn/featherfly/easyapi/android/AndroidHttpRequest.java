package cn.featherfly.easyapi.android;

import cn.featherfly.common.http.HttpClient;
import cn.featherfly.common.http.HttpMethod;
import cn.featherfly.common.http.HttpRequestConfig;
import cn.featherfly.easyapi.Environment;
import cn.featherfly.easyapi.Result;

import java.util.Map;

/**
 * The type Abstract http request.
 *
 * @author zhongj
 */
public class AndroidHttpRequest extends AbstractAndroidHttpRequest {

    /**
     * Instantiates a new Abstract http request.
     *
     * @param environment the environment
     * @param config      the config
     */
    public AndroidHttpRequest(Environment environment, HttpRequestConfig config) {
        super(environment, config);
    }

    /**
     * Instantiates a new Abstract http request.
     *
     * @param environment the environment
     * @param client      the client
     */
    public AndroidHttpRequest(Environment environment, HttpClient client) {
        super(environment, client);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected <T extends Result<?>> String preSend(HttpMethod method, String url, Object params,
                                                   Map<String, String> headers, Class<T> responseType) {
        logger.debug("request method {}, url {}, params {}, headers {}, responseType {}", new Object[] { method.name(),
                url, params == null ? "" : params, headers == null ? "" : headers, responseType.getName() });
        return url;
    }

}
