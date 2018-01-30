package com.Apptimice.Automation.testscript;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Apptimice.Automation.TestBase;
import com.Apptimice.Automation.WebUIAutomation;

public class ForgotpasswordTest extends TestBase {

	@BeforeClass
	public void doIhaveToSkip() {

		// logger.debug("Check the Test Case Run-mode");

		if (!(WebUIAutomation.checkTestCaseRunmode(this.getClass().getSimpleName()))) {

			throw new SkipException("Runmode set to No");
		}
		launchBrowser();
	}

	@Test(dataProvider = "getData")
	public static void forgotpass(String email, String actual_error_message, String invalid_email_address_msg,
			String invalid_Testdata, String actual_Final_Message) throws InterruptedException {

		// Click on the forgot password link

		WebUIAutomation.clickObj("Btn_Forgot_password");

		Thread.sleep(2000);

		// Verify the user lands upon the FP page.

		Assert.assertTrue(WebUIAutomation.isElementPresent("Txtbox_Forgot_password_email"),
				"User has not landed on the Forgot password Screen");

		Thread.sleep(2000);

		// Click on the submit button.

		WebUIAutomation.clickObj("Btn_Forgot_password_Submit");

		Thread.sleep(2000);

		// Grab the expected email error message.

		String expected_error_message = WebUIAutomation.getMsg("Msg_Forgot_password_EmailRequired");

		Thread.sleep(2000);

		// verify the error message.

		Assert.assertEquals(actual_error_message, expected_error_message);

		Thread.sleep(2000);

		// Enter invalid email address

		Assert.assertTrue(WebUIAutomation.setText("Txtbox_Forgot_password_email", invalid_Testdata),
				"User uis trying to enter Incorrect email");

		Thread.sleep(2000);

		// click on the Submit button

		WebUIAutomation.clickObj("Btn_Forgot_password_Submit");

		Thread.sleep(2000);

		// Navigate to the Login Screen

		Assert.assertTrue(WebUIAutomation.clickObj("Logo_Forgot_password_Logo"),
				"User sis not able to click on the apptimice logo");

		Thread.sleep(5000);

		// Enter Email

		WebUIAutomation.clickObj("Btn_Forgot_password");

		Thread.sleep(4000);

		// Enter the email address

		WebUIAutomation.setText("Txtbox_Forgot_password_email", email);

		// Click on the submit button.

		WebUIAutomation.clickObj("Btn_Forgot_password_Submit");

		Thread.sleep(3000);

		// Grab the error message.

		String expected_Final_Message = WebUIAutomation.getMsg("Msg_Forgot_password_FinalMessage");
		
		System.out.println("expected Message : " + expected_Final_Message);
		System.out.println("Actual  Message : " + actual_Final_Message);

		// Verify the final message.

		Assert.assertEquals(actual_Final_Message, expected_Final_Message);

		Thread.sleep(3000);

	}

	@DataProvider
	public Object[][] getData() {
		return WebUIAutomation.getData("ForgotpasswordTest");
	}

	@AfterTest
	public void logOut()

	{
		if (loggedIn)
			wbDv.quit();

	}

}