package com.Apptimice.Automation;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

public class TestBase {

	public static Properties config = null;
	public static Properties OR = null;
	public static boolean loggedIn = false;
	public static WebDriver wbDv = null;
	public static EventFiringWebDriver driver = null;
	public static MSExcelAutomation datatable = null;

	@BeforeSuite
	public void initialize() {

		// loading all the configuration values
		try {
			config = new Properties();
			FileInputStream fp = new FileInputStream(
				System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator
						+ "java" + File.separator + "com" + File.separator + "Apptimice" + File.separator
						+ "Automation" + File.separator + "config" + File.separator + "config.properties");
			config.load(fp);

			// loading Objects Repositories
			OR = new Properties();
			fp = new FileInputStream(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
					+ File.separator + "java" + File.separator + "com" + File.separator + "Apptimice" + File.separator
					+ "Automation" + File.separator + "config" + File.separator + "ObjectRepo.properties");
			OR.load(fp);

			datatable = new MSExcelAutomation(System.getProperty("user.dir") + File.separator + "src" + File.separator
					+ "test" + File.separator + "java" + File.separator + "com" + File.separator + "Apptimice"
					+ File.separator + "Automation" + File.separator + "xls" + File.separator + "Controller.xls");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void launchBrowser() {

		try {

			// checking the type of browser

			if (config.getProperty("browserType").equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\Geckodriver\\geckodriver.exe");
				wbDv = new FirefoxDriver();

			} else if (config.getProperty("browserType").equalsIgnoreCase("IE")) {

				wbDv = new InternetExplorerDriver();
				DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
				ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				wbDv = new InternetExplorerDriver(ieCapabilities);

			} else if (config.getProperty("browserType").equalsIgnoreCase("Chrome")) {
				
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\Chromedriver\\chromedriver.exe");
			
				wbDv = new ChromeDriver();
			}
			
			// To open the browser
			driver = new EventFiringWebDriver(wbDv);

			// maximizing the windows
			//driver.manage().window().maximize();

			// putting an implicit wait after every Action or Event
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

			// To open the URL

			driver.get(config.getProperty("liveSiteURL"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// Login to the application.

	/**
	 * This method is used to login to the application
	 */
	public static boolean login() {
		String userName = "Demo1";
		String password = "123456@@";
		try {
			// Wait for username field to display
			WebUIAutomation.isObjPresent("txtfield_Username_LoginPage", 10);

			// click on username field
			Assert.assertTrue(WebUIAutomation.clickObj("txtfield_Username_LoginPage"),
					"Unable to click on Username field.");

			// Enter the username into userName text field
			if (!(WebUIAutomation.setText("txtfield_Username_LoginPage", userName))) {
				Assert.fail("UserName textfield is not working");
			}

			// Enter the password into password text field
			if (!(WebUIAutomation.setText("txtfield_Password_LoginPage", password))) {
				Assert.fail("Password Text Field is not working");
			}
			// Click on login button on login page
			if (!(WebUIAutomation.clickObj("Btn_Login_LoginPage"))) {
				Assert.fail("Login button is not working LoginPage");

			}
			loggedIn = true;

			Thread.sleep(3000);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return loggedIn;
	}

	/**
	 * This function is used to identify the object on the Application
	 * 
	 * @author Nausha
	 * @param xpathKey
	 *            - unique sudo name which we have kept for every object on the
	 *            web page
	 * @return WebElement
	 */
	public static WebElement getObject(String pathKey) {
		WebElement obj = null;

		try {
			obj = driver.findElement(getLocator(pathKey, OR));
		} catch (Exception e) {
			obj = null;
		}
		return obj;
	}

	public static List<WebElement> getObjects(String xpathKeyOfElements) {

		List<WebElement> obj;

		try {
			By locator = getLocator(xpathKeyOfElements, OR);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			obj = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		} catch (Exception e) {
			obj = null;
		}

		return obj;

	}

	/**
	 * 
	 * @param strElement
	 * @param prop
	 * @return
	 * @throws Exception
	 */
	public static By getLocator(String strElement, Properties prop) throws Exception {

		// retrieve the specified object from the object list
		String locator = prop.getProperty(strElement);

		// extract the locator type and value from the object
		String locatorType = locator.split(":-")[0];
		String locatorValue = locator.split(":-")[1];

		// for testing and debugging purposes
		// System.out.println("Retrieving object of type '" + locatorType
		// + "' and value '" + locatorValue + "' from the object map");

		// return a instance of the By class based on the type of the locator
		// this By can be used by the browser object in the actual test
		switch (locatorType.toLowerCase().trim()) {
		case "id":
			return By.id(locatorValue);
		case "name":
			return By.name(locatorValue);
		case "classname":
		case "class":
			return By.className(locatorValue);
		case "tagname":
		case "tag":
			return By.className(locatorValue);
		case "linktext":
		case "link":
			return By.linkText(locatorValue);
		case "partiallinktext":
			return By.partialLinkText(locatorValue);
		case "cssselector":
		case "css":
			return By.cssSelector(locatorValue);
		case "xpath":
			return By.xpath(locatorValue);
		default:
			throw new Exception("Unknown locator type '" + locatorType + "'");
		}
	}

	/**
	 * This method will destroy driver instance
	 */
	public void quitBrowser() {
		driver.close();
		driver.quit();
	}

	

}
