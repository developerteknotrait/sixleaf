package com.sixleaf.automation.testcases;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sixleaf.automation.base.TestBase;
import com.sixleaf.automation.utility.WebUIAutomation;

public class IntegrationZonBlast extends TestBase {

	@BeforeClass
	public void doIhaveToSkip() {

		// logger.debug("Check the Test Case Run-mode");
		if (!(WebUIAutomation.checkTestCaseRunmode(this.getClass().getSimpleName()))) {

			throw new SkipException("Runmode set to No");
		}
	}
	

	@Test(dataProvider = "getData")
	public static void integration(String sellername, String sellerid, String authkey, String marketplaceid)
			throws InterruptedException, AWTException {

		// Login to the application.

		loggedIn = login();

		// To Click on drop-down after login
		Assert.assertTrue(WebUIAutomation.clickObj("dropdown_arrow_HomePage"),
				"Clicking on drop-down arrow is not successful");

		// To Click on Integration option
		Assert.assertTrue(WebUIAutomation.clickObj("listitem_Integration_HomePage"),
				"Clicking on Integration is not successful");
		
		Thread.sleep(2000);
		
		//Click on Setup New button
		Assert.assertTrue(WebUIAutomation.clickObj("button_setupnew_IntegrationPage"),
				"Clicking on setup new button is not successful");
		
		
		/* *******************************Create Amazon Integration************************* */
		Thread.sleep(2000);
		
		// To enter Seller Name
		Assert.assertTrue(WebUIAutomation.setText("txtfield_sellername_integrationPopup_IntegrationPage", sellername),
						"User is unable to enter seller name");
		
		// To enter Seller Name
		Assert.assertTrue(WebUIAutomation.setText("txtfield_sellerid_integrationPopup_IntegrationPage", sellerid),
						"User is unable to enter seller id");
				
		// To enter Seller Name
		Assert.assertTrue(WebUIAutomation.setText("txtfield_authkey_integrationPopup_IntegrationPage", authkey),
						"User is unable to enter authorization key");

		// To enter Seller Name
		Assert.assertTrue(WebUIAutomation.setText("txtfield_marketplaceid_integrationPopup_IntegrationPage", marketplaceid),
						"User is unable to enter marketplace id ");
		
		//To click on create button
		Assert.assertTrue(WebUIAutomation.clickObj("button_create_integrationPopup_IntegrationPage"),
				"Clicking on create button is not successful");
		
		Thread.sleep(5000);
		
		//To click on create button
		Assert.assertTrue(WebUIAutomation.clickObj("div_amazonProduct_IntegrationPage"),
				"Clicking on amazon product box is not successful");
		
	}
	
	@DataProvider
	public Object[][] getData() {
		return WebUIAutomation.getData("IntegrationZonBlast");
	}

}
