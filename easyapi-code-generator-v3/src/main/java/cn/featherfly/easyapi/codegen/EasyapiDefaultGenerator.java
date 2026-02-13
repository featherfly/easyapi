package cn.featherfly.easyapi.codegen;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

import cn.featherfly.common.lang.ClassLoaderUtils;
import io.swagger.codegen.v3.CodegenConfig;
import io.swagger.codegen.v3.DefaultGenerator;
import org.apache.commons.lang3.StringUtils;

/**
 * EasyapiDefaultGenerator.
 *
 * @author zhongj
 */
public class EasyapiDefaultGenerator extends DefaultGenerator {

    public String getFullTemplateFile(CodegenConfig config, String templateFile) {
        //1st the code will check if there's a <template folder>/libraries/<library> folder containing the file
        //2nd it will check for the file in the specified <template folder> folder
        //3rd it will check if there's an <embedded template>/libraries/<library> folder containing the file
        //4th and last it will assume the file is in <embedded template> folder.

        //check the supplied template library folder for the file
        final String library = config.getLibrary();
        if (StringUtils.isNotEmpty(library)) {
            //look for the file in the library subfolder of the supplied template
            if (StringUtils.isNotBlank(config.customTemplateDir())) {
                final String libTemplateFile = buildLibraryFilePath(config.customTemplateDir(), library, templateFile);
                if (new File(libTemplateFile).exists()) {
                    return libTemplateFile;
                }
                String libTemplatePath = libTemplateFile.replaceAll("\\\\", "/");
                if (this.getClass().getResource(libTemplatePath) != null || ClassLoaderUtils.getResource(libTemplatePath) != null) {
                    return libTemplatePath;
                }
            }
            final String libTemplateFile = buildLibraryFilePath(config.templateDir(), library, templateFile);
            if (new File(libTemplateFile).exists()) {
                return libTemplateFile;
            }
            final String libTemplatePath = libTemplateFile.replaceAll("\\\\", "/");
            if (this.getClass().getResource(libTemplatePath) != null || ClassLoaderUtils.getResource(libTemplatePath) != null) {
                return libTemplatePath;
            }
        }

        //check the supplied template main folder for the file
        if (StringUtils.isNotBlank(config.customTemplateDir())) {
            final String template = config.customTemplateDir() + File.separator + templateFile;
            if (new File(template).exists()) {
                return template;
            }
            final String templatePath = template.replaceAll("\\\\", "/");
            if (this.getClass().getResource(templatePath) != null || ClassLoaderUtils.getResource(templatePath) != null) {
                return templatePath;
            }
        }
        final String template = config.templateDir() + File.separator + templateFile;
        if (new File(template).exists()) {
            return template;
        }
        final String templatePath = template.replaceAll("\\\\", "/");
        if (this.getClass().getResource(templatePath) != null || ClassLoaderUtils.getResource(templatePath) != null) {
            return templatePath;
        }

        //try the embedded template library folder next
        if (StringUtils.isNotEmpty(library)) {
            final String embeddedLibTemplateFile = buildLibraryFilePath(config.embeddedTemplateDir(), library, templateFile);
            if (embeddedTemplateExists(embeddedLibTemplateFile)) {
                // Fall back to the template file embedded/packaged in the JAR file library folder...
                return embeddedLibTemplateFile;
            }
            final String embeddedLibTemplatePath = template.replaceAll("\\\\", "/");
            if (this.getClass().getResource(embeddedLibTemplatePath) != null || ClassLoaderUtils.getResource(embeddedLibTemplatePath) != null) {
                return embeddedLibTemplatePath;
            }
        }

        // Fall back to the template file embedded/packaged in the JAR file...
        return config.embeddedTemplateDir() + File.separator + templateFile;
    }

    private String buildLibraryFilePath(String dir, String library, String file) {
        return dir + File.separator + "libraries" + File.separator + library + File.separator + file;
    }

    @Override
    public Reader getTemplateReader(String name) {
        try {
            // 修改了读取逻辑
            InputStream is = ClassLoaderUtils.getResourceAsStream(getCPResourcePath(name), this.getClass());
            if (is == null) {
                is = new FileInputStream(new File(name)); // May throw but never return a null value
            }
            return new InputStreamReader(is, "UTF-8");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        throw new RuntimeException("can't load template " + name);
    }
}
