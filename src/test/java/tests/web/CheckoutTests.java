package tests.web;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.web.CartPage;
import pages.web.CheckoutPage;
import pages.web.ProductsPage;

public class CheckoutTests extends BaseWebTest {
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void loginAndAddProduct() {
        productsPage = loginPage.login(VALID_USERNAME, VALID_PASSWORD);
        productsPage.addProductToCartByIndex(0);
        cartPage = productsPage.goToCart();
        checkoutPage = cartPage.proceedToCheckout();
    }

    @Test
    public void testCheckoutStepOneDisplayed() {
        Assert.assertTrue(checkoutPage.isCheckoutStepOneDisplayed());
    }

    @Test
    public void testSuccessfulCheckout() {
        checkoutPage.fillCheckoutInfo("John", "Doe", "12345");
        checkoutPage.clickContinue();

        Assert.assertTrue(checkoutPage.isCheckoutOverviewDisplayed());

        checkoutPage.clickFinish();

        Assert.assertTrue(checkoutPage.isOrderComplete());
        Assert.assertEquals(checkoutPage.getCompleteHeader(), "Thank you for your order!");
    }

    @Test
    public void testCheckoutWithEmptyFirstName() {
        checkoutPage.fillCheckoutInfo("", "Doe", "12345");
        checkoutPage.clickContinue();

        Assert.assertTrue(checkoutPage.isErrorDisplayed());
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("First Name is required"));
    }

    @Test
    public void testCheckoutWithEmptyLastName() {
        checkoutPage.fillCheckoutInfo("John", "", "12345");
        checkoutPage.clickContinue();

        Assert.assertTrue(checkoutPage.isErrorDisplayed());
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("Last Name is required"));
    }

    @Test
    public void testCheckoutWithEmptyPostalCode() {
        checkoutPage.fillCheckoutInfo("John", "Doe", "");
        checkoutPage.clickContinue();

        Assert.assertTrue(checkoutPage.isErrorDisplayed());
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("Postal Code is required"));
    }

    @Test
    public void testCancelCheckout() {
        cartPage = checkoutPage.clickCancel();

        Assert.assertTrue(cartPage.isCartPageDisplayed());
    }

    @Test
    public void testCheckoutOverviewPriceCalculation() {
        checkoutPage.fillCheckoutInfo("John", "Doe", "12345");
        checkoutPage.clickContinue();

        double subtotal = checkoutPage.getSubtotalValue();
        double tax = checkoutPage.getTaxValue();
        double total = checkoutPage.getTotalValue();

        Assert.assertEquals(total, subtotal + tax, 0.01);
    }

    @Test
    public void testBackToHomeAfterCheckout() {
        checkoutPage.fillCheckoutInfo("John", "Doe", "12345");
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();

        productsPage = checkoutPage.clickBackHome();

        Assert.assertTrue(productsPage.isProductsPageDisplayed());
    }

    @DataProvider(name = "checkoutData")
    public Object[][] checkoutDataProvider() {
        return new Object[][] {
            {"Ivan", "Petrov", "101000"},
            {"Anna", "Sidorova", "190000"},
            {"Test", "User", "00000"}
        };
    }

    @Test(dataProvider = "checkoutData")
    public void testCheckoutWithDifferentData(String firstName, String lastName, String postalCode) {
        checkoutPage.fillCheckoutInfo(firstName, lastName, postalCode);
        checkoutPage.clickContinue();

        Assert.assertTrue(checkoutPage.isCheckoutOverviewDisplayed());
    }
}
