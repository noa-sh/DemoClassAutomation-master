package all.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    private static final Logger logger = LogManager.getLogger(BasePage.class);
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;


    /**
     * Constructor to initialize the WebDriver and WebDriverWait.
     *
     * @param driver  the WebDriver instance
     * @param timeout the timeout duration in seconds
     */
    public BasePage(WebDriver driver, int timeout) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver must not be null in BasePage");
        }
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        actions = new Actions(driver);
    }

    /**
     * Clicks on the element located by the given locator.
     *
     * @param locator the By locator of the element to be clicked
     */
    public void click(By locator) {
        logger.debug("Going to click WebElement: {}", locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        WebElement element = driver.findElement(locator);
        element.click();
    }

    /**
     * Types the given text into the element located by the given locator.
     *
     * @param locator the By locator of the element
     * @param text    the text to be typed
     */
    public void typeText(By locator, String text) {
        logger.debug("Going to send keys to WebElement: {} {}", locator, text);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Validates if elements exist for the given locator.
     *
     * @param locator the By locator of the elements
     * @return the according elements size
     */
    public boolean validateElementExist(By locator) {
        // Wait for the page to load completely
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        // Find all elements matching the locator
        List<WebElement> elements = driver.findElements(locator);
        if (elements.isEmpty()) {
            logger.error("No elements found for locator: {}", locator);
        } else {
            logger.debug("Elements found for locator: {}", locator);
        }
        return !elements.isEmpty();
    }

    /**
     * Checks if the current tab title matches the given title.
     *
     * @param title the expected title of the tab
     * @return true if the tab title matches, false otherwise
     */
    public boolean isTabTitleMatch(String title) {
        return driver.getTitle().equals(title);
    }


    /**
     * Retrieves the text content of the element located by the given locator.
     *
     * @param locator the By locator of the element
     * @return the text content of the element
     */
    public String getElementText(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    public String getElementAttributeUsingJS(By locator, String attribute) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].getAttribute(arguments[1]);", element, attribute);
    }


    /**
     * Double-clicks on the element located by the given locator.
     *
     * @param locator the By locator of the element to be double-clicked
     */
    public void doubleClickElement(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        WebElement element = driver.findElement(locator);
        actions.doubleClick(element).perform();
    }


    public void scrollToElement(By locator) {
        // Find the element using the provided locator
        WebElement element = driver.findElement(locator);

        // Scroll the element into view using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }


    /**
     * Selects the given value in the select element located by the given locator.
     *
     * @param locator the By locator of the select element
     * @param value the value to be selected
     */
    public void selectValueInDropdown(By locator, String value) {
        WebElement selectElement = driver.findElement(locator);
        Select select = new Select(selectElement);
        select.selectByValue(value);
    }

    public boolean isTextDisplayedInAnyElement(By locator, String text) {
        List<WebElement> elements = driver.findElements(locator);
        for (WebElement element : elements) {
            if (element.getText().contains(text)) {
                return true;
            }
        }
        return false;
    }
}