package io.github.diogobett.utils;

import static io.github.diogobett.constants.Constants.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Locale;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;

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

  public static String getBaseUrl() {
    String environment =
        System.getProperty(PROPERTY_ENVIRONMENT, getProperty(PROPERTY_ENVIRONMENT));
    if (environment == null || environment.isBlank()) {
      throw new IllegalStateException("The environment property must be configured.");
    }

    String baseUrl = getProperty(PROPERTY_BASE_URL_PREFIX + environment.toLowerCase(Locale.ROOT));
    if (baseUrl == null || baseUrl.isBlank()) {
      throw new IllegalStateException("No base URL is configured for environment: " + environment);
    }

    return baseUrl;
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
