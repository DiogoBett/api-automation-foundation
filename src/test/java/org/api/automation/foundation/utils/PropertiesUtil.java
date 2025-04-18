package org.api.automation.foundation.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;

import static org.api.automation.foundation.constants.Constants.*;

@Slf4j
public class PropertiesUtil {

    private static final Properties properties = new Properties();

    private PropertiesUtil() {}

    static {
        try {
            FileInputStream inputStream = new FileInputStream(PATH_PROPERTIES);
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            log.error(LOG_ERROR_IO_PROPERTIES, e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getEncodedProperty(String key) {
        return Base64.getEncoder().encodeToString(properties.getProperty(key).getBytes());
    }

    public static void setProperty(String key, String value) {
        try {
            properties.setProperty(key, value);
        } catch (NullPointerException e) {
            log.warn(LOG_WARN_INVALID_PROPERTY + "Property: " + key + "\nValue: " + value);
        }
    }

    public static String decodeProperty(String key) {
        byte[] decodedBytes = Base64.getDecoder().decode(key);
        return new String(decodedBytes);
    }
}
