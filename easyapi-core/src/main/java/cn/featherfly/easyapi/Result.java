package cn.featherfly.easyapi;

import cn.featherfly.common.api.Response;
import cn.featherfly.common.serialization.Serialization;

/**
 * The Class Result.
 *
 * @author zhongj
 * @param <D> the generic data type
 */
public class Result<D> extends Response<D> {

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Result " + new String(Serialization.serialize(this));
    }
}
