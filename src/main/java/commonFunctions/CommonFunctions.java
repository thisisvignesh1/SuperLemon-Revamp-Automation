package commonFunctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import locators.AbandonedCartLocators;
import locators.SettingsLocators;
import locators.AnalyticsLocators;
import locators.CODPageLocators;
import locators.ChatBotLocators;
import locators.CommonLocators;
import locators.EmailConfigurationLocators;
import locators.EmailLogsLocators;
import locators.EmailTemplatesLocators;
import locators.FAQsLocators;
import locators.LoginPageLocators;
import locators.MessagingLocators;
import locators.CampaignsLocators;
import locators.MessageLogsLocators;
import locators.CustomersLocators;
import locators.OrderCRMLocators;
import locators.WidgetLocators;
import locators.PlansPageLocators;
import locators.AutomationLocators;
import locators.MyWhatsAppLocators;
import locators.WhatsappExtensionLocators;
import locators.WhatsappInboxLocators;
import utils.DriverBase;
import utils.DriverBase.ElementType;

/**
 * @author Sumit
 * @company Gupshup October 12, 2021
 */
@SuppressWarnings("static-access")
public class CommonFunctions {

	private static Logger log = Logger.getLogger(CommonFunctions.class);
	private static boolean flag;
	private static String propertyFilePath;
	private static String url;
	public static Properties prop = new Properties();
	private static DriverBase webDB = new DriverBase();
	private static Set<String> windows;
	private static Iterator<String> it;
	private static String parentWindow;
	private static String childWindow;
	private static Select dropDown;
	private static JavascriptExecutor common = (JavascriptExecutor) webDB.driver;
	private static String text;
	private static WebElement element;
	// public static String SuperlemonWindow;

	/**
	 * verify Login Page
	 *
	 * @return
	 * @throws InterruptedException
	 */

	public static void clearCookies() throws InterruptedException, IOException, FileNotFoundException {
		webDB.driver.manage().deleteCookieNamed("cookie");
		webDB.driver.navigate().refresh();
	}

	public static boolean verifyLoginPage(String filename)
			throws InterruptedException, IOException, FileNotFoundException {
		clearLocalStorage();
		clearCookies();
		// flag = webDB.waitForElement(LoginPageLocators.GETAPP_BTN,
		// ElementType.CssSelector);
		flag = webDB.waitForElement(LoginPageLocators.NEW_LOGIN_SHOPIFY, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			// webDB.clickAnElement(LoginPageLocators.SHOPIFY_LOGIN, ElementType.Xpath);
			propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
			prop.load(new FileInputStream(propertyFilePath));
			String QAtoken = prop.getProperty(filename);
			System.out.println(QAtoken);
			String ProdToken = prop.getProperty("ProdToken");
			String Qaurl = prop.getProperty("QA_url");
			String Produrl = prop.getProperty("Prod_url");
			url = webDB.driver.getCurrentUrl();
			if (url.contains("qa")) {
				webDB.driver.manage().addCookie(new Cookie("access_token", QAtoken));
				webDB.driver.navigate().refresh();
			} else if (url.contains("beta")) {
				webDB.driver.manage().addCookie(new Cookie("access_token", ProdToken));
				webDB.driver.navigate().refresh();
			}
			Thread.sleep(3000);
			if (url.contains("qa")) {
				webDB.driver.navigate().to(Qaurl);
			} else if (url.contains("beta")) {
				webDB.driver.navigate().to(Produrl);
			}
//			flag =webDB.waitForElement(PlansPageLocators.PLANS_PAGE_BUTTONS, ElementType.Xpath);
//			flag = webDB.driver.findElement(By.cssSelector(PlansPageLocators.PLANS_PAGE_BUTTONS)).isDisplayed();
			Thread.sleep(3000);
			webDB.pressEscapeKey();
			log.info("Logged in Successfully");
			flag = true;
		}
		return flag;

	}

	public static boolean verifyPlansPage() throws InterruptedException, FileNotFoundException, IOException {
		flag = webDB.waitForElement(CommonLocators.ADMIN_PROFILE, ElementType.CssSelector);
		if (flag) {
			webDB.clickAnElement(CommonLocators.ADMIN_PROFILE, ElementType.CssSelector);
			flag = webDB.waitForElement(PlansPageLocators.PLAN_PAGE, ElementType.Xpath);
			if (flag) {
				flag = webDB.clickAnElement(PlansPageLocators.PLAN_PAGE, ElementType.Xpath);
				if (flag) {
					url = webDB.driver.getCurrentUrl();
					propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
					prop.load(new FileInputStream(propertyFilePath));
					// modified - below
					String planPageUrl = prop.getProperty("PlanspageURL");
					String planPageProdUrl = prop.getProperty("PlanspageProdURL");
					if (url == planPageUrl) {
						log.info("Landed on Plans page");
					} else if (url == planPageProdUrl) {
						log.info("Landed on Plans page");
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyHomePage() throws InterruptedException, FileNotFoundException, IOException {
		Thread.sleep(3000);
		webDB.pressEscapeKey();
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			flag = webDB.waitForElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			if (flag) {
				Thread.sleep(1500);
				webDB.clickAnElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			}
		}
		flag = webDB.waitForElement(CommonLocators.HOME_BTN, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonLocators.HOME_BTN, ElementType.Xpath);
			if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
					|| (System.getProperty("os").equals("Android"))) {
				webDB.clickAnElement(CommonLocators.CLOSEICON, ElementType.Xpath);
			}
			url = webDB.driver.getCurrentUrl();
			propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
			prop.load(new FileInputStream(propertyFilePath));
			String PageUrl = prop.getProperty("HomePageUrl");
			String PageUrlProd = prop.getProperty("HomepageUrlProd");
			if (url == PageUrl) {
				log.info("Landed on Home page");
			} else if (url == PageUrlProd) {
				log.info("Landed on Home page");
			}
		}
		return flag;
	}

	public static boolean verifyPersonalWidgetPage() throws InterruptedException, FileNotFoundException, IOException {
		webDB.driver.navigate().refresh();
//		SuperlemonWindow = webDB.driver.getWindowHandle();
//		System.out.println("sl window " + SuperlemonWindow);
//		flag = webDB.isElementDisplayed(CommonLocators.TOURPOPUP, ElementType.Xpath);
//		if (flag) {
//			webDB.pressEscapeKey();
//		}
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			flag = webDB.waitForElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			if (flag) {
				Thread.sleep(1500);
				webDB.clickAnElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			}
		}
		flag = webDB.waitForElement(WidgetLocators.PERSONAL_WIDGET_TAB, ElementType.CssSelector);
		webDB.clickAnElement(WidgetLocators.PERSONAL_WIDGET_TAB, ElementType.CssSelector);
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			webDB.clickAnElement(CommonLocators.CLOSEICON, ElementType.Xpath);
		}
		url = webDB.driver.getCurrentUrl();
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String personalWidgetPageUrl = prop.getProperty("personalWidgetPageUrl");
		String personalWidgetPageUrlProd = prop.getProperty("WidgetPageUrlProd");
		if (url.contains(personalWidgetPageUrl)) {
			webDB.waitForElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
			flag = webDB.isElementDisplayed(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
			if (flag) {
				log.info("Personal Widget Page accessed successfully");
			}
		} else if (url.equals(personalWidgetPageUrlProd)) {
			flag = webDB.isElementDisplayed(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
			if (flag) {
				log.info("Personal Widget Page accessed successfully");
			}

		}
		Thread.sleep(2000);
		webDB.pressEscapeKey();
		return flag;
	}

	public static boolean verifyAbandonedCartPage() throws InterruptedException, FileNotFoundException, IOException {
		flag = webDB.waitForElement(AbandonedCartLocators.ABCART_TAB, ElementType.LinkText);
		webDB.clickAnElement(AbandonedCartLocators.ABCART_TAB, ElementType.LinkText);
		url = webDB.driver.getCurrentUrl();
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String abcartUrl = prop.getProperty("abcartUrl");
		if (url == abcartUrl) {
			flag = webDB.isElementDisplayed(AbandonedCartLocators.ABCART_HEADERS, ElementType.CssSelector);
			if (flag) {
				log.info("Personal Widget Page accessed successfully");
			}
		}
		return flag;
	}

	public static boolean verifyOrderCRMPage() throws InterruptedException, FileNotFoundException, IOException {
		flag = webDB.waitForElement(OrderCRMLocators.ODR_CRM_TAB, ElementType.LinkText);
		webDB.clickAnElement(OrderCRMLocators.ODR_CRM_TAB, ElementType.LinkText);
		url = webDB.driver.getCurrentUrl();
		log.info(url);
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String orderCRMUrl = prop.getProperty("orderCRMUrl");
		if (url == orderCRMUrl) {
			flag = webDB.isElementDisplayed(OrderCRMLocators.ODR_CRM_PAGE_CONTENTS, ElementType.CssSelector);
			if (flag) {
				log.info("Personal Widget Page accessed successfully");
			}
		}
		return flag;
	}

	public static boolean verifyCODPage() throws InterruptedException, FileNotFoundException, IOException {
		flag = webDB.waitForElement(CODPageLocators.COD_TAB, ElementType.LinkText);
		webDB.clickAnElement(CODPageLocators.COD_TAB, ElementType.LinkText);
		url = webDB.driver.getCurrentUrl();
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String orderCRMUrl = prop.getProperty("codPageUrl");
		if (url == orderCRMUrl) {
			flag = webDB.isElementDisplayed(CODPageLocators.COD_PAGE_CONTENT, ElementType.CssSelector);
			if (flag) {
				log.info("Personal Widget Page accessed successfully");
			}
		}
		return flag;
	}

	public static boolean verifyOptinNumbersPage() throws InterruptedException, FileNotFoundException, IOException {
		webDB.driver.navigate().refresh();
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
		flag = webDB.waitForElement(CustomersLocators.OPTIN_NUMBERS_TAB, ElementType.Xpath);
		webDB.clickAnElement(CustomersLocators.OPTIN_NUMBERS_TAB, ElementType.Xpath);
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			webDB.clickAnElement(CommonLocators.CLOSEICON, ElementType.Xpath);
		}
		url = webDB.driver.getCurrentUrl();
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String optinNumbersPageUrlQA = prop.getProperty("optinNumbersPageUrlQA");
		String optinNumbersUrlProd = prop.getProperty("optinNumbersPageUrlProd");
		System.out.println(optinNumbersPageUrlQA);
		System.out.println(url);
		if (url.equalsIgnoreCase(optinNumbersUrlProd)) {
			flag = webDB.isElementDisplayed(CustomersLocators.EXPORT_OPTIN_LIST, ElementType.Xpath);
			if (flag) {
				log.info("Optin Numbers Page accessed successfully");
			}
		} else if (url.equals(optinNumbersPageUrlQA)) {
			flag = webDB.isElementDisplayed(CustomersLocators.EXPORT_OPTIN_LIST, ElementType.Xpath);
			if (flag) {
				log.info("Optin Numbers Page accessed successfully");
			}

		}
		return flag;
	}

	public static boolean verifyTemplatesPage() throws InterruptedException, FileNotFoundException, IOException {
		webDB.driver.navigate().refresh();
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
		flag = webDB.waitForElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
		webDB.javaScriptClickAnElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			webDB.clickAnElement(CommonLocators.CLOSEICON, ElementType.Xpath);
		}
		url = webDB.driver.getCurrentUrl();
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String templatesPageUrlQA = prop.getProperty("TemplatesPageUrlQA");
		String templatesPageUrlProd = prop.getProperty("TemplatesPageUrlProd");
		if (url.equals(templatesPageUrlQA)) {
			flag = webDB.isElementDisplayed(AutomationLocators.TEMPLATE_PAGE_CONTENT, ElementType.CssSelector);
			if (flag) {
				log.info("Templates Page accessed successfully");
			}
		} else if (url.equals(templatesPageUrlProd)) {
			flag = webDB.isElementDisplayed(AutomationLocators.TEMPLATE_PAGE_CONTENT, ElementType.CssSelector);
			if (flag) {
				log.info("Templates Page accessed successfully");
			}

		}
		return flag;
	}

	public static boolean verifyMessagingPage() throws InterruptedException, FileNotFoundException, IOException {
		webDB.driver.navigate().refresh();
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			flag = webDB.waitForElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			if (flag) {
				Thread.sleep(1500);
				webDB.clickAnElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			}
		}
		flag = webDB.waitForElement(AutomationLocators.MESSAGINGTAB, ElementType.Xpath);
		webDB.javaScriptClickAnElement(AutomationLocators.MESSAGINGTAB, ElementType.Xpath);
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			webDB.clickAnElement(CommonLocators.CLOSEICON, ElementType.Xpath);
		}
		url = webDB.driver.getCurrentUrl();
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String templatesPageUrlQA = prop.getProperty("MessagingURL");
		String MessagingPageUrlProd = prop.getProperty("MessagingpageURLProd");
		if (url.equals(templatesPageUrlQA)) {
			flag = webDB.isElementDisplayed(AutomationLocators.TEMPLATE_PAGE_CONTENT, ElementType.CssSelector);
			if (flag) {
				log.info("Messaging Page accessed successfully");
			}
		} else if (url.equals(MessagingPageUrlProd)) {
			flag = webDB.isElementDisplayed(AutomationLocators.TEMPLATE_PAGE_CONTENT, ElementType.CssSelector);
			if (flag) {
				log.info("Messaging Page accessed successfully");
			}

		}

		return flag;
	}

	public static boolean verifyCRMToggleDisable() throws InterruptedException {
		// modified - added disabling togglebutton code.
//		flag = webDB.waitForElement(MessagingLocators.NEW_CRMTOOL_INPUT, ElementType.Xpath);
		Thread.sleep(2000);
		flag = webDB.isElementDisplayed(MessagingLocators.NEW_CRMTOOL_INPUT, ElementType.Xpath);
		if (flag) {
			element = webDB.driver.findElement(By.xpath(MessagingLocators.NEW_CRMTOOL_INPUT));
			Thread.sleep(2000);
			if (element.isSelected()) {
				Thread.sleep(2000);
//				webDB.clickAnElement(MessagingLocators.NEW_CRMTOOL_TOGGLE, ElementType.Xpath);
				webDB.javaScriptClickAnElement(MessagingLocators.NEW_CRMTOOL_TOGGLE, ElementType.Xpath);
//				webDB.driver.navigate().refresh();
				log.info("New CRM tool disabled");
			} else {
				System.out.println("New CRM tool is already disabled");
			}
		}
		return flag;
	}

	public static boolean verifyFAQPage() throws InterruptedException, FileNotFoundException, IOException {
		flag = webDB.waitForElement(FAQsLocators.FAQs_TAB, ElementType.Xpath);
		webDB.clickAnElement(FAQsLocators.FAQs_TAB, ElementType.Xpath);
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String faqsUrl = prop.getProperty("FAQsPageUrl");
		Thread.sleep(3000);
		windows = webDB.driver.getWindowHandles();
		it = windows.iterator();
		parentWindow = it.next();
		childWindow = it.next();
		webDB.driver.switchTo().window(childWindow);
		url = webDB.driver.getCurrentUrl();
		log.info(url);
		if (url.equals(faqsUrl)) {
			flag = true;
			log.info("FAQs Page accessed successfully");
		} else {
			log.error("FAQs Page could not be accessed");
			flag = false;
		}

		return flag;
	}

	public static boolean verifyLogout() throws InterruptedException {
		flag = webDB.waitForElement(CommonLocators.PROFILENAME, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(CommonLocators.PROFILENAME, ElementType.Xpath);
			Thread.sleep(1000);
			flag = webDB.waitForElement(CommonLocators.LOG_OUT, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(CommonLocators.LOG_OUT, ElementType.Xpath);
				webDB.clickAnElement(CommonLocators.LOG_OUT, ElementType.Xpath);
				log.debug("Clicked on Logout Button");
				flag = webDB.isElementDisplayed(LoginPageLocators.GETAPP_BTN, ElementType.Xpath);
				if (flag) {
					flag = true;
					log.info("Logged out successfully");
				} else {
					flag = false;
				}
			}
		}
		return flag;
	}

	public static boolean verifyGoogleTranslate() throws InterruptedException, FileNotFoundException, IOException {
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			flag = webDB.waitForElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			if (flag) {
				Thread.sleep(1500);
				webDB.clickAnElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			}
		}
		webDB.driver.navigate().refresh();
		webDB.clickAnElement(CommonLocators.SIDENAV, ElementType.CssSelector);
		webDB.moveToElement(CommonLocators.GOOGLE_TRANSLATE, ElementType.CssSelector);
		webDB.clickAnElement(CommonLocators.GOOGLE_TRANSLATE, ElementType.CssSelector);
		log.info(text);
		dropDown = new Select(webDB.driver.findElement(By.cssSelector(CommonLocators.GOOGLE_TRANSLATE)));
		dropDown.selectByVisibleText("Arabic");
		log.debug("Clicked on DropDown and Selected Arabic");
		Thread.sleep(3500);
		text = dropDown.getFirstSelectedOption().getText();
		log.info(text);
		flag = text.equalsIgnoreCase("Arabic");
		if (flag) {
			log.info("Language changed to Arabic");
			Thread.sleep(3500);
			dropDown.selectByVisibleText("English");
			log.debug("Clicked on DropDown and Selected English");
		} else {
			flag = false;
			log.error("Language Updation could not be made");
		}
		log.info(flag);
		return flag;
	}

	public static boolean verifyWhatsappExtension() throws InterruptedException, FileNotFoundException, IOException {
		flag = webDB.waitForElement(WhatsappExtensionLocators.WHATSAPP_EXTN_TAB, ElementType.Xpath);
		webDB.clickAnElement(WhatsappExtensionLocators.WHATSAPP_EXTN_TAB, ElementType.Xpath);
		log.debug("Clicked on WHATSAPP Extension tab");
		url = webDB.driver.getCurrentUrl();
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String whatsappExtensionUrl = prop.getProperty("WhatsappExtensionUrl");
		if (url.equals(whatsappExtensionUrl)) {
			flag = webDB.isElementDisplayed(WhatsappExtensionLocators.DELIGHT_CHAT_INTEGRATION_TEXT, ElementType.Xpath);
			if (flag) {
				log.info("Whatsapp Extension Page accessed successfully");
			}
		}
		return flag;
	}

	public static boolean verifyAdminConfigurtionPage()
			throws InterruptedException, FileNotFoundException, IOException {
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			flag = webDB.waitForElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			if (flag) {
				Thread.sleep(1500);
				webDB.clickAnElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			}
		}
		flag = webDB.waitForElement(SettingsLocators.ADMIN_CONFIGURATION_TAB, ElementType.Xpath);
		webDB.clickAnElement(SettingsLocators.ADMIN_CONFIGURATION_TAB, ElementType.Xpath);
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			webDB.clickAnElement(CommonLocators.CLOSEICON, ElementType.Xpath);
		}
		log.debug("Clicked on Admin Configuration tab");
		webDB.waitForElement(SettingsLocators.WA_SUPPORT_NUM_TEXT_FEILD, ElementType.Xpath);
		url = webDB.driver.getCurrentUrl();
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String adminConfigurationUrlQA = prop.getProperty("AdminConfigurationUrlQA");
		String adminConfigurationUrlProd = prop.getProperty("AdminConfigurationUrlProd");
		if (url.equals(adminConfigurationUrlQA)) {
			flag = webDB.isElementDisplayed(SettingsLocators.WHATSAPP_CONFIGURATION_HEADING, ElementType.Xpath);
			if (flag) {
				flag = webDB.isElementDisplayed(SettingsLocators.WA_SUPPORT_NUM_TEXT_FEILD, ElementType.Xpath);
				if (flag) {
					log.info("Admin Configuration Page accessed successfully");
				}
			}
		} else if (url.equals(adminConfigurationUrlProd)) {
			flag = webDB.isElementDisplayed(SettingsLocators.WHATSAPP_CONFIGURATION_HEADING, ElementType.Xpath);
			if (flag) {
				flag = webDB.isElementDisplayed(SettingsLocators.WA_SUPPORT_NUM_TEXT_FEILD, ElementType.Xpath);
				if (flag) {
					log.info("Admin Configuration Page accessed successfully");
				}
			}
		}
		return flag;
	}

	public static String getItemFromLocalStorage(String key) {
		return (String) common.executeScript(String.format("return window.localStorage.getItem('%s');", key));
	}

	public static boolean verifyMessageLogsPage() throws InterruptedException, FileNotFoundException, IOException {
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			flag = webDB.waitForElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			if (flag) {
				Thread.sleep(1500);
				webDB.clickAnElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			}
		}
		flag = webDB.waitForElement(AnalyticsLocators.ANALYTICS_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AnalyticsLocators.ANALYTICS_TAB, ElementType.Xpath);
			if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
					|| (System.getProperty("os").equals("Android"))) {
				webDB.clickAnElement(CommonLocators.CLOSEICON, ElementType.Xpath);
			}
			Thread.sleep(2500);
			flag = webDB.waitForElement(MessageLogsLocators.MESSAGE_LOGS_TAB, ElementType.Xpath);
			webDB.javaScriptClickAnElement(MessageLogsLocators.MESSAGE_LOGS_TAB, ElementType.Xpath);
			propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
			prop.load(new FileInputStream(propertyFilePath));
			String MessageLogsUrlQA = prop.getProperty("MessageLogsPageURLQA");
			String MessageLogsUrlProd = prop.getProperty("AnalyticsUrlProd");
			Thread.sleep(3000);
			url = webDB.driver.getCurrentUrl();
			if (url.equals(MessageLogsUrlQA)) {
				log.info("Message Logs Page accessed successfully");
			} else if (url.equals(MessageLogsUrlProd)) {
				log.info("Message Logs Page accessed successfully");
			}
		}

		return flag;

	}

	public static boolean headertextcheck(String displaytext) throws InterruptedException {
		Thread.sleep(2000);
//		webDB.driver.navigate().refresh();
//		common.executeScript("window.localStorage.clear();");
//		webDB.driver.navigate().refresh();
		WebElement HeaderText = webDB.driver
				.findElement(By.xpath(CommonLocators.HEADER_TEXT + "'" + displaytext + "')]"));

		if (HeaderText.getText().equalsIgnoreCase("Widget Settings")) {
			common.executeScript("window.localStorage.clear();");
			webDB.driver.navigate().refresh();
			flag = webDB.isElementDisplayed(CommonLocators.CONTACT_US_BTN, ElementType.Xpath);
			log.info("Contact Us button is displayed on Personal Widget Page");
			flag = webDB.isElementDisplayed(CommonLocators.Rating_APP_BTN, ElementType.Xpath);
//			log.info("Rating App button is displayed on Personal Widget Page");
			Thread.sleep(2000);
		} else if (HeaderText.getText().equalsIgnoreCase("Automated Abandoned Cart Recovery")) {
			common.executeScript("window.localStorage.clear();");
			webDB.driver.navigate().refresh();
			flag = webDB.isElementDisplayed(CommonLocators.CONTACT_US_BTN, ElementType.Xpath);
			log.info("Contact Us button is displayed on Abandoned Cart Page");
			flag = webDB.isElementDisplayed(CommonLocators.Rating_APP_BTN, ElementType.Xpath);
			log.info("Rating App button is displayed on Abandoned Cart Page");
			Thread.sleep(2000);
		} else if (HeaderText.getText().equalsIgnoreCase("Automated Orders CRM")) {
			common.executeScript("window.localStorage.clear();");
			webDB.driver.navigate().refresh();
			flag = webDB.isElementDisplayed(CommonLocators.CONTACT_US_BTN, ElementType.Xpath);
			System.out.println("Contact Us button is displayed on Orders CRM Page");
			flag = webDB.isElementDisplayed(CommonLocators.Rating_APP_BTN, ElementType.Xpath);
			log.info("Rating App button is displayed Orders CRM Page");
			Thread.sleep(2000);
		} else if (HeaderText.getText().equalsIgnoreCase("Automated COD Order Confirmation")) {
			common.executeScript("window.localStorage.clear();");
			webDB.driver.navigate().refresh();
			flag = webDB.isElementDisplayed(CommonLocators.CONTACT_US_BTN, ElementType.Xpath);
			log.info("Contact Us button is displayed on COD Order Confirmation Page");
			flag = webDB.isElementDisplayed(CommonLocators.Rating_APP_BTN, ElementType.Xpath);
			System.out.println("Rating App button is displayed on COD Order Confirmation Page");
			Thread.sleep(2000);
		} else if (HeaderText.getText().equalsIgnoreCase("Customers")) {
			common.executeScript("window.localStorage.clear();");
			webDB.driver.navigate().refresh();
			flag = webDB.isElementDisplayed(CommonLocators.CONTACT_US_BTN, ElementType.Xpath);
			log.info("Contact Us button is displayed on Opt-in Numbers Page");
			Thread.sleep(2000);
		} else if (HeaderText.getText().equalsIgnoreCase("Templates")) {
			common.executeScript("window.localStorage.clear();");
			webDB.driver.navigate().refresh();
			flag = webDB.isElementDisplayed(CommonLocators.CONTACT_US_BTN, ElementType.Xpath);
			log.info("Contact Us button is displayed on Templates Page");
			Thread.sleep(2000);
		} else if (HeaderText.getText().equalsIgnoreCase("Apply for WhatsApp API to access for your phone number")) {
			webDB.clickAnElement(MyWhatsAppLocators.APPLICATION_FORM_BTN, ElementType.Xpath);
			webDB.moveToElement(CommonLocators.CONTACT_US_BTN, ElementType.Xpath);
			flag = webDB.isElementDisplayed(CommonLocators.CONTACT_US_BTN, ElementType.Xpath);
			log.info("Contact Us button is displayed on WhatsAPP API Page");
			Thread.sleep(2000);
		}
		return flag;
	}

	public static boolean VerifyWhatsAPPAPIPage() throws InterruptedException, FileNotFoundException, IOException {
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			flag = webDB.waitForElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			if (flag) {
				Thread.sleep(1500);
				webDB.clickAnElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			}
		}
		flag = webDB.waitForElement(MyWhatsAppLocators.WHATSAPPAPI_PAGE_TAB, ElementType.Xpath);
		webDB.clickAnElement(MyWhatsAppLocators.WHATSAPPAPI_PAGE_TAB, ElementType.Xpath);
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			webDB.clickAnElement(CommonLocators.CLOSEICON, ElementType.Xpath);
		}
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String WhatsAppAPIPageURL = prop.getProperty("WhatsAppAPIPageURL");
		String WhatsAppAPIPageProdURL = prop.getProperty("WhatsAppAPIPageProdURL");
		Thread.sleep(3000);
		url = webDB.driver.getCurrentUrl();
		log.info(url);
		if (url.equals(WhatsAppAPIPageURL)) {
			flag = true;
			log.info("WhatsApp API Page accessed successfully");
		} else if (url.equals(WhatsAppAPIPageProdURL)) {
			flag = true;
			log.info("WhatsApp API Page accessed successfully");
		} else {
			flag = false;
		}
		return flag;
	}

	public static boolean verifyManualMessagingPage() throws InterruptedException, FileNotFoundException, IOException {
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
		flag = webDB.waitForElement(MessagingLocators.MANUAL_MESSAGING_TAB, ElementType.Xpath);
		webDB.clickAnElement(MessagingLocators.MANUAL_MESSAGING_TAB, ElementType.Xpath);
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			webDB.clickAnElement(CommonLocators.CLOSEICON, ElementType.Xpath);
		}
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String ManualMessagingURL = prop.getProperty("MessagingURL");
		url = webDB.driver.getCurrentUrl();
		if (url.equals(ManualMessagingURL)) {
			flag = true;
			System.out.println("Manual Messaging Page accessed successfully");
		} else {
			flag = false;
		}

		return flag;
	}

	public static boolean verifyAnalyticsPage() throws InterruptedException, FileNotFoundException, IOException {
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
		flag = webDB.waitForElement(AnalyticsLocators.ANALYTICS_TAB, ElementType.Xpath);
		webDB.clickAnElement(AnalyticsLocators.ANALYTICS_TAB, ElementType.Xpath);
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			webDB.clickAnElement(CommonLocators.CLOSEICON, ElementType.Xpath);
		}
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String AnalyticsUrl = prop.getProperty("AnalyticsUrl");
		String AnalyticsUrlProd = prop.getProperty("AnalyticsUrlProd");
		url = webDB.driver.getCurrentUrl();
		log.info(url);
		if (url.equals(AnalyticsUrl)) {
			System.out.println("Analytics Page accessed successfully");
		} else if (url.equals(AnalyticsUrlProd)) {
			System.out.println("Analytics Page accessed successfully");
		}
		return flag;
	}

	public static void clearLocalStorage() {
		JavascriptExecutor jse = (JavascriptExecutor) webDB.driver;
		jse.executeScript("window.localStorage.clear();");
		log.info("local storage cleared");
		webDB.driver.navigate().refresh();
	}

	public static void closeAllChildWindows() {
		parentWindow = webDB.driver.getWindowHandle();
		windows = webDB.driver.getWindowHandles();
		Iterator<String> iterator = windows.iterator();
		while (iterator.hasNext()) {
			childWindow = iterator.next();
			if (!parentWindow.equals(childWindow)) {
				webDB.driver.switchTo().window(childWindow);
				webDB.driver.close();

			}
		}
		webDB.driver.switchTo().window(parentWindow);
	}

	public static boolean verifyEmailTemplatesPage() throws IOException, InterruptedException {
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			flag = webDB.waitForElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			if (flag) {
				Thread.sleep(1500);
				webDB.clickAnElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			}
		}
		webDB.driver.navigate().refresh();
		Thread.sleep(2000);
		log.info("refresh");
		webDB.pressEscapeKey();
		webDB.moveToElement(EmailTemplatesLocators.EMAIL_TEMPLATES_TAB, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.waitForElement(EmailTemplatesLocators.EMAIL_TEMPLATES_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmailTemplatesLocators.EMAIL_TEMPLATES_TAB, ElementType.Xpath);
			if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
					|| (System.getProperty("os").equals("Android"))) {
				webDB.clickAnElement(CommonLocators.CLOSEICON, ElementType.Xpath);
			}
			propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
			prop.load(new FileInputStream(propertyFilePath));
			String EmailTemplatesPageUrl = prop.getProperty("EmailTemplatesPageUrl");
			log.info(EmailTemplatesPageUrl);
			url = webDB.driver.getCurrentUrl();
			if (url.contains(EmailTemplatesPageUrl)) {
				flag = true;
				log.info("Emails Page accessed successfully");
			} else {
				flag = false;
			}
		}
		webDB.driver.navigate().refresh();
		return flag;
	}

	public static boolean verifyEmailConfigurationPage() throws IOException, InterruptedException {
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			flag = webDB.waitForElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			if (flag) {
				Thread.sleep(1500);
				webDB.clickAnElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			}
		}
		flag = webDB.waitForElement(EmailConfigurationLocators.CONFIGURATION_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmailConfigurationLocators.CONFIGURATION_TAB, ElementType.Xpath);
			if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
					|| (System.getProperty("os").equals("Android"))) {
				webDB.clickAnElement(CommonLocators.CLOSEICON, ElementType.Xpath);
			}
			propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
			prop.load(new FileInputStream(propertyFilePath));
			String EmailConfigurationPageUrl = prop.getProperty("EmailConfigurationPage");
			log.info(EmailConfigurationPageUrl);
			url = webDB.driver.getCurrentUrl();
			log.info(url);
			if (url.equals(EmailConfigurationPageUrl)) {
				flag = true;
				log.info("Emails Configuration Page accessed successfully");
			} else {
				flag = false;
			}
		}
		return flag;
	}

	public static boolean verifyEmailLogsPage() throws IOException, InterruptedException {
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			flag = webDB.waitForElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			if (flag) {
				Thread.sleep(1500);
				webDB.clickAnElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			}
		}
		flag = webDB.waitForElement(EmailTemplatesLocators.EMAIL_TEMPLATES_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmailTemplatesLocators.EMAIL_TEMPLATES_TAB, ElementType.Xpath);
			if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
					|| (System.getProperty("os").equals("Android"))) {
				webDB.clickAnElement(CommonLocators.CLOSEICON, ElementType.Xpath);
			}
			webDB.waitForElement(EmailLogsLocators.LOGS_TAB, ElementType.Xpath);
			webDB.clickAnElement(EmailLogsLocators.LOGS_TAB, ElementType.Xpath);
			propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
			prop.load(new FileInputStream(propertyFilePath));
			String EmailLogsPageUrl = prop.getProperty("EmailLogsPageUrl");
			log.info(EmailLogsPageUrl);
			url = webDB.driver.getCurrentUrl();
			log.info(url);
			if (url.equals(EmailLogsPageUrl)) {
				flag = true;
				log.info("Emails Logs Page accessed successfully");
			} else {
				flag = false;
			}
		}
		return flag;
	}

	public static boolean verifyMarketCampaignsPage() throws InterruptedException, FileNotFoundException, IOException {
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
		flag = webDB.waitForElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
			if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
					|| (System.getProperty("os").equals("Android"))) {
				webDB.clickAnElement(CommonLocators.CLOSEICON, ElementType.Xpath);
			}
			propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
			prop.load(new FileInputStream(propertyFilePath));
			String PageURL = prop.getProperty("MarketCampaignsPageURL");
			String PageURLProd = prop.getProperty("MarketCampaignsPageURLProd");
			url = webDB.driver.getCurrentUrl();
			log.info(url);
			if (url.equals(PageURL)) {
				System.out.println(" MarketCampaignsPage accessed successfully");
			} else if (url.equals(PageURLProd)) {
				System.out.println(" MarketCampaignsPage accessed successfully");
			}
		}
		webDB.pressEscapeKey();
		return flag;
	}

	public static boolean verifyAdminLogout() {
		flag = webDB.waitForElement(CommonLocators.ADMIN_PROFILE, ElementType.CssSelector);
		if (flag) {
			webDB.clickAnElement(CommonLocators.ADMIN_PROFILE, ElementType.CssSelector);
			flag = webDB.waitForElement(CommonLocators.ADMIN_LOGOUT, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CommonLocators.ADMIN_LOGOUT, ElementType.Xpath);
				log.debug("Clicked on Logout Button");
				flag = webDB.isElementDisplayed(CommonLocators.ADMIN_LOGIN, ElementType.CssSelector);
				if (flag) {
					flag = true;
					log.info("Logged out successfully");
				} else {
					flag = false;
				}
			}
		}
		return flag;
	}

	public static boolean verifyChatBotPage() throws InterruptedException, FileNotFoundException, IOException {
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			flag = webDB.waitForElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			if (flag) {
				Thread.sleep(1500);
				webDB.clickAnElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			}
		}
		webDB.moveToElement(ChatBotLocators.CHATBOT_TAB, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.waitForElement(ChatBotLocators.CHATBOT_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ChatBotLocators.CHATBOT_TAB, ElementType.Xpath);
			if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
					|| (System.getProperty("os").equals("Android"))) {
				webDB.clickAnElement(CommonLocators.CLOSEICON, ElementType.Xpath);
			}
			url = webDB.driver.getCurrentUrl();
			propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
			prop.load(new FileInputStream(propertyFilePath));
			String chatBotUrl = prop.getProperty("ChatBotUrl");
			String chatBotProdUrl = prop.getProperty("ChatBotProdUrl");
			if (url == chatBotUrl) {
				flag = webDB.isElementDisplayed(ChatBotLocators.CHATBOT_HEADER, ElementType.Xpath);
				if (flag) {
					log.info("Chatbot Page accessed successfully");
				}
			} else if (url == chatBotProdUrl) {
				flag = webDB.isElementDisplayed(ChatBotLocators.CHATBOT_HEADER, ElementType.Xpath);
				if (flag) {
					log.info("Chatbot Page accessed successfully");
				}
			}
		}
		return flag;
	}

	public static boolean verifyNewStoreLoginPage(String filename)
			throws InterruptedException, IOException, FileNotFoundException {
		verifyLogout();
		clearLocalStorage();
		clearCookies();
		Thread.sleep(3000);
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String QAtoken = prop.getProperty(filename);
		System.out.println(QAtoken);
		String ProdToken = prop.getProperty("ProdToken");
		String Qaurl = prop.getProperty("QA_url");
		String Produrl = prop.getProperty("Prod_url");
		url = webDB.driver.getCurrentUrl();
		if (url.contains("qa")) {
			webDB.driver.manage().addCookie(new Cookie("access_token", QAtoken));
			webDB.driver.navigate().refresh();
		} else if (url.contains("beta")) {
			webDB.driver.manage().addCookie(new Cookie("access_token", ProdToken));
			webDB.driver.navigate().refresh();
		}
		Thread.sleep(5000);
		if (url.contains("qa")) {
			webDB.driver.navigate().to(Qaurl);
		} else if (url.contains("beta")) {
			webDB.driver.navigate().to(Produrl);
		}
//			flag =webDB.waitForElement(PlansPageLocators.PLANS_PAGE_BUTTONS, ElementType.Xpath);
//			flag = webDB.driver.findElement(By.cssSelector(PlansPageLocators.PLANS_PAGE_BUTTONS)).isDisplayed();
		log.info("Logged in Successfully");
		Thread.sleep(3000);
		webDB.pressEscapeKey();
		flag = true;
		return flag;

	}

	public static boolean verifySharedTeamInboxPage() throws InterruptedException, FileNotFoundException, IOException {
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			flag = webDB.waitForElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			if (flag) {
				Thread.sleep(1500);
				webDB.clickAnElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			}
		}
		flag = webDB.waitForElement(CommonLocators.SHAREDTEAMINBOX, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonLocators.SHAREDTEAMINBOX, ElementType.Xpath);
			if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
					|| (System.getProperty("os").equals("Android"))) {
				webDB.clickAnElement(CommonLocators.CLOSEICON, ElementType.Xpath);
			}
			url = webDB.driver.getCurrentUrl();
			propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
			prop.load(new FileInputStream(propertyFilePath));
			String PageUrl = prop.getProperty("SharedTeamInboxUrl");
			String PageProdUrl = prop.getProperty("SharedTeamInboxProdUrl");
			if (url == PageUrl) {
				log.info("Landed on Shared Team Inbox page");
			} else if (url == PageProdUrl) {
				log.info("Landed on Shared Team Inbox page");
			}
		}
		return flag;
	}

	public static boolean verifyInvalidLogin() throws InterruptedException, FileNotFoundException, IOException {

		verifyLogout();
		clearLocalStorage();
		clearCookies();
		Thread.sleep(3000);
		flag = webDB.waitForElement(LoginPageLocators.STORE_ID, ElementType.CssSelector);
		if (flag) {
			webDB.sendTextToAnElement(LoginPageLocators.STORE_ID, LoginPageLocators.INVALID_ID,
					ElementType.CssSelector);
			flag = webDB.waitForElement(LoginPageLocators.WARNING_ERROR, ElementType.CssSelector);
			if (flag) {
				String invalidText = webDB.getText(LoginPageLocators.WARNING_ERROR, ElementType.CssSelector);
				System.out.println(invalidText);
				flag = invalidText.equals(LoginPageLocators.INVALID_STORE_VALUE);
			}
		}
		return flag;

	}

	public static boolean verifyUnavailableLogin() throws InterruptedException, FileNotFoundException, IOException {

		verifyLogout();
		clearLocalStorage();
		clearCookies();
		Thread.sleep(3000);
		flag = webDB.waitForElement(LoginPageLocators.STORE_ID, ElementType.CssSelector);
		if (flag) {
			webDB.sendTextToAnElement(LoginPageLocators.STORE_ID, LoginPageLocators.UNAVAIL_ID_VALUE,
					ElementType.CssSelector);
			webDB.clickAnElement(LoginPageLocators.LOGIN_BUTTON, ElementType.Xpath);
			String MainWindow = webDB.driver.getWindowHandle();
			webDB.switchToChildWindow();

			Thread.sleep(2000);
			flag = webDB.isElementDisplayed(LoginPageLocators.WARNING_MSG, ElementType.Xpath);
			webDB.driver.switchTo().window(MainWindow);
			Thread.sleep(1000);
			System.out.println("Switch to Main");
		}
		return flag;

	}

	public static boolean verifyInValidEmail() throws InterruptedException, FileNotFoundException, IOException {

		verifyLogout();
		clearLocalStorage();
		clearCookies();
		Thread.sleep(3000);
		String MainWindow = webDB.driver.getWindowHandle();
		flag = webDB.waitForElement(LoginPageLocators.STORE_ID, ElementType.CssSelector);
		if (flag) {
			webDB.sendTextToAnElement(LoginPageLocators.STORE_ID, LoginPageLocators.VALID_ID, ElementType.CssSelector);
			webDB.clickAnElement(LoginPageLocators.LOGIN_BUTTON, ElementType.Xpath);
			webDB.switchToChildWindow();
			Thread.sleep(2000);
			flag = webDB.waitForElement(LoginPageLocators.EMAIL, ElementType.Xpath);
			if (flag) {
				webDB.sendTextToAnElement(LoginPageLocators.EMAIL, LoginPageLocators.INVALID_MAILID, ElementType.Xpath);
				Thread.sleep(3000);
				webDB.waitForElement(LoginPageLocators.EMAIL_BTN, ElementType.Xpath);
				webDB.clickAnElement(LoginPageLocators.EMAIL_BTN, ElementType.Xpath);

				flag = webDB.isElementDisplayed(LoginPageLocators.VALIDATION_MSG, ElementType.Xpath);
				if (flag) {
					String validation_text = webDB.getText(LoginPageLocators.VALIDATION_MSG, ElementType.Xpath);
					System.out.println(validation_text);
					flag = validation_text.equals(LoginPageLocators.VALIDATION_ERROR);
				} else {
					log.info("Validation error is not shown");
				}
			}

			webDB.driver.switchTo().window(MainWindow);
			Thread.sleep(1000);
			System.out.println("Switch to Main");
		}
		return flag;

	}

	public static boolean verifyMyWhatsappPage() throws InterruptedException, FileNotFoundException, IOException {
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			flag = webDB.waitForElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			if (flag) {
				Thread.sleep(1500);
				webDB.clickAnElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			}
		}
		flag = webDB.waitForElement(MyWhatsAppLocators.WHATSAPPAPI_PAGE_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MyWhatsAppLocators.WHATSAPPAPI_PAGE_TAB, ElementType.Xpath);
			if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
					|| (System.getProperty("os").equals("Android"))) {
				webDB.clickAnElement(CommonLocators.CLOSEICON, ElementType.Xpath);
			}
			url = webDB.driver.getCurrentUrl();
			propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
			prop.load(new FileInputStream(propertyFilePath));
			String PageUrl = prop.getProperty("MyWhatsappUrl");
			String PageProdUrl = prop.getProperty("MyWhatsappProdUrl");
			if (url.contains(PageUrl)) {
				log.info("Landed on My Whatsapp page");
			} else if (url.contains(PageProdUrl)) {
				log.info("Landed on My Whatsapp Prod page");
			}
		}
		return flag;
	}

	public static boolean verifyNewTemplatesPage() throws FileNotFoundException, IOException {
		flag = webDB.waitForElement(CommonLocators.TEMPLATES_PAGE, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonLocators.TEMPLATES_PAGE, ElementType.Xpath);
			url = webDB.driver.getCurrentUrl();
			propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
			prop.load(new FileInputStream(propertyFilePath));
			String PageUrl = prop.getProperty("NewTemplatesUrl");
			if (url.contains(PageUrl)) {
				log.info("Landed on My Whatsapp page");
			}
		}
		return flag;
	}

	public static boolean verifywixStoreLoginPage() throws InterruptedException, IOException, FileNotFoundException {
		flag = DriverBase.verifyResponseAPI("GenerateToken_Wix");
		if (flag) {
			// verifyLogout();
			flag = webDB.waitForElement(LoginPageLocators.NEW_LOGIN_WIX, ElementType.Xpath);
			clearLocalStorage();
			clearCookies();
			Thread.sleep(3000);
			propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
			prop.load(new FileInputStream(propertyFilePath));
			// String QAtoken = prop.getProperty(filename);
			// System.out.println(QAtoken);
			String ProdToken = prop.getProperty("ProdToken");
			String Qaurl = prop.getProperty("QA_url");
			String Produrl = prop.getProperty("Prod_url");
			url = webDB.driver.getCurrentUrl();
			if (url.contains("qa")) {
				webDB.driver.manage().addCookie(new Cookie("access_token", DriverBase.token));
				webDB.driver.navigate().refresh();
			} else if (url.contains("beta")) {
				webDB.driver.manage().addCookie(new Cookie("access_token", ProdToken));
				webDB.driver.navigate().refresh();
			}
			Thread.sleep(5000);
			if (url.contains("qa")) {
				webDB.driver.navigate().to(Qaurl);
			} else if (url.contains("beta")) {
				webDB.driver.navigate().to(Produrl);
			}
			webDB.driver.navigate().refresh();
//			flag =webDB.waitForElement(PlansPageLocators.PLANS_PAGE_BUTTONS, ElementType.Xpath);
//			flag = webDB.driver.findElement(By.cssSelector(PlansPageLocators.PLANS_PAGE_BUTTONS)).isDisplayed();
			log.info("Logged in Successfully");
			Thread.sleep(3000);
			webDB.pressEscapeKey();
			flag = webDB.waitForElement(CommonLocators.HOME_BTN, ElementType.Xpath);
			if (flag) {
				log.info("Logged in Successfully");
			} else {
				System.out.println("Error in Login");
				flag = false;
			}
		}
		return flag;
	}

	public static boolean verifyLoginPageWithToken() throws InterruptedException, IOException, FileNotFoundException {
		flag = DriverBase.verifyResponseAPI("GenerateToken");
		if (flag) {
			clearLocalStorage();
			clearCookies();
			// flag = webDB.waitForElement(LoginPageLocators.GETAPP_BTN,
			// ElementType.CssSelector);
			flag = webDB.waitForElement(LoginPageLocators.NEW_LOGIN_SHOPIFY, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				// webDB.clickAnElement(LoginPageLocators.SHOPIFY_LOGIN, ElementType.Xpath);
				propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
				prop.load(new FileInputStream(propertyFilePath));
				// String QAtoken = prop.getProperty();
				// System.out.println(QAtoken);
				String ProdToken = prop.getProperty("ProdToken");
				String Qaurl = prop.getProperty("QA_url");
				String Produrl = prop.getProperty("Prod_url");
				url = webDB.driver.getCurrentUrl();
				if (url.contains("qa")) {
					webDB.driver.manage().addCookie(new Cookie("access_token", DriverBase.token));
					webDB.driver.navigate().refresh();
				} else if (url.contains("beta")) {
					webDB.driver.manage().addCookie(new Cookie("access_token", ProdToken));
					webDB.driver.navigate().refresh();
				}
				Thread.sleep(3000);
				if (url.contains("qa")) {
					webDB.driver.navigate().to(Qaurl);
				} else if (url.contains("beta")) {
					webDB.driver.navigate().to(Produrl);
				}
				webDB.driver.navigate().refresh();
//			flag =webDB.waitForElement(PlansPageLocators.PLANS_PAGE_BUTTONS, ElementType.Xpath);
//			flag = webDB.driver.findElement(By.cssSelector(PlansPageLocators.PLANS_PAGE_BUTTONS)).isDisplayed();
				Thread.sleep(3000);
				webDB.pressEscapeKey();

				flag = webDB.waitForElement(CommonLocators.HOME_BTN, ElementType.Xpath);
				if (flag) {
					log.info("Logged in Successfully");
				} else {
					System.out.println("Error in Login");
					flag = false;
				}
			}
		}
		return flag;
	}

	public static boolean verifyWhatsappInboxPage() throws InterruptedException, FileNotFoundException, IOException {
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			flag = webDB.waitForElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			if (flag) {
				Thread.sleep(1500);
				webDB.clickAnElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			} else {
				System.out.println("Side Navigation is not visible");
			}
		} else {
			flag = webDB.waitForElement(WhatsappInboxLocators.WHATSAPP_INBOX_PAGE, ElementType.Xpath);
			if (flag) {
				log.info("Whatsapp Inbox tab is visible.");
				webDB.clickAnElement(WhatsappInboxLocators.WHATSAPP_INBOX_PAGE, ElementType.Xpath);
				flag = webDB.waitForElement(WhatsappInboxLocators.SUPERLEMON_STORE_HEADING, ElementType.Xpath);
				if (flag) {
					log.info("Inside whatsapp inbox page");
					url = webDB.driver.getCurrentUrl();
					System.out.println(url);
					propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
					prop.load(new FileInputStream(propertyFilePath));
					String WhatsappInboxPageUrl = prop.getProperty("WhatsappInboxPageUrl");
					System.out.println(WhatsappInboxPageUrl);
					if (url.equalsIgnoreCase(WhatsappInboxPageUrl)) {
						log.info("Whatsapp Inbox page accessed successfully");
					}else {
						flag=false;
					}
					
				}

			}

		}

		return flag;
	}

}
