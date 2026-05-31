package org.example.pages;

import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.math3.analysis.function.Add;
import org.apache.commons.math3.ode.nonstiff.AdamsMoultonFieldIntegrator;
import org.apache.poi.ss.formula.functions.T;
import org.example.base.TestBase;
import org.example.utils.ExtentManagerUtil;
import org.example.utils.ReporterLogger;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.example.utils.WaitUtil;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddingProductToCart extends TestBase {
    public WebDriver driver;
    @FindBy (xpath="//*[@id='small-searchterms']")
    public WebElement Search;
    @FindBy (xpath="//input[@type='submit']")
    public WebElement ClcikSearchButton;
    @FindBy (xpath="//*[@alt='Picture of Build your own cheap computer']")
    public WebElement Select_cheapComputer;
    @FindBy (xpath="//label[text()='Fast  [+100.00]']")
    public WebElement select_processor;
    @FindBy (xpath="//label[text()='8 GB  [+60.00]']")
    public WebElement select_ram;
    @FindBy (xpath="//label[text()='400 GB  [+100.00]']")
    public WebElement select_HardDisk;
    @FindBy (xpath="//input[@value='Email a friend']")
    public WebElement Email_a_friend;
    @FindBy (xpath="//input[@class='friend-email']")
    public WebElement enterfriendemail;
    @FindBy (xpath="//textarea[@class='personal-message']")
    public WebElement personal_message;
    @FindBy (xpath="//input[@name='send-email']")
    public WebElement send_email;
    @FindBy (xpath="//*[contains(text(), 'Your message has been sent.')]")
    public WebElement ValidateMessageAfterEmail;
    @FindBy (xpath="//label[text()='Office Suite  [+100.00]']")
    public WebElement selectSoftware;
    @FindBy (xpath="//input[@id='add-to-cart-button-72']")
    public WebElement AddtoCart;
    @FindBy (xpath="//*[@class='product-price']/span")
    public WebElement Price;
    @FindBy(xpath = "//ul[@class='top-menu']//a[contains(text(),'Computers')]")
    public WebElement ComputersMenu;
    @FindBy(xpath = "//ul[@class='top-menu']//a[contains(text(),'Notebooks')]")
    public WebElement NotebooksMenu;
    @FindBy(xpath = "//div[@class='product-item']/div/a")
    public WebElement LAptop;
    @FindBy(xpath = "//div[@class='product-name']/h1")
    public WebElement laptoplength;
    @FindBy(xpath = "//*[@id='product-details-form']//table/tbody/tr")
    public List<WebElement> specificationRows;
    @FindBy(xpath = "//*[@id='add-to-cart-button-31']")
    public WebElement AddLaptoptoCart;
    @FindBy (xpath="//span[@class='price-value-31']")
    public WebElement LaptopPrice;

    public CommonPage comm_page;
    public WaitUtil Iwait;
    public AddingProductToCart(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.comm_page = new CommonPage(driver);
        this.Iwait = new WaitUtil(driver);
    }
    public double AddingComputertoCart() throws IOException{
        int Com_Price=0;
        try{
            Iwait.waiforElementtoBeClickable(Search);
            for(int i=0;i<2;i++){
                try{
                    comm_page.perform(Search, "click", "", "Clicked Search Box");
                    break;
                }catch(Exception e){
                    Thread.sleep(2000);
                }
            }
            comm_page.perform(Search, "sendkeys", "Computer", "Entered Product Name");
            comm_page.perform(ClcikSearchButton, "click", "", "Clicked Search Button");
            comm_page.perform(Select_cheapComputer, "click", "", "Selected Computer");
            ReporterLogger.log(Test.get(), driver,"Successfully Searched for Computer", "PASS" );
            comm_page.perform(select_processor, "click", "", "Selected Processor");
            comm_page.perform(select_ram, "click", "", "Selected RAM");
            comm_page.perform(select_HardDisk, "click", "", "Selected Hard Disk");
            comm_page.perform(selectSoftware, "click", "", "Selected Software");

            Com_Price = (int) Double.parseDouble(Price.getText());
            comm_page.perform(AddtoCart, "click", "", "Clicked Add To Cart");
        }
        catch(Exception e){
            System.out.println("Error in method SearchBox");
            e.printStackTrace();
        }
        return Com_Price;
    }
    public void EmailToFriend() throws IOException{
        WaitUtil wait = new WaitUtil(driver);
        try{
            comm_page.perform(Email_a_friend, "click", "", "Clicked Email A Friend");
            wait.waiforElement(enterfriendemail);
            comm_page.perform(enterfriendemail, "sendkeys", "rahulkumar@gmail.com", "Entered Friend Email");
            comm_page.perform(personal_message, "sendkeys", "Hi, How r you", "Entered Personal Message");
            comm_page.perform(send_email, "click", "", "Clicked Send Email");
            wait.waiforElement(ValidateMessageAfterEmail);

            String getmessage= ValidateMessageAfterEmail.getText();
            if(getmessage.equals("Your message has been sent."))
                ReporterLogger.log(Test.get(), driver,"Expected message Displayed after email to Friend", "PASS");
            else
                ReporterLogger.log(Test.get(), driver,"Expected message not Displayed after email to Friend Failed", "FAIL");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public double AddinglaptoptoCart() throws IOException{
        int PriceOfLaptop=0;
        try{
            comm_page.perform(ComputersMenu, "hover", "", "Hovered on Computers");
            Iwait.waiforElement(NotebooksMenu);
            comm_page.perform(NotebooksMenu, "click", "", "Clicked on Notebooks");
            comm_page.perform(LAptop, "click", "", "Click on Laptop");
            String forteeninchLaptop= laptoplength.getText().trim();
            if(forteeninchLaptop.equals("14.1-inch Laptop"))
                ReporterLogger.log(Test.get(), driver,"14th inch Laptop displayed", "PASS");
            else
                ReporterLogger.log(Test.get(), driver, "14th inch Laptop not displayed", "FAIL");
            Map<String, String> specs = new HashMap<>();
            for (WebElement row : specificationRows) {
                List<WebElement> cols = row.findElements(By.tagName("td"));
                if (cols.size() >= 2)
                    specs.put(cols.get(0).getText().trim(), cols.get(1).getText().trim());
            }
            for (Map.Entry<String, String> entry : specs.entrySet())
                System.out.println(entry.getKey() + " = " + entry.getValue());
            if (specs.getOrDefault("Screensize", "").contains("14.1''"))
                ExtentManagerUtil.Test.get().pass("Screen Size Validated");
            else
                ExtentManagerUtil.Test.get().fail("Screen Size Validation Failed");

            if (specs.getOrDefault("CPU Type", "").contains("Intel"))
                ExtentManagerUtil.Test.get().pass("CPU Type Validated");
            else
                ExtentManagerUtil.Test.get().fail("CPU Type Validation Failed");

            if (specs.getOrDefault("Memory", "").contains("3 GB"))
                ExtentManagerUtil.Test.get().pass("Memory Validated");
            else
                ExtentManagerUtil.Test.get().fail("Memory Validation Failed");

            if (specs.getOrDefault("Hardrive", "").contains("250 GB"))
                ExtentManagerUtil.Test.get().pass("Hard Drive Validated");
            else
                ExtentManagerUtil.Test.get().fail("Hard Drive Validation Failed");
            PriceOfLaptop=  (int)Double.parseDouble(LaptopPrice.getText());
            comm_page.perform(AddLaptoptoCart, "click", "","Click on Add to Cart for Laptop");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return PriceOfLaptop;
    }
}
