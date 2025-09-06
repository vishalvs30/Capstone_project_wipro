package nopTestCases;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.*;

public class CurrencySteps {
    WebDriver driver;
    NopCommercePage page;

    @Given("User opens NopCommerce homepage for currency")
    public void open_homepage_currency() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
        page = new NopCommercePage(driver);
    }

    @When("User changes currency to {string}")
    public void change_currency(String currency) {
        page.changeCurrency(currency);
    }

    @Then("Currency operation completed and close browser")
    public void currency_completed_close() {
        driver.quit();
    }
}
