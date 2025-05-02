package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.BaseClass;

/**
 * This class represents the Login Page and provides methods to interact
 * with the elements on the page. It extends the BasePage class to inherit common functionality.
 */
public class LoginClass extends BasePage {

    /**
     * Constructor to initialize the WebDriver for the Login Page.
     *
     * @param driver The WebDriver instance to interact with the web page.
     */
    public LoginClass(WebDriver driver) {
        super(driver);
    }

    // Web elements on the Login Page
    @FindBy(xpath = "//input[@id='input-password']")
    WebElement password; // Password input field

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement mailAddres; // Email input field

    @FindBy(xpath = "//button[normalize-space()='Login']")
    WebElement btnLogin; // Login button

    /**
     * Sets the email address in the login form.
     *
     * @param email The email address to be entered.
     */
    public void setEmail(String email) {
        mailAddres.sendKeys(email);
    }

    /**
     * Sets the password in the login form.
     *
     * @param pwd The password to be entered.
     */
    public void setPasswordd(String pwd) {
        password.sendKeys(pwd);
    }

    /**
     * Clicks the Login button to submit the login form.
     */
    public void clickLogin() {
        btnLogin.click();
    }
}
