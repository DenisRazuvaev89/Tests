package pages.mobile;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class ArticlePage extends BaseMobilePage {

    private final By articleTitle = By.id("org.wikipedia:id/view_page_title_text");
    private final By articleToolbar = By.id("org.wikipedia:id/page_toolbar");
    private final By articleWebView = By.className("android.webkit.WebView");
    private final By tocButton = By.id("org.wikipedia:id/page_toc_button");
    private final By saveButton = By.id("org.wikipedia:id/page_save");
    private final By languageButton = By.id("org.wikipedia:id/page_language");
    private final By shareButton = By.id("org.wikipedia:id/page_share");
    private final By findInPageButton = By.id("org.wikipedia:id/page_find_in_article");
    private final By backButton = By.className("android.widget.ImageButton");
    private final By articleHeaderTitle = By.xpath("//android.view.View[@resource-id='pcs-edit-section-title-description']/preceding-sibling::android.view.View");
    private final By moreOptionsButton = By.id("org.wikipedia:id/page_toolbar_button_show_overflow_menu");
    private final By articleDescription = By.id("org.wikipedia:id/view_page_subtitle_text");
    private final By tocContainer = By.id("org.wikipedia:id/toc_list");
    private final By tocItems = By.id("org.wikipedia:id/page_toc_item_text");

    public ArticlePage(AndroidDriver driver) {
        super(driver);
    }

    public boolean isArticlePageDisplayed() {
        return isElementDisplayed(articleToolbar) || isElementDisplayed(articleWebView);
    }

    public String getArticleTitle() {
        try {
            return getText(articleTitle);
        } catch (Exception e) {
            return "";
        }
    }

    public boolean hasArticleTitle() {
        return isElementDisplayed(articleTitle);
    }

    public void clickSaveArticle() {
        click(saveButton);
    }

    public void clickLanguageButton() {
        click(languageButton);
    }

    public void clickShareButton() {
        click(shareButton);
    }

    public void clickFindInPage() {
        click(findInPageButton);
    }

    public void goBack() {
        click(backButton);
    }

    public void openTableOfContents() {
        click(tocButton);
    }

    public boolean isTableOfContentsDisplayed() {
        return isElementDisplayed(tocContainer);
    }

    public void scrollDownArticle() {
        scrollDown();
    }

    public void scrollUpArticle() {
        scrollUp();
    }

    public void scrollToBottom() {
        for (int i = 0; i < 5; i++) {
            scrollDown();
        }
    }

    public boolean isToolbarDisplayed() {
        return isElementDisplayed(articleToolbar);
    }

    public void openMoreOptions() {
        click(moreOptionsButton);
    }

    public String getArticleDescription() {
        try {
            return getText(articleDescription);
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isSaveButtonDisplayed() {
        return isElementDisplayed(saveButton);
    }

    public boolean isLanguageButtonDisplayed() {
        return isElementDisplayed(languageButton);
    }

    public boolean isWebViewDisplayed() {
        return isElementDisplayed(articleWebView);
    }
}
