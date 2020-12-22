package cn.featherfly.easyapi;

import cn.featherfly.common.app.Platform;
import cn.featherfly.common.app.Version;
import cn.featherfly.common.location.LocationPoint;
import cn.featherfly.easyapi.pojo.User;

/**
 * The Class EnvironmentImpl.
 *
 * @author zhongj
 */
public class EnvironmentImpl implements Environment {

    private LocationPoint location;

    private Version version;

    private Platform platform;

    private User currentUser;

    private boolean debugEnable;

    /**
     * get location value.
     *
     * @return location
     */
    @Override
    public LocationPoint getLocation() {
        return location;
    }

    /**
     * set location value.
     *
     * @param location location
     */
    public void setLocation(LocationPoint location) {
        this.location = location;
    }

    /**
     * get version value.
     *
     * @return version
     */
    @Override
    public Version getVersion() {
        return version;
    }

    /**
     * set version value.
     *
     * @param version version
     */
    public void setVersion(Version version) {
        this.version = version;
    }

    /**
     * get platform value.
     *
     * @return platform
     */
    @Override
    public Platform getPlatform() {
        return platform;
    }

    /**
     * set platform value.
     *
     * @param platform platform
     */
    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * set currentUser value.
     *
     * @param currentUser currentUser
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDebugEnable() {
        return debugEnable;
    }

    /**
     * set debugEnable value.
     *
     * @param debugEnable debugEnable
     */
    public void setDebugEnable(boolean debugEnable) {
        this.debugEnable = debugEnable;
    }
}
