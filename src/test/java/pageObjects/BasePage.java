package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * This class serves as a base page for all page object classes.
 * It initializes the WebDriver instance and the web elements on the page.
 */
public class BasePage {

    // Static WebDriver instance to be shared across all page objects
    static WebDriver driver;

    /**
     * Constructor to initialize the WebDriver and web elements for the page.
     *
     * @param driver The WebDriver instance to interact with the web page.
     */
    public BasePage(WebDriver driver) {
        this.driver = driver; // Assign the WebDriver instance
        PageFactory.initElements(driver, this); // Initialize web elements using PageFactory
    }
}
