package cn.featherfly.easyapi.codegen;

import static io.swagger.codegen.v3.CodegenConstants.IS_ENUM_EXT_NAME;
import static io.swagger.codegen.v3.generators.handlebars.ExtensionHelper.getBooleanValue;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.bean.BeanUtils;
import cn.featherfly.common.lang.Strings;
import io.swagger.codegen.v3.CodegenConstants;
import io.swagger.codegen.v3.CodegenModelFactory;
import io.swagger.codegen.v3.CodegenModelType;
import io.swagger.codegen.v3.CodegenOperation;
import io.swagger.codegen.v3.CodegenParameter;
import io.swagger.codegen.v3.CodegenProperty;
import io.swagger.codegen.v3.generators.java.AbstractJavaCodegen;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.BinarySchema;
import io.swagger.v3.oas.models.media.ByteArraySchema;
import io.swagger.v3.oas.models.media.DateSchema;
import io.swagger.v3.oas.models.media.DateTimeSchema;
import io.swagger.v3.oas.models.media.EmailSchema;
import io.swagger.v3.oas.models.media.FileSchema;
import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.MapSchema;
import io.swagger.v3.oas.models.media.NumberSchema;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.media.UUIDSchema;
import io.swagger.v3.parser.util.SchemaTypeUtil;

/**
 * The type EasyapiAbstractJavaCodegen.
 *
 * @author zhongj
 */
public abstract class EasyapiAbstractJavaCodegen extends AbstractJavaCodegen
        implements EnableExtParameters, ModuleAbility {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    //
    protected String module;

    protected String secondModule;

    protected String configPackage = "";

    protected String defaultApiPath = "/v1/api";

    protected String title = "easyapi java code";

    protected Collection<ExtParameter> extParameters = new HashSet<>();

    public EasyapiAbstractJavaCodegen() {
        //        setModule(module);
        setGroupId("cn.featherfly.easyapi");
        setArtifactId("easyapi-code-generator");
        setArtifactVersion("1.0.0");
        languageSpecificPrimitives = new HashSet<>(
                Arrays.asList("String", "boolean", "Boolean", "Double", "Integer", "Long", "Float"));
    }

    @Override
    public Map<String, Object> postProcessOperations(Map<String, Object> objs) {
        @SuppressWarnings("unchecked")
        Map<String, Object> operations = (Map<String, Object>) objs.get("operations");
        if (operations != null) {
            @SuppressWarnings("unchecked")
            List<CodegenOperation> ops = (List<CodegenOperation>) operations.get("operation");
            for (CodegenOperation operation : ops) {
                if (operation.returnType != null) {
                    if (operation.returnType.startsWith("Pagination")) {
                        String rt = operation.returnType;
                        operation.returnType = "PaginationResults<" + rt.substring("Pagination".length(), rt.length())
                                + ">";
                        addExtParameterImports(objs, "cn.featherfly.common.structure.page.PaginationResults");
                    }
                }
                String notes = operation.notes;
                if (notes != null) {
                    ExtCodegenParameter requestParameter = new ExtCodegenParameter();
                    requestParameter.setIsRequestParam(true);
                    extParameters.forEach(extParameter -> {
                        if (notes.contains(extParameter.getName())) {
                            addAddtionalParameter(extParameter.getCodegenParameter(), operation,
                                    extParameter.getParamTypeDefined(), extParameter.getParamName());
                            addExtParameterImports(objs, extParameter.getParamTypeImport());
                        }
                    });
                }
            }
        }
        return objs;
    }

    private void addAddtionalParameter(CodegenParameter params, CodegenOperation operation, String dataType,
            String paramName) {
        params.dataType = dataType;
        params.paramName = paramName;
        params.baseName = paramName;
        if (StringUtils.isEmpty(params.description)) {
            params.description = paramName;
        }
        params.unescapedDescription = params.description;
        operation.allParams.add(params);
        operation.allParams = addHasMore(operation.allParams);
    }

    private void addExtParameterImports(Map<String, Object> objs, String importClass) {
        @SuppressWarnings("unchecked")
        List<Map<String, String>> imports = (List<Map<String, String>>) objs.get("imports");
        for (Map<String, String> importItem : imports) {
            String className = importItem.get("import");
            if (className != null && className.equals(importClass)) {
                return;
            }
        }
        Map<String, String> im = new LinkedHashMap<>();
        im.put("import", importClass);
        imports.add(im);
    }

    @Override
    public Collection<ExtParameter> getExtParameters() {
        return extParameters;
    }

    @Override
    public EnableExtParameters addExtParameter(ExtParameter extParameter) {
        if (extParameter != null) {
            extParameters.add(extParameter);
        }
        return this;
    }

    public String getSourceFolder() {
        return sourceFolder;
    }

    @Override
    public String apiPackage() {
        return Strings.format(super.apiPackage(), BeanUtils.toMap(this));
    }

    @Override
    public String toModelImport(String name) {
        return Strings.format(super.toModelImport(name), BeanUtils.toMap(this));
    }

    @Override
    public String toApiImport(String name) {
        return Strings.format(super.toApiImport(name), BeanUtils.toMap(this));
    }

    @Override
    public String modelPackage() {
        return Strings.format(super.modelPackage(), BeanUtils.toMap(this));
    }

    @Override
    public String apiFileFolder() {
        return outputFolder + "/" + sourceFolder + "/" + apiPackage().replace('.', File.separatorChar);
    }

    @Override
    public String modelFileFolder() {
        return outputFolder + "/" + sourceFolder + "/" + modelPackage().replace('.', File.separatorChar);
    }

    @Override
    public String toModelName(final String name) {
        if (name.startsWith(".")) {
            //            String extFile = StringUtils.substringBefore(name,"#");
            String returnType = StringUtils.substringAfterLast(name, "/");
            return returnType;
        } else {
            return super.toModelName(name);
        }
    }

    @Override
    public String toApiName(String name) {
        return super.toApiName(name);
    }

    /**
     * Setter for property 'defaultApiPath'.
     *
     * @param defaultApiPath Value to set for property 'defaultApiPath'.
     */
    public void setDefaultApiPath(String defaultApiPath) {
        this.defaultApiPath = defaultApiPath;
        additionalProperties.put("apiPath", defaultApiPath);
    }

    /**
     * Getter for property 'configPackage'.
     *
     * @return Value for property 'configPackage'.
     */
    public String getConfigPackage() {
        return configPackage;
    }

    /**
     * Setter for property 'configPackage'.
     *
     * @param configPackage Value to set for property 'configPackage'.
     */
    public void setConfigPackage(String configPackage) {
        this.configPackage = configPackage;
        additionalProperties.put("configPackage", configPackage);
    }

    /**
     * Getter for property 'defaultApiPath'.
     *
     * @return Value for property 'defaultApiPath'.
     */
    public String getDefaultApiPath() {
        return defaultApiPath;
    }

    @Override
    public String getModule() {
        return module;
    }

    public String getTitle() {
        return title;
    }

    /**
     * Setter for property 'title'.
     *
     * @param title Value to set for property 'title'.
     */
    public void setTitle(String title) {
        this.title = title;
        additionalProperties.put("title", title);

    }

    /**
     * Setter for property 'module'.
     *
     * @param module Value to set for property 'module'.
     */
    @Override
    public void setModule(String module) {
        this.module = module;
        additionalProperties.put("module", module);
    }

    public String getInvokerPackage() {
        return invokerPackage;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public String getArtifactVersion() {
        return artifactVersion;
    }

    @Override
    public void setInvokerPackage(String invokerPackage) {
        this.invokerPackage = invokerPackage;
        additionalProperties.put("invokerPackage", invokerPackage);
    }

    @Override
    public void setGroupId(String groupId) {
        this.groupId = groupId;
        additionalProperties.put("groupId", groupId);
    }

    @Override
    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
        additionalProperties.put("artifactId", artifactId);
    }

    @Override
    public void setArtifactVersion(String artifactVersion) {
        this.artifactVersion = artifactVersion;
        additionalProperties.put("artifactVersion", artifactVersion);
    }

    @Override
    public void setArtifactUrl(String artifactUrl) {
        this.artifactUrl = artifactUrl;
        additionalProperties.put("artifactUrl", artifactUrl);
    }

    @Override
    public void setArtifactDescription(String artifactDescription) {
        this.artifactDescription = artifactDescription;
        additionalProperties.put("artifactDescription", artifactDescription);
    }

    @Override
    public void setApiPackage(String apiPackage) {
        this.apiPackage = apiPackage;
        additionalProperties.put("apiPackage", apiPackage);
    }

    @Override
    public void setModelPackage(String modelPackage) {
        this.modelPackage = modelPackage;
        additionalProperties.put("modelPackage", modelPackage);
    }

    @Override
    public CodegenProperty fromProperty(String name, @SuppressWarnings("rawtypes") Schema propertySchema) {
        if (propertySchema == null) {
            logger.error("unexpected missing property for name " + name);
            return null;
        }

        final CodegenProperty codegenProperty = CodegenModelFactory.newInstance(CodegenModelType.PROPERTY);
        codegenProperty.name = toVarName(name);
        codegenProperty.baseName = name;
        codegenProperty.nameInCamelCase = camelize(codegenProperty.name, false);
        codegenProperty.description = escapeText(propertySchema.getDescription());
        codegenProperty.unescapedDescription = propertySchema.getDescription();
        codegenProperty.title = propertySchema.getTitle();
        codegenProperty.getter = toGetter(name);
        codegenProperty.setter = toSetter(name);
        String example = toExampleValue(propertySchema);
        if (!"null".equals(example)) {
            codegenProperty.example = example;
        }
        codegenProperty.defaultValue = toDefaultValue(propertySchema);
        codegenProperty.defaultValueWithParam = toDefaultValueWithParam(name, propertySchema);
        codegenProperty.jsonSchema = Json.pretty(propertySchema);
        if (propertySchema.getNullable() != null) {
            codegenProperty.nullable = propertySchema.getNullable();
        }
        if (propertySchema.getReadOnly() != null) {
            codegenProperty.getVendorExtensions().put(CodegenConstants.IS_READ_ONLY_EXT_NAME,
                    propertySchema.getReadOnly());
        }
        if (propertySchema.getXml() != null) {
            if (propertySchema.getXml().getAttribute() != null) {
                codegenProperty.getVendorExtensions().put(CodegenConstants.IS_XML_ATTRIBUTE_EXT_NAME,
                        propertySchema.getXml().getAttribute());
            }
            codegenProperty.xmlPrefix = propertySchema.getXml().getPrefix();
            codegenProperty.xmlName = propertySchema.getXml().getName();
            codegenProperty.xmlNamespace = propertySchema.getXml().getNamespace();
        }
        if (propertySchema.getExtensions() != null && !propertySchema.getExtensions().isEmpty()) {
            codegenProperty.getVendorExtensions().putAll(propertySchema.getExtensions());
        }

        final String type = getSchemaType(propertySchema);
        if (propertySchema instanceof IntegerSchema) {
            codegenProperty.getVendorExtensions().put(CodegenConstants.IS_NUMERIC_EXT_NAME, Boolean.TRUE);
            if (SchemaTypeUtil.INTEGER64_FORMAT.equals(propertySchema.getFormat())) {
                codegenProperty.getVendorExtensions().put(CodegenConstants.IS_LONG_EXT_NAME, Boolean.TRUE);
            } else {
                codegenProperty.getVendorExtensions().put(CodegenConstants.IS_INTEGER_EXT_NAME, Boolean.TRUE);
            }
            handleMinMaxValues(propertySchema, codegenProperty);

            // check if any validation rule defined
            // exclusive* are noop without corresponding min/max
            if (codegenProperty.minimum != null || codegenProperty.maximum != null) {
                codegenProperty.getVendorExtensions().put(CodegenConstants.HAS_VALIDATION_EXT_NAME, Boolean.TRUE);
            }

            // legacy support
            Map<String, Object> allowableValues = new HashMap<>();
            if (propertySchema.getMinimum() != null) {
                allowableValues.put("min", propertySchema.getMinimum());
            }
            if (propertySchema.getMaximum() != null) {
                allowableValues.put("max", propertySchema.getMaximum());
            }
            if (propertySchema.getEnum() != null) {
                List<Integer> _enum = propertySchema.getEnum();
                codegenProperty._enum = new ArrayList<>();
                for (Integer i : _enum) {
                    codegenProperty._enum.add(i.toString());
                }
                codegenProperty.getVendorExtensions().put(IS_ENUM_EXT_NAME, Boolean.TRUE);
                allowableValues.put("values", _enum);
            }
            if (allowableValues.size() > 0) {
                codegenProperty.allowableValues = allowableValues;
            }
        }

        if (propertySchema instanceof StringSchema) {
            codegenProperty.maxLength = propertySchema.getMaxLength();
            codegenProperty.minLength = propertySchema.getMinLength();
            codegenProperty.pattern = toRegularExpression(propertySchema.getPattern());

            // check if any validation rule defined
            if (codegenProperty.pattern != null || codegenProperty.minLength != null
                    || codegenProperty.maxLength != null) {
                codegenProperty.getVendorExtensions().put(CodegenConstants.HAS_VALIDATION_EXT_NAME, Boolean.TRUE);
            }

            codegenProperty.getVendorExtensions().put(CodegenConstants.IS_STRING_EXT_NAME, Boolean.TRUE);
            if (propertySchema.getEnum() != null) {
                List<String> _enum = propertySchema.getEnum();
                codegenProperty._enum = _enum;
                codegenProperty.getVendorExtensions().put(IS_ENUM_EXT_NAME, Boolean.TRUE);

                // legacy support
                Map<String, Object> allowableValues = new HashMap<>();
                allowableValues.put("values", _enum);
                codegenProperty.allowableValues = allowableValues;
            }
        }
        // TODO 因为使用的是java.lang.Boolean类型（boolean包装类）,还是使用标准的get set方法
        //        if (propertySchema instanceof BooleanSchema) {
        //            codegenProperty.getVendorExtensions().put(CodegenConstants.IS_BOOLEAN_EXT_NAME, Boolean.TRUE);
        //            codegenProperty.getter = toBooleanGetter(name);
        //        }
        if (propertySchema instanceof FileSchema || propertySchema instanceof BinarySchema) {
            codegenProperty.getVendorExtensions().put(CodegenConstants.IS_FILE_EXT_NAME, Boolean.TRUE);
            codegenProperty.getVendorExtensions().put(CodegenConstants.IS_BINARY_EXT_NAME, Boolean.TRUE);
            codegenProperty.getVendorExtensions().put(CodegenConstants.IS_STRING_EXT_NAME, Boolean.TRUE);
        }
        if (propertySchema instanceof EmailSchema) {
            codegenProperty.getVendorExtensions().put(CodegenConstants.IS_STRING_EXT_NAME, Boolean.TRUE);
        }
        if (propertySchema instanceof UUIDSchema) {
            codegenProperty.getVendorExtensions().put(CodegenConstants.IS_UUID_EXT_NAME, Boolean.TRUE);
            // keep isString to true to make it backward compatible
            codegenProperty.getVendorExtensions().put(CodegenConstants.IS_STRING_EXT_NAME, Boolean.TRUE);
        }
        if (propertySchema instanceof ByteArraySchema) {
            codegenProperty.getVendorExtensions().put(CodegenConstants.IS_BYTE_ARRAY_EXT_NAME, Boolean.TRUE);
        }
        // type is number and without format
        if (propertySchema instanceof NumberSchema) {
            codegenProperty.getVendorExtensions().put(CodegenConstants.IS_NUMERIC_EXT_NAME, Boolean.TRUE);
            if (SchemaTypeUtil.FLOAT_FORMAT.equals(propertySchema.getFormat())) {
                codegenProperty.getVendorExtensions().put(CodegenConstants.IS_FLOAT_EXT_NAME, Boolean.TRUE);
            } else {
                codegenProperty.getVendorExtensions().put(CodegenConstants.IS_DOUBLE_EXT_NAME, Boolean.TRUE);
            }
            handleMinMaxValues(propertySchema, codegenProperty);
            if (propertySchema.getEnum() != null && !propertySchema.getEnum().isEmpty()) {
                List<Number> _enum = propertySchema.getEnum();
                codegenProperty._enum = _enum.stream().map(number -> number.toString()).collect(Collectors.toList());
                codegenProperty.getVendorExtensions().put(IS_ENUM_EXT_NAME, Boolean.TRUE);

                // legacy support
                Map<String, Object> allowableValues = new HashMap<>();
                allowableValues.put("values", _enum);
                codegenProperty.allowableValues = allowableValues;
            }
        }
        if (propertySchema instanceof DateSchema) {
            codegenProperty.getVendorExtensions().put(CodegenConstants.IS_DATE_EXT_NAME, Boolean.TRUE);
            handlePropertySchema(propertySchema, codegenProperty);
        }
        if (propertySchema instanceof DateTimeSchema) {
            codegenProperty.getVendorExtensions().put(CodegenConstants.IS_DATE_TIME_EXT_NAME, Boolean.TRUE);
            handlePropertySchema(propertySchema, codegenProperty);
        }
        codegenProperty.datatype = getTypeDeclaration(propertySchema);
        codegenProperty.dataFormat = propertySchema.getFormat();

        // this can cause issues for clients which don't support enums
        boolean isEnum = getBooleanValue(codegenProperty, IS_ENUM_EXT_NAME);
        if (isEnum) {
            codegenProperty.datatypeWithEnum = toEnumName(codegenProperty);
            codegenProperty.enumName = toEnumName(codegenProperty);
        } else {
            codegenProperty.datatypeWithEnum = codegenProperty.datatype;
        }

        codegenProperty.baseType = getSchemaType(propertySchema);

        if (propertySchema instanceof ArraySchema) {
            codegenProperty.getVendorExtensions().put(CodegenConstants.IS_CONTAINER_EXT_NAME, Boolean.TRUE);
            codegenProperty.getVendorExtensions().put(CodegenConstants.IS_LIST_CONTAINER_EXT_NAME, Boolean.TRUE);
            codegenProperty.containerType = "array";
            codegenProperty.baseType = getSchemaType(propertySchema);
            if (propertySchema.getXml() != null) {
                codegenProperty.getVendorExtensions().put(CodegenConstants.IS_XML_WRAPPED_EXT_NAME,
                        propertySchema.getXml().getWrapped() == null ? false : propertySchema.getXml().getWrapped());
                codegenProperty.xmlPrefix = propertySchema.getXml().getPrefix();
                codegenProperty.xmlNamespace = propertySchema.getXml().getNamespace();
                codegenProperty.xmlName = propertySchema.getXml().getName();
            }
            // handle inner property
            codegenProperty.maxItems = propertySchema.getMaxItems();
            codegenProperty.minItems = propertySchema.getMinItems();
            String itemName = null;
            if (propertySchema.getExtensions() != null && propertySchema.getExtensions().get("x-item-name") != null) {
                itemName = propertySchema.getExtensions().get("x-item-name").toString();
            }
            if (itemName == null) {
                itemName = codegenProperty.name;
            }
            Schema items = ((ArraySchema) propertySchema).getItems();
            CodegenProperty innerCodegenProperty = fromProperty(itemName, items);
            updatePropertyForArray(codegenProperty, innerCodegenProperty);
        } else if (propertySchema instanceof MapSchema && hasSchemaProperties(propertySchema)) {

            codegenProperty.getVendorExtensions().put(CodegenConstants.IS_CONTAINER_EXT_NAME, Boolean.TRUE);
            codegenProperty.getVendorExtensions().put(CodegenConstants.IS_MAP_CONTAINER_EXT_NAME, Boolean.TRUE);
            codegenProperty.containerType = "map";
            codegenProperty.baseType = getSchemaType(propertySchema);
            codegenProperty.minItems = propertySchema.getMinProperties();
            codegenProperty.maxItems = propertySchema.getMaxProperties();

            // handle inner property
            CodegenProperty cp = fromProperty("inner", (Schema) propertySchema.getAdditionalProperties());
            updatePropertyForMap(codegenProperty, cp);
        } else if (propertySchema instanceof MapSchema && hasTrueAdditionalProperties(propertySchema)) {

            codegenProperty.getVendorExtensions().put(CodegenConstants.IS_CONTAINER_EXT_NAME, Boolean.TRUE);
            codegenProperty.getVendorExtensions().put(CodegenConstants.IS_MAP_CONTAINER_EXT_NAME, Boolean.TRUE);
            codegenProperty.containerType = "map";
            codegenProperty.baseType = getSchemaType(propertySchema);
            codegenProperty.minItems = propertySchema.getMinProperties();
            codegenProperty.maxItems = propertySchema.getMaxProperties();

            // handle inner property
            CodegenProperty cp = fromProperty("inner", new ObjectSchema());
            updatePropertyForMap(codegenProperty, cp);
        } else {
            if (isObjectSchema(propertySchema)) {
                codegenProperty.getVendorExtensions().put("x-is-object", Boolean.TRUE);
            }
            setNonArrayMapProperty(codegenProperty, type);
        }
        return codegenProperty;
    }

    private void handleMinMaxValues(Schema propertySchema, CodegenProperty codegenProperty) {
        if (propertySchema.getMinimum() != null) {
            codegenProperty.minimum = String.valueOf(propertySchema.getMinimum().longValue());
        }
        if (propertySchema.getMaximum() != null) {
            codegenProperty.maximum = String.valueOf(propertySchema.getMaximum().longValue());
        }
        if (propertySchema.getExclusiveMinimum() != null) {
            codegenProperty.exclusiveMinimum = propertySchema.getExclusiveMinimum();
        }
        if (propertySchema.getExclusiveMaximum() != null) {
            codegenProperty.exclusiveMaximum = propertySchema.getExclusiveMaximum();
        }
    }

    private void handlePropertySchema(Schema propertySchema, CodegenProperty codegenProperty) {
        if (propertySchema.getEnum() != null) {
            List<String> _enum = propertySchema.getEnum();
            codegenProperty._enum = new ArrayList<>();
            for (String i : _enum) {
                codegenProperty._enum.add(i);
            }
            codegenProperty.getVendorExtensions().put(IS_ENUM_EXT_NAME, Boolean.TRUE);

            // legacy support
            Map<String, Object> allowableValues = new HashMap<>();
            allowableValues.put("values", _enum);
            codegenProperty.allowableValues = allowableValues;
        }
    }

    /**
     * get secondModule value
     *
     * @return secondModule
     */
    @Override
    public String getSecondModule() {
        return secondModule;
    }

    /**
     * set secondModule value
     *
     * @param secondModule secondModule
     */
    @Override
    public void setSecondModule(String secondModule) {
        this.secondModule = secondModule;
    }
}
