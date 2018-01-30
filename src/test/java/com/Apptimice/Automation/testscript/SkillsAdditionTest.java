package com.Apptimice.Automation.testscript;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Apptimice.Automation.TestBase;
import com.Apptimice.Automation.WebUIAutomation;

public class SkillsAdditionTest extends TestBase {

	@BeforeClass
	public void doIHaveToSkip() {

		if (!(WebUIAutomation.checkTestCaseRunmode(this.getClass().getSimpleName()))) {
			throw new SkipException("RunMode Set to No");
		}

		launchBrowser();

	}

	@Test(dataProvider = "getData")
	public void skillsAdditionTest(String skillsName) throws InterruptedException {

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

		// Click on the Skills tab.

		Assert.assertTrue(WebUIAutomation.clickObj("Lnk_Skills_AdministrativeScreen"),
				"Unable to click on the Skills tab on the administrative screen.");
		
		Thread.sleep(2000);

		// Click on the Add Skills Link.

		Assert.assertTrue(WebUIAutomation.clickObj("Lnk_AddSkills_AddSkillScreen"),
				"Unable to click on the Add Skills icon.");

		Thread.sleep(3000);

		// Provide the name.

		Assert.assertTrue(WebUIAutomation.setText("Txt_Name_AddSkillScreen", skillsName),
				"Unable to set the skills name.");

		// Click on the save button.

		Assert.assertTrue(WebUIAutomation.clickObj("Btn_Submit_AddSkillScreen"), "Unable to click on the save button.");

		Thread.sleep(5000);

		// Provide skills Name in the search box.

		Assert.assertTrue(WebUIAutomation.setText("Txt_SearchSkills_AddSkillScreen", skillsName),
				"Unable to provide the skills name in the search box.");
		
		Thread.sleep(2000);

		// Click on the check box and delete the created skill from the list.

		Assert.assertTrue(WebUIAutomation.clickObj("ChkBox_Skills_AddSkillScreen"),
				"Unable to click on the check box to delete the skills.");

		// Click on the Delete icon.

		Assert.assertTrue(WebUIAutomation.clickObj("Lnk_DeleteIcon_AddSkillScreen"),
				"Unable to click on the delete screen");
		
		Thread.sleep(3000);

		// Confirm deletion by clicking remove.

		Assert.assertTrue(WebUIAutomation.clickObj("Btn_Remove_AddSkillsScreen"),
				"Unable to click on remove button to confirm deletion.");

	}

	@DataProvider
	public Object[][] getData() {
		return WebUIAutomation.getData("skillsAdditionTest");
	};

	@AfterMethod
	public void logout() {

		if (loggedIn)
			wbDv.close();
	}
}
