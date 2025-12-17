package pages.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for SauceDemo Checkout Pages
 */
public class CheckoutPage extends BasePage {

    // Checkout Step One Locators
    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By cancelButton = By.id("cancel");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    // Checkout Step Two (Overview) Locators
    private final By finishButton = By.id("finish");
    private final By subtotalLabel = By.className("summary_subtotal_label");
    private final By taxLabel = By.className("summary_tax_label");
    private final By totalLabel = By.className("summary_total_label");
    private final By paymentInfo = By.cssSelector("[data-test='payment-info-value']");
    private final By shippingInfo = By.cssSelector("[data-test='shipping-info-value']");

    // Checkout Complete Locators
    private final By completeHeader = By.className("complete-header");
    private final By completeText = By.className("complete-text");
    private final By backHomeButton = By.id("back-to-products");
    private final By ponyExpressImage = By.className("pony_express");

    // Common
    private final By pageTitle = By.className("title");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitleText() {
        return getText(pageTitle);
    }

    // Step One Methods
    public void enterFirstName(String firstName) {
        type(firstNameInput, firstName);
    }

    public void enterLastName(String lastName) {
        type(lastNameInput, lastName);
    }

    public void enterPostalCode(String postalCode) {
        type(postalCodeInput, postalCode);
    }

    public void fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
    }

    public void clickContinue() {
        click(continueButton);
    }

    public CartPage clickCancel() {
        click(cancelButton);
        return new CartPage(driver);
    }

    public boolean isErrorDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    // Step Two (Overview) Methods
    public String getSubtotal() {
        return getText(subtotalLabel);
    }

    public double getSubtotalValue() {
        String subtotal = getSubtotal();
        return Double.parseDouble(subtotal.replaceAll("[^0-9.]", ""));
    }

    public String getTax() {
        return getText(taxLabel);
    }

    public double getTaxValue() {
        String tax = getTax();
        return Double.parseDouble(tax.replaceAll("[^0-9.]", ""));
    }

    public String getTotal() {
        return getText(totalLabel);
    }

    public double getTotalValue() {
        String total = getTotal();
        return Double.parseDouble(total.replaceAll("[^0-9.]", ""));
    }

    public String getPaymentInfo() {
        return getText(paymentInfo);
    }

    public String getShippingInfo() {
        return getText(shippingInfo);
    }

    public void clickFinish() {
        click(finishButton);
    }

    // Checkout Complete Methods
    public String getCompleteHeader() {
        return getText(completeHeader);
    }

    public String getCompleteText() {
        return getText(completeText);
    }

    public boolean isOrderComplete() {
        return isElementDisplayed(completeHeader) &&
               getCompleteHeader().contains("Thank you for your order");
    }

    public boolean isPonyExpressDisplayed() {
        return isElementDisplayed(ponyExpressImage);
    }

    public ProductsPage clickBackHome() {
        click(backHomeButton);
        return new ProductsPage(driver);
    }

    public boolean isCheckoutStepOneDisplayed() {
        return getPageTitleText().equals("Checkout: Your Information");
    }

    public boolean isCheckoutOverviewDisplayed() {
        return getPageTitleText().equals("Checkout: Overview");
    }

    public boolean isCheckoutCompleteDisplayed() {
        return getPageTitleText().equals("Checkout: Complete!");
    }
}
