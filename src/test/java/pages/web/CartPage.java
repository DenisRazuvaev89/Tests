package pages.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Page Object for SauceDemo Shopping Cart Page
 */
public class CartPage extends BasePage {

    // Locators
    private final By pageTitle = By.className("title");
    private final By cartItems = By.className("cart_item");
    private final By cartItemNames = By.className("inventory_item_name");
    private final By cartItemPrices = By.className("inventory_item_price");
    private final By cartItemQuantities = By.className("cart_quantity");
    private final By removeButtons = By.cssSelector("[data-test^='remove']");
    private final By continueShoppingButton = By.id("continue-shopping");
    private final By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitleText() {
        return getText(pageTitle);
    }

    public boolean isCartPageDisplayed() {
        return isElementDisplayed(pageTitle) && getText(pageTitle).equals("Your Cart");
    }

    public int getCartItemCount() {
        try {
            return waitForElementsVisible(cartItems).size();
        } catch (Exception e) {
            return 0;
        }
    }

    public List<String> getCartItemNames() {
        return waitForElementsVisible(cartItemNames)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<Double> getCartItemPrices() {
        return waitForElementsVisible(cartItemPrices)
                .stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(Collectors.toList());
    }

    public boolean isProductInCart(String productName) {
        return getCartItemNames().contains(productName);
    }

    public void removeItemByName(String productName) {
        String dataTestId = "remove-" + productName.toLowerCase().replace(" ", "-");
        click(By.cssSelector("[data-test='" + dataTestId + "']"));
    }

    public void removeItemByIndex(int index) {
        List<WebElement> buttons = waitForElementsVisible(removeButtons);
        if (index < buttons.size()) {
            buttons.get(index).click();
        }
    }

    public ProductsPage continueShopping() {
        click(continueShoppingButton);
        return new ProductsPage(driver);
    }

    public CheckoutPage proceedToCheckout() {
        click(checkoutButton);
        return new CheckoutPage(driver);
    }

    public boolean isCartEmpty() {
        return !isElementPresent(cartItems);
    }

    public double getTotalPrice() {
        return getCartItemPrices().stream().mapToDouble(Double::doubleValue).sum();
    }
}
