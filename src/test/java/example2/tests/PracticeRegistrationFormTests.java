package example2.tests;

import all.utils.GenerateDriverAll;
import all.utils.JsonUtils;
import example2.actions.Actions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Step;


public class PracticeRegistrationFormTests {

    WebDriver driver;
    Actions actions;

    String URL_FORM_VALIDATION;

    /**
     * Sets up the test environment by initializing the WebDriver and Actions.
     * Reads the URL and browser type from the JSON configuration file.
     */
    @BeforeSuite(description = "setting up the test environment", alwaysRun = true)
    public void setUp() {
        URL_FORM_VALIDATION = JsonUtils.readJsonFromFile("url_form_validation");
        String BROWSER = JsonUtils.readJsonFromFile("browser");
        driver = GenerateDriverAll.initDriver(BROWSER, URL_FORM_VALIDATION);
        actions = new Actions(driver);
    }


    @BeforeMethod(description = "executing before each test method")
    public void beforeMethod() {
        driver.navigate().to(URL_FORM_VALIDATION);
        driver.navigate().refresh();
    }
    /**
     * Tests the forgot password functionality.
     */

    @Description("This is a sample test: testing the registration form positive")
    @Step("testing the registration form positive")
    @Test(description = "testing the registration form positive", groups = {"smoke", "regression", "registration"})
    public void registrationFormPositiveTest() {
        assert actions.doRegistrationForm("John Doe", "21/12/2024");
    }


    /**
     * Tests the forgot password functionality.
     */
    @Description("This is a 2nd sample test: testing again the registration form positive")
    @Step("testing the registration form positive")
    @Test(description = "testing the registration form positive", groups = {"smoke", "regression", "registration"})
    public void registrationFormNegativeTest() {
        assert actions.doRegistrationForm("John Doe", "21/12/2024");
    }
    /**
     * Cleans up the test environment by quitting the WebDriver.
     */
    @AfterSuite(description = "cleaning up the test environment", alwaysRun = true)
    public void tearDown() {
        GenerateDriverAll.cleanDriver(driver);
    }
}