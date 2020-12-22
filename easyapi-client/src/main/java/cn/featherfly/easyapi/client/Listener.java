package cn.featherfly.easyapi.client;

/**
 * The Interface Listener.
 *
 * @author zhongj
 * @param <T> the generic type
 */
public interface Listener<T> {

    /**
     * Success.
     *
     * @param t the t
     */
    void success(T t);

    /**
     * Failure.
     *
     * @param t the t
     */
    void failure(T t);
}
