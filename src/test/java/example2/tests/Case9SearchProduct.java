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

public class Case9SearchProduct {

    private static final Logger logger = LogManager.getLogger(Case9SearchProduct.class);
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
        logger.debug("WebDriver setup complete: {}, {}", BROWSER, URL_FOR_SUBSCRIPTION);
        driver = GenerateDriverAll.initDriver(BROWSER, URL_FOR_SUBSCRIPTION);
        actions = new Actions(driver);
    }

    /**
     * Testing the login positive scenario. first
     */

    @Test(description = "testing the product search", groups = {"smoke", "regression", "search"})
    public void searchProductTest() {

        //start the search product test
        boolean result = actions.searchProduct();
        if (result) {
            logger.info("Search product test passed.");
        } else {
            logger.error("Search product test failed.");
        }
        Assert.assertTrue(result, "Search product test failed.");
    }


    /**
     * Cleans up the test environment by quitting the WebDriver.
     */
    @AfterSuite(description = "cleaning up the test environment", alwaysRun = true)
    public void tearDown() {
        GenerateDriverAll.cleanDriver(driver);
        logger.debug("Registration form positive test ended.");
    }
}