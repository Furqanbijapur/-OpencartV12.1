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

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txtFirstname;

	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txtLastname;

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;

	/*
	 * @FindBy(xpath = "//input[@id='input-telephone']") WebElement txtTelephone;
	 */
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;

	/*
	 * @FindBy(xpath = "//input[@id='input-confirm']") WebElement
	 * txtConfirmPassword;
	 */

	@FindBy(xpath="//input[@name='agree']")
	WebElement chkdPolicy;

	@FindBy(xpath = "//button[normalize-space()='Continue']")
	WebElement btnContinue;

	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;

	public void setFirstName(String fname) {
		txtFirstname.sendKeys(fname);
	}

	public void setLastName(String lname) {
		txtLastname.sendKeys(lname);
	}

	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}

	/*
	 * public void setTelephone(String tel) { txtTelephone.sendKeys(tel); }
	 */
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}

	/*
	 * public void setConfirmPassword(String pwd) {
	 * txtConfirmPassword.sendKeys(pwd); }
	 */
	public void setPrivacyPolicy() {
		//chkdPolicy.click();
		 Actions act=new Actions(driver);
		 act.moveToElement(chkdPolicy).click().perform();
		
	}

	public void clickContinue() {
		// sol1
		btnContinue.click();

		// sol2
		/*
		 * Actions act=new Actions(driver);
		 * act.moveToElement(btnContinue).click().build().perform();
		 */

		// sol3
		/*
		 * JavascriptExecutor js= (JavascriptExecutor)driver;
		 * js.executeScript("arguments[0].click();", btnContinue);
		 */

		// sol4
//		btnContinue.sendKeys(Keys.ENTER);

		// sol5
		/*
		 * WebDriverWait myait=new WebDriverWait(driver,Duration.ofSeconds(5));
		 * myait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
		 */

		// sol6
//		btnContinue.submit();

	}

	public String getConfirmationMsg() {
		try {
			return (msgConfirmation.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
	}

}
