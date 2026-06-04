package com.automation.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ConfigReader class reads configuration properties from config.properties file
 * Provides centralized access to application configuration
 *
 * @author Automation Framework Team
 * @version 1.0
 */
public class ConfigReader {

    private static final Logger logger = LogManager.getLogger(ConfigReader.class);
    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";

    static {
        loadProperties();
    }

    /**
     * Loads properties from config file
     */
    private static void loadProperties() {
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE_PATH);
            properties.load(fileInputStream);
            logger.info("Configuration properties loaded successfully from: " + CONFIG_FILE_PATH);
            fileInputStream.close();
        } catch (IOException e) {
            logger.error("Failed to load configuration properties: ", e);
            throw new RuntimeException("Cannot find or load config.properties file");
        }
    }

    /**
     * Gets property value by key
     *
     * @param key Property key
     * @return Property value
     */
    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            logger.warn("Property not found for key: " + key);
        }
        return value;
    }

    /**
     * Gets base URL
     *
     * @return Base URL
     */
    public static String getBaseUrl() {
        return getProperty("base.url");
    }

    /**
     * Gets implicit wait time in seconds
     *
     * @return Wait time
     */
    public static long getImplicitWait() {
        return Long.parseLong(getProperty("implicit.wait"));
    }

    /**
     * Gets explicit wait time in seconds
     *
     * @return Wait time
     */
    public static long getExplicitWait() {
        return Long.parseLong(getProperty("explicit.wait"));
    }

    /**
     * Gets page load timeout in seconds
     *
     * @return Timeout value
     */
    public static long getPageLoadTimeout() {
        return Long.parseLong(getProperty("page.load.timeout"));
    }

    /**
     * Gets browser type
     *
     * @return Browser name
     */
    public static String getBrowser() {
        return getProperty("browser");
    }

    /**
     * Gets test data file path
     *
     * @return File path
     */
    public static String getTestDataFilePath() {
        return getProperty("testdata.file.path");
    }

    /**
     * Gets reports directory path
     *
     * @return Directory path
     */
    public static String getReportsPath() {
        return getProperty("reports.path");
    }

    /**
     * Gets screenshots directory path
     *
     * @return Directory path
     */
    public static String getScreenshotsPath() {
        return getProperty("screenshots.path");
    }
}
