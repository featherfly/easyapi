package cn.featherfly.easyapi;

import org.mozilla.javascript.optimizer.Codegen;

import cn.featherfly.common.model.app.Platform;
import cn.featherfly.conversion.codegen.CodegenUtils;
import cn.featherfly.conversion.codegen.PropertyConverterCodegen;
import cn.featherfly.conversion.codegen.property.ConvertorPropertyCodegen;
import cn.featherfly.conversion.codegen.property.EnumToStringPropertyCodegen;

/**
 * The type PlatformToStringPropertyCodegen.
 *
 * @author zhongj
 */
public class PlatformToStringPropertyCodegen extends ConvertorPropertyCodegen implements PropertyConverterCodegen {

    public PlatformToStringPropertyCodegen() {
        this(false);
    }

    /**
     * Instantiates a new enum to string property codegen.
     *
     * @param inverse the inverse
     */
    public PlatformToStringPropertyCodegen(boolean inverse) {
        super(CodegenUtils.getClassName(Platform.class), CodegenUtils.getClassName(String.class),
                new PlatformToStringConvertorCodegen(inverse));
    }
}
