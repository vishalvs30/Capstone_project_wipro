package nopTestCases;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.*;

public class SearchStep {
    WebDriver driver;
    NopCommercePage page;

    @Given("User opens NopCommerce homepage for search")
    public void open_homepage_search() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
        page = new NopCommercePage(driver);
    }

    @When("User searches for {string}")
    public void user_searches_for(String product) {
        page.searchProduct(product);
    }

    @Then("Search completed and close browser")
    public void search_completed_close() {
        driver.quit();
    }
}
