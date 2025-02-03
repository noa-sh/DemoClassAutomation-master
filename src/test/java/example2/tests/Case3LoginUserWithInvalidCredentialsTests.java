package example2.tests;

import all.utils.GenerateDriverAll;
import example2.actions.Actions;
import example2.pages.SignUpSignInPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Case3LoginUserWithInvalidCredentialsTests {

    WebDriver driver;
    Actions actions;
    /**
     * * This method runs before all tests.
     * * It sets up the test environment, launches the browser, and navigates to the website.
     */
    @BeforeSuite(description = "Setting up test environment", alwaysRun = true)
    public void setUp() {
        driver = GenerateDriverAll.initDriver("chrome", "http://automationexercise.com");
        actions = new Actions(driver);
    }
    /**
     * * Test that verifies login with invalid credentials.
     * * It expects an appropriate error message after a failed login attempt.
     */
    @Test(description = "Testing login with invalid credentials", groups = {"regression", "login"})
    public void loginWithInvalidCredentialsTest() {
        boolean result = actions.loginWithInvalidCredentials();
        Assert.assertTrue(result, "Error message not displayed for invalid login.");
    }
    /**
     * * This method runs after all tests.
     * * It cleans up the test environment and closes the browser.
     */
    @Test(dataProvider = "excelData", dataProviderClass = TestDataProvider.class)
    public void testInvalidLogin(String email, String password) {
        System.out.println("Testing login with: " + email + " | " + password);
        SignUpSignInPage loginPage = new SignUpSignInPage(driver);
        loginPage.typeEmail(email);
        loginPage.typePassword(password);
        loginPage.clickSignIn();
        Assert.assertTrue(loginPage.validateErrorMessage());
    }
    /**
     * * This method runs after all tests.
     * * It cleans up the test environment and closes the browser.
     */
    @AfterSuite(description = "Cleaning up test environment", alwaysRun = true)
    public void tearDown() {
        GenerateDriverAll.cleanDriver(driver);
    }
}