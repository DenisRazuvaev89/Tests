package tests.web;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.web.LoginPage;
import utils.ConfigReader;
import utils.DriverFactory;

public class BaseWebTest {
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected String baseUrl;

    protected static final String VALID_USERNAME = "standard_user";
    protected static final String VALID_PASSWORD = "secret_sauce";
    protected static final String LOCKED_USERNAME = "locked_out_user";
    protected static final String INVALID_PASSWORD = "wrong_password";

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.createDriver();
        baseUrl = ConfigReader.get("web.base.url");
        loginPage = new LoginPage(driver);
        loginPage.open(baseUrl);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
