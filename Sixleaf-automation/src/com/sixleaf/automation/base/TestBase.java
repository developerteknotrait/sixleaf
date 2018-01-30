package com.sixleaf.automation.base;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import com.sixleaf.automation.utility.WebUIAutomation;
import com.sixleaf.automation.utility.MSExcelAutomation;

public class TestBase {

	public static Properties config = null;
	public static Properties OR = null;
	public static boolean loggedIn = false;
	public WebDriver wbDv = null;
	public static EventFiringWebDriver driver = null;
	public static MSExcelAutomation datatable = null;
	DesiredCapabilities capabilities = new DesiredCapabilities();

	@BeforeSuite
	public void initialize() {

		// loading all the configuration values
		try {
			config = new Properties();
			FileInputStream fp = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\com\\sixleaf\\automation\\config\\Config.properties");
			config.load(fp);

			// loading Objects Repositories
			OR = new Properties();
			fp = new FileInputStream(System.getProperty("user.dir")
					+ "\\src\\com\\sixleaf\\automation\\config\\ObjectRepository.properties");
			OR.load(fp);

			System.setProperty("webdriver.chrome.driverSystem.getProperty("user.dir") + "/Third Party API/chromedriver.exe");
			System.setProperty("webdriver.gecko.driverSystem.getProperty("user.dir") + "/Third Party API/geckodriver.exe");
			
			datatable = new MSExcelAutomation(
					System.getProperty("user.dir") + "\\src\\com\\sixleaf\\automation\\xls\\Controller.xls");
			
			// checking the type of browser
			if (config.getProperty("browserType").equalsIgnoreCase("Firefox")) {

				wbDv = new FirefoxDriver();

			} else if (config.getProperty("browserType").equalsIgnoreCase("IE")) {

				wbDv = new InternetExplorerDriver();
				DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
				ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				wbDv = new InternetExplorerDriver(ieCapabilities);

			} else if (config.getProperty("browserType").equalsIgnoreCase("Chrome")) {

				//wbDv = new ChromeDriver();
				// maximizing the windows
				 ChromeOptions chromeOptions = new ChromeOptions();
			     chromeOptions.addArguments("--start-maximized");
			     wbDv = new ChromeDriver(chromeOptions);

			}

			// putting an implicit wait after every Action or Event

		     driver = new EventFiringWebDriver(wbDv);

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			// opening the browser
			driver.navigate().to(config.getProperty("DevSiteUrl"));

			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// Login to the application.

		/**
		 * This method is used to login to the application
		 */
		public static boolean login() {
			
			String userEmail = config.getProperty("userEmail");
			String userPassword = config.getProperty("userPassword");
			
			try {
				// To enter Email in text box
				Assert.assertTrue(WebUIAutomation.setText("txtfield_Email_LoginPage", userEmail),
						"User is unable to enter username");

				// To enter Password in text box
				Assert.assertTrue(WebUIAutomation.setText("txtfield_Password_LoginPage", userPassword),
						"User is unable to enter Password");
				

				// To click on Login button
				Assert.assertTrue(WebUIAutomation.clickObj("Btn_Login_LoginPage"),
						"Clicking on Login button is not successful");
				
				loggedIn = true;

				Thread.sleep(3000);
				}
				
			catch(Exception e) {
				e.printStackTrace();
			}
			return loggedIn;
		}
	
	/**
	 * This function is used to identify the object on the Application
	 * 
	 * @author Niharika
	 * @param xpathKey
	 *            - unique sudo name which we have kept for every object on the
	 *            web page
	 * @return WebElement
	 */
	public static WebElement getObject(String xpathKey) {

		WebElement obj = null;

		try {
			obj = driver.findElement(By.xpath(OR.getProperty(xpathKey)));

		} catch (Exception e) {

			obj = null;
		}

		return obj;

	}

}
