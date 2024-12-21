package tests;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.AssertJUnit.assertEquals;

public class CartTest extends BaseTest {

    @Test(dataProvider = "CartDataOneProduct", testName = "Проверка работы корзины", description = "Проверка работы корзины")
    @Feature("Корзина")
    @Story("Добавление одного товара в корзину и проверка его наличия")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TMS-12345")
    public void verifyCart(String productName) {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton(productName);
        productsPage.goToCart();
        String product = driver.findElement(By.cssSelector(".inventory_item_name")).getText();
        assertEquals(productName, product);
    }

    @Test(dataProvider = "CartDataMultipleProducts", testName = "Проверка добавления и удаления из корзины", description = "Проверка добавления и удаления из корзины")
    @Feature("Корзина")
    @Story("Добавление и удаление нескольких товаров из корзины")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyAddAndRemoveFromCart(String[] productNames) {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        for (String productName : productNames) {
            productsPage.clickAddButton(productName);
        }

        productsPage.goToCart();
        softAssert.assertEquals(cartPage.getProductsInTheCart(), productsPage.getAddedProducts(), "Продукты в корзине отличаются от добавленных");
        for (String productName : productNames) {
            cartPage.removeProduct(productName);
        }
        softAssert.assertTrue(cartPage.isEmpty(), "Корзина не пуста");
        softAssert.assertAll();
    }

    @Test(dataProvider = "CartDataOneProduct", testName = "Проверка добавления одного товара в корзину", description = "Проверка добавления одного товара в корзину")
    @Feature("Корзина")
    @Story("Добавление одного товара в корзину")
    @Severity(SeverityLevel.NORMAL)
    public void verifyAddToCart() {
        String productName = "Sauce Labs Bike Light";

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton(productName);

        productsPage.goToCart();
        assertEquals("Продукты в корзине отличаются от добавленных", productsPage.getAddedProducts(), cartPage.getProductsInTheCart());
    }

    @Test(
            dataProvider = "CartDataMultipleProducts",
            testName = "Проверка добавления нескольких товаров в корзину",
            description = "Проверка добавления нескольких товаров в корзину")
    @Feature("Корзина")
    @Story("Добавление нескольких товаров в корзину")
    @Severity(SeverityLevel.NORMAL)
    public void verifyAddMultipleProductToCart(String[] productNames) {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        for (String productName : productNames) {
            productsPage.clickAddButton(productName);
        }

        productsPage.goToCart();
        assertEquals("Продукты в корзине отличаются от добавленных", cartPage.getProductsInTheCart(), productsPage.getAddedProducts());
    }

    @Test(dataProvider = "CartDataOneProduct",
            testName = "Проверка удаления товара из корзины",
            description = "Проверка удаления товара из корзины")
    @Feature("Корзина")
    @Story("Удаление товара из корзины")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyRemoveProductsFromCart(String productName) {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton(productName);

        productsPage.goToCart();
        cartPage.removeProduct(productName);
        Assert.assertTrue(cartPage.isEmpty(), "Корзина не пуста");
    }

    @Test(dataProvider = "CartDataMultipleProducts",
            testName = "Проверка удаления нескольких товаров из корзины",
            description = "Проверка удаления нескольких товаров из корзины")
    @Feature("Корзина")
    @Story("Удаление нескольких товаров из корзины")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyRemoveMultipleProductsFromCart(String[] productNames) {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        for (String productName : productNames) {
            productsPage.clickAddButton(productName);
        }

        productsPage.goToCart();
        for (String productName : productNames) {
            cartPage.removeProduct(productName);
        }
        Assert.assertTrue(cartPage.isEmpty(), "Корзина не пуста");
    }

    @Test(dataProvider = "CartDataOneProduct",
            testName = "Проверка работы иконки корзины",
            description = "Проверка работы иконки корзины")
    @Feature("Корзина")
    @Story("Проверка отображения количества товаров на иконке корзины")
    @Severity(SeverityLevel.NORMAL)
    public void verifyShoppingBadge(String productName) {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton(productName);
        assertEquals(Integer.toString(productsPage.getAddedProducts().size()), productsPage.getShoppingCartBadgeText());
    }

    @Test(dataProvider = "CartDataOneProduct",
            testName = "Проверка смены кнопки Add на Remove",
            description = "Проверка смены кнопки Add на Remove")
    @Feature("Корзина")
    @Story("Проверка изменения кнопки добавления товара на удаление")
    @Severity(SeverityLevel.NORMAL)
    public void verifyAddToRemoveButtonChange(String productName) {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton(productName);
        productsPage.getButtonName(productName);
        assertEquals("Кнопка Remove не появилась", "Remove", productsPage.getButtonName(productName));
    }

    @Test(testName = "Проверка кнопки ContinueShopping",
            description = "Проверка кнопки ContinueShopping")
    @Feature("Корзина")
    @Story("Проверка функциональности кнопки Continue Shopping")
    @Severity(SeverityLevel.MINOR)
    public void verifyContinueToShoppingButtonFunctionality() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.goToCart();
        cartPage.continueShopping();
        assertEquals("Кнопка continue to shopping ведет на неверную страницу", productsPage.getURL(), driver.getCurrentUrl());
    }

    @Test(testName = "Проверка кнопки Checkout",
            description = "Проверка кнопки Checkout")
    @Feature("Корзина")
    @Story("Проверка функциональности кнопки Checkout")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyCheckoutButtonFunctionality() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.goToCart();
        cartPage.proceedToCheckout();
        assertEquals("Кнопка checkout ведет на неверную страницу", checkOutPage.getURL(), driver.getCurrentUrl());
    }

    @DataProvider(name = "CartDataOneProduct")
    public Object[][] cartDataOneProduct() {
        return new Object[][]{
                {"Sauce Labs Bike Light"}
        };
    }

    @DataProvider(name = "CartDataMultipleProducts")
    public Object[][] cartDataMultipleProducts() {
        return new Object[][]{
                {new String[]{"Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt", "Sauce Labs Onesie"}},
        };
    }

}

