package tests;

import java.lang.reflect.Method;
import java.time.Duration;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pageEvents.HomePageActions;
import pageEvents.SignupLoginActions;
import utils.Config;

public class LoginCorrectTest extends BaseTest {
    private HomePageActions homeActions;
    private SignupLoginActions signupActions;

    @BeforeMethod(alwaysRun = true)
    public void setupTest(Method testMethod) {
        beforeTestMethod("chrome");
        logger = extent.createTest(testMethod.getName());
        setupDriver("chrome");
        driver.manage().window().maximize();
        driver.get(Config.BASE_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Config.IMPLICIT_WAIT_SECONDS));
        homeActions = new HomePageActions();
        signupActions = new SignupLoginActions();
    }

    @Test(priority = 1)
    public void loginWithCorrectCredentials() {
        String email = "student" + generate4Digit() + "@example.com";
        String password = "Password123";

        // Step 1: Register a new account first (so we have valid credentials to log in with)
        homeActions.verifyHomePageIsVisible();
        homeActions.clickSignupLoginButton();
        signupActions.enterSignupCredentials("LoginUser", email);
        signupActions.clickSignupButton();
        signupActions.verifyAccountInformationPage();

        signupActions.fillAccountInformation("Mr", password, "10", "May", "1995");
        signupActions.fillAddressInformation(
                "LoginUser", "LastName", "TestCompany",
                "123 Sample St", "Apt 4B",
                "United States", "California", "Los Angeles",
                "90001", "09123456789"
        );
        signupActions.clickCreateAccountButton();
        signupActions.verifyAccountCreated();
        signupActions.clickContinueButton();

        // Step 2: Logout so we can test the login flow
        signupActions.logout();

        // Step 3: Login using the same email and password
        signupActions.login(email, password);
        signupActions.verifyLoggedIn();

        // Step 4: Clean up - delete the account
        signupActions.deleteAccount();
        signupActions.verifyAccountDeleted();
    }

    @AfterMethod(alwaysRun = true)
    public void cleanup(ITestResult result) {
        afterMethod(result, "chrome");
    }
}