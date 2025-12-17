package pages.mobile;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class SearchPage extends BaseMobilePage {

    private final By searchInput = By.id("org.wikipedia:id/search_src_text");
    private final By searchResultsList = By.id("org.wikipedia:id/search_results_list");
    private final By searchResultItems = By.id("org.wikipedia:id/page_list_item_title");
    private final By searchResultDescriptions = By.id("org.wikipedia:id/page_list_item_description");
    private final By searchCloseButton = By.id("org.wikipedia:id/search_close_btn");
    private final By searchBackButton = By.className("android.widget.ImageButton");
    private final By noResultsText = By.id("org.wikipedia:id/search_empty_message");
    private final By recentSearches = By.id("org.wikipedia:id/recent_searches_recycler");
    private final By searchContainer = By.id("org.wikipedia:id/search_container");
    private final By searchCabView = By.id("org.wikipedia:id/search_cab_view");

    public SearchPage(AndroidDriver driver) {
        super(driver);
    }

    public boolean isSearchPageDisplayed() {
        return isElementDisplayed(searchInput) || isElementDisplayed(searchCabView);
    }

    public void enterSearchQuery(String query) {
        type(searchInput, query);
    }

    public void clearSearch() {
        if (isElementPresent(searchCloseButton)) {
            click(searchCloseButton);
        }
    }

    public void goBack() {
        click(searchBackButton);
    }

    public List<String> getSearchResultTitles() {
        return waitForElementsVisible(searchResultItems)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public int getSearchResultsCount() {
        try {
            return waitForElementsVisible(searchResultItems).size();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean hasSearchResults() {
        return getSearchResultsCount() > 0;
    }

    public ArticlePage clickFirstSearchResult() {
        waitForElementsVisible(searchResultItems).get(0).click();
        return new ArticlePage(driver);
    }

    public ArticlePage clickSearchResultByIndex(int index) {
        List<WebElement> results = waitForElementsVisible(searchResultItems);
        if (index < results.size()) {
            results.get(index).click();
        }
        return new ArticlePage(driver);
    }

    public ArticlePage clickSearchResultByTitle(String title) {
        List<WebElement> results = waitForElementsVisible(searchResultItems);
        for (WebElement result : results) {
            if (result.getText().equalsIgnoreCase(title)) {
                result.click();
                return new ArticlePage(driver);
            }
        }
        results.get(0).click();
        return new ArticlePage(driver);
    }

    public boolean isNoResultsDisplayed() {
        return isElementDisplayed(noResultsText);
    }

    public String getNoResultsText() {
        return getText(noResultsText);
    }

    public boolean isRecentSearchesDisplayed() {
        return isElementDisplayed(recentSearches);
    }

    public boolean isResultContainingText(String text) {
        List<String> titles = getSearchResultTitles();
        return titles.stream().anyMatch(title ->
            title.toLowerCase().contains(text.toLowerCase()));
    }
}
