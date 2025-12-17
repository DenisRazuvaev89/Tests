package pages.mobile;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class MainPage extends BaseMobilePage {

    private final By searchContainer = By.id("org.wikipedia:id/search_container");
    private final By searchInput = By.id("org.wikipedia:id/search_src_text");
    private final By moreOptionsButton = By.id("org.wikipedia:id/menu_overflow_button");
    private final By navTabExplore = By.xpath("//android.widget.FrameLayout[@content-desc='Explore']");
    private final By navTabSaved = By.xpath("//android.widget.FrameLayout[@content-desc='Saved']");
    private final By navTabSearch = By.xpath("//android.widget.FrameLayout[@content-desc='Search']");
    private final By navTabEdits = By.xpath("//android.widget.FrameLayout[@content-desc='Edits']");
    private final By navTabMore = By.xpath("//android.widget.FrameLayout[@content-desc='More']");
    private final By featuredArticleCard = By.id("org.wikipedia:id/view_featured_article_card_content_container");
    private final By newsCard = By.id("org.wikipedia:id/news_cardview_recycler_view");
    private final By mainToolbar = By.id("org.wikipedia:id/main_toolbar");

    public MainPage(AndroidDriver driver) {
        super(driver);
    }

    public boolean isMainPageDisplayed() {
        return isElementDisplayed(searchContainer) || isElementDisplayed(mainToolbar);
    }

    public SearchPage clickSearchContainer() {
        click(searchContainer);
        return new SearchPage(driver);
    }

    public void openMoreOptions() {
        click(moreOptionsButton);
    }

    public void navigateToExplore() {
        click(navTabExplore);
    }

    public void navigateToSaved() {
        click(navTabSaved);
    }

    public void navigateToSearch() {
        click(navTabSearch);
    }

    public void navigateToEdits() {
        click(navTabEdits);
    }

    public void navigateToMore() {
        click(navTabMore);
    }

    public boolean isFeaturedArticleDisplayed() {
        scrollDown();
        return isElementDisplayed(featuredArticleCard);
    }

    public boolean isNavigationBarDisplayed() {
        return isElementDisplayed(navTabExplore);
    }

    public void scrollToNews() {
        for (int i = 0; i < 3; i++) {
            scrollDown();
            if (isElementPresent(newsCard)) {
                break;
            }
        }
    }

    public boolean isNewsCardDisplayed() {
        return isElementDisplayed(newsCard);
    }
}
