package cn.featherfly.easyapi.client;

import java.util.Map;

import cn.featherfly.common.http.ErrorListener;
import cn.featherfly.common.http.HttpMethod;
import cn.featherfly.easyapi.Result;
import io.reactivex.Observable;

/**
 * The Class HttpSendBodyApiInvoker.
 *
 * @author zhongj
 * @param <T> the generic type
 */
public class HttpSendBodyApiInvoker<T extends Result<?>> extends ApiInvoker<T> {

    /** The request body. */
    protected Object requestBody;

    /**
     * Instantiates a new http send body api invoker.
     *
     * @param method       the method
     * @param url          the url
     * @param requestBody  the request body
     * @param headers      the headers
     * @param responseType the response type
     * @param httpRequest  the http request
     */
    public HttpSendBodyApiInvoker(HttpMethod method, String url, Object requestBody, Map<String, String> headers,
            Class<T> responseType, HttpRequest httpRequest) {
        super(method, url, headers, responseType, httpRequest);
        this.requestBody = requestBody;
    }

    /**
     * Gets the request body.
     *
     * @return the request body
     */
    public Object getRequestBody() {
        return requestBody;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void invoke(CallBack<T> callBack) {
        httpRequest.send(method, url, requestBody, headers, responseType, callBack);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T invoke(ErrorListener callBack) {
        return httpRequest.send(method, url, requestBody, headers, responseType, callBack);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpRequestCompletion<T> invokeCompletion() {
        return httpRequest.sendCompletion(method, url, requestBody, headers, responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Observable<Completion<T>> invokeObservable() {
        return httpRequest.sendObservable(method, url, requestBody, headers, responseType);
    }
}
