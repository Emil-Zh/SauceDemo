package pages;

import org.openqa.selenium.WebDriver;

public class CheckOutPage extends BasePage {

    private final String URL = "https://www.saucedemo.com/checkout-step-one.html";

    public CheckOutPage(WebDriver driver) {
        super(driver);
    }

    public String getURL() {
        return URL;
    }


}
