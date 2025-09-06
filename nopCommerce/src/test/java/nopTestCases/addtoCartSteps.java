package nopTestCases;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class addtoCartSteps {

    WebDriver driver;
    NopCommercePage page;

    @Given("User opens NopCommerce homepage for cart")
    public void user_opens_nop_commerce_homepage_for_cart() {
        // Launch browser and open NopCommerce
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
        page = new NopCommercePage(driver);
    }

    @When("User searches {string} and adds to cart")
    public void user_searches_and_adds_to_cart(String product) {
       
        page.searchAndAddToCart(product);
    }

    @Then("Product is added to cart and browser is closed")
    public void product_is_added_to_cart_and_browser_is_closed() {
        //print the cart quantity
        String cartQty = driver.findElement(By.cssSelector("span.cart-qty")).getText();
        System.out.println("Cart quantity after add: " + cartQty);

        driver.quit();
    }
}
