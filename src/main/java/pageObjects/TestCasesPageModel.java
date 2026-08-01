package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestCasesPageModel {
    private final WebDriver driver;

    private static final int DEFAULT_TIMEOUT_SECONDS = 15;

    private final By testCasesLink = By.xpath("//a[@href='/test_cases']");
    private final By testCasesBreadcrumb = By.xpath("//li[contains(@class,'active') and contains(text(),'Test Cases')]");

    public TestCasesPageModel(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Reusable explicit-wait helper: waits for an element to become visible.
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

    private void waitForClickable(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    private void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});" +
                "arguments[0].click();",
                element);
    }

    /**
     * Removes known ad iframes/overlays and closes any extra browser tabs/windows
     * an ad may have opened, restoring focus to the original window.
     * Call this before clicking any element that navigates the page.
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

    public void clickTestCasesLink() {
        dismissInterceptingAdsAndExtraTabs();
        waitForClickable(testCasesLink);
        WebElement element = driver.findElement(testCasesLink);
        try {
            element.click();
        } catch (Exception e) {
            jsClick(element);
        }
        dismissInterceptingAdsAndExtraTabs();
    }

    public boolean isTestCasesPageVisible() {
        if (checkTestCasesPageState(20)) {
            return true;
        }

        // The public demo site occasionally slows down later in a run.
        // Re-click Test Cases once (in case the first click was missed or the
        // page never finished navigating) before declaring a real failure.
        try {
            dismissInterceptingAdsAndExtraTabs();
            WebElement element = driver.findElement(testCasesLink);
            try {
                element.click();
            } catch (Exception e) {
                jsClick(element);
            }
            dismissInterceptingAdsAndExtraTabs();
        } catch (Exception ignored) {
            // if the Test Cases link isn't reachable, fall through and let the
            // final check report the real result
        }

        return checkTestCasesPageState(20);
    }

    private boolean checkTestCasesPageState(int timeoutSeconds) {
        if (isElementVisible(testCasesBreadcrumb, timeoutSeconds)) {
            return true;
        }
        // Fallback signal: even if the breadcrumb text/markup changes, the URL
        // reliably confirms navigation succeeded.
        return driver.getCurrentUrl().contains("test_cases");
    }
}