
package cn.featherfly.easyapi.client;

import java.util.function.Consumer;

import cn.featherfly.common.http.HttpErrorResponse;
import cn.featherfly.easyapi.Result;

/**
 * HttpResponseHandler.
 *
 * @author zhongj
 * @param <T> the generic type
 */
public interface HttpRequestCompletion<T extends Result<?>> extends GenericCompletion<HttpRequestCompletion<T>, T> {

    /**
     * Completion.
     *
     * @param success the success
     * @param failure the failure
     * @param error   the error
     * @return the http request completion
     */
    HttpRequestCompletion<T> completion(Consumer<T> success, Consumer<T> failure, Consumer<HttpErrorResponse> error);

    /**
     * Error.
     *
     * @param action the action
     * @return the http request completion
     */
    HttpRequestCompletion<T> error(Consumer<HttpErrorResponse> action);
}
