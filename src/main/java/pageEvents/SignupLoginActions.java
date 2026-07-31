package pageEvents;

import static org.testng.Assert.assertTrue;

import base.BaseTest;
import pageObjects.SignupLoginPageModel;

public class SignupLoginActions extends BaseTest {
    private SignupLoginPageModel signupLoginPage;

    private void init() {
        signupLoginPage = new SignupLoginPageModel(driver);
    }

    public void verifySignupSection() {
        init();
        logger.info("Verify that signup section is visible");
        assertTrue(signupLoginPage.isSignupHeaderVisible(), "Signup header is not visible");
    }

    public void verifyLoginSection() {
        init();
        logger.info("Verify that login section is visible");
        assertTrue(signupLoginPage.isLoginHeaderVisible(), "Login header is not visible");
    }

    public void enterSignupCredentials(String name, String email) {
        init();
        signupLoginPage.typeSignupName(name);
        signupLoginPage.typeSignupEmail(email);
    }

    public void clickSignupButton() {
        init();
        signupLoginPage.clickSignupButton();
    }

    public void verifyAccountInformationPage() {
        init();
        logger.info("Verify that account information page is visible");
        assertTrue(signupLoginPage.isAccountInformationVisible(), "Account information page is not visible");
    }

    public void verifyLoginFields() {
        init();
        logger.info("Verify login email and password fields are visible");
        assertTrue(signupLoginPage.isLoginEmailVisible(), "Login email field is not visible");
        assertTrue(signupLoginPage.isLoginPasswordVisible(), "Login password field is not visible");
    }

    // ===== Account Information =====
    public void fillAccountInformation(String title, String password, String day, String month, String year) {
        init();
        logger.info("Fill account information details");
        signupLoginPage.selectTitle(title);
        signupLoginPage.typePassword(password);
        signupLoginPage.selectDateOfBirth(day, month, year);
        signupLoginPage.checkNewsletter();
        signupLoginPage.checkOffers();
    }

    // ===== Address Information =====
    public void fillAddressInformation(String firstName, String lastName, String company, String address1,
            String address2, String country, String state, String city, String zipcode, String mobileNumber) {
        init();
        logger.info("Fill address information details");
        signupLoginPage.typeFirstName(firstName);
        signupLoginPage.typeLastName(lastName);
        signupLoginPage.typeCompany(company);
        signupLoginPage.typeAddress1(address1);
        signupLoginPage.typeAddress2(address2);
        signupLoginPage.selectCountry(country);
        signupLoginPage.typeState(state);
        signupLoginPage.typeCity(city);
        signupLoginPage.typeZipcode(zipcode);
        signupLoginPage.typeMobileNumber(mobileNumber);
    }

    public void clickCreateAccountButton() {
        init();
        signupLoginPage.clickCreateAccountButton();
    }

    public void verifyAccountCreated() {
        init();
        logger.info("Verify that account was created successfully");
        assertTrue(signupLoginPage.isAccountCreatedVisible(), "Account Created message is not visible");
    }

    public void clickContinueButton() {
        init();
        signupLoginPage.clickContinueButton();
    }

    // ===== Login/Logout =====
    public void login(String email, String password) {
        init();
        logger.info("Login with email and password");
        signupLoginPage.typeLoginEmail(email);
        signupLoginPage.typeLoginPassword(password);
        signupLoginPage.clickLoginButton();
    }

    public void verifyLoggedIn() {
        init();
        logger.info("Verify that user is logged in");
        assertTrue(signupLoginPage.isLoggedInVisible(), "'Logged in as' indicator is not visible");
    }

    public void logout() {
        init();
        logger.info("Logout current user");
        signupLoginPage.clickLogout();
    }

    public void deleteAccount() {
        init();
        logger.info("Delete current account");
        signupLoginPage.clickDeleteAccount();
    }

    public void verifyAccountDeleted() {
        init();
        logger.info("Verify that account was deleted successfully");
        assertTrue(signupLoginPage.isAccountDeletedVisible(), "Account Deleted message is not visible");
    }

    public void verifyLoginError() {
        init();
        logger.info("Verify that incorrect login error message is visible");
        assertTrue(signupLoginPage.isLoginErrorVisible(), "Expected 'incorrect email or password' error message to be visible");
    }

    public void verifyEmailExistsError() {
        init();
        logger.info("Verify that 'Email Address already exist' error message is visible");
        assertTrue(signupLoginPage.isEmailExistsErrorVisible(), "Expected 'Email Address already exist' error message to be visible");
    }
} 