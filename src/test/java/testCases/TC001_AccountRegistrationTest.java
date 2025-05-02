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

/**
 * This class contains a test case for verifying the user account registration functionality.
 * It extends the BaseClass to utilize common setup and teardown methods.
 */
public class TC001_AccountRegistrationTest extends BaseClass {

    /**
     * Test method to verify the user registration functionality.
     * This test navigates to the registration page, fills in the required details,
     * submits the form, and validates the confirmation message.
     */
    @Test(groups={"Regression","Master"})
    public void verify_account_registration() {

        log.info("***** Starting TC001_AccountRegistrationTest *****");

        try {
            // Navigate to the home page and click on My Account and Register
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount(); // Clicks on the "My Account" menu
            log.info("Clicked on MyAccount");

            hp.clickRegister(); // Clicks on the "Register" option
            log.info("Clicked on Register");

            // Initialize the AccountRegistrationPage object
            AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

            // Fill in customer registration details
            log.info("Providing customer details");
            regpage.setFirstName(randomeString().toUpperCase()); // Sets a random first name
            regpage.setLastName(randomeString().toUpperCase());  // Sets a random last name
            regpage.setEmail(randomeString() + "@gmail.com");    // Sets a random email address

            // Optionally set phone number or password confirmation (commented out)
            // regpage.setTelephone("845184514851");

            regpage.setPassword(randomeAlphanumeric()); // Sets a random alphanumeric password

            // regpage.setConfirmPassword("scdcd45c5");

            regpage.setPrivacyPolicy(); // Accepts the privacy policy
            regpage.clickContinue();    // Submits the registration form

            log.info("Validating expected confirmation message");

            // Get the confirmation message after registration
            String confmsg = regpage.getConfirmationMsg();

            // Assert based on the expected confirmation message
            if (confmsg.equals("Your Account Has Been Created!")) {
                Assert.assertTrue(true); // Test passes if the message matches
            } else {
                log.info("Test failed...");
                log.info("Debug logs");
                Assert.assertTrue(false); // Test fails if the message does not match
            }
        } catch (Exception e) {
            // If any exception occurs during test execution, mark the test as failed
            Assert.fail();
        }

        log.info("***** Finished TC001_AccountRegistrationTest *****");
    }
}
