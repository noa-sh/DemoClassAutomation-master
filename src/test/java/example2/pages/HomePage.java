package example2.pages;

import all.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage extends BasePage {


    public HomePage(WebDriver driver) {
        super(driver, 10);
    }

    /**
     * Verify the home page
     * @return  boolean
     */
    public boolean verifyHomePage(){
        return validateElementExist(By.xpath("//a[@href='/'][contains(text(),'Home')][@style='color: orange;']"));
    }
    /**
     * Access the login page
     */
    public void accessSignUp(){
        click(By.xpath("//a[contains(text(), ' Signup / Login')]"));
    }

    /**
     * Access the delete account page
     */
    public void accessDeleteAccount(){
        click(By.xpath("//a[contains(text(), ' Delete Account')]"));
    }
    /**
     * Validate the delete account
     */
    public boolean validateDeleteAccount(){
        return   validateElementExist(By.xpath("//b[text()='Account Deleted!']"));
    }
    /**
     * validate the access sign in and sign up page
     */
    public boolean verifyAccessSignInSignUpPage(){
        boolean bSignIn = validateElementExist(By.xpath("//h2[text()='Login to your account']"));
        boolean bSignUp = validateElementExist(By.xpath("//h2[text()='New User Signup!']"));
        return bSignIn && bSignUp;
    }
    /**
     * Access the products page
     */
    public void accessProducts(){
        click(By.xpath("//a[contains(text(), ' Products')]"));
    }


}

