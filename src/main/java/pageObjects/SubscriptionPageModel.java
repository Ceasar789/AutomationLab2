package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

public class SubscriptionPageModel {
    private final WebDriver driver;

    private final By subscriptionHeader = By.xpath("//h2[contains(text(),'Subscription')]");
    private final By subscribeEmailInput = By.id("susbscribe_email");
    private final By subscribeButton = By.id("subscribe");
    private final By subscribeSuccessMessage = By.id("success-subscribe");

    public SubscriptionPageModel(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollToFooter() {
        WebElement footerElement = driver.findElement(subscriptionHeader);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", footerElement);
    }

    public boolean isSubscriptionTextVisible() {
        return !driver.findElements(subscriptionHeader).isEmpty() && driver.findElement(subscriptionHeader).isDisplayed();
    }

    public void typeSubscribeEmail(String email) {
        WebElement element = driver.findElement(subscribeEmailInput);
        element.clear();
        element.sendKeys(email);
    }

    public void clickSubscribeButton() {
        WebElement element = driver.findElement(subscribeButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public boolean isSubscribeSuccessMessageVisible() {
        return !driver.findElements(subscribeSuccessMessage).isEmpty() && driver.findElement(subscribeSuccessMessage).isDisplayed();
    }
}