package pageEvents;

import static org.testng.Assert.assertTrue;

import base.BaseTest;
import pageObjects.TestCasesPageModel;

public class TestCasesActions extends BaseTest {
    private TestCasesPageModel testCasesPage;

    private void init() {
        testCasesPage = new TestCasesPageModel(driver);
    }

    public void clickTestCasesLink() {
        init();
        logger.info("Navigate to Test Cases page");
        testCasesPage.clickTestCasesLink();
    }

    public void verifyTestCasesPageVisible() {
        init();
        logger.info("Verify that Test Cases page is visible");
        assertTrue(testCasesPage.isTestCasesPageVisible(), "Test Cases page header is not visible");
    }
}