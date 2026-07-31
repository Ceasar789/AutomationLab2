package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SignupLoginPageModel {
    private final WebDriver driver;

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

    public SignupLoginPageModel(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSignupHeaderVisible() {
        return !driver.findElements(signupHeader).isEmpty() && driver.findElement(signupHeader).isDisplayed();
    }

    public boolean isLoginHeaderVisible() {
        return !driver.findElements(loginHeader).isEmpty() && driver.findElement(loginHeader).isDisplayed();
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
        driver.findElement(signupButton).click();
    }

    public boolean isLoginEmailVisible() {
        return !driver.findElements(loginEmailInput).isEmpty() && driver.findElement(loginEmailInput).isDisplayed();
    }

    public boolean isLoginPasswordVisible() {
        return !driver.findElements(loginPasswordInput).isEmpty() && driver.findElement(loginPasswordInput).isDisplayed();
    }

    public boolean isAccountInformationVisible() {
        return !driver.findElements(accountInformationHeader).isEmpty() && driver.findElement(accountInformationHeader).isDisplayed();
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
        driver.findElement(newsletterCheckbox).click();
    }

    public void checkOffers() {
        driver.findElement(offersCheckbox).click();
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
        driver.findElement(createAccountButton).click();
    }

    public boolean isAccountCreatedVisible() {
        return !driver.findElements(accountCreatedHeader).isEmpty() && driver.findElement(accountCreatedHeader).isDisplayed();
    }

    public void clickContinueButton() {
        driver.findElement(continueButton).click();
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
        driver.findElement(loginButton).click();
    }

    public boolean isLoggedInVisible() {
        return !driver.findElements(loggedInAsIndicator).isEmpty() && driver.findElement(loggedInAsIndicator).isDisplayed();
    }

    public void clickLogout() {
        driver.findElement(logoutLink).click();
    }

    public void clickDeleteAccount() {
        driver.findElement(deleteAccountLink).click();
    }

    public boolean isAccountDeletedVisible() {
        return !driver.findElements(accountDeletedHeader).isEmpty() && driver.findElement(accountDeletedHeader).isDisplayed();
    }

    public boolean isLoginErrorVisible() {
        return !driver.findElements(loginErrorMessage).isEmpty() && driver.findElement(loginErrorMessage).isDisplayed();
    }

    // ===== Existing email error locator =====
    private final By emailExistsError = By.xpath("//p[contains(text(),'Email Address already exist')]");

    public boolean isEmailExistsErrorVisible() {
        return !driver.findElements(emailExistsError).isEmpty() && driver.findElement(emailExistsError).isDisplayed();
    }
}