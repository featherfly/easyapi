package cn.featherfly.easyapi.android;

import cn.featherfly.common.app.Platform;
import cn.featherfly.common.app.Version;
import cn.featherfly.common.flux.action.Action;
import cn.featherfly.common.flux.store.Store;
import cn.featherfly.common.flux.store.StoreAction;
import cn.featherfly.common.location.LocationPoint;
import cn.featherfly.easyapi.Environment;
import cn.featherfly.easyapi.EnvironmentImpl;
import cn.featherfly.easyapi.android.flux.EnvironmentAction;
import cn.featherfly.easyapi.pojo.User;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Environment store.
 *
 * @author zhongj
 */
public class EnvironmentStore extends Store<EnvironmentAction<?>> implements Environment {

    /**
     * The constant ENV.
     */
    public static final EnvironmentStore ENV = new EnvironmentStore();

    private EnvironmentImpl environment = new EnvironmentImpl();

    /**
     * Instantiates a new Environment store.
     */
    protected EnvironmentStore() {

    }

    @Override
    protected Map<Action.Type, StoreAction<EnvironmentAction<?>>> initStoreActions() {
        Map<Action.Type, StoreAction<EnvironmentAction<?>>> actions = new HashMap<>();
        actions.put(EnvironmentAction.EnvironmentActionType.LOCATION_CHANGE, action -> {
            environment.setLocation((LocationPoint) action.getData());
            storeChange(action);
        });
        actions.put(EnvironmentAction.EnvironmentActionType.USER_CHANGE, action -> {
            environment.setCurrentUser((User) action.getData());
            storeChange(action);
        });
        actions.put(EnvironmentAction.EnvironmentActionType.PLATFORM_CHANGE, action -> {
            environment.setPlatform((Platform) action.getData());
            storeChange(action);
        });
        actions.put(EnvironmentAction.EnvironmentActionType.VERSION_CHANGE, action -> {
            environment.setVersion((Version) action.getData());
            storeChange(action);
        });
        return actions;
    }


    /**
     * Get environment store.
     *
     * @return the environment store
     */
    public static EnvironmentStore get() {
        return ENV;
    }

    @Override
    public LocationPoint getLocation() {
        return environment.getLocation();
    }

    @Override
    public Version getVersion() {
        return environment.getVersion();
    }

    @Override
    public Platform getPlatform() {
        return environment.getPlatform();
    }

    @Override
    public User getCurrentUser() {
        return environment.getCurrentUser();
    }

    @Override
    public boolean isDebugEnable() {
        return environment.isDebugEnable();
    }

    @Override
    public <O> O get(String key) {
        return environment.get(key);
    }

    @Override
    public <O> O get(Class<O> type) {
        return environment.get(type);
    }
}
