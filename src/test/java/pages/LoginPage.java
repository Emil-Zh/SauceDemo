package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By USER_INPUT = By.id("user-name");
    private final By PASSWORD_INPUT = By.id("password");
    private final By LOGIN_BUTTON = By.id("login-button");
    private final By ERROR_MESSAGE = By.xpath("//h3[@data-test = 'error']");

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public void open(){
        driver.get("https://www.saucedemo.com");
    }

    public void login(String user, String password){
        driver.findElement(USER_INPUT).sendKeys(user);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    public String getErrorMessage(){
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}
