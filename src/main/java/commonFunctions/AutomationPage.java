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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import locators.SettingsLocators;
import locators.CommonLocators;
import locators.MessagingLocators;
import locators.CampaignsLocators;
import locators.WidgetLocators;
import locators.PlansPageLocators;
import locators.AutomationLocators;
import locators.MyWhatsAppLocators;
import utils.DriverBase;
import utils.DriverBase.ElementType;
import utils.Listener;

@SuppressWarnings({ "static-access", "unused" })
public class AutomationPage {
	private static Logger log = Logger.getLogger(AutomationPage.class.getName());
	private static boolean flag;
	private static String propertyFilePath;
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
	private static List<WebElement> enableDisableIcon = webDB.driver
			.findElements(By.xpath(AutomationLocators.ENABLE_DISABLE_ICON_GENERIC));
	private static List<WebElement> settingsDisabled = webDB.driver
			.findElements(By.xpath(AutomationLocators.SETTINGS_DISABLED_GENERIC));
	private static List<WebElement> settingsButton = webDB.driver
			.findElements(By.xpath(AutomationLocators.SETTINGS_GENERIC));
	private static List<WebElement> radioGeneric = webDB.driver
			.findElements(By.xpath(CommonLocators.RADIO_ATTRIBUTE_GENERIC));
	private static List<WebElement> radioClickable = webDB.driver.findElements(By.xpath(CommonLocators.RADIO_GENERIC));
	private static List<WebElement> toggleButton = webDB.driver
			.findElements(By.xpath(AutomationLocators.ENABLE_CHECK_GENERIC));
	private static String discountValue = RandomStringUtils.randomNumeric(2);
	private static String couponCode = RandomStringUtils.random(5);
	private static String VerifyTemplateName = "AutoTemplate";
	private static Set<String> windows;
	private static Iterator<String> iterator;
	private static String parentWindow;
	private static String childWindow;
	private static CommonFunctions HeaderText = new CommonFunctions();
	private static String startTime;
	private static String endTime;
	public static String random;

	/**
	 * verify Enable/disable Greeting
	 *
	 * @return
	 */
	public static boolean createAndDeleteTemplate() throws InterruptedException {
		templateName = AutomationPage.createExtensiontemplate();
		flag = AutomationPage.deleteExtensionTemplate(templateName);
		log.info("deleteExtensionTemplate method used ");
		return flag;

	}

	public static boolean createAndEditTemplateName() throws InterruptedException {
		templateName = createExtensiontemplate();
		element = webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES));
		js.executeScript("arguments[0].click();", element);
		flag = editExtensionTemplate(templateName);
		element = webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES));
		js.executeScript("arguments[0].click();", element);
		if (flag) {
			log.info("flag is true and deleteExtensionTemplate method executed ");
			flag = deleteExtensionTemplate(templateName);
		}
		return flag;

	}

	/**
	 * verify enable/disable ABCART automated messaging
	 *
	 * @return
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 */

	public static boolean verifyEnableDisableAbcartAutomatedMessaging()
			throws FileNotFoundException, InterruptedException, IOException {
		Thread.sleep(2000);
		webDB.driver.navigate().refresh();
		webDB.moveToElement(AutomationLocators.AUTOMATED_TEMPLATES_TAB, ElementType.Id);
		webDB.waitForElement(AutomationLocators.AUTOMATED_TEMPLATES_TAB, ElementType.Id);
		webDB.moveToElement(AutomationLocators.ABCART_ENABLE_CHECK, ElementType.Xpath);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AutomationLocators.ABCART_ENABLE_CHECK)));
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ABCART_ENABLE_CHECK)).isSelected();
		if (!flag) {
			log.info("Flag is False and enable AbCart Messaging method executed");
			flag = enableABCartMessagingTemplate();
		} else {
			log.info("Flag is True and disable AbCart Messaging method executed");
			flag = disableABCartTemplateMessaging();
			if (flag) {
				log.info("Enable Disable ABCart");
				enableABCartMessagingTemplate();
			}
		}
		return flag;
	}

	public static boolean verifyEnableDisableCODAutomatedMessaging()
			throws FileNotFoundException, InterruptedException, IOException {
		Thread.sleep(2000);
		webDB.scrollToAnElement(AutomationLocators.AUTOMATED_TEMPLATES_TAB, ElementType.Id);
		Thread.sleep(1000);
		webDB.waitForElement(AutomationLocators.AUTOMATED_TEMPLATES_TAB, ElementType.Id);
		webDB.scrollToAnElement(AutomationLocators.COD_ENABLE_CHECK, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.COD_ENABLE_CHECK)).isSelected();
		log.info(flag);
		if (!flag) {
			log.info("Flag is False and enable COD Messaging method executed");
			flag = enableCODTemplateMessaging();
		} else {
			log.info("Flag is True and disable COD Messaging method executed");
			flag = disableCODTemplateMessaging();
		}
		return flag;
	}

	/**
	 * verify enable/disable Delivery automated messaging
	 *
	 * @return
	 * @return
	 * @throws Exception
	 */

	public static boolean verifyEnableDisableOrderConfirmationAutomatedMessaging() throws Exception {
		Thread.sleep(2000);
		webDB.waitForElement(AutomationLocators.AUTOMATED_TEMPLATES_TAB, ElementType.Id);
		webDB.moveToElement(AutomationLocators.ORDER_CRM_ENABLE_CHECK, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ORDER_CRM_ENABLE_CHECK)).isSelected();
		log.info(flag);
		if (!flag) {
			log.info("Flag is False and enable Order Confirmation Messaging method executed");
			flag = enableOrderConfirmationTemplateMessaging();
		} else {
			log.info("Flag is False and disable Order Confirmation Messaging method executed");
			flag = disableOrderConfirmationTemplateMessaging();
		}
		return flag;
	}

	/**
	 * verify enable/disable Delivery automated messaging
	 *
	 * @return
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 */
	public static boolean verifyEnableDisableShipmentAutomatedMessaging()
			throws FileNotFoundException, InterruptedException, IOException {
		webDB.waitForElement(AutomationLocators.AUTOMATED_TEMPLATES_TAB, ElementType.Id);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AutomationLocators.ABCART_ENABLE_DISABLE_ICON)));
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ORDER_SHIPMENT_ENABLE_CHECK)).isSelected();
		if (!flag) {
			log.info("Flag is False and enable Order Shipment Messaging method executed");
			flag = enableShipmentTemplateMessaging();
		} else {
			log.info("Flag is False and disable Order Shipment Messaging method executed");
			flag = disableShipmentTemplateMessaging();
		}
		return flag;
	}

	public static String createExtensiontemplate() throws InterruptedException {
		webDB.driver.navigate().refresh();
		Thread.sleep(2000);
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
				category.selectByVisibleText("Quick Reply");
				webDB.sendTextToAnElement(AutomationLocators.TEMPLATE_NAME_FIELD, templateName, ElementType.Xpath);
				Thread.sleep(2000);
				webDB.sendTextToAnElement(AutomationLocators.TEMPLATE_DESC_FIELD, TemplateDesc, ElementType.Xpath);
				Thread.sleep(2000);
				category = new Select(webDB.driver.findElement(By.xpath(AutomationLocators.CATEGORY_QUICK_DROPDOWN)));
				category.selectByVisibleText("Quick Reply");
				Thread.sleep(2000);
				webDB.clickAnElement(AutomationLocators.SUBMIT, ElementType.Xpath);
				log.debug("Clicked on Submit Button");
				// log.info("Template created successfully");
				wait.until(ExpectedConditions
						.visibilityOf(webDB.driver.findElement(By.xpath(AutomationLocators.ACTION_SUCCESS_POPUP))));
				flag = webDB.waitForElement(AutomationLocators.ACTION_SUCCESS_POPUP, ElementType.Xpath);
				if (flag) {
					text = webDB.getText(AutomationLocators.ACTION_SUCCESS_POPUP, ElementType.Xpath);
					if (text.contains(MessagingLocators.TEMPLATE_CREATED)) {
						log.info("Template created successfully");
						flag = true;
					}
					element = webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES));
					js.executeScript("arguments[0].click();", element);
				}
			}
		}
		return templateName;
	}

	public static boolean deleteExtensionTemplate(String templateName) throws InterruptedException {
		Thread.sleep(3000);
		webDB.waitForElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
		templateNames = webDB.driver.findElements(By.xpath(AutomationLocators.TEMPLATE_NAMES_COMMON));
		for (int i = 0; i < templateNames.size(); i++) {
			text = templateNames.get(i).getText();
			if (text.contains(templateName)) {
				delteButton = webDB.driver.findElements(By.xpath(AutomationLocators.DELETE_TEMPLATE));
//				delteButton.get(i).click();
				js.executeScript("arguments[0].click();", delteButton.get(i));
				if ((System.getProperty("platformName").equals("browserstack"))
						&& (System.getProperty("os").equals("Ios")) || (System.getProperty("os").equals("Android"))) {
					webDB.pressTabKey();
					webDB.pressTabKey();
					Thread.sleep(1000);
					webDB.pressEnterKey();
					log.info("Template Deleted Successfully");
				} else {
					webDB.javaScriptClickAnElement(AutomationLocators.CONFIRM, ElementType.Xpath);
				}

				flag = webDB.waitForElement(AutomationLocators.ACTION_SUCCESS_POPUP, ElementType.Xpath);
				if (flag) {
					Thread.sleep(2000);
					flag = webDB.isElementDisplayed(AutomationLocators.ACTION_SUCCESS_POPUP, ElementType.Xpath);
					text = webDB.getText(AutomationLocators.ACTION_SUCCESS_POPUP, ElementType.Xpath);
					System.out.println("Deleted text = " + text);
					if (text.contains("Template deleted successfully")) {
						log.info("Template Deleted Successfully");
						Thread.sleep(2000);
					}
				}
//				wait.until(ExpectedConditions
//						.invisibilityOf(webDB.driver.findElement(By.xpath(AutomationLocators.ACTION_SUCCESS_POPUP))));
//				flag = webDB.waitForElement(AutomationLocators.ACTION_SUCCESS_POPUP, ElementType.Xpath);
//				if (flag) {
//					Thread.sleep(2000);
//					text = webDB.getText(AutomationLocators.ACTION_SUCCESS_POPUP, ElementType.Xpath);
//					if (text.contains(MessagingLocators.TEMPLATE_DELETED)) {
//						log.info("Template Deleted Successfully");
//						flag = true;
//						Thread.sleep(2000);
//					}
//				}
			}
		}
		return flag;
	}

	public static boolean editExtensionTemplate(String templateName) throws InterruptedException {
		templateNames = webDB.driver.findElements(By.xpath(AutomationLocators.TEMPLATE_NAMES_COMMON));
		for (int i = 0; i < templateNames.size(); i++) {
			text = templateNames.get(i).getText();
			if (text.contains(templateName)) {
				editButton = webDB.driver.findElements(By.xpath(AutomationLocators.EDIT_TEMPLATE));
				editButton.get(i).click();
				webDB.scrollToAnElement(MessagingLocators.EDITTEMPLATE_NAME, ElementType.Xpath);
				flag = webDB.waitForElement(MessagingLocators.EDITTEMPLATE_NAME, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(MessagingLocators.EDITTEMPLATE_NAME, ElementType.Xpath);
					flag = webDB.waitForElement(MessagingLocators.NAME_INPUT, ElementType.Xpath);
					if (flag) {
						webDB.sendTextToAnElement(MessagingLocators.NAME_INPUT, "edited", ElementType.Xpath);
						category = new Select(webDB.driver
								.findElement(By.cssSelector(AutomationLocators.EDIT_TEMPLATE_CATEGORY_DROPDOWN)));
						category.selectByVisibleText("Quick Reply");
						wait.until(ExpectedConditions
								.visibilityOf(webDB.driver.findElement(By.xpath(AutomationLocators.UPDATE))));
						webDB.clickAnElement(AutomationLocators.UPDATE, ElementType.Xpath);
						wait.until(ExpectedConditions
								.visibilityOf(webDB.driver.findElement(By.xpath(AutomationLocators.CONFIRM))));
						element = webDB.driver.findElement(By.xpath(AutomationLocators.CONFIRM));
						js.executeScript("arguments[0].click();", element);
						wait.until(ExpectedConditions.invisibilityOf(
								webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATE_EDIT_SUCCESS))));
						flag = webDB.isElementDisplayed(AutomationLocators.TEMPLATE_EDIT_SUCCESS, ElementType.Xpath);
						if (flag) {
							text = webDB.getText(AutomationLocators.TEMPLATE_EDIT_SUCCESS, ElementType.Xpath);
							if (text.contains(MessagingLocators.TEMPLATE_EDITED)) {
//				wait.until(ExpectedConditions.visibilityOf(webDB.driver.findElement(By.xpath(TemplatesLocators.CONFIRM))));
//				webDB.clickAnElement(TemplatesLocators.CONFIRM, ElementType.Xpath);
//				flag = webDB.isElementDisplayed(TemplatesLocators.ACTION_SUCCESS_POPUP, ElementType.Xpath);
								log.info("Template Edited Successfully");
							}
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean enableABCartMessagingTemplate()
			throws FileNotFoundException, IOException, InterruptedException {

		webDB.moveToElement(AutomationLocators.ABCART_ENABLE_DISABLE_ICON, ElementType.Xpath);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AutomationLocators.ABCART_ENABLE_DISABLE_ICON)));

		// webDB.driver.findElement(By.xpath(TemplatesLocators.ABCART_ENABLE_DISABLE_ICON)).click();
//		actions.moveToElement(element).click().build().perform();
//        js.executeScript("arguments[0].click();", element1);
//        enableDisableIcon.get(0).click();
		Thread.sleep(2000);
		webDB.clickAnElement(AutomationLocators.ABCART_ENABLE_DISABLE_ICON, ElementType.Xpath);
		log.debug("clicked on ABcart Toggle Button");
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String AbcartMessagingEnabledMessage = prop.getProperty("ABcartMessagingEnabledMessage");
		log.info(AbcartMessagingEnabledMessage);
		flag = webDB.waitForElement(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG, ElementType.Xpath);
		log.info("Template enabled message is Displayed");
		if (flag) {
			text = webDB.driver.findElement(By.xpath(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG)).getText();
			if (text.contains(AbcartMessagingEnabledMessage)) {
				flag = true;
				log.info("Abandoned Cart Enabled and Enabled Message is displayed");
			}
		}
		return flag;

	}

	public static boolean disableABCartTemplateMessaging()
			throws FileNotFoundException, IOException, InterruptedException {
		webDB.moveToElement(AutomationLocators.ABCART_ENABLE_DISABLE_ICON, ElementType.Xpath);
		Thread.sleep(1000);
		webDB.waitForElement(AutomationLocators.AUTOMATED_TEMPLATES_TAB, ElementType.Id);
		webDB.clickAnElement(AutomationLocators.ABCART_ENABLE_DISABLE_ICON, ElementType.Xpath);
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String AbcartMessagingDisabledMessage = prop.getProperty("ABcartMessagingDisabledMessage");
		log.info(AbcartMessagingDisabledMessage);
		flag = webDB.waitForElement(CommonLocators.TEMPLATE_MSGNG_DISABLED_MSG, ElementType.Xpath);
		if (flag) {
			text = webDB.driver.findElement(By.xpath(CommonLocators.TEMPLATE_MSGNG_DISABLED_MSG)).getText();
			log.info(text);
			if (text.contains(AbcartMessagingDisabledMessage)) {
				flag = true;
				log.info("Abandoned Cart Dusabled and  Disable Message is  displayed");
			}
		}
		return flag;
	}

	/**
	 * verify enable/disable COD automated messaging
	 *
	 * @return
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 */

	public static boolean enableCODTemplateMessaging() throws FileNotFoundException, IOException, InterruptedException {
		webDB.scrollToAnElement(AutomationLocators.COD_ENABLE_DISABLE_ICON, ElementType.Xpath);
		Thread.sleep(1000);
		webDB.clickAnElement(AutomationLocators.COD_ENABLE_DISABLE_ICON, ElementType.Xpath);
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String codTemplateMessagingEnableMessage = prop.getProperty("CODTemplateMessagingEnableMessage");
		log.info(codTemplateMessagingEnableMessage);
		flag = webDB.isElementDisplayed(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG, ElementType.Xpath);
		if (flag) {
			text = webDB.driver.findElement(By.xpath(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG)).getText();

			log.info(text);
			if (text.equalsIgnoreCase(codTemplateMessagingEnableMessage)) {
				flag = true;
				log.info("COD Order Confirmation Template Enabled and Enabled Message is displayed");
			}
		}
		return flag;
	}

	public static boolean disableCODTemplateMessaging()
			throws FileNotFoundException, IOException, InterruptedException {
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String codTemplateMessagingDisableMessage = prop.getProperty("CODTemplateMessagingDisableMessage");
		log.info(codTemplateMessagingDisableMessage);
		webDB.scrollToAnElement(AutomationLocators.COD_ENABLE_DISABLE_ICON, ElementType.Xpath);
		Thread.sleep(1000);
		webDB.waitForElement(AutomationLocators.COD_ENABLE_DISABLE_ICON, ElementType.Xpath);
		webDB.clickAnElement(AutomationLocators.COD_ENABLE_DISABLE_ICON, ElementType.Xpath);
		wait.until(ExpectedConditions
				.visibilityOf(webDB.driver.findElement(By.xpath(CommonLocators.TEMPLATE_MSGNG_DISABLED_MSG))));
		flag = webDB.isElementDisplayed(CommonLocators.TEMPLATE_MSGNG_DISABLED_MSG, ElementType.Xpath);
		if (flag) {
			text = webDB.driver.findElement(By.xpath(CommonLocators.TEMPLATE_MSGNG_DISABLED_MSG)).getText();
			log.info(text);
			if (text.equalsIgnoreCase(codTemplateMessagingDisableMessage)) {
				flag = true;
				log.info("Order Confirmation Template Disabled and  Disable Message is  displayed");
			}
		}
		return flag;
	}

	public static boolean enableOrderConfirmationTemplateMessaging()
			throws FileNotFoundException, IOException, Exception {
		webDB.javaScriptClickAnElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String orderConfirmationTemplateMessaginEnabled = prop.getProperty("OrderCrmConfirmationEnabledMessage");
		log.info(orderConfirmationTemplateMessaginEnabled);

//		webDB.clickAnElement(TemplatesLocators.ODR_CRM_ENABLE_DISABLE_ICON, ElementType.Xpath);
		Thread.sleep(2500);
		webDB.scrollElement(AutomationLocators.ORDER_CRM_ENABLE_CHECK);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ORDER_CRM_ENABLE_CHECK)).isSelected();
		System.out.println(flag);
		if (!flag) {
//			text = webDB.driver.findElement(By.xpath(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG)).getText();
//			log.info(text);
//			if (text.equalsIgnoreCase(orderConfirmationTemplateMessaginEnabled)) {
//				flag = true;
//				log.info("Order Confirmation Template Enabled and Enabled Message is displayed");
//			}
			Thread.sleep(2500);
			webDB.clickAnElement(AutomationLocators.ODR_CRM_ENABLE_DISABLE_ICON, ElementType.Xpath);
		}
		return flag;
	}

	public static boolean disableOrderConfirmationTemplateMessaging()
			throws FileNotFoundException, IOException, InterruptedException {
		webDB.moveToElement(AutomationLocators.ODR_CRM_ENABLE_DISABLE_ICON, ElementType.Xpath);
		Thread.sleep(1000);
		webDB.waitForElement(AutomationLocators.AUTOMATED_TEMPLATES_TAB, ElementType.Id);
		webDB.clickAnElement(AutomationLocators.ODR_CRM_ENABLE_DISABLE_ICON, ElementType.Xpath);
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String orderConfirmationTemplateMessagingDisableMessage = prop
				.getProperty("OrderCrmConfirmationDisabledMessage");
		log.info(orderConfirmationTemplateMessagingDisableMessage);
		flag = webDB.isElementDisplayed(CommonLocators.TEMPLATE_MSGNG_DISABLED_MSG, ElementType.Xpath);
		if (flag) {
			text = webDB.driver.findElement(By.xpath(CommonLocators.TEMPLATE_MSGNG_DISABLED_MSG)).getText();
			log.info(text);
			if (text.equalsIgnoreCase(orderConfirmationTemplateMessagingDisableMessage)) {
				flag = true;
				log.info("Order Confirmation Template Disabled and  Disable Message is  displayed");
			}
		}
		return flag;
	}

	public static boolean enableShipmentTemplateMessaging()
			throws FileNotFoundException, InterruptedException, IOException {
		webDB.clickAnElement(AutomationLocators.ODR_SHIPMENT__ENABLE_DISABLE_ICON, ElementType.Xpath);
		Thread.sleep(2000);
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String deliveryTemplateMessaginEnabledMessage = prop.getProperty("ShipmentTemplateMessagingEnabledMessage");
		log.info(deliveryTemplateMessaginEnabledMessage);
		flag = webDB.isElementDisplayed(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG, ElementType.Xpath);
		if (flag) {
			text = webDB.driver.findElement(By.xpath(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG)).getText();
			log.info(text);
			if (text.equalsIgnoreCase(deliveryTemplateMessaginEnabledMessage)) {
				flag = true;
				log.info("Shipment Template Enabled and Enabled Message is displayed");
			}
		}
		return flag;
	}

	public static boolean disableShipmentTemplateMessaging() throws FileNotFoundException, IOException {
		webDB.waitForElement(AutomationLocators.AUTOMATED_TEMPLATES_TAB, ElementType.Id);
		webDB.clickAnElement(AutomationLocators.ODR_SHIPMENT__ENABLE_DISABLE_ICON, ElementType.Xpath);
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String ShipmentTemplateMessagingDisabledMessage = prop.getProperty("ShipmentTemplateMessagingDisabledMessage");
		log.info(ShipmentTemplateMessagingDisabledMessage);
		flag = webDB.isElementDisplayed(CommonLocators.TEMPLATE_MSGNG_DISABLED_MSG, ElementType.Xpath);
		if (flag) {
			text = webDB.driver.findElement(By.xpath(CommonLocators.TEMPLATE_MSGNG_DISABLED_MSG)).getText();
			log.info(text);
			if (text.equalsIgnoreCase(ShipmentTemplateMessagingDisabledMessage)) {
				flag = true;
				log.info("Shipment Template Disabled and  Disable Message is  displayed");
			}
		}
		return flag;
	}

	public static boolean disableAllAutomatedTemplatesAndCheckSettings() throws InterruptedException {
//        parentWindow = webDB.driver.getWindowHandle();
//        String clickLink = Keys.chord(Keys.CONTROL,Keys.ENTER);
//        webDB.driver.findElement(By.linkText(TemplatesLocators.TEMPLATES_TAB)).sendKeys(clickLink);
//        windows = webDB.driver.getWindowHandles();
//        iterator = windows.iterator();
//        while (iterator.hasNext()) {
//            childWindow = iterator.next();
//            if (!parentWindow.equals(childWindow)) {
//                webDB.driver.switchTo().window(childWindow);
//            }
//        }
		Thread.sleep(3000);
//        elements = webDB.driver.findElements(By.xpath(TemplatesLocators.ENABLE_CHECK_GENERIC));
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ABCART_ENABLE_CHECK)).isSelected();
		if (flag) {
			actions.moveToElement(webDB.driver.findElement(By.xpath(AutomationLocators.ABCART_ENABLE_DISABLE_ICON)))
					.click().build().perform();
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(CommonLocators.TEMPLATE_MSGNG_DISABLED_MSG)));
		}
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ORDER_CRM_ENABLE_CHECK)).isSelected();
		if (flag) {
			actions.moveToElement(webDB.driver.findElement(By.xpath(AutomationLocators.ODR_CRM_ENABLE_DISABLE_ICON)))
					.click().build().perform();
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(CommonLocators.TEMPLATE_MSGNG_DISABLED_MSG)));
		}
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ORDER_SHIPMENT_ENABLE_CHECK)).isSelected();
		if (flag) {
			actions.moveToElement(
					webDB.driver.findElement(By.xpath(AutomationLocators.ODR_SHIPMENT__ENABLE_DISABLE_ICON))).click()
					.build().perform();
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(CommonLocators.TEMPLATE_MSGNG_DISABLED_MSG)));
		}
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.COD_ENABLE_CHECK)).isSelected();
		if (flag) {
			actions.moveToElement(webDB.driver.findElement(By.xpath(AutomationLocators.COD_ENABLE_DISABLE_ICON)))
					.click().build().perform();
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(CommonLocators.TEMPLATE_MSGNG_DISABLED_MSG)));
		}
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.PAYMENT_SUCCESSFUL_ENABLE_CHECK)).isSelected();
		if (flag) {
			actions.moveToElement(
					webDB.driver.findElement(By.xpath(AutomationLocators.PAYMENT_SUCCESSFUL_ENABLE_DISABLE_ICON)))
					.click().build().perform();
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(CommonLocators.TEMPLATE_MSGNG_DISABLED_MSG)));
		}
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ORDER_CANCELLATION_ENABLE_CHECK)).isSelected();
		if (flag) {
			actions.moveToElement(
					webDB.driver.findElement(By.xpath(AutomationLocators.ORDER_CANCELLATION_ENABLE_DISABLE_ICON)))
					.click().build().perform();
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(CommonLocators.TEMPLATE_MSGNG_DISABLED_MSG)));
		}
		settingsDisabled = webDB.driver.findElements(By.xpath(AutomationLocators.SETTINGS_DISABLED_GENERIC));
		int size = settingsDisabled.size();
		log.info(size);
		if (size == 6) {
			flag = true;
			log.info("Settings is disabled");
		}
		return flag;
	}

	public static boolean enableAllAutomatedTemplatesAndCheckSettings() throws InterruptedException {
//
		Thread.sleep(3000);
//        elements = webDB.driver.findElements(By.xpath(TemplatesLocators.ENABLE_CHECK_GENERIC));
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ABCART_ENABLE_CHECK)).isSelected();
		if (!flag) {
			actions.moveToElement(webDB.driver.findElement(By.xpath(AutomationLocators.ABCART_ENABLE_DISABLE_ICON)))
					.click().build().perform();
			// webDB.clickAnElement(AutomationLocators.ABCART_ENABLE_DISABLE_ICON,
			// ElementType.Xpath);
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG)));
		}
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ORDER_CRM_ENABLE_CHECK)).isSelected();
		if (!flag) {
			actions.moveToElement(webDB.driver.findElement(By.xpath(AutomationLocators.ODR_CRM_ENABLE_DISABLE_ICON)))
					.click().build().perform();
			// webDB.clickAnElement(AutomationLocators.ODR_CRM_ENABLE_DISABLE_ICON,
			// ElementType.Xpath);

			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG)));
		}
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ORDER_SHIPMENT_ENABLE_CHECK)).isSelected();
		if (!flag) {
			actions.moveToElement(
					webDB.driver.findElement(By.xpath(AutomationLocators.ODR_SHIPMENT__ENABLE_DISABLE_ICON))).click()
					.build().perform();
			// webDB.clickAnElement(AutomationLocators.ODR_SHIPMENT__ENABLE_DISABLE_ICON,
			// ElementType.Xpath);

			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG)));
		}
		Thread.sleep(10000);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.COD_ENABLE_CHECK)).isSelected();
		if (!flag) {
			webDB.clickAnElement(AutomationLocators.COD_ENABLE_DISABLE_ICON, ElementType.Xpath);
//			actions.moveToElement(webDB.driver.findElement(By.xpath(TemplatesLocators.COD_ENABLE_DISABLE_ICON))).click()
//					.build().perform();
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG)));
		}
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.PAYMENT_SUCCESSFUL_ENABLE_CHECK)).isSelected();
		if (!flag) {
			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(AutomationLocators.PAYMENT_SUCCESSFUL_ENABLE_DISABLE_ICON)));
			actions.moveToElement(
					webDB.driver.findElement(By.xpath(AutomationLocators.PAYMENT_SUCCESSFUL_ENABLE_DISABLE_ICON)))
					.click().build().perform();

			// webDB.clickAnElement(AutomationLocators.ODR_CRM_ENABLE_DISABLE_ICON,
			// ElementType.Xpath);

			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG)));
		}
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ORDER_CANCELLATION_ENABLE_CHECK)).isSelected();
		if (!flag) {
			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(AutomationLocators.ORDER_CANCELLATION_ENABLE_DISABLE_ICON)));
			actions.moveToElement(
					webDB.driver.findElement(By.xpath(AutomationLocators.ORDER_CANCELLATION_ENABLE_DISABLE_ICON)))
					.click().build().perform();

			// webDB.clickAnElement(AutomationLocators.ODR_CRM_ENABLE_DISABLE_ICON,
			// ElementType.Xpath);

			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG)));
		}
		settingsDisabled = webDB.driver.findElements(By.xpath(AutomationLocators.SETTINGS_DISABLED_GENERIC));
		int size = settingsDisabled.size();
		log.info(size);
		if (size == 0) {
			flag = true;
			log.info("Settings is enabled");
		}
		return flag;
	}

	public static void changeLanguageABCARTAutomatedTemplates(String language) throws InterruptedException {
		Thread.sleep(2000);
		elements = webDB.driver.findElements(By.xpath(AutomationLocators.ENABLE_CHECK_GENERIC));
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ABCART_ENABLE_CHECK)).isSelected();
		if (!flag) {
			webDB.driver.findElement(By.xpath(AutomationLocators.ABCART_ENABLE_DISABLE_ICON)).click();
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG)));
		}
//		log.info(settingsButton.size());
		WebElement button = webDB.driver.findElement(By.xpath(AutomationLocators.SETTINGS_ABCART_TEMPLATE));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AutomationLocators.SETTINGS_GENERIC)));
		js.executeScript("arguments[0].click();", button);
		webDB.waitForElement(AutomationLocators.TEMPLATE_LANGUAGE_SELECT, ElementType.Xpath);
		List<WebElement> languageText = webDB.driver
				.findElements(By.xpath(AutomationLocators.TEMPLATE_LANGUAGE_SELECT));
		for (int i = 0; i < languageText.size(); i++) {
			String languageFetched = languageText.get(i).getText();
			log.info(languageFetched);
			if (languageFetched.equalsIgnoreCase(language)) {
				languageText.get(i).click();
				break;
			}
		}
		webDB.clickAnElement(AutomationLocators.SAVE, ElementType.Xpath);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP)));

	}

	public static void changeLanguageOrderCRMAutomatedTemplates(String language) throws InterruptedException {
		Thread.sleep(2000);// wait to grab proepr selection value
		elements = webDB.driver.findElements(By.xpath(AutomationLocators.ENABLE_CHECK_GENERIC));
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ORDER_CRM_ENABLE_CHECK)).isSelected();
		if (!flag) {
			webDB.driver.findElement(By.xpath(AutomationLocators.ODR_CRM_ENABLE_DISABLE_ICON)).click();
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG)));
		}
		log.info(settingsButton.size());
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AutomationLocators.SETTINGS_GENERIC)));
		WebElement button = webDB.driver.findElement(By.xpath(AutomationLocators.SETTINGS_ODR_CONFIRMATION_TEMPLATE));
		js.executeScript("arguments[0].click();", button);
		webDB.waitForElement(AutomationLocators.TEMPLATE_LANGUAGE_SELECT, ElementType.Xpath);
		List<WebElement> languageText = webDB.driver
				.findElements(By.xpath(AutomationLocators.TEMPLATE_LANGUAGE_SELECT));
		for (int i = 0; i < languageText.size(); i++) {
			String languageFetched = languageText.get(i).getText();
			log.info(languageFetched);
			if (languageFetched.equalsIgnoreCase(language)) {
				languageText.get(i).click();
				break;
			}
		}
		webDB.clickAnElement(AutomationLocators.SAVE, ElementType.Xpath);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP)));
	}

	public static void changeLanguageDeliveryAutomatedTemplates(String language) throws Exception {
		Thread.sleep(2000);// wait to grab proepr selection value
		elements = webDB.driver.findElements(By.id(AutomationLocators.AUTOMATED_TEMPLATES_TAB));
		Thread.sleep(2000);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ORDER_SHIPMENT_ENABLE_CHECK)).isSelected();
		if (!flag) {
			enableOrderConfirmationTemplateMessaging();
		} else {
			enableOrderConfirmationTemplateMessaging();
		}
		log.info(settingsButton.size());
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AutomationLocators.SETTINGS_GENERIC)));
		webDB.driver.findElement(By.xpath(AutomationLocators.SETTINGS_ODR_CONFIRMATION_TEMPLATE)).click();
//		js.executeScript("arguments[0].click();", button);
		webDB.waitForElement(AutomationLocators.TEMPLATE_LANGUAGE_SELECT, ElementType.Xpath);
		List<WebElement> languageText = webDB.driver
				.findElements(By.xpath(AutomationLocators.TEMPLATE_LANGUAGE_SELECT));
		for (int i = 0; i < languageText.size(); i++) {
			String languageFetched = languageText.get(i).getText();
			log.info(languageFetched);
			if (languageFetched.equalsIgnoreCase(language)) {
				languageText.get(i).click();
				break;
			}
		}
		webDB.clickAnElement(AutomationLocators.SAVE, ElementType.Xpath);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP)));

	}

	public static void changeLanguageCODAutomatedTemplates(String language) throws InterruptedException, IOException {
		Thread.sleep(2000);// wait to grab proepr selection value
		elements = webDB.driver.findElements(By.xpath(AutomationLocators.ENABLE_CHECK_GENERIC));
		Thread.sleep(2000);// wait added to grab the proper slection value after proper loading
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.COD_ENABLE_CHECK)).isSelected();
		if (!flag) {
			enableCODTemplateMessaging();
		}
		log.info(settingsButton.size());
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AutomationLocators.SETTINGS_GENERIC)));
		WebElement button = webDB.driver.findElement(By.xpath(AutomationLocators.SETTINGS_COD_TEMPLATE));
		js.executeScript("arguments[0].click();", button);
		webDB.waitForElement(AutomationLocators.TEMPLATE_LANGUAGE_SELECT, ElementType.Xpath);
		List<WebElement> languageText = webDB.driver
				.findElements(By.xpath(AutomationLocators.TEMPLATE_LANGUAGE_SELECT));
		for (int i = 0; i < languageText.size(); i++) {
			String languageFetched = languageText.get(i).getText();
			log.info(languageFetched);
			if (languageFetched.equalsIgnoreCase(language)) {
				wait.until(ExpectedConditions.elementToBeClickable(languageText.get(i)));
				languageText.get(i).click();
				break;
			}
		}

		webDB.clickAnElement(AutomationLocators.SAVE, ElementType.Xpath);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP)));

	}

	public static boolean verifyChangeLanguageABCARTAutomatedTemplate()
			throws FileNotFoundException, IOException, InterruptedException {
		changeLanguageABCARTAutomatedTemplates("Spanish");
		flag = webDB.isElementDisplayed(AutomationLocators.SPANISH_TEMPLATE_TXT, ElementType.Xpath);
		log.info("ABCART Template language updated");
		return flag;
	}

	public static boolean verifyChangeLanguageOrderCRMAutomatedTemplate()
			throws FileNotFoundException, IOException, InterruptedException {
		changeLanguageOrderCRMAutomatedTemplates("Portuguese");
		Thread.sleep(1000);
		flag = webDB.isElementDisplayed(AutomationLocators.PORTUGESE_ORDR_CRM_TEMPLATE, ElementType.Xpath);
		log.info("Order Confirmation Template language updated");
		return flag;
	}

	public static boolean verifyChangeLanguageOrderShipmentAutomatedTemplate() throws Exception {
		changeLanguageDeliveryAutomatedTemplates("Italian");
		flag = webDB.isElementDisplayed(AutomationLocators.ITALIAN_ORDER_SHIPMENT_TEMPLATE, ElementType.Xpath);
		if (flag) {
			log.info("Delivery Template language updated");
		}
		return flag;
	}

	public static boolean verifyChangeLanguageCODAutomatedTemplate()
			throws FileNotFoundException, IOException, InterruptedException {
		changeLanguageCODAutomatedTemplates("Spanish");
		flag = webDB.isElementDisplayed(AutomationLocators.COD_SPANISH_TEMPLATE, ElementType.Xpath);
		log.info("COD Template language updated");
		return flag;
	}

	public static boolean selectDiscountTypeAsPercentage() throws InterruptedException {
		webDB.waitForElement(AutomationLocators.DISCOUNT_TYPE__CHECKBOX, ElementType.Xpath);
		Thread.sleep(3000);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.DISCOUNT_TYPE__CHECKBOX)).isSelected();
		if (!flag) {
			element = webDB.driver.findElement(By.xpath(AutomationLocators.DISCOUNT_TYPE));
			js.executeScript("arguments[0].click();", element);
//            webDB.clickAnElement(TemplatesLocators.DISCOUNT_TYPE, ElementType.Xpath);
		}
		Thread.sleep(1500);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AutomationLocators.PERCENTAGE_RADIO)));
		actions.click(webDB.driver.findElement(By.xpath(AutomationLocators.PERCENTAGE_RADIO))).build().perform();
		webDB.waitForElement(AutomationLocators.DISCOUNT_VALUE, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(AutomationLocators.DISCOUNT_VALUE))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(AutomationLocators.DISCOUNT_VALUE, discountValue, ElementType.Xpath);
		Thread.sleep(3000);
		webDB.clickAnElement(AutomationLocators.SAVE, ElementType.Xpath);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP)));
		flag = webDB.isElementDisplayed(CommonLocators.ACTION_SUCCESS_POPUP, ElementType.Xpath);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP)));
		log.info("Percentage Discount  has been added to  template");
		return flag;
	}

	public static boolean selectDiscountTypeAsCoupon() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(AutomationLocators.DISCOUNT_TYPE)));
		webDB.waitForElement(AutomationLocators.DISCOUNT_TYPE__CHECKBOX, ElementType.Xpath);
		Thread.sleep(3000);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.DISCOUNT_TYPE__CHECKBOX)).isSelected();
		if (!flag) {
			element = webDB.driver.findElement(By.xpath(AutomationLocators.DISCOUNT_TYPE));
			js.executeScript("arguments[0].click();", element);
		}

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AutomationLocators.FIXED_DISCOUNT__RADIO)));
		actions.click(webDB.driver.findElement(By.xpath(AutomationLocators.FIXED_DISCOUNT__RADIO))).build().perform();
		webDB.driver.findElement(By.xpath(AutomationLocators.COUPON_CODE))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(AutomationLocators.COUPON_CODE, couponCode, ElementType.Xpath);
		webDB.clickAnElement(AutomationLocators.SAVE, ElementType.Xpath);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP)));
		flag = webDB.isElementDisplayed(CommonLocators.ACTION_SUCCESS_POPUP, ElementType.Xpath);
		log.info("Discount successfully added to ABCART");
		return flag;
	}

	public static boolean addDiscountToABCartTemplate() throws IOException, InterruptedException {
		Thread.sleep(2000);
		webDB.driver.navigate().refresh();
		webDB.moveToElement(AutomationLocators.ABCART_ENABLE_CHECK, ElementType.Xpath);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ABCART_ENABLE_CHECK)).isSelected();
		if (!flag) {
			enableABCartMessagingTemplate();
			wait.until(ExpectedConditions
					.invisibilityOf(webDB.driver.findElement(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP))));
		}
		webDB.driver.findElement(By.xpath(AutomationLocators.SETTINGS_ABCART_TEMPLATE)).click();
		flag = selectDiscountTypeAsPercentage();
		return flag;
	}

	public static boolean addDiscountTypeToOrderConfirmationTemplate() throws Exception {
		Thread.sleep(2000);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ORDER_CRM_ENABLE_CHECK)).isSelected();
		if (!flag) {
			enableOrderConfirmationTemplateMessaging();
		}
		webDB.driver.findElement(By.xpath(AutomationLocators.SETTINGS_ODR_CONFIRMATION_TEMPLATE)).click();
		log.debug("Clicked on Settings Tab");
		flag = selectDiscountTypeAsPercentage();
		return flag;
	}

	public static boolean addCouponToABCartTemplate() throws IOException, InterruptedException {
		Thread.sleep(2000);// added wait to grab proper value of isSelected
		webDB.driver.navigate().refresh();
		webDB.moveToElement(AutomationLocators.ABCART_ENABLE_CHECK, ElementType.Xpath);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ABCART_ENABLE_CHECK)).isSelected();
		if (!flag) {
			enableABCartMessagingTemplate();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP)));

		}
		webDB.driver.findElement(By.xpath(AutomationLocators.SETTINGS_ABCART_TEMPLATE)).click();
		flag = selectDiscountTypeAsCoupon();
		return flag;
	}

	public static boolean addCouponToOrderConfirmationTemplate() throws Exception {
		Thread.sleep(2000);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ORDER_CRM_ENABLE_CHECK)).isSelected();
		if (!flag) {
			enableOrderConfirmationTemplateMessaging();
		}
		webDB.driver.findElement(By.xpath(AutomationLocators.SETTINGS_ODR_CONFIRMATION_TEMPLATE)).click();
		flag = selectDiscountTypeAsCoupon();
		return flag;
	}

	public static boolean verifyOrderShipmentTrackingUrlConfiguration() throws IOException, InterruptedException {
		webDB.waitForElement(AutomationLocators.AUTOMATED_TEMPLATES_TAB, ElementType.Id);
		Thread.sleep(2000);// wait to grab proepr selection value
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ORDER_SHIPMENT_ENABLE_CHECK)).isSelected();
		if (!flag) {
			enableShipmentTemplateMessaging();
			wait.until(ExpectedConditions
					.invisibilityOf(webDB.driver.findElement(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP))));
		}
		webDB.driver.findElement(By.xpath(AutomationLocators.SETTINGS_ODR_SHIPMENT_TEMPLATE)).click();
		Thread.sleep(3000);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.TRACKING_URL_FROM_ORDER_DATA)).isSelected();
		log.info(flag);
		if (!flag) {
			element = webDB.driver.findElement(By.xpath(AutomationLocators.TRACKING_FROM_ORDER_DATA_SELECT));
			js.executeScript("arguments[0].click();", element);
		} else if (flag) {
			element = webDB.driver.findElement(By.xpath(AutomationLocators.THANK_YOU_PAGE_SELECT));
			js.executeScript("arguments[0].click();", element);
		}
		webDB.clickAnElement(AutomationLocators.SAVE, ElementType.Xpath);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TemplatesLocators.SAVE)));
		flag = webDB.isElementDisplayed(CommonLocators.ACTION_SUCCESS_POPUP, ElementType.Xpath);
		return flag;
	}

	public static boolean verifyInvalidCountryCodeOnCodConfirmationPage() throws IOException, InterruptedException {
		String countryCodeErrorText = "A valid country code must be given";
		Thread.sleep(2000);
		webDB.waitForElement(AutomationLocators.AUTOMATED_TEMPLATES_TAB, ElementType.Id);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.COD_ENABLE_CHECK)).isSelected();
		if (!flag) {
			enableCODTemplateMessaging();
			wait.until(ExpectedConditions
					.invisibilityOf(webDB.driver.findElement(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP))));
		}
		webDB.driver.findElement(By.xpath(AutomationLocators.SETTINGS_COD_TEMPLATE)).click();
		Thread.sleep(3000);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.CALL_CHECKBOX_COD_SETTINGS)).isSelected();
		if (!flag) {
			Listener.reportLog("Can't Verify Country code");
			flag = true;
		} else {
			Listener.reportLog("Can't Verify Country code");
		}
//		webDB.driver.findElement(By.xpath(TemplatesLocators.CALL_COUNTRY_CODE_FIELD))
//				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
//		actions.moveToElement(webDB.driver.findElement(By.xpath(TemplatesLocators.CALL_COUNTRY_CODE_FIELD))).build()
//				.perform();
//		webDB.selectDropDownOptions(TemplatesLocators.CALL_COUNTRY_CODE_FIELD, PersonalWidgetLocators.INDIA_CODE,
//				ElementType.Xpath);
//		flag = webDB.isElementDisplayed(TemplatesLocators.INVALID_COUNTRY_CODE_ERROR_TEXT, ElementType.Xpath);
//		Thread.sleep(2000);
//		if (flag) {
//			webDB.clickAnElement(TemplatesLocators.SAVE, ElementType.Xpath);
//			wait.until(
//					ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(CommonLocators.ERROR_POPUP_ON_SAVE)));
//			flag = webDB.driver.findElement(
//					By.xpath("//*[@class='Polaris-Frame-Toast Polaris-Frame-Toast--error'][contains(text(),'"
//							+ countryCodeErrorText + "')]"))
//					.isDisplayed();
//			Thread.sleep(3000);
//		}
//		webDB.driver.findElement(By.xpath("//span[contains(text(),'Cancel')]")).click();
		return flag;
	}

	public static boolean verifyInvalidPhoneOnCodConfirmationPage() throws IOException, InterruptedException {
		String phoneNumberErrorText = "A valid phone number must be given";
		webDB.waitForElement(AutomationLocators.AUTOMATED_TEMPLATES_TAB, ElementType.Id);
		Thread.sleep(2000);// wait to grab proepr selection value
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.COD_ENABLE_CHECK)).isSelected();
		if (!flag) {
			enableCODTemplateMessaging();
			wait.until(ExpectedConditions
					.invisibilityOf(webDB.driver.findElement(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP))));
		}
		webDB.clickAnElement(AutomationLocators.SETTINGS_COD_TEMPLATE, ElementType.Xpath);
		Thread.sleep(3000);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.CALL_CHECKBOX_COD_SETTINGS)).isSelected();
		if (!flag) {
			webDB.clickAnElement(AutomationLocators.CALL_CHECKBOX_CLICKABLE, ElementType.Xpath);
		}
//		webDB.driver.findElement(By.xpath(TemplatesLocators.CALL_COUNTRY_CODE_FIELD))
//				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		actions.moveToElement(webDB.driver.findElement(By.xpath(AutomationLocators.CALL_COUNTRY_CODE_FIELD))).build()
				.perform();
		webDB.selectDropDownOptions(AutomationLocators.CALL_COUNTRY_CODE_FIELD, WidgetLocators.INDIA_CODE,
				ElementType.Xpath);
		webDB.driver.findElement(By.xpath(AutomationLocators.CALL_PHONE_FIELD))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(AutomationLocators.CALL_PHONE_FIELD, "error", ElementType.Xpath);
		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath(AutomationLocators.INVALID_PHONE_NUMBER_ERROR_TEXT)));
		flag = webDB.isElementDisplayed(AutomationLocators.INVALID_PHONE_NUMBER_ERROR_TEXT, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AutomationLocators.SAVE, ElementType.Xpath);
			wait.until(
					ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(CommonLocators.ERROR_POPUP_ON_SAVE)));
			flag = webDB.driver.findElement(
					By.xpath("//*[@class='Polaris-Frame-Toast Polaris-Frame-Toast--error'][contains(text(),'"
							+ phoneNumberErrorText + "')]"))
					.isDisplayed();
		}
		webDB.driver.findElement(By.xpath("//span[contains(text(),'Cancel')]")).click();
		return flag;
	}

	public static boolean verifyInvalidEmailOnCodConfirmationPage() throws IOException, InterruptedException {
		String emailErrorText = "A valid email must be given";
		webDB.waitForElement(AutomationLocators.AUTOMATED_TEMPLATES_TAB, ElementType.Id);
		Thread.sleep(2000);// wait to grab proepr selection value
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.COD_ENABLE_CHECK)).isSelected();
		if (!flag) {
			enableCODTemplateMessaging();
			wait.until(ExpectedConditions
					.invisibilityOf(webDB.driver.findElement(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP))));
		}
		webDB.clickAnElement(AutomationLocators.SETTINGS_COD_TEMPLATE, ElementType.Xpath);
		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath(AutomationLocators.CALL_CHECKBOX_COD_SETTINGS)));
//		flag = webDB.driver.findElement(By.xpath(TemplatesLocators.CALL_CHECKBOX_COD_SETTINGS)).isSelected();
//		if (!flag) {
//			webDB.clickAnElement(TemplatesLocators.CALL_CHECKBOX_CLICKABLE, ElementType.Xpath);
//		}
//		webDB.driver.findElement(By.xpath(TemplatesLocators.CALL_COUNTRY_CODE_FIELD))
//				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
//		actions.moveToElement(webDB.driver.findElement(By.xpath(TemplatesLocators.CALL_COUNTRY_CODE_FIELD))).build()
//				.perform();
		Thread.sleep(2000);
//		webDB.selectDropDownOptions(TemplatesLocators.CALL_COUNTRY_CODE_FIELD, PersonalWidgetLocators.INDIA_CODE,
//				ElementType.Xpath);
//		webDB.driver.findElement(By.xpath(TemplatesLocators.CALL_PHONE_FIELD))
//				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
//		webDB.sendTextToAnElement(TemplatesLocators.CALL_PHONE_FIELD, "7854125421", ElementType.Xpath);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.EMAIL_CHECKBOX_COD_SETTINGS)).isSelected();
		if (!flag) {
			webDB.clickAnElement(AutomationLocators.EMAIL_CHECKBOX_CLICKABLE, ElementType.Xpath);
		}
		webDB.driver.findElement(By.xpath(AutomationLocators.EMAIL_FIELD))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(AutomationLocators.EMAIL_FIELD, "qatest@gmail", ElementType.Xpath);
		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath(AutomationLocators.INVALID_EMAIL_ERROR_TEXT)));
		flag = webDB.isElementDisplayed(AutomationLocators.INVALID_EMAIL_ERROR_TEXT, ElementType.Xpath);

		if (flag) {
			webDB.clickAnElement(AutomationLocators.SAVE, ElementType.Xpath);
			log.info("Clicked on Save button");
			wait.until(
					ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(CommonLocators.ERROR_POPUP_ON_SAVE)));
			flag = webDB.driver.findElement(
					By.xpath("//*[@class='Polaris-Frame-Toast Polaris-Frame-Toast--error'][contains(text(),'"
							+ emailErrorText + "')]"))
					.isDisplayed();
		}
		log.info("Flag value is " + flag);
		webDB.driver.findElement(By.xpath("//span[contains(text(),'Cancel')]")).click();
		return flag;
	}

	public static boolean verifyCallAndEmailOptionSelectionCODSettings() throws IOException, InterruptedException {
		String emailErrorText = "A valid email must be given";
		String phone = "78" + RandomStringUtils.randomNumeric(8);
		webDB.waitForElement(AutomationLocators.AUTOMATED_TEMPLATES_TAB, ElementType.Id);
		Thread.sleep(2000);// wait to grab proper selection value
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.COD_ENABLE_CHECK)).isSelected();
		if (!flag) {
			enableCODTemplateMessaging();
			wait.until(ExpectedConditions
					.invisibilityOf(webDB.driver.findElement(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP))));
		}
		webDB.clickAnElement(AutomationLocators.SETTINGS_COD_TEMPLATE, ElementType.Xpath);
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath(AutomationLocators.CALL_CHECKBOX_COD_SETTINGS)));
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.CALL_CHECKBOX_COD_SETTINGS)).isSelected();
		if (!flag) {
			webDB.scrollToAnElement(AutomationLocators.CALL_CHECKBOX_CLICKABLE, ElementType.Xpath);
			webDB.clickAnElement(AutomationLocators.CALL_CHECKBOX_CLICKABLE, ElementType.Xpath);
		}
//		webDB.driver.findElement(By.id(TemplatesLocators.CALL_COUNTRY_CODE_FIELD))
//				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
//		actions.moveToElement(webDB.driver.findElement(By.xpath(TemplatesLocators.CALL_COUNTRY_CODE_FIELD))).build()
//				.perform();
		webDB.selectDropDownOptions(AutomationLocators.CALL_COUNTRY_CODE_FIELD, WidgetLocators.INDIA_CODE,
				ElementType.Xpath);
		webDB.driver.findElement(By.xpath(AutomationLocators.CALL_PHONE_FIELD))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(AutomationLocators.CALL_PHONE_FIELD, phone, ElementType.Xpath);
		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath(AutomationLocators.EMAIL_CHECKBOX_COD_SETTINGS)));
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.EMAIL_CHECKBOX_COD_SETTINGS)).isSelected();
		if (!flag) {
			webDB.clickAnElement(AutomationLocators.EMAIL_CHECKBOX_CLICKABLE, ElementType.Xpath);
		}
		webDB.driver.findElement(By.xpath(AutomationLocators.EMAIL_FIELD))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(AutomationLocators.EMAIL_FIELD, "qaautomationuser@gupshup.io", ElementType.Xpath);
		flag = webDB.isElementDisplayed(CommonLocators.ACTION_SUCCESS_POPUP, ElementType.Xpath);
		log.info("Saved Successfully");
		if (flag) {

			webDB.clickAnElement(AutomationLocators.SAVE, ElementType.Xpath);
			wait.until(
					ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP)));
			flag = webDB.driver
					.findElement(By.xpath("//*[@class='Polaris-Frame-Toast'][contains(text(),'Data Saved')]"))
					.isDisplayed();
			log.info(flag);
		}
		log.info("Call and Email Details updated Successfully");
		return flag;
	}

	public static boolean updateCODTags() throws IOException, InterruptedException {
		webDB.waitForElement(AutomationLocators.AUTOMATED_TEMPLATES_TAB, ElementType.Id);
		Thread.sleep(3000);// wait to grab proepr selection value
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.COD_ENABLE_CHECK)).isSelected();
		if (!flag) {
			enableCODTemplateMessaging();
			wait.until(ExpectedConditions
					.invisibilityOf(webDB.driver.findElement(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP))));
		}
		webDB.clickAnElement(AutomationLocators.SETTINGS_COD_TEMPLATE, ElementType.Xpath);
		Thread.sleep(3000);
		webDB.driver.findElement(By.xpath(AutomationLocators.ORDER_CONFIRMATION_TAG_FIELD))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(AutomationLocators.ORDER_CONFIRMATION_TAG_FIELD,
				"CODConfirmed-SuperLemon" + RandomStringUtils.random(1), ElementType.Xpath);
		webDB.driver.findElement(By.xpath(AutomationLocators.ORDER_CANCELLATION_TAG_FIELD))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Thread.sleep(2000);
		webDB.sendTextToAnElement(AutomationLocators.ORDER_CANCELLATION_TAG_FIELD,
				"CODCancelled-SuperLemon" + RandomStringUtils.random(1), ElementType.Xpath);
		webDB.driver.findElement(By.xpath(AutomationLocators.ORDER_NO_RESPONSE_TAG_FIELD))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Thread.sleep(3000);
		webDB.sendTextToAnElement(AutomationLocators.ORDER_NO_RESPONSE_TAG_FIELD,
				"CODNoResponse-SuperLemon" + RandomStringUtils.random(1), ElementType.Xpath);
		webDB.clickAnElement(AutomationLocators.SAVE, ElementType.Xpath);
		return flag;
	}

	public static boolean selectUnselectCancellationAndNoResponseCheckBox() throws IOException, InterruptedException {
		webDB.waitForElement(AutomationLocators.COD_ENABLE_DISABLE_ICON, ElementType.Xpath);
		Thread.sleep(2000);// wait to grab proepr selection value
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.COD_ENABLE_CHECK)).isSelected();
		if (!flag) {
			enableCODTemplateMessaging();
			wait.until(ExpectedConditions
					.invisibilityOf(webDB.driver.findElement(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP))));
		}
		webDB.clickAnElement(AutomationLocators.SETTINGS_COD_TEMPLATE, ElementType.Xpath);
		Thread.sleep(2000);
		List<WebElement> checkBoxes = webDB.driver.findElements(By.xpath(CommonLocators.CHECKBOX_SELECTION_CHECK));
		List<WebElement> checkBoxClickable = webDB.driver.findElements(By.xpath(CommonLocators.CHECKBOX_COMMON));
		for (int i = 0; i < checkBoxes.size() - 2; i++) {
			checkBoxClickable.get(i).click();
		}
		webDB.clickAnElement(AutomationLocators.SAVE, ElementType.Xpath);
		wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(AutomationLocators.ACTION_SUCCESS_POPUP)));
		flag = webDB.driver.findElement(By.xpath("//*[@class='Polaris-Frame-Toast'][contains(text(),'Data Saved')]"))
				.isDisplayed();
		return flag;
	}

	public static boolean VerifyEnableDisableOfTemplate() throws InterruptedException {
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			flag = webDB.waitForElement(CommonLocators.THREEDOTSMENU, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				webDB.clickAnElement(CommonLocators.THREEDOTSMENU, ElementType.Xpath);
			}
		}
		flag = webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(TemplatesLocators.EXTENSION_TEMPLATES)));
			templateName = createExtensiontemplate();
//		wait.until(
//				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(TemplatesLocators.AUTOMATED_TEMPLATES_TAB)));
			flag = webDB.waitForElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
			if (flag) {
				element = webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES));
				js.executeScript("arguments[0].click();", element);
				elements = webDB.driver.findElements(By.xpath(AutomationLocators.TEMPLATE_NAMES_QUICKREPLY));
				toggleButton = webDB.driver.findElements(By.xpath(AutomationLocators.ENABLE_CHECK_GENERIC));
				log.info(elements.size());
				for (int i = 0; i < elements.size(); i++) {
					if (elements.get(i).getText().contains(templateName)) {
						flag = toggleButton.get(i).isSelected();
						if (!flag) {
							flag = enableTemplate(templateName);
						} else {
							flag = disableTemplate(templateName);
						}
					}
				}
			}
		}
		deleteExtensionTemplate(templateName);
		return flag;
	}

	public static boolean enableTemplate(String templateName) throws InterruptedException {
		List<WebElement> els = webDB.driver.findElements(By.xpath(AutomationLocators.ENABLE_DISABLE_ICON_GENERIC));
		templateNames = webDB.driver.findElements(By.xpath(AutomationLocators.TEMPLATE_NAMES_COMMON));
		log.info(els.size());
		for (int i = 0; i < templateNames.size(); i++) {
			text = templateNames.get(i).getText();
			if (text.contains(templateName)) {
				js.executeScript("arguments[0].click();", els.get(i));
//				els.get(i).click();
			}
		}
		Thread.sleep(2500);
		flag = webDB.isElementDisplayed(AutomationLocators.ERROR_POPUP, ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath(AutomationLocators.ERROR_POPUP)).getText();
		log.info("Err" + text);
		if (flag) {
			if (text.equalsIgnoreCase("Template has been enabled")) {
				flag = true;
				log.info("Template has been enabled");
			}
			Thread.sleep(2000);
		}
		return flag;
	}

	public static boolean disableTemplate(String templateName) throws InterruptedException {
		List<WebElement> els = webDB.driver.findElements(By.xpath(AutomationLocators.ENABLE_DISABLE_ICON_GENERIC));
		templateNames = webDB.driver.findElements(By.xpath(AutomationLocators.TEMPLATE_NAMES_QUICKREPLY));
		log.info(els.size());
		for (int i = 0; i < templateNames.size(); i++) {
			text = templateNames.get(i).getText();
			if (text.contains(templateName)) {
				js.executeScript("arguments[0].click();", els.get(i));
				// els.get(i).click();
			}
		}
		Thread.sleep(2000);
		flag = webDB.isElementDisplayed(AutomationLocators.ERROR_POPUP, ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath(AutomationLocators.ERROR_POPUP)).getText();
		// log.info("Err" + text);
		if (flag) {
			if (text.equalsIgnoreCase("Template has been disabled")) {
				flag = true;
				log.info("Template has been disabled");
			}
			Thread.sleep(2000);
		}
		return flag;
	}

	public static boolean VerifyTemplateNameNotEmpty() throws InterruptedException {
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
			category.selectByVisibleText("Quick Reply");
			webDB.sendTextToAnElement(AutomationLocators.TEMPLATE_DESC_FIELD, TemplateDesc, ElementType.Xpath);
			Thread.sleep(2000);
			category = new Select(webDB.driver.findElement(By.xpath(AutomationLocators.CATEGORY_QUICK_DROPDOWN)));
			category.selectByVisibleText("Quick Reply");
			Thread.sleep(2000);
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

	public static boolean VerifyDuplicateTemplateName() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationLocators.EXTENSION_TEMPLATES)));
		element = webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES));
		actions.moveToElement(element).click().build().perform();
		templateName = AutomationPage.createExtensiontemplate();
		element = webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES));
		js.executeScript("arguments[0].click();", element);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(AutomationLocators.CREATE_TEMPLATES)));
		webDB.clickAnElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
		wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(AutomationLocators.TEMPLATE_NAME_FIELD)));
		flag = webDB.isElementDisplayed(AutomationLocators.TEMPLATE_NAME_FIELD, ElementType.Xpath);
		if (flag) {
			category = new Select(webDB.driver.findElement(By.xpath(AutomationLocators.CATEGORY_DROPDOWN)));
			category.selectByVisibleText("Quick Reply");
			webDB.sendTextToAnElement(AutomationLocators.TEMPLATE_NAME_FIELD, templateName, ElementType.Xpath);
			Thread.sleep(2000);
			webDB.sendTextToAnElement(AutomationLocators.TEMPLATE_DESC_FIELD, TemplateDesc, ElementType.Xpath);
			Thread.sleep(2000);
			category = new Select(webDB.driver.findElement(By.xpath(AutomationLocators.CATEGORY_QUICK_DROPDOWN)));
			category.selectByVisibleText("Quick Reply");
			Thread.sleep(2000);
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
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AutomationLocators.EXTENSION_TEMPLATES)));
		element = webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES));
		actions.moveToElement(element).click().build().perform();
		templateName = createExtensiontemplate();
		flag = MessagingPage.verifyAfterTemplateCreated(templateName);
		System.out.println("Flag of aftertemplatecreated is " + flag);
		if (flag) {
			element = webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES));
			js.executeScript("arguments[0].click();", element);
			flag = deleteExtensionTemplate(templateName);
		}
		return flag;
	}

	public static boolean VerifyPagePlaceholders() throws InterruptedException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AutomationLocators.EXTENSION_TEMPLATES)));
		Thread.sleep(3000);
		element = webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES));
		actions.moveToElement(element).click().build().perform();
		log.debug("Clicked on Create Templates Button");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationLocators.PAGE_PLACEHOLDER1)));
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.PAGE_PLACEHOLDER1)).isDisplayed();
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.PAGE_PLACEHOLDER2)).isDisplayed();
		log.info("Placeholders are displayed successfully");
		return flag;
	}

	public static boolean VerifyDiscardBtnOnCreateTemplate() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AutomationLocators.EXTENSION_TEMPLATES)));
		element = webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES));
		actions.moveToElement(element).click().build().perform();
		// CLick on create template
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationLocators.CREATE_TEMPLATES)));
		webDB.clickAnElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
		flag = webDB.isElementDisplayed(AutomationLocators.TEMPLATE_NAME_FIELD, ElementType.Xpath);
		if (flag) {
			category = new Select(webDB.driver.findElement(By.xpath(AutomationLocators.CATEGORY_DROPDOWN)));
			category.selectByVisibleText("Quick Reply");
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

	public static boolean VerifyGOBackBtnOnCreateTemplate() throws InterruptedException {
		// modified - added below method.
		CommonFunctions.verifyCRMToggleDisable();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AutomationLocators.EXTENSION_TEMPLATES)));
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationLocators.EXTENSION_TEMPLATES)));
		element = webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES));
		actions.moveToElement(element).click().build().perform();
		// CLick on create template
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationLocators.CREATE_TEMPLATES)));
		webDB.clickAnElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationLocators.TEMPLATE_NAME_FIELD)));
		flag = webDB.isElementDisplayed(AutomationLocators.TEMPLATE_NAME_FIELD, ElementType.Xpath);
		if (flag) {
			// Click on GOback Btn
			webDB.clickAnElement(AutomationLocators.TEMPLATE_DISCARD_BTN, ElementType.Xpath);
			element = webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES));
			js.executeScript("arguments[0].click();", element);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationLocators.CREATE_TEMPLATES)));
			// verify page
			flag = webDB.driver.findElement(By.xpath(AutomationLocators.CREATE_TEMPLATES)).isDisplayed();
			log.info("Successfully navigated Back");
		}
		return flag;
	}

	public static boolean VerifyEditTemplateName() throws InterruptedException {
		Thread.sleep(3000);
		templateName = createExtensiontemplate();
		element = webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES));
		js.executeScript("arguments[0].click();", element);
		// Delete a Template
		flag = editExtensionTemplate(templateName);
		Thread.sleep(3000);
		webDB.waitForElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
//		element = webDB.driver.findElement(By.xpath(TemplatesLocators.EXTENSION_TEMPLATES));
//		js.executeScript("arguments[0].click();", element);
		if (flag) {
			Thread.sleep(2000);
			deleteExtensionTemplate(templateName);
		} else {
			log.info("Flag value is false");
		}
		return flag;
	}

	public static boolean VerifyCatogeryForTemplate() throws InterruptedException {
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
			category.selectByVisibleText("Quick Reply");
			category = new Select(webDB.driver.findElement(By.xpath(AutomationLocators.QUICKREPLYDROPDOWN)));
			category.selectByVisibleText("Quick Reply");
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
			deleteExtensionTemplate(templateName);
		}
		return flag;
	}

	public static boolean VerifyCancelSettingButton() throws InterruptedException, IOException {
		Thread.sleep(2000);// wait to grab selection value of icon
		flag = webDB.waitForElement(AutomationLocators.ABCART_ENABLE_DISABLE_ICON, ElementType.Xpath);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ABCART_ENABLE_CHECK)).isSelected();
		if (!flag) {
			flag = enableABCartMessagingTemplate();
			wait.until(ExpectedConditions
					.invisibilityOf(webDB.driver.findElement(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP))));

		}
		webDB.clickAnElement(AutomationLocators.SETTINGS_ODR_SHIPMENT_TEMPLATE, ElementType.Xpath);
		Thread.sleep(1500);
		webDB.waitForElement(AutomationLocators.CANCEL_BTN, ElementType.Xpath);
		flag = webDB.clickAnElement(AutomationLocators.CANCEL_BTN, ElementType.Xpath);
		log.info("Click on cancel button");
		Thread.sleep(1500);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ABCART_ENABLE_DISABLE_ICON)).isDisplayed();
		return flag;
	}

	public static boolean VerifyCloseSettingPopup() throws InterruptedException, IOException {
//		webDB.driver.navigate().refresh();
		Thread.sleep(4500);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(TemplatesLocators.AUTOMATED_TEMPLATES_TAB)));
		webDB.waitForElement(AutomationLocators.SETTINGS_ABCART_TEMPLATE, ElementType.Xpath);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ABCART_ENABLE_CHECK)).isSelected();
		if (!flag) {
			flag = enableABCartMessagingTemplate();
			wait.until(ExpectedConditions
					.invisibilityOf(webDB.driver.findElement(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP))));
		}
		webDB.clickAnElement(AutomationLocators.SETTINGS_ABCART_TEMPLATE, ElementType.Xpath);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationLocators.MODAL_CLOSE_BTN)));
		webDB.waitForElement(AutomationLocators.MODAL_CLOSE_BTN, ElementType.Xpath);
		flag = webDB.clickAnElement(AutomationLocators.MODAL_CLOSE_BTN, ElementType.Xpath);
		if (flag) {
			log.info("Click on close button");
		}
		webDB.waitForElement(AutomationLocators.ABCART_ENABLE_DISABLE_ICON, ElementType.Xpath);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ABCART_ENABLE_DISABLE_ICON)).isDisplayed();
		log.info("Settings Pop up Closed and Templates Primary Page is Accessed");
		return flag;
	}

	public static boolean VerifyPricingChart() throws InterruptedException, IOException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AutomationLocators.PRICING_HYPERLINK)));
		webDB.clickAnElement(AutomationLocators.PRICING_HYPERLINK, ElementType.Xpath);
		Thread.sleep(1000);
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String PricePageURL = prop.getProperty("PricingPageURL");
		windows = webDB.driver.getWindowHandles();
		iterator = windows.iterator();
		parentWindow = iterator.next();
		childWindow = iterator.next();
		webDB.driver.switchTo().window(childWindow);
		webDB.takeScreenShotPath("Hyperlink", webDB.driver);
		url = webDB.driver.getCurrentUrl();
		log.info("current url is " + url);
		Thread.sleep(1500);
		if (url.equals(PricePageURL)) {
			flag = true;
			log.info("Pricing page displayed");
		} else {
			flag = false;
		}
		webDB.driver.close();
		log.info("closed the child windwo");
		webDB.driver.switchTo().window(parentWindow);
		log.info("navigated to parent window");

		return flag;
	}

	public static boolean VerifyNoteOnOrderShipmentTemplate()
			throws InterruptedException, IOException, FileNotFoundException {
		Thread.sleep(2000);
		flag = webDB.waitForElement(AutomationLocators.ODR_SHIPMENT__ENABLE_DISABLE_ICON, ElementType.Xpath);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ORDER_SHIPMENT_ENABLE_CHECK)).isSelected();
		log.info(flag);
		if (!flag) {
			flag = enableShipmentTemplateMessaging();
			wait.until(ExpectedConditions
					.invisibilityOf(webDB.driver.findElement(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP))));

		}
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ORDER_SHIPMENT_NOTE)).isDisplayed();
		if (flag) {
			log.info("Note is Displayed");
		}
		return flag;
	}

	public static boolean VerifyNoteOnCODTemplate() throws InterruptedException, IOException, FileNotFoundException {
		Thread.sleep(3000);
		flag = webDB.waitForElement(AutomationLocators.COD_ENABLE_DISABLE_ICON, ElementType.Xpath);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.COD_ENABLE_CHECK)).isSelected();
		if (!flag) {
			flag = enableCODTemplateMessaging();
			wait.until(ExpectedConditions
					.invisibilityOf(webDB.driver.findElement(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP))));

		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationLocators.COD_TEMPLATE_NOTE)));
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.COD_TEMPLATE_NOTE)).isDisplayed();
		if (flag) {
			log.info("Note is Displayed");
		}
		return flag;
	}

	public static boolean verifyContactRatingfeature() throws InterruptedException {
		Thread.sleep(2000);
		flag = HeaderText.headertextcheck("Automation");
		return flag;
	}

	public static boolean verifyEnableDisablePaymentSucessfullTemplate() throws InterruptedException {
		flag = webDB.waitForElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
			webDB.moveToElement(AutomationLocators.ORDERCANCELLATION_TOGGLE, ElementType.Xpath);
			// modified below locator
			flag = webDB.waitForElement(AutomationLocators.PAYMENTSUCCESSFULL, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				flag = webDB.IsEnabled(AutomationLocators.PAYMENTSUCCESSFUL_TOGGLE, ElementType.Xpath);
				if (!flag) {
					webDB.clickAnElement(AutomationLocators.PAYMENTSUCCESSFUL_TOGGLE, ElementType.Xpath);
					flag = webDB.waitForElement(AutomationLocators.TEMPLATES_TOAST_MSG, ElementType.Xpath);
					text = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATES_TOAST_MSG)).getText();
				}
				System.out.println("Payment successful toggle is already enabled");
				Thread.sleep(2000);
				webDB.clickAnElement(AutomationLocators.PAYMENTSUCCESSFUL_TOGGLE, ElementType.Xpath);
				System.out.println("Toggle disabled");
				flag = webDB.waitForElement(AutomationLocators.TEMPLATES_TOAST_MSG, ElementType.Xpath);
				String text1 = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATES_TOAST_MSG)).getText();
				// modified - added another click so that settings button is enabled.
				webDB.waitForElement(AutomationLocators.PAYMENTSUCCESSFUL_TOGGLE, ElementType.Xpath);
				webDB.clickAnElement(AutomationLocators.PAYMENTSUCCESSFUL_TOGGLE, ElementType.Xpath);
				System.out.println("Again toggle button enabled");
				if (flag) {
					webDB.waitForElement(AutomationLocators.PAYMENTSUCCESSFUL_SETTINGS, ElementType.Xpath);
					webDB.clickAnElement(AutomationLocators.PAYMENTSUCCESSFUL_SETTINGS, ElementType.Xpath);
					Thread.sleep(2000);
					flag = webDB.clickAnElement(AutomationLocators.SETTINGSCLOSEICON, ElementType.CssSelector);
					if (!flag) {
						Thread.sleep(2000);
						webDB.clickAnElement(AutomationLocators.PAYMENTSUCCESSFUL_TOGGLE, ElementType.Xpath);
						String text2 = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATES_TOAST_MSG))
								.getText();
						Thread.sleep(3000);
						webDB.clickAnElement(AutomationLocators.PAYMENTSUCCESSFUL_SETTINGS, ElementType.Xpath);
						Thread.sleep(2000);
						flag = webDB.waitForElement(AutomationLocators.SETTINGSCLOSEICON, ElementType.CssSelector);
						if (flag) {
							webDB.clickAnElement(AutomationLocators.SETTINGSCLOSEICON, ElementType.CssSelector);
							flag = webDB.waitForElement(AutomationLocators.PAYMENTSUCCESSFUL_SETTINGS,
									ElementType.Xpath);
						}
					}

				}
			}
		}
		System.out.println("Done with payment successful");
		return flag;
	}

	public static boolean verifyEnableDisableOderCancellationTemplate() throws InterruptedException {
		// modified - commented below
		// webDB.moveToElement(AutomationLocators.ORDERCANCELLATION_PRICINGCHART__HYPERLINK,
		// ElementType.Xpath);
		// modified - below locator
		flag = webDB.waitForElement(AutomationLocators.ORDERCANCELLATION, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			flag = webDB.IsEnabled(AutomationLocators.ORDERCANCELLATION_TOGGLE, ElementType.Xpath);
			if (!flag) {
				webDB.clickAnElement(AutomationLocators.ORDERCANCELLATION_TOGGLE, ElementType.Xpath);
				flag = webDB.waitForElement(AutomationLocators.TEMPLATES_TOAST_MSG, ElementType.Xpath);
				text = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATES_TOAST_MSG)).getText();
			}
			System.out.println("Orde cancellation toggle is enabled");
			Thread.sleep(2000);
			webDB.clickAnElement(AutomationLocators.ORDERCANCELLATION_TOGGLE, ElementType.Xpath);
			System.out.println("Order cancellation toggle is disabled");
			flag = webDB.waitForElement(AutomationLocators.TEMPLATES_TOAST_MSG, ElementType.Xpath);
			String text1 = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATES_TOAST_MSG)).getText();
			// modified - added enabling toggle button so that settings gets enabled.
			webDB.waitForElement(AutomationLocators.ORDERCANCELLATION_TOGGLE, ElementType.Xpath);
			webDB.clickAnElement(AutomationLocators.ORDERCANCELLATION_TOGGLE, ElementType.Xpath);
			if (flag) {
				webDB.waitForElement(AutomationLocators.ORDERCANCELLATION_SETTINGS, ElementType.Xpath);
				webDB.clickAnElement(AutomationLocators.ORDERCANCELLATION_SETTINGS, ElementType.Xpath);
				Thread.sleep(2000);
				flag = webDB.clickAnElement(AutomationLocators.SETTINGSCLOSEICON, ElementType.CssSelector);
				if (!flag) {
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.ORDERCANCELLATION_TOGGLE, ElementType.Xpath);
					String text2 = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATES_TOAST_MSG)).getText();
					Thread.sleep(3000);
					webDB.clickAnElement(AutomationLocators.ORDERCANCELLATION_SETTINGS, ElementType.Xpath);
					Thread.sleep(2000);
					flag = webDB.waitForElement(AutomationLocators.SETTINGSCLOSEICON, ElementType.CssSelector);
					if (flag) {
						webDB.clickAnElement(AutomationLocators.SETTINGSCLOSEICON, ElementType.CssSelector);
						flag = webDB.waitForElement(AutomationLocators.ORDERCANCELLATION_SETTINGS, ElementType.Xpath);
					}
				}

			}
		}
		return flag;
	}

	public static boolean verifyPaymentSuccessfullHyperLinks()
			throws InterruptedException, FileNotFoundException, IOException {
		flag = webDB.waitForElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
		if (flag) {
			startTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
			log.info("Test case EXECUTION started at" + " " + startTime);
			webDB.javaScriptClickAnElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
			webDB.scrollToAnElement(AutomationLocators.PAYMENTSUCCESSFULL_PRICINGCHART_HYPERLINK);
			flag = webDB.waitForElement(AutomationLocators.PAYMENTSUCCESSFULL_PRICINGCHART_HYPERLINK,
					ElementType.Xpath);
			if (flag) {
				flag = webDB.switchToChildWindowAndVerifyURL(
						AutomationLocators.PAYMENTSUCCESSFULL_PRICINGCHART_HYPERLINK,
						AutomationLocators.PRICINGCHART_URL);
				if (flag) {
					webDB.scrollElement(AutomationLocators.COD_ENABLE_DISABLE_ICON);
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.PAYMENTSUCCESSFUL_SETTINGS, ElementType.Xpath);
					Thread.sleep(2000);
					flag = webDB.waitForElement(AutomationLocators.PHONENUMBER_HYPERLINK, ElementType.Xpath);
					if (flag) {
						flag = webDB.switchToChildWindowAndVerifyURL(AutomationLocators.PHONENUMBER_HYPERLINK,
								AutomationLocators.CONFIGURATION_URL);
						if (flag) {
							flag = webDB.switchToChildWindowAndVerifyURL(AutomationLocators.BUSINESSNAME_HYPERLINK,
									AutomationLocators.CONFIGURATION_URL);
							if (flag) {
								flag = webDB.switchToChildWindowAndVerifyURL(
										AutomationLocators.WHATSAPPSUPPORT_HYPERLINK,
										AutomationLocators.CONFIGURATION_URL);
								webDB.clickAnElement(AutomationLocators.SETTINGSCLOSEICON, ElementType.CssSelector);
								if (flag) {
									flag = webDB.waitForElement(AutomationLocators.PAYMENTSUCCESSFUL_SETTINGS,
											ElementType.Xpath);
								}
							}
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyOrderCancellationHyperLinks()
			throws InterruptedException, FileNotFoundException, IOException {
		webDB.scrollToAnElement(AutomationLocators.ORDERCANCELLATION_PRICINGCHART__HYPERLINK);
		flag = webDB.waitForElement(AutomationLocators.ORDERCANCELLATION_PRICINGCHART__HYPERLINK, ElementType.Xpath);
		if (flag) {
			flag = webDB.switchToChildWindowAndVerifyURL(AutomationLocators.ORDERCANCELLATION_PRICINGCHART__HYPERLINK,
					AutomationLocators.PRICINGCHART_URL);
			if (flag) {
				webDB.clickAnElement(AutomationLocators.ORDERCANCELLATION_SETTINGS, ElementType.Xpath);
				flag = webDB.waitForElement(AutomationLocators.PHONENUMBER_HYPERLINK, ElementType.Xpath);
				if (flag) {
					flag = webDB.switchToChildWindowAndVerifyURL(AutomationLocators.PHONENUMBER_HYPERLINK,
							AutomationLocators.CONFIGURATION_URL);
					if (flag) {
						flag = webDB.switchToChildWindowAndVerifyURL(AutomationLocators.BUSINESSNAME_HYPERLINK,
								AutomationLocators.CONFIGURATION_URL);
						if (flag) {
							flag = webDB.switchToChildWindowAndVerifyURL(AutomationLocators.WHATSAPPSUPPORT_HYPERLINK,
									AutomationLocators.CONFIGURATION_URL);
							webDB.clickAnElement(AutomationLocators.SETTINGSCLOSEICON, ElementType.CssSelector);
							if (flag) {
								flag = webDB.waitForElement(AutomationLocators.ORDERCANCELLATION_SETTINGS,
										ElementType.Xpath);
							}
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyTemplateLanguage() throws InterruptedException, FileNotFoundException, IOException {
		flag = webDB.waitForElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
			webDB.moveToElement(AutomationLocators.ORDERCANCELLATION_TOGGLE, ElementType.Xpath);
			flag = webDB.waitForElement(AutomationLocators.PAYMENTSUCCESSFUL_SETTINGS, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(AutomationLocators.PAYMENTSUCCESSFUL_SETTINGS, ElementType.Xpath);
				Thread.sleep(2000);
				flag = webDB.waitForElement(AutomationLocators.SETTINGSPOPUP, ElementType.CssSelector);
				if (flag) {
					flag = webDB.waitForElement(AutomationLocators.SPANISH_LANGUAGE, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(AutomationLocators.SPANISH_LANGUAGE, ElementType.Xpath);
						Thread.sleep(2000);
						flag = webDB.waitForElement(AutomationLocators.SAVE_BTN, ElementType.Xpath);
						if (flag) {
							flag = webDB.waitForElement(AutomationLocators.SPANISH_TEMPLATE_TXT, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(AutomationLocators.ORDERCANCELLATION_SETTINGS, ElementType.Xpath);
								Thread.sleep(2000);
								flag = webDB.waitForElement(AutomationLocators.SETTINGSPOPUP, ElementType.CssSelector);
								if (flag) {
									flag = webDB.waitForElement(AutomationLocators.ENGLISH_LANGUAGE, ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(AutomationLocators.ENGLISH_LANGUAGE, ElementType.Xpath);
										Thread.sleep(2000);
										flag = webDB.waitForElement(AutomationLocators.SAVE_BTN, ElementType.Xpath);
										if (flag) {
											webDB.clickAnElement(AutomationLocators.SAVE_BTN, ElementType.Xpath);
											flag = webDB.waitForElement(
													AutomationLocators.PAYMENTSUCCESSFULL_ENGLISH_TXTMSG,
													ElementType.Xpath);
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

	public static boolean verifyDiscount() throws InterruptedException, FileNotFoundException, IOException {
		flag = webDB.waitForElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
			webDB.moveToElement(AutomationLocators.ORDERCANCELLATION_TOGGLE, ElementType.Xpath);
			flag = webDB.waitForElement(AutomationLocators.PAYMENTSUCCESSFUL_SETTINGS, ElementType.Xpath);
			if (flag) {
				Thread.sleep(1000);
				webDB.clickAnElement(AutomationLocators.PAYMENTSUCCESSFUL_SETTINGS, ElementType.Xpath);
				Thread.sleep(1500);
				flag = webDB.waitForElement(AutomationLocators.SETTINGSPOPUP, ElementType.CssSelector);
				if (flag) {
					webDB.moveToElement(AutomationLocators.DISCOUNT_TXTBOX, ElementType.Xpath);
					flag = webDB.waitForElement(AutomationLocators.DISCOUNT, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(AutomationLocators.DISCOUNT, ElementType.Xpath);
						flag = webDB.waitForElement(AutomationLocators.DISCOUNT_TXTBOX, ElementType.Xpath);
						if (flag) {
							Thread.sleep(3000);
							webDB.javaScriptClickAnElement(AutomationLocators.DISCOUNT_TXTBOX, ElementType.Xpath);
							WebElement element = webDB.driver.findElement(By.xpath(AutomationLocators.DISCOUNT_TXTBOX));
							String elementval = element.getAttribute("value");
							webDB.clearText(AutomationLocators.DISCOUNT_TXTBOX);
							Thread.sleep(3000);
							flag = webDB.waitForElement(AutomationLocators.DISCOUNT_EMPTYERROR, ElementType.Xpath);
							if (flag) {
								webDB.sendTextToAnElement(AutomationLocators.DISCOUNT_TXTBOX,
										AutomationLocators.DISCOUNT_VALUE0, ElementType.Xpath);
								Thread.sleep(2000);
								flag = webDB.waitForElement(AutomationLocators.DISCOUNT_ZEROERROR, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(AutomationLocators.DISCOUNT_TXTBOX, ElementType.Xpath);
									webDB.clearTextField(AutomationLocators.DISCOUNT_TXTBOX, ElementType.Xpath);
									Thread.sleep(2000);
									webDB.sendTextToAnElement(AutomationLocators.DISCOUNT_TXTBOX,
											AutomationLocators.DISCOUNT_VALUE99, ElementType.Xpath);
									flag = webDB.waitForElement(AutomationLocators.DISCOUNT_INCREASE,
											ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(AutomationLocators.DISCOUNT_INCREASE, ElementType.Xpath);
										Thread.sleep(2000);
										webDB.clickAnElement(AutomationLocators.DISCOUNT_INCREASE, ElementType.Xpath);
										String text = webDB.getText(AutomationLocators.DISCOUNT_TXTBOX,
												ElementType.Xpath);
										flag = !text.equalsIgnoreCase(AutomationLocators.DISCOUNT_VALUE101);
										if (flag) {
											webDB.clickAnElement(AutomationLocators.DISCOUNT_DECREASE,
													ElementType.Xpath);
											String text1 = webDB.getText(AutomationLocators.DISCOUNT_TXTBOX,
													ElementType.Xpath);
											flag = text1.equalsIgnoreCase(AutomationLocators.DISCOUNT_VALUE99);
											webDB.clickAnElement(AutomationLocators.SAVE_BTN, ElementType.Xpath);
											flag = webDB.waitForElement(AutomationLocators.DISCOUNT_TXTMSG,
													ElementType.Xpath);
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

	public static boolean verifyFixedDiscount() throws InterruptedException, FileNotFoundException, IOException {
		flag = webDB.waitForElement(AutomationLocators.PAYMENTSUCCESSFUL_SETTINGS, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AutomationLocators.PAYMENTSUCCESSFUL_SETTINGS, ElementType.Xpath);
			Thread.sleep(3000);
			flag = webDB.waitForElement(AutomationLocators.SETTINGSPOPUP, ElementType.CssSelector);
			if (flag) {
				webDB.moveToElement(AutomationLocators.DISCOUNT_BOX, ElementType.Xpath);
				Thread.sleep(2000);
				flag = webDB.waitForElement(AutomationLocators.FIXEDDISCOUNT, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(AutomationLocators.FIXEDDISCOUNT, ElementType.Xpath);
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.SAVE_BTN, ElementType.Xpath);
					Thread.sleep(2000);
//					flag = webDB.waitForElement(TemplatesLocators.FIXEDDISCOUNT_TXTMSG, ElementType.Xpath);
//					if (flag) {
//						String text = webDB.getText(TemplatesLocators.FIXEDDISCOUNT_TXTMSG, ElementType.Xpath);
//						flag = text.contains(TemplatesLocators.FIXEDDISCOUNT_TEXT);
//						if (flag) {
//							webDB.clickAnElement(TemplatesLocators.PAYMENTSUCCESSFUL_SETTINGS, ElementType.Xpath);
//							Thread.sleep(2000);
//							flag = webDB.waitForElement(TemplatesLocators.SETTINGSPOPUP, ElementType.CssSelector);
//							if (flag) {
					webDB.moveToElement(AutomationLocators.SHOPIFYADMINDISCOUNTS_HYPERLINK, ElementType.Xpath);
					Thread.sleep(2000);
					webDB.clearText(AutomationLocators.FIXEDDISCOUNT_TXTBOX);
					Thread.sleep(2000);
					flag = webDB.waitForElement(AutomationLocators.FIXEDDISCOUNT_EMPTYERROR, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(AutomationLocators.SETTINGS_CANCEL_BTN, ElementType.Xpath);
						Thread.sleep(2000);
						flag = webDB.waitForElement(AutomationLocators.PAYMENTSUCCESSFUL_SETTINGS, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(AutomationLocators.PAYMENTSUCCESSFUL_SETTINGS, ElementType.Xpath);
							flag = webDB.waitForElement(AutomationLocators.SETTINGSPOPUP, ElementType.CssSelector);
							if (flag) {
								Thread.sleep(2000);
//											webDB.moveToElement(TemplatesLocators.SHOPIFYADMINDISCOUNTS_HYPERLINK,
//													ElementType.Xpath);
								Thread.sleep(2000);
								WebElement element = webDB.driver
										.findElement(By.xpath(AutomationLocators.DISCOUNT_TXTBOX));
								String text1 = element.getAttribute("value");
//											flag = text1.contains(TemplatesLocators.FIXEDCOUPON_TEXT);
//											if (flag) {
								webDB.clickAnElement(AutomationLocators.SETTINGSCLOSEICON, ElementType.CssSelector);
								flag = webDB.waitForElement(AutomationLocators.PAYMENTSUCCESSFUL_SETTINGS,
										ElementType.Xpath);
//											}
//										}
//									}
//								}
							}
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyAutomatedPromotionalTabAndNote() throws InterruptedException {
		flag = webDB.waitForElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
			Thread.sleep(2000);
			flag = webDB.waitForElement(AutomationLocators.AUTOMATEDPROMOTIONAL_TAB, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(AutomationLocators.AUTOMATEDPROMOTIONAL_TAB, ElementType.Xpath);
				Thread.sleep(2000);
				String subnote = webDB.getText(AutomationLocators.AUTOMATEDPROMOTIONAL_SUBNOTE, ElementType.Xpath);
				flag = subnote.contains(AutomationLocators.AUTOMATEDPROMOTIONAL_SUBNOTE_TXT);
				if (flag) {
					js.executeScript("window.localStorage.clear();");
					webDB.driver.navigate().refresh();
					webDB.moveToElement(AutomationLocators.RATEOURAPP, ElementType.Xpath);
					flag = webDB.waitForElement(AutomationLocators.CONTACTUS_NOTE, ElementType.Xpath);
					if (flag) {
						String text1 = webDB.getText(AutomationLocators.CONTACTUS_NOTE, ElementType.Xpath);
						// modified below
						flag = text1.contains(AutomationLocators.CONTACTUS_NOTE_TXT);
						if (flag) {
							flag = webDB.waitForElement(AutomationLocators.RATEOURAPP_NOTE, ElementType.Xpath);
							if (flag) {
								String text2 = webDB.getText(AutomationLocators.RATEOURAPP_NOTE, ElementType.Xpath);
								flag = text2.contains(AutomationLocators.RATEOURAPP_NOTE_TXT);
							}
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyEnableDisableCrossSellTemplate() throws InterruptedException {
		flag = webDB.waitForElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
			Thread.sleep(2000);
			webDB.driver.navigate().refresh();
			webDB.moveToElement(AutomationLocators.AUTOMATEDPROMOTIONAL_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(AutomationLocators.AUTOMATEDPROMOTIONAL_TAB, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(AutomationLocators.AUTOMATEDPROMOTIONAL_TAB, ElementType.Xpath);
				Thread.sleep(2000);
				flag = webDB.waitForElement(AutomationLocators.CROSSSELL_TOGGLE, ElementType.Xpath);
				if (flag) {
					Thread.sleep(2000);
					flag = webDB.IsEnabled(AutomationLocators.CROSSSELL_TOGGLE, ElementType.Xpath);
					if (!flag) {
						webDB.clickAnElement(AutomationLocators.CROSSSELL_TOGGLE, ElementType.Xpath);
						flag = webDB.waitForElement(AutomationLocators.TEMPLATES_TOAST_MSG, ElementType.Xpath);
						text = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATES_TOAST_MSG)).getText();
					}
					System.out.println("Cross Sell is enabled");
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.CROSSSELL_TOGGLE, ElementType.Xpath);
					System.out.println("Cross Sell is disabled.");
					flag = webDB.waitForElement(AutomationLocators.TEMPLATES_TOAST_MSG, ElementType.Xpath);
					String text1 = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATES_TOAST_MSG)).getText();
					if (flag) {
						// modified - added enable so that settings is enabled.
						webDB.waitForElement(AutomationLocators.CROSSSELL_TOGGLE, ElementType.Xpath);
						webDB.clickAnElement(AutomationLocators.CROSSSELL_TOGGLE, ElementType.Xpath);
						System.out.println("Cross Sell is again enabled");
						// modified - added wait
						webDB.waitForElement(AutomationLocators.CROSSSELL_SETTINGS, ElementType.Xpath);
						webDB.clickAnElement(AutomationLocators.CROSSSELL_SETTINGS, ElementType.Xpath);
						Thread.sleep(2000);
						System.out.println("entered into cross sell settings");
						flag = webDB.clickAnElement(AutomationLocators.SETTINGSCLOSEICON, ElementType.CssSelector);
						if (!flag) {
							Thread.sleep(2000);
							webDB.clickAnElement(AutomationLocators.CROSSSELL_TOGGLE, ElementType.Xpath);
							String text2 = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATES_TOAST_MSG))
									.getText();
							Thread.sleep(3000);
							webDB.clickAnElement(AutomationLocators.CROSSSELL_SETTINGS, ElementType.Xpath);
							Thread.sleep(2000);
							// modified
							flag = webDB.waitForElement(AutomationLocators.SETTINGSCLOSEICON, ElementType.CssSelector);
							if (flag) {
								webDB.clickAnElement(AutomationLocators.SETTINGSCLOSEICON, ElementType.CssSelector);
								flag = webDB.waitForElement(AutomationLocators.CROSSSELL_SETTINGS, ElementType.Xpath);
							}
						}

					}
				}
			}
		}
		System.out.println("Done with Cross Sell");
		return flag;
	}

	public static boolean verifyEnableDisableCustomerWinbackTemplate() throws InterruptedException {
		webDB.moveToElement(AutomationLocators.CUSTOMERWINBACK, ElementType.Xpath);
		flag = webDB.waitForElement(AutomationLocators.CUSTOMERWINBACK, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			flag = webDB.IsEnabled(AutomationLocators.CUSTOMERWINBACK_TOGGLE, ElementType.Xpath);
			if (!flag) {
				webDB.clickAnElement(AutomationLocators.CUSTOMERWINBACK_TOGGLE, ElementType.Xpath);
				flag = webDB.waitForElement(AutomationLocators.TEMPLATES_TOAST_MSG, ElementType.Xpath);
				text = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATES_TOAST_MSG)).getText();
			}
			System.out.println("Win Back toggle is enabled");
			Thread.sleep(2000);
			webDB.clickAnElement(AutomationLocators.CUSTOMERWINBACK_TOGGLE, ElementType.Xpath);
			System.out.println("Win Back toggle is disabled");
			flag = webDB.waitForElement(AutomationLocators.TEMPLATES_TOAST_MSG, ElementType.Xpath);
			String text1 = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATES_TOAST_MSG)).getText();
			if (flag) {
				// modified - adding enable toggle
				webDB.waitForElement(AutomationLocators.CUSTOMERWINBACK_TOGGLE, ElementType.Xpath);
				webDB.clickAnElement(AutomationLocators.CUSTOMERWINBACK_TOGGLE, ElementType.Xpath);
				// modified - added wait time
				webDB.waitForElement(AutomationLocators.CUSTOMERWINBACK_SETTINGS, ElementType.Xpath);
				webDB.clickAnElement(AutomationLocators.CUSTOMERWINBACK_SETTINGS, ElementType.Xpath);
				Thread.sleep(2000);
				flag = webDB.clickAnElement(AutomationLocators.SETTINGSCLOSEICON, ElementType.CssSelector);
				if (!flag) {
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.CUSTOMERWINBACK_TOGGLE, ElementType.Xpath);
					String text2 = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATES_TOAST_MSG)).getText();
					Thread.sleep(3000);
					webDB.clickAnElement(AutomationLocators.CUSTOMERWINBACK_SETTINGS, ElementType.Xpath);
					Thread.sleep(2000);
					// modified
					flag = webDB.waitForElement(AutomationLocators.SETTINGSCLOSEICON, ElementType.CssSelector);
					if (flag) {
						webDB.clickAnElement(AutomationLocators.SETTINGSCLOSEICON, ElementType.CssSelector);
						flag = webDB.waitForElement(AutomationLocators.CUSTOMERWINBACK_SETTINGS, ElementType.Xpath);
					}
				}

			}
		}
		System.out.println("Done with Winback");
		return flag;
	}

	public static boolean verifyEnableDisableFeedbackTemplate() throws InterruptedException {
		// modified - commented below
//		webDB.moveToElement(AutomationLocators.FEEDBACK_PRICINGCHART_HYPERLINK, ElementType.Xpath);
		flag = webDB.waitForElement(AutomationLocators.FEEDBACK, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			flag = webDB.IsEnabled(AutomationLocators.FEEDBACK_TOGGLE, ElementType.Xpath);
			if (!flag) {
				webDB.clickAnElement(AutomationLocators.FEEDBACK_TOGGLE, ElementType.Xpath);
				flag = webDB.waitForElement(AutomationLocators.TEMPLATES_TOAST_MSG, ElementType.Xpath);
				text = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATES_TOAST_MSG)).getText();
			}
			System.out.println("Feed back toggle is enabled");
			Thread.sleep(2000);
			webDB.clickAnElement(AutomationLocators.FEEDBACK_TOGGLE, ElementType.Xpath);
			System.out.println("Feed back toggle is disabled");
			flag = webDB.waitForElement(AutomationLocators.TEMPLATES_TOAST_MSG, ElementType.Xpath);
			String text1 = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATES_TOAST_MSG)).getText();
			if (flag) {
				// modified - added enabling
				webDB.waitForElement(AutomationLocators.FEEDBACK_TOGGLE, ElementType.Xpath);
				webDB.clickAnElement(AutomationLocators.FEEDBACK_TOGGLE, ElementType.Xpath);
				// modified - added wait time
				webDB.waitForElement(AutomationLocators.FEEDBACK_SETTINGS, ElementType.Xpath);
				webDB.clickAnElement(AutomationLocators.FEEDBACK_SETTINGS, ElementType.Xpath);
				Thread.sleep(2000);
				flag = webDB.clickAnElement(AutomationLocators.SETTINGSCLOSEICON, ElementType.CssSelector);
				if (!flag) {
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.FEEDBACK_TOGGLE, ElementType.Xpath);
					String text2 = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATES_TOAST_MSG)).getText();
					Thread.sleep(3000);
					webDB.clickAnElement(AutomationLocators.FEEDBACK_SETTINGS, ElementType.Xpath);
					Thread.sleep(2000);
					// modified
					flag = webDB.waitForElement(AutomationLocators.SETTINGSCLOSEICON, ElementType.CssSelector);
					if (flag) {
						webDB.clickAnElement(AutomationLocators.SETTINGSCLOSEICON, ElementType.CssSelector);
						flag = webDB.waitForElement(AutomationLocators.FEEDBACK_SETTINGS, ElementType.Xpath);
					}
				}

			}
		}
		return flag;
	}

	public static boolean verifyCrossSellHyperLinks() throws InterruptedException, FileNotFoundException, IOException {
		flag = webDB.waitForElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
		if (flag) {
			webDB.driver.navigate().refresh();
			webDB.moveToElement(AutomationLocators.AUTOMATEDPROMOTIONAL_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(AutomationLocators.AUTOMATEDPROMOTIONAL_TAB, ElementType.Xpath);
			if (flag) {
				webDB.javaScriptClickAnElement(AutomationLocators.AUTOMATEDPROMOTIONAL_TAB, ElementType.Xpath);
				Thread.sleep(3000);
				webDB.scrollElement(AutomationLocators.CONTACTUS_NOTE);
				Thread.sleep(4000);
				// modified - commented below as not available.
//				flag = webDB.waitForElement(AutomationLocators.CROSSSELL_PRICINGCHART_HYPERLINK, ElementType.Xpath);
//				if (flag) {
//					Thread.sleep(5000);
//					flag = webDB.switchToChildWindowjavaScriptClickAndVerifyURL(
//							AutomationLocators.CROSSSELL_PRICINGCHART_HYPERLINK, AutomationLocators.PRICINGCHART_URL);
//					if (flag) {
				Thread.sleep(2000);
				webDB.scrollElement(AutomationLocators.CROSSSELL_TOGGLE);
				webDB.clickAnElement(AutomationLocators.CROSSSELL_SETTINGS, ElementType.Xpath);
				flag = webDB.waitForElement(AutomationLocators.PHONENUMBER_HYPERLINK, ElementType.Xpath);
				if (flag) {
					flag = webDB.switchToChildWindowAndVerifyURL(AutomationLocators.PHONENUMBER_HYPERLINK,
							AutomationLocators.CONFIGURATION_URL);
					if (flag) {
						flag = webDB.switchToChildWindowAndVerifyURL(AutomationLocators.BUSINESSNAME_HYPERLINK,
								AutomationLocators.CONFIGURATION_URL);
						if (flag) {
							flag = webDB.switchToChildWindowAndVerifyURL(AutomationLocators.WHATSAPPSUPPORT_HYPERLINK,
									AutomationLocators.CONFIGURATION_URL);
							webDB.clickAnElement(AutomationLocators.SETTINGSCLOSEICON, ElementType.CssSelector);
							Thread.sleep(2000);
							webDB.moveToElement(AutomationLocators.CROSSSELL_SETTINGS, ElementType.Xpath);
							if (flag) {
								flag = webDB.waitForElement(AutomationLocators.CROSSSELL_SETTINGS, ElementType.Xpath);
							}
						}
					}
				}
			}
		}
//			}
//		}
		System.out.println("Done with cross sell");
		return flag;
	}

	public static boolean verifyCustomerWinbackHyperLinks()
			throws InterruptedException, FileNotFoundException, IOException {
		// modified - commented below as not available.
//		webDB.moveToElement(AutomationLocators.CUSTOMERWINBACK_PRICINGCHART_HYPERLINK, ElementType.Xpath);
//		flag = webDB.waitForElement(AutomationLocators.CUSTOMERWINBACK_PRICINGCHART_HYPERLINK, ElementType.Xpath);
//		if (flag) {
//			flag = webDB.switchToChildWindowjavaScriptClickAndVerifyURL(
//					AutomationLocators.CUSTOMERWINBACK_PRICINGCHART_HYPERLINK, AutomationLocators.PRICINGCHART_URL);
//			if (flag) {
		Thread.sleep(1500);
		webDB.scrollElement(AutomationLocators.CUSTOMERWINBACK_TOGGLE);
		Thread.sleep(1500);
		webDB.clickAnElement(AutomationLocators.CUSTOMERWINBACK_SETTINGS, ElementType.Xpath);
		flag = webDB.waitForElement(AutomationLocators.PHONENUMBER_HYPERLINK, ElementType.Xpath);
		if (flag) {
			flag = webDB.switchToChildWindowAndVerifyURL(AutomationLocators.PHONENUMBER_HYPERLINK,
					AutomationLocators.CONFIGURATION_URL);
			if (flag) {
				flag = webDB.switchToChildWindowAndVerifyURL(AutomationLocators.BUSINESSNAME_HYPERLINK,
						AutomationLocators.CONFIGURATION_URL);
				if (flag) {
					flag = webDB.switchToChildWindowAndVerifyURL(AutomationLocators.WHATSAPPSUPPORT_HYPERLINK,
							AutomationLocators.CONFIGURATION_URL);
					webDB.clickAnElement(AutomationLocators.SETTINGSCLOSEICON, ElementType.CssSelector);
					if (flag) {
						webDB.scrollToAnElement(AutomationLocators.CUSTOMERWINBACK_SETTINGS);
						flag = webDB.waitForElement(AutomationLocators.CUSTOMERWINBACK_SETTINGS, ElementType.Xpath);
					}
				}
			}
		}
//			}
//		}
		System.err.println("Done with win back");
		return flag;
	}

	public static boolean verifyFeedbackHyperLinks() throws InterruptedException, FileNotFoundException, IOException {
		// modified - as not available.
//		webDB.moveToElement(AutomationLocators.FEEDBACK_PRICINGCHART_HYPERLINK, ElementType.Xpath);
//		flag = webDB.waitForElement(AutomationLocators.FEEDBACK_PRICINGCHART_HYPERLINK, ElementType.Xpath);
//		if (flag) {
//			flag = webDB.switchToChildWindowjavaScriptClickAndVerifyURL(
//					AutomationLocators.FEEDBACK_PRICINGCHART_HYPERLINK, AutomationLocators.PRICINGCHART_URL);
//			if (flag) {
		webDB.clickAnElement(AutomationLocators.FEEDBACK_SETTINGS, ElementType.Xpath);
		flag = webDB.waitForElement(AutomationLocators.PHONENUMBER_HYPERLINK, ElementType.Xpath);
		if (flag) {
			flag = webDB.switchToChildWindowAndVerifyURL(AutomationLocators.PHONENUMBER_HYPERLINK,
					AutomationLocators.CONFIGURATION_URL);
			if (flag) {
				flag = webDB.switchToChildWindowAndVerifyURL(AutomationLocators.BUSINESSNAME_HYPERLINK,
						AutomationLocators.CONFIGURATION_URL);
				if (flag) {
					flag = webDB.switchToChildWindowAndVerifyURL(AutomationLocators.WHATSAPPSUPPORT_HYPERLINK,
							AutomationLocators.CONFIGURATION_URL);
					webDB.clickAnElement(AutomationLocators.SETTINGSCLOSEICON, ElementType.CssSelector);
					if (flag) {
						webDB.moveToElement(AutomationLocators.FEEDBACK_SETTINGS, ElementType.Xpath);
						flag = webDB.waitForElement(AutomationLocators.FEEDBACK_SETTINGS, ElementType.Xpath);
					}
				}
			}
		}
//			}
//		}
		return flag;
	}

	public static boolean verifySettingsNote() throws InterruptedException, FileNotFoundException, IOException {
		flag = webDB.waitForElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
			Thread.sleep(2000);
			webDB.driver.navigate().refresh();
			webDB.moveToElement(AutomationLocators.AUTOMATEDPROMOTIONAL_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(AutomationLocators.AUTOMATEDPROMOTIONAL_TAB, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(AutomationLocators.AUTOMATEDPROMOTIONAL_TAB, ElementType.Xpath);
				Thread.sleep(1000);
				webDB.moveToElement(AutomationLocators.CROSSSELL_TOGGLE, ElementType.Xpath);
				Thread.sleep(1000);
				flag = webDB.waitForElement(AutomationLocators.CROSSSELL_SETTINGS, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(AutomationLocators.CROSSSELL_SETTINGS, ElementType.Xpath);
					Thread.sleep(2000);
					flag = webDB.waitForElement(AutomationLocators.SETTINGSPOPUP, ElementType.CssSelector);
					if (flag) {
						flag = webDB.waitForElement(AutomationLocators.WHATSAPPNOTE, ElementType.Xpath);
						if (flag) {
							webDB.moveToElement(AutomationLocators.DISCOUNT_BOX, ElementType.Xpath);
							flag = webDB.waitForElement(AutomationLocators.DISCOUNTNOTE, ElementType.Xpath);
							if (flag) {
								webDB.moveToElement(AutomationLocators.INTERVAL_NOTE, ElementType.Xpath);
								flag = webDB.waitForElement(AutomationLocators.INTERVALNOTE, ElementType.Xpath);
								if (flag) {
									flag = webDB.waitForElement(AutomationLocators.SETTINGSCLOSEICON,
											ElementType.CssSelector);
									if (flag) {
										webDB.clickAnElement(AutomationLocators.SETTINGSCLOSEICON,
												ElementType.CssSelector);
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

	public static boolean verifyIncludeExcludeDiscount()
			throws InterruptedException, FileNotFoundException, IOException {
		webDB.moveToElement(AutomationLocators.AUTOMATEDPROMOTIONAL_TAB, ElementType.Xpath);
		flag = webDB.waitForElement(AutomationLocators.CROSSSELL_SETTINGS, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			webDB.javaScriptClickAnElement(AutomationLocators.CROSSSELL_SETTINGS, ElementType.Xpath);
			flag = webDB.waitForElement(AutomationLocators.SETTINGSPOPUP, ElementType.CssSelector);
			if (flag) {
				Thread.sleep(2000);
				webDB.moveToElement(AutomationLocators.INTERVAL_NOTE, ElementType.Xpath);
				flag = webDB.waitForElement(AutomationLocators.INCLUDE_DISCOUNT, ElementType.Xpath);
				if (flag) {
					Thread.sleep(2000);
					flag = webDB.driver.findElement(By.xpath(AutomationLocators.INCLUDE_DISCOUNT)).isSelected();
					if (!flag) {
						webDB.clickAnElement(AutomationLocators.INCLUDE_DISCOUNT, ElementType.Xpath);
						flag = true;
					}
					flag = webDB.waitForElement(AutomationLocators.SAVE_BTN, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(AutomationLocators.SAVE_BTN, ElementType.Xpath);
						Thread.sleep(3000);
						flag = webDB.waitForElement(AutomationLocators.DISCOUNT_TXT_MSG, ElementType.Xpath);
						if (!flag) {
							webDB.javaScriptClickAnElement(AutomationLocators.CROSSSELL_SETTINGS, ElementType.Xpath);
							Thread.sleep(2000);
							flag = webDB.waitForElement(AutomationLocators.SETTINGSPOPUP, ElementType.CssSelector);
							if (flag) {
								webDB.moveToElement(AutomationLocators.INTERVAL_NOTE, ElementType.Xpath);
								flag = webDB.driver.findElement(By.xpath(AutomationLocators.INCLUDE_DISCOUNT))
										.isSelected();
								if (!flag) {
									webDB.clickAnElement(AutomationLocators.INCLUDE_DISCOUNT, ElementType.Xpath);
									flag = true;
								}
								Thread.sleep(2000);
								flag = webDB.waitForElement(AutomationLocators.DISCOUNT_BOX, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(AutomationLocators.SAVE_BTN, ElementType.Xpath);
									flag = webDB.waitForElement(AutomationLocators.CROSSSELL_SETTINGS,
											ElementType.Xpath);
									if (flag) {
										webDB.moveToElement(AutomationLocators.CUSTOMERWINBACK_SETTINGS,
												ElementType.Xpath);
										flag = webDB.waitForElement(AutomationLocators.CUSTOMERWINBACK_SETTINGS,
												ElementType.Xpath);
										if (flag) {
											webDB.javaScriptClickAnElement(AutomationLocators.CUSTOMERWINBACK_SETTINGS,
													ElementType.Xpath);
											Thread.sleep(2000);
											flag = webDB.waitForElement(AutomationLocators.SETTINGSPOPUP,
													ElementType.CssSelector);
											if (flag) {
												webDB.moveToElement(AutomationLocators.INTERVAL_NOTE,
														ElementType.Xpath);
												flag = webDB.waitForElement(AutomationLocators.INCLUDE_DISCOUNT,
														ElementType.Xpath);
												if (flag) {
													Thread.sleep(2000);
													flag = webDB.driver
															.findElement(By.xpath(AutomationLocators.INCLUDE_DISCOUNT))
															.isSelected();
													if (!flag) {
														webDB.clickAnElement(AutomationLocators.INCLUDE_DISCOUNT,
																ElementType.Xpath);
														flag = true;
													}
													flag = webDB.waitForElement(AutomationLocators.SAVE_BTN,
															ElementType.Xpath);
													if (flag) {
														webDB.clickAnElement(AutomationLocators.SAVE_BTN,
																ElementType.Xpath);
														Thread.sleep(3000);
														webDB.moveToElement(AutomationLocators.FEEDBACK_SETTINGS,
																ElementType.Xpath);
														String text = webDB.getText(
																AutomationLocators.WINBACKDISCOUNTTXTMSG,
																ElementType.Xpath);
														flag = text.contains(AutomationLocators.WINBACKDISCOUNT_TXTMSG);
														if (!flag) {
															webDB.clickAnElement(
																	AutomationLocators.CUSTOMERWINBACK_SETTINGS,
																	ElementType.Xpath);
															Thread.sleep(2000);
															flag = webDB.waitForElement(
																	AutomationLocators.SETTINGSPOPUP,
																	ElementType.CssSelector);
															if (flag) {
																webDB.moveToElement(AutomationLocators.INTERVAL_NOTE,
																		ElementType.Xpath);
																webDB.clickAnElement(
																		AutomationLocators.INCLUDE_DISCOUNT,
																		ElementType.Xpath);
																Thread.sleep(2000);
																flag = webDB.waitForElement(
																		AutomationLocators.DISCOUNT_BOX,
																		ElementType.Xpath);
																if (flag) {
																	webDB.clickAnElement(AutomationLocators.SAVE_BTN,
																			ElementType.Xpath);
																	flag = webDB.waitForElement(
																			AutomationLocators.CUSTOMERWINBACK_SETTINGS,
																			ElementType.Xpath);
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
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyCrossSellDiscount() throws InterruptedException, FileNotFoundException, IOException {
		flag = webDB.waitForElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
			Thread.sleep(2000);
			webDB.driver.navigate().refresh();
			webDB.moveToElement(AutomationLocators.AUTOMATEDPROMOTIONAL_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(AutomationLocators.AUTOMATEDPROMOTIONAL_TAB, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				webDB.clickAnElement(AutomationLocators.AUTOMATEDPROMOTIONAL_TAB, ElementType.Xpath);
				webDB.moveToElement(AutomationLocators.CROSSSELL_TOGGLE, ElementType.Xpath);
				flag = webDB.waitForElement(AutomationLocators.CROSSSELL_SETTINGS, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(AutomationLocators.CROSSSELL_SETTINGS, ElementType.Xpath);
					Thread.sleep(2000);
					flag = webDB.waitForElement(AutomationLocators.SETTINGSPOPUP, ElementType.CssSelector);
					if (flag) {
						webDB.moveToElement(AutomationLocators.INTERVAL_NOTE, ElementType.Xpath);
						flag = webDB.waitForElement(AutomationLocators.INCLUDE_DISCOUNT, ElementType.Xpath);
						if (flag) {
							Thread.sleep(2500);
							flag = webDB.driver.findElement(By.xpath(AutomationLocators.INCLUDE_DISCOUNT)).isSelected();
							if (!flag) {
								webDB.clickAnElement(AutomationLocators.INCLUDE_DISCOUNT, ElementType.Xpath);
								flag = true;
							}
							flag = webDB.waitForElement(AutomationLocators.DISCOUNT, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(AutomationLocators.DISCOUNT, ElementType.Xpath);
								flag = webDB.waitForElement(AutomationLocators.DISCOUNT_TXTBOX, ElementType.Xpath);
								if (flag) {
									Thread.sleep(3000);
									webDB.javaScriptClickAnElement(AutomationLocators.DISCOUNT_TXTBOX,
											ElementType.Xpath);
									WebElement element = webDB.driver
											.findElement(By.xpath(AutomationLocators.DISCOUNT_TXTBOX));
									String elementval = element.getAttribute("value");
									Thread.sleep(1000);
									webDB.clearText(AutomationLocators.DISCOUNT_TXTBOX);
									Thread.sleep(3000);
									flag = webDB.waitForElement(AutomationLocators.DISCOUNT_EMPTYERROR,
											ElementType.Xpath);
									if (flag) {
										webDB.sendTextToAnElement(AutomationLocators.DISCOUNT_TXTBOX,
												AutomationLocators.DISCOUNT_VALUE0, ElementType.Xpath);
										Thread.sleep(2000);
										flag = webDB.waitForElement(AutomationLocators.DISCOUNT_ZEROERROR,
												ElementType.Xpath);
										if (flag) {
											webDB.clickAnElement(AutomationLocators.DISCOUNT_TXTBOX, ElementType.Xpath);
											webDB.clearTextField(AutomationLocators.DISCOUNT_TXTBOX, ElementType.Xpath);
											Thread.sleep(2000);
											webDB.sendTextToAnElement(AutomationLocators.DISCOUNT_TXTBOX,
													AutomationLocators.DISCOUNT_VALUE99, ElementType.Xpath);
											flag = webDB.waitForElement(AutomationLocators.DISCOUNT_INCREASE,
													ElementType.Xpath);
											if (flag) {
												webDB.clickAnElement(AutomationLocators.DISCOUNT_INCREASE,
														ElementType.Xpath);
												Thread.sleep(2000);
												webDB.clickAnElement(AutomationLocators.DISCOUNT_INCREASE,
														ElementType.Xpath);
												WebElement element1 = webDB.driver
														.findElement(By.xpath(AutomationLocators.DISCOUNT_TXTBOX));
												String elementval1 = element1.getAttribute("value");
												flag = !elementval1
														.equalsIgnoreCase(AutomationLocators.DISCOUNT_VALUE101);
												if (flag) {
													webDB.clickAnElement(AutomationLocators.DISCOUNT_DECREASE,
															ElementType.Xpath);
													Thread.sleep(2000);
													WebElement element2 = webDB.driver
															.findElement(By.xpath(AutomationLocators.DISCOUNT_TXTBOX));
													String elementval2 = element2.getAttribute("value");
													flag = elementval2
															.equalsIgnoreCase(AutomationLocators.DISCOUNT_VALUE99);
													if (flag) {
														webDB.clickAnElement(AutomationLocators.SAVE_BTN,
																ElementType.Xpath);
														flag = webDB.waitForElement(
																AutomationLocators.CROSSSELL_SETTINGS,
																ElementType.Xpath);
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
		}
		return flag;
	}

	public static boolean verifyCrossSellFixedDiscount()
			throws InterruptedException, FileNotFoundException, IOException {
		flag = webDB.waitForElement(AutomationLocators.CROSSSELL_SETTINGS, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AutomationLocators.CROSSSELL_SETTINGS, ElementType.Xpath);
			Thread.sleep(2000);
			flag = webDB.waitForElement(AutomationLocators.SETTINGSPOPUP, ElementType.CssSelector);
			if (flag) {
				webDB.moveToElement(AutomationLocators.INTERVAL_NOTE, ElementType.Xpath);
				flag = webDB.driver.findElement(By.xpath(AutomationLocators.INCLUDE_DISCOUNT)).isSelected();
				if (!flag) {
					webDB.clickAnElement(AutomationLocators.INCLUDE_DISCOUNT, ElementType.Xpath);
					flag = true;
				}
				webDB.moveToElement(AutomationLocators.DISCOUNT_BOX, ElementType.Xpath);
				Thread.sleep(2000);
				flag = webDB.waitForElement(AutomationLocators.FIXEDDISCOUNT, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(AutomationLocators.FIXEDDISCOUNT, ElementType.Xpath);
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.DISCOUNT_TXTBOX, ElementType.Xpath);
					webDB.clearText(AutomationLocators.DISCOUNT_TXTBOX);
					Thread.sleep(2000);
					webDB.sendTextToAnElement(AutomationLocators.DISCOUNT_TXTBOX, AutomationLocators.COUPONCODE,
							ElementType.Xpath);
					webDB.clickAnElement(AutomationLocators.SAVE_BTN, ElementType.Xpath);
					Thread.sleep(3000);
					String text = webDB.getText(AutomationLocators.DISCOUNT_TXT_MSG, ElementType.Xpath);
					flag = text.contains(AutomationLocators.CROSSSELFIXEDDISCOUNT_TXTMSG);
					if (flag) {
						flag = webDB.waitForElement(AutomationLocators.CROSSSELL_SETTINGS, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(AutomationLocators.CROSSSELL_SETTINGS, ElementType.Xpath);
							Thread.sleep(2000);
							flag = webDB.waitForElement(AutomationLocators.SETTINGSPOPUP, ElementType.CssSelector);
							if (flag) {
								webDB.moveToElement(AutomationLocators.SHOPIFYADMINDISCOUNTS_HYPERLINK,
										ElementType.Xpath);
								Thread.sleep(2000);
								flag = webDB.driver.findElement(By.xpath(AutomationLocators.INCLUDE_DISCOUNT))
										.isSelected();
								if (!flag) {
									webDB.clickAnElement(AutomationLocators.INCLUDE_DISCOUNT, ElementType.Xpath);
									flag = true;
								}
								flag = webDB.waitForElement(AutomationLocators.DISCOUNT, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(AutomationLocators.DISCOUNT, ElementType.Xpath);
									Thread.sleep(2000);
									flag = webDB.waitForElement(AutomationLocators.SAVE_BTN, ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(AutomationLocators.SAVE_BTN, ElementType.Xpath);
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

	public static boolean verifyCrossSellInterval() throws InterruptedException, FileNotFoundException, IOException {
		webDB.moveToElement(AutomationLocators.CROSSSELL_SETTINGS, ElementType.Xpath);
		flag = webDB.waitForElement(AutomationLocators.CROSSSELL_SETTINGS, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AutomationLocators.CROSSSELL_SETTINGS, ElementType.Xpath);
			Thread.sleep(2000);
			flag = webDB.waitForElement(AutomationLocators.SETTINGSPOPUP, ElementType.CssSelector);
			if (flag) {
				webDB.moveToElement(AutomationLocators.INTERVAL_NOTE, ElementType.Xpath);
				flag = webDB.waitForElement(AutomationLocators.INTERVAL_NOTE, ElementType.Xpath);
				if (flag) {
					flag = webDB.waitForElement(AutomationLocators.INTERVAL_DROPDOWN, ElementType.Xpath);
					if (flag) {
						Thread.sleep(3000);
						WebElement element2 = webDB.driver.findElement(By.xpath(AutomationLocators.INTERVAL_DROPDOWN));
						String elementval2 = element2.getAttribute("value");
						flag = webDB.verifyDropdownValues(AutomationLocators.INTERVAL_DROPDOWN,
								AutomationLocators.DROPDOWNVALUES);
						if (flag) {
							webDB.selectDropDownOptions(AutomationLocators.INTERVAL_DROPDOWN,
									AutomationLocators.INTERVAL_VALUE, ElementType.Xpath);
							webDB.clickAnElement(AutomationLocators.SAVE_BTN, ElementType.Xpath);
							flag = webDB.waitForElement(AutomationLocators.CROSSSELL_SETTINGS, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(AutomationLocators.CROSSSELL_SETTINGS, ElementType.Xpath);
								Thread.sleep(2000);
								webDB.moveToElement(AutomationLocators.INTERVAL_NOTE, ElementType.Xpath);
								flag = webDB.waitForElement(AutomationLocators.INTERVAL_DROPDOWN, ElementType.Xpath);
								if (flag) {
									webDB.selectDropDownOptions(AutomationLocators.INTERVAL_DROPDOWN,
											AutomationLocators.INTERVAL_VALUE1, ElementType.Xpath);
									webDB.clickAnElement(AutomationLocators.SAVE_BTN, ElementType.Xpath);
									Thread.sleep(2000);
									flag = webDB.waitForElement(AutomationLocators.CROSSSELL_SETTINGS,
											ElementType.Xpath);
								}
							}
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyCustomerWinbackDiscount()
			throws InterruptedException, FileNotFoundException, IOException {
		flag = webDB.waitForElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
			Thread.sleep(2000);
			webDB.driver.navigate().refresh();
			webDB.moveToElement(AutomationLocators.AUTOMATEDPROMOTIONAL_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(AutomationLocators.AUTOMATEDPROMOTIONAL_TAB, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(AutomationLocators.AUTOMATEDPROMOTIONAL_TAB, ElementType.Xpath);
				webDB.moveToElement(AutomationLocators.CUSTOMERWINBACK_TOGGLE, ElementType.Xpath);
				flag = webDB.waitForElement(AutomationLocators.CUSTOMERWINBACK_SETTINGS, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(AutomationLocators.CUSTOMERWINBACK_SETTINGS, ElementType.Xpath);
					Thread.sleep(2000);
					flag = webDB.waitForElement(AutomationLocators.SETTINGSPOPUP, ElementType.CssSelector);
					if (flag) {
						webDB.moveToElement(AutomationLocators.INTERVAL_NOTE, ElementType.Xpath);
						flag = webDB.waitForElement(AutomationLocators.DISCOUNT_TXTBOX, ElementType.Xpath);
						if (flag) {
							Thread.sleep(3000);
							webDB.javaScriptClickAnElement(AutomationLocators.DISCOUNT_TXTBOX, ElementType.Xpath);
							WebElement element = webDB.driver.findElement(By.xpath(AutomationLocators.DISCOUNT_TXTBOX));
							String elementval = element.getAttribute("value");
							webDB.clearText(AutomationLocators.DISCOUNT_TXTBOX);
							Thread.sleep(3000);
							flag = webDB.waitForElement(AutomationLocators.DISCOUNT_EMPTYERROR, ElementType.Xpath);
							if (flag) {
								webDB.sendTextToAnElement(AutomationLocators.DISCOUNT_TXTBOX,
										AutomationLocators.DISCOUNT_VALUE0, ElementType.Xpath);
								Thread.sleep(2000);
								flag = webDB.waitForElement(AutomationLocators.DISCOUNT_ZEROERROR, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(AutomationLocators.DISCOUNT_TXTBOX, ElementType.Xpath);
									webDB.clearTextField(AutomationLocators.DISCOUNT_TXTBOX, ElementType.Xpath);
									Thread.sleep(2000);
									webDB.sendTextToAnElement(AutomationLocators.DISCOUNT_TXTBOX,
											AutomationLocators.DISCOUNT_VALUE99, ElementType.Xpath);
									flag = webDB.waitForElement(AutomationLocators.DISCOUNT_INCREASE,
											ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(AutomationLocators.DISCOUNT_INCREASE, ElementType.Xpath);
										Thread.sleep(2000);
										webDB.clickAnElement(AutomationLocators.DISCOUNT_INCREASE, ElementType.Xpath);
										WebElement element1 = webDB.driver
												.findElement(By.xpath(AutomationLocators.DISCOUNT_TXTBOX));
										String elementval1 = element1.getAttribute("value");
										flag = !elementval1.equalsIgnoreCase(AutomationLocators.DISCOUNT_VALUE101);
										if (flag) {
											webDB.clickAnElement(AutomationLocators.DISCOUNT_DECREASE,
													ElementType.Xpath);
											Thread.sleep(2000);
											WebElement element2 = webDB.driver
													.findElement(By.xpath(AutomationLocators.DISCOUNT_TXTBOX));
											String elementval2 = element2.getAttribute("value");
											flag = elementval2.equalsIgnoreCase(AutomationLocators.DISCOUNT_VALUE99);
											if (flag) {
												webDB.clickAnElement(AutomationLocators.SAVE_BTN, ElementType.Xpath);
												flag = webDB.waitForElement(AutomationLocators.CUSTOMERWINBACK_SETTINGS,
														ElementType.Xpath);
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

	public static boolean verifyCustomerWinbackFixedDiscount()
			throws InterruptedException, FileNotFoundException, IOException {
		flag = webDB.waitForElement(AutomationLocators.CUSTOMERWINBACK_SETTINGS, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AutomationLocators.CUSTOMERWINBACK_SETTINGS, ElementType.Xpath);
			Thread.sleep(2000);
			flag = webDB.waitForElement(AutomationLocators.SETTINGSPOPUP, ElementType.CssSelector);
			if (flag) {
				webDB.moveToElement(AutomationLocators.DISCOUNT_BOX, ElementType.Xpath);
				Thread.sleep(2000);
				flag = webDB.waitForElement(AutomationLocators.FIXEDDISCOUNT, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(AutomationLocators.FIXEDDISCOUNT, ElementType.Xpath);
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.SAVE_BTN, ElementType.Xpath);
					Thread.sleep(3000);
					String text = webDB.getText(AutomationLocators.WINBACKDISCOUNTTXTMSG, ElementType.Xpath);
					flag = text.contains(AutomationLocators.WINBACKFIXEDDISCOUNT_TXTMSG);
					if (flag) {
						flag = webDB.waitForElement(AutomationLocators.CUSTOMERWINBACK_SETTINGS, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(AutomationLocators.CUSTOMERWINBACK_SETTINGS, ElementType.Xpath);
							Thread.sleep(2000);
							flag = webDB.waitForElement(AutomationLocators.SETTINGSPOPUP, ElementType.CssSelector);
							if (flag) {
								webDB.moveToElement(AutomationLocators.SHOPIFYADMINDISCOUNTS_HYPERLINK,
										ElementType.Xpath);
								Thread.sleep(2000);
								flag = webDB.waitForElement(AutomationLocators.DISCOUNT, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(AutomationLocators.DISCOUNT, ElementType.Xpath);
									Thread.sleep(2000);
									flag = webDB.waitForElement(AutomationLocators.SAVE_BTN, ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(AutomationLocators.SAVE_BTN, ElementType.Xpath);
										flag = webDB.waitForElement(AutomationLocators.CUSTOMERWINBACK_SETTINGS,
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

	public static boolean verifyCustomerWinbackInterval()
			throws InterruptedException, FileNotFoundException, IOException {
		flag = webDB.waitForElement(AutomationLocators.CUSTOMERWINBACK_SETTINGS, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AutomationLocators.CUSTOMERWINBACK_SETTINGS, ElementType.Xpath);
			Thread.sleep(2000);
			flag = webDB.waitForElement(AutomationLocators.SETTINGSPOPUP, ElementType.CssSelector);
			if (flag) {
				webDB.moveToElement(AutomationLocators.INTERVAL_NOTE, ElementType.Xpath);
				flag = webDB.waitForElement(AutomationLocators.INTERVAL_NOTE, ElementType.Xpath);
				if (flag) {
					flag = webDB.waitForElement(AutomationLocators.INTERVAL_DROPDOWN, ElementType.Xpath);
					if (flag) {
						Thread.sleep(3000);
						WebElement element2 = webDB.driver.findElement(By.xpath(AutomationLocators.INTERVAL_DROPDOWN));
						String elementval2 = element2.getAttribute("value");
						flag = webDB.verifyDropdownValues(AutomationLocators.INTERVAL_DROPDOWN,
								AutomationLocators.DROPDOWNVALUES);
						if (flag) {
							webDB.selectDropDownOptions(AutomationLocators.INTERVAL_DROPDOWN,
									AutomationLocators.INTERVAL_VALUE, ElementType.Xpath);
							webDB.clickAnElement(AutomationLocators.SAVE_BTN, ElementType.Xpath);
							flag = webDB.waitForElement(AutomationLocators.CUSTOMERWINBACK_SETTINGS, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(AutomationLocators.CUSTOMERWINBACK_SETTINGS, ElementType.Xpath);
								Thread.sleep(2000);
								webDB.moveToElement(AutomationLocators.INTERVAL_NOTE, ElementType.Xpath);
								flag = webDB.waitForElement(AutomationLocators.INTERVAL_DROPDOWN, ElementType.Xpath);
								if (flag) {
									webDB.selectDropDownOptions(AutomationLocators.INTERVAL_DROPDOWN,
											AutomationLocators.INTERVAL_VALUE1, ElementType.Xpath);
									webDB.clickAnElement(AutomationLocators.SAVE_BTN, ElementType.Xpath);
									Thread.sleep(2000);
									flag = webDB.waitForElement(AutomationLocators.CUSTOMERWINBACK_SETTINGS,
											ElementType.Xpath);
								}
							}
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyFeedBackInterval() throws InterruptedException, FileNotFoundException, IOException {
		webDB.moveToElement(AutomationLocators.FEEDBACK_PRICINGCHART_HYPERLINK, ElementType.Xpath);
		flag = webDB.waitForElement(AutomationLocators.FEEDBACK_SETTINGS, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AutomationLocators.FEEDBACK_SETTINGS, ElementType.Xpath);
			Thread.sleep(2000);
			flag = webDB.waitForElement(AutomationLocators.SETTINGSPOPUP, ElementType.CssSelector);
			if (flag) {
				webDB.moveToElement(AutomationLocators.INTERVAL_NOTE, ElementType.Xpath);
				flag = webDB.waitForElement(AutomationLocators.INTERVAL_NOTE, ElementType.Xpath);
				if (flag) {
					flag = webDB.waitForElement(AutomationLocators.INTERVAL_DROPDOWN, ElementType.Xpath);
					if (flag) {
						Thread.sleep(3000);
						WebElement element2 = webDB.driver.findElement(By.xpath(AutomationLocators.INTERVAL_DROPDOWN));
						String elementval2 = element2.getAttribute("value");
						flag = webDB.verifyDropdownValues(AutomationLocators.INTERVAL_DROPDOWN,
								AutomationLocators.DROPDOWNVALUES);
						if (flag) {
							webDB.selectDropDownOptions(AutomationLocators.INTERVAL_DROPDOWN,
									AutomationLocators.INTERVAL_VALUE, ElementType.Xpath);
							webDB.clickAnElement(AutomationLocators.SAVE_BTN, ElementType.Xpath);
							flag = webDB.waitForElement(AutomationLocators.FEEDBACK_SETTINGS, ElementType.Xpath);
							if (flag) {
								Thread.sleep(2000);
								webDB.clickAnElement(AutomationLocators.FEEDBACK_SETTINGS, ElementType.Xpath);
								Thread.sleep(2000);
								webDB.moveToElement(AutomationLocators.INTERVAL_NOTE, ElementType.Xpath);
								flag = webDB.waitForElement(AutomationLocators.INTERVAL_DROPDOWN, ElementType.Xpath);
								if (flag) {
									webDB.selectDropDownOptions(AutomationLocators.INTERVAL_DROPDOWN,
											AutomationLocators.INTERVAL_VALUE1, ElementType.Xpath);
									webDB.clickAnElement(AutomationLocators.SAVE_BTN, ElementType.Xpath);
									Thread.sleep(2000);
									flag = webDB.waitForElement(AutomationLocators.FEEDBACK_SETTINGS,
											ElementType.Xpath);
								}
							}
						}
					}
				}
			}
		}
		return flag;
	}

	/**
	 * verify plans Disabled
	 *
	 * @return
	 * @throws InterruptedException
	 */
	public static boolean verifyTemplateBasedOnPlan() throws InterruptedException {
		Thread.sleep(6000);
		flag = webDB.javaScriptClickAnElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			String text1 = webDB.driver.findElement(By.xpath(AutomationLocators.VERIFYDISABLEMSG)).getText();
			flag = text1.contains(AutomationLocators.VERIFYDISABLEMSGVALUE);
			if (flag) {
				webDB.isElementDisplayed(AutomationLocators.UPGRADEPLAN, ElementType.CssSelector);
				flag = webDB.waitForElement(AutomationLocators.AUTOMATEDPROMOTIONAL_TAB, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(AutomationLocators.AUTOMATEDPROMOTIONAL_TAB, ElementType.Xpath);
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.UPGRADEPLAN, ElementType.CssSelector);
					flag = webDB.waitForElement(PlansPageLocators.ULTIMATE_PLAN_BUYBUTTON, ElementType.Xpath);
					if (flag) {
						Thread.sleep(2500);
						webDB.clickAnElement(PlansPageLocators.ULTIMATE_PLAN_BUYBUTTON, ElementType.Xpath);
						flag = webDB.waitForElement(PlansPageLocators.CONTINUEBUTTON, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(PlansPageLocators.CONTINUEBUTTON, ElementType.Xpath);
						} else {
							flag = true;
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyTransactionShopifyHyperLink()
			throws InterruptedException, FileNotFoundException, IOException {
		flag = webDB.waitForElement(AutomationLocators.PAYMENTSUCCESSFUL_SETTINGS, ElementType.Xpath);
		if (flag) {
			flag = webDB.waitForElement(AutomationLocators.PAYMENTSUCCESSFUL_SETTINGS, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(AutomationLocators.PAYMENTSUCCESSFUL_SETTINGS, ElementType.Xpath);
				Thread.sleep(2000);
				flag = webDB.waitForElement(AutomationLocators.SETTINGSPOPUP, ElementType.CssSelector);
				if (flag) {
					flag = webDB.waitForElement(AutomationLocators.FIXEDDISCOUNT, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(AutomationLocators.FIXEDDISCOUNT, ElementType.Xpath);
						webDB.moveToElement(AutomationLocators.SHOPIFYADMINDISCOUNTS_HYPERLINK, ElementType.Xpath);
						Thread.sleep(2000);
						flag = webDB.waitForElement(AutomationLocators.SHOPIFYADMINDISCOUNTS_HYPERLINK,
								ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(AutomationLocators.SHOPIFYADMINDISCOUNTS_HYPERLINK, ElementType.Xpath);
							Thread.sleep(2000);
							flag = webDB.waitForElement(AutomationLocators.PAYMENTSUCCESSFUL_SETTINGS,
									ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(AutomationLocators.PAYMENTSUCCESSFUL_SETTINGS, ElementType.Xpath);
								flag = webDB.waitForElement(AutomationLocators.SETTINGSPOPUP, ElementType.CssSelector);
								if (flag) {
									webDB.moveToElement(AutomationLocators.PAYMENTSUCCESSFULLUSERSETTINGSTEXT,
											ElementType.Xpath);
									Thread.sleep(2000);
									flag = webDB.driver.findElement(By.xpath(AutomationLocators.FIXED_DISCOUNT__RADIO))
											.isSelected();
									System.out.println(flag);
									if (flag) {
										System.out.println("Fixed Discount enabled");
									} else {
										System.out.println("Fixed Discount disabled");
										webDB.clickAnElement(AutomationLocators.FIXED_DISCOUNT__RADIO,
												ElementType.Xpath);
									}
//									webDB.clickAnElement(TemplatesLocators.SHOPIFYADMINDISCOUNTS_HYPERLINK,
//											ElementType.Xpath);
//									Thread.sleep(3500);
//									webDB.switchToChildWindowAndTitle(LoginPageLocators.USERNAME,
//											LoginPageLocators.NEXTBTN, LoginPageLocators.PASSWORD,
//											LoginPageLocators.USERNAMEVALUE, LoginPageLocators.PASSWORDVALUE,
//											TemplatesLocators.URL);
									webDB.switchToChildWindowAndVerifyURLText(
											AutomationLocators.SHOPIFYADMINDISCOUNTS_HYPERLINK,
											PlansPageLocators.SHOPIFY);
									System.out.println("Verified Shopify");
									flag = webDB.waitForElement(AutomationLocators.CANCEL_BTN, ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(AutomationLocators.CANCEL_BTN, ElementType.Xpath);
										Thread.sleep(2000);
										flag = webDB.waitForElement(AutomationLocators.PAYMENTSUCCESSFUL_SETTINGS,
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

	/**
	 * Verify Promtional Hyperlink
	 *
	 * @return
	 * @throws InterruptedException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static boolean verifyPromotionalShopifyHyperLink()
			throws InterruptedException, FileNotFoundException, IOException {
		webDB.moveToElement(AutomationLocators.CUSTOMERWINBACK_SETTINGS, ElementType.Xpath);
		Thread.sleep(2000);
		flag = webDB.waitForElement(AutomationLocators.CUSTOMERWINBACK_SETTINGS, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AutomationLocators.CUSTOMERWINBACK_SETTINGS, ElementType.Xpath);
			flag = webDB.waitForElement(AutomationLocators.SETTINGSPOPUP, ElementType.CssSelector);
			if (flag) {
				Thread.sleep(2000);
				webDB.moveToElement(AutomationLocators.DISCOUNT_BOX, ElementType.Xpath);
				Thread.sleep(2000);
				flag = webDB.driver.findElement(By.xpath(AutomationLocators.CUSTOMERWINBACKFIXEDDISCOUNT_RADIO))
						.isSelected();
				System.out.println(flag);
				if (flag) {
					System.out.println("Fixed Discount enabled");
				} else {
					System.out.println("Fixed Discount disabled");
					webDB.clickAnElement(AutomationLocators.FIXEDDISCOUNT, ElementType.Xpath);
				}
//				webDB.moveToElement(TemplatesLocators.DISCOUNT_BOX, ElementType.Xpath);
//				Thread.sleep(2000);
//				flag = webDB.waitForElement(TemplatesLocators.FIXEDDISCOUNT, ElementType.Xpath);
//				if (flag) {
//					Thread.sleep(2000);
//					webDB.clickAnElement(TemplatesLocators.FIXEDDISCOUNT, ElementType.Xpath);
//					webDB.moveToElement(TemplatesLocators.SHOPIFYADMINDISCOUNTS_HYPERLINK, ElementType.Xpath);
//					Thread.sleep(2000);
//					webDB.clickAnElement(TemplatesLocators.SHOPIFYADMINDISCOUNTS_HYPERLINK, ElementType.Xpath);
//					Thread.sleep(3500);
//					webDB.switchToChildWindowAndTitle(LoginPageLocators.USERNAME, LoginPageLocators.NEXTBTN,
//							LoginPageLocators.PASSWORD, LoginPageLocators.USERNAMEVALUE,
//							LoginPageLocators.PASSWORDVALUE, TemplatesLocators.URL);
				webDB.switchToChildWindowAndVerifyURLText(AutomationLocators.SHOPIFYADMINDISCOUNTS_HYPERLINK,
						PlansPageLocators.SHOPIFY);
				System.out.println("Verified Shopify");
				flag = webDB.waitForElement(AutomationLocators.CANCEL_BTN, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(AutomationLocators.CANCEL_BTN, ElementType.Xpath);
					Thread.sleep(2000);
					webDB.moveToElement(AutomationLocators.CUSTOMERWINBACK_SETTINGS, ElementType.Xpath);
					flag = webDB.waitForElement(AutomationLocators.CUSTOMERWINBACK_SETTINGS, ElementType.Xpath);
				}
			}
		}
		return flag;
	}

	public static boolean verifyCustomTemplateValidation()
			throws InterruptedException, FileNotFoundException, IOException {
		flag = webDB.waitForElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			webDB.javaScriptClickAnElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
			Thread.sleep(2000);
			flag = webDB.waitForElement(AutomationLocators.CREATECUSTOMTEMPLATESBTN, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				webDB.javaScriptClickAnElement(AutomationLocators.CREATECUSTOMTEMPLATESBTN, ElementType.Xpath);
				Thread.sleep(2000);
				webDB.sendTextToAnElement(AutomationLocators.TEMPLATESNAMEINPUT, AutomationLocators.TEMPLATENAMEVALUE,
						ElementType.CssSelector);
				Thread.sleep(1500);
				webDB.clickAnElement(AutomationLocators.CATEGORYDROPDOWN, ElementType.Xpath);
				// modified - changed dropdown option
				webDB.selectDropDownOptions(AutomationLocators.CATEGORYDROPDOWN,
						AutomationLocators.CATEGORYDROPDOWNVALUE, ElementType.Xpath);
				Thread.sleep(1500);
				webDB.clickAnElement(AutomationLocators.TYPEDROPDOWN, ElementType.Xpath);
				webDB.selectDropDownOptions(AutomationLocators.TYPEDROPDOWN, AutomationLocators.TYPEDROPDOWNVALUE,
						ElementType.Xpath);
				Thread.sleep(1500);
				webDB.clickAnElement(AutomationLocators.LANGUAGEDROPDOWN, ElementType.Xpath);
				webDB.selectDropDownOptions(AutomationLocators.LANGUAGEDROPDOWN,
						AutomationLocators.LANGUAGEDROPDOWNVALUE, ElementType.Xpath);
				webDB.moveToElement(AutomationLocators.CALLTOACTIONSRADIOBUTTON, ElementType.CssSelector);
				Thread.sleep(2500);
				flag = webDB.waitForElement(AutomationLocators.HEADERINPUT, ElementType.Xpath);
				if (flag) {
					Thread.sleep(2000);
					// modified - added one extra character for this invalid value
					webDB.sendTextToAnElement(AutomationLocators.HEADERINPUT, AutomationLocators.INVALIDVALUE,
							ElementType.Xpath);
					flag = webDB.isElementDisplayed(AutomationLocators.HEADERINPUTERROR, ElementType.Xpath);
					if (flag) {
						webDB.clearText(AutomationLocators.HEADERINPUT);
						webDB.sendTextToAnElement(AutomationLocators.HEADERINPUT, AutomationLocators.HEADERVALUE,
								ElementType.Xpath);
						Thread.sleep(2000);
						webDB.sendTextToAnElement(AutomationLocators.FOOTERINPUT, AutomationLocators.INVALIDVALUE,
								ElementType.Xpath);
						flag = webDB.isElementDisplayed(AutomationLocators.FOOTERINPUTERROR, ElementType.Xpath);
						if (flag) {
							webDB.clearText(AutomationLocators.FOOTERINPUT);
							webDB.sendTextToAnElement(AutomationLocators.FOOTERINPUT, AutomationLocators.FOOTERVALUE,
									ElementType.Xpath);
							Thread.sleep(2000);
							webDB.sendTextToAnElement(AutomationLocators.BODYINPUT,
									AutomationLocators.VERIFYDISABLEMSGVALUE, ElementType.Xpath);
							webDB.sendTextToAnElement(AutomationLocators.BODYINPUT,
									AutomationLocators.VERIFYDISABLEMSGVALUE, ElementType.Xpath);
							webDB.sendTextToAnElement(AutomationLocators.BODYINPUT,
									AutomationLocators.VERIFYDISABLEMSGVALUE, ElementType.Xpath);
							webDB.sendTextToAnElement(AutomationLocators.BODYINPUT,
									AutomationLocators.VERIFYDISABLEMSGVALUE, ElementType.Xpath);
							webDB.sendTextToAnElement(AutomationLocators.BODYINPUT,
									AutomationLocators.VERIFYDISABLEMSGVALUE, ElementType.Xpath);
							webDB.sendTextToAnElement(AutomationLocators.BODYINPUT,
									AutomationLocators.VERIFYDISABLEMSGVALUE, ElementType.Xpath);
							webDB.sendTextToAnElement(AutomationLocators.BODYINPUT,
									AutomationLocators.VERIFYDISABLEMSGVALUE, ElementType.Xpath);
							Thread.sleep(2000);
							webDB.moveToElement(AutomationLocators.BODYINPUTERROR, ElementType.Xpath);
							flag = webDB.isElementDisplayed(AutomationLocators.BODYINPUTERROR, ElementType.Xpath);
							if (flag) {
								webDB.moveToElement(AutomationLocators.BODYINPUT, ElementType.Xpath);
								webDB.clearText(AutomationLocators.BODYINPUT);
								webDB.sendTextToAnElement(AutomationLocators.BODYINPUT, AutomationLocators.BODYVALUE,
										ElementType.Xpath);
								flag = webDB.verifyElementIsNotDisplayed(AutomationLocators.CALLTOACTIONSDROPDOWN,
										ElementType.Xpath);
								if (flag) {
//									webDB.sendTextToAnElement(TemplatesLocators.SAMPLEMESSAGEINPUT,
//											TemplatesLocators.HEADERVALUE, ElementType.Xpath);
									webDB.javaScriptClickAnElement(AutomationLocators.CREATEBTN, ElementType.Xpath);
									flag = webDB.waitForElement(AutomationLocators.ERRORPOP, ElementType.Xpath);
									if (flag) {
										Thread.sleep(500);
										String text = webDB.getText(AutomationLocators.ERRORPOP, ElementType.Xpath);
//										flag = text.equals(TemplatesLocators.SAMPLEMESSAGEERROR);
//										if (flag) {
//											webDB.clearText(TemplatesLocators.SAMPLEMESSAGEINPUT);
										webDB.clickAnElement(AutomationLocators.TOASTCLOSE_ICON,
												ElementType.CssSelector);
										Thread.sleep(2000);
										webDB.javaScriptClickAnElement(AutomationLocators.CREATEBTN, ElementType.Xpath);
										flag = webDB.waitForElement(AutomationLocators.ERRORPOP, ElementType.Xpath);
										if (flag) {
//												webDB.sendTextToAnElement(TemplatesLocators.SAMPLEMESSAGEINPUT,
//														TemplatesLocators.SAMPLEMESSAGEVALUE, ElementType.Xpath);
											webDB.clickAnElement(AutomationLocators.TOASTCLOSE_ICON,
													ElementType.CssSelector);
											Thread.sleep(1000);
											webDB.clickAnElement(AutomationLocators.DISCARDBTN, ElementType.Xpath);
											Thread.sleep(2500);
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

	public static boolean verifyCustomTemplateValidationwithCallAction() throws InterruptedException {
		random = AutomationLocators.TEMPLATENAMEVALUE + RandomStringUtils.randomNumeric(3);
		webDB.driver.navigate().refresh();
		webDB.moveToElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
		flag = webDB.waitForElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			webDB.javaScriptClickAnElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
			Thread.sleep(2000);
			flag = webDB.waitForElement(AutomationLocators.CREATECUSTOMTEMPLATESBTN, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(AutomationLocators.CREATECUSTOMTEMPLATESBTN, ElementType.Xpath);
				Thread.sleep(2000);
				verifyCustomTemplateNameUpperCase();
				flag = webDB.clickAnElement(AutomationLocators.CATEGORYDROPDOWN, ElementType.Xpath);
				if (flag) {
					webDB.selectDropDownOptions(AutomationLocators.CATEGORYDROPDOWN,
							AutomationLocators.CATEGORYDROPDOWNVALUE, ElementType.Xpath);
					Thread.sleep(1500);
					flag = webDB.clickAnElement(AutomationLocators.TYPEDROPDOWN, ElementType.Xpath);
					if (flag) {
						webDB.selectDropDownOptions(AutomationLocators.TYPEDROPDOWN,
								AutomationLocators.TYPEDROPDOWNVALUE, ElementType.Xpath);
						Thread.sleep(1500);
						flag = webDB.clickAnElement(AutomationLocators.LANGUAGEDROPDOWN, ElementType.Xpath);
						if (flag) {
							webDB.selectDropDownOptions(AutomationLocators.LANGUAGEDROPDOWN,
									AutomationLocators.LANGUAGEDROPDOWNVALUE, ElementType.Xpath);
							webDB.moveToElement(AutomationLocators.CALLTOACTIONSRADIOBUTTON, ElementType.CssSelector);
							Thread.sleep(2500);
							flag = webDB.waitForElement(AutomationLocators.HEADERINPUT, ElementType.Xpath);
							if (flag) {
								webDB.sendTextToAnElement(AutomationLocators.HEADERINPUT,
										AutomationLocators.HEADERVALUE, ElementType.Xpath);
								Thread.sleep(2000);
								webDB.sendTextToAnElement(AutomationLocators.FOOTERINPUT,
										AutomationLocators.FOOTERVALUE, ElementType.Xpath);
								Thread.sleep(2000);
								webDB.moveToElement(AutomationLocators.BODYINPUT, ElementType.Xpath);
								flag = webDB.waitForElement(AutomationLocators.BODYINPUT, ElementType.Xpath);
								if (flag) {
									webDB.sendTextToAnElement(AutomationLocators.BODYINPUT,
											AutomationLocators.BODYVALUE, ElementType.Xpath);
//										webDB.sendTextToAnElement(TemplatesLocators.SAMPLEMESSAGEINPUT,
//												TemplatesLocators.SAMPLEMESSAGEVALUE, ElementType.Xpath);
									Thread.sleep(2500);
									flag = verifyCustomTemplateCallActionsValidations();
									if (flag) {
										flag = verifyCustomTemplateNameDuplicate();
										if (flag) {
											flag = verifyViewCustomTemplateName();
											if (flag) {
												flag = verifyDeleteCustomTemplateName();
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

	public static boolean verifyCustomTemplateNameUpperCase() throws InterruptedException {
		webDB.sendTextToAnElement(AutomationLocators.TEMPLATESNAMEINPUT, AutomationLocators.PRICINGCHART_URL,
				ElementType.CssSelector);
		Thread.sleep(1500);
		flag = webDB.isElementDisplayed(AutomationLocators.TEMPLATESNAMEINPUTERROR, ElementType.CssSelector);
		if (flag) {
			webDB.clearTextCSS(AutomationLocators.TEMPLATESNAMEINPUT);
			Thread.sleep(1000);
			webDB.sendTextToAnElement(AutomationLocators.TEMPLATESNAMEINPUT,
					AutomationLocators.INVALIDTEMPLATENAMEVALUE, ElementType.CssSelector);
			flag = webDB.waitForElement(AutomationLocators.CATEGORYDROPDOWN, ElementType.Xpath);
		}
		return flag;
	}

	public static boolean verifyCustomTemplateCallActionsValidations() throws InterruptedException {
		// modified - added wait
		Thread.sleep(2000);
		flag = webDB.javaScriptClickAnElement(AutomationLocators.CALLTOACTIONSRADIOBUTTON, ElementType.CssSelector);

		if (flag) {
			flag = webDB.clickAnElement(AutomationLocators.CALLTOACTIONSDROPDOWN, ElementType.Xpath);
			if (flag) {
				webDB.selectDropDownOptions(AutomationLocators.CALLTOACTIONSDROPDOWN,
						AutomationLocators.URLDROPDOWNVALUE, ElementType.Xpath);
				webDB.javaScriptClickAnElement(AutomationLocators.CREATEBTN, ElementType.Xpath);
				flag = webDB.isElementDisplayed(AutomationLocators.ERRORPOP, ElementType.Xpath);
				if (flag) {
					Thread.sleep(500);
					String text = webDB.getText(AutomationLocators.ERRORPOP, ElementType.Xpath);
					flag = text.equals(AutomationLocators.BUTTONNAMEERROR);
					if (flag) {
						webDB.sendTextToAnElement(AutomationLocators.CALLTOACTIONSBUTTONNAME,
								AutomationLocators.CALLTOACTIONSBUTTONNAMEVALUE, ElementType.CssSelector);
						Thread.sleep(500);
						webDB.clickAnElement(AutomationLocators.CALLTOACTIONSDROPDOWNTYPE, ElementType.Xpath);
						webDB.selectDropDownOptions(AutomationLocators.CALLTOACTIONSDROPDOWNTYPE,
								AutomationLocators.CALLTOACTIONSTYPEDROPDOWNVALUE, ElementType.Xpath);
						Thread.sleep(2000);
						webDB.javaScriptClickAnElement(AutomationLocators.CREATEBTN, ElementType.Xpath);
						flag = webDB.isElementDisplayed(AutomationLocators.ERRORPOP, ElementType.Xpath);
						if (flag) {
							Thread.sleep(500);
							String text1 = webDB.getText(AutomationLocators.ERRORPOP, ElementType.Xpath);
							flag = text1.equals(AutomationLocators.BUTTONVALUEERROR);
							if (flag) {
								webDB.sendTextToAnElement(AutomationLocators.CALLTOACTIONSBUTTONVALUEINPUT,
										AutomationLocators.CALLTOACTIONSBUTTONVALUE, ElementType.CssSelector);
								Thread.sleep(3500);
							}
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyCustomTemplateNameDuplicate() throws InterruptedException {
		Thread.sleep(2500);
		webDB.moveToElement(AutomationLocators.CREATEBTN, ElementType.Xpath);
		webDB.javaScriptClickAnElement(AutomationLocators.CREATEBTN, ElementType.Xpath);
		flag = webDB.isElementDisplayed(AutomationLocators.ERRORPOP, ElementType.Xpath);
		if (flag) {
			Thread.sleep(500);
			String text2 = webDB.getText(AutomationLocators.ERRORPOP, ElementType.Xpath);
			flag = text2.equals(AutomationLocators.TEMPLATENAMEERROR);
			if (flag) {
				webDB.moveToElement(AutomationLocators.TEMPLATESNAMEINPUT, ElementType.CssSelector);
				webDB.clearTextCSS(AutomationLocators.TEMPLATESNAMEINPUT);
				Thread.sleep(1000);
				webDB.sendTextToAnElement(AutomationLocators.TEMPLATESNAMEINPUT, random, ElementType.CssSelector);
				webDB.moveToElement(AutomationLocators.CREATEBTN, ElementType.Xpath);
				flag = webDB.waitForElement(AutomationLocators.CREATEBTN, ElementType.Xpath);
			}
		}
		return flag;

	}

	public static boolean verifyViewCustomTemplateName() throws InterruptedException {

		flag = webDB.waitForElement(AutomationLocators.CREATEBTN, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(AutomationLocators.CREATEBTN, ElementType.Xpath);
			Thread.sleep(5000);
			webDB.driver.navigate().refresh();
			if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
					|| (System.getProperty("os").equals("Android"))) {
				flag = webDB.waitForElement(CommonLocators.SIDENAV, ElementType.CssSelector);
				if (flag) {
					Thread.sleep(1500);
					webDB.clickAnElement(CommonLocators.SIDENAV, ElementType.CssSelector);
				}
			}
			flag = webDB.waitForElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
				if ((System.getProperty("platformName").equals("browserstack"))
						&& (System.getProperty("os").equals("Ios")) || (System.getProperty("os").equals("Android"))) {
					webDB.clickAnElement(CommonLocators.CLOSEICON, ElementType.Xpath);
				}
				Thread.sleep(2000);
				flag = webDB.waitForElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
				if (flag) {
					Thread.sleep(2000);
					webDB.javaScriptClickAnElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
					Thread.sleep(4500);
					// modified - commented below and added new xpath
//					element = webDB.driver.findElement(
//							By.xpath("//h2[contains(text(),'" + CampaignsPage.random + "')]/parent::div[1]"));
					element = webDB.driver.findElement(By.xpath(
							"(//div[@class='Polaris-TextContainer Polaris-TextContainer--spacingTight template-info'])[1]//h2"));
					Thread.sleep(3000);
					flag = element.isDisplayed();
					if (flag) {
						System.out.println("Created Template is visible");
					} else {
						System.out.println("Created Template is not visible");
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyDeleteCustomTemplateName() throws InterruptedException {
		// modified - commented below.
//		webDB.javaScriptClickAnElement(AutomationLocators.CREATECUSTOMTEMPLATESBTN, ElementType.Xpath);
//		Thread.sleep(500);
//		webDB.javaScriptClickAnElement(AutomationLocators.VIEWTEMPLATESBTN, ElementType.Xpath);
		// modified - commented below and added new xpath
//		element = webDB.driver
//				.findElement(By.xpath("//h2[contains(text(),'" + CampaignsPage.random + "')]/following::button[1]"));

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

	public static boolean verifyDeleteCustomTemplateName1() throws InterruptedException {
//		element = webDB.driver.findElement(By.xpath("(//div[@class='run-campaign-button'])[1]//button"));
		element = webDB.driver.findElement(By.xpath("(//div[@class='run-campaign-button'])[1]//button[2]"));

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

	public static boolean verifyGuideCustomTemplates() throws InterruptedException {
		webDB.driver.navigate().refresh();
		flag = webDB.waitForElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
			Thread.sleep(2000);
			webDB.moveToElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				webDB.javaScriptClickAnElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
				flag = webDB.waitForElement(AutomationLocators.GUIDE, ElementType.CssSelector);
				if (flag) {
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.GUIDE, ElementType.CssSelector);
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.STARTGUIDEBTN, ElementType.Xpath);
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.NEXTSTEPBTN, ElementType.Xpath);
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.NEXTSTEPBTN, ElementType.Xpath);
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.NEXTSTEPBTN, ElementType.Xpath);
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.NEXTSTEPBTN, ElementType.Xpath);
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.NEXTSTEPBTN, ElementType.Xpath);
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.NEXTSTEPBTN, ElementType.Xpath);
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.GUIDECOMPLETEDBTN, ElementType.Xpath);
					flag = webDB.isElementDisplayed(AutomationLocators.GUIDE, ElementType.CssSelector);
				}
			}
		}
		return flag;
	}

	public static boolean verifyCreateCustomTemplateswithoutFooterHeader() throws InterruptedException {
		random = AutomationLocators.TEMPLATENAMEVALUE + RandomStringUtils.randomNumeric(3);
		webDB.driver.navigate().refresh();
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			flag = webDB.waitForElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			if (flag) {
				Thread.sleep(1500);
				webDB.clickAnElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			}
		}
		flag = webDB.waitForElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
			if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
					|| (System.getProperty("os").equals("Android"))) {
				webDB.clickAnElement(CommonLocators.CLOSEICON, ElementType.Xpath);
			}
			Thread.sleep(2000);
			webDB.moveToElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				webDB.javaScriptClickAnElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
				Thread.sleep(2000);
				flag = webDB.waitForElement(AutomationLocators.CREATECUSTOMTEMPLATESBTN, ElementType.Xpath);
				if (flag) {
					webDB.javaScriptClickAnElement(AutomationLocators.CREATECUSTOMTEMPLATESBTN, ElementType.Xpath);
					webDB.sendTextToAnElement(AutomationLocators.TEMPLATESNAMEINPUT, random, ElementType.CssSelector);
					flag = webDB.clickAnElement(AutomationLocators.CATEGORYDROPDOWN, ElementType.Xpath);
					if (flag) {
						webDB.selectDropDownOptions(AutomationLocators.CATEGORYDROPDOWN,
								AutomationLocators.CATEGORYDROPDOWNVALUE, ElementType.Xpath);
						Thread.sleep(1500);
						flag = webDB.clickAnElement(AutomationLocators.TYPEDROPDOWN, ElementType.Xpath);
						if (flag) {
							webDB.selectDropDownOptions(AutomationLocators.TYPEDROPDOWN,
									AutomationLocators.TYPEDROPDOWNVALUE, ElementType.Xpath);
							Thread.sleep(1500);
							flag = webDB.clickAnElement(AutomationLocators.LANGUAGEDROPDOWN, ElementType.Xpath);
							if (flag) {
								webDB.selectDropDownOptions(AutomationLocators.LANGUAGEDROPDOWN,
										AutomationLocators.LANGUAGEDROPDOWNVALUE, ElementType.Xpath);
								webDB.moveToElement(AutomationLocators.BODYINPUT, ElementType.Xpath);
								flag = webDB.waitForElement(AutomationLocators.BODYINPUT, ElementType.Xpath);
								if (flag) {
									webDB.sendTextToAnElement(AutomationLocators.BODYINPUT,
											AutomationLocators.BODYVALUE, ElementType.Xpath);
//									webDB.sendTextToAnElement(TemplatesLocators.SAMPLEMESSAGEINPUT,
//											TemplatesLocators.SAMPLEMESSAGEVALUE, ElementType.Xpath);
									Thread.sleep(2500);
									flag = verifyViewCustomTemplateName();
									if (flag) {
										Thread.sleep(2500);
										flag = verifyDeleteCustomTemplateName();
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

	public static boolean verifyCreateCustomTemplateswithFooterHeader() throws InterruptedException {
		random = AutomationLocators.TEMPLATENAMEVALUE + RandomStringUtils.randomNumeric(3);
		webDB.driver.navigate().refresh();
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			flag = webDB.waitForElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			if (flag) {
				Thread.sleep(1500);
				webDB.clickAnElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			}
		}
		flag = webDB.waitForElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
			if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
					|| (System.getProperty("os").equals("Android"))) {
				webDB.clickAnElement(CommonLocators.CLOSEICON, ElementType.Xpath);
			}
			Thread.sleep(2000);
			webDB.moveToElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				webDB.javaScriptClickAnElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
				Thread.sleep(2000);
				flag = webDB.waitForElement(AutomationLocators.CREATECUSTOMTEMPLATESBTN, ElementType.Xpath);
				if (flag) {
					webDB.javaScriptClickAnElement(AutomationLocators.CREATECUSTOMTEMPLATESBTN, ElementType.Xpath);
					webDB.sendTextToAnElement(AutomationLocators.TEMPLATESNAMEINPUT, random, ElementType.CssSelector);
					flag = webDB.clickAnElement(AutomationLocators.CATEGORYDROPDOWN, ElementType.Xpath);
					if (flag) {
						webDB.selectDropDownOptions(AutomationLocators.CATEGORYDROPDOWN,
								AutomationLocators.CATEGORYDROPDOWNVALUE, ElementType.Xpath);
						Thread.sleep(1500);
						flag = webDB.clickAnElement(AutomationLocators.TYPEDROPDOWN, ElementType.Xpath);
						if (flag) {
							webDB.selectDropDownOptions(AutomationLocators.TYPEDROPDOWN,
									AutomationLocators.TYPEDROPDOWNVALUE, ElementType.Xpath);
							Thread.sleep(1500);
							flag = webDB.clickAnElement(AutomationLocators.LANGUAGEDROPDOWN, ElementType.Xpath);
							if (flag) {
								webDB.selectDropDownOptions(AutomationLocators.LANGUAGEDROPDOWN,
										AutomationLocators.LANGUAGEDROPDOWNVALUE, ElementType.Xpath);
								webDB.moveToElement(AutomationLocators.BODYINPUT, ElementType.Xpath);
								flag = webDB.waitForElement(AutomationLocators.BODYINPUT, ElementType.Xpath);
								if (flag) {
									webDB.sendTextToAnElement(AutomationLocators.BODYINPUT,
											AutomationLocators.BODYVARIABLEVALUE, ElementType.Xpath);
									Thread.sleep(2500);
									webDB.sendTextToAnElement(AutomationLocators.HEADERINPUT,
											AutomationLocators.HEADERVALUE, ElementType.Xpath);
									Thread.sleep(2500);
									webDB.sendTextToAnElement(AutomationLocators.FOOTERINPUT,
											AutomationLocators.FOOTERVALUE, ElementType.Xpath);
//									webDB.sendTextToAnElement(TemplatesLocators.SAMPLEMESSAGEINPUT,
//											TemplatesLocators.SAMPLEMESSAGEVARIABLEVALUE, ElementType.Xpath);
									flag = verifyViewCustomTemplateName();
									if (flag) {
										Thread.sleep(2500);
										// modified - added new method for this method alone
										flag = verifyDeleteCustomTemplateName1();
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

	public static boolean verifyOptionalMandatoryInput() throws InterruptedException {
		webDB.driver.navigate().refresh();
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			flag = webDB.waitForElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			if (flag) {
				Thread.sleep(1500);
				webDB.clickAnElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			}
		}
		flag = webDB.waitForElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
			if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
					|| (System.getProperty("os").equals("Android"))) {
				webDB.clickAnElement(CommonLocators.CLOSEICON, ElementType.Xpath);
			}
			Thread.sleep(2000);
			webDB.moveToElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				webDB.javaScriptClickAnElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
				Thread.sleep(2000);
				flag = webDB.waitForElement(AutomationLocators.CREATECUSTOMTEMPLATESBTN, ElementType.Xpath);
				if (flag) {
					webDB.javaScriptClickAnElement(AutomationLocators.CREATECUSTOMTEMPLATESBTN, ElementType.Xpath);
					webDB.moveToElement(AutomationLocators.HEADERLABEL, ElementType.Xpath);
					flag = webDB.waitForElement(AutomationLocators.HEADERLABEL, ElementType.Xpath);
					if (flag) {
						String temp = webDB.getText(AutomationLocators.HEADERLABEL, ElementType.Xpath);
						System.out.println("The text is " + temp);
						flag = temp.contains(AutomationLocators.LABELOPTIONALVALUE);
						if (flag) {
							String temp1 = webDB.getText(AutomationLocators.FOOTERLABEL, ElementType.Xpath);
							System.out.println("The text is " + temp);
							flag = temp1.contains(AutomationLocators.LABELOPTIONALVALUE);
							if (flag) {
								String temp2 = webDB.getText(AutomationLocators.BUTTONSLABEL, ElementType.Xpath);
								System.out.println("The text is " + temp2);
								flag = temp2.contains(AutomationLocators.LABELOPTIONALVALUE);
								if (flag) {
									webDB.moveToElement(AutomationLocators.TEMPLATELABEL, ElementType.Xpath);
									String temp3 = webDB.getText(AutomationLocators.TEMPLATELABEL, ElementType.Xpath);
									flag = temp3.contains(AutomationLocators.MANDATORYVALUE);
									if (flag) {
										String temp4 = webDB.getText(AutomationLocators.CATEGORYLABEL,
												ElementType.Xpath);
										flag = temp4.contains(AutomationLocators.MANDATORYVALUE);
										if (flag) {
											String temp5 = webDB.getText(AutomationLocators.TYPELABEL,
													ElementType.Xpath);
											flag = temp5.contains(AutomationLocators.MANDATORYVALUE);
											if (flag) {
												String temp6 = webDB.getText(AutomationLocators.LANGUAGELABEL,
														ElementType.Xpath);
												flag = temp6.contains(AutomationLocators.MANDATORYVALUE);
												if (flag) {
													String temp7 = webDB.getText(AutomationLocators.BODYLABEL,
															ElementType.Xpath);
													flag = temp7.contains(AutomationLocators.MANDATORYVALUE);
//													if (flag) {
//														webDB.moveToElement(TemplatesLocators.SAMPLEMESSAGELABEL,
//																ElementType.Xpath);
//														String temp8 = webDB.getText(
//																TemplatesLocators.SAMPLEMESSAGELABEL,
//																ElementType.Xpath);
//														flag = temp8.contains(TemplatesLocators.MANDATORYVALUE);
//													}
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

	public static boolean verifyWhatsapptoolSubscribed()
			throws InterruptedException, FileNotFoundException, IOException {
		// PlansPage.changeFreePlan();
		PlansPage.changePlanAPI(PlansPageLocators.FREE);
		flag = webDB.waitForElement(AutomationLocators.MESSAGINGTAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AutomationLocators.MESSAGINGTAB, ElementType.Xpath);
			Thread.sleep(2000);
			webDB.moveToElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
			flag = webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				webDB.javaScriptClickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
				Thread.sleep(2000);
				flag = webDB.waitForElement(AutomationLocators.WHATSAPPSUPPORTTOOL, ElementType.Xpath);
//				if (flag) {
//					Thread.sleep(2500);
//					PlansPage.loginShopify();
//					flag = webDB.isElementDisplayed(PlansPageLocators.BILLDETAILS, ElementType.Xpath);
//					if (flag) {
//						flag = webDB.isElementDisplayed(PlansPageLocators.SUBCRIPTIONBILLDETAILS, ElementType.Xpath);
//						webDB.moveToElement(PlansPageLocators.ULTIMATE_PLAN_APPROVEBUTTON, ElementType.Xpath);
//						flag = webDB.waitForElement(PlansPageLocators.ULTIMATE_PLAN_APPROVEBUTTON, ElementType.Xpath);
//						if (flag) {
//							Thread.sleep(2500);
//							webDB.clickAnElement(PlansPageLocators.ULTIMATE_PLAN_APPROVEBUTTON, ElementType.Xpath);
//						} else {
//							System.out.println("done");
//							flag = true;
//						}
//						flag = webDB.waitForElement(PlansPageLocators.UPGRADEBUTTON, ElementType.Xpath);
//
//					}
//				} else {
//					System.out.println("Already U have in Free Plan");
//					flag = true;
//				}
			}
//			flag = webDB.waitForElement(TemplatesLocators.TEMPLATES, ElementType.Xpath);
//			if (flag) {
//				webDB.clickAnElement(TemplatesLocators.TEMPLATES, ElementType.Xpath);
//				Thread.sleep(2000);
//				webDB.moveToElement(TemplatesLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
//				flag = webDB.waitForElement(TemplatesLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
//				if (flag) {
//					Thread.sleep(2000);
//					webDB.javaScriptClickAnElement(TemplatesLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
//					flag = webDB.waitForElement(TemplatesLocators.CREATE_TEMPLATES, ElementType.Xpath);
//					if (flag) {
//						flag = webDB.isElementDisplayed(TemplatesLocators.CREATE_TEMPLATES, ElementType.Xpath);
//						CommonFunctions.verifyPlansPage();
//						PlansPage.changeSmallBusinessPlan();
//						PlansPage.changeFreePlan();
//					}
//				}
//			}
		}
		return flag;

	}

	public static boolean verifyGuideAutomatedTemplates() throws InterruptedException {
		webDB.driver.navigate().refresh();
		flag = webDB.waitForElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
			Thread.sleep(4000);
			flag = webDB.waitForElement(AutomationLocators.GUIDE, ElementType.CssSelector);
			if (flag) {
				Thread.sleep(2000);
				webDB.clickAnElement(AutomationLocators.GUIDE, ElementType.CssSelector);
				Thread.sleep(2000);
				webDB.clickAnElement(AutomationLocators.STARTGUIDEBTN, ElementType.Xpath);
				Thread.sleep(2000);
				webDB.clickAnElement(AutomationLocators.NEXTSTEPBTN, ElementType.Xpath);
				Thread.sleep(2000);
				webDB.clickAnElement(AutomationLocators.NEXTSTEPBTN, ElementType.Xpath);
				Thread.sleep(2000);
				webDB.clickAnElement(AutomationLocators.NEXTSTEPBTN, ElementType.Xpath);
				Thread.sleep(2000);
				webDB.clickAnElement(AutomationLocators.NEXTSTEPBTN, ElementType.Xpath);
				Thread.sleep(2000);
				webDB.clickAnElement(AutomationLocators.GUIDECOMPLETEDBTN, ElementType.Xpath);
				flag = webDB.isElementDisplayed(AutomationLocators.GUIDE, ElementType.CssSelector);
			}
		}
		return flag;
	}

	public static boolean verifyGuideAutomatedPromotionalTemplates() throws InterruptedException {
		webDB.driver.navigate().refresh();
		flag = webDB.waitForElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
			Thread.sleep(2000);
			flag = webDB.waitForElement(AutomationLocators.AUTOMATEDPROMOTIONAL_TAB, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				webDB.javaScriptClickAnElement(AutomationLocators.AUTOMATEDPROMOTIONAL_TAB, ElementType.Xpath);
				flag = webDB.waitForElement(AutomationLocators.GUIDE, ElementType.CssSelector);
				if (flag) {
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.GUIDE, ElementType.CssSelector);
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.STARTGUIDEBTN, ElementType.Xpath);
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.NEXTSTEPBTN, ElementType.Xpath);
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.NEXTSTEPBTN, ElementType.Xpath);
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.NEXTSTEPBTN, ElementType.Xpath);
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.NEXTSTEPBTN, ElementType.Xpath);
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.GUIDECOMPLETEDBTN, ElementType.Xpath);
					flag = webDB.isElementDisplayed(AutomationLocators.GUIDE, ElementType.CssSelector);
				}
			}
		}
		return flag;
	}

	public static boolean verifyCreateCustomTemplatesWithAddCustomVariable() throws Exception {
		random = AutomationLocators.TEMPLATENAMEVALUE + RandomStringUtils.randomNumeric(3);
		webDB.moveToElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
		flag = webDB.waitForElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
		if (flag) {
			Thread.sleep(3000);
			webDB.javaScriptClickAnElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
			Thread.sleep(2000);
			flag = webDB.waitForElement(AutomationLocators.CREATECUSTOMTEMPLATESBTN, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				webDB.javaScriptClickAnElement(AutomationLocators.CREATECUSTOMTEMPLATESBTN, ElementType.Xpath);
				webDB.sendTextToAnElement(AutomationLocators.TEMPLATESNAMEINPUT, random, ElementType.CssSelector);
				flag = webDB.clickAnElement(AutomationLocators.CATEGORYDROPDOWN, ElementType.Xpath);
				if (flag) {
					webDB.selectDropDownOptions(AutomationLocators.CATEGORYDROPDOWN,
							AutomationLocators.CATEGORYDROPDOWNVALUE, ElementType.Xpath);
					Thread.sleep(1500);
					flag = webDB.clickAnElement(AutomationLocators.TYPEDROPDOWN, ElementType.Xpath);
					if (flag) {
						webDB.selectDropDownOptions(AutomationLocators.TYPEDROPDOWN,
								AutomationLocators.TYPEDROPDOWNVALUE, ElementType.Xpath);
						Thread.sleep(1500);
						flag = webDB.clickAnElement(AutomationLocators.LANGUAGEDROPDOWN, ElementType.Xpath);
						if (flag) {
							webDB.selectDropDownOptions(AutomationLocators.LANGUAGEDROPDOWN,
									AutomationLocators.LANGUAGEDROPDOWNVALUE, ElementType.Xpath);
							webDB.moveToElement(AutomationLocators.BODYINPUT, ElementType.Xpath);
							verifyBodyVariable();
							if (flag) {
								Thread.sleep(1000);
								webDB.moveToElement(AutomationLocators.CREATEBTN, ElementType.Xpath);
								String temp3 = "qastore";
								Thread.sleep(2500);
//								webDB.sendTextToAnElement(TemplatesLocators.HEADERINPUT, TemplatesLocators.HEADERVALUE,
//										ElementType.Xpath);
//								Thread.sleep(2500);
//								webDB.sendTextToAnElement(TemplatesLocators.FOOTERINPUT, TemplatesLocators.FOOTERVALUE,
//										ElementType.Xpath);
								webDB.sendTextToAnElement(AutomationLocators.SAMPLEMESSAGEINPUT, temp3,
										ElementType.Xpath);
								String text1 = webDB.getText(AutomationLocators.SAMPLEMESSAGEINPUT, ElementType.Xpath);
								System.out.println(text1);
								String text2 = webDB.driver.findElement(By.xpath(AutomationLocators.SAMPLEMESSAGEINPUT))
										.getAttribute("value");
								System.out.println(text2);
								webDB.clickAnElement(AutomationLocators.SAMPLEMESSAGELABEL, ElementType.Xpath);
								flag = verifyViewCustomTemplateNameWithApproved();
							}
						}

					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyBodyVariable() throws Exception {
		flag = webDB.waitForElement(AutomationLocators.BODYINPUT, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(AutomationLocators.BODYINPUT, AutomationLocators.BODYVARIABLE_FIRST_VALUE,
					ElementType.Xpath);
			Thread.sleep(1000);
			flag = webDB.waitForElement(AutomationLocators.ADD_VARIABLE, ElementType.Xpath);
			System.out.println(flag);
			if (flag) {
				webDB.clickAnElement(AutomationLocators.ADD_VARIABLE, ElementType.Xpath);
				Thread.sleep(2000);
				webDB.moveToElement(AutomationLocators.FIRST_NAME_VARIABLE, ElementType.Xpath);
				webDB.javaScriptClickAnElement(AutomationLocators.FIRST_NAME_VARIABLE, ElementType.Xpath);
				Thread.sleep(1000);
				webDB.sendTextToAnElement(AutomationLocators.BODYINPUT, AutomationLocators.BODYVARIABLE_SECOND_VALUE,
						ElementType.Xpath);
				flag = webDB.waitForElement(AutomationLocators.ADD_VARIABLE, ElementType.Xpath);
				if (flag) {
					Thread.sleep(2000);
					webDB.moveToElement(AutomationLocators.CUSTOM_VARIABLE, ElementType.Xpath);
					webDB.javaScriptClickAnElement(AutomationLocators.CUSTOM_VARIABLE, ElementType.Xpath);
					Thread.sleep(1000);
					webDB.sendTextToAnElement(AutomationLocators.BODYINPUT, AutomationLocators.BODYVARIABLE_THIRD_VALUE,
							ElementType.Xpath);
					flag = webDB.waitForElement(AutomationLocators.ADD_VARIABLE, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(AutomationLocators.ADD_VARIABLE, ElementType.Xpath);
						Thread.sleep(2000);
						webDB.moveToElement(AutomationLocators.PHONE_NUMBER_VARIABLE, ElementType.Xpath);
						if ((System.getProperty("platformName").equals("browserstack"))
								&& (System.getProperty("os").equals("Ios"))
								|| (System.getProperty("os").equals("Android"))) {
							System.out.println("BrowserStack");
						} else {
							flag = webDB.waitForElement(AutomationLocators.PHONE_NUMBER_VARIABLE, ElementType.Xpath);
							if (flag) {
								flag = webDB.waitForElement(AutomationLocators.LAST_NAME_VARIABLE, ElementType.Xpath);
								if (flag) {
									flag = webDB.waitForElement(AutomationLocators.NUMBER_OF_ORDER_VARIABLE,
											ElementType.Xpath);
									webDB.moveToElement(AutomationLocators.ABANDANED_CART_VARIABLE, ElementType.Xpath);
									Thread.sleep(1500);
									if (flag) {
										flag = webDB.waitForElement(AutomationLocators.TOTAL_ORDER_VALUE_VARIABLE,
												ElementType.Xpath);
										if (flag) {
											flag = webDB.waitForElement(AutomationLocators.ABANDANED_CART_VARIABLE,
													ElementType.Xpath);
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

	public static boolean verifyCreateCustomTemplatesExceptCustomVariable() throws Exception {
		random = AutomationLocators.TEMPLATENAMEVALUE + RandomStringUtils.randomNumeric(3);
		webDB.driver.navigate().refresh();
		webDB.moveToElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
		flag = webDB.waitForElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			webDB.javaScriptClickAnElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
			Thread.sleep(2000);
			flag = webDB.waitForElement(AutomationLocators.CREATECUSTOMTEMPLATESBTN, ElementType.Xpath);
			if (flag) {
				webDB.javaScriptClickAnElement(AutomationLocators.CREATECUSTOMTEMPLATESBTN, ElementType.Xpath);
				webDB.sendTextToAnElement(AutomationLocators.TEMPLATESNAMEINPUT, random, ElementType.CssSelector);
				flag = webDB.clickAnElement(AutomationLocators.CATEGORYDROPDOWN, ElementType.Xpath);
				if (flag) {
					webDB.selectDropDownOptions(AutomationLocators.CATEGORYDROPDOWN,
							AutomationLocators.CATEGORYDROPDOWNVALUE, ElementType.Xpath);
					Thread.sleep(1500);
					flag = webDB.clickAnElement(AutomationLocators.TYPEDROPDOWN, ElementType.Xpath);
					if (flag) {
						webDB.selectDropDownOptions(AutomationLocators.TYPEDROPDOWN,
								AutomationLocators.TYPEDROPDOWNVALUE, ElementType.Xpath);
						Thread.sleep(1500);
						flag = webDB.clickAnElement(AutomationLocators.LANGUAGEDROPDOWN, ElementType.Xpath);
						if (flag) {
							webDB.selectDropDownOptions(AutomationLocators.LANGUAGEDROPDOWN,
									AutomationLocators.LANGUAGEDROPDOWNVALUE, ElementType.Xpath);
							webDB.moveToElement(AutomationLocators.BODYINPUT, ElementType.Xpath);
							verifyBodyPredefinedVariable();
							if (flag) {
								Thread.sleep(1000);
								String temp3 = webDB.getText(AutomationLocators.BODYINPUT, ElementType.Xpath);

								Thread.sleep(2500);
								webDB.sendTextToAnElement(AutomationLocators.HEADERINPUT,
										AutomationLocators.HEADERVALUE, ElementType.Xpath);
								Thread.sleep(2500);
								webDB.sendTextToAnElement(AutomationLocators.FOOTERINPUT,
										AutomationLocators.FOOTERVALUE, ElementType.Xpath);
//									webDB.sendTextToAnElement(TemplatesLocators.SAMPLEMESSAGEINPUT, temp3,
//											ElementType.Xpath);
								flag = verifyViewCustomTemplateNameWithApproved();
//									if (flag) {
//										flag = MarketCampaignsPage.verifyCreateMarketCampaignwithPredefined();
//									}
							}

						}
					}
				}
			}
		}

		return flag;
	}

	public static boolean verifyBodyPredefinedVariable() throws Exception {
		flag = webDB.waitForElement(AutomationLocators.BODYINPUT, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(AutomationLocators.BODYINPUT, AutomationLocators.BODYVARIABLE_FIRST_VALUE,
					ElementType.Xpath);
			Thread.sleep(1000);
			flag = webDB.waitForElement(AutomationLocators.ADD_VARIABLE, ElementType.Xpath);
			System.out.println(flag);
			if (flag) {
				webDB.clickAnElement(AutomationLocators.ADD_VARIABLE, ElementType.Xpath);
				Thread.sleep(2000);
				webDB.moveToElement(AutomationLocators.FIRST_NAME_VARIABLE, ElementType.Xpath);
				webDB.javaScriptClickAnElement(AutomationLocators.FIRST_NAME_VARIABLE, ElementType.Xpath);
				Thread.sleep(1000);
				webDB.sendTextToAnElement(AutomationLocators.BODYINPUT, AutomationLocators.BODYVARIABLE_SECOND_VALUE,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(AutomationLocators.ADD_VARIABLE, ElementType.Xpath);
					Thread.sleep(2000);
					webDB.moveToElement(AutomationLocators.PHONE_NUMBER_VARIABLE, ElementType.Xpath);
					if ((System.getProperty("platformName").equals("browserstack"))
							&& (System.getProperty("os").equals("Ios"))
							|| (System.getProperty("os").equals("Android"))) {
						System.out.println("BrowserStack");
					} else {
						flag = webDB.waitForElement(AutomationLocators.PHONE_NUMBER_VARIABLE, ElementType.Xpath);
						if (flag) {
							flag = webDB.waitForElement(AutomationLocators.LAST_NAME_VARIABLE, ElementType.Xpath);
							if (flag) {
								flag = webDB.waitForElement(AutomationLocators.NUMBER_OF_ORDER_VARIABLE,
										ElementType.Xpath);
								webDB.moveToElement(AutomationLocators.ABANDANED_CART_VARIABLE, ElementType.Xpath);
								Thread.sleep(1500);
								if (flag) {
									flag = webDB.waitForElement(AutomationLocators.TOTAL_ORDER_VALUE_VARIABLE,
											ElementType.Xpath);
									if (flag) {
										flag = webDB.waitForElement(AutomationLocators.ABANDANED_CART_VARIABLE,
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

	public static boolean verifyViewCustomTemplateNameWithApproved() throws InterruptedException {
		Thread.sleep(4500);
		flag = webDB.waitForElement(AutomationLocators.CREATEBTN, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(AutomationLocators.CREATEBTN, ElementType.Xpath);
			Thread.sleep(4000);
			flag = webDB.waitForElement("//h2[contains(text(),'" + random + "')]/parent::div[1]", ElementType.Xpath);
		}
		return flag;
	}

	public static boolean createExtensiontemplateWithMaximum() throws InterruptedException {
		for (int i = 1; i <= 11; i++) {
			Thread.sleep(2000);
			if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
					|| (System.getProperty("os").equals("Android"))) {
				flag = webDB.waitForElement(CommonLocators.THREEDOTSMENU, ElementType.Xpath);
				if (flag) {
					Thread.sleep(1500);
					webDB.clickAnElement(CommonLocators.THREEDOTSMENU, ElementType.Xpath);
				}
			}
			wait.until(ExpectedConditions
					.elementToBeClickable(webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES))));
			element = webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES));
			js.executeScript("arguments[0].click();", element);
//		webDB.isElementDisplayed(TemplatesLocators.EXTENSION_TEMPLATES, ElementType.Id);
//		webDB.clickAnElement(TemplatesLocators.EXTENSION_TEMPLATES, ElementType.o);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AutomationLocators.CREATE_TEMPLATES)));
			webDB.clickAnElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
			flag = webDB.isElementDisplayed(AutomationLocators.TEMPLATE_NAME_FIELD, ElementType.Xpath);
			if (flag) {
				templateName = "AutoTemplate" + Math.random();
				log.info(templateName);
				category = new Select(webDB.driver.findElement(By.xpath(AutomationLocators.CATEGORY_DROPDOWN)));
				category.selectByVisibleText("Quick Reply");
				Thread.sleep(1000);
				category = new Select(webDB.driver.findElement(By.xpath(AutomationLocators.QUICKREPLYDROPDOWN)));
				category.selectByVisibleText("Quick Reply");
				Thread.sleep(1000);
				webDB.sendTextToAnElement(AutomationLocators.TEMPLATE_NAME_FIELD, templateName, ElementType.Xpath);
				Thread.sleep(1000);
				webDB.sendTextToAnElement(AutomationLocators.TEMPLATE_DESC_FIELD, TemplateDesc, ElementType.Xpath);
				Thread.sleep(1000);

				webDB.clickAnElement(AutomationLocators.SUBMIT, ElementType.Xpath);
				log.debug("Clicked on Submit Button");
				log.info("Template created successfully");
				if (i <= 10) {
					webDB.waitForElement(AutomationLocators.ACTION_SUCCESS_POPUP, ElementType.Xpath);
				}
				if (i == 11) {
					flag = webDB.waitForElement(AutomationLocators.ERROR_TOAST, ElementType.Xpath);
					if (flag) {
						webDB.moveToElement(AutomationLocators.GO_BACK_BTN, ElementType.Xpath);
						flag = webDB.waitForElement(AutomationLocators.GO_BACK_BTN, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(AutomationLocators.GO_BACK_BTN, ElementType.Xpath);
						}
					}
				}
				element = webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES));
				js.executeScript("arguments[0].click();", element);

			}

		}
		return flag;
	}

	public static boolean deleteExtensionTemplate() throws InterruptedException {
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			flag = webDB.waitForElement(CommonLocators.THREEDOTSMENU, ElementType.Xpath);
			if (flag) {
				Thread.sleep(1000);
				webDB.clickAnElement(CommonLocators.THREEDOTSMENU, ElementType.Xpath);
			}
		}

		for (int i = 1000; i <= 5000; i = i + 1000) {
			Thread.sleep(i);
		}
		wait.until(ExpectedConditions
				.elementToBeClickable(webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES))));
		element = webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES));
		js.executeScript("arguments[0].click();", element);
		flag = webDB.waitForElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			elements = webDB.driver.findElements(By.xpath(AutomationLocators.DELETE_FIRST_TEMPLATE));
			int i = 0;
			while (i < elements.size()) {
				Thread.sleep(1500);
				webDB.driver.navigate().refresh();
				wait.until(ExpectedConditions.elementToBeClickable(
						webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES))));
				element = webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES));
				js.executeScript("arguments[0].click();", element);
				webDB.scrollToAnElement(AutomationLocators.DELETE_FIRST_TEMPLATE, ElementType.Xpath);
				flag = webDB.javaScriptClickAnElement(AutomationLocators.DELETE_FIRST_TEMPLATE, ElementType.Xpath);
				if (flag) {
					Thread.sleep(1000);
					if ((System.getProperty("platformName").equals("browserstack"))
							&& (System.getProperty("os").equals("Ios"))
							|| (System.getProperty("os").equals("Android"))) {
						webDB.pressTabKey();
						webDB.pressTabKey();
						Thread.sleep(1000);
						webDB.pressEnterKey();
						log.info("Template Deleted Successfully");
					} else {
						webDB.javaScriptClickAnElement(AutomationLocators.CONFIRM, ElementType.Xpath);
					}
					flag = webDB.waitForElement(AutomationLocators.ACTION_SUCCESS_POPUP, ElementType.Xpath);
					if (flag) {
						flag = webDB.isElementDisplayed(AutomationLocators.ACTION_SUCCESS_POPUP, ElementType.Xpath);
						if (flag) {
							text = webDB.getText(AutomationLocators.ACTION_SUCCESS_POPUP, ElementType.Xpath);
							if (text.contains(MessagingLocators.TEMPLATE_DELETED)) {
								log.info("Template Deleted Successfully");
							}
						}
						Thread.sleep(2000);
					}
				}
				i++;
			}
		}
		flag = true;
		return flag;
	}

	public static boolean createOrderTemplateWithMaximum() throws InterruptedException {
		for (int i = 1; i <= 16; i++) {
			Thread.sleep(1500);
			if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
					|| (System.getProperty("os").equals("Android"))) {
				flag = webDB.waitForElement(CommonLocators.THREEDOTSMENU, ElementType.Xpath);
				if (flag) {
					Thread.sleep(1500);
					webDB.clickAnElement(CommonLocators.THREEDOTSMENU, ElementType.Xpath);
				}
			}
			wait.until(ExpectedConditions
					.elementToBeClickable(webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES))));
			element = webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES));
			js.executeScript("arguments[0].click();", element);
//		webDB.isElementDisplayed(TemplatesLocators.EXTENSION_TEMPLATES, ElementType.Id);
//		webDB.clickAnElement(TemplatesLocators.EXTENSION_TEMPLATES, ElementType.o);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AutomationLocators.CREATE_TEMPLATES)));
			webDB.clickAnElement(AutomationLocators.CREATE_TEMPLATES, ElementType.Xpath);
			flag = webDB.isElementDisplayed(AutomationLocators.TEMPLATE_NAME_FIELD, ElementType.Xpath);
			if (flag) {
				templateName = "AutoTemplate" + Math.random();
				log.info(templateName);
				Thread.sleep(1000);
				category = new Select(webDB.driver.findElement(By.xpath(AutomationLocators.CATEGORY_DROPDOWN)));
				category.selectByVisibleText("Orders");
				Thread.sleep(1000);
				webDB.sendTextToAnElement(AutomationLocators.TEMPLATEDISCOUNTVALUE, AutomationLocators.DISCOUNTVALUES,
						ElementType.Xpath);
				Thread.sleep(1000);
				webDB.sendTextToAnElement(AutomationLocators.TEMPLATE_NAME_FIELD, templateName, ElementType.Xpath);
				Thread.sleep(1000);
				webDB.sendTextToAnElement(AutomationLocators.TEMPLATE_DESC_FIELD, TemplateDesc, ElementType.Xpath);
				webDB.clickAnElement(AutomationLocators.SUBMIT, ElementType.Xpath);
				log.debug("Clicked on Submit Button");
				log.info("Template created successfully");
				if (i <= 15) {
					webDB.waitForElement(AutomationLocators.ACTION_SUCCESS_POPUP, ElementType.Xpath);
				}
				if (i == 16) {
					flag = webDB.waitForElement(AutomationLocators.ERROR_TOAST, ElementType.Xpath);
					if (flag) {
						webDB.moveToElement(AutomationLocators.GO_BACK_BTN, ElementType.Xpath);
						flag = webDB.waitForElement(AutomationLocators.GO_BACK_BTN, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(AutomationLocators.GO_BACK_BTN, ElementType.Xpath);
						}
					}
				}
				element = webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES));
				js.executeScript("arguments[0].click();", element);

			}

		}
		return flag;
	}

	/**
	 * verify enable/disable Delivery automated messaging
	 *
	 * @return
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 */
	public static boolean verifyEnableShipmentAutomatedMessaging()
			throws FileNotFoundException, InterruptedException, IOException {
		webDB.waitForElement(AutomationLocators.AUTOMATED_TEMPLATES_TAB, ElementType.Id);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AutomationLocators.ABCART_ENABLE_DISABLE_ICON)));
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ORDER_SHIPMENT_ENABLE_CHECK)).isSelected();
		if (!flag) {
			log.info("Flag is False and enable Order Shipment Messaging method executed");
			flag = enableShipmentTemplateMessaging();
		}
		return flag;
	}

	/**
	 * verify enable/disable Delivery automated messaging
	 *
	 * @return
	 * @return
	 * @throws Exception
	 */

	public static boolean verifyEnableOrderConfirmationAutomatedMessaging() throws Exception {
		Thread.sleep(2000);
		webDB.waitForElement(AutomationLocators.AUTOMATED_TEMPLATES_TAB, ElementType.Id);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ORDER_CRM_ENABLE_CHECK)).isSelected();
		log.info(flag);
		if (!flag) {
			log.info("Flag is False and enable Order Confirmation Messaging method executed");
			flag = enableOrderConfirmationTemplateMessaging();
		}
		return flag;
	}

	public static boolean verifyEnableCODAutomatedMessaging()
			throws FileNotFoundException, InterruptedException, IOException {
		Thread.sleep(2000);
		webDB.waitForElement(AutomationLocators.AUTOMATED_TEMPLATES_TAB, ElementType.Id);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.COD_ENABLE_CHECK)).isSelected();
		log.info(flag);
		if (!flag) {
			log.info("Flag is False and enable COD Messaging method executed");
			flag = enableCODTemplateMessaging();
		}
		return flag;
	}

	/**
	 * verify enable/disable ABCART automated messaging
	 *
	 * @return
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 */

	public static boolean verifyEnableAbcartAutomatedMessaging()
			throws FileNotFoundException, InterruptedException, IOException {
		Thread.sleep(2000);
		webDB.driver.navigate().refresh();
		webDB.moveToElement(AutomationLocators.AUTOMATED_TEMPLATES_TAB, ElementType.Id);
		webDB.waitForElement(AutomationLocators.AUTOMATED_TEMPLATES_TAB, ElementType.Id);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AutomationLocators.ABCART_ENABLE_CHECK)));
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ABCART_ENABLE_CHECK)).isSelected();
		if (!flag) {
			log.info("Flag is False and enable AbCart Messaging method executed");
			flag = enableABCartMessagingTemplate();
		}

		return flag;
	}

	public static boolean verifyEnablePaymentSucessfullTemplate() throws InterruptedException {
		flag = webDB.waitForElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
			webDB.moveToElement(AutomationLocators.ORDERCANCELLATION_TOGGLE, ElementType.Xpath);
			flag = webDB.waitForElement(AutomationLocators.PAYMENTSUCCESSFULL, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				flag = webDB.driver.findElement(By.xpath(AutomationLocators.PAYMENTSUCCESSFUL_ENABLE_CHECK))
						.isSelected();
				if (!flag) {
					webDB.clickAnElement(AutomationLocators.PAYMENTSUCCESSFUL_TOGGLE, ElementType.Xpath);
					flag = webDB.waitForElement(AutomationLocators.TEMPLATES_TOAST_MSG, ElementType.Xpath);
					text = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATES_TOAST_MSG)).getText();
				} else {
					log.info("Already enabled");
					flag = true;
				}
			}
		}
		return flag;
	}

	public static boolean VerifyAbdandedCartOldTemplateMsg()
			throws InterruptedException, FileNotFoundException, IOException {
		CommonFunctions.verifyAdminConfigurtionPage();
		flag = webDB.waitForElement(SettingsLocators.WA_SUPPORT_NUM_TEXT_FEILD, ElementType.Xpath);
		if (flag) {
			webDB.clearText(SettingsLocators.WA_SUPPORT_NUM_TEXT_FEILD);
			webDB.sendTextToAnElement(SettingsLocators.WA_SUPPORT_NUM_TEXT_FEILD,
					SettingsLocators.INVALIDSUPPORTNUMBER_VALUE, ElementType.Xpath);
			flag = webDB.waitForElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
			} else {
				log.info("Already Invalid No");
			}
			flag = webDB.waitForElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				webDB.javaScriptClickAnElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
				Thread.sleep(2000);
				String text = webDB.getText(AutomationLocators.AB_CART_TEMPLATE, ElementType.Xpath).trim();
				flag = text.contains(AutomationLocators.OLD_AB_CART_TEMPLATE_VALUE);
				if (flag) {
					log.info("Old AB Cart Template Msg is showing");
					CommonFunctions.verifyAdminConfigurtionPage();
					flag = webDB.waitForElement(SettingsLocators.WA_SUPPORT_NUM_TEXT_FEILD, ElementType.Xpath);
					if (flag) {
						webDB.clearText(SettingsLocators.WA_SUPPORT_NUM_TEXT_FEILD);
						webDB.sendTextToAnElement(SettingsLocators.WA_SUPPORT_NUM_TEXT_FEILD,
								SettingsLocators.SUPPORTNUMBER_VALUE, ElementType.Xpath);
						flag = webDB.waitForElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
						} else {
							flag = true;
							log.info("Already Valid No");
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean VerifyAbdandedCartNewTemplateMsg() throws InterruptedException {
		flag = webDB.waitForElement(AutomationLocators.SETUPENABLENOW_BTN, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(AutomationLocators.SETUPENABLENOW_BTN, ElementType.Xpath);
		} else {
			log.info("Already Shared inbox enabled");
		}
		flag = webDB.waitForElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			webDB.javaScriptClickAnElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
			Thread.sleep(2000);
			String text = webDB.getText(AutomationLocators.AB_CART_TEMPLATE, ElementType.Xpath).trim();
			System.out.println(text);
			System.out.println(AutomationLocators.NEW_AB_CART_TEMPLATE_VALUE);
			flag = text.contains(AutomationLocators.NEW_AB_CART_TEMPLATE_VALUE);
			if (flag) {
				log.info("New AB Cart Template Msg is showing");
			}
		}
		return flag;
	}

	public static boolean verifyCustomTemplateCallActionsSuffix() throws InterruptedException {
		flag = webDB.clickAnElement(AutomationLocators.CALLTOACTIONSRADIOBUTTON, ElementType.CssSelector);
		if (flag) {
			flag = webDB.clickAnElement(AutomationLocators.CALLTOACTIONSDROPDOWN, ElementType.Xpath);
			if (flag) {
				webDB.selectDropDownOptions(AutomationLocators.CALLTOACTIONSDROPDOWN,
						AutomationLocators.URLDROPDOWNVALUE, ElementType.Xpath);
				webDB.sendTextToAnElement(AutomationLocators.CALLTOACTIONSBUTTONNAME,
						AutomationLocators.CALLTOACTIONSBUTTONNAMEVALUE, ElementType.CssSelector);
				Thread.sleep(500);
				webDB.clickAnElement(AutomationLocators.CALLTOACTIONSDROPDOWNTYPE, ElementType.Xpath);
				webDB.selectDropDownOptions(AutomationLocators.CALLTOACTIONSDROPDOWNTYPE,
						AutomationLocators.CALLTOACTIONSTYPEDYNAMICDROPDOWNVALUE, ElementType.Xpath);
				Thread.sleep(2000);
				flag = webDB.waitForElement(AutomationLocators.CALLTOACTIONSBUTTONVALUEINPUT, ElementType.CssSelector);
				if (flag) {
					webDB.sendTextToAnElement(AutomationLocators.CALLTOACTIONSBUTTONVALUEINPUT,
							AutomationLocators.CALLTOACTIONSBUTTONVALUE, ElementType.CssSelector);
					webDB.sendTextToAnElement(AutomationLocators.SUFFIX_VALUE_INPUT,
							AutomationLocators.CALLTOACTIONSBUTTONVALUE, ElementType.CssSelector);
					Thread.sleep(3500);
				}
			}
		}
		return flag;
	}

	public static boolean verifyAbcartUserSettingsCheck()
			throws InterruptedException, FileNotFoundException, IOException {

//		flag = webDB.IsEnabled(AutomationLocators.ABCART_ENABLE_CHECK, ElementType.Xpath);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ABCART_ENABLE_CHECK)).isSelected();
		if (!flag) {
			EmailTemplatesPage.enableABCartEmailTemplate();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP)));
			System.out.println("Abcart enabled");
		} else {
			System.out.println("Abcart already enabled");
		}
		flag = webDB.waitForElement(AutomationLocators.SETTINGS_ABCART_TEMPLATE, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AutomationLocators.SETTINGS_ABCART_TEMPLATE, ElementType.Xpath);
			Thread.sleep(2000);
			flag = webDB.waitForElement(AutomationLocators.SETTINGSPOPUP, ElementType.CssSelector);
			if (flag) {
				webDB.scrollToAnElement(AutomationLocators.USER_SETTINGS_LEARNMORE, ElementType.Xpath);
				Thread.sleep(2000);
//				flag = webDB.IsEnabled(AutomationLocators.USERCHECK_INPUT, ElementType.Xpath);
				flag = webDB.driver.findElement(By.xpath(AutomationLocators.USERCHECK_INPUT)).isSelected();
				if (!flag) {
					System.out.println("Checkbox is unchecked");
//					webDB.clickAnElement(AutomationLocators.USERCHECKBOX, ElementType.Xpath);
					webDB.clickAnElement(AutomationLocators.USERCHECK_INPUT, ElementType.Xpath);
				}
				System.out.println("Checkbox is checked");
				flag = webDB.waitForElement(AutomationLocators.SAVE, ElementType.Xpath);
				if (flag) {

					webDB.clickAnElement(AutomationLocators.SAVE, ElementType.Xpath);
				}
				//modified - commented below bz no need of closing afte clicking save.
//				flag = webDB.waitForElement(AutomationLocators.SETTINGSCLOSEICON, ElementType.CssSelector);
//				if (flag) {
//					log.info("close icon is visible");
//					webDB.clickAnElement(AutomationLocators.SETTINGSCLOSEICON, ElementType.CssSelector);
					Thread.sleep(2000);
					flag = webDB.waitForElement(AutomationLocators.SETTINGS_ABCART_TEMPLATE, ElementType.Xpath);
					if (flag) {
						Thread.sleep(2000);
						webDB.clickAnElement(AutomationLocators.SETTINGS_ABCART_TEMPLATE, ElementType.Xpath);
						flag = webDB.waitForElement(AutomationLocators.SETTINGSPOPUP, ElementType.CssSelector);
						if (flag) {
							webDB.scrollToAnElement(AutomationLocators.USER_SETTINGS_LEARNMORE, ElementType.Xpath);
							Thread.sleep(2000);
//							 flag = webDB.IsEnabled(AutomationLocators.USERCHECK_INPUT,
//							 ElementType.Xpath);
							flag = webDB.driver.findElement(By.xpath(AutomationLocators.USERCHECK_INPUT)).isSelected();
							// modified - converted to not flag
							if (!flag) {
								System.out.println("Enabled");
								webDB.clickAnElement(AutomationLocators.SETTINGSCLOSEICON, ElementType.CssSelector);
								webDB.moveToElement(AutomationLocators.SETTINGS_ABCART_TEMPLATE, ElementType.Xpath);
								flag = webDB.waitForElement(AutomationLocators.SETTINGS_ABCART_TEMPLATE,
										ElementType.Xpath);
							}
						}
					}
				}
			}
//		}
		return flag;
	}

	public static boolean verifyAbcartUserSettingsUncheck()
			throws InterruptedException, FileNotFoundException, IOException {

		flag = webDB.IsEnabled(AutomationLocators.ABCART_ENABLE_CHECK, ElementType.Xpath);
		if (!flag) {
			EmailTemplatesPage.enableABCartEmailTemplate();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP)));
			System.out.println("Abcart enabled");
		}
		flag = webDB.waitForElement(AutomationLocators.SETTINGS_ABCART_TEMPLATE, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AutomationLocators.SETTINGS_ABCART_TEMPLATE, ElementType.Xpath);
			Thread.sleep(2000);
			flag = webDB.waitForElement(AutomationLocators.SETTINGSPOPUP, ElementType.CssSelector);
			if (flag) {
				webDB.scrollToAnElement(AutomationLocators.USER_SETTINGS_LEARNMORE, ElementType.Xpath);
				Thread.sleep(2000);
				flag = webDB.IsEnabled(AutomationLocators.USERCHECK_INPUT, ElementType.Xpath);
				if (flag) {
					System.out.println("Checkbox is checked");
					webDB.clickAnElement(AutomationLocators.USERCHECKBOX, ElementType.Xpath);
					flag = webDB.IsEnabled(AutomationLocators.USERCHECK_INPUT, ElementType.Xpath);
					if (!flag) {
						System.out.println("Checkbox is now Unchecked");
					}
				}
				flag = webDB.waitForElement(AutomationLocators.SAVE, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(AutomationLocators.SAVE, ElementType.Xpath);
					flag = webDB.waitForElement(AutomationLocators.SETTINGS_ABCART_TEMPLATE, ElementType.Xpath);
					Thread.sleep(2000);
					if (flag) {
						webDB.clickAnElement(AutomationLocators.SETTINGS_ABCART_TEMPLATE, ElementType.Xpath);
						flag = webDB.waitForElement(AutomationLocators.SETTINGSPOPUP, ElementType.CssSelector);
						if (flag) {
							webDB.scrollToAnElement(AutomationLocators.USER_SETTINGS_LEARNMORE, ElementType.Xpath);
							flag = webDB.IsEnabled(AutomationLocators.USERCHECK_INPUT, ElementType.Xpath);
							if (!flag) {
								System.out.println("Not Enabled");
								webDB.clickAnElement(AutomationLocators.SETTINGSCLOSEICON, ElementType.CssSelector);
								webDB.moveToElement(AutomationLocators.SETTINGS_ABCART_TEMPLATE, ElementType.Xpath);
								flag = webDB.waitForElement(AutomationLocators.SETTINGS_ABCART_TEMPLATE,
										ElementType.Xpath);
							}
						}
					}
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
						flag = webDB.IsEnabled(AutomationLocators.USERCHECK_INPUT, ElementType.Xpath);
						if (!flag) {
							System.out.println("Checkbox is unchecked");
							webDB.clickAnElement(AutomationLocators.USERCHECKBOX, ElementType.Xpath);
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
									flag = webDB.IsEnabled(AutomationLocators.USERCHECK_INPUT, ElementType.Xpath);
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
						flag = webDB.IsEnabled(AutomationLocators.USERCHECK_INPUT, ElementType.Xpath);
						Thread.sleep(2000);
						if (flag) {
							System.out.println("Checkbox is checked");
							webDB.clickAnElement(AutomationLocators.USERCHECKBOX, ElementType.Xpath);
							flag = webDB.IsEnabled(AutomationLocators.USERCHECK_INPUT, ElementType.Xpath);
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
									flag = webDB.IsEnabled(AutomationLocators.USERCHECK_INPUT, ElementType.Xpath);
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

	public static boolean verifyMediaType() throws InterruptedException {
		flag = webDB.waitForElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(CampaignsLocators.CREATE_TEMPLATES, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CampaignsLocators.CREATE_TEMPLATES, ElementType.Xpath);
				flag = webDB.waitForElement(AutomationLocators.TYPEDROPDOWN, ElementType.Xpath);
				if (flag) {
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.TYPEDROPDOWN, ElementType.Xpath);
					Thread.sleep(2000);
					WebElement drpCountryElement = webDB.driver.findElement(By.xpath(AutomationLocators.TYPEDROPDOWN));
					Select media = new Select(drpCountryElement);
					List<WebElement> alloptions = media.getOptions();
					for (WebElement option : alloptions) {
						flag = option.getText().equals("Image");
						if (flag) {
							System.out.println("Image is verified");
						}
						flag = option.getText().equals("Video");
						if (flag) {
							System.out.println("Video is verified");
						}
						flag = option.getText().equals("Document");
						if (flag) {
							System.out.println("Document is verified");
							break;
						}

					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyImageMediaType() throws InterruptedException, IOException {
		flag = webDB.waitForElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(CampaignsLocators.CREATE_TEMPLATES, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CampaignsLocators.CREATE_TEMPLATES, ElementType.Xpath);
				flag = webDB.waitForElement(AutomationLocators.TYPEDROPDOWN, ElementType.Xpath);
				if (flag) {
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.TYPEDROPDOWN, ElementType.Xpath);
					webDB.selectDropDownOptions(AutomationLocators.TYPEDROPDOWN,
							AutomationLocators.TYPEIMAGEDROPDOWNVALUE, ElementType.Xpath);
					webDB.moveToElement(AutomationLocators.DISCARDBTN, ElementType.Xpath);
					Thread.sleep(2000);
					propertyFilePath = (System.getProperty("user.dir") + File.separator + "payload" + File.separator
							+ AutomationLocators.IMAGE_VALIDFILE);
					flag = webDB.sendTextToAnElement(AutomationLocators.CHOOSEFILE_INPUT, propertyFilePath,
							ElementType.CssSelector);
					if (flag) {
						flag = webDB.isElementDisplayed(AutomationLocators.UPLOADED_FILE, ElementType.Xpath);
						System.out.println("Valid image file is uploaded");
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyVideoMediaType() throws InterruptedException, IOException {
		flag = webDB.waitForElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			webDB.javaScriptClickAnElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(CampaignsLocators.CREATE_TEMPLATES, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CampaignsLocators.CREATE_TEMPLATES, ElementType.Xpath);
				flag = webDB.waitForElement(AutomationLocators.TYPEDROPDOWN, ElementType.Xpath);
				if (flag) {
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.TYPEDROPDOWN, ElementType.Xpath);
					webDB.selectDropDownOptions(AutomationLocators.TYPEDROPDOWN,
							AutomationLocators.TYPEVIDEODROPDOWNVALUE, ElementType.Xpath);
					Thread.sleep(2000);
					webDB.moveToElement(AutomationLocators.DISCARDBTN, ElementType.Xpath);
					Thread.sleep(2000);
					propertyFilePath = (System.getProperty("user.dir") + File.separator + "payload" + File.separator
							+ AutomationLocators.VIDEO_VALIDFILE);
					flag = webDB.sendTextToAnElement(AutomationLocators.CHOOSEFILE_INPUT, propertyFilePath,
							ElementType.CssSelector);
					if (flag) {
						flag = webDB.isElementDisplayed(AutomationLocators.UPLOADED_FILE, ElementType.Xpath);
						System.out.println("Valid video file is uploaded");
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyDocumentMediaType() throws InterruptedException, IOException {
		flag = webDB.waitForElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(CampaignsLocators.CREATE_TEMPLATES, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CampaignsLocators.CREATE_TEMPLATES, ElementType.Xpath);
				flag = webDB.waitForElement(AutomationLocators.TYPEDROPDOWN, ElementType.Xpath);
				if (flag) {
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.TYPEDROPDOWN, ElementType.Xpath);
					webDB.selectDropDownOptions(AutomationLocators.TYPEDROPDOWN,
							AutomationLocators.TYPEDOCUMENTDROPDOWNVALUE, ElementType.Xpath);
					Thread.sleep(2000);
					webDB.moveToElement(AutomationLocators.DISCARDBTN, ElementType.Xpath);
					Thread.sleep(2000);
					propertyFilePath = (System.getProperty("user.dir") + File.separator + "payload" + File.separator
							+ AutomationLocators.DOCUMENT_VALIDFILE);
					flag = webDB.sendTextToAnElement(AutomationLocators.CHOOSEFILE_INPUT, propertyFilePath,
							ElementType.CssSelector);
					if (flag) {
						flag = webDB.isElementDisplayed(AutomationLocators.UPLOADED_FILE, ElementType.Xpath);
						System.out.println("Valid document is uploaded");
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyDocumentWarning() throws InterruptedException, IOException {
		flag = webDB.waitForElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(CampaignsLocators.CREATE_TEMPLATES, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CampaignsLocators.CREATE_TEMPLATES, ElementType.Xpath);
				flag = webDB.waitForElement(AutomationLocators.TYPEDROPDOWN, ElementType.Xpath);
				if (flag) {
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.TYPEDROPDOWN, ElementType.Xpath);
					webDB.selectDropDownOptions(AutomationLocators.TYPEDROPDOWN,
							AutomationLocators.TYPEDOCUMENTDROPDOWNVALUE, ElementType.Xpath);
					Thread.sleep(2000);
					webDB.moveToElement(AutomationLocators.DISCARDBTN, ElementType.Xpath);
					Thread.sleep(2000);
					propertyFilePath = (System.getProperty("user.dir") + File.separator + "payload" + File.separator
							+ AutomationLocators.DOCUMENT_ZIP);
					flag = webDB.sendTextToAnElement(AutomationLocators.CHOOSEFILE_INPUT, propertyFilePath,
							ElementType.CssSelector);
					if (flag) {
						wait.until(ExpectedConditions
								.visibilityOfAllElementsLocatedBy(By.xpath(AutomationLocators.TEMPLATE_EDIT_SUCCESS)));
						text = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATE_EDIT_SUCCESS)).getText();
						flag = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATE_EDIT_SUCCESS))
								.isDisplayed();
						if (flag) {
							flag = text.equals(AutomationLocators.DOCUMENT_WARNING);
							System.out.println("Warning message for document is displayed");
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyImageWarning() throws InterruptedException, IOException {
		flag = webDB.waitForElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(CampaignsLocators.CREATE_TEMPLATES, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CampaignsLocators.CREATE_TEMPLATES, ElementType.Xpath);
				flag = webDB.waitForElement(AutomationLocators.TYPEDROPDOWN, ElementType.Xpath);
				if (flag) {
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.TYPEDROPDOWN, ElementType.Xpath);
					webDB.selectDropDownOptions(AutomationLocators.TYPEDROPDOWN,
							AutomationLocators.TYPEIMAGEDROPDOWNVALUE, ElementType.Xpath);
					Thread.sleep(2000);
					webDB.moveToElement(AutomationLocators.DISCARDBTN, ElementType.Xpath);
					Thread.sleep(2000);
					propertyFilePath = (System.getProperty("user.dir") + File.separator + "payload" + File.separator
							+ AutomationLocators.IMAGE_TIFF);
					flag = webDB.sendTextToAnElement(AutomationLocators.CHOOSEFILE_INPUT, propertyFilePath,
							ElementType.CssSelector);
					if (flag) {
						wait.until(ExpectedConditions
								.visibilityOfAllElementsLocatedBy(By.xpath(AutomationLocators.TEMPLATE_EDIT_SUCCESS)));
						text = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATE_EDIT_SUCCESS)).getText();
						flag = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATE_EDIT_SUCCESS))
								.isDisplayed();
						if (flag) {
							flag = text.equals(AutomationLocators.IMAGE_WARNING);
							System.out.println("Warning message for image file is displayed");
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyVideoWarning() throws InterruptedException, IOException {
		flag = webDB.waitForElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(CampaignsLocators.CREATE_TEMPLATES, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CampaignsLocators.CREATE_TEMPLATES, ElementType.Xpath);
				flag = webDB.waitForElement(AutomationLocators.TYPEDROPDOWN, ElementType.Xpath);
				if (flag) {
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.TYPEDROPDOWN, ElementType.Xpath);
					webDB.selectDropDownOptions(AutomationLocators.TYPEDROPDOWN,
							AutomationLocators.TYPEVIDEODROPDOWNVALUE, ElementType.Xpath);
					Thread.sleep(2000);
					webDB.moveToElement(AutomationLocators.DISCARDBTN, ElementType.Xpath);
					Thread.sleep(2000);
					propertyFilePath = (System.getProperty("user.dir") + File.separator + "payload" + File.separator
							+ AutomationLocators.VIDEO_MOV);
					flag = webDB.sendTextToAnElement(AutomationLocators.CHOOSEFILE_INPUT, propertyFilePath,
							ElementType.CssSelector);
					if (flag) {
						wait.until(ExpectedConditions
								.visibilityOfAllElementsLocatedBy(By.xpath(AutomationLocators.TEMPLATE_EDIT_SUCCESS)));
						text = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATE_EDIT_SUCCESS)).getText();
						flag = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATE_EDIT_SUCCESS))
								.isDisplayed();
						if (flag) {
							flag = text.equals(AutomationLocators.VIDEO_WARNING);
							System.out.println("Warning message for video is displayed");
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyImageSizeLimit() throws InterruptedException, IOException {
		flag = webDB.waitForElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(CampaignsLocators.CREATE_TEMPLATES, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CampaignsLocators.CREATE_TEMPLATES, ElementType.Xpath);
				flag = webDB.waitForElement(AutomationLocators.TYPEDROPDOWN, ElementType.Xpath);
				if (flag) {
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.TYPEDROPDOWN, ElementType.Xpath);
					webDB.selectDropDownOptions(AutomationLocators.TYPEDROPDOWN,
							AutomationLocators.TYPEIMAGEDROPDOWNVALUE, ElementType.Xpath);
					Thread.sleep(2000);
					webDB.moveToElement(AutomationLocators.DISCARDBTN, ElementType.Xpath);
					Thread.sleep(2000);
					propertyFilePath = (System.getProperty("user.dir") + File.separator + "payload" + File.separator
							+ AutomationLocators.IMAGE_INVALIDFILE);
					flag = webDB.sendTextToAnElement(AutomationLocators.CHOOSEFILE_INPUT, propertyFilePath,
							ElementType.CssSelector);
					if (flag) {
						wait.until(ExpectedConditions
								.visibilityOfAllElementsLocatedBy(By.xpath(AutomationLocators.TEMPLATE_EDIT_SUCCESS)));
						text = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATE_EDIT_SUCCESS)).getText();
						flag = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATE_EDIT_SUCCESS))
								.isDisplayed();
						if (flag) {
							flag = text.equals(AutomationLocators.IMAGE_WARNING);
							System.out.println("Warning message for image size limit is displayed");
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyVideoSizeLimit() throws InterruptedException, IOException {
		flag = webDB.waitForElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(CampaignsLocators.CREATE_TEMPLATES, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CampaignsLocators.CREATE_TEMPLATES, ElementType.Xpath);
				flag = webDB.waitForElement(AutomationLocators.TYPEDROPDOWN, ElementType.Xpath);
				if (flag) {
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.TYPEDROPDOWN, ElementType.Xpath);
					webDB.selectDropDownOptions(AutomationLocators.TYPEDROPDOWN,
							AutomationLocators.TYPEVIDEODROPDOWNVALUE, ElementType.Xpath);
					webDB.moveToElement(AutomationLocators.DISCARDBTN, ElementType.Xpath);
					Thread.sleep(2000);
					propertyFilePath = (System.getProperty("user.dir") + File.separator + "payload" + File.separator
							+ AutomationLocators.VIDEO_INVALIDFILE);
					flag = webDB.sendTextToAnElement(AutomationLocators.CHOOSEFILE_INPUT, propertyFilePath,
							ElementType.CssSelector);
					if (flag) {
						wait.until(ExpectedConditions
								.visibilityOfAllElementsLocatedBy(By.xpath(AutomationLocators.TEMPLATE_EDIT_SUCCESS)));
						text = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATE_EDIT_SUCCESS)).getText();
						flag = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATE_EDIT_SUCCESS))
								.isDisplayed();
						if (flag) {
							flag = text.equals(AutomationLocators.VIDEO_WARNING);
							System.out.println("Warning message for video size limit is displayed");
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyDocumentSizeLimit() throws InterruptedException, IOException {
		flag = webDB.waitForElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(CampaignsLocators.CREATE_TEMPLATES, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CampaignsLocators.CREATE_TEMPLATES, ElementType.Xpath);
				flag = webDB.waitForElement(AutomationLocators.TYPEDROPDOWN, ElementType.Xpath);
				if (flag) {
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.TYPEDROPDOWN, ElementType.Xpath);
					webDB.selectDropDownOptions(AutomationLocators.TYPEDROPDOWN,
							AutomationLocators.TYPEDOCUMENTDROPDOWNVALUE, ElementType.Xpath);
					Thread.sleep(2000);
					webDB.moveToElement(AutomationLocators.DISCARDBTN, ElementType.Xpath);
					Thread.sleep(2000);
					propertyFilePath = (System.getProperty("user.dir") + File.separator + "payload" + File.separator
							+ AutomationLocators.DOCUMENT_INVALIDFILE);
					flag = webDB.sendTextToAnElement(AutomationLocators.CHOOSEFILE_INPUT, propertyFilePath,
							ElementType.CssSelector);
					if (flag) {
						wait.until(ExpectedConditions
								.visibilityOfAllElementsLocatedBy(By.xpath(AutomationLocators.TEMPLATE_EDIT_SUCCESS)));
						text = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATE_EDIT_SUCCESS)).getText();
						flag = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATE_EDIT_SUCCESS))
								.isDisplayed();
						if (flag) {
							flag = text.equals(AutomationLocators.DOCUMENT_WARNING);
							System.out.println("Warning message for document size limit is displayed");
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifySupportMediaTypes() throws InterruptedException {
		flag = webDB.waitForElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(CampaignsLocators.CREATE_TEMPLATES, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CampaignsLocators.CREATE_TEMPLATES, ElementType.Xpath);
				flag = webDB.waitForElement(AutomationLocators.TYPEDROPDOWN, ElementType.Xpath);
				if (flag) {
					Thread.sleep(2000);
					webDB.clickAnElement(AutomationLocators.TYPEDROPDOWN, ElementType.Xpath);
					Thread.sleep(2000);
					WebElement drpCountryElement = webDB.driver.findElement(By.xpath(AutomationLocators.TYPEDROPDOWN));
					Select media = new Select(drpCountryElement);
					List<WebElement> alloptions = media.getOptions();
					for (WebElement option : alloptions) {
						flag = option.getText().equals("Image");
						if (flag) {
							option.click();
							webDB.moveToElement(AutomationLocators.DISCARDBTN, ElementType.Xpath);
							Thread.sleep(2000);
							text = webDB.driver.findElement(By.xpath(AutomationLocators.FILETYPE_NOTE)).getText();
							flag = webDB.driver.findElement(By.xpath(AutomationLocators.FILETYPE_NOTE)).isDisplayed();
							if (flag) {
								flag = text.equals(AutomationLocators.IMAGE_TYPE);
								System.out.println("Support media type for image is displayed");
							}
						}
						flag = option.getText().equals("Video");
						if (flag) {
							option.click();
							webDB.moveToElement(AutomationLocators.DISCARDBTN, ElementType.Xpath);
							Thread.sleep(2000);
							text = webDB.driver.findElement(By.xpath(AutomationLocators.FILETYPE_NOTE)).getText();
							flag = webDB.driver.findElement(By.xpath(AutomationLocators.FILETYPE_NOTE)).isDisplayed();
							if (flag) {
								flag = text.equals(AutomationLocators.VIDEO_TYPE);
								System.out.println("Support media type for video is displayed");
							}
						}
						flag = option.getText().equals("Document");
						if (flag) {
							option.click();
							webDB.moveToElement(AutomationLocators.DISCARDBTN, ElementType.Xpath);
							Thread.sleep(2000);
							text = webDB.driver.findElement(By.xpath(AutomationLocators.FILETYPE_NOTE)).getText();
							flag = webDB.driver.findElement(By.xpath(AutomationLocators.FILETYPE_NOTE)).isDisplayed();
							if (flag) {
								flag = text.equals(AutomationLocators.DOCUMENT_TYPE);
								System.out.println("Support media type for document is displayed");
							}
							break;
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyAutomationTutorial() throws InterruptedException, IOException {
		flag = webDB.waitForElement(MyWhatsAppLocators.TUTORIAL_VIDEO, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(MyWhatsAppLocators.TUTORIAL_VIDEO, ElementType.Xpath);
			Thread.sleep(2000);
			flag = webDB.waitForElement(WidgetLocators.DISPLAY_TEXT_TUTORIAL, ElementType.Xpath);
			if (flag) {
				String text = webDB.getText(WidgetLocators.DISPLAY_TEXT_TUTORIAL, ElementType.Xpath);
				System.out.println(text);
				if (text.equals(AutomationLocators.TUTORIAL_TITLE)) {
					flag = webDB.waitForElement(CampaignsLocators.WHATSAPPCAMPAIGN_IFRAME, ElementType.CssSelector);
					if (flag) {
						flag = webDB.waitForElement(CampaignsLocators.TUTORIAL_CLOSEBTN, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(CampaignsLocators.TUTORIAL_CLOSEBTN, ElementType.Xpath);
							flag = webDB.waitForElement(CampaignsLocators.TUTORIAL_BTN, ElementType.Xpath);
							if (flag) {
								System.out.println("Verified tutorial video in Automation section");
							}
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyCampaignTemplatesTutorial() throws InterruptedException {
		// modified - commented below
//		flag = webDB.waitForElement(CampaignsLocators.CAMPAIGN_TEMPLATES, ElementType.Xpath);
//		if (flag) {
//			webDB.javaScriptClickAnElement(CampaignsLocators.CAMPAIGN_TEMPLATES, ElementType.Xpath);
		Thread.sleep(2000);
		flag = webDB.waitForElement(CampaignsLocators.CREATE_TEMPLATES, ElementType.Xpath);
		if (flag) {
			flag = webDB.waitForElement(CampaignsLocators.TUTORIAL_BTN, ElementType.Xpath);
			if (flag) {
				webDB.javaScriptClickAnElement(CampaignsLocators.TUTORIAL_BTN, ElementType.Xpath);
				flag = webDB.waitForElement(CampaignsLocators.TEMPLATE_TUTORIAL, ElementType.Xpath);
				if (flag) {
					Thread.sleep(1000);
					webDB.clickAnElement(CampaignsLocators.TEMPLATE_TUTORIAL, ElementType.Xpath);
					Thread.sleep(2000);
					flag = webDB.waitForElement(CampaignsLocators.WHATSAPPCAMPAIGN_IFRAME, ElementType.CssSelector);
					if (flag) {
						flag = webDB.waitForElement(CampaignsLocators.TUTORIAL_CLOSEBTN, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(CampaignsLocators.TUTORIAL_CLOSEBTN, ElementType.Xpath);
							flag = webDB.waitForElement(CampaignsLocators.TUTORIAL_BTN, ElementType.Xpath);
							if (flag) {
								webDB.javaScriptClickAnElement(CampaignsLocators.TUTORIAL_BTN, ElementType.Xpath);
								flag = webDB.waitForElement(CampaignsLocators.CREATETEMPLATE_TUTORIAL,
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(CampaignsLocators.CREATETEMPLATE_TUTORIAL, ElementType.Xpath);
									Thread.sleep(2000);
									flag = webDB.waitForElement(CampaignsLocators.WHATSAPPCAMPAIGN_IFRAME,
											ElementType.CssSelector);
									if (flag) {
										flag = webDB.waitForElement(CampaignsLocators.TUTORIAL_CLOSEBTN,
												ElementType.Xpath);
										if (flag) {
											webDB.clickAnElement(CampaignsLocators.TUTORIAL_CLOSEBTN,
													ElementType.Xpath);
											flag = webDB.waitForElement(CampaignsLocators.TUTORIAL_BTN,
													ElementType.Xpath);
											if (flag) {
												System.out.println("Verified tutorial videos in Campaigns Templates");
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
//		}
		return flag;
	}

	public static boolean verifyCampaignTemplatesGuide() throws InterruptedException {
		flag = webDB.waitForElement(CampaignsLocators.GUIDE, ElementType.CssSelector);
		if (flag) {
			webDB.moveToElement(CampaignsLocators.GUIDE, ElementType.CssSelector);
			webDB.waitForElement(CampaignsLocators.START_GUIDE, ElementType.Xpath);
			webDB.clickAnElement(CampaignsLocators.START_GUIDE, ElementType.Xpath);

			flag = webDB.waitForElement(CampaignsLocators.NEXT_STEP, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CampaignsLocators.NEXT_STEP, ElementType.Xpath);
				webDB.waitForElement(CampaignsLocators.GUIDE_IMG, ElementType.Xpath);
				flag = webDB.verifyElementIsDisplayed(CampaignsLocators.GUIDE_IMG, ElementType.Xpath);
				if (flag) {
					webDB.waitForElement(CampaignsLocators.NEXT_STEP, ElementType.Xpath);
					webDB.clickAnElement(CampaignsLocators.NEXT_STEP, ElementType.Xpath);
					webDB.waitForElement(CampaignsLocators.GUIDE_IMG, ElementType.Xpath);
					flag = webDB.verifyElementIsDisplayed(CampaignsLocators.GUIDE_IMG, ElementType.Xpath);
					if (flag) {
						webDB.waitForElement(CampaignsLocators.NEXT_STEP, ElementType.Xpath);
						webDB.clickAnElement(CampaignsLocators.NEXT_STEP, ElementType.Xpath);
						webDB.waitForElement(CampaignsLocators.GUIDE_IMG, ElementType.Xpath);
						flag = webDB.verifyElementIsDisplayed(CampaignsLocators.GUIDE_IMG, ElementType.Xpath);
						if (flag) {
							webDB.waitForElement(CampaignsLocators.NEXT_STEP, ElementType.Xpath);
							webDB.clickAnElement(CampaignsLocators.NEXT_STEP, ElementType.Xpath);
							webDB.waitForElement(CampaignsLocators.GUIDE_IMG, ElementType.Xpath);
							flag = webDB.verifyElementIsDisplayed(CampaignsLocators.GUIDE_IMG, ElementType.Xpath);
							if (flag) {
								webDB.waitForElement(CampaignsLocators.GUIDE_COMPLETED, ElementType.Xpath);
								webDB.clickAnElement(CampaignsLocators.GUIDE_COMPLETED, ElementType.Xpath);
								System.out.println("Guide screenshots are added to campaign templates");
							}
						}
					}
				}

			}

		}
		return flag;
	}
}