package cn.featherfly.easyapi.codegen;

import cn.featherfly.common.bean.BeanUtils;
import cn.featherfly.common.function.ThConsumer;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.Str;
import cn.featherfly.common.lang.Strings;
import io.swagger.codegen.v3.*;
import io.swagger.codegen.v3.generators.DefaultCodegenConfig;
import io.swagger.codegen.v3.generators.java.AbstractJavaCodegen;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.*;
import io.swagger.v3.parser.util.SchemaTypeUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static io.swagger.codegen.v3.CodegenConstants.IS_ENUM_EXT_NAME;
import static io.swagger.codegen.v3.generators.handlebars.ExtensionHelper.getBooleanValue;

/**
 *
 *
 * @author zhongj
 */
public interface EasyapiModuleJavaCodegen extends EnableExtParameters, ModuleAbility {

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
        if (!operation.getVendorExtensions().containsKey("x-server-parameters")) {
            return false;
        }

        List<String> serverParameters = (List<String>) operation.getVendorExtensions().get("x-server-parameters");
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
        if (!operation.getVendorExtensions().containsKey("x-server-parameters")) {
            return false;
        }

        List<String> serverParameters = (List<String>) operation.getVendorExtensions().get("x-server-parameters");
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
