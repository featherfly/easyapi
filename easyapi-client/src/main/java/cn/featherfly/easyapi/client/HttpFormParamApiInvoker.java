package cn.featherfly.easyapi.client;

import java.io.Serializable;
import java.util.Map;

import cn.featherfly.common.http.ErrorListener;
import cn.featherfly.common.http.HttpMethod;
import cn.featherfly.easyapi.Result;
import io.reactivex.rxjava3.core.Observable;

/**
 * The Class HttpFormParamApiInvoker.
 *
 * @param <T> the generic type
 * @author zhongj
 */
public class HttpFormParamApiInvoker<T extends Result<?>> extends ApiInvoker<T> {

    /** The params. */
    protected Map<String, Serializable> params;

    /**
     * Instantiates a new http form param api invoker.
     *
     * @param method       the method
     * @param url          the url
     * @param params       the params
     * @param headers      the headers
     * @param responseType the response type
     * @param httpRequest  the http request
     */
    public HttpFormParamApiInvoker(HttpMethod method, String url, Map<String, Serializable> params,
            Map<String, String> headers, Class<T> responseType, HttpRequest httpRequest) {
        super(method, url, headers, responseType, httpRequest);
        this.params = params;
    }

    /**
     * Gets the params.
     *
     * @return the params
     */
    public Map<String, Serializable> getParams() {
        return params;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T invoke() {
        return httpRequest.send(method, url, params, headers, responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T invoke(ErrorListener callBack) {
        return httpRequest.send(method, url, params, headers, responseType, callBack);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void invoke(CallBack<T> callBack) {
        httpRequest.send(method, url, params, headers, responseType, callBack);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpRequestCompletion<T> complete() {
        return httpRequest.sendCompletion(method, url, params, headers, responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Observable<Completion<T>> observable() {
        return httpRequest.sendObservable(method, url, params, headers, responseType);
    }
}
