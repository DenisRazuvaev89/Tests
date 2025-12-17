package tests.web;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.web.ProductsPage;

public class LoginTests extends BaseWebTest {

    @Test
    public void testSuccessfulLogin() {
        ProductsPage productsPage = loginPage.login(VALID_USERNAME, VALID_PASSWORD);

        Assert.assertTrue(productsPage.isProductsPageDisplayed());
        Assert.assertEquals(productsPage.getPageTitleText(), "Products");
    }

    @Test
    public void testLoginWithInvalidPassword() {
        loginPage.enterUsername(VALID_USERNAME);
        loginPage.enterPassword(INVALID_PASSWORD);
        loginPage.clickLoginButton();

        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"));
    }

    @Test
    public void testLoginWithLockedUser() {
        loginPage.enterUsername(LOCKED_USERNAME);
        loginPage.enterPassword(VALID_PASSWORD);
        loginPage.clickLoginButton();

        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertTrue(loginPage.getErrorMessage().contains("locked out"));
    }

    @Test
    public void testLoginWithEmptyCredentials() {
        loginPage.clickLoginButton();

        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"));
    }

    @Test
    public void testLoginPageElementsDisplayed() {
        Assert.assertTrue(loginPage.isLoginPageDisplayed());
        Assert.assertTrue(loginPage.isUsernameFieldDisplayed());
        Assert.assertTrue(loginPage.isPasswordFieldDisplayed());
    }

    @Test
    public void testLogout() {
        ProductsPage productsPage = loginPage.login(VALID_USERNAME, VALID_PASSWORD);
        Assert.assertTrue(productsPage.isProductsPageDisplayed());

        loginPage = productsPage.logout();
        Assert.assertTrue(loginPage.isLoginPageDisplayed());
    }
}
