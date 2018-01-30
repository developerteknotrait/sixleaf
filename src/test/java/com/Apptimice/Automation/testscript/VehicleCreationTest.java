package com.Apptimice.Automation.testscript;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Apptimice.Automation.Helper;
import com.Apptimice.Automation.TestBase;
import com.Apptimice.Automation.WebUIAutomation;

public class VehicleCreationTest extends TestBase {

	@BeforeClass
	public void doIHaveToSkip() {

		if (!(WebUIAutomation.checkTestCaseRunmode(this.getClass().getSimpleName()))) {
			throw new SkipException("RunMode Set to No");
		}

		launchBrowser(); 

	}

	@Test(dataProvider = "getData")
	public void vehicleCreationTest(String vehicleNumber, String startAddress, String endAddress, String weight,
			String volume, String vehicleType, String zone) throws InterruptedException {

		String newVehicleNumber = vehicleNumber + Helper.getUniqueValue().toString().substring(3);

		// Provide an implicit wait.

		wbDv.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// Login to the application.

		loggedIn = login();

		Thread.sleep(5000);

		// Verify that user has landed on the Home screen.

		Assert.assertTrue(WebUIAutomation.isObjPresent("Lnk_CreditPoints_HomeScreen", 10),
				"User has not landed on the Home screen");
		
		Thread.sleep(4000);

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

		// Click on the Show more option.

		WebElement showMoreOPtion = wbDv
				.findElement(By.xpath("//a[@class = 'link vehicle-options' and @data-value = 'showmore']"));
		showMoreOPtion.click();

		Thread.sleep(2000);

		// Click Different Address radio button

		WebElement differentAddress = wbDv.findElement(By.xpath("//label[@for='endAddressDifferent']"));
		differentAddress.click();

		Thread.sleep(2000);

		// Provide the End Address.

		WebElement newEndAddress = wbDv.findElement(By.name("EndAddress"));
		newEndAddress.click();
		newEndAddress.sendKeys(endAddress);
		Thread.sleep(3000);
		newEndAddress.sendKeys(Keys.ARROW_DOWN);
		newEndAddress.sendKeys(Keys.ENTER);

		Thread.sleep(5000);

		// Click on the vehicle text to get the control out of the text box and
		// its suggestion.

		Assert.assertTrue(WebUIAutomation.clickObj("Text_VehicleType_AddVehicleScreen"),
				"Unable to click outside of on the Vehicle Type text.");

		// Click on Return Check box.

		Assert.assertTrue(WebUIAutomation.clickObj("ChkBx_Return_AddVehicleScreen"),
				"Unable to click on the checkbox.");

		// Provide the weight text.

		Assert.assertTrue(WebUIAutomation.setText("Txt_Weight_AddVehicleScreen", weight), "Unable to set the weight.");

		// Provide the volume text

		Assert.assertTrue(WebUIAutomation.setText("Txt_Volume_AddVehicleScreen", volume), "Unable to set the volume.");

		// Click Vehicle Type select2 input
		JavascriptExecutor executor = (JavascriptExecutor) wbDv;

		WebElement vehicleTypeElement = wbDv.findElement(By.name("VehicleType"));
		WebElement vehicleTypeSelInput = (WebElement) executor.executeScript("return arguments[0].parentNode",
				vehicleTypeElement);
		List<WebElement> childInputs = vehicleTypeSelInput
				.findElements(By.xpath("//input[@class='select2-input select2-default']"));

		childInputs.get(0).click();

		if (vehicleType != null && !vehicleType.isEmpty()) {
			childInputs.get(0).sendKeys(vehicleType);
			Thread.sleep(500);
			childInputs.get(0).sendKeys(Keys.ARROW_DOWN);
			childInputs.get(0).sendKeys(Keys.ENTER);
		} else {

			Thread.sleep(500);

			WebDriverWait wait = new WebDriverWait(wbDv, 10);

			// First click on dropdown to show options
			WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("select2-drop")));
			dropdown.click();
		}

		// Enter new zone and enter

		// Click Vehicle Type select2 input
		WebElement zoneElement = wbDv.findElement(By.name("VehicleZone"));
		WebElement zoneSelInput = (WebElement) executor.executeScript("return arguments[0].parentNode", zoneElement);
		List<WebElement> zoneChildInputs = zoneSelInput
				.findElements(By.xpath("//input[@class='select2-input select2-default']"));

		zoneChildInputs.get(0).click();

		System.out.println("Zone = " + zone);
		System.out.println("Zone element");
		System.out.println(zoneElement);
		System.out.println("Dropdown input");
		System.out.println(zoneChildInputs.get(0));
		
		if (zone != null && !zone.isEmpty()) {
			zoneChildInputs.get(0).sendKeys(zone);
			Thread.sleep(500);
			zoneChildInputs.get(0).sendKeys(Keys.ARROW_DOWN);
			zoneChildInputs.get(0).sendKeys(Keys.ENTER);
		} else {

			Thread.sleep(500);

			WebDriverWait wait = new WebDriverWait(wbDv, 10);

			// First click on dropdown to show options
			WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("select2-drop")));
			dropdown.click();
		}

		// Click on the text of vehicle type to get the control out.

		// Assert.assertTrue(WebUIAutomation.clickObj("Text_CategorizationOfVehicle_AddVehicleScreen"),"Unable
		// to click on the Text of Vehicle Type");

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

		Thread.sleep(5000);

	}

	@DataProvider
	public Object[][] getData() {
		return WebUIAutomation.getData("vehicleCreationTest");
	};

	@AfterMethod
	public void logout() {

		if (loggedIn)
			wbDv.close();

	}
}
