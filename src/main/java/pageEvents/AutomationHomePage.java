package pageEvents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutomationHomePage {
    private final WebDriver driver;
    private static final int DEFAULT_TIMEOUT_SECONDS = 10;

    private final By homeLabel = By.xpath("//a[normalize-space()='Home']");
    private final By signupLoginLink = By.xpath("//a[normalize-space()='Signup / Login']");

    public AutomationHomePage(WebDriver driver) {
        this.driver = driver;
    }

    private boolean isElementVisible(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)) != null;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isHomeVisible() {
        return isElementVisible(homeLabel);
    }

    public void clickSignupLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(signupLoginLink));
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}