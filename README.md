# Selenium Automation Framework with TestNG

This repository contains a robust Selenium Automation Framework built using **TestNG**. The framework is designed to cover all essential concepts of Selenium and TestNG, along with advanced features suitable for scalable and maintainable test automation.

## Features

### Selenium and TestNG Concepts
- **Page Object Model (POM):** Implementation of POM design pattern for better maintainability and reusability.
- **Cross-Browser Testing:** Support for running tests on multiple browsers like Chrome, Firefox, Edge, etc.
- **Data-Driven Testing:** Leveraging **Apache POI** to read test data from Excel files.
- **Grouping Tests:** Organize and execute tests in logical groups using TestNG’s grouping feature.
- **Log4j Logging:** Integrated **Log4j** for logging test execution details.
- **Properties File Configuration:** Centralized configuration management using `.properties` files.

### Advanced Features
- **Extent Reports:** Generate visually appealing and detailed reports for test execution results.
- **Selenium Grid Integration:** Run tests on **Selenium Grid** for distributed execution.
- **Dockerized Selenium Grid:** Seamless integration with **Docker** to set up and manage Selenium Grid containers.
- **Jenkins CI Setup:** Automated test execution in a Continuous Integration pipeline using **Jenkins**.

## Prerequisites

Before you begin, ensure you have the following installed:
- **Java Development Kit (JDK)** (version 8 or later)
- **Maven** (for dependency management)
- **Docker** (for Selenium Grid setup)
- **Jenkins** (for CI pipeline setup)
- **Browsers and WebDriver binaries** (ChromeDriver, GeckoDriver, etc.)

## Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/selenium-testng-framework.git
cd selenium-testng-framework
```

### 2. Install Dependencies
Ensure all dependencies are installed by running:
```bash
mvn clean install
```

### 3. Configure the Project
- Update the `config.properties` file with your environment-specific details.
- Place your test data in the `data` folder (Excel files for data-driven tests).
- Modify `log4j.properties` if you want to customize logging behavior.

### 4. Run Tests
#### Run Tests Locally
```bash
mvn test
```

#### Run Tests on Selenium Grid
Start the Selenium Grid using Docker:
```bash
docker-compose up
```
Then execute tests:
```bash
mvn test -Dgrid=true
```

#### Run Specific Groups
```bash
mvn test -Dgroups="smoke"
```

### 5. Generate Reports
After execution, find the **Extent Reports** in the `reports` folder.

### 6. Jenkins CI Setup
1. Create a new Jenkins job and configure it as a Maven project.
2. Add the repository URL and configure the build steps to run:
   ```bash
   mvn clean test
   ```
3. Integrate with Docker (if needed) to start/stop the Selenium Grid containers before/after the test execution.

## Project Structure

```
selenium-testng-framework/
│
├── src/main/java
│   ├── base/                 # Base classes for WebDriver and configuration
│   ├── pages/                # Page Object Model classes
│   ├── utils/                # Utility classes (e.g., Excel, Logging, etc.)
│   └── tests/                # TestNG test classes
│
├── src/test/resources
│   ├── config/               # Configuration files (e.g., config.properties)
│   ├── data/                 # Test data files (e.g., Excel files)
│   └── log4j.properties      # Log4j configuration
│
├── reports/                  # Extent Reports
├── docker-compose.yml        # Docker setup for Selenium Grid
├── pom.xml                   # Maven dependencies and build configuration
└── README.md                 # Project documentation
```

## Technologies Used
- **Selenium WebDriver**
- **TestNG**
- **Log4j**
- **Apache POI**
- **Extent Reports**
- **Docker**
- **Jenkins**

## How to Contribute
1. Fork the repository.
2. Create a new branch for your feature or bug fix:
   ```bash
   git checkout -b feature-name
   ```
3. Commit your changes and push them to the new branch:
   ```bash
   git commit -m "Add new feature"
   git push origin feature-name
   ```
4. Open a pull request and provide a detailed description of your changes.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

### Happy Testing! 🚀
