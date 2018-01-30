package com.Apptimice.Automation.testscript;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Apptimice.Automation.TestBase;
import com.Apptimice.Automation.WebUIAutomation;

public class ResendemailTest extends TestBase {

	@BeforeClass
	public void doIhaveToSkip() {

		// logger.debug("Check the Test Case Run-mode");

		if (!(WebUIAutomation.checkTestCaseRunmode(this.getClass().getSimpleName()))) {

			throw new SkipException("Runmode set to No");
		}
		launchBrowser();
	}
	
	@Test(dataProvider = "getData")

	public static void resend(String email, String actual_error_message, String invalid_email_address_msg, String invalid_Testdata, String actual_Final_Message ) throws InterruptedException{
		
		// Clicking on the resend email button 
		
		WebUIAutomation.clickObj("Btn_Resende_email_resend");
		
		Thread.sleep(1000);
		
		
		// Clicking on the Resend button 
		
		WebUIAutomation.clickObj("Btn_Resend_email_resendbutton");
		
		
		// Verify the text that email is required 
		
		String expected_error_message = WebUIAutomation.getMsg("Msg_Resend_email_EmailRequired");
		
		Thread.sleep(1000);
		
		Assert.assertEquals(actual_error_message, expected_error_message);
		
	  //Entering the invalid email
		
		Assert.assertTrue(WebUIAutomation.setText("Txtbox_Resend_email_Email", invalid_Testdata),
				"User uis trying to enter Incorrect email");
		
		Thread.sleep(1000);
		
		//Click onResend button
		
		WebUIAutomation.clickObj("Btn_Resend_email_resendbutton");
		
		// Clicking on the back button
		
		
		
		//Entering the valid email
		
		WebUIAutomation.setText("Txtbox_Resend_email_Email", email);
		
		Thread.sleep(1000);
		
		// Clicking on the Resend button 	
		
		WebUIAutomation.clickObj("Btn_Resend_email_resendbutton");
		
		// Verifying the confirmation text 
		
		String expected_Final_Message = WebUIAutomation.getMsg("Msg_Resend_email_confirmation_email");
		
		Assert.assertEquals(actual_Final_Message, expected_Final_Message);
		
		}
	
	@DataProvider
	public Object[][] getData() {
		return WebUIAutomation.getData("ResendemailTest");
	}

	@AfterTest
	public void logOut()

	{
		if (loggedIn)
			wbDv.quit();

	}
}


