package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginClass;
import pageObjects.MyAccount;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups="datadriven")
	public void verify_logiDDt(String email, String pwd, String exp) {

		log.info("****Starting of TC003*****");
		try {
			// Navigate to HomePage and click on My Account and Login
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount(); // Click on "My Account" link/button
			hp.clickLogin(); // Click on "Login" option

			// Perform login using provided email and password
			LoginClass lp = new LoginClass(driver);
			lp.setEmail(email); // Enter email
			lp.setPasswordd(pwd); // Enter password
			lp.clickLogin(); // Click login button

			// Validate login by checking if My Account page is displayed
			MyAccount macc = new MyAccount(driver);
			boolean targetpage = macc.isMyAccountPageExists(); // Check if My Account page is displayed

			// Validation logic based on expected result
			if (exp.equalsIgnoreCase("Valid")) {
				if (targetpage = true) { // If login is successful
					macc.clickLogout(); // Logout if logged in
					Assert.assertTrue(true); // Assert pass
				} else {
					Assert.assertTrue(false); // Assert fail
				}
			}

			if (exp.equalsIgnoreCase("Invalid")) {
				if (targetpage = true) { // If login is successful unexpectedly
					macc.clickLogout(); // Logout
					Assert.assertTrue(false); // Assert fail
				} else {
					Assert.assertTrue(true); // Assert pass
				}
			}
		} catch (Exception e) {
			Assert.fail(); // Fail the test in case of exception
		} finally {
			log.info("****End of TC003*****"); // Log end of test
		}
	}
}
