package nopTestCases;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class prodDescription {

    WebDriver driver;
    NopCommercePage page;

    @Given("User opens NopCommerce homepage")
    public void user_opens_nopcommerce_homepage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        page = new NopCommercePage(driver);
        page.openHomePage();
    }

    @When("User searches the site for {string}")
    public void user_searches_for(String productName) {
        page.searchProduct1(productName);
    }

    @When("User clicks on the product link {string}")
    public void user_clicks_on_the_product_link(String productLinkText) {
        page.clickOnProduct(productLinkText);
    }

    @Then("Product description should contain {string}")
    public void product_description_should_contain(String expectedText) {
        String actualDescription = page.getProductDescription();
        Assert.assertTrue(actualDescription.contains(expectedText));
    }

    @Then("Close the browser")
    public void close_the_browser() {
        page.closeBrowser();
    }

}
