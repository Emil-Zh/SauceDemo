package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
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

    public void addProduct(String productName) {
        By productTitle = By.xpath(String.format(PRODUCT_TITLE_PATTERN, productName));
        By productDesc = By.xpath(String.format(PRODUCT_DESCRIPTION_PATTERN, productName));
        By productPrice = By.xpath(String.format(PRODUCT_PRICE_PATTERN, productName));
        addedProducts.add(new Product(
                driver.findElement(productTitle).getText(),
                driver.findElement(productDesc).getText(),
                driver.findElement(productPrice).getText()));
    }

    public void clickAddButton(String productName) {
        By addToCart = By.xpath(String.format(ADD_REMOVE_TO_CART_PATTERN, productName));
        driver.findElement(addToCart).click();
        addProduct(productName);

    }

    public void clickRemoveButton(String productName) {
        By removeFromCart = By.xpath(String.format(ADD_REMOVE_TO_CART_PATTERN, productName));
        driver.findElement(removeFromCart).click();
        removeProduct(productName);
    }

    public String getButtonName(String productName){
        By removeFromCart = By.xpath(String.format(ADD_REMOVE_TO_CART_PATTERN, productName));
        return driver.findElement(removeFromCart).getText();
    }

    public List<Product> getAddedProducts() {
        return addedProducts;
    }

    public String getURL() {
        return URL;
    }

    public String getShoppingCartBadgeText() {
        return driver.findElement(CART_SHOPPING_BADGE).getText();
    }

    public String getTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return driver.findElement(TITLE).getText();
    }

    public void goToCart() {
        driver.findElement(CART_LINK).click();
    }

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

    public boolean isSortedByPrice() {
        List<WebElement> productPrices = driver.findElements(PRODUCT_PRICE);
        List<Double> prices = new ArrayList<>();
        for (WebElement price : productPrices) {
            System.out.println(price.getText());
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

    public void removeProduct(String productName) {
        for (Product product : addedProducts) {
            if (Objects.equals(product.getName(), productName)) {
                addedProducts.remove(product);
            }
        }
    }

}