package cn.featherfly.easyapi.codegen;

import java.util.Collection;

/**
 * The interface Enable ext parameters.
 */
public interface EnableExtParameters {

    /**
     * Getter for property 'extParameters'.
     *
     * @return Value for property 'extParameters'.
     */
    public Collection<ExtParameter> getExtParameters();

    /**
     * Add ext parameters enable ext parameters.
     *
     * @param extParameter the ext parameter
     * @return the enable ext parameters
     */
    public EnableExtParameters addExtParameter(ExtParameter extParameter);

}
