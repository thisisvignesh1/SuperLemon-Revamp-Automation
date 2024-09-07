package commonFunctions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import locators.CampaignsLocators;
import locators.WidgetLocators;
import locators.PlansPageLocators;
import locators.MyWhatsAppLocators;
import utils.DriverBase;
import utils.DriverBase.ElementType;
import utils.Listener;

@SuppressWarnings({ "static-access", "unused" })
public class MyWhatsAppPage {
	private static boolean flag;
	private static Logger log = Logger.getLogger(MyWhatsAppPage.class);
	private static String propertyFilePath;
	private static String url;
	public static Properties prop = new Properties();
	private static DriverBase webDB = new DriverBase();;
	private static Set<String> windows;
	private static Iterator<String> it;
	private static String parentWindow;
	private static String childWindow;
	private static Actions actions;
	private static Select dropDown;
	private static String text;
	private static List<WebElement> elements;
	private static final String WebsiteURL = "https://www.example.xyz";
	private static final String BusinessEntityName = "Example Pvt LTD";
	private static final String BusinessVerticalOpt = "Automotive";
	private static final String LocaleOpt = "ar_EG";
	private static final String TimezoneOpt = "Asia/Kolkata";
	private static final String FB_Business_MangerID = "1900820339959633";
	private static final String AUTOMATED_MESSAGE_TEMPLATE_Opt = "English";
	private static final String DisplayNameWA = "Example Pvt LTD";
	private static final String CountryCode = "India - 91";
	private static final String PhoneNoWA_API = "9167204147";
	private static final String ContactEmailAddress = "example@mail.com";
	private static final String ContactPhoneNo = "+919167204147";
	private static CommonFunctions HeaderText = new CommonFunctions();

	public static boolean VerifyValidationsApplicationForm()
			throws FileNotFoundException, InterruptedException, IOException {

		Thread.sleep(1000);
		flag = webDB.isElementDisplayed(MyWhatsAppLocators.APPLICATION_FORM_BTN, ElementType.Xpath);
		flag = webDB.clickAnElement(MyWhatsAppLocators.APPLICATION_FORM_BTN, ElementType.Xpath);

		// Enter Website URL
		webDB.waitForElement(MyWhatsAppLocators.WEBSITE_URL, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MyWhatsAppLocators.WEBSITE_URL))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(MyWhatsAppLocators.WEBSITE_URL, WebsiteURL, ElementType.Xpath);

		webDB.waitForElement(MyWhatsAppLocators.SAVE_BTN, ElementType.Xpath);
		webDB.clickAnElement(MyWhatsAppLocators.SAVE_BTN, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.isElementDisplayed(MyWhatsAppLocators.ERROR_POPUP_MSG, ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath(MyWhatsAppLocators.ERROR_POPUP_MSG)).getText();
		if (flag) {
			log.info("Error Message - " + text);
		}
		Thread.sleep(5000);

		// Enter Business Entity Name
		webDB.waitForElement(MyWhatsAppLocators.BUSINESS_ENTITY_NAME, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MyWhatsAppLocators.BUSINESS_ENTITY_NAME))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(MyWhatsAppLocators.BUSINESS_ENTITY_NAME, BusinessEntityName, ElementType.Xpath);

		webDB.waitForElement(MyWhatsAppLocators.SAVE_BTN, ElementType.Xpath);
		webDB.clickAnElement(MyWhatsAppLocators.SAVE_BTN, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.isElementDisplayed(MyWhatsAppLocators.ERROR_POPUP_MSG, ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath(MyWhatsAppLocators.ERROR_POPUP_MSG)).getText();
		if (flag) {
			log.info("Error Message - " + text);
		}
		Thread.sleep(5000);

		// Select Business Vertical
		Select BusinessVertical = new Select(webDB.driver.findElement(By.xpath(MyWhatsAppLocators.BusinessVerticalDD)));
		BusinessVertical.selectByVisibleText(BusinessVerticalOpt);
		webDB.waitForElement(MyWhatsAppLocators.SAVE_BTN, ElementType.Xpath);
		webDB.clickAnElement(MyWhatsAppLocators.SAVE_BTN, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.isElementDisplayed(MyWhatsAppLocators.ERROR_POPUP_MSG, ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath(MyWhatsAppLocators.ERROR_POPUP_MSG)).getText();
		if (flag) {
			log.info("Error Message - " + text);
		}
		Thread.sleep(5000);

		// Select Locale
		Select Locale = new Select(webDB.driver.findElement(By.xpath(MyWhatsAppLocators.Locale_DD)));
		Locale.selectByVisibleText(LocaleOpt);
		webDB.waitForElement(MyWhatsAppLocators.SAVE_BTN, ElementType.Xpath);
		webDB.clickAnElement(MyWhatsAppLocators.SAVE_BTN, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.isElementDisplayed(MyWhatsAppLocators.ERROR_POPUP_MSG, ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath(MyWhatsAppLocators.ERROR_POPUP_MSG)).getText();
		if (flag) {
			log.info("Error Message - " + text);
		}
		Thread.sleep(5000);

		// Select TimeZone
		Select TIMEZONE_DD = new Select(webDB.driver.findElement(By.xpath(MyWhatsAppLocators.TIMEZONE_DD)));
		TIMEZONE_DD.selectByVisibleText(TimezoneOpt);
		webDB.waitForElement(MyWhatsAppLocators.SAVE_BTN, ElementType.Xpath);
		webDB.clickAnElement(MyWhatsAppLocators.SAVE_BTN, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.isElementDisplayed(MyWhatsAppLocators.ERROR_POPUP_MSG, ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath(MyWhatsAppLocators.ERROR_POPUP_MSG)).getText();
		if (flag) {
			log.info("Error Message - " + text);
		}
		Thread.sleep(5000);

		// Enter FB Manager ID
		webDB.waitForElement(MyWhatsAppLocators.FACEBOOK_BUSINESS_MANAGER_ID_TXTBOX, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MyWhatsAppLocators.FACEBOOK_BUSINESS_MANAGER_ID_TXTBOX))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(MyWhatsAppLocators.FACEBOOK_BUSINESS_MANAGER_ID_TXTBOX, FB_Business_MangerID,
				ElementType.Xpath);

		webDB.waitForElement(MyWhatsAppLocators.SAVE_BTN, ElementType.Xpath);
		webDB.clickAnElement(MyWhatsAppLocators.SAVE_BTN, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.isElementDisplayed(MyWhatsAppLocators.ERROR_POPUP_MSG, ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath(MyWhatsAppLocators.ERROR_POPUP_MSG)).getText();
		if (flag) {
			log.info("Error Message - " + text);
		}
		Thread.sleep(5000);

		// Enter Display Name
		webDB.waitForElement(MyWhatsAppLocators.DISPLAY_NAME_WA, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MyWhatsAppLocators.DISPLAY_NAME_WA))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(MyWhatsAppLocators.DISPLAY_NAME_WA, DisplayNameWA, ElementType.Xpath);

		webDB.waitForElement(MyWhatsAppLocators.SAVE_BTN, ElementType.Xpath);
		webDB.clickAnElement(MyWhatsAppLocators.SAVE_BTN, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.isElementDisplayed(MyWhatsAppLocators.ERROR_POPUP_MSG, ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath(MyWhatsAppLocators.ERROR_POPUP_MSG)).getText();
		if (flag) {
			log.info("Error Message - " + text);
		}
		Thread.sleep(5000);

		// Select Country Code
		Select COUNTRYCODEDD = new Select(webDB.driver.findElement(By.xpath(MyWhatsAppLocators.COUNTRY_CODE_DD)));
		COUNTRYCODEDD.selectByVisibleText(CountryCode);
		webDB.waitForElement(MyWhatsAppLocators.SAVE_BTN, ElementType.Xpath);
		webDB.clickAnElement(MyWhatsAppLocators.SAVE_BTN, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.isElementDisplayed(MyWhatsAppLocators.ERROR_POPUP_MSG, ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath(MyWhatsAppLocators.ERROR_POPUP_MSG)).getText();
		if (flag) {
			log.info("Error Message - " + text);
		}
		Thread.sleep(5000);

		// Phone Number for WhatsApp API
		webDB.waitForElement(MyWhatsAppLocators.PHONE_NO_WA_API, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MyWhatsAppLocators.PHONE_NO_WA_API))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(MyWhatsAppLocators.PHONE_NO_WA_API, PhoneNoWA_API, ElementType.Xpath);

		webDB.waitForElement(MyWhatsAppLocators.SAVE_BTN, ElementType.Xpath);
		webDB.clickAnElement(MyWhatsAppLocators.SAVE_BTN, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.isElementDisplayed(MyWhatsAppLocators.ERROR_POPUP_MSG, ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath(MyWhatsAppLocators.ERROR_POPUP_MSG)).getText();
		if (flag) {
			log.info("Error Message - " + text);
		}
		Thread.sleep(5000);

		// Automated Message Template Selection
		Select AUTOMATED_MESSAGE_TEMPLATE_DD = new Select(
				webDB.driver.findElement(By.xpath(MyWhatsAppLocators.AUTOMATED_MESSAGE_TEMPLATE_DD)));
		AUTOMATED_MESSAGE_TEMPLATE_DD.selectByVisibleText(AUTOMATED_MESSAGE_TEMPLATE_Opt);
		webDB.waitForElement(MyWhatsAppLocators.SAVE_BTN, ElementType.Xpath);
		webDB.clickAnElement(MyWhatsAppLocators.SAVE_BTN, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.isElementDisplayed(MyWhatsAppLocators.ERROR_POPUP_MSG, ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath(MyWhatsAppLocators.ERROR_POPUP_MSG)).getText();
		if (flag) {
			log.info("Error Message - " + text);
		}
		Thread.sleep(5000);

		// Contact Email Address
		webDB.waitForElement(MyWhatsAppLocators.CONTACT_EMAIL_ADDRESS_TXT, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MyWhatsAppLocators.CONTACT_EMAIL_ADDRESS_TXT))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(MyWhatsAppLocators.CONTACT_EMAIL_ADDRESS_TXT, ContactEmailAddress, ElementType.Xpath);

		webDB.waitForElement(MyWhatsAppLocators.SAVE_BTN, ElementType.Xpath);
		webDB.clickAnElement(MyWhatsAppLocators.SAVE_BTN, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.isElementDisplayed(MyWhatsAppLocators.ERROR_POPUP_MSG, ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath(MyWhatsAppLocators.ERROR_POPUP_MSG)).getText();
		if (flag) {
			log.info("Error Message - " + text);
		}
		Thread.sleep(5000);

		// Contact Phone Number
		webDB.waitForElement(MyWhatsAppLocators.CONTACT_PHONE_NO_TXT, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MyWhatsAppLocators.CONTACT_PHONE_NO_TXT))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(MyWhatsAppLocators.CONTACT_PHONE_NO_TXT, ContactPhoneNo, ElementType.Xpath);

		webDB.waitForElement(MyWhatsAppLocators.SAVE_BTN, ElementType.Xpath);
		webDB.clickAnElement(MyWhatsAppLocators.SAVE_BTN, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.isElementDisplayed(MyWhatsAppLocators.ERROR_POPUP_MSG, ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath(MyWhatsAppLocators.ERROR_POPUP_MSG)).getText();
		if (flag) {
			log.info("Error Message - " + text);
		}
		Thread.sleep(5000);

		return flag;
	}

	public static boolean VerifyFaceBookBusinessManagerID()
			throws FileNotFoundException, InterruptedException, IOException {
//		webDB.driver.manage().deleteCookieNamed("access_token");
//		webDB.driver.manage().addCookie(new Cookie("access_token", "sat_a96d9e1c0f5b41b9b17574a83c5d31ca"));
//		webDB.driver.navigate().refresh();
		Thread.sleep(1000);
		flag = webDB.isElementDisplayed(MyWhatsAppLocators.APPLICATION_FORM_BTN, ElementType.Xpath);
		flag = webDB.clickAnElement(MyWhatsAppLocators.APPLICATION_FORM_BTN, ElementType.Xpath);

		// Enter Website URL
		webDB.waitForElement(MyWhatsAppLocators.WEBSITE_URL, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MyWhatsAppLocators.WEBSITE_URL))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(MyWhatsAppLocators.WEBSITE_URL, WebsiteURL, ElementType.Xpath);

		// Enter Business Entity Name
		webDB.waitForElement(MyWhatsAppLocators.BUSINESS_ENTITY_NAME, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MyWhatsAppLocators.BUSINESS_ENTITY_NAME))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(MyWhatsAppLocators.BUSINESS_ENTITY_NAME, BusinessEntityName, ElementType.Xpath);

		// Select Business Vertical
		Select BusinessVertical = new Select(webDB.driver.findElement(By.xpath(MyWhatsAppLocators.BusinessVerticalDD)));
		BusinessVertical.selectByVisibleText(BusinessVerticalOpt);

		// Select Locale
		Select Locale = new Select(webDB.driver.findElement(By.xpath(MyWhatsAppLocators.Locale_DD)));
		Locale.selectByVisibleText(LocaleOpt);

		// Select TimeZone
		Select TIMEZONE_DD = new Select(webDB.driver.findElement(By.xpath(MyWhatsAppLocators.TIMEZONE_DD)));
		TIMEZONE_DD.selectByVisibleText(TimezoneOpt);

		String invalid_FB_Business_MangerID = "avgsvdhg";
		// Enter FB Manager ID
		webDB.waitForElement(MyWhatsAppLocators.FACEBOOK_BUSINESS_MANAGER_ID_TXTBOX, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MyWhatsAppLocators.FACEBOOK_BUSINESS_MANAGER_ID_TXTBOX))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(MyWhatsAppLocators.FACEBOOK_BUSINESS_MANAGER_ID_TXTBOX, invalid_FB_Business_MangerID,
				ElementType.Xpath);

		// Enter Display Name
		webDB.waitForElement(MyWhatsAppLocators.DISPLAY_NAME_WA, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MyWhatsAppLocators.DISPLAY_NAME_WA))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(MyWhatsAppLocators.DISPLAY_NAME_WA, DisplayNameWA, ElementType.Xpath);

		// Select Country Code
		Select COUNTRYCODEDD = new Select(webDB.driver.findElement(By.xpath(MyWhatsAppLocators.COUNTRY_CODE_DD)));
		COUNTRYCODEDD.selectByVisibleText(CountryCode);

		// Phone Number for WhatsApp API
		webDB.waitForElement(MyWhatsAppLocators.PHONE_NO_WA_API, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MyWhatsAppLocators.PHONE_NO_WA_API))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(MyWhatsAppLocators.PHONE_NO_WA_API, PhoneNoWA_API, ElementType.Xpath);

		// Automated Message Template Selection
		Select AUTOMATED_MESSAGE_TEMPLATE_DD = new Select(
				webDB.driver.findElement(By.xpath(MyWhatsAppLocators.AUTOMATED_MESSAGE_TEMPLATE_DD)));
		AUTOMATED_MESSAGE_TEMPLATE_DD.selectByVisibleText(AUTOMATED_MESSAGE_TEMPLATE_Opt);

		// Contact Email Address
		webDB.waitForElement(MyWhatsAppLocators.CONTACT_EMAIL_ADDRESS_TXT, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MyWhatsAppLocators.CONTACT_EMAIL_ADDRESS_TXT))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(MyWhatsAppLocators.CONTACT_EMAIL_ADDRESS_TXT, ContactEmailAddress, ElementType.Xpath);

		// Contact Phone Number
		webDB.waitForElement(MyWhatsAppLocators.CONTACT_PHONE_NO_TXT, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MyWhatsAppLocators.CONTACT_PHONE_NO_TXT))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(MyWhatsAppLocators.CONTACT_PHONE_NO_TXT, ContactPhoneNo, ElementType.Xpath);

//		webDB.waitForElement(WhatsAppAPILocators.SAVE_BTN, ElementType.Xpath);
//		webDB.clickAnElement(WhatsAppAPILocators.SAVE_BTN, ElementType.Xpath);
//		Thread.sleep(2000);
//		flag = webDB.isElementDisplayed(WhatsAppAPILocators.ERROR_POPUP_MSG, ElementType.Xpath);
//		text = webDB.driver.findElement(By.xpath(WhatsAppAPILocators.ERROR_POPUP_MSG)).getText();
//		if(flag) {log.info("Error Message - " + text);}
//		Thread.sleep(5000);

		Thread.sleep(1000);

		// webDB.driver.waitForElement(WhatsAppAPILocators.TERMS_CHECKBOX,
		// ElementType.Xpath);
		flag = webDB.clickAnElement(MyWhatsAppLocators.TERMS_CHECKBOX, ElementType.Xpath);
		if (flag) {
			webDB.waitForElement(MyWhatsAppLocators.APPLY_FOR_APPROVAL, ElementType.Xpath);
			webDB.clickAnElement(MyWhatsAppLocators.APPLY_FOR_APPROVAL, ElementType.Xpath);
			webDB.isElementDisplayed(MyWhatsAppLocators.ERROR_POPUP_MSG, ElementType.Xpath);
			text = webDB.driver.findElement(By.xpath(MyWhatsAppLocators.ERROR_POPUP_MSG)).getText();
			log.info("Error Message - " + text);
		}
		return flag;
	}

	public static boolean VerifyWhatsAppNoAlreadyExists()
			throws FileNotFoundException, InterruptedException, IOException {
//		webDB.driver.manage().deleteCookieNamed("access_token");
//		webDB.driver.manage().addCookie(new Cookie("access_token", "sat_a96d9e1c0f5b41b9b17574a83c5d31ca"));
		webDB.driver.navigate().refresh();
		Thread.sleep(2000);
		webDB.clickAnElement(MyWhatsAppLocators.WHATSAPPAPI_PAGE_TAB, ElementType.Xpath);
		flag = webDB.isElementDisplayed(MyWhatsAppLocators.APPLICATION_FORM_BTN, ElementType.Xpath);
		flag = webDB.clickAnElement(MyWhatsAppLocators.APPLICATION_FORM_BTN, ElementType.Xpath);

		// Enter Website URL
		webDB.waitForElement(MyWhatsAppLocators.WEBSITE_URL, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MyWhatsAppLocators.WEBSITE_URL))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(MyWhatsAppLocators.WEBSITE_URL, WebsiteURL, ElementType.Xpath);

		// Enter Business Entity Name
		webDB.waitForElement(MyWhatsAppLocators.BUSINESS_ENTITY_NAME, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MyWhatsAppLocators.BUSINESS_ENTITY_NAME))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(MyWhatsAppLocators.BUSINESS_ENTITY_NAME, BusinessEntityName, ElementType.Xpath);

		// Select Business Vertical
		Select BusinessVertical = new Select(webDB.driver.findElement(By.xpath(MyWhatsAppLocators.BusinessVerticalDD)));
		BusinessVertical.selectByVisibleText(BusinessVerticalOpt);

		// Select Locale
		Select Locale = new Select(webDB.driver.findElement(By.xpath(MyWhatsAppLocators.Locale_DD)));
		Locale.selectByVisibleText(LocaleOpt);

		// Select TimeZone
		Select TIMEZONE_DD = new Select(webDB.driver.findElement(By.xpath(MyWhatsAppLocators.TIMEZONE_DD)));
		TIMEZONE_DD.selectByVisibleText(TimezoneOpt);

		String invalid_FB_Business_MangerID = "avgsvdhg";
		// Enter FB Manager ID
		webDB.waitForElement(MyWhatsAppLocators.FACEBOOK_BUSINESS_MANAGER_ID_TXTBOX, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MyWhatsAppLocators.FACEBOOK_BUSINESS_MANAGER_ID_TXTBOX))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(MyWhatsAppLocators.FACEBOOK_BUSINESS_MANAGER_ID_TXTBOX, FB_Business_MangerID,
				ElementType.Xpath);

		// Enter Display Name
		webDB.waitForElement(MyWhatsAppLocators.DISPLAY_NAME_WA, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MyWhatsAppLocators.DISPLAY_NAME_WA))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(MyWhatsAppLocators.DISPLAY_NAME_WA, DisplayNameWA, ElementType.Xpath);

		// Select Country Code
		Select COUNTRYCODEDD = new Select(webDB.driver.findElement(By.xpath(MyWhatsAppLocators.COUNTRY_CODE_DD)));
		COUNTRYCODEDD.selectByVisibleText(CountryCode);

		// Phone Number for WhatsApp API
		webDB.waitForElement(MyWhatsAppLocators.PHONE_NO_WA_API, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MyWhatsAppLocators.PHONE_NO_WA_API))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(MyWhatsAppLocators.PHONE_NO_WA_API, PhoneNoWA_API, ElementType.Xpath);

		// Automated Message Template Selection
		Select AUTOMATED_MESSAGE_TEMPLATE_DD = new Select(
				webDB.driver.findElement(By.xpath(MyWhatsAppLocators.AUTOMATED_MESSAGE_TEMPLATE_DD)));
		AUTOMATED_MESSAGE_TEMPLATE_DD.selectByVisibleText(AUTOMATED_MESSAGE_TEMPLATE_Opt);

		// Contact Email Address
		webDB.waitForElement(MyWhatsAppLocators.CONTACT_EMAIL_ADDRESS_TXT, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MyWhatsAppLocators.CONTACT_EMAIL_ADDRESS_TXT))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(MyWhatsAppLocators.CONTACT_EMAIL_ADDRESS_TXT, ContactEmailAddress, ElementType.Xpath);

		// Contact Phone Number
		webDB.waitForElement(MyWhatsAppLocators.CONTACT_PHONE_NO_TXT, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MyWhatsAppLocators.CONTACT_PHONE_NO_TXT))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(MyWhatsAppLocators.CONTACT_PHONE_NO_TXT, ContactPhoneNo, ElementType.Xpath);

		Thread.sleep(1000);

		flag = webDB.clickAnElement(MyWhatsAppLocators.TERMS_CHECKBOX, ElementType.Xpath);
		if (flag) {
			webDB.waitForElement(MyWhatsAppLocators.APPLY_FOR_APPROVAL, ElementType.Xpath);
			webDB.clickAnElement(MyWhatsAppLocators.APPLY_FOR_APPROVAL, ElementType.Xpath);
			webDB.driver.navigate().refresh();
//			text = webDB.driver.findElement(By.xpath(WhatsAppAPILocators.ERROR_POPUP_MSG)).getText();
//			log.info("Error Message - " + text);
		}
		return flag;

	}

	public static boolean verifyContactRatingfeature() throws InterruptedException {
		Thread.sleep(1000);
		flag = HeaderText.headertextcheck("My WhatsApp");
		return flag;
	}

	public static boolean verifyConversationPricing() throws InterruptedException {
		webDB.driver.get("https://qa.gupshup.io/whatsapp/dashboard");
		webDB.driver.manage().addCookie(new Cookie("smacon",
				"eyJhcGlrZXkiOiJ2dXVweDByMDF6Zmo0cXdvcXNjZXl5YmV4azM3d3BrOSIsIm1ldGEiOnsiYWxsb3dEZXZib3giOnRydWUsImFsbG93Rmxvd0J1aWxkZXIiOnRydWUsImhhc1Zpc2l0ZWRGbG93QnVpbGRlciI6ZmFsc2UsImlzTmV3VXNlciI6ZmFsc2V9LCJwcm9maWxlUGljIjoiaHR0cHM6Ly9saDMuZ29vZ2xldXNlcmNvbnRlbnQuY29tL2EtL0FPaDE0R2loT0pkYTdKMmF4RTRNQUs2MDRyZTNuTnV3MlQ5YUhIaENULUdrPXM5Ni1jIiwidXNlcklkIjoiMTA0Njk1MjY2Mjk0Mjg2MTQ2MDg2IiwidXNlcm5hbWUiOiJNYW10YSBHaXJpIiwidXVpZCI6IjM5NjhhZGI5LWIxZjktNGY1Ni04MTYwLTFhZDViNzQ5NTlmZSJ9"));
		webDB.driver.navigate().refresh();
		Thread.sleep(3000);
		webDB.driver.findElement(By.xpath("//span[contains(text(),'Go Live')]")).click();
		WebDriverWait wait = new WebDriverWait(webDB.driver, 10);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='mat-select-value'])[3]")));
		WebElement droDown = webDB.driver.findElement(By.xpath("(//div[@class='mat-select-value'])[3]"));
		Actions actions = new Actions(webDB.driver);
		actions.moveToElement(droDown).click().build().perform();
//		System.out.println("reached here 2");
//		webDB.driver.findElement(By.xpath("//body/div[4]/div[2]/div[1]/mat-dialog-container[1]/app-go-live[1]/div[1]/div[1]/div[2]/mat-horizontal-stepper[1]/div[2]/div[1]/form[1]/div[1]/div[1]/div[1]/app-pricing-table[1]/form[1]/mat-form-field[1]/div[1]/div[1]/div[1]/mat-select[1]/div[1]/div[1]")).click();
//		System.out.println("clicked here");
//		sl.selectByIndex(0);
//		sl.selectByVisibleText("Canada");

		List<WebElement> elements = webDB.driver.findElements(By.xpath("//span[@class='mat-option-text']"));
//		Select sl = new Select(country);
//		sl.selectByIndex(1);
		int count = 0;
		System.out.println(elements.size());
		for (int i = 0; i < elements.size(); i++) {
			Thread.sleep(2000);
			String countryName = elements.get(i).getText();
//			System.out.println(elements.get(i).getText());
			elements.get(i).click();
			Thread.sleep(3000);
			WebElement businessInitiatedPricing = webDB.driver
					.findElement(By.xpath("(//ul[@class='pricing-field'])[2]/li[1]"));
			WebElement userInitiatedPricing = webDB.driver
					.findElement(By.xpath("(//ul[@class='pricing-field'])[2]/li[2]"));
			String bic = businessInitiatedPricing.getText();
			String uic = userInitiatedPricing.getText();
//			System.out.println(bic);
//			System.out.println(uic);
			flag = bic.isEmpty() && uic.isEmpty();
//			System.out.println(flag);
			if (flag) {
				count++;
				System.out.println(countryName + " " + uic + " " + bic);
			} else {
				System.out.println(countryName + " " + uic + " " + bic);

			}
			Thread.sleep(3000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='mat-select-value'])[3]")));
			droDown = webDB.driver.findElement(By.xpath("(//div[@class='mat-select-value'])[3]"));
			actions.moveToElement(droDown).click().build().perform();
		}
		if (count > 0) {
			flag = false;
			System.out.println("UIC and BIC pricing missing for some countries");
		}
		return flag;
	}

	public static boolean verifyWApageGuide() throws InterruptedException, FileNotFoundException, IOException {
		webDB.clickAnElement(MyWhatsAppLocators.WHATSAPPAPI_PAGE_TAB, ElementType.Xpath);
		flag = webDB.waitForElement(MyWhatsAppLocators.APPLICATION_FORM_BTN, ElementType.Xpath);
		if (flag) {
			Thread.sleep(3000);
			flag = webDB.clickAnElement(MyWhatsAppLocators.APPLICATION_FORM_BTN, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(MyWhatsAppLocators.TUTORIAL_VIDEO, ElementType.Xpath);
				Thread.sleep(3000);
				webDB.pressEscapeKey();
				webDB.waitForElement(PlansPageLocators.PLAN_PAGE, ElementType.Xpath);
			}
		}
		return flag;
	}

	public static boolean verifyWAUploadPhoto() throws InterruptedException, FileNotFoundException, IOException {
		propertyFilePath = (System.getProperty("user.dir") + File.separator + "payload" + File.separator
				+ MyWhatsAppLocators.UPLOADFILE_VALUE);
		Thread.sleep(2000);
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			Listener.reportLog("Mobile device, we cannot handle upload picture");
		} else {
			webDB.sendTextToAnElement(MyWhatsAppLocators.UPLOAD_BTN, propertyFilePath, ElementType.CssSelector);
			Thread.sleep(2000);
			flag = webDB.waitForElement(MyWhatsAppLocators.SAVESETTING_BTN, ElementType.Xpath);
//		webDB.clickAnElement(WhatsAppAPILocators.SAVESETTING_BTN, ElementType.Xpath);
		}
		return flag;
	}

	public static boolean verifyLanguageDropdown() throws Exception {
		flag = webDB.waitForElement(MyWhatsAppLocators.EDIT_LANGUAGE, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MyWhatsAppLocators.EDIT_LANGUAGE, ElementType.Xpath);
			webDB.moveToElement(MyWhatsAppLocators.LANGUAGE_DROPDOWN, ElementType.Xpath);
			Thread.sleep(2000);
			flag = webDB.waitForElement(MyWhatsAppLocators.LANGUAGE_DROPDOWN, ElementType.Xpath);
			if (flag) {
//			String text = webDB.getText(WhatsAppAPILocators.LANGUAGE_DROPDOWN, ElementType.Xpath);
//			System.out.println(text);
				Select select = new Select(webDB.driver.findElement(By.xpath(MyWhatsAppLocators.LANGUAGE_DROPDOWN)));
				WebElement option = select.getFirstSelectedOption();
				System.out.println(option);
				String defaultItem = option.getText();
				System.out.println(defaultItem);
				flag = defaultItem.equals("GERMAN");
				System.out.println(flag);
				if (!flag) {
					verifySwitchGermanLanguage();
					System.out.println("Changed German language");
				}
				verifySwitchEnglishLanguage();
				System.out.println("Changed English language");
			}
		}
		return flag;

	}

	public static void verifySwitchGermanLanguage() throws InterruptedException {
		flag = webDB.waitForElement(MyWhatsAppLocators.EDIT_LANGUAGE, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MyWhatsAppLocators.EDIT_LANGUAGE, ElementType.Xpath);
			flag = webDB.clickAnElement(MyWhatsAppLocators.LANGUAGE_DROPDOWN, ElementType.Xpath);
			Thread.sleep(2000);
			webDB.waitForElement(MyWhatsAppLocators.LANGUAGE_DROPDOWN, ElementType.Xpath);
			webDB.selectDropDownOptions(MyWhatsAppLocators.LANGUAGE_DROPDOWN, MyWhatsAppLocators.GERMAN_VALUE,
					ElementType.Xpath);
			flag = webDB.waitForElement(MyWhatsAppLocators.UPDATE_LANGUGAE, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				webDB.javaScriptClickAnElement(MyWhatsAppLocators.UPDATE_LANGUGAE, ElementType.Xpath);
			}
		}
	}

	public static void verifySwitchEnglishLanguage() throws InterruptedException {
		flag = webDB.waitForElement(MyWhatsAppLocators.EDIT_LANGUAGE, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MyWhatsAppLocators.EDIT_LANGUAGE, ElementType.Xpath);
		}
		flag = webDB.waitForElement(MyWhatsAppLocators.LANGUAGE_DROPDOWN, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			flag = webDB.clickAnElement(MyWhatsAppLocators.LANGUAGE_DROPDOWN, ElementType.Xpath);
			webDB.waitForElement(MyWhatsAppLocators.LANGUAGE_DROPDOWN, ElementType.Xpath);
//			webDB.selectDropDownOptions(WhatsAppAPILocators.LANGUAGE_DROPDOWN,
//					WhatsAppAPILocators.ENGLISHLANGUAGE_DROPDOWN, ElementType.Xpath);
			webDB.clickAnElement(MyWhatsAppLocators.ENGLISHLANGUAGE_DROPDOWN, ElementType.Xpath);
			flag = webDB.waitForElement(MyWhatsAppLocators.UPDATE_LANGUGAE, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				webDB.javaScriptClickAnElement(MyWhatsAppLocators.UPDATE_LANGUGAE, ElementType.Xpath);
			}
		}
	}

	public static boolean verifyProfileDetails() throws Exception {
		String random = RandomStringUtils.randomNumeric(6);
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			webDB.scrollToAnElement(MyWhatsAppLocators.EDIT_ADDRESS, ElementType.Xpath);
		}
		flag = webDB.waitForElement(MyWhatsAppLocators.EDIT_ADDRESS, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MyWhatsAppLocators.EDIT_ADDRESS, ElementType.Xpath);
			flag = webDB.waitForElement(MyWhatsAppLocators.ADDRESSLINE1_INPUT, ElementType.Xpath);
			if (flag) {
				webDB.clearText(MyWhatsAppLocators.ADDRESSLINE1_INPUT);
				Thread.sleep(2000);
				webDB.sendTextToAnElement(MyWhatsAppLocators.ADDRESSLINE1_INPUT, MyWhatsAppLocators.ADDRESSLINE1_VALUE,
						ElementType.Xpath);
				flag = webDB.waitForElement(MyWhatsAppLocators.ADDRESSLINE2_INPUT, ElementType.Xpath);
				if (flag) {
					webDB.clearText(MyWhatsAppLocators.ADDRESSLINE2_INPUT);
					Thread.sleep(2000);
					webDB.sendTextToAnElement(MyWhatsAppLocators.ADDRESSLINE2_INPUT,
							MyWhatsAppLocators.ADDRESSLINE2_VALUE, ElementType.Xpath);
					flag = webDB.waitForElement(MyWhatsAppLocators.STATEINPUT_INPUT, ElementType.Xpath);
					if (flag) {
						webDB.clearText(MyWhatsAppLocators.STATEINPUT_INPUT);
						Thread.sleep(2000);
						webDB.sendTextToAnElement(MyWhatsAppLocators.STATEINPUT_INPUT, MyWhatsAppLocators.STATE_VALUE,
								ElementType.Xpath);
						flag = webDB.waitForElement(MyWhatsAppLocators.ZIPCODE_INPUT, ElementType.Xpath);
						if (flag) {
							webDB.clearText(MyWhatsAppLocators.ZIPCODE_INPUT);
							Thread.sleep(1000);
							webDB.sendTextToAnElement(MyWhatsAppLocators.ZIPCODE_INPUT, random, ElementType.Xpath);
							flag = webDB.waitForElement(MyWhatsAppLocators.SELECTBUSINESS_INPUT, ElementType.Xpath);
							if (flag) {
								Thread.sleep(2000);
								webDB.selectDropDownOptionsByText(MyWhatsAppLocators.SELECTBUSINESS_INPUT,
										MyWhatsAppLocators.SELECTBUSINESS_VERTICAL_VALUE, ElementType.Xpath);
								flag = webDB.waitForElement(MyWhatsAppLocators.EMAIL_INPUT, ElementType.Xpath);
								if (flag) {
									webDB.clearText(MyWhatsAppLocators.EMAIL_INPUT);
									Thread.sleep(1000);
									webDB.sendTextToAnElement(MyWhatsAppLocators.EMAIL_INPUT,
											MyWhatsAppLocators.EMAILID_VALUE, ElementType.Xpath);
									flag = webDB.waitForElement(MyWhatsAppLocators.CITY_INPUT, ElementType.Xpath);
									if (flag) {
										webDB.clearText(MyWhatsAppLocators.CITY_INPUT);
										Thread.sleep(1000);
										webDB.sendTextToAnElement(MyWhatsAppLocators.CITY_INPUT,
												MyWhatsAppLocators.CITY_VALUE, ElementType.Xpath);
										flag = webDB.waitForElement(MyWhatsAppLocators.COUNTRY_INPUT,
												ElementType.Xpath);
										if (flag) {
											webDB.clearText(MyWhatsAppLocators.COUNTRY_INPUT);
											Thread.sleep(2000);
											webDB.sendTextToAnElement(MyWhatsAppLocators.COUNTRY_INPUT,
													MyWhatsAppLocators.COUNTRY_VALUE, ElementType.Xpath);
											flag = webDB.waitForElement(MyWhatsAppLocators.WEBSITEURL_INPUT,
													ElementType.Xpath);
											if (flag) {
												webDB.clearText(MyWhatsAppLocators.WEBSITEURL_INPUT);
												Thread.sleep(1000);
												webDB.sendTextToAnElement(MyWhatsAppLocators.WEBSITEURL_INPUT,
														MyWhatsAppLocators.WEBSITE_VALUE, ElementType.Xpath);
												Thread.sleep(2000);
												flag = webDB.waitForElement(MyWhatsAppLocators.UPDATE_DETAILS,
														ElementType.Xpath);
												if (flag) {
													webDB.clickAnElement(MyWhatsAppLocators.UPDATE_DETAILS,
															ElementType.Xpath);
													flag = webDB.waitForElement(MyWhatsAppLocators.EDIT_ADDRESS,
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
		return flag;

	}

	public static boolean verifyMyWhatsappTutorial() throws InterruptedException, IOException {
		flag = webDB.waitForElement(MyWhatsAppLocators.MYWHATSAPP_PAGE, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			flag = webDB.waitForElement(MyWhatsAppLocators.TUTORIAL_VIDEO, ElementType.Xpath);
			if (flag) {
				webDB.javaScriptClickAnElement(MyWhatsAppLocators.TUTORIAL_VIDEO, ElementType.Xpath);
				Thread.sleep(2000);
				flag = webDB.waitForElement(WidgetLocators.DISPLAY_TEXT_TUTORIAL, ElementType.Xpath);
				if (flag) {
					String text = webDB.getText(WidgetLocators.DISPLAY_TEXT_TUTORIAL, ElementType.Xpath);
					System.out.println(text);
					if (text.equals(MyWhatsAppLocators.TUTORIAL_TITLE)) {
						flag = webDB.waitForElement(CampaignsLocators.WHATSAPPCAMPAIGN_IFRAME, ElementType.CssSelector);
						if (flag) {
							flag = webDB.waitForElement(CampaignsLocators.TUTORIAL_CLOSEBTN, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(CampaignsLocators.TUTORIAL_CLOSEBTN, ElementType.Xpath);
								flag = webDB.waitForElement(CampaignsLocators.TUTORIAL_BTN, ElementType.Xpath);
								if (flag) {
									System.out.println("Verified tutorial videos in MyWhatsapp");
								}
							}
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean verificationDetails() throws Exception {
		String Text = webDB.getText(MyWhatsAppLocators.FB_STATUS, ElementType.Xpath);
		log.info(Text);
		flag = Text.contains("Unverified");
		if (flag) {
			String Textwithmessagelimit = webDB.getText(MyWhatsAppLocators.MESSAGE_LIMIT, ElementType.Xpath);
			log.info(Textwithmessagelimit);
			flag = Textwithmessagelimit.contains("1K/day");
		}
		if (flag) {
			String Textwithrating = webDB.getText(MyWhatsAppLocators.QUALITY_RATING, ElementType.Xpath);
			log.info(Textwithrating);
			flag = Textwithrating.contains("Green");
		}
		List<WebElement> Elements = webDB.driver.findElements(By.xpath(MyWhatsAppLocators.MANDATORY_FIELDS));
		for (WebElement element : Elements) {
			String text = element.getText();
			log.info(text);
			flag = text.contains("*");
		}
		return flag;
	}

}