package pageEvents;

import static org.testng.Assert.assertTrue;

import base.BaseTest;
import pageObjects.ContactUsPageModel;

public class ContactUsActions extends BaseTest {
    private ContactUsPageModel contactUsPage;

    private void init() {
        contactUsPage = new ContactUsPageModel(driver);
    }

    public void clickContactUsLink() {
        init();
        logger.info("Navigate to Contact Us page");
        contactUsPage.clickContactUsLink();
    }

    public void verifyGetInTouchVisible() {
        init();
        logger.info("Verify that 'GET IN TOUCH' section is visible");
        assertTrue(contactUsPage.isGetInTouchVisible(), "'Get In Touch' header is not visible");
    }

    public void fillContactForm(String name, String email, String subject, String message) {
        init();
        logger.info("Fill the contact us form fields");
        contactUsPage.typeName(name);
        contactUsPage.typeEmail(email);
        contactUsPage.typeSubject(subject);
        contactUsPage.typeMessage(message);
    }

    public void uploadFile(String filePath) {
        init();
        logger.info("Upload a file in the contact us form");
        contactUsPage.uploadFile(filePath);
    }

    public void submitFormAndAcceptAlert() {
        init();
        logger.info("Submit the contact us form and accept the confirmation alert");
        contactUsPage.clickSubmitButton();
        contactUsPage.acceptAlert();
    }

    public void verifySuccessMessage() {
        init();
        logger.info("Verify that success message is visible after form submission");
        assertTrue(contactUsPage.isSuccessMessageVisible(), "Success message is not visible after submitting contact form");
    }

    public void clickHomeButton() {
        init();
        logger.info("Click Home button to return to home page");
        contactUsPage.clickHomeButton();
    }
}