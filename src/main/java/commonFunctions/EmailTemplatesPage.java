package commonFunctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

import locators.CommonLocators;
import locators.EmailTemplatesLocators;
import locators.WidgetLocators;
import locators.AutomationLocators;
import utils.DriverBase;
import utils.DriverBase.ElementType;

@SuppressWarnings({ "static-access", "unused" })
public class EmailTemplatesPage {
	private static Logger log = Logger.getLogger(AutomationPage.class.getName());
	private static boolean flag;
	private static String propertyFilePath;
	private static String url;
	public static Properties prop = new Properties();
	private static DriverBase webDB = new DriverBase();
	private static String templateName;
	private static String text;
	private static String TemplateDesc = "This is a manual messaging Template";
	private static Actions actions = new Actions(webDB.driver);
	private static Select category;
	private static WebDriverWait wait = new WebDriverWait(webDB.driver, 10);
	private static JavascriptExecutor js = (JavascriptExecutor) webDB.driver;
	private static WebElement element;
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
	private static String discountText = "Get {{Discount_percentage}} off by completing the order at";

	public static boolean enableABCartEmailTemplate() throws FileNotFoundException, IOException, InterruptedException {

		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AutomationLocators.ABCART_ENABLE_DISABLE_ICON)));
		element = webDB.driver.findElement(By.xpath(AutomationLocators.ABCART_ENABLE_DISABLE_ICON));
		actions.moveToElement(element).click().build().perform();
//        js.executeScript("arguments[0].click();", element1);
//        enableDisableIcon.get(0).click();
		log.debug("clicked on ABcart Toggle Button");
		Thread.sleep(2000);
//        webDB.clickAnElement(TemplatesLocators.ABCART_ENABLE_DISABLE_ICON, ElementType.Xpath);
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String ABcartEmailEnabledMessage = prop.getProperty("ABcartEmailEnabledMessage");
		flag = webDB.isElementDisplayed(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG, ElementType.Xpath);
		log.info("Template enabled message is Displayed");
		if (flag) {
			text = webDB.driver.findElement(By.xpath(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG)).getText();
			if (text.equalsIgnoreCase(ABcartEmailEnabledMessage)) {
				flag = true;
				log.info("Abandoned Cart Email template Enabled and Enabled Message is displayed");
			}
		}
		return flag;

	}

	public static boolean disableABCartEmailTemplate() throws FileNotFoundException, IOException {
		webDB.waitForElement(EmailTemplatesLocators.TEMPLATES_TAB, DriverBase.ElementType.Id);
		webDB.clickAnElement(AutomationLocators.ABCART_ENABLE_DISABLE_ICON, DriverBase.ElementType.Xpath);
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String ABcartEmailTemplateDisabledMessage = prop.getProperty("ABcartEmailTemplateDisabledMessage");
		log.info(ABcartEmailTemplateDisabledMessage);
		flag = webDB.isElementDisplayed(CommonLocators.TEMPLATE_MSGNG_DISABLED_MSG, DriverBase.ElementType.Xpath);
		if (flag) {
			text = webDB.driver.findElement(By.xpath(CommonLocators.TEMPLATE_MSGNG_DISABLED_MSG)).getText();
			if (text.equalsIgnoreCase(ABcartEmailTemplateDisabledMessage)) {
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

	public static boolean enableCODEmailTemplate() throws FileNotFoundException, IOException {
		webDB.clickAnElement(AutomationLocators.COD_ENABLE_DISABLE_ICON, DriverBase.ElementType.Xpath);
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String CODEmailTemplateEnableMessage = prop.getProperty("CODEmailTemplateEnableMessage");
		log.info(CODEmailTemplateEnableMessage);
		flag = webDB.isElementDisplayed(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG, DriverBase.ElementType.Xpath);
		if (flag) {
			text = webDB.driver.findElement(By.xpath(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG)).getText();
			if (text.equalsIgnoreCase(CODEmailTemplateEnableMessage)) {
				flag = true;
				log.info("COD Order Confirmation Email Template Enabled and Enabled Message is displayed");
			}
		}
		return flag;
	}

	public static boolean disableCODEmailTemplate() throws FileNotFoundException, IOException {
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String CODEmailTemplateDisableMessage = prop.getProperty("CODEmailTemplateDisableMessage");
		webDB.waitForElement(EmailTemplatesLocators.TEMPLATES_TAB, DriverBase.ElementType.Id);
		webDB.clickAnElement(AutomationLocators.COD_ENABLE_DISABLE_ICON, DriverBase.ElementType.Xpath);
		wait.until(ExpectedConditions
				.visibilityOf(webDB.driver.findElement(By.xpath(CommonLocators.TEMPLATE_MSGNG_DISABLED_MSG))));
		flag = webDB.isElementDisplayed(CommonLocators.TEMPLATE_MSGNG_DISABLED_MSG, DriverBase.ElementType.Xpath);
		if (flag) {
			text = webDB.driver.findElement(By.xpath(CommonLocators.TEMPLATE_MSGNG_DISABLED_MSG)).getText();
			if (text.equalsIgnoreCase(CODEmailTemplateDisableMessage)) {
				flag = true;
				log.info("Order Confirmation Email Template Disabled and  Disable Message is  displayed");
			}
		}
		return flag;
	}

	public static boolean enableOrderConfirmationEmailTemplate() throws FileNotFoundException, IOException {
		webDB.clickAnElement(AutomationLocators.ODR_CRM_ENABLE_DISABLE_ICON, DriverBase.ElementType.Xpath);
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String OrderCrmConfirmationEmailTemplateEnabledMessage = prop
				.getProperty("OrderCrmConfirmationEmailTemplateEnabledMessage");
		log.info(OrderCrmConfirmationEmailTemplateEnabledMessage);
		flag = webDB.isElementDisplayed(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG, DriverBase.ElementType.Xpath);
		if (flag) {
			text = webDB.driver.findElement(By.xpath(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG)).getText();
			if (text.equalsIgnoreCase(OrderCrmConfirmationEmailTemplateEnabledMessage)) {
				flag = true;
				log.info("Order Confirmation Email Template Enabled and Enabled Message is displayed");
			}
		}
		return flag;
	}

	public static boolean disableOrderConfirmationEmailTemplate()
			throws FileNotFoundException, IOException, InterruptedException {
		webDB.waitForElement(EmailTemplatesLocators.TEMPLATES_TAB, DriverBase.ElementType.Id);
		webDB.clickAnElement(AutomationLocators.ODR_CRM_ENABLE_DISABLE_ICON, DriverBase.ElementType.Xpath);
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String OrderCrmConfirmationEmailTemplateDisabledMessage = prop
				.getProperty("OrderCrmConfirmationEmailTemplateDisabledMessage");
		log.info(OrderCrmConfirmationEmailTemplateDisabledMessage);
		flag = webDB.isElementDisplayed(CommonLocators.TEMPLATE_MSGNG_DISABLED_MSG, DriverBase.ElementType.Xpath);
		if (flag) {
			text = webDB.driver.findElement(By.xpath(CommonLocators.TEMPLATE_MSGNG_DISABLED_MSG)).getText();
			if (text.equalsIgnoreCase(OrderCrmConfirmationEmailTemplateDisabledMessage)) {
				flag = true;
				log.info("Order Confirmation Template Email Disabled and  Disable Message is  displayed");
			}
		}
		return flag;
	}

	public static boolean enableShipmentEmailTemplate()
			throws FileNotFoundException, InterruptedException, IOException {
		webDB.clickAnElement(AutomationLocators.ODR_SHIPMENT__ENABLE_DISABLE_ICON, DriverBase.ElementType.Xpath);
		Thread.sleep(2000);
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String ShipmentEmailTemplateEnabledMessage = prop.getProperty("ShipmentEmailTemplateEnabledMessage");
		log.info(ShipmentEmailTemplateEnabledMessage);
		flag = webDB.isElementDisplayed(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG, DriverBase.ElementType.Xpath);
		if (flag) {
			text = webDB.driver.findElement(By.xpath(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG)).getText();
			if (text.equalsIgnoreCase(ShipmentEmailTemplateEnabledMessage)) {
				flag = true;
				log.info("Shipment Email  Template Enabled and Enabled Message is displayed");
			}
		}
		return flag;
	}

	public static boolean disableShipmentEmailTemplate() throws FileNotFoundException, IOException {
		webDB.waitForElement(EmailTemplatesLocators.TEMPLATES_TAB, DriverBase.ElementType.Id);
		webDB.clickAnElement(AutomationLocators.ODR_SHIPMENT__ENABLE_DISABLE_ICON, DriverBase.ElementType.Xpath);
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String ShipmentEmailTemplatesDisabledMessage = prop.getProperty("ShipmentEmailTemplatesDisabledMessage");
		log.info(ShipmentEmailTemplatesDisabledMessage);
		flag = webDB.isElementDisplayed(CommonLocators.TEMPLATE_MSGNG_DISABLED_MSG, DriverBase.ElementType.Xpath);
		if (flag) {
			text = webDB.driver.findElement(By.xpath(CommonLocators.TEMPLATE_MSGNG_DISABLED_MSG)).getText();
			if (text.equalsIgnoreCase(ShipmentEmailTemplatesDisabledMessage)) {
				flag = true;
				log.info("Shipment Email Template Disabled and  Disable Message is  displayed");
			}
		}
		return flag;
	}

	public static boolean disableAllEmailTemplatesAndCheckSettings() throws InterruptedException {
		Thread.sleep(3000);
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
			actions.moveToElement(webDB.driver.findElement(By.xpath(AutomationLocators.COD_ENABLE_DISABLE_ICON))).click()
					.build().perform();
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(CommonLocators.TEMPLATE_MSGNG_DISABLED_MSG)));
		}
		settingsDisabled = webDB.driver.findElements(By.xpath(AutomationLocators.SETTINGS_DISABLED_GENERIC));
		int size = settingsDisabled.size();
		if (size == 4) {
			flag = true;
			log.info("Settings is disabled");
		}
		return flag;
	}

	public static boolean enableAllEmailTemplatesAndCheckSettings() throws InterruptedException {
		Thread.sleep(3000);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ABCART_ENABLE_CHECK)).isSelected();
		if (!flag) {
			actions.moveToElement(webDB.driver.findElement(By.xpath(AutomationLocators.ABCART_ENABLE_DISABLE_ICON)))
					.click().build().perform();
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG)));
		}
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ORDER_CRM_ENABLE_CHECK)).isSelected();
		if (!flag) {
			actions.moveToElement(webDB.driver.findElement(By.xpath(AutomationLocators.ODR_CRM_ENABLE_DISABLE_ICON)))
					.click().build().perform();
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG)));
		}
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ORDER_SHIPMENT_ENABLE_CHECK)).isSelected();
		if (!flag) {
			actions.moveToElement(
					webDB.driver.findElement(By.xpath(AutomationLocators.ODR_SHIPMENT__ENABLE_DISABLE_ICON))).click()
					.build().perform();
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG)));
		}
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.COD_ENABLE_CHECK)).isSelected();
		if (!flag) {
			actions.moveToElement(webDB.driver.findElement(By.xpath(AutomationLocators.COD_ENABLE_DISABLE_ICON))).click()
					.build().perform();
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG)));
		}
		settingsDisabled = webDB.driver.findElements(By.xpath(AutomationLocators.SETTINGS_DISABLED_GENERIC));
		int size = settingsDisabled.size();
		if (size == 0) {
			flag = true;
			log.info("Settings is enabled");
		}
		return flag;
	}

	public static boolean addDiscountToABCartTemplate() throws IOException, InterruptedException {
		Thread.sleep(2000);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ABCART_ENABLE_CHECK)).isSelected();
		if (!flag) {
			enableABCartEmailTemplate();
			wait.until(ExpectedConditions
					.invisibilityOf(webDB.driver.findElement(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP))));
		}
		webDB.driver.findElement(By.xpath(AutomationLocators.SETTINGS_ABCART_TEMPLATE)).click();
		flag = selectDiscountTypeAsPercentage();
		return flag;
	}

	public static boolean addDiscountTypeToOrderConfirmationEmailTemplate() throws IOException, InterruptedException {
		Thread.sleep(2000);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ORDER_CRM_ENABLE_CHECK)).isSelected();
		if (!flag) {
			enableOrderConfirmationEmailTemplate();
		}
		webDB.driver.findElement(By.xpath(AutomationLocators.SETTINGS_ODR_CONFIRMATION_TEMPLATE)).click();
		log.debug("Clicked on Settings Tab");
		flag = selectDiscountTypeAsPercentage();
		return flag;
	}

	public static boolean addCouponToABCartTemplate() throws IOException, InterruptedException {
		Thread.sleep(2000);// added wait to grab proper value of isSelected
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ABCART_ENABLE_CHECK)).isSelected();
		if (!flag) {
			enableABCartEmailTemplate();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP)));

		}
		webDB.driver.findElement(By.xpath(AutomationLocators.SETTINGS_ABCART_TEMPLATE)).click();
		flag = selectDiscountTypeAsCoupon();
		return flag;
	}

	public static boolean addCouponToOrderConfirmationTemplate() throws IOException, InterruptedException {
		Thread.sleep(2000);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ORDER_CRM_ENABLE_CHECK)).isSelected();
		if (!flag) {
			enableOrderConfirmationEmailTemplate();
		}
		webDB.driver.findElement(By.xpath(AutomationLocators.SETTINGS_ODR_CONFIRMATION_TEMPLATE)).click();
		flag = selectDiscountTypeAsCoupon();
		return flag;
	}

	@SuppressWarnings("static-access")
	public static boolean selectDiscountTypeAsPercentage() throws InterruptedException {
		Thread.sleep(3000);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.DISCOUNT_TYPE__CHECKBOX)).isSelected();
		if (!flag) {
			element = webDB.driver.findElement(By.xpath(AutomationLocators.DISCOUNT_TYPE));
			js.executeScript("arguments[0].click();", element);
//            webDB.clickAnElement(TemplatesLocators.DISCOUNT_TYPE, ElementType.Xpath);
		}
		Thread.sleep(1500);
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(EmailTemplatesLocators.PERCENTAGE_RADIO)));
//		actions.click(webDB.driver.findElement(By.xpath(EmailTemplatesLocators.PERCENTAGE_RADIO))).build().perform();
		webDB.javaScriptClickAnElement(EmailTemplatesLocators.PERCENTAGE_RADIO, ElementType.Xpath);
		webDB.waitForElement(AutomationLocators.DISCOUNT_VALUE, DriverBase.ElementType.Xpath);
		webDB.driver.findElement(By.xpath(AutomationLocators.DISCOUNT_VALUE))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(AutomationLocators.DISCOUNT_VALUE, discountValue, ElementType.Xpath);
		Thread.sleep(3000);
		webDB.clickAnElement(AutomationLocators.SAVE, DriverBase.ElementType.Xpath);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP)));
		flag = webDB.isElementDisplayed(CommonLocators.ACTION_SUCCESS_POPUP, DriverBase.ElementType.Xpath);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP)));
		return flag;
	}

	public static boolean selectDiscountTypeAsCoupon() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(AutomationLocators.DISCOUNT_TYPE)));
		webDB.waitForElement(AutomationLocators.DISCOUNT_TYPE__CHECKBOX, DriverBase.ElementType.Xpath);
		Thread.sleep(3000);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.DISCOUNT_TYPE__CHECKBOX)).isSelected();
		if (!flag) {
			element = webDB.driver.findElement(By.xpath(AutomationLocators.DISCOUNT_TYPE));
			js.executeScript("arguments[0].click();", element);
		}

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(EmailTemplatesLocators.FIXED_DISCOUNT__RADIO)));
		actions.click(webDB.driver.findElement(By.xpath(EmailTemplatesLocators.FIXED_DISCOUNT__RADIO))).build()
				.perform();
		webDB.driver.findElement(By.xpath(AutomationLocators.COUPON_CODE))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(AutomationLocators.COUPON_CODE, couponCode, DriverBase.ElementType.Xpath);
		webDB.clickAnElement(AutomationLocators.SAVE, DriverBase.ElementType.Xpath);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP)));
		flag = webDB.isElementDisplayed(CommonLocators.ACTION_SUCCESS_POPUP, DriverBase.ElementType.Xpath);
		log.info("Coupon Code has been added To Email Template");
		return flag;
	}

	public static boolean verifyOrderShipmentTrackingUrlConfiguration() throws IOException, InterruptedException {
		webDB.waitForElement(EmailTemplatesLocators.TEMPLATES_TAB, DriverBase.ElementType.Id);
		Thread.sleep(2000);// wait to grab proepr selection value
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ORDER_SHIPMENT_ENABLE_CHECK)).isSelected();
		if (!flag) {
			enableShipmentEmailTemplate();
			wait.until(ExpectedConditions
					.invisibilityOf(webDB.driver.findElement(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP))));
		}
		webDB.driver.findElement(By.xpath(AutomationLocators.SETTINGS_ODR_SHIPMENT_TEMPLATE)).click();
		Thread.sleep(3000);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.TRACKING_URL_FROM_ORDER_DATA)).isSelected();
		if (!flag) {
			element = webDB.driver.findElement(By.xpath(EmailTemplatesLocators.TRACKING_FROM_ORDER_DATA_SELECT));
			js.executeScript("arguments[0].click();", element);
		} else if (flag) {
			element = webDB.driver.findElement(By.xpath(EmailTemplatesLocators.THANK_YOU_PAGE_SELECT));
			js.executeScript("arguments[0].click();", element);
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationLocators.SAVE)));
		webDB.clickAnElement(AutomationLocators.SAVE, DriverBase.ElementType.Xpath);
		flag = webDB.isElementDisplayed(CommonLocators.ACTION_SUCCESS_POPUP, DriverBase.ElementType.Xpath);
		return flag;
	}

	public static boolean verifyInvalidCountryCodeOnCodConfirmationPage() throws IOException, InterruptedException {
		String countryCodeErrorText = "A valid country code must be given";
		Thread.sleep(2000);
		webDB.waitForElement(EmailTemplatesLocators.TEMPLATES_TAB, DriverBase.ElementType.Id);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.COD_ENABLE_CHECK)).isSelected();
		if (!flag) {
			enableAllEmailTemplatesAndCheckSettings();
			wait.until(ExpectedConditions
					.invisibilityOf(webDB.driver.findElement(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP))));
		}
		webDB.driver.findElement(By.xpath(AutomationLocators.SETTINGS_COD_TEMPLATE)).click();
		Thread.sleep(3000);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.CALL_CHECKBOX_COD_SETTINGS)).isSelected();
		if (!flag) {
			webDB.clickAnElement(AutomationLocators.CALL_CHECKBOX_CLICKABLE, DriverBase.ElementType.Xpath);
		}
//		webDB.driver.findElement(By.id(TemplatesLocators.CALL_COUNTRY_CODE_FIELD))
//				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		actions.moveToElement(webDB.driver.findElement(By.xpath(AutomationLocators.CALL_COUNTRY_CODE_FIELD))).build()
				.perform();
		webDB.selectDropDownOptions(AutomationLocators.CALL_COUNTRY_CODE_FIELD, WidgetLocators.INDIA_CODE,
				ElementType.Xpath);
//		flag = webDB.isElementDisplayed(TemplatesLocators.INVALID_COUNTRY_CODE_ERROR_TEXT,
//				DriverBase.ElementType.Xpath);
//		Thread.sleep(2000);
//		if (flag) {
		webDB.clickAnElement(AutomationLocators.SAVE, DriverBase.ElementType.Xpath);
		Thread.sleep(500);
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
		webDB.waitForElement(EmailTemplatesLocators.TEMPLATES_TAB, DriverBase.ElementType.Id);
		Thread.sleep(2000);// wait to grab proepr selection value
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.COD_ENABLE_CHECK)).isSelected();
		if (!flag) {
			enableCODEmailTemplate();
			wait.until(ExpectedConditions
					.invisibilityOf(webDB.driver.findElement(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP))));
		}
		webDB.clickAnElement(AutomationLocators.SETTINGS_COD_TEMPLATE, DriverBase.ElementType.Xpath);
		Thread.sleep(3000);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.CALL_CHECKBOX_COD_SETTINGS)).isSelected();
		if (!flag) {
			webDB.clickAnElement(AutomationLocators.CALL_CHECKBOX_CLICKABLE, DriverBase.ElementType.Xpath);
		}
//		webDB.driver.findElement(By.xpath(TemplatesLocators.CALL_COUNTRY_CODE_FIELD))
//				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		actions.moveToElement(webDB.driver.findElement(By.xpath(AutomationLocators.CALL_COUNTRY_CODE_FIELD))).build()
				.perform();
		webDB.selectDropDownOptions(AutomationLocators.CALL_COUNTRY_CODE_FIELD, WidgetLocators.INDIA_CODE,
				ElementType.Xpath);
		webDB.driver.findElement(By.xpath(AutomationLocators.CALL_PHONE_FIELD))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(AutomationLocators.CALL_PHONE_FIELD, "error", DriverBase.ElementType.Xpath);
		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath(AutomationLocators.INVALID_PHONE_NUMBER_ERROR_TEXT)));
		flag = webDB.isElementDisplayed(AutomationLocators.INVALID_PHONE_NUMBER_ERROR_TEXT,
				DriverBase.ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AutomationLocators.SAVE, DriverBase.ElementType.Xpath);
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
		webDB.waitForElement(EmailTemplatesLocators.TEMPLATES_TAB, DriverBase.ElementType.Id);
		Thread.sleep(2000);// wait to grab proepr selection value
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.COD_ENABLE_CHECK)).isSelected();
		if (!flag) {
			enableCODEmailTemplate();
			wait.until(ExpectedConditions
					.invisibilityOf(webDB.driver.findElement(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP))));
		}
		webDB.clickAnElement(AutomationLocators.SETTINGS_COD_TEMPLATE, DriverBase.ElementType.Xpath);
		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath(AutomationLocators.CALL_CHECKBOX_COD_SETTINGS)));
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.CALL_CHECKBOX_COD_SETTINGS)).isSelected();
		if (!flag) {
			webDB.clickAnElement(AutomationLocators.CALL_CHECKBOX_CLICKABLE, DriverBase.ElementType.Xpath);
		}
//		webDB.driver.findElement(By.id(TemplatesLocators.CALL_COUNTRY_CODE_FIELD))
//				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		actions.moveToElement(webDB.driver.findElement(By.xpath(AutomationLocators.CALL_COUNTRY_CODE_FIELD))).build()
				.perform();
		webDB.selectDropDownOptions(AutomationLocators.CALL_COUNTRY_CODE_FIELD, WidgetLocators.INDIA_CODE,
				ElementType.Xpath);
		webDB.driver.findElement(By.xpath(AutomationLocators.CALL_PHONE_FIELD))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(AutomationLocators.CALL_PHONE_FIELD, "7854125421", DriverBase.ElementType.Xpath);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.EMAIL_CHECKBOX_COD_SETTINGS)).isSelected();
		if (!flag) {
			webDB.clickAnElement(AutomationLocators.EMAIL_CHECKBOX_CLICKABLE, DriverBase.ElementType.Xpath);
		}
		webDB.driver.findElement(By.xpath(AutomationLocators.EMAIL_FIELD))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(AutomationLocators.EMAIL_FIELD, "qatest@gmail", DriverBase.ElementType.Xpath);
		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath(AutomationLocators.INVALID_EMAIL_ERROR_TEXT)));
		flag = webDB.isElementDisplayed(AutomationLocators.INVALID_EMAIL_ERROR_TEXT, DriverBase.ElementType.Xpath);

		if (flag) {
			webDB.clickAnElement(AutomationLocators.SAVE, DriverBase.ElementType.Xpath);
			log.info("Clicked on Save button");
			wait.until(
					ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(CommonLocators.ERROR_POPUP_ON_SAVE)));
			flag = webDB.driver.findElement(
					By.xpath("//*[@class='Polaris-Frame-Toast Polaris-Frame-Toast--error'][contains(text(),'"
							+ emailErrorText + "')]"))
					.isDisplayed();
		}
		webDB.driver.findElement(By.xpath("//span[contains(text(),'Cancel')]")).click();
		return flag;
	}

	public static boolean verifyCallAndEmailOptionSelectionCODSettings() throws IOException, InterruptedException {
		String phone = "78" + RandomStringUtils.randomNumeric(8);
		webDB.waitForElement(EmailTemplatesLocators.TEMPLATES_TAB, DriverBase.ElementType.Id);
		Thread.sleep(2000);// wait to grab proper selection value
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.COD_ENABLE_CHECK)).isSelected();
		if (!flag) {
			enableCODEmailTemplate();
			wait.until(ExpectedConditions
					.invisibilityOf(webDB.driver.findElement(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP))));
		}
		webDB.clickAnElement(AutomationLocators.SETTINGS_COD_TEMPLATE, DriverBase.ElementType.Xpath);
		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath(AutomationLocators.CALL_CHECKBOX_COD_SETTINGS)));
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.CALL_CHECKBOX_COD_SETTINGS)).isSelected();
		if (!flag) {
			webDB.clickAnElement(AutomationLocators.CALL_CHECKBOX_CLICKABLE, DriverBase.ElementType.Xpath);
		}
//		webDB.driver.findElement(By.id(TemplatesLocators.CALL_COUNTRY_CODE_FIELD))
//				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Thread.sleep(2000);
		webDB.scrollToAnElement(AutomationLocators.CALL_COUNTRY_CODE_FIELD, ElementType.Xpath);
		webDB.selectDropDownOptions(AutomationLocators.CALL_COUNTRY_CODE_FIELD, WidgetLocators.INDIA_CODE,
				ElementType.Xpath);
		webDB.driver.findElement(By.xpath(AutomationLocators.CALL_PHONE_FIELD))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(AutomationLocators.CALL_PHONE_FIELD, phone, DriverBase.ElementType.Xpath);
		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath(AutomationLocators.EMAIL_CHECKBOX_COD_SETTINGS)));
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.EMAIL_CHECKBOX_COD_SETTINGS)).isSelected();
		if (!flag) {
			webDB.clickAnElement(AutomationLocators.EMAIL_CHECKBOX_CLICKABLE, DriverBase.ElementType.Xpath);
		}
		webDB.driver.findElement(By.xpath(AutomationLocators.EMAIL_FIELD))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(AutomationLocators.EMAIL_FIELD, "qaautomationuser@gupshup.io",
				DriverBase.ElementType.Xpath);
		flag = webDB.isElementDisplayed(CommonLocators.ACTION_SUCCESS_POPUP, DriverBase.ElementType.Xpath);
		log.info("Saved Successfully");
		if (flag) {

			webDB.clickAnElement(AutomationLocators.SAVE, DriverBase.ElementType.Xpath);
			wait.until(
					ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP)));
			flag = webDB.driver
					.findElement(By.xpath("//*[@class='Polaris-Frame-Toast'][contains(text(),'Data Saved')]"))
					.isDisplayed();
		}
		log.info("Call and Email Details updated Successfully");
		return flag;
	}

	public static boolean updateCODTags() throws IOException, InterruptedException {
		webDB.waitForElement(EmailTemplatesLocators.TEMPLATES_TAB, DriverBase.ElementType.Xpath);
		Thread.sleep(3000);// wait to grab proepr selection value
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.COD_ENABLE_CHECK)).isSelected();
		if (!flag) {
			enableCODEmailTemplate();
			wait.until(ExpectedConditions
					.invisibilityOf(webDB.driver.findElement(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP))));
		}
		webDB.clickAnElement(AutomationLocators.SETTINGS_COD_TEMPLATE, DriverBase.ElementType.Xpath);
		Thread.sleep(1500);
		webDB.driver.findElement(By.xpath(AutomationLocators.ORDER_CONFIRMATION_TAG_FIELD))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(AutomationLocators.ORDER_CONFIRMATION_TAG_FIELD,
				"CODConfirmed-SuperLemon" + RandomStringUtils.random(1), DriverBase.ElementType.Xpath);
		Thread.sleep(2000);
		webDB.driver.findElement(By.xpath(AutomationLocators.ORDER_CANCELLATION_TAG_FIELD))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Thread.sleep(2000);
		webDB.sendTextToAnElement(AutomationLocators.ORDER_CANCELLATION_TAG_FIELD,
				"CODCancelled-SuperLemon" + RandomStringUtils.random(1), DriverBase.ElementType.Xpath);
		webDB.driver.findElement(By.xpath(AutomationLocators.ORDER_NO_RESPONSE_TAG_FIELD))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Thread.sleep(3000);
		webDB.sendTextToAnElement(AutomationLocators.ORDER_NO_RESPONSE_TAG_FIELD,
				"CODNoResponse-SuperLemon" + RandomStringUtils.random(1), DriverBase.ElementType.Xpath);
		webDB.clickAnElement(AutomationLocators.SAVE, DriverBase.ElementType.Xpath);
		return flag;
	}

	public static boolean selectUnselectCancellationAndNoResponseCheckBox() throws IOException, InterruptedException {
		webDB.waitForElement(AutomationLocators.COD_ENABLE_DISABLE_ICON, DriverBase.ElementType.Xpath);
		Thread.sleep(2000);// wait to grab proepr selection value
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.COD_ENABLE_CHECK)).isSelected();
		if (!flag) {
			enableCODEmailTemplate();
			wait.until(ExpectedConditions
					.invisibilityOf(webDB.driver.findElement(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP))));
		}
		webDB.clickAnElement(AutomationLocators.SETTINGS_COD_TEMPLATE, DriverBase.ElementType.Xpath);
		Thread.sleep(2000);
		List<WebElement> checkBoxes = webDB.driver.findElements(By.xpath(CommonLocators.CHECKBOX_SELECTION_CHECK));
		List<WebElement> checkBoxClickable = webDB.driver.findElements(By.xpath(CommonLocators.CHECKBOX_COMMON));
		for (int i = 0; i < checkBoxes.size() - 2; i++) {
			checkBoxClickable.get(i).click();
		}

		webDB.waitForElement("//span[contains(text(),'Call')]", ElementType.Xpath);
		category = new Select(webDB.driver.findElement(By.xpath(AutomationLocators.CALL_COUNTRY_CODE_FIELD)));
		category.selectByVisibleText("🇮🇳 (IN) +91");
		String phone = "7854" + RandomStringUtils.randomNumeric(6);
		webDB.sendTextToAnElement(AutomationLocators.PHONE_FIELD, phone, ElementType.Xpath);
		Thread.sleep(1000);
		webDB.clickAnElement(AutomationLocators.SAVE, DriverBase.ElementType.Xpath);
		webDB.waitForElement(AutomationLocators.ACTION_SUCCESS_POPUP, ElementType.Xpath);
//		wait.until(
//				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(TemplatesLocators.ACTION_SUCCESS_POPUP)));
		flag = webDB.driver.findElement(By.xpath("//*[@class='Polaris-Frame-Toast'][contains(text(),'Data Saved')]"))
				.isDisplayed();
		return flag;
	}

	public static boolean enableTemplate() throws InterruptedException {
		List<WebElement> els = webDB.driver.findElements(By.xpath(AutomationLocators.ENABLE_DISABLE_ICON_GENERIC));
		log.info(els.size());
		for (int i = 1; i < els.size(); i++) {
			els.get(0).click();
		}
		Thread.sleep(2500);
		flag = webDB.isElementDisplayed(AutomationLocators.ERROR_POPUP, DriverBase.ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath(AutomationLocators.ERROR_POPUP)).getText();
		if (flag) {
			if (text.equalsIgnoreCase("Template has been enabled")) {
				flag = true;
				log.info("Template has been enabled");
			}
			Thread.sleep(2000);
		}
		return flag;
	}

	public static boolean disableTemplate() throws InterruptedException {
		List<WebElement> els = webDB.driver.findElements(By.xpath(AutomationLocators.ENABLE_DISABLE_ICON_GENERIC));
		log.info(els.size());
		for (int i = 1; i < els.size(); i++) {
			els.get(0).click();
		}
		Thread.sleep(2000);
		flag = webDB.isElementDisplayed(AutomationLocators.ERROR_POPUP, DriverBase.ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath(AutomationLocators.ERROR_POPUP)).getText();
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
		webDB.waitForElement(AutomationLocators.CREATE_TEMPLATES, DriverBase.ElementType.Xpath);
		log.info("Create templates Button s Displayed");
		webDB.clickAnElement(AutomationLocators.CREATE_TEMPLATES, DriverBase.ElementType.Xpath);
		log.info("Clicked on Create Templates Button");
		flag = webDB.isElementDisplayed(AutomationLocators.TEMPLATE_NAME_FIELD, DriverBase.ElementType.Id);
		if (flag) {
			webDB.sendTextToAnElement(AutomationLocators.TEMPLATE_DESC_FIELD, TemplateDesc, DriverBase.ElementType.Id);
			category = new Select(webDB.driver.findElement(By.id(AutomationLocators.CATEGORY_DROPDOWN)));
			category.selectByVisibleText("Order Template");
			webDB.clickAnElement(AutomationLocators.SUBMIT, DriverBase.ElementType.Xpath);
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath(AutomationLocators.TEMPLATE_EDIT_SUCCESS)));
			text = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATE_EDIT_SUCCESS)).getText();
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath(AutomationLocators.TEMPLATE_EDIT_SUCCESS)));
			flag = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATE_EDIT_SUCCESS)).isDisplayed();
		}
		return flag;
	}

	public static boolean VerifyDuplicateTemplateName() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationLocators.EXTENSION_TEMPLATES)));
		element = webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES));
		actions.moveToElement(element).click().build().perform();
		templateName = AutomationPage.createExtensiontemplate();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(AutomationLocators.CREATE_TEMPLATES)));
		webDB.clickAnElement(AutomationLocators.CREATE_TEMPLATES, DriverBase.ElementType.Xpath);
		wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(AutomationLocators.TEMPLATE_NAME_TXTBOX)));
		flag = webDB.isElementDisplayed(AutomationLocators.TEMPLATE_NAME_TXTBOX, DriverBase.ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(AutomationLocators.TEMPLATE_NAME_TXTBOX, templateName,
					DriverBase.ElementType.Xpath);
			webDB.sendTextToAnElement(AutomationLocators.TEMPLATE_DESC_TXTBOX, TemplateDesc,
					DriverBase.ElementType.Xpath);
			category = new Select(webDB.driver.findElement(By.xpath(AutomationLocators.CATEGORY_DD)));
			category.selectByVisibleText("Quick Reply");
			webDB.clickAnElement(AutomationLocators.SUBMIT, DriverBase.ElementType.Xpath);
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath(AutomationLocators.TEMPLATE_EDIT_SUCCESS)));
			text = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATE_EDIT_SUCCESS)).getText();
			flag = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATE_EDIT_SUCCESS)).isDisplayed();
		}
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(AutomationLocators.GO_BACK_BTN)));
		webDB.waitForElement(AutomationLocators.GO_BACK_BTN, DriverBase.ElementType.Xpath);
		webDB.clickAnElement(AutomationLocators.GO_BACK_BTN, DriverBase.ElementType.Xpath);
		flag = AutomationPage.deleteExtensionTemplate(templateName);
		return flag;
	}

	public static boolean VerifyPagePlaceholders() throws InterruptedException {
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
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AutomationLocators.EXTENSION_TEMPLATES)));
		element = webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES));
		actions.moveToElement(element).click().build().perform();
		// CLick on create template
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationLocators.CREATE_TEMPLATES)));
		webDB.clickAnElement(AutomationLocators.CREATE_TEMPLATES, DriverBase.ElementType.Xpath);
		flag = webDB.isElementDisplayed(AutomationLocators.TEMPLATE_NAME_FIELD, DriverBase.ElementType.Id);
		if (flag) {
			// Enter template name
			webDB.sendTextToAnElement(AutomationLocators.TEMPLATE_NAME_FIELD, VerifyTemplateName,
					DriverBase.ElementType.Id);
			// Enter template description
			webDB.sendTextToAnElement(AutomationLocators.TEMPLATE_DESC_FIELD, TemplateDesc, DriverBase.ElementType.Id);
			// Select Category
			category = new Select(webDB.driver.findElement(By.id(AutomationLocators.CATEGORY_DROPDOWN)));
			category.selectByVisibleText("Quick Reply");
			// Click on Discard button
			webDB.clickAnElement(AutomationLocators.TEMPLATE_DISCARD_BTN, DriverBase.ElementType.Xpath);

		}
		// Verify Empty fields
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationLocators.TEMPLATE_NAME_ERROR)));
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATE_NAME_ERROR)).isDisplayed();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationLocators.TEMPLATE_DESC_ERROR)));
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.TEMPLATE_DESC_ERROR)).isDisplayed();
		return flag;
	}

	public static boolean VerifyGOBackBtnOnCreateTemplate() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AutomationLocators.EXTENSION_TEMPLATES)));
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationLocators.EXTENSION_TEMPLATES)));
		element = webDB.driver.findElement(By.xpath(AutomationLocators.EXTENSION_TEMPLATES));
		actions.moveToElement(element).click().build().perform();
		// CLick on create template
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationLocators.CREATE_TEMPLATES)));
		webDB.clickAnElement(AutomationLocators.CREATE_TEMPLATES, DriverBase.ElementType.Xpath);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(AutomationLocators.TEMPLATE_NAME_FIELD)));
		flag = webDB.isElementDisplayed(AutomationLocators.TEMPLATE_NAME_FIELD, DriverBase.ElementType.Id);
		if (flag) {
			// Click on GOback Btn
			webDB.clickAnElement(AutomationLocators.GO_BACK_BTN, DriverBase.ElementType.Xpath);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationLocators.CREATE_TEMPLATES)));
			// verify page
			flag = webDB.driver.findElement(By.xpath(AutomationLocators.CREATE_TEMPLATES)).isDisplayed();
			log.info("Successfully navigated Back");
		}
		return flag;
	}

	public static boolean VerifyCancelSettingButton() throws InterruptedException, IOException {
		Thread.sleep(2000);// wait to grab selection value of icon
		flag = webDB.waitForElement(AutomationLocators.ABCART_ENABLE_DISABLE_ICON, DriverBase.ElementType.Xpath);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ABCART_ENABLE_CHECK)).isSelected();
		if (!flag) {
			flag = enableABCartEmailTemplate();
			wait.until(ExpectedConditions
					.invisibilityOf(webDB.driver.findElement(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP))));

		}
		webDB.clickAnElement(AutomationLocators.SETTINGS_ODR_SHIPMENT_TEMPLATE, DriverBase.ElementType.Xpath);
		Thread.sleep(1500);
		webDB.waitForElement(AutomationLocators.CANCEL_BTN, DriverBase.ElementType.Xpath);
		flag = webDB.clickAnElement(AutomationLocators.CANCEL_BTN, DriverBase.ElementType.Xpath);
		log.info("Click on cancel button");
		Thread.sleep(1500);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ABCART_ENABLE_DISABLE_ICON)).isDisplayed();
		return flag;
	}

	public static boolean VerifyCloseSettingPopup() throws InterruptedException, IOException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(EmailTemplatesLocators.TEMPLATES_TAB)));
		Thread.sleep(2000);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ABCART_ENABLE_CHECK)).isSelected();
		if (!flag) {
			flag = enableABCartEmailTemplate();
			wait.until(ExpectedConditions
					.invisibilityOf(webDB.driver.findElement(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP))));

		}
		webDB.clickAnElement(AutomationLocators.SETTINGS_ABCART_TEMPLATE, DriverBase.ElementType.Xpath);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationLocators.MODAL_CLOSE_BTN)));
		webDB.waitForElement(AutomationLocators.MODAL_CLOSE_BTN, DriverBase.ElementType.Xpath);
		flag = webDB.clickAnElement(AutomationLocators.MODAL_CLOSE_BTN, DriverBase.ElementType.Xpath);
		if (flag) {
			log.info("Click on close button");
		}
		webDB.waitForElement(AutomationLocators.ABCART_ENABLE_DISABLE_ICON, DriverBase.ElementType.Xpath);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ABCART_ENABLE_DISABLE_ICON)).isDisplayed();
		log.info("Settings Pop up Closed and Templates Primary Page is Accessed");
		return flag;
	}

	public static boolean VerifyPricingChart() throws InterruptedException, IOException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AutomationLocators.PRICING_HYPERLINK)));
		webDB.clickAnElement(AutomationLocators.PRICING_HYPERLINK, DriverBase.ElementType.Xpath);
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
		flag = webDB.waitForElement(AutomationLocators.ODR_SHIPMENT__ENABLE_DISABLE_ICON, DriverBase.ElementType.Xpath);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ORDER_SHIPMENT_ENABLE_CHECK)).isSelected();
		if (!flag) {
			flag = enableShipmentEmailTemplate();
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
		flag = webDB.waitForElement(AutomationLocators.COD_ENABLE_DISABLE_ICON, DriverBase.ElementType.Xpath);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.COD_ENABLE_CHECK)).isSelected();
		if (!flag) {
			flag = enableCODEmailTemplate();
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
		flag = HeaderText.headertextcheck("Templates");
		return flag;
	}

	public static boolean verifyDiscountTextInclusionInAbCartEmailTemplate() throws IOException, InterruptedException {
		discountText = "Get {{discountPercent}}% off by";
		flag = addDiscountToABCartTemplate();
		if (flag) {
			elements = webDB.driver.findElements(By.xpath(EmailTemplatesLocators.EMAIL_BODY_CONTAINER));
			for (int i = 0; i < 1; i++) {
				log.info(elements.get(i).getText());
				log.info(discountText);
				flag = elements.get(i).getText().contains(discountText);
				log.info("Discount text is applied to the ABCART template");
			}
		}
		return flag;
	}

	public static boolean verifyDiscountTextInclusionInOrderEmailTemplate() throws IOException, InterruptedException {
		discountText = "Get {{discountPercent}}% off";
		flag = addDiscountTypeToOrderConfirmationEmailTemplate();
		if (flag) {
			elements = webDB.driver.findElements(By.xpath(EmailTemplatesLocators.EMAIL_BODY_CONTAINER));
			for (int i = 1; i < 2; i++) {
				log.info(elements.get(i).getText());
				log.info(discountText);
				flag = elements.get(i).getText().contains(discountText);
				log.info("Discount text is applied to the Order template");
			}
		}
		return flag;
	}

	public static boolean verifyDiscountTextExclusionInABCARTEmailTemplate() throws IOException, InterruptedException {
		Thread.sleep(3000);
		flag = webDB.waitForElement(AutomationLocators.ABCART_ENABLE_CHECK, DriverBase.ElementType.Xpath);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.ABCART_ENABLE_CHECK)).isSelected();
		if (!flag) {
			flag = enableABCartEmailTemplate();
			wait.until(ExpectedConditions
					.invisibilityOf(webDB.driver.findElement(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP))));

		}
		webDB.clickAnElement(AutomationLocators.SETTINGS_ABCART_TEMPLATE, DriverBase.ElementType.Xpath);
		webDB.waitForElement(AutomationLocators.DISCOUNT_TYPE__CHECKBOX, DriverBase.ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.driver.findElement(By.xpath(AutomationLocators.DISCOUNT_TYPE__CHECKBOX)).isSelected();
		if (flag) {
			element = webDB.driver.findElement(By.xpath(AutomationLocators.DISCOUNT_TYPE));
			js.executeScript("arguments[0].click();", element);
		}
		webDB.clickAnElement(AutomationLocators.SAVE, DriverBase.ElementType.Xpath);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP)));
		flag = webDB.isElementDisplayed(CommonLocators.ACTION_SUCCESS_POPUP, DriverBase.ElementType.Xpath);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP)));
		if (flag) {
			elements = webDB.driver.findElements(By.xpath(EmailTemplatesLocators.EMAIL_BODY_CONTAINER));
			for (int i = 0; i < 1; i++) {
				flag = elements.get(i).getText().contains(discountText);
				log.info("Discount text is excluded from the ABCART template");
			}
		}
		return flag;
	}

	public static boolean verifyDiscountTextExclusionInOrderEmailTemplate() throws IOException, InterruptedException {
		discountText = "Get {{discountPercent}}% off";
		Thread.sleep(3000);
		flag = webDB.waitForElement(AutomationLocators.ORDER_CRM_ENABLE_CHECK, DriverBase.ElementType.Xpath);
		if (flag) {
			flag = webDB.driver.findElement(By.xpath(AutomationLocators.ORDER_CRM_ENABLE_CHECK)).isSelected();
			if (!flag) {
				flag = enableABCartEmailTemplate();
				wait.until(ExpectedConditions
						.invisibilityOf(webDB.driver.findElement(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP))));

			}
			Thread.sleep(1000);
			webDB.clickAnElement(AutomationLocators.SETTINGS_ODR_CONFIRMATION_TEMPLATE, DriverBase.ElementType.Xpath);
			webDB.waitForElement(AutomationLocators.DISCOUNT_TYPE__CHECKBOX, DriverBase.ElementType.Xpath);
			Thread.sleep(1000);
			flag = webDB.driver.findElement(By.xpath(AutomationLocators.DISCOUNT_TYPE__CHECKBOX)).isSelected();
			if (flag) {
				System.out.println("Already selected");
			} else {
				Thread.sleep(1000);
				element = webDB.driver.findElement(By.xpath(AutomationLocators.DISCOUNT_TYPE));
				js.executeScript("arguments[0].click();", element);
				flag = webDB.driver.findElement(By.xpath(AutomationLocators.DISCOUNT_TYPE__CHECKBOX)).isSelected();
			}
			Thread.sleep(3000);
			webDB.clickAnElement(AutomationLocators.SAVE, DriverBase.ElementType.Xpath);
			flag = webDB.isElementDisplayed(CommonLocators.ACTION_SUCCESS_POPUP, DriverBase.ElementType.Xpath);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP)));
			Thread.sleep(3000);
			if (flag) {
				elements = webDB.driver.findElements(By.xpath(EmailTemplatesLocators.EMAIL_BODY_CONTAINER));
				for (int i = 1; i < 2; i++) {
					flag = elements.get(i).getText().contains(discountText);
					log.info(discountText);
					log.info(elements.get(i).getText());
					log.info("Discount text is excluded from the Order template");
				}
			}
		}
		return flag;
	}
}