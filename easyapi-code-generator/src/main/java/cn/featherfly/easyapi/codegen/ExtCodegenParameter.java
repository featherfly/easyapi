package cn.featherfly.easyapi.codegen;

import io.swagger.codegen.v3.CodegenParameter;

import java.util.ArrayList;
import java.util.HashMap;

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
        output.secondaryParam = this.secondaryParam;
        output.baseName = this.baseName;
        output.paramName = this.paramName;
        output.dataType = this.dataType;
        output.datatypeWithEnum = this.datatypeWithEnum;
        output.enumName = this.enumName;
        output.dataFormat = this.dataFormat;
        output.collectionFormat = this.collectionFormat;
        output.description = this.description;
        output.unescapedDescription = this.unescapedDescription;
        output.baseType = this.baseType;
        output.nullable = this.nullable;
        output.required = this.required;
        output.maximum = this.maximum;
        output.exclusiveMaximum = this.exclusiveMaximum;
        output.minimum = this.minimum;
        output.exclusiveMinimum = this.exclusiveMinimum;
        output.maxLength = this.maxLength;
        output.minLength = this.minLength;
        output.pattern = this.pattern;
        output.maxItems = this.maxItems;
        output.minItems = this.minItems;
        output.uniqueItems = this.uniqueItems;
        output.multipleOf = this.multipleOf;
        output.jsonSchema = this.jsonSchema;
        output.defaultValue = this.defaultValue;
        output.example = this.example;
        output.testExample = this.testExample;
        if (this._enum != null) {
            output._enum = new ArrayList<String>(this._enum);
        }
        if (this.allowableValues != null) {
            output.allowableValues = new HashMap<String, Object>(this.allowableValues);
        }
        if (this.items != null) {
            output.items = this.items;
        }
        if (this.vendorExtensions != null) {
            output.vendorExtensions = new HashMap<String, Object>(this.vendorExtensions);
        }
        return output;
    }
}
