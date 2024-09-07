
package commonFunctions;

import java.io.File;

/**
 * @author Sumit
 * @company Gupshup October 18, 2021
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;

import locators.CommonLocators;
import locators.OrderCRMLocators;
import utils.DriverBase;
import utils.DriverBase.ElementType;

@SuppressWarnings("static-access")
public class OrderCRMPage {

	private static boolean flag;
	private static String propertyFilePath;
	public static Properties prop = new Properties();
	private static DriverBase webDB = new DriverBase();;
	private static String text;
	private static CommonFunctions HeaderText = new CommonFunctions();

	/**
	 * verify enable/disable Delivery automated messaging
	 * 
	 * @return
	 * 
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 */

	public static boolean verifyEnableDisableOrderConfirmationAutomatedMessaging()
			throws FileNotFoundException, InterruptedException, IOException {
		webDB.waitForElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
		Thread.sleep(3000);
		text = webDB.driver.findElement(By.xpath(CommonLocators.ENABLE_DISABLE_BTN)).getText();
		if (text.equalsIgnoreCase("Enable")) {
			webDB.clickAnElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
			Thread.sleep(2000);
			propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
			prop.load(new FileInputStream(propertyFilePath));
			String orderConfirmationTemplateMessaginEnabled = prop.getProperty("OrderCrmConfirmationEnabledMessage");
			System.out.println(orderConfirmationTemplateMessaginEnabled);
			flag = webDB.isElementDisplayed(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG, ElementType.Xpath);
			if (flag) {
				text = webDB.driver.findElement(By.xpath(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG)).getText();
				if (text.equalsIgnoreCase(orderConfirmationTemplateMessaginEnabled)) {
					flag = true;
					System.out.println("Order Confirmation Template Enabled and Enabled Message is displayed");
				}
			}

		} else if (text.equalsIgnoreCase("Disable")) {
			webDB.waitForElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
			webDB.clickAnElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
			Thread.sleep(2000);
			propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
			prop.load(new FileInputStream(propertyFilePath));
			String orderConfirmationTemplateMessagingDisableMessage = prop
					.getProperty("OrderCrmConfirmationDisabledMessage");
			System.out.println(orderConfirmationTemplateMessagingDisableMessage);
			flag = webDB.isElementDisplayed(CommonLocators.TEMPLATE_MSGNG_DISABLED_MSG, ElementType.Xpath);
			if (flag) {
				text = webDB.driver.findElement(By.xpath(CommonLocators.TEMPLATE_MSGNG_DISABLED_MSG)).getText();
				if (text.equalsIgnoreCase(orderConfirmationTemplateMessagingDisableMessage)) {
					flag = true;
					System.out.println("Order Confirmation Template Disabled and  Disable Message is  displayed");
				}
			}

		}
		return flag;
	}

	/**
	 * verify enable/disable Delivery automated messaging
	 * 
	 * @return
	 * 
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 */
	public static boolean verifyEnableDisableShipmentAutomatedMessaging()
			throws FileNotFoundException, InterruptedException, IOException {
		webDB.waitForElement(OrderCRMLocators.ENABLE_DISABLE_SHIPMENT_MESSAGING_BTN, ElementType.Xpath);
		Thread.sleep(3000);
		text = webDB.driver.findElement(By.xpath(OrderCRMLocators.ENABLE_DISABLE_SHIPMENT_MESSAGING_BTN)).getText();
		if (text.equalsIgnoreCase("Enable")) {
			webDB.clickAnElement(OrderCRMLocators.ENABLE_DISABLE_SHIPMENT_MESSAGING_BTN, ElementType.Xpath);
			Thread.sleep(2000);
			propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
			prop.load(new FileInputStream(propertyFilePath));
			String deliveryTemplateMessaginEnabledMessage = prop.getProperty("ShipmentTemplateMessagingEnabledMessage");
			System.out.println(deliveryTemplateMessaginEnabledMessage);
			flag = webDB.isElementDisplayed(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG, ElementType.Xpath);
			if (flag) {
				text = webDB.driver.findElement(By.xpath(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG)).getText();
				if (text.equalsIgnoreCase(deliveryTemplateMessaginEnabledMessage)) {
					flag = true;
					System.out.println("Delivery Template Enabled and Enabled Message is displayed");
				}
			}
		}

		else if (text.equalsIgnoreCase("Disable")) {
			webDB.waitForElement(OrderCRMLocators.ENABLE_DISABLE_SHIPMENT_MESSAGING_BTN, ElementType.Xpath);
			webDB.clickAnElement(OrderCRMLocators.ENABLE_DISABLE_SHIPMENT_MESSAGING_BTN, ElementType.Xpath);
			Thread.sleep(1000);
			propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
			prop.load(new FileInputStream(propertyFilePath));
			String ShipmentTemplateMessagingDisabledMessage = prop
					.getProperty("ShipmentTemplateMessagingDisabledMessage");
			System.out.println(ShipmentTemplateMessagingDisabledMessage);
			flag = webDB.isElementDisplayed(CommonLocators.TEMPLATE_MSGNG_DISABLED_MSG, ElementType.Xpath);
			if (flag) {
				text = webDB.driver.findElement(By.xpath(CommonLocators.TEMPLATE_MSGNG_DISABLED_MSG)).getText();
				if (text.equalsIgnoreCase(ShipmentTemplateMessagingDisabledMessage)) {
					flag = true;
					System.out.println("Delivery Template Disabled and  Disable Message is  displayed");
				}
			}

		}
		return flag;
	}

	public static boolean verifyContactRatingfeature() throws InterruptedException {
		Thread.sleep(2000);
		flag = HeaderText.headertextcheck("Automated Orders CRM");
		return flag;
	}
}
