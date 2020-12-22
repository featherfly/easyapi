
package cn.featherfly.easyapi.client;

import java.util.function.Consumer;

import cn.featherfly.easyapi.Result;

/**
 * Completion.
 *
 * @author zhongj
 * @param <T> the generic type
 */
public interface GenericCompletion<C extends GenericCompletion<C, T>, T extends Result<?>> {

    /**
     * Success.
     *
     * @param action the action
     * @return the http request completion
     */
    C success(Consumer<T> action);

    /**
     * Failure.
     *
     * @param action the action
     * @return the http request completion
     */
    C failure(Consumer<T> action);

    /**
     * Completion.
     *
     * @param success the success
     * @param failure the failure
     * @return the http request completion
     */
    C completion(Consumer<T> success, Consumer<T> failure);
}
