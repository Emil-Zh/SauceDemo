package tests;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Тестирование страницы продуктов")
public class MainPageTest extends BaseTest {

    @Test
    @Feature("Сортировка продуктов")
    @Story("Проверка сортировки продуктов по алфавиту от A до Z")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет, что продукты отсортированы по алфавиту от A до Z в выпадающем меню сортировки.")
    public void verifyProductsAreSortedByAlphabet() throws InterruptedException {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        WebElement sortDropdown = driver.findElement(By.cssSelector(".product_sort_container"));
        Select sortSelect = new Select(sortDropdown);
        sortSelect.selectByVisibleText("Name (A to Z)");
        Thread.sleep(2000);

        Assert.assertTrue(productsPage.isSortedAlphabetically(), "Продукты отсортированы неверно, должно быть от A-Z");
    }

    @Test
    @Feature("Сортировка продуктов")
    @Story("Проверка сортировки продуктов по алфавиту от Z до A")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет, что продукты отсортированы по алфавиту от Z до A в выпадающем меню сортировки.")
    public void verifyProductsAreSortedReverseByAlphabet() throws InterruptedException {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        WebElement sortDropdown = driver.findElement(By.cssSelector(".product_sort_container"));
        Select sortSelect = new Select(sortDropdown);
        sortSelect.selectByVisibleText("Name (Z to A)");
        Thread.sleep(2000);

        Assert.assertFalse(productsPage.isSortedAlphabetically(), "Продукты отсортированы неверно, должно быть от Z-A");
    }

    @Test
    @Feature("Сортировка продуктов")
    @Story("Проверка сортировки продуктов по цене от низкой к высокой")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет, что продукты отсортированы по цене от низкой к высокой в выпадающем меню сортировки.")
    public void veryfyProductAreSortedByPrice() throws InterruptedException {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        WebElement sortDropdown = driver.findElement(By.cssSelector(".product_sort_container"));
        Select sortSelect = new Select(sortDropdown);
        sortSelect.selectByVisibleText("Price (low to high)");
        Thread.sleep(2000);

        Assert.assertTrue(productsPage.isSortedByPrice(), "Продукты отсортированы неверно, должны быть от низкой цены к высокой");
    }

    @Test
    @Feature("Сортировка продуктов")
    @Story("Проверка сортировки продуктов по цене от высокой к низкой")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет, что продукты отсортированы по цене от высокой к низкой в выпадающем меню сортировки.")
    public void veryfyProductAreSortedReverseByPrice() throws InterruptedException {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        WebElement sortDropdown = driver.findElement(By.cssSelector(".product_sort_container"));
        Select sortSelect = new Select(sortDropdown);
        sortSelect.selectByVisibleText("Price (high to low)");
        Thread.sleep(2000);

        Assert.assertFalse(productsPage.isSortedByPrice(), "Продукты отсортированы неверно, должны быть от высокой цены к низкой");
    }
}
