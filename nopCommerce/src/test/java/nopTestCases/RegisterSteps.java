package nopTestCases;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.*;

public class RegisterSteps {
    WebDriver driver;
    NopCommercePage page;

    @Given("User opens NopCommerce homepage for register")
    public void open_homepage_register() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
        page = new NopCommercePage(driver);
    }

    @When("User registers with {string} {string} {string} {string} {string}")
    public void user_registers(String fn, String ln, String email, String pass, String cpass) {
        page.register(fn, ln, email, pass, cpass);
    }

    @Then("Registration completed and close browser")
    public void registration_completed_close() {
        driver.quit();
    }
}

