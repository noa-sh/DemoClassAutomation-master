package example2.tests;

import all.utils.GenerateDriverAll;
import all.utils.JsonUtils;
import example2.actions.Actions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ForgotPasswordExcelTests {

    private static final Logger logger = LogManager.getLogger(ForgotPasswordExcelTests.class);
    WebDriver driver;
    Actions actions;

    String URL = JsonUtils.readJsonFromFile("url");
    String BROWSER = JsonUtils.readJsonFromFile("browser");

    /**
     * Sets up the test environment by initializing the WebDriver and Actions.
     * Reads the URL and browser type from the JSON configuration file.
     */
    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        driver = GenerateDriverAll.initDriver(BROWSER, URL);
        actions = new Actions(driver);
        logger.debug("WebDriver setup complete: {}, {}", BROWSER, URL);
    }

    @BeforeMethod
    public void navigateToForgotPasswordPage() {
        driver.navigate().to(URL);
    }

    /**
     * Tests the forgot password functionality.
     */
    @Test(dataProvider = "excelData", dataProviderClass = TestDataProvider.class, description = "testing the forgot password positive functionality, using excel data", groups = {"smoke",  "forgotPassword"})
    public void forgotPassword(String email) {
        assert actions.doForgotPasswordFromExcel(email);
    }

    /**
     * Cleans up the test environment by quitting the WebDriver.
     */
    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        GenerateDriverAll.cleanDriver(driver);
    }
}