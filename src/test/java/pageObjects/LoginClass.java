package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.BaseClass;

public class LoginClass extends BasePage {

	public LoginClass(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement password;

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement mailAddres;

	@FindBy(xpath = "//button[normalize-space()='Login']")
	WebElement btnLogin;

	public void setEmail(String email) {
		mailAddres.sendKeys(email);
	}

	public void setPasswordd(String pwd) {
		password.sendKeys(pwd);
	}

	public void clickLogin() {
		btnLogin.click();
	}
}
