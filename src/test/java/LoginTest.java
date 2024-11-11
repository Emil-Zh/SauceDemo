import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {
    @Test
    public void checkLogin() {
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.name("login-button")).click();
        String title = driver.findElement(By.cssSelector(".title")).getText();
        assertEquals(title, "Products", "переход на страницу не выполнен");
    }

    @Test
    public void noPassowrd(){
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.name("login-button")).click();
        String errorText = driver.findElement(By.cssSelector("[data-test = error")).getText().trim();
        assertEquals(errorText, "Epic sadface: Password is required", "переход на страницу не выполнен");
    }

    @Test
    public void noLogin(){
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.name("login-button")).click();
        String errorText = driver.findElement(By.cssSelector("[data-test = error")).getText().trim();
        assertEquals(errorText, "Epic sadface: Username is required", "переход на страницу не выполнен");
    }

    @Test
    public void incorrectPasswordLogin(){
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.name("login-button")).click();
        String errorText = driver.findElement(By.cssSelector("[data-test = error")).getText().trim();
        assertEquals(errorText, "Epic sadface: Username and password do not match any user in this service", "переход на страницу не выполнен");
    }
}
