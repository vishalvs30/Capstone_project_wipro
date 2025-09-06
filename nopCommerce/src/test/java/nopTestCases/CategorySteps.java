package nopTestCases;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.*;

public class CategorySteps {
    WebDriver driver;
    NopCommercePage page;

    @Given("User opens NopCommerce homepage for category")
    public void open_homepage_category() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
        page = new NopCommercePage(driver);
    }

    @When("User shops by category")
    public void shop_by_category() {
        page.shopByCategory();
    }

    @Then("Category operation completed and close browser")
    public void category_completed_close() {
        driver.quit();
    }
}

