package commonFunctions;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import locators.ShopifyStoreLocators;
import locators.WhatsappExtensionLocators;
import utils.DriverBase;
import utils.DriverBase.ElementType;

@SuppressWarnings({ "static-access", "unused" })
public class WhatsappExtensionPage {

	private static Logger log = Logger.getLogger(WhatsappExtensionPage.class);
	private static String propertyFilePath;
	private static boolean flag;
	private static String url;
	public static Properties prop = new Properties();
	private static DriverBase webDB = new DriverBase();
	private static String templateName;
	private static String text;
	private static String TemplateDesc = "This is a manual messaging Template";
	private static List<WebElement> templateNames;
	private static String editedTemplateName;
	private static Actions actions = new Actions(webDB.driver);
	private static List<WebElement> delteButton;
	private static Select category;
	private static WebDriverWait wait = new WebDriverWait(webDB.driver, 10);
	private static JavascriptExecutor js = (JavascriptExecutor) webDB.driver;
	private static WebElement element;
	private static List<WebElement> editButton;
	private static List<WebElement> elements;
	private static Set<String> windows;
	private static Iterator<String> iterator;
	private static String parentWindow;
	private static String childWindow;
	private static List<WebElement> productName = webDB.driver
			.findElements(By.xpath(WhatsappExtensionLocators.PRODUCT_NAME_COMMON));
	private static List<WebElement> quickReplyTemplate = webDB.driver
			.findElements(By.xpath(WhatsappExtensionLocators.QUICK_REPLIES_NAMES_COMMON));
	private static String quickReplyTemplateName = "QRTemplate" + RandomStringUtils.randomAlphanumeric(5);
	private static String quickReplyTemplateText = "Hi {{customerName}}  Your oder  {{name}}  has been confirmed.Please use {{trackingUrl}}  to check the {{total}} of the order and also the {{status}}. The Automation template Applied";
	private static String firstName = "AutoUser";
	private static String lastName = RandomStringUtils.randomNumeric(5);
	private static String emailId = "qatest@gupshup.io";
	private static String shippingAddress = "Gupshup Technology India Private Limited 11th Main Road 2nd Phase Jayanagar";
	private static String phone = "9439770090";

	public static boolean verifyExtensionLogin() throws InterruptedException, IOException {

		Thread.sleep(3000);
//        CommonFunctions.verifyLoginPage("accessToken");
//        CommonFunctions.verifyPersonalWidgetPage();
//        PersonalWidgetPage.personalWidgetPageToStoreNavigation();
		webDB.driver.get("https://web.whatsapp.com/");
		element = webDB.driver.findElement(By.xpath(WhatsappExtensionLocators.SHOP_NAME_LOGIN_POPUP));
		if (element.isDisplayed()) {
			js.executeScript("arguments[0].click();", element);
			webDB.driver.findElement(By.xpath(WhatsappExtensionLocators.SHOP_NAME_LOGIN_POPUP)).sendKeys("qaprod45");
			parentWindow = webDB.driver.getWindowHandle();
			webDB.clickAnElement(WhatsappExtensionLocators.LOGIN_BUTTON, ElementType.Xpath);
			Thread.sleep(3000);
			windows = webDB.driver.getWindowHandles();
			iterator = windows.iterator();
			while (iterator.hasNext()) {
				childWindow = iterator.next();
				if (!parentWindow.equals(childWindow)) {
					webDB.driver.switchTo().window(childWindow);
					log.info("reached here");
					log.info("reached here as well");
					Thread.sleep(3000);
					element = webDB.driver.findElement(By.xpath("//input[@id='account_email']"));
					if (element.isDisplayed()) {
						webDB.driver.findElement(By.xpath("//input[@id='account_email']"))
								.sendKeys("bottest.gupshup.101@gmail.com");
						Thread.sleep(3000);
						webDB.driver.findElement(By.xpath("//button[contains(@class,'captcha__submit')]")).click();
						webDB.driver.findElement(By.xpath("//input[@id='account_password']"))
								.sendKeys("=watd\\vZz!$&UZMx");
						Thread.sleep(3000);
						webDB.driver.findElement(By.xpath("//button[contains(@class,'captcha__submit')]")).click();
						log.info("Reached Here");
						webDB.driver.switchTo().window(parentWindow);
					}
				}
			}
		}
		flag = webDB.isElementDisplayed(WhatsappExtensionLocators.ORDER_ID, ElementType.Id);
		log.info("Logged in to Extension successfully");
		return flag;
	}

	public static boolean verifyExtensionLogout() throws InterruptedException, IOException {
		webDB.driver.findElement(By.xpath(WhatsappExtensionLocators.MAIN_ICON)).click();
		webDB.driver.findElement(By.xpath(WhatsappExtensionLocators.LOGOUT_BUTTON)).click();
		flag = webDB.isElementDisplayed(WhatsappExtensionLocators.SHOP_NAME_LOGIN_POPUP, ElementType.Xpath);
		return flag;
	}

	public static boolean verifyQuickReplyCreationOnExtension() throws InterruptedException, IOException {
		WidgetPage.personalWidgetPageToStoreNavigation();
		ShopifyStore.shopifyStorePassEnter();
		ShopifyStore.addItemAndCheckout();
		WhatsappExtensionPage.enterShippingDetailsWithValidPhone();
		ShopifyStore.enterCardDetails();
		webDB.clickAnElement(ShopifyStoreLocators.CONTINUE_BTN, ElementType.Xpath);
		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath(ShopifyStoreLocators.ORDER_CONFIRMED_MESSAGE)));
		verifyExtensionLogin();
		webDB.clickAnElement(WhatsappExtensionLocators.FIRST_ORDER_PRODUCT, ElementType.Xpath);
		Thread.sleep(3000);
		webDB.clickAnElement(WhatsappExtensionLocators.CREATE_NEW_TEMPLATE_ON_EXTENSION, ElementType.Id);
		Thread.sleep(3000);
		webDB.sendTextToAnElement(WhatsappExtensionLocators.TEMPLATE_NAME_QUICK_REPLIES, quickReplyTemplateName,
				ElementType.Id);
		Thread.sleep(3000);
		webDB.sendTextToAnElement(WhatsappExtensionLocators.TEMPLATE_TEXT, quickReplyTemplateText, ElementType.Id);
		Thread.sleep(3000);
		webDB.clickAnElement(WhatsappExtensionLocators.DONE_BUUTON, ElementType.Id);
		Thread.sleep(3000);
		log.info(quickReplyTemplate.size());
		for (int i = 0; i < quickReplyTemplate.size(); i++) {
			text = quickReplyTemplate.get(i).getText();
			if (text.equalsIgnoreCase(quickReplyTemplateName)) {
				flag = true;
				log.info("Quick Reply template Created successfully from Extension");
				break;
			}
		}
		return flag;
	}

	public static boolean createExtensionTemplatesAndCheckOnExtension() throws IOException, InterruptedException {
//        templateName = TemplatesPage.createExtensiontemplate();
		templateName = "AutoTemplate0.008286832142361478";
		verifyExtensionLogin();
		Thread.sleep(5000);
		flag = webDB.isElementDisplayed(WhatsappExtensionLocators.CREATE_NEW_TEMPLATE_ON_EXTENSION, ElementType.Xpath);
		log.info(flag);
		Thread.sleep(5000);
		log.info(quickReplyTemplate.size());
		for (int i = 0; i < quickReplyTemplate.size(); i++) {
			text = quickReplyTemplate.get(i).getText();
			log.info(text);
			if (text.equalsIgnoreCase(quickReplyTemplateName)) {
				flag = true;
				log.info("Quick Reply template Created successfully from Extension");
				break;
			}
		}
		return flag;

	}

	public static boolean verifyOrderTemplateOnExtension() throws InterruptedException, IOException {
		WidgetPage.personalWidgetPageToStoreNavigation();
		ShopifyStore.shopifyStorePassEnter();
		ShopifyStore.addItemAndCheckout();
		WhatsappExtensionPage.enterShippingDetailsWithValidPhone();
		ShopifyStore.enterCardDetails();
		webDB.clickAnElement(ShopifyStoreLocators.CONTINUE_BTN, ElementType.Xpath);
		Thread.sleep(8000);
		verifyExtensionLogin();
		Thread.sleep(3000);
		log.info("reached here");
		webDB.clickAnElement(WhatsappExtensionLocators.FIRST_ORDER_PRODUCT, ElementType.Xpath);
		log.info("reached here");
		webDB.clickAnElement(WhatsappExtensionLocators.DROPDOWN_ORDER_TEMPLATE_SELECT, ElementType.Xpath);
		Thread.sleep(2000);
		webDB.clickAnElement(WhatsappExtensionLocators.ORDER_TEMPLATE_SELECTION, ElementType.Xpath);
		webDB.clickAnElement(WhatsappExtensionLocators.APPLY_BUTTON, ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath(WhatsappExtensionLocators.MESSAGE_BODY_WHATSAPP_CHAT)).getText();
		log.info(text);
		String templateText = "The Automation template Applied";
		if (text.equalsIgnoreCase(templateText)) {
			flag = true;
			log.info("Order template applied successfully");
		}
		Thread.sleep(30000);
		return flag;
	}

	public static boolean verifyPhoneNumberNotAssociatedIssue() throws InterruptedException, IOException {
		WidgetPage.personalWidgetPageToStoreNavigation();
		ShopifyStore.shopifyStorePassEnter();
		ShopifyStore.addItemAndCheckout();
		ShopifyStore.enterShippingDetailsWithEmail();
		ShopifyStore.enterCardDetails();
		webDB.clickAnElement(ShopifyStoreLocators.CONTINUE_BTN, ElementType.Xpath);
		verifyExtensionLogin();
		Thread.sleep(3000);
		webDB.clickAnElement(WhatsappExtensionLocators.FIRST_ORDER_PRODUCT, ElementType.Xpath);
		flag = webDB.isElementDisplayed(WhatsappExtensionLocators.ERROR_MESSAGE_AREA, ElementType.Xpath);
		log.info(flag);
		if (flag) {
			text = webDB.driver.findElement(By.xpath(WhatsappExtensionLocators.ERROR_MESSAGE_AREA)).getText();
			flag = text.equalsIgnoreCase("No Phone Number Associated with the Order");
			{
				log.info("Phone Number Validation Given");
			}
		}
		return flag;
	}

	public static boolean verifyAbandonedCartIndividualorders() throws IOException, InterruptedException {
		WidgetPage.personalWidgetPageToStoreNavigation();
		ShopifyStore.shopifyStorePassEnter();
		ShopifyStore.addItemAndCheckout();
		String fullName = firstName + lastName;
		WhatsappExtensionPage.enterShippingDetailsWithValidPhone();
		ShopifyStore.enterCardDetails();
		webDB.clickAnElement(ShopifyStoreLocators.CONTINUE_BTN, ElementType.Xpath);
		verifyExtensionLogin();
		webDB.clickAnElement(WhatsappExtensionLocators.FIRST_ORDER_PRODUCT, ElementType.Xpath);
		Thread.sleep(3000);
		webDB.clickAnElement(WhatsappExtensionLocators.ABANDONED_CART_TAB_INDIVIDUAL_ORDERS, ElementType.Xpath);
		Thread.sleep(3000);
		flag = webDB.isElementDisplayed(WhatsappExtensionLocators.ABANDONED_CART_HIGHLOGHTED_GREEN, ElementType.Xpath);
		Thread.sleep(3000);
		if (flag) {
			flag = true;
			log.info("Navigated to Abandoned Cart Section in Individual Orders");
		}
		return flag;

	}

	public static boolean enterShippingDetailsWithValidPhone() throws InterruptedException {
		String fullName = firstName + lastName;
		webDB.sendTextToAnElement(ShopifyStoreLocators.CHECKOUT_EMAIL_PHONE, phone, ElementType.Xpath);
		webDB.clickAnElement(ShopifyStoreLocators.CHECKOUT_OPTIN_CHECKBOX, ElementType.Xpath);
		webDB.sendTextToAnElement(ShopifyStoreLocators.FIRST_NAME, firstName, ElementType.Xpath);
		Thread.sleep(1000);
		webDB.sendTextToAnElement(ShopifyStoreLocators.LAST_NAME, lastName, ElementType.Xpath);
		webDB.sendTextToAnElement(ShopifyStoreLocators.ADDRESS_LINE_1, shippingAddress, ElementType.CssSelector);
		Thread.sleep(3000);
		webDB.clickAnElement(ShopifyStoreLocators.ADDRESS_SELECT_FROM_SUGGESTIONS, ElementType.Xpath);
		Thread.sleep(2000);
		webDB.clickAnElement(ShopifyStoreLocators.CONTINUE_BTN, ElementType.Xpath);
		webDB.clickAnElement(ShopifyStoreLocators.CONTINUE_BTN, ElementType.Xpath);
		return flag;
	}

}
