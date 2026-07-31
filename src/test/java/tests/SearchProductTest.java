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

public class SearchProductTest extends BaseTest {
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
    public void searchProduct() {
        String searchKeyword = "Dress";

        // Step 1: Go to Products page
        homeActions.verifyHomePageIsVisible();
        productsActions.clickProductsLink();
        productsActions.verifyAllProductsPageVisible();

        // Step 2: Search for a product
        productsActions.searchForProduct(searchKeyword);

        // Step 3: Verify 'SEARCHED PRODUCTS' header is visible
        productsActions.verifySearchedProductsHeaderVisible();

        // Step 4: Verify all displayed products relate to the search keyword
        productsActions.verifySearchResultsContainKeyword(searchKeyword);
    }

    @AfterMethod(alwaysRun = true)
    public void cleanup(ITestResult result) {
        afterMethod(result, "chrome");
    }
}