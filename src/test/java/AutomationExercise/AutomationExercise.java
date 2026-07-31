package AutomationExercise;

import java.lang.reflect.Method;
import java.util.Dictionary;
import java.util.Hashtable;

import org.openqa.selenium.TimeoutException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseTest;
import pageEvents.homePageEvents;


public class AutomationExercise extends BaseTest{
	String browser;
	homePageEvents homePage = new homePageEvents();
	
	
	@BeforeTest(alwaysRun = true)
	@Parameters({"browser"})
	public void prepareReport(@Optional("chrome") String browser) {
		this.browser = browser;
		beforeTestMethod(browser);
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setup(Method testMethod) throws TimeoutException{
		initializeBrowser(browser, testMethod);
	}
	
  @Test (priority = 1)
  public void Test_Case_1_Register_User() {
	 homePage.Verify_that_home_page_is_visible_successfully();
	 homePage.Click_on_Signup_Login_button();
	  
  }

@Test (priority = 2)
  public void Test_Case_2_Login_User_with_correct_email_and_password() {
	 homePage.Verify_that_home_page_is_visible_successfully();
	 homePage.Click_on_Signup_Login_button();
	  
  }
  
  

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) {
		afterMethod(result, browser);
		
	}
	
}
	
	
