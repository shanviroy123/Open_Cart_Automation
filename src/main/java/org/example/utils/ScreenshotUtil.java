package org.example.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ScreenshotUtil {
    public static  String Screenshots(WebDriver driver, String filename){
        String dir =System.getProperty("user.dir")+"/Reports/screenshots/";
        new File(dir).mkdirs();
        String path = dir +filename +".png";
        File src=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(src.toPath(), new File(path).toPath());
        } catch(IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}
