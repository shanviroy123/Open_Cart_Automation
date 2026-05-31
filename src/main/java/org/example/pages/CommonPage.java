package org.example.pages;

import org.example.utils.ExtentManagerUtil;
import org.example.utils.WaitUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;

import java.io.IOException;
import java.util.*;

public class CommonPage {
    WebDriver driver;
    public CommonPage(WebDriver driver){
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }
    String parentWindow; //Global variable for Common page
    public void Switchtochildwindow(){
        parentWindow= this.driver.getWindowHandle();
        Set<String> windows = this.driver.getWindowHandles();
        for(String window: windows) {
            if (!window.equals(parentWindow)) {
                this.driver.switchTo().window(window);
                break;
            }
        }
    }
    public void SwitchtoParentwindow(){
        this.driver.close();
        this.driver.switchTo().window(parentWindow);
    }
    public  void perform(WebElement element, String action, String value, String message) throws IOException{
        try{
            WaitUtil wait = new WaitUtil(driver);
            switch(action.toLowerCase()){
                case "click":
                    element.click();
                    ExtentManagerUtil.Test.get().pass(message);
                    break;
                case "sendkeys":
                    wait.waiforElement(element);
                    wait.waiforElementtoBeClickable(element);
                    element.clear();
                    element.sendKeys(value);
                    ExtentManagerUtil.Test.get().info(message + " : " + value);
                    break;
                case "gettext":
                    wait.waiforElement(element);
                    String text = element.getText();
                    ExtentManagerUtil.Test.get().info(message + " : " + text);
                    break;
                case "select":
                    wait.waiforElement(element);
                    Select select = new Select(element);
                    select.selectByVisibleText(value);
                    ExtentManagerUtil.Test.get().info(message + " : " + value);
                    break;
                case "hover":
                    wait.waiforElement(element);
                    Actions actions = new Actions(driver);
                    actions.moveToElement(element).perform();
                    ExtentManagerUtil.Test.get().pass(message);
                    break;
                default:
                    ExtentManagerUtil.Test.get().warning("Invalid action passed : " + action);
            }
        } catch (Exception e) {
            ExtentManagerUtil.Test.get().fail("Action Failed : " + action);
            ExtentManagerUtil.Test.get().fail(e.getMessage());
            e.printStackTrace();
        }
    }
    public int generateFiveDigitRandom(){
        int num=10000+new java.util.Random().nextInt(90000);
        return num;
    }
}
