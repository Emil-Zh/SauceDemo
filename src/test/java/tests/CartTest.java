package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CartPage;
import pages.ProductsPage;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class CartTest extends BaseTest{

    @Test
    public void verifyCart() {
        String productName = "Sauce Labs Bike Light";

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton(productName);
        productsPage.goToCart();
        String product = driver.findElement(By.cssSelector(".inventory_item_name")).getText();
        assertEquals(productName, product);
    }

    @Test
    public void verifyAddAndRemoveFromCart(){
        String[] productNames = {"Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt", "Sauce Labs Onesie"};
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        for(String productName : productNames){
            productsPage.clickAddButton(productName);
        }

        productsPage.goToCart();
        softAssert.assertEquals(cartPage.getProductsInTheCart(), productsPage.getAddedProducts(), "продукты в корзине отличаются от добавленных");
        for(String productName : productNames){
            cartPage.removeProduct(productName);
        }
        softAssert.assertTrue(cartPage.isEmpty(), "Корзина не пуста");
        softAssert.assertAll();
    }

    @Test
    public void verifyAddToCart(){
        String productName = "Sauce Labs Bike Light";

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton(productName);

        productsPage.goToCart();
        assertEquals("продукты в корзине отличаются от добавленных", productsPage.getAddedProducts(),cartPage.getProductsInTheCart());
    }

    @Test
    public void verifyAddMultipleProductToCart(){
        String[] productNames = {"Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt", "Sauce Labs Onesie"};

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        for(String productName : productNames){
            productsPage.clickAddButton(productName);
        }

        productsPage.goToCart();
        assertEquals("продукты в корзине отличаются от добавленны", cartPage.getProductsInTheCart(), productsPage.getAddedProducts());
    }

    @Test
    public void verifyRemoveProductsFromCart(){
        String productName = "Sauce Labs Bike Light";

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton(productName);

        productsPage.goToCart();
        cartPage.removeProduct(productName);
        Assert.assertTrue(cartPage.isEmpty(),"Корзина не пуста");
    }

    @Test
    public void verifyRemoveMultipleProductsFromCart(){
        String[] productNames = {"Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt", "Sauce Labs Onesie"};

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        for(String productName : productNames){
            productsPage.clickAddButton(productName);
        }

        productsPage.goToCart();
        for(String productName : productNames){
            cartPage.removeProduct(productName);
        }
        Assert.assertTrue(cartPage.isEmpty(),"Корзина не пуста");
    }

    @Test
    public void verifyShoppingBadge(){
        String productName = "Sauce Labs Bike Light";
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton(productName);
        assertEquals(Integer.toString(productsPage.getAddedProducts().size()), productsPage.getShoppingCartBadgeText());
    }

    @Test
    public void verifyAddToRemoveButtonChange(){
        String productName = "Sauce Labs Bike Light";

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton(productName);
        productsPage.getButtonName(productName);
        assertEquals("Кнопка Remove не появилась", "Remove", productsPage.getButtonName(productName));

    }

    @Test
    public void verifyContinueToShoppingButtonFunctionality(){
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.goToCart();
        cartPage.continueShopping();
        assertEquals("Кнопка continue to shopping ведет на неверную страницу", productsPage.getURL(), driver.getCurrentUrl());
    }

    @Test
    public void verifyCheckoutButtonFunctionality(){
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.goToCart();
        cartPage.proceedToCheckout();
        assertEquals("Кнопка checkout ведет на неверную страницу", checkOutPage.getURL(), driver.getCurrentUrl());
    }
}
