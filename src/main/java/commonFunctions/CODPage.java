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
import utils.DriverBase;
import utils.DriverBase.ElementType;

@SuppressWarnings("static-access")
public class CODPage {
	private static boolean flag;
	private static String propertyFilePath;
	public static Properties prop = new Properties();
	private static DriverBase webDB = new DriverBase();;
	private static String text;
	private static CommonFunctions HeaderText = new CommonFunctions();

	/**
	 * verify enable/disable COD automated messaging
	 * 
	 * @return
	 * 
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 */

	public static boolean verifyEnableDisableCODAutomatedMessaging()
			throws FileNotFoundException, InterruptedException, IOException {
		webDB.waitForElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
		Thread.sleep(3000);
		text = webDB.driver.findElement(By.xpath(CommonLocators.ENABLE_DISABLE_BTN)).getText();
		if (text.equalsIgnoreCase("Enable")) {
			webDB.clickAnElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
			Thread.sleep(2000);
			propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
			prop.load(new FileInputStream(propertyFilePath));
			String codTemplateMessagingEnableMessage = prop.getProperty("CODTemplateMessagingEnableMessage");
			System.out.println(codTemplateMessagingEnableMessage);
			flag = webDB.isElementDisplayed(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG, ElementType.Xpath);
			if (flag) {
				text = webDB.driver.findElement(By.xpath(CommonLocators.TEMPLATE_MSGNG_ENABLED_MSG)).getText();
				if (text.equalsIgnoreCase(codTemplateMessagingEnableMessage)) {
					flag = true;
					System.out.println("COD Order Confirmation Template Enabled and Enabled Message is displayed");
				}
			}

		} else if (text.equalsIgnoreCase("Disable")) {
			webDB.waitForElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
			webDB.clickAnElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
			Thread.sleep(2000);
			propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
			prop.load(new FileInputStream(propertyFilePath));
			String codTemplateMessagingDisableMessage = prop.getProperty("CODTemplateMessagingDisableMessage");
			System.out.println(codTemplateMessagingDisableMessage);
			flag = webDB.isElementDisplayed(CommonLocators.TEMPLATE_MSGNG_DISABLED_MSG, ElementType.Xpath);
			if (flag) {
				text = webDB.driver.findElement(By.xpath(CommonLocators.TEMPLATE_MSGNG_DISABLED_MSG)).getText();
				if (text.equalsIgnoreCase(codTemplateMessagingDisableMessage)) {
					flag = true;
					System.out.println(" COD Order Confirmation Template Disabled and  Disable Message is  displayed");
				}
			}

		}
		return flag;
	}

	public static boolean verifyContactRatingfeature() throws InterruptedException {
		Thread.sleep(2000);
		flag = HeaderText.headertextcheck("Automated COD Order Confirmation");
		return flag;
	}
}
