package org.example.utils;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.MediaEntityBuilder;

public class ReporterLogger {
    public static void log(ExtentTest Test, WebDriver driver, String message, String status){
        try {
            String ScreenPath = ScreenshotUtil.Screenshots(driver, message.replace(" ", "_"));
            switch(status.toUpperCase()){
                case "INFO":
                    Test.info(message);
                    break;
                case "PASS":
                    Test.pass(message, MediaEntityBuilder.createScreenCaptureFromPath(ScreenPath).build());
                    break;
                case "FAIL":
                    Test.fail(message, MediaEntityBuilder.createScreenCaptureFromPath(ScreenPath).build());
                    break;
                case "SKIP":
                    Test.skip(message, MediaEntityBuilder.createScreenCaptureFromPath(ScreenPath).build());
                    break;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
