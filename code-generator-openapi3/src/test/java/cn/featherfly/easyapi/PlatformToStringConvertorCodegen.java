package cn.featherfly.easyapi;

import cn.featherfly.common.lang.Str;
import cn.featherfly.common.model.app.Platform;
import cn.featherfly.common.model.app.Platforms;
import cn.featherfly.conversion.codegen.convertor.EnumToStringConvertorCodegen;

/**
 * The type PlatformToStringCodegen.
 *
 * @author zhongj
 */
public class PlatformToStringConvertorCodegen extends EnumToStringConvertorCodegen {

    public PlatformToStringConvertorCodegen() {
        this(false);
    }

    public PlatformToStringConvertorCodegen(boolean inverse) {
        super(Platform.class.getName(), inverse);
    }

    @Override
    public String generateToTarget(String source) {
        if (inverse) {
            return toEnum(Platforms.class.getName(), source);
        }
        return toString(source);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String generateToSource(String target) {
        if (inverse) {
            return toString(target);
        }
        return toEnum(Platforms.class.getName(), target);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String toEnum(String toType, String src) {
        return Str.format("{0}.valueOf({1})", toType, src);
    }

    private String toString(String src) {
        return Str.format("{0}.name()", src);
    }

}