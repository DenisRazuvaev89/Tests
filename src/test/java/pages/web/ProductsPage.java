package pages.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Page Object for SauceDemo Products/Inventory Page
 */
public class ProductsPage extends BasePage {

    // Locators
    private final By pageTitle = By.className("title");
    private final By productItems = By.className("inventory_item");
    private final By productNames = By.className("inventory_item_name");
    private final By productPrices = By.className("inventory_item_price");
    private final By addToCartButtons = By.cssSelector("[data-test^='add-to-cart']");
    private final By removeButtons = By.cssSelector("[data-test^='remove']");
    private final By shoppingCartBadge = By.className("shopping_cart_badge");
    private final By shoppingCartLink = By.className("shopping_cart_link");
    private final By sortDropdown = By.className("product_sort_container");
    private final By burgerMenuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");
    private final By menuCloseButton = By.id("react-burger-cross-btn");

    // Sort options
    private final By sortAZ = By.cssSelector("option[value='az']");
    private final By sortZA = By.cssSelector("option[value='za']");
    private final By sortLowHigh = By.cssSelector("option[value='lohi']");
    private final By sortHighLow = By.cssSelector("option[value='hilo']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitleText() {
        return getText(pageTitle);
    }

    public boolean isProductsPageDisplayed() {
        return isElementDisplayed(pageTitle) && getText(pageTitle).equals("Products");
    }

    public int getProductCount() {
        return waitForElementsVisible(productItems).size();
    }

    public List<String> getAllProductNames() {
        return waitForElementsVisible(productNames)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<Double> getAllProductPrices() {
        return waitForElementsVisible(productPrices)
                .stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(Collectors.toList());
    }

    public void addProductToCartByIndex(int index) {
        List<WebElement> buttons = waitForElementsVisible(addToCartButtons);
        if (index < buttons.size()) {
            buttons.get(index).click();
        }
    }

    public void addProductToCartByName(String productName) {
        String dataTestId = "add-to-cart-" + productName.toLowerCase().replace(" ", "-");
        click(By.cssSelector("[data-test='" + dataTestId + "']"));
    }

    public void removeProductFromCartByName(String productName) {
        String dataTestId = "remove-" + productName.toLowerCase().replace(" ", "-");
        click(By.cssSelector("[data-test='" + dataTestId + "']"));
    }

    public int getCartItemCount() {
        try {
            return Integer.parseInt(getText(shoppingCartBadge));
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isCartBadgeDisplayed() {
        return isElementPresent(shoppingCartBadge);
    }

    public CartPage goToCart() {
        click(shoppingCartLink);
        return new CartPage(driver);
    }

    public void sortByNameAZ() {
        click(sortDropdown);
        click(sortAZ);
    }

    public void sortByNameZA() {
        click(sortDropdown);
        click(sortZA);
    }

    public void sortByPriceLowToHigh() {
        click(sortDropdown);
        click(sortLowHigh);
    }

    public void sortByPriceHighToLow() {
        click(sortDropdown);
        click(sortHighLow);
    }

    public void openBurgerMenu() {
        click(burgerMenuButton);
    }

    public void closeBurgerMenu() {
        click(menuCloseButton);
    }

    public LoginPage logout() {
        openBurgerMenu();
        click(logoutLink);
        return new LoginPage(driver);
    }

    public ProductDetailPage clickOnProduct(String productName) {
        for (WebElement element : waitForElementsVisible(productNames)) {
            if (element.getText().equals(productName)) {
                element.click();
                return new ProductDetailPage(driver);
            }
        }
        throw new RuntimeException("Product not found: " + productName);
    }

    public boolean isProductDisplayed(String productName) {
        return getAllProductNames().contains(productName);
    }
}
