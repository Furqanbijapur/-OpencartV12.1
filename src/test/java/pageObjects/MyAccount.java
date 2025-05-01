package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccount extends BasePage {

	// Constructor to initialize WebDriver instance using the base class
	public MyAccount(WebDriver driver) {
		super(driver);
	}

	// Web element for the page heading "My Account"
	@FindBy(xpath = "//h1[normalize-space()='My Account']") 
	WebElement msgHeading;

	// Web element for the Logout link
	@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']") // Note: Possible typo in 'dic'
	WebElement lnkLogout;

	// Method to verify if the "My Account" page is displayed
	public boolean isMyAccountPageExists() {
		try {
			return (msgHeading.isDisplayed()); // Returns true if heading is visible
		} catch (Exception e) {
			return false; // If element not found or not visible, return false
		}
	}

	// Method to click on the Logout link
	public void clickLogout() {
		//lnkLogout.click();
		
		JavascriptExecutor js =(JavascriptExecutor) driver;
		
		js.executeScript("arguments[0].click();", lnkLogout);
	}
}
