package com.Apptimice.Automation.testscript;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
//import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Apptimice.Automation.TestBase;
import com.Apptimice.Automation.WebUIAutomation;

public class ImportVehicleTest extends TestBase {

	@BeforeClass
	public void doIHaveToSkip() {

		if (!(WebUIAutomation.checkTestCaseRunmode(this.getClass().getSimpleName()))) {
			throw new SkipException("RunMode Set to No");
		}

		launchBrowser();

	}

	@Test(/*dataProvider = "getData"*/)
	public void importVehicleTest() throws InterruptedException, AWTException {

		// Provide an implicit wait.

		wbDv.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// Login to the application.

		loggedIn = login();

		// Verify that user has landed on the Home screen.

		Assert.assertTrue(WebUIAutomation.isObjPresent("Lnk_CreditPoints_HomeScreen", 10),
				"User has not landed on the Home screen");

		// Click on the Vehicles tab.

		Assert.assertTrue(WebUIAutomation.clickObj("Btn_VehicleButton_HomeScreen"),
				"Unable to click on the vehicle tab.");

		// Click on the Import link

		Assert.assertTrue(WebUIAutomation.clickObj("Lnk_ImportVehicles_HomeScreen"),
				"Unable to click on the Import Link");

		// assuming driver is a healthy WebDriver instance
		WebElement fileInput = driver.findElement(By.xpath(".//*[@id='tab1']/div/div/div[2]/div/div/div/a"));
		fileInput.click();

		Thread.sleep(5000);

		StringSelection s = new StringSelection(System.getProperty("user.dir") + File.separator + "Vehicle import"
				+ File.separator + "Vehicles_Import.xls");
		System.out.println(System.getProperty("user.dir") + File.separator + "Vehicle import" + File.separator
				+ "Vehicles_Import.xls");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
		Robot robot = new Robot();
		robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
		robot.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
		robot.keyPress(java.awt.event.KeyEvent.VK_CONTROL);
		robot.keyPress(java.awt.event.KeyEvent.VK_V);
		robot.keyRelease(java.awt.event.KeyEvent.VK_CONTROL);
		Thread.sleep(3000);
		robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);

		Thread.sleep(10000);

		// Click on Import button.

		Assert.assertTrue(WebUIAutomation.clickObj("Btn_Import_ImportVehicleScreen"),
				"Unable to click on the import button.");

		Thread.sleep(40000);
		// Click on Skip & Proceed

		Assert.assertTrue(WebUIAutomation.clickObj("Btn_SkipAndProceed_ImportVehicleScreen"),
				"Unable to click on the Skip & Proceed nutton.");

		Thread.sleep(5000);
		// Navigate to vehicles tab.

		Assert.assertTrue(WebUIAutomation.clickObj("Btn_VehicleButton_HomeScreen"),
				"Unable to click on the vehicle tab.");

		// Click on delete tab.
		Thread.sleep(5000);

		Assert.assertTrue(WebUIAutomation.clickObj("Lnk_DeleteSelectedVehicles_ImportVehicleScreen"),
				"Unable to click on the Delete link.");

		Thread.sleep(7000);

		// Confirm deletion by clicking on remove button.

		Assert.assertTrue(WebUIAutomation.clickObj("Btn_Remove_ImportVehicleScreen"),
				"Unable to click on the remove button.");
	}
/*
	@DataProvider
	public Object[][] getData() {
		return WebUIAutomation.getData("importVehicleTest");
	};*/

	@AfterMethod
	public void logout() {

		if (loggedIn)
			wbDv.close();
	}

}
