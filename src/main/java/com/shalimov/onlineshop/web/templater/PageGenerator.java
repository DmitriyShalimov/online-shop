package com.shalimov.onlineshop.web.templater;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.Map;

public class PageGenerator {
    private static final String HTML_DIR = "templates/onlineshop/";
    private static PageGenerator pageGenerator;
    private final Configuration configuration;

    public static PageGenerator instance() {
        if (pageGenerator == null) {
            pageGenerator = new PageGenerator();
        }
        return pageGenerator;
    }

    public String getPage(String filename, Map<String, Object> data) {
        Writer stream = new StringWriter();
        try {
          ClassTemplateLoader templateLoader = new ClassTemplateLoader(getClass(), "/webapp");
            configuration.setTemplateLoader(templateLoader);
            Template template = configuration.getTemplate(filename);
            template.process(data, stream);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
        return stream.toString();
    }

    private PageGenerator() {
        configuration = new Configuration();
    }

    public String getPage(String filename) {
        return getPage(filename, Collections.emptyMap());
    }
}
