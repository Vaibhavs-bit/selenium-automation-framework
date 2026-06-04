package com.automation.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.Duration;

/**
 * WaitUtility class provides explicit wait methods for various conditions
 * Eliminates hardcoded Thread.sleep() and improves test reliability
 *
 * @author Automation Framework Team
 * @version 1.0
 */
public class WaitUtility {

    private static final Logger logger = LogManager.getLogger(WaitUtility.class);
    private static final long DEFAULT_TIMEOUT = ConfigReader.getExplicitWait();

    /**
     * Waits for element to be present in DOM
     *
     * @param driver WebDriver instance
     * @param locator Element locator
     * @return WebElement
     */
    public static WebElement waitForPresenceOfElement(WebDriver driver, By locator) {
        logger.info("Waiting for element to be present: " + locator);
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            logger.error("Element presence wait failed for locator: " + locator, e);
            throw e;
        }
    }

    /**
     * Waits for element to be visible
     *
     * @param driver WebDriver instance
     * @param locator Element locator
     * @return WebElement
     */
    public static WebElement waitForElementToBeVisible(WebDriver driver, By locator) {
        logger.info("Waiting for element to be visible: " + locator);
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            logger.error("Element visibility wait failed for locator: " + locator, e);
            throw e;
        }
    }

    /**
     * Waits for element to be clickable
     *
     * @param driver WebDriver instance
     * @param locator Element locator
     * @return WebElement
     */
    public static WebElement waitForElementToBeClickable(WebDriver driver, By locator) {
        logger.info("Waiting for element to be clickable: " + locator);
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            logger.error("Element clickable wait failed for locator: " + locator, e);
            throw e;
        }
    }

    /**
     * Waits for element to be invisible
     *
     * @param driver WebDriver instance
     * @param locator Element locator
     */
    public static void waitForElementToBeInvisible(WebDriver driver, By locator) {
        logger.info("Waiting for element to be invisible: " + locator);
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            logger.error("Element invisibility wait failed for locator: " + locator, e);
            throw e;
        }
    }

    /**
     * Waits for element text to be present
     *
     * @param driver WebDriver instance
     * @param locator Element locator
     * @param text Expected text
     * @return true if text is present
     */
    public static boolean waitForTextToBePresentInElement(WebDriver driver, By locator, String text) {
        logger.info("Waiting for text '" + text + "' to be present in element: " + locator);
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
            return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
        } catch (Exception e) {
            logger.error("Text presence wait failed for locator: " + locator + ", text: " + text, e);
            throw e;
        }
    }

    /**
     * Waits for element attribute to have specific value
     *
     * @param driver WebDriver instance
     * @param locator Element locator
     * @param attribute Attribute name
     * @param value Expected attribute value
     * @return true if attribute has the value
     */
    public static boolean waitForElementAttributeToHaveValue(WebDriver driver, By locator, String attribute, String value) {
        logger.info("Waiting for attribute '" + attribute + "' to have value '" + value + "' for element: " + locator);
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
            return wait.until(ExpectedConditions.attributeToBe(locator, attribute, value));
        } catch (Exception e) {
            logger.error("Attribute value wait failed for locator: " + locator, e);
            throw e;
        }
    }

    /**
     * Waits for number of elements to be present
     *
     * @param driver WebDriver instance
     * @param locator Element locator
     * @param numberOfElements Expected number of elements
     * @return List of WebElements
     */
    public static java.util.List<WebElement> waitForPresenceOfAllElements(WebDriver driver, By locator, int numberOfElements) {
        logger.info("Waiting for " + numberOfElements + " elements to be present: " + locator);
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, numberOfElements - 1));
            return driver.findElements(locator);
        } catch (Exception e) {
            logger.error("Multiple elements presence wait failed for locator: " + locator, e);
            throw e;
        }
    }

    /**
     * Waits for page title to contain specific text
     *
     * @param driver WebDriver instance
     * @param titleText Expected title text
     * @return true if title contains text
     */
    public static boolean waitForPageTitleToContain(WebDriver driver, String titleText) {
        logger.info("Waiting for page title to contain: " + titleText);
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
            return wait.until(ExpectedConditions.titleContains(titleText));
        } catch (Exception e) {
            logger.error("Page title wait failed for text: " + titleText, e);
            throw e;
        }
    }

    /**
     * Waits for page URL to contain specific text
     *
     * @param driver WebDriver instance
     * @param urlText Expected URL text
     * @return true if URL contains text
     */
    public static boolean waitForPageUrlToContain(WebDriver driver, String urlText) {
        logger.info("Waiting for page URL to contain: " + urlText);
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
            return wait.until(ExpectedConditions.urlContains(urlText));
        } catch (Exception e) {
            logger.error("Page URL wait failed for text: " + urlText, e);
            throw e;
        }
    }

    /**
     * Waits for element with custom timeout
     *
     * @param driver WebDriver instance
     * @param locator Element locator
     * @param timeout Timeout in seconds
     * @return WebElement
     */
    public static WebElement waitForElementWithCustomTimeout(WebDriver driver, By locator, long timeout) {
        logger.info("Waiting for element with custom timeout " + timeout + " seconds: " + locator);
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            logger.error("Element wait with custom timeout failed for locator: " + locator, e);
            throw e;
        }
    }
}
