package nopTestCases;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NopCommercePage {
     WebDriver driver;
     WebDriverWait wait;

    // ---------------- Locators ----------------
     By registerLink = By.linkText("Register");
     By genderMale = By.id("gender-male");
     By firstName = By.id("FirstName");
     By lastName = By.id("LastName");
     By email = By.id("Email");
     By password = By.id("Password");
     By confirmPassword = By.id("ConfirmPassword");
     By registerButton = By.id("register-button");

     By loginLink = By.linkText("Log in");
     By loginEmail = By.id("Email");
     By loginPassword = By.id("Password");
     By loginButton = By.cssSelector("button.login-button");

     By searchBox = By.id("small-searchterms");
     By cartQty = By.cssSelector("span.cart-qty");
     By cartLink = By.linkText("Shopping cart");
     By removeCartItem = By.name("removefromcart");
     By updateCart = By.name("updatecart");

     By wishlistBtn = By.cssSelector(".add-to-wishlist-button");
     By wishlistLink = By.linkText("Wishlist");
     By removeWishlistItem = By.name("removefromcart");
     By updateWishlist = By.name("updatecart");

     By categoryLink = By.linkText("Books"); // example category
     By currencyDropdown = By.id("customerCurrency");

    // Community Poll locators (updated)
     By pollOptions = By.cssSelector("input[name*='pollanswers']");
     By voteButton = By.cssSelector("button[name*='vote-poll']");
     By pollResult = By.cssSelector("div.poll-results");
     By pollError = By.cssSelector("div.poll-vote-error");

    // ---------------- Constructor ----------------
    public NopCommercePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ---------------- Methods ----------------

    // Registration
    public void register(String fn, String ln, String mail, String pass, String cpass) {
        driver.findElement(registerLink).click();
        driver.findElement(genderMale).click();
        driver.findElement(firstName).sendKeys(fn);
        driver.findElement(lastName).sendKeys(ln);
        driver.findElement(email).sendKeys(mail);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(confirmPassword).sendKeys(cpass);
        driver.findElement(registerButton).click();
    }

    // Login
    public void login(String mail, String pass) {
        driver.findElement(loginLink).click();
        driver.findElement(loginEmail).sendKeys(mail);
        driver.findElement(loginPassword).sendKeys(pass);
        driver.findElement(loginButton).click();
    }

    // Search for a product and add it to the cart
    public void searchAndAddToCart(String product) {
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(product);
        driver.findElement(searchBox).sendKeys(Keys.ENTER);

        WebElement firstProduct = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("h2.product-title a")));
        firstProduct.click();

        WebElement addBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("button[id^='add-to-cart-button']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addBtn);
        addBtn.click();

        wait.until(ExpectedConditions.textMatches(cartQty, Pattern.compile("\\(\\d+\\)")));
    }

    
 // Remove all items from cart and wait until cart is empty
    public void removeFromCart() {
        driver.findElement(cartLink).click();
        wait.until(ExpectedConditions.titleContains("Shopping Cart"));

        // give a short wait for any rows to load
        List<WebElement> items = driver.findElements(removeCartItem);
        if (items.isEmpty()) {
            System.out.println("No items in cart to remove");
            return;
        }

        // tick each remove checkbox safely
        for (WebElement item : items) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", item);
            // use visibilityOf rather than elementToBeClickable to avoid timeout
            wait.until(ExpectedConditions.visibilityOf(item));
            // JavaScript click avoids “not clickable” overlays
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", item);
        }

        WebElement update = wait.until(ExpectedConditions.elementToBeClickable(updateCart));
        update.click();

        // wait until cart count goes to (0) OR until "Your Shopping Cart is empty!" appears
        wait.until(ExpectedConditions.or(
                ExpectedConditions.textToBePresentInElementLocated(cartQty, "(0)"),
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".order-summary-content"))
        ));
        System.out.println("Cart cleared successfully");
    }


    // Add a product to wishlist
    public void addToWishlist(String product) {
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(product);
        driver.findElement(searchBox).sendKeys(Keys.ENTER);

        WebElement wishBtn = wait.until(ExpectedConditions.elementToBeClickable(wishlistBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", wishBtn);
        wishBtn.click();
    }

    // Remove all products from wishlist
    public void removeFromWishlist() {
        driver.findElement(wishlistLink).click();

        List<WebElement> items = driver.findElements(removeWishlistItem);
        if (items.isEmpty()) {
            System.out.println("No items in wishlist to remove");
            return;
        }

        for (WebElement item : items) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", item);
            wait.until(ExpectedConditions.elementToBeClickable(item)).click();
        }

        WebElement update = wait.until(ExpectedConditions.elementToBeClickable(updateWishlist));
        update.click();
    }

    // Category
    public void shopByCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(categoryLink)).click();
    }

    // Currency
    public void changeCurrency(String currency) {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(currencyDropdown));
        Select select = new Select(dropdown);
        select.selectByVisibleText(currency);
    }

    // Simple search without adding
    public void searchProduct(String product) {
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(product);
        driver.findElement(searchBox).sendKeys(Keys.ENTER);
    }

   
 // Community Poll - vote for an option
    public void voteInCommunityPoll(String optionText) {
        // ensure we’re on homepage
        driver.get("https://demo.nopcommerce.com/");

        // wait for poll options
        List<WebElement> options = wait.until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("input[name*='pollanswers']")
            )
        );

        boolean optionFound = false;
        for (WebElement option : options) {
            String label = option.findElement(By.xpath("./following-sibling::label")).getText().trim();
            if (label.equalsIgnoreCase(optionText)) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
                wait.until(ExpectedConditions.elementToBeClickable(option)).click();
                optionFound = true;
                break;
            }
        }
        if (!optionFound) {
            throw new RuntimeException("Poll option '" + optionText + "' not found!");
        }

        WebElement vote = wait.until(ExpectedConditions.elementToBeClickable(By.id("vote-poll-1")));
        vote.click();

        // now wait for either result OR error bar
        By pollResults = By.cssSelector("div.poll-results");
        By pollError = By.cssSelector("div.poll-vote-error");

        try {
            wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(pollResults),
                ExpectedConditions.visibilityOfElementLocated(pollError)
            ));
        } catch (Exception e) {
            throw new RuntimeException("Neither poll results nor error message appeared after voting.");
        }

        if (driver.findElements(pollResults).size() > 0) {
            System.out.println("Community poll voted successfully for: " + optionText);
        } else {
            String error = driver.findElement(pollError).getText();
            System.out.println("Poll error: " + error);
        }
    }
    
    //prodDescription
 // go to homepage
    public void openHomePage() {
        driver.get("https://demo.nopcommerce.com/");
    }

    // search for a product
    public void searchProduct1(String product) {
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        search.clear();
        search.sendKeys(product);
        search.sendKeys(Keys.ENTER);
    }

    // click on a product in the search results
    public void clickOnProduct(String productName) {
        // robust locator with contains text
        By productLocator = By.xpath("//h2[@class='product-title']/a[contains(normalize-space(.),'" + productName + "')]");

        WebElement productLink = wait.until(
                ExpectedConditions.visibilityOfElementLocated(productLocator));

        // scroll to link
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productLink);

        wait.until(ExpectedConditions.elementToBeClickable(productLink)).click();
    }

    // get the product description text on the product details page
    public String getProductDescription() {
        By descLocator = By.cssSelector("div.full-description"); // nopCommerce product description container
        WebElement desc = wait.until(ExpectedConditions.visibilityOfElementLocated(descLocator));
        return desc.getText();
    }

    // close browser at end
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    }



