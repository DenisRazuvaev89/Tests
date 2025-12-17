package tests.web;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.web.ProductsPage;
import pages.web.ProductDetailPage;

import java.util.List;

public class ProductsTests extends BaseWebTest {
    private ProductsPage productsPage;

    @BeforeMethod
    public void loginAndNavigate() {
        productsPage = loginPage.login(VALID_USERNAME, VALID_PASSWORD);
    }

    @Test
    public void testProductsDisplayed() {
        Assert.assertTrue(productsPage.isProductsPageDisplayed());
        Assert.assertTrue(productsPage.getProductCount() > 0);
    }

    @Test
    public void testSortProductsByNameAZ() {
        productsPage.sortByNameAZ();
        List<String> productNames = productsPage.getAllProductNames();

        for (int i = 0; i < productNames.size() - 1; i++) {
            Assert.assertTrue(productNames.get(i).compareToIgnoreCase(productNames.get(i + 1)) <= 0);
        }
    }

    @Test
    public void testSortProductsByNameZA() {
        productsPage.sortByNameZA();
        List<String> productNames = productsPage.getAllProductNames();

        for (int i = 0; i < productNames.size() - 1; i++) {
            Assert.assertTrue(productNames.get(i).compareToIgnoreCase(productNames.get(i + 1)) >= 0);
        }
    }

    @Test
    public void testSortProductsByPriceLowToHigh() {
        productsPage.sortByPriceLowToHigh();
        List<Double> prices = productsPage.getAllProductPrices();

        for (int i = 0; i < prices.size() - 1; i++) {
            Assert.assertTrue(prices.get(i) <= prices.get(i + 1));
        }
    }

    @Test
    public void testSortProductsByPriceHighToLow() {
        productsPage.sortByPriceHighToLow();
        List<Double> prices = productsPage.getAllProductPrices();

        for (int i = 0; i < prices.size() - 1; i++) {
            Assert.assertTrue(prices.get(i) >= prices.get(i + 1));
        }
    }

    @Test
    public void testOpenProductDetail() {
        String firstProductName = productsPage.getAllProductNames().get(0);
        ProductDetailPage detailPage = productsPage.clickOnProduct(firstProductName);

        Assert.assertEquals(detailPage.getProductName(), firstProductName);
        Assert.assertTrue(detailPage.isProductImageDisplayed());
        Assert.assertTrue(detailPage.isAddToCartButtonDisplayed());
    }

    @Test
    public void testNavigateBackFromProductDetail() {
        String firstProductName = productsPage.getAllProductNames().get(0);
        ProductDetailPage detailPage = productsPage.clickOnProduct(firstProductName);
        productsPage = detailPage.goBackToProducts();

        Assert.assertTrue(productsPage.isProductsPageDisplayed());
    }

    @Test
    public void testAddToCartFromProductDetail() {
        String firstProductName = productsPage.getAllProductNames().get(0);
        ProductDetailPage detailPage = productsPage.clickOnProduct(firstProductName);

        detailPage.addToCart();

        Assert.assertTrue(detailPage.isRemoveButtonDisplayed());
        Assert.assertEquals(detailPage.getCartItemCount(), 1);
    }
}
