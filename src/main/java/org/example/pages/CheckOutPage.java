package org.example.pages;

import org.example.base.TestBase;
import org.example.utils.ExtentManagerUtil;
import org.example.utils.ReporterLogger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.example.pages.CommonPage;
import org.example.utils.WaitUtil;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;

public class CheckOutPage extends TestBase {
    WebDriver driver;
    @FindBy(xpath="//input[@id='termsofservice']")
    public WebElement ClickTermsandConditions;
    @FindBy(xpath="//button[@id='checkout']")
    public WebElement CheckOut_Button;
    @FindBy(xpath="//select[@id='BillingNewAddress_CountryId']")
    public WebElement selectCountry;
    @FindBy(xpath="//input[@id='BillingNewAddress_City']")
    public WebElement EnterCity;
    @FindBy(xpath="//input[@id='BillingNewAddress_Address1']")
    public WebElement EnterAddress1;
    @FindBy(xpath="//input[@id='BillingNewAddress_ZipPostalCode']")
    public WebElement EnterZip;
    @FindBy(xpath="//input[@id='BillingNewAddress_PhoneNumber']")
    public WebElement EnterPhoneNum;
    @FindBy(xpath="//*[@id='billing-buttons-container']/input")
    public WebElement Continue_button;
    @FindBy(xpath="//*[@id='shipping-buttons-container']/input")
    public WebElement Continue_Shipping_address;
    @FindBy(xpath="//label[text()='Next Day Air (0.00)']")
    public WebElement SelectShippingMEthod;
    @FindBy(xpath="//*[@id='shipping-method-buttons-container']/input")
    public WebElement Continue_ShippingMethod;
    @FindBy(xpath="//label[text()='Credit Card']")
    public WebElement PaymentType;
    @FindBy(xpath="//*[@id='payment-method-buttons-container']/input")
    public WebElement Continue_paymenttype;
    @FindBy(id="paymentmethod_0")
    public WebElement CashOnDelivery;
//    @FindBy(xpath="//input[@id='CardholderName']")
//    public WebElement CardHolderName;
//    @FindBy(xpath="//input[@id='CardNumber']")
//    public WebElement CardNumber;
//    @FindBy(xpath="//input[@id='ExpireMonth']")
//    public WebElement ExpireMonth;
//    @FindBy(xpath="//input[@id='ExpireYear']")
//    public WebElement ExpireYear;
//    @FindBy(xpath="//input[@id='CardCode']")
//    public WebElement CardCode;
    @FindBy(xpath="//*[@id=\"payment-info-buttons-container\"]/input")
    public WebElement Continue_Payment;
    @FindBy(xpath="//*[contains(text(),'You will pay by COD')]")
    public WebElement COD;
    @FindBy(xpath="//*[@id='confirm-order-buttons-container']/input")
    public WebElement ClickConfirm;
    @FindBy(xpath="//*[contains(text(),'Your order has been successfully processed!')]")
    public WebElement ORderPlaced;
    @FindBy(xpath="//i[text()='Pick up your items at the store (put your store address here)']")
    public WebElement ScreenClick;
    @FindBy(xpath = "//input[@value=\"Continue\"]")
    public WebElement ContiinueShopping;
    @FindBy(xpath="//div[@class='order-review-data']")
    public WebElement OrderReviewSection;



    public CheckOutPage(WebDriver driver){
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }
    /*public void FinalBilling() throws IOException{
        WaitUtil wait = new WaitUtil(driver);
        try{

            CommonPage comm_page= new CommonPage(driver);
            comm_page.perform(ClickTermsandConditions, "click", "", "Clcik on Terms and Conditions");
            comm_page.perform(CheckOut_Button, "click", "", "Clicked on CheckOut Button");
            comm_page.perform(selectCountry, "click", "", "Select value from Country");
            Select select = new Select(selectCountry);
            select.selectByVisibleText("India");
            comm_page.perform(EnterCity, "sendkeys", "Gurgaon", "City Entered");
            comm_page.perform(EnterAddress1, "sendkeys", "123 main Street", "Entered Address");
            comm_page.perform(EnterZip, "sendkeys", "122017", "Entered Zip");
            comm_page.perform(EnterPhoneNum, "sendkeys", "9876545678", "Entered Phone");
//            ReporterLogger.log(Test, driver, "Added Address", "Pass");
            comm_page.perform(Continue_button, "click", "", "Clicked on Continue Button");
            wait.waiforElement(ScreenClick);
            comm_page.perform(ScreenClick, "click", "", "Clcik on Screen");
            comm_page.perform(Continue_Shipping_address, "click", "", "Clicked on Shipping Continue Button");
            wait.waiforElement(SelectShippingMEthod);
            comm_page.perform(SelectShippingMEthod, "click", "", "Select Shipping Method");
            comm_page.perform(Continue_ShippingMethod, "click", "", "Clicked on Continue Shipping Method");
//            ReporterLogger.log(Test, driver, "Selected Shipping Details", "Pass");
            wait.waiforElement(CashOnDelivery);
            comm_page.perform(CashOnDelivery, "click", "", "Select Payment type method");
            comm_page.perform(Continue_paymenttype, "click", "", "Clicked on Payment Method");
//            ReporterLogger.log(Test, driver, "Payment method Added", "Pass");
            String COD_text= COD.getText();
            if(COD_text.equalsIgnoreCase("You will pay by COD"))
//                ReporterLogger.log(Test, driver, "Cash on delivery is successfully selected", "Pass");
                System.out.println("COD selected");
            else
                System.out.println("COD Not selected");
//                ReporterLogger.log(Test, driver,"Cash on dElivery is not successfully selected", "FAIL");

            comm_page.perform(Continue_Payment, "click", "", "Clicked on Continue Payment");
            comm_page.perform(ClickConfirm, "click", "", "Clicked on Confirm Button");

            wait.waiforElement(ORderPlaced);
            String OrderPlaced_confirmation= ORderPlaced.getText();
            if(OrderPlaced_confirmation.equalsIgnoreCase("Your order has been successfully processed!"))
                System.out.println("Ordered");
//                ReporterLogger.log(Test, driver, "Successfully placed order", "PASS");
            else
                System.out.println("Not Ordered");
//                ReporterLogger.log(Test, driver, "Order not placed Failed", "FAIL");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     */
    public void FinalBilling() {

        WaitUtil wait = new WaitUtil(driver);
        CommonPage comm_page = new CommonPage(driver);

        try {

            // Checkout
            comm_page.perform(ClickTermsandConditions, "click", "", "Clicked Terms and Conditions");
            comm_page.perform(CheckOut_Button, "click", "", "Clicked Checkout Button");

            // Billing Address
            comm_page.perform(selectCountry, "select", "India", "Selected Country");
            comm_page.perform(EnterCity, "sendkeys", "Gurgaon", "Entered City");
            comm_page.perform(EnterAddress1, "sendkeys", "123 Main Street", "Entered Address");
            comm_page.perform(EnterZip, "sendkeys", "122017", "Entered Zip");
            comm_page.perform(EnterPhoneNum, "sendkeys", "9876545678", "Entered Phone Number");

            ReporterLogger.log(ExtentManagerUtil.Test.get(), driver, "Billing Address Added Successfully", "PASS");
            comm_page.perform(Continue_button, "click", "", "Clicked Billing Continue");

            // Shipping Address
            wait.waiforElement(Continue_Shipping_address);
            comm_page.perform(Continue_Shipping_address, "click", "", "Clicked Shipping Address Continue");

            // Shipping Method
            wait.waiforElement(SelectShippingMEthod);
            comm_page.perform(SelectShippingMEthod, "click", "", "Selected Shipping Method");

            ReporterLogger.log(ExtentManagerUtil.Test.get(), driver, "Shipping Method Selected", "PASS");
            comm_page.perform(Continue_ShippingMethod, "click", "", "Clicked Shipping Method Continue");

            // Payment Method
            wait.waiforElement(CashOnDelivery);
            comm_page.perform(CashOnDelivery, "click", "", "Selected Cash On Delivery");

            ReporterLogger.log(ExtentManagerUtil.Test.get(), driver, "Payment Method Selected", "PASS");
            comm_page.perform(Continue_paymenttype, "click", "", "Clicked Payment Continue");

            // Payment Verification
            wait.waiforElement(COD);
            String COD_text_validation = COD.getText().trim();
            if (COD_text_validation.equals("You will pay by COD"))
                ReporterLogger.log(ExtentManagerUtil.Test.get(),driver, "Cash On Delivery Verified", "PASS");
            else
                ReporterLogger.log(ExtentManagerUtil.Test.get(), driver, "Cash On Delivery Verification Failed", "FAIL");
            // Order Review
            comm_page.perform(Continue_Payment, "click", "", "Clicked Continue Payment");
//            wait.waiforElement(ClickConfirm);
//            comm_page.perform(ClickConfirm, "click", "", "Clicked Confirm Order");
            Thread.sleep(2000); // temporary for debugging
            wait.waiforElement(ClickConfirm);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ClickConfirm);
            Thread.sleep(1000);
            wait.waiforElement(OrderReviewSection);
            ReporterLogger.log(ExtentManagerUtil.Test.get(), driver, "Order Review Page", "PASS");
            comm_page.perform(ClickConfirm, "click", "", "Clicked Confirm Order");
            // Final Validation
            wait.waiforElement(ORderPlaced);
            String Orderplaced= ORderPlaced.getText().trim();
            if (Orderplaced.equals("Your order has been successfully processed!")) {
                ReporterLogger.log(ExtentManagerUtil.Test.get(), driver, "Order Placement Successfully", "PASS");
            } else
                ReporterLogger.log(ExtentManagerUtil.Test.get(), driver, "Order Placement Failed", "FAIL");
            comm_page.perform(ContiinueShopping,"click", "", "Continue Shopping");
        } catch (Exception e) {
            ReporterLogger.log(ExtentManagerUtil.Test.get(), driver, "Checkout Flow Failed : " + e.getMessage(), "FAIL");
            throw new RuntimeException(e);
        }
    }
}
