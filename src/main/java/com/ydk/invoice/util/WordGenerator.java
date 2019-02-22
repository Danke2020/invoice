package com.ydk.invoice.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class WordGenerator {  
    private static Configuration configuration = null;
    private static Map<String, Template> allTemplates = null;
      
    static {
        configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setDefaultEncoding("utf-8");
        configuration.setClassForTemplateLoading(WordGenerator.class, "/templates");
        allTemplates = new HashMap<>();   // Java 7 钻石语法
    }

    private WordGenerator() {}

    public static synchronized File createDoc(Map<?, ?> dataMap, String templateName) {
        String name = "temp" + (int) (Math.random() * 1000) + ".doc";
        File f = new File(name);
        try {
            allTemplates.put("resume", configuration.getTemplate(templateName));
            Template t = allTemplates.get("resume");
            // 这个地方不能使用FileWriter因为需要指定编码类型否则生成的Word文档会因为有无法识别的编码而无法打开
            Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
            t.process(dataMap, w);
            w.close();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return f;
    }  
  
}