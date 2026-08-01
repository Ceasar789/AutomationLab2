package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignupLoginPageModel {
    private final WebDriver driver;

    private static final int DEFAULT_TIMEOUT_SECONDS = 15;

    private final By signupHeader = By.xpath("//h2[normalize-space()='New User Signup!']");
    private final By loginHeader = By.xpath("//h2[normalize-space()='Login to your account']");
    private final By signupNameInput = By.xpath("//input[@data-qa='signup-name']");
    private final By signupEmailInput = By.xpath("//input[@data-qa='signup-email']");
    private final By signupButton = By.xpath("//button[@data-qa='signup-button']");
    private final By loginEmailInput = By.xpath("//input[@data-qa='login-email']");
    private final By loginPasswordInput = By.xpath("//input[@data-qa='login-password']");
    private final By loginButton = By.xpath("//button[@data-qa='login-button']");
    private final By accountInformationHeader = By.xpath("//b[normalize-space()='Enter Account Information']");

    // ===== Account Information locators =====
    private final By titleMr = By.id("id_gender1");
    private final By titleMrs = By.id("id_gender2");
    private final By passwordInput = By.id("password");
    private final By daysDropdown = By.id("days");
    private final By monthsDropdown = By.id("months");
    private final By yearsDropdown = By.id("years");
    private final By newsletterCheckbox = By.id("newsletter");
    private final By offersCheckbox = By.id("optin");

    // ===== Address Information locators =====
    private final By firstNameInput = By.id("first_name");
    private final By lastNameInput = By.id("last_name");
    private final By companyInput = By.id("company");
    private final By address1Input = By.id("address1");
    private final By address2Input = By.id("address2");
    private final By countryDropdown = By.id("country");
    private final By stateInput = By.id("state");
    private final By cityInput = By.id("city");
    private final By zipcodeInput = By.id("zipcode");
    private final By mobileNumberInput = By.id("mobile_number");

    private final By createAccountButton = By.xpath("//button[@data-qa='create-account']");
    private final By accountCreatedHeader = By.xpath("//b[normalize-space()='Account Created!']");
    private final By continueButton = By.xpath("//a[@data-qa='continue-button']");

    // ===== Login error locator =====
    private final By loginErrorMessage = By.xpath("//p[contains(text(),'incorrect')]");

    // ===== Login/Logout locators =====
    private final By loggedInAsIndicator = By.xpath("//a[contains(text(),'Logged in as')]");
    private final By logoutLink = By.xpath("//a[@href='/logout']");
    private final By deleteAccountLink = By.xpath("//a[@href='/delete_account']");
    private final By accountDeletedHeader = By.xpath("//b[normalize-space()='Account Deleted!']");

    // ===== Existing email error locator =====
    private final By emailExistsError = By.xpath("//p[contains(text(),'Email Address already exist')]");

    public SignupLoginPageModel(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Reusable explicit-wait helper: waits for an element to become visible.
     * Returns false (instead of throwing) if it times out, so callers
     * can keep using it directly inside assertTrue(...).
     * This replaces relying on the global implicit wait, which was causing
     * inconsistent ~20s stalls before every failure.
     */
    private boolean isElementVisible(By locator, int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)) != null;
        } catch (TimeoutException e) {
            return false;
        }
    }

    private boolean isElementVisible(By locator) {
        return isElementVisible(locator, DEFAULT_TIMEOUT_SECONDS);
    }

    private void waitForClickable(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    private void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "var el = arguments[0];" +
                "var frames = document.querySelectorAll('iframe[src*=" + "\"googlesyndication\"" + "], iframe[src*=" + "\"doubleclick\"" + "], ins.adsbygoogle, div[id^=\"google_ads_iframe\"], div[style*=\"position:absolute\"][style*=\"z-index:2147483647\"]');" +
                "for (var i = 0; i < frames.length; i++) { frames[i].remove(); }" +
                "el.scrollIntoView({block: 'center'});" +
                "el.click();",
                element);
    }

    /**
     * Removes known ad iframes/overlays and closes any extra browser tabs/windows
     * an ad may have opened, restoring focus to the original window.
     * Call this before clicking any element that navigates the page.
     */
    private void dismissInterceptingAdsAndExtraTabs() {
        try {
            ((JavascriptExecutor) driver).executeScript(
                    "document.querySelectorAll(" +
                    "'iframe[src*=\"googlesyndication\"], iframe[src*=\"doubleclick\"], " +
                    "ins.adsbygoogle, div[id^=\"google_ads_iframe\"], " +
                    "div[style*=\"position:absolute\"][style*=\"z-index:2147483647\"]'" +
                    ").forEach(function(el){ el.remove(); });"
            );
        } catch (Exception ignored) {
            // best-effort cleanup; don't fail the test if this script errors out
        }

        String originalHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        driver.switchTo().window(originalHandle);
    }

    public boolean isSignupHeaderVisible() {
        return isElementVisible(signupHeader);
    }

    public boolean isLoginHeaderVisible() {
        return isElementVisible(loginHeader);
    }

    public void typeSignupName(String name) {
        WebElement element = driver.findElement(signupNameInput);
        element.clear();
        element.sendKeys(name);
    }

    public void typeSignupEmail(String email) {
        WebElement element = driver.findElement(signupEmailInput);
        element.clear();
        element.sendKeys(email);
    }

    public void clickSignupButton() {
        dismissInterceptingAdsAndExtraTabs();
        waitForClickable(signupButton);
        WebElement element = driver.findElement(signupButton);
        try {
            element.click();
        } catch (Exception e) {
            jsClick(element);
        }
        dismissInterceptingAdsAndExtraTabs();
    }

    public boolean isLoginEmailVisible() {
        return isElementVisible(loginEmailInput);
    }

    public boolean isLoginPasswordVisible() {
        return isElementVisible(loginPasswordInput);
    }

    public boolean isAccountInformationVisible() {
        return isElementVisible(accountInformationHeader);
    }

    // ===== Account Information methods =====
    public void selectTitle(String title) {
        if (title.equalsIgnoreCase("Mr")) {
            driver.findElement(titleMr).click();
        } else {
            driver.findElement(titleMrs).click();
        }
    }

    public void typePassword(String password) {
        WebElement element = driver.findElement(passwordInput);
        element.clear();
        element.sendKeys(password);
    }

    public void selectDateOfBirth(String day, String month, String year) {
        new Select(driver.findElement(daysDropdown)).selectByValue(day);
        new Select(driver.findElement(monthsDropdown)).selectByVisibleText(month);
        new Select(driver.findElement(yearsDropdown)).selectByValue(year);
    }

    public void checkNewsletter() {
        waitForClickable(newsletterCheckbox);
        WebElement element = driver.findElement(newsletterCheckbox);
        try {
            element.click();
        } catch (Exception e) {
            jsClick(element);
        }
    }

    public void checkOffers() {
        waitForClickable(offersCheckbox);
        WebElement element = driver.findElement(offersCheckbox);
        try {
            element.click();
        } catch (Exception e) {
            jsClick(element);
        }
    }

    // ===== Address Information methods =====
    public void typeFirstName(String firstName) {
        WebElement element = driver.findElement(firstNameInput);
        element.clear();
        element.sendKeys(firstName);
    }

    public void typeLastName(String lastName) {
        WebElement element = driver.findElement(lastNameInput);
        element.clear();
        element.sendKeys(lastName);
    }

    public void typeCompany(String company) {
        WebElement element = driver.findElement(companyInput);
        element.clear();
        element.sendKeys(company);
    }

    public void typeAddress1(String address1) {
        WebElement element = driver.findElement(address1Input);
        element.clear();
        element.sendKeys(address1);
    }

    public void typeAddress2(String address2) {
        WebElement element = driver.findElement(address2Input);
        element.clear();
        element.sendKeys(address2);
    }

    public void selectCountry(String country) {
        new Select(driver.findElement(countryDropdown)).selectByVisibleText(country);
    }

    public void typeState(String state) {
        WebElement element = driver.findElement(stateInput);
        element.clear();
        element.sendKeys(state);
    }

    public void typeCity(String city) {
        WebElement element = driver.findElement(cityInput);
        element.clear();
        element.sendKeys(city);
    }

    public void typeZipcode(String zipcode) {
        WebElement element = driver.findElement(zipcodeInput);
        element.clear();
        element.sendKeys(zipcode);
    }

    public void typeMobileNumber(String mobileNumber) {
        WebElement element = driver.findElement(mobileNumberInput);
        element.clear();
        element.sendKeys(mobileNumber);
    }

    public void clickCreateAccountButton() {
        dismissInterceptingAdsAndExtraTabs();
        waitForClickable(createAccountButton);
        WebElement element = driver.findElement(createAccountButton);
        try {
            element.click();
        } catch (Exception e) {
            jsClick(element);
        }
        dismissInterceptingAdsAndExtraTabs();
    }

    public boolean isAccountCreatedVisible() {
        return isElementVisible(accountCreatedHeader);
    }

    public void clickContinueButton() {
        dismissInterceptingAdsAndExtraTabs();
        waitForClickable(continueButton);
        WebElement element = driver.findElement(continueButton);
        try {
            element.click();
        } catch (Exception e) {
            jsClick(element);
        }
        dismissInterceptingAdsAndExtraTabs();
    }

    // ===== Login/Logout methods =====
    public void typeLoginEmail(String email) {
        WebElement element = driver.findElement(loginEmailInput);
        element.clear();
        element.sendKeys(email);
    }

    public void typeLoginPassword(String password) {
        WebElement element = driver.findElement(loginPasswordInput);
        element.clear();
        element.sendKeys(password);
    }

    public void clickLoginButton() {
        dismissInterceptingAdsAndExtraTabs();
        waitForClickable(loginButton);
        WebElement element = driver.findElement(loginButton);
        try {
            element.click();
        } catch (Exception e) {
            jsClick(element);
        }
        dismissInterceptingAdsAndExtraTabs();
    }

    public boolean isLoggedInVisible() {
        return isElementVisible(loggedInAsIndicator);
    }

    public void clickLogout() {
        dismissInterceptingAdsAndExtraTabs();
        waitForClickable(logoutLink);
        WebElement element = driver.findElement(logoutLink);
        try {
            element.click();
        } catch (Exception e) {
            jsClick(element);
        }
        dismissInterceptingAdsAndExtraTabs();
    }

    public void clickDeleteAccount() {
        dismissInterceptingAdsAndExtraTabs();
        waitForClickable(deleteAccountLink);
        WebElement element = driver.findElement(deleteAccountLink);
        try {
            element.click();
        } catch (Exception e) {
            jsClick(element);
        }
        dismissInterceptingAdsAndExtraTabs();
    }

    public boolean isAccountDeletedVisible() {
        // This follows a full page navigation (delete_account) which is the
        // slowest transition in the flow, and the public demo site occasionally
        // takes longer than 20s to respond under load. Wait, and if it still
        // hasn't shown up, re-click Delete Account once (in case the first
        // click was missed or queued slowly) before giving up.
        if (isElementVisible(accountDeletedHeader, 25)) {
            return true;
        }

        try {
            WebElement element = driver.findElement(deleteAccountLink);
            try {
                element.click();
            } catch (Exception e) {
                jsClick(element);
            }
        } catch (Exception ignored) {
            // if the delete account link is no longer present, fall through
            // and let the final visibility check report the real result
        }

        return isElementVisible(accountDeletedHeader, 25);
    }

    public boolean isLoginErrorVisible() {
        return isElementVisible(loginErrorMessage);
    }

    public boolean isEmailExistsErrorVisible() {
        return isElementVisible(emailExistsError);
    }
}