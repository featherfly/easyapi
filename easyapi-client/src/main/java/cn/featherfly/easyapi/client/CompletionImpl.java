
package cn.featherfly.easyapi.client;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import cn.featherfly.easyapi.Result;

/**
 * HttpRequestCompletionImpl.
 *
 * @author zhongj
 * @param <T> the generic type
 */
public class CompletionImpl<T extends Result<?>> implements Completion<T> {

    /** The success future. */
    CompletableFuture<T> successFuture;

    /** The failure future. */
    CompletableFuture<T> failureFuture;

    /**
     * Instantiates a new http request completion impl.
     */
    public CompletionImpl() {
        successFuture = new CompletableFuture<>();
        failureFuture = new CompletableFuture<>();
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
    public CompletionImpl<T> success(Consumer<T> action) {
        successFuture.thenAccept(action);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompletionImpl<T> failure(Consumer<T> action) {
        failureFuture.thenAccept(action);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompletionImpl<T> completion(Consumer<T> success, Consumer<T> failure) {
        success(success);
        failure(failure);
        return this;
    }
}
