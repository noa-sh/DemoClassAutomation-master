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

public class ProjectRegisterUserTests {

    private static final Logger logger = LogManager.getLogger(ProjectRegisterUserTests.class);
    WebDriver driver;
    Actions actions;


    /**
     * Sets up the test environment by initializing the WebDriver and Actions.
     * Reads the URL and browser type from the JSON configuration file.
     */
    @BeforeSuite(description = "setting up the test environment", alwaysRun = true)
    public void setUp() {
        String URL_FOR_SUBSCRIPTION = JsonUtils.readJsonFromFile("url_for_subscription");
        String BROWSER = JsonUtils.readJsonFromFile("browser");
        logger.info("WebDriver setup complete: {}, {}", BROWSER, URL_FOR_SUBSCRIPTION);
        driver = GenerateDriverAll.initDriver(BROWSER, URL_FOR_SUBSCRIPTION);
        actions = new Actions(driver);
    }


    /**
     * Testing Verify that home page is visible successfully.
     */
    @Test(priority = 1, description = "testing the Home page", groups = {"smoke", "registration_User"})
    public void verifyHomePage() {
        boolean result = actions.verifyHomePage();
        if (result) {
            logger.info("Home page is visible, test passed.");
        } else {
            logger.error("Home page is not visible, test failed.");
        }
        Assert.assertTrue(result, "The Home page is not visible.");
    }

    @Test(priority = 2, description = "testing Accessed the sign in page", groups = {"smoke", "registration_User"})
    public void verifySignInSignUp() {
        boolean result = actions.accessVerifySignInSignUpPage();
        if (result) {
            logger.info("Accessed the sign in page, test passed.");
        } else {
            logger.error("sign in page is not visible, test failed.");
        }
        Assert.assertTrue(result, "sign in page is not visible, test failed.");
    }

    @Test(priority = 3, description = "Verify that 'ENTER ACCOUNT INFORMATION' is visible", groups = {"smoke", "registration_User"})
    public void verifyStartingSignUp() {
        boolean result = actions.verifyStartingSignUp();
        if (result) {
            logger.info("Accessed the sign in page, test passed.");
        } else {
            logger.error("sign in page is not visible, test failed.");
        }
        Assert.assertTrue(result, "sign in page is not visible, test failed.");
    }


    @Test(priority = 4, description = "Verify positive signUp form", groups = {"smoke", "registration_User"})
    public void verifyFillSignUpForm() {
        boolean result = actions.verifyFillingSignUp();
        if (result) {
            logger.info("sign up form is approved, test passed.");
        } else {
            logger.error("sign up form is not approved, test failed.");
        }
        Assert.assertTrue(result, "sign up form could not be approved, test failed.");
    }




    /**
     * Cleans up the test environment by quitting the WebDriver.
     */
    @AfterSuite(description = "cleaning up the test environment", alwaysRun = true)
    public void tearDown() {
        GenerateDriverAll.cleanDriver(driver);
        logger.info("Registration form positive test ended.");
    }
}