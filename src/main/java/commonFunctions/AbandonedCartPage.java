package commonFunctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;

import locators.CommonLocators;
import locators.OrderCRMLocators;
import utils.DriverBase;
import utils.DriverBase.ElementType;

/**
 * @author Sumit
 * @company Gupshup October 18, 2021
 */
@SuppressWarnings("static-access")
public class AbandonedCartPage {
	private static boolean flag;
	private static String propertyFilePath;
	public static Properties prop = new Properties();
	private static DriverBase webDB = new DriverBase();;
	private static String text;
	private static CommonFunctions HeaderText = new CommonFunctions();

	/**
	 * verify enable/disable ABCART automated messaging
	 * 
	 * @return
	 * 
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 */
	public static boolean verifyEnableAbcartAutomatedMessaging()
			throws FileNotFoundException, InterruptedException, IOException {
		webDB.waitForElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
		Thread.sleep(3000);
		text = webDB.driver.findElement(By.xpath(OrderCRMLocators.ENABLE_DISABLE_SHIPMENT_MESSAGING_BTN)).getText();
		if (text.equalsIgnoreCase("Enable")) {
			webDB.clickAnElement(OrderCRMLocators.ENABLE_DISABLE_SHIPMENT_MESSAGING_BTN, ElementType.Xpath);
			Thread.sleep(2000);
			propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
			prop.load(new FileInputStream(propertyFilePath));
			String AbcartMessagingEnabledMessage = prop.getProperty("ABcartMessagingEnabledMessage");
			flag = webDB.isElementDisplayed(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG, ElementType.Xpath);
			if (flag) {
				text = webDB.driver.findElement(By.xpath(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG)).getText();
				if (text.equalsIgnoreCase(AbcartMessagingEnabledMessage)) {
					flag = true;
					System.out.println("Abandoned Cart Enabled and Enabled Message is displayed");
				}
			}

		} else if (text.equalsIgnoreCase("Disable")) {
			webDB.waitForElement(OrderCRMLocators.ENABLE_DISABLE_SHIPMENT_MESSAGING_BTN, ElementType.Xpath);
			webDB.clickAnElement(OrderCRMLocators.ENABLE_DISABLE_SHIPMENT_MESSAGING_BTN, ElementType.Xpath);
			Thread.sleep(2000);
			propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
			prop.load(new FileInputStream(propertyFilePath));
			String AbcartMessagingDisabledMessage = prop.getProperty("ABcartMessagingDisabledMessage");
			flag = webDB.isElementDisplayed(CommonLocators.TEMPLATE_MSGNG_DISABLED_MSG, ElementType.Xpath);
			if (flag) {
				text = webDB.driver.findElement(By.xpath(CommonLocators.TEMPLATE_MSGNG_DISABLED_MSG)).getText();
				if (text.equalsIgnoreCase(AbcartMessagingDisabledMessage)) {
					flag = true;
					System.out.println("Abandoned Cart Dusabled and  Disable Message is  displayed");
				}
			}

		}
		return flag;
	}

	public static boolean verifyContactRatingfeature() throws InterruptedException {
		Thread.sleep(2000);
		flag = HeaderText.headertextcheck("Automated Abandoned Cart Recovery");
		return flag;
	}
}
