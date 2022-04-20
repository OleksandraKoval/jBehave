package config;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {

    private static final Logger LOG = LoggerFactory.getLogger(ConfigurationManager.class);
    private static final Properties properties = new Properties();

    private static final String RESOURCES_PATH =
            System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                    + File.separator + "resources";
    private static final String PROPERTY_FILE_PATH_MASK = RESOURCES_PATH + File.separator + "environments" + File.separator
            + "%s.properties";

    public static void loadProperties() throws IOException {
        String ENV_PROPERTY = "env";
        String environment = System.getProperty(ENV_PROPERTY);
        File file = new File(String.format(PROPERTY_FILE_PATH_MASK, environment));
        properties.load(new FileInputStream(file));
    }

    @SneakyThrows
    public static String getProperty(String propertyName) {
        LOG.debug("Read property " + propertyName + ": " + properties.getProperty(propertyName));
        return properties.getProperty(propertyName);
    }
}
