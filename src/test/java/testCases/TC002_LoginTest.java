package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginClass;
import pageObjects.MyAccount;
import testBase.BaseClass;

/**
 * Test case for verifying the login functionality. This test navigates to the
 * login page, performs login using credentials from the properties file, and
 * verifies if the login was successful by checking the presence of the "My
 * Account" page.
 */
public class TC002_LoginTest extends BaseClass {

	/**
	 * Verifies the login functionality.
	 * 
	 * Steps: 1. Navigate to the "My Account" section and click on the "Login"
	 * option. 2. Enter email and password from the properties file and click the
	 * login button. 3. Verify if the "My Account" page is displayed after login.
	 * 
	 * Assertions: - The test passes if the "My Account" page is displayed. - The
	 * test fails if any exception occurs or the "My Account" page is not found.
	 */
	@Test(groups = { "Sanity", "Master" })
	public void verify_login() {
		// Logging start of the test case
		log.info("****Starting of TC02***");

		try {
			// Initialize HomePage object and perform navigation to Login
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount(); // Click on "My Account" link/button
			hp.clickLogin(); // Click on "Login" option

			// Initialize LoginClass and perform login using credentials from property file
			LoginClass lp = new LoginClass(driver);
			log.info("Entering email and password");
			lp.setEmail(property.getProperty("email"));

			lp.setPasswordd(property.getProperty("password")); // Enter password
			lp.clickLogin(); // Click login button

			// Initialize MyAccount page object to verify successful login
			MyAccount macc = new MyAccount(driver);
			boolean targetpage = macc.isMyAccountPageExists(); // Check if My Account page is displayed

			// Assert login success
			Assert.assertTrue(targetpage); // Test passes if My Account page is found

		} catch (Exception e) {
			// Fail the test if any exception occurs during login
			Assert.fail();
		}

		// Logging end of the test case
		log.info("****Finished TC002****");
		
	}
}
