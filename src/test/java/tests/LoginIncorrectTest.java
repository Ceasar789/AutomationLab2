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

public class LoginIncorrectTest extends BaseTest {
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
    public void loginWithIncorrectCredentials() {
        homeActions.verifyHomePageIsVisible();
        homeActions.clickSignupLoginButton();
        signupActions.verifyLoginSection();

        signupActions.login("invalid" + generate4Digit() + "@example.com", "wrongpass123");
        signupActions.verifyLoginError();
    }

    @AfterMethod(alwaysRun = true)
    public void cleanup(ITestResult result) {
        afterMethod(result, "chrome");
    }
}