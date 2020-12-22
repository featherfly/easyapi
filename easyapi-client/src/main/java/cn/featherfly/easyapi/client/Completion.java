
package cn.featherfly.easyapi.client;

import cn.featherfly.easyapi.Result;

/**
 * Completion.
 *
 * @author zhongj
 * @param <T> the generic type
 */
public interface Completion<T extends Result<?>> extends GenericCompletion<Completion<T>, T> {
}
