package pages.mobile;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class SettingsPage extends BaseMobilePage {

    private final By settingsToolbar = By.id("org.wikipedia:id/toolbar");
    private final By settingsList = By.id("android:id/list");
    private final By languagePreference = By.xpath("//android.widget.TextView[@text='Wikipedia language']");
    private final By showImagesSwitch = By.xpath("//android.widget.TextView[@text='Show images']/following-sibling::android.widget.Switch");
    private final By showLinkPreviewsSwitch = By.xpath("//android.widget.TextView[@text='Show link previews']/following-sibling::android.widget.Switch");
    private final By sendUsagReportsSwitch = By.xpath("//android.widget.TextView[@text='Send usage reports']/following-sibling::android.widget.Switch");
    private final By sendCrashReportsSwitch = By.xpath("//android.widget.TextView[@text='Send crash reports']/following-sibling::android.widget.Switch");
    private final By aboutWikipediaApp = By.xpath("//android.widget.TextView[@text='About the Wikipedia app']");
    private final By privacyPolicy = By.xpath("//android.widget.TextView[@text='Privacy policy']");
    private final By backButton = By.className("android.widget.ImageButton");
    private final By languageListItems = By.id("org.wikipedia:id/localized_language_name");
    private final By languageSearchInput = By.id("org.wikipedia:id/menu_search_language");
    private final By selectedLanguage = By.xpath("//android.widget.TextView[@text='Wikipedia language']/following-sibling::android.widget.TextView");

    public SettingsPage(AndroidDriver driver) {
        super(driver);
    }

    public boolean isSettingsPageDisplayed() {
        return isElementDisplayed(settingsList) || isElementDisplayed(settingsToolbar);
    }

    public void clickLanguagePreference() {
        click(languagePreference);
    }

    public String getSelectedLanguage() {
        try {
            return getText(selectedLanguage);
        } catch (Exception e) {
            return "";
        }
    }

    public List<String> getAvailableLanguages() {
        return waitForElementsVisible(languageListItems)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void selectLanguage(String language) {
        List<WebElement> languages = waitForElementsVisible(languageListItems);
        for (WebElement lang : languages) {
            if (lang.getText().equalsIgnoreCase(language)) {
                lang.click();
                return;
            }
        }
    }

    public void searchLanguage(String query) {
        click(languageSearchInput);
        type(languageSearchInput, query);
    }

    public void goBack() {
        click(backButton);
    }

    public void clickAboutWikipediaApp() {
        scrollDown();
        click(aboutWikipediaApp);
    }

    public void clickPrivacyPolicy() {
        scrollDown();
        click(privacyPolicy);
    }

    public boolean isShowImagesEnabled() {
        try {
            WebElement toggle = driver.findElement(showImagesSwitch);
            return toggle.getAttribute("checked").equals("true");
        } catch (Exception e) {
            return false;
        }
    }

    public void toggleShowImages() {
        click(showImagesSwitch);
    }
}
