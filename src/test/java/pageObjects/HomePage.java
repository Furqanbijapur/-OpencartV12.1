package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object class for the Home Page.
 * This class contains web elements and methods to interact with the Home Page.
 */
public class HomePage extends BasePage {

    /**
     * Constructor to initialize the WebDriver for the Home Page.
     *
     * @param driver WebDriver instance passed from the test class.
     */
    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Web element for the "My Account" link/button
    @FindBy(xpath = "//span[normalize-space()='My Account']")
    WebElement linkaccount;

    // Web element for the "Register" link
    @FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement linkRegister;

    // Web element for the "Login" link
    @FindBy(xpath = "//a[normalize-space()='Login']")
    WebElement login;

    /**
     * Clicks on the "My Account" link/button.
     */
    public void clickMyAccount() {
        linkaccount.click();
    }

    /**
     * Clicks on the "Register" link.
     */
    public void clickRegister() {
        linkRegister.click();
    }

    /**
     * Clicks on the "Login" link.
     */
    public void clickLogin() {
        login.click();
    }
}
