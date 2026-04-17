package cn.featherfly.easyapi.codegen;

import cn.featherfly.common.bean.BeanDescriptor;
import cn.featherfly.common.bean.BeanProperty;
import cn.featherfly.common.function.ThConsumer;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.WordUtils;
import cn.featherfly.common.structure.ChainMapImpl;
import cn.featherfly.conversion.codegen.*;
import io.swagger.codegen.v3.*;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.Schema;
import org.apache.commons.lang3.StringUtils;

import java.time.*;
import java.util.*;

/**
 *
 *
 * @author zhongj
 */
public interface EasyapiModuleJavaCodegen extends EnableExtParameters, ModuleAbility {

    public static final String X_CONVERTOR = "x-convertor";
    public static final String X_SERVER_PARAMETERS = "x-server-parameters";

    public default CodegenOperation fromOperation(CodegenOperation op,
                                                  ThConsumer<EasyapiModuleJavaCodegen, CodegenContent,
                                                          List<CodegenParameter>> addParameters,
                                                  String path, String httpMethod,
                                                  Operation operation,
                                                  Map<String, Schema> schemas, OpenAPI openAPI) {
        if (!processServerParameter(op, addParameters)) {
            String notes = op.notes;
            if (notes != null) {
                List<CodegenParameter> codegenParameters = new ArrayList<>();
                getExtParameters().forEach(extParameter -> {
                    if (notes.contains("@" + extParameter.getName())) {
                        codegenParameters.add(extParameter.getCodegenParameter().copy());
                    }
                });
                if (!codegenParameters.isEmpty()) {
                    for (CodegenContent content : op.getContents()) {
                        addParameters.accept(this, content, codegenParameters);
                    }
                }
            }
        }
        // process hasMore
        for (CodegenContent content : op.getContents()) {
            for (CodegenParameter parameter : content.getParameters()) {
                parameter.vendorExtensions.put(CodegenConstants.HAS_MORE_EXT_NAME, Boolean.TRUE);
            }
            if (!content.getParameters().isEmpty()) {
                content.getParameters().get(content.getParameters().size() - 1).vendorExtensions
                        .put(CodegenConstants.HAS_MORE_EXT_NAME, Boolean.FALSE);
            }
        }
        return op;
    }

    public default boolean processServerParameter(CodegenOperation operation, ThConsumer<EasyapiModuleJavaCodegen,
            CodegenContent, List<CodegenParameter>> addParameters) {
        if (!operation.getVendorExtensions().containsKey(X_SERVER_PARAMETERS)) {
            return false;
        }

        List<String> serverParameters = (List<String>) operation.getVendorExtensions().get(X_SERVER_PARAMETERS);
        if (Lang.isEmpty(serverParameters)) {
            return false;
        }

        boolean result = false;

        List<CodegenParameter> codegenParameters = new ArrayList<>();
        for (String serverParameter : serverParameters) {
            for (ExtParameter extParameter : getExtParameters()) {
                if (extParameter.getName().equals(serverParameter)) {
                    codegenParameters.add(extParameter.getCodegenParameter().copy());
                    result = true;
                }
            }
        }
        if (!codegenParameters.isEmpty()) {
            for (CodegenContent content : operation.getContents()) {
                addParameters.accept(this, content, codegenParameters);
            }
        }
        return result;
    }

    public default Map<String, Object> postProcessOperations(Map<String, Object> objs) {
        @SuppressWarnings("unchecked")
        Map<String, Object> operations = (Map<String, Object>) objs.get("operations");
        if (operations != null) {
            @SuppressWarnings("unchecked")
            List<CodegenOperation> ops = (List<CodegenOperation>) operations.get("operation");
            for (CodegenOperation operation : ops) {
                if (operation.returnType != null) {
                    if (operation.returnType.startsWith("Pagination")) {
                        String rt = operation.returnType;
                        operation.returnType = "cn.featherfly.common.structure.page.PaginationResults<" + rt.substring("Pagination".length(), rt.length())
                                + ">";
//                        addExtParameterImports(objs, "cn.featherfly.common.structure.page.PaginationResults");
                    }
                }
                if (!processServerParameter(operation, objs)) {
                    String notes = operation.notes;
                    if (notes != null) {
                        getExtParameters().forEach(extParameter -> {
                            if (notes.contains(extParameter.getName())) {
                                addAddtionalParameter(extParameter.getCodegenParameter().copy(), operation,
                                        extParameter.getParamTypeDefined(), extParameter.getParamName());
//                                addExtParameterImports(objs, extParameter.getParamTypeImport());
                            }
                        });
                    }
                }
            }
        }
        return objs;
    }


    public default boolean processServerParameter(CodegenOperation operation, Map<String, Object> objs) {
        if (!operation.getVendorExtensions().containsKey(X_SERVER_PARAMETERS)) {
            return false;
        }

        List<String> serverParameters = (List<String>) operation.getVendorExtensions().get(X_SERVER_PARAMETERS);
        if (Lang.isEmpty(serverParameters)) {
            return false;
        }

        boolean result = false;
        for (String serverParameter : serverParameters) {
            for (ExtParameter extParameter : getExtParameters()) {
                if (extParameter.getName().equals(serverParameter)) {
                    addAddtionalParameter(extParameter.getCodegenParameter().copy(), operation,
                            extParameter.getParamTypeDefined(), extParameter.getParamName());
                    addExtParameterImports(objs, extParameter.getParamTypeImport());
                    result = true;
                }
            }
        }
        return result;
    }

    public default Map<String, Object> postProcessAllModels0(Map<String, Object> objs) {
        for (Object obj : objs.values()) {
            processObjectConvertor(obj, objs);
        }
        return objs;
    }

    static void processObjectConvertor(Object obj, Map<String, Object> objs) {
        CodegenModel model = ((CodegenModel) ((List<Map>) ((Map) obj).get("models")).get(0).get("model"));
        if (model.getVendorExtensions() == null || !model.getVendorExtensions().containsKey(X_CONVERTOR)) {
            return;
        }
        List<String> convertorParameters = (List<String>) model.getVendorExtensions().get(X_CONVERTOR);
        if (Lang.isEmpty(convertorParameters)) {
            return;
        }

        BeanCodegen beanCodegen = new BeanCodegenImpl(1);
        List<Map<String, String>> convertors = new ArrayList<>();
        for (String toType : convertorParameters) {
            BeanDescriptor<?> bd = BeanDescriptor.getBeanDescriptor(ClassUtils.forName(toType));
            String targetName = WordUtils.lowerCaseFirst(bd.getType().getSimpleName());
            List<ConvertibleProperty> properties = new ArrayList<>(model.getVars().size());
            for (CodegenProperty var : model.getAllVars()) {
                if (bd.hasBeanProperty(var.getName())) {
                    final Map<String, Object> om = (Map<String, Object>) objs.get(var.getDatatype());
                    if (om != null) {
                        properties.add(new ConvertiblePropertyImpl(var.getName(), new TypeMetadataImpl(om.get("package") + "." + var.getDatatype(),
                                isEnum(om)), new TypeMetadataImpl(bd.getBeanProperty(var.getName()).getType())));
                    } else {
                        TypeMetadata typeMetadata = getType(var.getDatatype(), objs);
                        if (typeMetadata.elementType() == null) {
                            properties.add(new ConvertiblePropertyImpl(var.getName(), typeMetadata,
                                    new TypeMetadataImpl(bd.getBeanProperty(var.getName()).getType())));
                        } else {
                            // FIXME 这里加入容器转换逻辑
                            BeanProperty<?, ?> bp = bd.getBeanProperty(var.getName());
                            properties.add(new ConvertiblePropertyImpl(var.getName(), typeMetadata,
                                    bp.getType().isArray() ? new TypeMetadataImpl(bp.getType())
                                            : new TypeMetadataImpl(bp.getType(), bp.getGenericType())));
                        }
                    }
                }
            }
            convertors.add(new ChainMapImpl<String, String>()
                    .set("to", beanCodegen.generateToTarget(new MethodMetadataImpl("to" + bd.getType().getSimpleName())
                            , null, bd.getTypeName(), properties, null, targetName))
                    .set("from", beanCodegen.generateFromTarget(new MethodMetadataImpl(model.getName(), true)
                            , model.getClassname(), bd.getTypeName(), properties, null, targetName)));
        }
        model.getVendorExtensions().put("convertors", convertors);
    }

    static class ContainerType {
        private String type;

        private String elementType;

        public ContainerType(Class<?> type) {
            this.type = CodegenUtils.getClassName(type);
        }

        public ContainerType(String type) {
            this.type = CodegenUtils.getClassName(type);
        }

        public ContainerType(Class<?> type, Class<?> elementType) {
            this.type = CodegenUtils.getClassName(type);
            this.elementType = CodegenUtils.getClassName(elementType);
        }

        public ContainerType(String type, String elementType) {
            this.type = CodegenUtils.getClassName(type);
            this.elementType = CodegenUtils.getClassName(elementType);
        }
    }

    private static String getTypeName(Map<String, Object> objs, String typeName) {
        final Map<String, Object> om = (Map<String, Object>) objs.get(typeName);
        if (om != null) {
            return om.get("package") + "." + typeName;
        }
        return null;
    }

    private static boolean isEnum(Map<String, Object> objectMap) {
        return isEnum((((CodegenModel) ((List<Map>) objectMap.get("models")).get(0).get("model"))));
    }
    private static boolean isEnum(CodegenModel codegenModel) {
        return Boolean.parseBoolean(codegenModel.getVendorExtensions().get("x-is-enum") + "");
    }

    private static TypeMetadata getType(Map<String, Object> objs, String typeName) {
        final Map<String, Object> om = (Map<String, Object>) objs.get(typeName);
        if (om != null) {
            return new TypeMetadataImpl(om.get("package") + "." + typeName, isEnum(om));
//            return ClassUtils.forName(om.get("package") + "." + typeName);
        }
        return null;
    }

    private static TypeMetadata getType(String basicType, Map<String, Object> objs) {
        Class<?> type = getBasicType(basicType);
        if (type != null) {
            return new TypeMetadataImpl(type);
        }
        return getCollectionType(basicType, objs);
    }

    private static Class<?> getBasicType(String basicType) {
        Class<?> type = ClassUtils.getPrimitiveType(basicType);
        if (type != null) {
            return type;
        }
        switch (basicType) {
            case "Boolean":
                return Boolean.class;
            case "Byte":
                return Byte.class;
            case "Character":
                return Character.class;
            case "Short":
                return Short.class;
            case "Integer":
                return Integer.class;
            case "Long":
                return Long.class;
            case "Float":
                return Float.class;
            case "Double":
                return Double.class;
            case "String":
                return String.class;
            case "Date":
                return Date.class;
            case "LocalDate":
                return LocalDate.class;
            case "LocalDateTime":
                return LocalDateTime.class;
            case "LocalTime":
                return LocalTime.class;
            case "OffsetDateTime":
                return OffsetDateTime.class;
            case "OffsetTime":
                return OffsetTime.class;
            default:
                return null;
        }
    }

    private static TypeMetadata getCollectionType(String basicType, Map<String, Object> objs) {
        switch (basicType) {
            case "Collection":
                return new TypeMetadataImpl(Collection.class);
            case "List":
                return new TypeMetadataImpl(List.class);
            case "Set":
                return new TypeMetadataImpl(Set.class);
            case "Queue":
                return new TypeMetadataImpl(Queue.class);
//            case "Map":
//                return Map.class;
            default:
        }
        if (basicType.startsWith("List<")) {
            return new TypeMetadataImpl(List.class, getType(objs, extractTypeName(basicType)));
        } else if (basicType.startsWith("Set<")) {
            return new TypeMetadataImpl(Set.class, getType(objs, extractTypeName(basicType)));
        } else if (basicType.startsWith("Queue<")) {
            return new TypeMetadataImpl(Queue.class, getType(objs, extractTypeName(basicType)));
        } else if (basicType.startsWith("Collection<")) {
            return new TypeMetadataImpl(Collection.class, getType(objs, extractTypeName(basicType)));
        }
//        else if (basicType.startsWith("Map<")) {
//            return Map.class;
//        }
        return new TypeMetadataImpl(ClassUtils.forName(basicType));
    }

    private static String extractTypeName(String basicType) {
        return basicType.substring(basicType.indexOf('<') + 1, basicType.indexOf('>'));
    }



    public default void addAddtionalParameter(CodegenParameter params, CodegenOperation operation, String dataType,
                                              String paramName) {
        params.dataType = dataType;
        params.paramName = paramName;
        params.baseName = paramName;
        if (StringUtils.isEmpty(params.description)) {
            params.description = paramName;
        }
        params.unescapedDescription = params.description;
        operation.allParams.add(params);
        // TODO 测试
        // operation.allParams = addHasMore(operation.allParams);
    }


    public String getDefaultTemplateDir();

    public static void addParameters(CodegenContent codegenContent, List<CodegenParameter> codegenParameters) {
        if (codegenParameters == null || codegenParameters.isEmpty()) {
            return;
        }
        for (CodegenParameter codegenParameter : codegenParameters) {
            codegenContent.getParameters().add(codegenParameter.copy());
        }
    }

    public static void addExtParameterImports(Map<String, Object> objs, String importClass) {
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
}
