package com.Apptimice.Automation.testscript;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Apptimice.Automation.Helper;
import com.Apptimice.Automation.TestBase;
import com.Apptimice.Automation.WebUIAutomation;

public class SignupTest extends TestBase {

	@BeforeClass
	public void doIhaveToSkip() {

		// logger.debug("Check the Test Case Run-mode");

		if (!(WebUIAutomation.checkTestCaseRunmode(this.getClass().getSimpleName()))) {

			throw new SkipException("Runmode set to No");
		}
		launchBrowser();
	}

	@Test(dataProvider = "getData")
	public static void signupTest(String actual_error_message1, String actual_error_message2,String actual_error_message3, String actual_error_message4, String name, String companyName, String country, String password,
			String confirmpassword, String BuildingNumber, String UnitNumber, String StreetName, String City,
			String PostalCode ) throws InterruptedException {
		WebUIAutomation.isObjPresent("Lnk_signup_Createanaccount", 10);
		
		// Click on the Create account link
		
		WebUIAutomation.clickObj("Lnk_signup_Createanaccount");
		  Thread.sleep(1000);
		
		// Scrolling the page 
		
		((JavascriptExecutor) driver).executeScript("scroll(0,300)");
		
         // Click on the submit button 
		
		WebUIAutomation.clickObj("Btn_Signup_Signup");
		
		
		
		// Verify the enter name 
		
           String expected_error_message = WebUIAutomation.getMsg("Msg_Signup_Nameerror");
		
		    Thread.sleep(1000);
		    
		    System.out.println(expected_error_message);
		
		Assert.assertNotEquals(actual_error_message1, expected_error_message);
		
		// Verify the Enter company name 
		
		 String expected_error_message1 = WebUIAutomation.getMsg("Msg_Signup_Companyerror");
			
		    Thread.sleep(1000);
		
		    Assert.assertNotEquals(actual_error_message2, expected_error_message1);
		
		
		// Verify that the email is required
		
		
		 String expected_error_message11 = WebUIAutomation.getMsg("Msg_signup_Emailerror");
			
		    Thread.sleep(1000);
		
		 Assert.assertNotEquals(actual_error_message3, expected_error_message11);
		
		
		//verify the enter password 
		
		 String expected_error_message111 = WebUIAutomation.getMsg("Msg_signup_Passworderror");
			
		    Thread.sleep(1000);
		
		Assert.assertNotEquals(actual_error_message4, expected_error_message111);
		
		// Scroll the page up 
		
		 JavascriptExecutor jse = (JavascriptExecutor) driver;
		 
		 System.out.println(" testing");
		 
		    jse.executeScript("window.scrollBy(250,0)", "");
		
		// Enter Name 
		
		WebUIAutomation.setText("Txtbox_signup_Name", name);
		
		// Enter company name 
		
		WebUIAutomation.setText("Txtbox_signup_Company", companyName);

		// Select Country

		driver.findElement(By.className("select2-choice")).click();

		List<WebElement> countryOptions = driver.findElements(By.xpath("//ul[@class='select2-results']/li"));

		// System.out.println(countryOptions);

		for (WebElement option : countryOptions) {

			// System.out.println("Country:" + option.getText().trim());

			if ((option.getText().trim()).equalsIgnoreCase(country)) {
				// System.out.println("Matched");
				option.click();
				break;
			}

		}

		// Enter Email
		WebUIAutomation.setText("Txtbox_signup_Email", Helper.getUniqueEmail());

		// Enter password
		WebUIAutomation.setText("Txtbox_singup_Password", password);

		// Enter Confirm Password
		WebUIAutomation.setText("Txtbox_signup_Confirmpassword", confirmpassword);

		WebUIAutomation.clickObj("Lnk_signup_Moredetails");

		// Enter building name 
		
		WebUIAutomation.setText("Txtbox_Signup_Buildingname", BuildingNumber);
		
		// Enter unit number
		
		WebUIAutomation.setText("Txtbox_Signup_Unitnumber", UnitNumber);
		
		 // Enter street name 
		
		WebUIAutomation.setText("Txtbox_Signup_Streetname", StreetName);
		
		 // Enter the city name
		
		WebUIAutomation.setText("Txtbox_Signup_City", City);
		
		// Enter postal code 
		
		WebUIAutomation.setText("Txtbox_Signup_Postalcode", PostalCode);

		
		System.out.println(WebUIAutomation.isObjPresent("Btn_Signup_Signup", 10));
		System.out.println(WebUIAutomation.getMsg("Btn_Signup_Signup"));

		Helper.scrollToPageEnd(wbDv);
		WebUIAutomation.clickObj("Btn_Signup_Signup");

//		Actions action = new Actions(wbDv);
//		action.moveToElement(WebUIAutomation.getObject("Btn_Signup_Signup")).perform();
//		action.click(WebUIAutomation.getObject("Btn_Signup_Signup")).perform();
//		action.release();
	}

	@DataProvider
	public Object[][] getData() {
		return WebUIAutomation.getData("signupTest");
	}

	@AfterTest
	public void logOut()

	{
		if (loggedIn)
			wbDv.quit();

	}

}
