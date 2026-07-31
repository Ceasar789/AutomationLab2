package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Alert;

public class ContactUsPageModel {
    private final WebDriver driver;

    private final By contactUsLink = By.xpath("//a[@href='/contact_us']");
    private final By getInTouchHeader = By.xpath("//h2[normalize-space()='Get In Touch']");
    private final By nameInput = By.xpath("//input[@data-qa='name']");
    private final By emailInput = By.xpath("//input[@data-qa='email']");
    private final By subjectInput = By.xpath("//input[@data-qa='subject']");
    private final By messageTextarea = By.xpath("//textarea[@data-qa='message']");
    private final By uploadFileInput = By.name("upload_file");
    private final By submitButton = By.xpath("//input[@data-qa='submit-button']");
    private final By successMessage = By.xpath("//div[contains(@class,'alert-success')]");
    private final By homeButton = By.xpath("//a[@href='/' and contains(@class,'btn-success')]");

    public ContactUsPageModel(WebDriver driver) {
        this.driver = driver;
    }

    public void clickContactUsLink() {
        driver.findElement(contactUsLink).click();
    }

    public boolean isGetInTouchVisible() {
        return !driver.findElements(getInTouchHeader).isEmpty() && driver.findElement(getInTouchHeader).isDisplayed();
    }

    public void typeName(String name) {
        WebElement element = driver.findElement(nameInput);
        element.clear();
        element.sendKeys(name);
    }

    public void typeEmail(String email) {
        WebElement element = driver.findElement(emailInput);
        element.clear();
        element.sendKeys(email);
    }

    public void typeSubject(String subject) {
        WebElement element = driver.findElement(subjectInput);
        element.clear();
        element.sendKeys(subject);
    }

    public void typeMessage(String message) {
        WebElement element = driver.findElement(messageTextarea);
        element.clear();
        element.sendKeys(message);
    }

    public void uploadFile(String filePath) {
        driver.findElement(uploadFileInput).sendKeys(filePath);
    }

    public void clickSubmitButton() {
        driver.findElement(submitButton).click();
    }

    public void acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public boolean isSuccessMessageVisible() {
        return !driver.findElements(successMessage).isEmpty() && driver.findElement(successMessage).isDisplayed();
    }

    public void clickHomeButton() {
        driver.findElement(homeButton).click();
    }
}