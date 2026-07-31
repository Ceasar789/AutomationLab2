package tests;

import java.lang.reflect.Method;
import java.time.Duration;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pageEvents.HomePageActions;
import pageEvents.CartActions;
import pageEvents.SubscriptionActions;
import utils.Config;

public class VerifySubscriptionCartTest extends BaseTest {
    private HomePageActions homeActions;
    private CartActions cartActions;
    private SubscriptionActions subscriptionActions;

    @BeforeMethod(alwaysRun = true)
    public void setupTest(Method testMethod) {
        beforeTestMethod("chrome");
        logger = extent.createTest(testMethod.getName());
        setupDriver("chrome");
        driver.manage().window().maximize();
        driver.get(Config.BASE_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Config.IMPLICIT_WAIT_SECONDS));
        homeActions = new HomePageActions();
        cartActions = new CartActions();
        subscriptionActions = new SubscriptionActions();
    }

    @Test(priority = 1)
    public void verifySubscriptionInCartPage() {
        // Step 1-3: Verify home page
        homeActions.verifyHomePageIsVisible();

        // Step 4: Click 'Cart' button
        cartActions.clickCartLink();

        // Step 5-6: Scroll to footer and verify 'SUBSCRIPTION' text
        subscriptionActions.scrollToFooter();
        subscriptionActions.verifySubscriptionTextVisible();

        // Step 7: Enter email and click subscribe (arrow) button
        String email = "student" + generate4Digit() + "@example.com";
        subscriptionActions.subscribeWithEmail(email);

        // Step 8: Verify success message
        subscriptionActions.verifySubscribeSuccessMessage();
    }

    @AfterMethod(alwaysRun = true)
    public void cleanup(ITestResult result) {
        afterMethod(result, "chrome");
    }
}