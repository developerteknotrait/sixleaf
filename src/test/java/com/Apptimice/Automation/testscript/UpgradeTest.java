package com.Apptimice.Automation.testscript;

import org.apache.bcel.generic.IFGT;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Apptimice.Automation.TestBase;
import com.Apptimice.Automation.WebUIAutomation;

public class UpgradeTest extends TestBase {

	@BeforeClass
	public void doIhaveToSkip() {

		// logger.debug("Check the Test Case Run-mode");

		if (!(WebUIAutomation.checkTestCaseRunmode(this.getClass().getSimpleName()))) {

			throw new SkipException("Runmode set to No");
		}
		launchBrowser();
	}

	@Test(dataProvider = "getData")
	public static void upgrade(String Email, String Password, String Total_invalid_data, String Email1,
			String Password1, String invalid_email_address_msg, String Paypal_invalid_error_msg) throws InterruptedException {

        // Login application username 
		
		WebUIAutomation.setText("Txt_upgrade_Username", Email);
		 
		Thread.sleep(1000);
		
		// Enter the password 
		
		WebUIAutomation.setText("Txt_upgrade_Password", Password);
		 
		Thread.sleep(1000);
		
		// Click on the Login button 
		
		WebUIAutomation.clickObj("Btn_Login_LoginPage");
		
		Thread.sleep(1000);
		
		// Click on the upgrade link

		WebUIAutomation.clickObj("Lnk_Upgrade_Header");

		Thread.sleep(1000);
		
		// Click on the Upgrade now button

		WebUIAutomation.clickObj("Btn_Upgrade_upgradenow");
		
		Thread.sleep(1000);

		// Enter invalid data in the Total field

		Assert.assertTrue(WebUIAutomation.setText("Txt_Upgrade_total", Total_invalid_data),
				"User is trying to enter Incorrect email");
		
		Thread.sleep(1000);

		// Verify Paypal error message 
		
		String expected_Final_Message = WebUIAutomation.getMsg("Msg_Upgrade_paypal");
		
		System.err.println("expected Message : " + expected_Final_Message);
		
		Assert.assertEquals(Paypal_invalid_error_msg, expected_Final_Message);
		
		
		// Click on paypal Img 
		
		driver.findElement(By.id("tabheaderpayment")).click();
		
		// Click on the Paypal radio button
			
		WebElement radioBtn = driver.findElement(By.id("inlineRadio1"));
		 
		radioBtn.click();
		
		Thread.sleep(1000);
		
		// Click on the confirm button
		
		WebUIAutomation.clickObj("Btn_Upgrade_confrim");

		// Clickin on confirm button of the pop up
		
		WebUIAutomation.clickObj("Btn_Upgrade_Confrimb");

		// Login to paypal account username with invalid

		WebUIAutomation.setText("Txt_Upgrade_Email",Email1 );
		
		// Enter the paypal credentials password invalid
		
		WebUIAutomation.setText("Txt_Upgrade_Password",Password1 );
		
		// Stay logging button 
		
		WebUIAutomation.clickObj("Lbl_Upgrade_keeplog");
		
		// Stay logging details 

		WebUIAutomation.clickObj("Lbl_Upgrade_Learn");
		
		// Login in Paypal

		WebUIAutomation.clickObj("Btn_Upgrade_Login");
		
		
		// Read the Error message
		
       //  
		
		// Click on the continue of the Paypal paytment

	}

	@DataProvider
	public Object[][] getData() {
		return WebUIAutomation.getData("UpgradeTest");
	}

	@AfterTest
	public void logOut()

	{
		if (loggedIn)
			wbDv.quit();

	}

}
