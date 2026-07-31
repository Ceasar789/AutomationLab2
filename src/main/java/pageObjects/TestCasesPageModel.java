package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestCasesPageModel {
    private final WebDriver driver;

    private final By testCasesLink = By.xpath("//a[@href='/test_cases']");
    private final By testCasesBreadcrumb = By.xpath("//li[contains(@class,'active') and contains(text(),'Test Cases')]");

    public TestCasesPageModel(WebDriver driver) {
        this.driver = driver;
    }

    public void clickTestCasesLink() {
        driver.findElement(testCasesLink).click();
    }

    public boolean isTestCasesPageVisible() {
        boolean breadcrumbVisible = !driver.findElements(testCasesBreadcrumb).isEmpty()
                && driver.findElement(testCasesBreadcrumb).isDisplayed();
        boolean urlContainsTestCases = driver.getCurrentUrl().contains("test_cases");
        return breadcrumbVisible || urlContainsTestCases;
    }
}