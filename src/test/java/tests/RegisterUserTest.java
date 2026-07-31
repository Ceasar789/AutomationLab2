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

public class RegisterUserTest extends BaseTest {
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
    public void registerUser() {
        homeActions.verifyHomePageIsVisible();
        homeActions.clickSignupLoginButton();
        signupActions.verifySignupSection();

        String email = "student" + generate4Digit() + "@example.com";
        signupActions.enterSignupCredentials("TestUser", email);
        signupActions.clickSignupButton();
        signupActions.verifyAccountInformationPage();

        signupActions.fillAccountInformation("Mr", "Password123", "10", "May", "1995");
        signupActions.fillAddressInformation(
                "TestUser", "LastName", "TestCompany",
                "123 Sample St", "Apt 4B",
                "United States", "California", "Los Angeles",
                "90001", "09123456789"
        );
        signupActions.clickCreateAccountButton();
        signupActions.verifyAccountCreated();
        signupActions.clickContinueButton();

        // Steps 16-18 per official Test Case 1: verify logged in, delete account, verify deleted
        signupActions.verifyLoggedIn();
        signupActions.deleteAccount();
        signupActions.verifyAccountDeleted();
    }

    @AfterMethod(alwaysRun = true)
    public void cleanup(ITestResult result) {
        afterMethod(result, "chrome");
    }
}