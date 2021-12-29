package cn.featherfly.easyapi.codegen;


import io.swagger.codegen.v3.CodegenParameter;

/**
 * Created by zj on 2017/4/27.
 */
public class ExtParameter {

    private String name;

    private CodegenParameter codegenParameter;

    private String paramTypeImport;

    private String paramTypeDefined;

    private String paramName;

    public ExtParameter() {
    }

    /**
     * Instantiates a new Ext parameter.
     *
     * @param name             the name
     * @param codegenParameter the codegen parameter
     * @param paramTypeImport  the param type import
     * @param paramTypeDefined the param type defined
     * @param paramName        the param name
     */
    public ExtParameter(String name, CodegenParameter codegenParameter, String paramTypeImport, String paramTypeDefined, String paramName) {
        this.name = name;
        this.codegenParameter = codegenParameter;
        this.paramTypeImport = paramTypeImport;
        this.paramTypeDefined = paramTypeDefined;
        this.paramName = paramName;
    }

    /**
     * Getter for property 'name'.
     *
     * @return Value for property 'name'.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for property 'name'.
     *
     * @param name Value to set for property 'name'.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for property 'codegenParameter'.
     *
     * @return Value for property 'codegenParameter'.
     */
    public CodegenParameter getCodegenParameter() {
        return codegenParameter;
    }

    /**
     * Setter for property 'codegenParameter'.
     *
     * @param codegenParameter Value to set for property 'codegenParameter'.
     */
    public void setCodegenParameter(CodegenParameter codegenParameter) {
        this.codegenParameter = codegenParameter;
    }

    /**
     * Getter for property 'paramTypeImport'.
     *
     * @return Value for property 'paramTypeImport'.
     */
    public String getParamTypeImport() {
        return paramTypeImport;
    }

    /**
     * Setter for property 'paramTypeImport'.
     *
     * @param paramTypeImport Value to set for property 'paramTypeImport'.
     */
    public void setParamTypeImport(String paramTypeImport) {
        this.paramTypeImport = paramTypeImport;
    }

    /**
     * Getter for property 'paramTypeDefined'.
     *
     * @return Value for property 'paramTypeDefined'.
     */
    public String getParamTypeDefined() {
        return paramTypeDefined;
    }

    /**
     * Setter for property 'paramTypeDefined'.
     *
     * @param paramTypeDefined Value to set for property 'paramTypeDefined'.
     */
    public void setParamTypeDefined(String paramTypeDefined) {
        this.paramTypeDefined = paramTypeDefined;
    }

    /**
     * Getter for property 'paramName'.
     *
     * @return Value for property 'paramName'.
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * Setter for property 'paramName'.
     *
     * @param paramName Value to set for property 'paramName'.
     */
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }
}
