package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPageModel {
    private final WebDriver driver;

    private final By cartLink = By.xpath("//a[@href='/view_cart']");

    public CartPageModel(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCartLink() {
        driver.findElement(cartLink).click();
    }
}