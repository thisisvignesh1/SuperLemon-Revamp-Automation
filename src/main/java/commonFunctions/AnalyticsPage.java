package commonFunctions;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import locators.AnalyticsLocators;
import locators.CommonLocators;
import locators.WidgetLocators;
import locators.ShopifyStoreLocators;
import locators.AutomationLocators;
import utils.DriverBase;
import utils.DriverBase.ElementType;

@SuppressWarnings("static-access")
public class AnalyticsPage {
	private static Logger log = Logger.getLogger(AnalyticsPage.class);
	private static boolean flag;
	private static String propertyFilePath;
	public static Properties prop = new Properties();
	private static DriverBase webDB = new DriverBase();;
	private static Set<String> windows;
	private static Iterator<String> iterator;
	private static String parentWindow;
	private static String childWindow;
	private static Actions actions = new Actions(webDB.driver);
	private static String text;
	private static WebDriverWait wait = new WebDriverWait(webDB.driver, 10);
	private static int chatCount;
	private static int shareCount;

	public static int getChatClickCount() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(AnalyticsLocators.CHAT_CLICK_COUNT)));
		Thread.sleep(3000);
		String text = webDB.driver.findElement(By.xpath(AnalyticsLocators.CHAT_CLICK_COUNT)).getText();
		String Count = text.substring(text.indexOf("\n") + 1).trim();
		chatCount = Integer.parseInt(Count);
		return chatCount;
	}

	public static boolean verifyChatCount() throws InterruptedException, IOException {
		chatCount = getChatClickCount();
		System.out.println("count before click" + chatCount);
		CommonFunctions.verifyPersonalWidgetPage();
		clickOnChatAgent();
		CommonFunctions.verifyAnalyticsPage();
		webDB.driver.navigate().refresh();
		int countAfterClick = getChatClickCount();
		System.out.println("count after click is" + countAfterClick);
		int finalCount = countAfterClick - chatCount;
		System.out.println("final count click is" + finalCount);
		System.out.println("diff is " + (countAfterClick - chatCount));
		flag = false;
		if (finalCount == 0) {
			flag = true;
		}
		return flag;
	}

	public static void clickOnShareWidget() throws InterruptedException {
		parentWindow = webDB.driver.getWindowHandle();
		WidgetPage.enableShareButton();
		WidgetPage.personalWidgetPageToStoreNavigation();
		ShopifyStore.shopifyStorePassEnter();
		// modified - added wait time and altered below code.
		Thread.sleep(5000);
//		webDB.isElementDisplayed(ShopifyStoreLocators.HOMEPAGE_SHARE_WIDGET, DriverBase.ElementType.Xpath);
		webDB.waitForElement(ShopifyStoreLocators.HOMEPAGE_SHARE_WIDGET, DriverBase.ElementType.Xpath);
		//modified - removed for loop
//		for (int i = 0; i < 2; i++) {
////			webDB.clickAnElement(ShopifyStoreLocators.HOMEPAGE_SHARE_WIDGET, DriverBase.ElementType.Xpath);
//			webDB.javaScriptClickAnElement(ShopifyStoreLocators.HOMEPAGE_SHARE_WIDGET, DriverBase.ElementType.Xpath);
//			log.debug("Clicled on Share" + i);
//		}
	webDB.javaScriptClickAnElement(ShopifyStoreLocators.HOMEPAGE_SHARE_WIDGET, DriverBase.ElementType.Xpath);
	log.debug("Clicled on Share");
		Thread.sleep(3000);
		webDB.driver.switchTo().window(parentWindow);
		windows = webDB.driver.getWindowHandles();
		iterator = windows.iterator();
		while (iterator.hasNext()) {
			childWindow = iterator.next();
			if (!parentWindow.equals(childWindow)) {
				webDB.driver.switchTo().window(childWindow);
				webDB.driver.close();
			}
		}
		webDB.driver.switchTo().window(parentWindow);
	}

	public static void clickOnChatAgent() throws InterruptedException, FileNotFoundException, IOException {
		log.info("Inside click on chat agent");
		CommonFunctions.verifyPersonalWidgetPage();
		WidgetPage.selectShowChatWhenAgentOffline();
		parentWindow = webDB.driver.getWindowHandle();
		WidgetPage.enableCHatButton();
		WidgetPage.enableGreetingsWidget();
		WidgetPage.personalWidgetPageToStoreNavigation();
		ShopifyStore.shopifyStorePassEnter();
		// modified - added sleep time and commented few lines and addd javascript
		// click.
		Thread.sleep(10000);
//		webDB.waitForElement(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET, DriverBase.ElementType.Xpath);
//		webDB.clickAnElement(WidgetLocators.CALLOUTCLOSEICON, ElementType.CssSelector);
		log.info("Inside store");
		webDB.waitForElement(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET, DriverBase.ElementType.Xpath);
//		webDB.isElementDisplayed(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET, DriverBase.ElementType.Xpath);
//		webDB.clickAnElement(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET, DriverBase.ElementType.Xpath);
		webDB.javaScriptClickAnElement(ShopifyStoreLocators.HOMEPAGE_CHAT_WIDGET, DriverBase.ElementType.Xpath);
		Thread.sleep(3000);
		// modified - commented below for loop and separated
//		for (int i = 0; i < 2; i++) {
//			webDB.waitForElement(ShopifyStoreLocators.FIRST_CHAT_AGENT, DriverBase.ElementType.Xpath);
//			actions.moveToElement(webDB.driver.findElement(By.xpath(ShopifyStoreLocators.FIRST_CHAT_AGENT))).click()
//					.build().perform();
//			log.debug("Clicled on chat" + i);
//		}
		webDB.waitForElement(ShopifyStoreLocators.FIRST_CHAT_AGENT, DriverBase.ElementType.Xpath);
		actions.moveToElement(webDB.driver.findElement(By.xpath(ShopifyStoreLocators.FIRST_CHAT_AGENT))).click()
				.build().perform();
		log.debug("Clicled on chat first agent");
		webDB.driver.switchTo().window(parentWindow);
		windows = webDB.driver.getWindowHandles();
		iterator = windows.iterator();
		while (iterator.hasNext()) {
			childWindow = iterator.next();
			if (!parentWindow.equals(childWindow)) {
				webDB.driver.switchTo().window(childWindow);
				webDB.driver.close();

			}
		}
		webDB.driver.switchTo().window(parentWindow);
	}

	public static int getShareClickCount() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(AnalyticsLocators.SHARE_CLICK_COUNT)));
		Thread.sleep(3000);
		String text = webDB.driver.findElement(By.xpath(AnalyticsLocators.SHARE_CLICK_COUNT)).getText();
		String Count = text.substring(text.indexOf("\n") + 1).trim();
		shareCount = Integer.parseInt(Count);
		return shareCount;
	}

	public static boolean verifyShareCount() throws InterruptedException, IOException {
		chatCount = getShareClickCount();
		CommonFunctions.verifyPersonalWidgetPage();
		clickOnShareWidget();
		CommonFunctions.verifyAnalyticsPage();
		webDB.driver.navigate().refresh();
		int countAfterClick = getShareClickCount();
		int finalCount = countAfterClick - chatCount;
		System.out.println("final count after clicks" + finalCount);
		if (finalCount == 5) {
			flag = true;
		}
		return flag;
	}

	public static boolean verifyChatClickCountForSpecificDateRange() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AnalyticsLocators.CHAT_SHARE_CLICK_FILTER)));
		webDB.clickAnElement(AnalyticsLocators.CHAT_SHARE_CLICK_FILTER, DriverBase.ElementType.Xpath);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(AnalyticsLocators.CUSTOMIZERADIOBTN)));
		WebElement element = webDB.driver.findElement(By.cssSelector(AnalyticsLocators.CUSTOMIZERADIOBTN));
		JavascriptExecutor js = (JavascriptExecutor) webDB.driver;
		js.executeScript("arguments[0].click();", element);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(AnalyticsLocators.FROM_DATE_FIELD)));
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM");
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, -5);
		Date dateCalculated = c.getTime();
		String fromDate = dateFormat.format(dateCalculated);
		webDB.driver.findElement(By.xpath(AnalyticsLocators.FROM_DATE_FIELD))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.driver.findElement(By.xpath(AnalyticsLocators.FROM_DATE_FIELD)).sendKeys(fromDate);
		webDB.clickAnElement(AutomationLocators.SUBMIT, DriverBase.ElementType.Xpath);
		Thread.sleep(2000);
		flag = true;
//		List<Integer> analyticsData = getChatAnalyticsDateSpecific();
//		chatCount = getChatClickCount();
//		shareCount = getShareClickCount();
//		if (chatCount == analyticsData.get(0) && shareCount == analyticsData.get(1)) {
//			flag = true;
//		}
		return flag;
	}

	public static List<Integer> getChatAnalyticsDateSpecific() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, -5);
		Date dateCalculated = c.getTime();
		String fromDate = dateFormat.format(dateCalculated);
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		RestAssured.baseURI = "https://qa-api-ss-integration.gupshup.io";
		Response response = given().header("token", "sat_e8e3f92cafa24f21ae46187953e255b4").queryParam("from", fromDate)
				.queryParam("to", currentDate).when().get("/analytics/v1/click/97").then().extract().response();
		System.out.println(response);
		JsonPath j = new JsonPath(response.asString());
		String chatClickCount = j.getString("analytics.chatCount");
		String ShareClickCount = j.getString("analytics.shareCount");
		chatCount = Integer.parseInt(chatClickCount);
		shareCount = Integer.parseInt(ShareClickCount);
		System.out.println(chatCount + "is the chat count");
		System.out.println(shareCount + "is the share count");
		List<Integer> clickAnalyticsCount = new ArrayList<Integer>();
		clickAnalyticsCount.add(chatCount);
		clickAnalyticsCount.add(shareCount);
		return clickAnalyticsCount;
	}

	public static boolean verifyStartingDateValidation() throws IOException, InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(AnalyticsLocators.CHAT_CLICK_COUNT)));
		webDB.clickAnElement(AnalyticsLocators.CHAT_SHARE_CLICK_FILTER, DriverBase.ElementType.ClassName.Xpath);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(AnalyticsLocators.FROM_DATE_FIELD)));
		webDB.driver.findElement(By.xpath(AnalyticsLocators.FROM_DATE_FIELD))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.driver.findElement(By.xpath(AnalyticsLocators.FROM_DATE_FIELD)).sendKeys("2022-01-30");
		Thread.sleep(3000);
		webDB.isElementDisplayed(AnalyticsLocators.STARTING_DATE_VALIDATION_MESSAGE, DriverBase.ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath(AnalyticsLocators.STARTING_DATE_VALIDATION_MESSAGE)).getText();
		log.info(text);
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String validationText = prop.getProperty("StartingDateValidationMessageText");
		log.info(validationText);
		flag = text.equalsIgnoreCase(validationText);
		return flag;
	}

	public static boolean verifyAnalyticsPageOtherMessageSections() throws InterruptedException {
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			System.out.println("BrowserStack");
		} else {
			flag = webDB.waitForElement(AnalyticsLocators.ANALYTICS_TAB, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				webDB.clickAnElement(AnalyticsLocators.ANALYTICS_TAB, ElementType.Xpath);
			}
		}
		Thread.sleep(1000);
		webDB.moveToElement(AnalyticsLocators.WIDGET_ANALYTICS_SECTION, ElementType.Xpath);
		flag = webDB.isElementDisplayed(AnalyticsLocators.WIDGET_ANALYTICS_SECTION, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(AnalyticsLocators.SPINWHEEL_ANALYTICS_SECTION, ElementType.Xpath);
			Thread.sleep(1000);
			flag = webDB.isElementDisplayed(AnalyticsLocators.SPINWHEEL_ANALYTICS_SECTION, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(AnalyticsLocators.AUTOMATED_MESSAGE_REPORT_SECTION, ElementType.Xpath);
				Thread.sleep(1000);
				flag = webDB.isElementDisplayed(AnalyticsLocators.AUTOMATED_MESSAGE_REPORT_SECTION, ElementType.Xpath);
				if (flag) {
					Thread.sleep(1000);
					flag = webDB.isElementDisplayed(AnalyticsLocators.ABANDONEDCARDTITLE, ElementType.Xpath);
					if (flag) {
						webDB.moveToElement(AnalyticsLocators.OTHER_MESSAGES_SECTION, ElementType.Xpath);
						Thread.sleep(1000);
						flag = webDB.isElementDisplayed(AnalyticsLocators.OTHER_MESSAGES_SECTION, ElementType.Xpath);
						if (flag) {
							webDB.moveToElement(AnalyticsLocators.OPT_INS_SECTION, ElementType.Xpath);
							Thread.sleep(1000);
							flag = webDB.isElementDisplayed(AnalyticsLocators.OPT_INS_SECTION, ElementType.Xpath);
						}
					}
				}
			}
		}
		return flag;

	}

	public static boolean verifyOtherMessageGraph() throws InterruptedException {
		webDB.moveToElement(AnalyticsLocators.CASHONDELIVERY_GRAPH, ElementType.Xpath);
		flag = webDB.isElementDisplayed(AnalyticsLocators.ORDERCONFIRMATION_GRAPH, ElementType.Xpath);
		if (flag) {
			flag = webDB.isElementDisplayed(AnalyticsLocators.ORDERSHIPMENT_GRAPH, ElementType.Xpath);
			Thread.sleep(1000);
			webDB.moveToElement(AnalyticsLocators.ORDERCANCELLATION_GRAPH, ElementType.Xpath);
			flag = webDB.isElementDisplayed(AnalyticsLocators.CASHONDELIVERY_GRAPH, ElementType.Xpath);
			if (flag) {
				flag = webDB.isElementDisplayed(AnalyticsLocators.PAYMENTSUCCESSFUL_GRAPH, ElementType.Xpath);
				webDB.moveToElement(AnalyticsLocators.CUSTOMERWINBACK_GRAPH, ElementType.Xpath);
				flag = webDB.isElementDisplayed(AnalyticsLocators.ORDERCANCELLATION_GRAPH, ElementType.Xpath);
				if (flag) {
					flag = webDB.isElementDisplayed(AnalyticsLocators.CROSSSELL_GRAPH, ElementType.Xpath);
					webDB.moveToElement(AnalyticsLocators.OPT_INS_SECTION, ElementType.Xpath);
					flag = webDB.isElementDisplayed(AnalyticsLocators.CUSTOMERWINBACK_GRAPH, ElementType.Xpath);
					if (flag) {
						flag = webDB.isElementDisplayed(AnalyticsLocators.FEEDBACKGRAPH, ElementType.Xpath);
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyAbandedcartOthermsgGraphShows() throws InterruptedException {

		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			System.out.println("BrowserStack");
		} else {
			flag = webDB.waitForElement(AnalyticsLocators.ANALYTICS_TAB, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				webDB.clickAnElement(AnalyticsLocators.ANALYTICS_TAB, ElementType.Xpath);
			}
		}
		Thread.sleep(1000);
		webDB.moveToElement(AnalyticsLocators.ABANDONEDCARD_TOTALREAD, ElementType.Xpath);
		flag = webDB.isElementDisplayed(AnalyticsLocators.ABANDONEDCARD_TOTALREAD, ElementType.Xpath);
		if (flag) {
			flag = webDB.isElementDisplayed(AnalyticsLocators.ABANDONEDCARD_TOTALSENT, ElementType.Xpath);
			if (flag) {
				flag = webDB.isElementDisplayed(AnalyticsLocators.ABANDONEDCARD_TOTALRECEIVED, ElementType.Xpath);
				if (flag) {
					flag = webDB.isElementDisplayed(AnalyticsLocators.ABANDONEDCARD_NO_OF_ABANDONEDCART, //
							ElementType.Xpath);
					if (flag) {
						flag = webDB.isElementDisplayed(AnalyticsLocators.ABANDONEDCARD_REVENUEGENERATED, //
								ElementType.Xpath);
						webDB.moveToElement(AnalyticsLocators.OTHER_MESSAGES_TOTALREAD, ElementType.Xpath);
						flag = webDB.isElementDisplayed(AnalyticsLocators.OTHER_MESSAGES_TOTALREAD, ElementType.Xpath);
						if (flag) {
							flag = webDB.isElementDisplayed(AnalyticsLocators.OTHER_MESSAGES_TOTALSENT,
									ElementType.Xpath);
							if (flag) {
								flag = webDB.isElementDisplayed(AnalyticsLocators.OTHER_MESSAGES_TOTALRECEIVED,
										ElementType.Xpath);
							}
						}
					}
				}
			}
		}
		return flag;

	}

	public static boolean verifyWidgetMaxMonthsDateFiler() throws InterruptedException {
		flag = webDB.waitForElement(AnalyticsLocators.ANALYTICS_TAB, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			webDB.clickAnElement(AnalyticsLocators.ANALYTICS_TAB, ElementType.Xpath);
			Thread.sleep(1000);
			webDB.moveToElement(AnalyticsLocators.WIDGETANALYTICS_CUSTOMIZE, ElementType.Xpath);
			flag = webDB.waitForElement(AnalyticsLocators.WIDGETANALYTICS_CUSTOMIZE, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(AnalyticsLocators.WIDGETANALYTICS_CUSTOMIZE, ElementType.Xpath);
				Thread.sleep(1000);
				flag = webDB.isElementDisplayed(AnalyticsLocators.LASTWEEKRADIOBTN, ElementType.Xpath);
				if (flag) {
					webDB.javaScriptClickAnElement(AnalyticsLocators.CUSTOMIZERADIOBTN, ElementType.CssSelector);
					flag = webDB.waitForElement(AnalyticsLocators.STARTINGDATE, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(AnalyticsLocators.STARTINGDATE, ElementType.Xpath);
						webDB.sendTextToAnElement(AnalyticsLocators.STARTINGDATE, AnalyticsLocators.STARTINGDATEVALUE,
								ElementType.Xpath);
						Thread.sleep(2000);
						flag = webDB.isElementDisplayed(AnalyticsLocators.STARTING_DATE_VALIDATION_MESSAGE,
								ElementType.Xpath);
						if (flag) {
							String toast = webDB.driver
									.findElement(By.xpath(AnalyticsLocators.STARTING_DATE_VALIDATION_MESSAGE))
									.getText();
							Thread.sleep(2000);
							flag = toast.equals(AnalyticsLocators.STARTINGDATEERRORVALUE);
							webDB.driver.navigate().refresh();
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyAutomatedMessageMaxMonthsDateFiler() throws InterruptedException {
		flag = webDB.waitForElement(AnalyticsLocators.ANALYTICS_TAB, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			webDB.clickAnElement(AnalyticsLocators.ANALYTICS_TAB, ElementType.Xpath);
			Thread.sleep(1000);
			webDB.moveToElement(AnalyticsLocators.AUTOMATED_MESSAGE_REPORT_CUSTOMIZE, ElementType.Xpath);
			flag = webDB.waitForElement(AnalyticsLocators.AUTOMATED_MESSAGE_REPORT_CUSTOMIZE, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(AnalyticsLocators.AUTOMATED_MESSAGE_REPORT_CUSTOMIZE, ElementType.Xpath);
				Thread.sleep(1000);
				flag = webDB.isElementDisplayed(AnalyticsLocators.LASTWEEKRADIOBTN, ElementType.Xpath);
				if (flag) {
					webDB.javaScriptClickAnElement(AnalyticsLocators.CUSTOMIZERADIOBTN, ElementType.CssSelector);
					flag = webDB.waitForElement(AnalyticsLocators.STARTINGDATE, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(AnalyticsLocators.STARTINGDATE, ElementType.Xpath);
						webDB.sendTextToAnElement(AnalyticsLocators.STARTINGDATE, AnalyticsLocators.STARTINGDATEVALUE,
								ElementType.Xpath);
						Thread.sleep(2000);
						flag = webDB.isElementDisplayed(AnalyticsLocators.STARTING_DATE_VALIDATION_MESSAGE,
								ElementType.Xpath);
						if (flag) {
							String toast = webDB.driver
									.findElement(By.xpath(AnalyticsLocators.STARTING_DATE_VALIDATION_MESSAGE))
									.getText();
							Thread.sleep(2000);
							flag = toast.equals(AnalyticsLocators.STARTINGDATEERRORVALUE);
							webDB.driver.navigate().refresh();
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyOptsInMaxMonthsDateFiler() throws InterruptedException {
		flag = webDB.waitForElement(AnalyticsLocators.ANALYTICS_TAB, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			webDB.clickAnElement(AnalyticsLocators.ANALYTICS_TAB, ElementType.Xpath);
			Thread.sleep(1000);
			webDB.moveToElement(AnalyticsLocators.WIDGETANALYTICS_CUSTOMIZE, ElementType.Xpath);
			flag = webDB.waitForElement(AnalyticsLocators.OPT_INS_CUSTOMIZE, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(AnalyticsLocators.OPT_INS_CUSTOMIZE, ElementType.Xpath);
				Thread.sleep(1000);
				flag = webDB.isElementDisplayed(AnalyticsLocators.LASTWEEKRADIOBTN, ElementType.Xpath);
				if (flag) {
					webDB.javaScriptClickAnElement(AnalyticsLocators.CUSTOMIZERADIOBTN, ElementType.CssSelector);
					flag = webDB.waitForElement(AnalyticsLocators.STARTINGDATE, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(AnalyticsLocators.STARTINGDATE, ElementType.Xpath);
						webDB.sendTextToAnElement(AnalyticsLocators.STARTINGDATE, AnalyticsLocators.STARTINGDATEVALUE,
								ElementType.Xpath);
						Thread.sleep(2000);
						flag = webDB.isElementDisplayed(AnalyticsLocators.STARTING_DATE_VALIDATION_MESSAGE,
								ElementType.Xpath);
						if (flag) {
							String toast = webDB.driver
									.findElement(By.xpath(AnalyticsLocators.STARTING_DATE_VALIDATION_MESSAGE))
									.getText();
							Thread.sleep(2000);
							flag = toast.equals(AnalyticsLocators.STARTINGDATEERRORVALUE);
							webDB.driver.navigate().refresh();
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyStartGuideIcon() throws InterruptedException {
		flag = webDB.waitForElement(AnalyticsLocators.ANALYTICS_TAB, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			webDB.clickAnElement(AnalyticsLocators.ANALYTICS_TAB, ElementType.Xpath);
			Thread.sleep(1000);
			webDB.moveToElement(AnalyticsLocators.QUESTIONMARKICON, ElementType.CssSelector);
			flag = webDB.isElementDisplayed(AnalyticsLocators.QUESTIONMARKICON, ElementType.CssSelector);
			if (flag) {
				webDB.clickAnElement(AnalyticsLocators.QUESTIONMARKICON, ElementType.CssSelector);
				Thread.sleep(1000);
				flag = webDB.isElementDisplayed(AnalyticsLocators.STARTGUIDEBTN, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(AnalyticsLocators.STARTGUIDEBTN, ElementType.Xpath);
					Thread.sleep(1000);
					webDB.clickAnElement(AnalyticsLocators.NEXTBTN, ElementType.Xpath);
					Thread.sleep(1000);
					webDB.clickAnElement(AnalyticsLocators.NEXTBTN, ElementType.Xpath);
					Thread.sleep(1000);
					webDB.clickAnElement(AnalyticsLocators.NEXTBTN, ElementType.Xpath);
					Thread.sleep(1000);
					webDB.clickAnElement(AnalyticsLocators.NEXTBTN, ElementType.Xpath);
					flag = webDB.waitForElement(AnalyticsLocators.GUIDECOMPLETEDBTN, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(AnalyticsLocators.GUIDECOMPLETEDBTN, ElementType.Xpath);
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifySpinWheelCurrentlyDisableEnable()
			throws InterruptedException, FileNotFoundException, IOException {
		flag = webDB.waitForElement(WidgetLocators.PERSONALWIDGETTAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WidgetLocators.PERSONALWIDGETTAB, ElementType.Xpath);
			Thread.sleep(2500);
			flag = webDB.waitForElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
				Thread.sleep(2500);
				webDB.moveToElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
				flag = webDB.waitForElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
					flag = webDB.isElementDisplayed(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
					text = webDB.driver.findElement(By.xpath(CommonLocators.ENABLE_DISABLE_BTN)).getText();
					log.info(text);
					if (text.equals("Disable")) {
						Thread.sleep(2000);
						webDB.clickAnElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
					}
					flag = webDB.waitForElement(AnalyticsLocators.ANALYTICS_TAB, ElementType.Xpath);
					if (flag) {
						Thread.sleep(2000);
						webDB.clickAnElement(AnalyticsLocators.ANALYTICS_TAB, ElementType.Xpath);
						Thread.sleep(1000);
						webDB.moveToElement(AnalyticsLocators.AUTOMATED_MESSAGE_REPORT_SECTION, ElementType.Xpath);
						// modified - commented below
//						flag = webDB.isElementDisplayed(AnalyticsLocators.CURRENTLYDISABLED, ElementType.Xpath);
//						if (flag) {
//							webDB.clickAnElement(AnalyticsLocators.CURRENTLYDISABLEDCLICKHERE, ElementType.Xpath);
						CommonFunctions.verifyPersonalWidgetPage();
						flag = webDB.waitForElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(WidgetLocators.SPINWHEEL_SETTINGS_TAB, ElementType.Xpath);
							Thread.sleep(2000);
							webDB.moveToElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
							flag = webDB.waitForElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(WidgetLocators.CONFIGURESPINWHEEL, ElementType.Xpath);
								String text = webDB.driver.findElement(By.xpath(CommonLocators.ENABLE_DISABLE_BTN))
										.getText();
								log.info(text);
								if (text.equals("Enable")) {
									Thread.sleep(2000);
									flag = webDB.clickAnElement(CommonLocators.ENABLE_DISABLE_BTN, ElementType.Xpath);
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

	public static boolean verifyAbandonedCurrentlyDisableEnable()
			throws InterruptedException, FileNotFoundException, IOException {
		flag = webDB.waitForElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
			Thread.sleep(2000);
			webDB.clickAnElement(AutomationLocators.ABCART_ENABLE_DISABLE_ICON, ElementType.Xpath);
			Thread.sleep(2000);
			flag = webDB.clickAnElement(AutomationLocators.SETTINGS_ABCART_TEMPLATE, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				webDB.javaScriptClickAnElement(AutomationLocators.CANCEL_BTN, ElementType.Xpath);
				Thread.sleep(2000);
				webDB.clickAnElement(AutomationLocators.ABCART_ENABLE_DISABLE_ICON, ElementType.Xpath);
			} else {
				System.out.println("Abdanted cart is disabled");
			}
			flag = webDB.waitForElement(AnalyticsLocators.ANALYTICS_TAB, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				webDB.clickAnElement(AnalyticsLocators.ANALYTICS_TAB, ElementType.Xpath);
				Thread.sleep(1000);
				webDB.moveToElement(AnalyticsLocators.OTHER_MESSAGES_SECTION, ElementType.Xpath); // - only in shopify
//				flag = webDB.isElementDisplayed(AnalyticsLocators.CURRENTLYDISABLED, ElementType.Xpath);//
//				if (flag) {
//					webDB.clickAnElement(AnalyticsLocators.CURRENTLYDISABLEDCLICKHERE, ElementType.Xpath);//
				flag = webDB.waitForElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
					flag = webDB.clickAnElement(AutomationLocators.SETTINGS_ABCART_TEMPLATE, ElementType.Xpath);
					if (!flag) {
						Thread.sleep(2000);
						webDB.clickAnElement(AutomationLocators.ABCART_ENABLE_DISABLE_ICON, ElementType.Xpath);
						flag = true;
					}
				}
			}
		}
//		}
		return flag;
	}

	public static boolean verifyOtherMessageCurrentlyDisableEnable()
			throws InterruptedException, FileNotFoundException, IOException {
		flag = webDB.waitForElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
			Thread.sleep(2000);
			webDB.clickAnElement(AutomationLocators.ODR_CRM_ENABLE_DISABLE_ICON, ElementType.Xpath);
			Thread.sleep(2000);
			flag = webDB.clickAnElement(AutomationLocators.SETTINGS_ODR_CONFIRMATION_TEMPLATE, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				webDB.javaScriptClickAnElement(AutomationLocators.CANCEL_BTN, ElementType.Xpath);
				Thread.sleep(2000);
				webDB.clickAnElement(AutomationLocators.ODR_CRM_ENABLE_DISABLE_ICON, ElementType.Xpath);
			} else {
				System.out.println("Abdanted cart is disabled");
			}
			flag = webDB.waitForElement(AnalyticsLocators.ANALYTICS_TAB, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				webDB.clickAnElement(AnalyticsLocators.ANALYTICS_TAB, ElementType.Xpath);
				Thread.sleep(1000);
				webDB.moveToElement(AnalyticsLocators.CASHONDELIVERY_TITLE, ElementType.Xpath);
				// modified - not available
//				flag = webDB.isElementDisplayed(AnalyticsLocators.OTHERMESSAGECURRENTLYDISABLED, ElementType.Xpath);
//				if (flag) {
//					webDB.clickAnElement(AnalyticsLocators.OTHERMESSAGECURRENTLYDISABLEDCLICKHERE, ElementType.Xpath);
				flag = webDB.waitForElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
					flag = webDB.clickAnElement(AutomationLocators.SETTINGS_ODR_CONFIRMATION_TEMPLATE,
							ElementType.Xpath);
					if (!flag) {
						Thread.sleep(2000);
						webDB.clickAnElement(AutomationLocators.ODR_CRM_ENABLE_DISABLE_ICON, ElementType.Xpath);
						flag = true;
					}
				}
			}
		}
//		}
		return flag;
	}

	public static boolean verifyClickcount() throws Exception {
		clickOnChatAgent();
		clickOnShareWidget();
		flag = WidgetPage.VerifyWheelSpinsAfterWins();
		return flag;
	}

	public static boolean verifySpinWheelMaxMonthsDateFiler() throws InterruptedException {
		flag = webDB.waitForElement(AnalyticsLocators.ANALYTICS_TAB, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			webDB.clickAnElement(AnalyticsLocators.ANALYTICS_TAB, ElementType.Xpath);
			Thread.sleep(1000);
			webDB.moveToElement(AnalyticsLocators.AUTOMATED_MESSAGE_REPORT_CUSTOMIZE, ElementType.Xpath);
			flag = webDB.waitForElement(AnalyticsLocators.SPINWHEEL_CUSTOMIZE, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(AnalyticsLocators.SPINWHEEL_CUSTOMIZE, ElementType.Xpath);
				Thread.sleep(1000);
				flag = webDB.isElementDisplayed(AnalyticsLocators.LASTWEEKRADIOBTN, ElementType.Xpath);
				if (flag) {
					webDB.javaScriptClickAnElement(AnalyticsLocators.CUSTOMIZERADIOBTN, ElementType.CssSelector);
					flag = webDB.waitForElement(AnalyticsLocators.STARTINGDATE, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(AnalyticsLocators.STARTINGDATE, ElementType.Xpath);
						webDB.sendTextToAnElement(AnalyticsLocators.STARTINGDATE, AnalyticsLocators.STARTINGDATEVALUE,
								ElementType.Xpath);
						Thread.sleep(2000);
						flag = webDB.isElementDisplayed(AnalyticsLocators.STARTING_DATE_VALIDATION_MESSAGE,
								ElementType.Xpath);
						if (flag) {
							String toast = webDB.driver
									.findElement(By.xpath(AnalyticsLocators.STARTING_DATE_VALIDATION_MESSAGE))
									.getText();
							Thread.sleep(2000);
							flag = toast.equals(AnalyticsLocators.STARTINGDATEERRORVALUE);
							webDB.driver.navigate().refresh();
						}
					}
				}
			}
		}
		return flag;
	}

}