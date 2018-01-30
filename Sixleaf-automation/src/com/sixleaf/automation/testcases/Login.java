/**
 * Test Description: Login functionality
 * Prerequisites: 
 * a) valid confirmed login credential
 * b) valid unconfirmed login credential
 *
 *  Limitation: NA
 *  @author Niharika
 */

package com.sixleaf.automation.testcases;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sixleaf.automation.base.TestBase;
import com.sixleaf.automation.utility.WebUIAutomation;
import com.sixleaf.automation.testcases.Login;

public class Login extends TestBase {

	@BeforeClass
	public void doIhaveToSkip() {

		// logger.debug("Check the Test Case Run-mode");
		if (!(WebUIAutomation.checkTestCaseRunmode(this.getClass().getSimpleName()))) {

			throw new SkipException("Runmode set to No");
		}

		// Launching Browser
		//initialize();
	}

	/**
	 * Test Login functionality
	 * 
	 * @param scenarioType
	 * @param email
	 * @param password
	 * @param expectedResult
	 */
	@Test(dataProvider = "getData")
	public static void loginTest(String scenarioType, String email, String password, String expectedresult) {
		
		try {

			// Click on email text box
//			Assert.assertTrue(WebUIAutomation.clickObj("txtfield_Email_LoginPage"),
//					"Clicking on Login button is not successful");

			// To enter Email in text box
			Assert.assertTrue(WebUIAutomation.setText("txtfield_Email_LoginPage", email),
					"User is unable to enter username");

			// To enter Password in text box
			Assert.assertTrue(WebUIAutomation.setText("txtfield_Password_LoginPage", password),
					"User is unable to enter Password");
			

			// To click on Login button
			Assert.assertTrue(WebUIAutomation.clickObj("Btn_Login_LoginPage"),
					"Clicking on Login button is not successful");
			
			//To Click on drop-down after login
			Assert.assertTrue(WebUIAutomation.clickObj("dropdown_arrow_HomePage"),
					"Clicking on drop-down arrow is not successful");
			
			//To Click on Logout
			Assert.assertTrue(WebUIAutomation.clickObj("listitem_Logout_HomePage"),
					"Clicking on logout is not successful");
			
			// Verify Login Functionality
			switch (scenarioType.toLowerCase().trim()) {

			case "valid":
				Assert.assertTrue(WebUIAutomation.isElementPresent("txtfield_Zonblast_HomePage"),
						"Login is Unsuccessful");
				break;
		
			case "invalid":
				// To check for validation
				String errorMsg = WebUIAutomation.getMsg("Msg_UsernameExpectedMsg_LoginPage");
				System.err.println("-" + errorMsg);
				Assert.assertEquals(errorMsg, expectedresult, "Error message doesn't matched.");
				
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@DataProvider
	public Object[][] getData() {
		return WebUIAutomation.getData("Login");
	}

	@AfterTest
	public void tearDown() {
		wbDv.quit();

	}

}
