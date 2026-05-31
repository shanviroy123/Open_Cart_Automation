package org.example.base;

import org.example.driver.DriverFactory;
import org.example.utils.ExtentManagerUtil;
import org.example.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase extends ExtentManagerUtil {

    public WebDriver driver;

    @BeforeMethod
    public void stup() throws InterruptedException {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get("https://demowebshop.tricentis.com/");
        Test.get().info("URL launched");
        Thread.sleep(2000);
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws InterruptedException {
        try {
            if (result.getStatus() == ITestResult.SUCCESS) {
                Test.get().pass("Test Passed");
            } else if (result.getStatus() == ITestResult.FAILURE) {
                Test.get().fail(result.getThrowable());
            } else if (result.getStatus() == ITestResult.SKIP) {
                Test.get().skip("Test skipped");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Thread.sleep(2000);
            if (DriverFactory.getDriver() != null) {
                DriverFactory.getDriver().quit();
            }
        }
    }
}