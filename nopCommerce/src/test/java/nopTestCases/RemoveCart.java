package nopTestCases;

import io.cucumber.java.en.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RemoveCart {

    WebDriver driver;
    NopCommercePage page;

    @Given("User opens NopCommerce homepage for remove")
    public void user_opens_homepage_for_remove() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
        page = new NopCommercePage(driver);
    }

    @And("User already has {string} in the cart")
    public void user_already_has_product_in_cart(String product) {
        // This ensures there is at least one item in the cart before removal
        page.searchAndAddToCart(product);
    }

    @When("User removes product from cart")
    public void user_removes_product_from_cart() {
        page.removeFromCart();
    }

    @Then("Removal operation completed and close browser")
    public void removal_operation_completed_and_close_browser() {
        driver.quit();
    }
}

