package com.Apptimice.Automation.testscript;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Apptimice.Automation.TestBase;
import com.Apptimice.Automation.WebUIAutomation;

public class LoginTest extends TestBase {

	// public static Logger logger =
	// LoggerFactory.getLogger(JobCreationTest.class.getName());

	@BeforeClass
	public void doIhaveToSkip() {

		// logger.debug("Check the Test Case Run-mode");

		if (!(WebUIAutomation.checkTestCaseRunmode(this.getClass().getSimpleName()))) {

			throw new SkipException("Runmode set to No");
		}
		launchBrowser();
	}

	@Test(dataProvider = "getData")

	public static void loginTest(String scenarioType, String username, String password, String expectedresult)
			throws InterruptedException

	{
		try {

			if (scenarioType.equalsIgnoreCase("invalid"))

			{
				if (!WebUIAutomation.setText("txtfield_Username_LoginPage", username))

				{
					Assert.fail("User does not exist ");
				}

				// Provide the password.

				if (!WebUIAutomation.setText("txtfield_Password_LoginPage", password))

				{
					Assert.fail("Incorrect password");
				}
				
				WebUIAutomation.clickObj("Btn_Login_LoginPage");
				Thread.sleep(5000);

				String actualResult = WebUIAutomation.getMsg("Msg_UsernameExpectedMsg_LoginPgae");
				System.err.println(actualResult);
				Assert.assertEquals(expectedresult, actualResult);

			} else if (scenarioType.equalsIgnoreCase("formValidation"))

			{
				if (!WebUIAutomation.setText("txtfield_Username_LoginPage", username))

				{
					Assert.fail("UserName textfield is not working");
				}

				// Provide the password.

				if (!WebUIAutomation.setText("txtfield_Password_LoginPage", password))

				{
					Assert.fail("Password textfield is not working");
				}
				

				WebUIAutomation.clickObj("Btn_Login_LoginPage");

				String actualResult1 = WebUIAutomation.getMsg("Msg_NillExpectedMsg_LoginPage");
				System.err.println(actualResult1);
				Assert.assertEquals(expectedresult, actualResult1);
			}

			else if (scenarioType.equalsIgnoreCase("valid"))

			{
				if (!(WebUIAutomation.setText("txtfield_Username_LoginPage", username)))

				{
					Assert.fail("UserName textfield is not working");
				}

				// Provide the password.

				if (!WebUIAutomation.setText("txtfield_Password_LoginPage", password))

				{
					Assert.fail("Incorrect password");
				}
				//
				WebUIAutomation.clickObj("Btn_Login_LoginPage");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DataProvider
	public Object[][] getData() {
		return WebUIAutomation.getData("loginTest");
	}

	@AfterTest
	public void logOut()

	{
		if (loggedIn)
			wbDv.quit();

	}

}
