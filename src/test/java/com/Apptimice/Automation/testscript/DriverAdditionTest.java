package com.Apptimice.Automation.testscript;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Apptimice.Automation.Helper;
import com.Apptimice.Automation.TestBase;
import com.Apptimice.Automation.WebUIAutomation;

public class DriverAdditionTest extends TestBase {

	@BeforeClass
	public void doIHaveToSkip() {

		if (!(WebUIAutomation.checkTestCaseRunmode(this.getClass().getSimpleName()))) {
			throw new SkipException("RunMode Set to No");
		}

		launchBrowser();
	}

	@Test(dataProvider = "getData")
	public void driverAdditionTest(String name, String idNumber) throws InterruptedException {
		
		String UniqueIdNumber = idNumber + Helper.getUniqueValue().toString().substring(4);

		// Provide an implicit wait.

		wbDv.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// Login to the application.

		loggedIn = login();

		Thread.sleep(5000);

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

		// Click on the Driver tab.

		Assert.assertTrue(WebUIAutomation.clickObj("Lnk_Driver_AdministrativeScreen"),
				"Unable to click on the Driver's tab on the Administrative page.");

		Thread.sleep(5000);

		// Click on the Add Driver icon

		Assert.assertTrue(WebUIAutomation.clickObj("Lnk_AddDriver_AddDriverScreen"),
				"Unable to click the Add Driver icon.");

		Thread.sleep(5000);

		// Provide name

		Assert.assertTrue(WebUIAutomation.setText("Txt_Name_AddDriverScreen", name), "Unable to provide the name.");

		// Provide Id number

		Assert.assertTrue(WebUIAutomation.setText("Txt_IDNumber_AddDriverScreen", UniqueIdNumber),
				"Unable to provide idNumber.");

		// Provide skills

		WebElement skills = wbDv.findElement(By.xpath(".//*[@id='s2id_autogen2']"));
		skills.click();
		Thread.sleep(3000);
		skills.sendKeys(Keys.ARROW_DOWN);
		skills.sendKeys(Keys.ENTER);

		// Provide Zones

		WebElement Zone = wbDv.findElement(By.xpath(".//*[@id='s2id_autogen3']/ul"));
		Zone.click();
		Zone.sendKeys(Keys.DOWN);
		Thread.sleep(2000);
		Zone.sendKeys(Keys.ENTER);

		Thread.sleep(2000);

		// Provide Vehicle type.

		WebElement VehicleType = wbDv.findElement(By.xpath(".//*[@id='select2-chosen-6']"));
		VehicleType.click();
		VehicleType.sendKeys(Keys.DOWN);
		Thread.sleep(2000);
		VehicleType.sendKeys(Keys.ENTER);

		Thread.sleep(3000);

		// Click on the save button.

		Assert.assertTrue(WebUIAutomation.clickObj("Btn_Submit_AddDriverScreen"), "Unable to click on save button.");

		Thread.sleep(3000);

	}

	@DataProvider
	public Object[][] getData() {
		return WebUIAutomation.getData("driverAdditionTest");
	};

	@AfterMethod
	public void logout() {

		if (loggedIn)
			wbDv.close();
	}
}
