package com.automation.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * BaseTest class provides common setup and teardown methods for all test classes.
 * Uses ThreadLocal to manage WebDriver instances safely in parallel execution.
 *
 * @author Automation Framework Team
 * @version 1.0
 */
public class BaseTest {

    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    /**
     * Initializes WebDriver based on browser parameter from Maven/TestNG
     * Supports Chrome, Firefox, and Edge browsers
     * Runs before each test method
     */
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        String browser = getBrowser();
        logger.info("===== Test Setup Started =====");
        logger.info("Launching browser: " + browser);
        
        WebDriver driver = initializeDriver(browser);
        driverThreadLocal.set(driver);
        
        logger.info("WebDriver initialized successfully for browser: " + browser);
        logger.info("===== Test Setup Completed =====");
    }

    /**
     * Initializes WebDriver based on browser type
     *
     * @param browser Browser name (chrome, firefox, edge)
     * @return WebDriver instance
     */
    private WebDriver initializeDriver(String browser) {
        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
                driver = new FirefoxDriver(firefoxOptions);
                logger.info("Firefox driver initialized");
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                driver = new EdgeDriver(edgeOptions);
                logger.info("Edge driver initialized");
                break;

            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                driver = new ChromeDriver(chromeOptions);
                logger.info("Chrome driver initialized");
                break;
        }

        driver.manage().window().maximize();
        return driver;
    }

    /**
     * Gets browser name from system property or defaults to Chrome
     *
     * @return Browser name
     */
    public String getBrowser() {
        String browser = System.getProperty("browser");
        if (browser == null || browser.isEmpty()) {
            browser = "chrome";
            logger.warn("Browser not specified, using default: Chrome");
        }
        return browser;
    }

    /**
     * Gets WebDriver instance from ThreadLocal
     *
     * @return WebDriver instance
     */
    public WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    /**
     * Closes browser and cleans up resources
     * Runs after each test method
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        logger.info("===== Test Teardown Started =====");
        WebDriver driver = driverThreadLocal.get();

        if (driver != null) {
            try {
                driver.quit();
                logger.info("WebDriver closed successfully");
            } catch (Exception e) {
                logger.error("Error occurred while closing WebDriver: ", e);
            } finally {
                driverThreadLocal.remove();
                logger.info("ThreadLocal cleaned up");
            }
        }
        logger.info("===== Test Teardown Completed =====");
    }
}