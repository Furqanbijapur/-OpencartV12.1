package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

    // Test method to verify user registration functionality
    @Test(groups={"Regression","Master"})
    public void verify_account_registration() {

        log.info("***** Starting TC001_AccountRegistrationTest *****");

        try {
            // Navigate to the home page and click on My Account and Register
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            log.info("Clicked on MyAccount");

            hp.clickRegister();
            log.info("Clicked on Register");

            // Initialize registration page object
            AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

            // Fill in customer registration details
            log.info("Providing customer details");
            regpage.setFirstName(randomeString().toUpperCase());
            regpage.setLastName(randomeString().toUpperCase());
            regpage.setEmail(randomeString() + "@gmail.com");

            // Optionally set phone number or password confirmation
            // regpage.setTelephone("845184514851");

            regpage.setPassword(randomeAlphanumeric());

            // regpage.setConfirmPassword("scdcd45c5");

            regpage.setPrivacyPolicy(); // Accept privacy policy
            regpage.clickContinue();    // Submit the form

            log.info("Validating expected confirmation message");

            // Get the confirmation message after registration
            String confmsg = regpage.getConfirmationMsg();

            // Assert based on expected confirmation message
            if (confmsg.equals("Your Account Has Been Created!")) {
                Assert.assertTrue(true);
            } else {
                log.info("Test failed...");
                log.info("Debug logs");
                Assert.assertTrue(false);
            }
        } catch (Exception e) {
            // If any exception occurs during test execution, mark test as failed
            Assert.fail();
        }

        log.info("***** Finished TC001_AccountRegistrationTest *****");
    }
}
