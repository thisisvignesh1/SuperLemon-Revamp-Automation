package commonFunctions;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import groovyjarjarantlr4.v4.runtime.tree.xpath.XPath;
import locators.AutomationLocators;
import locators.CustomersLocators;
import locators.ShopifyStoreLocators;
import locators.WidgetLocators;
import locators.WixStoreLocators;
import utils.DriverBase;
import utils.Listener;
import utils.DriverBase.ElementType;

public class WixStore {
	private static DriverBase webDB = new DriverBase();
	private static boolean flag;
	private static WebElement element;
	private static JavascriptExecutor jse = (JavascriptExecutor) webDB.driver;
	public static String firstName = "AutoUser";
	public static String lastName = RandomStringUtils.randomNumeric(5);
	private static WebDriverWait wait = new WebDriverWait(webDB.driver, 10);
	private static String phone = "9854" + RandomStringUtils.randomNumeric(6);
	private static String address = "Gupshup Technology";
	private static String city = "Chennai";
	private static String state = "Maharastra";
	private static String pinCode = "400098";
	private static Logger log = Logger.getLogger(CustomersPage.class.getName());
	private static Set<String> windows;
	private static Iterator<String> it;
	private static String parentWindow;
	private static String childWindow;
	private static String url;
	private static JavascriptExecutor js = (JavascriptExecutor) webDB.driver;
	private static String shippingAddress = "Gupshup Technology India Private Limited 11th Main Road 2nd Phase Jayanagar";
	private static Iterator<String> iterator;
	private static JavascriptExecutor adminConfiguration = (JavascriptExecutor) webDB.driver;
	private static String fullName;
	private static List<WebElement> elements;

	@SuppressWarnings("static-access")
	public static String addItemAndCheckout() throws InterruptedException {
		webDB.moveToElement(WixStoreLocators.PRODUCT, ElementType.Xpath);
		flag = webDB.waitForElement(WixStoreLocators.PRODUCT, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WixStoreLocators.PRODUCT, ElementType.Xpath);
			flag = webDB.waitForElement(WixStoreLocators.BUY_NOW, ElementType.Xpath);
			if (flag) {
				// modified - added sleep time
				Thread.sleep(2000);
				webDB.clickAnElement(WixStoreLocators.BUY_NOW, ElementType.Xpath);
				Thread.sleep(3000);
				// modified - added refresh
				webDB.driver.navigate().refresh();
				flag = webDB.waitForElement(WixStoreLocators.CHECKOUT_PAGE, ElementType.Xpath);
				if (flag) {
					Thread.sleep(2000);
					flag = webDB.waitForElement(WixStoreLocators.EMAIL, ElementType.Xpath);
					if (flag) {
						System.out.println("In check out page");
						// modified - replaced wait until clickable to wait for element.
						Thread.sleep(3000);
						webDB.sendTextToAnElement(WixStoreLocators.EMAIL, WixStoreLocators.EMAIL_ID, ElementType.Xpath);
//						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.FIRST_NAME)));
						webDB.waitForElement(WixStoreLocators.FIRST_NAME, ElementType.Xpath);
						Thread.sleep(1000);
						webDB.sendTextToAnElement(WixStoreLocators.FIRST_NAME, firstName, ElementType.Xpath);
//						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.LAST_NAME)));
						webDB.waitForElement(WixStoreLocators.LAST_NAME, ElementType.Xpath);
						Thread.sleep(1000);
						webDB.sendTextToAnElement(WixStoreLocators.LAST_NAME, lastName, ElementType.Xpath);
//						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.PHONE)));
						webDB.waitForElement(WixStoreLocators.PHONE, ElementType.Xpath);
						Thread.sleep(1000);
						webDB.sendTextToAnElement(WixStoreLocators.PHONE, phone, ElementType.Xpath);
//						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.ADDRESS)));
						webDB.waitForElement(WixStoreLocators.ADDRESS, ElementType.Xpath);
						Thread.sleep(1000);
						webDB.sendTextToAnElement(WixStoreLocators.ADDRESS, address, ElementType.Xpath);
//						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.CITY)));
						webDB.waitForElement(WixStoreLocators.CITY, ElementType.Xpath);
						Thread.sleep(1000);
						webDB.sendTextToAnElement(WixStoreLocators.CITY, city, ElementType.Xpath);
						// modified - commented below
//						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.STATE)));
//						webDB.sendTextToAnElement(WixStoreLocators.STATE, state, ElementType.Xpath);
//						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.PINCODE)));
//						webDB.sendTextToAnElement(WixStoreLocators.PINCODE, pinCode, ElementType.Xpath);
						webDB.moveToElement(WixStoreLocators.CONTINUE, ElementType.Xpath);
//						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.CONTINUE)));
						// modified - added wait time.
						Thread.sleep(5000);
						webDB.clickAnElement(WixStoreLocators.CONTINUE, ElementType.Xpath);
						webDB.moveToElement(WixStoreLocators.DELIVERY_CONTINUE, ElementType.Xpath);
						wait.until(
								ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.DELIVERY_CONTINUE)));
						webDB.clickAnElement(WixStoreLocators.DELIVERY_CONTINUE, ElementType.Xpath);
						Thread.sleep(1000);
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.COD)));
						webDB.clickAnElement(WixStoreLocators.COD, ElementType.Xpath);
						webDB.scrollToAnElement(WixStoreLocators.PAYMENT_CONTINUE, ElementType.Xpath);
						wait.until(
								ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.PAYMENT_CONTINUE)));
						webDB.clickAnElement(WixStoreLocators.PAYMENT_CONTINUE, ElementType.Xpath);
						webDB.scrollToAnElement(WixStoreLocators.PLACE_ORDER, ElementType.Xpath);
						webDB.waitForElement(WixStoreLocators.PLACE_ORDER, ElementType.Xpath);
						Thread.sleep(1000);
						// modified - commented below (not available)
//						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.CHECKOUT_OPTIN)));
//						webDB.waitForElement(WixStoreLocators.CHECKOUT_OPTIN, ElementType.Xpath);
//						flag = webDB.IsEnabled(WixStoreLocators.CHECKOUT_OPTIN, ElementType.Xpath);
//						if (!flag) {
//							webDB.clickAnElement(WixStoreLocators.CHECKOUT_OPTIN, ElementType.Xpath);
//						}
//						// webDB.scrollToAnElement(WixStoreLocators.PLACE_ORDER, ElementType.Xpath);
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.PLACE_ORDER)));
						webDB.scrollToAnElement(WixStoreLocators.PLACE_ORDER, ElementType.Xpath);
						webDB.clickAnElement(WixStoreLocators.PLACE_ORDER, ElementType.Xpath);
						Thread.sleep(1000);
						// modified - element type on both
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.THANK_YOU)));
						flag = webDB.isElementDisplayed(WixStoreLocators.THANK_YOU, ElementType.Xpath);
					}
				}
			}
		}
		return phone;
	}

	public static boolean verifyOptinPopUpDisplayed() throws InterruptedException {
		Thread.sleep(2500);
		webDB.moveToElement(WixStoreLocators.PRODUCT, ElementType.Xpath);
		flag = webDB.waitForElement(WixStoreLocators.PRODUCT, ElementType.Xpath);
		if (flag) {
			// modified - added clear local storage.
			adminConfiguration.executeScript("window.localStorage.clear();");
			CommonFunctions.clearLocalStorage();
			webDB.clickAnElement(WixStoreLocators.PRODUCT, ElementType.Xpath);
			Thread.sleep(2000);
			webDB.moveToElement(WixStoreLocators.ADD_TO_CART, ElementType.Xpath);
			flag = webDB.waitForElement(WixStoreLocators.ADD_TO_CART, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WixStoreLocators.ADD_TO_CART, ElementType.Xpath);
				log.info("Clicked Add to cart");
				Thread.sleep(5000);
//				// modified - getting into iframe
//				List<WebElement> iframeElements = webDB.driver.findElements(By.tagName("iframe"));
//				log.info("Total number of iframes are " + iframeElements.size());
//				webDB.driver.switchTo().frame(webDB.driver.findElement(By.xpath("(//iframe[@class='U73P_q'])")));
//				Thread.sleep(2000);
//				log.info("Getting into iframe");
//				// modified - added new locator
//				webDB.waitForElement(WixStoreLocators.VIEW_CART, ElementType.Xpath);
//				webDB.clickAnElement(WixStoreLocators.VIEW_CART, ElementType.Xpath);
//				log.info("view cart is clicked");
//				webDB.driver.switchTo().defaultContent();
				flag = webDB.isElementDisplayed(ShopifyStoreLocators.CONFIRM_BUTTON_OPTIN_POPUP, ElementType.Id);
				if (flag) {
					log.info("Confirm button in optin pop up is displayed");
					// modified locator and type & Commented element displayed and added flag to
					// wait for element.
					flag = webDB.waitForElement(ShopifyStoreLocators.OPTIN_POPUP_COUNTRY_CODE_FIELD, ElementType.Xpath);
//					flag = webDB.isElementDisplayed(ShopifyStoreLocators.OPTIN_POPUP_COUNTRY_CODE_FIELD,
//							ElementType.Xpath);
					if (flag) {
						// modified - locator type
						flag = webDB.isElementDisplayed(ShopifyStoreLocators.OPTIN_POPUP_PHONE_FIELD,
								ElementType.Xpath);
						if (flag) {
							log.info("Optin Pop up displayed on Store");
						} else {
							log.info("Optin Pop Up not displayed on store");
						}
//		webDB.waitForElement(ShopifyStoreLocators.EMPTY_CART_ICON, ElementType.Xpath);
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ShopifyStoreLocators.EMPTY_CART_ICON)));
//		Thread.sleep(1500);
//		webDB.clickAnElement(ShopifyStoreLocators.EMPTY_CART_ICON, ElementType.Xpath);
//		log.debug("Clicked on Empty Cart Icon");
//		flag = webDB.isElementDisplayed(ShopifyStoreLocators.CONFIRM_BUTTON_OPTIN_POPUP, ElementType.Id);
//		flag = webDB.isElementDisplayed(ShopifyStoreLocators.OPTIN_POPUP_PHONE_FIELD, ElementType.Id);
//		flag = webDB.isElementDisplayed(ShopifyStoreLocators.OPTIN_POPUP_COUNTRY_CODE_FIELD, ElementType.Id);
//		if (flag) {
//			log.info("Optin Pop up displayed on Store");
//		} else {
//			log.info("Optin Pop Up not displayed on store");
//		}
					}
				}
			}
		}
		return flag;
	}

	@SuppressWarnings("static-access")
	public static boolean addItem() throws InterruptedException {
		// webDB.switchToQuitChildWindows();
		windows = webDB.driver.getWindowHandles();
		// System.out.println("2nd Total num of windows = " + windows.size());
		webDB.moveToElement(WixStoreLocators.PRODUCT, ElementType.Xpath);
		flag = webDB.waitForElement(WixStoreLocators.PRODUCT, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WixStoreLocators.PRODUCT, ElementType.Xpath);
			flag = webDB.waitForElement(WixStoreLocators.BUY_NOW, ElementType.Xpath);
			if (flag) {
				// modified - commented below and added js click.
//				webDB.clickAnElement(WixStoreLocators.BUY_NOW, ElementType.Xpath);
				webDB.javaScriptClickAnElement(WixStoreLocators.BUY_NOW, ElementType.Xpath);
				Thread.sleep(1000);
				// modified - added refresh to get rid of optin
				webDB.driver.navigate().refresh();
				flag = webDB.waitForElement(WixStoreLocators.CHECKOUT_PAGE, ElementType.Xpath);
//				if (flag) {
//					Thread.sleep(2000);
//					flag = webDB.waitForElement(WixStoreLocators.EMAIL, ElementType.Xpath);
				if (flag) {
					System.out.println("In check out page");
				}
			}
		}
		return flag;
	}

	@SuppressWarnings("static-access")
	public static String checkOutAndEnterDetails() throws InterruptedException {
//		webDB.moveToElement(WixStoreLocators.PRODUCT, ElementType.Xpath);
//		flag = webDB.waitForElement(WixStoreLocators.PRODUCT, ElementType.Xpath);
//		if (flag) {
//			webDB.clickAnElement(WixStoreLocators.PRODUCT, ElementType.Xpath);
//			flag = webDB.waitForElement(WixStoreLocators.BUY_NOW, ElementType.Xpath);
//			if (flag) {
//				webDB.clickAnElement(WixStoreLocators.BUY_NOW, ElementType.Xpath);
//				Thread.sleep(1000);
		flag = webDB.waitForElement(WixStoreLocators.CHECKOUT_PAGE, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			flag = webDB.waitForElement(WixStoreLocators.EMAIL, ElementType.Xpath);
			if (flag) {
				System.out.println("In check out page");
				webDB.sendTextToAnElement(WixStoreLocators.EMAIL, WixStoreLocators.EMAIL_ID, ElementType.Xpath);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.FIRST_NAME)));
				webDB.sendTextToAnElement(WixStoreLocators.FIRST_NAME, firstName, ElementType.Xpath);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.LAST_NAME)));
				webDB.sendTextToAnElement(WixStoreLocators.LAST_NAME, lastName, ElementType.Xpath);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.PHONE)));
				webDB.sendTextToAnElement(WixStoreLocators.PHONE, phone, ElementType.Xpath);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.ADDRESS)));
				webDB.sendTextToAnElement(WixStoreLocators.ADDRESS, address, ElementType.Xpath);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.CITY)));
				webDB.sendTextToAnElement(WixStoreLocators.CITY, city, ElementType.Xpath);
//				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.STATE)));
//						webDB.sendTextToAnElement(WixStoreLocators.STATE, state, ElementType.Xpath);
//				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.PINCODE)));
//				webDB.sendTextToAnElement(WixStoreLocators.PINCODE, pinCode, ElementType.Xpath);
				webDB.moveToElement(WixStoreLocators.CONTINUE, ElementType.Xpath);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.CONTINUE)));
				webDB.clickAnElement(WixStoreLocators.CONTINUE, ElementType.Xpath);
				webDB.moveToElement(WixStoreLocators.DELIVERY_CONTINUE, ElementType.Xpath);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.DELIVERY_CONTINUE)));
				webDB.clickAnElement(WixStoreLocators.DELIVERY_CONTINUE, ElementType.Xpath);
				Thread.sleep(1000);
//						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.COD)));
//						webDB.clickAnElement(WixStoreLocators.COD, ElementType.Xpath);
//						webDB.scrollToAnElement(WixStoreLocators.PAYMENT_CONTINUE, ElementType.Xpath);
//						wait.until(
//								ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.PAYMENT_CONTINUE)));
//						webDB.clickAnElement(WixStoreLocators.PAYMENT_CONTINUE, ElementType.Xpath);
//						webDB.scrollToAnElement(WixStoreLocators.PLACE_ORDER, ElementType.Xpath);
//						webDB.waitForElement(WixStoreLocators.PLACE_ORDER, ElementType.Xpath);
//						Thread.sleep(1000);
//						// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.CHECKOUT_OPTIN)));
//						webDB.waitForElement(WixStoreLocators.CHECKOUT_OPTIN, ElementType.Xpath);
//						flag = webDB.IsEnabled(WixStoreLocators.CHECKOUT_OPTIN, ElementType.Xpath);
//						if (!flag) {
//							webDB.clickAnElement(WixStoreLocators.CHECKOUT_OPTIN, ElementType.Xpath);
//						}
//						// webDB.scrollToAnElement(WixStoreLocators.PLACE_ORDER, ElementType.Xpath);
//						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.PLACE_ORDER)));
//						webDB.scrollToAnElement(WixStoreLocators.PLACE_ORDER, ElementType.Xpath);
//						webDB.clickAnElement(WixStoreLocators.PLACE_ORDER, ElementType.Xpath);
//						Thread.sleep(1000);
//						wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(WixStoreLocators.THANK_YOU)));
//						flag = webDB.isElementDisplayed(WixStoreLocators.THANK_YOU, ElementType.CssSelector);
//					}
//				}
			}
		}
		return phone;
	}

	public static boolean PaymentAndOrder() throws InterruptedException {
		webDB.scrollToAnElement(WixStoreLocators.PAYMENT_CONTINUE, ElementType.Xpath);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.PAYMENT_CONTINUE)));
		webDB.clickAnElement(WixStoreLocators.PAYMENT_CONTINUE, ElementType.Xpath);
		webDB.scrollToAnElement(WixStoreLocators.PLACE_ORDER, ElementType.Xpath);
		webDB.waitForElement(WixStoreLocators.PLACE_ORDER, ElementType.Xpath);
		webDB.clickAnElement(WixStoreLocators.PLACE_ORDER, ElementType.Xpath);
		Thread.sleep(2000);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("razorpay-checkout-frame")));
//		WebDriverWait w = new WebDriverWait(driver, 5);    
//	     w.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("frame-bottom"));
		return flag;
	}

//	@SuppressWarnings("static-access")
//	public static boolean enterCardDetails() throws InterruptedException {
//		List<WebElement> iframeElements = webDB.driver.findElements(By.tagName("iframe"));
//		System.out.println("Total number of iframes are " + iframeElements.size());
//		String currentWindow = driver.getWindowHandle();
//		System.out.println("current window " + currentWindow);
//		webDB.driver.switchTo().frame(webDB.driver.findElement(By.xpath("//iframe[@class='razorpay-checkout-frame']")));
////		windows = webDB.driver.getWindowHandles();
////		it = windows.iterator();
////		parentWindow = it.next();
////		url = webDB.driver.getCurrentUrl();
////		System.out.println("Parent window url is " + url);
////		childWindow = it.next();
////		url = webDB.driver.getCurrentUrl();
////		System.out.println("Child window url is " + url);
////		Set<String> allWindowHandles = driver.getWindowHandles();
////		System.out.println("size is " + allWindowHandles.size());
////		it = windows.iterator();
////		while(it.hasNext())
////		{
////			String next_window=it.next();
////			System.out.println("Url is " + driver.switchTo().window(next_window).getTitle());
////		}
//
//		flag = webDB.waitForElement(WixStoreLocators.CARD, ElementType.Xpath);
//		webDB.clickAnElement(WixStoreLocators.CARD, ElementType.Xpath);
//		webDB.waitForElement(WixStoreLocators.CARD_NUM, ElementType.CssSelector);
//		webDB.clickAnElement(WixStoreLocators.CARD_NUM, ElementType.CssSelector);
//		webDB.sendTextToAnElement(WixStoreLocators.CARD_NUM, WixStoreLocators.CARD_NUMBER, ElementType.CssSelector);
//		webDB.sendTextToAnElement(WixStoreLocators.CARD_EXPIRY, "11", ElementType.CssSelector);
//		// Thread.sleep(1000);
//		webDB.sendTextToAnElement(WixStoreLocators.CARD_EXPIRY, "25", ElementType.CssSelector);
//		webDB.sendTextToAnElement(WixStoreLocators.CARD_NAME, "Bogus", ElementType.CssSelector);
//		webDB.sendTextToAnElement(WixStoreLocators.CARD_CVV, "111", ElementType.CssSelector);
//		webDB.clickAnElement(WixStoreLocators.INR, ElementType.Xpath);
//		webDB.scrollToAnElement(WixStoreLocators.PAY_NOW, ElementType.Xpath);
//		webDB.clickAnElement(WixStoreLocators.PAY_NOW, ElementType.Xpath);
//		flag = webDB.waitForElement(WixStoreLocators.SAVE_CARD, ElementType.Xpath);
//		if (flag) {
//			webDB.clickAnElement(WixStoreLocators.SAVE_CARD, ElementType.Xpath);
//		}
//
//		Thread.sleep(3000);
//		
//		//webDB.switchNotTothisWindow(CommonFunctions.SuperlemonWindow);
////		Set<String> allWindowHandles = driver.getWindowHandles();
////		System.out.println("size is " + allWindowHandles.size());
////		it = windows.iterator();
////		while(it.hasNext())
////		{
////			String next_window=it.next();
////			System.out.println("Url is " + driver.switchTo().window(next_window).getTitle());
////		}
//		// webDB.driver.switchTo().window(childWindow);
////		webDB.driver.switchTo().window("https://api.razorpay.com/v1/gateway/mocksharp/payment?key_id=rzp_test_c3OZZzszch5OFl");
////		url = webDB.driver.getCurrentUrl();
////		System.out.println("Razor window url is " + url);
//
//		// webDB.driver.findElement(By.cssSelector("//form[@method='post']"));
//		// Robot robot = new Robot();
//		// robot.mouseMove(888,60);
//		// webDB.driver.switchTo().window("https://api.razorpay.com/v1/gateway/mocksharp/payment?key_id=rzp_test_c3OZZzszch5OFl");
//		// robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//		// Thread.sleep(2000);
//		// robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//		Thread.sleep(2000);
//		webDB.waitForElement(WixStoreLocators.RAZOR_SUCCESS, ElementType.Xpath);
//		webDB.clickAnElement(WixStoreLocators.RAZOR_SUCCESS, ElementType.Xpath);
//
//		//Thread.sleep(5000);
//		driver.switchTo().window(currentWindow);
//		driver.switchTo().defaultContent();
//		webDB.waitForElement(WixStoreLocators.THANK_YOU, ElementType.Xpath);
//
////		Thread.sleep(5000);
////		List<WebElement> iframeElements = webDB.driver.findElements(By.tagName("iframe"));
////		log.info("Total number of iframes are " + iframeElements.size());
////		webDB.driver.switchTo().frame(webDB.driver.findElement(By.xpath("(//iframe[@class='card-fields-iframe'])[1]")));
////		webDB.sendTextToAnElement(ShopifyStoreLocators.CARD_NUMBER_PAYMENTS, "1", ElementType.Xpath);
////		webDB.driver.switchTo().defaultContent();
////		webDB.driver.switchTo()
////				.frame(webDB.driver.findElement(By.xpath("(//iframe[contains(@class,'card-fields-iframe')])[2]")));
////		webDB.sendTextToAnElement(ShopifyStoreLocators.NAME_ON_CARD, "Bogus Gateway", ElementType.Xpath);
////		webDB.driver.switchTo().defaultContent();
////		webDB.driver.switchTo()
////				.frame(webDB.driver.findElement(By.xpath("(//iframe[contains(@class,'card-fields-iframe')])[3]")));
////		webDB.sendTextToAnElement(ShopifyStoreLocators.CARD_EXPIRY, "11", ElementType.Xpath);
////		Thread.sleep(1000);
////		webDB.sendTextToAnElement(ShopifyStoreLocators.CARD_EXPIRY, "24", ElementType.Xpath);
////		webDB.driver.switchTo().defaultContent();
////		webDB.driver.switchTo()
////				.frame(webDB.driver.findElement(By.xpath("(//iframe[contains(@class,'card-fields-iframe')])[4]")));
////		webDB.sendTextToAnElement(ShopifyStoreLocators.CARD_CVV, "111", ElementType.Xpath);
////		webDB.driver.switchTo().defaultContent();
//		return flag;
//	}

	public static boolean verifyCartpageCheckbox() throws Exception {
		SettingsPage.enableOptinStoreFront();
//		flag = SettingsPage.checkOptinCheckBoxesEnabled();
		flag = SettingsPage.selectAllOptinOptions();
		if (flag) {
			webDB.clickAnElement(WidgetLocators.CHATWIDGET_SETTING, ElementType.Xpath);
			WidgetPage.personalWidgetPageToStoreNavigation();
			log.info("personalWidgetPageToStoreNavigation method executed");
			JavascriptExecutor js = (JavascriptExecutor) webDB.driver;
			js.executeScript("window.localStorage.clear();");
			webDB.driver.navigate().refresh();
			// modified - changed below
			flag = verifyOptinPopUpDisplayed();
			if (flag) {
				log.info("Verified optin popup by selecting cart page checkbox");
			}
		}
		return flag;
	}

	public static boolean addItemAndAddtocart() throws InterruptedException {
		Thread.sleep(2500);
		webDB.moveToElement(WixStoreLocators.PRODUCT, ElementType.Xpath);
		flag = webDB.waitForElement(WixStoreLocators.PRODUCT, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WixStoreLocators.PRODUCT, ElementType.Xpath);
			Thread.sleep(2000);
			webDB.moveToElement(WixStoreLocators.ADD_TO_CART, ElementType.Xpath);
			flag = webDB.waitForElement(WixStoreLocators.ADD_TO_CART, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WixStoreLocators.ADD_TO_CART, ElementType.Xpath);
				log.info("Clicked Add to cart");
				Thread.sleep(5000);
//				// modified - getting into iframe
//				List<WebElement> iframeElements = webDB.driver.findElements(By.tagName("iframe"));
//				log.info("Total number of iframes are " + iframeElements.size());
//				webDB.driver.switchTo().frame(webDB.driver.findElement(By.xpath("(//iframe[@class='U73P_q'])")));
//				Thread.sleep(2000);
//				log.info("Getting into iframe");
//				// modified - added new locator
//				webDB.waitForElement(WixStoreLocators.VIEW_CART, ElementType.Xpath);
//				webDB.clickAnElement(WixStoreLocators.VIEW_CART, ElementType.Xpath);
//				log.info("view cart is clicked");
//				webDB.driver.switchTo().defaultContent();
				flag = webDB.isElementDisplayed(ShopifyStoreLocators.CONFIRM_BUTTON_OPTIN_POPUP, ElementType.Id);
				if (flag) {
					webDB.waitForElement(ShopifyStoreLocators.OPTIN_POPUP_COUNTRY_CODE_FIELD, ElementType.Xpath);
					flag = webDB.isElementDisplayed(ShopifyStoreLocators.OPTIN_POPUP_COUNTRY_CODE_FIELD,
							ElementType.Xpath);
					if (flag) {
						flag = webDB.isElementDisplayed(ShopifyStoreLocators.OPTIN_POPUP_PHONE_FIELD,
								ElementType.Xpath);
						if (flag) {
							log.info("Optin Pop up displayed on Store");
							webDB.clickAnElement(WixStoreLocators.OPTIN_POPUP_CLOSE_ICON, ElementType.Xpath);
							// changing constatnly
							webDB.driver.switchTo()
									.frame(webDB.driver.findElement(By.xpath("(//iframe[@class='U73P_q'])")));
							webDB.waitForElement(WixStoreLocators.VIEW_CART, ElementType.Xpath);
							webDB.clickAnElement(WixStoreLocators.VIEW_CART, ElementType.Xpath);
							webDB.driver.switchTo().defaultContent();
							//
							webDB.waitForElement(WixStoreLocators.CHECKOUT, ElementType.Xpath);
							flag = webDB.isElementDisplayed(WixStoreLocators.CHECKOUT, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(WixStoreLocators.CHECKOUT, ElementType.Xpath);
								flag = webDB.isElementDisplayed(ShopifyStoreLocators.CONFIRM_BUTTON_OPTIN_POPUP,
										ElementType.Id);
								if (flag) {
									webDB.waitForElement(ShopifyStoreLocators.OPTIN_POPUP_COUNTRY_CODE_FIELD,
											ElementType.Xpath);
									flag = webDB.isElementDisplayed(ShopifyStoreLocators.OPTIN_POPUP_COUNTRY_CODE_FIELD,
											ElementType.Xpath);
									if (flag) {
										flag = webDB.isElementDisplayed(ShopifyStoreLocators.OPTIN_POPUP_PHONE_FIELD,
												ElementType.Xpath);
										if (flag) {
											log.info("Optin Pop up displayed on Store");
											webDB.clickAnElement(WixStoreLocators.OPTIN_POPUP_CLOSE_ICON,
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

	public static boolean verifyCheckoutCheckbox() throws Exception {
		SettingsPage.enableOptinStoreFront();
		parentWindow = webDB.driver.getWindowHandle();
		flag = SettingsPage.checkOptinCheckBoxesEnabled();
		if (flag) {
			webDB.clickAnElement(WidgetLocators.CHATWIDGET_SETTING, ElementType.Xpath);
			WidgetPage.personalWidgetPageToStoreNavigation();
			log.info("personalWidgetPageToStoreNavigation method executed");
			ShopifyStore.shopifyStorePassEnter();
			log.info("shopifyStorePassEnter method executed");
			CommonFunctions.clearLocalStorage();
			webDB.driver.navigate().refresh();
			// modified - changed below
			flag = addItemAndAddtocart();
			if (flag) {
				log.info("Verified optin popup by selecting Checkout checkbox");
			}
			webDB.driver.switchTo().window(parentWindow);
		}
		return flag;
	}

	public static boolean verifyAbcartRecovered() throws Exception {
		String phone = "919" + RandomStringUtils.randomNumeric(9);
		System.out.println("phone = " + phone);
		parentWindow = webDB.driver.getWindowHandle();
		WidgetPage.personalWidgetPageToStoreNavigation();
		flag = webDB.waitForElement(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET, ElementType.Xpath);
		windows = webDB.driver.getWindowHandles();
		if (flag) {
			webDB.moveToElement(WixStoreLocators.PRODUCT, ElementType.Xpath);
			flag = webDB.waitForElement(WixStoreLocators.PRODUCT, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WixStoreLocators.PRODUCT, ElementType.Xpath);
				flag = webDB.waitForElement(WixStoreLocators.BUY_NOW, ElementType.Xpath);
				if (flag) {
					Thread.sleep(2000);
					webDB.clickAnElement(WixStoreLocators.BUY_NOW, ElementType.Xpath);
					Thread.sleep(3000);
					webDB.driver.navigate().refresh();
					flag = webDB.waitForElement(WixStoreLocators.CHECKOUT_PAGE, ElementType.Xpath);
					if (flag) {
						Thread.sleep(2000);
						flag = webDB.waitForElement(WixStoreLocators.EMAIL, ElementType.Xpath);
						if (flag) {
							System.out.println("In check out page");
							Thread.sleep(3000);
							webDB.sendTextToAnElement(WixStoreLocators.EMAIL, WixStoreLocators.EMAIL_ID,
									ElementType.Xpath);
							webDB.waitForElement(WixStoreLocators.FIRST_NAME, ElementType.Xpath);
							Thread.sleep(1000);
							webDB.sendTextToAnElement(WixStoreLocators.FIRST_NAME, firstName, ElementType.Xpath);
							webDB.waitForElement(WixStoreLocators.LAST_NAME, ElementType.Xpath);
							Thread.sleep(1000);
							webDB.sendTextToAnElement(WixStoreLocators.LAST_NAME, lastName, ElementType.Xpath);
							webDB.waitForElement(WixStoreLocators.PHONE, ElementType.Xpath);
							Thread.sleep(1000);
							webDB.sendTextToAnElement(WixStoreLocators.PHONE, phone, ElementType.Xpath);
							webDB.waitForElement(WixStoreLocators.ADDRESS, ElementType.Xpath);
							Thread.sleep(1000);
							webDB.sendTextToAnElement(WixStoreLocators.ADDRESS, address, ElementType.Xpath);
							webDB.waitForElement(WixStoreLocators.CITY, ElementType.Xpath);
							Thread.sleep(1000);
							webDB.sendTextToAnElement(WixStoreLocators.CITY, city, ElementType.Xpath);
							webDB.moveToElement(WixStoreLocators.CONTINUE, ElementType.Xpath);
							Thread.sleep(5000);
							webDB.clickAnElement(WixStoreLocators.CONTINUE, ElementType.Xpath);
							webDB.moveToElement(WixStoreLocators.DELIVERY_CONTINUE, ElementType.Xpath);
							wait.until(ExpectedConditions
									.elementToBeClickable(By.xpath(WixStoreLocators.DELIVERY_CONTINUE)));
							webDB.clickAnElement(WixStoreLocators.DELIVERY_CONTINUE, ElementType.Xpath);
							if (flag) {
								Thread.sleep(1000);
								webDB.driver.switchTo().window(parentWindow);
								log.info("switched to parent window");

								flag = CommonFunctions.verifyOptinNumbersPage();
								if (flag) {
									webDB.driver.navigate().refresh();
									flag = webDB.waitForElement(CustomersLocators.FIRST_NUM, ElementType.Xpath);
									if (flag) {
										String ph = webDB.getText(CustomersLocators.FIRST_NUM, ElementType.Xpath);
										System.out.println("Opted in phone number is " + ph);
										if (phone.equals(ph)) {
											System.out.println("Phone num is same");
											flag = true;
											iterator = windows.iterator();
											while (iterator.hasNext()) {
												childWindow = iterator.next();
												if (!parentWindow.equals(childWindow)) {
													webDB.driver.switchTo().window(childWindow);
												}
											}
											Thread.sleep(1000);
											flag = DriverBase.verifyResponseAPI("abcart");
											// Thread.sleep(60000);

											if (flag) {
												webDB.driver.switchTo().window(parentWindow);
												log.info("switched to parent window");

												CommonFunctions.verifyOptinNumbersPage();
												// webDB.driver.navigate().refresh();
												// Thread.sleep(1000);
												for (int i = 2000; i < 120000; i = i + 2000) {
													Thread.sleep(i);
													webDB.driver.navigate().refresh();
													String abcart_value = webDB.getText("(//tbody//th[contains(text(),'"
															+ phone + "')])/following::td[4]", ElementType.Xpath);

													if (abcart_value.equals("135.00")) {
														flag = true;
														System.out.println("Abcart value is " + abcart_value);
														System.out.println("Order is abandoned");
														iterator = windows.iterator();
														while (iterator.hasNext()) {
															childWindow = iterator.next();
															if (!parentWindow.equals(childWindow)) {
																webDB.driver.switchTo().window(childWindow);
															}
														}
														ShopifyStore.enterCardDetails();
														webDB.clickAnElement(ShopifyStoreLocators.CONTINUE_BTN,
																ElementType.Xpath);
														wait.until(ExpectedConditions.elementToBeClickable(By
																.xpath(ShopifyStoreLocators.ORDER_CONFIRMED_MESSAGE)));
														webDB.driver.navigate().refresh();

														webDB.driver.switchTo().window(parentWindow);
														log.info("switched to parent window");
														Thread.sleep(1000);
														flag = CommonFunctions.verifyOptinNumbersPage();
														if (flag) {
															String num_of_order = webDB.getText(
																	"(//tbody//th[contains(text(),'" + phone
																			+ "')])/following::td[2]",
																	ElementType.Xpath);
															// System.out.println("Number of order is " + num_of_order);
															if (num_of_order.equals("1")) {
																flag = true;
																System.out.println(
																		"Recovered order verified in customer section");
															} else {
																flag = false;
																System.out.println(
																		"Recovered order is not updated in customer section");
															}
														}
														break;
													} else {
														flag = false;
														System.out.println("Order didn't get abandoned");
													}
												}
											}
										} else {
											flag = false;
											System.out.println("Phone num is not the same");
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

	public static String addItemAndCheckoutReturningName() throws InterruptedException {
		webDB.moveToElement(WixStoreLocators.PRODUCT, ElementType.Xpath);
		flag = webDB.waitForElement(WixStoreLocators.PRODUCT, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WixStoreLocators.PRODUCT, ElementType.Xpath);
			flag = webDB.waitForElement(WixStoreLocators.BUY_NOW, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				webDB.clickAnElement(WixStoreLocators.BUY_NOW, ElementType.Xpath);
				Thread.sleep(3000);
				webDB.driver.navigate().refresh();
				flag = webDB.waitForElement(WixStoreLocators.CHECKOUT_PAGE, ElementType.Xpath);
				if (flag) {
					Thread.sleep(2000);
					flag = webDB.waitForElement(WixStoreLocators.EMAIL, ElementType.Xpath);
					if (flag) {
						System.out.println("In check out page");
						Thread.sleep(3000);
						webDB.sendTextToAnElement(WixStoreLocators.EMAIL, WixStoreLocators.EMAIL_ID, ElementType.Xpath);
						webDB.waitForElement(WixStoreLocators.FIRST_NAME, ElementType.Xpath);
						Thread.sleep(1000);
						webDB.sendTextToAnElement(WixStoreLocators.FIRST_NAME, firstName, ElementType.Xpath);
						webDB.waitForElement(WixStoreLocators.LAST_NAME, ElementType.Xpath);
						Thread.sleep(1000);
						webDB.sendTextToAnElement(WixStoreLocators.LAST_NAME, lastName, ElementType.Xpath);
						webDB.waitForElement(WixStoreLocators.PHONE, ElementType.Xpath);
						Thread.sleep(1000);
						webDB.sendTextToAnElement(WixStoreLocators.PHONE, phone, ElementType.Xpath);
						webDB.waitForElement(WixStoreLocators.ADDRESS, ElementType.Xpath);
						Thread.sleep(1000);
						webDB.sendTextToAnElement(WixStoreLocators.ADDRESS, address, ElementType.Xpath);
						webDB.waitForElement(WixStoreLocators.CITY, ElementType.Xpath);
						Thread.sleep(1000);
						webDB.sendTextToAnElement(WixStoreLocators.CITY, city, ElementType.Xpath);
						webDB.moveToElement(WixStoreLocators.CONTINUE, ElementType.Xpath);
						Thread.sleep(5000);
						webDB.clickAnElement(WixStoreLocators.CONTINUE, ElementType.Xpath);
						webDB.moveToElement(WixStoreLocators.DELIVERY_CONTINUE, ElementType.Xpath);
						wait.until(
								ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.DELIVERY_CONTINUE)));
						webDB.clickAnElement(WixStoreLocators.DELIVERY_CONTINUE, ElementType.Xpath);
						Thread.sleep(1000);
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.COD)));
						webDB.clickAnElement(WixStoreLocators.COD, ElementType.Xpath);
						webDB.scrollToAnElement(WixStoreLocators.PAYMENT_CONTINUE, ElementType.Xpath);
						wait.until(
								ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.PAYMENT_CONTINUE)));
						webDB.clickAnElement(WixStoreLocators.PAYMENT_CONTINUE, ElementType.Xpath);
						webDB.scrollToAnElement(WixStoreLocators.PLACE_ORDER, ElementType.Xpath);
						webDB.waitForElement(WixStoreLocators.PLACE_ORDER, ElementType.Xpath);
						Thread.sleep(1000);
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.PLACE_ORDER)));
						webDB.scrollToAnElement(WixStoreLocators.PLACE_ORDER, ElementType.Xpath);
						webDB.clickAnElement(WixStoreLocators.PLACE_ORDER, ElementType.Xpath);
						Thread.sleep(1000);
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.THANK_YOU)));
						flag = webDB.isElementDisplayed(WixStoreLocators.THANK_YOU, ElementType.Xpath);

						// added below
						fullName = firstName + " " + lastName;
					}
				}
			}
		}
		return fullName;
	}

	public static boolean checkOptinDetailsInOptinList() throws Exception {
		SimpleDateFormat simpleformat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");
		simpleformat = new SimpleDateFormat("MMMM d");
		String datevalue = simpleformat.format(new Date());
		parentWindow = webDB.driver.getWindowHandle();
		SettingsPage.selectAllOptinOptions();
		webDB.waitForElement(WidgetLocators.CHAT_WIDGET_TAB, ElementType.Xpath);
		webDB.clickAnElement(WidgetLocators.CHAT_WIDGET_TAB, ElementType.Xpath);
		Thread.sleep(2000);
		WidgetPage.personalWidgetPageToStoreNavigation();
		webDB.moveToElement(WixStoreLocators.PRODUCT, ElementType.Xpath);
		flag = webDB.waitForElement(WixStoreLocators.PRODUCT, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WixStoreLocators.PRODUCT, ElementType.Xpath);
			flag = webDB.waitForElement(WixStoreLocators.BUY_NOW, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				webDB.clickAnElement(WixStoreLocators.BUY_NOW, ElementType.Xpath);
				Thread.sleep(3000);
				flag = webDB.waitForElement(WixStoreLocators.PHONE_NUMBER_FIELD, ElementType.Xpath);
				if (flag) {
					webDB.sendTextToAnElement(WixStoreLocators.PHONE_NUMBER_FIELD, phone, ElementType.Xpath);
					flag = webDB.waitForElement(WixStoreLocators.CONFIRM_BTN, ElementType.Xpath);
					if (flag) {
						webDB.javaScriptClickAnElement(WixStoreLocators.CONFIRM_BTN, ElementType.Xpath);
						Thread.sleep(2000);
						webDB.driver.navigate().refresh();
						flag = webDB.waitForElement(WixStoreLocators.CHECKOUT_PAGE, ElementType.Xpath);
						if (flag) {
							Thread.sleep(2000);
							flag = webDB.waitForElement(WixStoreLocators.EMAIL, ElementType.Xpath);
							if (flag) {
								System.out.println("In check out page");
								Thread.sleep(3000);
								webDB.sendTextToAnElement(WixStoreLocators.EMAIL, WixStoreLocators.EMAIL_ID,
										ElementType.Xpath);
								webDB.waitForElement(WixStoreLocators.FIRST_NAME, ElementType.Xpath);
								Thread.sleep(1000);
								webDB.sendTextToAnElement(WixStoreLocators.FIRST_NAME, firstName, ElementType.Xpath);
								webDB.waitForElement(WixStoreLocators.LAST_NAME, ElementType.Xpath);
								Thread.sleep(1000);
								webDB.sendTextToAnElement(WixStoreLocators.LAST_NAME, lastName, ElementType.Xpath);
								webDB.waitForElement(WixStoreLocators.PHONE, ElementType.Xpath);
								// modified - called full name
								fullName = firstName + " " + lastName;
								Thread.sleep(1000);
								webDB.sendTextToAnElement(WixStoreLocators.PHONE,"91"+ phone, ElementType.Xpath);
								webDB.waitForElement(WixStoreLocators.ADDRESS, ElementType.Xpath);
								Thread.sleep(1000);
								webDB.sendTextToAnElement(WixStoreLocators.ADDRESS, address, ElementType.Xpath);
								webDB.waitForElement(WixStoreLocators.CITY, ElementType.Xpath);
								Thread.sleep(1000);
								webDB.sendTextToAnElement(WixStoreLocators.CITY, city, ElementType.Xpath);
								Thread.sleep(5000);
								webDB.clickAnElement(WixStoreLocators.CONTINUE, ElementType.Xpath);
								webDB.moveToElement(WixStoreLocators.DELIVERY_CONTINUE, ElementType.Xpath);
								wait.until(ExpectedConditions
										.elementToBeClickable(By.xpath(WixStoreLocators.DELIVERY_CONTINUE)));
								webDB.clickAnElement(WixStoreLocators.DELIVERY_CONTINUE, ElementType.Xpath);
								Thread.sleep(1000);
								wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.COD)));
								webDB.clickAnElement(WixStoreLocators.COD, ElementType.Xpath);
								webDB.scrollToAnElement(WixStoreLocators.PAYMENT_CONTINUE, ElementType.Xpath);
								wait.until(ExpectedConditions
										.elementToBeClickable(By.xpath(WixStoreLocators.PAYMENT_CONTINUE)));
								webDB.clickAnElement(WixStoreLocators.PAYMENT_CONTINUE, ElementType.Xpath);
								webDB.scrollToAnElement(WixStoreLocators.PLACE_ORDER, ElementType.Xpath);
								webDB.waitForElement(WixStoreLocators.PLACE_ORDER, ElementType.Xpath);
								Thread.sleep(1000);
								wait.until(ExpectedConditions
										.elementToBeClickable(By.xpath(WixStoreLocators.PLACE_ORDER)));
								webDB.scrollToAnElement(WixStoreLocators.PLACE_ORDER, ElementType.Xpath);
								webDB.clickAnElement(WixStoreLocators.PLACE_ORDER, ElementType.Xpath);
								Thread.sleep(1000);
								wait.until(
										ExpectedConditions.elementToBeClickable(By.xpath(WixStoreLocators.THANK_YOU)));
								flag = webDB.isElementDisplayed(WixStoreLocators.THANK_YOU, ElementType.Xpath);
								if (flag) {
									webDB.driver.close();
									log.info("closed the child window");
									webDB.driver.switchTo().window(parentWindow);
									log.info("switched to parent window");
									CommonFunctions.verifyOptinNumbersPage();
									webDB.driver.navigate().refresh();
									Thread.sleep(1000);
									wait.until(ExpectedConditions.visibilityOf(
											webDB.driver.findElement(By.xpath(CustomersLocators.EXPORT_OPTIN_LIST))));
									List<WebElement> optinstatus = webDB.driver
											.findElements(By.xpath(CustomersLocators.OPTEDIN_COLUMN));
									elements = webDB.driver
											.findElements(By.xpath(CustomersLocators.PHONE_COLUMBER_COLUMN));
									List<WebElement> source = webDB.driver
											.findElements(By.xpath(CustomersLocators.NAME_COLUMN));
									List<WebElement> totalordervalue = webDB.driver
											.findElements(By.xpath(CustomersLocators.TOTAL_ORDER_VALUE_COLUMN));
									List<WebElement> dateordervalue = webDB.driver
											.findElements(By.xpath(CustomersLocators.ORDER_DATE_COLUMN));
									List<WebElement> numberoforder = webDB.driver
											.findElements(By.xpath(CustomersLocators.NO_OF_ORDER_COLUMN));
									for (int i = 0; i < source.size(); i++) {
										String element = source.get(i).getText();
										flag = element.contains(fullName);
										if (flag) {
											System.out.println("Name is verified");
											break;
										}
										if (!flag) {
											Listener.reportLog("Name is not verified");

//								String phonevalue = elements.get(i).getText();
//								flag = phonevalue.contains(phone);
//								if (flag) {
//									System.out.println("Phone verified");
//									break;
//								}
//							}
//							if (!flag) {
//								Listener.reportLog("Phone No is not verified");
//							}
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
}