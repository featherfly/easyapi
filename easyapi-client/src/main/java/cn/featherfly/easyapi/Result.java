/*
 *
 */
package cn.featherfly.easyapi;

import cn.featherfly.common.serialization.Serialization;

/**
 * The Class Result.
 *
 * @author zhongj
 * @param <D> the generic data type
 */
public class Result<D> {

    /** The Constant SUCCESS_CODE. */
    public static final String SUCCESS_CODE = "OK";

    private D data;

    private String message;

    private String code;

    /**
     * Checks if is success.
     *
     * @param result the result
     * @return true, if is success
     */
    public static boolean isSuccess(Result<?> result) {
        if (result != null) {
            return result.isSuccess();
        }
        return false;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message.
     *
     * @param message the new message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the code.
     *
     * @param code the new code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the data.
     *
     * @return the data
     */
    public D getData() {
        return data;
    }

    /**
     * Sets the data.
     *
     * @param data the new data
     */
    public void setData(D data) {
        this.data = data;
    }

    /**
     * Checks if is success.
     *
     * @return true, if is success
     */
    public boolean isSuccess() {
        return SUCCESS_CODE.equals(code);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Result " + new String(Serialization.serialize(this));
    }
}
