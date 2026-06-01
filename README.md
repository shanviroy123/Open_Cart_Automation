This project is an automation testing framework developed for the OpenCart e-commerce application using Selenium WebDriver, Java, and TestNG. 
The framework follows the Page Object Model (POM) design pattern to improve code maintainability and reusability. 
It automates key user workflows such as product search, product customization, add-to-cart, and checkout validation. 
Maven is used for dependency management, while Extent Reports provide detailed execution reports. 
The framework also supports screenshot capture for failed test cases. 
Jenkins integration enables automated test execution as part of a CI/CD pipeline. 
This project demonstrates best practices in test automation and quality assurance.


## Installation Guide
1. Clone the repository:
   git clone <repository-url>
2. Open the project in IntelliJ IDEA or Eclipse.
3. Ensure Java JDK 17 (or your project's required version) is installed and configured.
4. Install Maven and verify the installation:
   mvn -version
5. Download project dependencies:
   mvn clean install
6. Configure the application URL, browser, and test data in the configuration file.
7. Execute the test suite using TestNG or run:
   mvn test
8. View the generated Extent Reports in the reports folder after execution.
9. (Optional) Configure Jenkins to automate test execution through a CI/CD pipeline.
