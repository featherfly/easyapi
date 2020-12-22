package cn.featherfly.easyapi.client;

import cn.featherfly.common.http.ErrorListener;
import cn.featherfly.easyapi.Result;

/**
 * The Interface CallBack.
 *
 * @param <T> the generic type
 * @author zhongj
 */
public interface CallBack<T extends Result<?>> extends Listener<T>, ErrorListener {

}
