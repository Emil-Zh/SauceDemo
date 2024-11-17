package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LocatorTest extends BaseTest {

    @Test
    public void locatorTest() {
        driver.get("https://www.saucedemo.com");
        driver.findElements(By.id("user-name"));
        driver.findElements(By.name("login-button"));
        driver.findElements(By.className("submit-button"));
        driver.findElements(By.tagName("div"));
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@placeholder = 'Password']")).sendKeys("secret_sauce");
        driver.findElement(By.name("login-button")).click();
        driver.findElement(By.linkText("Sauce Labs Bike Light"));
        driver.findElement(By.partialLinkText("Light"));
        driver.findElement(By.xpath("//span[ @data-test='title']"));
        driver.findElement(By.xpath("//span[text()=\"Products\"]"));
        driver.findElement(By.xpath("//*[contains(@class,\"title\")]"));
        driver.findElement(By.xpath("//*[contains(text(),\"streamlined \")]"));
        driver.findElement(By.xpath("//*[contains" +
                "(text(),\"streamlined \")]/ancestor::div[@class = \"inventory_item_description\"]"));
        driver.findElement(By.xpath("//div[@class ='inventory_item_description'" +
                " and @data-test = 'inventory-item-description']" +
                "/descendant::a[@data-test = \"item-4-title-link\"]"));
        driver.findElement(By.xpath("//*[@class = \"inventory_item_description\"]" +
                "/following::a[@id = \"item_0_title_link\"]"));
        driver.findElement(By.xpath("//*[@id = \"item_0_title_link\"]" +
                "//parent::div[@class = 'inventory_item_label']"));
        driver.findElement(By.xpath("//a[@id = \"item_0_title_link\"]" +
                "//preceding::div[@class = 'inventory_item_description']"));
        driver.findElements(By.cssSelector(".btn.btn_primary"));
        driver.findElements(By.cssSelector(".inventory_item_description .inventory_item_desc"));
        driver.findElements(By.tagName("a"));
        driver.findElements(By.cssSelector("div.inventory_item_name "));
        driver.findElement(By.cssSelector("a[id = 'item_4_title_link'"));
        driver.findElements(By.cssSelector("button[class~=\"btn\"]"));
        driver.findElements(By.cssSelector("div[data-test|='inventory']"));
        driver.findElements(By.cssSelector("a[id^='item']"));
        driver.findElements(By.cssSelector("a[id$='link']"));
        driver.findElements(By.cssSelector("a[id*='item']"));
    }


}
