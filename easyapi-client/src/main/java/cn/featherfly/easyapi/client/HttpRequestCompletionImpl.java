
package cn.featherfly.easyapi.client;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import cn.featherfly.common.http.HttpErrorResponse;
import cn.featherfly.easyapi.Result;

/**
 * HttpRequestCompletionImpl.
 *
 * @author zhongj
 * @param <T> the generic type
 */
public class HttpRequestCompletionImpl<T extends Result<?>> implements HttpRequestCompletion<T> {

    /** The success future. */
    CompletableFuture<T> successFuture;

    /** The failure future. */
    CompletableFuture<T> failureFuture;

    /** The error future. */
    CompletableFuture<HttpErrorResponse> errorFuture;

    /**
     * Instantiates a new http request completion impl.
     */
    public HttpRequestCompletionImpl() {
        successFuture = new CompletableFuture<>();
        errorFuture = new CompletableFuture<>();
        failureFuture = new CompletableFuture<>();
    }

    /**
     * set httpErrorResponse value.
     *
     * @param httpErrorResponse httpErrorResponse
     */
    public void setError(HttpErrorResponse httpErrorResponse) {
        errorFuture.complete(httpErrorResponse);
    }

    /**
     * Sets the success.
     *
     * @param response the new success
     */
    public void setSuccess(T response) {
        successFuture.complete(response);
    }

    /**
     * set response value.
     *
     * @param response response
     */
    public void setFailure(T response) {
        failureFuture.complete(response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpRequestCompletion<T> error(Consumer<HttpErrorResponse> action) {
        errorFuture.thenAccept(action);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpRequestCompletion<T> success(Consumer<T> action) {
        successFuture.thenAccept(action);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpRequestCompletion<T> failure(Consumer<T> action) {
        failureFuture.thenAccept(action);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpRequestCompletion<T> completion(Consumer<T> success, Consumer<T> failure) {
        success(success);
        failure(failure);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpRequestCompletion<T> completion(Consumer<T> success, Consumer<T> failure,
            Consumer<HttpErrorResponse> error) {
        completion(success, failure);
        error(error);
        return this;
    }

}
