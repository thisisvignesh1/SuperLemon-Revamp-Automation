package commonFunctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.google.api.client.http.HttpRequest;

import io.restassured.RestAssured;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import locators.SettingsLocators;
import locators.CommonLocators;
import locators.AutomationLocators;
import locators.CampaignsLocators;
import locators.CustomersLocators;
import locators.WidgetLocators;
import locators.ShopifyStoreLocators;
import locators.MyWhatsAppLocators;
import utils.DriverBase;
import utils.DriverBase.ElementType;
import utils.Listener;

@SuppressWarnings({ "static-access", "unused" })
public class CustomersPage {

	private static Logger log = Logger.getLogger(CustomersPage.class.getName());
	private static boolean flag;
	private static String propertyFilePath;
	private static String url;
	public static Properties prop = new Properties();
	private static DriverBase webDB = new DriverBase();;
	private static List<WebElement> checkBoxes;
	private static Actions actions;
	private static Set<String> s;
	private static Iterator<String> it;
	private static String parentWindow;
	private static String childWindow;
	private static CommonFunctions HeaderText = new CommonFunctions();
	private static List<WebElement> elements;
	private static String text;
	private static WebDriverWait wait = new WebDriverWait(webDB.driver, 10);
	private static JavascriptExecutor optinList = (JavascriptExecutor) webDB.driver;
	private static String emailId = "qatest@gupshup.io";
	public static String grpname;
	public static XSSFWorkbook wb;
	public static int rowCount;
	public static String[][] data = null;
	public static String output_filename;
	public static String filepath;
	public static int columnCount;
	public static String OrderNO;
	private static String Text;
	private static JavascriptExecutor js = (JavascriptExecutor) webDB.driver;

	public static String firstName = "AutoUser";
	public static String lastName = RandomStringUtils.randomNumeric(5);
	private static String shippingAddress = "Gupshup Technology India Private Limited 11th Main Road 2nd Phase Jayanagar";
	private static String city = "Mumbai";
	private static String pinCode = "400098";

	private static Iterator<String> iterator;
	private static Set<String> windows;
	public static RequestSpecification request;

	/**
	 * verify optin checkboxes selection
	 *
	 * @return
	 *
	 * @return
	 * @throws InterruptedException
	 */

	public static boolean verifySelectUnselectOptinCheckboxes() throws InterruptedException {
		checkBoxes = webDB.driver.findElements(By.xpath(SettingsLocators.ADMIN_PAGE_CHECKBOXES_GENERIC));

		for (int i = 1; i < checkBoxes.size(); i++) {
			if (!checkBoxes.get(i).isSelected()) {
				Thread.sleep(2000);
				WebElement element = checkBoxes.get(i);
				element.click();

			}
		}
		webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
		flag = webDB.isElementDisplayed(CustomersLocators.SAVE_SUCCESS_MSG, ElementType.Xpath);
		System.err.println("Opt in checkboxes ticked");
		return flag;

	}

	public static boolean verifyContactRatingfeature() throws InterruptedException {
		Thread.sleep(2000);
		flag = HeaderText.headertextcheck("Customers");
		return flag;
	}

	public static boolean checkOptinWidgetSourceInOptinList() throws Throwable {
		flag = false;
		parentWindow = webDB.driver.getWindowHandle();
		SettingsPage.enableOptinsAndCheckOnstore();
		String phone = 9 + RandomStringUtils.randomNumeric(9);
		Thread.sleep(5000);
		webDB.waitForElement(ShopifyStoreLocators.OPTIN_POPUP_COUNTRY_CODE_FIELD, ElementType.Id);
		webDB.selectDropDownOptions(ShopifyStoreLocators.OPTIN_POPUP_COUNTRY_CODE_FIELD, WidgetLocators.INDIA_CODE,
				ElementType.Id);
//		webDB.sendTextToAnElement(ShopifyStoreLocators.OPTIN_POPUP_COUNTRY_CODE_FIELD, "+1", ElementType.Id);
		webDB.sendTextToAnElement(ShopifyStoreLocators.OPTIN_POPUP_PHONE_FIELD, phone, ElementType.Xpath);
		System.out.println("phonenumber" + phone);
		webDB.waitForElement(ShopifyStoreLocators.CONFIRM_BUTTON_OPTIN_POPUP, ElementType.Id);
		webDB.clickAnElement(ShopifyStoreLocators.CONFIRM_BUTTON_OPTIN_POPUP, ElementType.Id);
		Thread.sleep(5000);
		webDB.driver.close();
		log.info("closed the child window");
		webDB.driver.switchTo().window(parentWindow);
		log.info("switched to parent window");
		CommonFunctions.verifyOptinNumbersPage();
		webDB.driver.navigate().refresh();
		wait.until(ExpectedConditions
				.visibilityOf(webDB.driver.findElement(By.xpath(CustomersLocators.EXPORT_OPTIN_LIST))));
		elements = webDB.driver.findElements(By.xpath(CustomersLocators.PHONE_COLUMBER_COLUMN));
		List<WebElement> source = webDB.driver.findElements(By.xpath(CustomersLocators.SOURCE_COLUMN));
		for (int i = 0; i < elements.size(); i++) {
			String element = elements.get(i).getText();
			System.out.println(element);
			System.out.println(phone);
			if (element.contains(phone)) {
				text = source.get(i).getText();
				if (text.equalsIgnoreCase("WIDGET")) {
					flag = true;
					log.info("Source for the number opted in is properly updated in Optin List");
					break;
				}

			}
		}
		return flag;
	}

	public static boolean checkOptinCheckoutSourceInOptinList()
			throws FileNotFoundException, InterruptedException, IOException {
		flag = false;
		parentWindow = webDB.driver.getWindowHandle();
		WidgetPage.personalWidgetPageToStoreNavigation();
		ShopifyStore.shopifyStorePassEnter();
//		Dimension d = new Dimension(1940, 1000);
//		webDB.driver.manage().window().setSize(d);
		ShopifyStore.addItemAndCheckout();
		webDB.driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ShopifyStoreLocators.CHECKOUT_EMAIL_PHONE)));
		String phone = ShopifyStore.enterShippingDetailsWithPhone();
		System.out.println(phone);
		Thread.sleep(1000);
		ShopifyStore.enterCardDetails();
		webDB.clickAnElement(ShopifyStoreLocators.CREDITCARDTEXT, ElementType.Xpath);
		Thread.sleep(2000);
		webDB.scrollToAnElement(ShopifyStoreLocators.PAYNOW, ElementType.Xpath);
		Thread.sleep(2000);
		flag = webDB.waitForElement(ShopifyStoreLocators.PAYNOW, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ShopifyStoreLocators.PAYNOW, ElementType.Xpath);
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(ShopifyStoreLocators.ORDER_CONFIRMED_MESSAGE)));
			log.info("Order confirmation message is displayed");
			Thread.sleep(2000);
			webDB.driver.navigate().refresh();
//		wait.until(
//				ExpectedConditions.visibilityOfElementLocated(By.id(ShopifyStoreLocators.OPTIN_CONFIRM_SUCCESS_MSG)));
//		webDB.isElementDisplayed(ShopifyStoreLocators.OPTIN_CONFIRM_SUCCESS_MSG, ElementType.Id);
			webDB.driver.close();
			log.info("closed the child window");
			webDB.driver.switchTo().window(parentWindow);
			log.info("switched to parent window");
			CommonFunctions.verifyOptinNumbersPage();
			wait.until(ExpectedConditions
					.visibilityOf(webDB.driver.findElement(By.xpath(CustomersLocators.EXPORT_OPTIN_LIST))));
			elements = webDB.driver.findElements(By.xpath(CustomersLocators.PHONE_COLUMBER_COLUMN));
			List<WebElement> source = webDB.driver.findElements(By.xpath(CustomersLocators.SOURCE_COLUMN));
			for (int i = 0; i < elements.size(); i++) {
				String element = elements.get(i).getText();
//			log.info(element);
				if (element.contains(phone)) {
					text = source.get(i).getText();
					log.info(text);
					if (text.equalsIgnoreCase("CHECKOUT")) {
						flag = true;
						log.info("Source for the number opted in is properly updated in Optin List");
						break;
					}

				}
			}
		}
		return flag;
	}

	public static boolean verifyExportEmailEmptyValidation() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CustomersLocators.EXPORT_OPTIN_LIST)));
		webDB.clickAnElement(CustomersLocators.EXPORT_OPTIN_LIST, ElementType.Xpath);
		log.info("Export Optin Popup Accessed");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CustomersLocators.SUBMIT_EXPORT_OPTIN_LIST)));
		webDB.clickAnElement(CustomersLocators.SUBMIT_EXPORT_OPTIN_LIST, ElementType.Xpath);
		log.info("Clicked on Submit Button");
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(CustomersLocators.EMAIL_EMPTY_VALIDATION_MESSAGE_POPUP)));
		flag = webDB.isElementDisplayed(CustomersLocators.EMAIL_EMPTY_VALIDATION_MESSAGE_POPUP, ElementType.Xpath);
		if (flag) {
			log.info("Empty email validation displayed");
		} else {
			flag = false;
			log.info("Empty Email Validation not displayed");
		}
		return flag;
	}

	public static boolean verifyExportEmailInvalidValidation() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CustomersLocators.EXPORT_OPTIN_LIST)));
		webDB.clickAnElement(CustomersLocators.EXPORT_OPTIN_LIST, ElementType.Xpath);
		wait.until(ExpectedConditions.visibilityOf(webDB.driver.findElement(By.id(CustomersLocators.EMAIL_TEXTBOX))));
		webDB.sendTextToAnElement(CustomersLocators.EMAIL_TEXTBOX, "abcd@", ElementType.Id);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CustomersLocators.SUBMIT_EXPORT_OPTIN_LIST)));
		webDB.clickAnElement(CustomersLocators.SUBMIT_EXPORT_OPTIN_LIST, ElementType.Xpath);
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(CustomersLocators.EMAIL_INVALID_VALIDATION_MESSAGE_POPUP)));
		flag = webDB.isElementDisplayed(CustomersLocators.EMAIL_INVALID_VALIDATION_MESSAGE_POPUP, ElementType.Xpath);
		if (flag) {
			log.info("invalid email validation displayed");
		} else {
			flag = false;
		}
		return flag;
	}

//	public static boolean verifyInvalidOrEmptyDateExport() {
//		webDB.clickAnElement(CustomersLocators.EXPORT_OPTIN_LIST, ElementType.Xpath);
//		webDB.driver.findElement(By.xpath(CustomersLocators.DATE_STARTING))
//				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
//		webDB.driver.findElement(By.xpath(CustomersLocators.SUBMIT_EXPORT_OPTIN_LIST)).click();
//			webDB.driver.findElement(By.xpath(CustomersLocators.DATE_ENDING))
//					.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
////			wait.until(ExpectedConditions.elementToBeClickable(null))
//			flag=webDB.isElementDisplayed(CustomersLocators.EXPORT_LIST_SUCCESS_MESSAGE, ElementType.Xpath);
//			log.info("Validation for empty or invalid dates shown");
//		return flag;
//	}

	public static boolean verifyDateRangevalidation() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CustomersLocators.EXPORT_OPTIN_LIST)));
		webDB.clickAnElement(CustomersLocators.EXPORT_OPTIN_LIST, ElementType.Xpath);
		wait.until(ExpectedConditions.visibilityOf(webDB.driver.findElement(By.id(CustomersLocators.EMAIL_TEXTBOX))));
		webDB.sendTextToAnElement(CustomersLocators.EMAIL_TEXTBOX, emailId, ElementType.Id);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, -4);
		Date updatedDate = c.getTime();
		String currentdate = dateFormat.format(updatedDate);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(CustomersLocators.DATE_STARTING)));
		webDB.driver.findElement(By.id(CustomersLocators.DATE_STARTING))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(CustomersLocators.DATE_STARTING, currentdate, ElementType.Id);
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector(CustomersLocators.DATE_RANGE_VALIDATION_MESSAGE)));
		flag = webDB.isElementDisplayed(CustomersLocators.DATE_RANGE_VALIDATION_MESSAGE, ElementType.CssSelector);
		return flag;

	}

	public static boolean verifyExportSuccessful() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CustomersLocators.EXPORT_OPTIN_LIST)));
		Thread.sleep(5000);
//		wait.until(
//				ExpectedConditions.visibilityOf(webDB.driver.findElement(By.id(CustomersLocators.EMAIL_TEXTBOX))));
//		webDB.sendTextToAnElement(CustomersLocators.EMAIL_TEXTBOX, emailId, ElementType.Id);
//		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//		Thread.sleep(3000);
//		System.out.println(currentDate);
//		text = webDB.driver.findElement(By.id(CustomersLocators.DATE_ENDING)).getText();
//		webDB.driver.findElement(By.id(CustomersLocators.DATE_STARTING))
//				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		// Thread.sleep(3000);
//		webDB.sendTextToAnElement(CustomersLocators.DATE_STARTING, currentDate, ElementType.Id);
//		webDB.clickAnElement(CustomersLocators.SUBMIT_EXPORT_OPTIN_LIST, ElementType.Xpath);
//		String successText = "Your requested export will be sent as a CSV to" + " " + emailId;
//		wait.until(ExpectedConditions
//				.visibilityOf(webDB.driver.findElement(By.xpath(CustomersLocators.EXPORT_LIST_SUCCESS_MESSAGE))));
		flag = webDB.isElementDisplayed(CustomersLocators.EXPORT_OPTIN_LIST, ElementType.Xpath);
//		if (flag) {
//			flag = webDB.driver
//					.findElement(
//							By.xpath("//div[@class='Polaris-Frame-Toast'][contains(text(),'" + successText + "')]"))
//					.isDisplayed();
//			log.info("opt in list export successfully mailed");
//		}
		return flag;

	}

	public static boolean verifyAddNumberInCheckoutAndConfirmOptInFromThankYouPage() throws Exception {
		parentWindow = webDB.driver.getWindowHandle();
		CommonFunctions.verifyTemplatesPage();
		Thread.sleep(3000);
		AutomationPage.enableOrderConfirmationTemplateMessaging();
		CommonFunctions.verifyPersonalWidgetPage();
		WidgetPage.personalWidgetPageToStoreNavigation();
		ShopifyStore.shopifyStorePassEnter();
		ShopifyStore.addItemAndCheckout();
		Thread.sleep(3500);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ShopifyStoreLocators.CHECKOUT_EMAIL_PHONE)));
		webDB.driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ShopifyStoreLocators.CHECKOUT_EMAIL_PHONE)));
		String phone = ShopifyStore.enterShippingDetailsWithPhoneWithoutOptinCheckBoxTicked();
		log.info(phone);
		Thread.sleep(1000);
		ShopifyStore.enterCardDetails();
		webDB.clickAnElement(ShopifyStoreLocators.CONTINUE_BTN, ElementType.Xpath);
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath(ShopifyStoreLocators.ORDER_CONFIRMED_MESSAGE)));
		Thread.sleep(3000);
		webDB.waitForElement(ShopifyStoreLocators.OPTIN_NUMBER_COUNTRYCODE, ElementType.Id);
		webDB.selectDropDownOptions(ShopifyStoreLocators.OPTIN_NUMBER_COUNTRYCODE, "91", ElementType.Id);
		webDB.sendTextToAnElement(ShopifyStoreLocators.OPTIN_NUMBER_INPUT, phone, ElementType.Id);
		Thread.sleep(1000);
		flag = webDB.waitForElement(ShopifyStoreLocators.CONFIRMBUTTON, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(ShopifyStoreLocators.CONFIRMBUTTON, ElementType.Xpath);
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(ShopifyStoreLocators.OPTIN_CONFIRM_SUCCESS_MSG)));
			webDB.isElementDisplayed(ShopifyStoreLocators.OPTIN_CONFIRM_SUCCESS_MSG, ElementType.Xpath);
//		wait.until(
//				ExpectedConditions.visibilityOfElementLocated(By.id(ShopifyStoreLocators.OPTIN_BUTTON_THANKYOU_PAGE)));
//		wait.until(ExpectedConditions.elementToBeClickable(By.id(ShopifyStoreLocators.OPTIN_BUTTON_THANKYOU_PAGE)));
//		webDB.clickAnElement(ShopifyStoreLocators.OPTIN_BUTTON_THANKYOU_PAGE, ElementType.Id);
//		wait.until(ExpectedConditions
//				.visibilityOfElementLocated(By.xpath(ShopifyStoreLocators.OPTIN_CONFIRM_SUCCESS_MSG)));
			webDB.driver.close();
			log.info("closed the child window");
			webDB.driver.switchTo().window(parentWindow);
			log.info("switched to parent window");
			CommonFunctions.verifyOptinNumbersPage();
			webDB.driver.navigate().refresh();
			wait.until(ExpectedConditions
					.visibilityOf(webDB.driver.findElement(By.xpath(CustomersLocators.EXPORT_OPTIN_LIST))));
			elements = webDB.driver.findElements(By.xpath(CustomersLocators.PHONE_COLUMBER_COLUMN));
			List<WebElement> source = webDB.driver.findElements(By.xpath(CustomersLocators.SOURCE_COLUMN));
			for (int i = 0; i < elements.size(); i++) {
				String element = elements.get(i).getText();
				log.info(element);
				if (element.contains(phone)) {
					text = source.get(i).getText();
					log.info(text);
					if (text.equalsIgnoreCase("WIDGET")) {
						flag = true;
						log.info("Source for the number opted in is properly updated in Optin List");
						break;
					}

				}
			}
		}
		return flag;
	}

	public static boolean verifyRedirectWhatswebURL() throws Exception {
		parentWindow = webDB.driver.getWindowHandle();
		wait.until(ExpectedConditions
				.visibilityOf(webDB.driver.findElement(By.xpath(CustomersLocators.EXPORT_OPTIN_LIST))));
		webDB.moveToElement(CustomersLocators.FIRSTSENTMESSAGEBTN, ElementType.Xpath);
		flag = webDB.waitForElement(CustomersLocators.FIRSTSENTMESSAGEBTN, ElementType.Xpath);
		if (flag) {
			propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
			prop.load(new FileInputStream(propertyFilePath));
			Thread.sleep(5000);
			String Whatsappweburl = prop.getProperty("Whatsappweburl");
			webDB.clickAnElement(CustomersLocators.FIRSTSENTMESSAGEBTN, ElementType.Xpath);
			Thread.sleep(1500);
			for (String winHandle : webDB.driver.getWindowHandles()) {
				if (webDB.driver.switchTo().window(winHandle).getCurrentUrl().equals(Whatsappweburl)) {
					webDB.driver.close();
				}
			}
			webDB.driver.switchTo().window(parentWindow);
			flag = webDB.waitForElement(CustomersLocators.EXPORT_OPTIN_LIST, ElementType.Xpath);
		}
		return flag;
	}

	public static boolean verifyCustomersRequiredOptions() throws Exception {
		flag = webDB.VerifyListOfElement(CustomersLocators.CUSTOMER_TABLE_HEADER);
		return flag;

	}

	public static boolean checkOptinDetailsInOptinList() throws Exception {
		SimpleDateFormat simpleformat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");
		simpleformat = new SimpleDateFormat("MMMM d");
		String datevalue = simpleformat.format(new Date());
		flag = false;
		parentWindow = webDB.driver.getWindowHandle();
		WidgetPage.personalWidgetPageToStoreNavigation();
		ShopifyStore.shopifyStorePassEnter();
		ShopifyStore.addItemAndCheckout();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ShopifyStoreLocators.CHECKOUT_EMAIL_PHONE)));
		webDB.driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ShopifyStoreLocators.CHECKOUT_EMAIL_PHONE)));
		String phone = ShopifyStore.enterShippingDetailsWithPhone();
		System.out.println(phone);
		Thread.sleep(1000);
		ShopifyStore.enterCardDetails();
		webDB.scrollToAnElement(ShopifyStoreLocators.PAYNOW, ElementType.Xpath);
		webDB.clickAnElement(ShopifyStoreLocators.PAYNOW, ElementType.Xpath);
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath(ShopifyStoreLocators.ORDER_CONFIRMED_MESSAGE)));
		log.info("Order confirmation message is displayed");
		Thread.sleep(3000);
//		webDB.driver.navigate().refresh();
//		webDB.waitForElement(ShopifyStoreLocators.OPTIN_NUMBER_INPUT, ElementType.Id);
//		if (flag) {
		webDB.selectDropDownOptions(ShopifyStoreLocators.OPTIN_NUMBER_COUNTRYCODE, "91", ElementType.Id);
		webDB.sendTextToAnElement(ShopifyStoreLocators.OPTIN_NUMBER_INPUT, phone, ElementType.Id);
		Thread.sleep(1000);
//		webDB.waitForElement(ShopifyStoreLocators.CONFIRMBUTTON, ElementType.Xpath);
//		if (flag) {
		webDB.clickAnElement(ShopifyStoreLocators.CONFIRMBUTTON, ElementType.Xpath);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(ShopifyStoreLocators.OPTIN_CONFIRM_SUCCESS_MSG)));
		webDB.isElementDisplayed(ShopifyStoreLocators.OPTIN_CONFIRM_SUCCESS_MSG, ElementType.Xpath);
		webDB.driver.close();
		log.info("closed the child window");
		webDB.driver.switchTo().window(parentWindow);
		log.info("switched to parent window");
		CommonFunctions.verifyOptinNumbersPage();
		webDB.driver.navigate().refresh();
		Thread.sleep(1000);
		wait.until(ExpectedConditions
				.visibilityOf(webDB.driver.findElement(By.xpath(CustomersLocators.EXPORT_OPTIN_LIST))));
		String fullname = ShopifyStore.firstName + " " + ShopifyStore.lastName;
		System.out.println(fullname);
		List<WebElement> optinstatus = webDB.driver.findElements(By.xpath(CustomersLocators.OPTEDIN_COLUMN));
		elements = webDB.driver.findElements(By.xpath(CustomersLocators.PHONE_COLUMBER_COLUMN));
		List<WebElement> source = webDB.driver.findElements(By.xpath(CustomersLocators.NAME_COLUMN));
		List<WebElement> totalordervalue = webDB.driver
				.findElements(By.xpath(CustomersLocators.TOTAL_ORDER_VALUE_COLUMN));
		List<WebElement> dateordervalue = webDB.driver.findElements(By.xpath(CustomersLocators.ORDER_DATE_COLUMN));
		List<WebElement> numberoforder = webDB.driver.findElements(By.xpath(CustomersLocators.NO_OF_ORDER_COLUMN));
		for (int i = 0; i < source.size(); i++) {
			String element = source.get(i).getText();
			String phonevalue = elements.get(i).getText();
			flag = phonevalue.contains(phone);
			if (flag) {
				System.out.println("Phone verified");
				break;
			}
		}
		if (!flag) {
			Listener.reportLog("Phone No is not verified");
		}
		return flag;
	}

	public static boolean VerifyOptinSourcesAsSpinWheel() throws Exception {
		parentWindow = webDB.driver.getWindowHandle();
		String phone = "7854" + RandomStringUtils.randomNumeric(6);
		if ((System.getProperty("platformName").equals("browserstack"))
				&& (System.getProperty("os").equals("Ios") || System.getProperty("os").equals("Android"))) {
			webDB.waitForElement(CommonLocators.THREEDOTSMENU, ElementType.Xpath);
			webDB.driver.findElement(By.xpath(CommonLocators.THREEDOTSMENU)).click();
		}
		flag = webDB.waitForElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
			Thread.sleep(1000);
			webDB.moveToElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
			Thread.sleep(1000);
			webDB.clickAnElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
			Thread.sleep(1000);
			flag = webDB.isElementDisplayed(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
			text = webDB.driver.findElement(By.xpath(CommonLocators.ENABLE_DISABLE_BTN)).getText();
			log.info(text);
			if (!text.equals("Disable")) {
				Thread.sleep(1000);
				webDB.javaScriptClickAnElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
			}
			Thread.sleep(1000);
			webDB.moveToElement(WidgetLocators.MESSAGE_TITLE, ElementType.Xpath);
			Thread.sleep(2000);
			flag = webDB.waitForElement(WidgetLocators.FIRST_DISPLAY_CODE, ElementType.Xpath);
			if (flag) {
				webDB.clearText(WidgetLocators.FIRST_DISPLAY_CODE);
				Thread.sleep(1000);
				webDB.sendTextToAnElement(WidgetLocators.FIRST_DISPLAY_CODE, WidgetLocators.FIRST_DISPLAY_VALUE,
						ElementType.Xpath);
			}
			flag = webDB.waitForElement(WidgetLocators.SECOND_DISPLAY_CODE, ElementType.Xpath);
			if (flag) {
				webDB.clearText(WidgetLocators.SECOND_DISPLAY_CODE);
				Thread.sleep(1000);
				webDB.sendTextToAnElement(WidgetLocators.SECOND_DISPLAY_CODE, WidgetLocators.SECOND_DISPLAY_VALUE,
						ElementType.Xpath);
			}
			flag = webDB.waitForElement(WidgetLocators.THIRD_DISPLAY_CODE, ElementType.Xpath);
			if (flag) {
				webDB.clearText(WidgetLocators.THIRD_DISPLAY_CODE);
				Thread.sleep(1000);
				webDB.sendTextToAnElement(WidgetLocators.THIRD_DISPLAY_CODE, WidgetLocators.THIRD_DISPLAY_VALUE,
						ElementType.Xpath);
			}
			flag = webDB.waitForElement(WidgetLocators.FOURTH_DISPLAY_CODE, ElementType.Xpath);
			if (flag) {
				webDB.clearText(WidgetLocators.FOURTH_DISPLAY_CODE);
				Thread.sleep(1000);
				webDB.sendTextToAnElement(WidgetLocators.FOURTH_DISPLAY_CODE, WidgetLocators.FOURTH_DISPLAY_VALUE,
						ElementType.Xpath);
			}
			flag = webDB.waitForElement(WidgetLocators.SAVE_BTN, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.SAVE_BTN, ElementType.Xpath);
			}
			webDB.scrollToAnElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
			WidgetPage.EnableDisableStoreBtnStatus();
			ShopifyStore.shopifyStorePassEnter();
			optinList.executeScript("window.localStorage.clear();");
			webDB.driver.navigate().refresh();
			flag = webDB.waitForElement(WidgetLocators.SPIN_WHEEL_LOGO, ElementType.CssSelector);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.SPIN_WHEEL_LOGO, ElementType.CssSelector);
				Thread.sleep(2000);
				flag = webDB.waitForElement(WidgetLocators.SPIN_WHEEL_BUTTON, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(WidgetLocators.SPIN_WHEEL_BUTTON, ElementType.Xpath);
					Thread.sleep(7000);
					flag = webDB.waitForElement(WidgetLocators.COUNTRY_CODE, ElementType.CssSelector);
					if (flag) {
						webDB.selectDropDownOptions(WidgetLocators.COUNTRY_CODE, WidgetLocators.INDIA_CODE,
								ElementType.CssSelector);
						flag = webDB.waitForElement(WidgetLocators.SPIN_WHEEL_PHONE_NUMBER, ElementType.CssSelector);
						if (flag) {
							webDB.sendTextToAnElement(WidgetLocators.SPIN_WHEEL_PHONE_NUMBER, phone,
									ElementType.CssSelector);
							webDB.clickAnElement(WidgetLocators.SPINWHEELTEXT, ElementType.CssSelector);
							flag = webDB.waitForElement(WidgetLocators.SPIN_WHEEL_CONFIRM_BUTTON,
									ElementType.CssSelector);
							if (flag) {
								webDB.clickAnElement(WidgetLocators.SPIN_WHEEL_CONFIRM_BUTTON, ElementType.CssSelector);
								flag = webDB.waitForElement(WidgetLocators.COPY_COUPON_CODE, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(WidgetLocators.COPY_COUPON_CODE, ElementType.Xpath);
									Thread.sleep(2000);
									webDB.switchToAlertAccept();
									flag = webDB.verifyElementIsNotDisplayed(WidgetLocators.SPIN_WHEEL_BUTTON,
											ElementType.Xpath);
									webDB.driver.close();
									log.info("closed the child window");
									webDB.driver.switchTo().window(parentWindow);
									log.info("switched to parent window");
									CommonFunctions.verifyOptinNumbersPage();
									wait.until(ExpectedConditions.visibilityOf(
											webDB.driver.findElement(By.xpath(CustomersLocators.EXPORT_OPTIN_LIST))));
									elements = webDB.driver
											.findElements(By.xpath(CustomersLocators.PHONE_COLUMBER_COLUMN));
									List<WebElement> source = webDB.driver
											.findElements(By.xpath(CustomersLocators.SOURCE_COLUMN));
									for (int i = 0; i < elements.size(); i++) {
										String element = elements.get(i).getText();
//										log.info(element);
										if (element.contains(phone)) {
											text = source.get(i).getText();
											log.info(text);
											if (text.equalsIgnoreCase("SPIN_WHEEL")) {
												flag = true;
												log.info(
														"Source for the number opted in is properly updated in Optin List");
												break;
											}

										}
									}
								}
							}
						}
					}
				}
			}
		}

		return flag;

	}

	public static boolean VerifyCreateCustomerGrp() throws Exception {
		String random = RandomStringUtils.randomNumeric(5);
		flag = webDB.waitForElement(CustomersLocators.USERGRPTAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomersLocators.USERGRPTAB, ElementType.Xpath);
			flag = webDB.waitForElement(CustomersLocators.CREATEUSERGRPBTN, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CustomersLocators.CREATEUSERGRPBTN, ElementType.Xpath);
				flag = webDB.waitForElement(CustomersLocators.GRPNAMEINPUT, ElementType.Xpath);
				if (flag) {
					grpname = CustomersLocators.GRPNAMEVALUE + random;
					Thread.sleep(2000);
					webDB.sendTextToAnElement(CustomersLocators.GRPNAMEINPUT, grpname, ElementType.Xpath);
					flag = webDB.waitForElement(CustomersLocators.PARAMETERDROPDOWN, ElementType.Xpath);
					if (flag) {
						Thread.sleep(2000);
						webDB.clickAnElement(CustomersLocators.PARAMETERDROPDOWN, ElementType.Xpath);
						webDB.selectDropDownOptions(CustomersLocators.PARAMETERDROPDOWN,
								CustomersLocators.PARAMETERVALUE, ElementType.Xpath);
						Thread.sleep(2000);
						flag = webDB.waitForElement(CustomersLocators.CONDITIONDROPDOWN, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(CustomersLocators.CONDITIONDROPDOWN, ElementType.Xpath);
							webDB.selectDropDownOptions(CustomersLocators.CONDITIONDROPDOWN,
									CustomersLocators.CONDITIONVALUE, ElementType.Xpath);
							Thread.sleep(2000);
							webDB.sendTextToAnElement(CustomersLocators.VALUEINPUT, CustomersLocators.VALUE,
									ElementType.Xpath);
							flag = webDB.waitForElement(CustomersLocators.ADDCONDITIONBTN, ElementType.Xpath);
							if (flag) {
								Thread.sleep(2000);
								webDB.clickAnElement(CustomersLocators.SAVEBTN, ElementType.Xpath);
								Thread.sleep(2000);
								flag = webDB.waitForElement(CustomersLocators.GRPNODE, ElementType.Xpath);
								// modified - added flag
								if (flag) {
									webDB.moveToElement(CustomersLocators.GRPNODE, ElementType.Xpath);
									flag = webDB.waitForElement(CustomersLocators.GRPEDITBUTTON, ElementType.Xpath);
									if (flag) {
										System.out.println(CustomersLocators.GRPEDITBUTTON);
										System.out.println(flag);
									}

								}

							}
						}
					}
				}
			}
		}
		return flag;

	}

	public static boolean verifyValidationGrpTab() throws Exception {
		String random = RandomStringUtils.randomNumeric(5);
		flag = webDB.waitForElement(CustomersLocators.USERGRPTAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomersLocators.USERGRPTAB, ElementType.Xpath);
			flag = webDB.waitForElement(CustomersLocators.CREATEUSERGRPBTN, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CustomersLocators.CREATEUSERGRPBTN, ElementType.Xpath);
				
				flag = webDB.waitForElement(CustomersLocators.GRPNAMEINPUT, ElementType.Xpath);
				if (flag) {
					grpname = CustomersLocators.GRPNAMEVALUE + random;
					System.out.println(grpname);
					Thread.sleep(2000);
					webDB.sendTextToAnElement(CustomersLocators.GRPNAMEINPUT, grpname, ElementType.Xpath);
					flag = webDB.waitForElement(CustomersLocators.PARAMETERDROPDOWN, ElementType.Xpath);
					if (flag) {
						// modified - changed condition dropdown to parameter
						webDB.clickAnElement(CustomersLocators.PARAMETERDROPDOWN, ElementType.Xpath);
						webDB.selectDropDownOptions(CustomersLocators.PARAMETERDROPDOWN,
								CustomersLocators.PARAMETERVALUE, ElementType.Xpath);
						Thread.sleep(2000);
						webDB.clickAnElement(CustomersLocators.SAVEBTN, ElementType.Xpath);

						flag = webDB.waitForElement(CustomersLocators.VALIDATION_MESSAGE_POPUP, ElementType.Xpath);
						if (flag) {
							String text = webDB.getText(CustomersLocators.VALIDATION_MESSAGE_POPUP, ElementType.Xpath);
							System.out.println(text);
							Thread.sleep(5000);
							System.out.println(CustomersLocators.ERRORVALUE1);
							flag = text.equals(CustomersLocators.ERRORVALUE1);
							if (flag) {
								flag = webDB.waitForElement(CustomersLocators.CONDITIONDROPDOWN, ElementType.Xpath);//
								if (flag) {
									// modified - changed valueinput to conditiondropdown
									Thread.sleep(2000);
									webDB.clickAnElement(CustomersLocators.CONDITIONDROPDOWN, ElementType.Xpath);
									webDB.selectDropDownOptions(CustomersLocators.CONDITIONDROPDOWN,
											CustomersLocators.CONDITIONVALUE, ElementType.Xpath);
									flag = webDB.waitForElement(CustomersLocators.SAVEBTN, ElementType.Xpath);
									// modified - moved click inside flag
									if (flag) {
										webDB.clickAnElement(CustomersLocators.SAVEBTN, ElementType.Xpath);
										Thread.sleep(2000);
										String text1 = webDB.getText(CustomersLocators.VALIDATION_MESSAGE_POPUP,
												ElementType.Xpath);
										System.out.println("The text is " + text1);
										log.info(text1);
										Thread.sleep(5000);
										System.out.println("Error value 3 is " + CustomersLocators.ERRORVALUE3);
										flag = text1.equals(CustomersLocators.ERRORVALUE3);
										if (flag) {
											webDB.waitForElement(CustomersLocators.CLOSEGRP, ElementType.Xpath);
											webDB.clickAnElement(CustomersLocators.CLOSEGRP, ElementType.Xpath);
										}
									}
								}

							}
						}
					}
				}
			}

		}
		return flag;

	}

	public static boolean editUserGroup() throws Exception {

		flag = webDB.waitForElement(CustomersLocators.USERGRPTAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomersLocators.USERGRPTAB, ElementType.Xpath);
			System.out.println("Clicked on user groups tab");
			// modified - added create method
			VerifyCreateCustomerGrp();
			System.out.println("Clicked on edit button" + CustomersLocators.GRPEDITBUTTON);
			flag = webDB.waitForElement(CustomersLocators.GRPEDITBUTTON, ElementType.Xpath);
			if (flag) {
				Thread.sleep(1000);
				webDB.clickAnElement(CustomersLocators.GRPEDITBUTTON, ElementType.Xpath);
				System.out.println("Clicked on edit button");
				flag = webDB.waitForElement(CustomersLocators.GRPNAMEINPUT, ElementType.Xpath);
				if (flag) {
					Thread.sleep(1000);
					webDB.clearText(CustomersLocators.GRPNAMEINPUT);
					Thread.sleep(1000);
					grpname = CustomersLocators.GROUPNAME;
					webDB.sendTextToAnElement(CustomersLocators.GRPNAMEINPUT, grpname, ElementType.Xpath);
					webDB.clickAnElement(CustomersLocators.PARAMETERDROPDOWN, ElementType.Xpath);
					Thread.sleep(1000);
					webDB.selectDropDownOptions(CustomersLocators.PARAMETERDROPDOWN, CustomersLocators.EDITPARAMETER,
							ElementType.Xpath);
					Thread.sleep(2000);
					flag = webDB.waitForElement(CustomersLocators.CONDITIONDROPDOWN, ElementType.Xpath);
					if (flag) {
						Thread.sleep(1000);
						webDB.clickAnElement(CustomersLocators.CONDITIONDROPDOWN, ElementType.Xpath);
						Thread.sleep(1000);
						webDB.selectDropDownOptions(CustomersLocators.CONDITIONDROPDOWN,
								CustomersLocators.EDITCONDITION, ElementType.Xpath);
						Thread.sleep(2000);
						flag = webDB.waitForElement(CustomersLocators.VALUEFIELD, ElementType.Xpath);

						if (flag) {
							Thread.sleep(1000);
							webDB.clickAnElement(CustomersLocators.VALUEFIELD, ElementType.Xpath);
							webDB.sendTextToAnElement(CustomersLocators.VALUEFIELD, CustomersLocators.EDITVALUE,
									ElementType.Xpath);
							webDB.clickAnElement(CustomersLocators.SAVEBTN, ElementType.Xpath);
							Thread.sleep(1000);
							flag = webDB.waitForElement(CustomersLocators.NOTIF, ElementType.Xpath);
							if (flag) {
								String text = webDB.getText(CustomersLocators.NOTIF, ElementType.Xpath);
								System.out.println(text);
								flag = text.equals(CustomersLocators.EDITSUCCESSNOTIF);
							}

						}

					}
				}

			}
		}
		return flag;
	}

	public static boolean verifyDeleteUserGroup() throws Exception {
		flag = webDB.waitForElement(CustomersLocators.USERGRPTAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomersLocators.USERGRPTAB, ElementType.Xpath);
			System.out.println("Clicked on user groups tab");
			// modified - added create
			VerifyCreateCustomerGrp();
			flag = webDB.waitForElement("//div[contains(text(),'" + CustomersPage.grpname + "')]/child::div[1]/div[3]",
					ElementType.Xpath);
			webDB.moveToElement("//div[contains(text(),'" + CustomersPage.grpname + "')]/child::div[1]/div[3]",
					ElementType.Xpath);

			if (flag) {
				Thread.sleep(1000);
				webDB.clickAnElement("//div[contains(text(),'" + CustomersPage.grpname + "')]/child::div[1]/div[3]",
						ElementType.Xpath);
				System.out.println("Clicked on the delete button");
				Thread.sleep(1000);
				flag = webDB.waitForElement(CustomersLocators.CONFIRMDELETE, ElementType.Xpath);
				if (flag) {
					Thread.sleep(1000);
					webDB.clickAnElement(CustomersLocators.CONFIRMDELETE, ElementType.Xpath);
					System.out.println("Clicked on the Confirm delete button");
					Thread.sleep(1000);

					text = webDB.getText(CustomersLocators.NOTIF, ElementType.Xpath);
					System.out.println(text);
					flag = text.equalsIgnoreCase(CustomersLocators.DELETESUCCESSNOTIF);

				}
			}
		}

		return flag;

	}

	public static boolean verifyCustomerPredefinedVariable() throws Exception {
		deleteexsistfile();
		flag = webDB.waitForElement(CustomersLocators.USER_GROUP, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomersLocators.USER_GROUP, ElementType.Xpath);
			flag = webDB.waitForElement(CustomersLocators.GRPDOWNLOADBUTTON, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CustomersLocators.GRPDOWNLOADBUTTON, ElementType.Xpath);
				Thread.sleep(10000);
				getAllSheetData();
				flag = true;
			}
		}

		return flag;
	}

	public static void deleteexsistfile() {
		try {
			String path = System.getProperty("user.dir") + File.separator + "Resources";
			File file = new File(path);
			File[] files = file.listFiles();
			for (File f : files) {
				if (f.isFile() && f.exists()) {
					f.delete();
					System.out.println("successfully deleted");
				} else {
					System.out.println("cant delete a file due to open or error");
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static String getfilename() {
		String path = System.getProperty("user.dir") + File.separator + "Resources";
		File file = new File(path);
		File[] files = file.listFiles();
		for (File f : files) {
			if (f.isFile() && f.exists()) {
				filepath = f.getAbsolutePath();
			} else {
				System.out.println("cant delete a file due to open or error");
			}
		}
		System.out.println(filepath);
		return filepath;
	}

	public static void getAllSheetData() {
		try {
			String[] COLUMNVALUES = { "Phone Number", "Customer Name", "Number of Orders", "Total Order Value",
					"Abandoned Cart Value", "Optin", "Optin Date", "Optin Source" };
			String input_sheetname = getfilename();
			System.out.println(input_sheetname);
//			prop.load(new FileInputStream(propertyFilePath));
//			String input_filename = prop.getProperty("input_filename");
//			output_filename = prop.getProperty("output_filename");
			File file = new File(input_sheetname);
			FileInputStream inputStream = new FileInputStream(file);
			wb = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = wb.getSheet("customers");
			rowCount = sheet.getLastRowNum();
			columnCount = sheet.getRow(0).getLastCellNum();

			data = new String[rowCount][columnCount];
			for (int i = 0; i < 1; i++) {
				try {
					XSSFRow row = sheet.getRow(i);
					for (int j = 0; j < columnCount; j++) { // loop through the columns
						try {
							String cellValue = "";
							try {
								cellValue = row.getCell(j).getStringCellValue();
							} catch (NullPointerException e) {

							}
							System.out.println(COLUMNVALUES[j]);
							Assert.assertEquals(COLUMNVALUES[j], cellValue);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String writeDataInSheet() {
		String input_sheetname = getfilename();
		try {
			String[] COLUMNVALUES = { "Purchase" };
			File file = new File(input_sheetname);
			FileInputStream inputStream = new FileInputStream(file);
			wb = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = wb.getSheet("customers");
			rowCount = sheet.getLastRowNum();
			columnCount = sheet.getRow(0).getLastCellNum();

			data = new String[rowCount][columnCount];
			for (int i = 1; i < rowCount + 1; i++) {
				try {
					XSSFRow row = sheet.getRow(i);
					int j = columnCount - 1; // loop through the columns
					try {
						String cellValue = "";
						try {

							XSSFCell cell = row.createCell(j);
							cell.setCellValue(COLUMNVALUES[i - 1]);
						} catch (NullPointerException e) {

						}
						System.out.println(COLUMNVALUES[i - 1]);

					} catch (Exception e) {
						e.printStackTrace();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			FileOutputStream out = new FileOutputStream(new File(input_sheetname));

			wb.write(out);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return input_sheetname;
	}

	public static boolean verifyOrderWithPhoneno() throws Exception {
		String phone = "8693858821";
		CommonFunctions.verifyTemplatesPage();
		AutomationPage.verifyEnableOrderConfirmationAutomatedMessaging();
		AutomationPage.verifyEnablePaymentSucessfullTemplate();
		SimpleDateFormat simpleformat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");
		simpleformat = new SimpleDateFormat("MMMM d");
		String datevalue = simpleformat.format(new Date());
		parentWindow = webDB.driver.getWindowHandle();
		CommonFunctions.verifyPersonalWidgetPage();
		WidgetPage.personalWidgetPageToStoreNavigation();
		ShopifyStore.shopifyStorePassEnter();
		ShopifyStore.addItemAndCheckout();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ShopifyStoreLocators.CHECKOUT_EMAIL_PHONE)));
		webDB.driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ShopifyStoreLocators.CHECKOUT_EMAIL_PHONE)));
		String phone2 = ShopifyStore.enterShippingDetailsWithExistingno(phone);
		System.out.println(phone2);
		Thread.sleep(1000);
		ShopifyStore.enterCardDetails();
		webDB.moveToElement(ShopifyStoreLocators.PAYNOW, ElementType.Xpath);
		webDB.clickAnElement(ShopifyStoreLocators.PAYNOW, ElementType.Xpath);
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath(ShopifyStoreLocators.ORDER_CONFIRMED_MESSAGE)));
		log.info("Order confirmation message is displayed");
		webDB.driver.navigate().refresh();
		Thread.sleep(4000);
		OrderNO = webDB.getText(ShopifyStoreLocators.ORDERNO, ElementType.CssSelector).replace("Order ", "");
		System.out.println(OrderNO);
		Thread.sleep(2000);
		webDB.driver.navigate().refresh();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(ShopifyStoreLocators.OPTIN_CONFIRM_SUCCESS_MSG)));
		flag = webDB.isElementDisplayed(ShopifyStoreLocators.OPTIN_CONFIRM_SUCCESS_MSG, ElementType.Xpath);
		System.out.println(flag);
		webDB.driver.close();
		log.info("closed the child window");
		webDB.driver.switchTo().window(parentWindow);
		log.info("switched to parent window");
		UpdateDB();
		return flag;
	}

	public static Connection setDBConnection() throws ClassNotFoundException, SQLException {
		String dbURL = "jdbc:mysql://10.80.12.58:3306/tadpole";
		String username = "superlemon";
		String password = "superL@m0n";
		Connection con = DriverManager.getConnection(dbURL, username, password);
		return con;
	}

	public static void UpdateDB() {
		try {
			Connection con = setDBConnection();
			Statement stmt = con.createStatement();
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -7);
			System.out.println("Date = " + cal.getTimeInMillis());
			webDB.verifyResponseAPI("GetShopID");
			String query = "update orders " + "set created_on = " + cal.getTimeInMillis()
					+ " , status = 6 ,fulfillment_status = 2 where shop_id = " + webDB.shopid + " and name = " + "'"
					+ OrderNO + "';";
			System.out.println(query);
			stmt.executeUpdate(query);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean verifyAllCustomersTutorial() throws InterruptedException, IOException {
		flag = webDB.waitForElement(CustomersLocators.EXPORT_OPTIN_LIST, ElementType.Xpath);
		if (flag) {
			flag = webDB.waitForElement(MyWhatsAppLocators.TUTORIAL_VIDEO, ElementType.Xpath);
			if (flag) {
				webDB.javaScriptClickAnElement(MyWhatsAppLocators.TUTORIAL_VIDEO, ElementType.Xpath);
				Thread.sleep(2000);
				flag = webDB.waitForElement(WidgetLocators.DISPLAY_TEXT_TUTORIAL, ElementType.Xpath);
				if (flag) {
					String text = webDB.getText(WidgetLocators.DISPLAY_TEXT_TUTORIAL, ElementType.Xpath);
					System.out.println(text);
					if (text.equals(CustomersLocators.TUTORIAL_TITLE)) {
						flag = webDB.waitForElement(CampaignsLocators.WHATSAPPCAMPAIGN_IFRAME, ElementType.CssSelector);
						if (flag) {
							flag = webDB.waitForElement(CampaignsLocators.TUTORIAL_CLOSEBTN, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(CampaignsLocators.TUTORIAL_CLOSEBTN, ElementType.Xpath);
								flag = webDB.waitForElement(CampaignsLocators.TUTORIAL_BTN, ElementType.Xpath);
								if (flag) {
									System.out.println("Verified tutorial video in All Customers section");
								}
							}
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyCustomerGroupsTutorial() throws InterruptedException, IOException {
		flag = webDB.waitForElement(CustomersLocators.USER_GROUP, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomersLocators.USER_GROUP, ElementType.Xpath);
			flag = webDB.waitForElement(CustomersLocators.CREATEUSERGRPBTN, ElementType.Xpath);
			if (flag) {
				flag = webDB.waitForElement(MyWhatsAppLocators.TUTORIAL_VIDEO, ElementType.Xpath);
				if (flag) {
					webDB.javaScriptClickAnElement(MyWhatsAppLocators.TUTORIAL_VIDEO, ElementType.Xpath);
					Thread.sleep(2000);
					flag = webDB.waitForElement(CustomersLocators.CUSTOMERTUTORIAL_TITLE, ElementType.Xpath);
					if (flag) {
						String text = webDB.getText(CustomersLocators.CUSTOMERTUTORIAL_TITLE, ElementType.Xpath);
						System.out.println(text);
						if (text.equals(CustomersLocators.CUSTOMERTUTORIAL_TEXT)) {
							flag = webDB.waitForElement(CampaignsLocators.WHATSAPPCAMPAIGN_IFRAME,
									ElementType.CssSelector);
							if (flag) {
								flag = webDB.waitForElement(CampaignsLocators.TUTORIAL_CLOSEBTN, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(CampaignsLocators.TUTORIAL_CLOSEBTN, ElementType.Xpath);
									flag = webDB.waitForElement(CampaignsLocators.TUTORIAL_BTN, ElementType.Xpath);
									if (flag) {
										System.out.println("Verified tutorial video in Customer Groups section");
									}
								}
							}
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyThankyouPageOptin() throws Exception {
		String phoneNumber = "86";
		String phone = phoneNumber += RandomStringUtils.randomNumeric(8);
		log.info(phone);
		/*
		 * flag = webDB.isElementDisplayed(CommonLocators.TOURPOPUP, ElementType.Xpath);
		 * if (flag) { webDB.pressEscapeKey(); }
		 */
		flag = webDB.clickAnElement(CustomersLocators.WIDGET, ElementType.Xpath);
		if (flag) {
			flag = webDB.clickAnElement(CustomersLocators.STORELINK, ElementType.Xpath);
		}
		parentWindow = webDB.driver.getWindowHandle();
		webDB.switchToChildWindow();
		ShopifyStore.shopifyStorePassEnter();
		ShopifyStore.addItemAndCheckout();
		String phone2 = ShopifyStore.enterShippingDetailsWithExistingno(phone);
		System.out.println(phone2);
		Thread.sleep(1000);
		ShopifyStore.enterCardDetails();
		webDB.moveToElement(ShopifyStoreLocators.PAYNOW, ElementType.Xpath);
		webDB.clickAnElement(ShopifyStoreLocators.PAYNOW, ElementType.Xpath);
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath(ShopifyStoreLocators.ORDER_CONFIRMED_MESSAGE)));
		log.info("Order confirmation message is displayed");
		webDB.driver.navigate().refresh();
		flag = webDB.isElementDisplayed(CustomersLocators.THANK_YOU_OPTIN, ElementType.Xpath);
		log.info("Flag For Thank you optin found " + flag);
		if (flag) {
			log.info("Thank yoy page optin is passed");
		}
		webDB.driver.close();
		log.info("closed the child window");
		webDB.driver.switchTo().window(parentWindow);
		log.info("switched to parent window");
		return flag;
	}

//	public static boolean verifyOptinPopUp() throws Exception {
//
//		flag = webDB.clickAnElement(CustomersLocators.WIDGET, ElementType.Xpath);
//		if (flag) {
//			flag = webDB.clickAnElement(CustomersLocators.STORELINK, ElementType.Xpath);
//		}
//		parentWindow = webDB.driver.getWindowHandle();
//		webDB.switchToChildWindow();
//		ShopifyStore.shopifyStorePassEnter();
//		flag = webDB.clickAnElement(CustomersLocators.CART, ElementType.Xpath);
//		String text = webDB.getAttribute(ShopifyStoreLocators.COUNTRY_CODE, "value");
//		log.info("country code is " + text);
//		if (text.contentEquals("91") || text.contentEquals("1")) {
//			log.info("country code is matched");
//			flag = true;
//		} else {
//			log.info("Different country code found");
//		}
//		if (flag) {
//			flag = webDB.isElementDisplayed(CustomersLocators.CLOSE_BUTTON, ElementType.Xpath);
//			flag = webDB.clickAnElement(CustomersLocators.CLOSE_BUTTON, ElementType.Xpath);
//		}
//		webDB.driver.close();
//		log.info("closed the child window");
//		webDB.driver.switchTo().window(parentWindow);
//		log.info("switched to parent window");
//		return flag;
//	}

	public static boolean verifyAbcartRecovered() throws Exception {
		String phone = "919" + RandomStringUtils.randomNumeric(9);
		System.out.println("phone = " + phone);
		parentWindow = webDB.driver.getWindowHandle();
		WidgetPage.personalWidgetPageToStoreNavigation();
		ShopifyStore.shopifyStorePassEnter();
		flag = webDB.waitForElement(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET, ElementType.Xpath);
		windows = webDB.driver.getWindowHandles();
		if (flag) {
			ShopifyStore.addItemAndCheckout();
			String fullName = firstName + lastName;
			flag = webDB.waitForElement(ShopifyStoreLocators.CONTACT, ElementType.Xpath);
			if (flag) {
				flag = webDB.waitForElement(ShopifyStoreLocators.EMAIL_OR_PHONE, ElementType.Xpath);
				if (flag) {
					webDB.sendTextToAnElement(ShopifyStoreLocators.EMAIL_OR_PHONE, phone, ElementType.Xpath);
					wait.until(
							ExpectedConditions.elementToBeClickable(By.xpath(ShopifyStoreLocators.COUNTRY_DROPDOWN)));
					Select country = new Select(
							webDB.driver.findElement(By.xpath(ShopifyStoreLocators.COUNTRY_DROPDOWN)));
					country.selectByVisibleText("India");
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ShopifyStoreLocators.FIRST_NAME)));
					webDB.sendTextToAnElement(ShopifyStoreLocators.FIRST_NAME, firstName, ElementType.Xpath);
					Thread.sleep(8000);
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
					webDB.sendTextToAnElement(ShopifyStoreLocators.SHIPPING_ADDRESS_PINCODE, pinCode,
							ElementType.Xpath);
					// Thread.sleep(1000);
					flag = webDB.waitForElement(ShopifyStoreLocators.CHECKOUT_OPTIN_CHECKBOX, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(ShopifyStoreLocators.CHECKOUT_OPTIN_CHECKBOX, ElementType.Xpath);
						wait.until(
								ExpectedConditions.elementToBeClickable(By.xpath(ShopifyStoreLocators.CONTINUE_BTN)));
						webDB.clickAnElement(ShopifyStoreLocators.CONTINUE_BTN, ElementType.Xpath);
						flag = webDB.waitForElement(ShopifyStoreLocators.SHIPPINGMETHOD, ElementType.Xpath);
						if (flag) {
							Thread.sleep(1000);
							webDB.driver.switchTo().window(parentWindow);
							log.info("switched to parent window");

							flag = CommonFunctions.verifyOptinNumbersPage();
							if (flag) {
								webDB.driver.navigate().refresh();
								flag = webDB.waitForElement(CustomersLocators.FIRST_NUM, ElementType.Xpath);
								if (flag) {
									String ph = webDB.getText(CustomersLocators.FIRST_NUM, ElementType.Xpath);
									System.out.println("Opted in phone number is " + ph);
									if (phone.equals(ph)) {
										System.out.println("Phone num is same");
										flag = true;
										iterator = windows.iterator();
										while (iterator.hasNext()) {
											childWindow = iterator.next();
											if (!parentWindow.equals(childWindow)) {
												webDB.driver.switchTo().window(childWindow);
											}
										}
										webDB.clickAnElement(ShopifyStoreLocators.CONTINUE_BTN, ElementType.Xpath);
										Thread.sleep(1000);
										flag = DriverBase.verifyResponseAPI("abcart");
										// Thread.sleep(60000);

										if (flag) {
											webDB.driver.switchTo().window(parentWindow);
											log.info("switched to parent window");

											CommonFunctions.verifyOptinNumbersPage();
											// webDB.driver.navigate().refresh();
											// Thread.sleep(1000);
											for (int i = 2000; i < 120000; i = i + 2000) {
												Thread.sleep(i);
												webDB.driver.navigate().refresh();
												String abcart_value = webDB.getText("(//tbody//th[contains(text(),'"
														+ phone + "')])/following::td[4]", ElementType.Xpath);

												if (abcart_value.equals("118.00")) {
													flag = true;
													System.out.println("Abcart value is " + abcart_value);
													System.out.println("Order is abandoned");
													iterator = windows.iterator();
													while (iterator.hasNext()) {
														childWindow = iterator.next();
														if (!parentWindow.equals(childWindow)) {
															webDB.driver.switchTo().window(childWindow);
														}
													}
													ShopifyStore.enterCardDetails();
													webDB.clickAnElement(ShopifyStoreLocators.CONTINUE_BTN,
															ElementType.Xpath);
													wait.until(ExpectedConditions.elementToBeClickable(
															By.xpath(ShopifyStoreLocators.ORDER_CONFIRMED_MESSAGE)));
													webDB.driver.navigate().refresh();

													webDB.driver.switchTo().window(parentWindow);
													log.info("switched to parent window");
													Thread.sleep(1000);
													flag = CommonFunctions.verifyOptinNumbersPage();
													if (flag) {
														String num_of_order = webDB
																.getText(
																		"(//tbody//th[contains(text(),'" + phone
																				+ "')])/following::td[2]",
																		ElementType.Xpath);
														// System.out.println("Number of order is " + num_of_order);
														if (num_of_order.equals("1")) {
															flag = true;
															System.out.println(
																	"Recovered order verified in customer section");
														} else {
															flag = false;
															System.out.println(
																	"Recovered order is not updated in customer section");
														}
													}
													break;
												} else {
													flag = false;
													System.out.println("Order didn't get abandoned");
												}
											}
										}
									} else {
										flag = false;
										System.out.println("Phone num is not the same");
									}
								}
							}
						}
					}
				}
			}
		}

		return flag;
	}

	public static boolean verifyAbcartNotRecovered() throws Exception {
		String phone = "919" + RandomStringUtils.randomNumeric(9);
		// System.out.println("phone = " + phone);
		parentWindow = webDB.driver.getWindowHandle();
		WidgetPage.personalWidgetPageToStoreNavigation();
		ShopifyStore.shopifyStorePassEnter();
		flag = webDB.waitForElement(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET, ElementType.Xpath);
		windows = webDB.driver.getWindowHandles();
		if (flag) {
//			webDB.clickAnElement(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET, ElementType.Xpath);
//			flag = webDB.waitForElement(ShopifyStoreLocators.AGENT, ElementType.Xpath);
//			if (flag) {
//				webDB.clickAnElement(ShopifyStoreLocators.AGENT, ElementType.Xpath);
//				flag = webDB.waitForElement(ShopifyStoreLocators.CHAT_PHONE, ElementType.Xpath);
//				if (flag) {
//					webDB.clickAnElement(ShopifyStoreLocators.CHAT_PHONE, ElementType.Xpath);
//					webDB.sendTextToAnElement(ShopifyStoreLocators.CHAT_PHONE, phone, ElementType.Xpath);
//					webDB.clickAnElement(ShopifyStoreLocators.CHAT_ENTER, ElementType.Xpath);

			ShopifyStore.addItemAndCheckout();
			String fullName = firstName + lastName;
			flag = webDB.waitForElement(ShopifyStoreLocators.CONTACT, ElementType.Xpath);
			if (flag) {
				flag = webDB.waitForElement(ShopifyStoreLocators.EMAIL_OR_PHONE, ElementType.Xpath);
				if (flag) {
					webDB.sendTextToAnElement(ShopifyStoreLocators.EMAIL_OR_PHONE, phone, ElementType.Xpath);
					wait.until(
							ExpectedConditions.elementToBeClickable(By.xpath(ShopifyStoreLocators.COUNTRY_DROPDOWN)));
					Select country = new Select(
							webDB.driver.findElement(By.xpath(ShopifyStoreLocators.COUNTRY_DROPDOWN)));
					country.selectByVisibleText("India");
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ShopifyStoreLocators.FIRST_NAME)));
					webDB.sendTextToAnElement(ShopifyStoreLocators.FIRST_NAME, firstName, ElementType.Xpath);
					Thread.sleep(8000);
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
					webDB.sendTextToAnElement(ShopifyStoreLocators.SHIPPING_ADDRESS_PINCODE, pinCode,
							ElementType.Xpath);
					// Thread.sleep(1000);
					flag = webDB.waitForElement(ShopifyStoreLocators.CHECKOUT_OPTIN_CHECKBOX, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(ShopifyStoreLocators.CHECKOUT_OPTIN_CHECKBOX, ElementType.Xpath);
						wait.until(
								ExpectedConditions.elementToBeClickable(By.xpath(ShopifyStoreLocators.CONTINUE_BTN)));
						webDB.clickAnElement(ShopifyStoreLocators.CONTINUE_BTN, ElementType.Xpath);
						flag = webDB.waitForElement(ShopifyStoreLocators.SHIPPINGMETHOD, ElementType.Xpath);
						if (flag) {
							Thread.sleep(1000);
							webDB.driver.switchTo().window(parentWindow);
							log.info("switched to parent window");

							flag = CommonFunctions.verifyOptinNumbersPage();
							if (flag) {
								webDB.driver.navigate().refresh();
								flag = webDB.waitForElement(CustomersLocators.FIRST_NUM, ElementType.Xpath);
								if (flag) {
									String ph = webDB.getText(CustomersLocators.FIRST_NUM, ElementType.Xpath);
									// System.out.println("Opted in phone number is " + ph);
									if (phone.equals(ph)) {
										System.out.println("Phone num is same");
										flag = true;
										iterator = windows.iterator();
										while (iterator.hasNext()) {
											childWindow = iterator.next();
											if (!parentWindow.equals(childWindow)) {
												webDB.driver.switchTo().window(childWindow);
											}
										}
										webDB.clickAnElement(ShopifyStoreLocators.CONTINUE_BTN, ElementType.Xpath);
										Thread.sleep(1000);

										// Thread.sleep(60000);
										flag = DriverBase.verifyResponseAPI("abcart");
										if (flag) {
											webDB.driver.switchTo().window(parentWindow);
											log.info("switched to parent window");

											CommonFunctions.verifyOptinNumbersPage();
//											webDB.driver.navigate().refresh();
//											Thread.sleep(1000);
											for (int i = 2000; i < 120000; i = i + 2000) {
												Thread.sleep(i);
												webDB.driver.navigate().refresh();
												String abcart_value = webDB.getText("(//tbody//th[contains(text(),'"
														+ phone + "')])/following::td[4]", ElementType.Xpath);

												if (abcart_value.equals("118.00")) {
													flag = true;
													System.out.println("Abcart value is " + abcart_value);
													System.out.println("Order is abandoned");
													String num_of_order = webDB.getText("(//tbody//th[contains(text(),'"
															+ phone + "')])/following::td[2]", ElementType.Xpath);
													// System.out.println("Number of order is " + num_of_order);
													if (!num_of_order.equals("1")) {
														flag = true;
														System.out.println(
																"Number of order in customer section is verified");

													} else {
														flag = false;
													}
													String total_ordervalue = webDB
															.getText(
																	"(//tbody//th[contains(text(),'" + phone
																			+ "')])/following::td[3]",
																	ElementType.Xpath);
													// System.out.println("Total order value is " + total_ordervalue);
													if (!total_ordervalue.equals("118.00")) {
														flag = true;
														System.out.println(
																"Total order value in customer section is verified");
														break;
													} else {
														flag = false;
													}

												} else {
													flag = false;
													System.out.println("Order didn't get abandoned");
												}
											}
										}
									} else {
										flag = false;
										System.out.println("Phone num is not the same");
									}
								}
							}
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyAbcartValue() throws Exception {
		String phone = "919" + RandomStringUtils.randomNumeric(9);
		// System.out.println("phone = " + phone);
		parentWindow = webDB.driver.getWindowHandle();
		WidgetPage.personalWidgetPageToStoreNavigation();
		ShopifyStore.shopifyStorePassEnter();
		flag = webDB.waitForElement(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET, ElementType.Xpath);
		windows = webDB.driver.getWindowHandles();
		if (flag) {
			ShopifyStore.addItemAndCheckout();
			String fullName = firstName + lastName;
			flag = webDB.waitForElement(ShopifyStoreLocators.CONTACT, ElementType.Xpath);
			if (flag) {
				flag = webDB.waitForElement(ShopifyStoreLocators.EMAIL_OR_PHONE, ElementType.Xpath);
				if (flag) {
					webDB.sendTextToAnElement(ShopifyStoreLocators.EMAIL_OR_PHONE, phone, ElementType.Xpath);
					wait.until(
							ExpectedConditions.elementToBeClickable(By.xpath(ShopifyStoreLocators.COUNTRY_DROPDOWN)));
					Select country = new Select(
							webDB.driver.findElement(By.xpath(ShopifyStoreLocators.COUNTRY_DROPDOWN)));
					country.selectByVisibleText("India");
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ShopifyStoreLocators.FIRST_NAME)));
					webDB.sendTextToAnElement(ShopifyStoreLocators.FIRST_NAME, firstName, ElementType.Xpath);
					Thread.sleep(8000);
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
					webDB.sendTextToAnElement(ShopifyStoreLocators.SHIPPING_ADDRESS_PINCODE, pinCode,
							ElementType.Xpath);
					// Thread.sleep(1000);
					flag = webDB.waitForElement(ShopifyStoreLocators.CHECKOUT_OPTIN_CHECKBOX, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(ShopifyStoreLocators.CHECKOUT_OPTIN_CHECKBOX, ElementType.Xpath);
						wait.until(
								ExpectedConditions.elementToBeClickable(By.xpath(ShopifyStoreLocators.CONTINUE_BTN)));
						webDB.clickAnElement(ShopifyStoreLocators.CONTINUE_BTN, ElementType.Xpath);
						flag = webDB.waitForElement(ShopifyStoreLocators.SHIPPINGMETHOD, ElementType.Xpath);
						if (flag) {
							Thread.sleep(1000);
							webDB.driver.switchTo().window(parentWindow);
							log.info("switched to parent window");

							flag = CommonFunctions.verifyOptinNumbersPage();
							if (flag) {
								webDB.driver.navigate().refresh();
								flag = webDB.waitForElement(CustomersLocators.FIRST_NUM, ElementType.Xpath);
								if (flag) {
									String ph = webDB.getText(CustomersLocators.FIRST_NUM, ElementType.Xpath);
									// System.out.println("Opted in phone number is " + ph);
									if (phone.equals(ph)) {
										// System.out.println("Phone num is same");
										flag = true;
										iterator = windows.iterator();
										while (iterator.hasNext()) {
											childWindow = iterator.next();
											if (!parentWindow.equals(childWindow)) {
												webDB.driver.switchTo().window(childWindow);
											}
										}
										webDB.clickAnElement(ShopifyStoreLocators.CONTINUE_BTN, ElementType.Xpath);
										Thread.sleep(1000);

										// Thread.sleep(60000);
										flag = DriverBase.verifyResponseAPI("abcart");
										if (flag) {
											webDB.driver.switchTo().window(parentWindow);
											log.info("switched to parent window");

											CommonFunctions.verifyOptinNumbersPage();
											for (int i = 2000; i < 120000; i = i + 2000) {
												Thread.sleep(i);
												webDB.driver.navigate().refresh();
												String abcart_value = webDB.getText("(//tbody//th[contains(text(),'"
														+ phone + "')])/following::td[4]", ElementType.Xpath);

												if (abcart_value.equals("118.00")) {
													flag = true;
													System.out.println("Abcart value is " + abcart_value);
													System.out.println("Order is abandoned");
													break;
												} else {
													flag = false;
													System.out.println("Order didn't get abandoned");
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean VerifyCustomerGroupCondition() throws Exception {
		String random = RandomStringUtils.randomNumeric(5);
		flag = webDB.waitForElement(CustomersLocators.USERGRPTAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomersLocators.USERGRPTAB, ElementType.Xpath);
			flag = webDB.waitForElement(CustomersLocators.CREATEUSERGRPBTN, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CustomersLocators.CREATEUSERGRPBTN, ElementType.Xpath);
				flag = webDB.waitForElement(CustomersLocators.GRPNAMEINPUT, ElementType.Xpath);
				if (flag) {
					grpname = CustomersLocators.GRPNAMEVALUE + random;
					Thread.sleep(2000);
					webDB.sendTextToAnElement(CustomersLocators.GRPNAMEINPUT, grpname, ElementType.Xpath);
					flag = webDB.waitForElement(CustomersLocators.PARAMETERDROPDOWN, ElementType.Xpath);
					if (flag) {
						Thread.sleep(2000);
						webDB.clickAnElement(CustomersLocators.PARAMETERDROPDOWN, ElementType.Xpath);
						webDB.selectDropDownOptions(CustomersLocators.PARAMETERDROPDOWN,
								CustomersLocators.PARAMETERVALUE, ElementType.Xpath);
						Thread.sleep(2000);
						flag = webDB.waitForElement(CustomersLocators.CONDITIONDROPDOWN, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(CustomersLocators.CONDITIONDROPDOWN, ElementType.Xpath);
							webDB.selectDropDownOptions(CustomersLocators.CONDITIONDROPDOWN,
									CustomersLocators.CONDITIONVALUE, ElementType.Xpath);
							Thread.sleep(2000);
							webDB.sendTextToAnElement(CustomersLocators.VALUEINPUT, CustomersLocators.VALUE,
									ElementType.Xpath);
							flag = webDB.waitForElement(CustomersLocators.ADDCONDITIONBTN, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(CustomersLocators.ADDCONDITIONBTN, ElementType.Xpath);
								flag = webDB.waitForElement(CustomersLocators.ORBUTTON, ElementType.CssSelector);
								if (flag) {
									webDB.clickAnElement(CustomersLocators.ORBUTTON, ElementType.CssSelector);

									flag = webDB.waitForElement(CustomersLocators.SECOND_PARAMETERDROPDOWN,
											ElementType.Xpath);
									if (flag) {
										Thread.sleep(2000);
										webDB.clickAnElement(CustomersLocators.SECOND_PARAMETERDROPDOWN,
												ElementType.Xpath);
										webDB.selectDropDownOptions(CustomersLocators.SECOND_PARAMETERDROPDOWN,
												CustomersLocators.SECOND_PARAMETERVALUE, ElementType.Xpath);
										Thread.sleep(2000);
										flag = webDB.waitForElement(CustomersLocators.SECOND_CONDITIONDROPDOWN,
												ElementType.Xpath);
										if (flag) {
											webDB.clickAnElement(CustomersLocators.SECOND_CONDITIONDROPDOWN,
													ElementType.Xpath);
											webDB.selectDropDownOptions(CustomersLocators.SECOND_CONDITIONDROPDOWN,
													CustomersLocators.SECOND_CONDITIONVALUE, ElementType.Xpath);
											Thread.sleep(2000);
											flag = webDB.waitForElement(CustomersLocators.SELECT_VALUE,
													ElementType.Xpath);
											if (flag) {
												webDB.clickAnElement(CustomersLocators.SELECT_VALUE, ElementType.Xpath);
												// modified - changed locator
												flag = webDB.waitForElement(CustomersLocators.DATE, ElementType.Xpath);
												if (flag) {
													webDB.clickAnElement(CustomersLocators.DATE, ElementType.Xpath);
//									webDB.sendTextToAnElement(CustomersLocators.SECOND_VALUEINPUT,
//											CustomersLocators.SECOND_VALUE, ElementType.Xpath);

													Thread.sleep(2000);
													webDB.clickAnElement(CustomersLocators.SAVEBTN, ElementType.Xpath);
													Thread.sleep(2000);
													webDB.moveToElement(CustomersLocators.GRPNODE, ElementType.Xpath);
													flag = webDB.waitForElement(CustomersLocators.GRPEDITBUTTON,
															ElementType.Xpath);
													System.out.println(flag);
												}

											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean VerifyCustomerGroupDetails() throws Exception {
		flag = webDB.waitForElement(CustomersLocators.USERGRPTAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomersLocators.USERGRPTAB, ElementType.Xpath);
			String grp = webDB.getText(CustomersLocators.NAME, ElementType.Xpath);
			System.out.println("grp = " + grp);
			String[] result = grp.split("\n", 2);
			System.out.println("result = " + result[0]);
			grpname = result[0];
			webDB.waitForElement(CustomersLocators.GRPDOWNLOADBUTTON, ElementType.Xpath);
			flag = webDB.isElementDisplayed(CustomersLocators.GRPDOWNLOADBUTTON, ElementType.Xpath);
			if (flag) {
				webDB.waitForElement(CustomersLocators.GRPEDITBUTTON, ElementType.Xpath);
				flag = webDB.isElementDisplayed(CustomersLocators.GRPEDITBUTTON, ElementType.Xpath);
				if (flag) {
					webDB.waitForElement(CustomersLocators.GRPDELETEBUTTON, ElementType.Xpath);
					flag = webDB.isElementDisplayed(CustomersLocators.GRPDELETEBUTTON, ElementType.Xpath);
					if (flag) {
						webDB.waitForElement(CustomersLocators.CONDITION, ElementType.Xpath);
						flag = webDB.isElementDisplayed(CustomersLocators.CONDITION, ElementType.Xpath);
						if (flag) {
							webDB.waitForElement(CustomersLocators.CREATED_ON, ElementType.Xpath);
							flag = webDB.isElementDisplayed(CustomersLocators.CREATED_ON, ElementType.Xpath);
							if (flag) {
								webDB.waitForElement(CustomersLocators.SEND_MESSAGE, ElementType.Xpath);
								flag = webDB.isElementDisplayed(CustomersLocators.SEND_MESSAGE, ElementType.Xpath);
								if (flag) {
									System.out.println("Customer group details verified");
								}
							}
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean WixcheckOptinCheckoutSourceInOptinList()
			throws FileNotFoundException, InterruptedException, IOException {
		flag = false;
		parentWindow = webDB.driver.getWindowHandle();
		WidgetPage.personalWidgetPageToStoreNavigation();
//		ShopifyStore.shopifyStorePassEnter();
//		Dimension d = new Dimension(1940, 1000);
//		webDB.driver.manage().window().setSize(d);
		String phone = WixStore.addItemAndCheckout();

//		webDB.driver.navigate().refresh();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ShopifyStoreLocators.CHECKOUT_EMAIL_PHONE)));
//		String phone = ShopifyStore.enterShippingDetailsWithPhone();
//		System.out.println(phone);
//		Thread.sleep(1000);
//		ShopifyStore.enterCardDetails();
//		webDB.clickAnElement(ShopifyStoreLocators.CREDITCARDTEXT, ElementType.Xpath);
//		Thread.sleep(2000);
//		webDB.scrollToAnElement(ShopifyStoreLocators.PAYNOW, ElementType.Xpath);
//		Thread.sleep(2000);
//		flag = webDB.waitForElement(ShopifyStoreLocators.PAYNOW, ElementType.Xpath);
//		if (flag) {
//			webDB.clickAnElement(ShopifyStoreLocators.PAYNOW, ElementType.Xpath);
//			wait.until(ExpectedConditions
//					.visibilityOfElementLocated(By.xpath(ShopifyStoreLocators.ORDER_CONFIRMED_MESSAGE)));
//			log.info("Order confirmation message is displayed");
//			Thread.sleep(2000);
//			webDB.driver.navigate().refresh();
////		wait.until(
////				ExpectedConditions.visibilityOfElementLocated(By.id(ShopifyStoreLocators.OPTIN_CONFIRM_SUCCESS_MSG)));
////		webDB.isElementDisplayed(ShopifyStoreLocators.OPTIN_CONFIRM_SUCCESS_MSG, ElementType.Id);
		webDB.driver.close();
		log.info("closed the child window");
		webDB.driver.switchTo().window(parentWindow);
		log.info("switched to parent window");
		CommonFunctions.verifyOptinNumbersPage();
		wait.until(ExpectedConditions
				.visibilityOf(webDB.driver.findElement(By.xpath(CustomersLocators.EXPORT_OPTIN_LIST))));
		elements = webDB.driver.findElements(By.xpath(CustomersLocators.PHONE_COLUMBER_COLUMN));
		List<WebElement> source = webDB.driver.findElements(By.xpath(CustomersLocators.SOURCE_COLUMN));
		for (int i = 0; i < elements.size(); i++) {
			String element = elements.get(i).getText();
//			log.info(element);
			if (element.contains(phone)) {
				text = source.get(i).getText();
				log.info(text);
				if (text.equalsIgnoreCase("CHECKOUT")) {
					flag = true;
					log.info("Source for the number opted in is properly updated in Optin List");
					break;
				}

			}
		}

		return flag;
	}
}
