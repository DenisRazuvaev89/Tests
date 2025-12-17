package tests.mobile;

import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.mobile.MainPage;
import pages.mobile.OnboardingPage;
import utils.AppiumDriverFactory;

public class BaseMobileTest {
    protected AndroidDriver driver;
    protected OnboardingPage onboardingPage;
    protected MainPage mainPage;

    @BeforeMethod
    public void setUp() {
        driver = AppiumDriverFactory.createDriver();
        onboardingPage = new OnboardingPage(driver);
        mainPage = new MainPage(driver);

        skipOnboardingIfPresent();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected void skipOnboardingIfPresent() {
        try {
            Thread.sleep(2000);
            if (onboardingPage.isOnboardingDisplayed()) {
                onboardingPage.skipOnboarding();
                Thread.sleep(1000);
            }
        } catch (Exception ignored) {
        }
    }
}
