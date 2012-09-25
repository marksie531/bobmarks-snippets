package org.bobmarks.freemarker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerExample {

    public static void main(String[] args) throws IOException,
            TemplateException {
        Configuration cfg = new Configuration();
        Template template = cfg.getTemplate("template.ftl");

        // Create data model
        Map<String, Object> data = new HashMap<String, Object>();

        data.put("message", "Hello World");
        data.put("people", new String [] {"Bob", "Dave", "Joe"});

        // File output
        Writer file = new FileWriter(new File("output.txt"));
        template.process(data, file);
        file.flush();
        file.close();
    }

}