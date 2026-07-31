package pageEvents;

import base.BaseTest;
import pageObjects.CartPageModel;

public class CartActions extends BaseTest {
    private CartPageModel cartPage;

    private void init() {
        cartPage = new CartPageModel(driver);
    }

    public void clickCartLink() {
        init();
        logger.info("Navigate to Cart page");
        cartPage.clickCartLink();
    }
}