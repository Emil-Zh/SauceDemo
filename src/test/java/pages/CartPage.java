package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    private final By TITLE = By.xpath("//span[@class = 'title']");
    private final By CONTINUE_SHOPPING_BUTTON = By.xpath(
            "//button[@class = 'btn btn_secondary back btn_medium']");
    private final By CHECKOUT_BUTTON = By.xpath(
            "//button[@class = 'btn btn_action btn_medium checkout_button ']");
    private final String REMOVE_BUTTON_PATTERN = "//div[text() = '%s']" +
            "/ancestor::div[@class = 'cart_item']//button";

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("удалить продукт из корзины")
    public void removeProduct(String productName) {
        By removeButton = By.xpath(String.format(REMOVE_BUTTON_PATTERN, productName));
        driver.findElement(removeButton).click();
    }

    @Step("нажать на кнопку continue shopping")
    public void continueShopping() {
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    @Step("нажать на кнопку checkout")
    public void proceedToCheckout() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    @Step("получить список продуктов в корзине")
    public List<Product> getProductsInTheCart() {
        List<Product> productsInTheCart = new ArrayList<>();
        List<WebElement> products = driver.findElements(By.className("cart_item"));
        for (WebElement product : products) {
            String name = product.findElement(By.className("inventory_item_name")).getText();
            String description = product.findElement(By.className("inventory_item_desc")).getText();
            String price = product.findElement(By.className("inventory_item_price")).getText();
            productsInTheCart.add(new Product(name, description, price));
        }
        return productsInTheCart;
    }
    @Step("проверить, что корзина пуста")
    public boolean isEmpty(){
        return getProductsInTheCart().isEmpty();
    }
}
