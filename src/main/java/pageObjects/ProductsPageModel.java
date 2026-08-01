package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class ProductsPageModel {
    private final WebDriver driver;

    private static final int DEFAULT_TIMEOUT_SECONDS = 15;

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

    /**
     * Reusable helper: waits explicitly for an element to become visible.
     * Returns false (instead of throwing) if it times out, so callers
     * can keep using it directly inside assertTrue(...).
     */
    private boolean isElementVisible(By locator, int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)) != null;
        } catch (TimeoutException e) {
            return false;
        }
    }

    private boolean isElementVisible(By locator) {
        return isElementVisible(locator, DEFAULT_TIMEOUT_SECONDS);
    }

    private void waitForClickable(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    private void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS))
            .until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    /**
     * Removes known ad iframes/overlays and closes any extra browser tabs/windows
     * an ad may have opened, restoring focus to the original window.
     * Call this before clicking any element that navigates the page, since ads
     * on this site can intercept clicks or spawn new tabs without throwing an
     * exception, silently leaving the driver on the previous page.
     */
    private void dismissInterceptingAdsAndExtraTabs() {
        try {
            ((JavascriptExecutor) driver).executeScript(
                    "document.querySelectorAll(" +
                    "'iframe[src*=\"googlesyndication\"], iframe[src*=\"doubleclick\"], " +
                    "ins.adsbygoogle, div[id^=\"google_ads_iframe\"], " +
                    "div[style*=\"position:absolute\"][style*=\"z-index:2147483647\"]'" +
                    ").forEach(function(el){ el.remove(); });"
            );
        } catch (Exception ignored) {
            // best-effort cleanup; don't fail the test if this script errors out
        }

        String originalHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        driver.switchTo().window(originalHandle);
    }

    private void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});" +
                "arguments[0].click();",
                element);
    }

    public void clickProductsLink() {
        dismissInterceptingAdsAndExtraTabs();
        waitForClickable(productsLink);
        WebElement element = driver.findElement(productsLink);
        try {
            element.click();
        } catch (Exception e) {
            jsClick(element);
        }
        dismissInterceptingAdsAndExtraTabs();
        waitForPageLoad();
    }

    public boolean isAllProductsPageVisible() {
        waitForPageLoad();
        if (isElementVisible(allProductsHeader, 20)) {
            return true;
        }

        // The public demo site occasionally slows down later in a run.
        // Re-click Products once (in case the first click was missed or the
        // page never finished navigating) before declaring a real failure.
        try {
            dismissInterceptingAdsAndExtraTabs();
            WebElement element = driver.findElement(productsLink);
            try {
                element.click();
            } catch (Exception e) {
                jsClick(element);
            }
            dismissInterceptingAdsAndExtraTabs();
            waitForPageLoad();
        } catch (Exception ignored) {
            // if the Products link isn't reachable, fall through and let the
            // final visibility check report the real result
        }

        return isElementVisible(allProductsHeader, 20);
    }

    public boolean isProductsListVisible() {
        List<WebElement> items = driver.findElements(productItems);
        return !items.isEmpty();
    }

    public int getProductCount() {
        return driver.findElements(viewProductLinks).size();
    }

    public void clickViewProductAt(int index) {
        dismissInterceptingAdsAndExtraTabs();
        List<WebElement> links = driver.findElements(viewProductLinks);
        WebElement element = links.get(index);
        jsClick(element);
        dismissInterceptingAdsAndExtraTabs();
    }

    public void navigateBack() {
        driver.navigate().back();
        dismissInterceptingAdsAndExtraTabs();
        waitForPageLoad();
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS))
            .until(webDriver -> webDriver.findElements(allProductsHeader).size() > 0 || webDriver.findElements(productItems).size() > 0);
    }

    // ===== Search Product methods =====
    public void typeSearchProduct(String productName) {
        WebElement element = driver.findElement(searchInput);
        element.clear();
        element.sendKeys(productName);
    }

    public void clickSearchButton() {
        dismissInterceptingAdsAndExtraTabs();
        WebElement element = driver.findElement(searchButton);
        try {
            element.click();
        } catch (Exception e) {
            jsClick(element);
        }
        dismissInterceptingAdsAndExtraTabs();
    }

    public boolean isSearchedProductsHeaderVisible() {
        return isElementVisible(searchedProductsHeader, 20);
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
        return isElementVisible(productInformation);
    }

    public boolean isProductNameVisible() {
        return isElementVisible(productName);
    }

    public boolean isProductPriceVisible() {
        return isElementVisible(productPrice);
    }

    public boolean isProductCategoryVisible() {
        return isElementVisible(productCategory);
    }

    public boolean isProductAvailabilityVisible() {
        return isElementVisible(productAvailability);
    }

    public boolean isProductConditionVisible() {
        return isElementVisible(productCondition);
    }

    public boolean isProductBrandVisible() {
        return isElementVisible(productBrand);
    }
}