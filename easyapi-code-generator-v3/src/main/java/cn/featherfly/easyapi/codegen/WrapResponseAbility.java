package cn.featherfly.easyapi.codegen;

/**
 * The type ModuleAbility.
 *
 * @author zhongj
 */
public interface WrapResponseAbility {

    boolean isWrapResponse();

    void setWrapResponse(boolean wrapResponse);

    String toModelFilename(String name, String templateName);
}
