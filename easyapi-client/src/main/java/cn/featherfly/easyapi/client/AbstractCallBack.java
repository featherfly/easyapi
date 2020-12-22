package cn.featherfly.easyapi.client;

import cn.featherfly.common.http.HttpErrorResponse;
import cn.featherfly.easyapi.Result;

/**
 * The Class AbstractCallBack.
 *
 * @param <T> the generic type
 * @author zhongj
 */
public abstract class AbstractCallBack<T extends Result<?>> implements CallBack<T> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void failure(T t) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void error(HttpErrorResponse error) {

    }
}