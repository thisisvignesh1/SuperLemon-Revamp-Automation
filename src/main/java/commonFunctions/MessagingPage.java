package commonFunctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.Ordering;

import locators.CommonLocators;
import locators.MessagingLocators;
import locators.MessageLogsLocators;
import locators.WidgetLocators;
import locators.AutomationLocators;
import utils.DriverBase;
import utils.DriverBase.ElementType;

@SuppressWarnings({ "static-access", "unused" })
public class MessagingPage {

	private static Logger log = Logger.getLogger(MessagingPage.class);
	private static boolean flag;
	private static String propertyFilePath;
	private static String url;
	public static Properties prop = new Properties();
	private static DriverBase webDB = new DriverBase();
	private static Set<String> windows;
	private static Iterator<String> it;
	private static String parentWindow;
	private static String childWindow;
	private static Actions actions = new Actions(webDB.driver);
	private static Select dropDown;
	private static JavascriptExecutor js = (JavascriptExecutor) webDB.driver;
	private static WebElement element;
	private static List<WebElement> elements;
	private static String text;
	private static WebDriverWait wait = new WebDriverWait(webDB.driver, 10);
	private static String MANUAL_MESSAGING_NOTE = "Note: Send manual messages faster by opening our app on your mobile phone browser, or by using WhatsApp Desktop app. Go to Settings tab to configure.";
	private static List<WebElement> radioGeneric = webDB.driver
			.findElements(By.xpath(MessagingLocators.RADIO_ATTRIBUTE_GENERIC));
	private static List<WebElement> radioClickable = webDB.driver
			.findElements(By.xpath(MessagingLocators.RADIO_GENERIC));
//	private static List<WebElement> buttons = webDB.driver.findElements(By.xpath(ManualMessagingLocators.Buttons));
	private static List<WebElement> buttons;
	private static String TagName;
	private static List<WebElement> tagNames;
	private static Select category;
	private static List<WebElement> delteButton;
	private static List<WebElement> editButton;
	private static String TemplateName;
	private static List<WebElement> templateNames;
	private static String TemplateMsg = "This is automated message";
	private static String discountValue = RandomStringUtils.randomNumeric(2);
	private static String OrderID;
	private static String Name;
	private static String NoTagMsg = "Please add tags from Settings Tab";
	private static String NoTemplateMsg = "Please add message templates from Settings Tab";
	private static int tempcount = 0;
	private static int tagcount = 0;
	// private static String TempError = "Max templates count reached";
	private static String TagError = "Max tags count reached";
	private static String OrderCRMtemplatename;
	private static String ABcarttemplatename;
	private static String Quickreplytemplatename;

	private static String TempError = "Maximum templates limit reached";

	private static String TemplateDesc = "This is a manual messaging Template";

	public static boolean verifySubTabsManualMessagingPage() throws InterruptedException {
		flag = webDB.driver.findElement(By.xpath(MessagingLocators.ABANDONED_CART_SUBTAB)).isDisplayed();
		if (flag) {
			log.info("Abandoned Cart SubTab is displayed");
		}
		flag = webDB.driver.findElement(By.xpath(MessagingLocators.CRM_CART_SUBTAB)).isDisplayed();
		if (flag) {
			log.info("CRM Cart SubTab is displayed");
		}
		flag = webDB.driver.findElement(By.xpath(MessagingLocators.MESSAGE_TEMPLATE_SUBTAB)).isDisplayed();
		if (flag) {
			log.info("Message Templates SubTab is displayed");
		}
		flag = webDB.driver.findElement(By.xpath(MessagingLocators.SETTINGS_SUBTAB)).isDisplayed();
		if (flag) {
			log.info("Settings SubTab is displayed");
		}
		return flag;
	}

	public static boolean verifyNotesManualMessagingPage() throws InterruptedException {
		flag = webDB.driver.findElement(By.xpath(MessagingLocators.WA_SPAM_NOTE1)).isDisplayed();
		if (flag) {
			log.info("WhatsApp Spam Note 1 is Displayed");
		}

		flag = webDB.driver.findElement(By.xpath(MessagingLocators.WA_SPAM_NOTE2)).isDisplayed();
		if (flag) {
			log.info("WhatsApp Spam Note 2 is Displayed");
		}
		return flag;
	}

	public static boolean verifyAbandonedNoteManualMessagingPage() throws InterruptedException {
		text = webDB.driver.findElement(By.xpath(MessagingLocators.SUBTAB_NOTE)).getText();
		if (text.contains(MANUAL_MESSAGING_NOTE)) {
			log.info("Abandoned Note is Displayed");
			flag = true;
		}

		return flag;
	}

	public static boolean verifyOrderCRMNoteManualMessagingPage() throws InterruptedException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2000);
		flag = webDB.clickAnElement(MessagingLocators.CRM_CART_SUBTAB, ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath(MessagingLocators.SUBTAB_NOTE)).getText();
		if (text.contains(MANUAL_MESSAGING_NOTE)) {
			log.info("Order CRM Note is Displayed");
			flag = true;
		}

		return flag;
	}

	public static boolean verifyWhatsAPPConfigSettings() throws InterruptedException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2000);
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			webDB.clickAnElement(CommonLocators.THREEDOTSMENU, ElementType.Xpath);
		}
		webDB.waitForElement(MessagingLocators.SETTINGS_BTN, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MessagingLocators.SETTINGS_BTN)).click();
		log.info("Settings SubTab is displayed");
		Thread.sleep(1000);
		webDB.scrollToAnElement("//span[contains(text(),'Whatsapp Web')]", ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.driver.findElement(By.xpath("//span[contains(text(),'Whatsapp Web')]")).isDisplayed();
		if (flag) {
			log.info("WhatsApp Web Setting is displayed");
		}
		webDB.scrollToAnElement("//span[contains(text(),'Whatsapp Desktop App')]", ElementType.Xpath);
		flag = webDB.driver.findElement(By.xpath("//span[contains(text(),'Whatsapp Desktop App')]")).isDisplayed();
		if (flag) {
			log.info("WhatsApp Desktop App Setting is displayed");
		}
		return flag;
	}

	public static boolean verifyHyperlinkSettings() throws InterruptedException, FileNotFoundException, IOException {
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			webDB.clickAnElement(CommonLocators.THREEDOTSMENU, ElementType.Xpath);
		}
		webDB.waitForElement(MessagingLocators.SETTINGS_BTN, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MessagingLocators.SETTINGS_BTN)).click();
		log.info("Settings SubTab is displayed");
		Thread.sleep(1000);

		webDB.waitForElement(MessagingLocators.LOGIN_HYPERLINK, ElementType.Xpath);
		flag = webDB.clickAnElement(MessagingLocators.LOGIN_HYPERLINK, ElementType.Xpath);

		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String HyperlinkLogin = prop.getProperty("HyperlinkLogin");
		windows = webDB.driver.getWindowHandles();
		it = windows.iterator();
		parentWindow = it.next();
		childWindow = it.next();
		webDB.driver.switchTo().window(childWindow);
		url = webDB.driver.getCurrentUrl();
		log.info(url);
		if (url.equals(HyperlinkLogin)) {
			flag = true;
			log.info("SuperLemon Login site Hyperlink page displayed");
		}

		webDB.driver.close();
		log.info("closed the child window");
		webDB.driver.switchTo().window(parentWindow);
		log.info("navigated to parent window");

		return flag;
	}

	public static boolean verifyWAdesktopAppHyperlinkSettings()
			throws InterruptedException, FileNotFoundException, IOException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2000);
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			webDB.clickAnElement(CommonLocators.THREEDOTSMENU, ElementType.Xpath);
		} else {
			webDB.waitForElement(MessagingLocators.SETTINGS_BTN, ElementType.Xpath);
			webDB.driver.findElement(By.xpath(MessagingLocators.SETTINGS_BTN)).click();
			log.info("Settings SubTab is displayed");
			Thread.sleep(1000);
			webDB.scrollToAnElement(MessagingLocators.WA_DESKTOP_APP_HYPERLINK, ElementType.Xpath);
			webDB.waitForElement(MessagingLocators.WA_DESKTOP_APP_HYPERLINK, ElementType.Xpath);
			flag = webDB.clickAnElement(MessagingLocators.WA_DESKTOP_APP_HYPERLINK, ElementType.Xpath);
			propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
			prop.load(new FileInputStream(propertyFilePath));
			String WADesktopAppURL = prop.getProperty("WADesktopAppURL");
			windows = webDB.driver.getWindowHandles();
			it = windows.iterator();
			parentWindow = it.next();
			childWindow = it.next();
			webDB.driver.switchTo().window(childWindow);
			url = webDB.driver.getCurrentUrl();
			log.info(url);
			if (url.equals(WADesktopAppURL)) {
				flag = true;
				log.info("Whatsapp Desktop App Hyperlink page displayed");
			}
			webDB.driver.close();
			log.info("closed the child window");
			webDB.driver.switchTo().window(parentWindow);
			log.info("navigated to parent window");
		}
		return flag;
	}

	public static boolean verifySubSectionUnderSettings()
			throws InterruptedException, FileNotFoundException, IOException {
		parentWindow = webDB.driver.getWindowHandle();
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			webDB.clickAnElement(CommonLocators.THREEDOTSMENU, ElementType.Xpath);
		}
		webDB.waitForElement(MessagingLocators.SETTINGS_BTN, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MessagingLocators.SETTINGS_BTN)).click();
		log.info("Settings SubTab is displayed");
		Thread.sleep(1000);
		webDB.scrollToAnElement("//h2[contains(text(),'Tags')]", ElementType.Xpath);
		flag = webDB.driver.findElement(By.xpath("//h2[contains(text(),'Tags')]")).isDisplayed();
		if (flag) {
			log.info("Subsection Tags under Settings is displayed");
		}
		webDB.scrollToAnElement("//span[contains(text(),'Create Tag')]", ElementType.Xpath);
		flag = webDB.driver.findElement(By.xpath("//span[contains(text(),'Create Tag')]")).isDisplayed();
		if (flag) {
			log.info("Subsection Messaging Templates under Settings is displayed");
		}

		return flag;
	}

	public static boolean verifyTagsButtons() throws InterruptedException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2000);
		if ((System.getProperty("platformName").equals("browserstack"))
				&& (System.getProperty("os").equals("Ios") || System.getProperty("os").equals("Android"))) {
			webDB.waitForElement(CommonLocators.THREEDOTSMENU, ElementType.Xpath);
			webDB.driver.findElement(By.xpath(CommonLocators.THREEDOTSMENU)).click();
		}
		webDB.waitForElement(MessagingLocators.SETTINGS_BTN, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MessagingLocators.SETTINGS_BTN)).click();
		log.info("Settings SubTab is displayed");
		Thread.sleep(2000);

		flag = webDB.driver.findElement(By.xpath("//span[contains(text(),'View Tags')]")).isDisplayed();
		if (flag) {
			log.info("View Tags is displayed");
		}
		flag = webDB.driver.findElement(By.xpath("//span[contains(text(),'Create Tag')]")).isDisplayed();
		if (flag) {
			log.info("Create Tag is displayed");
		}
		return flag;

	}

	public static boolean verifyMessagingTemplatesButtons() throws InterruptedException {
		Thread.sleep(2000);
		webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		webDB.clickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
//		webDB.waitForElement(ManualMessagingLocators.SETTINGS_BTN, ElementType.Xpath);
//		webDB.driver.findElement(By.xpath(ManualMessagingLocators.SETTINGS_BTN)).click();
		log.info("Message Templates tab is displayed");
		Thread.sleep(2000);
		flag = webDB.driver.findElement(By.xpath("//strong[contains(text(),'Show Templates: ')]")).isDisplayed();
		if (flag) {
			log.info("View Templates is displayed");
		}
		flag = webDB.driver.findElement(By.xpath("//span[contains(text(),'Create new Template')]")).isDisplayed();
		if (flag) {
			log.info("Create Templates is displayed");
		}
		return flag;
	}

	public static String createTags() throws InterruptedException {
		deleteAllTags();
		Thread.sleep(1000);
		// Click on create tag button
//		webDB.driver.findElement(By.xpath("//h2[contains(text(),'WhatsApp Configuration')]")).click();
		Thread.sleep(1000);
		// Enter name
		webDB.scrollToAnElement(MessagingLocators.CREATETAG, ElementType.Xpath);
		flag = webDB.waitForElement(MessagingLocators.CREATETAG, ElementType.Xpath);
		if (flag) {
			Thread.sleep(1000);
			webDB.clickAnElement(MessagingLocators.CREATETAG, ElementType.Xpath);
			TagName = "AutoTag" + Math.random();
			webDB.waitForElement(MessagingLocators.TAG_NAME_FIELD, ElementType.Xpath);
			webDB.sendTextToAnElement(MessagingLocators.TAG_NAME_FIELD, TagName, ElementType.Xpath);

			// Select Category
			category = new Select(webDB.driver.findElement(By.xpath(MessagingLocators.CATEGORY_DROPDOWN)));
			flag = category.getOptions().equals("Orders");
			category.selectByVisibleText("Orders");
			log.info("Category Orders " + flag);
//			flag = category.getOptions().equals("Abandoned Cart");
//			category.selectByVisibleText("Abandoned Cart");
//			log.info("Abandoned Cart " + flag);
			Thread.sleep(1000);
			// Click on save
			webDB.waitForElement(MessagingLocators.SAVE_BTN, ElementType.Xpath);
			webDB.clickAnElement(MessagingLocators.SAVE_BTN, ElementType.Xpath);
			Thread.sleep(500);
			// Success Popup
			text = webDB.driver.findElement(By.xpath(MessagingLocators.SUCCESS)).getText();
			Thread.sleep(500);
			log.info(text);
		}
		return TagName;
	}

	public static boolean deleteTags(String TagName) throws InterruptedException {

		Thread.sleep(1000);
		// Click on view tag button
		webDB.driver.findElement(By.xpath("//span[contains(text(),'View Tags')]")).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(MessagingLocators.TAG_NAMES_COMMON)));
		tagNames = webDB.driver.findElements(By.xpath(MessagingLocators.TAG_NAMES_COMMON));
		for (int i = 0; i < tagNames.size(); i++) {
			text = tagNames.get(i).getText();
			if (text.contains(TagName)) {
				delteButton = webDB.driver.findElements(By.xpath(MessagingLocators.DELETE_BTN));
				js.executeScript("arguments[0].click();", delteButton.get(i));
//				delteButton.get(i).click();
				Thread.sleep(800);
				webDB.waitForElement(MessagingLocators.CONFIRM_BTN, ElementType.Xpath);
//                wait.until(
//                        ExpectedConditions.elementToBeClickable(webDB.driver.findElement(By.xpath(ManualMessagingLocators.CONFIRM_BTN))));
				webDB.clickAnElement(MessagingLocators.CONFIRM_BTN, ElementType.Xpath);
				Thread.sleep(1000);
				flag = webDB.isElementDisplayed(MessagingLocators.SUCCESS, ElementType.Xpath);
				text = webDB.driver.findElement(By.xpath(MessagingLocators.SUCCESS)).getText();
				Thread.sleep(500);
				log.info(text);
			}
		}
		return flag;
	}

	public static boolean verifyCreatedTags() throws InterruptedException {
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			webDB.clickAnElement(CommonLocators.THREEDOTSMENU, ElementType.Xpath);
		}
		webDB.waitForElement(MessagingLocators.SETTINGS_BTN, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MessagingLocators.SETTINGS_BTN)).click();
		log.info("Settings SubTab is displayed");
		Thread.sleep(1500);
		webDB.scrollToAnElement("//h2[contains(text(),'WhatsApp Configuration')]", ElementType.Xpath);
		webDB.waitForElement("//h2[contains(text(),'WhatsApp Configuration')]", ElementType.Xpath);
		createTags();
		System.out.println("CreatedTags");
		// Click on view tags button
		webDB.driver.findElement(By.xpath("//span[contains(text(),'View Tags')]")).click();

		// List out all
		tagNames = webDB.driver.findElements(By.xpath(MessagingLocators.TAG_NAMES_COMMON));
		// Check specific tag
		for (int i = 0; i < tagNames.size(); i++) {
			text = tagNames.get(i).getText();
			if (text.contains(TagName)) {
				log.info("Tag Name : " + TagName + " is diplayed");
				flag = true;
			}
		}

		// Close modal
		webDB.waitForElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
		webDB.clickAnElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);

		Thread.sleep(1500);
		// Delete Created tag
		deleteTags(TagName);

		Thread.sleep(500);
		// Close modal
		webDB.waitForElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
		webDB.clickAnElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
		return flag;
	}

	public static String createTemplate() throws InterruptedException {
		AutomationPage.deleteExtensionTemplate();
		Thread.sleep(1000);
		// Click on create template button
//		buttons = webDB.driver.findElements(By.xpath(ManualMessagingLocators.Buttons));
//		buttons.get(3).click();
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

			// Enter Discount value
			webDB.waitForElement(MessagingLocators.DISCOUNT_VALUE, ElementType.Xpath);
			webDB.sendTextToAnElement(MessagingLocators.DISCOUNT_VALUE, discountValue, ElementType.Xpath);

			// Select Category
			category = new Select(webDB.driver.findElement(By.xpath(MessagingLocators.CATEGORY_DROPDOWN)));
			flag = category.getOptions().equals("Orders");
			log.info("Category Orders " + flag);
			flag = category.getOptions().equals("Abandoned Cart");
			category.selectByVisibleText("Abandoned Cart");
			log.info("Abandoned Cart " + flag);
			Thread.sleep(1000);
			// Click on save
			flag = webDB.waitForElement(MessagingLocators.SAVE_BTN, ElementType.Xpath);
			webDB.clickAnElement(MessagingLocators.SAVE_BTN, ElementType.Xpath);
			Thread.sleep(500);
			// Success Popup
			text = webDB.driver.findElement(By.xpath(MessagingLocators.SUCCESS)).getText();
			Thread.sleep(500);
			log.info(text);
		}
		return TemplateName;
	}

	public static boolean deleteTemplate(String TemplateName) throws InterruptedException {

		Thread.sleep(3000);
		// Click on view template button
//		buttons = webDB.driver.findElements(By.xpath(ManualMessagingLocators.Buttons));
//		buttons.get(2).click();

		wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(MessagingLocators.TEMPLATE_NAMES_COMMON)));
		templateNames = webDB.driver.findElements(By.xpath(MessagingLocators.TEMPLATE_NAMES_COMMON));
		System.out.println(templateNames.size());
		for (int i = 0; i < templateNames.size(); i++) {
			text = templateNames.get(i).getText();
			if (text.contains(TemplateName)) {
				List<WebElement> findElements = webDB.driver.findElements(By.xpath(MessagingLocators.DELETE_BTN));
				findElements.get(i);
				webDB.waitForElement(MessagingLocators.DELETE_BTN, ElementType.Xpath);
				js.executeScript("arguments[0].click();", findElements.get(i));
				Thread.sleep(3000);
//				webDB.javaScriptClickAnElement(ManualMessagingLocators.DELETE_BTN, ElementType.Xpath);
				Thread.sleep(500);
				if ((System.getProperty("platformName").equals("browserstack"))
						&& (System.getProperty("os").equals("Ios")) || (System.getProperty("os").equals("Android"))) {
					webDB.pressTabKey();
					webDB.pressTabKey();
					Thread.sleep(2000);
					webDB.pressEnterKey();
					log.info("Template Deleted Successfully");
				} else {
					wait.until(ExpectedConditions
							.elementToBeClickable(webDB.driver.findElement(By.xpath(MessagingLocators.CONFIRM_BTN))));
					webDB.clickAnElement(MessagingLocators.CONFIRM_BTN, ElementType.Xpath);
					Thread.sleep(1500);
				}
				flag = webDB.waitForElement(MessagingLocators.SUCCESS, ElementType.Xpath);
				if (flag) {
					text = webDB.driver.findElement(By.xpath(MessagingLocators.SUCCESS)).getText();
					if (text.contains(MessagingLocators.TEMPLATE_DELETED)) {
						log.info("Template Deleted Successfully");
						Thread.sleep(4000);
						flag = true;
						break;
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyCreatedTemplate() throws InterruptedException {
		webDB.driver.navigate().refresh();
		Thread.sleep(2500);
//		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
//				|| (System.getProperty("os").equals("Android"))) {
//			flag = webDB.waitForElement(CommonLocators.SIDENAV, ElementType.CssSelector);
//			if (flag) {
//				Thread.sleep(1500);
//				webDB.clickAnElement(CommonLocators.SIDENAV, ElementType.CssSelector);
//			}
//		}
//		flag = webDB.waitForElement(ManualMessagingLocators.MANUAL_MESSAGING_TAB, ElementType.Xpath);
//		webDB.clickAnElement(ManualMessagingLocators.MANUAL_MESSAGING_TAB, ElementType.Xpath);
//		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
//				|| (System.getProperty("os").equals("Android"))) {
//			webDB.clickAnElement(CommonLocators.CLOSEICON, ElementType.Xpath);
//		}
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			webDB.clickAnElement(CommonLocators.THREEDOTSMENU, ElementType.Xpath);
		}
		webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		webDB.clickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		log.info("Messaging sub tab  is displayed");
		Thread.sleep(1500);
//		webDB.waitForElement("//h2[contains(text(),'WhatsApp Configuration')]", ElementType.Xpath);
		webDB.driver.findElement(By.xpath("//span[contains(text(),'Create new Template')]")).click();

		createTemplate();

		Thread.sleep(2000);
//		wait.until(ExpectedConditions
//				.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'WhatsApp Configuration')]")));
		// Click on view templates button
		// webDB.driver.findElement(By.xpath("//span[contains(text(),'Create new
		// Template')]")).click();
		// List out all
		templateNames = webDB.driver.findElements(By.xpath(MessagingLocators.TEMPLATE_NAMES_COMMON));
		// Check specific tag
		for (int i = 0; i < templateNames.size(); i++) {
			text = templateNames.get(i).getText();
			if (text.contains(TemplateName)) {
				log.info("Template Name : " + TemplateName + " is diplayed");
				flag = true;
			}
		}

		// Close modal
//		flag = webDB.waitForElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
//		webDB.clickAnElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
//		if (flag) {
//			log.info("Popup Closed");
//		}

		Thread.sleep(3000);
//		 //Delete Created tag
		flag = deleteTemplate(TemplateName);
//
		Thread.sleep(3000);
		// Close modal
		// webDB.clickAnElement(ManualMessagingLocators.CLOSE_VIEW_TAG_MODAL,
		// ElementType.Xpath);
		return flag;
	}

	public static boolean verifyTagPopup() throws InterruptedException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2000);
		webDB.waitForElement(MessagingLocators.SETTINGS_BTN, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MessagingLocators.SETTINGS_BTN)).click();
		log.info("Settings SubTab is displayed");

		webDB.waitForElement("//h2[contains(text(),'WhatsApp Configuration')]", ElementType.Xpath);

		Thread.sleep(1000);
		// Click on create tag button
		webDB.clickAnElement(MessagingLocators.CREATETAG, ElementType.Xpath);

//		buttons = webDB.driver.findElements(By.xpath(ManualMessagingLocators.Buttons));
//		buttons.get(1).click();

		Thread.sleep(1000);
		// Enter name
		TagName = "AutoTag" + Math.random();
		flag = webDB.waitForElement(MessagingLocators.TAG_NAME_FIELD, ElementType.Xpath);
		webDB.sendTextToAnElement(MessagingLocators.TAG_NAME_FIELD, TagName, ElementType.Xpath);
		if (flag) {
			log.info("Tag Name displayed");
		}

		// Select Category
		flag = webDB.driver.findElement(By.xpath(MessagingLocators.CATEGORY_DROPDOWN)).isDisplayed();
		category = new Select(webDB.driver.findElement(By.xpath(MessagingLocators.CATEGORY_DROPDOWN)));
		category.selectByVisibleText("Abandoned Cart");
		if (flag)
			log.info("Category displayed");
		Thread.sleep(1000);

		// Close modal
		flag = webDB.waitForElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
		flag = webDB.clickAnElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
		if (flag) {
			log.info("Popup Closed");
		}

		return flag;
	}

	public static boolean verifyClosePopup() throws InterruptedException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2000);
		webDB.waitForElement(MessagingLocators.SETTINGS_BTN, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MessagingLocators.SETTINGS_BTN)).click();
		log.info("Settings SubTab is displayed");

		webDB.waitForElement("//h2[contains(text(),'WhatsApp Configuration')]", ElementType.Xpath);

		Thread.sleep(1000);
		// Click on create tag button
		webDB.clickAnElement(MessagingLocators.CREATETAG, ElementType.Xpath);

		Thread.sleep(1000);
		// Enter name
		TagName = "AutoTag" + Math.random();
		flag = webDB.waitForElement(MessagingLocators.TAG_NAME_FIELD, ElementType.Xpath);
		webDB.sendTextToAnElement(MessagingLocators.TAG_NAME_FIELD, TagName, ElementType.Xpath);
		if (flag) {
			log.info("Tag Name displayed");
		}

		// Select Category
		flag = webDB.driver.findElement(By.xpath(MessagingLocators.CATEGORY_DROPDOWN)).isDisplayed();
		category = new Select(webDB.driver.findElement(By.xpath(MessagingLocators.CATEGORY_DROPDOWN)));
		category.selectByVisibleText("Abandoned Cart");
		if (flag)
			log.info("Category displayed");
		Thread.sleep(1000);

		// Close modal
		webDB.waitForElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
		webDB.clickAnElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);

		// Verify Close Modal
		flag = webDB.waitForElement("//h2[contains(text(),'WhatsApp Configuration')]", ElementType.Xpath);
		if (flag) {
			log.info("Data not saved!!! Popup closed");
		}
		return flag;
	}

	public static boolean verifySavedTag() throws InterruptedException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2000);
		webDB.waitForElement(MessagingLocators.SETTINGS_BTN, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MessagingLocators.SETTINGS_BTN)).click();
		log.info("Settings SubTab is displayed");

		webDB.waitForElement("//h2[contains(text(),'WhatsApp Configuration')]", ElementType.Xpath);

		Thread.sleep(800);
		// Create template
		createTags();
		Thread.sleep(1000);

		flag = webDB.waitForElement("//h2[contains(text(),'WhatsApp Configuration')]", ElementType.Xpath);
		if (flag) {
			log.info("Popup Closed!!!");
		}

		Thread.sleep(500);
		// Delete Created tag
		deleteTags(TagName);

		Thread.sleep(1000);
		// Close modal
		webDB.waitForElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
		webDB.clickAnElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);

		return flag;
	}

	public static boolean verifyCancelPopup() throws InterruptedException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2000);
		webDB.waitForElement(MessagingLocators.SETTINGS_BTN, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MessagingLocators.SETTINGS_BTN)).click();
		log.info("Settings SubTab is displayed");

		webDB.waitForElement("//h2[contains(text(),'WhatsApp Configuration')]", ElementType.Xpath);

		Thread.sleep(1000);
		// Click on create tag button
		webDB.clickAnElement(MessagingLocators.CREATETAG, ElementType.Xpath);

		Thread.sleep(1500);
		// Enter name
		TagName = "AutoTag" + Math.random();
		flag = webDB.waitForElement(MessagingLocators.TAG_NAME_FIELD, ElementType.Xpath);
		webDB.sendTextToAnElement(MessagingLocators.TAG_NAME_FIELD, TagName, ElementType.Xpath);
		if (flag) {
			log.info("Tag Name displayed");
		}

		// Select Category
		flag = webDB.driver.findElement(By.xpath(MessagingLocators.CATEGORY_DROPDOWN)).isDisplayed();
		category = new Select(webDB.driver.findElement(By.xpath(MessagingLocators.CATEGORY_DROPDOWN)));
		category.selectByVisibleText("Abandoned Cart");
		if (flag)
			log.info("Category displayed");
		Thread.sleep(1000);

		// Close modal
		webDB.waitForElement(MessagingLocators.CANCEL_BTN, ElementType.Xpath);
		webDB.clickAnElement(MessagingLocators.CANCEL_BTN, ElementType.Xpath);

		// Verify Close Modal
		flag = webDB.waitForElement("//h2[contains(text(),'WhatsApp Configuration')]", ElementType.Xpath);
		if (flag) {
			log.info("Data not saved!!! Cancel Button Clicked");
		}

		return flag;
	}

	public static boolean verifyDuplicateTag() throws InterruptedException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2000);
		webDB.waitForElement(MessagingLocators.SETTINGS_BTN, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MessagingLocators.SETTINGS_BTN)).click();
		log.info("Settings SubTab is displayed");

		webDB.waitForElement("//h2[contains(text(),'WhatsApp Configuration')]", ElementType.Xpath);

		Thread.sleep(800);

		// Create template
		createTags();
		Thread.sleep(1000);

		// Click on create tag button
		webDB.clickAnElement(MessagingLocators.CREATETAG, ElementType.Xpath);
		Thread.sleep(1000);

		// retry with same name
		webDB.waitForElement(MessagingLocators.TAG_NAME_FIELD, ElementType.Xpath);
		webDB.sendTextToAnElement(MessagingLocators.TAG_NAME_FIELD, TagName, ElementType.Xpath);

		// Select Category
		category = new Select(webDB.driver.findElement(By.xpath(MessagingLocators.CATEGORY_DROPDOWN)));
		category.selectByVisibleText("Abandoned Cart");
		Thread.sleep(1000);

		// Click on save
		webDB.clickAnElement(MessagingLocators.SAVE_BTN, ElementType.Xpath);
		Thread.sleep(500);

		// catch error
		text = webDB.driver.findElement(By.xpath(MessagingLocators.SUCCESS)).getText();
		Thread.sleep(500);
		log.info(text);
		Thread.sleep(5000);

		// Close modal
		webDB.waitForElement(MessagingLocators.CANCEL_BTN, ElementType.Xpath);
		flag = webDB.clickAnElement(MessagingLocators.CANCEL_BTN, ElementType.Xpath);
		if (flag) {
			log.info("Popup Closed!!!");
		}

		Thread.sleep(1000);
		// Delete Created tag
		deleteTags(TagName);

		Thread.sleep(500);
		// Close modal
		webDB.waitForElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
		webDB.clickAnElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);

		return flag;
	}

	public static boolean verifyEmptyTagFields() throws InterruptedException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2000);
		webDB.waitForElement(MessagingLocators.SETTINGS_BTN, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MessagingLocators.SETTINGS_BTN)).click();
		log.info("Settings SubTab is displayed");

		webDB.waitForElement("//h2[contains(text(),'WhatsApp Configuration')]", ElementType.Xpath);

		// Click on create tag button
		webDB.clickAnElement(MessagingLocators.CREATETAG, ElementType.Xpath);
		Thread.sleep(1000);

		// Click on save
		webDB.clickAnElement(MessagingLocators.SAVE_BTN, ElementType.Xpath);
		Thread.sleep(500);

		// catch error
		text = webDB.driver.findElement(By.xpath(MessagingLocators.SUCCESS)).getText();
		Thread.sleep(500);
		log.info("Error : " + text);
		Thread.sleep(5000);

		// Enter name
		TagName = "AutoTag" + Math.random();
		flag = webDB.waitForElement(MessagingLocators.TAG_NAME_FIELD, ElementType.Xpath);
		webDB.sendTextToAnElement(MessagingLocators.TAG_NAME_FIELD, TagName, ElementType.Xpath);
		if (flag) {
			log.info("Tag Name displayed");
		}

		// Click on save
		webDB.clickAnElement(MessagingLocators.SAVE_BTN, ElementType.Xpath);
		Thread.sleep(500);

		// catch error
		text = webDB.driver.findElement(By.xpath(MessagingLocators.SUCCESS)).getText();
		Thread.sleep(500);
		log.info("Error : " + text);
		Thread.sleep(5000);

		// Close modal
		webDB.waitForElement(MessagingLocators.CANCEL_BTN, ElementType.Xpath);
		flag = webDB.clickAnElement(MessagingLocators.CANCEL_BTN, ElementType.Xpath);
		if (flag) {
			log.info("Popup Closed!!!");
		}

		return flag;
	}

	public static boolean verifyEmptyTagNameField() throws InterruptedException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2000);
		webDB.waitForElement(MessagingLocators.SETTINGS_BTN, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MessagingLocators.SETTINGS_BTN)).click();
		log.info("Settings SubTab is displayed");

		webDB.waitForElement("//h2[contains(text(),'WhatsApp Configuration')]", ElementType.Xpath);

		// Click on create tag button
		webDB.clickAnElement(MessagingLocators.CREATETAG, ElementType.Xpath);
		Thread.sleep(1000);

		// Click on save
		webDB.clickAnElement(MessagingLocators.SAVE_BTN, ElementType.Xpath);
		Thread.sleep(500);

		// catch error
		text = webDB.driver.findElement(By.xpath(MessagingLocators.SUCCESS)).getText();
		Thread.sleep(500);
		log.info("Error : " + text);
		Thread.sleep(5000);

		// Close modal
		webDB.waitForElement(MessagingLocators.CANCEL_BTN, ElementType.Xpath);
		flag = webDB.clickAnElement(MessagingLocators.CANCEL_BTN, ElementType.Xpath);
		if (flag) {
			log.info("Popup Closed!!!");
		}

		return flag;
	}

	public static boolean verifyEmptyTagCategoryField() throws InterruptedException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2000);
		webDB.waitForElement(MessagingLocators.SETTINGS_BTN, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MessagingLocators.SETTINGS_BTN)).click();
		log.info("Settings SubTab is displayed");

		webDB.waitForElement("//h2[contains(text(),'WhatsApp Configuration')]", ElementType.Xpath);

		// Click on create tag button
		webDB.clickAnElement(MessagingLocators.CREATETAG, ElementType.Xpath);
		Thread.sleep(1000);

		// Enter name
		TagName = "AutoTag" + Math.random();
		flag = webDB.waitForElement(MessagingLocators.TAG_NAME_FIELD, ElementType.Xpath);
		webDB.sendTextToAnElement(MessagingLocators.TAG_NAME_FIELD, TagName, ElementType.Xpath);
		if (flag) {
			log.info("Tag Name displayed");
		}

		// Click on save
		webDB.clickAnElement(MessagingLocators.SAVE_BTN, ElementType.Xpath);
		Thread.sleep(500);

		// catch error
		text = webDB.driver.findElement(By.xpath(MessagingLocators.SUCCESS)).getText();
		Thread.sleep(500);
		log.info("Error : " + text);
		Thread.sleep(5000);

		// Close modal
		webDB.waitForElement(MessagingLocators.CANCEL_BTN, ElementType.Xpath);
		flag = webDB.clickAnElement(MessagingLocators.CANCEL_BTN, ElementType.Xpath);
		if (flag) {
			log.info("Popup Closed!!!");
		}

		return flag;
	}

	public static boolean verifyCreateTemplateField() throws InterruptedException {
		Thread.sleep(1000);
		webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES)).click();
		log.info("Settings SubTab is displayed");

		webDB.waitForElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);

		// Click on create template button
		webDB.clickAnElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);

		Thread.sleep(1000);
		// Name
		flag = webDB.driver.findElement(By.xpath(MessagingLocators.TEMPLATE_NAME_FIELD)).isDisplayed();
		if (flag) {
			log.info("Template Name field is displayed");
		}

		// Message
		flag = webDB.driver.findElement(By.xpath(MessagingLocators.TEMPLATE_MSG_FIELD)).isDisplayed();
		if (flag) {
			log.info("Template Message field is displayed");
		}

		// Discount value
		flag = webDB.driver.findElement(By.xpath(MessagingLocators.DISCOUNT_VALUE)).isDisplayed();
		if (flag) {
			log.info("Template Discount field is displayed");
		}

		// Category
		category = new Select(webDB.driver.findElement(By.xpath(MessagingLocators.CATEGORY_DROPDOWN)));
		if (!category.getOptions().isEmpty())
			log.info("Category is displayed");

		// Tags listing
		flag = webDB.driver.findElement(By.xpath("//p[contains(text(),'Some useful tags can be found below,')]"))
				.isDisplayed();
		if (flag) {
			log.info("Template Tags listing is displayed");
		}

		Thread.sleep(1000);
		// Close modal
		flag = webDB.waitForElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
		}

		return flag;
	}

	public static boolean verifyDuplicateTemplate() throws InterruptedException {
		Thread.sleep(1000);
		webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES)).click();
		log.info("Settings SubTab is displayed");

		webDB.waitForElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);

		// Click on create template button
		webDB.clickAnElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);

		Thread.sleep(800);

		// Create template
		createTemplate();
		Thread.sleep(1000);

		// Click on create Template button
		webDB.clickAnElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
		Thread.sleep(1000);

		// retry with same name
		// Enter name
		webDB.waitForElement(MessagingLocators.TEMPLATE_NAME_FIELD, ElementType.Xpath);
		webDB.sendTextToAnElement(MessagingLocators.TEMPLATE_NAME_FIELD, TemplateName, ElementType.Xpath);

		// Enter Message
		webDB.waitForElement(MessagingLocators.TEMPLATE_MSG_FIELD, ElementType.Xpath);
		webDB.sendTextToAnElement(MessagingLocators.TEMPLATE_MSG_FIELD, TemplateMsg, ElementType.Xpath);

		// Enter Discount value
		webDB.waitForElement(MessagingLocators.DISCOUNT_VALUE, ElementType.Xpath);
		webDB.sendTextToAnElement(MessagingLocators.DISCOUNT_VALUE, discountValue, ElementType.Xpath);

		// Select Category
		category = new Select(webDB.driver.findElement(By.xpath(MessagingLocators.CATEGORY_DROPDOWN)));
		category.selectByVisibleText("Abandoned Cart");
		Thread.sleep(1000);

		// Click on save
		webDB.clickAnElement(MessagingLocators.SAVE_BTN, ElementType.Xpath);
		Thread.sleep(500);

		// catch error
		text = webDB.driver.findElement(By.xpath(MessagingLocators.SUCCESS)).getText();
		Thread.sleep(500);
		log.info(text);
		Thread.sleep(5000);

		// Close modal
		webDB.waitForElement(MessagingLocators.CANCEL_BTN, ElementType.Xpath);
		flag = webDB.clickAnElement(MessagingLocators.CANCEL_BTN, ElementType.Xpath);
		if (flag) {
			log.info("Popup Closed!!!");
		}

		Thread.sleep(1000);
		// Delete Created tag
		deleteTemplate(TemplateName);

		return flag;
	}

	public static boolean verifyEmptyTemplateNameField() throws InterruptedException {

		Thread.sleep(1000);
		webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		webDB.clickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.waitForElement("//strong[contains(text(),'Show Templates: ')]", ElementType.Xpath);
		if (flag) {
			webDB.waitForElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
			webDB.clickAnElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
			Thread.sleep(1000);

			// Enter Message
			flag = webDB.waitForElement(MessagingLocators.TEMPLATE_MSG_FIELD, ElementType.Xpath);
			flag = webDB.sendTextToAnElement(MessagingLocators.TEMPLATE_MSG_FIELD, TemplateMsg, ElementType.Xpath);

			// Enter Discount value
			flag = webDB.waitForElement(MessagingLocators.DISCOUNT_VALUE, ElementType.Xpath);
			flag = webDB.sendTextToAnElement(MessagingLocators.DISCOUNT_VALUE, discountValue, ElementType.Xpath);

			// Select Category
			category = new Select(webDB.driver.findElement(By.xpath(MessagingLocators.CATEGORY_DROPDOWN)));
			category.selectByVisibleText("Abandoned Cart");
			Thread.sleep(1000);

			// Click on save
			flag = webDB.clickAnElement(MessagingLocators.SAVE_BTN, ElementType.Xpath);
			Thread.sleep(500);

			// Success Popup
			text = webDB.driver.findElement(By.xpath(MessagingLocators.SUCCESS)).getText();
			Thread.sleep(500);
			log.info(text);
			Thread.sleep(5000);

			// Close modal
			flag = webDB.waitForElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
			flag = webDB.clickAnElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
		}
		return flag;
	}

	public static boolean verifyEmptyTemplateMessageField() throws InterruptedException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2000);
		webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		webDB.clickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.waitForElement("//strong[contains(text(),'Show Templates: ')]", ElementType.Xpath);
		if (flag) {
			webDB.waitForElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
			webDB.clickAnElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
			Thread.sleep(1000);
			// Enter name
			TemplateName = "AutoTemplate" + Math.random();
			webDB.waitForElement(MessagingLocators.TEMPLATE_NAME_FIELD, ElementType.Xpath);
			webDB.sendTextToAnElement(MessagingLocators.TEMPLATE_NAME_FIELD, TemplateName, ElementType.Xpath);

			// Enter Discount value
			flag = webDB.waitForElement(MessagingLocators.DISCOUNT_VALUE, ElementType.Xpath);
			flag = webDB.sendTextToAnElement(MessagingLocators.DISCOUNT_VALUE, discountValue, ElementType.Xpath);

			// Select Category
			category = new Select(webDB.driver.findElement(By.xpath(MessagingLocators.CATEGORY_DROPDOWN)));
			category.selectByVisibleText("Abandoned Cart");
			Thread.sleep(1000);

			// Click on save
			flag = webDB.clickAnElement(MessagingLocators.SAVE_BTN, ElementType.Xpath);
			Thread.sleep(500);

			// Success Popup
			text = webDB.driver.findElement(By.xpath(MessagingLocators.SUCCESS)).getText();
			Thread.sleep(500);
			log.info(text);
			Thread.sleep(5000);

			// Close modal
			flag = webDB.waitForElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
			flag = webDB.clickAnElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
		}
		return flag;
	}

	public static boolean verifyEmptyTemplateCategoryField() throws InterruptedException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2000);
		webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		webDB.clickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.waitForElement("//strong[contains(text(),'Show Templates: ')]", ElementType.Xpath);
		if (flag) {
			webDB.waitForElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
			webDB.clickAnElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
			Thread.sleep(1000);
			// Enter name
			TemplateName = "AutoTemplate" + Math.random();
			flag = webDB.waitForElement(MessagingLocators.TEMPLATE_NAME_FIELD, ElementType.Xpath);
			flag = webDB.sendTextToAnElement(MessagingLocators.TEMPLATE_NAME_FIELD, TemplateName, ElementType.Xpath);

			// Enter Message
			flag = webDB.waitForElement(MessagingLocators.TEMPLATE_MSG_FIELD, ElementType.Xpath);
			flag = webDB.sendTextToAnElement(MessagingLocators.TEMPLATE_MSG_FIELD, TemplateMsg, ElementType.Xpath);

			// Enter Discount value
			flag = webDB.waitForElement(MessagingLocators.DISCOUNT_VALUE, ElementType.Xpath);
			flag = webDB.sendTextToAnElement(MessagingLocators.DISCOUNT_VALUE, discountValue, ElementType.Xpath);

			// Click on save
			flag = webDB.clickAnElement(MessagingLocators.SAVE_BTN, ElementType.Xpath);
			Thread.sleep(500);

			// Success Popup
			text = webDB.driver.findElement(By.xpath(MessagingLocators.SUCCESS)).getText();
			Thread.sleep(500);
			log.info(text);
			Thread.sleep(5000);

			// Close modal
			flag = webDB.waitForElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
			flag = webDB.clickAnElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
		}
		return flag;
	}

	public static boolean verifyEmptyTemplateDiscountField() throws InterruptedException {
		Thread.sleep(1000);
		webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		webDB.clickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.waitForElement("//strong[contains(text(),'Show Templates: ')]", ElementType.Xpath);
		if (flag) {
			webDB.waitForElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
			webDB.clickAnElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
			Thread.sleep(1000);
			// Enter name
			TemplateName = "AutoTemplate" + Math.random();
			flag = webDB.waitForElement(MessagingLocators.TEMPLATE_NAME_FIELD, ElementType.Xpath);
			flag = webDB.sendTextToAnElement(MessagingLocators.TEMPLATE_NAME_FIELD, TemplateName, ElementType.Xpath);

			// Enter Message
			flag = webDB.waitForElement(MessagingLocators.TEMPLATE_MSG_FIELD, ElementType.Xpath);
			flag = webDB.sendTextToAnElement(MessagingLocators.TEMPLATE_MSG_FIELD, TemplateMsg, ElementType.Xpath);

			// Select Category
			category = new Select(webDB.driver.findElement(By.xpath(MessagingLocators.CATEGORY_DROPDOWN)));
			category.selectByVisibleText("Abandoned Cart");
			Thread.sleep(800);

			// Click on save
			flag = webDB.clickAnElement(MessagingLocators.SAVE_BTN, ElementType.Xpath);
			Thread.sleep(500);

			// Success Popup
			text = webDB.driver.findElement(By.xpath(MessagingLocators.SUCCESS)).getText();
			Thread.sleep(500);
			log.info(text);
			Thread.sleep(5000);

			// Close modal
			flag = webDB.waitForElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
			flag = webDB.clickAnElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
		}
		return flag;
	}

	public static boolean verifyClosePopupTemplate() throws InterruptedException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2000);
		webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		webDB.clickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.waitForElement("//strong[contains(text(),'Show Templates: ')]", ElementType.Xpath);
		if (flag) {
			webDB.waitForElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
			webDB.clickAnElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
			Thread.sleep(1000);
			// Enter name
			TemplateName = "AutoTemplate" + Math.random();
			flag = webDB.waitForElement(MessagingLocators.TEMPLATE_NAME_FIELD, ElementType.Xpath);
			flag = webDB.sendTextToAnElement(MessagingLocators.TEMPLATE_NAME_FIELD, TemplateName, ElementType.Xpath);

			// Enter Message
			flag = webDB.waitForElement(MessagingLocators.TEMPLATE_MSG_FIELD, ElementType.Xpath);
			flag = webDB.sendTextToAnElement(MessagingLocators.TEMPLATE_MSG_FIELD, TemplateMsg, ElementType.Xpath);

			// Select Category
			category = new Select(webDB.driver.findElement(By.xpath(MessagingLocators.CATEGORY_DROPDOWN)));
			category.selectByVisibleText("Abandoned Cart");
			Thread.sleep(800);

			// Enter Discount value
			flag = webDB.waitForElement(MessagingLocators.DISCOUNT_VALUE, ElementType.Xpath);
			flag = webDB.sendTextToAnElement(MessagingLocators.DISCOUNT_VALUE, discountValue, ElementType.Xpath);
			Thread.sleep(800);

			// Close modal
			webDB.waitForElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
			webDB.clickAnElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);

			// Verify Close Modal
			flag = webDB.waitForElement("//strong[contains(text(),'Show Templates: ')]", ElementType.Xpath);
			if (flag) {
				log.info("Data not saved!!! Popup closed");
			}
		}
		return flag;
	}

	public static boolean verifyCancelPopupTemplate() throws InterruptedException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2000);
		webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		webDB.clickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.waitForElement("//strong[contains(text(),'Show Templates: ')]", ElementType.Xpath);
		if (flag) {
			webDB.waitForElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
			webDB.clickAnElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
			Thread.sleep(1000);
			// Enter name
			TemplateName = "AutoTemplate" + Math.random();
			flag = webDB.waitForElement(MessagingLocators.TEMPLATE_NAME_FIELD, ElementType.Xpath);
			flag = webDB.sendTextToAnElement(MessagingLocators.TEMPLATE_NAME_FIELD, TemplateName, ElementType.Xpath);

			// Enter Message
			flag = webDB.waitForElement(MessagingLocators.TEMPLATE_MSG_FIELD, ElementType.Xpath);
			flag = webDB.sendTextToAnElement(MessagingLocators.TEMPLATE_MSG_FIELD, TemplateMsg, ElementType.Xpath);

			// Select Category
			category = new Select(webDB.driver.findElement(By.xpath(MessagingLocators.CATEGORY_DROPDOWN)));
			category.selectByVisibleText("Abandoned Cart");
			Thread.sleep(800);

			// Enter Discount value
			flag = webDB.waitForElement(MessagingLocators.DISCOUNT_VALUE, ElementType.Xpath);
			flag = webDB.sendTextToAnElement(MessagingLocators.DISCOUNT_VALUE, discountValue, ElementType.Xpath);
			Thread.sleep(800);

			// Close modal
			webDB.waitForElement(MessagingLocators.CANCEL_BTN, ElementType.Xpath);
			webDB.clickAnElement(MessagingLocators.CANCEL_BTN, ElementType.Xpath);

			// Verify Close Modal
			flag = webDB.waitForElement("//strong[contains(text(),'Show Templates: ')]", ElementType.Xpath);
			if (flag) {
				log.info("Data not saved!!! Popup closed");
			}
		}
		return flag;
	}

	public static boolean verifySavedTemplate() throws InterruptedException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2000);
		webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		webDB.clickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.waitForElement("//strong[contains(text(),'Show Templates: ')]", ElementType.Xpath);
		if (flag) {
			webDB.waitForElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
			webDB.clickAnElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
			Thread.sleep(1000);
			createTemplate();
			Thread.sleep(1000);
			deleteTemplate(TemplateName);
			Thread.sleep(1000);
			// Close modal
			webDB.waitForElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
			webDB.clickAnElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
		}
		return flag;
	}

	public static boolean VerifyFilters() throws InterruptedException {
		flag = webDB.driver.findElement(By.xpath("(//span[contains(text(),'Abandoned Cart')])[1]")).isDisplayed();
		if (flag) {
			log.info("Abandoned Cart displayed");
		}

		flag = webDB.driver.findElement(By.xpath(MessagingLocators.ABANDONED_ORDERID_FILTER)).isDisplayed();
		if (flag) {
			log.info("Order ID filter displayed");
		}

		flag = webDB.driver.findElement(By.xpath(MessagingLocators.TAG_FILTER)).isDisplayed();
		if (flag) {
			log.info("Tag filter displayed");
		}

		flag = webDB.driver.findElement(By.xpath(MessagingLocators.NAME_FILTER)).isDisplayed();
		if (flag) {
			log.info("Name filter displayed");
		}

		Thread.sleep(1000);

		webDB.waitForElement(MessagingLocators.CRM_CART_SUBTAB, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MessagingLocators.CRM_CART_SUBTAB)).click();

		flag = webDB.driver.findElement(By.xpath("(//span[contains(text(),'Order CRM')])[1]")).isDisplayed();
		if (flag) {
			log.info("Order CRM displayed");
		}

		flag = webDB.driver.findElement(By.xpath(MessagingLocators.ORDERCRM_ORDERID_FILTER)).isDisplayed();
		if (flag) {
			log.info("Order ID filter displayed");
		}

		flag = webDB.driver.findElement(By.xpath(MessagingLocators.TAG_FILTER)).isDisplayed();
		if (flag) {
			log.info("Tag filter displayed");
		}

		flag = webDB.driver.findElement(By.xpath(MessagingLocators.NAME_FILTER)).isDisplayed();
		if (flag) {
			log.info("Name filter displayed");
		}

		return flag;
	}

	public static boolean VerifyDataTableAbandonedCart() throws InterruptedException {
		flag = webDB.driver.findElement(By.xpath("(//span[contains(text(),'Abandoned Cart')])[1]")).isDisplayed();
		if (flag) {
			log.info("Abandoned Cart displayed");
		}

		flag = webDB.driver.findElement(By.xpath(MessagingLocators.DATATABLE_ROWS)).isDisplayed();
		if (flag) {
			log.info("Data Table displayed");
		}

		text = webDB.driver.findElement(By.xpath("//div[@class='Polaris-Stack Polaris-Stack--vertical']//div[2]//p"))
				.getText();
		log.info(text);

		return flag;
	}

	public static boolean VerifyDataTableOrderCRM() throws InterruptedException {
		webDB.waitForElement(MessagingLocators.CRM_CART_SUBTAB, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MessagingLocators.CRM_CART_SUBTAB)).click();

		flag = webDB.driver.findElement(By.xpath("(//span[contains(text(),'Order CRM')])[1]")).isDisplayed();
		if (flag) {
			log.info("Order CRM displayed");
		}

		flag = webDB.driver.findElement(By.xpath(MessagingLocators.DATATABLE_ROWS)).isDisplayed();
		if (flag) {
			log.info("Data Table displayed");
		}

		text = webDB.driver.findElement(By.xpath("//div[@class='Polaris-Stack Polaris-Stack--vertical']//div[2]//p"))
				.getText();
		log.info(text);

		return flag;
	}

	public static boolean VerifyClearFilterAbandonedCart() throws InterruptedException {
//		flag = webDB.driver.findElement(By.xpath("//h1[contains(text(),'Abandoned Cart Manual Messaging')]"))
//				.isDisplayed();
//		if (flag) {
//			log.info("Abandoned Cart displayed");
//		}

		flag = webDB.driver.findElement(By.xpath(MessagingLocators.ABANDONED_ORDERID_FILTER)).isDisplayed();
		if (flag) {
			log.info("Order ID filter displayed");
		}

		OrderID = webDB.driver.findElement(By.xpath("//tbody/tr[1]/th[1]")).getText();
		OrderID = OrderID.substring(0);
		log.info("Order ID send " + OrderID);

		flag = webDB.clickAnElement(MessagingLocators.ABANDONED_ORDERID_FILTER, ElementType.Xpath);

		webDB.driver.findElement(By.xpath(MessagingLocators.ABANDONED_ORDERID_FILTER))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		flag = webDB.sendTextToAnElement(MessagingLocators.ABANDONED_ORDERID_FILTER, OrderID, ElementType.Xpath);

		Thread.sleep(1000);

		flag = webDB.waitForElement(MessagingLocators.ORDER_FILTER_CLEAR_BTN, ElementType.Xpath);
		flag = webDB.clickAnElement(MessagingLocators.ORDER_FILTER_CLEAR_BTN, ElementType.Xpath);
		if (flag) {
			log.info("OrderID filter cleared");
		}

		Thread.sleep(1000);

		flag = webDB.driver.findElement(By.xpath(MessagingLocators.TAG_FILTER)).isDisplayed();
		if (flag) {
			log.info("Tag filter displayed");
		}

		flag = webDB.clickAnElement(MessagingLocators.TAG_FILTER, ElementType.Xpath);
		Thread.sleep(1000);
		elements = webDB.driver.findElements(By.xpath(MessagingLocators.RADIO_GENERIC));
		for (int i = 0; i < elements.size(); i++) {
			elements.get(i).click();
			break;
		}

		Thread.sleep(1000);
		flag = webDB.driver.findElement(By.xpath(MessagingLocators.TAG_FILTER_CLEAR_BTN)).isEnabled();
		if (flag) {
			flag = webDB.clickAnElement(MessagingLocators.TAG_FILTER_CLEAR_BTN, ElementType.Xpath);
			log.info("Tag Filter Cleared");
		}

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MessagingLocators.NAME_FILTER)));
		flag = webDB.driver.findElement(By.xpath(MessagingLocators.NAME_FILTER)).isDisplayed();
		if (flag) {
			log.info("Name filter displayed");
		}

//		webDB.driver.navigate().refresh();
//		Dimension d = new Dimension(1920, 1080);
//		webDB.driver.manage().window().setSize(d);
		Thread.sleep(1000);
		flag = webDB.clickAnElement(MessagingLocators.NAME_FILTER, ElementType.Xpath);

		Name = "AutoUser ゘锩ֻ쟣﫿";
		log.info("Name send " + Name);
		webDB.driver.findElement(By.xpath(MessagingLocators.NAME_FILTER_TEXTBOX))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		flag = webDB.sendTextToAnElement(MessagingLocators.NAME_FILTER_TEXTBOX, Name, ElementType.Xpath);
		flag = webDB.waitForElement(MessageLogsLocators.SEARCH_BTN, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MessageLogsLocators.SEARCH_BTN, ElementType.Xpath);
			Thread.sleep(1500);
			flag = webDB.driver.findElement(By.xpath(MessagingLocators.NAME_FILTER_CLEAR_BTN)).isEnabled();
			if (flag) {
				flag = webDB.clickAnElement(MessagingLocators.NAME_FILTER_CLEAR_BTN, ElementType.Xpath);
				log.info("Name Filter Cleared");
			}
		}
		return flag;
	}

	public static boolean VerifyClearFilterORDERCRM() throws InterruptedException {
		webDB.waitForElement(MessagingLocators.CRM_CART_SUBTAB, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MessagingLocators.CRM_CART_SUBTAB)).click();

//		flag = webDB.driver.findElement(By.xpath("//h1[contains(text(),'Orders Manual Messaging')]")).isDisplayed();
//		if (flag) {
//			log.info("Order CRM displayed");
//		}

		flag = webDB.driver.findElement(By.xpath(MessagingLocators.ORDERCRM_ORDERID_FILTER)).isDisplayed();
		if (flag) {
			log.info("Order ID filter displayed");
		}

		OrderID = webDB.driver.findElement(By.xpath(MessagingLocators.ORDER_COLUMN_FIRST_VALUE)).getText();
		OrderID = OrderID.substring(1);
		String[] result = OrderID.split("\n", 2);
		log.info("Order ID send " + result[0]);

		flag = webDB.clickAnElement(MessagingLocators.ORDERCRM_ORDERID_FILTER, ElementType.Xpath);

		webDB.driver.findElement(By.xpath(MessagingLocators.ORDERCRM_ORDERID_FILTER))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		flag = webDB.sendTextToAnElement(MessagingLocators.ORDERCRM_ORDERID_FILTER, OrderID, ElementType.Xpath);

		Thread.sleep(1000);

		flag = webDB.waitForElement(MessagingLocators.ORDER_FILTER_CLEAR_BTN, ElementType.Xpath);
		flag = webDB.clickAnElement(MessagingLocators.ORDER_FILTER_CLEAR_BTN, ElementType.Xpath);
		if (flag) {
			log.info("OrderID filter cleared");
		}

		Thread.sleep(1000);

		flag = webDB.driver.findElement(By.xpath(MessagingLocators.TAG_FILTER)).isDisplayed();
		if (flag) {
			log.info("Tag filter displayed");
		}

		flag = webDB.clickAnElement(MessagingLocators.TAG_FILTER, ElementType.Xpath);
		Thread.sleep(1000);
		elements = webDB.driver.findElements(By.xpath(MessagingLocators.RADIO_GENERIC));
		for (int i = 0; i < elements.size(); i++) {
			elements.get(i).click();
			break;
		}

		Thread.sleep(1000);
		flag = webDB.driver.findElement(By.xpath(MessagingLocators.TAG_FILTER_CLEAR_BTN)).isEnabled();
		if (flag) {
			flag = webDB.clickAnElement(MessagingLocators.TAG_FILTER_CLEAR_BTN, ElementType.Xpath);
			log.info("Tag Filter Cleared");
		}

//		Dimension d = new Dimension(1920, 1080);
//		webDB.driver.manage().window().setSize(d);
		flag = webDB.driver.findElement(By.xpath(MessagingLocators.NAME_FILTER)).isDisplayed();
		if (flag) {
			log.info("Name filter displayed");
		}

		flag = webDB.clickAnElement(MessagingLocators.NAME_FILTER, ElementType.Xpath);

		Name = "AutoUser ゘锩ֻ쟣﫿";
		log.info("Name send " + Name);
		webDB.driver.findElement(By.xpath(MessagingLocators.NAME_FILTER_TEXTBOX))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		flag = webDB.sendTextToAnElement(MessagingLocators.NAME_FILTER_TEXTBOX, Name, ElementType.Xpath);
		flag = webDB.waitForElement(MessageLogsLocators.SEARCH_BTN, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MessageLogsLocators.SEARCH_BTN, ElementType.Xpath);
			Thread.sleep(1500);

			// flag =
			// webDB.driver.findElement(By.xpath(MessagingLocators.NAME_FILTER_CLEAR_BTN)).isEnabled();
			flag = webDB.IsEnabled(MessagingLocators.NAME_FILTER_CLEAR_BTN, ElementType.Xpath);
			System.out.println("flag = " + flag);
			if (flag) {
				flag = webDB.clickAnElement(MessagingLocators.NAME_FILTER_CLEAR_BTN, ElementType.Xpath);
				log.info("Name Filter Cleared");
			}
		}
		return flag;
	}

	public static boolean VerifyOrderDataTable() throws InterruptedException {
//		flag = webDB.driver.findElement(By.xpath("//h1[contains(text(),'Abandoned Cart Manual Messaging')]"))
//				.isDisplayed();
//		if (flag) {
//			log.info("Abandoned Cart displayed");
//		}

		flag = webDB.clickAnElement(MessagingLocators.TABLE_COLUMN + "'" + "Order" + "']", ElementType.Xpath);
		Thread.sleep(1000);
		if (flag) {
			System.out.print("Order ");
		}
		Asc_Desc_Sorted("th[1]");
		Thread.sleep(1000);

		flag = webDB.clickAnElement(MessagingLocators.TABLE_COLUMN + "'" + "Amount" + "']", ElementType.Xpath);
		Thread.sleep(1000);
		if (flag) {
			System.out.print("Amount ");
		}
		Asc_Desc_Sorted("td[2]");

		Thread.sleep(1000);

		webDB.waitForElement(MessagingLocators.CRM_CART_SUBTAB, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MessagingLocators.CRM_CART_SUBTAB)).click();

//		flag = webDB.driver.findElement(By.xpath("//h1[contains(text(),'Orders Manual Messaging')]")).isDisplayed();
//		if (flag) {
//			log.info("Order CRM displayed");
//		}

		flag = webDB.clickAnElement(MessagingLocators.TABLE_COLUMN + "'" + "Order" + "']", ElementType.Xpath);
		Thread.sleep(1000);
		if (flag) {
			System.out.print("Order ");
		}
		Asc_Desc_Sorted("th[1]");
		Thread.sleep(1000);

		flag = webDB.clickAnElement(MessagingLocators.TABLE_COLUMN + "'" + "Amount" + "']", ElementType.Xpath);
		Thread.sleep(1000);
		if (flag) {
			System.out.print("Amount ");
		}
		Asc_Desc_Sorted("td[2]");
		return flag;
	}

	public static void Asc_Desc_Sorted(String param) throws InterruptedException {
		ArrayList<String> sortedList = new ArrayList<>();
		List<WebElement> elementList1 = webDB.driver.findElements(By.xpath(MessagingLocators.DATATABLE_ROWS + param));
		for (WebElement we : elementList1) {
			sortedList.add(we.getText());
		}

		boolean DescisSorted = Ordering.from(String.CASE_INSENSITIVE_ORDER).isOrdered(sortedList);
		boolean AscisSorted = Ordering.from(String.CASE_INSENSITIVE_ORDER.reversed()).isOrdered(sortedList);

		if (AscisSorted == true) {
			log.info("List Sorted in Ascending Order");
			DescisSorted = false;
		} else if (DescisSorted == true) {
			log.info("List Sorted in Descending Order");
			AscisSorted = false;
		} else {
			log.info("Not Sorted");
		}
	}

	public static boolean VerifyOrderStatusDataTable() throws InterruptedException {
//		flag = webDB.driver.findElement(By.xpath("//h1[contains(text(),'Abandoned Cart Manual Messaging')]"))
//				.isDisplayed();
//		if (flag) {
//			log.info("Abandoned Cart displayed");
//		}

		flag = webDB.driver.findElement(By.xpath(MessagingLocators.STATIC_COLUMN)).isDisplayed();
		if (flag) {
			log.info("Status Column displayed");
		}

		elements = webDB.driver.findElements(By.xpath(MessagingLocators.STATIC_ROWS));
		int count = 0;
		for (int i = 1; i <= elements.size(); i++) {
			count++;
		}
		log.info("Abandoned Cart Status row count " + count + " is displayed");

		Thread.sleep(1000);

		webDB.waitForElement(MessagingLocators.CRM_CART_SUBTAB, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MessagingLocators.CRM_CART_SUBTAB)).click();

//		flag = webDB.driver.findElement(By.xpath("//h1[contains(text(),'Orders Manual Messaging')]")).isDisplayed();
//		if (flag) {
//			log.info("Order CRM displayed");
//		}

		flag = webDB.driver.findElement(By.xpath(MessagingLocators.STATIC_COLUMN)).isDisplayed();
		if (flag) {
			log.info("Status Column displayed");
		}
		count = 0;
		elements = webDB.driver.findElements(By.xpath(MessagingLocators.STATIC_ROWS));
		for (int j = 1; j <= elements.size(); j++) {
			count++;
		}

		log.info("Order CRM Status row count " + count + " is displayed");

		return flag;
	}

	public static int DataTableRowCount() throws InterruptedException {
		List<WebElement> rows = webDB.driver.findElements(By.xpath(MessageLogsLocators.DATA_TABLE_ROW));
		int rowcount = rows.size();

		if (rowcount >= 10) {
			flag = true;
			log.info("Number of pages displayed " + rowcount);
		} else {
			// flag=false;
			log.info("Number of pages beyond count 10");
		}

		return rowcount;
	}

	public static boolean verifyOrderCountDisplay() throws InterruptedException {
//		flag = webDB.driver.findElement(By.xpath("//h1[contains(text(),'Abandoned Cart Manual Messaging')]"))
//				.isDisplayed();
//		if (flag) {
//			log.info("Abandoned Cart displayed");
//		}

		text = webDB.driver.findElement(By.xpath("//div[@class='Polaris-Stack Polaris-Stack--vertical']//div[2]//p"))
				.getText();
		log.info(text);

		DataTableRowCount();

		Thread.sleep(1000);

		webDB.waitForElement(MessagingLocators.CRM_CART_SUBTAB, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MessagingLocators.CRM_CART_SUBTAB)).click();

//		flag = webDB.driver.findElement(By.xpath("//h1[contains(text(),'Orders Manual Messaging')]")).isDisplayed();
//		if (flag) {
//			log.info("Order CRM displayed");
//		}

		text = webDB.driver.findElement(By.xpath("//div[@class='Polaris-Stack Polaris-Stack--vertical']//div[2]//p"))
				.getText();
		log.info(text);

		DataTableRowCount();

		return flag;
	}

	public static boolean verifyNextNavigation() throws InterruptedException {
		Thread.sleep(2000);
//		flag = webDB.driver.findElement(By.xpath("//h1[contains(text(),'Abandoned Cart Manual Messaging')]"))
//				.isDisplayed();
//		if (flag) {
//			log.info("Abandoned Cart displayed");
//		}

		flag = webDB.driver.findElement(By.xpath(MessagingLocators.PREV_NAV)).isEnabled();
		if (!flag) {
			log.info("Prev button disable");
		}

		Thread.sleep(800);

		flag = webDB.clickAnElement(MessagingLocators.NEXT_NAV, ElementType.Xpath);
		if (flag) {
			log.info("Next button clicked");
		}

		Thread.sleep(800);

		flag = webDB.driver.findElement(By.xpath(MessagingLocators.PREV_NAV)).isEnabled();
		if (flag) {
			log.info("Prev button enabled");
		}

		Thread.sleep(800);

		return flag;
	}

	public static boolean verifyPrevtNavigation() throws InterruptedException {
//		flag = webDB.driver.findElement(By.xpath("//h1[contains(text(),'Abandoned Cart Manual Messaging')]"))
//				.isDisplayed();
//		if (flag) {
//			log.info("Abandoned Cart displayed");
//		}

		flag = webDB.driver.findElement(By.xpath(MessagingLocators.PREV_NAV)).isEnabled();
		if (!flag) {
			log.info("Prev button disable");
		}

		Thread.sleep(800);

		flag = webDB.clickAnElement(MessagingLocators.NEXT_NAV, ElementType.Xpath);
		if (flag) {
			log.info("Next button clicked");
		}

		Thread.sleep(800);

		flag = webDB.driver.findElement(By.xpath(MessagingLocators.PREV_NAV)).isEnabled();
		if (flag) {
			log.info("Prev button enabled");
		}

		Thread.sleep(800);

		flag = webDB.clickAnElement(MessagingLocators.PREV_NAV, ElementType.Xpath);
		if (flag) {
			log.info("Prev button clicked");
		}

		Thread.sleep(800);

		return flag;
	}

	public static boolean verifyOrderIDSearchBox() throws InterruptedException {
//		flag = webDB.driver.findElement(By.xpath("//h1[contains(text(),'Abandoned Cart Manual Messaging')]"))
//				.isDisplayed();
//		if (flag) {
//			log.info("Abandoned Cart displayed");
//		}

		flag = webDB.driver.findElement(By.xpath(MessagingLocators.ABANDONED_ORDERID_FILTER)).isDisplayed();
		if (flag) {
			log.info("Order ID filter displayed");
		}

		OrderID = webDB.driver.findElement(By.xpath(MessagingLocators.ORDER_COLUMN_FIRST_VALUE)).getText();
		OrderID = OrderID.substring(0);
		log.info("Order ID send " + OrderID);

		flag = webDB.clickAnElement(MessagingLocators.ABANDONED_ORDERID_FILTER, ElementType.Xpath);

		webDB.driver.findElement(By.xpath(MessagingLocators.ABANDONED_ORDERID_FILTER))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		flag = webDB.sendTextToAnElement(MessagingLocators.ABANDONED_ORDERID_FILTER, OrderID, ElementType.Xpath);

		text = webDB.driver.findElement(By.xpath(MessagingLocators.ABANDONED_ORDERID_FILTER)).getAttribute("value");
		if (text.contains(OrderID)) {
			log.info("Abandoned Cart Order is displayed");
		}
		Thread.sleep(1000);

		flag = webDB.waitForElement(MessagingLocators.ORDER_FILTER_CLEAR_BTN, ElementType.Xpath);
		flag = webDB.clickAnElement(MessagingLocators.ORDER_FILTER_CLEAR_BTN, ElementType.Xpath);
		if (flag) {
			log.info("OrderID filter cleared");
		}

		Thread.sleep(1000);
		webDB.waitForElement(MessagingLocators.CRM_CART_SUBTAB, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MessagingLocators.CRM_CART_SUBTAB)).click();

//		flag = webDB.driver.findElement(By.xpath("//h1[contains(text(),'Orders Manual Messaging')]")).isDisplayed();
//		if (flag) {
//			log.info("Order CRM displayed");
//		}

		flag = webDB.driver.findElement(By.xpath(MessagingLocators.ORDERCRM_ORDERID_FILTER)).isDisplayed();
		if (flag) {
			log.info("Order ID filter displayed");
		}

		OrderID = webDB.driver.findElement(By.xpath(MessagingLocators.ORDER_COLUMN_FIRST_VALUE)).getText();
		log.info("Order ID send " + OrderID);

		flag = webDB.clickAnElement(MessagingLocators.ORDERCRM_ORDERID_FILTER, ElementType.Xpath);

		webDB.driver.findElement(By.xpath(MessagingLocators.ORDERCRM_ORDERID_FILTER))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		flag = webDB.sendTextToAnElement(MessagingLocators.ORDERCRM_ORDERID_FILTER, OrderID, ElementType.Xpath);

		text = webDB.driver.findElement(By.xpath(MessagingLocators.ORDERCRM_ORDERID_FILTER)).getAttribute("value");
		if (text.contains(OrderID)) {
			log.info("Order CRM Order is displayed");
		}

		Thread.sleep(1000);

		flag = webDB.waitForElement(MessagingLocators.ORDER_FILTER_CLEAR_BTN, ElementType.Xpath);
		flag = webDB.clickAnElement(MessagingLocators.ORDER_FILTER_CLEAR_BTN, ElementType.Xpath);
		if (flag) {
			log.info("OrderID filter cleared");
		}

		return flag;
	}

	public static boolean verifyPlaceholderMessageTemplate() throws InterruptedException {
		webDB.driver.navigate().refresh();
		Thread.sleep(5000);
		webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES)).click();
		log.info("Settings SubTab is displayed");

		flag = AutomationPage.deleteExtensionTemplate();

		webDB.waitForElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
		webDB.clickAnElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);

		createTemplate();
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

		deleteTemplate(TemplateName);
//		flag = webDB.waitForElement(ManualMessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
//		webDB.driver.findElement(By.xpath(ManualMessagingLocators.CLOSE_VIEW_TAG_MODAL)).click();

		return flag;
	}

	public static String editTags() throws InterruptedException {

		Thread.sleep(1000);
		// Click on create tag button
		webDB.clickAnElement(MessagingLocators.VIEWTAG, ElementType.Xpath);

		Thread.sleep(1500);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(MessagingLocators.TAG_NAMES_COMMON)));
		tagNames = webDB.driver.findElements(By.xpath(MessagingLocators.TAG_NAMES_COMMON));
		for (int i = 0; i < tagNames.size(); i++) {
			text = tagNames.get(i).getText();
			if (text.contains(TagName)) {
				editButton = webDB.driver.findElements(By.xpath(MessagingLocators.EDIT_BTN));
				editButton.get(i).click();

				// Enter name
				TagName = "AutoTag" + Math.random();
				webDB.waitForElement(MessagingLocators.TAG_NAME_FIELD, ElementType.Xpath);
				element = webDB.driver.findElement(By.xpath(MessagingLocators.TAG_NAME_FIELD));
				element.sendKeys(Keys.chord(Keys.CONTROL, "a"), TagName);

				// Select Category
				category = new Select(webDB.driver.findElement(By.xpath(MessagingLocators.CATEGORY_DROPDOWN)));
				category.selectByVisibleText("Abandoned Cart");
				Thread.sleep(1500);
				// Click on save
				webDB.clickAnElement(MessagingLocators.SAVE_BTN, ElementType.Xpath);
				Thread.sleep(500);
				// Success Popup
				text = webDB.driver.findElement(By.xpath(MessagingLocators.SUCCESS)).getText();

			}
		}

		Thread.sleep(500);
		log.info(text);
		return TagName;
	}

	public static boolean verifyEditTag() throws InterruptedException {
		webDB.waitForElement(MessagingLocators.SETTINGS_BTN, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MessagingLocators.SETTINGS_BTN)).click();
		log.info("Settings SubTab is displayed");

		webDB.waitForElement("//h2[contains(text(),'WhatsApp Configuration')]", ElementType.Xpath);
		createTags();
		Thread.sleep(800);

		editTags();
		Thread.sleep(800);

		deleteTags(TagName);
		Thread.sleep(800);

		// Close modal
//		webDB.waitForElement(ManualMessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
//		webDB.clickAnElement(ManualMessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);

		return flag;
	}

	public static String editTemplate() throws InterruptedException {
		Thread.sleep(1000);
		// Click on create template button
//		buttons = webDB.driver.findElements(By.xpath(ManualMessagingLocators.Buttons));
//		buttons.get(2).click();

		wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(MessagingLocators.TEMPLATE_NAMES_COMMON)));
		templateNames = webDB.driver.findElements(By.xpath(MessagingLocators.TEMPLATE_NAMES_COMMON));
		for (int i = 0; i < templateNames.size(); i++) {
			text = templateNames.get(i).getText();
			if (text.contains(TemplateName)) {
				editButton = webDB.driver.findElements(By.xpath(MessagingLocators.EDIT_BTN));
				editButton.get(i).click();

				Thread.sleep(1500);
				webDB.waitForElement(MessagingLocators.TEMPLATE_NAME_FIELD, ElementType.Xpath);
				Thread.sleep(800);
				// Enter name
				TemplateName = "AutoTemplate" + Math.random();
//	                webDB.sendTextToAnElement(ManualMessagingLocators.TEMPLATE_NAME_FIELD, TemplateName, ElementType.Xpath);
				element = webDB.driver.findElement(By.xpath(MessagingLocators.TEMPLATE_NAME_FIELD));
				webDB.clearText(MessagingLocators.TEMPLATE_NAME_FIELD);
//				element.sendKeys(Keys.chord(Keys.CONTROL, "a"), TemplateName);

				// Enter Message
				webDB.waitForElement(MessagingLocators.TEMPLATE_MSG_FIELD, ElementType.Xpath);
				element = webDB.driver.findElement(By.xpath(MessagingLocators.TEMPLATE_MSG_FIELD));
				webDB.clearText(MessagingLocators.TEMPLATE_MSG_FIELD);
//				element.sendKeys(Keys.chord(Keys.CONTROL, "a"), TemplateMsg);

				// Enter Discount value
				webDB.waitForElement(MessagingLocators.DISCOUNT_VALUE, ElementType.Xpath);
				element = webDB.driver.findElement(By.xpath(MessagingLocators.DISCOUNT_VALUE));
//	                webDB.sendTextToAnElement(ManualMessagingLocators.DISCOUNT_VALUE, discountValue, ElementType.Xpath);
				webDB.clearText(MessagingLocators.DISCOUNT_VALUE);
//				element.sendKeys(Keys.chord(Keys.CONTROL, "a"), discountValue);

				// Select Category
				category = new Select(webDB.driver.findElement(By.xpath(MessagingLocators.CATEGORY_DROPDOWN)));
				category.selectByVisibleText("Abandoned Cart");
				Thread.sleep(1500);

				// Click on save
				webDB.clickAnElement(MessagingLocators.SAVE_BTN, ElementType.Xpath);
				Thread.sleep(500);

				// Success Popup
				text = webDB.driver.findElement(By.xpath(MessagingLocators.SUCCESS)).getText();
				Thread.sleep(500);
				log.info(text);
			}
		}
		return TemplateName;
	}

	public static boolean verifyEditTemplate() throws InterruptedException {
		Thread.sleep(1000);
		webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		webDB.clickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.waitForElement("//strong[contains(text(),'Show Templates: ')]", ElementType.Xpath);
		if (flag) {
			Thread.sleep(1000);
			TemplateName = createTemplate();
			Thread.sleep(800);

			editTemplate();
			Thread.sleep(800);

			deleteTemplate(TemplateName);
		}
		return flag;
	}

	public static boolean verifyNoTagMsg() throws InterruptedException {
		webDB.waitForElement(MessagingLocators.CRM_CART_SUBTAB, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MessagingLocators.CRM_CART_SUBTAB)).click();

		flag = webDB.driver.findElement(By.xpath("//b[contains(text(),'Settings')]")).isDisplayed();
		if (flag) {
			log.info("Order CRM displayed");
		}

		text = webDB.driver.findElement(By.xpath(MessagingLocators.NO_TAG_MSG)).getText();
		if (text.equalsIgnoreCase(NoTagMsg)) {
			log.info("Message Displayed : " + text);
		}
		return flag;
	}

	public static boolean verifyNoTemplateMsg() throws InterruptedException {
		webDB.waitForElement(MessagingLocators.CRM_CART_SUBTAB, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MessagingLocators.CRM_CART_SUBTAB)).click();

		flag = webDB.driver.findElement(By.xpath("//b[contains(text(),'Settings')]")).isDisplayed();
		if (flag) {
			log.info("Order CRM displayed");
		}

		text = webDB.driver.findElement(By.xpath(MessagingLocators.NO_TEMPLATE_MSG)).getText();
		if (text.equalsIgnoreCase(NoTemplateMsg)) {
			log.info("Message Displayed : " + text);
		}
		return flag;
	}

	public static int getTagCount() throws InterruptedException {
		webDB.waitForElement(MessagingLocators.SETTINGS_BTN, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MessagingLocators.SETTINGS_BTN)).click();
		log.info("Settings SubTab is displayed");

		webDB.waitForElement("//h2[contains(text(),'WhatsApp Configuration')]", ElementType.Xpath);

		// Click on view tag button
		buttons = webDB.driver.findElements(By.xpath(MessagingLocators.Buttons));
		buttons.get(0).click();

		Thread.sleep(800);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(MessagingLocators.TAG_NAMES_COMMON)));
		tagNames = webDB.driver.findElements(By.xpath(MessagingLocators.TAG_NAMES_COMMON));
		for (int i = 0; i < tagNames.size(); i++) {
			tagcount = tagNames.size();
		}

		log.info("Tag Count " + tagcount);

		// Close modal
		flag = webDB.waitForElement(MessagingLocators.CANCEL_BTN, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MessagingLocators.CANCEL_BTN)).click();

		return tagcount;
	}

	public static int getTemplateCount() throws InterruptedException {
		webDB.waitForElement(MessagingLocators.SETTINGS_BTN, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MessagingLocators.SETTINGS_BTN)).click();
		log.info("Settings SubTab is displayed");

		webDB.waitForElement("//h2[contains(text(),'WhatsApp Configuration')]", ElementType.Xpath);

		// Click on create template button
		buttons = webDB.driver.findElements(By.xpath(MessagingLocators.Buttons));
		buttons.get(2).click();

		wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(MessagingLocators.TEMPLATE_NAMES_COMMON)));
		templateNames = webDB.driver.findElements(By.xpath(MessagingLocators.TEMPLATE_NAMES_COMMON));
		List<String> ArrTemp = new ArrayList<String>();
		for (int i = 0; i < templateNames.size(); i++) {
			text = templateNames.get(i).getText();
			ArrTemp.add(text);
			tempcount = templateNames.size();
		}

		log.info("Template Count " + tempcount);

		// Close modal
		webDB.waitForElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
		webDB.clickAnElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);

		return tempcount;
	}

	public static boolean verifySendMsgPopup() throws InterruptedException {
		Thread.sleep(3000);
		webDB.waitForElement(MessagingLocators.CRM_CART_SUBTAB, ElementType.Xpath);
		webDB.clickAnElement(MessagingLocators.CRM_CART_SUBTAB, ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath("//tbody/tr[1]/td[5]")).getText();
		System.out.println(text + "   1");
		boolean condition = text.contains(NoTemplateMsg);
		System.out.println(condition);
		if (!condition) {
			webDB.clickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
			Thread.sleep(1000);
			TemplateName = createTemplate();
			Thread.sleep(2000);
		}
		element = webDB.driver.findElement(By.xpath(MessagingLocators.ABANDONED_CART_SUBTAB));
		element.click();
		flag = webDB.driver.findElement(By.xpath("(//b[contains(text(),'Settings')])")).isDisplayed();
		if (flag) {
			log.info("ABANDONED_CART displayed");
			flag = webDB.clickAnElement(MessagingLocators.NAME_FILTER, ElementType.Xpath);
			Dimension d = new Dimension(1920, 1080);
			webDB.driver.manage().window().setSize(d);
			Name = "AUTOUSER";
			log.info("Name send " + Name);
			webDB.driver.findElement(By.xpath(MessagingLocators.NAME_FILTER_TEXTBOX))
					.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			flag = webDB.sendTextToAnElement(MessagingLocators.NAME_FILTER_TEXTBOX, Name, ElementType.Xpath);
			Thread.sleep(800);
			buttons = webDB.driver.findElements(By.xpath(MessagingLocators.SEND_MESSAGE_GENERIC));
			buttons.get(0).click();
			flag = webDB.driver.findElement(By.xpath("//div[@class='Polaris-Modal-Dialog__Modal']")).isDisplayed();
			if (flag) {
				log.info("Send Message popup displayed");
			}
			Thread.sleep(1000);
			webDB.clickAnElement(AutomationLocators.GO_BACK_BTN, ElementType.Xpath);
			Thread.sleep(1000);
			webDB.clickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
			Thread.sleep(1000);
			if (!condition) {
				deleteTemplate(TemplateName);
			}
		}
		return flag;
	}

	public static boolean verifyCloseSendMsgPopup() throws InterruptedException {

		Thread.sleep(3000);
		webDB.waitForElement(MessagingLocators.CRM_CART_SUBTAB, ElementType.Xpath);
		webDB.clickAnElement(MessagingLocators.CRM_CART_SUBTAB, ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath("//tbody/tr[1]/td[5]")).getText();
		System.out.println(text + "   1");
		boolean condition = text.contains(NoTemplateMsg);
		System.out.println(condition);
		if (!condition) {
			webDB.clickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
			Thread.sleep(1000);
			TemplateName = createTemplate();
			Thread.sleep(2000);
		}
		element = webDB.driver.findElement(By.xpath(MessagingLocators.ABANDONED_CART_SUBTAB));
		element.click();
		flag = webDB.driver.findElement(By.xpath("(//b[contains(text(),'Settings')])")).isDisplayed();
		if (flag) {
			log.info("Abandoned Cart displayed");
			flag = webDB.clickAnElement(MessagingLocators.NAME_FILTER, ElementType.Xpath);
			Dimension d = new Dimension(1920, 1080);
			webDB.driver.manage().window().setSize(d);
			Name = "AUTOUSER";
			log.info("Name send " + Name);
			webDB.driver.findElement(By.xpath(MessagingLocators.NAME_FILTER_TEXTBOX))
					.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			flag = webDB.sendTextToAnElement(MessagingLocators.NAME_FILTER_TEXTBOX, Name, ElementType.Xpath);
			Thread.sleep(800);
			buttons = webDB.driver.findElements(By.xpath(MessagingLocators.SEND_MESSAGE_GENERIC));
			buttons.get(0).click();
			flag = webDB.waitForElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
			webDB.driver.findElement(By.xpath(MessagingLocators.CLOSE_VIEW_TAG_MODAL)).click();
			flag = webDB.driver.findElement(By.xpath("(//b[contains(text(),'Settings')])")).isDisplayed();
			if (flag) {
				log.info("Abandoned Cart displayed...Send Message popup closed");
			}
			Thread.sleep(1000);
			webDB.clickAnElement(AutomationLocators.GO_BACK_BTN, ElementType.Xpath);
			Thread.sleep(1000);
			webDB.clickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
			Thread.sleep(1000);
			if (!condition) {
				deleteTemplate(TemplateName);
			}
		}
		return flag;
	}

	public static boolean verifyCancelSendMsgPopup() throws InterruptedException {
		Thread.sleep(3000);
		webDB.waitForElement(MessagingLocators.CRM_CART_SUBTAB, ElementType.Xpath);
		webDB.clickAnElement(MessagingLocators.CRM_CART_SUBTAB, ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath("//tbody/tr[1]/td[5]")).getText();
		System.out.println(text + "   1");
		boolean condition = text.contains(NoTemplateMsg);
		System.out.println(condition);
		if (!condition) {
			webDB.clickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
			Thread.sleep(1000);
			TemplateName = createTemplate();
			Thread.sleep(2000);
		}
		element = webDB.driver.findElement(By.xpath(MessagingLocators.ABANDONED_CART_SUBTAB));
		element.click();
		flag = webDB.driver.findElement(By.xpath("(//b[contains(text(),'Settings')])")).isDisplayed();
		if (flag) {
			log.info("Abandoned Cart displayed");
			flag = webDB.clickAnElement(MessagingLocators.NAME_FILTER, ElementType.Xpath);
			Dimension d = new Dimension(1920, 1080);
			webDB.driver.manage().window().setSize(d);
			Name = "AUTOUSER";
			log.info("Name send " + Name);
			webDB.driver.findElement(By.xpath(MessagingLocators.NAME_FILTER_TEXTBOX))
					.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			flag = webDB.sendTextToAnElement(MessagingLocators.NAME_FILTER_TEXTBOX, Name, ElementType.Xpath);
			Thread.sleep(800);
			buttons = webDB.driver.findElements(By.xpath(MessagingLocators.SEND_MESSAGE_GENERIC));
			buttons.get(0).click();

			flag = webDB.waitForElement(MessagingLocators.CANCEL_BTN, ElementType.Xpath);
			webDB.driver.findElement(By.xpath(MessagingLocators.CANCEL_BTN)).click();
			flag = webDB.driver.findElement(By.xpath("(//b[contains(text(),'Settings')])")).isDisplayed();
			if (flag) {
				log.info("Abandoned Cart displayed...Send Message popup canceled");
			}

			Thread.sleep(1000);
			webDB.clickAnElement(AutomationLocators.GO_BACK_BTN, ElementType.Xpath);
			Thread.sleep(1000);
			webDB.clickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
			Thread.sleep(1000);
			if (!condition) {
				deleteTemplate(TemplateName);
			}
		}
		return flag;
	}

	public static boolean verifySendButtonDisabledOnSendMsgPopup() throws InterruptedException {

		Thread.sleep(3000);
		webDB.waitForElement(MessagingLocators.CRM_CART_SUBTAB, ElementType.Xpath);
		webDB.clickAnElement(MessagingLocators.CRM_CART_SUBTAB, ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath("//tbody/tr[1]/td[5]")).getText();
		System.out.println(text + "   1");
		boolean condition = text.contains(NoTemplateMsg);
		System.out.println(condition);
		if (!condition) {
			webDB.clickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
			Thread.sleep(1000);
			TemplateName = createTemplate();
			Thread.sleep(2000);
		}
		element = webDB.driver.findElement(By.xpath(MessagingLocators.ABANDONED_CART_SUBTAB));
		element.click();
		flag = webDB.driver.findElement(By.xpath("(//b[contains(text(),'Settings')])")).isDisplayed();
		if (flag) {
			log.info("Abandoned Cart displayed");
			flag = webDB.clickAnElement(MessagingLocators.NAME_FILTER, ElementType.Xpath);
			Dimension d = new Dimension(1920, 1080);
			webDB.driver.manage().window().setSize(d);
			Name = "AUTOUSER";
			log.info("Name send " + Name);
			webDB.driver.findElement(By.xpath(MessagingLocators.NAME_FILTER_TEXTBOX))
					.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			flag = webDB.sendTextToAnElement(MessagingLocators.NAME_FILTER_TEXTBOX, Name, ElementType.Xpath);
			Thread.sleep(800);
			buttons = webDB.driver.findElements(By.xpath(MessagingLocators.SEND_MESSAGE_GENERIC));
			buttons.get(0).click();

			element = webDB.driver.findElement(By.xpath(MessagingLocators.SEND_MESSAGE_SEND_BTN));
			flag = (Boolean) js.executeScript("return arguments[0].hasAttribute(\"disabled\");", element);
			log.info("Send message button is " + flag);

			flag = webDB.waitForElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
			webDB.driver.findElement(By.xpath(MessagingLocators.CLOSE_VIEW_TAG_MODAL)).click();

			flag = webDB.driver.findElement(By.xpath("(//b[contains(text(),'Settings')])")).isDisplayed();
			if (flag) {
				log.info("Abandoned Cart displayed...Send Message popup canceled");
			}

			Thread.sleep(1000);
			webDB.clickAnElement(AutomationLocators.GO_BACK_BTN, ElementType.Xpath);
			Thread.sleep(1000);
			webDB.clickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
			Thread.sleep(1000);
			if (!condition) {
				deleteTemplate(TemplateName);
			}
		}
		return flag;
	}

	public static boolean verifyEditTagName() throws InterruptedException {
		webDB.waitForElement(MessagingLocators.SETTINGS_BTN, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MessagingLocators.SETTINGS_BTN)).click();
		log.info("Settings SubTab is displayed");

		TagName = createTags();

		Thread.sleep(2500);
		// Click on view tag button
		webDB.clickAnElement(MessagingLocators.VIEWTAG, ElementType.Xpath);

		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(MessagingLocators.TAG_NAMES_COMMON)));
		tagNames = webDB.driver.findElements(By.xpath(MessagingLocators.TAG_NAMES_COMMON));
		for (int i = 0; i < tagNames.size(); i++) {
			text = tagNames.get(i).getText();
			if (text.contains(TagName)) {
				editButton = webDB.driver.findElements(By.xpath(MessagingLocators.EDIT_BTN));
				editButton.get(i).click();

				// Enter name
				flag = webDB.waitForElement(MessagingLocators.TAG_NAME_FIELD, ElementType.Xpath);
				element = webDB.driver.findElement(By.xpath(MessagingLocators.TAG_NAME_FIELD));
				element.sendKeys(Keys.chord(Keys.CONTROL, "a"), "ABC");
				log.info("Tag name edited");

				Thread.sleep(800);
				// Close modal
				flag = webDB.waitForElement(MessagingLocators.CANCEL_BTN, ElementType.Xpath);
				webDB.driver.findElement(By.xpath(MessagingLocators.CANCEL_BTN)).click();

			}
		}

		Thread.sleep(1500);
		flag = deleteTags(TagName);
		Thread.sleep(1000);
		flag = webDB.waitForElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MessagingLocators.CLOSE_VIEW_TAG_MODAL)).click();

		return flag;
	}

	public static boolean verifyEditTemplatePlaceholders() throws InterruptedException {
		Thread.sleep(1000);
		webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		webDB.clickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.waitForElement("//strong[contains(text(),'Show Templates: ')]", ElementType.Xpath);
		if (flag) {
			createTemplate();
			Thread.sleep(2000);
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath(MessagingLocators.TEMPLATE_NAMES_COMMON)));
			templateNames = webDB.driver.findElements(By.xpath(MessagingLocators.TEMPLATE_NAMES_COMMON));
			for (int i = 0; i < templateNames.size(); i++) {
				text = templateNames.get(i).getText();
				if (text.contains(TemplateName)) {
					editButton = webDB.driver.findElements(By.xpath(MessagingLocators.EDIT_BTN));
					editButton.get(i).click();

					Thread.sleep(1500);
					webDB.waitForElement(MessagingLocators.TEMPLATE_NAME_FIELD, ElementType.Xpath);
					Thread.sleep(800);
					// Enter name
//	                webDB.sendTextToAnElement(ManualMessagingLocators.TEMPLATE_NAME_FIELD, TemplateName, ElementType.Xpath);
					element = webDB.driver.findElement(By.xpath(MessagingLocators.TEMPLATE_NAME_FIELD));
					element.sendKeys(Keys.chord(Keys.CONTROL, "a"), "ABC");
					log.info("Template name edited");

					// Enter Message
					webDB.waitForElement(MessagingLocators.TEMPLATE_MSG_FIELD, ElementType.Xpath);
					element = webDB.driver.findElement(By.xpath(MessagingLocators.TEMPLATE_MSG_FIELD));
					element.sendKeys(Keys.chord(Keys.CONTROL, "a"), "No such message");
					log.info("Template message edited");

					// Enter Discount value
					webDB.waitForElement(MessagingLocators.DISCOUNT_VALUE, ElementType.Xpath);
					webDB.sendTextToAnElement(MessagingLocators.DISCOUNT_VALUE, discountValue, ElementType.Xpath);
					log.info("Template discount edited");

					// Select Category
					category = new Select(webDB.driver.findElement(By.xpath(MessagingLocators.CATEGORY_DROPDOWN)));
					category.selectByVisibleText("Abandoned Cart");
					log.info("Template Category edited");
					Thread.sleep(1500);

					// Close modal
					flag = webDB.waitForElement(MessagingLocators.CANCEL_BTN, ElementType.Xpath);
					webDB.driver.findElement(By.xpath(MessagingLocators.CANCEL_BTN)).click();
				}
			}
		}

		Thread.sleep(2000);
		flag = deleteTemplate(TemplateName);

		return flag;
	}

	public static boolean deleteAllTemplates() throws InterruptedException {

		wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(MessagingLocators.TEMPLATE_NAMES_COMMON)));
		templateNames = webDB.driver.findElements(By.xpath(MessagingLocators.TEMPLATE_NAMES_COMMON));
		log.info(templateNames.size());
		tempcount = templateNames.size();
		for (int i = tempcount - 1; i < tempcount; i--) {
//				log.info(i);
			delteButton = webDB.driver.findElements(By.xpath(MessagingLocators.DELETE_BTN));
			Thread.sleep(3000);
			delteButton.get(i).click();
			Thread.sleep(800);
			wait.until(ExpectedConditions
					.elementToBeClickable(webDB.driver.findElement(By.xpath(MessagingLocators.CONFIRM_BTN))));
			webDB.clickAnElement(MessagingLocators.CONFIRM_BTN, ElementType.Xpath);
			Thread.sleep(500);
			flag = webDB.isElementDisplayed(MessagingLocators.SUCCESS, ElementType.Xpath);
			text = webDB.driver.findElement(By.xpath(MessagingLocators.SUCCESS)).getText();
			Thread.sleep(4000);
			log.info(text);
			if (i == 0)
				break;
		}
		return flag;
	}

	public static boolean verifyTemplateLimit() throws InterruptedException {
		Thread.sleep(1000);
		webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		webDB.clickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		Thread.sleep(1500);
		do {
			Thread.sleep(1000);
			webDB.waitForElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
			webDB.clickAnElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
			Thread.sleep(1000);
			// Enter name
			TemplateName = "AutoTemplate" + Math.random();
			webDB.waitForElement(MessagingLocators.TEMPLATE_NAME_FIELD, ElementType.Xpath);
			webDB.sendTextToAnElement(MessagingLocators.TEMPLATE_NAME_FIELD, TemplateName, ElementType.Xpath);

			// Enter Message
			webDB.waitForElement(MessagingLocators.TEMPLATE_MSG_FIELD, ElementType.Xpath);
			webDB.sendTextToAnElement(MessagingLocators.TEMPLATE_MSG_FIELD, TemplateMsg, ElementType.Xpath);

			// Enter Discount value
			webDB.waitForElement(MessagingLocators.DISCOUNT_VALUE, ElementType.Xpath);
			webDB.sendTextToAnElement(MessagingLocators.DISCOUNT_VALUE, discountValue, ElementType.Xpath);

			// Select Category
			category = new Select(webDB.driver.findElement(By.xpath(MessagingLocators.CATEGORY_DROPDOWN)));
			category.selectByVisibleText("Abandoned Cart");
			Thread.sleep(1000);
			// Click on save
			element = webDB.driver.findElement(By.xpath(MessagingLocators.SAVE_BTN));
			js.executeScript("arguments[0].click();", element);
			Thread.sleep(500);
			// Success Popup
			text = webDB.driver.findElement(By.xpath(MessagingLocators.SUCCESS)).getText();
			Thread.sleep(500);
			log.info(text);
			Thread.sleep(5000);
			// if (text.contains(TempError)) {
			if (text.equalsIgnoreCase(TempError)) {
				Thread.sleep(5000);
				flag = webDB.waitForElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
				webDB.driver.findElement(By.xpath(MessagingLocators.CLOSE_VIEW_TAG_MODAL)).click();
				Thread.sleep(1000);
				break;

			}
		} while (!text.contains(TempError));
		deleteAllTemplates();
//		flag = webDB.waitForElement(ManualMessagingLocators.CANCEL_BTN, ElementType.Xpath);
//		webDB.driver.findElement(By.xpath(ManualMessagingLocators.CANCEL_BTN)).click();
		return flag;
	}

	public static boolean deleteAllTags() throws InterruptedException {
//		buttons = webDB.driver.findElements(By.xpath(ManualMessagingLocators.Buttons));
//		buttons.get(0).click();
		Thread.sleep(2000);
		webDB.driver.findElement(By.xpath("//span[contains(text(),'View Tags')]")).click();
		flag = webDB.waitForElement(MessagingLocators.TAG_NAMES_COMMON, ElementType.Xpath);
		if (flag) {
			tagNames = webDB.driver.findElements(By.xpath(MessagingLocators.TAG_NAMES_COMMON));
			log.info(tagNames.size());
			tagcount = tagNames.size();
			for (int i = tagcount - 1; i < tagcount; i--) {
				if (i == 1 || i == 0) {
//				flag = webDB.waitForElement(ManualMessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
//			if (flag) {
					webDB.clickAnElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
					break;
//			}
				}
//				log.info(i);
				delteButton = webDB.driver.findElements(By.xpath(MessagingLocators.DELETE_BTN));
				Thread.sleep(2000);
				delteButton.get(i).click();
				Thread.sleep(2000);
				wait.until(ExpectedConditions
						.elementToBeClickable(webDB.driver.findElement(By.xpath(MessagingLocators.CONFIRM_BTN))));
				webDB.clickAnElement(MessagingLocators.CONFIRM_BTN, ElementType.Xpath);
				Thread.sleep(500);
				flag = webDB.isElementDisplayed(MessagingLocators.SUCCESS, ElementType.Xpath);
				text = webDB.driver.findElement(By.xpath(MessagingLocators.SUCCESS)).getText();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(MessagingLocators.SUCCESS)));
				log.info(text);

			}
		} else {
			flag = webDB.waitForElement(MessagingLocators.NO_TAG, ElementType.Xpath);
			System.out.println(flag);
			if (flag) {
				webDB.clickAnElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
				flag = webDB.waitForElement("//span[contains(text(),'View Tags')]", ElementType.Xpath);
			}
		}
		return flag;

	}

	public static boolean verifyTagLimit() throws InterruptedException {

		webDB.waitForElement(MessagingLocators.SETTINGS_BTN, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MessagingLocators.SETTINGS_BTN)).click();
		log.info("Settings SubTab is displayed");

		webDB.waitForElement("//h2[contains(text(),'WhatsApp Configuration')]", ElementType.Xpath);

		do {
			// Click on create tag button
//			buttons = webDB.driver.findElements(By.xpath(ManualMessagingLocators.Buttons));
//			buttons.get(1).click();

			webDB.clickAnElement(MessagingLocators.CREATETAG, ElementType.Xpath);

			Thread.sleep(2000);
			// Enter name
			TagName = "AutoTag" + Math.random();
			webDB.waitForElement(MessagingLocators.TAG_NAME_FIELD, ElementType.Xpath);
			webDB.sendTextToAnElement(MessagingLocators.TAG_NAME_FIELD, TagName, ElementType.Xpath);

			// Select Category
			category = new Select(webDB.driver.findElement(By.xpath(MessagingLocators.CATEGORY_DROPDOWN)));
			category.selectByVisibleText("Abandoned Cart");
			Thread.sleep(2000);
			// Click on save
//	        actions.moveToElement(webDB.driver.findElement(By.xpath(ManualMessagingLocators.SAVE_BTN))).click().build().perform();
			element = webDB.driver.findElement(By.xpath(MessagingLocators.SAVE_BTN));
			js.executeScript("arguments[0].click();", element);
			Thread.sleep(1000);
			// Success Popup
			text = webDB.driver.findElement(By.xpath(MessagingLocators.SUCCESS)).getText();
			Thread.sleep(1000);
			log.info(text);
			Thread.sleep(5000);

			if (text.contains(TagError)) {
				flag = webDB.waitForElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
				webDB.driver.findElement(By.xpath(MessagingLocators.CLOSE_VIEW_TAG_MODAL)).click();
				Thread.sleep(1000);

			}
		} while (!text.contains(TagError));

		deleteAllTags();
//		flag = webDB.waitForElement(ManualMessagingLocators.CANCEL_BTN, ElementType.Xpath);
//		actions.moveToElement(webDB.driver.findElement(By.xpath(ManualMessagingLocators.CANCEL_BTN))).click().build()
//				.perform();
		return flag;
	}

	public static boolean verifySendMessage() throws InterruptedException, FileNotFoundException, IOException {
		Thread.sleep(4000);
		webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES)).click();
		log.info("Abcart is displayed");
		TemplateName = createTemplate();
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
//		Name = "AUTOUSER";

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
		flag = webDB.driver.findElement(By.xpath("//h1[contains(text(),'Messaging')]")).isDisplayed();
		if (flag) {
			log.info("Abandoned Cart displayed");
		}

		webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES)).click();
		log.info("Extension Template is displayed");

//		webDB.waitForElement("//h2[contains(text(),'WhatsApp Configuration')]", ElementType.Xpath);

		deleteTemplate(TemplateName);
//		flag = webDB.waitForElement(ManualMessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
//		webDB.driver.findElement(By.xpath(ManualMessagingLocators.CLOSE_VIEW_TAG_MODAL)).click();

		return flag;
	}

	public static boolean verifySendTemplateRedirectWApage()
			throws InterruptedException, FileNotFoundException, IOException {
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

		Name = "AUTOUSER";
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
		deleteTemplate(TemplateName);
		Thread.sleep(800);
//		flag = webDB.waitForElement(ManualMessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
//		webDB.driver.findElement(By.xpath(ManualMessagingLocators.CLOSE_VIEW_TAG_MODAL)).click();
		return flag;
	}

	public static boolean verifySendTemplateRedirectWAapp()
			throws InterruptedException, FileNotFoundException, IOException {
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

		createTemplate();

		Thread.sleep(2000);
		js.executeScript("scroll(0, -250);");

		element = webDB.driver.findElement(By.xpath(MessagingLocators.ABANDONED_CART_SUBTAB));
		element.click();
		flag = webDB.driver.findElement(By.xpath("//h1[contains(text(),'Messaging')]")).isDisplayed();
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

		Name = "AUTOUSER";
		log.info("Name send " + Name);
		webDB.driver.findElement(By.xpath(MessagingLocators.NAME_FILTER_TEXTBOX))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		flag = webDB.sendTextToAnElement(MessagingLocators.NAME_FILTER_TEXTBOX, Name, ElementType.Xpath);

		Thread.sleep(800);

		buttons = webDB.driver.findElements(By.xpath(MessagingLocators.SEND_MESSAGE_GENERIC));
		buttons.get(0).click();

		// Select Category
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
		flag = webDB.driver.findElement(By.xpath("//h1[contains(text(),'Messaging')]")).isDisplayed();
		if (flag) {
			log.info("Abandoned Cart displayed");
		}

		Thread.sleep(4000);
		webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES)).click();
		Thread.sleep(800);
		deleteTemplate(TemplateName);
		Thread.sleep(800);
//		flag = webDB.waitForElement(ManualMessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
//		webDB.driver.findElement(By.xpath(ManualMessagingLocators.CLOSE_VIEW_TAG_MODAL)).click();
		return flag;
	}

	public static boolean verifyMessageSubSection() throws InterruptedException {
		webDB.waitForElement(MessagingLocators.ABANDONED_CART_SUBTAB, ElementType.Xpath);
		Thread.sleep(2000);
		flag = webDB.verifyElementIsDisplayed(MessagingLocators.ABANDONED_CART_SUBTAB, ElementType.Xpath);
		if (flag) {
			flag = webDB.verifyElementIsDisplayed(MessagingLocators.CRM_CART_SUBTAB, ElementType.Xpath);
			Thread.sleep(2000);
			if (flag) {
				flag = webDB.verifyElementIsDisplayed(MessagingLocators.SETTINGS_SUBTAB, ElementType.Xpath);
				if (flag) {
					flag = webDB.verifyElementIsDisplayed(MessagingLocators.MESSAGE_TEMPLATE_SUBTAB, ElementType.Xpath);
					if (flag) {
						// modified - commented below
//						flag = webDB.verifyElementIsDisplayed(MessagingLocators.MESSAGING_PROMOTIONAL_TILE,
//								ElementType.CssSelector);
						log.info("Verified sub section in messaging tab");
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyMessageInstallbuttontutorial() throws InterruptedException {
		parentWindow = webDB.driver.getWindowHandle();
		flag = webDB.waitForElement(MessagingLocators.INSTALLNOW, ElementType.Xpath);
		Thread.sleep(2000);
		if (flag) {
			webDB.clickAnElement(MessagingLocators.INSTALLNOW, ElementType.Xpath);
			flag = webDB.waitForElement(MessagingLocators.INSTALLEXTENSION, ElementType.Xpath);
			Thread.sleep(2000);
			if (flag) {
				webDB.clickAnElement(MessagingLocators.INSTALLEXTENSION, ElementType.Xpath);
				Thread.sleep(2000);
				webDB.switchToChildWindow();
				String url = webDB.driver.getCurrentUrl();
				flag = url.contains(MessagingLocators.CHROMEURL);
				if (flag) {
					log.info("Verified Url");
					webDB.driver.close();
					webDB.driver.switchTo().window(parentWindow);
					flag = webDB.waitForElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
					Thread.sleep(2000);
					if (flag) {
						webDB.clickAnElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
						webDB.waitForElement(MessagingLocators.SEETUTORIALBTN, ElementType.Xpath);
						webDB.clickAnElement(MessagingLocators.SEETUTORIALBTN, ElementType.Xpath);
						Thread.sleep(2000);
						flag = webDB.isElementDisplayed(MessagingLocators.IFRAMEYOUTUBEPLAYER, ElementType.CssSelector);
						if (flag) {
							flag = webDB.clickAnElement(MessagingLocators.CLOSE_VIEW_TAG_MODAL, ElementType.Xpath);
							System.out.println("Verified the Youtube tutorial");
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyClosePromotionaltile() throws InterruptedException {
		flag = webDB.waitForElement(MessagingLocators.CLOSEPROMOTIONALTILE, ElementType.CssSelector);
		Thread.sleep(2000);
		if (flag) {
			webDB.clickAnElement(MessagingLocators.CLOSEPROMOTIONALTILE, ElementType.CssSelector);
			flag = webDB.isElementDisplayed(MessagingLocators.CLOSEPROMOTIONALTILE, ElementType.CssSelector);
			System.out.println(flag);
			if (!flag) {
				log.info("Verified Promotional tile is Closed");
				flag = true;
			}
		}
		return flag;
	}

	public static String createCartTags() throws InterruptedException {
		Thread.sleep(1000);
//		// Click on create tag button
//		webDB.driver.findElement(By.xpath("//h2[contains(text(),'WhatsApp Configuration')]")).click();
//		Thread.sleep(1000);
		// Enter name
		webDB.scrollToAnElement(MessagingLocators.CREATETAG, ElementType.Xpath);
		flag = webDB.waitForElement(MessagingLocators.CREATETAG, ElementType.Xpath);
		if (flag) {
			Thread.sleep(1000);
			webDB.clickAnElement(MessagingLocators.CREATETAG, ElementType.Xpath);
			TagName = "AutoTag" + Math.random();
			webDB.waitForElement(MessagingLocators.TAG_NAME_FIELD, ElementType.Xpath);
			webDB.sendTextToAnElement(MessagingLocators.TAG_NAME_FIELD, TagName, ElementType.Xpath);

			// Select Category
			category = new Select(webDB.driver.findElement(By.xpath(MessagingLocators.CATEGORY_DROPDOWN)));
			category.selectByVisibleText("Abandoned Cart");
			log.info("Abandoned Cart");
			Thread.sleep(1000);
			// Click on save
			webDB.waitForElement(MessagingLocators.SAVE_BTN, ElementType.Xpath);
			webDB.clickAnElement(MessagingLocators.SAVE_BTN, ElementType.Xpath);
			Thread.sleep(500);
			// Success Popup
			text = webDB.driver.findElement(By.xpath(MessagingLocators.SUCCESS)).getText();
			Thread.sleep(500);
			log.info(text);
		}
		return TagName;
	}

	public static boolean verifyTagsinAB_CART() throws InterruptedException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2500);
		flag = webDB.waitForElement(MessagingLocators.SETTINGS_BTN, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MessagingLocators.SETTINGS_BTN, ElementType.Xpath);
			log.info("Settings SubTab is displayed");
			String Tagname = createCartTags();
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
									flag = webDB.waitForElement(MessagingLocators.SETTINGS_BTN, ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(MessagingLocators.SETTINGS_BTN, ElementType.Xpath);
										deleteTags(Tagname);
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

	public static boolean verifyApplyTagsinAB_CART() throws InterruptedException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2500);
		flag = webDB.waitForElement(MessagingLocators.ADDTAG, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MessagingLocators.ADDTAG, ElementType.Xpath);
			log.info("Clicked add tag");
			flag = webDB.waitForElement(MessagingLocators.POUUP, ElementType.CssSelector);
			log.info("Pop up is visible");
			if (flag) {
				webDB.clickAnElement(MessagingLocators.SELECTVALUE, ElementType.Xpath);
				webDB.selectDropDownOptionsByText(MessagingLocators.SELECTVALUE, MessagingLocators.TAGNAME,
						ElementType.Xpath);
				log.info("Drop down value is selected");
				Thread.sleep(2000);
				webDB.clickAnElement(MessagingLocators.ADDTAG_BTN, ElementType.Xpath);
				log.info("Clicked add tag button");
				Thread.sleep(2000);
				flag = webDB.verifyElementIsDisplayed("//span[contains(text(),'" + MessagingLocators.TAGNAME + "')]",
						ElementType.Xpath);
				if (flag) {
					log.info("Verified user is able to apply tag in ab cart");
					flag = webDB.waitForElement(MessagingLocators.TAG_DROPDOWN, ElementType.CssSelector);
					if (flag) {
						webDB.clickAnElement(MessagingLocators.TAG_DROPDOWN, ElementType.CssSelector);
						Thread.sleep(2000);
						webDB.clickAnElement("//span[contains(text(),'" + MessagingLocators.TAGNAME + "')]",
								ElementType.Xpath);
						flag = webDB.verifyElementIsDisplayed(MessagingLocators.TAGSCOLOUMN, ElementType.Xpath);
						if (flag) {
							String tagname = webDB.getText(MessagingLocators.TAGSCOLOUMN, ElementType.Xpath);
							System.out.println(tagname);
							if (tagname.equals(MessagingLocators.TAGNAME)) {
								log.info("Verified user is able to filter ab cart order using tag");
								webDB.clickAnElement(MessagingLocators.REMOVETAG, ElementType.Xpath);
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
			flag = webDB.IsEnabled(MessagingLocators.NEW_CRMTOOL_INPUT, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(MessagingLocators.NEW_CRMTOOL_TOGGLE, ElementType.Xpath);
				log.info("New CRM tool disabled");
			}
			String Templatename = createABcartTemplate();
			flag = webDB.waitForElement(MessagingLocators.ABANDONED_CART_SUBTAB, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(MessagingLocators.ABANDONED_CART_SUBTAB, ElementType.Xpath);
				flag = webDB.waitForElement(MessagingLocators.NAME_FILTER, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(MessagingLocators.NAME_FILTER, ElementType.Xpath);
					flag = webDB.waitForElement(MessagingLocators.CLEAR, ElementType.Xpath);
					if (flag) {
						webDB.clearText(MessagingLocators.NAME_FILTER_TEXTBOX);
						webDB.sendTextToAnElement(MessagingLocators.NAME_FILTER_TEXTBOX, MessagingLocators.USERNAME,
								ElementType.Xpath);
						Thread.sleep(1500);
						// modified - commented below and added click search
//					webDB.pressEnterKey();
						webDB.clickAnElement(MessagingLocators.SEARCH_NAME, ElementType.Xpath);
						webDB.scrollToAnElement(MessagingLocators.SENDMESSAGE, ElementType.Xpath);
						flag = webDB.verifyElementIsDisplayed(MessagingLocators.ADDTAG, ElementType.Xpath);
						if (flag) {
							flag = webDB.waitForElement(MessagingLocators.SENDMESSAGE, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(MessagingLocators.SENDMESSAGE, ElementType.Xpath);
							}
						} else {
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
								deleteTemplate(Templatename);
							}
						}
					}
				}
			}
		}
		return flag;
	}

	public static String createorderTags() throws InterruptedException {
		Thread.sleep(1000);
		// Click on create tag button
//		webDB.driver.findElement(By.xpath("//h2[contains(text(),'WhatsApp Configuration')]")).click();
		Thread.sleep(1000);
		// Enter name
		webDB.scrollToAnElement(MessagingLocators.CREATETAG, ElementType.Xpath);
		flag = webDB.waitForElement(MessagingLocators.CREATETAG, ElementType.Xpath);
		if (flag) {
			Thread.sleep(1000);
			webDB.clickAnElement(MessagingLocators.CREATETAG, ElementType.Xpath);
			TagName = "AutoTag" + Math.random();
			webDB.waitForElement(MessagingLocators.TAG_NAME_FIELD, ElementType.Xpath);
			webDB.sendTextToAnElement(MessagingLocators.TAG_NAME_FIELD, TagName, ElementType.Xpath);

			// Select Category
			category = new Select(webDB.driver.findElement(By.xpath(MessagingLocators.CATEGORY_DROPDOWN)));
			flag = category.getOptions().equals("Orders");
			category.selectByVisibleText("Orders");
			log.info("Category Orders ");
//			flag = category.getOptions().equals("Abandoned Cart");
//			category.selectByVisibleText("Abandoned Cart");
//			log.info("Abandoned Cart " + flag);
			Thread.sleep(1000);
			// Click on save
			webDB.waitForElement(MessagingLocators.SAVE_BTN, ElementType.Xpath);
			webDB.clickAnElement(MessagingLocators.SAVE_BTN, ElementType.Xpath);
			Thread.sleep(500);
			// Success Popup
			text = webDB.driver.findElement(By.xpath(MessagingLocators.SUCCESS)).getText();
			Thread.sleep(500);
			log.info(text);
		}
		return TagName;
	}

	public static boolean verifyTagsinOrderCRM() throws InterruptedException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2500);
		flag = webDB.waitForElement(MessagingLocators.SETTINGS_BTN, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MessagingLocators.SETTINGS_BTN, ElementType.Xpath);
			log.info("Settings SubTab is displayed");
			String Tagname = createorderTags();
			flag = webDB.waitForElement(MessagingLocators.CRM_CART_SUBTAB, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(MessagingLocators.CRM_CART_SUBTAB, ElementType.Xpath);
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
									log.info("Verified All tags are visible in OrderCRM");
									flag = webDB.waitForElement(MessagingLocators.SETTINGS_BTN, ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(MessagingLocators.SETTINGS_BTN, ElementType.Xpath);
										deleteTags(Tagname);
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

	public static boolean verifyApplyTagsinOrderCRM() throws InterruptedException {
		flag = webDB.waitForElement(MessagingLocators.SETTINGS_BTN, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MessagingLocators.SETTINGS_BTN, ElementType.Xpath);
			String Tagname = createTags();
			flag = webDB.waitForElement(MessagingLocators.CRM_CART_SUBTAB, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				webDB.javaScriptClickAnElement(MessagingLocators.CRM_CART_SUBTAB, ElementType.Xpath);
				flag = webDB.waitForElement(MessagingLocators.ADDTAG, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(MessagingLocators.ADDTAG, ElementType.Xpath);
					flag = webDB.waitForElement(MessagingLocators.POUUP, ElementType.CssSelector);
					if (flag) {
						webDB.clickAnElement(MessagingLocators.SELECTVALUE, ElementType.Xpath);
						webDB.selectDropDownOptionsByText(MessagingLocators.SELECTVALUE, Tagname, ElementType.Xpath);
						Thread.sleep(2000);
						System.out.println(Tagname);
						webDB.clickAnElement(MessagingLocators.ADDTAG_BTN, ElementType.Xpath);
						Thread.sleep(2000);
						flag = webDB.verifyElementIsDisplayed("//span[contains(text(),'" + Tagname + "')]",
								ElementType.Xpath);
						if (flag) {
							flag = webDB.waitForElement(MessagingLocators.TAG_DROPDOWN, ElementType.CssSelector);
							if (flag) {
								webDB.clickAnElement(MessagingLocators.TAG_DROPDOWN, ElementType.CssSelector);
								Thread.sleep(2000);
								webDB.clickAnElement("//span[contains(text(),'" + Tagname + "')]", ElementType.Xpath);
								flag = webDB.verifyElementIsDisplayed(MessagingLocators.TAGSCOLOUMN, ElementType.Xpath);
								if (flag) {
									String tagname = webDB.getText(MessagingLocators.TAGSCOLOUMN, ElementType.Xpath);
									System.out.println(tagname);
									if (tagname.equals(Tagname)) {
										log.info("Verified user is able to filter order in orderCRM using tag");
										webDB.clickAnElement(MessagingLocators.REMOVETAG, ElementType.Xpath);
										flag = webDB.waitForElement(MessagingLocators.SETTINGS_BTN, ElementType.Xpath);
										if (flag) {
											webDB.clickAnElement(MessagingLocators.SETTINGS_BTN, ElementType.Xpath);
											deleteTags(Tagname);
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
							deleteTemplate(Templatename);
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

			webDB.waitForElement(MessagingLocators.DISCOUNT_VALUE, ElementType.Xpath);
			webDB.sendTextToAnElement(MessagingLocators.DISCOUNT_VALUE, discountValue, ElementType.Xpath);

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

			// Enter Discount value
			webDB.waitForElement(MessagingLocators.DISCOUNT_VALUE, ElementType.Xpath);
			webDB.sendTextToAnElement(MessagingLocators.DISCOUNT_VALUE, discountValue, ElementType.Xpath);

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

	public static String createQuickreplyTemplate() throws InterruptedException {
		webDB.driver.navigate().refresh();
		Thread.sleep(2500);
		webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		webDB.clickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		Thread.sleep(3000);
		flag = webDB.waitForElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
			Thread.sleep(1000);
			// Select Category
			category = new Select(webDB.driver.findElement(By.xpath(MessagingLocators.CATEGORY_DROPDOWN)));
			category.selectByVisibleText("Quick Reply");
			log.info("Quickreply");
			category = new Select(
					webDB.driver.findElement(By.xpath(MessagingLocators.QUICKREPLY_2ndCATEGORY_DROPDOWN)));
			category.selectByVisibleText("Quick Reply");
			log.info("2nd Quickreply");
			Thread.sleep(1000);
			// Enter name
			TemplateName = "AutoTemplate" + Math.random();
			webDB.waitForElement(MessagingLocators.TEMPLATE_NAME_FIELD, ElementType.Xpath);
			webDB.sendTextToAnElement(MessagingLocators.TEMPLATE_NAME_FIELD, TemplateName, ElementType.Xpath);

			// Enter Message
			webDB.waitForElement(MessagingLocators.TEMPLATE_MSG_FIELD, ElementType.Xpath);
			webDB.sendTextToAnElement(MessagingLocators.TEMPLATE_MSG_FIELD, TemplateMsg, ElementType.Xpath);

			Thread.sleep(1000);
			// Click on save
			flag = webDB.waitForElement(MessagingLocators.SAVE_BTN, ElementType.Xpath);
			webDB.clickAnElement(MessagingLocators.SAVE_BTN, ElementType.Xpath);
			Thread.sleep(500);
			// Success pop-up
			text = webDB.driver.findElement(By.xpath(MessagingLocators.SUCCESS)).getText();
			Thread.sleep(500);
			log.info(text);
			if (text.equals(MessagingLocators.TEMPLATE_CREATED)) {
				log.info("Quickreply template is created");
			}
		}
		return TemplateName;
	}

	public static boolean verifyQuickReplytemplate() throws InterruptedException {
		flag = webDB.waitForElement(MessagingLocators.NEW_CRMTOOL_INPUT, ElementType.Xpath);
		if (flag) {
			flag = webDB.IsEnabled(MessagingLocators.NEW_CRMTOOL_INPUT, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(MessagingLocators.NEW_CRMTOOL_TOGGLE, ElementType.Xpath);
				webDB.driver.navigate().refresh();
				log.info("New CRM tool disabled");
			}
			Quickreplytemplatename = createQuickreplyTemplate();
			log.info("Created quick reply template name is " + Quickreplytemplatename);
			ABcarttemplatename = createABcartTemplate();
			log.info("Created abcart template name is " + ABcarttemplatename);
			OrderCRMtemplatename = createOrderCRMTemplate();
			log.info("Created order crm template name is " + OrderCRMtemplatename);
			webDB.driver.navigate().refresh();
			Thread.sleep(2500);
			webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
			webDB.clickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
			flag = webDB.waitForElement(MessagingLocators.QUICKREPLY_TEMPLATE_BTN, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(MessagingLocators.QUICKREPLY_TEMPLATE_BTN, ElementType.Xpath);
				templateNames = webDB.driver.findElements(By.xpath(MessagingLocators.TEMPLATE_NAMES_COMMON));
				for (int i = 0; i < templateNames.size(); i++) {
					text = templateNames.get(i).getText();
					System.out.println(text);
					if (text.contains(Quickreplytemplatename)) {
						log.info("Template Name : " + Quickreplytemplatename + " is displayed");
						log.info("Verified created QuickReply templates are shown in QuickReply section");
					}
				}
			}

			flag = webDB.waitForElement(MessagingLocators.ALLTEMPLATE_BTN, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(MessagingLocators.ALLTEMPLATE_BTN, ElementType.Xpath);
				templateNames = webDB.driver.findElements(By.xpath(MessagingLocators.TEMPLATE_NAMES_COMMON));
				for (int j = 0; j < templateNames.size(); j++) {
					text = templateNames.get(j).getText();
					if (text.contains(ABcarttemplatename)) {
						System.out.println(text);
						log.info("Template Name : " + ABcarttemplatename + " is displayed");
						log.info("Verified created ABcart templates are shown in all section");
					}
					if (text.contains(OrderCRMtemplatename)) {
						System.out.println(text);
						log.info("Template Name : " + OrderCRMtemplatename + " is displayed");
						log.info("Verified created OrderCRM templates are shown in all section");
					}
					if (text.contains(Quickreplytemplatename)) {
						System.out.println(text);
						log.info("Template Name : " + Quickreplytemplatename + " is displayed");
						log.info("Verified created Quickreply templates are shown in all section");
					}
				}
			}
		}
		AutomationPage.deleteExtensionTemplate();
		return flag;
	}

	public static boolean verifyAfterTemplateCreated(String templateName) {
		String template_category = webDB.driver
				.findElement(By.xpath("//h2[contains(text(),'" + templateName + "')]/following::span[1]")).getText();
		System.out.println("Template category is " + template_category);
		if (template_category.equals("Quick Reply")) {
			log.info("Template category is verified");
			String template_message = webDB.driver
					.findElement(By.xpath("//h2[text()='" + templateName + "']/following::div[6]/span/span")).getText();
			System.out.println("Template message is " + template_message);
			if (template_message.equals(TemplateDesc)) {
				log.info("Template message is verified");
				WebElement template_enable_button = webDB.driver
						.findElement(By.xpath("//h2[text()='" + templateName + "']/..//input"));
				if (template_enable_button.isEnabled()) {
					log.info("Template button is enabled");
					flag = true;
				}
			}
		}
		return flag;
	}
}
