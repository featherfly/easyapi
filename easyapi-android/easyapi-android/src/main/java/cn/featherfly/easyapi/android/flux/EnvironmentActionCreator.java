package cn.featherfly.easyapi.android.flux;

import cn.featherfly.common.app.Platform;
import cn.featherfly.common.app.Version;
import cn.featherfly.common.flux.action.ActionsCreator;
import cn.featherfly.common.flux.dispatcher.Dispatcher;
import cn.featherfly.common.location.LocationPoint;
import cn.featherfly.easyapi.pojo.User;


/**
 * The type Environment action creator.
 *
 * @author zhongj
 */
public class EnvironmentActionCreator extends ActionsCreator {

    /**
     * The constant instance.
     */
    public static EnvironmentActionCreator instance;

    private EnvironmentActionCreator(Dispatcher dispatcher) {
        super(dispatcher);
    }

    /**
     * Get environment action creator.
     *
     * @return the environment action creator
     */
    public static EnvironmentActionCreator get() {
        if (instance == null) {
            instance = new EnvironmentActionCreator(Dispatcher.get());
        }
        //        return ActionsCreator.get(EnvironmentActionsCreator.class);
        return instance;
    }


    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(LocationPoint location) {
        dispatcher.dispatch(new EnvironmentAction<>(EnvironmentAction.EnvironmentActionType.LOCATION_CHANGE, location));
    }


    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        dispatcher.dispatch(new EnvironmentAction<>(EnvironmentAction.EnvironmentActionType.USER_CHANGE, user));
    }

    /**
     * Sets version.
     *
     * @param version the version
     */
    public void setVersion(Version version) {
        dispatcher.dispatch(new EnvironmentAction<>(EnvironmentAction.EnvironmentActionType.VERSION_CHANGE, version));
    }


    /**
     * Sets platform.
     *
     * @param platform the platform
     */
    public void setPlatform(Platform platform) {
        dispatcher.dispatch(new EnvironmentAction<>(EnvironmentAction.EnvironmentActionType.PLATFORM_CHANGE, platform));
    }
}
