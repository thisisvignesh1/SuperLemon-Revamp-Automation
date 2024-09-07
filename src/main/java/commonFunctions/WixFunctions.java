package commonFunctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import locators.AnalyticsLocators;
import locators.AutomationLocators;
import locators.CampaignsLocators;
import locators.CommonLocators;
import locators.CustomersLocators;
import locators.MessageLogsLocators;
import locators.MessagingLocators;
import locators.ShopifyStoreLocators;
import locators.WidgetLocators;
import locators.WixLocators;
import locators.WixStoreLocators;
import utils.DriverBase;
import utils.Listener;
import utils.DriverBase.ElementType;

@SuppressWarnings({ "static-access", "unused" })
public class WixFunctions {

	private static Logger log = Logger.getLogger(WidgetPage.class);
	private static DriverBase webDB = new DriverBase();
	private static JavascriptExecutor personalWidget = (JavascriptExecutor) webDB.driver;
	private static Actions actions = new Actions(webDB.driver);
	private static WebDriverWait wait = new WebDriverWait(webDB.driver, 10);
	private static boolean flag;
	private static String parentWindow;
	private static String text;
	private static List<WebElement> elements;
	private static JavascriptExecutor adminConfiguration = (JavascriptExecutor) webDB.driver;
	private static String phone;

	private static WebElement element;
	private static Select category;
	private static String TemplateDesc = "This is a manual messaging Template";
	private static String templateName;
	private static List<WebElement> templateNames;
	private static JavascriptExecutor js = (JavascriptExecutor) webDB.driver;
	private static List<WebElement> editButton;
	private static String VerifyTemplateName = "AutoTemplate";
	private static Iterator<String> iterator;
	private static String TemplateName;

	public static String random = RandomStringUtils.randomNumeric(2);
	private static String propertyFilePath;

	private static String TemplateMsg = "This is automated message";
	private static String ABcarttemplatename;
	private static String OrderCRMtemplatename;

	private static List<WebElement> buttons;
	public static Properties prop = new Properties();
	private static String Name;
	private static String url;
	private static Iterator<String> it;
	private static Set<String> windows;
	private static String childWindow;
	private static List<WebElement> tagNames;
	private static String fullName;

	public static boolean checkupdatedGreetingsSettingOnStore() throws InterruptedException {
		flag = false;
		parentWindow = webDB.driver.getWindowHandle();
		WidgetPage.enableCHatButton();
		WidgetPage.enableAgent();
		WidgetPage.resetDefaultAgentTimings();
		WidgetPage.selectShowChatWhenAgentOffline();
		WidgetPage.enableGreetingsWidget();
		updateGreetingsTitleAndHelpText();
		WidgetPage.personalWidgetPageToStoreNavigation();
//		ShopifyStore.shopifyStorePassEnter();
		Thread.sleep(1000);
		webDB.waitForElement(WixStoreLocators.CHAT_WIDGET, ElementType.Xpath);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.CHAT_WIDGET)));

		webDB.clickAnElement(WixStoreLocators.CHAT_WIDGET, ElementType.Xpath);
		Thread.sleep(1000);
		// webDB.isElementDisplayed(ShopifyStoreLocators.GREETINGS_DESC_COLOR,
		// ElementType.Xpath);
		text = webDB.driver.findElement(By.cssSelector(ShopifyStoreLocators.GREETINGS_HEADER_TITLE)).getText();
		String descText = webDB.driver.findElement(By.cssSelector(ShopifyStoreLocators.GREETINGS_DESC_TEXT)).getText();
		if (text.equalsIgnoreCase("Hello There") && descText.contains("We are here to solve your Queries")) {
			flag = true;
			log.info("Updated Greetings Title and HelpText displayed on store");
		}
		webDB.driver.close();
		log.info("closed the child window");
		webDB.driver.switchTo().window(parentWindow);
		log.info("switched to parent window");
		return flag;
	}

	public static boolean updateGreetingsTitleAndHelpText() throws InterruptedException {
		// webDB.driver.navigate().refresh();
		// personalWidget.executeScript("window.scrollBy(0,500)");

//		actions.moveToElement(webDB.driver.findElement(By.cssSelector(WixLocators.GREETINGS_TITLE_WIX)))
//				.click().build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WixLocators.GREETINGS_TITLE_WIX)));
		webDB.scrollToAnElement(WixLocators.GREETINGS_TITLE_WIX, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(WixLocators.GREETINGS_TITLE_WIX))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(WixLocators.GREETINGS_TITLE_WIX, "Hello There", ElementType.Xpath);
		webDB.driver.findElement(By.xpath(WixLocators.GREETINGS_HELP_TEXT))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(WixLocators.GREETINGS_HELP_TEXT, "We are here to solve your Queries",
				ElementType.Xpath);
		// wait.until(ExpectedConditions.visibilityOf(webDB.driver.findElement(By.xpath(CommonLocators.SAVE_SETTINGS))));
		flag = webDB.isElementDisplayed(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
			flag = webDB.isElementDisplayed(WidgetLocators.SAVE_SUCCESS_MSG, ElementType.Xpath);
			if (flag) {
				log.info("Greeting Text and Helptext updated successfullly");
			}

//		personalWidget.executeScript("window.scrollBy(0,500)");
//		wait.until(ExpectedConditions
//				.elementToBeClickable(By.xpath(PersonalWidgetLocators.GREETINGS_TITLE_TXT_FIELD)));
//		actions.moveToElement(
//				webDB.driver.findElement(By.xpath(PersonalWidgetLocators.GREETINGS_TITLE_TXT_FIELD))).click()
//				.build().perform();
//		webDB.driver.findElement(By.xpath(PersonalWidgetLocators.GREETINGS_TITLE_TXT_FIELD))
//				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
//		webDB.sendTextToAnElement(PersonalWidgetLocators.GREETINGS_TITLE_TXT_FIELD, "Hello There",
//				ElementType.CssSelector);
//		Thread.sleep(800);
//		actions.moveToElement(
//				webDB.driver.findElement(By.xpath(PersonalWidgetLocators.GREETINGS_HELPTEXT_FIELD))).click()
//				.build().perform();
//		webDB.driver.findElement(By.xpath(PersonalWidgetLocators.GREETINGS_HELPTEXT_FIELD))
//				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
//		webDB.sendTextToAnElement(PersonalWidgetLocators.GREETINGS_HELPTEXT_FIELD, "We are here to solve your Queries",
//				ElementType.Xpath);
//
//		wait.until(ExpectedConditions.visibilityOf(webDB.driver.findElement(By.xpath(CommonLocators.SAVE_SETTINGS))));
//		webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
//		flag = webDB.isElementDisplayed(PersonalWidgetLocators.SAVE_SUCCESS_MSG, ElementType.Xpath);
//		if (flag) {
//			log.info("Greeting Text and Helptext updated successfullly");
//		}
		}
		return flag;

	}

	public static boolean checkOptinWidgetSourceInOptinList() throws Throwable {
		flag = false;
		parentWindow = webDB.driver.getWindowHandle();
		CommonFunctions.verifyPersonalWidgetPage();
		log.info("verifyPersonalWidgetPage method executed");
		// Thread.sleep(2000);
		WidgetPage.personalWidgetPageToStoreNavigation();
		log.info("personalWidgetPageToStoreNavigation method executed");
//		ShopifyStore.shopifyStorePassEnter();
//		log.info("shopifyStorePassEnter method executed");
		// adminConfiguration.executeScript("window.localStorage.clear();");
		// CommonFunctions.clearLocalStorage();
		flag = WixStore.verifyOptinPopUpDisplayed();
		String phone = 9 + RandomStringUtils.randomNumeric(9);
		for (int j = 1000; j < 5000; j = j + 1000) {
			Thread.sleep(j);
		}
		// Thread.sleep(5000);
		// modified - changed locator type for country code field, phone field
		flag = webDB.waitForElement(ShopifyStoreLocators.OPTIN_POPUP_COUNTRY_CODE_FIELD, ElementType.Xpath);
		webDB.selectDropDownOptions(ShopifyStoreLocators.OPTIN_POPUP_COUNTRY_CODE_FIELD, WidgetLocators.INDIA_CODE,
				ElementType.Xpath);
//		webDB.sendTextToAnElement(ShopifyStoreLocators.OPTIN_POPUP_COUNTRY_CODE_FIELD, "+1", ElementType.Id);
		webDB.sendTextToAnElement(ShopifyStoreLocators.OPTIN_POPUP_PHONE_FIELD, phone, ElementType.Xpath);
		System.out.println("phonenumber" + phone);
		webDB.clickAnElement(ShopifyStoreLocators.CONFIRM_BUTTON_OPTIN_POPUP, ElementType.Id);
		for (int j = 1000; j < 5000; j = j + 1000) {
			Thread.sleep(j);
		}
		// Thread.sleep(5000);
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

	public static boolean VerifyOrderConfirmationMessageLog()
			throws FileNotFoundException, InterruptedException, IOException {
		parentWindow = webDB.driver.getWindowHandle();
		flag = CommonFunctions.verifyPersonalWidgetPage();
		if (flag) {
			Thread.sleep(800);
			WidgetPage.personalWidgetPageToStoreNavigation();
			Thread.sleep(800);
			fullName = WixStore.addItemAndCheckoutReturningName();
			log.info("The full name is " + fullName);
			webDB.driver.switchTo().window(parentWindow);
			flag = CommonFunctions.verifyAnalyticsPage();
			if (flag) {
				Thread.sleep(10000);
				log.info("Reached analytics page");
				flag = webDB.waitForElement(MessageLogsLocators.MESSAGE_LOGS_TAB, ElementType.Xpath);
				if (flag) {
					log.info("Message logs is visible");
					webDB.clickAnElement(MessageLogsLocators.MESSAGE_LOGS_TAB, ElementType.Xpath);
					flag = webDB.waitForElement(MessageLogsLocators.FIRST_NAME, ElementType.Xpath);
					if (flag) {
						log.info("First name is visible");
						text = webDB.getText(MessageLogsLocators.FIRST_NAME, ElementType.Xpath);
						log.info("The name is " + text);

						if (fullName.equals(text)) {
							System.out.println("Success");
							flag = true;
						}
					}
				}
			}
		}

//		WixStore.addItem();
//		phone = WixStore.checkOutAndEnterDetails();
//		System.out.println(phone);
//		WixStore.PaymentAndOrder();
		// WixStore.enterCardDetails();

		return flag;
	}

	public static boolean VerifyTemplateNameNotEmpty() throws InterruptedException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AutomationLocators.EXTENSION_TEMPLATES)));
		Thread.sleep(2000);
		element = webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES));
		actions.moveToElement(element).click().build().perform();
		// Create template button click
		webDB.waitForElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
		log.info("Create templates Button s Displayed");
		webDB.clickAnElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
		log.info("Clicked on Create Templates Button");
		flag = webDB.isElementDisplayed(AutomationLocators.TEMPLATE_NAME_FIELD, ElementType.Xpath);
		if (flag) {
			category = new Select(webDB.driver.findElement(By.xpath(AutomationLocators.CATEGORY_DROPDOWN)));
			category.selectByVisibleText("Orders");
			webDB.sendTextToAnElement(AutomationLocators.TEMPLATE_DESC_FIELD, TemplateDesc, ElementType.Xpath);
			Thread.sleep(2000);
//			category = new Select(webDB.driver.findElement(By.xpath(AutomationLocators.CATEGORY_QUICK_DROPDOWN)));
//			category.selectByVisibleText("Quick Reply");
//			Thread.sleep(2000);
			webDB.clickAnElement(AutomationLocators.SUBMIT, ElementType.Xpath);
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath(AutomationLocators.TEMPLATE_EDIT_SUCCESS)));
			text = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATE_EDIT_SUCCESS)).getText();
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath(AutomationLocators.TEMPLATE_EDIT_SUCCESS)));
			flag = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATE_EDIT_SUCCESS)).isDisplayed();
			log.info(text);
		}
		return flag;
	}

	public static String createExtensiontemplate() throws InterruptedException {
		webDB.driver.navigate().refresh();
//		Thread.sleep(5000);
		if ((System.getProperty("platformName").equals("browserstack"))
				&& (System.getProperty("os").equals("Ios") || System.getProperty("os").equals("Android"))) {
			webDB.waitForElement(CommonLocators.THREEDOTSMENU, ElementType.Xpath);
			webDB.driver.findElement(By.xpath(CommonLocators.THREEDOTSMENU)).click();
		}
		flag = webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			webDB.clickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
//		element = webDB.driver.findElement(By.xpath(TemplatesLocators.EXTENSION_TEMPLATES));
//		js.executeScript("arguments[0].click();", element);
//		webDB.isElementDisplayed(TemplatesLocators.EXTENSION_TEMPLATES, ElementType.Id);
//		webDB.clickAnElement(TemplatesLocators.EXTENSION_TEMPLATES, ElementType.o);
			webDB.scrollToAnElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AutomationLocators.CREATE_TEMPLATES)));
			webDB.clickAnElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);

			flag = webDB.isElementDisplayed(AutomationLocators.TEMPLATE_NAME_FIELD, ElementType.Xpath);

			if (flag) {
				templateName = "AutoTemplate" + RandomStringUtils.randomNumeric(5);
				log.info(templateName);
				category = new Select(webDB.driver.findElement(By.xpath(AutomationLocators.CATEGORY_DROPDOWN)));
				category.selectByVisibleText("Orders");
				webDB.sendTextToAnElement(AutomationLocators.TEMPLATE_NAME_FIELD, templateName, ElementType.Xpath);
				Thread.sleep(2000);
				webDB.sendTextToAnElement(AutomationLocators.TEMPLATE_DESC_FIELD, TemplateDesc, ElementType.Xpath);
				Thread.sleep(2000);
//				category = new Select(webDB.driver.findElement(By.xpath(AutomationLocators.CATEGORY_QUICK_DROPDOWN)));
//				category.selectByVisibleText("Quick Reply"); //Not available in wix
//				Thread.sleep(2000);
				webDB.clickAnElement(AutomationLocators.SUBMIT, ElementType.Xpath);
				log.debug("Clicked on Submit Button");
				wait.until(ExpectedConditions
						.visibilityOf(webDB.driver.findElement(By.xpath(AutomationLocators.ACTION_SUCCESS_POPUP))));
				text = webDB.getText(AutomationLocators.ACTION_SUCCESS_POPUP, ElementType.Xpath);
				if (text.contains(MessagingLocators.TEMPLATE_CREATED)) {
					log.info("Template created successfully");
				}
				element = webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES));
				js.executeScript("arguments[0].click();", element);
			}
		}
		return templateName;
	}

	public static boolean VerifyDuplicateTemplateName() throws InterruptedException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationLocators.EXTENSION_TEMPLATES)));
		element = webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES));
		actions.moveToElement(element).click().build().perform();
		templateName = createExtensiontemplate();
		element = webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES));
		js.executeScript("arguments[0].click();", element);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(AutomationLocators.CREATE_TEMPLATES)));
		webDB.clickAnElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
		wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(AutomationLocators.TEMPLATE_NAME_FIELD)));
		flag = webDB.isElementDisplayed(AutomationLocators.TEMPLATE_NAME_FIELD, ElementType.Xpath);
		if (flag) {
			category = new Select(webDB.driver.findElement(By.xpath(AutomationLocators.CATEGORY_DROPDOWN)));
			category.selectByVisibleText("Orders");
			webDB.sendTextToAnElement(AutomationLocators.TEMPLATE_NAME_FIELD, templateName, ElementType.Xpath);
			Thread.sleep(2000);
			webDB.sendTextToAnElement(AutomationLocators.TEMPLATE_DESC_FIELD, TemplateDesc, ElementType.Xpath);
			Thread.sleep(2000);
//			category = new Select(webDB.driver.findElement(By.xpath(AutomationLocators.CATEGORY_QUICK_DROPDOWN)));
//			category.selectByVisibleText("Quick Reply");
//			Thread.sleep(2000);
			webDB.clickAnElement(AutomationLocators.SUBMIT, ElementType.Xpath);
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath(AutomationLocators.TEMPLATE_EDIT_SUCCESS)));
			text = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATE_EDIT_SUCCESS)).getText();
			flag = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATE_EDIT_SUCCESS)).isDisplayed();
			log.info(text);
		}
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(AutomationLocators.GO_BACK_BTN)));
		webDB.waitForElement(AutomationLocators.GO_BACK_BTN, ElementType.Xpath);
		webDB.clickAnElement(AutomationLocators.GO_BACK_BTN, ElementType.Xpath);
		element = webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES));
		js.executeScript("arguments[0].click();", element);
		flag = AutomationPage.deleteExtensionTemplate(templateName);
		return flag;
	}

	public static boolean VerifyTemplateContents() throws InterruptedException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AutomationLocators.EXTENSION_TEMPLATES)));
		element = webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES));
		actions.moveToElement(element).click().build().perform();
		templateName = createExtensiontemplate();
		flag = verifyAfterTemplateCreated(templateName);
		System.out.println("Flag of aftertemplatecreated is " + flag);
		if (flag) {
			element = webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES));
			js.executeScript("arguments[0].click();", element);
			flag = AutomationPage.deleteExtensionTemplate(templateName);
			log.info("flag is " + flag);
		}
		return flag;
	}

	public static boolean VerifyEditTemplateName() throws InterruptedException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2000);
		templateName = createExtensiontemplate();
		element = webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES));
		js.executeScript("arguments[0].click();", element);
		// Delete a Template
		flag = editExtensionTemplate(templateName);
		Thread.sleep(3000);
		webDB.waitForElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
//		element = webDB.driver.findElement(By.xpath(TemplatesLocators.EXTENSION_TEMPLATES));
//		js.executeScript("arguments[0].click();", element);
//		if (flag) {
//			Thread.sleep(2000);
//			AutomationPage.deleteExtensionTemplate(templateName);
//		} else {
//			log.info("Flag value is false");
//		}
		return flag;
	}

	public static boolean editExtensionTemplate(String templateName) {
		templateNames = webDB.driver.findElements(By.xpath(AutomationLocators.TEMPLATE_NAMES_COMMON));
		for (int i = 0; i < templateNames.size(); i++) {
			text = templateNames.get(i).getText();
			if (text.contains(templateName)) {
				editButton = webDB.driver.findElements(By.xpath(AutomationLocators.EDIT_TEMPLATE));
				editButton.get(i).click();
				flag = webDB.isElementDisplayed(AutomationLocators.TEMPLATE_NAME_FIELD, ElementType.Xpath);
				if (flag) {
					webDB.sendTextToAnElement(AutomationLocators.TEMPLATE_NAME_FIELD, "edited", ElementType.Xpath);
//				category = new Select(
//						webDB.driver.findElement(By.cssSelector(AutomationLocators.EDIT_TEMPLATE_CATEGORY_DROPDOWN)));
//				category.selectByVisibleText("Orders");
//				wait.until(
//						ExpectedConditions.visibilityOf(webDB.driver.findElement(By.xpath(AutomationLocators.UPDATE))));
//				webDB.clickAnElement(AutomationLocators.UPDATE, ElementType.Xpath);
					wait.until(ExpectedConditions
							.visibilityOf(webDB.driver.findElement(By.xpath(WixLocators.TEMPLATE_EDIT_SAVE))));
					element = webDB.driver.findElement(By.xpath(WixLocators.TEMPLATE_EDIT_SAVE));
					js.executeScript("arguments[0].click();", element);
					flag = webDB.isElementDisplayed(AutomationLocators.TEMPLATE_EDIT_SUCCESS, ElementType.Xpath);
					if (flag) {
						wait.until(ExpectedConditions.invisibilityOf(
								webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATE_EDIT_SUCCESS))));
						text = webDB.getText(AutomationLocators.TEMPLATE_EDIT_SUCCESS, ElementType.Xpath);
						if (text.contains(AutomationLocators.TEMPLATE_EDIT)) {
//				wait.until(ExpectedConditions.visibilityOf(webDB.driver.findElement(By.xpath(TemplatesLocators.CONFIRM))));
//				webDB.clickAnElement(TemplatesLocators.CONFIRM, ElementType.Xpath);
//				flag = webDB.isElementDisplayed(TemplatesLocators.ACTION_SUCCESS_POPUP, ElementType.Xpath);
							log.info("Template Edited Successfully");
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean VerifyDiscardBtnOnCreateTemplate() throws InterruptedException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AutomationLocators.EXTENSION_TEMPLATES)));
		element = webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES));
		actions.moveToElement(element).click().build().perform();
		// CLick on create template
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationLocators.CREATE_TEMPLATES)));
		webDB.clickAnElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
		flag = webDB.isElementDisplayed(AutomationLocators.TEMPLATE_NAME_FIELD, ElementType.Xpath);
		if (flag) {
			category = new Select(webDB.driver.findElement(By.xpath(AutomationLocators.CATEGORY_DROPDOWN)));
			category.selectByVisibleText("Orders");
			// Enter template name
			webDB.sendTextToAnElement(AutomationLocators.TEMPLATE_NAME_FIELD, VerifyTemplateName, ElementType.Xpath);
			// Enter template description
			webDB.sendTextToAnElement(AutomationLocators.TEMPLATE_DESC_FIELD, TemplateDesc, ElementType.Xpath);
			// Select Category
			// Click on Discard button
			webDB.clickAnElement(AutomationLocators.TEMPLATE_DISCARD_BTN, ElementType.Xpath);

		}
		// Verify Empty fields
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TemplatesLocators.TEMPLATE_NAME_ERROR)));
//		flag = webDB.driver.findElement(By.xpath(TemplatesLocators.TEMPLATE_NAME_ERROR)).isDisplayed();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TemplatesLocators.TEMPLATE_DESC_ERROR)));
//		flag = webDB.driver.findElement(By.xpath(TemplatesLocators.TEMPLATE_DESC_ERROR)).isDisplayed();
		return flag;
	}

	public static boolean VerifyCatogeryForTemplate() throws InterruptedException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2500);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AutomationLocators.EXTENSION_TEMPLATES)));
//		element = webDB.driver.findElement(By.xpath(TemplatesLocators.EXTENSION_TEMPLATES));
//		actions.moveToElement(element).click().build().perform();
		webDB.clickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationLocators.CREATE_TEMPLATES)));
		webDB.clickAnElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
		flag = webDB.isElementDisplayed(AutomationLocators.TEMPLATE_NAME_FIELD, ElementType.Xpath);
		if (flag) {
			templateName = "AutoTemplate" + Math.random();
			log.info(templateName);
			category = new Select(webDB.driver.findElement(By.xpath(AutomationLocators.CATEGORY_DROPDOWN)));
			category.selectByVisibleText("Orders");
//			category = new Select(webDB.driver.findElement(By.xpath(AutomationLocators.QUICKREPLYDROPDOWN)));
//			category.selectByVisibleText("Quick Reply");
			webDB.sendTextToAnElement(AutomationLocators.TEMPLATE_NAME_FIELD, templateName, ElementType.Xpath);
			webDB.sendTextToAnElement(AutomationLocators.TEMPLATE_DESC_FIELD, TemplateDesc, ElementType.Xpath);
			webDB.clickAnElement(AutomationLocators.SUBMIT, ElementType.Xpath);
			log.info("Template created successfully");
			wait.until(ExpectedConditions
					.visibilityOf(webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES))));
			element = webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES));
			js.executeScript("arguments[0].click();", element);
			// Delete a Template
			templateNames = webDB.driver.findElements(By.xpath(AutomationLocators.TEMPLATE_NAMES_COMMON));
			AutomationPage.deleteExtensionTemplate(templateName);
		}
		return flag;
	}

	public static boolean createAndEditTemplateName() throws InterruptedException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2000);
		templateName = createExtensiontemplate();
		element = webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES));
		js.executeScript("arguments[0].click();", element);
		flag = editExtensionTemplate(templateName);
		element = webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES));
		js.executeScript("arguments[0].click();", element);
		if (flag) {
			log.info("flag is true and deleteExtensionTemplate method executed ");
			flag = AutomationPage.deleteExtensionTemplate(templateName);
		}
		return flag;

	}

	public static boolean verifyAfterTemplateCreated(String templateName) {
//		String template_name = webDB.driver.findElement(By.xpath("//h2[contains(text(),'" + templateName + "')]"))
//				.getText();
//		String template_name = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATE_NAME))
//				.getText();
		String template_category = webDB.driver
				.findElement(By.xpath("//h2[contains(text(),'" + templateName + "')]/following::span[1]")).getText();
		System.out.println("Template category is " + template_category);
		if (template_category.equals("ORDER")) {
			log.info("Template category is verified");

//		String template_category = webDB.driver
//				.findElement(By
//						.xpath(AutomationLocators.TEMPLATE_CATEGORY))
//				.getText();
			// modified - locator
			String template_message = webDB.driver
					.findElement(By.xpath("//h2[text()='" + templateName + "']/following::div[3]//span")).getText();
			System.out.println("Template message is " + template_message);
			if (template_message.equals(TemplateDesc)) {
				log.info("Template message is verified");

//		String template_message = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATE_MESSAGE)).getText();
				// commented, bcs not available
//		boolean template_enable_button = webDB.driver.findElement(By.xpath("//h2[contains(text(),'"+templateName+"')]/..//input")).isEnabled();
//				WebElement template_enable_button = webDB.driver
//						.findElement(By.xpath("//h2[text()='" + templateName + "']/..//input"));
//				flag = true;
//				if (template_enable_button.isEnabled()) {
//					log.info("Template button is enabled");
//					flag = true;
//				}
			}
		}
		return flag;
	}

	public static boolean verifyEditTemplate() throws InterruptedException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2000);
		webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		webDB.clickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.waitForElement("//strong[contains(text(),'Show Templates: ')]", ElementType.Xpath);
		if (flag) {
			Thread.sleep(1000);
			TemplateName = createExtensiontemplate();
			Thread.sleep(800);

			editExtensionTemplate(TemplateName);
			Thread.sleep(800);

			MessagingPage.deleteTemplate(TemplateName);
		}
		return flag;
	}

	public static boolean verifyAnalyticsPageOtherMessageSections() throws InterruptedException {
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			System.out.println("BrowserStack");
		} else {
			flag = webDB.waitForElement(AnalyticsLocators.ANALYTICS_TAB, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				webDB.clickAnElement(AnalyticsLocators.ANALYTICS_TAB, ElementType.Xpath);
			}
		}
		Thread.sleep(1000);
		webDB.moveToElement(AnalyticsLocators.WIDGET_ANALYTICS_SECTION, ElementType.Xpath);
		flag = webDB.isElementDisplayed(AnalyticsLocators.WIDGET_ANALYTICS_SECTION, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(AnalyticsLocators.SPINWHEEL_ANALYTICS_SECTION, ElementType.Xpath);
			Thread.sleep(1000);
			flag = webDB.isElementDisplayed(AnalyticsLocators.SPINWHEEL_ANALYTICS_SECTION, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(AnalyticsLocators.AUTOMATED_MESSAGE_REPORT_SECTION, ElementType.Xpath);
				Thread.sleep(1000);
				flag = webDB.isElementDisplayed(AnalyticsLocators.AUTOMATED_MESSAGE_REPORT_SECTION, ElementType.Xpath);
				if (flag) {
					Thread.sleep(1000);
					flag = webDB.isElementDisplayed(AnalyticsLocators.ABANDONEDCARDTITLE, ElementType.Xpath);
					if (flag) {
						webDB.moveToElement(AnalyticsLocators.OPT_INS_SECTION, ElementType.Xpath);
						Thread.sleep(1000);
						flag = webDB.isElementDisplayed(AnalyticsLocators.OPT_INS_SECTION, ElementType.Xpath);
					}
				}
			}
		}
		return flag;

	}

	public static boolean verifyAbandedcartOthermsgGraphShows() throws InterruptedException {

		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			System.out.println("BrowserStack");
		} else {
			flag = webDB.waitForElement(AnalyticsLocators.ANALYTICS_TAB, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				webDB.clickAnElement(AnalyticsLocators.ANALYTICS_TAB, ElementType.Xpath);
			}
		}
		Thread.sleep(1000);
		webDB.moveToElement(AnalyticsLocators.ABANDONEDCARD_TOTALREAD, ElementType.Xpath);
		flag = webDB.isElementDisplayed(AnalyticsLocators.ABANDONEDCARD_TOTALREAD, ElementType.Xpath);
		if (flag) {
			flag = webDB.isElementDisplayed(AnalyticsLocators.ABANDONEDCARD_TOTALSENT, ElementType.Xpath);
			if (flag) {
				flag = webDB.isElementDisplayed(AnalyticsLocators.ABANDONEDCARD_TOTALRECEIVED, ElementType.Xpath);
				if (flag) {
					webDB.moveToElement(AnalyticsLocators.OTHER_MESSAGES_TOTALREAD, ElementType.Xpath);
					flag = webDB.isElementDisplayed(AnalyticsLocators.OTHER_MESSAGES_TOTALREAD, ElementType.Xpath);
					if (flag) {
						flag = webDB.isElementDisplayed(AnalyticsLocators.OTHER_MESSAGES_TOTALSENT, ElementType.Xpath);
						if (flag) {
							flag = webDB.isElementDisplayed(AnalyticsLocators.OTHER_MESSAGES_TOTALRECEIVED,
									ElementType.Xpath);
						}
					}
				}
			}
		}
		return flag;

	}

	public static boolean verifyAbandonedCurrentlyDisableEnable()
			throws InterruptedException, FileNotFoundException, IOException {
		flag = webDB.waitForElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
			Thread.sleep(2000);
			webDB.clickAnElement(AutomationLocators.ABCART_ENABLE_DISABLE_ICON, ElementType.Xpath);
			Thread.sleep(2000);
			flag = webDB.clickAnElement(AutomationLocators.SETTINGS_ABCART_TEMPLATE, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				webDB.javaScriptClickAnElement(AutomationLocators.CANCEL_BTN, ElementType.Xpath);
				Thread.sleep(2000);
				webDB.clickAnElement(AutomationLocators.ABCART_ENABLE_DISABLE_ICON, ElementType.Xpath);
			} else {
				System.out.println("Abdanted cart is disabled");
			}
			flag = webDB.waitForElement(AnalyticsLocators.ANALYTICS_TAB, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				webDB.clickAnElement(AnalyticsLocators.ANALYTICS_TAB, ElementType.Xpath);
				Thread.sleep(1000);
//				webDB.moveToElement(AnalyticsLocators.OTHER_MESSAGES_SECTION, ElementType.Xpath); // - only in shopify
//				flag = webDB.isElementDisplayed(AnalyticsLocators.CURRENTLYDISABLED, ElementType.Xpath);//
//				if (flag) {
//					webDB.clickAnElement(AnalyticsLocators.CURRENTLYDISABLEDCLICKHERE, ElementType.Xpath);//
				flag = webDB.waitForElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
					flag = webDB.clickAnElement(AutomationLocators.SETTINGS_ABCART_TEMPLATE, ElementType.Xpath);
					if (!flag) {
						Thread.sleep(2000);
						webDB.clickAnElement(AutomationLocators.ABCART_ENABLE_DISABLE_ICON, ElementType.Xpath);
						flag = true;
					}
				}
			}
		}
//		}
		return flag;
	}

	public static boolean checkOptinDetailsInOptinList() throws Exception {
		SimpleDateFormat simpleformat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");
		simpleformat = new SimpleDateFormat("MMMM d");
		String datevalue = simpleformat.format(new Date());
		flag = false;
		parentWindow = webDB.driver.getWindowHandle();
		WidgetPage.personalWidgetPageToStoreNavigation();
		WixStore.addItemAndCheckout();
		flag = webDB.isElementDisplayed(WixStoreLocators.THANK_YOU, ElementType.Xpath);
		if (flag) {
			text = webDB.getText(WixStoreLocators.THANK_YOU, ElementType.Xpath);
			log.info("Thank you page text is " + text);
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
		}
		return flag;
	}

	public static boolean verifyDeleteCustomTemplateName() throws InterruptedException {
		element = webDB.driver.findElement(By.xpath("(//div[@class='run-campaign-button'])[1]//button"));

		if (element.isDisplayed()) {
			Thread.sleep(5500);
			element.click();
			flag = webDB.waitForElement(AutomationLocators.DELETECONFIRMATIONBTN, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				flag = webDB.javaScriptClickAnElement(AutomationLocators.DELETECONFIRMATIONBTN, ElementType.Xpath);
				Thread.sleep(2000);
				if (flag) {
					System.out.println("Created Template is deleted");
				}
			}
		}
		return flag;
	}

	public static boolean verifyCrossSellUserSettingsCheck() throws InterruptedException {
		flag = webDB.waitForElement(AutomationLocators.AUTOMATEDPROMOTIONAL_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AutomationLocators.AUTOMATEDPROMOTIONAL_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(AutomationLocators.CROSSSELL_ENABLE_CHECK, ElementType.Xpath);
			if (flag) {
				flag = webDB.IsEnabled(AutomationLocators.CROSSSELL_ENABLE_CHECK, ElementType.Xpath);
				if (!flag) {
					webDB.clickAnElement(AutomationLocators.CROSSSELL_TOGGLE, ElementType.Xpath);
					flag = webDB.waitForElement(AutomationLocators.TEMPLATES_TOAST_MSG, ElementType.Xpath);
				}
				flag = webDB.waitForElement(AutomationLocators.SETTINGS_ABCART_TEMPLATE, ElementType.Xpath);
				if (flag) {
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.SETTINGS_ABCART_TEMPLATE, ElementType.Xpath);
					Thread.sleep(2000);
					flag = webDB.waitForElement(AutomationLocators.SETTINGSPOPUP, ElementType.CssSelector);
					if (flag) {
						webDB.scrollToAnElement(AutomationLocators.USER_SETTINGS_LEARNMORE, ElementType.Xpath);
						Thread.sleep(2000);
						// modified - below flag and if
						flag = webDB.IsEnabled(WixLocators.USERCHECK_INPUT, ElementType.Xpath);
						if (!flag) {
							System.out.println("Checkbox is unchecked");
							webDB.clickAnElement(WixLocators.USERCHECKBOX, ElementType.Xpath);
						}
						System.out.println("Checkbox is checked");
						flag = webDB.waitForElement(AutomationLocators.SAVE, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(AutomationLocators.SAVE, ElementType.Xpath);
							Thread.sleep(2000);
							flag = webDB.waitForElement(AutomationLocators.SETTINGS_ABCART_TEMPLATE, ElementType.Xpath);
							Thread.sleep(2000);
							if (flag) {
								webDB.clickAnElement(AutomationLocators.SETTINGS_ABCART_TEMPLATE, ElementType.Xpath);
								flag = webDB.waitForElement(AutomationLocators.SETTINGSPOPUP, ElementType.CssSelector);
								if (flag) {
									webDB.scrollToAnElement(AutomationLocators.USER_SETTINGS_LEARNMORE,
											ElementType.Xpath);
									Thread.sleep(2000);
									// modified - below locator
									flag = webDB.IsEnabled(WixLocators.USERCHECK_INPUT, ElementType.Xpath);
									Thread.sleep(2000);
									if (flag) {
										System.out.println("Enabled");
										webDB.clickAnElement(AutomationLocators.SETTINGSCLOSEICON,
												ElementType.CssSelector);
										webDB.moveToElement(AutomationLocators.CROSSSELL_SETTINGS, ElementType.Xpath);
										flag = webDB.waitForElement(AutomationLocators.CROSSSELL_SETTINGS,
												ElementType.Xpath);
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

	public static boolean verifyCrossSellUserSettingsUncheck()
			throws InterruptedException, FileNotFoundException, IOException {
		flag = webDB.waitForElement(AutomationLocators.AUTOMATEDPROMOTIONAL_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AutomationLocators.AUTOMATEDPROMOTIONAL_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(AutomationLocators.CROSSSELL_ENABLE_CHECK, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				flag = webDB.IsEnabled(AutomationLocators.CROSSSELL_ENABLE_CHECK, ElementType.Xpath);
				if (!flag) {
					webDB.clickAnElement(AutomationLocators.CROSSSELL_TOGGLE, ElementType.Xpath);
					flag = webDB.waitForElement(AutomationLocators.TEMPLATES_TOAST_MSG, ElementType.Xpath);
				}
				flag = webDB.waitForElement(AutomationLocators.SETTINGS_ABCART_TEMPLATE, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(AutomationLocators.SETTINGS_ABCART_TEMPLATE, ElementType.Xpath);
					flag = webDB.waitForElement(AutomationLocators.SETTINGSPOPUP, ElementType.CssSelector);
					if (flag) {
						webDB.scrollToAnElement(AutomationLocators.USER_SETTINGS_LEARNMORE, ElementType.Xpath);
						Thread.sleep(2000);
						// modified
						flag = webDB.IsEnabled(WixLocators.USERCHECK_INPUT, ElementType.Xpath);
						Thread.sleep(2000);
						if (flag) {
							System.out.println("Checkbox is checked");
							webDB.clickAnElement(WixLocators.USERCHECKBOX, ElementType.Xpath);
							flag = webDB.IsEnabled(WixLocators.USERCHECK_INPUT, ElementType.Xpath);
							if (!flag) {
								System.out.println("Checkbox is now Unchecked");
							}
						}
						flag = webDB.waitForElement(AutomationLocators.SAVE, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(AutomationLocators.SAVE, ElementType.Xpath);
							flag = webDB.waitForElement(AutomationLocators.SETTINGS_ABCART_TEMPLATE, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(AutomationLocators.SETTINGS_ABCART_TEMPLATE, ElementType.Xpath);
								flag = webDB.waitForElement(AutomationLocators.SETTINGSPOPUP, ElementType.CssSelector);
								if (flag) {
									webDB.scrollToAnElement(AutomationLocators.USER_SETTINGS_LEARNMORE,
											ElementType.Xpath);
									Thread.sleep(2000);
									// modified
									flag = webDB.IsEnabled(WixLocators.USERCHECK_INPUT, ElementType.Xpath);
									Thread.sleep(2000);
									if (!flag) {
										System.out.println("Not Enabled");
										webDB.clickAnElement(AutomationLocators.SETTINGSCLOSEICON,
												ElementType.CssSelector);
										webDB.scrollToAnElement(AutomationLocators.CROSSSELL_SETTINGS,
												ElementType.Xpath);
										flag = webDB.waitForElement(AutomationLocators.CROSSSELL_SETTINGS,
												ElementType.Xpath);
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

//	public static boolean createMultiCampaignOptin() throws InterruptedException {
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CampaignsLocators.MARKETCAMPAIGNS_TAB)));
//		webDB.clickAnElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
//		flag = webDB.waitForElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
//		if (flag) {
//			Thread.sleep(7000);
//			//webDB.javaScriptClickAnElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
//			webDB.clickAnElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
//			flag = webDB.waitForElement(CampaignsLocators.CAMPAIGNSNAME_INPUT, ElementType.Xpath);
//			if (flag) {
//				webDB.clearText(CampaignsLocators.CAMPAIGNSNAME_INPUT);
//				Thread.sleep(2000);
//				webDB.sendTextToAnElement(CampaignsLocators.CAMPAIGNSNAME_INPUT,
//						CampaignsLocators.CAMPAIGNSINPUTVALUE + random, ElementType.Xpath);
//				webDB.clickAnElement(CampaignsLocators.SELECTTEMPLATE, ElementType.Xpath);
//				Thread.sleep(4000);
//				flag = webDB.waitForElement(WixLocators.ALL_TEMPLATES, ElementType.Xpath);
//				if (flag) {
//					webDB.clickAnElement(WixLocators.ALL_TEMPLATES, ElementType.Xpath);
//					// webDB.moveToElement("//span[text()='" + CampaignsLocators.CHOOSETEMPLATENAME
//					// + "']", ElementType.Xpath);
//					// Thread.sleep(1000);
//					webDB.scrollToAnElement(WixLocators.CHOOSETEMPLATEBTN, ElementType.Xpath);
//					Thread.sleep(1000);
//					flag = webDB.waitForElement(WixLocators.CHOOSETEMPLATENAME, ElementType.Xpath);
//					if (flag) {
////					webDB.clickAnElement("//span[text()='" + CampaignsLocators.CHOOSETEMPLATENAME + "']",
////							ElementType.Xpath);
////					Thread.sleep(1000);
//						webDB.clickAnElement(WixLocators.CHOOSETEMPLATEBTN, ElementType.Xpath);
//						flag = webDB.waitForElement(CampaignsLocators.FROMFILE, ElementType.CssSelector);
//						if (flag) {
////							propertyFilePath = (System.getProperty("user.dir") + File.separator + "payload"
////									+ File.separator + CampaignsLocators.MULTIPLECAMPAIGNSFILENAMEVALUE);
//							webDB.clickAnElement(CampaignsLocators.FROMFILE, ElementType.CssSelector);
//							CustomersPage.deleteexsistfile();
//							flag = webDB.waitForElement(CampaignsLocators.DOWNLOADEXCELBUTTON, ElementType.Xpath);
//							if (flag) {
//								webDB.clickAnElement(CampaignsLocators.DOWNLOADEXCELBUTTON, ElementType.Xpath);
//								Thread.sleep(10000);
//								String inputfilepath = CustomersPage.writeDataInSheetXLFile();
//								Thread.sleep(5000);
//								webDB.sendTextToAnElement(CampaignsLocators.RECEIPTENTSNAME, inputfilepath,
//										ElementType.CssSelector);
////								webDB.sendTextToAnElement(CampaignsLocators.RECEIPTENTSNAME, propertyFilePath,
////										ElementType.CssSelector);
//								Thread.sleep(2000);
//								webDB.moveToElement(CampaignsLocators.SUBMITBTN, ElementType.Xpath);
//								flag = webDB.driver.findElement(By.xpath(CampaignsLocators.CONFIRMATION_CHECKBOX))
//										.isSelected();
//								System.out.println(flag);
//								if (!flag) {
//									Thread.sleep(2000);
//									webDB.clickAnElement(CampaignsLocators.CONFIRMATION_CHECKBOX_TXT,
//											ElementType.Xpath);
//								}
//								System.out.println("enabled");
//								Thread.sleep(4000);
//								flag = webDB.waitForElement(CampaignsLocators.SUBMITBTN, ElementType.Xpath);
//								if (flag) {
//									Thread.sleep(3000);
//									webDB.clickAnElement(CampaignsLocators.SUBMITBTN, ElementType.Xpath);
//									Thread.sleep(7000);
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//		return flag;
//
//	}

	public static boolean verifyDeselectCheckboxes() throws Exception {
		flag = SettingsPage.deSelectAllOptinOptions();
		if (flag) {
			parentWindow = webDB.driver.getWindowHandle();
			webDB.clickAnElement(WidgetLocators.CHATWIDGET_SETTING, ElementType.Xpath);
			WidgetPage.personalWidgetPageToStoreNavigation();
			log.info("personalWidgetPageToStoreNavigation method executed");
			JavascriptExecutor js = (JavascriptExecutor) webDB.driver;
			js.executeScript("window.localStorage.clear();");
			webDB.driver.navigate().refresh();
			flag = WixStore.verifyCartpageCheckbox();
			if (flag = false) {
				WixStore.verifyCheckoutCheckbox();
				SettingsPage.selectAllOptinOptions();
			}
		}
		flag = true;
		return flag;
	}

	public static boolean verifyTagsinAB_CART() throws InterruptedException {
		// modified - changed locator
		flag = webDB.waitForElement(WixLocators.SETTINGS_BTN, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WixLocators.SETTINGS_BTN, ElementType.Xpath);
			log.info("Settings SubTab is displayed");
			String Tagname = MessagingPage.createCartTags();
			flag = webDB.waitForElement(MessagingLocators.ABANDONED_CART_SUBTAB, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(MessagingLocators.ABANDONED_CART_SUBTAB, ElementType.Xpath);
				flag = webDB.waitForElement(MessagingLocators.ADDTAG, ElementType.Xpath);
				if (flag) {
					flag = webDB.waitForElement(MessagingLocators.TAG_DROPDOWN, ElementType.CssSelector);
					if (flag) {
						webDB.clickAnElement(MessagingLocators.TAG_DROPDOWN, ElementType.CssSelector);
						flag = webDB.waitForElement(MessagingLocators.CLEAR, ElementType.Xpath);
						if (flag) {
							List<WebElement> tags = webDB.driver.findElements(By.xpath(MessagingLocators.CREATEDTAGS));
							for (int i = 0; i < tags.size(); i++) {
								text = tags.get(i).getText();
								// System.out.println(text);
								if (text.contains(Tagname)) {
									log.info("Verified All tags are visible in ABcart");
									// modified below locator
									flag = webDB.waitForElement(WixLocators.SETTINGS_BTN, ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(WixLocators.SETTINGS_BTN, ElementType.Xpath);
										MessagingPage.deleteTags(Tagname);
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

	public static boolean verifySendmessageinAB_CART() throws InterruptedException, FileNotFoundException, IOException {
		flag = webDB.waitForElement(MessagingLocators.NEW_CRMTOOL_INPUT, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			flag = webDB.IsEnabled(MessagingLocators.NEW_CRMTOOL_INPUT, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				webDB.clickAnElement(MessagingLocators.NEW_CRMTOOL_TOGGLE, ElementType.Xpath);
				webDB.driver.navigate().refresh();
				log.info("New CRM tool disabled");
			}

			String Templatename = createABcartTemplate();
			flag = webDB.waitForElement(MessagingLocators.ABANDONED_CART_SUBTAB, ElementType.Xpath);
			if (flag) {
				log.info("AB Cart subtab is visible");
				webDB.clickAnElement(MessagingLocators.ABANDONED_CART_SUBTAB, ElementType.Xpath);
				flag = webDB.waitForElement(MessagingLocators.NAME_FILTER, ElementType.Xpath);
				if (flag) {
					log.info("Name filter is visible");
					webDB.clickAnElement(MessagingLocators.NAME_FILTER, ElementType.Xpath);
					flag = webDB.waitForElement(MessagingLocators.CLEAR, ElementType.Xpath);
					if (flag) {
						log.info("Clear button is visible");
						webDB.clearText(MessagingLocators.NAME_FILTER_TEXTBOX);
						// modified - added username in wix locators
						webDB.sendTextToAnElement(MessagingLocators.NAME_FILTER_TEXTBOX, WixLocators.USERNAME,
								ElementType.Xpath);
						Thread.sleep(1500);
						log.info("Name has been sent to text box");
						// modified - commented below and added click search
//					webDB.pressEnterKey();
						webDB.clickAnElement(MessagingLocators.SEARCH_NAME, ElementType.Xpath);
						log.info("Search Name is clicked");
						webDB.scrollToAnElement(MessagingLocators.SENDMESSAGE, ElementType.Xpath);
						flag = webDB.verifyElementIsDisplayed(MessagingLocators.ADDTAG, ElementType.Xpath);
						if (flag) {
							log.info("Add tag is visible");
							flag = webDB.waitForElement(MessagingLocators.SENDMESSAGE, ElementType.Xpath);
							if (flag) {
								log.info("Send message is visible");
								webDB.clickAnElement(MessagingLocators.SENDMESSAGE, ElementType.Xpath);
							}
						} else {
							log.info("Send message 2 is visible");
							webDB.clickAnElement(MessagingLocators.SENDMESSAGE1, ElementType.Xpath);
						}
						flag = webDB.waitForElement(MessagingLocators.POUUP, ElementType.CssSelector);
						if (flag) {
							webDB.clickAnElement(MessagingLocators.SELECTVALUE, ElementType.Xpath);
							webDB.selectDropDownOptionsByText(MessagingLocators.SELECTVALUE, Templatename,
									ElementType.Xpath);
							Thread.sleep(2000);
							flag = webDB.driver.findElement(By.xpath(MessagingLocators.SENDMESSAGE_BTN)).isEnabled();
							if (flag) {
								log.info("Verified send message displayed all cart template in ABcart");
								webDB.clickAnElement(MessagingLocators.CLOSEPOPUP, ElementType.Xpath);
								webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
								webDB.clickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
								MessagingPage.deleteTemplate(Templatename);
							}
						}
					}
				}
			}
		}
		return flag;
	}

	public static String createABcartTemplate() throws InterruptedException {
		webDB.driver.navigate().refresh();
		Thread.sleep(2500);
		webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		webDB.clickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.waitForElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
			Thread.sleep(1000);
			TemplateName = "AutoTemplate" + Math.random();
			webDB.waitForElement(MessagingLocators.TEMPLATE_NAME_FIELD, ElementType.Xpath);
			webDB.sendTextToAnElement(MessagingLocators.TEMPLATE_NAME_FIELD, TemplateName, ElementType.Xpath);

			webDB.waitForElement(MessagingLocators.TEMPLATE_MSG_FIELD, ElementType.Xpath);
			webDB.sendTextToAnElement(MessagingLocators.TEMPLATE_MSG_FIELD, TemplateMsg, ElementType.Xpath);
//			webDB.waitForElement(MessagingLocators.DISCOUNT_VALUE, ElementType.Xpath);
//			webDB.sendTextToAnElement(MessagingLocators.DISCOUNT_VALUE, discountValue, ElementType.Xpath);
			category = new Select(webDB.driver.findElement(By.xpath(MessagingLocators.CATEGORY_DROPDOWN)));
			category.selectByVisibleText("Abandoned Cart");
			log.info("Abandoned Cart");
			Thread.sleep(1000);
			// Click on save
			flag = webDB.waitForElement(MessagingLocators.SAVE_BTN, ElementType.Xpath);
			webDB.clickAnElement(MessagingLocators.SAVE_BTN, ElementType.Xpath);
			Thread.sleep(500);
			// Success Popup
			text = webDB.driver.findElement(By.xpath(MessagingLocators.SUCCESS)).getText();
			Thread.sleep(500);
			log.info(text);
			if (text.contains(MessagingLocators.TEMPLATE_CREATED)) {
				log.info("ABcart template is created");
			}
		}
		return TemplateName;
	}

	public static boolean verifySendmessageinOrderCRM()
			throws InterruptedException, FileNotFoundException, IOException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2500);
		String Templatename = createOrderCRMTemplate();
		log.info("Created template name is " + Templatename);
		flag = webDB.waitForElement(MessagingLocators.CRM_CART_SUBTAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MessagingLocators.CRM_CART_SUBTAB, ElementType.Xpath);
			flag = webDB.waitForElement(MessagingLocators.NAME_FILTER, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(MessagingLocators.NAME_FILTER, ElementType.Xpath);
				flag = webDB.waitForElement(MessagingLocators.CLEAR, ElementType.Xpath);
				if (flag) {
					webDB.clearText(MessagingLocators.NAME_FILTER_TEXTBOX);
					webDB.sendTextToAnElement(MessagingLocators.NAME_FILTER_TEXTBOX, MessagingLocators.USERNAME,
							ElementType.Xpath);
					Thread.sleep(1500);
					// webDB.pressEnterKey();
					webDB.waitForElement(MessageLogsLocators.SEARCH_BTN, ElementType.Xpath);
					webDB.clickAnElement(MessageLogsLocators.SEARCH_BTN, ElementType.Xpath);

					webDB.waitForElement(MessagingLocators.NAME_FILTER, ElementType.Xpath);
					webDB.clickAnElement(MessagingLocators.NAME_FILTER, ElementType.Xpath);

					// webDB.scrollToAnElement(MessagingLocators.SENDMESSAGE, ElementType.Xpath);
					// flag = webDB.verifyElementIsDisplayed(MessagingLocators.ADDTAG,
					// ElementType.Xpath);
					// if (flag) {
					flag = webDB.waitForElement(MessagingLocators.SENDMESSAGE, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(MessagingLocators.SENDMESSAGE, ElementType.Xpath);
					}
					// }
					else {
						webDB.clickAnElement(MessagingLocators.SENDMESSAGE1, ElementType.Xpath);
					}
					flag = webDB.waitForElement(MessagingLocators.POUUP, ElementType.CssSelector);
					if (flag) {
						webDB.clickAnElement(MessagingLocators.SELECTVALUE, ElementType.Xpath);
						webDB.selectDropDownOptionsByText(MessagingLocators.SELECTVALUE, Templatename,
								ElementType.Xpath);
						Thread.sleep(2000);
						flag = webDB.driver.findElement(By.xpath(MessagingLocators.SENDMESSAGE_BTN)).isEnabled();
						if (flag) {
							log.info("Order CRM template is displayed on clicking send message");
							webDB.clickAnElement(MessagingLocators.CLOSEPOPUP, ElementType.Xpath);
							webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
							webDB.clickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
							MessagingPage.deleteTemplate(Templatename);
						}
					}
				}
			}
		}
		return flag;

	}

	public static String createOrderCRMTemplate() throws InterruptedException {
		webDB.driver.navigate().refresh();
		Thread.sleep(2500);
		webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		webDB.clickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		Thread.sleep(3000);
		flag = webDB.waitForElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
			Thread.sleep(1000);
			// Enter name
			TemplateName = "AutoTemplate" + Math.random();
			webDB.waitForElement(MessagingLocators.TEMPLATE_NAME_FIELD, ElementType.Xpath);
			webDB.sendTextToAnElement(MessagingLocators.TEMPLATE_NAME_FIELD, TemplateName, ElementType.Xpath);

			// Enter Message
			webDB.waitForElement(MessagingLocators.TEMPLATE_MSG_FIELD, ElementType.Xpath);
			webDB.sendTextToAnElement(MessagingLocators.TEMPLATE_MSG_FIELD, TemplateMsg, ElementType.Xpath);

//			// Enter Discount value
//			webDB.waitForElement(MessagingLocators.DISCOUNT_VALUE, ElementType.Xpath);
//			webDB.sendTextToAnElement(MessagingLocators.DISCOUNT_VALUE, discountValue, ElementType.Xpath);

			// Select Category
			category = new Select(webDB.driver.findElement(By.xpath(MessagingLocators.CATEGORY_DROPDOWN)));
			category.selectByVisibleText("Orders");
			log.info("OrderCRM ");
			Thread.sleep(1000);
			// Click on save
			flag = webDB.waitForElement(MessagingLocators.SAVE_BTN, ElementType.Xpath);
			webDB.clickAnElement(MessagingLocators.SAVE_BTN, ElementType.Xpath);
			Thread.sleep(500);
			// Success Pop-up
			text = webDB.driver.findElement(By.xpath(MessagingLocators.SUCCESS)).getText();
			Thread.sleep(500);
			log.info(text);
			log.info("OrderCRM template is created");
		}
		return TemplateName;
	}

	public static boolean verifyABcarttemplate() throws InterruptedException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2500);
		ABcarttemplatename = createABcartTemplate();
		webDB.driver.navigate().refresh();
		Thread.sleep(2500);
		webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		webDB.clickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		flag = webDB.waitForElement(MessagingLocators.ABCART_TEMPLATE_BTN, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MessagingLocators.ABCART_TEMPLATE_BTN, ElementType.Xpath);
			templateNames = webDB.driver.findElements(By.xpath(MessagingLocators.TEMPLATE_NAMES_COMMON));
			for (int i = 0; i < templateNames.size(); i++) {
				text = templateNames.get(i).getText();
				System.out.println(text);
				if (text.contains(ABcarttemplatename)) {
					log.info("Template Name : " + ABcarttemplatename + " is diplayed");
					log.info("Verified created ABcart templates are shown in ABCart section");
				}
			}
		}

		return flag;

	}

	public static boolean verifyOrderCRMtemplate() throws InterruptedException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2500);
		OrderCRMtemplatename = createOrderCRMTemplate();
		webDB.driver.navigate().refresh();
		Thread.sleep(2500);
		webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		webDB.clickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		flag = webDB.waitForElement(MessagingLocators.ORDERCRM_TEMPLATE_BTN, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MessagingLocators.ORDERCRM_TEMPLATE_BTN, ElementType.Xpath);
			templateNames = webDB.driver.findElements(By.xpath(MessagingLocators.TEMPLATE_NAMES_COMMON));
			for (int i = 0; i < templateNames.size(); i++) {
				text = templateNames.get(i).getText();
				System.out.println(text);
				if (text.contains(OrderCRMtemplatename)) {
					log.info("Template Name : " + OrderCRMtemplatename + " is diplayed");
					log.info("Verified created OrderCRM templates are shown in OrderCRM section");
				}
			}
		}

		return flag;

	}

	public static boolean verifyThankyouPageOptin() throws Exception {
		String phoneNumber = "86";
		String phone = phoneNumber += RandomStringUtils.randomNumeric(8);
		log.info(phone);
		flag = webDB.clickAnElement(CustomersLocators.WIDGET, ElementType.Xpath);
		if (flag) {
			flag = webDB.clickAnElement(CustomersLocators.STORELINK, ElementType.Xpath);
		}
		parentWindow = webDB.driver.getWindowHandle();
		webDB.switchToChildWindow();
		// modified - commented below
//		ShopifyStore.shopifyStorePassEnter();
//		ShopifyStore.addItemAndCheckout();
		// modified - added below
		WixStore.addItemAndCheckout();
		// modified - commented below
//		String phone2 = ShopifyStore.enterShippingDetailsWithExistingno(phone);
//		System.out.println(phone2);
//		Thread.sleep(1000);
//		ShopifyStore.enterCardDetails();
//		webDB.moveToElement(ShopifyStoreLocators.PAYNOW, ElementType.Xpath);
//		webDB.clickAnElement(ShopifyStoreLocators.PAYNOW, ElementType.Xpath);
//		wait.until(
//				ExpectedConditions.visibilityOfElementLocated(By.xpath(ShopifyStoreLocators.ORDER_CONFIRMED_MESSAGE)));
//		log.info("Order confirmation message is displayed");
//		webDB.driver.navigate().refresh();
//		flag = webDB.isElementDisplayed(CustomersLocators.THANK_YOU_OPTIN, ElementType.Xpath);
//		log.info("Flag For Thank you optin found " + flag);
//		if (flag) {
//			log.info("Thank yoy page optin is passed");
//		}
		webDB.driver.close();
		log.info("closed the child window");
		webDB.driver.switchTo().window(parentWindow);
		log.info("switched to parent window");
		return flag;
	}

	public static boolean verifySendMessage() throws InterruptedException, FileNotFoundException, IOException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2000);
		webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES)).click();
		log.info("Abcart is displayed");
		// modified - added below
		TemplateName = createABcartTemplate();
		Thread.sleep(2000);
		js.executeScript("scroll(0, -250);");

//		webDB.driver.navigate().refresh();
		element = webDB.driver.findElement(By.xpath(MessagingLocators.ABANDONED_CART_SUBTAB));
		element.click();
		// modified
//		flag = webDB.driver.findElement(By.xpath("//h1[contains(text(),'Messaging')]")).isDisplayed();
		flag = webDB.driver.findElement(By.xpath("//h1[contains(text(),'CRM Tool')]")).isDisplayed();
		if (flag) {
			log.info("Abandoned Cart displayed");
		}

		flag = webDB.driver.findElement(By.xpath(MessagingLocators.NAME_FILTER)).isDisplayed();
		if (flag) {
			log.info("Name filter displayed");
		}
		Dimension d = new Dimension(1920, 1080);
		webDB.driver.manage().window().setSize(d);
		flag = webDB.clickAnElement(MessagingLocators.NAME_FILTER, ElementType.Xpath);

		// modified
		Name = "AutoUser 05277";

		log.info("Name send " + Name);
		webDB.driver.findElement(By.xpath(MessagingLocators.NAME_FILTER_TEXTBOX))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		flag = webDB.sendTextToAnElement(MessagingLocators.NAME_FILTER_TEXTBOX, Name, ElementType.Xpath);
		// MODIFIED - ADDED SEARCH NAME
		webDB.waitForElement(MessagingLocators.SEARCH_NAME, ElementType.Xpath);
		webDB.clickAnElement(MessagingLocators.SEARCH_NAME, ElementType.Xpath);

		Thread.sleep(800);

		buttons = webDB.driver.findElements(By.xpath(MessagingLocators.SEND_MESSAGE_GENERIC));
		buttons.get(0).click();

		// Select Category
		// modified - added sleep and click
		Thread.sleep(2000);
		webDB.clickAnElement(MessagingLocators.CATEGORY_DROPDOWN, ElementType.Xpath);
		category = new Select(webDB.driver.findElement(By.xpath(MessagingLocators.CATEGORY_DROPDOWN)));
		category.selectByVisibleText(TemplateName);

		// Click on send button
		flag = webDB.clickAnElement(MessagingLocators.SEND_MESSAGE_SEND_BTN, ElementType.Xpath);
		if (flag) {
			log.info("Send Button clicked");
		}

		Thread.sleep(2000);
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String SendTemplateWA_Page = prop.getProperty("SendTemplateWA_Page");
		windows = webDB.driver.getWindowHandles();
		it = windows.iterator();
		parentWindow = it.next();
		childWindow = it.next();
		webDB.driver.switchTo().window(childWindow);
		url = webDB.driver.switchTo().window(childWindow).getCurrentUrl();
		log.info(url);
		Thread.sleep(2000);
		if (url.equals(SendTemplateWA_Page) || url.equals("https://web.whatsapp.com/")) {
			flag = true;
			log.info("WhatsApp page accessed successfully");
			Thread.sleep(800);
		} else {
			flag = false;
		}
		webDB.driver.close();
		log.info("closed the child window");
		webDB.driver.switchTo().window(parentWindow);
		log.info("navigated to parent window");
		js.executeScript("scroll(0, -250);");
		// modified
//		flag = webDB.driver.findElement(By.xpath("//h1[contains(text(),'Messaging')]")).isDisplayed();
		flag = webDB.driver.findElement(By.xpath("//h1[contains(text(),'CRM Tool')]")).isDisplayed();
		if (flag) {
			log.info("Abandoned Cart displayed");
		}

		webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES)).click();
		log.info("Extension Template is displayed");

//		webDB.waitForElement("//h2[contains(text(),'WhatsApp Configuration')]", ElementType.Xpath);
		// modified - added from messaging page
		MessagingPage.deleteTemplate(TemplateName);
//		flag = webDB.waitForElement(ManualMessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
//		webDB.driver.findElement(By.xpath(ManualMessagingLocators.CLOSE_VIEW_TAG_MODAL)).click();

		return flag;
	}

	public static boolean verifySendTemplateRedirectWApage()
			throws InterruptedException, FileNotFoundException, IOException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2000);
		webDB.waitForElement(MessagingLocators.SETTINGS_BTN, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MessagingLocators.SETTINGS_BTN)).click();
		log.info("Settings SubTab is displayed");

		webDB.waitForElement("//h2[contains(text(),'WhatsApp Configuration')]", ElementType.Xpath);

		buttons = webDB.driver.findElements(By.xpath(MessagingLocators.RADIO_GENERIC));
		buttons.get(0).click();
		webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
		Thread.sleep(800);

		Thread.sleep(4000);
		webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES)).click();

//		// modified
//		WixFunctions.createExtensiontemplate();
		TemplateName = createABcartTemplate();

		Thread.sleep(2000);
		js.executeScript("scroll(0, -250);");

		element = webDB.driver.findElement(By.xpath(MessagingLocators.ABANDONED_CART_SUBTAB));
		element.click();

		// modified
		flag = webDB.driver.findElement(By.xpath("//h1[contains(text(),'CRM Tool')]")).isDisplayed();
		if (flag) {
			log.info("Abandoned Cart displayed");
		}

		flag = webDB.driver.findElement(By.xpath(MessagingLocators.NAME_FILTER)).isDisplayed();
		if (flag) {
			log.info("Name filter displayed");
		}

		Dimension d = new Dimension(1920, 1080);
		webDB.driver.manage().window().setSize(d);
		flag = webDB.clickAnElement(MessagingLocators.NAME_FILTER, ElementType.Xpath);

		// modified
		Name = "AutoUser 05277";
		log.info("Name send " + Name);
		webDB.driver.findElement(By.xpath(MessagingLocators.NAME_FILTER_TEXTBOX))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		flag = webDB.sendTextToAnElement(MessagingLocators.NAME_FILTER_TEXTBOX, Name, ElementType.Xpath);

		Thread.sleep(800);

		buttons = webDB.driver.findElements(By.xpath(MessagingLocators.SEND_MESSAGE_GENERIC));
		buttons.get(0).click();

		// Select Category
		// modified - added wait and click
		Thread.sleep(2000);
		webDB.clickAnElement(MessagingLocators.CATEGORY_DROPDOWN, ElementType.Xpath);
		category = new Select(webDB.driver.findElement(By.xpath(MessagingLocators.CATEGORY_DROPDOWN)));
		category.selectByVisibleText(TemplateName);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MessagingLocators.SEND_MESSAGE_SEND_BTN)));
		// Click on send button
		flag = webDB.clickAnElement(MessagingLocators.SEND_MESSAGE_SEND_BTN, ElementType.Xpath);
		if (flag) {
			log.info("Send Button clicked");
		}

		Thread.sleep(2000);
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String SendTemplateWA_Page = prop.getProperty("SendTemplateWA_Page");
		windows = webDB.driver.getWindowHandles();
		it = windows.iterator();
		parentWindow = it.next();
		childWindow = it.next();
		webDB.driver.switchTo().window(childWindow);
		url = webDB.driver.switchTo().window(childWindow).getCurrentUrl();
		log.info(url);
		Thread.sleep(2000);
		if (url.equals(SendTemplateWA_Page) || url.equals("https://web.whatsapp.com/")) {
			flag = true;
			log.info("WhatsApp Web page accessed successfully");
			Thread.sleep(800);
		} else {
			flag = false;
		}

		webDB.driver.close();
		log.info("closed the child window");
		webDB.driver.switchTo().window(parentWindow);
		log.info("navigated to parent window");
		js.executeScript("scroll(0, -250);");
		flag = webDB.driver.findElement(By.xpath("//h1[contains(text(),'CRM Tool')]")).isDisplayed();
		if (flag) {
			log.info("Abandoned Cart displayed");
		}

		Thread.sleep(4000);
		webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES)).click();
		log.info("Message Template is displayed");
		Thread.sleep(800);
		MessagingPage.deleteTemplate(TemplateName);
		Thread.sleep(800);
//		flag = webDB.waitForElement(ManualMessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
//		webDB.driver.findElement(By.xpath(ManualMessagingLocators.CLOSE_VIEW_TAG_MODAL)).click();
		return flag;
	}

	public static boolean verifySendTemplateRedirectWAapp()
			throws InterruptedException, FileNotFoundException, IOException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2000);
		webDB.waitForElement(MessagingLocators.SETTINGS_BTN, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MessagingLocators.SETTINGS_BTN)).click();
		log.info("Settings SubTab is displayed");

		webDB.waitForElement("//h2[contains(text(),'WhatsApp Configuration')]", ElementType.Xpath);

		buttons = webDB.driver.findElements(By.xpath(MessagingLocators.RADIO_GENERIC));
		buttons.get(1).click();
		webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
		Thread.sleep(800);

		Thread.sleep(4000);
		webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES)).click();

		TemplateName = createABcartTemplate();

		Thread.sleep(2000);
		js.executeScript("scroll(0, -250);");

		element = webDB.driver.findElement(By.xpath(MessagingLocators.ABANDONED_CART_SUBTAB));
		element.click();
		flag = webDB.driver.findElement(By.xpath("//h1[contains(text(),'CRM Tool')]")).isDisplayed();
		if (flag) {
			log.info("Abandoned Cart displayed");
		}

		flag = webDB.driver.findElement(By.xpath(MessagingLocators.NAME_FILTER)).isDisplayed();
		if (flag) {
			log.info("Name filter displayed");
		}

		Dimension d = new Dimension(1920, 1080);
		webDB.driver.manage().window().setSize(d);
		flag = webDB.clickAnElement(MessagingLocators.NAME_FILTER, ElementType.Xpath);

		Name = "AutoUser 05277";
		log.info("Name send " + Name);
		webDB.driver.findElement(By.xpath(MessagingLocators.NAME_FILTER_TEXTBOX))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		flag = webDB.sendTextToAnElement(MessagingLocators.NAME_FILTER_TEXTBOX, Name, ElementType.Xpath);

		Thread.sleep(800);

		buttons = webDB.driver.findElements(By.xpath(MessagingLocators.SEND_MESSAGE_GENERIC));
		buttons.get(0).click();

		// Select Category
		Thread.sleep(2000);
		webDB.clickAnElement(MessagingLocators.CATEGORY_DROPDOWN, ElementType.Xpath);
		category = new Select(webDB.driver.findElement(By.xpath(MessagingLocators.CATEGORY_DROPDOWN)));
		category.selectByVisibleText(TemplateName);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MessagingLocators.SEND_MESSAGE_SEND_BTN)));
		// Click on send button
		flag = webDB.clickAnElement(MessagingLocators.SEND_MESSAGE_SEND_BTN, ElementType.Xpath);
		if (flag) {
			log.info("Send Button clicked");
		}

		Thread.sleep(2000);
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String SendTemplateWA_APP_Page = prop.getProperty("SendTemplateWA_APP_Page");
		windows = webDB.driver.getWindowHandles();
		it = windows.iterator();
		parentWindow = it.next();
		childWindow = it.next();
		webDB.driver.switchTo().window(childWindow);
		url = webDB.driver.switchTo().window(childWindow).getCurrentUrl();
		log.info(url);
		Thread.sleep(2000);
		if (url.equals(SendTemplateWA_APP_Page) || url.equals("https://web.whatsapp.com/")) {
			flag = true;
			log.info("WhatsApp app page accessed");
			Thread.sleep(800);
		} else {
			flag = false;
		}

		webDB.driver.close();
		log.info("closed the child window");
		webDB.driver.switchTo().window(parentWindow);
		log.info("navigated to parent window");
		webDB.driver.switchTo().window(parentWindow);
		js.executeScript("scroll(0, -250);");
		flag = webDB.driver.findElement(By.xpath("//h1[contains(text(),'CRM Tool')]")).isDisplayed();
		if (flag) {
			log.info("Abandoned Cart displayed");
		}

		Thread.sleep(4000);
		webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES)).click();
		Thread.sleep(800);
		MessagingPage.deleteTemplate(TemplateName);
		Thread.sleep(800);
//		flag = webDB.waitForElement(ManualMessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
//		webDB.driver.findElement(By.xpath(ManualMessagingLocators.CLOSE_VIEW_TAG_MODAL)).click();
		return flag;
	}

	public static boolean verifyPlaceholderMessageTemplate() throws InterruptedException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2000);
		webDB.driver.navigate().refresh();
		Thread.sleep(5000);
		webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES)).click();
		log.info("Settings SubTab is displayed");

		flag = AutomationPage.deleteExtensionTemplate();

		webDB.waitForElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
		webDB.clickAnElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);

		TemplateName = createExtensiontemplate();
		Thread.sleep(1000);

//		flag=webDB.waitForElement(ManualMessagingLocators.VIEWTAG, ElementType.Xpath);
//		if(flag) {
//		webDB.clickAnElement(ManualMessagingLocators.VIEWTAG, ElementType.Xpath);
//		}
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(MessagingLocators.TAG_NAMES_COMMON)));
		tagNames = webDB.driver.findElements(By.xpath(MessagingLocators.TAG_NAMES_COMMON));
		for (int i = 0; i < tagNames.size(); i++)
			text = tagNames.get(i).getText();
		log.info(text);

		elements = webDB.driver.findElements(By.xpath(MessagingLocators.TEMPLATE_PLACEHOLDERS));
		for (int j = 1; j < elements.size(); j++)
			log.info(elements.get(j).getText());

		// Close modal
//		flag = webDB.waitForElement(ManualMessagingLocators.CANCEL_BTN, ElementType.Xpath);
//		flag = webDB.clickAnElement(ManualMessagingLocators.CANCEL_BTN, ElementType.Xpath);

		Thread.sleep(3000);

		MessagingPage.deleteTemplate(TemplateName);
//		flag = webDB.waitForElement(ManualMessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
//		webDB.driver.findElement(By.xpath(ManualMessagingLocators.CLOSE_VIEW_TAG_MODAL)).click();

		return flag;
	}

	public static boolean verifyDesktopHeightOffsetShareButton() throws InterruptedException {
		parentWindow = webDB.driver.getWindowHandle();
		WidgetPage.enableShareButton();
		WidgetPage.changeDesktopeHeightOffsetShareButton();
		actions.moveToElement(webDB.driver.findElement(By.id(WidgetLocators.SHARE_SETTINGS_TAB)));
//		personalWidgetPageToStoreNavigation();
//		ShopifyStore.shopifyStorePassEnter();

		// modified - added below method from widgetpage to here
		flag = EnableDisableStoreBtnStatus();
		if (flag) {
			// modified - commented beow and changed locator
//			webDB.sendTextToAnElement(ShopifyStoreLocators.STORE_PASS, "1234", ElementType.Xpath);
//			webDB.clickAnElement(ShopifyStoreLocators.STORE_PASS_ENTER, ElementType.Xpath);
			flag = webDB.isElementDisplayed(ShopifyStoreLocators.HOMEPAGE_SHARE_WIDGET, ElementType.Xpath);
			log.info(flag);
			if (flag) {
//				element = webDB.driver.findElement(By.xpath(ShopifyStoreLocators.SHARE_WIDGET_POS));
				element = webDB.driver.findElement(By.xpath(ShopifyStoreLocators.HOMEPAGE_SHARE_WIDGET));
				Point location = element.getLocation();
				int offset = location.getY();
				flag = (offset == 804);
				log.info("value of offset is " + offset);
			}
		} else {
			webDB.driver.switchTo().window(childWindow);
			flag = webDB.isElementDisplayed(ShopifyStoreLocators.HOMEPAGE_SHARE_WIDGET, ElementType.Xpath);
			log.info(flag);
			if (flag) {
				// modified - changed locator
				element = webDB.driver.findElement(By.xpath(ShopifyStoreLocators.HOMEPAGE_SHARE_WIDGET));
				Point location = element.getLocation();
				int offset = location.getY();
				flag = (offset == 804);
				log.info("value of offset is " + offset);
			}

		}

		webDB.driver.close();
		log.info("closed the child window");
		webDB.driver.switchTo().window(parentWindow);
		log.info("switched to parent window");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(WidgetLocators.SHARE_SETTINGS_TAB)));
		webDB.driver.findElement(By.xpath(WidgetLocators.HEIGHT_OFFSET_SHARE_BTN_MOBILE_DESKTOP_SELECTED))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(WidgetLocators.HEIGHT_OFFSET_SHARE_BTN_MOBILE_DESKTOP_SELECTED, "20",
				ElementType.Xpath);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CommonLocators.SAVE_SETTINGS)));
		flag = webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP)));
		return flag;
	}

	public static boolean EnableDisableStoreBtnStatus() throws InterruptedException {

		flag = webDB.waitForElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath(CommonLocators.ENABLE_DISABLE_BTN)).getText();
		if (text.equalsIgnoreCase("Disable")) {
			parentWindow = webDB.driver.getWindowHandle();
//			webDB.clickAnElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
//			actions.moveToElement(webDB.driver.findElement(By.xpath(PersonalWidgetLocators.VIEW_STORE))).build()
//					.perform();
			webDB.scrollToAnElement(WidgetLocators.VIEW_STORE, ElementType.Xpath);
			Thread.sleep(1000);
			webDB.clickAnElement(WidgetLocators.VIEW_STORE, ElementType.Xpath);
			windows = webDB.driver.getWindowHandles();
			iterator = windows.iterator();
			while (iterator.hasNext()) {
				childWindow = iterator.next();
				if (!parentWindow.equals(childWindow)) {
					webDB.driver.switchTo().window(childWindow);
//					flag = webDB.waitForElement(ShopifyStoreLocators.STORE_PASS, ElementType.Xpath);

				}
			}
		}

		if (text.equalsIgnoreCase("Enable")) {
			webDB.clickAnElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
			parentWindow = webDB.driver.getWindowHandle();
			webDB.clickAnElement(WidgetLocators.VIEW_STORE, ElementType.Xpath);
			windows = webDB.driver.getWindowHandles();
			iterator = windows.iterator();
			while (iterator.hasNext()) {
				childWindow = iterator.next();
				if (!parentWindow.equals(childWindow)) {
					webDB.driver.switchTo().window(childWindow);
//					flag = webDB.isElementDisplayed(ShopifyStoreLocators.STORE_PASS, ElementType.Xpath);
				}
			}
		}
		return flag;
	}

	public static boolean createMultiCampaignOptin() throws InterruptedException {
		//modified - commented below
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CampaignsLocators.MARKETCAMPAIGNS_TAB)));
//		webDB.clickAnElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
		Thread.sleep(7000);
		flag = webDB.waitForElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
		if (flag) {
			log.info("create campaign button is visible");
			Thread.sleep(2000);
			webDB.clickAnElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
			flag = webDB.waitForElement(CampaignsLocators.CAMPAIGNSNAME_INPUT, ElementType.Xpath);
			if (flag) {
				webDB.clearText(CampaignsLocators.CAMPAIGNSNAME_INPUT);
				Thread.sleep(2000);
				webDB.sendTextToAnElement(CampaignsLocators.CAMPAIGNSNAME_INPUT,
						CampaignsLocators.CAMPAIGNSINPUTVALUE + random, ElementType.Xpath);
				webDB.clickAnElement(CampaignsLocators.SELECTTEMPLATE, ElementType.Xpath);
				Thread.sleep(2000);
//				webDB.moveToElement("//span[text()='" + CampaignsLocators.CHOOSETEMPLATENAME + "']", ElementType.Xpath);
//				Thread.sleep(1000);
//				flag = webDB.waitForElement("//span[text()='" + CampaignsLocators.CHOOSETEMPLATENAME + "']",
//						ElementType.Xpath);
//				if (flag) {
//					webDB.clickAnElement("//span[text()='" + CampaignsLocators.CHOOSETEMPLATENAME + "']",
//							ElementType.Xpath);
//					Thread.sleep(1000);
//					webDB.clickAnElement(CampaignsLocators.CHOOSETEMPLATEBTN, ElementType.Xpath);

				// modified - added clicking All category - by default it is in utility
				webDB.waitForElement(CampaignsLocators.ALL_CATEGORY_TEMPLATE, ElementType.Xpath);
				webDB.clickAnElement(CampaignsLocators.ALL_CATEGORY_TEMPLATE, ElementType.Xpath);
				Thread.sleep(2000);

				// modified - below locator
				List<WebElement> element = webDB.driver.findElements(By.xpath(CampaignsLocators.TEMPLATESELECT));

				for (int i = 0; i < element.size(); i++) {
					actions.moveToElement(element.get(i)).build().perform();
					Thread.sleep(500);
					String TemplateName = element.get(i).getText();
					if (TemplateName.contains(WixLocators.CHOOSETEMPLATENAME)) {
						// modified - commented and added below
//						element.get(i).click();
						Thread.sleep(2000);
						webDB.clickAnElement(WixLocators.CLICKTEMPLATE, ElementType.Xpath);

//						webDB.clickAnElement("//div[@class='template']//p[contains(text(), "+TemplateName+")]/following-sibling::div//button", ElementType.Xpath);
						break;
					}
				}

				flag = webDB.waitForElement(CampaignsLocators.FROMFILE, ElementType.CssSelector);
				if (flag) {
					propertyFilePath = (System.getProperty("user.dir") + File.separator + "payload" + File.separator
							+ CampaignsLocators.MULTIPLECAMPAIGNSFILENAMEVALUE);
					webDB.clickAnElement(CampaignsLocators.FROMFILE, ElementType.CssSelector);
					Thread.sleep(2000);
					webDB.sendTextToAnElement(CampaignsLocators.RECEIPTENTSNAME, propertyFilePath,
							ElementType.CssSelector);
					Thread.sleep(2000);
					webDB.moveToElement(CampaignsLocators.SUBMITBTN, ElementType.Xpath);
					flag = webDB.driver.findElement(By.xpath(CampaignsLocators.CONFIRMATION_CHECKBOX)).isSelected();
					System.out.println(flag);
					if (!flag) {
						Thread.sleep(2000);
						webDB.clickAnElement(CampaignsLocators.CONFIRMATION_CHECKBOX_TXT, ElementType.Xpath);
					}
					System.out.println("enabled");
					Thread.sleep(4000);
					flag = webDB.waitForElement(CampaignsLocators.SUBMITBTN, ElementType.Xpath);
					if (flag) {
						Thread.sleep(3000);
						webDB.clickAnElement(CampaignsLocators.SUBMITBTN, ElementType.Xpath);
						Thread.sleep(4000);
					}
				}
			}
		}
//		}
		return flag;

	}

}