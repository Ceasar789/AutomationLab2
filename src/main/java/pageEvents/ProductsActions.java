package pageEvents;

import static org.testng.Assert.assertTrue;

import base.BaseTest;
import pageObjects.ProductsPageModel;

public class ProductsActions extends BaseTest {
    private ProductsPageModel productsPage;

    private void init() {
        productsPage = new ProductsPageModel(driver);
    }

    public void clickProductsLink() {
        init();
        logger.info("Navigate to Products page");
        productsPage.clickProductsLink();
    }

    public void verifyAllProductsPageVisible() {
        init();
        logger.info("Verify that All Products page is visible");
        assertTrue(productsPage.isAllProductsPageVisible(), "'All Products' header is not visible");
    }

    public void verifyProductsListVisible() {
        init();
        logger.info("Verify that products list is visible");
        assertTrue(productsPage.isProductsListVisible(), "Products list is not visible");
    }

    public int getProductCount() {
        init();
        return productsPage.getProductCount();
    }

    public void clickViewProductAt(int index) {
        init();
        logger.info("Click 'View Product' on product index " + index);
        productsPage.clickViewProductAt(index);
    }

    public void navigateBack() {
        init();
        productsPage.navigateBack();
    }

    // ===== Search Product =====
    public void searchForProduct(String productName) {
        init();
        logger.info("Search for product: " + productName);
        productsPage.typeSearchProduct(productName);
        productsPage.clickSearchButton();
    }

    public void verifySearchedProductsHeaderVisible() {
        init();
        logger.info("Verify 'SEARCHED PRODUCTS' header is visible");
        assertTrue(productsPage.isSearchedProductsHeaderVisible(), "'Searched Products' header is not visible");
    }

    public void verifySearchResultsContainKeyword(String keyword) {
        init();
        logger.info("Verify search results relate to keyword: " + keyword);
        int count = productsPage.getSearchResultCount();
        assertTrue(count > 0, "Expected at least one search result for '" + keyword + "'");

        java.util.List<String> names = productsPage.getSearchResultProductNames();
        int matchCount = 0;
        for (String name : names) {
            if (name.toLowerCase().contains(keyword.toLowerCase())) {
                matchCount++;
            } else {
                logger.info("Product name did not contain keyword (site search may be fuzzy): " + name);
            }
        }
        assertTrue(matchCount > 0, "Expected at least one search result whose name relates to '" + keyword + "'");
    }

    public void verifyProductDetailPage() {
        init();
        logger.info("Verify that product detail page shows name, price, category, availability, condition, brand");
        assertTrue(productsPage.isProductInformationVisible(), "Product information section is not visible");
        assertTrue(productsPage.isProductNameVisible(), "Product name is not visible");
        assertTrue(productsPage.isProductPriceVisible(), "Product price is not visible");
        assertTrue(productsPage.isProductCategoryVisible(), "Product category is not visible");
        assertTrue(productsPage.isProductAvailabilityVisible(), "Product availability is not visible");
        assertTrue(productsPage.isProductConditionVisible(), "Product condition is not visible");
        assertTrue(productsPage.isProductBrandVisible(), "Product brand is not visible");
    }
}