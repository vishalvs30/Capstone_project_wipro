package nopTestCases;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.*;

public class LoginSteps {
    WebDriver driver;
    NopCommercePage page;

    @Given("User opens NopCommerce homepage for login")
    public void open_homepage_login() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
        page = new NopCommercePage(driver);
    }

    @When("User logs in with {string} and {string}")
    public void user_logs_in(String email, String pass) {
        page.login(email, pass);
    }

    @Then("Login completed and close browser")
    public void login_completed_close() {
        driver.quit();
    }
}
