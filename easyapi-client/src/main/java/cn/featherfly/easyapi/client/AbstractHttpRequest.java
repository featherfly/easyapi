package cn.featherfly.easyapi.client;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.http.ErrorListener;
import cn.featherfly.common.http.HttpClient;
import cn.featherfly.common.http.HttpErrorResponse;
import cn.featherfly.common.http.HttpMethod;
import cn.featherfly.common.http.HttpRequestConfig;
import cn.featherfly.easyapi.Environment;
import cn.featherfly.easyapi.Result;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * AbstractHttpRequest.
 *
 * @author zhongj
 */
public abstract class AbstractHttpRequest implements HttpRequest {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private HttpClient client;

    /** The environment. */
    protected Environment environment;

    /**
     * Instantiates a new abstract http request.
     *
     * @param environment the environment
     * @param config      the config
     */
    public AbstractHttpRequest(Environment environment, HttpRequestConfig config) {
        this(environment, new HttpClient(config, new HashMap<>()));
    }

    /**
     * Instantiates a new abstract http request.
     *
     * @param environment the environment
     * @param client      the client
     */
    public AbstractHttpRequest(Environment environment, HttpClient client) {
        this.environment = environment;
        this.client = client;
    }

    //    /**
    //     * Post body.
    //     *
    //     * @param <R>          the generic type
    //     * @param <T>          the generic type
    //     * @param url          the url
    //     * @param requestBody  the request body
    //     * @param headers      the headers
    //     * @param responseType the response type
    //     * @param callBack     the call back
    //     */
    //    public <R, T extends Result<?>> void postBody(String url, R requestBody, Map<String, String> headers,
    //            Class<T> responseType, final CallBack<T> callBack) {
    //        send(HttpMethod.POST, url, requestBody, headers, responseType, callBack);
    //    }
    //
    //    /**
    //     * Put body.
    //     *
    //     * @param <R>          the generic type
    //     * @param <T>          the generic type
    //     * @param url          the url
    //     * @param requestBody  the request body
    //     * @param headers      the headers
    //     * @param responseType the response type
    //     * @param callBack     the call back
    //     */
    //    public <R, T extends Result<?>> void putBody(String url, R requestBody, Map<String, String> headers,
    //            Class<T> responseType, final CallBack<T> callBack) {
    //        send(HttpMethod.PUT, url, requestBody, headers, responseType, callBack);
    //    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R, T extends Result<?>> T send(HttpMethod method, String url, R requestBody, Map<String, String> headers,
            Class<T> responseType) {
        final String finalUrl = preSend(method, url, requestBody, headers, responseType);
        return client.request(method, finalUrl, requestBody, headers, responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Result<?>> T send(HttpMethod method, String url, Map<String, Serializable> params,
            Map<String, String> headers, Class<T> responseType) {
        final String finalUrl = preSend(method, url, params, headers, responseType);
        return client.request(method, finalUrl, params, headers, responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R, T extends Result<?>> void send(HttpMethod method, String url, R requestBody, final Class<T> responseType,
            final CallBack<T> callBack) {
        send(method, url, requestBody, null, responseType, callBack);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R, T extends Result<?>> void send(HttpMethod method, String url, R requestBody, Map<String, String> headers,
            final Class<T> responseType, final CallBack<T> callBack) {
        final String finalUrl = preSend(method, url, requestBody, headers, responseType);
        client.requestObservable(method, finalUrl, requestBody, headers, responseType).subscribe(t -> {
            if (t.isSuccess()) {
                callBack.success(t);
            } else {
                callBack.failure(t);
            }
        }, e -> {
            callBack.error(new HttpErrorResponse(e.getMessage()));
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R, T extends Result<?>> T send(HttpMethod method, String url, R requestBody, Class<T> responseType,
            ErrorListener errorListener) {
        return send(method, url, requestBody, null, responseType, errorListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R, T extends Result<?>> T send(HttpMethod method, String url, R requestBody, Map<String, String> headers,
            Class<T> responseType, ErrorListener errorListener) {
        final String finalUrl = preSend(method, url, requestBody, headers, responseType);
        try {
            return client.request(method, finalUrl, requestBody, headers, responseType);
        } catch (Exception e) {
            errorListener.error(new HttpErrorResponse(e.getMessage()));
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Result<?>> T send(HttpMethod method, String url, Map<String, Serializable> params,
            Class<T> responseType, ErrorListener errorListener) {
        return send(method, url, params, null, responseType, errorListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Result<?>> T send(HttpMethod method, String url, Map<String, Serializable> params,
            Map<String, String> headers, Class<T> responseType, ErrorListener errorListener) {
        final String finalUrl = preSend(method, url, params, headers, responseType);
        try {
            return client.request(method, finalUrl, params, headers, responseType);
        } catch (Exception e) {
            errorListener.error(new HttpErrorResponse(e.getMessage()));
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Result<?>> void send(HttpMethod method, String url, Map<String, Serializable> params,
            final Class<T> responseType, final CallBack<T> callBack) {
        send(method, url, params, null, responseType, callBack);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Result<?>> void send(HttpMethod method, String url, Map<String, Serializable> params,
            Map<String, String> headers, final Class<T> responseType, final CallBack<T> callBack) {
        final String finalUrl = preSend(method, url, params, headers, responseType);
        client.requestObservable(method, finalUrl, params, headers, responseType).subscribe(t -> {
            if (t.isSuccess()) {
                callBack.success(t);
            } else {
                callBack.failure(t);
            }
        }, e -> {
            callBack.error(new HttpErrorResponse(e.getMessage()));
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R, T extends Result<?>> HttpRequestCompletion<T> sendCompletion(HttpMethod method, String url,
            R requestBody, Class<T> responseType) {
        return sendCompletion(method, url, requestBody, null, responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R, T extends Result<?>> Observable<Completion<T>> sendObservable(HttpMethod method, String url,
            R requestBody, Class<T> responseType) {
        return sendObservable(method, url, requestBody, null, responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R, T extends Result<?>> HttpRequestCompletion<T> sendCompletion(HttpMethod method, String url,
            R requestBody, Map<String, String> headers, Class<T> responseType) {
        final String finalUrl = preSend(method, url, requestBody, headers, responseType);
        final HttpRequestCompletionImpl<T> completion = new HttpRequestCompletionImpl<>();
        client.requestObservable(method, finalUrl, requestBody, headers, responseType).subscribe(t -> {
            if (t.isSuccess()) {
                completion.setSuccess(t);
            } else {
                completion.setFailure(t);
            }
        }, e -> {
            completion.setError(new HttpErrorResponse(e.getMessage()));
        });
        return completion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R, T extends Result<?>> Observable<Completion<T>> sendObservable(HttpMethod method, String url,
            R requestBody, Map<String, String> headers, Class<T> responseType) {
        final String finalUrl = preSend(method, url, requestBody, headers, responseType);
        Observable<Completion<T>> o = Observable.create(emitter -> {
            final CompletionImpl<T> completion = new CompletionImpl<>();
            try {
                T t = client.request(method, finalUrl, requestBody, headers, responseType);
                emitter.onNext(completion);
                if (t.isSuccess()) {
                    completion.setSuccess(t);
                } else {
                    completion.setFailure(t);
                }
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
        return o.subscribeOn(Schedulers.io());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Result<?>> HttpRequestCompletion<T> sendCompletion(HttpMethod method, String url,
            Map<String, Serializable> params, Map<String, String> headers, Class<T> responseType) {
        final String finalUrl = preSend(method, url, params, headers, responseType);
        final HttpRequestCompletionImpl<T> completion = new HttpRequestCompletionImpl<>();
        client.requestObservable(method, finalUrl, params, headers, responseType).subscribe(t -> {
            if (t.isSuccess()) {
                completion.setSuccess(t);
            } else {
                completion.setFailure(t);
            }
        }, e -> {
            completion.setError(new HttpErrorResponse(e.getMessage()));
        });
        return completion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Result<?>> Observable<Completion<T>> sendObservable(HttpMethod method, String url,
            Map<String, Serializable> params, Map<String, String> headers, Class<T> responseType) {
        final String finalUrl = preSend(method, url, params, headers, responseType);
        Observable<Completion<T>> o = Observable.create(emitter -> {
            final CompletionImpl<T> completion = new CompletionImpl<>();
            try {
                T t = client.request(method, finalUrl, params, headers, responseType);
                emitter.onNext(completion);
                if (t.isSuccess()) {
                    completion.setSuccess(t);
                } else {
                    completion.setFailure(t);
                }
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
        return o.subscribeOn(Schedulers.io());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Result<?>> HttpRequestCompletion<T> sendCompletion(HttpMethod method, String url,
            Map<String, Serializable> params, Class<T> responseType) {
        return sendCompletion(method, url, params, null, responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Result<?>> Observable<Completion<T>> sendObservable(HttpMethod method, String url,
            Map<String, Serializable> params, Class<T> responseType) {
        return sendObservable(method, url, params, null, responseType);
    }

    /**
     * Pre send.
     *
     * @param <T>          the generic type
     * @param method       the method
     * @param url          the url
     * @param params       the params
     * @param headers      the headers
     * @param responseType the response type
     * @return the url string
     */
    protected abstract <T extends Result<?>> String preSend(HttpMethod method, String url, Object params,
            Map<String, String> headers, Class<T> responseType);

}
