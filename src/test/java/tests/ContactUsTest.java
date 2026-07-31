package tests;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.time.Duration;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pageEvents.HomePageActions;
import pageEvents.ContactUsActions;
import utils.Config;

public class ContactUsTest extends BaseTest {
    private HomePageActions homeActions;
    private ContactUsActions contactUsActions;

    @BeforeMethod(alwaysRun = true)
    public void setupTest(Method testMethod) {
        beforeTestMethod("chrome");
        logger = extent.createTest(testMethod.getName());
        setupDriver("chrome");
        driver.manage().window().maximize();
        driver.get(Config.BASE_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Config.IMPLICIT_WAIT_SECONDS));
        homeActions = new HomePageActions();
        contactUsActions = new ContactUsActions();
    }

    @Test(priority = 1)
    public void submitContactUsForm() throws Exception {
        // Step 1: Verify home page and navigate to Contact Us
        homeActions.verifyHomePageIsVisible();
        contactUsActions.clickContactUsLink();
        contactUsActions.verifyGetInTouchVisible();

        // Step 2: Fill the contact form
        String email = "student" + generate4Digit() + "@example.com";
        contactUsActions.fillContactForm(
                "TestUser",
                email,
                "Test Subject",
                "This is a test message submitted via automated test."
        );

        // Step 3: Create a small dummy file at runtime and upload it
        File tempFile = File.createTempFile("sample_upload", ".txt");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("This is a sample file for the Contact Us upload test.");
        }
        contactUsActions.uploadFile(tempFile.getAbsolutePath());

        // Step 4: Submit form and accept the confirmation alert
        contactUsActions.submitFormAndAcceptAlert();

        // Step 5: Verify success message
        contactUsActions.verifySuccessMessage();

        // Step 6: Click Home button and verify home page is visible again
        contactUsActions.clickHomeButton();
        homeActions.verifyHomePageIsVisible();

        // Cleanup: delete the temp file
        tempFile.deleteOnExit();
    }

    @AfterMethod(alwaysRun = true)
    public void cleanup(ITestResult result) {
        afterMethod(result, "chrome");
    }
}