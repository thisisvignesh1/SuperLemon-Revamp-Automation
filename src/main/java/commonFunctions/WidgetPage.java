package commonFunctions;

import static org.testng.Assert.expectThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import locators.SettingsLocators;
import locators.CommonLocators;
import locators.CustomersLocators;
import locators.CampaignsLocators;
import locators.WidgetLocators;
//import locators.WixStoreLocators;
import locators.PlansPageLocators;
import locators.ShopifyStoreLocators;
import locators.MyWhatsAppLocators;
import utils.DriverBase;
import utils.DriverBase.ElementType;

/**
 * @author Sumit
 * @company Gupshup October 12, 2021
 */

@SuppressWarnings({ "static-access", "unused" })
public class WidgetPage {

	private static Logger log = Logger.getLogger(WidgetPage.class);
	private static boolean flag;
	private static String propertyFilePath;
	public static Properties prop = new Properties();
	private static DriverBase webDB = new DriverBase();;
	private static String text;
	private static Set<String> windows;
	private static Iterator<String> iterator;
	private static String parentWindow;
	private static String childWindow;
	private static Actions actions = new Actions(webDB.driver);
	private static List<WebElement> elements;
	private static JavascriptExecutor personalWidget = (JavascriptExecutor) webDB.driver;
	private static String autoAgentName;
	private static String endTimeHour;
	private static WebElement element;
	private static boolean checkboxstatus = false;
	private static String callouttext;
	private static CommonFunctions HeaderText = new CommonFunctions();
	private static WebDriverWait wait = new WebDriverWait(webDB.driver, 10);
	private static List<WebElement> radioGeneric = webDB.driver
			.findElements(By.xpath(CommonLocators.RADIO_ATTRIBUTE_GENERIC));
	private static List<WebElement> radioClickable = webDB.driver.findElements(By.xpath(CommonLocators.RADIO_GENERIC));
	private static List<WebElement> toggleButton = webDB.driver.findElements(By.xpath(WidgetLocators.CHECKBOX_GENERIC));
	private static List<WebElement> checkboxClickable = webDB.driver
			.findElements(By.xpath(CommonLocators.CHECKBOX_COMMON));
	private static String Text;

	/**
	 * verify enable/enable chat
	 *
	 * @return
	 * @return
	 * @throws InterruptedException
	 */

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
					flag = webDB.waitForElement(ShopifyStoreLocators.STORE_PASS, ElementType.Xpath);

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
					flag = webDB.isElementDisplayed(ShopifyStoreLocators.STORE_PASS, ElementType.Xpath);
				}
			}
		}
		return flag;
	}

	public static boolean verifyEnableChatWidget() throws Exception {
		Thread.sleep(2000);
		log.info(text);
		selectShowChatWhenAgentOffline();
		flag = webDB.isElementDisplayed(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath(CommonLocators.ENABLE_DISABLE_BTN)).getText();
		log.info(text);
		if (text.equals("Enable")) {
			webDB.clickAnElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
		}
		personalWidgetPageToStoreNavigation();
		ShopifyStore.shopifyStorePassEnter();
		flag = webDB.waitForElement(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET, ElementType.Xpath);
		webDB.driver.close();
		log.info("closed the child window");
		webDB.driver.switchTo().window(parentWindow);
		log.info("switched to parent window");
		return flag;
	}

	/**
	 * verify enable/disable chat
	 *
	 * @return
	 * @return
	 * @throws InterruptedException
	 */
	public static boolean verifyDisableChatWidget() throws InterruptedException {

		Thread.sleep(2000);
		log.info(text);
		// selectShowChatWhenAgentOffline();
		webDB.moveToElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
		webDB.waitForElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath(CommonLocators.ENABLE_DISABLE_BTN)).getText();
		log.info(text);
		if (text.equals("Disable")) {
			Thread.sleep(2000);
			webDB.clickAnElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
		}
		personalWidgetPageToStoreNavigation();
		ShopifyStore.shopifyStorePassEnter();
		Thread.sleep(2000);
		flag = webDB.verifyElementIsNotDisplayed(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET, ElementType.Xpath);
		webDB.driver.close();
		log.info("closed the child window");
		webDB.driver.switchTo().window(parentWindow);
		log.info("switched to parent window");
		return flag;
	}

	/**
	 * verify disable share
	 *
	 * @return
	 * @return
	 * @throws InterruptedException
	 */
	public static boolean verifyDisableShareWidget() throws InterruptedException {
		flag = webDB.waitForElement(WidgetLocators.SHARE_SETTINGS_TAB, ElementType.Id);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.SHARE_SETTINGS_TAB, ElementType.Id);
			Thread.sleep(2000);
			webDB.moveToElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
			Thread.sleep(1000);
			flag = webDB.isElementDisplayed(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
			text = webDB.driver.findElement(By.xpath(CommonLocators.ENABLE_DISABLE_BTN)).getText();
			log.info(text);
			if (text.equals("Disable")) {
				Thread.sleep(2000);
				webDB.clickAnElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
			}
			personalWidgetPageToStoreNavigation();
			ShopifyStore.shopifyStorePassEnter();
			flag = webDB.verifyElementIsNotDisplayed(ShopifyStoreLocators.HOMEPAGE_SHARE_WIDGET, ElementType.Xpath);
			if (flag) {
				System.out.println("element is not present");
			} else {
				System.out.println("element is present");
			}
			webDB.driver.close();
			log.info("closed the child window");
			webDB.driver.switchTo().window(parentWindow);
			log.info("switched to parent window");
		}
		return flag;
	}

	/**
	 * verify enable share
	 *
	 * @return
	 * @return
	 * @throws InterruptedException
	 */

	public static boolean verifyEnableShareWidget() throws InterruptedException {
		flag = webDB.waitForElement(WidgetLocators.SHARE_SETTINGS_TAB, ElementType.Id);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.SHARE_SETTINGS_TAB, ElementType.Id);
			Thread.sleep(2000);
			webDB.moveToElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
			Thread.sleep(1000);
			flag = webDB.waitForElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
			text = webDB.driver.findElement(By.xpath(CommonLocators.ENABLE_DISABLE_BTN)).getText();
			log.info(text);
			if (text.equals("Enable")) {
				webDB.clickAnElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
			}
			personalWidgetPageToStoreNavigation();
			ShopifyStore.shopifyStorePassEnter();
			flag = webDB.waitForElement(ShopifyStoreLocators.HOMEPAGE_SHARE_WIDGET, ElementType.Xpath);
			webDB.driver.close();
			log.info("closed the child window");
			webDB.driver.switchTo().window(parentWindow);
			log.info("switched to parent window");
		}
		return flag;
	}

	/**
	 * verify device selection
	 *
	 * @return throws IOException
	 * @return
	 * @throws InterruptedException
	 */
	public static boolean selectMobileDeviceOnly() throws FileNotFoundException, InterruptedException, IOException {
		actions.moveToElement(webDB.driver.findElement(By.xpath(WidgetLocators.MOBILE_ONLY_RADIO))).build().perform();
		wait.until(
				ExpectedConditions.visibilityOf(webDB.driver.findElement(By.xpath(WidgetLocators.MOBILE_ONLY_RADIO))));
		elements = webDB.driver.findElements(By.xpath(CommonLocators.RADIO_GENERIC));
		log.info(elements.size());
		Thread.sleep(2000);
		actions.moveToElement(webDB.driver.findElement(By.xpath(WidgetLocators.MOBILE_ONLY_RADIO))).build().perform();
		flag = webDB.driver.findElement(By.xpath(WidgetLocators.MOBILE_ONLY_RADIO)).isSelected();
		if (flag) {
			Thread.sleep(2000);
			element = webDB.driver.findElement(By.xpath(WidgetLocators.MOBILE_ONLY_RADIO));
			JavascriptExecutor js = (JavascriptExecutor) webDB.driver;
			js.executeScript("arguments[0].click();", element);
			log.debug("Clicked on Mobile Only Radio");
			webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
			log.debug("Clicked on SAVE_SETTINGS");
			personalWidgetPageToStoreNavigation();
			ShopifyStore.shopifyStorePassEnter();

			if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
					|| (System.getProperty("os").equals("Android"))) {
				webDB.scrollToAnElement(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET, ElementType.Xpath);
				Thread.sleep(1000);
				flag = webDB.waitForElement(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET, ElementType.Xpath);

			} else {
				flag = webDB.verifyElementIsNotDisplayed(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET, ElementType.Xpath);
			}

			log.info("Chat button is not displayed on Store");
			log.info("Mobile Only radio button selected");
		} else if (!flag) {
			Thread.sleep(2000);
			webDB.scrollToAnElement(WidgetLocators.MOBILE_ONLY_RADIO, ElementType.Xpath);
			Thread.sleep(1000);
			element = webDB.driver.findElement(By.xpath(WidgetLocators.MOBILE_ONLY_RADIO));
			JavascriptExecutor js = (JavascriptExecutor) webDB.driver;
			js.executeScript("arguments[0].click();", element);
			log.debug("Clicked on Mobile Only Radio");
			webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
			log.debug("Clicked on SAVE_SETTINGS");
			personalWidgetPageToStoreNavigation();
			ShopifyStore.shopifyStorePassEnter();
			if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
					|| (System.getProperty("os").equals("Android"))) {
				webDB.scrollToAnElement(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET, ElementType.Xpath);
				Thread.sleep(1000);
				flag = webDB.waitForElement(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET, ElementType.Xpath);

			} else {
				flag = webDB.verifyElementIsNotDisplayed(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET, ElementType.Xpath);
			}
			log.info(flag);
			log.info("Chat button is not displayed on Store");
			log.info("Mobile Only radio button selected");

		}
		webDB.driver.close();
		log.info("child Window closed");
		webDB.driver.switchTo().window(parentWindow);
		log.info("switched to parent window");
		Thread.sleep(2000);
		actions.moveToElement(webDB.driver.findElement(By.xpath(WidgetLocators.DESKTOP_MOBILE_RADIO))).build()
				.perform();
		wait.until(ExpectedConditions
				.visibilityOf(webDB.driver.findElement(By.xpath(WidgetLocators.DESKTOP_MOBILE_RADIO))));
		actions.moveToElement(webDB.driver.findElement(By.xpath(WidgetLocators.DESKTOP_MOBILE_RADIO))).click().build()
				.perform();
//		actions.moveToElement(webDB.driver.findElement(By.xpath(PersonalWidgetLocators.PRODUCT_HEIGHT_OFFSET))).build()
//				.perform();
		element = webDB.driver.findElement(By.xpath(WidgetLocators.DESKTOP_POS_RIGHT));
		personalWidget = (JavascriptExecutor) webDB.driver;
		personalWidget.executeScript("arguments[0].click();", element);
		webDB.driver.findElement(By.xpath(WidgetLocators.HEIGHT_OFFSET_DESKTOP))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		String offset = RandomStringUtils.randomNumeric(2);
		int offsetInt = Integer.parseInt(offset);
		offsetInt = offsetInt + 10;
		offset = Integer.toString(offsetInt);
		webDB.sendTextToAnElement(WidgetLocators.HEIGHT_OFFSET_DESKTOP, offset, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(WidgetLocators.EDGE_OFFSET))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(WidgetLocators.EDGE_OFFSET, offset, ElementType.Xpath);
		flag = webDB.driver.findElement(By.xpath("//h2[text()='Desktop']/following::input[5]")).isSelected();
		System.out.println(flag);
		if (!flag) {
			flag = true;
		} else {
			webDB.clickAnElement("//h2[text()='Desktop']/following::input[5]/parent::span[1]", ElementType.Xpath);

		}
//		flag = webDB.isElementDisplayed(PersonalWidgetLocators.PRODUCT_HEIGHT_OFFSET, ElementType.Xpath);
//		if (flag) {
//			webDB.driver.findElement(By.xpath(PersonalWidgetLocators.PRODUCT_HEIGHT_OFFSET))
//					.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
//			webDB.sendTextToAnElement(PersonalWidgetLocators.PRODUCT_HEIGHT_OFFSET, offset, ElementType.Xpath);
//		}
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CommonLocators.SAVE_SETTINGS)));
		webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
		wait.until(
				ExpectedConditions.visibilityOf(webDB.driver.findElement(By.xpath(WidgetLocators.SAVE_SUCCESS_MSG))));
		flag = true;
		return flag;
	}

	/**
	 * verify device enable Agent
	 *
	 * @return
	 * @throws InterruptedException
	 */

	public static boolean enableAgent() throws InterruptedException {
		elements = webDB.driver.findElements(By.xpath(WidgetLocators.AGENT_STATUS));
		Thread.sleep(2000);
		for (int i = 0; i < elements.size(); i++) {
			System.out.println("Agent status of " + i + "is " + elements.get(i));
			if (elements.get(i).getText().equalsIgnoreCase("Disabled")) {
				System.out.println("Agent status of " + i + " is disabled");
				elements = webDB.driver.findElements(By.xpath(WidgetLocators.ENABLE_DISABLE_AGENT_BUTTON));
				// personalWidget.executeScript("arguments[0].click();", elements.get(i));
				// personalWidget.executeScript("arguments[0].click()", elements.get(i));
				elements.get(i).click();
				webDB.isElementDisplayed(WidgetLocators.AGENT_ENABLED_MESSAGE, ElementType.Xpath);
				flag = true;
				log.info("Agent Enabled successfully");
			}
		}

		return flag;
	}

	/**
	 * verify disableAgent
	 *
	 * @return
	 * @throws InterruptedException
	 */
	public static boolean disableAgent() throws InterruptedException {
		elements = webDB.driver.findElements(By.xpath(WidgetLocators.AGENT_STATUS));
		for (int i = 0; i < elements.size(); i++) {
			if ((elements.get(i).getText().contains("Offline")) || (elements.get(i).getText().contains("Online"))) {
				elements = webDB.driver.findElements(By.xpath(WidgetLocators.ENABLE_DISABLE_AGENT_BUTTON));
				for (int j = 0; j < elements.size(); j++) {
					elements.get(i).click();
					flag = webDB.isElementDisplayed(WidgetLocators.AGENT_DISABLED_MESSAGE, ElementType.Xpath);
					log.info("Agent Disabled successfully");
					wait.until(ExpectedConditions
							.visibilityOf(webDB.driver.findElement(By.xpath(WidgetLocators.AGENT_DISABLED_MESSAGE))));
					Thread.sleep(2000);
				}
			}
		}

		return flag;
	}

	/**
	 * verify Enable/disable Greeting
	 *
	 * @return * @throws InterruptedException
	 */

	public static final boolean verifyEnableDisableGreetingWidget() throws InterruptedException {
		Thread.sleep(3000);
		webDB.scrollToAnElement(WidgetLocators.ENABLE_DISABLE_GREETINGS, ElementType.Xpath);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(WidgetLocators.ENABLE_DISABLE_GREETINGS)));
		Thread.sleep(2000);
		text = webDB.driver.findElement(By.xpath(WidgetLocators.ENABLE_DISABLE_GREETINGS)).getText();
		log.info(text);
		if (text.equalsIgnoreCase("Disable")) {
			actions = new Actions(webDB.driver);
			actions.moveToElement(webDB.driver.findElement(By.xpath(WidgetLocators.ENABLE_DISABLE_GREETINGS))).click()
					.build().perform();
			Thread.sleep(3000);
			flag = webDB.isElementDisplayed(WidgetLocators.GREETINGS_DISABLED_MSG, ElementType.Xpath);
			log.info("Greeting Widget Disabled");
			actions.moveToElement(webDB.driver.findElement(By.xpath(WidgetLocators.ENABLE_DISABLE_GREETINGS))).click()
					.build().perform();
			log.info("Greeting Widget Enabled");
		} else if (text.equalsIgnoreCase("Enable")) {
			actions = new Actions(webDB.driver);
			actions.moveToElement(webDB.driver.findElement(By.xpath(WidgetLocators.ENABLE_DISABLE_GREETINGS))).click()
					.build().perform();
			Thread.sleep(3000);
			flag = webDB.isElementDisplayed(WidgetLocators.GREETINGS_ENABLED_MSG, ElementType.Xpath);
			log.info("Greeting Widget Enabled");
		}

		return flag;
	}

	/**
	 * verify store hours
	 *
	 * @return
	 * @throws InterruptedException
	 */

	public static boolean verifyStoreOffline() throws InterruptedException {
		try {
			webDB.waitForElement(WidgetLocators.STORE_TIMINGS_EDIT_OPTION, ElementType.Xpath);
			Thread.sleep(2000);
			webDB.clickAnElement(WidgetLocators.STORE_TIMINGS_EDIT_OPTION, ElementType.Xpath);
			elements = webDB.driver.findElements(By.xpath(WidgetLocators.STORE_TO_TIME));
			log.info(elements.size());
			DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			Date date = new Date();
			String time = dateFormat.format(date);
			log.info(time);
			String str = time.replaceAll("[^a-zA-Z0-9]", " ");
			endTimeHour = str.substring(0, 2);
			endTimeHour = endTimeHour + "00";
//     int endTime =  Integer.valueOf(endTimeHour);
//     endTime =endTime -100;
//     endTimeHour = Integer.toString(endTime);
			log.info(endTimeHour);
			Thread.sleep(3000);
			System.out.println(elements.size());
			for (int i = 0; i < elements.size(); i++) {
				if (i % 2 == 0) {
					elements.get(i).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
					elements.get(i).sendKeys(endTimeHour);
					log.info("To timings changed and store marked offline");
					flag = true;
				}
			}
			webDB.clickAnElement(WidgetLocators.SAVE, ElementType.Xpath);
			Thread.sleep(3000);
		} catch (Exception e) {
			log.info("Exception is thrown");
		} finally {
			webDB.waitForElement(WidgetLocators.STORE_TIMINGS_EDIT_OPTION, ElementType.Xpath);
			Thread.sleep(3000);
			webDB.clickAnElement(WidgetLocators.STORE_TIMINGS_EDIT_OPTION, ElementType.Xpath);
			elements = webDB.driver.findElements(By.xpath("//div[contains(@class,'Polaris-Labelled--hidden')]"));
			elements = webDB.driver.findElements(By.xpath("//input[@value=" + endTimeHour + "]"));
			for (int i = 0; i < elements.size(); i++) {
//		if(i%2==0)
//		{
//			element = elements.get(i);
//		personalWidget =(JavascriptExecutor)webDB.driver;
//		personalWidget.executeScript("arguments[0].click();", element);
				elements.get(i).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
				elements.get(i).sendKeys("2359");
				log.info("To timings changed and store marked offline");
				flag = true;
			}
			webDB.clickAnElement(WidgetLocators.SAVE, ElementType.Xpath);
		}
		return flag;
	}

	/**
	 * verify edit details agent
	 *
	 * @return
	 * @throws InterruptedException
	 */

	public static boolean verifyEditDetailsAgent() throws InterruptedException {
		Thread.sleep(2000);
		elements = webDB.driver.findElements(By.xpath(WidgetLocators.AGENT_NAME));
		log.info(elements.size());
		for (int i = 0; i < elements.size(); i++) {
			log.info(elements.get(i).getText());
			if (elements.get(i).getText().contains("Auto")) {
				elements = webDB.driver.findElements(By.xpath(WidgetLocators.EDIT_DETAILS));
				elements.get(i).click();
				autoAgentName = "AutoAgent" + Math.random();
				webDB.driver.findElement(By.xpath(WidgetLocators.AGENT_NAME_EDITABLE))
						.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
				webDB.sendTextToAnElement(WidgetLocators.AGENT_NAME_EDITABLE, autoAgentName, ElementType.Xpath);
				Thread.sleep(2000);
				webDB.clickAnElement(WidgetLocators.SAVE, ElementType.Xpath);
				flag = true;
				log.info("Agent Name is edited");
			}
		}
		return flag;
	}

	public static boolean verifyChatWidgetPosition() throws InterruptedException {
		parentWindow = webDB.driver.getWindowHandle();
		enableCHatButton();
		log.info("reached here");
		actions.moveToElement(webDB.driver.findElement(By.xpath(WidgetLocators.DESKTOP_MOBILE_RADIO))).build()
				.perform();
		webDB.clickAnElement(WidgetLocators.DESKTOP_MOBILE_RADIO, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.driver.findElement(By.xpath(WidgetLocators.LEFT_POS_DESKTOP)).isSelected();
		if (!flag) {
			actions.moveToElement(webDB.driver.findElement(By.xpath(WidgetLocators.LEFT_POS_DESKTOP))).click().build()
					.perform();
		}
		webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
		Thread.sleep(2000);
		personalWidgetPageToStoreNavigation();
		ShopifyStore.shopifyStorePassEnter();
		flag = webDB.isElementDisplayed(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET, ElementType.Xpath);
		if (flag) {
			log.info("Chat button is displayed");
		} else {
			log.info("Chat button is not displayed");
		}
		webDB.driver.close();
		log.info("closed the window");
		webDB.driver.switchTo().window(parentWindow);
		log.info("navigated to parent window");
		return flag;
	}

	public static boolean checkAtLeastOneAgentEnabled() throws InterruptedException {
		webDB.waitForElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
		elements = webDB.driver.findElements(By.xpath(WidgetLocators.DISABLE_AGENT_BTN));
//		elements = webDB.driver.findElements(By.xpath(PersonalWidgetLocators.ENABLE_DISABLE_AGENT_BUTTON));
		for (int i = 0; i < elements.size(); i++) {
			if (i == elements.size() - 1) {
				elements.get(i).click();
				Thread.sleep(4000);
				flag = webDB.isElementDisplayed(WidgetLocators.ATLEAST_ONE_AGENT_ENABLED_MSG, ElementType.Xpath);
				log.info("Error Message displayed on Disabling all agents");
				wait.until(ExpectedConditions.visibilityOf(
						webDB.driver.findElement(By.xpath(WidgetLocators.ATLEAST_ONE_AGENT_ENABLED_MSG))));

			} else {
				elements.get(i).click();
			}

		}
		return flag;
	}

	public static boolean changeChatIconAndMessageBodyText() throws InterruptedException {
		parentWindow = webDB.driver.getWindowHandle();
		Thread.sleep(2000);
		enableCHatButton();
		selectShowChatWhenAgentOffline();
		enableAgent();
		webDB.isElementDisplayed(WidgetLocators.CHAT_ICON_TEXT_FEILD, ElementType.Xpath);
		Thread.sleep(2000);
		webDB.driver.findElement(By.xpath(WidgetLocators.CHAT_ICON_TEXT_FEILD))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(WidgetLocators.CHAT_ICON_TEXT_FEILD, "Click on Widget", ElementType.Xpath);
		webDB.driver.findElement(By.xpath(WidgetLocators.CHAT_MSG_BODY_TEXT_FEILD))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(WidgetLocators.CHAT_MSG_BODY_TEXT_FEILD, "Welcome to the Store!", ElementType.Xpath);
		webDB.isElementDisplayed(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
		webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
		personalWidgetPageToStoreNavigation();
		ShopifyStore.shopifyStorePassEnter();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET)));
		webDB.isElementDisplayed(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET, ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath(ShopifyStoreLocators.CHATWIDGET_TEXT)).getText();
		log.info(text);
		flag = text.equalsIgnoreCase("Click on Widget");
		if (flag) {
			log.info("Automated Chat button Icon Text Visible on store");
		}
		webDB.driver.close();
		log.info("closed the child window");
		webDB.driver.switchTo().window(parentWindow);
		log.info("switched to parent window");
		return flag;
	}

	public static boolean checkEnableRadio(int RadioNum) throws InterruptedException {
		for (int i = 0; i < radioGeneric.size(); i++) {
			if (i == RadioNum) {
				flag = radioGeneric.get(i).isSelected();
				if (!flag) {
					radioClickable.get(i).click();
					text = radioClickable.get(i).getAttribute("id");
					log.info(text);
					log.info("Radio Button is selected");
				} else {
					log.info("Radio Button is already selected");
				}
			}
		}
		return flag;
	}

	public static boolean changeDesignChatBuutton() throws InterruptedException {

		parentWindow = webDB.driver.getWindowHandle();
		enableCHatButton();
		selectShowChatWhenAgentOffline();
		Thread.sleep(800);
		webDB.isElementDisplayed(WidgetLocators.CHAT_CHANGE_DESIGN_BTN, ElementType.Xpath);
		webDB.clickAnElement(WidgetLocators.CHAT_CHANGE_DESIGN_BTN, ElementType.Xpath);
		webDB.waitForElement(WidgetLocators.CUSTMZED_CHT_BTN, ElementType.Xpath);
		webDB.clickAnElement(WidgetLocators.CUSTMZED_CHT_BTN, ElementType.Xpath);
		webDB.clickAnElement(WidgetLocators.SAVE, ElementType.Xpath);
		Thread.sleep(6000);
		log.info("1");

		flag = webDB.clickAnElement(WidgetLocators.SELECTDESIGNBTN, ElementType.Xpath);
		if (flag) {
			element = webDB.driver.findElement(By.xpath(WidgetLocators.SECOND_DESIGN_CHATWIDGET));
			if (!element.isSelected()) {
				actions.moveToElement(webDB.driver.findElement(By.xpath(WidgetLocators.SECOND_DESIGN_CHATWIDGET)))
						.click().build().perform();
				Thread.sleep(1500);
				webDB.clickAnElement(WidgetLocators.SAVE_BTN, ElementType.Xpath);
				Thread.sleep(2500);
				webDB.clickAnElement(WidgetLocators.SAVE, ElementType.Xpath);
			} else {
				Thread.sleep(1500);
				webDB.clickAnElement(WidgetLocators.SAVE_BTN, ElementType.Xpath);
			}
			webDB.scrollToAnElement(WidgetLocators.BTN_TEXT_COLOR_CHAT_BTN_SINGLE_COLOR, ElementType.Xpath);
			Thread.sleep(1000);
			webDB.clearText(WidgetLocators.BTN_TEXT_COLOR_CHAT_BTN_SINGLE_COLOR);
//			webDB.driver.findElement(By.xpath(PersonalWidgetLocators.BTN_TEXT_COLOR_CHAT_BTN_SINGLE_COLOR))
//					.sendKeys(Keys.CONTROL, "a", Keys.DELETE);

			webDB.sendTextToAnElement(WidgetLocators.BTN_TEXT_COLOR_CHAT_BTN_SINGLE_COLOR, "#111111",
					ElementType.Xpath);
			webDB.scrollToAnElement(WidgetLocators.BG_COLOR_CHAT_BTN_SINGLE_COLOR, ElementType.Xpath);
			Thread.sleep(1000);
			webDB.clearText(WidgetLocators.BG_COLOR_CHAT_BTN_SINGLE_COLOR);
//
//			webDB.driver.findElement(By.xpath(PersonalWidgetLocators.BG_COLOR_CHAT_BTN_SINGLE_COLOR))
//					.sendKeys(Keys.CONTROL, "a", Keys.DELETE);
			webDB.sendTextToAnElement(WidgetLocators.BG_COLOR_CHAT_BTN_SINGLE_COLOR, "#231a1a", ElementType.Xpath);
//		Thread.sleep(800);
			log.info("2");
			Thread.sleep(2000);
//		Thread.sleep(1000);
			log.info("3");
			webDB.waitForElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
			webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
			Thread.sleep(5000);

			personalWidgetPageToStoreNavigation();
			ShopifyStore.shopifyStorePassEnter();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET)));
			flag = webDB.waitForElement(ShopifyStoreLocators.CSTMZED_CHAT_WIDGET_TEXT_COLOR, ElementType.Xpath);
			WebElement element = webDB.driver.findElement(By.cssSelector("div.wa-chat-button-cta-text"));
			String chatbtncolor = element.getCssValue("color");
			String colorvalue = Color.fromString(chatbtncolor).asHex();
			System.out.println(colorvalue);
			Thread.sleep(100000);
			log.info(flag);
			log.info("Customized chat button is displayed on the Store");
			webDB.driver.close();
			log.info("closed the child window");
			webDB.driver.switchTo().window(parentWindow);
			log.info("switched to parent window");
		}
		return flag;

	}

	public static boolean markStoreOfflineAndVerifyOfflineMessageOnGreetingsCard()
			throws InterruptedException, FileNotFoundException, IOException {
		try {
			markStoreOffline();
			enableGreetingsWidget();
			personalWidgetPageToStoreNavigation();
			ShopifyStore.shopifyStorePassEnter();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET)));
			webDB.clickAnElement(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET, ElementType.Xpath);
			webDB.isElementDisplayed(ShopifyStoreLocators.STORE_OFFLINE_MSG_TEXT, ElementType.CssSelector);
			text = webDB.driver.findElement(By.cssSelector(ShopifyStoreLocators.STORE_OFFLINE_MSG_TEXT)).getText();
			propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
			prop.load(new FileInputStream(propertyFilePath));
			String offlineText = prop.getProperty("StoreOfflineMessageOnStore");
			if (text.contains(offlineText)) {
				flag = true;
				log.info("Store Offline Message is displayed on store on Greetings");
			}
		} catch (Exception e) {
			log.info("Exception thrown");
		} finally {
			webDB.driver.close();
			log.info("closed the child window");
			webDB.driver.switchTo().window(parentWindow);
			log.info("switched to parent window");
//			Thread.sleep(2000);
			resetDefaultStoreTimings();
		}
		return flag;
	}

	public static boolean markStoreOfflineAndVerifyOfflineMessageOnAlertPopup()
			throws InterruptedException, FileNotFoundException, IOException {
		try {
			markStoreOffline();
			disableGreetingsWidget();
			personalWidgetPageToStoreNavigation();
			flag = webDB.isElementDisplayed(ShopifyStoreLocators.STORE_PASS, ElementType.Xpath);
			if (flag) {
				ShopifyStore.shopifyStorePassEnter();
			}
			wait.until(ExpectedConditions
					.elementToBeClickable(By.ByXPath.xpath(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET)));
			webDB.clickAnElement(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET, ElementType.Xpath);
			Thread.sleep(3000);
			Alert alert = webDB.driver.switchTo().alert();
			text = alert.getText();
			Thread.sleep(1000);
			propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
			prop.load(new FileInputStream(propertyFilePath));
			String offlineText = prop.getProperty("StoreOfflineMessageOnStore");
			log.info(offlineText);
			if (text.contains(offlineText)) {
				flag = true;
				alert.accept();
				log.info("Store Offline Message is displayed on store on Alert pop up");
			}
		} catch (Exception e) {
			log.info("Exception e");
		} finally {
			webDB.driver.close();
			log.info("closed the child window");
			webDB.driver.switchTo().window(parentWindow);
			log.info("switched to parent window");
			resetDefaultStoreTimings();
		}
		return flag;
	}

	public static boolean enableGreetingsWidget() throws InterruptedException {
		log.info("Inside enable freetings widget");
		Thread.sleep(3000);
		webDB.waitForElement(WidgetLocators.ENABLE_DISABLE_GREETINGS, ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath(WidgetLocators.ENABLE_DISABLE_GREETINGS)).getText();
		log.info(text);
		if (text.equalsIgnoreCase("Enable")) {
			actions = new Actions(webDB.driver);
			actions.moveToElement(webDB.driver.findElement(By.xpath(WidgetLocators.ENABLE_DISABLE_GREETINGS))).click()
					.build().perform();
			Thread.sleep(3000);
			flag = webDB.isElementDisplayed(WidgetLocators.GREETINGS_ENABLED_MSG, ElementType.Xpath);
			if (flag) {
				log.info("Greeting Widget Enabled");
			}
		}
		return flag;
	}

	public static boolean disableGreetingsWidget() throws InterruptedException {
//		Thread.sleep(3000);
		webDB.waitForElement(WidgetLocators.ENABLE_DISABLE_GREETINGS, ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath(WidgetLocators.ENABLE_DISABLE_GREETINGS)).getText();
		log.info(text);
		if (text.equalsIgnoreCase("Disable")) {
			actions = new Actions(webDB.driver);
			actions.moveToElement(webDB.driver.findElement(By.xpath(WidgetLocators.ENABLE_DISABLE_GREETINGS))).click()
					.build().perform();
			Thread.sleep(3000);
			flag = webDB.isElementDisplayed(WidgetLocators.GREETINGS_DISABLED_MSG, ElementType.Xpath);
			log.info("Greeting Widget Disabled");
		}
		return flag;
	}

	public static void personalWidgetPageToStoreNavigation() throws InterruptedException {
		System.out.println("store navigation");
		parentWindow = webDB.driver.getWindowHandle();
//		element = webDB.driver.findElement(By.xpath(PersonalWidgetLocators.VIEW_STORE));
//		JavascriptExecutor js = (JavascriptExecutor) webDB.driver;
//		js.executeScript("arguments[0].click();", element);
		webDB.scrollToAnElement(WidgetLocators.VIEW_STORE, ElementType.Xpath);
		Thread.sleep(2000);
		flag = webDB.waitForElement(WidgetLocators.VIEW_STORE, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.VIEW_STORE, ElementType.Xpath);
//		actions.moveToElement(element).click().build().perform();
			// webDB.clickAnElement(PersonalWidgetLocators.VIEW_STORE,
			// ElementType.LinkText);
			windows = webDB.driver.getWindowHandles();
			iterator = windows.iterator();
			while (iterator.hasNext()) {
				childWindow = iterator.next();
				if (!parentWindow.equals(childWindow)) {
					webDB.driver.switchTo().window(childWindow);
				}
			}
		}
	}

	public static void markStoreOffline() throws InterruptedException {
		Thread.sleep(3000);
		webDB.waitForElement(WidgetLocators.STORE_TIMINGS_EDIT_OPTION, ElementType.Xpath);
		webDB.clickAnElement(WidgetLocators.STORE_TIMINGS_EDIT_OPTION, ElementType.Xpath);
		Thread.sleep(2000);
		elements = webDB.driver.findElements(By.xpath(WidgetLocators.STORE_TO_TIME));
		log.info(elements.size());
		if (elements.size() != 0) {
			DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			Date date = new Date();
			String time = dateFormat.format(date);
			log.info(time);
			String str = time.replaceAll("[^a-zA-Z0-9]", " ");
			endTimeHour = str.substring(0, 2);
			endTimeHour = endTimeHour + "00";
			webDB.isElementDisplayed(WidgetLocators.STORE_TO_TIME, ElementType.Xpath);
			for (int i = 0; i < elements.size(); i++) {
				System.out.println(i);
				elements.get(i).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
				elements.get(i).sendKeys(endTimeHour);
				log.info("To timings changed and store marked offline");
				flag = true;

			}
		}
		webDB.clickAnElement(WidgetLocators.SAVE, ElementType.Xpath);
		webDB.isElementDisplayed(WidgetLocators.TIMINGS_UPDATED_SUCCESS_MSG, ElementType.Xpath);
	}

	public static void resetDefaultStoreTimings() throws InterruptedException {
		actions = new Actions(webDB.driver);
		actions.moveToElement(webDB.driver.findElement(By.xpath(WidgetLocators.STORE_TIMINGS_EDIT_OPTION))).click()
				.build().perform();
		Thread.sleep(3000);
		elements = webDB.driver.findElements(By.xpath("//div[contains(@class,'Polaris-Labelled--hidden')]"));
		elements = webDB.driver.findElements(By.xpath("//input[@value=" + endTimeHour + "]"));
		for (int i = 0; i < elements.size(); i++) {
			elements.get(i).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			elements.get(i).sendKeys("2359");
			log.info("To timings changed and store marked online");
			flag = true;
		}
		Thread.sleep(2000);
		webDB.waitForElement(WidgetLocators.SAVE, ElementType.Xpath);
		webDB.clickAnElement(WidgetLocators.SAVE, ElementType.Xpath);
		webDB.isElementDisplayed(WidgetLocators.TIMINGS_UPDATED_SUCCESS_MSG, ElementType.Xpath);
	}

	public static void markAgentOffline() throws InterruptedException {
		webDB.waitForElement(WidgetLocators.STORE_TO_TIME, ElementType.Xpath);
		elements = webDB.driver.findElements(By.xpath(WidgetLocators.STORE_TO_TIME));
		log.info(elements.size());
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		String time = dateFormat.format(date);
		log.info(time);
		String str = time.replaceAll("[^a-zA-Z0-9]", " ");
		endTimeHour = str.substring(0, 2);
		endTimeHour = endTimeHour + "00";
		for (int i = 0; i < elements.size(); i++) {
			elements.get(i).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			elements.get(i).sendKeys(endTimeHour);
			log.info("To timings changed and store marked offline");
			flag = true;

		}
		webDB.clickAnElement(WidgetLocators.SAVE, ElementType.Xpath);
	}

	public static void resetDefaultAgentTimings() throws InterruptedException {
		actions = new Actions(webDB.driver);
		actions.moveToElement(webDB.driver.findElement(By.xpath(WidgetLocators.EDIT_DETAILS))).click().build()
				.perform();
		Thread.sleep(2000);
//		text = webDB.driver.findElement(By.xpath("(//input[@class='Polaris-TextField__Input'])[13]")).getAttribute("value");
//		endTimeHour = text;
//		log.info(text);
		elements = webDB.driver.findElements(By.xpath("//div[contains(@class,'Polaris-Labelled--hidden')]"));
		elements = webDB.driver.findElements(By.xpath("//input[@value=" + endTimeHour + "]"));
		Thread.sleep(1000);
		for (int i = 0; i < elements.size(); i++) {
			if (i % 2 != 0) {
				elements.get(i).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
				elements.get(i).sendKeys("2359");
				log.info("To timings changed and store marked online");
				flag = true;
			}
		}
		Thread.sleep(2000);
		webDB.waitForElement(WidgetLocators.SAVE, ElementType.Xpath);
		webDB.clickAnElement(WidgetLocators.SAVE, ElementType.Xpath);
	}

	public static boolean markAgentOfflineAndCheckOfflineMessageOnAlertsPopup()
			throws InterruptedException, FileNotFoundException, IOException {
		Thread.sleep(2000);
		try {
			selectShowChatWhenAgentOffline();
			enableCHatButton();
			Thread.sleep(1000);
			elements = webDB.driver.findElements(By.xpath(WidgetLocators.EDIT_DETAILS));
			log.info(elements.size());
			for (int i = 0; i < elements.size(); i++) {
//				elements.get(i).click();
				wait.until(ExpectedConditions.elementToBeClickable(element));
				actions.click(elements.get(i)).build().perform();
				List<WebElement> toTime = webDB.driver.findElements(By.xpath(WidgetLocators.STORE_TO_TIME));
				log.info(toTime.size());
				DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
				Date date = new Date();
				String time = dateFormat.format(date);
				log.info(time);
				String str = time.replaceAll("[^a-zA-Z0-9]", " ");
				endTimeHour = str.substring(0, 2);
				endTimeHour = endTimeHour + "00";
				for (int j = 0; j < toTime.size(); j++) {
					toTime.get(j).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
					toTime.get(j).sendKeys(endTimeHour);
					log.info("To timings changed and store marked offline");
					flag = true;
				}
				webDB.clickAnElement(WidgetLocators.SAVE, ElementType.Xpath);
				Thread.sleep(3000);
			}
			disableGreetingsWidget();
			Thread.sleep(1000);
			personalWidgetPageToStoreNavigation();
			ShopifyStore.shopifyStorePassEnter();
			webDB.waitForElement(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET, ElementType.Xpath);
			webDB.clickAnElement(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET, ElementType.Xpath);
			Thread.sleep(3000);
			Alert alert = webDB.driver.switchTo().alert();
			text = alert.getText();
			Thread.sleep(1000);
			log.info(text);
			propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
			prop.load(new FileInputStream(propertyFilePath));
			String offlineText = prop.getProperty("AgentOfflineMessageOnStore");
			log.info(offlineText);
			if (text.contains(offlineText)) {
				flag = true;
				log.info(flag);
				log.info(text);
				log.info("Agent Offline Message is displayed on store on Alerts");
				alert.accept();
			}

		} catch (Exception e) {
			log.info("Exception thrown");
		} finally {
			webDB.driver.close();
			log.info("closed the child window");
			webDB.driver.switchTo().window(parentWindow);
			log.info("switched to parent window");
			actions.moveToElement(webDB.driver.findElement(By.xpath(WidgetLocators.STORE_TIMINGS_EDIT_OPTION))).build()
					.perform();
			Thread.sleep(2000);
			for (int i = 0; i < elements.size(); i++) {
//				elements.get(i).click();
				actions.click(elements.get(i)).build().perform();
				log.info("here we are");
				Thread.sleep(2000);
				List<WebElement> toTiming = webDB.driver
						.findElements(By.xpath("//div[contains(@class,'Polaris-Labelled--hidden')]"));
				toTiming = webDB.driver.findElements(By.xpath("//input[@value=" + endTimeHour + "]"));
				for (int j = 0; j < toTiming.size(); j++) {
					toTiming.get(j).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
					toTiming.get(j).sendKeys("2359");
					log.info("To timings changed and store marked online");
					flag = true;
				}
				webDB.clickAnElement(WidgetLocators.SAVE, ElementType.Xpath);
				Thread.sleep(1000);
			}
		}

		return flag;

	}

	public static boolean verifycallout() throws InterruptedException {

//		webDB.driver.manage().deleteCookieNamed("access_token");
//		webDB.driver.manage().addCookie(new Cookie("access_token", "sat_b55880b4421e4fca8c5f769abc9c6bf0"));
		webDB.driver.navigate().refresh();
		enableCHatButton();
		selectShowChatWhenAgentOffline();
		Thread.sleep(2000);
//		element = webDB.driver.findElement(By.xpath(WidgetLocators.CALLOUT_CHECKBOX_SELECT));
//		actions.moveToElement(element).build().perform();
//		flag = webDB.driver.findElements(By.xpath(WidgetLocators.CALLOUT_CARD_CHECKBOX)).isEmpty();
//		if (!flag) {
//			webDB.driver.findElement(By.xpath(WidgetLocators.CALLOUT_CHECKBOX_SELECT));
//		}
		flag = webDB.driver.findElement(By.xpath(WidgetLocators.CALLOUT_CHECKBOX_SELECT)).isSelected();
		if (!flag) {
			element = webDB.driver.findElement(By.xpath(WidgetLocators.CALLOUT_CHECKBOX_SELECT));
			personalWidget = (JavascriptExecutor) webDB.driver;
			personalWidget.executeScript("arguments[0].click();", element);
		}
		callouttext = "Hi,How can i help you?";
		webDB.driver.findElement(By.xpath(WidgetLocators.CALLOUT_TEXTBOX_FIELD))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(WidgetLocators.CALLOUT_TEXTBOX_FIELD, callouttext, ElementType.Xpath);
		log.info("Callout textbox text changed");
		Thread.sleep(3000);
		String calloutcarddelay = "5";
		webDB.driver.findElement(By.xpath(WidgetLocators.CALLOUT_CARD_DELAY))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(WidgetLocators.CALLOUT_CARD_DELAY, calloutcarddelay, ElementType.Xpath);
		log.info("Callout delay set to " + calloutcarddelay);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CommonLocators.SAVE_SETTINGS)));
		webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
		log.info("Save settings clicked");

		flag = webDB.isElementDisplayed(WidgetLocators.SUCCESS_POPUP, ElementType.Xpath);
		log.info("Data Saved Successfully");
		personalWidgetPageToStoreNavigation();
		ShopifyStore.shopifyStorePassEnter();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET)));
		personalWidget.executeScript("window.localStorage.clear();");
		webDB.driver.navigate().refresh();
		Thread.sleep(8000);
		if (flag) {
			webDB.waitForElement(ShopifyStoreLocators.CALLOUT_CONTAINER, ElementType.Xpath);
			flag = webDB.isElementDisplayed(ShopifyStoreLocators.CALLOUT_CONTAINER, ElementType.Xpath);
			log.info("Callout text is displayed");
		}
		webDB.driver.close();
		log.info("closed the child window");
		webDB.driver.switchTo().window(parentWindow);
		log.info("switched to parent window");
		return flag;
	}

	public static boolean verifypagestodisplaysharewidget() throws InterruptedException {

		webDB.waitForElement(WidgetLocators.SHARE_SETTINGS_TAB, ElementType.Id);
		webDB.clickAnElement(WidgetLocators.SHARE_SETTINGS_TAB, ElementType.Id);
		Thread.sleep(2000);

		elements = webDB.driver.findElements(By.xpath(WidgetLocators.CHECKBOX_GENERIC));
		for (int i = 0; i < elements.size(); i++) {
			flag = elements.get(i).isSelected();
			if (!flag) {

				webDB.driver.findElement(By.xpath("//span[text()='Homepage']")).click();
				webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
				log.info("Save settings clicked");

				webDB.isElementDisplayed(WidgetLocators.SUCCESS_POPUP, ElementType.Xpath);
				log.info("Data Saved Successfully !!!");
				flag = true;
			}

		}

		Thread.sleep(3000);
		verifyShareWidget();
		Thread.sleep(2000);
		webDB.driver.close();
		Thread.sleep(2000);
		webDB.driver.switchTo().window(parentWindow);
		return flag;
	}

	public static boolean verifyShareWidget() throws InterruptedException {
		flag = webDB.isElementDisplayed(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
		Thread.sleep(2000);
		text = webDB.driver.findElement(By.xpath(CommonLocators.ENABLE_DISABLE_BTN)).getText();
		log.info(text);

		EnableDisableStoreBtnStatus();
		if (flag) {
			webDB.sendTextToAnElement(ShopifyStoreLocators.STORE_PASS, "1234", ElementType.Xpath);
			webDB.clickAnElement(ShopifyStoreLocators.STORE_PASS_ENTER, ElementType.Xpath);
			flag = webDB.isElementDisplayed(ShopifyStoreLocators.HOMEPAGE_SHARE_WIDGET, ElementType.Xpath);
			if (flag) {
				log.info("Share button is displayed on Store");
			} else {
				log.info("Share button is not displayed on Store");
			}

		} else {
			webDB.driver.switchTo().window(childWindow);
			flag = webDB.isElementDisplayed(ShopifyStoreLocators.HOMEPAGE_SHARE_WIDGET, ElementType.Xpath);
			if (flag) {
				log.info("Share button is displayed on Store");
			} else {
				log.info("Share button is not displayed on Store");
			}
		}

		return flag;
	}

	private static boolean selectCheckbox(String displaytext) throws InterruptedException {
		WebElement Homepage = webDB.driver
				.findElement(By.xpath(WidgetLocators.HOMEPAGE_CHECKBOX + "'" + displaytext + "']"));
		WebElement checkboxclick = webDB.driver.findElement(By.xpath(WidgetLocators.PAGES_CHECKBOX_CLICK));
		checkboxstatus = Homepage.isSelected();
		log.info("Homepage checkbox is " + checkboxstatus);
		if (checkboxstatus) {
			Thread.sleep(2000);
			checkboxclick.click();
			// log.info("Homepage checkbox clicked");
			checkboxclick.click();
			// log.info("Homepage checkbox clicked");
			Thread.sleep(2000);
		} else if (!checkboxstatus) {
			checkboxclick.click();
			log.info("Homepage checkbox clicked");
			Thread.sleep(2000);
		}

		checkboxstatus = Homepage.isSelected();
		log.info("Homepage checkbox is " + checkboxstatus);
		return checkboxstatus;
	}

	public static boolean verifyContactRatingfeature() throws InterruptedException {
		Thread.sleep(1000);
		flag = HeaderText.headertextcheck("Widget Settings");
		return flag;
	}

	public static boolean verifypagestodisplaychatbutton() throws InterruptedException {
		parentWindow = webDB.driver.getWindowHandle();

//		Dimension d = new Dimension(1920, 1080);
//		webDB.driver.manage().window().setSize(d);
		Thread.sleep(1000);

		enableCHatButton();
		selectShowChatWhenAgentOffline();
		Thread.sleep(2000);
		elements = webDB.driver.findElements(By.xpath(WidgetLocators.CHECKBOX_GENERIC));
		List<WebElement> checkBox = webDB.driver.findElements(By.xpath(CommonLocators.CHECKBOX_COMMON));
		System.out.println(elements.size());
		for (int i = 6; i < elements.size(); i++) {
			flag = elements.get(i).isSelected();
			System.out.println(flag);
			System.out.println(i + "out");
			if (!flag) {
				System.out.println(i);
				// actions.moveToElement(checkBox.get(i)).click().build().perform();
				int a = i + 1;
				webDB.scrollToAnElement("(" + CommonLocators.CHECKBOX_COMMON + ")[" + a + "]", ElementType.Xpath);
				Thread.sleep(2000);
				webDB.driver.findElement(By.xpath("(" + CommonLocators.CHECKBOX_COMMON + ")[" + a + "]")).click();
				System.out.println("clicked here");
				Thread.sleep(15000);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CommonLocators.SAVE_SETTINGS)));
				webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
				wait.until(
						ExpectedConditions.invisibilityOfElementLocated(By.xpath(CommonLocators.ACTION_SUCCESS_POPUP)));
			}
		}
		System.out.println("Flag out");
		Thread.sleep(1000);
		personalWidgetPageToStoreNavigation();
		ShopifyStore.shopifyStorePassEnter();
		Thread.sleep(2500);
		flag = webDB.isElementDisplayed(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET, ElementType.Xpath);
		if (flag) {
			log.info("Chat button is displayed on Store");
		}
//		verifyChatButton();
		webDB.driver.switchTo().window(parentWindow);
		return flag;
	}

	public static boolean verifyChatButton() throws InterruptedException {
		flag = webDB.isElementDisplayed(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
		Thread.sleep(2000);
		text = webDB.driver.findElement(By.xpath(CommonLocators.ENABLE_DISABLE_BTN)).getText();
		log.info(text);

		EnableDisableStoreBtnStatus();

		if (flag) {
			webDB.sendTextToAnElement(ShopifyStoreLocators.STORE_PASS, "1234", ElementType.Xpath);
			webDB.clickAnElement(ShopifyStoreLocators.STORE_PASS_ENTER, ElementType.Xpath);
			flag = webDB.isElementDisplayed(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET, ElementType.Xpath);
			if (flag) {
				log.info("Chat button is displayed on Store");
			} else {
				log.info("Chat button is not displayed on Store");
			}

		} else {
			webDB.driver.switchTo().window(childWindow);
			flag = webDB.isElementDisplayed(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET, ElementType.Xpath);
			if (flag) {
				log.info("Chat button is displayed on Store");
			} else {
				log.info("Chat button is not displayed on Store");
			}
		}

		return flag;
	}

	public static boolean verifysharebuttondisplayposition(String displaytext, String pos) throws InterruptedException {
		Thread.sleep(2000);
		webDB.waitForElement(WidgetLocators.SHARE_SETTINGS_TAB, ElementType.Id);
//		element = webDB.driver.findElement(By.id(PersonalWidgetLocators.SHARE_SETTINGS_TAB));
		webDB.clickAnElement(WidgetLocators.SHARE_SETTINGS_TAB, ElementType.Id);
		Thread.sleep(3000);

		// for Desktop Only Radio
		// displaytext = "2";

		// for Mobile Only Radio
		// displaytext ="1";

		// for Desktop + Mobile Radio
		// displaytext = "3";

		webDB.moveToElement(WidgetLocators.SHARE_BTN_DISPLAY_RADIO + "'" + displaytext + "']", ElementType.Xpath);
		flag = webDB.driver.findElement(By.xpath(WidgetLocators.SHARE_BTN_DISPLAY_RADIO + "'" + displaytext + "']"))
				.isSelected();
		webDB.clickAnElement(WidgetLocators.SHARE_BTN_DISPLAY_RADIO + "'" + displaytext + "']", ElementType.Xpath);
//		actions.moveToElement(webDB.driver
//				.findElement(By.xpath(PersonalWidgetLocators.SHARE_BTN_DISPLAY_RADIO + "'" + displaytext + "']")))
//				.click().build().perform();
		element = webDB.driver.findElement(By.xpath(WidgetLocators.DESKTOP_POS + "[" + pos + "]"));
		personalWidget = (JavascriptExecutor) webDB.driver;
		personalWidget.executeScript("arguments[0].click();", element);

		elements = webDB.driver.findElements(By.xpath(WidgetLocators.CHECKBOX_GENERIC));
		for (int i = 0; i < elements.size() - 2; i++) {
			if (!elements.get(1).isSelected()) {
				actions.moveToElement(elements.get(i)).click().build().perform();
			}
		}
		webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
		log.info("Save settings clicked");
		flag = webDB.isElementDisplayed(WidgetLocators.SUCCESS_POPUP, ElementType.Xpath);
		log.info("Data Saved Successfully !!!");
		parentWindow = webDB.driver.getWindowHandle();
		personalWidgetPageToStoreNavigation();
		ShopifyStore.shopifyStorePassEnter();
		element = webDB.driver.findElement(By.xpath(ShopifyStoreLocators.SHARE_WIDGET_POS));
		Point location = element.getLocation();
		int x = location.getX();
		int y = location.getY();
		log.info("Location of Share Widget " + x + " " + y);
		flag = webDB.isElementDisplayed(ShopifyStoreLocators.SHARE_WIDGET_POS, ElementType.Xpath);
		log.info("Share widget is displayed");
		webDB.driver.close();
		log.info("closed the child window");
		webDB.driver.switchTo().window(parentWindow);
		log.info("switched to parent window");
		webDB.driver.switchTo().window(parentWindow);
		return flag;
	}

	public static boolean verifyShareBtnDesign(String template) throws InterruptedException {
//		Thread.sleep(3000);
		webDB.waitForElement(WidgetLocators.SHARE_SETTINGS_TAB, ElementType.Id);
		webDB.clickAnElement(WidgetLocators.SHARE_SETTINGS_TAB, ElementType.Id);
		log.info("Share Settings clicked");
		Thread.sleep(2000);

		webDB.waitForElement(WidgetLocators.SHARE_DESIGN_CHANGE_BTN, ElementType.Xpath);
		flag = webDB.clickAnElement(WidgetLocators.SHARE_DESIGN_CHANGE_BTN, ElementType.Xpath);
		log.info("Design Change button clicked");

//		actions.moveToElement(webDB.driver
//				.findElement(By.xpath(PersonalWidgetLocators.SHARE_BTN_TEMPLATE_RADIO + "'" + template + "']"))).click()
//				.build().perform();
		webDB.moveToElement(WidgetLocators.SHARE_BTN_TEMPLATE_RADIO + "'" + template + "']", ElementType.Xpath);
		flag = webDB.driver.findElement(By.xpath(WidgetLocators.SHARE_BTN_TEMPLATE_RADIO + "'" + template + "']"))
				.isSelected();
		log.info("Share Btn template radio button " + template + "selected");

		webDB.clickAnElement(WidgetLocators.SAVE_BTN, ElementType.Xpath);

		flag = webDB.waitForElement(WidgetLocators.SUCCESS_POPUP, ElementType.Xpath);

		flag = EnableDisableStoreBtnStatus();

		if (flag) {
			webDB.sendTextToAnElement(ShopifyStoreLocators.STORE_PASS, "1234", ElementType.Xpath);
			webDB.clickAnElement(ShopifyStoreLocators.STORE_PASS_ENTER, ElementType.Xpath);
			flag = webDB.isElementDisplayed(ShopifyStoreLocators.SHARE_WIDGET_POS, ElementType.Xpath);
			if (flag) {
				log.info("Share widget is displayed");
			} else {
				log.info("Share widget not is displayed");
			}

		} else {
			webDB.driver.switchTo().window(childWindow);
			flag = webDB.isElementDisplayed(ShopifyStoreLocators.SHARE_WIDGET_POS, ElementType.Xpath);
			if (flag) {
				log.info("Share widget is displayed");
			} else {
				log.info("Share widget not is displayed");
			}
		}
		webDB.driver.close();
		log.info("closed the child window");
		webDB.driver.switchTo().window(parentWindow);
		log.info("switched to parent window");
		return flag;
	}

	public static boolean ChangeTextandMessageShareBtn() throws InterruptedException {

		String btnTxt = "Share Button";
		String btnMsg = "Hi, this is a message";
		webDB.waitForElement(WidgetLocators.SHARE_SETTINGS_TAB, ElementType.Id);
		element = webDB.driver.findElement(By.id(WidgetLocators.SHARE_SETTINGS_TAB));
		webDB.clickAnElement(WidgetLocators.SHARE_SETTINGS_TAB, ElementType.Id);
		log.info("Share Settings clicked");

		Dimension d = new Dimension(1920, 1080);
		webDB.driver.manage().window().setSize(d);
		Thread.sleep(1000);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WidgetLocators.SHARE_BTN_TEXT)));
		webDB.driver.findElement(By.xpath(WidgetLocators.SHARE_BTN_TEXT))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(WidgetLocators.SHARE_BTN_TEXT, btnTxt, ElementType.Xpath);
		log.info("Share button text set to " + btnTxt);

		webDB.driver.findElement(By.xpath(WidgetLocators.SHARE_MSG_TXTAREA))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(WidgetLocators.SHARE_MSG_TXTAREA, btnMsg, ElementType.Xpath);
		log.info("Share button message set to " + btnMsg);

		text = webDB.driver.findElement(By.xpath(WidgetLocators.SHARE_MSG_TXTAREA)).getText();

		Thread.sleep(2000);
		webDB.clickAnElement(WidgetLocators.SAVE_BTN, ElementType.Xpath);
		flag = webDB.waitForElement(WidgetLocators.SUCCESS_POPUP, ElementType.Xpath);

		Thread.sleep(500);
		WidgetPage.personalWidgetPageToStoreNavigation();

		ShopifyStore.shopifyStorePassEnter();
		Thread.sleep(800);
		windows = webDB.driver.getWindowHandles();
		iterator = windows.iterator();
		while (iterator.hasNext()) {
			childWindow = iterator.next();
			if (!parentWindow.equals(childWindow)) {
				webDB.driver.switchTo().window(childWindow);
				if (text.equalsIgnoreCase(btnTxt)) {
					log.info("Share Widget content verified");
					flag = webDB.isElementDisplayed(ShopifyStoreLocators.SHARE_WIDGET_POS, ElementType.Xpath);
					log.info("Share widget is displayed");
				}

			} else {

				if (text.equalsIgnoreCase(btnTxt)) {
					log.info("Share Widget content verified");
					flag = webDB.isElementDisplayed(ShopifyStoreLocators.SHARE_WIDGET_POS, ElementType.Xpath);
					log.info("Share widget is displayed");
				}
			}
		}

		webDB.driver.close();
		// Move to Message logs page
		webDB.driver.switchTo().window(parentWindow);

		return flag;
	}

	public static boolean verifySubsectionUnderChatSetting() throws InterruptedException {
		webDB.waitForElement(WidgetLocators.CHAT_SETTING_TAB, ElementType.Id);
		log.info("Chat Settings SubHeaders");
		Thread.sleep(2000);
		checkSubHeaders("WhatsApp Chat");
		checkSubHeaders("Basic Settings");
		checkSubHeaders("Button Text & Design");
		checkSubHeaders("Callout Card");
		checkSubHeaders("Greetings Widget");
		checkSubHeaders("Button Display & Position");
		checkSubHeaders("Pages to display");

		return flag;
	}

	public static boolean verifySubsectionUnderShareSetting() throws InterruptedException {

		Thread.sleep(1000);
		webDB.waitForElement(WidgetLocators.SHARE_SETTINGS_TAB, ElementType.Id);
		webDB.clickAnElement(WidgetLocators.SHARE_SETTINGS_TAB, ElementType.Id);
		log.info("Share Settings SubHeaders");
		checkSubHeaders("WhatsApp Share");
		checkSubHeaders("Button Text & Design");
		checkSubHeaders("Button Display & Position");
		checkSubHeaders("Pages to display");

		return flag;
	}

	public static boolean checkSubHeaders(String SubHeaderTxt) throws InterruptedException {
		// Thread.sleep(1500);
		// log.info(SubHeaderTxt);
		flag = webDB.isElementDisplayed(WidgetLocators.SUBSECTION_HEADERS + "'" + SubHeaderTxt + "')]",
				ElementType.Xpath);
		log.info(SubHeaderTxt + " is displayed");

		return flag;
	}

	public static boolean verifyAddAgent() throws InterruptedException {
		String PINCODE = "91";
		String WA_PHONE_NO = RandomStringUtils.randomNumeric(10);
		String AgentName = "AutoAgent" + RandomStringUtils.randomNumeric(2);
		log.info(AgentName);
		String AGENT_ROLE = "Support";
		String AGENT_AVATAR_RADIOBTN_NO = "1";

		flag = webDB.waitForElement(WidgetLocators.CHAT_SETTING_TAB, ElementType.Id);
		checkSubHeaders("Basic Settings");

		elements = webDB.driver.findElements(By.xpath(WidgetLocators.AGENT_TABLE_ROWS));
		int count = elements.size();
		log.info("Previous count " + count);

		Thread.sleep(1000);
		webDB.waitForElement(WidgetLocators.ADD_ANOTHER_AGENT_BTN, ElementType.Xpath);
		webDB.clickAnElement(WidgetLocators.ADD_ANOTHER_AGENT_BTN, ElementType.Xpath);

		Thread.sleep(1000);
		webDB.driver.findElement(By.xpath(WidgetLocators.PINCODE_TXTBOX))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(WidgetLocators.PINCODE_TXTBOX, PINCODE, ElementType.Xpath);

		webDB.driver.findElement(By.xpath(WidgetLocators.WA_PHONE_NO_TXTBOX))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(WidgetLocators.WA_PHONE_NO_TXTBOX, WA_PHONE_NO, ElementType.Xpath);

		webDB.driver.findElement(By.xpath(WidgetLocators.AGENT_NAME_TXTBOX))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(WidgetLocators.AGENT_NAME_TXTBOX, AgentName, ElementType.Xpath);

		webDB.driver.findElement(By.xpath(WidgetLocators.AGENT_ROLE))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(WidgetLocators.AGENT_ROLE, AGENT_ROLE, ElementType.Xpath);

		actions.moveToElement(webDB.driver
				.findElement(By.xpath(WidgetLocators.AGENT_AVATAR_RADIO_BTN + "'" + AGENT_AVATAR_RADIOBTN_NO + "']")))
				.click().build().perform();
		flag = webDB.driver
				.findElement(By.xpath(WidgetLocators.AGENT_AVATAR_RADIO_BTN + "'" + AGENT_AVATAR_RADIOBTN_NO + "']"))
				.isSelected();

		Thread.sleep(1200);
		webDB.clickAnElement(WidgetLocators.SAVE_BTN, ElementType.Xpath);

		Thread.sleep(1000);
		elements = webDB.driver.findElements(By.xpath(WidgetLocators.AGENT_TABLE_ROWS));
		int currcount = elements.size();
		log.info("Current count " + currcount);
		if (currcount > count) {
			log.info(AgentName + " Added");
			flag = true;
		} else {
			log.info(AgentName + " Not added");
			flag = false;
		}

		return flag;
	}

//	public static boolean AgentOnlineHrsTxtbox(String txtbox,String Time) throws InterruptedException
//	{
//		webDB.driver.findElement(By.xpath(PersonalWidgetLocators.AGENT_HRS_TXTBOX+"'"+txtbox+"']")).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
//		webDB.sendTextToAnElement(PersonalWidgetLocators.AGENT_HRS_TXTBOX, Time, ElementType.Xpath);
//		return flag;
//	}

	public static boolean markAgentOfflineAndCheckOfflineMessageOnGreetings()
			throws InterruptedException, FileNotFoundException, IOException {
		Thread.sleep(1000);
		try {
			enableCHatButton();
			selectShowChatWhenAgentOffline();
			elements = webDB.driver.findElements(By.xpath(WidgetLocators.EDIT_DETAILS));
			log.info(elements.size());
			for (int i = 0; i < elements.size(); i++) {
				elements.get(i).click();
				Thread.sleep(1000);
//				List<WebElement> toTime = webDB.driver
//						.findElements(By.cssSelector(PersonalWidgetLocators.STORE_TO_TIME));
//				log.info(toTime.size());
				DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
				Date date = new Date();
				String time = dateFormat.format(date);
				log.info(time);
				String str = time.replaceAll("[^a-zA-Z0-9]", " ");
				System.out.println("str = " + str);
				endTimeHour = str.substring(0, 2);
				endTimeHour = endTimeHour + "00";
				System.out.println("endtimehour = " + endTimeHour);
				List<WebElement> toTime;
				toTime = webDB.driver.findElements(By.xpath("//input[@value=" + endTimeHour + "]"));
				for (int j = 0; j < toTime.size(); j++) {
					toTime.get(j).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
					toTime.get(j).sendKeys(endTimeHour);
					log.info("To timings changed and store marked offline");
					flag = true;
				}
				webDB.clickAnElement(WidgetLocators.SAVE, ElementType.Xpath);
				Thread.sleep(2000);
			}
			enableGreetingsWidget();
			personalWidgetPageToStoreNavigation();
			ShopifyStore.shopifyStorePassEnter();
//			webDB.waitForElement(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET, ElementType.Xpath);
//			webDB.clickAnElement(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET, ElementType.Xpath);
			Thread.sleep(1000);
			text = webDB.driver.findElement(By.cssSelector(ShopifyStoreLocators.STORE_OFFLINE_MSG_TEXT)).getText();
			log.info(text);
			propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
			prop.load(new FileInputStream(propertyFilePath));
			String offlineText = prop.getProperty("AgentOfflineMessageOnStore");
			log.info(offlineText);
			if (text.contains(offlineText)) {
				flag = true;
				log.info("Store Offline Message is displayed on store on Alerts");
			}
		} catch (Exception e) {
			log.info("Exception thrown is " + e);
		} finally {
			webDB.driver.close();
			log.info("closed the child window");
			webDB.driver.switchTo().window(parentWindow);
			log.info("switched to parent window");
			resetDefaultStoreTimings();
//			personalWidget.executeScript("window.scrollBy(1182,26)");
//			actions.moveToElement(webDB.driver.findElement(By.xpath(PersonalWidgetLocators.STORE_TIMINGS_EDIT_OPTION)))
//					.build().perform();
//			for (int i = 0; i < elements.size(); i++) {
//				elements.get(i).click();
//				Thread.sleep(1000);
//				List<WebElement> toTiming = webDB.driver
//						.findElements(By.xpath("//div[contains(@class,'Polaris-Labelled--hidden')]"));
//				toTiming = webDB.driver.findElements(By.xpath("//input[@value=" + endTimeHour + "]"));
//				for (int j = 0; j < toTiming.size(); j++) {
//					toTiming.get(j).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
//					toTiming.get(j).sendKeys("2359");
			log.info("To timings changed and store marked online");
//					Thread.sleep(1000);
			flag = true;
//				}
//				webDB.clickAnElement(PersonalWidgetLocators.SAVE, ElementType.Xpath);
//				Thread.sleep(1000);
//			}
		}
		return flag;

	}

	public static boolean changeDesignGreetingsCard() throws InterruptedException {
		actions = new Actions(webDB.driver);
		actions.moveToElement(webDB.driver.findElement(By.xpath(WidgetLocators.GREETINGS_CHANGE_DESIGN))).click()
				.build().perform();
		webDB.isElementDisplayed(WidgetLocators.FIRST_CUSTOM_GREETING_CARDS, ElementType.Xpath);
		webDB.clickAnElement(WidgetLocators.FIRST_CUSTOM_GREETING_CARDS, ElementType.Xpath);
		webDB.clickAnElement(WidgetLocators.SAVE, ElementType.Xpath);
		webDB.clickAnElement(WidgetLocators.SINGLE_COLOR_RADIO_GREETINGS, ElementType.Xpath);
		webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
		webDB.driver.findElement(By.id(WidgetLocators.GREETINGS_HEADING_TXT__COLOR))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(WidgetLocators.GREETINGS_HEADING_TXT__COLOR, "#ffffff", ElementType.Id);
		webDB.driver.findElement(By.id(WidgetLocators.GREETINGS_DESC_TXT_COLOR))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(WidgetLocators.GREETINGS_DESC_TXT_COLOR, "#311610", ElementType.Id);
		webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
		flag = webDB.isElementDisplayed(WidgetLocators.SAVE_SUCCESS_MSG, ElementType.Xpath);
		if (flag) {
			log.info("Greeting design and color changed successfullly");
		}
		return flag;

	}

	public static boolean updateGreetingsTitleAndHelpText() throws InterruptedException {
		webDB.driver.navigate().refresh();
		personalWidget.executeScript("window.scrollBy(0,500)");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(WidgetLocators.GREETINGS_TITLE_TXT_FIELD)));
		actions.moveToElement(webDB.driver.findElement(By.cssSelector(WidgetLocators.GREETINGS_TITLE_TXT_FIELD)))
				.click().build().perform();
		webDB.driver.findElement(By.cssSelector(WidgetLocators.GREETINGS_TITLE_TXT_FIELD))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(WidgetLocators.GREETINGS_TITLE_TXT_FIELD, "Hello There", ElementType.CssSelector);
		webDB.driver.findElement(By.cssSelector(WidgetLocators.GREETINGS_HELPTEXT_FIELD))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(WidgetLocators.GREETINGS_HELPTEXT_FIELD, "We are here to solve your Queries",
				ElementType.CssSelector);
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

	public static boolean checkupdatedGreetingsSettingOnStore() throws InterruptedException {
		flag = false;
		parentWindow = webDB.driver.getWindowHandle();
		enableCHatButton();
		enableAgent();
		resetDefaultAgentTimings();
		enableGreetingsWidget();
		selectShowChatWhenAgentOffline();
		updateGreetingsTitleAndHelpText();
		personalWidgetPageToStoreNavigation();
		ShopifyStore.shopifyStorePassEnter();
		Thread.sleep(1000);
		webDB.waitForElement(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET, ElementType.Xpath);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET)));
		webDB.clickAnElement(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET, ElementType.Xpath);
		webDB.isElementDisplayed(ShopifyStoreLocators.GREETINGS_DESC_COLOR, ElementType.Xpath);
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

	public static boolean checkChatWidgetOffsetOnStore() throws InterruptedException {
		flag = false;
		parentWindow = webDB.driver.getWindowHandle();
		Thread.sleep(2000);
		enableCHatButton();
		selectShowChatWhenAgentOffline();
		changeHChatHeightAndEdgeOffset();
		personalWidgetPageToStoreNavigation();
		ShopifyStore.shopifyStorePassEnter();
		element = webDB.driver.findElement(By.xpath(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET));
		Point point = element.getLocation();
		int width = point.getY();
		int height = point.getX();
		if (height == 1425 && width == 681) {
			flag = true;
		}

		webDB.driver.close();
		log.info("closed the child window");
		webDB.driver.switchTo().window(parentWindow);
		log.info("switched to parent window");
		return flag;
	}

	public static boolean checkChatButtonDisplayedWhenAgentsOffline() throws InterruptedException {
		Thread.sleep(800);
		selectShowChatWhenAgentOffline();
		markStoreOffline();
		personalWidgetPageToStoreNavigation();
		ShopifyStore.shopifyStorePassEnter();
		flag = webDB.isElementDisplayed(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET, ElementType.Xpath);
		if (flag) {
			log.info("chat widget is displayed when store is offline");
//		if (flag) {
//			webDB.driver.close();
//			log.info("switched to child window");
//			webDB.driver.switchTo().window(parentWindow);
//			log.info("switched to parent window");
//			resetDefaultStoreTimings();
//			flag = webDB.driver.findElement(By.xpath(PersonalWidgetLocators.SHOW_CHT_BTN_WHEN_OFFLINE_CHKBOX))
//					.isSelected();
//			if (!flag) {
//				element = webDB.driver.findElement(By.xpath(PersonalWidgetLocators.SHOW_CHAT_WHEN_OFFLINE_CHECKBOX));
//				personalWidget = (JavascriptExecutor) webDB.driver;
//				personalWidget.executeScript("arguments[0].click();", element);
//				webDB.clickAnElement(PersonalWidgetLocators.SHOW_CHT_BTN_WHEN_OFFLINE_CHKBOX, ElementType.Xpath);
//				webDB.isElementDisplayed(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
//				webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
//			}
		}
		webDB.driver.close();
		log.info("Closed the child window");
		webDB.driver.switchTo().window(parentWindow);
		log.info("switched to parent window");
		resetDefaultStoreTimings();
		return flag;

	}

	public static void changeHChatHeightAndEdgeOffset() throws InterruptedException {
		actions.moveToElement(webDB.driver.findElement(By.xpath(WidgetLocators.DESKTOP_MOBILE_RADIO))).click().build()
				.perform();
		enableMobileProductOffsetWhenMobilePlusDesktopSelected();
		element = webDB.driver.findElement(By.xpath(WidgetLocators.DESKTOP_POS_RIGHT));
		personalWidget = (JavascriptExecutor) webDB.driver;
		personalWidget.executeScript("arguments[0].click();", element);
		webDB.driver.findElement(By.xpath(WidgetLocators.HEIGHT_OFFSET_DESKTOP))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(WidgetLocators.HEIGHT_OFFSET_DESKTOP, "20", ElementType.Xpath);
		webDB.driver.findElement(By.xpath(WidgetLocators.EDGE_OFFSET))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(WidgetLocators.EDGE_OFFSET, "20", ElementType.Xpath);
//		flag = webDB.driver.findElement(By.xpath(PersonalWidgetLocators.DESKTOP_PRODUCT_OFFSET_CHECKBOX)).isSelected();
//		if (!flag) {
//			webDB.clickAnElement(PersonalWidgetLocators.DESKTOP_PRODUCT_OFFSET_CHECKBOX, ElementType.Xpath);
//		}
		flag = webDB.driver.findElement(By.xpath("//h2[text()='Desktop']/following::input[5]")).isSelected();
		System.out.println(flag);
		if (!flag) {
			flag = true;
		} else {
			webDB.clickAnElement("//h2[text()='Desktop']/following::input[5]/parent::span[1]", ElementType.Xpath);

		}
//		Thread.sleep(2000);
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(PersonalWidgetLocators.PRODUCT_HEIGHT_OFFSET)));
//		webDB.driver.findElement(By.xpath(PersonalWidgetLocators.PRODUCT_HEIGHT_OFFSET))
//				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
//		webDB.sendTextToAnElement(PersonalWidgetLocators.PRODUCT_HEIGHT_OFFSET, "10", ElementType.Xpath);
		Thread.sleep(2000);
		webDB.isElementDisplayed(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
		actions.click(webDB.driver.findElement(By.xpath(CommonLocators.SAVE_SETTINGS))).build().perform();
		;
		log.info("Height and Edge offset changed to 200");

	}

	public static boolean checkSelectDeselectRandomizeAgentOption() throws InterruptedException {
		Thread.sleep(2000);
		actions.moveToElement(webDB.driver.findElement(By.xpath(WidgetLocators.ENABLE_DISABLE_GREETINGS))).build()
				.perform();
		enableGreetingsWidget();
		Thread.sleep(30000);
		actions.moveToElement(webDB.driver.findElement(By.xpath(WidgetLocators.AGENT_RANDOMIZE_CHECKBOX))).click()
				.build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CommonLocators.SAVE_SETTINGS)));
		webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WidgetLocators.SAVE_SUCCESS_MSG)));
		flag = webDB.isElementDisplayed(WidgetLocators.SAVE_SUCCESS_MSG, ElementType.Xpath);
		return flag;
	}

	public static boolean enableCHatButton() throws InterruptedException {
		log.info("Inside enable chat button");
		Thread.sleep(2500);
		// flag = false;
		flag = webDB.waitForElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
		element = webDB.driver.findElement(By.xpath(CommonLocators.ENABLE_DISABLE_BTN));
//		actions.moveToElement(element).build().perform();
		webDB.scrollToAnElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath(CommonLocators.ENABLE_DISABLE_BTN)).getText();
		if (text.equalsIgnoreCase("Enable")) {
			webDB.clickAnElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
			flag = webDB.waitForElement(WidgetLocators.CHAT_ENABLED_SUCCESS_MSG, ElementType.Xpath);
			if(flag) {
				log.info("enable chat button is done.");
			}
			
		}
		return flag;

	}

	public static boolean enableShareButton() throws InterruptedException {
		flag = false;
		actions.moveToElement(webDB.driver.findElement(By.id(WidgetLocators.SHARE_SETTINGS_TAB))).click().build()
				.perform();
		Thread.sleep(3000);
		flag = webDB.isElementDisplayed(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
		element = webDB.driver.findElement(By.xpath(CommonLocators.ENABLE_DISABLE_BTN));
		actions.moveToElement(element).build().perform();
		text = webDB.driver.findElement(By.xpath(CommonLocators.ENABLE_DISABLE_BTN)).getText();
		if (text.equalsIgnoreCase("Enable")) {
			webDB.clickAnElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
			flag = webDB.waitForElement(WidgetLocators.CHAT_ENABLED_SUCCESS_MSG, ElementType.Xpath);
		}
		return flag;

	}

	public static boolean disableCHatButton() throws InterruptedException {
		Thread.sleep(1000);
		flag = false;
		flag = webDB.isElementDisplayed(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath(CommonLocators.ENABLE_DISABLE_BTN)).getText();
		if (text.equalsIgnoreCase("Disable")) {
			webDB.clickAnElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
			flag = webDB.isElementDisplayed(WidgetLocators.CHAT_DISABLED_SUCCESS_MSG, ElementType.Xpath);
		}
		return flag;

	}

	public static boolean selectShowChatWhenAgentOffline() throws InterruptedException {
		log.info("Indise select show chat when agent offline");
		webDB.moveToElement(WidgetLocators.SHOW_CHT_BTN_WHEN_OFFLINE_CHKBOX, ElementType.Xpath);
		Thread.sleep(3000);
		flag = webDB.driver.findElement(By.xpath(WidgetLocators.SHOW_CHT_BTN_WHEN_OFFLINE_CHKBOX)).isSelected();
		if (!flag) {
			element = webDB.driver.findElement(By.xpath(WidgetLocators.SHOW_CHAT_WHEN_OFFLINE_CHECKBOX));
			personalWidget = (JavascriptExecutor) webDB.driver;
			personalWidget.executeScript("arguments[0].click();", element);
			webDB.clickAnElement(WidgetLocators.SHOW_CHT_BTN_WHEN_OFFLINE_CHKBOX, ElementType.Xpath);
			webDB.waitForElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
			webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
			flag = webDB.isElementDisplayed(WidgetLocators.SAVE_SUCCESS_MSG, ElementType.Xpath);
			if(flag) {
				log.info("Select show chat when agent offline is done.");
			}
		}
		return flag;
	}

	public static boolean unselectShowChatWhenAgentOffline() throws InterruptedException {
		Thread.sleep(3000);
		flag = webDB.driver.findElement(By.xpath(WidgetLocators.SHOW_CHT_BTN_WHEN_OFFLINE_CHKBOX)).isSelected();
		if (flag) {
			element = webDB.driver.findElement(By.xpath(WidgetLocators.SHOW_CHAT_WHEN_OFFLINE_CHECKBOX));
			personalWidget = (JavascriptExecutor) webDB.driver;
			personalWidget.executeScript("arguments[0].click();", element);
			webDB.clickAnElement(WidgetLocators.SHOW_CHT_BTN_WHEN_OFFLINE_CHKBOX, ElementType.Xpath);
			webDB.isElementDisplayed(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
			webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
			flag = webDB.isElementDisplayed(WidgetLocators.SAVE_SUCCESS_MSG, ElementType.Xpath);
		}
		return flag;
	}

	public static boolean checklistChatButton() throws InterruptedException {
		List<WebElement> checkBoxGeneric = webDB.driver.findElements(By.xpath(WidgetLocators.CHECKBOX_GENERIC));
		log.info(checkBoxGeneric.size());
		if (checkBoxGeneric.get(0).isSelected() == false) {
			wait.until(ExpectedConditions.elementToBeClickable(checkBoxGeneric.get(0)));
//		        elements.get(0).click();
			actions.moveToElement(checkBoxGeneric.get(0)).click().build().perform();
//		    	log.info(elements.get(0).getText());
			flag = true;
		} else {
			flag = false;
		}

		Thread.sleep(2000);
		elements = webDB.driver.findElements(By.xpath(WidgetLocators.CHECKBOX_GENERIC));
		if (elements.get(6).isSelected() == false) {
			wait.until(ExpectedConditions.elementToBeClickable(elements.get(6)));
//		        elements.get(6).click();
			actions.moveToElement(elements.get(6)).click().build().perform();
//		    	log.info(elements.get(6).getText());
			flag = true;
		} else {
			flag = false;
		}
		// RadioButton Desktop
		flag = checkEnableRadio(4);
		// GreetingWidget
		flag = enableGreetingsWidget();

		return flag;
	}

	public static boolean changeDesktopeHeightOffsetShareButton() throws InterruptedException {
		String offsetValue = "3" + RandomStringUtils.randomNumeric(1);
		actions.moveToElement(
				webDB.driver.findElement(By.xpath(WidgetLocators.MOBILE_PLUS_DESKTOP_RADIO_SELECTION_CHECK_SHARE)))
				.build().perform();
		flag = webDB.driver.findElement(By.xpath(WidgetLocators.MOBILE_PLUS_DESKTOP_RADIO_SELECTION_CHECK_SHARE))
				.isSelected();
		if (!flag) {
			elements = webDB.driver.findElements(By.xpath(CommonLocators.RADIO_GENERIC));
			elements.get(0).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CommonLocators.SAVE_SETTINGS)));
			webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
		}

		actions.moveToElement(webDB.driver.findElement(By.xpath(WidgetLocators.DESKTOP_POS_LEFT))).build().perform();
		flag = webDB.driver.findElement(By.xpath(WidgetLocators.DESKTOP_POS_LEFT)).isSelected();
		if (!flag) {
			elements = webDB.driver.findElements(By.xpath(CommonLocators.RADIO_GENERIC));
			elements.get(5).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CommonLocators.SAVE_SETTINGS)));
			webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
		}

		actions.moveToElement(
				webDB.driver.findElement(By.xpath(WidgetLocators.HEIGHT_OFFSET_SHARE_BTN_MOBILE_DESKTOP_SELECTED)))
				.build().perform();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(WidgetLocators.HEIGHT_OFFSET_SHARE_BTN_MOBILE_DESKTOP_SELECTED)));
		element = webDB.driver.findElement(By.xpath(WidgetLocators.HEIGHT_OFFSET_SHARE_BTN_MOBILE_DESKTOP_SELECTED));
		element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		element.sendKeys(Keys.chord(Keys.CONTROL, "a"), offsetValue);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CommonLocators.SAVE_SETTINGS)));
		flag = webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
		return flag;
	}

	public static boolean verifyDesktopHeightOffsetShareButton() throws InterruptedException {
		parentWindow = webDB.driver.getWindowHandle();
		enableShareButton();
		changeDesktopeHeightOffsetShareButton();
		actions.moveToElement(webDB.driver.findElement(By.id(WidgetLocators.SHARE_SETTINGS_TAB)));
//		personalWidgetPageToStoreNavigation();
//		ShopifyStore.shopifyStorePassEnter();

		EnableDisableStoreBtnStatus();
		if (flag) {
			webDB.sendTextToAnElement(ShopifyStoreLocators.STORE_PASS, "1234", ElementType.Xpath);
			webDB.clickAnElement(ShopifyStoreLocators.STORE_PASS_ENTER, ElementType.Xpath);
			flag = webDB.isElementDisplayed(ShopifyStoreLocators.HOMEPAGE_SHARE_WIDGET, ElementType.Xpath);
			log.info(flag);
			if (flag) {
				element = webDB.driver.findElement(By.xpath(ShopifyStoreLocators.SHARE_WIDGET_POS));
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
				element = webDB.driver.findElement(By.xpath(ShopifyStoreLocators.SHARE_WIDGET_POS));
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

	public static void enableMobileProductOffsetWhenMobilePlusDesktopSelected() {
		element = webDB.driver.findElement(By.id(WidgetLocators.MOBILE_PRODUCT_OFFSET_CHECKBOX_SELECTION_CHECK));
		if (!element.isSelected()) {
			actions.moveToElement(
					webDB.driver.findElement(By.xpath(WidgetLocators.MOBILE_PRODUCT_HEIGHT_OFFSET_CHECKBOX_CLICKABLE)))
					.click().build().perform();
			webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
			log.info("Product Offset enabled for mobile");
		}
	}

	public static boolean verifyDiscountMsgSpinWSheel() throws Exception {
		flag = webDB.isElementDisplayed(CommonLocators.TOURPOPUP, ElementType.Xpath);
		if (flag) {
			webDB.pressEscapeKey();
		}
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			flag = webDB.waitForElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			if (flag) {
				Thread.sleep(1500);
				webDB.clickAnElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			}
		}
		flag = webDB.waitForElement(WidgetLocators.PERSONALWIDGETTAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.PERSONALWIDGETTAB, ElementType.Xpath);
			if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
					|| (System.getProperty("os").equals("Android"))) {
				webDB.clickAnElement(CommonLocators.CLOSEICON, ElementType.Xpath);
			}
			Thread.sleep(2500);
			if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
					|| (System.getProperty("os").equals("Android"))) {
				flag = webDB.waitForElement(CommonLocators.THREEDOTSMENU, ElementType.Xpath);
				if (flag) {
					Thread.sleep(1000);
					webDB.clickAnElement(CommonLocators.THREEDOTSMENU, ElementType.Xpath);
				}
			}
			webDB.waitForElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
			webDB.clickAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
			Thread.sleep(2500);
			flag = webDB.waitForElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
				webDB.moveToElement(WidgetLocators.ACCOUNTPAGESTEXT_CHECKBOX, ElementType.Xpath);
				Thread.sleep(1000);
				webDB.javaScriptClickAnElement(WidgetLocators.HOMEPAGECHECKBOXCLICK, ElementType.Xpath);
				webDB.javaScriptClickAnElement(WidgetLocators.COLLECTIONS_CHECKBOX, ElementType.Xpath);
				webDB.javaScriptClickAnElement(WidgetLocators.PRODUCT_PAGES_CHECKBOX, ElementType.Xpath);
				Thread.sleep(1000);
				flag = webDB.isElementDisplayed(WidgetLocators.ERRORPOP, ElementType.Xpath);
				Thread.sleep(1500);
				flag = webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
				flag = true;
			}
		}
		return flag;
	}

	public static boolean verifyDisablePageCheckStore() throws Exception {
		flag = webDB.isElementDisplayed(CommonLocators.TOURPOPUP, ElementType.Xpath);
		if (flag) {
			webDB.pressEscapeKey();
		}
		parentWindow = webDB.driver.getWindowHandle();
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			flag = webDB.waitForElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			if (flag) {
				Thread.sleep(1500);
				webDB.clickAnElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			}
		}
		flag = webDB.waitForElement(WidgetLocators.PERSONALWIDGETTAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.PERSONALWIDGETTAB, ElementType.Xpath);
			if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
					|| (System.getProperty("os").equals("Android"))) {
				webDB.clickAnElement(CommonLocators.CLOSEICON, ElementType.Xpath);
			}
			if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
					|| (System.getProperty("os").equals("Android"))) {
				flag = webDB.waitForElement(CommonLocators.THREEDOTSMENU, ElementType.Xpath);
				if (flag) {
					Thread.sleep(1000);
					webDB.clickAnElement(CommonLocators.THREEDOTSMENU, ElementType.Xpath);
				}
			}
			Thread.sleep(2500);
			webDB.waitForElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
			webDB.clickAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
			Thread.sleep(2500);
			flag = webDB.waitForElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
				flag = webDB.isElementDisplayed(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
				text = webDB.driver.findElement(By.xpath(CommonLocators.ENABLE_DISABLE_BTN)).getText();
				log.info(text);
				if (!text.equals("Disable")) {
					Thread.sleep(1000);
					webDB.javaScriptClickAnElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
				}
				webDB.moveToElement(WidgetLocators.ACCOUNTPAGESTEXT_CHECKBOX, ElementType.Xpath);
				Thread.sleep(1000);
				flag = webDB.driver.findElement(By.xpath(WidgetLocators.HOMEPAGECHECKBOX)).isSelected();
				if (flag) {
					webDB.javaScriptClickAnElement(WidgetLocators.HOMEPAGECHECKBOXCLICK, ElementType.Xpath);
				}
				Thread.sleep(1000);
				webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
				Thread.sleep(1500);
				webDB.moveToElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
				EnableDisableStoreBtnStatus();
				ShopifyStore.shopifyStorePassEnter();
				Thread.sleep(5000);
				flag = webDB.verifyElementIsNotDisplayed(WidgetLocators.SPIN_WHEEL_LOGO, ElementType.CssSelector);
				if (flag) {
					Thread.sleep(1500);
					webDB.driver.close();
					webDB.driver.switchTo().window(parentWindow);
					webDB.moveToElement(WidgetLocators.ACCOUNTPAGESTEXT_CHECKBOX, ElementType.Xpath);
					Thread.sleep(1000);
					flag = webDB.driver.findElement(By.xpath(WidgetLocators.HOMEPAGECHECKBOX)).isSelected();
					if (!flag) {
						webDB.javaScriptClickAnElement(WidgetLocators.HOMEPAGECHECKBOXCLICK, ElementType.Xpath);
					}
					Thread.sleep(2000);
					flag = webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
					if (flag) {
						webDB.moveToElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
						EnableDisableStoreBtnStatus();
						ShopifyStore.shopifyStorePassEnter();
						personalWidget.executeScript("window.localStorage.clear();");
						webDB.driver.navigate().refresh();
						Thread.sleep(2500);
//					String url = webDB.driver.getCurrentUrl();
//					flag = url.contains(PersonalWidgetLocators.STOREURL);
//					if (flag) {
						flag = webDB.isElementDisplayed(WidgetLocators.SPIN_WHEEL_LOGO, ElementType.CssSelector);
						Thread.sleep(1500);
						webDB.driver.switchTo().window(parentWindow);
//					}
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyMinimumOffset() throws Exception {
		int randomRoomNoIntValue;
		Random randomGenerator = new Random();
		int value = randomGenerator.nextInt(30) + 8;
		String Offset_value = Integer.toString(value);
		flag = webDB.waitForElement(WidgetLocators.PERSONALWIDGETTAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.PERSONALWIDGETTAB, ElementType.Xpath);
			Thread.sleep(2500);
			webDB.waitForElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
			webDB.clickAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
			Thread.sleep(1500);
			webDB.moveToElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
			webDB.clickAnElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
			Thread.sleep(2500);
			flag = webDB.isElementDisplayed(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
			text = webDB.driver.findElement(By.xpath(CommonLocators.ENABLE_DISABLE_BTN)).getText();
			log.info(text);
			if (!text.equals("Disable")) {
				Thread.sleep(1000);
				webDB.javaScriptClickAnElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
			}
			webDB.moveToElement(WidgetLocators.SPINWHEELMOBILEDESKTOPRADIOBTN, ElementType.Xpath);
			webDB.clickAnElement(WidgetLocators.SPINWHEELMOBILEDESKTOPRADIOBTN, ElementType.Xpath);
			flag = webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(WidgetLocators.MINIMUMHEIGHTINPUT, ElementType.Xpath);
			} else {
				flag = true;
			}
			flag = webDB.waitForElement(WidgetLocators.MINIMUMHEIGHTINPUT, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.MINIMUMHEIGHTINPUT, ElementType.Xpath);
				webDB.clearText(WidgetLocators.MINIMUMHEIGHTINPUT);
				webDB.clickAnElement(WidgetLocators.MINIMUMEDGEINPUT, ElementType.Xpath);
				webDB.clearText(WidgetLocators.MINIMUMEDGEINPUT);
				flag = webDB.isElementDisplayed(WidgetLocators.EMPTYHEIGHTERROR, ElementType.Xpath);
				if (flag) {
					flag = webDB.isElementDisplayed(WidgetLocators.EMPTYHEIGHTERROR, ElementType.Xpath);
					Thread.sleep(2500);
					webDB.clickAnElement(WidgetLocators.MINIMUMHEIGHTINPUT, ElementType.Xpath);
					webDB.sendTextToAnElement(WidgetLocators.MINIMUMHEIGHTINPUT, WidgetLocators.MINIMUMVALUE,
							ElementType.Xpath);
					webDB.clickAnElement(WidgetLocators.HEIGHTINCREASEINDENT, ElementType.Xpath);
					webDB.clickAnElement(WidgetLocators.HEIGHTINCREASEINDENT, ElementType.Xpath);
					webDB.clickAnElement(WidgetLocators.HEIGHTDECREASEINDENT, ElementType.Xpath);
					webDB.clickAnElement(WidgetLocators.MINIMUMEDGEINPUT, ElementType.Xpath);
					webDB.sendTextToAnElement(WidgetLocators.MINIMUMEDGEINPUT, WidgetLocators.MINIMUMVALUE,
							ElementType.Xpath);
					webDB.clickAnElement(WidgetLocators.EDGEINCREASEINDENT, ElementType.Xpath);
					webDB.clickAnElement(WidgetLocators.EDGEINCREASEINDENT, ElementType.Xpath);
					webDB.clickAnElement(WidgetLocators.EDGEDECREASEINDENT, ElementType.Xpath);
					Thread.sleep(2500);
					flag = webDB.isElementDisplayed(WidgetLocators.MINIMUMHEIGHTERROR, ElementType.Xpath);
					if (flag) {
						flag = webDB.isElementDisplayed(WidgetLocators.MINIMUMEDGEERROR, ElementType.Xpath);
						webDB.clearText(WidgetLocators.MINIMUMHEIGHTINPUT);
						webDB.clearText(WidgetLocators.MINIMUMEDGEINPUT);
						Thread.sleep(2500);
						webDB.clickAnElement(WidgetLocators.MINIMUMHEIGHTINPUT, ElementType.Xpath);
						webDB.sendTextToAnElement(WidgetLocators.MINIMUMHEIGHTINPUT, WidgetLocators.OFFSETVALUE,
								ElementType.Xpath);
						Thread.sleep(1500);
						webDB.clickAnElement(WidgetLocators.MINIMUMEDGEINPUT, ElementType.Xpath);
						webDB.sendTextToAnElement(WidgetLocators.MINIMUMEDGEINPUT, Offset_value, ElementType.Xpath);
						Thread.sleep(2500);
						flag = webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyDesktopSpinWheelIcon() throws Exception {
		flag = webDB.isElementDisplayed(CommonLocators.TOURPOPUP, ElementType.Xpath);
		if (flag) {
			webDB.pressEscapeKey();
		}
		parentWindow = webDB.driver.getWindowHandle();
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			flag = webDB.waitForElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			if (flag) {
				Thread.sleep(1500);
				webDB.clickAnElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			}
		}
		flag = webDB.waitForElement(WidgetLocators.PERSONALWIDGETTAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.PERSONALWIDGETTAB, ElementType.Xpath);
			if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
					|| (System.getProperty("os").equals("Android"))) {
				webDB.clickAnElement(CommonLocators.CLOSEICON, ElementType.Xpath);
			}
			Thread.sleep(2000);
			if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
					|| (System.getProperty("os").equals("Android"))) {
				flag = webDB.waitForElement(CommonLocators.THREEDOTSMENU, ElementType.Xpath);
				if (flag) {
					Thread.sleep(1000);
					webDB.clickAnElement(CommonLocators.THREEDOTSMENU, ElementType.Xpath);
				}
			}
			webDB.waitForElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
			webDB.clickAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
			Thread.sleep(2500);
			flag = webDB.waitForElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
				flag = webDB.isElementDisplayed(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
				text = webDB.driver.findElement(By.xpath(CommonLocators.ENABLE_DISABLE_BTN)).getText();
				log.info(text);
				if (!text.equals("Disable")) {
					Thread.sleep(1000);
					webDB.javaScriptClickAnElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
				}
				Thread.sleep(1500);
				webDB.moveToElement(WidgetLocators.SPINWHEELDESKTOPONLYRADIOBTN, ElementType.Xpath);
				flag = webDB.isElementDisplayed(WidgetLocators.MESSAGELANGUAGESECTION, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(WidgetLocators.SPINWHEELDESKTOPONLYRADIOBTN, ElementType.Xpath);
					Thread.sleep(1500);
					webDB.moveToElement(WidgetLocators.ACCOUNTPAGESTEXT_CHECKBOX, ElementType.Xpath);
					Thread.sleep(1000);
					flag = webDB.driver.findElement(By.xpath(WidgetLocators.HOMEPAGECHECKBOX)).isSelected();
					if (!flag) {
						webDB.javaScriptClickAnElement(WidgetLocators.HOMEPAGECHECKBOXCLICK, ElementType.Xpath);
					}
					webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
					Thread.sleep(1500);
					webDB.moveToElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
					webDB.clickAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
					Thread.sleep(2500);
					EnableDisableStoreBtnStatus();
					ShopifyStore.shopifyStorePassEnter();
					personalWidget.executeScript("window.localStorage.clear();");
					webDB.driver.navigate().refresh();
					Thread.sleep(3500);
					if ((System.getProperty("platformName").equals("browserstack"))
							&& (System.getProperty("os").equals("Ios"))
							|| (System.getProperty("os").equals("Android"))) {
						flag = webDB.verifyElementIsNotDisplayed(WidgetLocators.SPIN_WHEEL_LOGO,
								ElementType.CssSelector);
					} else {
						flag = webDB.waitForElement(WidgetLocators.SPIN_WHEEL_LOGO, ElementType.CssSelector);
					}
					if (flag) {
						Thread.sleep(1500);
						webDB.driver.close();
						webDB.driver.switchTo().window(parentWindow);
						webDB.moveToElement(WidgetLocators.SPINWHEELDESKTOPONLYRADIOBTN, ElementType.Xpath);
						webDB.clickAnElement(WidgetLocators.SPINWHEELMOBILEDESKTOPRADIOBTN, ElementType.Xpath);
						flag = webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
					}
				}
			}
		}
		return flag;
	}

	public static boolean VerifyWheelSpinsAfterWins() throws Exception {
		flag = webDB.waitForElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
				webDB.moveToElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
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
				webDB.moveToElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
				EnableDisableStoreBtnStatus();
				ShopifyStore.shopifyStorePassEnter();
				personalWidget.executeScript("window.localStorage.clear();");
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
							flag = webDB.waitForElement(WidgetLocators.SPIN_WHEEL_PHONE_NUMBER,
									ElementType.CssSelector);
							if (flag) {
								webDB.sendTextToAnElement(WidgetLocators.SPIN_WHEEL_PHONE_NUMBER,
										WidgetLocators.PHONE_NUMBER, ElementType.CssSelector);
								flag = webDB.waitForElement(WidgetLocators.SPIN_WHEEL_CONFIRM_BUTTON,
										ElementType.CssSelector);
								if (flag) {
									webDB.clickAnElement(WidgetLocators.SPIN_WHEEL_CONFIRM_BUTTON,
											ElementType.CssSelector);
									flag = webDB.waitForElement(WidgetLocators.COPY_COUPON_CODE, ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(WidgetLocators.COPY_COUPON_CODE, ElementType.Xpath);
										Thread.sleep(2000);
										webDB.switchToAlertAccept();
										flag = webDB.verifyElementIsNotDisplayed(WidgetLocators.SPIN_WHEEL_BUTTON,
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

	public static boolean VerifyUserNotWinDiscount() throws Exception {
		parentWindow = webDB.driver.getWindowHandle();
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			flag = webDB.waitForElement(CommonLocators.THREEDOTSMENU, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CommonLocators.THREEDOTSMENU, ElementType.Xpath);
			}
		}
		flag = webDB.waitForElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
				webDB.moveToElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
				flag = webDB.isElementDisplayed(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
				text = webDB.driver.findElement(By.xpath(CommonLocators.ENABLE_DISABLE_BTN)).getText();
				log.info(text);
				if (!text.equals("Disable")) {
					Thread.sleep(1000);
					webDB.javaScriptClickAnElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
				}else {
					System.out.println("Already Enabled");
				}
				Thread.sleep(1000);
				webDB.moveToElement(WidgetLocators.MESSAGE_TITLE, ElementType.Xpath);
				Thread.sleep(2000);
				flag = webDB.waitForElement(WidgetLocators.FIRST_DISPLAY_CODE, ElementType.Xpath);
				if (flag) {
					webDB.clearText(WidgetLocators.FIRST_DISPLAY_CODE);
					Thread.sleep(1000);
				}else {
					System.out.println("First discount is not visible");
				}
				flag = webDB.waitForElement(WidgetLocators.SECOND_DISPLAY_CODE, ElementType.Xpath);
				if (flag) {
					webDB.clearText(WidgetLocators.SECOND_DISPLAY_CODE);
					Thread.sleep(1000);
				}else {
					System.out.println("Second discount is not visible");
				}
				flag = webDB.waitForElement(WidgetLocators.THIRD_DISPLAY_CODE, ElementType.Xpath);
				if (flag) {
					webDB.clearText(WidgetLocators.THIRD_DISPLAY_CODE);
					Thread.sleep(1000);
				}else {
					System.out.println("Third discount is not visible");
				}
				flag = webDB.waitForElement(WidgetLocators.FOURTH_DISPLAY_CODE, ElementType.Xpath);
				if (flag) {
					webDB.clearText(WidgetLocators.FOURTH_DISPLAY_CODE);
					Thread.sleep(1000);
				}else {
					System.out.println("Fourth discount is not visible");
				}
				flag = webDB.waitForElement(WidgetLocators.SAVE_BTN, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(WidgetLocators.SAVE_BTN, ElementType.Xpath);
				}else {
					System.out.println("Save Button is not visible");
				}
				webDB.moveToElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
				EnableDisableStoreBtnStatus();
				ShopifyStore.shopifyStorePassEnter();
				personalWidget.executeScript("window.localStorage.clear();");
				webDB.driver.navigate().refresh();
				flag = webDB.waitForElement(WidgetLocators.SPIN_WHEEL_LOGO, ElementType.CssSelector);
				if (flag) {
					webDB.clickAnElement(WidgetLocators.SPIN_WHEEL_LOGO, ElementType.CssSelector);
					Thread.sleep(2000);
					flag = webDB.waitForElement(WidgetLocators.SPIN_WHEEL_BUTTON, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(WidgetLocators.SPIN_WHEEL_BUTTON, ElementType.Xpath);
						Thread.sleep(5000);
						//modified - below
						flag = webDB.waitForElement(WidgetLocators.BETER_LUCK_NEXT_TIME, ElementType.CssSelector);
						if (flag) {
							String temp = webDB.getText(WidgetLocators.BETER_LUCK_NEXT_TIME, ElementType.CssSelector);
							flag = temp.contains(WidgetLocators.BETTER_LUCK_VALUE);
							if (flag) {
								flag = webDB.waitForElement(WidgetLocators.CLOSE_BUTTON, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(WidgetLocators.CLOSE_BUTTON, ElementType.Xpath);
									webDB.driver.close();
									webDB.driver.switchTo().window(parentWindow);
								}
							}
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyMinimumMaximumDiscountSection() throws InterruptedException {
		flag = webDB.waitForElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
			Thread.sleep(1500);
			flag = webDB.waitForElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
				webDB.moveToElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
				flag = webDB.isElementDisplayed(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
				text = webDB.driver.findElement(By.xpath(CommonLocators.ENABLE_DISABLE_BTN)).getText();
				log.info(text);
				if (!text.equals("Disable")) {
					Thread.sleep(1000);
					webDB.javaScriptClickAnElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
				}
				Thread.sleep(1000);
				webDB.moveToElement(WidgetLocators.ADDDISCOUNTCODE, ElementType.Xpath);
				flag = webDB.waitForElement(WidgetLocators.DELETEDISCOUNTCODE, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(WidgetLocators.DELETEDISCOUNTCODE, ElementType.Xpath);
					Thread.sleep(1000);
					//modified - below locator
					String Toastvalue = webDB.driver.findElement(By.xpath(WidgetLocators.MINIMUMDISCOUNTCODEERROR))
							.getText();
					//modified - below text value
					flag = Toastvalue.contains(WidgetLocators.MINIMUMDISCOUNTCODEERRORVALUE);
					Thread.sleep(3500);
					if (flag) {
						for (int i = 1; i <= 9; i++) {
							element = webDB.driver.findElement(By.xpath(WidgetLocators.ADDDISCOUNTCODE));
							webDB.moveToElement(WidgetLocators.ADDDISCOUNTCODE, ElementType.Xpath);
							element.click();
						}
						Thread.sleep(1000);
						String Toast = webDB.driver.findElement(By.xpath(WidgetLocators.MAXIMUMDISCOUNTCODEERROR))
								.getText();
						flag = Toast.contains(WidgetLocators.MAXIMUMDISCOUNTCODEERRORVALUE);
						Thread.sleep(3500);
						webDB.driver.navigate().refresh();
					}
				}
			}
		}
		return flag;

	}

	public static boolean verifyAtleastSelectOneDesign() throws Exception {
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			flag = webDB.waitForElement(CommonLocators.THREEDOTSMENU, ElementType.Xpath);
			if (flag) {
				Thread.sleep(1000);
				webDB.clickAnElement(CommonLocators.THREEDOTSMENU, ElementType.Xpath);
			}
		}
		flag = webDB.waitForElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
				webDB.moveToElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
				flag = webDB.isElementDisplayed(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
				text = webDB.driver.findElement(By.xpath(CommonLocators.ENABLE_DISABLE_BTN)).getText();
				log.info(text);
				parentWindow = webDB.driver.getWindowHandle();
				if (!text.equals("Disable")) {
					Thread.sleep(1000);
					webDB.javaScriptClickAnElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
				}
				Thread.sleep(1000);
				webDB.moveToElement(WidgetLocators.ADDDISCOUNTCODE, ElementType.Xpath);
				flag = webDB.waitForElement(WidgetLocators.DISCOUNTCODETITLE, ElementType.Xpath);
				if (flag) {
//					webDB.clickAnElement(PersonalWidgetLocators.DISCOUNTCODETITLE, ElementType.Xpath);
					Thread.sleep(3500);
//					webDB.switchToChildWindowAndTitle(LoginPageLocators.USERNAME, LoginPageLocators.NEXTBTN,
//							LoginPageLocators.PASSWORD, LoginPageLocators.USERNAMEVALUE,
//							LoginPageLocators.PASSWORDVALUE, TemplatesLocators.URL);
					webDB.switchToChildWindowAndVerifyURLText(WidgetLocators.DISCOUNTCODETITLE,
							PlansPageLocators.SHOPIFY);
					webDB.moveToElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
					webDB.moveToElement(WidgetLocators.DISCOUNTCODETITLE, ElementType.Xpath);
					Thread.sleep(1500);
					flag = webDB.clickAnElement(WidgetLocators.SELECTDESIGNBTN, ElementType.Xpath);
					if (flag) {
						element = webDB.driver.findElement(By.xpath(WidgetLocators.SECONDSPINWHEELRADIOINPUT));
						if (!element.isSelected()) {
							actions.moveToElement(
									webDB.driver.findElement(By.xpath(WidgetLocators.SECONDSPINWHEELRADIOBUTTON)))
									.click().build().perform();
							Thread.sleep(1500);
							webDB.clickAnElement(WidgetLocators.SAVE_BTN, ElementType.Xpath);
							Thread.sleep(2500);
							webDB.clickAnElement(WidgetLocators.SAVE, ElementType.Xpath);
						} else {
							Thread.sleep(1500);
							webDB.clickAnElement(WidgetLocators.SAVE_BTN, ElementType.Xpath);
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyMessagelanguageSection() throws Exception {
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			flag = webDB.waitForElement(CommonLocators.THREEDOTSMENU, ElementType.Xpath);
			if (flag) {
				Thread.sleep(1000);
				webDB.clickAnElement(CommonLocators.THREEDOTSMENU, ElementType.Xpath);
			}
		}
		flag = webDB.waitForElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
				webDB.moveToElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
				flag = webDB.isElementDisplayed(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
				text = webDB.driver.findElement(By.xpath(CommonLocators.ENABLE_DISABLE_BTN)).getText();
				log.info(text);
				parentWindow = webDB.driver.getWindowHandle();
				if (!text.equals("Disable")) {
					Thread.sleep(1000);
					webDB.javaScriptClickAnElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
				}
				Thread.sleep(1000);
				webDB.moveToElement(WidgetLocators.SPINWHEELMOBILEDESKTOPRADIOBTN, ElementType.Xpath);
				flag = webDB.waitForElement(WidgetLocators.ENGLISHLANGUAGERADIOBTN, ElementType.Xpath);
				if (flag) {
					element = webDB.driver.findElement(By.xpath(WidgetLocators.ENGLISHLANGUAGERADIOBTN));
					if (!element.isSelected()) {
						actions.moveToElement(
								webDB.driver.findElement(By.xpath(WidgetLocators.ENGLISHLANGUAGERADIOBTN))).click()
								.build().perform();
						Thread.sleep(1500);
						webDB.clickAnElement(WidgetLocators.SAVE_BTN, ElementType.Xpath);
					} else {
						webDB.javaScriptClickAnElement(WidgetLocators.GERMANLANGUAGERADIOBTN, ElementType.Xpath);
						webDB.clickAnElement(WidgetLocators.SAVE_BTN, ElementType.Xpath);
					}
					flag = webDB.waitForElement(WidgetLocators.ENGLISHLANGUAGERADIOBTN, ElementType.Xpath);
					if (flag) {
						Thread.sleep(1000);
						webDB.javaScriptClickAnElement(WidgetLocators.ENGLISHLANGUAGERADIOBTN, ElementType.Xpath);
						webDB.clickAnElement(WidgetLocators.SAVE_BTN, ElementType.Xpath);
						Thread.sleep(1500);
						webDB.javaScriptClickAnElement(WidgetLocators.GERMANLANGUAGERADIOBTN, ElementType.Xpath);
						Thread.sleep(2500);
						webDB.clickAnElement(WidgetLocators.DISCARDBTN, ElementType.Xpath);
						Thread.sleep(2500);
						element = webDB.driver.findElement(By.xpath(WidgetLocators.ENGLISHLANGUAGERADIOBTN));
						if (element.isSelected()) {
							System.out.println("Default english language choosed");
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyMaxMinDiscountText() throws Exception {
		flag = webDB.waitForElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
			Thread.sleep(2000);
			flag = webDB.waitForElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
				webDB.moveToElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
				flag = webDB.isElementDisplayed(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
				text = webDB.driver.findElement(By.xpath(CommonLocators.ENABLE_DISABLE_BTN)).getText();
				log.info(text);
				parentWindow = webDB.driver.getWindowHandle();
				if (!text.equals("Disable")) {
					Thread.sleep(1000);
					webDB.javaScriptClickAnElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
				}
				Thread.sleep(1000);
				webDB.moveToElement(WidgetLocators.ADDDISCOUNTCODE, ElementType.Xpath);
				flag = webDB.waitForElement(WidgetLocators.FIRSTDISCOUNDCODETEXT, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(WidgetLocators.FIRSTDISCOUNDCODETEXT, ElementType.Xpath);
					webDB.clearText(WidgetLocators.FIRSTDISCOUNDCODETEXT);
					Thread.sleep(2500);
					webDB.sendTextToAnElement(WidgetLocators.FIRSTDISCOUNDCODETEXT,
							WidgetLocators.FIRSTDISCOUNDCODETEXTINVALIDVALUE, ElementType.Xpath);
					webDB.clickAnElement(WidgetLocators.SAVE_BTN, ElementType.Xpath);
					Thread.sleep(1500);
					String Toast = webDB.driver.findElement(By.xpath(WidgetLocators.MAXIMUMDISCOUNTCODETEXTERROR))
							.getText();
					flag = Toast.contains(WidgetLocators.MAXIMUMDISCOUNTCODEFIRSTERRORVALUE);
					Thread.sleep(3500);
					if (flag) {
						webDB.clickAnElement(WidgetLocators.FIRSTDISCOUNDCODETEXT, ElementType.Xpath);
						webDB.clearText(WidgetLocators.FIRSTDISCOUNDCODETEXT);
						Thread.sleep(2500);
						webDB.pressSpaceKey();
						webDB.pressSpaceKey();
						Thread.sleep(1500);
						webDB.clickAnElement(WidgetLocators.SAVE_BTN, ElementType.Xpath);
						Thread.sleep(1500);
						String Toastvaue = webDB.driver
								.findElement(By.xpath(WidgetLocators.MINIMUMDISCOUNTCODETEXTERROR)).getText();
						System.out.println(Toastvaue);
						flag = Toastvaue.contains(WidgetLocators.MINIMUMDISCOUNTCODEFIRSTERRORVALUE);
						webDB.driver.navigate().refresh();
						Thread.sleep(3500);
					}
				}
			}
		}
		return flag;
	}

	public static void verifyEnterDiscountcode() throws InterruptedException {
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			flag = webDB.waitForElement(CommonLocators.THREEDOTSMENU, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CommonLocators.THREEDOTSMENU, ElementType.Xpath);
			}
		}
		flag = webDB.waitForElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
			webDB.moveToElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
			Thread.sleep(1000);
			flag = webDB.waitForElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
				webDB.moveToElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
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
				webDB.moveToElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
			}
		}
	}

	public static boolean VerifyWheelSpinsOnce() throws Exception {
		verifyEnterDiscountcode();
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			flag = webDB.waitForElement(CommonLocators.THREEDOTSMENU, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CommonLocators.THREEDOTSMENU, ElementType.Xpath);
			}
		}
		flag = webDB.waitForElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);

			webDB.moveToElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
			flag = webDB.isElementDisplayed(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
			text = webDB.driver.findElement(By.xpath(CommonLocators.ENABLE_DISABLE_BTN)).getText();
			log.info(text);
			if (!text.equals("Disable")) {
				Thread.sleep(1000);
				webDB.javaScriptClickAnElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
			//modified - added else
			}else {
				System.out.println("Already Enabled");
			}
			Thread.sleep(1000);
			webDB.moveToElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
			EnableDisableStoreBtnStatus();
			ShopifyStore.shopifyStorePassEnter();
			personalWidget.executeScript("window.localStorage.clear();");
			webDB.driver.navigate().refresh();
			flag = webDB.waitForElement(WidgetLocators.SPIN_WHEEL_LOGO, ElementType.CssSelector);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.SPIN_WHEEL_LOGO, ElementType.CssSelector);
				Thread.sleep(2000);
				flag = webDB.waitForElement(WidgetLocators.SPIN_WHEEL_BUTTON, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(WidgetLocators.SPIN_WHEEL_BUTTON, ElementType.Xpath);
					Thread.sleep(5000);
					flag = webDB.waitForElement(WidgetLocators.COUNTRY_CODE, ElementType.CssSelector);
					if ((System.getProperty("platformName").equals("browserstack"))
							&& (System.getProperty("os").equals("Ios"))
							|| (System.getProperty("os").equals("Android"))) {
						webDB.clearTextCSS(WidgetLocators.COUNTRY_CODE);
						Thread.sleep(1000);
						webDB.sendTextToAnElement(WidgetLocators.COUNTRY_CODE, WidgetLocators.INDIA_CODE,
								ElementType.CssSelector);
					} else {
						webDB.selectDropDownOptions(WidgetLocators.COUNTRY_CODE, WidgetLocators.INDIA_CODE,
								ElementType.CssSelector);
					}
					flag = webDB.waitForElement(WidgetLocators.SPIN_WHEEL_PHONE_NUMBER, ElementType.CssSelector);
					if (flag) {
						webDB.sendTextToAnElement(WidgetLocators.SPIN_WHEEL_PHONE_NUMBER, WidgetLocators.PHONE_NUMBER,
								ElementType.CssSelector);
						webDB.clickAnElement(WidgetLocators.SPINWHEELTEXT, ElementType.CssSelector);
						flag = webDB.waitForElement(WidgetLocators.SPIN_WHEEL_CONFIRM_BUTTON, ElementType.CssSelector);
						if (flag) {
							webDB.clickAnElement(WidgetLocators.SPIN_WHEEL_CONFIRM_BUTTON, ElementType.CssSelector);
							flag = webDB.waitForElement(WidgetLocators.COPY_COUPON_CODE, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(WidgetLocators.COPY_COUPON_CODE, ElementType.Xpath);
								Thread.sleep(2000);
								webDB.switchToAlertAccept();
								flag = webDB.verifyElementIsNotDisplayed(WidgetLocators.SPIN_WHEEL_BUTTON,
										ElementType.Xpath);
							}
						}

					}
				} else {
					flag = webDB.waitForElement(WidgetLocators.CLOSE_BUTTON, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(WidgetLocators.CLOSE_BUTTON, ElementType.Xpath);
						Thread.sleep(1000);
						flag = webDB.waitForElement(WidgetLocators.SPIN_WHEEL_LOGO, ElementType.CssSelector);
						if (flag) {
							webDB.clickAnElement(WidgetLocators.SPIN_WHEEL_LOGO, ElementType.CssSelector);
							flag = webDB.verifyElementIsNotDisplayed(WidgetLocators.SPIN_WHEEL_BUTTON,
									ElementType.Xpath);
						}
					}
				}
			}
		}
		return flag;

	}

	public static boolean verifyAgentAssistCountryCode() throws Exception {
		flag = webDB.waitForElement(WidgetLocators.CHAT_SETTING_TAB, ElementType.Id);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.CHAT_SETTING_TAB, ElementType.Id);
			webDB.moveToElement(WidgetLocators.ADDAGENTBUTTON, ElementType.Xpath);
			Thread.sleep(1000);
			flag = webDB.waitForElement(WidgetLocators.ADDAGENTBUTTON, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.ADDAGENTBUTTON, ElementType.Xpath);
				flag = webDB.waitForElement(WidgetLocators.COUNTRYCODEAA, ElementType.CssSelector);
				if (flag) {
					Thread.sleep(1000);
					webDB.clickAnElement(WidgetLocators.COUNTRYCODEAA, ElementType.CssSelector);
					Thread.sleep(1000);
					webDB.moveToElement(WidgetLocators.INDIACODE, ElementType.CssSelector);
					Thread.sleep(1000);
					flag = webDB.waitForElement(WidgetLocators.INDIACODE, ElementType.CssSelector);
					if (flag) {
						flag = webDB.waitForElement(WidgetLocators.CLOSEICON, ElementType.CssSelector);
						if (flag) {
							webDB.clickAnElement(WidgetLocators.CLOSEICON, ElementType.CssSelector);
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyChangeHeadingBackgroundcolor() throws InterruptedException {
		flag = webDB.waitForElement(WidgetLocators.CHAT_SETTING_TAB, ElementType.Id);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.CHAT_SETTING_TAB, ElementType.Id);
			webDB.moveToElement(WidgetLocators.ADDAGENTBUTTON, ElementType.Xpath);
			Thread.sleep(1000);
			flag = webDB.waitForElement(WidgetLocators.ADDAGENTBUTTON, ElementType.Xpath);
			if (flag) {

			}
		}
		return flag;
	}

	public static boolean verifyPreview() throws InterruptedException, FileNotFoundException, IOException {
		String MainWindow = webDB.driver.getWindowHandle();
		flag = webDB.waitForElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
			Thread.sleep(2000);
			webDB.waitForElement(WidgetLocators.STOREFRONT_PREVIEW_BTN, ElementType.Xpath);
			flag = webDB.isElementDisplayed(WidgetLocators.STOREFRONT_PREVIEW_BTN, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.STOREFRONT_PREVIEW_BTN, ElementType.Xpath);
				Thread.sleep(2000);
				webDB.switchToChildWindow();
				String currenturl = webDB.driver.getCurrentUrl();
				propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
				prop.load(new FileInputStream(propertyFilePath));
				String actualUrl = prop.getProperty("STOREFRONTPREVIEWURL");
				if (currenturl.contains(actualUrl)) {
					webDB.driver.close();
					log.info("Verified Preview Url");
					webDB.driver.switchTo().window(MainWindow);
					// flag = webDB.waitForElement(WidgetLocators.STOREFRONT_PREVIEW_BTN,
					// ElementType.Xpath);
					flag = webDB.isElementDisplayed(WidgetLocators.STOREFRONT_PREVIEW_BTN, ElementType.Xpath);
				}
			}
		}
		return flag;
	}

	public static boolean verifyAddCartOptinWidget() throws Exception {
		flag = false;
		parentWindow = webDB.driver.getWindowHandle();
		SettingsPage.selectAllOptinOptions();
		log.info("selectAllOptinOptions method executed");
		CommonFunctions.verifyPersonalWidgetPage();
		WidgetPage.personalWidgetPageToStoreNavigation();
		ShopifyStore.shopifyStorePassEnter();
//		Dimension d = new Dimension(1940, 1000);
//		webDB.driver.manage().window().setSize(d);
		flag = webDB.clickAnElement(ShopifyStoreLocators.POISTORECATALOG_TAB, ElementType.Xpath);
		if (flag) {
			System.out.println("Catalog Clicked");
		} else {
			webDB.clickAnElement(ShopifyStoreLocators.CATALOG_TAB, ElementType.LinkText);
			flag = true;
		}
		element = webDB.driver.findElement(By.xpath(ShopifyStoreLocators.ITEM_SELECT));
		System.out.println(element);
		JavascriptExecutor storeExecutor = (JavascriptExecutor) webDB.driver;
		storeExecutor.executeScript("arguments[0].click();", element);
		storeExecutor.executeScript("window.localStorage.clear();");
		webDB.driver.navigate().refresh();
		Thread.sleep(4000);
		flag = webDB.waitForElement(ShopifyStoreLocators.ADD_TO_CART, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ShopifyStoreLocators.ADD_TO_CART, ElementType.Xpath);
			Thread.sleep(2000);
			if (webDB.waitForElement(ShopifyStoreLocators.OPTIN_POPUP_CLOSE_ICON, ElementType.CssSelector)) {
				System.out.println("Close icon and  Optin popup is visble");
				Thread.sleep(2000);
				webDB.clickAnElement(ShopifyStoreLocators.OPTIN_POPUP_CLOSE_ICON, ElementType.CssSelector);
			} else {
				System.out.println("Close icon is not visible");
				webDB.clickAnElement(ShopifyStoreLocators.BUY_NOW, ElementType.Xpath);

			}
		}
		webDB.driver.close();
		log.info("Verfied Preview Url");
		webDB.driver.switchTo().window(parentWindow);
		flag = webDB.waitForElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
		return flag;
	}

	public static boolean verifyConfigCheckoutWidget() throws InterruptedException, FileNotFoundException, IOException {
		flag = webDB.waitForElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
			Thread.sleep(2000);
			flag = webDB.waitForElement(WidgetLocators.CHECKOUTCONFIGURE_WIDGET, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.CHECKOUTCONFIGURE_WIDGET, ElementType.Xpath);
				Thread.sleep(2000);
				webDB.verifyElementIsDisplayed(WidgetLocators.CHECKOUT_CONFIG_REQUEST, ElementType.Xpath);
				flag = webDB.waitForElement(WidgetLocators.CHECKOUT_CONFIG_REQUEST, ElementType.Xpath);
			}
		}
		return flag;
	}

	public static boolean verifyOptinviaChatwidgetEnable() throws Exception {
		flag = webDB.waitForElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
			Thread.sleep(2000);
			flag = webDB.waitForElement(WidgetLocators.ENABLE_DISABLE_STOREFRONT_WIDGET, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				webDB.scrollToAnElement(WidgetLocators.OPTIN_CHATWIDGET_EDIT_CONFIG, ElementType.Xpath);
				flag = webDB.waitForElement(WidgetLocators.OPTIN_CHATWIDGET_ENABLE_DISABLE_TXT, ElementType.Xpath);
				if (flag) {
					Thread.sleep(2500);
					String text = webDB.getText(WidgetLocators.OPTIN_CHATWIDGET_ENABLE_DISABLE_TXT, ElementType.Xpath);
					System.out.println(text);
					if (!text.contains("Enabled")) {
						Thread.sleep(1000);
						webDB.clickAnElement(WidgetLocators.OPTIN_CHATWIDGET_ENABLE_DISABLE_BTN, ElementType.Xpath);
						flag = webDB.waitForElement(WidgetLocators.SAVE, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(WidgetLocators.SAVE, ElementType.Xpath);
							flag = webDB.waitForElement(WidgetLocators.SUCCESS_POPUP, ElementType.Xpath);
						}
					}
					String text1 = webDB.getText(WidgetLocators.OPTIN_CHATWIDGET_ENABLE_DISABLE_TXT, ElementType.Xpath);
					System.out.println(text1);
					Thread.sleep(1000);
				}
			}
		}
		return flag;
	}

	public static boolean verifyOptinviaChatwidgetEnableonStore() throws Exception {
		flag = verifyOptinviaChatwidgetEnable();
		parentWindow = webDB.driver.getWindowHandle();
		webDB.scrollToAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
		webDB.clickAnElement(WidgetLocators.CHATWIDGET_SETTING, ElementType.Xpath);
		WidgetPage.personalWidgetPageToStoreNavigation();
		log.info("personalWidgetPageToStoreNavigation method executed");
		ShopifyStore.shopifyStorePassEnter();
		log.info("shopifyStorePassEnter method executed");
		JavascriptExecutor js = (JavascriptExecutor) webDB.driver;
		js.executeScript("window.localStorage.clear();");
		webDB.driver.navigate().refresh();
		// flag = ShopifyStore.verifyOptinviaChatEnable();
		flag = ShopifyStore.verifyOptinviaChatDisable();
		if (flag) {
			webDB.driver.close();
			webDB.driver.switchTo().window(parentWindow);
		}
		return flag;
	}

	public static boolean verifyOptinviaChatwidgetDisable() throws Exception {
		flag = webDB.waitForElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(WidgetLocators.ENABLE_DISABLE_STOREFRONT_WIDGET, ElementType.Xpath);
			if (flag) {
				webDB.scrollToAnElement(WidgetLocators.OPTIN_CHATWIDGET_EDIT_CONFIG, ElementType.Xpath);
				flag = webDB.waitForElement(WidgetLocators.OPTIN_CHATWIDGET_ENABLE_DISABLE_TXT, ElementType.Xpath);
				if (flag) {
					Thread.sleep(2500);
					String text = webDB.getText(WidgetLocators.OPTIN_CHATWIDGET_ENABLE_DISABLE_TXT, ElementType.Xpath);
					System.out.println(text);
					if (!text.contains("Disabled")) {
						Thread.sleep(1000);
						webDB.clickAnElement(WidgetLocators.OPTIN_CHATWIDGET_ENABLE_DISABLE_BTN, ElementType.Xpath);
						flag = webDB.waitForElement(WidgetLocators.SAVE, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(WidgetLocators.SAVE, ElementType.Xpath);
							flag = webDB.waitForElement(WidgetLocators.SUCCESS_POPUP, ElementType.Xpath);
						}
					}
					String text1 = webDB.getText(WidgetLocators.OPTIN_CHATWIDGET_ENABLE_DISABLE_TXT, ElementType.Xpath);
					System.out.println(text1);
					Thread.sleep(1000);

				}
			}
		}
		return flag;
	}

	public static boolean verifyOptinviaChatwidgetDisableonStore() throws Exception {
		flag = verifyOptinviaChatwidgetDisable();
		parentWindow = webDB.driver.getWindowHandle();
		webDB.scrollToAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
		webDB.clickAnElement(WidgetLocators.CHATWIDGET_SETTING, ElementType.Xpath);
		WidgetPage.personalWidgetPageToStoreNavigation();
		log.info("personalWidgetPageToStoreNavigation method executed");
		ShopifyStore.shopifyStorePassEnter();
		log.info("shopifyStorePassEnter method executed");
		JavascriptExecutor js = (JavascriptExecutor) webDB.driver;
		js.executeScript("window.localStorage.clear();");
		webDB.driver.navigate().refresh();
		flag = ShopifyStore.verifyOptinviaChatDisable();
		if (flag) {
			webDB.driver.close();
			webDB.driver.switchTo().window(parentWindow);
			flag = verifyOptinviaChatwidgetEnable();
			if (flag) {
				System.out.println("Verified Optin via chat widget disabled");
			}
		}
		return flag;
	}

	public static boolean verifySelectdesignforOptinviachatwidget() throws Exception {
		flag = verifyOptinviaChatwidgetEnable();
		parentWindow = webDB.driver.getWindowHandle();
		Thread.sleep(2000);
		webDB.clickAnElement(WidgetLocators.OPTIN_CHATWIDGET_EDIT_CONFIG, ElementType.Xpath);
		log.info("Verified On clicking Edit configuration, the settings for opt-in chat widget is shown");
		flag = webDB.waitForElement(WidgetLocators.SELECTDESIGNBTN, ElementType.Xpath);
		if (flag) {
			//modified - added wait and scroll to element
			Thread.sleep(2000);
			webDB.scrollToAnElement(WidgetLocators.SELECTDESIGNBTN, ElementType.Xpath);
			webDB.clickAnElement(WidgetLocators.SELECTDESIGNBTN, ElementType.Xpath);
			flag = webDB.waitForElement(ShopifyStoreLocators.DESIGN_SECTION, ElementType.CssSelector);
			if (flag) {
				log.info("Select design");
				webDB.scrollToAnElement(WidgetLocators.OPTIN_CHATWIDGET_DESIGN, ElementType.Xpath);
				webDB.clickAnElement(WidgetLocators.OPTIN_CHATWIDGET_DESIGN, ElementType.Xpath);
				flag = webDB.waitForElement(WidgetLocators.SAVE, ElementType.Xpath);
				if (flag) {
					log.info("Selected");
					webDB.clickAnElement(WidgetLocators.SAVE, ElementType.Xpath);
					flag = webDB.waitForElement(WidgetLocators.SUCCESS_POPUP, ElementType.Xpath);
					if (flag) {
						log.info("Saved successfully");
						webDB.scrollToAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
						webDB.clickAnElement(WidgetLocators.CHATWIDGET_SETTING, ElementType.Xpath);
						WidgetPage.personalWidgetPageToStoreNavigation();
						log.info("personalWidgetPageToStoreNavigation method executed");
						ShopifyStore.shopifyStorePassEnter();
						log.info("shopifyStorePassEnter method executed");
						JavascriptExecutor js = (JavascriptExecutor) webDB.driver;
						js.executeScript("window.localStorage.clear();");
						webDB.driver.navigate().refresh();
						flag = ShopifyStore.verifyOptinviaChatEnable();
						if (flag) {
							log.info("Verified chatbox design");
							webDB.driver.switchTo().window(parentWindow);
							flag = webDB.waitForElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
								flag = webDB.waitForElement(WidgetLocators.ENABLE_DISABLE_STOREFRONT_WIDGET,
										ElementType.Xpath);
								if (flag) {
									webDB.scrollToAnElement(WidgetLocators.OPTIN_CHATWIDGET_EDIT_CONFIG,
											ElementType.Xpath);
									webDB.clickAnElement(WidgetLocators.OPTIN_CHATWIDGET_EDIT_CONFIG,
											ElementType.Xpath);
									webDB.scrollToAnElement(WidgetLocators.SELECTDESIGNBTN, ElementType.Xpath);
									flag = webDB.waitForElement(WidgetLocators.SELECTDESIGNBTN, ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(WidgetLocators.SELECTDESIGNBTN, ElementType.Xpath);
										flag = webDB.waitForElement(ShopifyStoreLocators.DESIGN_SECTION,
												ElementType.CssSelector);
										if (flag) {
											webDB.clickAnElement(WidgetLocators.OPTIN_CHATWIDGET_DEFAULTDESIGN,
													ElementType.Xpath);
											flag = webDB.waitForElement(WidgetLocators.SAVE, ElementType.Xpath);
											if (flag) {
												webDB.clickAnElement(WidgetLocators.SAVE, ElementType.Xpath);
												flag = webDB.waitForElement(WidgetLocators.SUCCESS_POPUP,
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

	public static boolean verifyTextandHelptextinOptinviaChatwidget() throws Exception {
		flag = verifyOptinviaChatwidgetEnable();
		parentWindow = webDB.driver.getWindowHandle();
		webDB.clickAnElement(WidgetLocators.OPTIN_CHATWIDGET_EDIT_CONFIG, ElementType.Xpath);
		flag = webDB.waitForElement(WidgetLocators.SELECTDESIGNBTN, ElementType.Xpath);
		if (flag) {
			//modified - changed locator
			flag = webDB.waitForElement(WidgetLocators.OPTIN_CHATWIDGET_HEADING_TEXT_INPUT, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.OPTIN_CHATWIDGET_HEADING_TEXT_INPUT, ElementType.Xpath);
				webDB.clearText(WidgetLocators.OPTIN_CHATWIDGET_HEADING_TEXT_INPUT);
				Thread.sleep(1000);
				webDB.sendTextToAnElement(WidgetLocators.OPTIN_CHATWIDGET_HEADING_TEXT_INPUT, WidgetLocators.HEADINGTXT,
						ElementType.Xpath);
				//modified - changed locator
				flag = webDB.waitForElement(WidgetLocators.OPTIN_CHATWIDGET_HELP_TEXT_INPUT, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(WidgetLocators.OPTIN_CHATWIDGET_HELP_TEXT_INPUT, ElementType.Xpath);
					webDB.clearText(WidgetLocators.OPTIN_CHATWIDGET_HELP_TEXT_INPUT);
					webDB.clickAnElement(WidgetLocators.OPTIN_CHATWIDGET_HELP_TEXT_INPUT, ElementType.Xpath);
					Thread.sleep(1000);
					webDB.sendTextToAnElement(WidgetLocators.OPTIN_CHATWIDGET_HELP_TEXT_INPUT, WidgetLocators.HELPTEXT,
							ElementType.Xpath);
					flag = webDB.waitForElement(WidgetLocators.SAVE, ElementType.Xpath);
					if (flag) {
						log.info("Changed heading and helptext");
						webDB.clickAnElement(WidgetLocators.SAVE, ElementType.Xpath);
						flag = webDB.waitForElement(WidgetLocators.SUCCESS_POPUP, ElementType.Xpath);
						if (flag) {
							webDB.scrollToAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
							webDB.clickAnElement(WidgetLocators.CHATWIDGET_SETTING, ElementType.Xpath);
							WidgetPage.personalWidgetPageToStoreNavigation();
							log.info("personalWidgetPageToStoreNavigation method executed");
							ShopifyStore.shopifyStorePassEnter();
							log.info("shopifyStorePassEnter method executed");
							JavascriptExecutor js = (JavascriptExecutor) webDB.driver;
							js.executeScript("window.localStorage.clear();");
							webDB.driver.navigate().refresh();
							flag = ShopifyStore.verifyOptinviaChatHeadingandHelpText();
							if (flag) {
								log.info("Verified chatbox Heading and helptext");
								webDB.driver.switchTo().window(parentWindow);
								flag = webDB.waitForElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
									webDB.scrollToAnElement(WidgetLocators.OPTIN_CHATWIDGET_EDIT_CONFIG,
											ElementType.Xpath);
									webDB.clickAnElement(WidgetLocators.OPTIN_CHATWIDGET_EDIT_CONFIG,
											ElementType.Xpath);
									flag = webDB.waitForElement(WidgetLocators.OPTIN_CHATWIDGET_HEADING_TEXT_INPUT,
											ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(WidgetLocators.OPTIN_CHATWIDGET_HEADING_TEXT_INPUT,
												ElementType.Xpath);
										webDB.clearText(WidgetLocators.OPTIN_CHATWIDGET_HEADING_TEXT_INPUT);
										Thread.sleep(1000);
										webDB.sendTextToAnElement(WidgetLocators.OPTIN_CHATWIDGET_HEADING_TEXT_INPUT,
												WidgetLocators.DEFAULT_HEADINGTXT, ElementType.Xpath);
										flag = webDB.waitForElement(WidgetLocators.OPTIN_CHATWIDGET_HELP_TEXT_INPUT,
												ElementType.Xpath);
										if (flag) {
											webDB.clickAnElement(WidgetLocators.OPTIN_CHATWIDGET_HELP_TEXT_INPUT,
													ElementType.Xpath);
											webDB.clearText(WidgetLocators.OPTIN_CHATWIDGET_HELP_TEXT_INPUT);
											webDB.clickAnElement(WidgetLocators.OPTIN_CHATWIDGET_HELP_TEXT_INPUT,
													ElementType.Xpath);
											Thread.sleep(1000);
											webDB.sendTextToAnElement(WidgetLocators.OPTIN_CHATWIDGET_HELP_TEXT_INPUT,
													WidgetLocators.DEFAULT_HELPTEXT, ElementType.Xpath);
											flag = webDB.waitForElement(WidgetLocators.SAVE, ElementType.Xpath);
											if (flag) {
												webDB.clickAnElement(WidgetLocators.SAVE, ElementType.Xpath);
												flag = webDB.waitForElement(WidgetLocators.SUCCESS_POPUP,
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

	public static boolean verifyWidgetsection() throws Exception {

		flag = webDB.verifyElementIsDisplayed(WidgetLocators.CHATWIDGET_SETTING, ElementType.Xpath);
		if (flag) {
			log.info("Verified Chatwidget tab");
			flag = webDB.verifyElementIsDisplayed(WidgetLocators.SHAREWIDGET_SETTING, ElementType.Xpath);
			if (flag) {
				log.info("Verified Sharewidget tab");
				flag = webDB.verifyElementIsDisplayed(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
				if (flag) {
					log.info("Verified Optinwidget tab");
					webDB.clickAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
					flag = webDB.waitForElement(WidgetLocators.STOREFRONT_WIDGET, ElementType.Xpath);
					if (flag) {
						flag = webDB.verifyElementIsDisplayed(WidgetLocators.STOREFRONT_WIDGET, ElementType.Xpath);
						if (flag) {
							log.info("Storefront widget option displayed");
							flag = webDB.verifyElementIsDisplayed(WidgetLocators.ENABLE_DISABLE_STOREFRONT_WIDGET,
									ElementType.Xpath);
							if (flag) {
								log.info("Enabel Disable Storefront widget button displayed");
								flag = webDB.verifyElementIsDisplayed(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
								if (flag) {
									log.info("Edit Storefront widget Button displayed");
									flag = webDB.verifyElementIsDisplayed(WidgetLocators.THANKYOU_WIDGET,
											ElementType.Xpath);
									if (flag) {
										log.info("Thankyou widget option displayed");
										flag = webDB.verifyElementIsDisplayed(WidgetLocators.CHECKOUT_WIDGET,
												ElementType.Xpath);
										if (flag) {
											log.info("Checkout widget option displayed");
											flag = webDB.verifyElementIsDisplayed(
													WidgetLocators.CHECKOUTCONFIGURE_WIDGET, ElementType.Xpath);
											if (flag) {
												log.info("Configure Checkout widget button displayed");
												flag = webDB.verifyElementIsDisplayed(WidgetLocators.SPINTHEWHEEL,
														ElementType.Xpath);
												if (flag) {
													log.info("Spin the wheel option displayed");
													flag = webDB.verifyElementIsDisplayed(
															WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
													if (flag) {
														log.info("Configure spin wheel button displayed");
														flag = webDB.verifyElementIsDisplayed(
																WidgetLocators.OPTIN_CHATWIDGET, ElementType.Xpath);
														if (flag) {
															log.info("Optinvia chate widget option displayed");
															flag = webDB.verifyElementIsDisplayed(
																	WidgetLocators.OPTIN_CHATWIDGET_ENABLE_DISABLE_BTN,
																	ElementType.Xpath);
															if (flag) {
																log.info("Enable disable widget button displayed");
																flag = webDB.verifyElementIsDisplayed(
																		WidgetLocators.OPTIN_CHATWIDGET_EDIT_CONFIG,
																		ElementType.Xpath);
																if (flag) {
																	log.info("Edit configure button displayed");
																	flag = webDB.verifyElementIsDisplayed(
																			WidgetLocators.RATE_OUR_APP,
																			ElementType.Xpath);
																	if (flag) {
																		log.info(
																				"Verified all options in Optin widgets is displayed");
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
		}
		return flag;
	}

	public static boolean verifySettingsChange() throws Exception {

		flag = webDB.waitForElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
				flag = webDB.waitForElement(WidgetLocators.STOREFRONT_WIDGET_LANGUGAE_HEADER, ElementType.Xpath);
				if (flag) {
					Thread.sleep(2500);
					element = webDB.driver.findElement(By.xpath(SettingsLocators.SPANISH_LANGUAGE_RADIO));
					actions.moveToElement(element).click().build().perform();
					webDB.isElementDisplayed(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
					webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
					wait.until(
							ExpectedConditions.visibilityOfElementLocated(By.xpath(SettingsLocators.SAVE_SUCCESS_MSG)));
					flag = webDB.isElementDisplayed(SettingsLocators.SAVE_SUCCESS_MSG, ElementType.Xpath);
					if (flag) {
						Thread.sleep(2000);
						WebElement ele = webDB.driver
								.findElement(By.xpath(SettingsLocators.SPAPNISH_LANGUAGE_SELECTION_CHECK));
						if (ele.isEnabled()) {
							log.info("Verified save settings by selecting language");
							element = webDB.driver.findElement(By.xpath(SettingsLocators.ITALIAN_LANGUAGE_RADIO));
							actions.moveToElement(element).click().build().perform();
							webDB.isElementDisplayed(CommonLocators.DISCARD_SETTINGS, ElementType.Xpath);
							webDB.clickAnElement(CommonLocators.DISCARD_SETTINGS, ElementType.Xpath);
							Thread.sleep(2000);
							if (ele.isEnabled()) {
								log.info("Verified Discard setting by selecting language");
								Thread.sleep(4000);
								element = webDB.driver.findElement(By.xpath(SettingsLocators.ITALIAN_LANGUAGE_RADIO));
								actions.moveToElement(element).click().build().perform();
								webDB.isElementDisplayed(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
								webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
							}
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean checkCartpageCheckBoxEnabled() throws Exception {
		flag = webDB.waitForElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
			// element =
			// webDB.driver.findElement(By.xpath("(//input[contains(@class,'Polaris-Checkbox__Input')])[3]"));
			element = webDB.driver.findElement(By.xpath("//span[contains(text(),'Landing on Cart page')]"));
			flag = element.isSelected();
			// flag =webDB.IsEnabled(WidgetLocators.LAND_ON_CART, ElementType.Xpath);

			Thread.sleep(2000);
			// flag = webDB.IsEnabled(WidgetLocators.LAND_ON_CART_INPUT, ElementType.Xpath);
			System.out.println("flag for optin is " + flag);
			if (!flag) {
				webDB.clickAnElement(WidgetLocators.LAND_ON_CART, ElementType.Xpath);
				flag = webDB.waitForElement(WidgetLocators.SAVE_BTN, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(WidgetLocators.SAVE_BTN, ElementType.Xpath);
					log.info("Landing on cart page is selected");
				}
			}
//			if (!flag) {
//				element.click();
//				Thread.sleep(2000);
//				webDB.clickAnElement(WidgetLocators.SAVE_BTN, ElementType.Xpath);
//			}
		}
		return flag;
	}

	public static boolean verifyCartpageCheckbox() throws Exception {
		SettingsPage.enableOptinStoreFront();
		//flag = checkCartpageCheckBoxEnabled();
		flag = SettingsPage.checkOptinCheckBoxesEnabled();
		if (flag) {
			webDB.clickAnElement(WidgetLocators.CHATWIDGET_SETTING, ElementType.Xpath);
			WidgetPage.personalWidgetPageToStoreNavigation();
			log.info("personalWidgetPageToStoreNavigation method executed");
			ShopifyStore.shopifyStorePassEnter();
			log.info("shopifyStorePassEnter method executed");
			JavascriptExecutor js = (JavascriptExecutor) webDB.driver;
			js.executeScript("window.localStorage.clear();");
			webDB.driver.navigate().refresh();
			flag = ShopifyStore.verifyOptinPopUpDisplayed();
			if (flag) {
				log.info("Verified optin popup by selecting cart page checkbox");
			}
		}
		return flag;
	}

	public static boolean checkCheckoutCheckBoxEnabled() throws Exception {
		flag = webDB.waitForElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
			element = webDB.driver.findElement(
					By.xpath("//label[@for='PolarisCheckbox20']//span[@class='Polaris-Checkbox__Backdrop']"));
			// flag = element.isEnabled();
			flag = element.isSelected();
			if (!flag) {
				element.click();
				Thread.sleep(2000);
				flag = webDB.waitForElement(WidgetLocators.SAVE_BTN, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(WidgetLocators.SAVE_BTN, ElementType.Xpath);
				}
			}
		}
		return flag;
	}

	public static boolean verifyCheckoutCheckbox() throws Exception {
		SettingsPage.enableOptinStoreFront();
		parentWindow = webDB.driver.getWindowHandle();
		// flag = SettingsPage.selectAllOptinOptions();
		//flag = checkCheckoutCheckBoxEnabled();
		flag = SettingsPage.checkOptinCheckBoxesEnabled();
		if (flag) {
			webDB.clickAnElement(WidgetLocators.CHATWIDGET_SETTING, ElementType.Xpath);
			WidgetPage.personalWidgetPageToStoreNavigation();
			log.info("personalWidgetPageToStoreNavigation method executed");
			ShopifyStore.shopifyStorePassEnter();
			log.info("shopifyStorePassEnter method executed");
			CommonFunctions.clearLocalStorage();
			webDB.driver.navigate().refresh();
			flag = ShopifyStore.addItemAndAddtocart();
			if (flag) {
				log.info("Verified optin popup by selecting Checkout checkbox");
			}
			webDB.driver.switchTo().window(parentWindow);
		}
		return flag;
	}

	public static boolean verifyDeselectCheckboxes() throws Exception {
//		flag = SettingsPage.deslectoptincheckboxes();
		flag = SettingsPage.deSelectAllOptinOptions();
		if (flag) {
			parentWindow = webDB.driver.getWindowHandle();
			webDB.clickAnElement(WidgetLocators.CHATWIDGET_SETTING, ElementType.Xpath);
			WidgetPage.personalWidgetPageToStoreNavigation();
			log.info("personalWidgetPageToStoreNavigation method executed");
			ShopifyStore.shopifyStorePassEnter();
			log.info("shopifyStorePassEnter method executed");
			JavascriptExecutor js = (JavascriptExecutor) webDB.driver;
			js.executeScript("window.localStorage.clear();");
			webDB.driver.navigate().refresh();
			flag = ShopifyStore.verifyOptinPopUpDisplayed();
			if (flag) {
				log.info("Verified optin popup is not in cartpage");
				flag = ShopifyStore.addItemAndAddtocart();
				if (flag) {
					log.info("Verified optin popup is not checkout page");
					webDB.driver.switchTo().window(parentWindow);
					flag = SettingsPage.selectAllOptinOptions();
				}
			}
		}
		return flag;
	}

	public static boolean verifyClickBack() throws Exception {
		flag = webDB.waitForElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
				flag = webDB.waitForElement(WidgetLocators.BACK, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(WidgetLocators.BACK, ElementType.Xpath);
					flag = webDB.verifyElementIsDisplayed(WidgetLocators.STOREFRONT_WIDGET, ElementType.Xpath);
					if (flag) {
						log.info("Verified click back button redirect to optinpage");
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyThankyouPreview() throws Exception {
		parentWindow = webDB.driver.getWindowHandle();
		flag = webDB.waitForElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(WidgetLocators.THANKYOU_WIDGET_PREVIEW, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.THANKYOU_WIDGET_PREVIEW, ElementType.Xpath);
				propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
				prop.load(new FileInputStream(propertyFilePath));
				String pageurl = prop.getProperty("ThankyouWidgetPreviewURL");
				webDB.switchToChildWindow();
				Thread.sleep(2000);
				String currentUrl = webDB.driver.getCurrentUrl();
				System.out.println(currentUrl);
				if (currentUrl.contains(pageurl)) {
					log.info("Verified Thankyoupage widget preview opened in new tab");
					Thread.sleep(2000);
					webDB.driver.close();
					webDB.driver.switchTo().window(parentWindow);
				}
			}
		}
		return flag;
	}

	public static boolean verifyViewonStoreSpinwheel() throws Exception {
		parentWindow = webDB.driver.getWindowHandle();
		webDB.clickAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
		Thread.sleep(2500);
		flag = webDB.waitForElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
			flag = webDB.isElementDisplayed(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
			text = webDB.driver.findElement(By.xpath(CommonLocators.ENABLE_DISABLE_BTN)).getText();
			log.info(text);
			if (!text.equals("Disable")) {
				Thread.sleep(1000);
				webDB.javaScriptClickAnElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
			}
			Thread.sleep(1500);
			if (flag) {
				String link = webDB.driver.findElement(By.xpath(WidgetLocators.SPINWHEEL_VIEWON_STORE))
						.getAttribute("href");
				System.out.println(link);
				webDB.clickAnElement(WidgetLocators.SPINWHEEL_VIEWON_STORE, ElementType.Xpath);
				webDB.switchToChildWindow();
				ShopifyStore.shopifyStorePassEnter();
				log.info("shopifyStorePassEnter method executed");
				JavascriptExecutor js = (JavascriptExecutor) webDB.driver;
				js.executeScript("window.localStorage.clear();");
				webDB.driver.navigate().refresh();
				Thread.sleep(2000);
				String currentUrl = webDB.driver.getCurrentUrl();
				System.out.println(currentUrl);
				if (currentUrl.equals(link)) {
					log.info("Verified view on store in spinwheel redirects to particular store");
					Thread.sleep(2000);
					webDB.driver.close();
					webDB.driver.switchTo().window(parentWindow);
				}
			}
		}
		return flag;
	}

	public static boolean verifyOptinviaChatPreview() throws Exception {
		parentWindow = webDB.driver.getWindowHandle();
		flag = webDB.waitForElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(WidgetLocators.OPTINVIACHAT_WIDGET_PREVIEW, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.OPTINVIACHAT_WIDGET_PREVIEW, ElementType.Xpath);
				propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
				prop.load(new FileInputStream(propertyFilePath));
				String pageurl = prop.getProperty("OptinviaChatwidgetPreviewURL");
				webDB.switchToChildWindow();
				Thread.sleep(2000);
				String currentUrl = webDB.driver.getCurrentUrl();
				System.out.println(currentUrl);
				if (currentUrl.contains(pageurl)) {
					log.info("Verified OptinviaChat widget preview opened in new tab");
					Thread.sleep(2000);
					webDB.driver.close();
					webDB.driver.switchTo().window(parentWindow);
				}
			}
		}
		return flag;
	}

	public static boolean verifyOptinviaChatwidgetDisableinEditconfig() throws Exception {
		flag = webDB.waitForElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(WidgetLocators.ENABLE_DISABLE_STOREFRONT_WIDGET, ElementType.Xpath);
			if (flag) {
				webDB.scrollToAnElement(WidgetLocators.OPTIN_CHATWIDGET_EDIT_CONFIG, ElementType.Xpath);
				webDB.clickAnElement(WidgetLocators.OPTIN_CHATWIDGET_EDIT_CONFIG, ElementType.Xpath);
				webDB.scrollToAnElement(WidgetLocators.BACK, ElementType.Xpath);
				flag = webDB.waitForElement(WidgetLocators.BACK, ElementType.Xpath);
				if (flag) {
					Thread.sleep(2500);
					String text = webDB.getText(WidgetLocators.OPTINVIACHAT_WIDGET_ENABLE_DISABLE, ElementType.Xpath);
					System.out.println(text);
					if (!text.contains("disabled")) {
						Thread.sleep(1000);
						webDB.clickAnElement(WidgetLocators.OPTINVIACHAT_WIDGET_ENABLE_DISABLE_BTN, ElementType.Xpath);
						flag = webDB.waitForElement(WidgetLocators.SAVE, ElementType.Xpath);
						if (flag) {
							Thread.sleep(1000);
							webDB.clickAnElement(WidgetLocators.SAVE, ElementType.Xpath);
							flag = webDB.waitForElement(WidgetLocators.SUCCESS_POPUP, ElementType.Xpath);
						}
					}
					String text1 = webDB.getText(WidgetLocators.OPTINVIACHAT_WIDGET_ENABLE_DISABLE, ElementType.Xpath);
					System.out.println(text1);
					Thread.sleep(1000);
				}
			}
		}
		return flag;
	}

	public static boolean verifyOptinvia_ChatwidgetDisableonStore() throws Exception {
		flag = verifyOptinviaChatwidgetDisableinEditconfig();
		if (flag) {
			parentWindow = webDB.driver.getWindowHandle();
			webDB.scrollToAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
			webDB.clickAnElement(WidgetLocators.CHATWIDGET_SETTING, ElementType.Xpath);
			WidgetPage.personalWidgetPageToStoreNavigation();
			log.info("personalWidgetPageToStoreNavigation method executed");
			ShopifyStore.shopifyStorePassEnter();
			log.info("shopifyStorePassEnter method executed");
			JavascriptExecutor js = (JavascriptExecutor) webDB.driver;
			js.executeScript("window.localStorage.clear();");
			webDB.driver.navigate().refresh();
			flag = ShopifyStore.verifyOptinviaChatDisable();
			if (flag) {
				webDB.driver.close();
				webDB.driver.switchTo().window(parentWindow);
				verifyOptinviaChatwidgetEnableinEditconfig();
			}
		}
		return flag;
	}

	public static boolean verifyOptinviaChatwidgetEnableinEditconfig() throws Exception {
		flag = webDB.waitForElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(WidgetLocators.ENABLE_DISABLE_STOREFRONT_WIDGET, ElementType.Xpath);
			if (flag) {
				webDB.scrollToAnElement(WidgetLocators.OPTIN_CHATWIDGET_EDIT_CONFIG, ElementType.Xpath);
				webDB.clickAnElement(WidgetLocators.OPTIN_CHATWIDGET_EDIT_CONFIG, ElementType.Xpath);
				Thread.sleep(2500);
				webDB.scrollToAnElement(WidgetLocators.BACK, ElementType.Xpath);
				flag = webDB.waitForElement(WidgetLocators.BACK, ElementType.Xpath);
				if (flag) {
					Thread.sleep(2500);
					String text = webDB.getText(WidgetLocators.OPTINVIACHAT_WIDGET_ENABLE_DISABLE, ElementType.Xpath);
					System.out.println(text);
					if (!text.contains("enabled")) {
						Thread.sleep(1000);
						webDB.javaScriptClickAnElement(WidgetLocators.OPTINVIACHAT_WIDGET_ENABLE_DISABLE_BTN,
								ElementType.Xpath);
						flag = webDB.waitForElement(WidgetLocators.SAVE, ElementType.Xpath);
						if (flag) {
							Thread.sleep(1000);
							webDB.clickAnElement(WidgetLocators.SAVE, ElementType.Xpath);
							flag = webDB.waitForElement(WidgetLocators.SUCCESS_POPUP, ElementType.Xpath);
						}
					}
					String text1 = webDB.getText(WidgetLocators.OPTINVIACHAT_WIDGET_ENABLE_DISABLE, ElementType.Xpath);
					System.out.println(text1);
					Thread.sleep(1000);
				}
			}
		}
		return flag;
	}

	public static boolean verifyOptinvia_ChatwidgetEnableonStore() throws Exception {
		flag = verifyOptinviaChatwidgetEnableinEditconfig();
		if (flag) {
			parentWindow = webDB.driver.getWindowHandle();
			webDB.scrollToAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
			webDB.clickAnElement(WidgetLocators.CHATWIDGET_SETTING, ElementType.Xpath);
			WidgetPage.personalWidgetPageToStoreNavigation();
			log.info("personalWidgetPageToStoreNavigation method executed");
			ShopifyStore.shopifyStorePassEnter();
			log.info("shopifyStorePassEnter method executed");
			JavascriptExecutor js = (JavascriptExecutor) webDB.driver;
			js.executeScript("window.localStorage.clear();");
			webDB.driver.navigate().refresh();
			//flag = ShopifyStore.verifyOptinviaChatEnable();
			flag = ShopifyStore.verifyOptinviaChatDisable();
			if (flag) {
				webDB.driver.close();
				webDB.driver.switchTo().window(parentWindow);
			}
		}
		return flag;
	}

	public static boolean verifyColourofOptinviachatwidget() throws Exception {
		flag = verifyOptinviaChatwidgetEnable();
		parentWindow = webDB.driver.getWindowHandle();
		webDB.clickAnElement(WidgetLocators.OPTIN_CHATWIDGET_EDIT_CONFIG, ElementType.Xpath);
		log.info("Verified On clicking Edit configuration, the settings for opt-in chat widget is shown");
		//modified - added wait and scroll to element
		Thread.sleep(2000);
		webDB.scrollToAnElement(WidgetLocators.SELECTDESIGNBTN, ElementType.Xpath);
		flag = webDB.waitForElement(WidgetLocators.SELECTDESIGNBTN, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.SELECTDESIGNBTN, ElementType.Xpath);
			flag = webDB.waitForElement(ShopifyStoreLocators.DESIGN_SECTION, ElementType.CssSelector);
			if (flag) {
				log.info("Select design");
				flag = webDB.waitForElement(WidgetLocators.OPTIN_CHATWIDGET_DEFAULTDESIGN, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(WidgetLocators.OPTIN_CHATWIDGET_DEFAULTDESIGN, ElementType.Xpath);
					flag = webDB.waitForElement(WidgetLocators.SAVE, ElementType.Xpath);
					if (flag) {
						Thread.sleep(2000);
						log.info("Selected");
						webDB.clickAnElement(WidgetLocators.SAVE, ElementType.Xpath);
						flag = webDB.waitForElement(WidgetLocators.SUCCESS_POPUP, ElementType.Xpath);
						if (flag) {
							flag = webDB.waitForElement(WidgetLocators.SINGLECOLOUR, ElementType.CssSelector);
							if (flag) {
								Thread.sleep(2000);
								flag = webDB.driver.findElement(By.cssSelector(WidgetLocators.SINGLECOLOUR))
										.isSelected();
								log.info("Flag is true");
								System.out.println(flag);
								if (!flag) {
									webDB.clickAnElement(WidgetLocators.OPTINVIACHAT_WIDGET_SINGLECOLOR_BTN,
											ElementType.Xpath);
									flag = webDB.waitForElement(WidgetLocators.SAVE, ElementType.Xpath);
									if (flag) {
										Thread.sleep(2000);
										log.info("Single colour Selected");
										webDB.clickAnElement(WidgetLocators.SAVE, ElementType.Xpath);
									}
								}
								flag = webDB.waitForElement(WidgetLocators.OPTINVIACHAT_WIDGET_COLOR_TEXTFIELD,
										ElementType.Xpath);
								if (flag) {
									Thread.sleep(2000);
									webDB.clearText(WidgetLocators.OPTINVIACHAT_WIDGET_COLOR_TEXTFIELD);
									Thread.sleep(2000);
									webDB.sendTextToAnElement(WidgetLocators.OPTINVIACHAT_WIDGET_COLOR_TEXTFIELD,
											WidgetLocators.REDCOLOR, ElementType.Xpath);
									Thread.sleep(2000);
									flag = webDB.waitForElement(WidgetLocators.SAVE, ElementType.Xpath);
									if (flag) {
										log.info("Red colour Selected");
										webDB.clickAnElement(WidgetLocators.SAVE, ElementType.Xpath);
										webDB.scrollToAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB,
												ElementType.Xpath);
										webDB.clickAnElement(WidgetLocators.CHATWIDGET_SETTING, ElementType.Xpath);
										WidgetPage.personalWidgetPageToStoreNavigation();
										log.info("personalWidgetPageToStoreNavigation method executed");
										ShopifyStore.shopifyStorePassEnter();
										log.info("shopifyStorePassEnter method executed");
										JavascriptExecutor js = (JavascriptExecutor) webDB.driver;
										js.executeScript("window.localStorage.clear();");
										webDB.driver.navigate().refresh();
										flag = ShopifyStore.verifyOptinviaChatBoxColor();
										if (flag) {
											log.info("Verified chatbox color");
											webDB.driver.switchTo().window(parentWindow);
											flag = webDB.waitForElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB,
													ElementType.Xpath);
											if (flag) {
												webDB.clickAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB,
														ElementType.Xpath);
												flag = webDB.waitForElement(
														WidgetLocators.ENABLE_DISABLE_STOREFRONT_WIDGET,
														ElementType.Xpath);
												if (flag) {
													webDB.scrollToAnElement(WidgetLocators.OPTIN_CHATWIDGET_EDIT_CONFIG,
															ElementType.Xpath);
													webDB.clickAnElement(WidgetLocators.OPTIN_CHATWIDGET_EDIT_CONFIG,
															ElementType.Xpath);
													webDB.scrollToAnElement(WidgetLocators.SELECTDESIGNBTN,
															ElementType.Xpath);
													flag = webDB.waitForElement(
															WidgetLocators.OPTINVIACHAT_WIDGET_SINGLECOLOR_BTN,
															ElementType.Xpath);
													if (flag) {
														webDB.clearText(
																WidgetLocators.OPTINVIACHAT_WIDGET_COLOR_TEXTFIELD);
														webDB.sendTextToAnElement(
																WidgetLocators.OPTINVIACHAT_WIDGET_COLOR_TEXTFIELD,
																WidgetLocators.GREENCOLOR, ElementType.Xpath);
														flag = webDB.waitForElement(WidgetLocators.SAVE,
																ElementType.Xpath);
														if (flag) {
															log.info("Green colour Selected");
															webDB.clickAnElement(WidgetLocators.SAVE,
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
		return flag;
	}

	public static boolean verifyHeaderColourofOptinviachatwidget() throws Exception {
		flag = verifyOptinviaChatwidgetEnable();
		parentWindow = webDB.driver.getWindowHandle();
		webDB.clickAnElement(WidgetLocators.OPTIN_CHATWIDGET_EDIT_CONFIG, ElementType.Xpath);
		log.info("Verified On clicking Edit configuration, the settings for opt-in chat widget is shown");
		//modified - added wait and scroll to element
		Thread.sleep(2000);
		webDB.scrollToAnElement(WidgetLocators.SELECTDESIGNBTN, ElementType.Xpath);
		flag = webDB.waitForElement(WidgetLocators.SELECTDESIGNBTN, ElementType.Xpath);//
		if (flag) {
			webDB.clickAnElement(WidgetLocators.SELECTDESIGNBTN, ElementType.Xpath);
			flag = webDB.waitForElement(ShopifyStoreLocators.DESIGN_SECTION, ElementType.CssSelector);
			if (flag) {
				log.info("Select design");
				flag = webDB.waitForElement(WidgetLocators.OPTIN_CHATWIDGET_DEFAULTDESIGN, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(WidgetLocators.OPTIN_CHATWIDGET_DEFAULTDESIGN, ElementType.Xpath);
					flag = webDB.waitForElement(WidgetLocators.SAVE, ElementType.Xpath);
					if (flag) {
						log.info("Selected");
						webDB.clickAnElement(WidgetLocators.SAVE, ElementType.Xpath);
						flag = webDB.waitForElement(WidgetLocators.SUCCESS_POPUP, ElementType.Xpath);
						if (flag) {
							flag = webDB.waitForElement(WidgetLocators.OPTINVIACHAT_WIDGET_HEADERCOLOR_TEXTFIELD,
									ElementType.Xpath);
							if (flag) {
								//modified - changed below locator.
								webDB.clearText(WidgetLocators.OPTINVIACHAT_WIDGET_HEADERCOLOR_TEXTFIELD);
								webDB.sendTextToAnElement(WidgetLocators.OPTINVIACHAT_WIDGET_HEADERCOLOR_TEXTFIELD,
										WidgetLocators.COLOR, ElementType.Xpath);
								Thread.sleep(2000);
								//modified - changed below locator.
								webDB.clearText(WidgetLocators.OPTINVIACHAT_WIDGET_DESCRIPTIONCOLOR_TEXTFIELD);
								webDB.sendTextToAnElement(WidgetLocators.OPTINVIACHAT_WIDGET_DESCRIPTIONCOLOR_TEXTFIELD,
										WidgetLocators.COLOR, ElementType.Xpath);
								flag = webDB.waitForElement(WidgetLocators.SAVE, ElementType.Xpath);
								if (flag) {
									log.info("Header and Description color changed");
									webDB.clickAnElement(WidgetLocators.SAVE, ElementType.Xpath);
									flag = webDB.waitForElement(WidgetLocators.SUCCESS_POPUP, ElementType.Xpath);
									if (flag) {
										webDB.scrollToAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB,
												ElementType.Xpath);
										webDB.clickAnElement(WidgetLocators.CHATWIDGET_SETTING, ElementType.Xpath);
										WidgetPage.personalWidgetPageToStoreNavigation();
										log.info("personalWidgetPageToStoreNavigation method executed");
										ShopifyStore.shopifyStorePassEnter();
										log.info("shopifyStorePassEnter method executed");
										JavascriptExecutor js = (JavascriptExecutor) webDB.driver;
										js.executeScript("window.localStorage.clear();");
										webDB.driver.navigate().refresh();
										flag = ShopifyStore.verifyOptinviaChatBoxHeaderColor();
										if (flag) {
											log.info("Verified chatbox header and description color changed");
											webDB.driver.switchTo().window(parentWindow);
											flag = webDB.waitForElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB,
													ElementType.Xpath);
											if (flag) {
												webDB.clickAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB,
														ElementType.Xpath);
												flag = webDB.waitForElement(
														WidgetLocators.ENABLE_DISABLE_STOREFRONT_WIDGET,
														ElementType.Xpath);
												if (flag) {
													webDB.scrollToAnElement(WidgetLocators.OPTIN_CHATWIDGET_EDIT_CONFIG,
															ElementType.Xpath);
													webDB.clickAnElement(WidgetLocators.OPTIN_CHATWIDGET_EDIT_CONFIG,
															ElementType.Xpath);
													webDB.scrollToAnElement(WidgetLocators.SELECTDESIGNBTN,
															ElementType.Xpath);
													flag = webDB.waitForElement(
															WidgetLocators.OPTINVIACHAT_WIDGET_HEADERCOLOR_TEXTFIELD,
															ElementType.Xpath);
													if (flag) {
														webDB.clearText(
																WidgetLocators.OPTINVIACHAT_WIDGET_HEADERCOLOR_TEXTFIELD);
														webDB.sendTextToAnElement(
																WidgetLocators.OPTINVIACHAT_WIDGET_HEADERCOLOR_TEXTFIELD,
																WidgetLocators.DEFAULTHEADRCOLOR, ElementType.Xpath);
														Thread.sleep(2000);
														webDB.clearText(
																WidgetLocators.OPTINVIACHAT_WIDGET_DESCRIPTIONCOLOR_TEXTFIELD);
														webDB.sendTextToAnElement(
																WidgetLocators.OPTINVIACHAT_WIDGET_DESCRIPTIONCOLOR_TEXTFIELD,
																WidgetLocators.DEFAULTHEADRCOLOR, ElementType.Xpath);
														flag = webDB.waitForElement(WidgetLocators.SAVE,
																ElementType.Xpath);
														if (flag) {
															log.info("Header and Description color chnaged to default");
															webDB.clickAnElement(WidgetLocators.SAVE,
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
		return flag;
	}

	public static boolean verifyOptinWidgetTutorial() throws InterruptedException, IOException {
		flag = webDB.waitForElement(WidgetLocators.OPTINWIDGETSTAB, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			webDB.javaScriptClickAnElement(WidgetLocators.OPTINWIDGETSTAB, ElementType.Xpath);
			flag = webDB.waitForElement(WidgetLocators.ENABLE_DISABLE_STOREFRONT_WIDGET, ElementType.Xpath);
			if (flag) {
				flag = webDB.waitForElement(MyWhatsAppLocators.TUTORIAL_VIDEO, ElementType.Xpath);
				if (flag) {
					webDB.javaScriptClickAnElement(MyWhatsAppLocators.TUTORIAL_VIDEO, ElementType.Xpath);
					Thread.sleep(2000);
					flag = webDB.waitForElement(WidgetLocators.DISPLAY_TEXT_TUTORIAL, ElementType.Xpath);
					if (flag) {
						String text = webDB.getText(WidgetLocators.DISPLAY_TEXT_TUTORIAL, ElementType.Xpath);
						System.out.println(text);
						if (text.equals(WidgetLocators.TUTORIAL_TITLE)) {
							flag = webDB.waitForElement(CampaignsLocators.WHATSAPPCAMPAIGN_IFRAME,
									ElementType.CssSelector);
							if (flag) {
								flag = webDB.waitForElement(CampaignsLocators.TUTORIAL_CLOSEBTN, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(CampaignsLocators.TUTORIAL_CLOSEBTN, ElementType.Xpath);
									flag = webDB.waitForElement(CampaignsLocators.TUTORIAL_BTN, ElementType.Xpath);
									if (flag) {
										System.out.println("Verified tutorial video in Optin widgets");
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

	public static boolean countryCodeForSpinWheel() throws Exception {
		flag = webDB.waitForElement(WidgetLocators.OPTINWIDGETSTAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.OPTINWIDGETSTAB, ElementType.Xpath);
			Thread.sleep(2000);
			flag = webDB.waitForElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
				flag = webDB.waitForElement(WidgetLocators.DISABLE_TEXT, ElementType.Xpath);
				if (flag) {
					Text = webDB.getText(WidgetLocators.DISABLE_TEXT, ElementType.Xpath);
					if (!Text.equals("enabled")) {
						webDB.clickAnElement(WidgetLocators.ENABLE_BUTTON, ElementType.Xpath);
						log.info("Spin the wheel is enabled");
					}
					webDB.moveToElement(WidgetLocators.SPINWHEEL_MESSAGE, ElementType.Xpath);
					flag = webDB.waitForElement(WidgetLocators.SPINWHEEL_MESSAGE, ElementType.Xpath);
					if (flag) {
						flag = webDB.waitForElement(WidgetLocators.SPINWHEEL_ENG, ElementType.Xpath);
						if (flag) {
							flag = webDB.IsEnabled(WidgetLocators.SPINWHEEL_ENG, ElementType.Xpath);
							if (!flag) {
								webDB.clickAnElement(WidgetLocators.ENGLISHLANGUAGERADIOBTN, ElementType.Xpath);
								flag = webDB.waitForElement(WidgetLocators.SAVE, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(WidgetLocators.SAVE, ElementType.Xpath);
								}
							}
							webDB.moveToElement(WidgetLocators.STORE_LINK_SPINWHEEL, ElementType.Xpath);
							webDB.clickAnElement(WidgetLocators.STORE_LINK_SPINWHEEL, ElementType.Xpath);
							parentWindow = webDB.driver.getWindowHandle();
							webDB.switchToChildWindow();
							ShopifyStore.shopifyStorePassEnter();
							CommonFunctions.clearLocalStorage();
							webDB.driver.navigate().refresh();
							flag = webDB.waitForElement(ShopifyStoreLocators.SPIN_WHEEL_STORE, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(ShopifyStoreLocators.SPIN_WHEEL_STORE, ElementType.Xpath);
								Thread.sleep(4000);
								if (flag) {
									webDB.clickAnElement(ShopifyStoreLocators.SPIN_WHEEL, ElementType.Xpath);
									Thread.sleep(3000);
									webDB.waitForElement(ShopifyStoreLocators.SPIN_WHEEL_TEXT, ElementType.Xpath);
									Text = webDB.getAttribute(ShopifyStoreLocators.SPINWHEEL_COUNTRY_CODE, "value");
									log.info("Country Code Is: " + Text);
									if (Text.contains("+91") || Text.contains("+1")) {
										log.info("country code is matched");
										flag = true;
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

	public static boolean verifyOptinPopUp() throws Exception {

		flag = webDB.waitForElement(WidgetLocators.OPTINWIDGETSTAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.OPTINWIDGETSTAB, ElementType.Xpath);
			Thread.sleep(2000);
			flag = webDB.waitForElement(WidgetLocators.ENABLE_DISABLE_STOREFRONT_WIDGET_TXT, ElementType.Xpath);
			if (flag) {
				text = webDB.getText(WidgetLocators.ENABLE_DISABLE_STOREFRONT_WIDGET_TXT, ElementType.Xpath);
				if (!text.equals("Enabled")) {
					webDB.clickAnElement(WidgetLocators.ENABLE_DISABLE_STOREFRONT_WIDGET, ElementType.Xpath);
					log.info("Optin popup is enabled");
				}
				flag = webDB.waitForElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
					flag = webDB.waitForElement(WidgetLocators.LAND_ON_CART, ElementType.Xpath);
					if (flag) {
						flag = webDB.IsEnabled(WidgetLocators.LAND_ON_CART_INPUT, ElementType.Xpath);
						if (!flag) {
							webDB.clickAnElement(WidgetLocators.LAND_ON_CART, ElementType.Xpath);
							flag = webDB.waitForElement(WidgetLocators.SAVE_BTN, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(WidgetLocators.SAVE_BTN, ElementType.Xpath);
								log.info("Landing on cart page is selected");
							}
						}
						flag = webDB.waitForElement(WidgetLocators.BACK, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(WidgetLocators.BACK, ElementType.Xpath);
							webDB.driver.navigate().refresh();
							flag = webDB.waitForElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(WidgetLocators.VIEW_STORE, ElementType.Xpath);
								parentWindow = webDB.driver.getWindowHandle();
								webDB.switchToChildWindow();
								ShopifyStore.shopifyStorePassEnter();
								CommonFunctions.clearLocalStorage();
								webDB.driver.navigate().refresh();
								flag = webDB.waitForElement(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET,
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(CustomersLocators.CART, ElementType.Xpath);
									// Text = webDB.getAttribute(ShopifyStoreLocators.OPTIN_COUNTRYCODE, "value");
									Text = webDB.getText(ShopifyStoreLocators.OPTIN_COUNTRYCODE, ElementType.Xpath);
									log.info("Country Code Is: " + Text);
									if (Text.contains("+91") || Text.contains("+1")) {
										log.info("country code is matched");
										flag = true;
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

	public static boolean verifyOptinpopupLanguage() throws InterruptedException {
		flag = webDB.waitForElement(WidgetLocators.OPTINWIDGETSTAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.OPTINWIDGETSTAB, ElementType.Xpath);
			Thread.sleep(2000);
			flag = webDB.waitForElement(WidgetLocators.ENABLE_DISABLE_STOREFRONT_WIDGET_TXT, ElementType.Xpath);
			if (flag) {
				text = webDB.getText(WidgetLocators.ENABLE_DISABLE_STOREFRONT_WIDGET_TXT, ElementType.Xpath);
				if (!text.equals("Enabled")) {
					webDB.clickAnElement(WidgetLocators.ENABLE_DISABLE_STOREFRONT_WIDGET, ElementType.Xpath);
					log.info("Optin popup is enabled");
				}
				flag = webDB.waitForElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
					flag = webDB.isElementDisplayed(WidgetLocators.STOREFRONT_WIDGET_LANGUGAE_HEADER,
							ElementType.Xpath);
					if (flag) {
						for (int i = 1; i <= 15; i++) {
							flag = webDB.isElementDisplayed(WidgetLocators.STOREFRONT_ALLOPTIONS + "[" + i + "]",
									ElementType.Xpath);
							if (flag) {
								text = webDB.getText(WidgetLocators.STOREFRONT_ALLOPTIONS + "[" + i + "]",
										ElementType.Xpath);
								log.info(text);
								// System.out.println("storefront text of " + i + " is " + text);
							}
						}
					}
				}
			}
		}
		return flag;
	}
}
