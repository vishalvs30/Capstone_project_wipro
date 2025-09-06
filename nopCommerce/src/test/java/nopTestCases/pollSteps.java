package nopTestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.*;

public class pollSteps {
    private WebDriver driver;
    private NopCommercePage page;

    @Given("User opens NopCommerce homepage for poll")
    public void user_opens_homepage_for_poll() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
        page = new NopCommercePage(driver);
    }

    @When("User logs in with email {string} and password {string}")
    public void user_logs_in(String email, String password) {
        page.login(email, password);
    }

    @When("User votes {string} in community poll")
    public void vote_in_poll(String option) {
        page.voteInCommunityPoll(option);
    }

    @Then("Poll operation completed and close browser")
    public void close_browser() {
        driver.quit();
    }
}
