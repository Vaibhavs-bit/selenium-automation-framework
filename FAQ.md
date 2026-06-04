# Frequently Asked Questions (FAQ)

## Installation & Setup

### Q1: How do I install the framework?
**A:** Follow these steps:
1. Clone the repository: `git clone [repo-url]`
2. Navigate to project: `cd selenium-automation-framework`
3. Install dependencies: `mvn clean install`
4. Update config.properties with your application URL

### Q2: What are the system requirements?
**A:** 
- Java 17 or higher
- Maven 3.6 or higher
- Any modern OS (Windows, Mac, Linux)
- 2GB RAM minimum
- Internet connection for dependency download

### Q3: I'm getting "Maven not found" error?
**A:** 
1. Install Maven from https://maven.apache.org/
2. Add Maven to system PATH
3. Verify: `mvn -version`

### Q4: WebDriver not downloading automatically?
**A:** 
1. Check internet connection
2. Clear Maven cache: `mvn clean`
3. Manually update pom.xml WebDriverManager version
4. Run: `mvn dependency:resolve`

## Configuration

### Q5: How do I change the base URL?
**A:** Edit `src/test/resources/config.properties`:
```properties
base.url=https://your-application-url.com
```

### Q6: Can I use different wait times for different tests?
**A:** Yes, use WaitUtility with custom timeout:
```java
WebElement element = WaitUtility.waitForElementWithCustomTimeout(driver, locator, 5);
```

### Q7: How do I add more properties to config.properties?
**A:** 
1. Add property: `my.property=value`
2. Access in code: `ConfigReader.getProperty("my.property")`
3. Or create getter: `public static String getMyProperty() { return getProperty("my.property"); }`

## Test Execution

### Q8: How do I run tests in parallel?
**A:** 
1. Use testng-parallel.xml: `mvn test -DsuiteXmlFile=src/test/resources/testng-parallel.xml`
2. Or set in testng.xml: `<suite parallel="tests" thread-count="4">`

### Q9: Can I run tests on multiple browsers simultaneously?
**A:** Yes, use testng-parallel.xml which runs tests on Chrome, Firefox, and Edge simultaneously.

### Q10: How do I run only failed tests?
**A:** TestNG automatically creates testng-failed.xml:
```bash
mvn test -DsuiteXmlFile=target/testng-failed.xml
```

### Q11: How do I skip specific tests?
**A:** 
1. Remove from testng.xml, OR
2. Use @Test(enabled = false), OR
3. Use: `mvn test -Dtest=!LoginTest`

## Reporting

### Q12: Where are the test reports?
**A:** Reports are in `test-results/reports/` directory. Open `ExtentReport_*.html` in browser.

### Q13: How do I view screenshots in the report?
**A:** Screenshots are automatically attached to failed tests in Extent Reports. Open the report and navigate to failed test.

### Q14: Can I customize the report name?
**A:** Yes, modify ExtentManager.java:
```java
String reportPath = REPORTS_PATH + "/MyCustomReport_" + timestamp + ".html";
```

### Q15: How do I attach additional information to reports?
**A:** In your test:
```java
extentTest.info("Custom information");
extentTest.log(Status.PASS, "Step description");
```

## Data & Test Cases

### Q16: How do I add test data?
**A:** 
1. Create Excel file in `src/test/resources/TestData.xlsx`
2. Create sheets with data
3. Use ExcelUtility to read data
4. Or use @DataProvider in TestDataProvider class

### Q17: Can I use JSON test data?
**A:** Yes, use JsonUtility class:
```java
Map<String, Object> data = JsonUtility.readJsonFileAsMap("path/to/file.json");
```

### Q18: How do I add more test cases?
**A:** 
1. Create new test class extending BaseTest
2. Add @Test methods
3. Add to testng.xml
4. Run tests

## Page Objects

### Q19: How do I add new page?
**A:** 
1. Create new class in `src/test/java/com/automation/pages/`
2. Name it `[PageName]Page.java`
3. Define locators and methods
4. Use WaitUtility for waits

### Q20: Should I create separate files for each page?
**A:** Yes, one file per page following POM pattern. This improves maintainability.

## Utilities

### Q21: How do I take manual screenshots?
**A:** Use ScreenshotUtility:
```java
String path = ScreenshotUtility.takeScreenshot(driver, "my-screenshot");
```

### Q22: How do I add custom logging?
**A:** Use Log4j2:
```java
logger.info("Information message");
logger.debug("Debug message");
logger.error("Error message", exception);
```

### Q23: Can I read files from resources folder?
**A:** Yes, use FileUtility:
```java
String content = FileUtility.readFileContent("src/test/resources/file.txt");
```

## Assertions

### Q24: Should I use Assert or SoftAssert?
**A:** 
- Use **Assert** for critical assertions (fail immediately)
- Use **SoftAssert** for multiple assertions (continue execution)

### Q25: How do I add custom assertion messages?
**A:** 
```java
Assert.assertTrue(condition, "Custom message when assertion fails");
```

## Troubleshooting

### Q26: Test runs but finds no elements?
**A:** 
1. Verify locator using browser dev tools
2. Check if element is inside iframe
3. Wait for element: Use WaitUtility
4. Check if page loaded fully
5. Add logging to debug

### Q27: Screenshot not capturing?
**A:** 
1. Verify screenshots directory exists
2. Check write permissions
3. Verify ScreenshotUtility is called
4. Check test listener configuration

### Q28: Tests timeout frequently?
**A:** 
1. Increase explicit.wait in config.properties
2. Check network/application performance
3. Use custom timeout for specific elements
4. Verify implicit waits not set

### Q29: WebDriver crashes during test?
**A:** 
1. Add --no-sandbox flag in ChromeOptions
2. Update WebDriver to latest version
3. Check system resources (RAM, CPU)
4. Clear browser cache

### Q30: Cannot read Excel test data?
**A:** 
1. Verify file path in config.properties
2. Check Excel file format (.xlsx)
3. Verify sheet name matches
4. Check cell values are accessible

## Performance

### Q31: How do I make tests run faster?
**A:** 
1. Use parallel execution
2. Reduce wait times for fast applications
3. Remove unnecessary logging
4. Optimize locators
5. Use headless mode if possible

### Q32: Can I run tests in headless mode?
**A:** Yes, add to ChromeOptions:
```java
chromeOptions.addArguments("--headless");
```

## Best Practices

### Q33: Should tests depend on each other?
**A:** No, each test should be independent and reusable.

### Q34: Can I hardcode data in tests?
**A:** No, use config.properties or @DataProvider for externalized data.

### Q35: Should I use Thread.sleep()?
**A:** No, use WaitUtility for explicit waits instead.

## Integration

### Q36: How do I integrate with Jenkins?
**A:** Create Jenkins pipeline that runs: `mvn clean test`

### Q37: Can I integrate with Slack notifications?
**A:** Yes, use Slack plugin in Jenkins to send test results.

### Q38: How do I generate reports in CI/CD?
**A:** Reports are auto-generated. Publish from `test-results/reports/` directory.

## Advanced

### Q39: How do I create custom listeners?
**A:** Implement ITestListener interface and add to testng.xml.

### Q40: Can I use annotations for custom tests?
**A:** Yes, create custom annotations and use them with ITestListener.

---

**Still have questions?** Create an issue on GitHub or check documentation files.
