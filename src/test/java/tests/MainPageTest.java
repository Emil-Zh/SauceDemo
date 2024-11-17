package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;


public class MainPageTest extends BaseTest{

    @Test
    public void verifyProductsAreSortedByAlphabet() throws InterruptedException {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        WebElement sortDropdown = driver.findElement(By.cssSelector(".product_sort_container"));
        Select sortSelect = new Select(sortDropdown);
        sortSelect.selectByVisibleText("Name (A to Z)");
        Thread.sleep(2000);

        Assert.assertTrue(productsPage.isSortedAlphabetically(), "продукты отсортированы неверно, долнжо быть от A-Z");
    }

    @Test
    public void verifyProductsAreSortedReverseByAlphabet() throws InterruptedException {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        WebElement sortDropdown = driver.findElement(By.cssSelector(".product_sort_container"));
        Select sortSelect = new Select(sortDropdown);
        sortSelect.selectByVisibleText("Name (Z to A)");
        Thread.sleep(2000);

        Assert.assertFalse(productsPage.isSortedAlphabetically(), "продукты отсортированы неверно, долнжо быть от Z-A");
    }

    @Test
    public void veryfyProductAreSortedByPrice() throws InterruptedException{
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        WebElement sortDropdown = driver.findElement(By.cssSelector(".product_sort_container"));
        Select sortSelect = new Select(sortDropdown);
        sortSelect.selectByVisibleText("Price (low to high)");
        Thread.sleep(2000);

        Assert.assertTrue(productsPage.isSortedByPrice(), "продукты отсортированы неверно, должны быть от низкой цене к высокой");
    }

    @Test
    public void veryfyProductAreSortedReverseByPrice() throws InterruptedException{
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        WebElement sortDropdown = driver.findElement(By.cssSelector(".product_sort_container"));
        Select sortSelect = new Select(sortDropdown);
        sortSelect.selectByVisibleText("Price (high to low)");
        Thread.sleep(2000);

        Assert.assertFalse(productsPage.isSortedByPrice(), "продукты отсортированы неверно, должны быть от высокой цене к низкой");
    }
}
