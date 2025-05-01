package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

/**
 * ExtentReportManager is a utility class that implements the ITestListener
 * interface to generate ExtentReports for TestNG test execution. It provides
 * methods to log test results (success, failure, skipped) and generate a
 * detailed HTML report.
 */
public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter; // Reporter for generating HTML reports
	public ExtentReports extent; // Main ExtentReports object
	public ExtentTest test; // Represents individual test cases in the report
	String repName; // Name of the report file

	/**
	 * Called when the test suite starts. Initializes the ExtentReports and
	 * configures the report settings such as title, theme, and system information.
	 *
	 * @param testContext The TestNG context object containing test suite
	 *                    information.
	 */
	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // Current timestamp
		repName = "Test-Report-" + timeStamp + ".html"; // Report file name
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);

		// Configure the report
		sparkReporter.config().setDocumentTitle("OpenCart Automation Test Project");
		sparkReporter.config().setReportName("OpenCart Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);

		// Initialize ExtentReports and attach the reporter
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		// Add system information to the report
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environemnt", "QA");

		// Add OS and browser information from test context parameters
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);

		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);

		// Add included groups to the report
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}

	/**
	 * Called when a test case passes. Logs the test result as "PASS" in the report.
	 *
	 * @param result The result of the executed test case.
	 */
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // Assign test groups
		test.log(Status.PASS, result.getName() + " got successfully executed");
	}

	/**
	 * Called when a test case fails. Logs the test result as "FAIL" in the report
	 * and captures a screenshot of the failure.
	 *
	 * @param result The result of the executed test case.
	 */
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // Assign test groups

		// Log failure details
		test.log(Status.FAIL, result.getName() + " got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());

		// Capture and attach a screenshot to the report
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Called when a test case is skipped. Logs the test result as "SKIP" in the
	 * report.
	 *
	 * @param result The result of the executed test case.
	 */
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // Assign test groups
		test.log(Status.SKIP, result.getName() + " got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	/**
	 * Called when the test suite finishes. Flushes the ExtentReports data to the
	 * report file and opens the report in the default browser.
	 *
	 * @param testContext The TestNG context object containing test suite
	 *                    information.
	 */
	public void onFinish(ITestContext testContext) {
		extent.flush(); // Write all data to the report file

		// Open the report in the default browser
		String pathofExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
		File extentReport = new File(pathofExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * try { URL url = new URL("file:///" + System.getProperty("user.dir") +
		 * "\\reports\\" + repName);
		 * 
		 * // Create the email message ImageHtmlEmail email = new ImageHtmlEmail();
		 * 
		 * // Set the data source resolver for embedding images in the email
		 * email.setDataSourceResolver(new DataSourceUrlResolver(url));
		 * 
		 * // Configure SMTP server settings email.setHostName("smtp.googlemail.com");
		 * // SMTP server hostname email.setSmtpPort(465); // SMTP server port
		 * email.setAuthenticator(new DefaultAuthenticator("furqana@gmail.com",
		 * "password")); // Authentication // credentials email.setSSLOnConnect(true);
		 * // Enable SSL for secure connection
		 * 
		 * // Set email details email.setFrom("pavanoltraining@gmail.com"); // Sender's
		 * email address email.setSubject("Test Results"); // Email subject
		 * email.setMsg("Please find Attached Report...."); // Email body message
		 * email.addTo("pavankumar.busyqa@gmail.com"); // Receiver's email address
		 * 
		 * // Attach the report file to the email email.attach(url, "extent report",
		 * "please check report...");
		 * 
		 * // Send the email email.send(); } catch (Exception e) { e.printStackTrace();
		 * }
		 */
	}
}
