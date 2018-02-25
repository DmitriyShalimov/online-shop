package onlineshop.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.Properties;

public class PropertiesParser {
    private static Properties props = new Properties();
    private static Logger logger = LoggerFactory.getLogger(PropertiesParser.class);
    public static String getSQL(String name) {
        try {
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("SQL.properties"));
        } catch (IOException e) {
            logger.error("method getSQL:",e);
        }
        return props.getProperty(name);
    }
}
