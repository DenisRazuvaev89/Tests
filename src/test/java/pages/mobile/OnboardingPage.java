package pages.mobile;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class OnboardingPage extends BaseMobilePage {

    private final By skipButton = By.id("org.wikipedia:id/fragment_onboarding_skip_button");
    private final By continueButton = By.id("org.wikipedia:id/fragment_onboarding_forward_button");
    private final By onboardingPager = By.id("org.wikipedia:id/fragment_onboarding_pager");
    private final By primaryTextView = By.id("org.wikipedia:id/primaryTextView");
    private final By secondaryTextView = By.id("org.wikipedia:id/secondaryTextView");
    private final By doneButton = By.id("org.wikipedia:id/fragment_onboarding_done_button");
    private final By addLanguageButton = By.id("org.wikipedia:id/addLanguageButton");

    public OnboardingPage(AndroidDriver driver) {
        super(driver);
    }

    public boolean isOnboardingDisplayed() {
        return isElementDisplayed(skipButton) || isElementDisplayed(onboardingPager);
    }

    public void skipOnboarding() {
        if (isElementDisplayed(skipButton)) {
            click(skipButton);
        }
    }

    public void clickContinue() {
        click(continueButton);
    }

    public String getPrimaryText() {
        return getText(primaryTextView);
    }

    public String getSecondaryText() {
        return getText(secondaryTextView);
    }

    public void completeOnboarding() {
        try {
            for (int i = 0; i < 4; i++) {
                if (isElementPresent(doneButton)) {
                    click(doneButton);
                    return;
                }
                if (isElementPresent(continueButton)) {
                    click(continueButton);
                    Thread.sleep(500);
                }
            }
            if (isElementPresent(skipButton)) {
                click(skipButton);
            }
        } catch (Exception e) {
            if (isElementPresent(skipButton)) {
                click(skipButton);
            }
        }
    }

    public boolean isAddLanguageButtonDisplayed() {
        return isElementDisplayed(addLanguageButton);
    }
}
