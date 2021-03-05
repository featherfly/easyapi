package cn.featherfly.easyapi.client;

import java.util.Map;

import cn.featherfly.common.http.HttpClients;
import cn.featherfly.common.http.HttpMethod;
import cn.featherfly.easyapi.Environment;
import cn.featherfly.easyapi.Result;

/**
 * The Class DefaultHttpRequest.
 *
 * @author zhongj
 */
public class DefaultHttpRequest extends AbstractHttpRequest {

    /**
     * Instantiates a new default http request.
     *
     * @param environment the environment
     * @param client      the client
     */
    public DefaultHttpRequest(Environment environment, HttpClients client) {
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
