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

public class ImportJobTest extends TestBase {

	@BeforeClass
	public void doIHaveToSkip() {

		if (!(WebUIAutomation.checkTestCaseRunmode(this.getClass().getSimpleName()))) {
			throw new SkipException("RunMode Set to No");
		}

		launchBrowser();

	}

	@Test(/*dataProvider = "getData"*/)
	public void importJoBTest() throws InterruptedException, AWTException {

		// Provide an implicit wait.

		wbDv.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// Login to the application.

		loggedIn = login();

		// Verify that user has landed on the Home screen.

		Assert.assertTrue(WebUIAutomation.isObjPresent("Lnk_CreditPoints_HomeScreen", 10),
				"User has not landed on the Home screen");

		// Click on the Import Job icon.

		Assert.assertTrue(WebUIAutomation.clickObj("Lnk_ImportJob_HomeScreen"), "Unable to click on Import job icon");

		// assuming driver is a healthy WebDriver instance
		WebElement fileInput = driver.findElement(By.xpath(".//*[@id='tab1']/div/div/div[2]/div/div/div/a"));
		fileInput.click();

		Thread.sleep(5000);

		StringSelection s = new StringSelection(
				System.getProperty("user.dir") + File.separator + "Job import" + File.separator + "Job_Import.xls");
		System.out.println(System.getProperty("user.dir") + File.separator + "Job import" + File.separator + "Job_Import.xls");
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

		Assert.assertTrue(WebUIAutomation.clickObj("Btn_import_ImportJobScreen"),
				"Unable to click on import button on import screen.");

		Thread.sleep(30000);

		// Click on Skip to Proceed.

		Assert.assertTrue(WebUIAutomation.clickObj("Btn_SkipAndProceed_ImportJobScreen"),
				"Unable to Click on the skip to Proceed");

		Thread.sleep(15000);

		// Click on Delete icon of jobs.

		Assert.assertTrue(WebUIAutomation.clickObj("Lnk_DeletJobs_ImportJobScreen"),
				"Unable to click on the Delete jobs Icon do to delete jobs selected.");

		Thread.sleep(5000);

		// Click on the remove button.

		Assert.assertTrue(WebUIAutomation.clickObj("Btn_RemoveJobs_ImportJobScreen"),
				"Unable to click on the Remove button to remove all the jobs imported.");

		Thread.sleep(3000);

	}

/*	@DataProvider
	public Object[][] getData() {
		return WebUIAutomation.getData("importJobTest");
	};
*/
	@AfterMethod
	public void logout() {

		if (loggedIn)
			wbDv.close();
	}

}
