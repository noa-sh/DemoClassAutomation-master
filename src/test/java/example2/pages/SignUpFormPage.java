package example2.pages;

import all.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class SignUpFormPage extends BasePage {


    public SignUpFormPage(WebDriver driver) {
        super(driver, 10);
    }

    public boolean verifySignUpFormPage(){
        return validateElementExist(By.xpath("//h2/b[text()='Enter Account Information']"));
    }

    public void selectTitle(String gender){
        if (gender.equalsIgnoreCase("man"))
            click(By.xpath("//input[@value='Mr']"));
       else
            click(By.xpath("//input[@value='Mrs']"));
    }

    public boolean verifyDisabledEmailInput(){
        return (validateElementExist(By.xpath("//input[@id=\"email\"][@disabled=\"disabled\"]")));
    }

    public void typePassword(String password){
        typeText(By.cssSelector("#password"), password);
    }

    public void selectAyear(String year){
        selectValueInDropdown(By.cssSelector("#years"), year);
    }
    public void signUpForNewsletters(){
        click(By.cssSelector("#newsletter"));
    }

    public void signUpForOffers(){
        click(By.cssSelector("#optin"));
    }

    public void typeFirstName(String firstName){
        typeText(By.cssSelector("#first_name"), firstName);
    }

    public void typeLastName(String lastName){
        typeText(By.cssSelector("#last_name"), lastName);
    }

    public void typeCompany(String company){
        typeText(By.cssSelector("#company"), company);
    }

    public void typeAddress_1(String address_1){
        typeText(By.cssSelector("#address1"), address_1);
    }

    public void typeAddress_2(String address_2){
        typeText(By.cssSelector("#address2"), address_2);
    }

    public void selectCountry(String country){
        selectValueInDropdown(By.cssSelector("#country"), country);
    }

    public void typeState(String state){
        typeText(By.cssSelector("#state"), state);
    }

    public void typeCity(String city){
        typeText(By.cssSelector("#city"), city);
    }

    public void typeZipCode(String zipCode){
        typeText(By.cssSelector("#zipcode"), zipCode);
    }

    public void typeMobileNumber(String mobileNumber){
        typeText(By.cssSelector("#mobile_number"), mobileNumber);
    }

    public void clickCreateAccount(){
        click(By.xpath("//button[@data-qa=\"create-account\"]"));
    }

    public boolean verifySignUpSuccess(){
        return validateElementExist(By.xpath("//h2/b[text()='Account Created!']"));
    }
}

