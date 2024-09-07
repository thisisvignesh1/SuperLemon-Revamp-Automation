package commonFunctions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.Ordering;
import com.google.gson.annotations.Until;

import locators.CampaignsLocators;
import locators.CommonLocators;
import locators.MessageLogsLocators;
import locators.ShopifyStoreLocators;
import utils.DriverBase;
import utils.DriverBase.ElementType;

@SuppressWarnings({ "static-access", "unused" })
public class MessageLogsPage {

	private static Logger log = Logger.getLogger(MessageLogsPage.class);
	private static boolean flag;
	private static String propertyFilePath;
	private static String url;
	public static Properties prop = new Properties();
	private static DriverBase webDB = new DriverBase();
	private static Set<String> windows;
	private static Iterator<String> it;
	private static String parentWindow;
	private static String childWindow;
	private static Actions actions = new Actions(webDB.driver);
	private static Select dropDown;
	private static String text;
	private static boolean AscisSorted;
	private static boolean DescisSorted;
	private static String phone;
	private static String Name;
	private static String EMAIL = "bottest.gupshup.101@gmail.com";
	private static String PASSWORD = "=watd\\vZz!$&UZMx";
	private static int rowcount = 0;
	private static List<WebElement> elements;
	private static WebDriverWait wait = new WebDriverWait(webDB.driver, 10);
	private static JavascriptExecutor js = (JavascriptExecutor) webDB.driver;
	private static WebElement element;

	public static boolean verifyNote() throws InterruptedException {
		flag = webDB.isElementDisplayed(MessageLogsLocators.MESSAGE_LOGS_TITLE, ElementType.Xpath);
		flag = webDB.isElementDisplayed(MessageLogsLocators.MESSAGE_LOGS_NOTE, ElementType.Xpath);
		if (flag = true) {
			System.out.println("Note is Displayed");
		}
		return flag;
	}

	public static boolean verifyColumnsHeader() throws InterruptedException {
		flag = webDB.isElementDisplayed(MessageLogsLocators.MESSAGE_LOGS_TABLE_COLUMN + "'" + "Order" + "']",
				ElementType.Xpath);
		flag = webDB.isElementDisplayed(MessageLogsLocators.MESSAGE_LOGS_TABLE_COLUMN + "'" + "Name" + "']",
				ElementType.Xpath);
		flag = webDB.isElementDisplayed(MessageLogsLocators.MESSAGE_LOGS_TABLE_COLUMN + "'" + "Amount" + "']",
				ElementType.Xpath);
		flag = webDB.isElementDisplayed(MessageLogsLocators.MESSAGE_LOGS_TABLE_COLUMN2 + "'" + "Type" + "']",
				ElementType.Xpath);
		flag = webDB.isElementDisplayed(MessageLogsLocators.MESSAGE_LOGS_TABLE_COLUMN2 + "'" + "Status" + "']",
				ElementType.Xpath);
		if (flag = true) {
			System.out.println("All Columns Present");
		}
		return flag;
	}

	public static boolean verifyNamesDisplay() throws InterruptedException {
		flag = webDB.isElementDisplayed(MessageLogsLocators.NAME_CELL, ElementType.Xpath);
		if (flag = true) {
			System.out.println("Names Columns Present");
		}
		return flag;
	}

	public static boolean verifyfilterforcolumns() throws InterruptedException {
		Thread.sleep(1000);
		String orderid = webDB.driver.findElement(By.xpath("//tbody/tr[1]/th[1]")).getText();
		System.out.println(orderid);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MessageLogsLocators.SEARCH_BY_ORDER)));
		webDB.driver.findElement(By.xpath(MessageLogsLocators.SEARCH_BY_ORDER)).click();
//		actions.moveToElement(element).click().build().perform();
		webDB.driver.findElement(By.xpath(MessageLogsLocators.SEARCH_BY_ORDER))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(MessageLogsLocators.SEARCH_BY_ORDER, orderid, ElementType.Xpath);
		flag = webDB.isElementDisplayed(MessageLogsLocators.ORDERID_COLUMN + "'" + orderid + "']", ElementType.Xpath);
		if (flag = true) {
			log.info("Order is displayed");
		}
		Thread.sleep(1200);
		webDB.driver.findElement(By.xpath(MessageLogsLocators.SEARCH_BY_ORDER))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		String Name = "Auto User";
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			webDB.clickAnElement(CommonLocators.MOREFILTERSBTN, ElementType.Xpath);
			flag = webDB.clickAnElement(MessageLogsLocators.NAME_DROPDOWN, ElementType.Xpath);
			if (flag) {
				webDB.waitForElement(MessageLogsLocators.SEARCH_BY_NAME, ElementType.Xpath);
				webDB.driver.findElement(By.xpath(MessageLogsLocators.SEARCH_BY_NAME))
						.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
				webDB.sendTextToAnElement(MessageLogsLocators.SEARCH_BY_NAME, Name, ElementType.Xpath);
				webDB.clickAnElement(CommonLocators.DONEBTN, ElementType.Xpath);
			}
		} else {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MessageLogsLocators.NAME_DROPDOWN)));
			flag = webDB.clickAnElement(MessageLogsLocators.NAME_DROPDOWN, ElementType.Xpath);
			webDB.waitForElement(MessageLogsLocators.SEARCH_BY_NAME, ElementType.Xpath);
			webDB.driver.findElement(By.xpath(MessageLogsLocators.SEARCH_BY_NAME))
					.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			webDB.sendTextToAnElement(MessageLogsLocators.SEARCH_BY_NAME, Name, ElementType.Xpath);
			Thread.sleep(1000);
		}
		flag = webDB.isElementDisplayed(MessageLogsLocators.NAME_DISPLAY1 + Name + "')]", ElementType.Xpath);
		flag = webDB.isElementDisplayed(MessageLogsLocators.NAME_CELL, ElementType.Xpath);
		if (flag = true) {
			log.info("Name is displayed");
		}
		String TypeCheckbox = "Order Confirmation";
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			webDB.clickAnElement(CommonLocators.MOREFILTERSBTN, ElementType.Xpath);
			flag = webDB.clickAnElement(MessageLogsLocators.TYPE_DROPDOWN, ElementType.Xpath);
			webDB.waitForElement(MessageLogsLocators.TYPE_CHECKBOX + "'" + TypeCheckbox + "')]", ElementType.Xpath);
			flag = webDB.clickAnElement(MessageLogsLocators.TYPE_CHECKBOX + "'" + TypeCheckbox + "')]",
					ElementType.Xpath);
			flag = webDB.isElementDisplayed(MessageLogsLocators.TYPE_CHECKBOX + "'" + TypeCheckbox + "')]",
					ElementType.Xpath);
			webDB.clickAnElement(CommonLocators.DONEBTN, ElementType.Xpath);
		} else {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MessageLogsLocators.TYPE_DROPDOWN)));
			flag = webDB.clickAnElement(MessageLogsLocators.TYPE_DROPDOWN, ElementType.Xpath);

			// Order Confirmation
			// Cash on Delivery
			webDB.waitForElement(MessageLogsLocators.TYPE_CHECKBOX + "'" + TypeCheckbox + "')]", ElementType.Xpath);
			flag = webDB.clickAnElement(MessageLogsLocators.TYPE_CHECKBOX + "'" + TypeCheckbox + "')]",
					ElementType.Xpath);
			flag = webDB.isElementDisplayed(MessageLogsLocators.TYPE_CHECKBOX + "'" + TypeCheckbox + "')]",
					ElementType.Xpath);
		}
		if (flag = true) {
			log.info("Order Type displayed");
		}
		return flag;
	}

	public static boolean verifyTimestampcolumn() throws InterruptedException {
		flag = webDB.isElementDisplayed(MessageLogsLocators.ATTEMPTED_HEADER, ElementType.Xpath);
		flag = webDB.isElementDisplayed(MessageLogsLocators.ATTEMPTED_TIMESTAMP, ElementType.Xpath);
		if (flag = true) {
			log.info("Timestamp is visible");
		}
		return flag;
	}

	public static boolean verifySearchByName() throws InterruptedException {
		Thread.sleep(1000);
		String Name = "AutoUser ゘锩ֻ쟣﫿";
		Dimension d = new Dimension(1920, 1000);
		webDB.driver.manage().window().setSize(d);
		webDB.waitForElement(MessageLogsLocators.NAME_DROPDOWN, ElementType.Xpath);
		element = webDB.driver.findElement(By.xpath(MessageLogsLocators.NAME_DROPDOWN));
		actions.moveToElement(element).click().build().perform();
		System.out.println("clicked on name dropdown");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MessageLogsLocators.SEARCH_BY_NAME)));
		WebElement searchByName = webDB.driver.findElement(By.xpath(MessageLogsLocators.NAME_DROPDOWN));
		actions.moveToElement(searchByName).click().build().perform();
		webDB.driver.findElement(By.xpath(MessageLogsLocators.SEARCH_BY_NAME))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(MessageLogsLocators.SEARCH_BY_NAME, Name, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.isElementDisplayed(MessageLogsLocators.NAME_DISPLAY1 + Name + "')]", ElementType.Xpath);
		flag = webDB.isElementDisplayed(MessageLogsLocators.NAME_CELL, ElementType.Xpath);
		if (flag = true) {
			System.out.println("Name is displayed");
		}

		return flag;
	}

	public static boolean verifySearchByOrderID() throws InterruptedException {
		// String orderid = "4228606656692";
		String OrderID = webDB.driver.findElement(By.xpath("//tbody/tr[1]/th[1]")).getText();

		webDB.waitForElement(MessageLogsLocators.SEARCH_BY_ORDER, ElementType.Xpath);
		webDB.sendTextToAnElement(MessageLogsLocators.SEARCH_BY_ORDER, OrderID, ElementType.Xpath);
		flag = webDB.isElementDisplayed(MessageLogsLocators.ORDERID_COLUMN + "'" + OrderID + "']", ElementType.Xpath);
		if (flag = true) {
			System.out.println("Order is displayed");
		}

		webDB.driver.findElement(By.xpath(MessageLogsLocators.SEARCH_BY_ORDER))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

		return flag;
	}

	public static boolean verifyClearAllButtonOnFilter() throws InterruptedException {
		flag = webDB.waitForElement(MessageLogsLocators.TYPE_DROPDOWN, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MessageLogsLocators.TYPE_DROPDOWN, ElementType.Xpath);
			Thread.sleep(2500);
			List<WebElement> elsize = webDB.driver.findElements(By.xpath(MessageLogsLocators.INPUT_CHECKBOX));
			for (int i = 0; i < elsize.size(); i++) {
				// if (!elsize.get(i).isSelected()) {
				List<WebElement> els1 = webDB.driver.findElements(By.xpath(MessageLogsLocators.ALL_TYPE_CHECKBOX));
				Thread.sleep(1000);
				js.executeScript("arguments[0].click();", els1.get(i));
				flag = webDB.waitForElement(MessageLogsLocators.TYPE_DROPDOWN, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(MessageLogsLocators.TYPE_DROPDOWN, ElementType.Xpath);
				}
				// }
			}
			flag = webDB.waitForElement(MessageLogsLocators.TYPE_DROPDOWN, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(MessageLogsLocators.TYPE_DROPDOWN, ElementType.Xpath);

				flag = webDB.isElementDisplayed(MessageLogsLocators.CLEAR_ALL_BTN, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(MessageLogsLocators.CLEAR_ALL_BTN, ElementType.Xpath);
					Thread.sleep(1000);
					webDB.waitForElement(MessageLogsLocators.TYPE_DROPDOWN, ElementType.Xpath);
					flag = webDB.clickAnElement(MessageLogsLocators.TYPE_DROPDOWN, ElementType.Xpath);

					if (webDB.driver.findElement(By.xpath(MessageLogsLocators.CLEAR_ALL_BTN)).isEnabled()) {
						System.out.println("Selection Not Cleared");
					} else {
						flag = true;
						System.out.println("Selection Cleared");
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyStatusColumn() throws InterruptedException {
		flag = webDB.isElementDisplayed(MessageLogsLocators.MESSAGE_LOGS_TABLE_COLUMN2 + "'" + "Status" + "']",
				ElementType.Xpath);
		flag = webDB.isElementDisplayed(MessageLogsLocators.STATUS_CELL, ElementType.Xpath);
		if (flag) {
			System.out.println("Status Cell is displayed");
		}
		return flag;
	}

	public static boolean VerifyTypeOfOrderDisplay() throws InterruptedException {
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			webDB.clickAnElement(CommonLocators.MOREFILTERSBTN, ElementType.Xpath);
		}
		webDB.waitForElement(MessageLogsLocators.TYPE_DROPDOWN, ElementType.Xpath);
		flag = webDB.clickAnElement(MessageLogsLocators.TYPE_DROPDOWN, ElementType.Xpath);

		flag = webDB.isElementDisplayed(MessageLogsLocators.TYPE_CHECKBOX + "'" + "Cart Recovery" + "')]",
				ElementType.Xpath);
		if (flag) {
			flag = webDB.isElementDisplayed(MessageLogsLocators.TYPE_CHECKBOX + "'" + "Cash on Delivery" + "')]",
					ElementType.Xpath);
			if (flag) {
				flag = webDB.isElementDisplayed(MessageLogsLocators.TYPE_CHECKBOX + "'" + "Order Confirmation" + "')]",
						ElementType.Xpath);
				if (flag) {
					flag = webDB.isElementDisplayed(MessageLogsLocators.TYPE_CHECKBOX + "'" + "Order Tracking" + "')]",
							ElementType.Xpath);
					if (flag) {
						flag = webDB.isElementDisplayed(
								MessageLogsLocators.TYPE_CHECKBOX + "'" + "Order Cancellation" + "')]",
								ElementType.Xpath);
						if (flag) {
							flag = webDB.isElementDisplayed(
									MessageLogsLocators.TYPE_CHECKBOX + "'" + "Payment Confirmation" + "')]",
									ElementType.Xpath);
							if (flag) {
								flag = webDB.isElementDisplayed(
										MessageLogsLocators.TYPE_CHECKBOX + "'" + "Cross Sell" + "')]",
										ElementType.Xpath);
								if (flag) {
									flag = webDB.isElementDisplayed(
											MessageLogsLocators.TYPE_CHECKBOX + "'" + "Feedback" + "')]",
											ElementType.Xpath);
									if (flag) {
										flag = webDB.isElementDisplayed(
												MessageLogsLocators.TYPE_CHECKBOX + "'" + "Win Back" + "')]",
												ElementType.Xpath);
										if (flag) {
											flag = webDB.isElementDisplayed(
													MessageLogsLocators.TYPE_CHECKBOX + "'" + "Spin the Wheel" + "')]",
													ElementType.Xpath);
//											if (flag) {
//												flag = webDB.isElementDisplayed(MessageLogsLocators.TYPE_CHECKBOX + "'"
//														+ "WhatsApp Campaign" + "')]", ElementType.Xpath);
											if (flag) {

												if ((System.getProperty("platformName").equals("browserstack"))
														&& (System.getProperty("os").equals("Ios"))
														|| (System.getProperty("os").equals("Android"))) {
													webDB.waitForElement(MessageLogsLocators.CLOSEICON,
															ElementType.Xpath);
													flag = webDB.clickAnElement(MessageLogsLocators.CLOSEICON,
															ElementType.Xpath);
												}

												if (flag) {
													System.out.println("Type of order checkbox displayed");
												}
											}
											// }
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

	public static boolean VerifyClearButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MessageLogsLocators.TYPE_DROPDOWN)));
		webDB.waitForElement(MessageLogsLocators.TYPE_DROPDOWN, ElementType.Xpath);
		element = webDB.driver.findElement(By.xpath(MessageLogsLocators.TYPE_DROPDOWN));
		actions.moveToElement(element).click().build().perform();
		flag = !webDB.driver.findElement(By.xpath(MessageLogsLocators.CLEAR_ALL_BTN)).isEnabled();
		if (flag) {
			System.out.println("Clear Button Disabled");
		}
		webDB.waitForElement(MessageLogsLocators.TYPE_CHECKBOX + "'" + "Cart Recovery" + "')]", ElementType.Xpath);
		flag = webDB.javaScriptClickAnElement(MessageLogsLocators.TYPE_CHECKBOX + "'" + "Cart Recovery" + "')]",
				ElementType.Xpath);
		if (flag) {
			System.out.println("Checkbox selected");

			flag = webDB.waitForElement(MessageLogsLocators.TYPE_DROPDOWN, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(MessageLogsLocators.TYPE_DROPDOWN, ElementType.Xpath);
				flag = webDB.driver.findElement(By.xpath(MessageLogsLocators.CLEAR_ALL_BTN)).isEnabled();
				if (flag) {
					System.out.println("Clear Button Enabled");
				}
			}
		}
		return flag;
	}

	public static boolean VerifyFilterTextBelowOrderID() throws InterruptedException {
		Dimension d = new Dimension(1920, 1080);
		webDB.driver.manage().window().setSize(d);
		String Name = "AutoUser ゘锩ֻ쟣﫿";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MessageLogsLocators.NAME_DROPDOWN)));
		element = webDB.driver.findElement(By.xpath(MessageLogsLocators.NAME_DROPDOWN));
		actions.moveToElement(element).click().build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MessageLogsLocators.SEARCH_BY_NAME)));
		WebElement searchByName = webDB.driver.findElement(By.xpath(MessageLogsLocators.SEARCH_BY_NAME));
		actions.moveToElement(searchByName).click().build().perform();
		webDB.driver.findElement(By.xpath(MessageLogsLocators.SEARCH_BY_NAME))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(MessageLogsLocators.SEARCH_BY_NAME, Name, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.isElementDisplayed(MessageLogsLocators.FILTER_TAG + "'Name - " + Name + "']", ElementType.Xpath);
		if (flag) {
			System.out.println("Name filter tag displayed");
		}
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MessageLogsLocators.TYPE_DROPDOWN)));
		flag = webDB.clickAnElement(MessageLogsLocators.TYPE_DROPDOWN, ElementType.Xpath);
		Thread.sleep(1000);
		webDB.waitForElement(MessageLogsLocators.TYPE_CHECKBOX + "'" + "Cart Recovery" + "')]", ElementType.Xpath);
		flag = webDB.javaScriptClickAnElement(MessageLogsLocators.TYPE_CHECKBOX + "'" + "Cart Recovery" + "')]",
				ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.isElementDisplayed(MessageLogsLocators.FILTER_TAG + "'" + "CART_RECOVERY" + "']",
				ElementType.Xpath);
		if (flag) {
			System.out.println("Order Type filter tag displayed");
		}

		return flag;
	}

	public static boolean VerifyClearFilterTags() throws InterruptedException {
//		Dimension d = new Dimension(1920, 1080);
//		webDB.driver.manage().window().setSize(d);
		Thread.sleep(1000);
		// String Name = "AutoUser ゘锩ֻ쟣﫿";
		String Name = webDB.getText(MessageLogsLocators.FIRST_NAME, ElementType.Xpath);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MessageLogsLocators.NAME_DROPDOWN)));
		element = webDB.driver.findElement(By.xpath(MessageLogsLocators.NAME_DROPDOWN));
		actions.moveToElement(element).click().build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MessageLogsLocators.SEARCH_BY_NAME)));
		webDB.waitForElement(MessageLogsLocators.SEARCH_BY_NAME, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MessageLogsLocators.SEARCH_BY_NAME))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(MessageLogsLocators.SEARCH_BY_NAME, Name, ElementType.Xpath);
		flag = webDB.waitForElement(MessageLogsLocators.SEARCH_BTN, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MessageLogsLocators.SEARCH_BTN, ElementType.Xpath);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MessageLogsLocators.FILTER_TAG_CLOSE_BTN)));
			flag = webDB.clickAnElement(MessageLogsLocators.FILTER_TAG_CLOSE_BTN, ElementType.Xpath);
			if (flag) {
				System.out.println("Filter Tag cleared");
			}
		}
		return flag;
	}

	public static boolean VerifyFilterResult() throws InterruptedException {
		Dimension d = new Dimension(1920, 1080);
		webDB.driver.manage().window().setSize(d);
		Thread.sleep(1000);
//		String OrderID = "4608775160059";
		String Name = webDB.getText(MessageLogsLocators.FIRST_NAME, ElementType.Xpath);
		// String Name = "AutoUser ゘锩ֻ쟣﫿";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MessageLogsLocators.NAME_DROPDOWN)));
		element = webDB.driver.findElement(By.xpath(MessageLogsLocators.NAME_DROPDOWN));
		actions.moveToElement(element).click().build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MessageLogsLocators.SEARCH_BY_NAME)));
		WebElement searchByName = webDB.driver.findElement(By.xpath(MessageLogsLocators.SEARCH_BY_NAME));
		actions.moveToElement(searchByName).click().build().perform();
		webDB.driver.findElement(By.xpath(MessageLogsLocators.SEARCH_BY_NAME))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(MessageLogsLocators.SEARCH_BY_NAME, Name, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.isElementDisplayed(MessageLogsLocators.NAME_DISPLAY1 + Name + "')]", ElementType.Xpath);
		flag = webDB.isElementDisplayed(MessageLogsLocators.NAME_CELL, ElementType.Xpath);
		if (flag = true) {
			System.out.println("Name is displayed");
		}
//		webDB.driver.findElement(By.xpath(MessageLogsLocators.SEARCH_BY_NAME))
//				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

		webDB.waitForElement(MessageLogsLocators.ORDERID_CELL, ElementType.Xpath);
		String orderid = webDB.getText(MessageLogsLocators.ORDERID_CELL, ElementType.Xpath);
		// String orderid =
		// webDB.driver.findElement(By.xpath("//tbody/tr[1]/th[1]")).getText();
		Thread.sleep(2000);
		webDB.waitForElement(MessageLogsLocators.SEARCH_BY_ORDER, ElementType.Xpath);
		webDB.sendTextToAnElement(MessageLogsLocators.SEARCH_BY_ORDER, orderid, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.isElementDisplayed(MessageLogsLocators.ORDERID_COLUMN + "'" + orderid + "']", ElementType.Xpath);
		if (flag = true) {
			System.out.println("Order is displayed");
		}

		webDB.driver.findElement(By.xpath(MessageLogsLocators.SEARCH_BY_ORDER))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

		return flag;
	}

	public static boolean ClearSearchByNameTxtbox() throws InterruptedException {
		Dimension d = new Dimension(1920, 1080);
		webDB.driver.manage().window().setSize(d);
		Thread.sleep(1000);
		// String Name = "AutoUser ゘锩ֻ쟣﫿";
		String Name = webDB.getText(MessageLogsLocators.FIRST_NAME, ElementType.Xpath);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MessageLogsLocators.NAME_DROPDOWN)));
		element = webDB.driver.findElement(By.xpath(MessageLogsLocators.NAME_DROPDOWN));
		actions.moveToElement(element).click().build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MessageLogsLocators.SEARCH_BY_NAME)));
		webDB.waitForElement(MessageLogsLocators.SEARCH_BY_NAME, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MessageLogsLocators.SEARCH_BY_NAME))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(MessageLogsLocators.SEARCH_BY_NAME, Name, ElementType.Xpath);
		webDB.clickAnElement(MessageLogsLocators.SEARCH_BTN, ElementType.Xpath);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MessageLogsLocators.NAME_DROPDOWN)));
		element = webDB.driver.findElement(By.xpath(MessageLogsLocators.NAME_DROPDOWN));
		actions.moveToElement(element).click().build().perform();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MessageLogsLocators.CLEAR_NAME_BTN)));
		flag = webDB.isElementDisplayed(MessageLogsLocators.CLEAR_NAME_BTN, ElementType.Xpath);
		flag = webDB.clickAnElement(MessageLogsLocators.CLEAR_NAME_BTN, ElementType.Xpath);
		Thread.sleep(1000);
		webDB.waitForElement(MessageLogsLocators.NAME_DROPDOWN, ElementType.Xpath);
		flag = webDB.clickAnElement(MessageLogsLocators.NAME_DROPDOWN, ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath(MessageLogsLocators.SEARCH_BY_NAME)).getText();
		flag = text.equalsIgnoreCase("");
		if (flag) {
			System.out.println("Name field cleared");
		}
		return flag;
	}

	public static boolean VerifyClearSearchByNameTxtboxEnableDisable() throws InterruptedException {
		// Dimension d = new Dimension(1920, 1080);
		// webDB.driver.manage().window().setSize(d);
		Thread.sleep(1000);
		// String Name = "AutoUser ゘锩ֻ쟣﫿";
		String Name = webDB.getText(MessageLogsLocators.FIRST_NAME, ElementType.Xpath);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MessageLogsLocators.NAME_DROPDOWN)));
		element = webDB.driver.findElement(By.xpath(MessageLogsLocators.NAME_DROPDOWN));
		actions.moveToElement(element).click().build().perform();
		Thread.sleep(1000);
		// flag =
		// !webDB.driver.findElement(By.xpath(MessageLogsLocators.SEARCH_BY_NAME)).isEnabled();
		flag = webDB.IsEnabled(MessageLogsLocators.CLEAR_NAME_BTN, ElementType.Xpath);
		System.out.println(flag);
		webDB.waitForElement(MessageLogsLocators.SEARCH_BY_NAME, ElementType.Xpath);
		webDB.driver.findElement(By.xpath(MessageLogsLocators.SEARCH_BY_NAME))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(MessageLogsLocators.SEARCH_BY_NAME, Name, ElementType.Xpath);
		Thread.sleep(1000);
		webDB.clickAnElement(MessageLogsLocators.SEARCH_BTN, ElementType.Xpath);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MessageLogsLocators.NAME_DROPDOWN)));
		element = webDB.driver.findElement(By.xpath(MessageLogsLocators.NAME_DROPDOWN));
		actions.moveToElement(element).click().build().perform();
		// flag =
		// webDB.driver.findElement(By.xpath(MessageLogsLocators.SEARCH_BY_NAME)).isEnabled();
		flag = webDB.IsEnabled(MessageLogsLocators.CLEAR_NAME_BTN, ElementType.Xpath);
		System.out.println(flag);
		return flag;
	}

	public static boolean VerifyOrderSorting() throws InterruptedException {
		flag = webDB.clickAnElement(MessageLogsLocators.MESSAGE_LOGS_TABLE_COLUMN + "'" + "Order" + "']",
				ElementType.Xpath);
		Thread.sleep(1000);
		System.out.print("Order ");
		Asc_Desc_Sorted("th");
		return flag;
	}

	public static boolean VerifyNameSorting() throws InterruptedException {
		flag = webDB.clickAnElement(MessageLogsLocators.MESSAGE_LOGS_TABLE_COLUMN + "'" + "Name" + "']",
				ElementType.Xpath);
		Thread.sleep(1500);
		System.out.print("Name ");
		Asc_Desc_Sorted("td[1]");
		return flag;
	}

	public static boolean VerifyAmountSorting() throws InterruptedException {
		flag = webDB.clickAnElement(MessageLogsLocators.MESSAGE_LOGS_TABLE_COLUMN + "'" + "Amount" + "']",
				ElementType.Xpath);
		Thread.sleep(1000);
		System.out.print("Amount ");
		Asc_Desc_Sorted("td[2]");
		return flag;
	}

	public static void Asc_Desc_Sorted(String param) throws InterruptedException {
		ArrayList<String> sortedList = new ArrayList<>();
		List<WebElement> elementList1 = webDB.driver.findElements(By.xpath(MessageLogsLocators.TABLE_DATA + param));
		for (WebElement we : elementList1) {
			sortedList.add(we.getText());
		}

		boolean DescisSorted = Ordering.from(String.CASE_INSENSITIVE_ORDER).isOrdered(sortedList);
		boolean AscisSorted = Ordering.from(String.CASE_INSENSITIVE_ORDER.reversed()).isOrdered(sortedList);

		if (AscisSorted == true) {
			System.out.println("List Sorted in Ascending Order");
			DescisSorted = false;
		} else if (DescisSorted == true) {
			System.out.println("List Sorted in Descending Order");
			AscisSorted = false;
		} else {
			System.out.println("Not Sorted");
		}
	}

	public static boolean VerifyDataTableRowCount() throws InterruptedException {
		Thread.sleep(1000);
		DataTableRowCount();
		return flag;
	}

	public static int DataTableRowCount() throws InterruptedException {
		List<WebElement> rows = webDB.driver.findElements(By.xpath(MessageLogsLocators.DATA_TABLE_ROW));
		rowcount = rows.size();

		if (rowcount >= 10) {
			flag = true;
			System.out.println("Number of pages displayed " + rowcount);
		} else {
			// flag=false;
			System.out.println("Number of pages beyond count 10");
		}

		return rowcount;
	}

	public static boolean VerifyPageNavigation() throws InterruptedException {
		webDB.moveToElement(MessageLogsLocators.FIRST_ROW, ElementType.Xpath);
		String FirstPageRow = webDB.driver.findElement(By.xpath(MessageLogsLocators.FIRST_ROW)).getText();

		Thread.sleep(1000);
		flag = webDB.driver.findElement(By.xpath(MessageLogsLocators.NAV_NEXT)).isEnabled();
		if (flag) {
			webDB.waitForElement(MessageLogsLocators.NAV_NEXT, ElementType.Xpath);
			flag = webDB.clickAnElement(MessageLogsLocators.NAV_NEXT, ElementType.Xpath);
			System.out.println("Navigation next clicked");

			Thread.sleep(1000);
			String NextPageRow = webDB.driver.findElement(By.xpath(MessageLogsLocators.FIRST_ROW)).getText();

			if (!FirstPageRow.equalsIgnoreCase(NextPageRow)) {
				System.out.println("DataTable Row Data changed");
				flag = true;
			}
		} else {
			System.out.println("Only 10 rows are populated");
		}
		return flag;
	}

	public static boolean VerifyTimestamp() throws InterruptedException {
		String TIMESTAMP = webDB.driver.findElement(By.xpath(MessageLogsLocators.TIMESTAMP_FIRST_ROW)).getText();

		SimpleDateFormat format = new SimpleDateFormat("MMMMM d,y h:mm a");

		try {
			format.parse(TIMESTAMP);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}

		return flag;
	}

	public static boolean VerifyAmountDisplay() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MessageLogsLocators.NAME_DROPDOWN)));
		flag = webDB.isElementDisplayed(MessageLogsLocators.MESSAGE_LOGS_TABLE_COLUMN + "'" + "Amount" + "']",
				ElementType.Xpath);
		if (flag) {
			flag = webDB.isElementDisplayed(MessageLogsLocators.CURRENCY_CELL, ElementType.Xpath);
			if (flag) {
				System.out.println("Amount cell is displayed");
			}
		}
		return flag;
	}

	public static boolean VerifyLatestSortedTimestamp() throws InterruptedException, ParseException {
		elements = webDB.driver.findElements(By.xpath(MessageLogsLocators.TABLE_ROWS));

		if (elements.size() > 1) {
			String TIMESTAMP1 = webDB.driver
					.findElement(By.xpath(MessageLogsLocators.ATTEMPTED_CELL + "[1]" + "/td[5]")).getText();
			System.out.println(TIMESTAMP1);
			String TIMESTAMP2 = webDB.driver
					.findElement(By.xpath(MessageLogsLocators.ATTEMPTED_CELL + "[" + elements.size() + "]" + "/td[5]"))
					.getText();
			System.out.println(TIMESTAMP2);
			SimpleDateFormat dateFormat = new SimpleDateFormat("MMMMM d,y h:mm a");

//			try {
			Date date1 = dateFormat.parse(TIMESTAMP1);
			Date date2 = dateFormat.parse(TIMESTAMP2);
			if (date2.before(date1)) {
				flag = true;
				System.out.println("Date is sorted by latest timestamp");
			} else {
				flag = false;
				System.out.println("Date is not sorted by latest timestamp");
			}
//	        	}catch(Exception ex){}

		} else {
			System.out.println("Only 1 row present");
			flag = true;
		}

		// System.out.println(flag);
		return flag;
	}

	public static boolean VerifyOrderConfirmationMessageLog()
			throws FileNotFoundException, InterruptedException, IOException {

		// Go to personal widget page
		CommonFunctions.verifyPersonalWidgetPage();
		Thread.sleep(800);

		// Go to store
		WidgetPage.personalWidgetPageToStoreNavigation();

		// Enter Credentials
		ShopifyStore.shopifyStorePassEnter();

		Thread.sleep(800);
		// Add item to cart and checkout
//		Dimension d = new Dimension(1920, 1000);
//		webDB.driver.manage().window().setSize(d);
		ShopifyStore.addItemAndCheckout();

		// Enter shipment details
		ShopifyStore.enterShippingDetailsWithPhone();
		phone = phone;

		Thread.sleep(1000);
		// Enter card details
		ShopifyStore.enterCardDetails();

		// Click on Pay Now
		webDB.clickAnElement(ShopifyStoreLocators.CONTINUE_BTN, ElementType.Xpath);

		Thread.sleep(500);
		// Verify thankyou page
		webDB.waitForElement(ShopifyStoreLocators.THANKYOU_MSG, ElementType.Xpath);
		flag = webDB.isElementDisplayed(ShopifyStoreLocators.THANKYOU_MSG, ElementType.Xpath);
		String Address;
		if (flag) {
			text = webDB.driver.findElement(By.xpath(ShopifyStoreLocators.THANKYOU_MSG)).getText();
			System.out.println("text = " + text);
			Address = webDB.driver.findElement(By.xpath(ShopifyStoreLocators.NAME_IN_ADDRESS)).getText();
			System.out.println("Address " + Address);
			String[] AddressArr = Address.split("\n");
//			System.out.println(AddressArr[0]+"New--");
			Thread.sleep(2000);
			Name = AddressArr[0];
			String[] AddressArr1 = Address.split(" ");
			System.out.println("Name = " + Name);
//			System.out.println(AddressArr1[0]+"----");
			Thread.sleep(4000);
			if (text.contains(AddressArr1[0])) {
				System.out.println("Opted in successfully through widget on thank you page");
				flag = true;
			}
		} else {
			flag = false;
		}
		Thread.sleep(1000);
		windows = webDB.driver.getWindowHandles();
		it = windows.iterator();
		parentWindow = it.next();
		childWindow = it.next();
		webDB.driver.close();
		// Move to Message logs page
		webDB.driver.switchTo().window(parentWindow);

		CommonFunctions.verifyMessageLogsPage();

		// Filter name and search
		Thread.sleep(2000);
		// webDB.driver.navigate().refresh();
		// webDB.driver.manage().window().setSize(d);
		webDB.waitForElement(MessageLogsLocators.NAME_DROPDOWN, ElementType.Xpath);
		element = webDB.driver.findElement(By.xpath(MessageLogsLocators.NAME_DROPDOWN));
		actions.moveToElement(element).click().build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MessageLogsLocators.SEARCH_BY_NAME)));
		// WebElement searchByName =
		// webDB.driver.findElement(By.xpath(MessageLogsLocators.NAME_DROPDOWN));
		// actions.moveToElement(searchByName).click().build().perform();
		webDB.driver.findElement(By.xpath(MessageLogsLocators.SEARCH_BY_NAME))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(MessageLogsLocators.SEARCH_BY_NAME, Name, ElementType.Xpath);
		webDB.clickAnElement(MessageLogsLocators.SEARCH_BTN, ElementType.Xpath);
		Thread.sleep(3000);
		webDB.waitForElement(MessageLogsLocators.NAME_CELL, ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath(MessageLogsLocators.NAME_CELL)).getText().toString();
		log.info(text);
		log.info(Name);
		Thread.sleep(3000);
		// System.out.println("Text = "+ text);

		// Verify Entry
		if (text.equalsIgnoreCase(Name)) {
			text = webDB.driver.findElement(By.xpath(MessageLogsLocators.TYPE_CELL)).getText();
			log.info("Extracted text is " + text);
			if (text.equalsIgnoreCase("Order Confirmation")) {
				// System.out.println("2nd Text = "+ text);
				flag = true;
				System.out.println("Entry Displayed");
			}
		} else {
			flag = false;
		}

		return flag;
	}

	public static boolean VerifyOrderTrackingMessageLog()
			throws FileNotFoundException, InterruptedException, IOException {
		// Verify Message Logs
		// Go to Message Logs Page
		CommonFunctions.verifyMessageLogsPage();

		// click on order ID
		webDB.waitForElement(MessageLogsLocators.ORDERID_CELL, ElementType.Xpath);
		flag = webDB.clickAnElement(MessageLogsLocators.ORDERID_CELL, ElementType.Xpath);

		flag = webDB.isElementDisplayed(ShopifyStoreLocators.SHOPIFY_EMAIL, ElementType.Id);
		if (flag) {
			webDB.sendTextToAnElement(ShopifyStoreLocators.SHOPIFY_EMAIL, EMAIL, ElementType.Id);
			flag = webDB.clickAnElement(ShopifyStoreLocators.SHOPIFY_NEXT_BTN, ElementType.Xpath);

			flag = webDB.isElementDisplayed(ShopifyStoreLocators.SHOPIFY_PASSWORD, ElementType.Xpath);
			webDB.sendTextToAnElement(ShopifyStoreLocators.SHOPIFY_PASSWORD, PASSWORD, ElementType.Id);

			flag = webDB.driver.findElement(By.xpath(ShopifyStoreLocators.SHOPIFY_LOGIN_BTN)).isEnabled();
			if (flag) {
				flag = webDB.clickAnElement(ShopifyStoreLocators.SHOPIFY_LOGIN_BTN, ElementType.Xpath);
				flag = webDB.isElementDisplayed(ShopifyStoreLocators.USERNAME_LABEL, ElementType.Xpath);
			} else {
				flag = false;
			}

		} else {
			flag = false;
		}

		return flag;
	}
}
