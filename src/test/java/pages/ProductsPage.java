package pages;

import io.qameta.allure.Step; // Импорт аннотации Step
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductsPage extends BasePage {

    private List<Product> addedProducts = new ArrayList<>();

    private final By TITLE = By.cssSelector(".title");
    private final By CART_LINK = By.cssSelector(".shopping_cart_link");
    private final By CART_SHOPPING_BADGE = By.cssSelector(".shopping_cart_badge");
    private final By PRODUCTS_NAME = By.cssSelector(".inventory_item_name");
    private final By PRODUCT_PRICE = By.cssSelector(".inventory_item_price");
    private final String URL = "https://www.saucedemo.com/inventory.html";
    private final String ADD_REMOVE_TO_CART_PATTERN = "//div[text() = '%s']" +
            "/ancestor::div[@class = 'inventory_item']//button";
    private final String PRODUCT_TITLE_PATTERN = "//div[text() = '%s']";
    private final String PRODUCT_DESCRIPTION_PATTERN = "//div[text() = '%s']" +
            "/ancestor::div[@class = 'inventory_item_label']/div[@class = 'inventory_item_desc']";
    private final String PRODUCT_PRICE_PATTERN = "//div[text() = '%s']/" +
            "ancestor::div[@class = 'inventory_item_description']//div[@class = 'inventory_item_price']";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Добавить продукт в корзину: {productName}")
    public void addProduct(String productName) {
        By productTitle = By.xpath(String.format(PRODUCT_TITLE_PATTERN, productName));
        By productDesc = By.xpath(String.format(PRODUCT_DESCRIPTION_PATTERN, productName));
        By productPrice = By.xpath(String.format(PRODUCT_PRICE_PATTERN, productName));
        addedProducts.add(new Product(
                driver.findElement(productTitle).getText(),
                driver.findElement(productDesc).getText(),
                driver.findElement(productPrice).getText()));
    }

    @Step("Нажать кнопку добавления в корзину для продукта: {productName}")
    public void clickAddButton(String productName) {
        By addToCart = By.xpath(String.format(ADD_REMOVE_TO_CART_PATTERN, productName));
        driver.findElement(addToCart).click();
        addProduct(productName);
    }

    @Step("Нажать кнопку удаления из корзины для продукта: {productName}")
    public void clickRemoveButton(String productName) {
        By removeFromCart = By.xpath(String.format(ADD_REMOVE_TO_CART_PATTERN, productName));
        driver.findElement(removeFromCart).click();
        removeProduct(productName);
    }

    @Step("Получить имя кнопки для продукта: {productName}")
    public String getButtonName(String productName){
        By removeFromCart = By.xpath(String.format(ADD_REMOVE_TO_CART_PATTERN, productName));
        return driver.findElement(removeFromCart).getText();
    }

    @Step("Получить список добавленных продуктов")
    public List<Product> getAddedProducts() {
        return addedProducts;
    }

    @Step("Получить URL страницы товаров")
    public String getURL() {
        return URL;
    }

    @Step("Получить текст на бейджике корзины")
    public String getShoppingCartBadgeText() {
        return driver.findElement(CART_SHOPPING_BADGE).getText();
    }

    @Step("Получить заголовок страницы продуктов")
    public String getTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return driver.findElement(TITLE).getText();
    }

    @Step("Перейти в корзину")
    public void goToCart() {
        driver.findElement(CART_LINK).click();
    }

    @Step("Проверить, что продукты отсортированы по алфавиту")
    public boolean isSortedAlphabetically() {
        List<WebElement> productList = driver.findElements(PRODUCTS_NAME);
        String previousProductName = "";
        for (WebElement product : productList) {
            String currentProductName = product.getText();
            if (currentProductName.compareToIgnoreCase(previousProductName) < 0) {
                return false;
            }
            previousProductName = currentProductName;
        }
        return true;
    }

    @Step("Проверить, что продукты отсортированы по цене")
    public boolean isSortedByPrice() {
        List<WebElement> productPrices = driver.findElements(PRODUCT_PRICE);
        List<Double> prices = new ArrayList<>();
        for (WebElement price : productPrices) {
            String priceText = price.getText().replace("$", "");
            prices.add(Double.parseDouble(priceText));
        }
        for (int i = 1; i < prices.size(); i++) {
            if (prices.get(i) < prices.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    @Step("Удалить продукт из списка добавленных: {productName}")
    public void removeProduct(String productName) {
        for (Product product : addedProducts) {
            if (Objects.equals(product.getName(), productName)) {
                addedProducts.remove(product);
                break;
            }
        }
    }
}

