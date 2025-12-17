package tests.web;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.web.CartPage;
import pages.web.ProductsPage;

import java.util.List;

public class CartTests extends BaseWebTest {
    private ProductsPage productsPage;

    @BeforeMethod
    public void loginAndNavigate() {
        productsPage = loginPage.login(VALID_USERNAME, VALID_PASSWORD);
    }

    @Test
    public void testAddSingleProductToCart() {
        productsPage.addProductToCartByIndex(0);

        Assert.assertEquals(productsPage.getCartItemCount(), 1);
    }

    @Test
    public void testAddMultipleProductsToCart() {
        productsPage.addProductToCartByIndex(0);
        productsPage.addProductToCartByIndex(1);
        productsPage.addProductToCartByIndex(2);

        Assert.assertEquals(productsPage.getCartItemCount(), 3);
    }

    @Test
    public void testRemoveProductFromCart() {
        String productName = productsPage.getAllProductNames().get(0);
        productsPage.addProductToCartByName(productName);
        Assert.assertEquals(productsPage.getCartItemCount(), 1);

        productsPage.removeProductFromCartByName(productName);
        Assert.assertFalse(productsPage.isCartBadgeDisplayed());
    }

    @Test
    public void testCartPageDisplaysAddedProducts() {
        List<String> productNames = productsPage.getAllProductNames();
        String firstProduct = productNames.get(0);
        String secondProduct = productNames.get(1);

        productsPage.addProductToCartByName(firstProduct);
        productsPage.addProductToCartByName(secondProduct);

        CartPage cartPage = productsPage.goToCart();

        Assert.assertTrue(cartPage.isCartPageDisplayed());
        Assert.assertEquals(cartPage.getCartItemCount(), 2);
        Assert.assertTrue(cartPage.isProductInCart(firstProduct));
        Assert.assertTrue(cartPage.isProductInCart(secondProduct));
    }

    @Test
    public void testRemoveProductFromCartPage() {
        String productName = productsPage.getAllProductNames().get(0);
        productsPage.addProductToCartByName(productName);

        CartPage cartPage = productsPage.goToCart();
        Assert.assertTrue(cartPage.isProductInCart(productName));

        cartPage.removeItemByName(productName);
        Assert.assertTrue(cartPage.isCartEmpty());
    }

    @Test
    public void testContinueShoppingFromCart() {
        productsPage.addProductToCartByIndex(0);
        CartPage cartPage = productsPage.goToCart();

        productsPage = cartPage.continueShopping();

        Assert.assertTrue(productsPage.isProductsPageDisplayed());
    }

    @Test
    public void testCartPersistsAfterNavigation() {
        productsPage.addProductToCartByIndex(0);
        CartPage cartPage = productsPage.goToCart();
        productsPage = cartPage.continueShopping();

        Assert.assertEquals(productsPage.getCartItemCount(), 1);
    }
}
