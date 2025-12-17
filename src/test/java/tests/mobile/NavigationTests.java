package tests.mobile;

import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTests extends BaseMobileTest {

    @Test
    public void testMainPageDisplayed() {
        Assert.assertTrue(mainPage.isMainPageDisplayed());
    }

    @Test
    public void testNavigationBarDisplayed() {
        Assert.assertTrue(mainPage.isNavigationBarDisplayed());
    }

    @Test
    public void testNavigateToExplore() {
        mainPage.navigateToExplore();
        Assert.assertTrue(mainPage.isMainPageDisplayed());
    }

    @Test
    public void testNavigateToSaved() {
        mainPage.navigateToSaved();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
        Assert.assertTrue(mainPage.isNavigationBarDisplayed());
    }

    @Test
    public void testNavigateToSearch() {
        mainPage.navigateToSearch();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
        Assert.assertTrue(mainPage.isNavigationBarDisplayed());
    }

    @Test
    public void testScrollOnMainPage() {
        mainPage.scrollToNews();
        Assert.assertTrue(mainPage.isMainPageDisplayed());
    }

    @Test
    public void testSearchContainerClickable() {
        Assert.assertTrue(mainPage.isMainPageDisplayed());
        mainPage.clickSearchContainer();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
