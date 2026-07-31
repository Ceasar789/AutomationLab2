package pageEvents;

import static org.testng.Assert.assertTrue;

import base.BaseTest;
import pageObjects.SubscriptionPageModel;

public class SubscriptionActions extends BaseTest {
    private SubscriptionPageModel subscriptionPage;

    private void init() {
        subscriptionPage = new SubscriptionPageModel(driver);
    }

    public void scrollToFooter() {
        init();
        logger.info("Scroll down to footer");
        subscriptionPage.scrollToFooter();
    }

    public void verifySubscriptionTextVisible() {
        init();
        logger.info("Verify 'SUBSCRIPTION' text is visible");
        assertTrue(subscriptionPage.isSubscriptionTextVisible(), "'Subscription' text is not visible");
    }

    public void subscribeWithEmail(String email) {
        init();
        logger.info("Enter email and subscribe: " + email);
        subscriptionPage.typeSubscribeEmail(email);
        subscriptionPage.clickSubscribeButton();
    }

    public void verifySubscribeSuccessMessage() {
        init();
        logger.info("Verify subscription success message is visible");
        assertTrue(subscriptionPage.isSubscribeSuccessMessageVisible(), "Subscription success message is not visible");
    }
}