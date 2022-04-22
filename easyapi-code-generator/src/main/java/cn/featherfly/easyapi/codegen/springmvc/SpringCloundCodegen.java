package cn.featherfly.easyapi.codegen.springmvc;

import java.util.Arrays;
import java.util.HashSet;

import cn.featherfly.easyapi.codegen.EasyapiAbstractJavaCodegen;
import io.swagger.codegen.v3.CodegenType;

public abstract class SpringCloundCodegen extends EasyapiAbstractJavaCodegen {

    //    public SpringCloundCodegen(String module) {
    //        super(module);
    public SpringCloundCodegen() {
        super();
        //        setLibrary("feign");
        setDateLibrary("legacy");
        super.processOpts();
        //        setDateLibrary("java8-localdatetime");
        outputFolder = "generated-code/javaSpringMVC";
        modelTemplateFiles.put("model.mustache", ".java");

        apiDocPath = apiDocPath + "/" + module;
        modelDocPath = modelDocPath + "/" + module;

        apiPackage = "cn.featherfly.easyapi.api";
        modelPackage = "cn.featherfly.easyapi.model";
        configPackage = "cn.featherfly.easyapi.configuration";
        templateDir = "codegen/" + getDefaultTemplateDir();

        //		modelPackage = "mobi.meihuo.platform.v2.web.api.%s.dto";
        //		apiPackage = "mobi.meihuo.platform.v2.web.api.%s";

        setInvokerPackage(invokerPackage);
        setGroupId(groupId);
        setArtifactId(artifactId);
        setArtifactVersion(artifactVersion);
        setTitle(title);
        setConfigPackage(configPackage);
        setDefaultApiPath(defaultApiPath);
        additionalProperties.put("apiPackage", apiPackage());
        additionalProperties.put("modelPackage", modelPackage());
        languageSpecificPrimitives = new HashSet<>(
                Arrays.asList("String", "boolean", "Boolean", "Double", "Integer", "Long", "Float"));
    }

    @Override
    public CodegenType getTag() {
        return CodegenType.SERVER;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public String getHelp() {
        return "Generates a Java Spring-MVC Server application using the SpringFox integration.";
    }

    @Override
    public void processOpts() {
        super.processOpts();
        supportingFiles.clear();
    }

    //    /**
    //     * Convert Swagger Operation object to Codegen Operation object
    //     *
    //     * @param path the path of the operation
    //     * @param httpMethod HTTP method
    //     * @param operation Swagger operation object
    //     * @param schemas a map of schemas
    //     * @param openAPI a OpenAPI object representing the spec
    //     * @return Codegen Operation object
    //     */
    //    public CodegenOperation fromOperation(String path, String httpMethod, Operation operation, Map<String, Schema> schemas, OpenAPI openAPI) {
    //        CodegenOperation codegenOperation = CodegenModelFactory.newInstance(CodegenModelType.OPERATION);
    //        Set<String> imports = new HashSet<String>();
    //        if (operation.getExtensions() != null && !operation.getExtensions().isEmpty()) {
    //            codegenOperation.vendorExtensions.putAll(operation.getExtensions());
    //        }
    //
    //        String operationId = getOrGenerateOperationId(operation, path, httpMethod);
    //        // remove prefix in operationId
    //        if (removeOperationIdPrefix) {
    //            int offset = operationId.indexOf('_');
    //            if (offset > -1) {
    //                operationId = operationId.substring(offset + 1);
    //            }
    //        }
    //        operationId = removeNonNameElementToCamelCase(operationId);
    //        codegenOperation.path = path;
    //        codegenOperation.operationId = toOperationId(operationId);
    //        codegenOperation.summary = escapeText(operation.getSummary());
    //        codegenOperation.unescapedNotes = operation.getDescription();
    //        codegenOperation.notes = escapeText(operation.getDescription());
    //        codegenOperation.getVendorExtensions().put(CodegenConstants.HAS_CONSUMES_EXT_NAME, Boolean.FALSE);
    //        codegenOperation.getVendorExtensions().put(CodegenConstants.HAS_PRODUCES_EXT_NAME, Boolean.FALSE);
    //        if (operation.getDeprecated() != null) {
    //            codegenOperation.getVendorExtensions().put(CodegenConstants.IS_DEPRECATED_EXT_NAME, operation.getDeprecated());
    //        }
    //
    //        addConsumesInfo(operation, codegenOperation, openAPI);
    //
    //        if (operation.getResponses() != null && !operation.getResponses().isEmpty()) {
    //            ApiResponse methodResponse = findMethodResponse(operation.getResponses());
    //
    //            for (String key : operation.getResponses().keySet()) {
    //                ApiResponse response = operation.getResponses().get(key);
    //
    //                addProducesInfo(response, codegenOperation);
    //
    //                CodegenResponse codegenResponse = fromResponse(key, response);
    //                codegenResponse.getVendorExtensions().put(CodegenConstants.HAS_MORE_EXT_NAME, Boolean.TRUE);
    //                if (codegenResponse.baseType != null && !defaultIncludes.contains(codegenResponse.baseType) && !languageSpecificPrimitives.contains(codegenResponse.baseType)) {
    //                    imports.add(codegenResponse.baseType);
    //                }
    //                codegenResponse.getVendorExtensions().put(CodegenConstants.IS_DEFAULT_EXT_NAME, response == methodResponse);
    //                codegenOperation.responses.add(codegenResponse);
    //                if (getBooleanValue(codegenResponse, CodegenConstants.IS_BINARY_EXT_NAME) && getBooleanValue(codegenResponse, CodegenConstants.IS_DEFAULT_EXT_NAME)) {
    //                    codegenOperation.getVendorExtensions().put(CodegenConstants.IS_RESPONSE_BINARY_EXT_NAME, Boolean.TRUE);
    //                }
    //                if (getBooleanValue(codegenResponse, CodegenConstants.IS_FILE_EXT_NAME) && getBooleanValue(codegenResponse, CodegenConstants.IS_DEFAULT_EXT_NAME)) {
    //                    codegenOperation.getVendorExtensions().put(CodegenConstants.IS_RESPONSE_FILE_EXT_NAME, Boolean.TRUE);
    //                }
    //            }
    //            if (codegenOperation.produces != null){
    //                Set<String> mediaTypes = new HashSet<String>();
    //                codegenOperation.produces.removeIf(map -> !mediaTypes.add(map.get("mediaType")));
    //                codegenOperation.produces.get(codegenOperation.produces.size() - 1).remove("hasMore");
    //            }
    //            codegenOperation.responses.get(codegenOperation.responses.size() - 1).getVendorExtensions().put(CodegenConstants.HAS_MORE_EXT_NAME, Boolean.FALSE);
    //
    //            if (methodResponse != null) {
    //                final Schema responseSchema = getSchemaFromResponse(methodResponse);
    //                if (responseSchema != null) {
    //                    final CodegenProperty codegenProperty = fromProperty("response", responseSchema);
    //
    //                    if (responseSchema instanceof ArraySchema) {
    //                        ArraySchema arraySchema = (ArraySchema) responseSchema;
    //                        CodegenProperty innerProperty = fromProperty("response", arraySchema.getItems());
    //                        codegenOperation.returnBaseType = innerProperty.baseType;
    //                    } else if (responseSchema instanceof MapSchema && hasSchemaProperties(responseSchema)) {
    //                        MapSchema mapSchema = (MapSchema) responseSchema;
    //                        CodegenProperty innerProperty = fromProperty("response", (Schema) mapSchema.getAdditionalProperties());
    //                        codegenOperation.returnBaseType = innerProperty.baseType;
    //                    } else if (responseSchema instanceof MapSchema  && hasTrueAdditionalProperties(responseSchema)) {
    //                        CodegenProperty innerProperty = fromProperty("response", new ObjectSchema());
    //                        codegenOperation.returnBaseType = innerProperty.baseType;
    //                    } else {
    //                        if (codegenProperty.complexType != null) {
    //                            codegenOperation.returnBaseType = codegenProperty.complexType;
    //                        } else {
    //                            codegenOperation.returnBaseType = codegenProperty.baseType;
    //                        }
    //                    }
    //                    if (!additionalProperties.containsKey(CodegenConstants.DISABLE_EXAMPLES_OPTION)) {
    //                        codegenOperation.examples = new ExampleGenerator(openAPI).generate(null, null, responseSchema);
    //                    }
    //                    codegenOperation.defaultResponse = toDefaultValue(responseSchema);
    //                    codegenOperation.returnType = codegenProperty.datatype;
    //                    boolean hasReference = schemas != null && schemas.containsKey(codegenOperation.returnBaseType);
    //                    codegenOperation.getVendorExtensions().put(CodegenConstants.HAS_REFERENCE_EXT_NAME, hasReference);
    //
    //                    // lookup discriminator
    //                    if (schemas != null) {
    //                        Schema schemaDefinition = schemas.get(codegenOperation.returnBaseType);
    //                        if (schemaDefinition != null) {
    //                            CodegenModel cmod = fromModel(codegenOperation.returnBaseType, schemaDefinition, schemas);
    //                            codegenOperation.discriminator = cmod.discriminator;
    //                        }
    //                    }
    //
    //                    boolean isContainer = getBooleanValue(codegenProperty, CodegenConstants.IS_CONTAINER_EXT_NAME);
    //                    if (isContainer) {
    //                        codegenOperation.returnContainer = codegenProperty.containerType;
    //                        if ("map".equals(codegenProperty.containerType)) {
    //                            codegenOperation.getVendorExtensions().put(CodegenConstants.IS_MAP_CONTAINER_EXT_NAME, Boolean.TRUE);
    //                        } else if ("list".equalsIgnoreCase(codegenProperty.containerType)) {
    //                            codegenOperation.getVendorExtensions().put(CodegenConstants.IS_LIST_CONTAINER_EXT_NAME, Boolean.TRUE);
    //                        } else if ("array".equalsIgnoreCase(codegenProperty.containerType)) {
    //                            codegenOperation.getVendorExtensions().put(CodegenConstants.IS_LIST_CONTAINER_EXT_NAME, Boolean.TRUE);
    //                        }
    //                    } else {
    //                        codegenOperation.returnSimpleType = true;
    //                    }
    //                    if (languageSpecificPrimitives().contains(codegenOperation.returnBaseType) || codegenOperation.returnBaseType == null) {
    //                        codegenOperation.returnTypeIsPrimitive = true;
    //                    }
    //                }
    //                Map<String, Header> componentHeaders = null;
    //                if ((openAPI != null) && (openAPI.getComponents() != null)) {
    //                    componentHeaders = openAPI.getComponents().getHeaders();
    //                }
    //                addHeaders(methodResponse, codegenOperation.responseHeaders, componentHeaders);
    //            }
    //        }
    //
    //        List<Parameter> parameters = operation.getParameters();
    //        CodegenParameter bodyParam = null;
    //        List<CodegenParameter> allParams = new ArrayList<>();
    //        List<CodegenParameter> bodyParams = new ArrayList<>();
    //        List<CodegenParameter> pathParams = new ArrayList<>();
    //        List<CodegenParameter> queryParams = new ArrayList<>();
    //        List<CodegenParameter> headerParams = new ArrayList<>();
    //        List<CodegenParameter> cookieParams = new ArrayList<>();
    //        List<CodegenParameter> formParams = new ArrayList<>();
    //        List<CodegenParameter> requiredParams = new ArrayList<>();
    //
    //        List<CodegenContent> codegenContents = new ArrayList<>();
    //
    //        RequestBody body = operation.getRequestBody();
    //        if (body != null) {
    //            if (StringUtils.isNotBlank(body.get$ref())) {
    //                String bodyName = OpenAPIUtil.getSimpleRef(body.get$ref());
    //                body = openAPI.getComponents().getRequestBodies().get(bodyName);
    //            }
    //
    //            List<Schema> foundSchemas = new ArrayList<>();
    //
    //            for (String contentType : body.getContent().keySet()) {
    //                boolean isForm = "application/x-www-form-urlencoded".equalsIgnoreCase(contentType) || "multipart/form-data".equalsIgnoreCase(contentType);
    //
    //                String schemaName = null;
    //                Schema schema = body.getContent().get(contentType).getSchema();
    //                if (schema != null && StringUtils.isNotBlank(schema.get$ref())) {
    //                    schemaName = OpenAPIUtil.getSimpleRef(schema.get$ref());
    //                    try {
    //                        schemaName = URLDecoder.decode(schemaName, StandardCharsets.UTF_8.name());
    //                    } catch (UnsupportedEncodingException e) {
    //                        logger.error("Could not decoded string: " + schemaName, e);
    //                    }
    //                    schema = schemas.get(schemaName);
    //                }
    //                final CodegenContent codegenContent = new CodegenContent(contentType);
    //                codegenContent.getContentExtensions().put(CodegenConstants.IS_FORM_EXT_NAME, isForm);
    //
    //                if (schema == null) {
    //                    CodegenParameter codegenParameter = CodegenModelFactory.newInstance(CodegenModelType.PARAMETER);
    //                    codegenParameter.description = body.getDescription();
    //                    codegenParameter.unescapedDescription = body.getDescription();
    //                    codegenParameter.baseName = REQUEST_BODY_NAME;
    //                    codegenParameter.paramName = REQUEST_BODY_NAME;
    //                    codegenParameter.dataType = "Object";
    //                    codegenParameter.baseType = "Object";
    //
    //                    codegenParameter.required = body.getRequired() != null ? body.getRequired() : Boolean.FALSE;
    //                    if (!isForm) {
    //                        codegenParameter.getVendorExtensions().put(CodegenConstants.IS_BODY_PARAM_EXT_NAME, Boolean.TRUE);
    //                    }
    //                    continue;
    //                }
    //                if (isForm) {
    //                    final Map<String, Schema> propertyMap = schema.getProperties();
    //                    boolean isMultipart = contentType.equalsIgnoreCase("multipart/form-data");
    //                    if (propertyMap != null && !propertyMap.isEmpty()) {
    //                        for (String propertyName : propertyMap.keySet()) {
    //                            CodegenParameter formParameter = fromParameter(new Parameter()
    //                                    .name(propertyName)
    //                                    .required(body.getRequired())
    //                                    .schema(propertyMap.get(propertyName)), imports);
    //                            if (isMultipart) {
    //                                formParameter.getVendorExtensions().put(CodegenConstants.IS_MULTIPART_EXT_NAME, Boolean.TRUE);
    //                            }
    //                            // todo: this segment is only to support the "older" template design. it should be removed once all templates are updated with the new {{#contents}} tag.
    //                            formParameter.getVendorExtensions().put(CodegenConstants.IS_FORM_PARAM_EXT_NAME, Boolean.TRUE);
    //                            formParams.add(formParameter.copy());
    //                            if (body.getRequired() != null && body.getRequired()) {
    //                                requiredParams.add(formParameter.copy());
    //                            }
    //                            allParams.add(formParameter);
    //                        }
    //                        codegenContents.add(codegenContent);
    //                    }
    //                } else {
    //                    bodyParam = fromRequestBody(body, schemaName, schema, schemas, imports);
    //                    if (foundSchemas.isEmpty()) {
    //                        // todo: this segment is only to support the "older" template design. it should be removed once all templates are updated with the new {{#contents}} tag.
    //                        bodyParams.add(bodyParam.copy());
    //                        allParams.add(bodyParam);
    //                    } else {
    //                        boolean alreadyAdded = false;
    //                        for (Schema usedSchema : foundSchemas) {
    //                            if (alreadyAdded = usedSchema.equals(schema)) {
    //                                break;
    //                            }
    //                        }
    //                        if (alreadyAdded) {
    //                            continue;
    //                        }
    //                    }
    //                    foundSchemas.add(schema);
    //                    codegenContents.add(codegenContent);
    //                }
    //            }
    //        }
    //
    //        if (parameters != null) {
    //            for (Parameter param : parameters) {
    //                if (StringUtils.isNotBlank(param.get$ref())) {
    //                    param = getParameterFromRef(param.get$ref(), openAPI);
    //                }
    //                CodegenParameter codegenParameter = fromParameter(param, imports);
    //                allParams.add(codegenParameter);
    //                // Issue #2561 (neilotoole) : Moved setting of is<Type>Param flags
    //                // from here to fromParameter().
    //                if (param instanceof QueryParameter || "query".equalsIgnoreCase(param.getIn())) {
    //                    queryParams.add(codegenParameter.copy());
    //                } else if (param instanceof PathParameter || "path".equalsIgnoreCase(param.getIn())) {
    //                    pathParams.add(codegenParameter.copy());
    //                } else if (param instanceof HeaderParameter || "header".equalsIgnoreCase(param.getIn())) {
    //                    headerParams.add(codegenParameter.copy());
    //                } else if (param instanceof CookieParameter || "cookie".equalsIgnoreCase(param.getIn())) {
    //                    cookieParams.add(codegenParameter.copy());
    //                }
    //                if (codegenParameter.required) {
    //                    requiredParams.add(codegenParameter.copy());
    //                }
    //            }
    //        }
    //
    //        for (String i : imports) {
    //            if (needToImport(i)) {
    //                codegenOperation.imports.add(i);
    //            }
    //        }
    //
    //        codegenOperation.bodyParam = bodyParam;
    //        codegenOperation.httpMethod = httpMethod.toUpperCase();
    //
    //        // move "required" parameters in front of "optional" parameters
    //        if (sortParamsByRequiredFlag) {
    //            Collections.sort(allParams, new Comparator<CodegenParameter>() {
    //                @Override
    //                public int compare(CodegenParameter one, CodegenParameter another) {
    //                    if (one.required == another.required) return 0;
    //                    else if (one.required) return -1;
    //                    else return 1;
    //                }
    //            });
    //        }
    //
    //        codegenOperation.allParams = addHasMore(allParams);
    //        codegenOperation.bodyParams = addHasMore(bodyParams);
    //        codegenOperation.pathParams = addHasMore(pathParams);
    //        codegenOperation.queryParams = addHasMore(queryParams);
    //        codegenOperation.headerParams = addHasMore(headerParams);
    //        codegenOperation.cookieParams = addHasMore(cookieParams);
    //        codegenOperation.formParams = addHasMore(formParams);
    //        codegenOperation.requiredParams = addHasMore(requiredParams);
    //        codegenOperation.externalDocs = operation.getExternalDocs();
    //
    //        configuresParameterForMediaType(codegenOperation, codegenContents);
    //        // legacy support
    //        codegenOperation.nickname = codegenOperation.operationId;
    //
    //        if (codegenOperation.allParams.size() > 0) {
    //            codegenOperation.getVendorExtensions().put(CodegenConstants.HAS_PARAMS_EXT_NAME, Boolean.TRUE);
    //        }
    //        boolean hasRequiredParams = codegenOperation.requiredParams.size() > 0;
    //        codegenOperation.getVendorExtensions().put(CodegenConstants.HAS_REQUIRED_PARAMS_EXT_NAME, hasRequiredParams);
    //
    //        boolean hasOptionalParams = codegenOperation.allParams.stream()
    //                .anyMatch(codegenParameter -> !codegenParameter.required);
    //        codegenOperation.getVendorExtensions().put(CodegenConstants.HAS_OPTIONAL_PARAMS_EXT_NAME, hasOptionalParams);
    //
    //        // set Restful Flag
    //        codegenOperation.getVendorExtensions().put(CodegenConstants.IS_RESTFUL_SHOW_EXT_NAME, codegenOperation.getIsRestfulShow());
    //        codegenOperation.getVendorExtensions().put(CodegenConstants.IS_RESTFUL_INDEX_EXT_NAME, codegenOperation.getIsRestfulIndex());
    //        codegenOperation.getVendorExtensions().put(CodegenConstants.IS_RESTFUL_CREATE_EXT_NAME, codegenOperation.getIsRestfulCreate());
    //        codegenOperation.getVendorExtensions().put(CodegenConstants.IS_RESTFUL_UPDATE_EXT_NAME, codegenOperation.getIsRestfulUpdate());
    //        codegenOperation.getVendorExtensions().put(CodegenConstants.IS_RESTFUL_DESTROY_EXT_NAME, codegenOperation.getIsRestfulDestroy());
    //        codegenOperation.getVendorExtensions().put(CodegenConstants.IS_RESTFUL_EXT_NAME, codegenOperation.getIsRestful());
    //
    //        configureDataForTestTemplate(codegenOperation);
    //
    //        return codegenOperation;
    //    }

    @Override
    public String getDefaultTemplateDir() {
        return "easyapi-springmvc";
    }

}
