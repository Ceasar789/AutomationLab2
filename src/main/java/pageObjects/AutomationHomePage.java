package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AutomationHomePage {
    private final WebDriver driver;

    private final By homeLabel = By.xpath("//a[normalize-space()='Home']");
    private final By signupLoginLink = By.xpath("//a[normalize-space()='Signup / Login']");

    public AutomationHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isHomeVisible() {
        return !driver.findElements(homeLabel).isEmpty() && driver.findElement(homeLabel).isDisplayed();
    }

    public void clickSignupLogin() {
        WebElement element = driver.findElement(signupLoginLink);
        element.click();
    }
}
