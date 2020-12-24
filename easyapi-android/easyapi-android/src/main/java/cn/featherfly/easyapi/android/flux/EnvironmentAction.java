package cn.featherfly.easyapi.android.flux;

import cn.featherfly.common.flux.action.SimpleAction;

/**
 * The Class EnvironmentAction.
 *
 * @author zhongj
 * @param <T> the generic type
 */
public class EnvironmentAction<T> extends SimpleAction<T> {

    /**
     * The Enum EnvironmentActionType.
     *
     * @author zhongj
     */
    public enum EnvironmentActionType implements Type {
        /** The location change. */
        LOCATION_CHANGE,
        /** The user change. */
        USER_CHANGE,
        /** The version change. */
        VERSION_CHANGE,
        /** The Platform CHANGE. */
        PLATFORM_CHANGE;
    }

    /**
     * Instantiates a new environment action.
     *
     * @param type the type
     * @param data the data
     */
    public EnvironmentAction(EnvironmentActionType type, T data) {
        super(type, data);
    }
}
