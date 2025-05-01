package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

/**
 * BaseClass is a utility class that provides common setup and teardown methods
 * for Selenium WebDriver tests. It also includes utility methods for generating
 * random strings and capturing screenshots.
 */
public class BaseClass {

	static public WebDriver driver; // WebDriver instance for browser automation
	public Logger log; // Logger instance for logging test information
	public Properties property; // Properties object for reading configuration files

	/**
	 * Sets up the WebDriver and initializes the browser based on the provided
	 * parameters. Loads the application URL from the configuration file.
	 *
	 * @param os The operating system on which the tests are running.
	 * @param br The browser to be used for the tests (e.g., chrome, firefox, edge).
	 * @throws IOException If an error occurs while reading the configuration file.
	 */
	@BeforeClass(groups = { "Regression", "Master", "datadriven" })
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws IOException {
		FileReader file = new FileReader("./src//test//resources//config.properties");
		property = new Properties();

		property.load(file);

		String url = property.getProperty("appURL1");

		log = LogManager.getLogger(this.getClass());
		if (property.getProperty("execution_envi").equalsIgnoreCase("remote")) {
			DesiredCapabilities cap = new DesiredCapabilities();

			// os
			if (os.equalsIgnoreCase("windows")) {

				cap.setPlatform(Platform.WIN11);

			}
			if (os.equalsIgnoreCase("linux")) {

				cap.setPlatform(Platform.LINUX);

			}

			else if (os.equalsIgnoreCase("mac")) {

				cap.setPlatform(Platform.MAC);
			}

			else {
				System.out.println("Invalid OS name");
				return;
			}
			// browser

			switch (br.toLowerCase()) {

			case "chrome":
				cap.setBrowserName("chrome");
				break;
			case "firefox":
				cap.setBrowserName("firefox");
				break;
			case "edge":
				cap.setBrowserName("edge");
				break;
			default:
				System.out.println("Invalid browser name");
				return;
			}

			driver = new RemoteWebDriver(new URL(property.getProperty("http://localhost:4444/wd/hub")), cap);
		}

		if (property.getProperty("execution_envi").equalsIgnoreCase("local")) {

			switch (br.toLowerCase()) {

			case "chrome":
				driver = new ChromeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;

			default:
				System.out.println("Invalid browser name");
				return;
			}
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

			driver.get(property.getProperty("appURL1"));

		}

		
	}

	/**
	 * Tears down the WebDriver instance by closing the browser.
	 */
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	/**
	 * Generates a random alphabetic string of length 6.
	 *
	 * @return A randomly generated alphabetic string.
	 */
	public String randomeString() {
		String generatedstring = RandomStringUtils.randomAlphabetic(6);
		return generatedstring;
	}

	/**
	 * Generates a random alphanumeric string of length 8.
	 *
	 * @return A randomly generated alphanumeric string.
	 */
	public String randomeAlphanumeric() {
		String generatedAlphanumeric = RandomStringUtils.randomAlphanumeric(8);
		return generatedAlphanumeric;
	}

	/**
	 * Captures a screenshot of the current browser window and saves it to the
	 * specified directory with a timestamped filename.
	 *
	 * @param tname The name of the test for which the screenshot is being captured.
	 * @return The file path of the saved screenshot.
	 * @throws IOException If an error occurs while saving the screenshot.
	 */
	public String captureScreen(String tname) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir") + "\\ScreenShots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
}
