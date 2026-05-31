package org.example.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.utils.ConfigReaderUtil;
import org.example.utils.ExtentManagerUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.*;
import org.openqa.selenium.firefox.*;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver initDriver() {
        System.out.println("Creating Browser on Thread : " + Thread.currentThread().getId());
        String browser = ConfigReaderUtil.getProperty("browser");
        String headless = ConfigReaderUtil.getProperty("headless");

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions ChromeOptions = new ChromeOptions();
                if (headless.equalsIgnoreCase("true")) {
                    ChromeOptions.addArguments("--headless=new");
                }
                ChromeOptions.addArguments("--start-maximized");
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver(ChromeOptions));
                break;

            case "edge":
                EdgeOptions EdgeOptions = new EdgeOptions();
                if (headless.equalsIgnoreCase("true")) {
                    EdgeOptions.addArguments("--headless=new");
                }
                EdgeOptions.addArguments("--start-maximized");
                System.setProperty(
                        "webdriver.edge.driver",
                        "C:\\Drivers\\msedgedriver.exe"
                );
                driver.set(new EdgeDriver(EdgeOptions));
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless.equalsIgnoreCase("true")) {
                    firefoxOptions.addArguments("--headless=new");
                }
                firefoxOptions.addArguments("--star-maximized");
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver(firefoxOptions));
                break;
        }
        return driver.get();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }
}