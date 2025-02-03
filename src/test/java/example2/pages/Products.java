package example2.pages;

import all.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class Products extends BasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    /**
     * Constructor
     * @param driver
     */
    public Products(WebDriver driver) {
        super(driver, 10);
        //this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    /**
     * Verify the products page
     * @return
     */
    public boolean verifyProducts(){
        return validateElementExist(By.xpath("//h2[text()='All Products']"));
    }
    /**
     * Search the product
     * @param productName
     */
    public void searchProduct(String productName){
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_product")));
        nameInput.clear();
        nameInput.sendKeys(productName);
    }
    /**
     * Click the search button
     */
    public void clickSearchButton(){
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit_search")));
        searchButton.click();
    }

    /**
     * Verify the product result
     * @param productName product name
     * @return boolean
     */
    public boolean verifyProductResult(String productName){
        return validateElementExist(By.xpath("//p[text()='"+productName+"']"));
    }



}

