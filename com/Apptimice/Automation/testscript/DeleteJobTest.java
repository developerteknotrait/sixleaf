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

import com.Apptimice.Automation.TestBase;
import com.Apptimice.Automation.WebUIAutomation;

public class DeleteJobTest extends TestBase {
	@BeforeClass
	public void doIHaveToSkip() {

		if (!(WebUIAutomation.checkTestCaseRunmode(this.getClass().getSimpleName()))) {
			throw new SkipException("RunMode Set to No");
		}

		launchBrowser();

	}

	@Test(dataProvider = "getData")
	public void jobCreationTest(String date, String description, String pickUpAddress, String zone, String skill,
			String duration, String weight, String volume, String notes,
			String driver, String vehicle) throws InterruptedException {

		// Provide an implicit wait.

		wbDv.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// Login to the application.

		loggedIn = login();

		Thread.sleep(5000);

		// Verify that user has landed on the Home screen.

		Assert.assertTrue(WebUIAutomation.isObjPresent("Lnk_CreditPoints_HomeScreen", 10),
				"User has not landed on the Home screen");

		// Click on the "Add Job" icon.

		Assert.assertTrue(WebUIAutomation.clickObj("Btn_AddJob_Homescreen"), "Unable to click on 'Add Job' icon");

		Thread.sleep(3000);

		// Grab the Job Id.

		String jobId = wbDv.findElement(By.xpath(".//*[@id='frmJob']/div[1]/div[1]/div[2]/p")).getText();

		// Provide Date to the Job.

		DateFormat dateFormat2 = new SimpleDateFormat("dd");
		Date date2 = new Date();

		String today = dateFormat2.format(date2);
		System.out.println(today);

		// find the calendar
		// WebElement dateWidget =
		// wbDv.findElement(By.xpath(".//*[@id='basic-addon2']"));

		// dateWidget.click();
		// dateWidget.sendKeys(today);

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

		Thread.sleep(4000);

		// Provide Note.

		Assert.assertTrue(WebUIAutomation.clickObj("ChkBox_Priority_AddJobScreen"), "ChkBox_Priority_AddJobScreen");

		// Click on the Driver component first.

		Assert.assertTrue(WebUIAutomation.setText("Txt_Notes_AddJobScreen", notes), "Unable to provide Notes");

		// Click on the Save button.

		Assert.assertTrue(WebUIAutomation.clickObj("Btn_Save_AddJobScreen"), "Unable to save the Job created");

		Thread.sleep(10000);

		wbDv.navigate().refresh();

		Thread.sleep(10000);

		// Set the text in search box.

		Assert.assertTrue(WebUIAutomation.setText("Txt_SearchBoxJob_HomeScreen", jobId),
				"Unable to search the Job through a search box.");

		/*
		 * Thread.sleep(3000);
		 * 
		 * // Click on the check-box for the Vehicle.
		 * 
		 * Assert.assertTrue(WebUIAutomation.clickObj(
		 * "ChkBox_SearchedJob_HomeScreen"),
		 * "Unable to click the check box to select the Job");
		 */
		Thread.sleep(3000);

		// Click on the delete icon.

		Assert.assertTrue(WebUIAutomation.clickObj("Btn_DeleteJOb_HomeScreen"),
				"Unable to click on the delete button to delete the created job");

		Thread.sleep(3000);

		// Confirm the deletion of the Job newly created.

		Assert.assertTrue(WebUIAutomation.clickObj("Btn_RemoveCreation_HomeScreen"),
				"Unable to confirm the deletion of the job by clicking on the remove button.");

	}

	@DataProvider
	public Object[][] getData() {
		return WebUIAutomation.getData("jobCreationTest");
	};

	@AfterMethod
	public void logout() {

		if (loggedIn)
			wbDv.close();
	}
}