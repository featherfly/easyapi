package cn.featherfly.easyapi.codegen.v3.handlebars;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import java.io.IOException;
import java.io.Writer;

public class SnakecaseLambda implements Mustache.Lambda {
    @Override
    public void execute(Template.Fragment fragment, Writer writer) throws IOException {
        // fragment.execute() 获取区块内渲染后的文本
        String input = fragment.execute();
        
        // 驼峰转下划线 + 空格/连字符转下划线
        String result = input
            .replaceAll("([a-z])([A-Z])", "$1_$2")
            .replaceAll("[\\s-]+", "_")
            .toLowerCase();
        
        writer.write(result);
    }
}