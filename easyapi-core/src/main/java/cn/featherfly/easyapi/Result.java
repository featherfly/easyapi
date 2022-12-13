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
        if (getData() != null) {
            return "Result [code = " + getCode() + ", message = " + getMessage() + ", data = "
                    + new String(Serialization.serialize(getData())) + "]";
        } else {
            return "Result [code =" + getCode() + ", message=" + getMessage() + "]";
        }
    }
}
