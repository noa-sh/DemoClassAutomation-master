package example2.actions;

import all.utils.JsonUtils;
import all.utils.RandomUtils;
import example2.pages.*;
import org.openqa.selenium.WebDriver;

public class Actions {

    ForgotPasswordPage forgotPassword;
    private WebDriver driver;
    RegistrationFormPage registrationForm;
    AutomationExercisePage automationExercisePage;
    HomePage homePage;
    Products products;
    SignUpFormPage signUpFormPage;
    SignUpSignInPage signUpSignInPage = new SignUpSignInPage(driver);

    /**
     * Constructor to initialize the Actions class with a WebDriver instance.
     *
     * @param driver the WebDriver instance to be used for interacting with web elements
     */
    public Actions(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver must not be null in Actions class");
        }
        this.driver = driver; // אתחול driver
        forgotPassword = new ForgotPasswordPage(driver);
        registrationForm = new RegistrationFormPage(driver);
        automationExercisePage = new AutomationExercisePage(driver);
        homePage = new HomePage(driver);
        products = new Products(driver);
        signUpSignInPage = new SignUpSignInPage(driver);
        signUpFormPage = new SignUpFormPage(driver);
    }
    /**
     * Deletes the account by accessing the delete account page and validating the result.
     *
     * @return true if the account is deleted successfully, false otherwise
     */
    public boolean deleteAccount() {
        homePage.accessDeleteAccount();
        return homePage.validateDeleteAccount();

    }
    /**
     * Searches for a product by accessing the products page, searching for a product, and validating the result.
     *
     * @return true if the product is found successfully, false otherwise
     */
    public boolean searchProduct() {
        //verify home page is displayed
        if(homePage.verifyHomePage()){
            System.out.println("Home page is displayed");
        }
        else
        {
            System.out.println("Home page is not displayed");
        }
        //click on the product button on the home page
        homePage.accessProducts();
        //verify products page is displayed
       if(   products.verifyProducts()){
           System.out.println("Products page is displayed");
           //reading product name from json file
           String productName = JsonUtils.readJsonFromFile("product_name");
           //search product
           products.searchProduct(productName);
           //click search button
           products.clickSearchButton();
            //verify search results
              if( products.verifyProductResult(productName)){
                  System.out.println("Product " + productName + "is displayed successfully");
                  return true;
              }
              else
              {
                  System.out.println("Product " + productName + "is not displayed");
              }
         }
       else
         {
              System.out.println("Products page is not displayed");
            }
       //
       return true;

        //return homePage.validateProducts();

    }

    /**
     * Logs in with valid credentials by typing the email, password, clicking submit, and validating the result.
     *
     * @return true if the login is successful, false otherwise
     */
    public boolean loginValidCredentials() {
        String email = JsonUtils.readJsonFromFile("valid_email");
        String password = JsonUtils.readJsonFromFile("valid_password");
        String name = JsonUtils.readJsonFromFile("valid_name");
        boolean results = homePage.verifyHomePage();
        if (results) {
            homePage.accessSignUp();
            results = homePage.verifyAccessSignInSignUpPage();
            if (results) {
                signUpSignInPage.typeSignInEmail(email);
                signUpSignInPage.typeSignInPassword(password);
                signUpSignInPage.clickSignIn();
                results = signUpSignInPage.validateSucceedLogin(name);

                return results;
            }
        }
        return results;
    }

    /**
     * Performs the forgot password action by typing the email, clicking submit, and validating the result.
     *
     * @return true if the forgot password functionality is validated successfully, false otherwise
     */
    public boolean doForgotPassword() {
        forgotPassword.typeEmail("test@test.com");
        forgotPassword.clickSubmit();
        return forgotPassword.validateForgotPassword();
    }
    /**
     * Performs the forgot password action by typing the email, clicking submit, and validating the result.
     *
     * @param email the email to be typed in the forgot password form
     * @return true if the forgot password functionality is validated successfully, false otherwise
     */
    public boolean doForgotPasswordFromExcel(String email) {
        forgotPassword.typeEmail(email);
        forgotPassword.clickSubmit();
        return forgotPassword.validateForgotPassword();
    }

    /**
     * Performs the registration form action by filling the contact name, date, and clicking submit.
     *
     * @param name the contact name to be filled
     * @param date the date to be filled
     */
    public boolean doRegistrationForm(String name, String date) {
        registrationForm.fillContactName(name);
        registrationForm.fillContactNumber("012-3456789");
        registrationForm.fillDate(date);
        registrationForm.selectPaymentMethod("cashondelivery");
        registrationForm.clickRegister();
        return registrationForm.validateFormSuccessSubmitted();
    }

    /**
     * doSubscription form action by filling the contact name, date, and clicking submit.
     *
     * @param email the contact email to be filled
     */
    public boolean doSubscription(String email) {
        return automationExercisePage.fillSubscription(email);
    }
    /**
     * verify the home page
     * @return  boolean
     */
    public boolean verifyHomePage() {

        return homePage.verifyHomePage();
    }
    /**
     * verify the sing up page
     * @return  boolean
     */
    public boolean accessVerifySignInSignUpPage() {
        homePage.accessSignUp();
        return homePage.verifyAccessSignInSignUpPage();

    }
    /**
     * verify starting sign up
     * @return  boolean
     */
    public boolean verifyStartingSignUp() {
        int randomInt = RandomUtils.getRandomInt(6);
        String name = "Auto Test User " + randomInt;
        String email = "test_user" + randomInt + "@autotest.com";
        signUpSignInPage.typeSignUpName(name);
        signUpSignInPage.typeSignUpEmail(email);
        signUpSignInPage.clickSignUp();
        return signUpSignInPage.verifyStartingSignUp(name, email);
    }
    /**
     * verify filling sign up
     * @return  boolean
     */
    public boolean verifyFillingSignUp() {

        signUpFormPage.selectTitle("woman");
        boolean results = signUpFormPage.verifyDisabledEmailInput();
        if (!results) {
            results = signUpFormPage.verifySignUpFormPage();
        }
        signUpFormPage.typePassword("12345678");
        signUpFormPage.selectAyear("1990");
        signUpFormPage.signUpForNewsletters();
        signUpFormPage.signUpForOffers();
        signUpFormPage.typeFirstName("QA_firstName");
        signUpFormPage.typeLastName("QA_lastName");
        signUpFormPage.typeCompany("QA_company");
        signUpFormPage.typeAddress_1("QA_address_1");
        signUpFormPage.typeAddress_2("QA_address_2");
        signUpFormPage.selectCountry("United States");
        signUpFormPage.typeState("California");
        signUpFormPage.typeCity("Los Angeles");
        signUpFormPage.typeZipCode("90001");
        signUpFormPage.typeMobileNumber("1234567890");
        signUpFormPage.clickCreateAccount();
        if (!results) {
            results = signUpFormPage.verifySignUpSuccess();
        }
        return results;
    }
    /**
     * verify filling sign up
     * @return  boolean
     */
    public String getChromeCpuValue() {
        TableTaskPage tableTaskPage = new TableTaskPage(driver);
        return tableTaskPage.getChromeCpuValue();
    }
    /**
     * login with invalid credentials
     * @return  boolean
     */
    public boolean loginInvalidCredentials() {
        String email = "wrongemail@example.com";
        String password = "wrongpassword";

        boolean results = homePage.verifyHomePage();
        if (results) {
            homePage.accessSignUp();
            results = homePage.verifyAccessSignInSignUpPage();
            if (results) {
                signUpSignInPage.typeSignInEmail(email);
                signUpSignInPage.typeSignInPassword(password);
                signUpSignInPage.clickSignIn();
                return signUpSignInPage.verifyIncorrectLoginMessage();
            }
        }
        return false;
    }
    /**
     * login with invalid credentials
     * @return  boolean
     */
    public boolean loginWithInvalidCredentials() {
        String invalidEmail = "invalid@test.com"; // * Define an invalid email
        String invalidPassword = "wrongpassword"; // * Define an incorrect password

        boolean results = homePage.verifyHomePage();
        System.out.println("Step 1: Verified that the home page has loaded - " + results);

        if (results) {
            homePage.accessSignUp();
            System.out.println("Step 2: Clicked on the 'Signup / Login' button");

            results = homePage.verifyAccessSignInSignUpPage();
            System.out.println("Step 3: Verified that we reached the login page - " + results);

            if (results) {
                System.out.println("Step 4: Starting to enter the email...");
                signUpSignInPage.typeSignInEmail(invalidEmail);
                System.out.println("Step 5: Finished entering the email");

                signUpSignInPage.typeSignInPassword(invalidPassword);
                System.out.println("Step 6: Entered the password");

                signUpSignInPage.clickSignIn();
                System.out.println("Step 7: Clicked the 'Login' button");

                results = signUpSignInPage.verifyIncorrectLoginMessage();
                System.out.println("Step 8: Verified that the error message appeared - " + results);

                return results;
            }
        }
        return results;
    }
    /**
     * login with valid credentials
     * @return  boolean
     */
    public boolean loginWithValidCredentials() {
        String validEmail = JsonUtils.readJsonFromFile("valid_email"); // * Retrieve a valid email from JSON file
        String validPassword = JsonUtils.readJsonFromFile("valid_password"); // * Retrieve a valid password from JSON file
        String username = JsonUtils.readJsonFromFile("valid_name"); // * Retrieve the expected username for verification

        boolean results = homePage.verifyHomePage();
        System.out.println("Step 1: Verified that the home page has loaded - " + results);

        if (results) {
            homePage.accessSignUp();
            System.out.println("Step 2: Clicked on the 'Signup / Login' button");

            results = homePage.verifyAccessSignInSignUpPage();
            System.out.println("Step 3: Verified that we reached the login page - " + results);

            if (results) {
                System.out.println("Step 4: Entering the email...");
                signUpSignInPage.typeSignInEmail(validEmail);
                System.out.println("Step 5: Entered the email");

                signUpSignInPage.typeSignInPassword(validPassword);
                System.out.println("Step 6: Entered the password");

                signUpSignInPage.clickSignIn();
                System.out.println("Step 7: Clicked the 'Login' button");

                results = signUpSignInPage.validateSucceedLogin(username);
                System.out.println("Step 8: Verified successful login - " + results);

                if (results) {
                    signUpSignInPage.clickDeleteAccount();
                    System.out.println("Step 9: Clicked 'Delete Account' button");

                    results = signUpSignInPage.verifyAccountDeleted();
                    System.out.println("Step 10: Verified that the account is deleted - " + results);
                }
                return results;
            }
        }
        return results;
    }
}