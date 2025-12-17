package pages.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for SauceDemo Product Detail Page
 */
public class ProductDetailPage extends BasePage {

    // Locators
    private final By productName = By.className("inventory_details_name");
    private final By productDescription = By.className("inventory_details_desc");
    private final By productPrice = By.className("inventory_details_price");
    private final By productImage = By.className("inventory_details_img");
    private final By addToCartButton = By.cssSelector("[data-test^='add-to-cart']");
    private final By removeButton = By.cssSelector("[data-test^='remove']");
    private final By backButton = By.id("back-to-products");
    private final By shoppingCartBadge = By.className("shopping_cart_badge");

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return getText(productName);
    }

    public String getProductDescription() {
        return getText(productDescription);
    }

    public String getProductPrice() {
        return getText(productPrice);
    }

    public double getProductPriceAsDouble() {
        return Double.parseDouble(getProductPrice().replace("$", ""));
    }

    public boolean isProductImageDisplayed() {
        return isElementDisplayed(productImage);
    }

    public void addToCart() {
        click(addToCartButton);
    }

    public void removeFromCart() {
        click(removeButton);
    }

    public boolean isAddToCartButtonDisplayed() {
        return isElementDisplayed(addToCartButton);
    }

    public boolean isRemoveButtonDisplayed() {
        return isElementPresent(removeButton);
    }

    public ProductsPage goBackToProducts() {
        click(backButton);
        return new ProductsPage(driver);
    }

    public int getCartItemCount() {
        try {
            return Integer.parseInt(getText(shoppingCartBadge));
        } catch (Exception e) {
            return 0;
        }
    }
}
