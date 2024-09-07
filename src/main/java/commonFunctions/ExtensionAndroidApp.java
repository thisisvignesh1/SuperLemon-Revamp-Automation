package commonFunctions;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import locators.ExtensionAppLocators;

@SuppressWarnings({ "unused" })
public class ExtensionAndroidApp {
	private static Logger log = Logger.getLogger(ExtensionAndroidApp.class);
	public static AndroidDriver<AndroidElement> driver;
	private static boolean flag = false;
	private static String titlename;
	private static boolean changepic = false;
//    private static WebDriverWait wait = new WebDriverWait(driver, 10);

	public static void initBrowser() throws MalformedURLException {
		try {
			log.info("reached here");
			File appdir = new File("/home/sumit/Downloads");
			log.info("reached here");
			File app = new File(appdir, "superLemon-prod-beta-1.5.apk");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Extension_App");
			log.info(capabilities.getCapability(MobileCapabilityType.DEVICE_NAME));
			log.info("reached here");
			capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
			log.info(capabilities.getCapability(MobileCapabilityType.APP));
			log.info("reached here");
			capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
			log.info(capabilities.getCapability(MobileCapabilityType.UDID));
			log.info("reached here");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11");
			log.info(capabilities.getCapability(MobileCapabilityType.PLATFORM_VERSION));
			capabilities.setCapability("appPackage", " io.gupshup.superlemon");
			log.info(capabilities.getCapability("appPackage"));
			capabilities.setCapability("appActivity", "io.gupshup.superlemon.ui.views.MainActivity");
			log.info(capabilities.getCapability("appActivity"));
//            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
			log.info("reached here");
			driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			log.info("reached here");
			log.info("reached here");
			log.info("reached here");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			log.info("reached here");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void loginToApp() {
		try {
			driver.findElement((By.xpath("//android.widget.EditText[@index='2']"))).sendKeys("Prod45");
			driver.findElement(By.xpath("//android.widget.Button[@index='1']")).click();
			Thread.sleep(10000);
			driver.findElement((By.xpath("//android.widget.EditText[@index='1']")))
					.sendKeys("bottest.gupshup.101@gmail.com");
			Thread.sleep(10000);
//            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.EditText[@index='1']")));
			driver.findElement(By.xpath("//android.widget.Button[@text='Next']")).click();
			driver.findElement((By.xpath("//android.widget.EditText[@index='0']"))).sendKeys("=watd\\vZz!$&UZMx");
			Thread.sleep(10000);
			driver.findElement(By.xpath("//android.widget.Button[@text='Log in']")).click();
			Thread.sleep(10000);
//
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean verifyTagAdditionToOrders(String tagName) throws InterruptedException {
//        driver.findElement(By.xpath("//android.widget.EditText[@index='0']")).sendKeys("gupshup");
////            Thread.sleep(10000);
		log.info("here");
		driver.findElement(By.xpath("//android.widget.Button[@index='4']")).click();
		log.info("here");
		Thread.sleep(10000);
		driver.findElement(By.xpath("//android.view.View[@text='" + tagName + "']")).click();
		log.info("here");
		Thread.sleep(10000);
		Boolean flag = driver.findElementsByXPath("//android.view.View[@text='" + tagName + "']").size() != 0;
		return flag;
	}

	public static boolean verifyLogOut() throws InterruptedException {
		Thread.sleep(10000);
		driver.findElement(By.xpath("//android.view.View[@bounds='[720,2043][1080,2208]']")).click();
		log.info("here");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//android.view.View[@bounds='[937,116][1003,182]']")).click();
		Thread.sleep(3000);
		log.info("here");
		AndroidElement element = driver.findElement(By.xpath("//android.widget.EditText[@index='2']"));
		flag = element.isDisplayed();
		return flag;
	}

	public static boolean verifyRemoveTagsFromOrders(String tagName) throws InterruptedException {
		driver.findElement(By.xpath(ExtensionAppLocators.TAG_ADD_REMOVE_ICON_ON_ORDERS)).click();
		log.info("here");
		Thread.sleep(10000);
		driver.findElement(By.xpath(ExtensionAppLocators.REMOVE_TAG)).click();
		log.info("here");
		Thread.sleep(10000);
		Boolean flag = driver.findElementsByXPath("//android.view.View[@text='" + tagName + "']").size() != 0;
		log.info(flag);
		return flag;
	}

	public static String createNewTag() throws InterruptedException {
		String tagName = "AutoTag" + RandomStringUtils.randomAlphanumeric(5);
		driver.findElement(By.xpath(ExtensionAppLocators.ADD_NEW_TAG)).click();
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ExtensionAppLocators.TAG_NAME_FIELD)));
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ExtensionAppLocators.TAG_NAME_FIELD)));
		driver.findElement(By.xpath(ExtensionAppLocators.TAG_NAME_FIELD)).sendKeys(tagName);
		driver.findElement(By.xpath(ExtensionAppLocators.ADD_BUTTON_TAG_CREATION)).click();
		return tagName;
	}

	public static void navigateToSettingsTab() throws InterruptedException {
		Thread.sleep(10000);
		driver.findElement(By.xpath(ExtensionAppLocators.SETTINGS_TAB)).click();
		log.info("here");
		Thread.sleep(3000);
	}

	public static String cancelNewTagCreation() throws InterruptedException {
		String tagName = "AutoTag" + RandomStringUtils.randomAlphanumeric(5);
		driver.findElement(By.xpath(ExtensionAppLocators.ADD_NEW_TAG)).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(ExtensionAppLocators.TAG_NAME_FIELD)).sendKeys(tagName);
		driver.findElement(By.xpath(ExtensionAppLocators.CANCEL_BUTTON_TAG_CREATION)).click();
		return tagName;
	}

	public static boolean verifyTagNameCharactersLimit() throws InterruptedException {
		String tagName = "AutoTag" + RandomStringUtils.randomAlphanumeric(35);
		driver.findElement(By.xpath(ExtensionAppLocators.ADD_NEW_TAG)).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(ExtensionAppLocators.TAG_NAME_FIELD)).sendKeys(tagName);
		AndroidElement element = driver.findElement(By.xpath(ExtensionAppLocators.TOO_LONG_TEXT));
		String text = element.getText();
		flag = element.isDisplayed();
		if (flag) {
			driver.findElement(By.xpath(ExtensionAppLocators.CANCEL_BUTTON_TAG_CREATION)).click();
		}
		return flag;
	}

	public static boolean verifySearchOrderByCustomerName() throws InterruptedException {
		driver.findElement(By.xpath(ExtensionAppLocators.SEARCH_BOX_ORDERS_SECTION)).sendKeys("Sumit");
//        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//android.view.View[@text='Sumit']")));
		Boolean flag = driver.findElementsByXPath("//android.view.View[@text='Sumit']").size() != 0;
		log.info(flag);
		return flag;
	}

	public static boolean verifyFilterOrdersWithTagName() throws InterruptedException {
		navigateToSettingsTab();
		log.info("here");
		String tagName = createNewTag();
		navigateToOrdersPage();
		log.info("here");
		verifyTagAdditionToOrders(tagName);
		Thread.sleep(3000);
		log.info("here");
		driver.findElement(By.xpath(ExtensionAppLocators.FILTER_ICON)).click();
		Thread.sleep(2000);
		log.info("here");
		driver.findElement(By.xpath("//android.view.View[@text='" + tagName + "']")).click();
		Thread.sleep(2000);
		Boolean flag = driver.findElementsByXPath("//android.view.View[@text='" + tagName + "']").size() != 0;
		return flag;
	}

	public static boolean verifyTagNameCreation() throws InterruptedException {
		ExtensionAndroidApp.navigateToSettingsTab();
		String tagName = ExtensionAndroidApp.createNewTag();
		Thread.sleep(3000);
		AndroidElement element = driver.findElement(By.xpath("//android.view.View[@text='" + tagName + "']"));
		flag = element.isDisplayed();
		return flag;

	}

	public static void navigateToOrdersPage() throws InterruptedException {
		driver.findElement(By.xpath(ExtensionAppLocators.ORDERS_TAB)).click();
		;
		Thread.sleep(3000);
	}

	public static boolean verifyExpandedView() throws InterruptedException {
		navigateToSettingsTab();
		Thread.sleep(3000);
		log.info("here");
		driver.findElement(By.xpath(ExtensionAppLocators.EXPANDED_VIEW_ICON)).click();
		log.info("here");
		ExtensionAndroidApp.navigateToOrdersPage();
		log.info("here");
		Boolean flag = driver.findElementsByXPath(ExtensionAppLocators.SELECT_TEMPLATE_BUTTON).size() != 0;
		log.info("here");
		return flag;
	}

	public static boolean cancelAppliedFilter() throws InterruptedException {
		verifyFilterOrdersWithTagName();
		Thread.sleep(3000);
		driver.findElement(By.xpath(ExtensionAppLocators.FILTER_ICON)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(ExtensionAppLocators.CLEAR_FILTER_OPTION)).click();
		return flag = true;
	}

	public static boolean deleteTagName() throws InterruptedException {
		String tagName;
		navigateToSettingsTab();
		tagName = createNewTag();
		driver.findElement(By.xpath(ExtensionAppLocators.DELETE_ICON_FIRST_TAG)).click();
		Boolean flag = driver.findElementsByXPath("//android.view.View[@text='" + tagName + "']").size() != 0;
		return flag;
	}

	public static boolean editTagName() throws InterruptedException {
		navigateToSettingsTab();
		String tagName = createNewTag();
		driver.findElement(By.xpath(ExtensionAppLocators.EDIT_ICON_FIRST_TAG)).click();
		String editedTagName = "EditedTag" + RandomStringUtils.randomAlphanumeric(5);
		driver.findElement(By.xpath("//android.widget.EditText[@text='" + tagName + "']"))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		driver.findElement(By.xpath("//android.widget.EditText[@text='" + tagName + "']")).sendKeys(editedTagName);
		driver.findElement(By.xpath(ExtensionAppLocators.ADD_BUTTON_TAG_CREATION)).click();
		Boolean flag = driver.findElementsByXPath("//android.view.View[@text='" + editedTagName + "']").size() != 0;
		return flag;
	}
}
