package cn.featherfly.easyapi.android;

import cn.featherfly.common.http.HttpClient;
import cn.featherfly.common.http.HttpMethod;
import cn.featherfly.common.http.HttpRequestConfig;
import cn.featherfly.easyapi.Environment;
import cn.featherfly.easyapi.Result;
import cn.featherfly.easyapi.client.Completion;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;

import java.io.Serializable;
import java.util.Map;

/**
 * The type Abstract http request.
 *
 * @author zhongj
 */
public abstract class AbstractAndroidHttpRequest extends cn.featherfly.easyapi.client.AbstractHttpRequest {

    /**
     * Instantiates a new Abstract http request.
     *
     * @param environment the environment
     * @param config      the config
     */
    public AbstractAndroidHttpRequest(Environment environment, HttpRequestConfig config) {
        super(environment, config);
    }

    /**
     * Instantiates a new Abstract http request.
     *
     * @param environment the environment
     * @param client      the client
     */
    public AbstractAndroidHttpRequest(Environment environment, HttpClient client) {
        super(environment, client);
    }

    @Override
    public <R, T extends Result<?>> Observable<Completion<T>> sendObservable(HttpMethod method, String url, R requestBody, Map<String, String> headers, Class<T> responseType) {
        return super.sendObservable(method, url, requestBody, headers, responseType).subscribeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public <T extends Result<?>> Observable<Completion<T>> sendObservable(HttpMethod method, String url, Map<String, Serializable> params, Map<String, String> headers, Class<T> responseType) {
        return super.sendObservable(method, url, params, headers, responseType).subscribeOn(AndroidSchedulers.mainThread());
    }
}
