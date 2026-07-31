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

public class RegisterExistingEmailTest extends BaseTest {
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
    public void registerWithExistingEmail() {
        String email = "existinguser" + generate4Digit() + "@example.com";
        String password = "Password123";

        // Step 1: Register a new account first, so the email actually exists in the system
        homeActions.verifyHomePageIsVisible();
        homeActions.clickSignupLoginButton();
        signupActions.enterSignupCredentials("ExistingUser", email);
        signupActions.clickSignupButton();
        signupActions.verifyAccountInformationPage();

        signupActions.fillAccountInformation("Mr", password, "10", "May", "1995");
        signupActions.fillAddressInformation(
                "ExistingUser", "LastName", "TestCompany",
                "123 Sample St", "Apt 4B",
                "United States", "California", "Los Angeles",
                "90001", "09123456789"
        );
        signupActions.clickCreateAccountButton();
        signupActions.verifyAccountCreated();
        signupActions.clickContinueButton();

        // Step 2: Logout, then attempt to register again with the same email
        signupActions.logout();
        homeActions.clickSignupLoginButton();
        signupActions.enterSignupCredentials("ExistingUser", email);
        signupActions.clickSignupButton();

        // Step 3: Verify the "Email Address already exist" error is shown
        signupActions.verifyEmailExistsError();
    }

    @AfterMethod(alwaysRun = true)
    public void cleanup(ITestResult result) {
        afterMethod(result, "chrome");
    }
}