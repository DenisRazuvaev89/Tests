package tests.mobile;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.mobile.ArticlePage;
import pages.mobile.SearchPage;

import java.util.List;

public class SearchTests extends BaseMobileTest {

    @Test
    public void testSearchForArticle() {
        SearchPage searchPage = mainPage.clickSearchContainer();
        Assert.assertTrue(searchPage.isSearchPageDisplayed());

        searchPage.enterSearchQuery("Java programming");
        Assert.assertTrue(searchPage.hasSearchResults());
        Assert.assertTrue(searchPage.isResultContainingText("Java"));
    }

    @Test
    public void testOpenSearchResult() {
        SearchPage searchPage = mainPage.clickSearchContainer();
        searchPage.enterSearchQuery("Python");

        Assert.assertTrue(searchPage.hasSearchResults());

        ArticlePage articlePage = searchPage.clickFirstSearchResult();
        Assert.assertTrue(articlePage.isArticlePageDisplayed());
    }

    @Test
    public void testVerifyArticleTitle() {
        SearchPage searchPage = mainPage.clickSearchContainer();
        searchPage.enterSearchQuery("Albert Einstein");

        ArticlePage articlePage = searchPage.clickSearchResultByTitle("Albert Einstein");

        Assert.assertTrue(articlePage.isArticlePageDisplayed());
        String title = articlePage.getArticleTitle();
        Assert.assertTrue(title.toLowerCase().contains("einstein") || articlePage.isWebViewDisplayed());
    }

    @Test
    public void testSearchWithNoResults() {
        SearchPage searchPage = mainPage.clickSearchContainer();
        searchPage.enterSearchQuery("xyznonexistentarticle123456");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {
        }

        Assert.assertTrue(searchPage.isNoResultsDisplayed() || !searchPage.hasSearchResults());
    }

    @Test
    public void testClearSearchQuery() {
        SearchPage searchPage = mainPage.clickSearchContainer();
        searchPage.enterSearchQuery("Test query");

        Assert.assertTrue(searchPage.hasSearchResults() || searchPage.isSearchPageDisplayed());

        searchPage.clearSearch();
        searchPage.goBack();

        Assert.assertTrue(mainPage.isMainPageDisplayed());
    }

    @Test
    public void testMultipleSearchResults() {
        SearchPage searchPage = mainPage.clickSearchContainer();
        searchPage.enterSearchQuery("Moscow");

        Assert.assertTrue(searchPage.hasSearchResults());
        List<String> results = searchPage.getSearchResultTitles();
        Assert.assertTrue(results.size() > 1);
    }

    @DataProvider(name = "searchQueries")
    public Object[][] searchQueriesProvider() {
        return new Object[][] {
            {"Wikipedia"},
            {"Moscow"},
            {"Computer science"}
        };
    }

    @Test(dataProvider = "searchQueries")
    public void testSearchWithDifferentQueries(String query) {
        SearchPage searchPage = mainPage.clickSearchContainer();
        searchPage.enterSearchQuery(query);

        Assert.assertTrue(searchPage.hasSearchResults());
        searchPage.goBack();
    }
}
