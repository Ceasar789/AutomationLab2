package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import java.util.List;

public class ProductsPageModel {
    private final WebDriver driver;

    private final By productsLink = By.xpath("//a[@href='/products']");
    private final By allProductsHeader = By.xpath("//h2[contains(text(),'All Products')]");
    private final By productItems = By.xpath("//div[contains(@class,'product-image-wrapper')]");
    private final By viewProductLinks = By.xpath("//a[contains(text(),'View Product')]");

    // ===== Search Product locators =====
    private final By searchInput = By.id("search_product");
    private final By searchButton = By.id("submit_search");
    private final By searchedProductsHeader = By.xpath("//h2[contains(text(),'Searched Products')]");
    private final By searchResultItems = By.xpath("//div[contains(@class,'product-image-wrapper')]");
    private final By productNames = By.xpath("//div[contains(@class,'productinfo')]/p");

    // ===== Product Detail locators =====
    private final By productInformation = By.xpath("//div[contains(@class,'product-information')]");
    private final By productName = By.xpath("//div[contains(@class,'product-information')]//h2");
    private final By productCategory = By.xpath("//div[contains(@class,'product-information')]//p[contains(.,'Category')]");
    private final By productPrice = By.xpath("//div[contains(@class,'product-information')]//span[contains(text(),'Rs.')]");
    private final By productAvailability = By.xpath("//div[contains(@class,'product-information')]//p[contains(.,'Availability')]");
    private final By productCondition = By.xpath("//div[contains(@class,'product-information')]//p[contains(.,'Condition')]");
    private final By productBrand = By.xpath("//div[contains(@class,'product-information')]//p[contains(.,'Brand')]");

    public ProductsPageModel(WebDriver driver) {
        this.driver = driver;
    }

    public void clickProductsLink() {
        driver.findElement(productsLink).click();
    }

    public boolean isAllProductsPageVisible() {
        return !driver.findElements(allProductsHeader).isEmpty() && driver.findElement(allProductsHeader).isDisplayed();
    }

    public boolean isProductsListVisible() {
        List<org.openqa.selenium.WebElement> items = driver.findElements(productItems);
        return !items.isEmpty();
    }

    public int getProductCount() {
        return driver.findElements(viewProductLinks).size();
    }

    public void clickViewProductAt(int index) {
        List<WebElement> links = driver.findElements(viewProductLinks);
        WebElement element = links.get(index);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void navigateBack() {
        driver.navigate().back();
    }

    // ===== Search Product methods =====
    public void typeSearchProduct(String productName) {
        WebElement element = driver.findElement(searchInput);
        element.clear();
        element.sendKeys(productName);
    }

    public void clickSearchButton() {
        driver.findElement(searchButton).click();
    }

    public boolean isSearchedProductsHeaderVisible() {
        return !driver.findElements(searchedProductsHeader).isEmpty() && driver.findElement(searchedProductsHeader).isDisplayed();
    }

    public int getSearchResultCount() {
        return driver.findElements(searchResultItems).size();
    }

    public List<String> getSearchResultProductNames() {
        List<WebElement> elements = driver.findElements(productNames);
        List<String> names = new java.util.ArrayList<>();
        for (WebElement e : elements) {
            names.add(e.getText().trim());
        }
        return names;
    }

    public boolean isProductInformationVisible() {
        return !driver.findElements(productInformation).isEmpty() && driver.findElement(productInformation).isDisplayed();
    }

    public boolean isProductNameVisible() {
        return !driver.findElements(productName).isEmpty() && driver.findElement(productName).isDisplayed();
    }

    public boolean isProductPriceVisible() {
        return !driver.findElements(productPrice).isEmpty() && driver.findElement(productPrice).isDisplayed();
    }

    public boolean isProductCategoryVisible() {
        return !driver.findElements(productCategory).isEmpty() && driver.findElement(productCategory).isDisplayed();
    }

    public boolean isProductAvailabilityVisible() {
        return !driver.findElements(productAvailability).isEmpty() && driver.findElement(productAvailability).isDisplayed();
    }

    public boolean isProductConditionVisible() {
        return !driver.findElements(productCondition).isEmpty() && driver.findElement(productCondition).isDisplayed();
    }

    public boolean isProductBrandVisible() {
        return !driver.findElements(productBrand).isEmpty() && driver.findElement(productBrand).isDisplayed();
    }
}