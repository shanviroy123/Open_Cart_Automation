package tests;

import org.example.pages.CartPage;
import org.example.pages.CheckOutPage;
import org.example.pages.AddingProductToCart;
import org.example.base.TestBase;
import org.example.pages.RegisterPage;

import org.testng.annotations.Test;

import java.io.IOException;


public class OpenCartScripts extends TestBase{
//    WebDriver driver; Since you extend TestBase, you already likely have a driver initialized there. But in this class you created a new driver variable that is NULL:

//    @Test
    public void Adding_Computer_toCart() throws IOException{
        try {
//            System.out.println("Computer Thread = " + Thread.currentThread().getId());
            Test.get().info("Registration process started");
            RegisterPage Register = new RegisterPage(driver);
            AddingProductToCart AddproducttoCart = new AddingProductToCart(driver);
            CartPage cp = new CartPage(driver);
            CheckOutPage CheckOut= new CheckOutPage(driver);
            Register.registeruser();
            Test.get().info("Search for Computer");
            int Price = (int) AddproducttoCart.AddingComputertoCart();
            AddproducttoCart.EmailToFriend();
            cp.Cart();
            cp.PriceCalculationForComputer(Price);
            CheckOut.FinalBilling();
        }catch(Exception e){
            Test.get().fail(e);
            e.printStackTrace();
        }
        finally{
            extent.flush();
        }
    }
//   @Test
   public void Adding_Laptop_ToCart() throws IOException{
        try{
            Test.get().info("Registration process started");
           RegisterPage Register = new RegisterPage(driver);
           AddingProductToCart AddproducttoCart = new AddingProductToCart(driver);
           CartPage cp = new CartPage(driver);
           CheckOutPage CheckOut= new CheckOutPage(driver);
           Register.registeruser();
           Test.get().info("Processes to add laptop");
           int Amount = (int)AddproducttoCart.AddinglaptoptoCart();
           cp.Cart();
           cp.price_CalculationforLaptop(Amount);
           CheckOut.FinalBilling();
        }
        catch(Exception e){
            Test.get().fail(e);
            e.printStackTrace();
    }
        finally{
        extent.flush();
    }
    }
    @Test
    public void Multiple_Items_added_ToCart() throws IOException{
        try {
            Test.get().info("Registration process started");
            RegisterPage Register = new RegisterPage(driver);
            AddingProductToCart AddproducttoCart = new AddingProductToCart(driver);
            CartPage cp = new CartPage(driver);
            CheckOutPage CheckOut = new CheckOutPage(driver);
            Register.registeruser();
            Test.get().info("Processes to add laptop");
            int LaptopPrice = (int) AddproducttoCart.AddinglaptoptoCart();
//            Thread.sleep(2000);
            Test.get().info("Search for Computer");
            int ComputerPrice = (int) AddproducttoCart.AddingComputertoCart();
            AddproducttoCart.EmailToFriend();
            cp.Cart();
            cp.Multipleitems_PriceCalculation(ComputerPrice, LaptopPrice);
            CheckOut.FinalBilling();
        }
        catch(Exception e){
            Test.get().fail(e);
            e.printStackTrace();
        }
        finally{
            extent.flush();
        }
    }

}
