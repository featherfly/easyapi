package cn.featherfly.easyapi;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

import cn.featherfly.common.lang.ClassLoaderUtils;
import io.swagger.codegen.v3.CodegenConfig;

/**
 * EasyapiDefaultGenerator.
 *
 * @author zhongj
 */
public class EasyapiDefaultGenerator extends DefaultGenerator {

    @Override
    public String getFullTemplateFile(CodegenConfig config, String templateFile) {
        String template = config.templateDir() + File.separator + templateFile;
        if (ClassLoaderUtils.getResource(getCPResourcePath(template), this.getClass()) != null) {
            return template;
        } else {
            String library = config.getLibrary();
            if (library != null && !"".equals(library)) {
                String libTemplateFile = config.embeddedTemplateDir() + File.separator + "libraries" + File.separator
                        + library + File.separator + templateFile;
                if (embeddedTemplateExists(libTemplateFile)) {
                    // Fall back to the template file embedded/packaged in the JAR file...
                    return libTemplateFile;
                }
            }
            // Fall back to the template file embedded/packaged in the JAR file...
            return config.embeddedTemplateDir() + File.separator + templateFile;
        }
    }

    @Override
    public String readTemplate(String name) {
        try {
            Reader reader = getTemplateReader(name);
            if (reader == null) {
                throw new RuntimeException("no file found");
            }
            Scanner s = new Scanner(reader).useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        throw new RuntimeException("can't load template " + name);
    }

    @Override
    public Reader getTemplateReader(String name) {
        try {
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
