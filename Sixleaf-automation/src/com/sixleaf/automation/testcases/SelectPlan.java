package com.sixleaf.automation.testcases;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sixleaf.automation.base.TestBase;
import com.sixleaf.automation.utility.WebUIAutomation;

public class SelectPlan extends TestBase {

	@BeforeClass
	public void doIhaveToSkip() {

		// logger.debug("Check the Test Case Run-mode");
		if (!(WebUIAutomation.checkTestCaseRunmode(this.getClass().getSimpleName()))) {

			throw new SkipException("Runmode set to No");
		}
	}

	@Test(dataProvider = "getData")
	public static void paymentDetails(String cardName, String cardNumber, String expirationDate, String cvv)
			throws InterruptedException, AWTException {

		// Login to the application.

		loggedIn = login();

		// To Click on drop-down after login
		Assert.assertTrue(WebUIAutomation.clickObj("dropdown_arrow_HomePage"),
				"Clicking on drop-down arrow is not successful");

		// To Click on Billing option
		Assert.assertTrue(WebUIAutomation.clickObj("listitem_Billing_HomePage"),
				"Clicking on Billing is not successful");
		
		Thread.sleep(2000);
		
				/* ************************Code to Add Payment Method*********************** */
        
		WebElement element = driver.findElement(By.xpath(".//*[@id='billingnewcarddialog-open']/span"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                                      
		// To Click on plus icon to add payment method
		Assert.assertTrue(WebUIAutomation.clickObj("button_addPaymentMethod_BillingPage"),
				"Clicking on plus icon to add payment method is not successful");

		Thread.sleep(2000);
		
		// To enter Name on Card
		Assert.assertTrue(WebUIAutomation.setText("txtfield_nameOnCard_paymentPopup_BillingPage", cardName),
				"User is unable to enter name on card");

		// To enter Card Number
		Assert.assertTrue(WebUIAutomation.setText("txtfield_cardNumber_paymentPopup_BillingPage", cardNumber),
				"User is unable to enter card number");

		// To enter Expire Date
		Assert.assertTrue(WebUIAutomation.setText("txtfield_expDate_paymentPopup_BillingPage", expirationDate),
				"User is unable to enter expire date");

		// To enter cvv number
		Assert.assertTrue(WebUIAutomation.setText("txtfield_cvv_paymentPopup_BillingPage", cvv),
				"User is unable to enter cvv number");
		
		//Thread.sleep(1000);
		
		   WebElement elementOne = driver.findElement(By.xpath(".//*[@id='new-card-save']"));
		  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementOne);
        
		// To click on Save button
		Assert.assertTrue(WebUIAutomation.clickObj("button_save_paymentPopup_BillingPage"),
				"Clicking on Save button is not successful");
		
		Thread.sleep(2000);
		
		//Scroll up the page
		JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,-250)", "");
		
        Thread.sleep(1000);
        
        	/* ****************************Code to Add Plan************************************** */
		
		// To click on plus icon to add plan
		Assert.assertTrue(WebUIAutomation.clickObj("button_addPlan_BillingPage"),
						"Clicking on plus icon to add plan is not successful");
		
		Thread.sleep(2000);
		
		// To click on zonblast plan
		Assert.assertTrue(WebUIAutomation.clickObj("div_zonblastPlan_BillingPage"),
								"Clicking on zonblast is not successful");
				
		//Slider for active products
				
		WebElement slider = driver.findElement(By.xpath(".//*[@id='blastqntyslider']/span"));
		 for (int i = 1; i <= 2 ; i++) {
	            slider.sendKeys(Keys.ARROW_RIGHT);
	        }
        
		 //To click on subscribe button of zonblast plan
		Assert.assertTrue(WebUIAutomation.clickObj("button_subscribePlan_PopupZonblast"),
					"Clicking on zonblast is not successful");
		
		Thread.sleep(2000);
        
		//To click on approve button of zonblast plan
		Assert.assertTrue(WebUIAutomation.clickObj("button_approvePlan_PopupZonblast"),
					"Clicking on approve is not successful");
	}

	@DataProvider
	public Object[][] getData() {
		return WebUIAutomation.getData("SelectPlan");
	}

}
