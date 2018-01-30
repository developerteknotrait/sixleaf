package com.Apptimice.Automation.testscript;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class OptimizationTest extends TestBase {
	@BeforeClass
	public void doIHaveToSkip() {

		if (!(WebUIAutomation.checkTestCaseRunmode(this.getClass().getSimpleName()))) {
			throw new SkipException("RunMode Set to No");
		}

		launchBrowser();

	}

	@Test(dataProvider = "getData")
	public void optimizationTest(String date, String description, String pickUpAddress, String zone, String skill,
			String duration, String weight, String volume, String notes, String driver, String vehicle,
			String vehicleNumber, String startAddress, String name, String idNumber) throws InterruptedException {

		// Provide an implicit wait.

		wbDv.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// Login to the application.

		loggedIn = login();

		Thread.sleep(5000);

		String UniqueIdNumber = idNumber + Helper.getUniqueValue().toString().substring(4);

		// Verify that user has landed on the Home screen.

		Assert.assertTrue(WebUIAutomation.isObjPresent("Lnk_CreditPoints_HomeScreen", 10),
				"User has not landed on the Home screen");

		// Click on the "Add Job" icon.

		Assert.assertTrue(WebUIAutomation.clickObj("Btn_AddJob_Homescreen"), "Unable to click on 'Add Job' icon");

		Thread.sleep(3000);

		// Provide Date to the Job.

		DateFormat dateFormat2 = new SimpleDateFormat("dd");
		Date date2 = new Date();

		String today = dateFormat2.format(date2);
		System.out.println(today);

		Assert.assertTrue(WebUIAutomation.setText("Txt_JobDate_AddJobScreen", today), "Unable to set jod date");

		// Provide some Description

		Assert.assertTrue(WebUIAutomation.setText("Txt_Description_AddJobScreen", description),
				"Unable to provide Description");

		// Provide the address and select the GPS option.

		WebElement newPickUpAddress = wbDv.findElement(By.xpath(".//*[@id='job-jobService']/div[1]/div[2]/input"));
		newPickUpAddress.click();
		newPickUpAddress.sendKeys(pickUpAddress);
		Thread.sleep(3000);
		newPickUpAddress.sendKeys(Keys.ARROW_DOWN);
		newPickUpAddress.sendKeys(Keys.ENTER);

		// Click on the vehicle text to get the control out of the text box and
		// its suggestion.

		Assert.assertTrue(WebUIAutomation.clickObj("Label_Zone_AddJobScreen"),
				"Unable to get the control out side the Address textbox.");

		// Provide Zone

		WebElement newZone = wbDv.findElement(
				By.xpath("//ul[@class = 'select2-choices']/li/input[@id = 's2id_autogen2' and @type = 'text']"));

		newZone.sendKeys(Keys.DOWN);
		Thread.sleep(2000);
		newZone.sendKeys(Keys.ENTER);

		// Provide Skills

		WebElement newSkill = wbDv.findElement(
				By.xpath("//ul[@class = 'select2-choices']/li/input[@id = 's2id_autogen12' and @type = 'text']"));

		newSkill.sendKeys(Keys.DOWN);
		Thread.sleep(2000);
		newSkill.sendKeys(Keys.ENTER);

		// Provide Duration

		Assert.assertTrue(WebUIAutomation.setText("Txt_PickUpDuration_AddJobScreen", duration),
				"Unable to set theasss Skills");

		Thread.sleep(3000);

		// Click on the Start Time.

		WebElement startTime = wbDv
				.findElement(By.xpath(".//*[@id='job-jobService']/div[5]/div[2]/span[1]/span/input"));

		startTime.sendKeys(Keys.DOWN);
		Thread.sleep(2000);
		startTime.sendKeys(Keys.ENTER);

		// Click on the End Time.

		WebElement endTime = wbDv.findElement(By.xpath(".//*[@id='job-jobService']/div[5]/div[2]/span[3]/span/input"));

		endTime.sendKeys(Keys.DOWN);
		endTime.sendKeys(Keys.DOWN);
		endTime.sendKeys(Keys.DOWN);
		Thread.sleep(2000);
		endTime.sendKeys(Keys.ENTER);

		// Click on the Show more option.

		WebElement showMoreOPtion = wbDv.findElement(
				By.xpath("//button[@id='jobAdvanced' and @class = 'btn btn-default' and @type = 'button']"));

		showMoreOPtion.click();

		Thread.sleep(3000);

		// Scroll down a bit.

		JavascriptExecutor jse = (JavascriptExecutor) wbDv;
		jse.executeScript("window.scrollBy(0,600)", "");

		Thread.sleep(4000);

		// Provide Advance options as well such as Weight.|

		Assert.assertTrue(WebUIAutomation.setText("Txt_Weight_AddJobScreen", weight), "Unable to provide Weight");

		Thread.sleep(2000);

		// Provide Volume.

		Assert.assertTrue(WebUIAutomation.setText("Txt_Volume_AddJobScreen", volume), "Unable to provide Volume");

		Thread.sleep(2000);

		// Provide priority.

		// Assert.assertTrue(WebUIAutomation.clickObj("ChkBox_Priority_AddJobScreen"),
		// "Unable to set Priority");

		Thread.sleep(2000);

		// Provide Note.

		Assert.assertTrue(WebUIAutomation.clickObj("ChkBox_Priority_AddJobScreen"), "ChkBox_Priority_AddJobScreen");

		// Click on the Driver component first.

		Assert.assertTrue(WebUIAutomation.setText("Txt_Notes_AddJobScreen", notes), "Unable to provide Notes");

		// Click on the Save button.

		Assert.assertTrue(WebUIAutomation.clickObj("Btn_Save_AddJobScreen"), "Unable to save the Job created");

		Thread.sleep(10000);

		// *****************************************************************************************************

		// Vehicle creation

		wbDv.navigate().to("http://app.apptimice.com");

		String newVehicleNumber = vehicleNumber + Helper.getUniqueValue().toString().substring(3);

		// Provide an implicit wait.

		wbDv.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// Verify that user has landed on the Home screen.

		Assert.assertTrue(WebUIAutomation.isObjPresent("Lnk_CreditPoints_HomeScreen", 10),
				"User has not landed on the Home screen");

		Thread.sleep(5000);

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

		JavascriptExecutor scroll = (JavascriptExecutor) wbDv;
		scroll.executeScript("window.scrollBy(0,600)", "");

		// Provide Vehicle type.

		WebElement newVehicle = wbDv
				.findElement(By.xpath("//label[text()='Vehicle Type']/../following-sibling::div/input"));
		newVehicle.click();
		newVehicle.sendKeys(Keys.DOWN);
		Thread.sleep(2000);
		newVehicle.sendKeys(Keys.ENTER);

		// Select a zone.

		WebElement newZoned = wbDv.findElement(By.xpath("//label[text()='Zone']/../following-sibling::div/input"));
		newZoned.click();
		newZoned.sendKeys(Keys.DOWN);
		Thread.sleep(2000);
		newZoned.sendKeys(Keys.ENTER);

		// Click on the radio button stating 'Vehicle has a Driver'.

		Assert.assertTrue(WebUIAutomation.clickObj("RdoBtn_VehicleHasDriver_AddVehicleScreen"),
				"Unable to click on the Radio button for 'Vehicle hass a driver.'");

		Thread.sleep(3000);

		// Provide name

		Assert.assertTrue(WebUIAutomation.setText("Txt_DriverName_AddVehicleScreen", name),
				"Unable to provide the name.");

		// Provide Id number

		Assert.assertTrue(WebUIAutomation.setText("Txt_IdNunber_AddVehicleScreen", UniqueIdNumber),
				"Unable to provide idNumber.");

		// Click on the 'More Option'.

		Assert.assertTrue(WebUIAutomation.clickObj("Lnk_ShowMoreOptionDriver_AddVehicleScreen"),
				"Unable to click on the 'More options' link.");

		// Provide Vehicle type.

		WebElement VehicleType = wbDv.findElement(By.xpath(
				"html/body/div[3]/div/div[1]/div[8]/div/div/form/div[2]/div/div[2]/div[3]/div[1]/div[2]/div/ul"));
		VehicleType.click();
		VehicleType.sendKeys(Keys.DOWN);
		Thread.sleep(2000);
		VehicleType.sendKeys(Keys.ENTER);

		// Provide skills

		WebElement skills = wbDv.findElement(By.xpath(
				"html/body/div[3]/div/div[1]/div[8]/div/div/form/div[2]/div/div[2]/div[3]/div[5]/div[2]/div/ul"));
		skills.click();
		Thread.sleep(3000);
		skills.sendKeys(Keys.ARROW_DOWN);
		skills.sendKeys(Keys.ENTER);

		// Provide Zones

		WebElement Zone = wbDv.findElement(By.xpath(
				"html/body/div[3]/div/div[1]/div[8]/div/div/form/div[2]/div/div[2]/div[3]/div[5]/div[2]/div/ul"));
		Zone.click();
		Zone.sendKeys(Keys.DOWN);
		Thread.sleep(2000);
		Zone.sendKeys(Keys.ENTER);

		Thread.sleep(2000);

		// Click on Save button.

		Assert.assertTrue(WebUIAutomation.clickObj("Btn_Save_AddVehicleScreen"), "Unable to click on the save button.");

		// Navigate to the home screen if not available.

		Thread.sleep(5000);

		wbDv.navigate().to("http://app.apptimice.com");

		Thread.sleep(10000);

		// Click on the optimization screen observe.

		Assert.assertTrue(WebUIAutomation.clickObj("Btn_Optimization_HomeScreen"),
				"Unable to click on the optimization button to optimize");

		Thread.sleep(10000);

		// Confirm the optimization either by click on the confirm button or by
		// clicking enter.

		WebElement confirm = wbDv.findElement(By.xpath("//button[contains(text(), 'Continue')]"));

		confirm.click();
		
		Thread.sleep(5000);

	}

	@DataProvider
	public Object[][] getData() {
		return WebUIAutomation.getData("optimizationTest");
	};

	@AfterMethod
	public void logout() {

		if (loggedIn)
			wbDv.close();
	}
}
