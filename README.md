# TC002_LoginTest

## Overview

This project contains a test case (`TC002_LoginTest`) for verifying the login functionality of a web application. The test is implemented using the **TestNG** framework and follows a structured approach to ensure the login process works as expected.

## Features

- Navigates to the "My Account" section of the application.
- Performs login using credentials stored in a properties file.
- Verifies the successful login by checking the presence of the "My Account" page.
- Logs the start and end of the test case for better traceability.
- Handles exceptions gracefully and fails the test if any issue occurs during execution.

## Prerequisites

- **Java Development Kit (JDK)**: Ensure JDK 8 or higher is installed.
- **TestNG Framework**: The test case uses TestNG for execution.
- **Selenium WebDriver**: Required for browser automation.
- **Maven**: Used for dependency management.
- **Properties File**: Contains the email and password for login.

## Project Structure

- **`testCases/TC002_LoginTest.java`**: Contains the test case for login functionality.
- **`pageObjects`**: Includes classes for interacting with different pages of the application:
  - `HomePage`: Handles navigation to the login page.
  - `LoginClass`: Handles login actions (entering email, password, and clicking login).
  - `MyAccount`: Verifies the presence of the "My Account" page.
- **`testBase/BaseClass`**: Provides common setup and teardown methods for the test.

## Test Steps

1. Navigate to the "My Account" section and click on the "Login" option.
2. Enter the email and password from the properties file.
3. Click the login button.
4. Verify if the "My Account" page is displayed.

## Assertions

- The test passes if the "My Account" page is displayed after login.
- The test fails if:
  - Any exception occurs during execution.
  - The "My Account" page is not found.

## How to Run the Test

1. Clone the repository and import it into your IDE (e.g., Eclipse).
2. Ensure all dependencies are resolved using Maven.
3. Update the `properties` file with valid email and password credentials.
4. Run the test using TestNG:
   - Right-click on the test file (`TC002_LoginTest.java`) and select **Run As > TestNG Test**.

## Logging

- Logs are generated to indicate the start and end of the test case.
- Additional logs are added for key actions, such as entering credentials and clicking buttons.

## Dependencies

The following dependencies are required for the project:

- **TestNG**: For test execution.
- **Selenium WebDriver**: For browser automation.
- **Log4j**: For logging (if used in the project).

## Notes

- Ensure the WebDriver is configured correctly for the browser you are testing.
- The test assumes the application under test is accessible and the credentials provided are valid.
- Update the `BaseClass` to include any required setup, such as browser initialization and teardown.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.
