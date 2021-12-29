package cn.featherfly.easyapi.codegen;

import io.swagger.codegen.CodegenProperty;

import java.lang.reflect.Field;

/**
 * Created by richard on 15/12/16.
 */
public class DateFormatProperties extends CodegenProperty {
    public final boolean isDate = true;

    public String dateFormat = "@DateTimeFormat(pattern = \"yyyy-MM-dd\")";

    public void copyFromParent(CodegenProperty property) throws Exception {
        if(property==null) {
            throw new Exception("源对象和目标对象不能为null");
        }
        Field[] fields = CodegenProperty.class.getDeclaredFields();
        for(int i = 0; i < fields.length; i++){
            fields[i].setAccessible(true);
            Object sourceValue=fields[i].get(property);
            fields[i].set(this, sourceValue);
        }
    }
}
