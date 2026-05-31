package org.example.pages;

import org.example.base.TestBase;
import org.example.utils.ReporterLogger;
import org.example.utils.WaitUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



import java.io.IOException;

public class RegisterPage extends TestBase{
    WebDriver driver;
    CommonPage comm_page;
    WaitUtil wait;

    @FindBy (xpath="//a[text()='Register']")
    public WebElement RegisterUser;
    @FindBy (xpath="//h1[text()='Register']")
    public WebElement RegisterPage;
    @FindBy(xpath = "//label[text()='Female']")
    public WebElement SelectGender;
    @FindBy(xpath = "//*[@id='FirstName']")
    public WebElement FirstNAme;
    @FindBy(xpath = "//*[@id='LastName']")
    public WebElement LastNAme;
    @FindBy(xpath = "//*[@id='Email']")
    public WebElement email;
    @FindBy(xpath = "//*[@id='Password']")
    public WebElement Password;
    @FindBy(xpath = "//*[@id='ConfirmPassword']")
    public WebElement confirmPassword;
    @FindBy(xpath = "//*[@id='register-button']")
    public WebElement RegisterButton;
    @FindBy(xpath = "//*[contains(text(),'Your registration completed')]")
    public WebElement ConfirmRegistration;


    public RegisterPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);

        comm_page = new CommonPage(driver);
        wait = new WaitUtil(driver);
    }
    public void registeruser() throws IOException {
        try{
            int num= comm_page.generateFiveDigitRandom();
            comm_page.perform(RegisterUser,"click","","Click on Register User");
            wait.waiforElement(RegisterPage);
            comm_page.perform(SelectGender,"click","","Select Gender");
            comm_page.perform(FirstNAme,"sendkeys","Milky","Enter First Name");
            comm_page.perform(LastNAme,"sendkeys","Roy","Enter Last Name");
            comm_page.perform(email,"sendkeys","milkyroy"+num+"@gmail.com","Enter Email");
            comm_page.perform(Password,"sendkeys","Milky@123","Enter Password");
            comm_page.perform(confirmPassword,"sendkeys","Milky@123","Enter Confirm Password");
            comm_page.perform(RegisterButton,"click","","Click on Register Button");
            wait.waiforElement(RegisterPage);
            String ValidateRegistrationMessage = ConfirmRegistration.getText();
            if(ValidateRegistrationMessage.equals("Your registration completed"))
                ReporterLogger.log(Test.get(), driver, "Registration Successfully Completed", "PASS");
            else
                ReporterLogger.log(Test.get(), driver, "Registration Failed ", "FAIL");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
