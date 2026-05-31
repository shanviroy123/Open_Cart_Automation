package org.example.pages;

import org.example.base.TestBase;
import org.example.utils.ReporterLogger;
import org.example.utils.WaitUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.util.*;

public class CartPage extends TestBase {
    WebDriver driver;
    CommonPage comm_page;
    WaitUtil wait;
    //Xpaths
    @FindBy(xpath="//span[text()='Shopping cart']")
    public WebElement shoppingCart;
    @FindBy(xpath="//*[@id='CountryId']")
    public WebElement CountryDD;
    @FindBy(xpath="//*[@id='ZipPostalCode']")
    public WebElement ZipCode;
    @FindBy(xpath="//input[@name='estimateshipping']")
    public WebElement EstimateShipping;
    @FindBy(xpath="//span[text()='(read)']")
    public WebElement readTandC;
    @FindBy(xpath="//*[@id='ph-title']/div/h1")
    public WebElement ValidateTandC;
    @FindBy (xpath="//td[@class='product']//div[@class='attributes']")
    public  WebElement AdditionalPrice;
    @FindBy (xpath="//span[@class='nobr']/span/strong")
    public WebElement TotalAmount;
    @FindBy (xpath="//td[@class='product']/a")
    public List<WebElement> Productnames;
    @FindBy (xpath="//td[contains(@class,'subtotal')]")
    public List<WebElement> ItemPrices;



    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        comm_page = new CommonPage(driver);
        wait = new WaitUtil(driver);
    }
    public void Cart() throws IOException{
        try{
            comm_page.perform(shoppingCart, "click", "", "Clicked Shopping Cart");
            comm_page.perform(CountryDD, "select", "India", "Selected Country");
            comm_page.perform(ZipCode, "sendkeys", "122017", "Entered Zip Code");
            comm_page.perform(EstimateShipping, "click", "", "Clicked Estimate Shipping");
            comm_page.perform(readTandC, "click", "", "Accepted Terms and Conditions");

            comm_page.Switchtochildwindow();
            driver.getTitle();
            String ValidateTermsandCondition = ValidateTandC.getText();
            if(ValidateTermsandCondition.equals("Conditions of use"))
                ReporterLogger.log(Test.get(), driver,"Validation of Terms and Condition Successfully" + " "+ValidateTermsandCondition, "PASS");
            else
                ReporterLogger.log(Test.get(), driver,"Validation of Terms and Condition Failed" + " "+ValidateTermsandCondition, "FAIL");
            comm_page.SwitchtoParentwindow();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void PriceCalculationForComputer(int Price) throws IOException{
        try{
//            int ActualComputerPrice= Integer.parseInt(Price);
            String AdditonalallPrice= AdditionalPrice.getText();
            String processorPrice = AdditonalallPrice.split("\n")[0].split("\\[\\+")[1]
                    .replace("]", "")
                    .replace(".00", "");
            String PROCESSER_price = processorPrice.replaceAll("[^0-9.]", "");

            String RAMPRice= AdditonalallPrice.split("\n")[1].split("\\[\\+")[1]
                    .replace("]", "")
                    .replace(".00", "");
            String RAM_price = RAMPRice.replaceAll("[^0-9.]", "");

            String HDD= AdditonalallPrice.split("\n")[2].split("\\[\\+")[1]
                    .replace("]", "")
                    .replace(".00", "");
            String HDD_price= HDD.replaceAll("[^0-9.]", "");

            String OfficePrice= AdditonalallPrice.split("\n")[3].split("\\[\\+")[1]
                    .replace("]", "")
                    .replace(".00", "");
            String Office_Price= OfficePrice.replaceAll("[^0-9.]", "");

            System.out.println();
            int totalPrice = Price + (int)Double.parseDouble(PROCESSER_price)
                    + (int)Double.parseDouble(RAM_price)
                    + (int)Double.parseDouble(HDD_price)
                    + (int)Double.parseDouble(Office_Price);
            String Total_Amount= TotalAmount.getText();
            if(totalPrice==(int)Double.parseDouble(Total_Amount))
                ReporterLogger.log(Test.get(), driver,"Correct Price in Cart", "PASS");
            else
                ReporterLogger.log(Test.get(), driver,"InCorrect Price in Cart", "FAIL");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    public void price_CalculationforLaptop(int Amount) throws IOException{
        try{
            String Total_Amount= TotalAmount.getText();
            if(Amount==(int)Double.parseDouble(Total_Amount))
                ReporterLogger.log(Test.get(), driver,"Correct Price in Cart", "PASS");
            else
                ReporterLogger.log(Test.get(), driver,"InCorrect Price in Cart", "FAIL");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    public void Multipleitems_PriceCalculation(int Computer_Price, int Laptop_PRice) {

        String AdditonalallPrice = AdditionalPrice.getText();

        String processorPrice = AdditonalallPrice.split("\n")[0].split("\\[\\+")[1]
                .replace("]", "").replace(".00", "");

        String RAM_price = AdditonalallPrice.split("\n")[1].split("\\[\\+")[1]
                .replace("]", "").replace(".00", "");

        String HDD_price = AdditonalallPrice.split("\n")[2].split("\\[\\+")[1]
                .replace("]", "").replace(".00", "");

        String Office_Price = AdditonalallPrice.split("\n")[3].split("\\[\\+")[1]
                .replace("]", "").replace(".00", "");

        int totalPriceOfComputer =
                Computer_Price
                        + Integer.parseInt(processorPrice.replaceAll("[^0-9]", ""))
                        + Integer.parseInt(RAM_price.replaceAll("[^0-9]", ""))
                        + Integer.parseInt(HDD_price.replaceAll("[^0-9]", ""))
                        + Integer.parseInt(Office_Price.replaceAll("[^0-9]", ""));

        int total_PriceOFLaptop = Laptop_PRice;

        Map<String, Integer> expectedMap = new HashMap<>();
        expectedMap.put("Build your own cheap computer", totalPriceOfComputer);
        expectedMap.put("14.1-inch Laptop", total_PriceOFLaptop);

        Map<String, Integer> actualMap = new HashMap<>();

        for (int i = 0; i < Productnames.size(); i++) {
            String productName = Productnames.get(i).getText().trim();
            int price = Integer.parseInt(ItemPrices.get(i).getText()
                    .replace(",", "").replace(".00", "").trim());
            actualMap.put(productName, price);
        }

        if (actualMap.equals(expectedMap)) {
            ReporterLogger.log(Test.get(), driver,"Product Prices in Cart match with Actual Cost of the Product", "PASS");
        } else {
            for (String product : expectedMap.keySet()) {
                Integer expectedPrice = expectedMap.get(product);
                Integer actualPrice = actualMap.get(product);

                if (actualPrice == null) {
                    ReporterLogger.log(Test.get(), driver,"PRoduct prices does not match FAILED: " + product,"FAIL");
                } else if (!actualPrice.equals(expectedPrice)) {
                    ReporterLogger.log(Test.get(), driver,"Mismatch for: " + product +
                            " Expected: " + expectedPrice +
                            " Actual: " + actualPrice, "FAIL");
                }
            }
        }
        int actualTotalPrice = 0;

        for (Integer price : actualMap.values()) {
            actualTotalPrice += price;
        }
        String Total_Amount= TotalAmount.getText();
        if(actualTotalPrice==(int)Double.parseDouble(Total_Amount))
            ReporterLogger.log(Test.get(), driver,"Correct Price in Cart", "PASS");
        else
            ReporterLogger.log(Test.get(), driver,"InCorrect Price in Cart", "FAIL");
    }
}
