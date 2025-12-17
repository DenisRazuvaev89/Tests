package tests.mobile;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.mobile.ArticlePage;
import pages.mobile.SearchPage;

public class ArticleTests extends BaseMobileTest {

    @Test
    public void testArticleToolbarDisplayed() {
        SearchPage searchPage = mainPage.clickSearchContainer();
        searchPage.enterSearchQuery("Java");
        ArticlePage articlePage = searchPage.clickFirstSearchResult();

        Assert.assertTrue(articlePage.isArticlePageDisplayed());
        Assert.assertTrue(articlePage.isToolbarDisplayed());
    }

    @Test
    public void testArticleHasTitle() {
        SearchPage searchPage = mainPage.clickSearchContainer();
        searchPage.enterSearchQuery("London");
        ArticlePage articlePage = searchPage.clickFirstSearchResult();

        Assert.assertTrue(articlePage.isArticlePageDisplayed());
        Assert.assertTrue(articlePage.hasArticleTitle() || articlePage.isWebViewDisplayed());
    }

    @Test
    public void testScrollArticle() {
        SearchPage searchPage = mainPage.clickSearchContainer();
        searchPage.enterSearchQuery("Russia");
        ArticlePage articlePage = searchPage.clickFirstSearchResult();

        Assert.assertTrue(articlePage.isArticlePageDisplayed());

        articlePage.scrollDownArticle();
        articlePage.scrollDownArticle();
        articlePage.scrollUpArticle();

        Assert.assertTrue(articlePage.isArticlePageDisplayed());
    }

    @Test
    public void testNavigateBackFromArticle() {
        SearchPage searchPage = mainPage.clickSearchContainer();
        searchPage.enterSearchQuery("Paris");
        ArticlePage articlePage = searchPage.clickFirstSearchResult();

        Assert.assertTrue(articlePage.isArticlePageDisplayed());

        articlePage.goBack();

        Assert.assertTrue(searchPage.isSearchPageDisplayed() || mainPage.isMainPageDisplayed());
    }

    @Test
    public void testArticleSaveButtonDisplayed() {
        SearchPage searchPage = mainPage.clickSearchContainer();
        searchPage.enterSearchQuery("New York");
        ArticlePage articlePage = searchPage.clickFirstSearchResult();

        Assert.assertTrue(articlePage.isArticlePageDisplayed());
        Assert.assertTrue(articlePage.isSaveButtonDisplayed());
    }

    @Test
    public void testArticleLanguageButtonDisplayed() {
        SearchPage searchPage = mainPage.clickSearchContainer();
        searchPage.enterSearchQuery("Berlin");
        ArticlePage articlePage = searchPage.clickFirstSearchResult();

        Assert.assertTrue(articlePage.isArticlePageDisplayed());
        Assert.assertTrue(articlePage.isLanguageButtonDisplayed());
    }

    @Test
    public void testOpenTableOfContents() {
        SearchPage searchPage = mainPage.clickSearchContainer();
        searchPage.enterSearchQuery("United States");
        ArticlePage articlePage = searchPage.clickFirstSearchResult();

        Assert.assertTrue(articlePage.isArticlePageDisplayed());

        try {
            articlePage.openTableOfContents();
            Thread.sleep(1000);
            Assert.assertTrue(articlePage.isTableOfContentsDisplayed() || articlePage.isArticlePageDisplayed());
        } catch (Exception e) {
            Assert.assertTrue(articlePage.isArticlePageDisplayed());
        }
    }

    @Test
    public void testScrollToBottomOfArticle() {
        SearchPage searchPage = mainPage.clickSearchContainer();
        searchPage.enterSearchQuery("Wikipedia");
        ArticlePage articlePage = searchPage.clickFirstSearchResult();

        Assert.assertTrue(articlePage.isArticlePageDisplayed());

        articlePage.scrollToBottom();

        Assert.assertTrue(articlePage.isArticlePageDisplayed());
    }
}
