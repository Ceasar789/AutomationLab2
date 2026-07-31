package pageEvents;

import static org.testng.Assert.assertTrue;

import base.BaseTest;
import pageObjects.AutomationHomePage;

public class HomePageActions extends BaseTest {
    private AutomationHomePage homePage;

    public void init() {
        homePage = new AutomationHomePage(driver);
    }

    public void verifyHomePageIsVisible() {
        init();
        logger.info("Verify that home page is visible successfully");
        assertTrue(homePage.isHomeVisible(), "Home page is not visible");
    }

    public void clickSignupLoginButton() {
        init();
        logger.info("Click on 'Signup / Login' button");
        homePage.clickSignupLogin();
    }
}
