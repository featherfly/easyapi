package cn.featherfly.easyapi;

import cn.featherfly.common.app.Platform;
import cn.featherfly.common.app.Version;
import cn.featherfly.common.location.LocationPoint;
import cn.featherfly.easyapi.pojo.User;

/**
 * The Interface Environment.
 *
 * @author zhongj
 */
public interface Environment {

    /**
     * Gets the location.
     *
     * @return the location
     */
    LocationPoint getLocation();

    /**
     * Gets the version.
     *
     * @return the version
     */
    Version getVersion();

    /**
     * Gets the platform.
     *
     * @return the platform
     */
    Platform getPlatform();

    /**
     * Gets the current user.
     *
     * @return the current user
     */
    User getCurrentUser();

    /**
     * Checks if is debug enable.
     *
     * @return true, if is debug enable
     */
    boolean isDebugEnable();

    /**
     * Gets the.
     *
     * @param <O> the generic type
     * @param key the key
     * @return the o
     */
    <O> O get(String key);

    /**
     * Gets the.
     *
     * @param <O>  the generic type
     * @param type the type
     * @return the o
     */
    <O> O get(Class<O> type);
}
