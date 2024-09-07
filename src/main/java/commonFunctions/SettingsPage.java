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
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import locators.SettingsLocators;
import locators.CommonLocators;
import locators.WidgetLocators;
import locators.ShopifyStoreLocators;
import utils.DriverBase;
import utils.DriverBase.ElementType;

/**
 * @author Sumit
 * @company Gupshup October 18, 2021
 */
@SuppressWarnings("static-access")
public class SettingsPage {
//	PropertyConfigurator.
	private static Logger log = Logger.getLogger(SettingsPage.class.getName());
	private static boolean flag;
	private static String propertyFilePath;
	private static String url;
	public static Properties prop = new Properties();
	private static DriverBase webDB = new DriverBase();;
	private static String text;
	private static Set<String> windows;
	private static Iterator<String> iterator;
	private static String parentWindow;
	private static String childWindow;
	private static Actions actions = new Actions(webDB.driver);
	private static List<WebElement> elements;
	private static JavascriptExecutor adminConfiguration = (JavascriptExecutor) webDB.driver;
	private static WebDriverWait wait = new WebDriverWait(webDB.driver, 10);
	private static WebElement element;

	private static String initialValue;
	private static String increasedValue;
	private static String decreasedValue;
	private static String percentage = RandomStringUtils.randomNumeric(2);

	/**
	 * verify update supportNumber and CountryCode
	 * 
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */

	public static boolean updateAndVerifySupportNumber() throws InterruptedException {
		flag = false;
		Thread.sleep(1000);
		webDB.waitForElement(SettingsLocators.WA_SUPPORT_NUM_COUNTRY_CODE, ElementType.Xpath);
//		webDB.driver.findElement(By.xpath(AdminConfigurationLocators.WA_SUPPORT_NUM_COUNTRY_CODE))
//				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.selectDropDownOptions(SettingsLocators.WA_SUPPORT_NUM_COUNTRY_CODE, WidgetLocators.INDIA_CODE,
				ElementType.Xpath);
		String phone = RandomStringUtils.randomNumeric(15);
		webDB.waitForElement(SettingsLocators.WA_SUPPORT_NUM_TEXT_FEILD, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(SettingsLocators.WA_SUPPORT_NUM_TEXT_FEILD))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(SettingsLocators.WA_SUPPORT_NUM_TEXT_FEILD, phone, ElementType.Xpath);
		webDB.isElementDisplayed(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
		webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SettingsLocators.SAVE_SUCCESS_MSG)));
		flag = webDB.isElementDisplayed(SettingsLocators.SAVE_SUCCESS_MSG, ElementType.Xpath);
		if (flag) {
			log.info("Support Number and Country Code updated Successfully");
		}
		return flag;

	}

	public static boolean disableOptinStoreFront() throws InterruptedException {
		Thread.sleep(2000);
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
			flag = webDB.waitForElement(WidgetLocators.ENABLE_DISABLE_STOREFRONT_WIDGET, ElementType.Xpath);
			Thread.sleep(2500);
			if (flag) {
				Thread.sleep(2500);
				String text2 = webDB.getText(WidgetLocators.ENABLE_DISABLE_STOREFRONT_WIDGET_TXT, ElementType.Xpath);
				System.out.println(text2);
				if (!text2.contains("Disabled")) {
					Thread.sleep(1000);
					webDB.clickAnElement(WidgetLocators.ENABLE_DISABLE_STOREFRONT_WIDGET, ElementType.Xpath);
				}
				String text3 = webDB.getText(WidgetLocators.ENABLE_DISABLE_STOREFRONT_WIDGET_TXT, ElementType.Xpath);
				System.out.println(text3);
			}
		}
		return flag;
	}

	public static boolean checkOptinCheckBoxesDisabled() {

		elements = webDB.driver.findElements(By.xpath(SettingsLocators.OPTIN_CHECKBOXES));
		for (int i = 1; i < elements.size(); i++)

		{
			flag = !elements.get(1).isEnabled();
			log.info("Optin checkbox is disabled " + elements.get(1).getText() + "");

		}
		return flag;
	}

	public static boolean selectDisableOptinOptionAndCheckifOptinCheckboxesDisabled() throws InterruptedException {
		SettingsPage.disableOptinStoreFront();
		return flag;
	}

	public static boolean enableOptinStoreFront() throws Exception {
		Thread.sleep(2000);
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
			flag = webDB.waitForElement(WidgetLocators.ENABLE_DISABLE_STOREFRONT_WIDGET, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2500);
				String text2 = webDB.getText(WidgetLocators.ENABLE_DISABLE_STOREFRONT_WIDGET_TXT, ElementType.Xpath);
				System.out.println(text2);
				if (!text2.contains("Enabled")) {
					Thread.sleep(1000);
					webDB.clickAnElement(WidgetLocators.ENABLE_DISABLE_STOREFRONT_WIDGET, ElementType.Xpath);
					log.info("Enabled");
				}
				String text3 = webDB.getText(WidgetLocators.ENABLE_DISABLE_STOREFRONT_WIDGET_TXT, ElementType.Xpath);
				System.out.println(text3);
			}
		}
		return flag;
	}

	public static boolean checkOptinCheckBoxesEnabled() throws InterruptedException {
		Thread.sleep(2000);
		flag = webDB.waitForElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
			Thread.sleep(2000);
			elements = webDB.driver.findElements(By.xpath(SettingsLocators.OPTIN_CHECKBOXES));
			Thread.sleep(2000);
			for (int i = 0; i < elements.size(); i++) {
				Thread.sleep(2000);
				flag = elements.get(i).isSelected();
				Thread.sleep(2000);
				if (!flag) {
					Thread.sleep(2000);
					elements.get(i).click();
					Thread.sleep(2000);
					webDB.clickAnElement(WidgetLocators.SAVE_BTN, ElementType.Xpath);
				}
			}
		}
		return flag;
	}

	public static boolean selectEnableOptinOptionAndCheckifOptinCheckboxesEnabled() throws Exception {
		flag = false;
		SettingsPage.enableOptinStoreFront();
		flag = SettingsPage.checkOptinCheckBoxesEnabled();
		return flag;
	}

	public static boolean selectAllOptinOptions() throws Exception {
		Thread.sleep(3000);
		flag = webDB.waitForElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
			Thread.sleep(1500);
			enableOptinStoreFront();
			flag = webDB.waitForElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
				flag = webDB.waitForElement(WidgetLocators.STOREFRONT_WIDGET_LANGUGAE_HEADER, ElementType.Xpath);
				if (flag) {
					Thread.sleep(2500);
					elements = webDB.driver.findElements(By.xpath(SettingsLocators.ADMIN_PAGE_CHECKBOXES_GENERIC));
					log.info(elements.size());
					List<WebElement> checkBoxes = webDB.driver
							.findElements(By.xpath(SettingsLocators.OPTIN_CHECKBOXES));
					log.info(checkBoxes.size());
					for (int j = 0; j < checkBoxes.size(); j++) {
						if (!checkBoxes.get(j).isSelected()) {
							elements.get(j).click();
							webDB.isElementDisplayed(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
							webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
							flag = webDB.isElementDisplayed(SettingsLocators.SAVE_SUCCESS_MSG, ElementType.Xpath);
						}
					}
				}
			}
		}
		flag = true;
		return flag;

	}

	public static boolean enableOptinsAndCheckOnstore() throws Exception {
		Thread.sleep(3000);
		CommonFunctions.verifyPersonalWidgetPage();
		SettingsPage.selectAllOptinOptions();
		log.info("selectAllOptinOptions method executed");
		CommonFunctions.verifyPersonalWidgetPage();
		log.info("verifyPersonalWidgetPage method executed");
		Thread.sleep(2000);
		WidgetPage.personalWidgetPageToStoreNavigation();
		log.info("personalWidgetPageToStoreNavigation method executed");
		ShopifyStore.shopifyStorePassEnter();
		log.info("shopifyStorePassEnter method executed");
		adminConfiguration.executeScript("window.localStorage.clear();");
		webDB.driver.navigate().refresh();
		flag = ShopifyStore.verifyOptinPopUpDisplayed();
		log.info(" method executed");
		log.info("Optin Pop up is Displayed On Store");
		return flag;
	}

	public static boolean verifyOptinOnStore() throws Exception {
		parentWindow = webDB.driver.getWindowHandle();
		flag = enableOptinsAndSubmitOptinOnStore();
		webDB.driver.close();
		log.info("closed the child window");
		webDB.driver.switchTo().window(parentWindow);
		log.info("switched to parent window");
		return flag;
	}

	public static boolean enableOptinsAndSubmitOptinOnStore() throws Exception {
		parentWindow = webDB.driver.getWindowHandle();
		SettingsPage.enableOptinsAndCheckOnstore();
		if (flag) {
			flag = ShopifyStore.verifyNumberSuccessfullyOptedIn();
		}
		return flag;
	}

	public static boolean verifyenableOptinsAndSubmitOptinOnStore() throws Exception {
		{
			parentWindow = webDB.driver.getWindowHandle();
			flag = enableOptinsAndSubmitOptinOnStore();
			log.info("Verified opt in submission");
			webDB.driver.close();
			log.info("closed the child window");
			webDB.driver.switchTo().window(parentWindow);
			log.info("switched to parent window");
			return flag;
		}
	}

	public static boolean updateOptinPopupLanguage() throws Exception {
		Thread.sleep(2000);
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
			flag = webDB.waitForElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
				flag = webDB.waitForElement(WidgetLocators.STOREFRONT_WIDGET_LANGUGAE_HEADER, ElementType.Xpath);
				if (flag) {
					Thread.sleep(2500);
					element = webDB.driver.findElement(By.xpath(SettingsLocators.SPANISH_LANGUAGE_RADIO));
					WebElement ele = webDB.driver
							.findElement(By.xpath(SettingsLocators.SPAPNISH_LANGUAGE_SELECTION_CHECK));
					if (!ele.isEnabled()) {
						actions.moveToElement(element).click().build().perform();
						webDB.isElementDisplayed(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
						webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath(SettingsLocators.SAVE_SUCCESS_MSG)));
						flag = webDB.isElementDisplayed(SettingsLocators.SAVE_SUCCESS_MSG, ElementType.Xpath);
					}
				}
			}
		}

//		element = webDB.driver.findElement(By.xpath(AdminConfigurationLocators.SPANISH_LANGUAGE_RADIO));
//		WebElement ele = webDB.driver
//				.findElement(By.xpath(AdminConfigurationLocators.SPAPNISH_LANGUAGE_SELECTION_CHECK));

		SettingsPage.enableOptinsAndCheckOnstore();
		return flag;
	}

	@SuppressWarnings("unused")
	public static boolean checkLanguageOptinPopupOnStore() throws Exception {
		parentWindow = webDB.driver.getWindowHandle();
		SettingsPage.updateOptinPopupLanguage();
		text = webDB.driver.findElement(By.id(ShopifyStoreLocators.CONFIRM_BUTTON_OPTIN_POPUP)).getText();
		log.info(text);
		flag = false;
		if (text.equalsIgnoreCase("CONFIRMAR")) {
			flag = true;
			log.info("Opt in Pop up Language is displayed as per update");
		}
		webDB.driver.close();
		log.info("closed the child window");
		webDB.driver.switchTo().window(parentWindow);
		log.info("switched to parent window");
//		CommonFunctions.verifyAdminConfigurtionPage();
//		elements = webDB.driver.findElements(By.xpath(AdminConfigurationLocators.RADIO_CLICKABLE_GENERIC));
//		for (int i = 0; i < elements.size(); i++) {
//			elements.get(i).click();
//			break;
//		}
		flag = webDB.waitForElement(WidgetLocators.WIDGET_SETTING_HEADER, ElementType.Xpath);
		return flag;

	}

	public static boolean verifyOptinOnThankyouPage() throws Exception {
		flag = false;
		parentWindow = webDB.driver.getWindowHandle();
		SettingsPage.selectAllOptinOptions();
		CommonFunctions.verifyTemplatesPage();
		Thread.sleep(3000);
		AutomationPage.enableOrderConfirmationTemplateMessaging();
		CommonFunctions.verifyPersonalWidgetPage();
		WidgetPage.personalWidgetPageToStoreNavigation();
		ShopifyStore.shopifyStorePassEnter();
//		adminConfiguration.executeScript("window.localStorage.clear();");
		flag = ShopifyStore.verifyOptinFromThankYouPage();
		webDB.driver.close();
		log.info("closed the child window");
		webDB.driver.switchTo().window(parentWindow);
		log.info("switched to parent window");
		return flag;

	}

	public static boolean validateEmptySupportNumber() throws InterruptedException {
		flag = false;
		parentWindow = webDB.driver.getWindowHandle();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SettingsLocators.WA_SUPPORT_NUM_COUNTRY_CODE)));
//		webDB.driver.findElement(By.xpath(AdminConfigurationLocators.WA_SUPPORT_NUM_COUNTRY_CODE))
//				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
//		log.info("Cleared the country code");
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CommonLocators.SAVE_SETTINGS)));
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CommonLocators.SAVE_SETTINGS)));
//		webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
//		log.debug("Clicked on Save settings");
//		wait.until(ExpectedConditions
//				.elementToBeClickable(By.xpath(AdminConfigurationLocators.COUNTRY_CODE_VALIDATION_MSG_ON_SAVE)));
//		flag = webDB.isElementDisplayed(AdminConfigurationLocators.COUNTRY_CODE_VALIDATION_MSG_ON_SAVE,
//				ElementType.Xpath);
//		log.info("Country Code Validation Displayed");
//		if (flag) {
//			Thread.sleep(3000);
//			webDB.clickAnElement(AdminConfigurationLocators.WA_SUPPORT_NUM_COUNTRY_CODE, ElementType.Xpath);
		webDB.sendTextToAnElement(SettingsLocators.WA_SUPPORT_NUM_COUNTRY_CODE, "+91", ElementType.Xpath);
		Thread.sleep(1500);
		webDB.pressEnterKey();
		webDB.driver.findElement(By.xpath(SettingsLocators.WA_SUPPORT_NUM_TEXT_FEILD))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		log.info("Cleared the support number");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CommonLocators.SAVE_SETTINGS)));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CommonLocators.SAVE_SETTINGS)));
		webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
		log.debug("Clicked on Save settings");
		flag = webDB.isElementDisplayed(SettingsLocators.SUPPORT_NUMBER_VALIDATION_0N_SAVE_MSG, ElementType.Xpath);
		if (flag) {
			log.info("Support Number and Country Code Validation Displayed");
		}
//		}
		return flag;

	}

	@SuppressWarnings("unused")
	public static boolean validateInvalidSupportNumber() throws InterruptedException {
		flag = false;
		String phone = RandomStringUtils.randomNumeric(10);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SettingsLocators.WA_SUPPORT_NUM_COUNTRY_CODE)));
//		webDB.driver.findElement(By.xpath(AdminConfigurationLocators.WA_SUPPORT_NUM_COUNTRY_CODE))
//				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(SettingsLocators.WA_SUPPORT_NUM_COUNTRY_CODE, "+91", ElementType.Xpath);
		webDB.pressEnterKey();
//		webDB.selectDropDownOptions(AdminConfigurationLocators.WA_SUPPORT_NUM_COUNTRY_CODE,PersonalWidgetLocators.INDIA_CODE, ElementType.Xpath);
//		webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
//		webDB.isElementDisplayed(AdminConfigurationLocators.COUNTRY_CODE_VALIDATION_ERROR, ElementType.Xpath);

//		flag = webDB.isElementDisplayed(AdminConfigurationLocators.COUNTRY_CODE_VALIDATION_MSG_ON_SAVE,
//				ElementType.Xpath);
//		if (flag) {
//			wait.until(ExpectedConditions.invisibilityOf(webDB.driver
//					.findElement(By.xpath(AdminConfigurationLocators.COUNTRY_CODE_VALIDATION_MSG_ON_SAVE))));
//			webDB.driver.findElement(By.xpath(AdminConfigurationLocators.WA_SUPPORT_NUM_COUNTRY_CODE))
//					.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
//		webDB.selectDropDownOptions(AdminConfigurationLocators.WA_SUPPORT_NUM_COUNTRY_CODE,
//				PersonalWidgetLocators.INDIA_CODE, ElementType.Xpath);
		webDB.waitForElement(SettingsLocators.WA_SUPPORT_NUM_TEXT_FEILD, ElementType.Xpath);
		Thread.sleep(1000);
		webDB.driver.findElement(By.xpath(SettingsLocators.WA_SUPPORT_NUM_TEXT_FEILD))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(SettingsLocators.WA_SUPPORT_NUM_TEXT_FEILD, "abcd", ElementType.Xpath);
		webDB.isElementDisplayed(SettingsLocators.SUPPORT_NUMBER_INVALID_ERROR, ElementType.Xpath);
		webDB.isElementDisplayed(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
		webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
		flag = webDB.isElementDisplayed(SettingsLocators.SUPPORT_NUMBER_VALIDATION_0N_SAVE_MSG, ElementType.Xpath);
		log.info("Support Number and Country Code validated Successfully");
//		}
		return flag;

	}

	public static boolean checkThankYouPageEnabledByDefaultMessage() throws InterruptedException {
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
		Thread.sleep(1000);
		webDB.moveToElement(SettingsLocators.OPTIN_STOREFRONT_DESCRIPTION, ElementType.Xpath);
		webDB.isElementDisplayed(SettingsLocators.OPTIN_STOREFRONT_DESCRIPTION, ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath(SettingsLocators.OPTIN_STOREFRONT_DESCRIPTION)).getText();
		System.out.println(text);
		if (text.contains(
				"This widget opens a pop-up where the user can enter their Whatsapp number and “optin” to receive messages from your business")) {
			flag = true;
			log.info("Thank You page Optin Pop up enabeld by default message displayed");
		} else {
			flag = false;
		}
		return flag;
	}

	public static boolean checkCheckoutOptinApproved() throws InterruptedException {
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
		Thread.sleep(1000);
		flag = webDB.waitForElement(WidgetLocators.CHECKOUTCONFIGURE_WIDGET, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.CHECKOUTCONFIGURE_WIDGET, ElementType.Xpath);
			webDB.isElementDisplayed(SettingsLocators.COLLECT_OPTIN_FROM_CHCKOUT_APPROVED_MSG, ElementType.Xpath);
			text = webDB.driver.findElement(By.xpath(SettingsLocators.COLLECT_OPTIN_FROM_CHCKOUT_APPROVED_MSG))
					.getText();
			if (text.contains("approved")) {
				flag = true;
				log.info("Checkout Approved Message displayed in the Admin Configuration page");
			} else {
				flag = false;
			}
		}
		return flag;
	}

	public static boolean VerifyHyperlinkNavigation() throws FileNotFoundException, InterruptedException, IOException {
		// Phone number hyperlink
		webDB.moveToElement(SettingsLocators.PHONE_NO_HYPERLINK, ElementType.Xpath);
		webDB.isElementDisplayed(SettingsLocators.PHONE_NO_HYPERLINK, ElementType.Xpath);
		flag = webDB.clickAnElement(SettingsLocators.PHONE_NO_HYPERLINK, ElementType.Xpath);

		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String PhoneNumberPageURL = prop.getProperty("AdminConfigurationHyperlinks");
		windows = webDB.driver.getWindowHandles();
		iterator = windows.iterator();
		parentWindow = iterator.next();
		childWindow = iterator.next();
		webDB.driver.switchTo().window(childWindow);
		url = webDB.driver.getCurrentUrl();
		if (url.equals(PhoneNumberPageURL)) {
			flag = true;
			log.info("Phone Number Hyperlink page displayed");
		}
		webDB.driver.close();
		webDB.driver.switchTo().window(parentWindow);

		Thread.sleep(2000);
		// Business name hyperlink
		webDB.isElementDisplayed(SettingsLocators.BUSINESS_NAME_HYPERLINK, ElementType.Xpath);
		flag = webDB.clickAnElement(SettingsLocators.BUSINESS_NAME_HYPERLINK, ElementType.Xpath);
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String BusinessNamePageURL = prop.getProperty("AdminConfigurationHyperlinks");
		windows = webDB.driver.getWindowHandles();
		iterator = windows.iterator();
		parentWindow = iterator.next();
		childWindow = iterator.next();
		webDB.driver.switchTo().window(childWindow);
		url = webDB.driver.getCurrentUrl();
		if (url.equals(BusinessNamePageURL)) {
			flag = true;
			log.info("Business Name Hyperlink page displayed");
		}
		webDB.driver.close();
		log.info("closed the child window");
		webDB.driver.switchTo().window(parentWindow);
		log.info("switched to parent window");
		webDB.driver.switchTo().window(parentWindow);

		Thread.sleep(2000);
		// WhatsApp support number hyperlink
		webDB.isElementDisplayed(SettingsLocators.WHATSAPP_SUPPORT_NUMBER, ElementType.Xpath);
		flag = webDB.clickAnElement(SettingsLocators.WHATSAPP_SUPPORT_NUMBER, ElementType.Xpath);
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String WASupportNumberPageURL = prop.getProperty("AdminConfigurationHyperlinks");
		windows = webDB.driver.getWindowHandles();
		iterator = windows.iterator();
		parentWindow = iterator.next();
		childWindow = iterator.next();
		webDB.driver.switchTo().window(childWindow);
		url = webDB.driver.getCurrentUrl();
		if (url.equals(WASupportNumberPageURL)) {
			flag = true;
			log.info("WhatsApp Support Number Hyperlink page displayed");
		}
		webDB.driver.close();
		log.info("closed the child window");
		webDB.driver.switchTo().window(parentWindow);
		log.info("switched to parent window");

		return flag;
	}

	public static boolean VerifyPricingPageNavigation()
			throws FileNotFoundException, InterruptedException, IOException {
		Dimension d = new Dimension(1920, 1000);
		webDB.driver.manage().window().setSize(d);
		Thread.sleep(800);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SettingsLocators.PRICING_PAGE_HYPERLINK)));
		element = webDB.driver.findElement(By.xpath(SettingsLocators.PRICING_PAGE_HYPERLINK));
		actions.moveToElement(element).click().build().perform();
		log.debug("Clicked on" + element);
		windows = webDB.driver.getWindowHandles();
		iterator = windows.iterator();
		parentWindow = iterator.next();
		childWindow = iterator.next();
		webDB.driver.switchTo().window(childWindow);
		log.info("Navigate to Child Window");
		Thread.sleep(2000);
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String PricingPageURL = prop.getProperty("PricingPageURL");
		url = webDB.driver.getCurrentUrl();
		log.info(url);
		if (url.equals(PricingPageURL)) {
			flag = true;
			log.info("Pricing page displayed");
		} else {
			flag = false;
			log.fatal("Pricing Page could not be accessed");
		}
//		webDB.driver.close();
		webDB.driver.close();
		log.info("closed the child window");
		webDB.driver.switchTo().window(parentWindow);
		log.info("switched to parent window");
		return flag;
	}

	public static boolean VerifyThankyouWidgetHyperlinkENG()
			throws FileNotFoundException, InterruptedException, IOException {
		// English Language Check
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
			flag = webDB.waitForElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
				SelectLanguageRadio(0);
				flag = webDB.waitForElement(WidgetLocators.BACK, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(WidgetLocators.BACK, ElementType.Xpath);
					wait.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath(SettingsLocators.THANKYOU_WIDGET_PAGE_HYPERLINK)));
					flag = webDB.clickAnElement(SettingsLocators.THANKYOU_WIDGET_PAGE_HYPERLINK, ElementType.Xpath);
					VerifyPageLanguage("ThankYouPageWidgetENG");
				}
			}
		}
		return flag;
	}

	public static boolean VerifyThankyouWidgetHyperlinkPOR()
			throws FileNotFoundException, InterruptedException, IOException {
		// Portuguese Language Check

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
			flag = webDB.waitForElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
				SelectLanguageRadio(1);
				flag = webDB.waitForElement(WidgetLocators.BACK, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(WidgetLocators.BACK, ElementType.Xpath);
					wait.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath(SettingsLocators.THANKYOU_WIDGET_PAGE_HYPERLINK)));
					flag = webDB.clickAnElement(SettingsLocators.THANKYOU_WIDGET_PAGE_HYPERLINK, ElementType.Xpath);
					VerifyPageLanguage("ThankYouPageWidgetPOR");
				}
			}
		}

		return flag;
	}

	public static boolean VerifyThankyouWidgetHyperlinkSPA()
			throws FileNotFoundException, InterruptedException, IOException {
		// Spanish Language Check

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
			flag = webDB.waitForElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
				SelectLanguageRadio(2);
				flag = webDB.waitForElement(WidgetLocators.BACK, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(WidgetLocators.BACK, ElementType.Xpath);
					wait.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath(SettingsLocators.THANKYOU_WIDGET_PAGE_HYPERLINK)));
					flag = webDB.clickAnElement(SettingsLocators.THANKYOU_WIDGET_PAGE_HYPERLINK, ElementType.Xpath);
					VerifyPageLanguage("ThankYouPageWidgetSPA");
				}
			}
		}
		return flag;
	}

	public static boolean VerifyThankyouWidgetHyperlinkITA()
			throws FileNotFoundException, InterruptedException, IOException {
		// Italian Language Check

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
			flag = webDB.waitForElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
				SelectLanguageRadio(3);
				flag = webDB.waitForElement(WidgetLocators.BACK, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(WidgetLocators.BACK, ElementType.Xpath);
					wait.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath(SettingsLocators.THANKYOU_WIDGET_PAGE_HYPERLINK)));
					flag = webDB.clickAnElement(SettingsLocators.THANKYOU_WIDGET_PAGE_HYPERLINK, ElementType.Xpath);
					VerifyPageLanguage("ThankYouPageWidgetITA");
				}
			}
		}
		return flag;
	}

	public static boolean VerifyPreviewOptInHyperlinkENG()
			throws FileNotFoundException, InterruptedException, IOException {

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
			flag = webDB.waitForElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
				// English Language Check
				SelectLanguageRadio(0);
				flag = webDB.waitForElement(WidgetLocators.BACK, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(WidgetLocators.BACK, ElementType.Xpath);
					wait.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath(SettingsLocators.PREVIEW_OPTIN_HYPERLINK)));
					flag = webDB.clickAnElement(SettingsLocators.PREVIEW_OPTIN_HYPERLINK, ElementType.Xpath);
					// System.out.print("English ");
					VerifyPageLanguage("ThankYouPageWidgetENG");
				}
			}
		}

		return flag;
	}

	public static boolean VerifyPreviewOptInHyperlinkPOR()
			throws FileNotFoundException, InterruptedException, IOException {
		// Portuguese Language Check

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
			flag = webDB.waitForElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
				SelectLanguageRadio(1);
				flag = webDB.waitForElement(WidgetLocators.BACK, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(WidgetLocators.BACK, ElementType.Xpath);
					wait.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath(SettingsLocators.PREVIEW_OPTIN_HYPERLINK)));
					flag = webDB.clickAnElement(SettingsLocators.PREVIEW_OPTIN_HYPERLINK, ElementType.Xpath);
					VerifyPageLanguage("PreviewOptinWidgetPOR");
				}
			}
		}

		return flag;

	}

	public static boolean VerifyPreviewOptInHyperlinkSPA()
			throws FileNotFoundException, InterruptedException, IOException {
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
			flag = webDB.waitForElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
				// Spanish Language Check
				SelectLanguageRadio(2);
				flag = webDB.waitForElement(WidgetLocators.BACK, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(WidgetLocators.BACK, ElementType.Xpath);
					wait.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath(SettingsLocators.PREVIEW_OPTIN_HYPERLINK)));
					flag = webDB.clickAnElement(SettingsLocators.PREVIEW_OPTIN_HYPERLINK, ElementType.Xpath);
					// System.out.print("Spanish ");
					VerifyPageLanguage("PreviewOptinWidgetSPA");
				}
			}
		}

		return flag;
	}

	public static boolean VerifyPreviewOptInHyperlinkITA()
			throws FileNotFoundException, InterruptedException, IOException {

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
			flag = webDB.waitForElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
				// Italian Language Check
				SelectLanguageRadio(3);
				flag = webDB.waitForElement(WidgetLocators.BACK, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(WidgetLocators.BACK, ElementType.Xpath);
					wait.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath(SettingsLocators.PREVIEW_OPTIN_HYPERLINK)));
					flag = webDB.clickAnElement(SettingsLocators.PREVIEW_OPTIN_HYPERLINK, ElementType.Xpath);
					// System.out.print("Italian ");
					VerifyPageLanguage("PreviewOptinWidgetITA");
				}
			}
		}

		return flag;
	}

	public static boolean VerifyPageLanguage(String PAGEURL)
			throws FileNotFoundException, InterruptedException, IOException {
		windows = webDB.driver.getWindowHandles();
		iterator = windows.iterator();
		parentWindow = iterator.next();
		childWindow = iterator.next();
		webDB.driver.switchTo().window(childWindow);
		url = webDB.driver.getCurrentUrl();

		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String URL = prop.getProperty(PAGEURL);

		if (url.equals(URL)) {
			flag = true;
			log.info("Widget page displayed");
		}

		webDB.driver.close();
		log.info("Closed the child window");
		webDB.driver.switchTo().window(parentWindow);
		log.info("switched to parent window");
		return flag;
	}

	public static void SelectLanguageRadio(int RadioNo)
			throws FileNotFoundException, InterruptedException, IOException {
		elements = webDB.driver.findElements(By.xpath(SettingsLocators.RADIO_CLICKABLE_GENERIC));
		for (int i = 0; i < elements.size(); i++) {
			if (i == RadioNo) {
				flag = elements.get(RadioNo).isSelected();
				if (!flag) {
					elements.get(i).click();
					log.info(elements.get(i).getText());
					webDB.isElementDisplayed(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
					webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
					webDB.isElementDisplayed(SettingsLocators.SAVE_SUCCESS_MSG, ElementType.Xpath);
					Thread.sleep(6000);
					break;
				}
				break;
			}
		}

	}

	public static boolean deslectoptincheckboxes() throws Exception {
//		SettingsPage.enableOptinStoreFront();
		SettingsPage.selectAllOptinOptions();
		log.info("*****************");
		flag = webDB.waitForElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
			elements = webDB.driver.findElements(By.xpath(SettingsLocators.OPTIN_CHECKBOXES));
			for (int i = 0; i < (elements.size()) - 1; i++) {
				flag = elements.get(i).isEnabled();
				if (flag) {
					elements = webDB.driver.findElements(By.cssSelector(SettingsLocators.OPTIN_SELECT_CHECKBOXES));
					elements.get(i).click();
					Thread.sleep(2000);
					webDB.clickAnElement(WidgetLocators.SAVE_BTN, ElementType.Xpath);
				}
			}
		}
		return flag;
	}

	public static boolean marketingCampaignUsageLimit() throws InterruptedException {
		// flag = false;
		Thread.sleep(1000);
		flag = webDB.waitForElement(SettingsLocators.MARKETING_MONTHLY_USAGE, ElementType.Xpath);
		if (flag) {
			webDB.driver.findElement(By.xpath(SettingsLocators.MARKETING_MONTHLY_USAGE))
					.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			// String percentage = RandomStringUtils.randomNumeric(2);
			webDB.sendTextToAnElement(SettingsLocators.MARKETING_MONTHLY_USAGE, percentage, ElementType.Xpath);
			flag = webDB.isElementDisplayed(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SettingsLocators.SAVE_SUCCESS_MSG)));
				flag = webDB.isElementDisplayed(SettingsLocators.SAVE_SUCCESS_MSG, ElementType.Xpath);
				if (flag) {
					log.info("Marketing campaign monthly usage limit working fine");
				}
			}
		}
		return flag;
	}

	public static boolean marketingCampaignUsageLimitUsingSpinner() throws InterruptedException {
		Thread.sleep(2000);
		flag = webDB.waitForElement(SettingsLocators.MARKETING_MONTHLY_USAGE, ElementType.Xpath);
		if (flag) {
			webDB.driver.findElement(By.xpath(SettingsLocators.MARKETING_MONTHLY_USAGE))
					.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			webDB.sendTextToAnElement(SettingsLocators.MARKETING_MONTHLY_USAGE, percentage, ElementType.Xpath);
			flag = webDB.isElementDisplayed(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
				Thread.sleep(2000);
				initialValue = webDB.getAttribute(SettingsLocators.MARKETING_MONTHLY_USAGE, "value");
				System.out.println("initial = " + initialValue);

				Thread.sleep(2000);
				webDB.clickAnElement(SettingsLocators.MARKETING_MONTHLY_USAGE_UP, ElementType.Xpath);

				flag = webDB.isElementDisplayed(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
					Thread.sleep(2000);
					increasedValue = webDB.getAttribute(SettingsLocators.MARKETING_MONTHLY_USAGE, "value");
					System.out.println("increased = " + increasedValue);

					Thread.sleep(2000);
					webDB.clickAnElement(SettingsLocators.MARKETING_MONTHLY_USAGE_DOWN, ElementType.Xpath);

					flag = webDB.isElementDisplayed(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
						Thread.sleep(2000);
						decreasedValue = webDB.getAttribute(SettingsLocators.MARKETING_MONTHLY_USAGE, "value");
						System.out.println("decreased = " + decreasedValue);

						if (Integer.parseInt(increasedValue) == (Integer.parseInt(initialValue) + 1)
								&& Integer.parseInt(decreasedValue) == Integer.parseInt(initialValue)) {
							flag = true;
							System.out.println("Test passed: Up and down spinners working correctly.");
						} else {
							flag = false;
							System.out.println("Test failed: Up and down spinners are not working as expected.");
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean InvalidMarketingCampaignUsageLimitCheck() throws InterruptedException {
		Thread.sleep(1000);
		flag = webDB.waitForElement(SettingsLocators.MARKETING_MONTHLY_USAGE, ElementType.Xpath);
		if (flag) {
			webDB.driver.findElement(By.xpath(SettingsLocators.MARKETING_MONTHLY_USAGE))
					.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			Thread.sleep(2000);
			int randomNumber = generateInvalidPercentage();
			System.out.println("The random number generated is " + randomNumber);

			String invalid_percentage = String.valueOf(randomNumber);
			System.out.println("The invalid percentage generated is " + invalid_percentage);
			flag = webDB.waitForElement(SettingsLocators.MARKETING_MONTHLY_USAGE, ElementType.Xpath);
			if (flag) {
				webDB.sendTextToAnElement(SettingsLocators.MARKETING_MONTHLY_USAGE, invalid_percentage,
						ElementType.Xpath);
				Thread.sleep(2000);
				flag = webDB.isElementDisplayed(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
					flag = webDB.waitForElement(CommonLocators.ERROR_POPUP_ON_SAVE, ElementType.Xpath);
					if (flag) {
						String error_message = webDB.getText(CommonLocators.ERROR_POPUP_ON_SAVE, ElementType.Xpath);
						System.out.println("The error message displayed is: " + error_message);

						if (error_message.equals("Invalid marketing campaign monthly usage limit")) {
							System.out.println("Error message is displayed");
							flag = true;
						}
					}
				}
			}
		}

		return flag;
	}

	private static int generateInvalidPercentage() {
		int[] possibleValues = { -1000, -500, -100, 0, 101, 200, 500, 1000 };
		int randomIndex = (int) (Math.random() * possibleValues.length);
		return possibleValues[randomIndex];
	}

	public static boolean deSelectAllOptinOptions() throws Exception {
		Thread.sleep(3000);
		flag = webDB.waitForElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
			Thread.sleep(1500);
			enableOptinStoreFront();
			flag = webDB.waitForElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.EDIT_CONFIG, ElementType.Xpath);
				flag = webDB.waitForElement(WidgetLocators.STOREFRONT_WIDGET_LANGUGAE_HEADER, ElementType.Xpath);
				if (flag) {
					Thread.sleep(2500);
					elements = webDB.driver.findElements(By.xpath(SettingsLocators.ADMIN_PAGE_CHECKBOXES_GENERIC));
					log.info(elements.size());
					List<WebElement> checkBoxes = webDB.driver
							.findElements(By.xpath(SettingsLocators.OPTIN_CHECKBOXES));
					log.info(checkBoxes.size());
//					for (int j = 0; j < checkBoxes.size(); j++) {
					for (int j = 0; j < 3; j++) {
						if (checkBoxes.get(j).isSelected()) {
							elements.get(j).click();
							webDB.isElementDisplayed(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
							webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, ElementType.Xpath);
							flag = webDB.isElementDisplayed(SettingsLocators.SAVE_SUCCESS_MSG, ElementType.Xpath);
						}
					}
				}
			}
		}
		flag = true;
		return flag;

	}

}
