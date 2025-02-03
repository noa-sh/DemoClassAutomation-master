package example2.tests;

import all.utils.GenerateDriverAll;
import all.utils.JsonUtils;
import example2.actions.Actions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class VerifyTableTaskTests {

    private static final Logger logger = LogManager.getLogger(VerifyTableTaskTests.class);
    WebDriver driver;
    Actions actions;

    /**
     * אתחול הסביבה, יצירת WebDriver ואובייקט Actions
     */
    @BeforeSuite(description = "Setting up the test environment", alwaysRun = true)
    public void setUp() {
        String URL = JsonUtils.readJsonFromFile("url");
        String BROWSER = JsonUtils.readJsonFromFile("browser");
        driver = GenerateDriverAll.initDriver(BROWSER, URL);
        actions = new Actions(driver);
        logger.info("WebDriver initialized successfully.");
    }

    /**
     * בדיקה: אימות ערך ה- CPU בטבלת Chrome
     */
    @Test(description = "Verify Chrome CPU value in Table Task Page", groups = {"smoke", "tableTask"})
    public void verifyChromeCpuValue() {
        String cpuValue = actions.getChromeCpuValue();
        logger.info("Fetched CPU value from table: {}", cpuValue);
        Assert.assertEquals(cpuValue, "TEST", "Chrome CPU value does not match expected result!");
    }

    /**
     * ניקוי הסביבה וסגירת הדפדפן
     */
    @AfterSuite(description = "Cleaning up the test environment", alwaysRun = true)
    public void tearDown() {
        GenerateDriverAll.cleanDriver(driver);
        logger.info("Test execution completed.");
    }
}