package com.shalimov.onlineshop.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class PropertiesParser {
    private Properties props = new Properties();
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public String getSQL(String name) {
        try {
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("SQL.properties"));
        } catch (IOException e) {
            logger.error("method getSQL:", e);
            throw new RuntimeException(e);
        }
        return props.getProperty(name);
    }
}
