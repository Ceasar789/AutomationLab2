package tests;

import java.lang.reflect.Method;
import java.time.Duration;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pageEvents.HomePageActions;
import pageEvents.TestCasesActions;
import utils.Config;

public class TestCasesPageTest extends BaseTest {
    private HomePageActions homeActions;
    private TestCasesActions testCasesActions;

    @BeforeMethod(alwaysRun = true)
    public void setupTest(Method testMethod) {
        beforeTestMethod("chrome");
        logger = extent.createTest(testMethod.getName());
        setupDriver("chrome");
        driver.manage().window().maximize();
        driver.get(Config.BASE_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Config.IMPLICIT_WAIT_SECONDS));
        homeActions = new HomePageActions();
        testCasesActions = new TestCasesActions();
    }

    @Test(priority = 1)
    public void verifyTestCasesPage() {
        homeActions.verifyHomePageIsVisible();
        testCasesActions.clickTestCasesLink();
        testCasesActions.verifyTestCasesPageVisible();
    }

    @AfterMethod(alwaysRun = true)
    public void cleanup(ITestResult result) {
        afterMethod(result, "chrome");
    }
}