package nopTestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.*;
import nopTestCases.NopCommercePage;

public class WishlistSteps {

    WebDriver driver;
    NopCommercePage page;

    @Given("User opens NopCommerce homepage for wishlist")
    public void user_opens_homepage_for_wishlist() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
        page = new NopCommercePage(driver);
    }

    @When("User adds {string} to wishlist")
    public void user_adds_product_to_wishlist(String product) {
        page.addToWishlist(product);
    }

    @Then("Wishlist operation completed and close browser")
    public void wishlist_operation_completed_and_close_browser() {
        driver.quit();
    }
}
