package org.stepik.kushnirenko.templater;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.Map;


public class PageGenerator {

    private static final String HTML_DIR = "templates";

    private final Configuration config;

    private static PageGenerator generator;

    private PageGenerator() {
        config = new Configuration();
    }

    public static PageGenerator instance() {
        if (generator == null) {
            generator = new PageGenerator();
        }
        return generator;
    }

    public String getPage(String filename, Map<String, Object> data) {
        Writer sWriter = new StringWriter();

        try {
            Template template = config.getTemplate(HTML_DIR + File.separator + filename);
            template.process(data, sWriter);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return sWriter.toString();
    }
}
