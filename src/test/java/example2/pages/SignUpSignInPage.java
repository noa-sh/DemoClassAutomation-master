package example2.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SignUpSignInPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By deleteAccountButtonLocator = By.xpath("//a[contains(text(),'Delete Account')]");


    // Constructor
    public SignUpSignInPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Type sign-up name
    public void typeSignUpName(String name) {
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signup-name")));
        nameInput.clear();
        nameInput.sendKeys(name);
    }

    // Type sign-up email
    public void typeSignUpEmail(String email) {
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signup-email")));
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    // Click sign-up button
    public void clickSignUp() {
        WebElement signUpButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("signup-button")));
        signUpButton.click();
    }

    // Type sign-in email
    public void typeSignInEmail(String email) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@data-qa='login-email']")
        ));
        emailField.sendKeys(email);
    }

    // Type sign-in password
    public void typeSignInPassword(String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@data-qa='login-password']")
        ));
        passwordField.sendKeys(password);
    }

    // Click sign-in button
    public void clickSignIn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@data-qa='login-button']")
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginButton);
    }


    // Validate successful login
    public boolean validateSucceedLogin(String name) {
        try {
            System.out.println("Waiting for login confirmation message...");
            WebElement loggedInText = new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Logged in as')]")
                    ));

            System.out.println("Logged in successfully: " + loggedInText.getText());
            return loggedInText.isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("Login confirmation message was not found.");
            return false;
        }
    }

    // Verify starting sign-up
    public boolean verifyStartingSignUp(String name, String email) {
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signup-name")));
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signup-email")));

        return nameField.getAttribute("value").equals(name) && emailField.getAttribute("value").equals(email);
    }
    public boolean verifyIncorrectLoginMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[contains(text(),'Your email or password is incorrect!')]")
        )).isDisplayed();
    }

    public boolean isDeleteAccountButtonVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(deleteAccountButtonLocator)).click();
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Delete Account')]"))).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
    public void clickDeleteAccount() {
        try {
            WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(text(),'Delete Account')]")
            ));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", deleteButton);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteButton);

            System.out.println("Clicked 'Delete Account' button successfully.");
        } catch (TimeoutException e) {
            System.out.println("Error: Could not find 'Delete Account' button.");
        }
    }
    public boolean verifyAccountDeleted() {
        try {
            WebElement deleteMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h2[contains(text(),'ACCOUNT DELETED!')]")
            ));
            System.out.println("Account deletion message found: " + deleteMessage.getText());
            return deleteMessage.isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("Error: Account deletion confirmation message not found.");
            return false;
        }
    }
    public void typeEmail(String email) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email"))); // עדכון ה-ID לשם הנכון
        emailField.clear();
        emailField.sendKeys(email);

    }

    public void typePassword(String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password"))); // עדכון ה-ID לשם הנכון
        passwordField.clear();
        passwordField.sendKeys(password);
    }
    public boolean validateErrorMessage() {
        try {
            WebElement errorMessage = driver.findElement(By.id("error-message"));
            return errorMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}