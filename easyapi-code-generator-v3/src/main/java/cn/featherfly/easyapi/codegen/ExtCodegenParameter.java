package cn.featherfly.easyapi.codegen;

import java.util.ArrayList;
import java.util.HashMap;

import io.swagger.codegen.v3.CodegenParameter;

/**
 * The type ExtCodegenParameter.
 *
 * @author zhongj
 */
public class ExtCodegenParameter extends CodegenParameter {

    private Boolean isLoginParam = false;

    private Boolean isRequestParam = false;

    public ExtCodegenParameter() {
    }

    public ExtCodegenParameter(Boolean isLoginParam, Boolean isRequestParam) {
        this.isLoginParam = isLoginParam;
        this.isRequestParam = isRequestParam;
    }

    public Boolean getIsLoginParam() {
        return isLoginParam;
    }

    public void setIsLoginParam(Boolean loginParam) {
        isLoginParam = loginParam;
    }

    public Boolean getIsRequestParam() {
        return isRequestParam;
    }

    public void setIsRequestParam(Boolean requestParam) {
        isRequestParam = requestParam;
    }

    @Override
    public CodegenParameter copy() {
        ExtCodegenParameter output = new ExtCodegenParameter();
        output.secondaryParam = secondaryParam;
        output.baseName = baseName;
        output.paramName = paramName;
        output.dataType = dataType;
        output.datatypeWithEnum = datatypeWithEnum;
        output.enumName = enumName;
        output.dataFormat = dataFormat;
        output.collectionFormat = collectionFormat;
        output.description = description;
        output.unescapedDescription = unescapedDescription;
        output.baseType = baseType;
        output.nullable = nullable;
        output.required = required;
        output.maximum = maximum;
        output.exclusiveMaximum = exclusiveMaximum;
        output.minimum = minimum;
        output.exclusiveMinimum = exclusiveMinimum;
        output.maxLength = maxLength;
        output.minLength = minLength;
        output.pattern = pattern;
        output.maxItems = maxItems;
        output.minItems = minItems;
        output.uniqueItems = uniqueItems;
        output.multipleOf = multipleOf;
        output.jsonSchema = jsonSchema;
        output.defaultValue = defaultValue;
        output.example = example;
        output.testExample = testExample;
        if (_enum != null) {
            output._enum = new ArrayList<>(_enum);
        }
        if (allowableValues != null) {
            output.allowableValues = new HashMap<>(allowableValues);
        }
        if (items != null) {
            output.items = items;
        }
        if (vendorExtensions != null) {
            output.vendorExtensions = new HashMap<>(vendorExtensions);
        }

        // 扩展的属性
        output.isLoginParam = isLoginParam;
        output.isRequestParam = isRequestParam;
        return output;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (isLoginParam == null ? 0 : isLoginParam.hashCode());
        result = prime * result + (isRequestParam == null ? 0 : isRequestParam.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ExtCodegenParameter other = (ExtCodegenParameter) obj;
        if (isLoginParam == null) {
            if (other.isLoginParam != null) {
                return false;
            }
        } else if (!isLoginParam.equals(other.isLoginParam)) {
            return false;
        }
        if (isRequestParam == null) {
            if (other.isRequestParam != null) {
                return false;
            }
        } else if (!isRequestParam.equals(other.isRequestParam)) {
            return false;
        }
        return true;
    }
}
