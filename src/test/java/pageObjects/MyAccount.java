package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class represents the "My Account" page and provides methods to interact
 * with the elements on the page. It extends the BasePage class to inherit common functionality.
 */
public class MyAccount extends BasePage {

    /**
     * Constructor to initialize the WebDriver for the "My Account" page.
     *
     * @param driver The WebDriver instance to interact with the web page.
     */
    public MyAccount(WebDriver driver) {
        super(driver);
    }

    // Web element for the page heading "My Account"
    @FindBy(xpath = "//h1[normalize-space()='My Account']")
    WebElement msgHeading;

    // Web element for the Logout link
    @FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
    WebElement lnkLogout;

    /**
     * Verifies if the "My Account" page is displayed by checking the visibility of the heading.
     *
     * @return true if the "My Account" heading is visible, false otherwise.
     */
    public boolean isMyAccountPageExists() {
        try {
            return (msgHeading.isDisplayed());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Clicks on the Logout link to log out of the account.
     * Uses JavaScriptExecutor to perform the click action.
     */
    public void clickLogout() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", lnkLogout);
    }
}
