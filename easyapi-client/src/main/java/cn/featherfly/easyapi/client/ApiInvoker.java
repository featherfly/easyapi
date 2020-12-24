package cn.featherfly.easyapi.client;

import java.util.Map;

import cn.featherfly.common.http.ErrorListener;
import cn.featherfly.common.http.HttpMethod;
import cn.featherfly.easyapi.Result;
import io.reactivex.rxjava3.core.Observable;

/**
 * The Class ApiInvoker.
 *
 * @param <T> the generic type
 * @author zhongj
 */
public abstract class ApiInvoker<T extends Result<?>> {

    /** The method. */
    protected HttpMethod method;

    /** The url. */
    protected String url;

    /** The headers. */
    protected Map<String, String> headers;

    /** The response type. */
    protected Class<T> responseType;

    /** The http request. */
    protected HttpRequest httpRequest;

    /**
     * Instantiates a new api invoker.
     *
     * @param method       the method
     * @param url          the url
     * @param headers      the headers
     * @param responseType the response type
     * @param httpRequest  the http request
     */
    public ApiInvoker(HttpMethod method, String url, Map<String, String> headers, Class<T> responseType,
            HttpRequest httpRequest) {
        this.method = method;
        this.url = url;
        this.headers = headers;
        this.responseType = responseType;
        this.httpRequest = httpRequest;
    }

    /**
     * Adds the header.
     *
     * @param name  the name
     * @param value the value
     * @return the api invoker
     */
    public ApiInvoker<T> addHeader(String name, String value) {
        this.headers.put(name, value);
        return this;
    }

    /**
     * Sets the method.
     *
     * @param method the method
     * @return the api invoker
     */
    public ApiInvoker<T> setMethod(HttpMethod method) {
        this.method = method;
        return this;
    }

    /**
     * Sets the url.
     *
     * @param url the url
     * @return the api invoker
     */
    public ApiInvoker<T> setUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * Gets the method.
     *
     * @return the method
     */
    public HttpMethod getMethod() {
        return method;
    }

    /**
     * Gets the url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Gets the headers.
     *
     * @return the headers
     */
    public Map<String, String> getHeaders() {
        return headers;
    }

    /**
     * Gets the response type.
     *
     * @return the response type
     */
    public Class<T> getResponseType() {
        return responseType;
    }

    /**
     * Gets the cache key.
     *
     * @return the cache key
     */
    public String getCacheKey() {
        return method + ":" + url;
    }

    /**
     * Gets the http request.
     *
     * @return the http request
     */
    public HttpRequest getHttpRequest() {
        return httpRequest;
    }

    /**
     * 同步调用，当出错时抛出异常.
     *
     * @return the t
     */
    public abstract T invoke();

    /**
     * 同步调用. 当错误时返回null.
     *
     * @param errorListener 错误监听器
     * @return the t
     */
    public abstract T invoke(ErrorListener errorListener);

    /**
     * 异步调用.
     *
     * @param callBack 回调
     */
    public abstract void invoke(CallBack<T> callBack);

    /**
     * Invoke completion.
     *
     * @return the http request completion
     */
    public abstract HttpRequestCompletion<T> complete();

    /**
     * Invoke completion.
     *
     * @return the http request completion
     */
    public abstract Observable<Completion<T>> observable();

}
