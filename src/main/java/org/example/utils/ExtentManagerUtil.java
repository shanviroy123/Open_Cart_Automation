package org.example.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManagerUtil {

    public static ExtentSparkReporter reporter;
    public static ExtentReports extent;
    public static ThreadLocal<ExtentTest> Test = new ThreadLocal<>();

    @BeforeSuite
    public void setup() {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportPath = System.getProperty("user.dir") + "/Reports/TestReport_" + timestamp + ".html";
        reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("Automation Report");
        reporter.config().setDocumentTitle("Open Cart Automation");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Automation");
    }

    @BeforeMethod
    public void getTestCaseName(ITestResult result) {
        String testCaseName = result.getMethod().getMethodName();
        Test.set(extent.createTest(testCaseName));
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
    }
}