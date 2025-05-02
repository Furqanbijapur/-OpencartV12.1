package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class represents the Account Registration Page and provides methods to interact
 * with the elements on the page. It extends the BasePage class to inherit common functionality.
 */
public class AccountRegistrationPage extends BasePage {

    /**
     * Constructor to initialize the WebDriver for the Account Registration Page.
     *
     * @param driver The WebDriver instance to interact with the web page.
     */
    public AccountRegistrationPage(WebDriver driver) {
        super(driver);
    }

    // Web elements on the Account Registration Page
    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement txtFirstname;

    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement txtLastname;

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtEmail;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txtPassword;

    @FindBy(xpath = "//input[@name='agree']")
    WebElement chkdPolicy;

    @FindBy(xpath = "//button[normalize-space()='Continue']")
    WebElement btnContinue;

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement msgConfirmation;

    /**
     * Sets the first name in the registration form.
     *
     * @param fname The first name to be entered.
     */
    public void setFirstName(String fname) {
        txtFirstname.sendKeys(fname);
    }

    /**
     * Sets the last name in the registration form.
     *
     * @param lname The last name to be entered.
     */
    public void setLastName(String lname) {
        txtLastname.sendKeys(lname);
    }

    /**
     * Sets the email address in the registration form.
     *
     * @param email The email address to be entered.
     */
    public void setEmail(String email) {
        txtEmail.sendKeys(email);
    }

    /**
     * Sets the password in the registration form.
     *
     * @param pwd The password to be entered.
     */
    public void setPassword(String pwd) {
        txtPassword.sendKeys(pwd);
    }

    /**
     * Accepts the privacy policy by clicking the checkbox.
     */
    public void setPrivacyPolicy() {
        Actions act = new Actions(driver);
        act.moveToElement(chkdPolicy).click().perform();
    }

    /**
     * Clicks the Continue button to submit the registration form.
     */
    public void clickContinue() {
        btnContinue.click();
    }

    /**
     * Retrieves the confirmation message displayed after successful registration.
     *
     * @return The confirmation message as a String.
     */
    public String getConfirmationMsg() {
        try {
            return (msgConfirmation.getText());
        } catch (Exception e) {
            return (e.getMessage());
        }
    }
