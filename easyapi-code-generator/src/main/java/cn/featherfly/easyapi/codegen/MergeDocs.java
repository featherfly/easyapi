package cn.featherfly.easyapi.codegen;

import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import cn.featherfly.common.lang.ClassLoaderUtils;

public class MergeDocs {

    //    public final static String COMPONENTS = "components";
    public static final String SCHEMAS = "schemas";

    public static final String REF_NODE_NAME = "$ref";

    private static final ObjectMapper MAPPER = new ObjectMapper(new YAMLFactory());

    private static Map<String, JsonNode> findSchemasNodes(String file, Set<String> includeSchemas,
            String relativePathRoot) throws IOException {
        URL fileUrl = ClassLoaderUtils.getResource(file);
        String path = StringUtils.substringAfterLast(fileUrl.getPath(), relativePathRoot);
        path = StringUtils.substringBeforeLast("./" + path, "/");
        //        System.out.println(fileUrl);
        //        System.out.println(path);

        JsonNode rootNode = MAPPER.readTree(fileUrl);
        final Map<String, JsonNode> schemaNodes = new HashMap<>();
        //        System.out.println(schemasNodes);
        includeSchemas.forEach(s -> {
            String name = StringUtils.substringAfterLast(s, "/");
            JsonNode n = rootNode.findValue(SCHEMAS).findValue(name);
            if (n == null) {
                throw new RuntimeException("read schema " + name + "  from " + file + " error, schema not exits");
            }
            schemaNodes.put(name, n);
        });

        //        Iterator<JsonNode> iterator = schemaNodes.values().iterator();
        //        while(iterator.hasNext()) {
        //            JsonNode node = iterator.next();
        //        }
        List<Map<String, Set<String>>> extFilesComponentsList = new ArrayList<>();
        for (JsonNode node : schemaNodes.values()) {
            final Map<String, Set<String>> extFilesComponents = findExtFilesComponents(node, path, true, file);
            Set<String> defines = extFilesComponents.get(file);
            if (defines != null) {
                Iterator<String> iter = defines.iterator();
                while (iter.hasNext()) {
                    if (includeSchemas.contains(iter.next())) {
                        iter.remove();
                    }
                }
            }
            extFilesComponentsList.add(extFilesComponents);
        }
        for (Map<String, Set<String>> extFilesComponents : extFilesComponentsList) {
            for (Map.Entry<String, Set<String>> entry : extFilesComponents.entrySet()) {
                Map<String, JsonNode> newSchemasNodes = findSchemasNodes(entry.getKey(), entry.getValue(),
                        relativePathRoot);
                schemaNodes.putAll(newSchemasNodes);
            }
        }
        return schemaNodes;
    }

    public static void mergeSchemas(String target, Writer writer) throws IOException {
        JsonNode jsonNode = mergeSchemas(target);
        MAPPER.writer().writeValue(writer, jsonNode);
        writer.close();
    }

    //    private static void extFiles(JsonNode jsonNode, Map<String, Set<String>> extFilesComponents, String path) {
    //        jsonNode.findValues("$ref").forEach(ref -> {
    //            String rv = ref.asText();
    //            if (rv.startsWith("./") || rv.startsWith("../")) {
    //                String extFile = StringUtils.substringBefore(rv, "#");
    //                extFile = path + "/" + extFile;
    //                Set<String> components = extFilesComponents.get(extFile);
    //                if (components == null) {
    //                    components = new HashSet<>();
    //                    extFilesComponents.put(extFile, components);
    //                }
    //                components.add(StringUtils.substringAfter(rv, "#"));
    //            }
    //        });
    //    }

    private static Map<String, Set<String>> findExtFilesComponents(JsonNode node, String relativePathRoot,
            boolean includeCuurent, String cuurentFile) {
        final Map<String, Set<String>> extFilesComponents = new HashMap<>();
        node.findParents(REF_NODE_NAME).forEach(refNode -> {
            //            System.out.println(refNode);
            //            merge(refNode,path, extFilesComponents);
            String rv = refNode.findPath(REF_NODE_NAME).asText();
            if (rv.startsWith("./") || rv.startsWith("../")) {
                String extFile = StringUtils.substringBefore(rv, "#");
                if (relativePathRoot.length() > 0) {
                    extFile = relativePathRoot + "/" + extFile;
                }
                Set<String> components = extFilesComponents.get(extFile);
                if (components == null) {
                    components = new HashSet<>();
                    extFilesComponents.put(extFile, components);
                }
                String include = StringUtils.substringAfter(rv, "#");
                components.add(include);

                ((ObjectNode) refNode).set(REF_NODE_NAME, new TextNode("#" + include));
            } else if (includeCuurent) {
                String extFile = cuurentFile;
                Set<String> components = extFilesComponents.get(extFile);
                if (components == null) {
                    components = new HashSet<>();
                    extFilesComponents.put(extFile, components);
                }
                String include = StringUtils.substringAfter(rv, "#");
                components.add(include);
            }
        });
        return extFilesComponents;
    }

    public static JsonNode mergeSchemas(String target) throws IOException {
        URL targetURL = ClassLoaderUtils.getResource(target, MergeDocs.class);
        String relativePath = StringUtils.substringBeforeLast(target, "/");
        String rootPath = StringUtils.substringBeforeLast(targetURL.getPath(), target);

        GenConstants.LOG.info("target: {}", targetURL);
        //        final Map<String, Set<String>> extFilesComponents = new HashMap<>();
        JsonNode rootNode = MAPPER.readTree(targetURL);
        final Map<String, Set<String>> extFilesComponents = findExtFilesComponents(rootNode, relativePath, false,
                target);
        //        System.out.println(rootNode);
        ObjectNode schemaNode = (ObjectNode) rootNode.findValue(SCHEMAS);

        if (schemaNode == null) {
            System.out.println("there is no schemas found in " + targetURL.getPath());

            return rootNode;
        }

        for (Map.Entry<String, Set<String>> entry : extFilesComponents.entrySet()) {
            //                System.out.println(entry);
            Map<String, JsonNode> newSchemasNodes = findSchemasNodes(entry.getKey(), entry.getValue(), rootPath);
            //            System.out.println(newSchemasNodes);
            newSchemasNodes.forEach((k, v) -> {
                //                System.out.println(k);
                //                System.out.println(v);
                schemaNode.set(k, v);
            });
        }

        return rootNode;
    }

    //    private static void mergeArray(ObjectNode target, ObjectNode source, String name) {
    //        ObjectNode node = (ObjectNode) (target.get(name));
    //        ObjectNode nodeAdded = (ObjectNode) (source.get(name));
    //
    //        if (node == null) {
    //            node = target.putObject(name);
    //        }
    //        if (nodeAdded != null) {
    //            node.setAll(nodeAdded);
    //        }
    //
    //    }
}
