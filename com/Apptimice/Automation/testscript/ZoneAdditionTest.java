package com.Apptimice.Automation.testscript;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Apptimice.Automation.Helper;
import com.Apptimice.Automation.TestBase;
import com.Apptimice.Automation.WebUIAutomation;

public class ZoneAdditionTest extends TestBase {

	@BeforeClass
	public void doIHaveToSkip() {

		if (!(WebUIAutomation.checkTestCaseRunmode(this.getClass().getSimpleName()))) {
			throw new SkipException("RunMode Set to No");
		}

		launchBrowser();

	}

	@Test(dataProvider = "getData")
	public void zoneAdditionTest(String zoneName, String zoneCode) throws InterruptedException {
		
		String newZoneCode = zoneCode + Helper.getUniqueValue().toString().substring(5);

		// Provide an implicit wait.

		wbDv.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// Login to the application.

		loggedIn = login();

		// Verify that user has landed on the Home screen.

		Assert.assertTrue(WebUIAutomation.isObjPresent("Lnk_CreditPoints_HomeScreen", 10),
				"User has not landed on the Home screen");
		
		// Click on the Demo1(Settings) drop-down on the right upper corner.

		Assert.assertTrue(WebUIAutomation.clickObj("Lnk_Settings_HomeScreen"),
				"Unable to click on the settings (Demo1) ");

		// Choose "Administration" from the list and click.

		Assert.assertTrue(WebUIAutomation.clickObj("lnk_Administration_HomeScreen"),
				"Unable to click on the Administrative link");

		Thread.sleep(5000);

		// Click on the Zones tab

		Assert.assertTrue(WebUIAutomation.clickObj("Lnk_Zone_AdministrativeScreen"),
				"Unable to click on the Zones tab");

		Thread.sleep(2000);

		// Click on the Add Zone link

		Assert.assertTrue(WebUIAutomation.clickObj("Lnk_AddZone_AddZoneScreen"),
				"Unable to click on the Add Zone link");

		Thread.sleep(3000);
		
		// Provide the name.

		Assert.assertTrue(WebUIAutomation.setText("Txt_Name_AddZoneScreen", zoneName),
				"Unable to set the name for the zones.");
		
		Thread.sleep(2000);

		// Provide the code

		Assert.assertTrue(WebUIAutomation.setText("Txt_Code_AddZoneScreen", newZoneCode),
				"Unable to set the name for the zones.");

		// click on the save button.

		Assert.assertTrue(WebUIAutomation.clickObj("Btn_Submit_AddZoneScreen"), "Unable to click on save button.");
		
		Thread.sleep(5000);

		// Search in the search box

		Assert.assertTrue(WebUIAutomation.setText("Txt_Searchzone_AddZoneScreen", zoneName),
				"Unable to search the zone created in the search box.");

		// Click on the check-box and select.

		Assert.assertTrue(WebUIAutomation.clickObj("ChkBox_zone_AddZoneScreen"),
				"Unable to click on the check box to select the zones.");
		
		Thread.sleep(2000);

		// Click on the Delete link

		Assert.assertTrue(WebUIAutomation.clickObj("Lnk_DeleteIcon_AddZoneScreen"),
				"Unable to click on the delete link to delete the zone selected.");
		
		Thread.sleep(4000);

		// Confirm deletion by clicking on remove button.

		Assert.assertTrue(WebUIAutomation.clickObj("Btn_Remove_AddZoneScreen"),
				"Unable to click on remove button to confirm removal fo te zone.");

	}

	@DataProvider
	public Object[][] getData() {
		return WebUIAutomation.getData("zoneAdditionTest");
	};

	@AfterMethod
	public void logout() {

		if (loggedIn)
			wbDv.close();
	}

}
