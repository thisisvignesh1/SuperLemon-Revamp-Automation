package commonFunctions;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import locators.WidgetLocators;
import locators.ShopifyStoreLocators;
import utils.DriverBase;
import utils.DriverBase.ElementType;

@SuppressWarnings({ "static-access", "unused" })
public class ShopifyStore {
	private static Logger log = Logger.getLogger(ShopifyStore.class.getName());
	private static boolean flag;
	private static String propertyFilePath;
	private static String url;
	public static Properties prop = new Properties();
	private static DriverBase webDB = new DriverBase();
	private static String text;
	private static Set<String> windows;
	private static Iterator<String> iterator;
	private static String parentWindow;
	private static String childWindow;
	private static Actions actions = new Actions(webDB.driver);
	private static List<WebElement> elements;
	private static JavascriptExecutor jse = (JavascriptExecutor) webDB.driver;
	private static String autoAgentName;
	private static String endTimeHour;
	private static WebElement element;
	private static String emailId = "qatest@gupshup.io";
	private static String shippingAddress = "Gupshup Technology India Private Limited 11th Main Road 2nd Phase Jayanagar";
	public static String firstName = "AutoUser";
	public static String lastName = RandomStringUtils.randomNumeric(5);
	private static WebDriverWait wait = new WebDriverWait(webDB.driver, 10);
	private static String phone;
	private static String city = "Mumbai";
	private static String pinCode = "400098";

	public static void shopifyStorePassEnter() throws InterruptedException {
		jse.executeScript("window.localStorage.clear();");
		webDB.driver.manage().deleteAllCookies();
		webDB.driver.navigate().refresh();
		Thread.sleep(1000);
		flag = webDB.isElementDisplayed(ShopifyStoreLocators.STORE_PASS, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(ShopifyStoreLocators.STORE_PASS, "1234", ElementType.Xpath);
			webDB.clickAnElement(ShopifyStoreLocators.STORE_PASS_ENTER, ElementType.Xpath);
		} else {
			System.out.println("Already signed user");
			flag = true;
		}
	}

	public static boolean verifyOptinPopUpDisplayed() throws InterruptedException {
		Thread.sleep(2500);
		webDB.waitForElement(ShopifyStoreLocators.EMPTY_CART_ICON, ElementType.Xpath);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ShopifyStoreLocators.EMPTY_CART_ICON)));
		Thread.sleep(1500);
		webDB.clickAnElement(ShopifyStoreLocators.EMPTY_CART_ICON, ElementType.Xpath);
		log.debug("Clicked on Empty Cart Icon");
		flag = webDB.isElementDisplayed(ShopifyStoreLocators.CONFIRM_BUTTON_OPTIN_POPUP, ElementType.Id);
		flag = webDB.isElementDisplayed(ShopifyStoreLocators.OPTIN_POPUP_PHONE_FIELD, ElementType.Xpath);
		// flag =
		// webDB.isElementDisplayed(ShopifyStoreLocators.OPTIN_POPUP_COUNTRY_CODE_FIELD,
		// ElementType.Id);
		flag = webDB.isElementDisplayed(ShopifyStoreLocators.OPTIN_COUNTRYCODE, ElementType.Xpath);
		if (flag) {
			log.info("Optin Pop up displayed on Store");
		} else {
			log.info("Optin Pop Up not displayed on store");
		}
		return flag;
	}

	public static boolean verifyNumberSuccessfullyOptedIn() throws InterruptedException {
		String phone = RandomStringUtils.randomNumeric(10);
		webDB.selectDropDownOptions(ShopifyStoreLocators.OPTIN_POPUP_COUNTRY_CODE_FIELD, WidgetLocators.INDIA_CODE,
				ElementType.ClassName);
		webDB.sendTextToAnElement(ShopifyStoreLocators.OPTIN_POPUP_PHONE_FIELD, phone, ElementType.Xpath);
		webDB.clickAnElement(ShopifyStoreLocators.CONFIRM_BUTTON_OPTIN_POPUP, ElementType.Id);
		Thread.sleep(3000);
		LocalStorage local = ((WebStorage) webDB.driver).getLocalStorage();
		String optedPhone = CommonFunctions.getItemFromLocalStorage("opted_in_phone_v2");
		log.info(optedPhone);
		if (phone.equalsIgnoreCase(optedPhone)) {
			log.info("Opted In Successfully");
			flag = true;
		} else {
			flag = false;
			log.info("Could not opt in due to issue");
		}
		return flag;
	}

	public static boolean verifyCountryCodeEmptyOptinPopupValidationAlert() throws Throwable {
		parentWindow = webDB.driver.getWindowHandle();
		SettingsPage.enableOptinsAndCheckOnstore();
		wait.until(ExpectedConditions.elementToBeClickable(By.id(ShopifyStoreLocators.CONFIRM_BUTTON_OPTIN_POPUP)));
		webDB.clearTextField(ShopifyStoreLocators.OPTIN_POPUP_COUNTRY_CODE_FIELD, ElementType.Id);
		Thread.sleep(2000);
		webDB.clickAnElement(ShopifyStoreLocators.CONFIRM_BUTTON_OPTIN_POPUP, ElementType.Id);
		Thread.sleep(2000);
		Alert alert = webDB.driver.switchTo().alert();
		text = alert.getText();
		log.info("value of text is" + text);
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String OptinEmptyCountryCodeValidation = prop.getProperty("OptinEmptyCountryCodeValidation");
		if (text.equalsIgnoreCase(OptinEmptyCountryCodeValidation)) {
			flag = true;
			alert.accept();
		}
		webDB.driver.close();
		log.info("closed the child window");
		webDB.driver.switchTo().window(parentWindow);
		log.info("switched to parent window");
		return flag;

	}

	public static boolean verifyPhoneNumberEmptyOptinPopupValidationAlert() throws Throwable {
		parentWindow = webDB.driver.getWindowHandle();
		SettingsPage.enableOptinsAndCheckOnstore();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.className(ShopifyStoreLocators.OPTIN_POPUP_COUNTRY_CODE_FIELD)));
		webDB.selectDropDownOptions(ShopifyStoreLocators.OPTIN_POPUP_COUNTRY_CODE_FIELD, WidgetLocators.INDIA_CODE,
				ElementType.ClassName);
		webDB.clickAnElement(ShopifyStoreLocators.CONFIRM_BUTTON_OPTIN_POPUP, ElementType.Id);
		Thread.sleep(1000);
		Alert alert = webDB.driver.switchTo().alert();
		text = alert.getText();
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String OptinEmptyPhoneNumberValidation = prop.getProperty("OptinEmptyPhoneNumberValidation");
		if (text.equalsIgnoreCase(OptinEmptyPhoneNumberValidation)) {
			flag = true;
			alert.accept();
		}
		webDB.driver.close();
		log.info("closed the child window");
		webDB.driver.switchTo().window(parentWindow);
		log.info("switched to parent window");
		return flag;

	}

	public static boolean verifyInvalidPhoneAlertMessage() throws Throwable {
		parentWindow = webDB.driver.getWindowHandle();
		SettingsPage.enableOptinsAndCheckOnstore();
		flag = webDB.waitForElement(ShopifyStoreLocators.OPTIN_POPUP_COUNTRY_CODE_FIELD, ElementType.ClassName);
		if (flag) {
			webDB.selectDropDownOptions(ShopifyStoreLocators.OPTIN_POPUP_COUNTRY_CODE_FIELD, WidgetLocators.INDIA_CODE,
					ElementType.ClassName);
			webDB.sendTextToAnElement(ShopifyStoreLocators.OPTIN_POPUP_PHONE_FIELD, "123", ElementType.Xpath);
			webDB.clickAnElement(ShopifyStoreLocators.CONFIRM_BUTTON_OPTIN_POPUP, ElementType.Id);
			Thread.sleep(3000);
			Alert alert = webDB.driver.switchTo().alert();
			text = alert.getText();
			propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
			prop.load(new FileInputStream(propertyFilePath));
			String OptinInvalidPhoneNumberAlert = prop.getProperty("OptinEmptyPhoneNumberValidation");
			if (text.equalsIgnoreCase(OptinInvalidPhoneNumberAlert)) {
				flag = true;
				alert.accept();

			}
			webDB.driver.close();
			log.info("closed the child window");
			webDB.driver.switchTo().window(parentWindow);
			log.info("switched to parent window");
		}
		return flag;

	}

	public static boolean verifyOptinFromThankYouPage() throws InterruptedException {
		phone = "78" + RandomStringUtils.randomNumeric(8);
		ShopifyStore.addItemAndCheckout();
//		jse.executeScript("window.localStorage.clear();");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ShopifyStoreLocators.CHECKOUT_EMAIL_PHONE)));
		webDB.driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ShopifyStoreLocators.CHECKOUT_EMAIL_PHONE)));
		ShopifyStore.enterShippingDetailsWithPhone();
//		jse.executeScript("window.localStorage.clear();");
		Thread.sleep(3000);
		ShopifyStore.enterCardDetails();
		webDB.clickAnElement(ShopifyStoreLocators.CONTINUE_BTN, ElementType.Xpath);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ShopifyStoreLocators.ORDER_CONFIRMED_MESSAGE)));
		webDB.driver.navigate().refresh();
//		String scriptToExecute = "var performance = window.performance || window.mozPerformance || window.msPerformance || window.webkitPerformance || {}; var network = performance.getEntries() || {}; return network;";
//		String netData = ((JavascriptExecutor)webDB.driver).executeScript(scriptToExecute).toString();
//		System.out.println(netData);
//		wait.until(
//				ExpectedConditions.elementToBeClickable(By.id(ShopifyStoreLocators.THANK_U_PAGE_OPTIN_COUNTRY_CODE)));
		webDB.isElementDisplayed(ShopifyStoreLocators.THANK_U_PAGE_OPTIN_COUNTRY_CODE, ElementType.Id);
//		webDB.driver.findElement(By.id(ShopifyStoreLocators.THANK_U_PAGE_OPTIN_COUNTRY_CODE))
//				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.selectDropDownOptions(ShopifyStoreLocators.THANK_U_PAGE_OPTIN_COUNTRY_CODE, WidgetLocators.INDIA_CODE,
				ElementType.Id);
		webDB.sendTextToAnElement(ShopifyStoreLocators.OPTIN_PHONE_THANKYOU_PAGE, phone, ElementType.Id);
		webDB.clickAnElement(ShopifyStoreLocators.OPTIN_CONFIRM_THANKU_PAGE, ElementType.Id);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(ShopifyStoreLocators.OPTIN_CONFIRM_SUCCESS_MSG)));
		flag = webDB.isElementDisplayed(ShopifyStoreLocators.OPTIN_CONFIRM_SUCCESS_MSG, ElementType.Xpath);
		if (flag) {
			text = webDB.driver.findElement(By.xpath(ShopifyStoreLocators.OPTIN_CONFIRM_SUCCESS_MSG)).getText();
			log.info(text);
			if (text.contains(phone)) {
				log.info("Opted in successfully through widget on thank you page");
				flag = true;
			}
		} else {
			flag = false;
		}
		return flag;

	}

	public static void enterCardDetails() throws InterruptedException {
		Thread.sleep(5000);
		List<WebElement> iframeElements = webDB.driver.findElements(By.tagName("iframe"));
		log.info("Total number of iframes are " + iframeElements.size());
		webDB.driver.switchTo().frame(webDB.driver.findElement(By.xpath("(//iframe[@class='card-fields-iframe'])[1]")));
		webDB.sendTextToAnElement(ShopifyStoreLocators.CARD_NUMBER_PAYMENTS, "1", ElementType.Xpath);
		webDB.driver.switchTo().defaultContent();
		webDB.driver.switchTo()
				.frame(webDB.driver.findElement(By.xpath("(//iframe[contains(@class,'card-fields-iframe')])[2]")));
		webDB.sendTextToAnElement(ShopifyStoreLocators.NAME_ON_CARD, "Bogus Gateway", ElementType.Xpath);
		webDB.driver.switchTo().defaultContent();
		webDB.driver.switchTo()
				.frame(webDB.driver.findElement(By.xpath("(//iframe[contains(@class,'card-fields-iframe')])[3]")));
		webDB.sendTextToAnElement(ShopifyStoreLocators.CARD_EXPIRY, "11", ElementType.Xpath);
		Thread.sleep(1000);
		webDB.sendTextToAnElement(ShopifyStoreLocators.CARD_EXPIRY, "24", ElementType.Xpath);
		webDB.driver.switchTo().defaultContent();
		webDB.driver.switchTo()
				.frame(webDB.driver.findElement(By.xpath("(//iframe[contains(@class,'card-fields-iframe')])[4]")));
		webDB.sendTextToAnElement(ShopifyStoreLocators.CARD_CVV, "111", ElementType.Xpath);
		webDB.driver.switchTo().defaultContent();
	}

	public static void enterShippingDetailsWithEmail() throws InterruptedException {
		webDB.sendTextToAnElement(ShopifyStoreLocators.CHECKOUT_EMAIL_PHONE, emailId, ElementType.Xpath);
		Thread.sleep(1000);
		webDB.clickAnElement(ShopifyStoreLocators.CHECKOUT_OPTIN_CHECKBOX, ElementType.Xpath);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ShopifyStoreLocators.COUNTRY_DROPDOWN)));
		Select country = new Select(webDB.driver.findElement(By.xpath(ShopifyStoreLocators.COUNTRY_DROPDOWN)));
		Thread.sleep(1000);
		country.selectByVisibleText("India");
		Thread.sleep(1000);
		webDB.sendTextToAnElement(ShopifyStoreLocators.FIRST_NAME, firstName, ElementType.Xpath);
		Thread.sleep(8000);
		webDB.waitForElement(ShopifyStoreLocators.LAST_NAME, ElementType.Xpath);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ShopifyStoreLocators.LAST_NAME)));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ShopifyStoreLocators.LAST_NAME)));
		webDB.sendTextToAnElement(ShopifyStoreLocators.LAST_NAME, lastName, ElementType.Xpath);
		Thread.sleep(1000);
		webDB.sendTextToAnElement(ShopifyStoreLocators.ADDRESS_LINE_1, shippingAddress, ElementType.CssSelector);
		webDB.sendTextToAnElement(ShopifyStoreLocators.ADDRESS_LINE_1, shippingAddress, ElementType.CssSelector);
		webDB.driver.findElement(By.xpath(ShopifyStoreLocators.SHIPPING_ADDRESS_CITY))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(ShopifyStoreLocators.SHIPPING_ADDRESS_CITY, city, ElementType.Xpath);
		Thread.sleep(1000);
		Select state = new Select(webDB.driver.findElement(By.xpath(ShopifyStoreLocators.SHIPPING_ADDRESS_STATE)));
		state.selectByVisibleText("Maharashtra");
		Thread.sleep(2000);
		webDB.driver.findElement(By.xpath(ShopifyStoreLocators.SHIPPING_ADDRESS_PINCODE))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(ShopifyStoreLocators.SHIPPING_ADDRESS_PINCODE, pinCode, ElementType.Xpath);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ShopifyStoreLocators.CONTINUE_BTN)));
		webDB.clickAnElement(ShopifyStoreLocators.CONTINUE_BTN, ElementType.Xpath);
		webDB.clickAnElement(ShopifyStoreLocators.CONTINUE_BTN, ElementType.Xpath);
	}

	public static void addItemAndCheckout() throws InterruptedException {
		flag = webDB.clickAnElement(ShopifyStoreLocators.POISTORECATALOG_TAB, ElementType.Xpath);
		if (flag) {
			System.out.println("Catalog Clicked");
		} else {
			webDB.clickAnElement(ShopifyStoreLocators.CATALOG_TAB, ElementType.LinkText);
			flag = true;
		}
		element = webDB.driver.findElement(By.xpath(ShopifyStoreLocators.ITEM_SELECT));
		System.out.println(element);
		System.out.println("flag (additemandcheckout) = " + flag);
		JavascriptExecutor storeExecutor = (JavascriptExecutor) webDB.driver;
		storeExecutor.executeScript("arguments[0].click();", element);
		jse.executeScript("window.localStorage.clear();");
		webDB.driver.navigate().refresh();
		Thread.sleep(4000);
//		webDB.clickAnElement(ShopifyStoreLocators.ITEM_SELECT, ElementType.Xpath);
		flag = webDB.waitForElement(ShopifyStoreLocators.BUY_NOW, ElementType.Xpath);
		if (flag) {
			System.out.println(flag);
			webDB.moveToElement(ShopifyStoreLocators.BUY_NOW, ElementType.Xpath);
			webDB.clickAnElement(ShopifyStoreLocators.BUY_NOW, ElementType.Xpath);
			if (webDB.waitForElement(ShopifyStoreLocators.OPTIN_POPUP_CLOSE_ICON, ElementType.CssSelector)) {
				System.out.println("Close icon is visible");
				Thread.sleep(2000);
				webDB.clickAnElement(ShopifyStoreLocators.OPTIN_POPUP_CLOSE_ICON, ElementType.CssSelector);
			} else {
				System.out.println("Close icon is not visible");
				flag = webDB.clickAnElement(ShopifyStoreLocators.BUY_NOW, ElementType.Xpath);
			}
		}
	}

	public static void POIaddItemAndCheckout() throws InterruptedException {
		flag = webDB.clickAnElement(ShopifyStoreLocators.POISTORECATALOG_TAB, ElementType.Xpath);
		if (flag) {
			System.out.println("Catalog Clicked");
		} else {
			webDB.clickAnElement(ShopifyStoreLocators.CATALOG_TAB, ElementType.LinkText);
			flag = true;
		}
		element = webDB.driver.findElement(By.xpath(ShopifyStoreLocators.POIITEM_SELECT));
		System.out.println(element);
		JavascriptExecutor storeExecutor = (JavascriptExecutor) webDB.driver;
		storeExecutor.executeScript("arguments[0].click();", element);
		jse.executeScript("window.localStorage.clear();");
		webDB.driver.navigate().refresh();
		Thread.sleep(4000);
//		webDB.clickAnElement(ShopifyStoreLocators.ITEM_SELECT, ElementType.Xpath);
		webDB.waitForElement(ShopifyStoreLocators.BUY_NOW, ElementType.Xpath);
		webDB.clickAnElement(ShopifyStoreLocators.BUY_NOW, ElementType.Xpath);
		if (webDB.isElementDisplayed(ShopifyStoreLocators.OPTIN_POPUP_CLOSE_ICON, ElementType.CssSelector)) {
			webDB.clickAnElement(ShopifyStoreLocators.OPTIN_POPUP_CLOSE_ICON, ElementType.CssSelector);
		}
	}

	public static String enterShippingDetailsWithPhone() throws InterruptedException {
		Thread.sleep(2000);
		phone = "7854" + RandomStringUtils.randomNumeric(6);
		System.out.println("Phone = " + phone);
		String fullName = firstName + lastName;
		System.out.println("Name = " + fullName);
		flag = webDB.waitForElement(ShopifyStoreLocators.CONTACT, ElementType.Xpath);
//		flag = webDB.waitForElement(ShopifyStoreLocators.CHECKOUT_EMAIL_PHONE, ElementType.Xpath);
		if (flag) {
			// System.out.println("flag (entershippingdetails) = " + flag);
			flag = webDB.waitForElement(ShopifyStoreLocators.EMAIL_OR_PHONE, ElementType.Xpath);
			if (flag) {
				webDB.sendTextToAnElement(ShopifyStoreLocators.EMAIL_OR_PHONE, phone, ElementType.Xpath);
				// System.out.println("email id = " + emailId);

//			webDB.sendTextToAnElement(ShopifyStoreLocators.CHECKOUT_EMAIL_PHONE, emailId, ElementType.Xpath);
//			webDB.clickAnElement(ShopifyStoreLocators.CHECKOUT_OPTIN_CHECKBOX, ElementType.Xpath);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ShopifyStoreLocators.COUNTRY_DROPDOWN)));
				Select country = new Select(webDB.driver.findElement(By.xpath(ShopifyStoreLocators.COUNTRY_DROPDOWN)));
				country.selectByVisibleText("India");
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ShopifyStoreLocators.FIRST_NAME)));
				webDB.sendTextToAnElement(ShopifyStoreLocators.FIRST_NAME, firstName, ElementType.Xpath);
				Thread.sleep(8000);
//		webDB.waitForElement(ShopifyStoreLocators.LAST_NAME, ElementType.Xpath);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ShopifyStoreLocators.LAST_NAME)));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ShopifyStoreLocators.LAST_NAME)));
				webDB.sendTextToAnElement(ShopifyStoreLocators.LAST_NAME, lastName, ElementType.Xpath);
				webDB.sendTextToAnElement(ShopifyStoreLocators.ADDRESS_LINE_1, shippingAddress,
						ElementType.CssSelector);
				webDB.driver.findElement(By.xpath(ShopifyStoreLocators.SHIPPING_ADDRESS_CITY))
						.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
				webDB.sendTextToAnElement(ShopifyStoreLocators.SHIPPING_ADDRESS_CITY, city, ElementType.Xpath);
				Thread.sleep(1000);
				Select state = new Select(
						webDB.driver.findElement(By.xpath(ShopifyStoreLocators.SHIPPING_ADDRESS_STATE)));
				state.selectByVisibleText("Maharashtra");
				Thread.sleep(2000);
				webDB.driver.findElement(By.xpath(ShopifyStoreLocators.SHIPPING_ADDRESS_PINCODE))
						.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
				webDB.sendTextToAnElement(ShopifyStoreLocators.SHIPPING_ADDRESS_PINCODE, pinCode, ElementType.Xpath);
				Thread.sleep(1000);
//		webDB.clickAnElement(ShopifyStoreLocators.ADDRESS_SELECT_FROM_SUGGESTIONS, ElementType.Xpath);
//		webDB.driver.findElement(By.xpath(ShopifyStoreLocators.SHIPPING_ADDRESS_PHONE))
//				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
//		webDB.sendTextToAnElement(ShopifyStoreLocators.SHIPPING_ADDRESS_PHONE, phone, ElementType.Xpath);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ShopifyStoreLocators.CONTINUE_BTN)));
				webDB.clickAnElement(ShopifyStoreLocators.CONTINUE_BTN, ElementType.Xpath);
				flag = webDB.waitForElement(ShopifyStoreLocators.SHIPPINGMETHOD, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(ShopifyStoreLocators.CONTINUE_BTN, ElementType.Xpath);
					Thread.sleep(1000);
				}
			}
		}
		return phone;
	}

	public static String enterShippingDetailsWithExistingno(String phone) throws InterruptedException {

		String fullName = firstName + lastName;
		Thread.sleep(2000);
		flag = webDB.waitForElement(ShopifyStoreLocators.CHECKOUT_EMAIL_PHONE, ElementType.Xpath);
		if (flag) {
			System.out.println(flag);
			webDB.copyPaste(ShopifyStoreLocators.FIRST_NAME, phone, ShopifyStoreLocators.CHECKOUT_EMAIL_PHONE);
			webDB.clearText(ShopifyStoreLocators.FIRST_NAME);
			// webDB.sendTextToAnElement(ShopifyStoreLocators.CHECKOUT_EMAIL_PHONE,
			// "918693858861", ElementType.Xpath);
			Thread.sleep(1500);
			Select contactno = new Select(
					webDB.driver.findElement(By.xpath(ShopifyStoreLocators.CONTACTNOCOUNTRYREGION)));
			contactno.selectByValue("IN");
//			webDB.clearText(ShopifyStoreLocators.CHECKOUT_EMAIL_PHONE);
//			webDB.sendTextToAnElement(ShopifyStoreLocators.CHECKOUT_EMAIL_PHONE, phone, ElementType.Xpath);
//			webDB.clickAnElement(ShopifyStoreLocators.CHECKOUT_OPTIN_CHECKBOX, ElementType.Xpath);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ShopifyStoreLocators.COUNTRY_DROPDOWN)));
			Select country = new Select(webDB.driver.findElement(By.xpath(ShopifyStoreLocators.COUNTRY_DROPDOWN)));
			country.selectByVisibleText("India");
			Thread.sleep(2000);

			Thread.sleep(3000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ShopifyStoreLocators.FIRST_NAME)));
			webDB.sendTextToAnElement(ShopifyStoreLocators.FIRST_NAME, firstName, ElementType.Xpath);
			Thread.sleep(8000);
//		webDB.waitForElement(ShopifyStoreLocators.LAST_NAME, ElementType.Xpath);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ShopifyStoreLocators.LAST_NAME)));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ShopifyStoreLocators.LAST_NAME)));
			webDB.sendTextToAnElement(ShopifyStoreLocators.LAST_NAME, lastName, ElementType.Xpath);
			webDB.sendTextToAnElement(ShopifyStoreLocators.ADDRESS_LINE_1, shippingAddress, ElementType.CssSelector);
			webDB.driver.findElement(By.xpath(ShopifyStoreLocators.SHIPPING_ADDRESS_CITY))
					.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			webDB.sendTextToAnElement(ShopifyStoreLocators.SHIPPING_ADDRESS_CITY, city, ElementType.Xpath);
			Thread.sleep(1000);
			Select state = new Select(webDB.driver.findElement(By.xpath(ShopifyStoreLocators.SHIPPING_ADDRESS_STATE)));
			state.selectByVisibleText("Maharashtra");
			Thread.sleep(2000);
			webDB.driver.findElement(By.xpath(ShopifyStoreLocators.SHIPPING_ADDRESS_PINCODE))
					.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			webDB.sendTextToAnElement(ShopifyStoreLocators.SHIPPING_ADDRESS_PINCODE, pinCode, ElementType.Xpath);
			Thread.sleep(1000);
//		webDB.clickAnElement(ShopifyStoreLocators.ADDRESS_SELECT_FROM_SUGGESTIONS, ElementType.Xpath);
//		webDB.driver.findElement(By.xpath(ShopifyStoreLocators.SHIPPING_ADDRESS_PHONE))
//				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
//		webDB.sendTextToAnElement(ShopifyStoreLocators.SHIPPING_ADDRESS_PHONE, phone, ElementType.Xpath);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ShopifyStoreLocators.CONTINUE_BTN)));
			webDB.clickAnElement(ShopifyStoreLocators.CONTINUE_BTN, ElementType.Xpath);
			flag = webDB.waitForElement(ShopifyStoreLocators.SHIPPINGMETHOD, ElementType.Xpath);
			log.info("Shipping method Text found " + flag);
			if (flag) {
				log.info("Continue button is clicked " + flag);

				webDB.clickAnElement(ShopifyStoreLocators.CONTINUE_BTN, ElementType.Xpath);
				Thread.sleep(1000);
			}
		}
		return phone;
	}

	public static String enterShippingDetailsWithPhoneWithoutOptinCheckBoxTicked() throws InterruptedException {
		Thread.sleep(2000);
		phone = "7854" + RandomStringUtils.randomNumeric(6);
		String fullName = firstName + lastName;
		webDB.sendTextToAnElement(ShopifyStoreLocators.CHECKOUT_EMAIL_PHONE, phone, ElementType.Xpath);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ShopifyStoreLocators.COUNTRY_DROPDOWN)));
		Select country = new Select(webDB.driver.findElement(By.xpath(ShopifyStoreLocators.COUNTRY_DROPDOWN)));
		country.selectByVisibleText("India");
		webDB.sendTextToAnElement(ShopifyStoreLocators.FIRST_NAME, firstName, ElementType.Xpath);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ShopifyStoreLocators.LAST_NAME)));
		Thread.sleep(8000);
		webDB.sendTextToAnElement(ShopifyStoreLocators.LAST_NAME, lastName, ElementType.Xpath);
		webDB.sendTextToAnElement(ShopifyStoreLocators.ADDRESS_LINE_1, shippingAddress, ElementType.CssSelector);
		webDB.driver.findElement(By.xpath(ShopifyStoreLocators.SHIPPING_ADDRESS_CITY))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(ShopifyStoreLocators.SHIPPING_ADDRESS_CITY, city, ElementType.Xpath);
		Thread.sleep(1000);
		Select state = new Select(webDB.driver.findElement(By.xpath(ShopifyStoreLocators.SHIPPING_ADDRESS_STATE)));
		state.selectByVisibleText("Maharashtra");
		Thread.sleep(2000);
		webDB.driver.findElement(By.xpath(ShopifyStoreLocators.SHIPPING_ADDRESS_PINCODE))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(ShopifyStoreLocators.SHIPPING_ADDRESS_PINCODE, pinCode, ElementType.Xpath);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ShopifyStoreLocators.CONTINUE_BTN)));
		webDB.clickAnElement(ShopifyStoreLocators.CONTINUE_BTN, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.waitForElement(ShopifyStoreLocators.SHIPPINGMETHOD, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ShopifyStoreLocators.CONTINUE_BTN, ElementType.Xpath);
			Thread.sleep(1000);
		}
		return phone;
	}

	public static boolean addItemAndAddtocart() throws InterruptedException {
		webDB.clickAnElement(ShopifyStoreLocators.CATALOG_TAB, ElementType.LinkText);
		element = webDB.driver.findElement(By.xpath(ShopifyStoreLocators.ITEM_SELECT));
		System.out.println(element);
		JavascriptExecutor storeExecutor = (JavascriptExecutor) webDB.driver;
		storeExecutor.executeScript("arguments[0].click();", element);
//		storeExecutor.executeScript("window.localStorage.clear();");
//		webDB.driver.navigate().refresh();
		Thread.sleep(4000);
		webDB.clickAnElement(ShopifyStoreLocators.ADD_TO_CART, ElementType.Xpath);
		flag = webDB.verifyElementIsDisplayed(ShopifyStoreLocators.OPTIN_POPUP, ElementType.CssSelector);
		if (flag) {
			System.out.println("Optin popup is displayed");
			webDB.clickAnElement(ShopifyStoreLocators.OPTIN_POPUP_CLOSE_ICON, ElementType.CssSelector);
		}
		flag = webDB.waitForElement(ShopifyStoreLocators.ADDTOCART_NOTIFICATION, ElementType.CssSelector);
		if (flag) {
			webDB.clickAnElement(ShopifyStoreLocators.CART_ICON, ElementType.CssSelector);
			Thread.sleep(2000);
			storeExecutor.executeScript("window.localStorage.clear();");
			webDB.driver.navigate().refresh();
			flag = webDB.waitForElement(ShopifyStoreLocators.CART_CHECKOUT_BTN, ElementType.CssSelector);
			if (flag) {
				webDB.clickAnElement(ShopifyStoreLocators.CART_CHECKOUT_BTN, ElementType.CssSelector);
				Thread.sleep(2000);
				webDB.driver.navigate().back();
				flag = webDB.waitForElement(ShopifyStoreLocators.OPTIN_POPUP, ElementType.CssSelector);
				System.out.println(flag);
				if (flag) {
					log.info("Verified optin popup");
				} else {
					log.info("Verified optin popup is disabled by deselecting");
					flag = true;
				}
			}
		}
		return flag;
	}

	public static boolean verifyOptinviaChatEnable() throws InterruptedException {
		Thread.sleep(2500);
		webDB.waitForElement(ShopifyStoreLocators.CHATWIDGET_TEXT, ElementType.Xpath);
		Thread.sleep(1500);
		// modified - commented below and added javascript click
		// webDB.clickAnElement(ShopifyStoreLocators.CHATWIDGET_TEXT,
		// ElementType.Xpath);
		webDB.javaScriptClickAnElement(ShopifyStoreLocators.CHATWIDGET_TEXT, ElementType.Xpath);
		log.debug("Clicked Chat Widget Icon");
		flag = webDB.isElementDisplayed(ShopifyStoreLocators.GREETINGS_CHATBOX, ElementType.Xpath);
		if (flag) {
			log.info("Verified chatbox design");
			flag = webDB.isElementDisplayed(ShopifyStoreLocators.STORE_OFFLINE_MSG_TEXT, ElementType.CssSelector);
			if (flag) {
				webDB.javaScriptClickAnElement(ShopifyStoreLocators.STORE_OFFLINE_MSG_TEXT, ElementType.CssSelector);
				log.info("Clicked chat button");
				// modified - added wait and changed below locator
				Thread.sleep(2000);
				webDB.waitForElement(ShopifyStoreLocators.OPTIN_POPUP_COUNTRY_CODE_FIELD, ElementType.Xpath);
				flag = webDB.isElementDisplayed(ShopifyStoreLocators.OPTIN_POPUP_COUNTRY_CODE_FIELD, ElementType.Xpath);
				if (flag) {
					log.info("Verified optinvia chatwidget enable");
				}
			}
		}
		return flag;
	}

	public static boolean verifyOptinviaChatDisable() throws InterruptedException {
		Thread.sleep(2500);
		webDB.waitForElement(ShopifyStoreLocators.CHATWIDGET_TEXT, ElementType.Xpath);
		Thread.sleep(1500);
		webDB.clickAnElement(ShopifyStoreLocators.CHATWIDGET_TEXT, ElementType.Xpath);
		log.debug("Clicked Chat Widget Icon");
		// flag = webDB.isElementDisplayed(ShopifyStoreLocators.GREETINGS_CHATBOX,
		// ElementType.CssSelector);
		flag = webDB.isElementDisplayed(ShopifyStoreLocators.FIRST_CHAT_AGENT, ElementType.Xpath);
		if (flag) {
			// log.info("Verified chatbox design");
			flag = webDB.waitForElement(ShopifyStoreLocators.FIRST_CHAT_AGENT, ElementType.Xpath);
			if (flag) {
				// webDB.clickAnElement(ShopifyStoreLocators.FIRST_CHAT_AGENT,
				// ElementType.Xpath);
				webDB.javaScriptClickAnElement(ShopifyStoreLocators.FIRST_CHAT_AGENT, ElementType.Xpath);
				log.info("Clicked 1st agent");
				Thread.sleep(1500);
//			flag = webDB.isElementDisplayed(ShopifyStoreLocators.STORE_OFFLINE_MSG_TEXT, ElementType.CssSelector);
//			if (flag) {
//				webDB.javaScriptClickAnElement(ShopifyStoreLocators.STORE_OFFLINE_MSG_TEXT, ElementType.CssSelector);
				// flag =
				// webDB.isElementDisplayed(ShopifyStoreLocators.OPTIN_POPUP_COUNTRY_CODE_FIELD,
				// ElementType.Id);
				flag = webDB.isElementDisplayed(ShopifyStoreLocators.OPTIN_COUNTRYCODE, ElementType.Xpath);
				if (!flag) {
					log.info("Optinvia chat widget Disabled");
					flag = true;
				} else {
					log.info("Optinvia chat widget Enabled");
					// flag = false;
				}
			}
		}
		return flag;
	}

	public static boolean verifyOptinviaChatHeadingandHelpText() throws InterruptedException {
		Thread.sleep(2500);
		webDB.waitForElement(ShopifyStoreLocators.CHATWIDGET_TEXT, ElementType.Xpath);
		Thread.sleep(1500);
		webDB.clickAnElement(ShopifyStoreLocators.CHATWIDGET_TEXT, ElementType.Xpath);
		log.debug("Clicked Chat Widget Icon");
		// modified - changed locator type
		flag = webDB.isElementDisplayed(ShopifyStoreLocators.GREETINGS_CHATBOX, ElementType.Xpath);
		if (flag) {
			log.info("Verified chatbox design");
			flag = webDB.isElementDisplayed(ShopifyStoreLocators.STORE_OFFLINE_MSG_TEXT, ElementType.CssSelector);
			if (flag) {
				webDB.javaScriptClickAnElement(ShopifyStoreLocators.STORE_OFFLINE_MSG_TEXT, ElementType.CssSelector);
				String Headingtext = webDB.getText(ShopifyStoreLocators.GREETINGS_HEADER_TITLE,
						ElementType.CssSelector);
				System.out.println(Headingtext);
				if (Headingtext.equals(WidgetLocators.HEADINGTXT)) {
					log.info("Verified chatbox heading");
					String Helptext = webDB.getText(ShopifyStoreLocators.GREETINGS_DESC_TEXT, ElementType.CssSelector);
					System.out.println(Helptext);
					if (Helptext.equals(WidgetLocators.HELPTEXT)) {
						log.info("Verified chatbox helptext");
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyOptinviaChatBoxColor() throws InterruptedException {
		Thread.sleep(2500);
		webDB.waitForElement(ShopifyStoreLocators.CHATWIDGET_TEXT, ElementType.Xpath);
		Thread.sleep(1500);
		webDB.clickAnElement(ShopifyStoreLocators.CHATWIDGET_TEXT, ElementType.Xpath);
		log.debug("Clicked Chat Widget Icon");
		// modified - added wait time
		Thread.sleep(2000);
		webDB.waitForElement(ShopifyStoreLocators.GREETINGS_CHATBOX, ElementType.Xpath);
		flag = webDB.isElementDisplayed(ShopifyStoreLocators.GREETINGS_CHATBOX, ElementType.Xpath);
		if (flag) {
			log.info("Verified chatbox design");
			flag = webDB.isElementDisplayed(ShopifyStoreLocators.STORE_OFFLINE_MSG_TEXT, ElementType.CssSelector);
			if (flag) {
				log.info("chatbox is displayed");
				webDB.javaScriptClickAnElement(ShopifyStoreLocators.STORE_OFFLINE_MSG_TEXT, ElementType.CssSelector);
				log.info("chatbox is clicked");
				// modified - below locator - again modified to old locator and added wait
				Thread.sleep(2000);
				flag = webDB.waitForElement(ShopifyStoreLocators.OPTINVIA_CHATWIDGET_BACKROUND,
						ElementType.CssSelector);
				Thread.sleep(2000);
				if (flag) {
					log.info("Chat widget background is visible");
					WebElement Body = webDB.driver
							.findElement(By.cssSelector(ShopifyStoreLocators.OPTINVIA_CHATWIDGET_BACKROUND));
					log.info(Body);
					String bodycolor = Body.getCssValue("background-color");
					log.info(bodycolor);
					String color = Color.fromString(bodycolor).asHex();
					log.info(color);
//					System.out.println("Color is :" + bodycolor);
//					System.out.println(color);
					flag = color.contains(WidgetLocators.REDCOLOR);
					if (flag) {
						log.info("Verified selected color changed");
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyOptinviaChatBoxHeaderColor() throws InterruptedException {
		Thread.sleep(2500);
		webDB.waitForElement(ShopifyStoreLocators.CHATWIDGET_TEXT, ElementType.Xpath);
		Thread.sleep(1500);
		webDB.clickAnElement(ShopifyStoreLocators.CHATWIDGET_TEXT, ElementType.Xpath);
		log.debug("Clicked Chat Widget Icon");
		flag = webDB.isElementDisplayed(ShopifyStoreLocators.GREETINGS_CHATBOX, ElementType.CssSelector);
		if (flag) {
			log.info("Verified chatbox design");
			flag = webDB.isElementDisplayed(ShopifyStoreLocators.STORE_OFFLINE_MSG_TEXT, ElementType.CssSelector);
			if (flag) {
				webDB.javaScriptClickAnElement(ShopifyStoreLocators.STORE_OFFLINE_MSG_TEXT, ElementType.CssSelector);
				flag = webDB.waitForElement(ShopifyStoreLocators.OPTINVIA_CHATWIDGET_HEADER, ElementType.CssSelector);
				if (flag) {
					log.info("ChatWidget header is visible");
					WebElement header = webDB.driver
							.findElement(By.cssSelector(ShopifyStoreLocators.OPTINVIA_CHATWIDGET_HEADER));
					String headercolor = header.getCssValue("color");
					String Hcolor = Color.fromString(headercolor).asHex();
//					System.out.println("Color is :" + headercolor);
//					System.out.println(Hcolor);
					flag = Hcolor.contains(WidgetLocators.COLOR);
					if (flag) {
						log.info("Verified selected header color changed");
						flag = webDB.waitForElement(ShopifyStoreLocators.OPTINVIA_CHATWIDGET_DESC,
								ElementType.CssSelector);
						if (flag) {
							WebElement desc = webDB.driver
									.findElement(By.cssSelector(ShopifyStoreLocators.OPTINVIA_CHATWIDGET_DESC));
							String desccolor = desc.getCssValue("color");
							String Dcolor = Color.fromString(desccolor).asHex();
//							System.out.println("Color is :" + desccolor);
//							System.out.println(Dcolor);
							flag = Dcolor.contains(WidgetLocators.COLOR);
							if (flag) {
								log.info("Verified selected Descprition color changed");
							}
						}
					}
				}
			}
		}
		return flag;
	}

}
