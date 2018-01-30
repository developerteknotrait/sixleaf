package com.Apptimice.Automation.testscript;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class DeleteVehicleTest extends TestBase {
	@BeforeClass
	public void doIHaveToSkip() {

		if (!(WebUIAutomation.checkTestCaseRunmode(this.getClass().getSimpleName()))) {
			throw new SkipException("RunMode Set to No");
		}

		launchBrowser();

	}

	@Test(dataProvider = "getData")
	public void deleteVehicleTest(String vehicleNumber, String startAddress) throws InterruptedException {

		String newVehicleNumber = vehicleNumber + Helper.getUniqueValue().toString().substring(3);

		// Provide an implicit wait.

		wbDv.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// Login to the application.

		loggedIn = login();

		Thread.sleep(5000);

		// Verify that user has landed on the Home screen.

		Assert.assertTrue(WebUIAutomation.isObjPresent("Lnk_CreditPoints_HomeScreen", 10),
				"User has not landed on the Home screen");

		// Click on the Vehicles tab.

		Assert.assertTrue(WebUIAutomation.clickObj("Btn_VehicleButton_HomeScreen"),
				"Unable to click on the vehicle tab.");

		// Click on the Add Vehicle icon on the home screen.

		Assert.assertTrue(WebUIAutomation.clickObj("Lnk_AddVehicle_AddVehicleScreen"),
				"Unable to click on the Add Vehicle Link");

		Thread.sleep(4000);

		// Provide the Vehicle Number.

		WebElement vehicleNumberTxt = wbDv.findElement(By.name("LicenseNumber"));
		vehicleNumberTxt.sendKeys(newVehicleNumber);

		Thread.sleep(3000);

		// Provide the Start Address.

		WebElement newStartAddress = wbDv.findElement(By.name("StartAddress"));
		newStartAddress.click();
		newStartAddress.sendKeys(startAddress);
		Thread.sleep(3000);
		newStartAddress.sendKeys(Keys.ARROW_DOWN);
		newStartAddress.sendKeys(Keys.ENTER);

		Thread.sleep(5000);

		// Click on the vehicle text to get the control out of the text box and
		// its suggestion.

		Assert.assertTrue(WebUIAutomation.clickObj("Text_VehicleType_AddVehicleScreen"),
				"Unable to click outside of on the Vehicle Type text.");

		// Click on 'More OPttions' link.

		WebElement moreOptions = wbDv
				.findElement(By.xpath("//a[@class = 'link vehicle-options' and @data-value = 'showmore']"));

		moreOptions.click();

		Thread.sleep(3000);

		// Click on Return Check box.

		Assert.assertTrue(WebUIAutomation.clickObj("ChkBx_Return_AddVehicleScreen"),
				"Unable to click on the checkbox.");

		// Provide the weight text.

		Assert.assertTrue(WebUIAutomation.setText("Txt_Weight_AddVehicleScreen", "2"), "Unable to set the weight.");

		// Click on the text of vehicle type to get the control out.

		Assert.assertTrue(WebUIAutomation.clickObj("Text_CategorizationOfVehicle_AddVehicleScreen"),
				"Unable to click on the Text of Vehicle Type");

		// Scroll Down.

		JavascriptExecutor jse = (JavascriptExecutor) wbDv;
		jse.executeScript("window.scrollBy(0,600)", "");

		// Provide Vehicle type.

		WebElement newVehicle = wbDv
				.findElement(By.xpath("//label[text()='Vehicle Type']/../following-sibling::div/input"));
		newVehicle.click();
		newVehicle.sendKeys(Keys.DOWN);
		Thread.sleep(2000);
		newVehicle.sendKeys(Keys.ENTER);

		// Select a zone.

		WebElement newZone = wbDv.findElement(By.xpath("//label[text()='Zone']/../following-sibling::div/input"));
		newZone.click();
		newZone.sendKeys(Keys.DOWN);
		Thread.sleep(2000);
		newZone.sendKeys(Keys.ENTER);

		// Click on Save button.

		Assert.assertTrue(WebUIAutomation.clickObj("Btn_Save_AddVehicleScreen"), "Unable to click on the save button.");

		Thread.sleep(10000);

		driver.navigate().refresh();
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, 20);
		 * wait.until(ExpectedConditions.elementToBeClickable(By.id(
		 * "navVehicles")));
		 */
		Thread.sleep(10000);

		// Click on the Vehicles tab.

		Assert.assertTrue(WebUIAutomation.clickObj("Btn_VehicleButton_HomeScreen"),
				"Unable to click on the vehicle tab.");

		Thread.sleep(3000);

		// Set the text in search box.

		Assert.assertTrue(WebUIAutomation.setText("Txt_SearchBoxVehicle_HomeScreen", newVehicleNumber),
				"Unable to search the Job through a search box.");
		
		// Click on the check-box for the Vehicle.

		/*
		 * Assert.assertTrue(WebUIAutomation.clickObj(
		 * "ChkBox_SearchedVehicle_HomeScreen"),
		 * "Unable to click on the check box of appeared Vehicle.");
		 */
		// Click on the delete icon.

		Assert.assertTrue(WebUIAutomation.clickObj("Btn_DeleteVehicle_HomeScreen"),
				"Unable to click on the delete button.");

		Thread.sleep(3000);

		// Confirm the deletion of the Vehicle newly created.

		Assert.assertTrue(WebUIAutomation.clickObj("Btn_RemoveCreation_HomeScreen"),
				"Unable to confirm the deletion of the job by clicking on the remove button.");

		Thread.sleep(2000);
	}

	@DataProvider
	public Object[][] getData() {
		return WebUIAutomation.getData("deleteVehicleTest");
	};

	@AfterMethod
	public void logout() {

		if (loggedIn)
			wbDv.close();

	}
}
