package com.automation.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ScreenshotUtility class handles screenshot capture functionality
 * Captures screenshots on test failure and for reporting purposes
 *
 * @author Automation Framework Team
 * @version 1.0
 */
public class ScreenshotUtility {

    private static final Logger logger = LogManager.getLogger(ScreenshotUtility.class);
    private static final String SCREENSHOT_PATH = ConfigReader.getScreenshotsPath();

    /**
     * Takes screenshot and saves it with timestamp
     *
     * @param driver WebDriver instance
     * @param screenshotName Name for the screenshot
     * @return Path to the screenshot file
     */
    public static String takeScreenshot(WebDriver driver, String screenshotName) {
        try {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
            
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String fileName = screenshotName + "_" + timestamp + ".png";
            String destinationPath = SCREENSHOT_PATH + File.separator + fileName;
            
            File destinationFile = new File(destinationPath);
            
            // Create directory if it doesn't exist
            if (!destinationFile.getParentFile().exists()) {
                destinationFile.getParentFile().mkdirs();
                logger.info("Screenshot directory created at: " + destinationFile.getParentFile().getAbsolutePath());
            }
            
            FileUtils.copyFile(sourceFile, destinationFile);
            logger.info("Screenshot captured successfully: " + destinationPath);
            return destinationPath;
            
        } catch (Exception e) {
            logger.error("Failed to capture screenshot: ", e);
            return null;
        }
    }

    /**
     * Takes screenshot on test failure
     *
     * @param driver WebDriver instance
     * @param testName Name of the test that failed
     * @return Path to the screenshot file
     */
    public static String captureScreenshotOnFailure(WebDriver driver, String testName) {
        String screenshotName = "FAILED_" + testName;
        return takeScreenshot(driver, screenshotName);
    }

    /**
     * Takes screenshot for reporting
     *
     * @param driver WebDriver instance
     * @param testName Name of the test
     * @return Path to the screenshot file
     */
    public static String captureScreenshotForReport(WebDriver driver, String testName) {
        String screenshotName = "REPORT_" + testName;
        return takeScreenshot(driver, screenshotName);
    }

    /**
     * Gets screenshot directory path
     *
     * @return Directory path
     */
    public static String getScreenshotDirectoryPath() {
        File directory = new File(SCREENSHOT_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        return SCREENSHOT_PATH;
    }

    /**
     * Converts screenshot path to relative path for reports
     *
     * @param absolutePath Absolute path of screenshot
     * @return Relative path for reports
     */
    public static String getRelativeScreenshotPath(String absolutePath) {
        return absolutePath.substring(absolutePath.indexOf("screenshots"));
    }
}
