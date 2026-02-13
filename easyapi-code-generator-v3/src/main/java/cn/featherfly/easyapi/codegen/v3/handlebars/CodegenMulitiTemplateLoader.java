package cn.featherfly.easyapi.codegen.v3.handlebars;

import cn.featherfly.common.lang.ClassLoaderUtils;
import com.github.jknack.handlebars.io.URLTemplateLoader;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class CodegenMulitiTemplateLoader extends URLTemplateLoader {

    private String templateDir;
    private String customTemplateDir;

    public CodegenMulitiTemplateLoader() {
        setSuffix(".mustache");
    }

    @Override
    public String resolve(String uri) {
        if (!uri.endsWith(this.getSuffix())) {
            uri = uri + this.getSuffix();
        }
        File templateFile = new File(uri);
        if (templateFile.exists()) {
            return templateFile.toString();
        }
        templateFile = new File(this.getPrefix() + this.normalize(uri));
        if (templateFile.exists()) {
            return templateFile.toString();
        }
        if (this.customTemplateDir != null) {
            templateFile = new File(this.customTemplateDir, this.normalize(uri));
            // read from dir
            if (templateFile.exists()) {
                return templateFile.toString();
            }
            // read from jar
            String templatePath = this.normalize(this.customTemplateDir + "/" + uri);
            if (this.getClass().getResource(templatePath) != null || ClassLoaderUtils.getResource(templatePath) != null) {
                return templatePath;
            }
        }
        if (getClass().getResource(this.getPrefix() + this.normalize(uri)) != null) {
            return this.getPrefix() + this.normalize(uri);
        }
        return this.templateDir + this.normalize(uri);
    }

    @Override
    public URL getResource(String location) throws IOException {
        if (this.customTemplateDir == null) {
            return this.getClass().getResource(location);
        }
        final File file = new File(location);
        if (file.exists()) {
            return file.toURI().toURL();
        }
        if (location.startsWith("/")) {
            return this.getClass().getResource(location);
        } else {
            return ClassLoaderUtils.getResource(location, this.getClass());
        }
    }

    public String getCustomTemplateDir() {
        return customTemplateDir;
    }

    public void setCustomTemplateDir(String customTemplateDir) {
        this.customTemplateDir = customTemplateDir;
    }

    public CodegenMulitiTemplateLoader customTemplateDir(String customTemplateDir) {
        this.customTemplateDir = customTemplateDir;
        return this;
    }

    public String getTemplateDir() {
        return templateDir;
    }

    public void setTemplateDir(String templateDir) {
        this.templateDir = this.getPrefix() + templateDir;
        if (!this.templateDir.endsWith("/")) {
            this.templateDir = this.templateDir + "/";
        }
    }

    public CodegenMulitiTemplateLoader templateDir(String templateDir) {
        setTemplateDir(templateDir);
        return this;
    }
}
