package tests;

import java.lang.reflect.Method;
import java.time.Duration;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pageEvents.HomePageActions;
import pageEvents.ProductsActions;
import utils.Config;

public class VerifyProductsTest extends BaseTest {
    private HomePageActions homeActions;
    private ProductsActions productsActions;

    @BeforeMethod(alwaysRun = true)
    public void setupTest(Method testMethod) {
        beforeTestMethod("chrome");
        logger = extent.createTest(testMethod.getName());
        setupDriver("chrome");
        driver.manage().window().maximize();
        driver.get(Config.BASE_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Config.IMPLICIT_WAIT_SECONDS));
        homeActions = new HomePageActions();
        productsActions = new ProductsActions();
    }

    @Test(priority = 1)
    public void verifyAllProductsAndProductDetailPage() {
        // Step 1: Go to Products page
        homeActions.verifyHomePageIsVisible();
        productsActions.clickProductsLink();
        productsActions.verifyAllProductsPageVisible();

        // Step 2: Verify products list is visible
        productsActions.verifyProductsListVisible();

        // Step 3: Get the total number of products
        int productCount = productsActions.getProductCount();
        logger.info("Total products found: " + productCount);

        // Step 4: Loop through EVERY product and verify its detail page
        for (int i = 0; i < productCount; i++) {
            productsActions.clickViewProductAt(i);
            productsActions.verifyProductDetailPage();
            productsActions.navigateBack();
            productsActions.verifyAllProductsPageVisible();
        }
    }

    @AfterMethod(alwaysRun = true)
    public void cleanup(ITestResult result) {
        afterMethod(result, "chrome");
    }
}