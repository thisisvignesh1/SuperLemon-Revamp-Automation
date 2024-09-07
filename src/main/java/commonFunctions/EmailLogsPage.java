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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.Ordering;

import locators.CommonLocators;
import locators.EmailLogsLocators;
import locators.MessageLogsLocators;
import locators.ShopifyStoreLocators;
import utils.DriverBase;
import utils.DriverBase.ElementType;

@SuppressWarnings({ "static-access" })
public class EmailLogsPage {
	private static Logger log = Logger.getLogger(EmailLogsPage.class);
	private static boolean flag;
	public static Properties prop = new Properties();
	private static DriverBase webDB = new DriverBase();;
	private static Set<String> windows;
	private static Iterator<String> it;
	private static String parentWindow;
	private static Actions actions = new Actions(webDB.driver);
	private static String text;
	private static String phone;
	private static String Name;
	private static String EMAIL = "bottest.gupshup.101@gmail.com";
	private static String PASSWORD = "=watd\\vZz!$&UZMx";
	private static int rowcount = 0;
	private static List<WebElement> elements;
	private static WebDriverWait wait = new WebDriverWait(webDB.driver, 10);
	private static WebElement element;

	public static boolean verifyNamesDisplay() throws InterruptedException {
		flag = webDB.isElementDisplayed(EmailLogsLocators.NAME_CELL, DriverBase.ElementType.Xpath);
		if (flag = true) {
			System.out.println("Names Columns Present");
		}
		return flag;
	}

	public static boolean verifyFilterForColumns() throws InterruptedException {
		Thread.sleep(1000);
		String orderid = "4685101859067";
		webDB.driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(EmailLogsLocators.SEARCH_BY_ORDER)));
		element = webDB.driver.findElement(By.xpath(EmailLogsLocators.SEARCH_BY_ORDER));
		actions.moveToElement(element).click().build().perform();
		webDB.driver.findElement(By.xpath(EmailLogsLocators.SEARCH_BY_ORDER))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(EmailLogsLocators.SEARCH_BY_ORDER, orderid, DriverBase.ElementType.Xpath);
		flag = webDB.isElementDisplayed(EmailLogsLocators.ORDERID_COLUMN + "'" + orderid + "']",
				DriverBase.ElementType.Xpath);
		if (flag = true) {
			log.info("Order is displayed");
		}
		webDB.driver.findElement(By.xpath(EmailLogsLocators.SEARCH_BY_ORDER))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Thread.sleep(1200);
		String Name = "AutoUser ゘锩ֻ쟣﫿";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(EmailLogsLocators.NAME_DROPDOWN)));
		flag = webDB.clickAnElement(EmailLogsLocators.NAME_DROPDOWN, DriverBase.ElementType.Xpath);
		webDB.waitForElement(EmailLogsLocators.SEARCH_BY_NAME, DriverBase.ElementType.Xpath);
		webDB.driver.findElement(By.xpath(EmailLogsLocators.SEARCH_BY_NAME))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(EmailLogsLocators.SEARCH_BY_NAME, Name, DriverBase.ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.isElementDisplayed(EmailLogsLocators.NAME_DISPLAY1 + Name + "')]", DriverBase.ElementType.Xpath);
		flag = webDB.isElementDisplayed(EmailLogsLocators.NAME_CELL, DriverBase.ElementType.Xpath);
		if (flag = true) {
			log.info("Name is displayed");
		}
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(EmailLogsLocators.TYPE_DROPDOWN)));
		flag = webDB.clickAnElement(EmailLogsLocators.TYPE_DROPDOWN, DriverBase.ElementType.Xpath);

		String TypeCheckbox = "Order Confirmation";
		// Order Confirmation
		// Cash on Delivery
		webDB.waitForElement(EmailLogsLocators.TYPE_CHECKBOX + "'" + TypeCheckbox + "')]",
				DriverBase.ElementType.Xpath);
		flag = webDB.clickAnElement(EmailLogsLocators.TYPE_CHECKBOX + "'" + TypeCheckbox + "')]",
				DriverBase.ElementType.Xpath);
		flag = webDB.isElementDisplayed(EmailLogsLocators.TYPE_CHECKBOX + "'" + TypeCheckbox + "')]",
				DriverBase.ElementType.Xpath);
		if (flag = true) {
			log.info("Order Type displayed");
		}
		return flag;
	}

	public static boolean verifyTimeStampColumn() throws InterruptedException {
		flag = webDB.isElementDisplayed(EmailLogsLocators.ATTEMPTED_HEADER, DriverBase.ElementType.Xpath);
		flag = webDB.isElementDisplayed(EmailLogsLocators.ATTEMPTED_TIMESTAMP, DriverBase.ElementType.Xpath);
		if (flag = true) {
			log.info("Timestamp is visible");
		}
		return flag;
	}

	public static boolean verifySearchByName() throws InterruptedException {
		Thread.sleep(1000);
		String Name = "AutoUser 49080";
//        Dimension d = new Dimension(1920, 1000);
//        webDB.driver.manage().window().setSize(d);
		webDB.waitForElement(EmailLogsLocators.NAME_DROPDOWN, DriverBase.ElementType.Xpath);
//        element = webDB.driver.findElement(By.xpath(EmailLogsLocators.NAME_DROPDOWN));
//        actions.moveToElement(element).click().build().perform();
		webDB.clickAnElement(EmailLogsLocators.NAME_DROPDOWN, DriverBase.ElementType.Xpath);
		System.out.println("clicked on name dropdown");
		Thread.sleep(2000);
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
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(EmailLogsLocators.SEARCH_BY_NAME)));
//		WebElement searchByName = webDB.driver.findElement(By.xpath(EmailLogsLocators.NAME_DROPDOWN));
//		actions.moveToElement(searchByName).click().build().perform();
			webDB.driver.findElement(By.xpath(EmailLogsLocators.SEARCH_BY_NAME))
					.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			webDB.sendTextToAnElement(EmailLogsLocators.SEARCH_BY_NAME, Name, DriverBase.ElementType.Xpath);
			Thread.sleep(6000);
		}
		flag = webDB.isElementDisplayed(EmailLogsLocators.NAME_DISPLAY1 + Name + "')]", DriverBase.ElementType.Xpath);
		System.out.println(flag + "Value");
		flag = webDB.isElementDisplayed(EmailLogsLocators.NAME_CELL, DriverBase.ElementType.Xpath);
		if (flag = true) {
			System.out.println("Name is displayed");
		}

		return flag;
	}

	public static boolean verifySearchByOrderID() throws InterruptedException {
		// String orderid = "7959690269273";
		Thread.sleep(2000);
		String OrderID = webDB.driver.findElement(By.xpath("//tbody/tr[1]/th[1]/a[1]")).getText();
		System.out.println(OrderID);
		Thread.sleep(2000);
		webDB.waitForElement(EmailLogsLocators.SEARCH_BY_ORDER, DriverBase.ElementType.Xpath);
		webDB.sendTextToAnElement(EmailLogsLocators.SEARCH_BY_ORDER, OrderID, DriverBase.ElementType.Xpath);
		System.out.println(EmailLogsLocators.ORDERID_COLUMN + "'" + OrderID + "']");
		flag = webDB.waitForElement(EmailLogsLocators.ORDERID_COLUMN + "'" + OrderID + "']",
				DriverBase.ElementType.Xpath);
		if (flag = true) {
			System.out.println("Order is displayed");
		}
		webDB.driver.findElement(By.xpath(EmailLogsLocators.SEARCH_BY_ORDER))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

		return flag;
	}

	public static boolean verifyClearAllButtonOnFilter() throws InterruptedException {
		webDB.waitForElement(EmailLogsLocators.TYPE_DROPDOWN, DriverBase.ElementType.Xpath);
		flag = webDB.clickAnElement(EmailLogsLocators.TYPE_DROPDOWN, DriverBase.ElementType.Xpath);

		List<WebElement> els = webDB.driver.findElements(By.xpath(EmailLogsLocators.ALL_TYPE_CHECKBOX));
		for (WebElement el : els) {
			if (!el.isSelected()) {
				el.click();
			}
		}

		flag = webDB.isElementDisplayed(EmailLogsLocators.CLEAR_ALL_BTN, DriverBase.ElementType.Xpath);
		flag = webDB.clickAnElement(EmailLogsLocators.CLEAR_ALL_BTN, DriverBase.ElementType.Xpath);

		Thread.sleep(1000);
		webDB.waitForElement(EmailLogsLocators.TYPE_DROPDOWN, DriverBase.ElementType.Xpath);
		flag = webDB.clickAnElement(EmailLogsLocators.TYPE_DROPDOWN, DriverBase.ElementType.Xpath);

		if (webDB.driver.findElement(By.xpath(EmailLogsLocators.CLEAR_ALL_BTN)).isEnabled()) {
			System.out.println("Selection Not Cleared");
		} else {
			flag = true;
			System.out.println("Selection Cleared");
		}
		return flag;
	}

	public static boolean verifyStatusColumn() throws InterruptedException {
		flag = webDB.isElementDisplayed(EmailLogsLocators.MESSAGE_LOGS_TABLE_COLUMN2 + "'" + "Status" + "']",
				DriverBase.ElementType.Xpath);
		flag = webDB.isElementDisplayed(EmailLogsLocators.STATUS_CELL, DriverBase.ElementType.Xpath);
		if (flag) {
			System.out.println("Status Cell is displayed");
		}
		return flag;
	}

	public static boolean VerifyTypeOfOrderDisplay() throws InterruptedException {
		webDB.waitForElement(EmailLogsLocators.TYPE_DROPDOWN, DriverBase.ElementType.Xpath);
		flag = webDB.clickAnElement(EmailLogsLocators.TYPE_DROPDOWN, DriverBase.ElementType.Xpath);

		flag = webDB.isElementDisplayed(EmailLogsLocators.TYPE_CHECKBOX + "'" + "Cart Recovery" + "')]",
				DriverBase.ElementType.Xpath);
		flag = webDB.isElementDisplayed(EmailLogsLocators.TYPE_CHECKBOX + "'" + "Cash on Delivery" + "')]",
				DriverBase.ElementType.Xpath);
		flag = webDB.isElementDisplayed(EmailLogsLocators.TYPE_CHECKBOX + "'" + "Order Confirmation" + "')]",
				DriverBase.ElementType.Xpath);
		flag = webDB.isElementDisplayed(EmailLogsLocators.TYPE_CHECKBOX + "'" + "Order Tracking" + "')]",
				DriverBase.ElementType.Xpath);

		if (flag) {
			System.out.println("Type of order checkbox displayed");
		}

		return flag;
	}

	public static boolean VerifyClearButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(EmailLogsLocators.TYPE_DROPDOWN)));
		webDB.waitForElement(EmailLogsLocators.TYPE_DROPDOWN, DriverBase.ElementType.Xpath);
		element = webDB.driver.findElement(By.xpath(EmailLogsLocators.TYPE_DROPDOWN));
		actions.moveToElement(element).click().build().perform();
		flag = !webDB.driver.findElement(By.xpath(EmailLogsLocators.CLEAR_ALL_BTN)).isEnabled();
		if (flag) {
			System.out.println("Clear Button Disabled");
		}
		webDB.waitForElement(EmailLogsLocators.TYPE_CHECKBOX + "'" + "Cart Recovery" + "')]",
				DriverBase.ElementType.Xpath);
		flag = webDB.clickAnElement(EmailLogsLocators.TYPE_CHECKBOX + "'" + "Cart Recovery" + "')]",
				DriverBase.ElementType.Xpath);
		if (flag) {
			System.out.println("Checkbox selected");
		}

		flag = webDB.driver.findElement(By.xpath(EmailLogsLocators.CLEAR_ALL_BTN)).isEnabled();
		if (flag) {
			System.out.println("Clear Button Enabled");
		}

		return flag;
	}

	public static boolean VerifyFilterTextBelowOrderID() throws InterruptedException {
		Dimension d = new Dimension(1920, 1080);
		webDB.driver.manage().window().setSize(d);
		String Name = "Sumit Kumar";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(EmailLogsLocators.NAME_DROPDOWN)));
		element = webDB.driver.findElement(By.xpath(EmailLogsLocators.NAME_DROPDOWN));
		actions.moveToElement(element).click().build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(EmailLogsLocators.SEARCH_BY_NAME)));
		WebElement searchByName = webDB.driver.findElement(By.xpath(EmailLogsLocators.SEARCH_BY_NAME));
		actions.moveToElement(searchByName).click().build().perform();
		webDB.driver.findElement(By.xpath(EmailLogsLocators.SEARCH_BY_NAME))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(EmailLogsLocators.SEARCH_BY_NAME, Name, DriverBase.ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.isElementDisplayed(EmailLogsLocators.FILTER_TAG + "'Name - " + Name + "']",
				DriverBase.ElementType.Xpath);
		if (flag) {
			System.out.println("Name filter tag displayed");
		}
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(EmailLogsLocators.TYPE_DROPDOWN)));
		flag = webDB.clickAnElement(EmailLogsLocators.TYPE_DROPDOWN, DriverBase.ElementType.Xpath);
		Thread.sleep(1000);
		webDB.waitForElement(EmailLogsLocators.TYPE_CHECKBOX + "'" + "Cart Recovery" + "')]",
				DriverBase.ElementType.Xpath);
		flag = webDB.clickAnElement(EmailLogsLocators.TYPE_CHECKBOX + "'" + "Cart Recovery" + "')]",
				DriverBase.ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.isElementDisplayed(EmailLogsLocators.FILTER_TAG + "'" + "CART_RECOVERY" + "']",
				DriverBase.ElementType.Xpath);
		if (flag) {
			System.out.println("Order Type filter tag displayed");
		}

		return flag;
	}

	public static boolean VerifyClearFilterTags() throws InterruptedException {
		Dimension d = new Dimension(1920, 1080);
		webDB.driver.manage().window().setSize(d);
		Thread.sleep(1000);
		String Name = "Sumit Kumar";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(EmailLogsLocators.NAME_DROPDOWN)));
		element = webDB.driver.findElement(By.xpath(EmailLogsLocators.NAME_DROPDOWN));
		actions.moveToElement(element).click().build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(EmailLogsLocators.SEARCH_BY_NAME)));
		webDB.waitForElement(EmailLogsLocators.SEARCH_BY_NAME, DriverBase.ElementType.Xpath);
		webDB.driver.findElement(By.xpath(EmailLogsLocators.SEARCH_BY_NAME))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(EmailLogsLocators.SEARCH_BY_NAME, Name, DriverBase.ElementType.Xpath);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(EmailLogsLocators.FILTER_TAG_CLOSE_BTN)));
		flag = webDB.clickAnElement(EmailLogsLocators.FILTER_TAG_CLOSE_BTN, DriverBase.ElementType.Xpath);
		if (flag) {
			System.out.println("Filter Tag cleared");
		}
		return flag;
	}

	public static boolean VerifyFilterResult() throws InterruptedException {
		Dimension d = new Dimension(1920, 1080);
		webDB.driver.manage().window().setSize(d);
		Thread.sleep(1000);
//		String OrderID = "4685101859067";
		String Name = "Sumit Kumar";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(EmailLogsLocators.NAME_DROPDOWN)));
		element = webDB.driver.findElement(By.xpath(EmailLogsLocators.NAME_DROPDOWN));
		actions.moveToElement(element).click().build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(EmailLogsLocators.SEARCH_BY_NAME)));
		WebElement searchByName = webDB.driver.findElement(By.xpath(EmailLogsLocators.SEARCH_BY_NAME));
		actions.moveToElement(searchByName).click().build().perform();
		webDB.driver.findElement(By.xpath(EmailLogsLocators.SEARCH_BY_NAME))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(EmailLogsLocators.SEARCH_BY_NAME, Name, DriverBase.ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.isElementDisplayed(EmailLogsLocators.NAME_DISPLAY1 + Name + "')]", DriverBase.ElementType.Xpath);
		flag = webDB.isElementDisplayed(EmailLogsLocators.NAME_CELL, DriverBase.ElementType.Xpath);
		if (flag = true) {
			System.out.println("Name is displayed");
		}
		webDB.driver.findElement(By.xpath(EmailLogsLocators.SEARCH_BY_NAME))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

		String orderid = webDB.driver.findElement(By.xpath("//tbody/tr[1]/th[1]/a[1]")).getText();
		Thread.sleep(2000);
		webDB.waitForElement(EmailLogsLocators.SEARCH_BY_ORDER, DriverBase.ElementType.Xpath);
		webDB.sendTextToAnElement(EmailLogsLocators.SEARCH_BY_ORDER, orderid, DriverBase.ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.isElementDisplayed(EmailLogsLocators.ORDERID_COLUMN + "'" + orderid + "']",
				DriverBase.ElementType.Xpath);
		if (flag = true) {
			System.out.println("Order is displayed");
		}

		webDB.driver.findElement(By.xpath(EmailLogsLocators.SEARCH_BY_ORDER))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

		return flag;
	}

	public static boolean ClearSearchByNameTxtbox() throws InterruptedException {
		Dimension d = new Dimension(1920, 1080);
		webDB.driver.manage().window().setSize(d);
		Thread.sleep(1000);
		String Name = "Sumit Kumar";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(EmailLogsLocators.NAME_DROPDOWN)));
		element = webDB.driver.findElement(By.xpath(EmailLogsLocators.NAME_DROPDOWN));
		actions.moveToElement(element).click().build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(EmailLogsLocators.SEARCH_BY_NAME)));
		webDB.waitForElement(EmailLogsLocators.SEARCH_BY_NAME, DriverBase.ElementType.Xpath);
		webDB.driver.findElement(By.xpath(EmailLogsLocators.SEARCH_BY_NAME))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(EmailLogsLocators.SEARCH_BY_NAME, Name, DriverBase.ElementType.Xpath);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(EmailLogsLocators.CLEAR_NAME_BTN)));
		flag = webDB.isElementDisplayed(EmailLogsLocators.CLEAR_NAME_BTN, DriverBase.ElementType.Xpath);
		flag = webDB.clickAnElement(EmailLogsLocators.CLEAR_NAME_BTN, DriverBase.ElementType.Xpath);
		Thread.sleep(1000);
		webDB.waitForElement(EmailLogsLocators.NAME_DROPDOWN, DriverBase.ElementType.Xpath);
		flag = webDB.clickAnElement(EmailLogsLocators.NAME_DROPDOWN, DriverBase.ElementType.Xpath);
		text = webDB.driver.findElement(By.xpath(EmailLogsLocators.SEARCH_BY_NAME)).getText();
		flag = text.equalsIgnoreCase("");
		if (flag) {
			System.out.println("Name field cleared");
		}
		return flag;
	}

	public static boolean VerifyClearSearchByNameTxtboxEnableDisable() throws InterruptedException {
		Dimension d = new Dimension(1920, 1080);
		webDB.driver.manage().window().setSize(d);
		Thread.sleep(1000);
		String Name = "Sumit Kumar";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(EmailLogsLocators.NAME_DROPDOWN)));
		element = webDB.driver.findElement(By.xpath(EmailLogsLocators.NAME_DROPDOWN));
		actions.moveToElement(element).click().build().perform();
		Thread.sleep(1000);
		flag = !webDB.driver.findElement(By.xpath(EmailLogsLocators.SEARCH_BY_NAME)).isEnabled();
		webDB.waitForElement(EmailLogsLocators.SEARCH_BY_NAME, DriverBase.ElementType.Xpath);
		webDB.driver.findElement(By.xpath(EmailLogsLocators.SEARCH_BY_NAME))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(EmailLogsLocators.SEARCH_BY_NAME, Name, DriverBase.ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.driver.findElement(By.xpath(EmailLogsLocators.SEARCH_BY_NAME)).isEnabled();

		return flag;
	}

	public static boolean VerifyOrderSorting() throws InterruptedException {
		flag = webDB.clickAnElement(EmailLogsLocators.MESSAGE_LOGS_TABLE_COLUMN + "'" + "Order" + "']",
				DriverBase.ElementType.Xpath);
		Thread.sleep(1000);
		System.out.print("Order ");
		Asc_Desc_Sorted("th");
		return flag;
	}

	public static boolean VerifyNameSorting() throws InterruptedException {
		flag = webDB.clickAnElement(EmailLogsLocators.MESSAGE_LOGS_TABLE_COLUMN + "'" + "Name" + "']",
				DriverBase.ElementType.Xpath);
		Thread.sleep(1500);
		System.out.print("Name ");
		Asc_Desc_Sorted("td[1]");
		return flag;
	}

	public static boolean VerifyAmountSorting() throws InterruptedException {
		flag = webDB.clickAnElement(EmailLogsLocators.MESSAGE_LOGS_TABLE_COLUMN + "'" + "Amount" + "']",
				DriverBase.ElementType.Xpath);
		Thread.sleep(1000);
		System.out.print("Amount ");
		Asc_Desc_Sorted("td[2]");
		return flag;
	}

	public static void Asc_Desc_Sorted(String param) throws InterruptedException {
		ArrayList<String> sortedList = new ArrayList<>();
		List<WebElement> elementList1 = webDB.driver.findElements(By.xpath(EmailLogsLocators.TABLE_DATA + param));
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
		List<WebElement> rows = webDB.driver.findElements(By.xpath(EmailLogsLocators.DATA_TABLE_ROW));
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
		String FirstPageRow = webDB.driver.findElement(By.xpath(EmailLogsLocators.FIRST_ROW)).getText();

		Thread.sleep(1000);
		flag = webDB.driver.findElement(By.xpath(EmailLogsLocators.NAV_NEXT)).isEnabled();
		if (flag) {
			webDB.waitForElement(EmailLogsLocators.NAV_NEXT, DriverBase.ElementType.Xpath);
			flag = webDB.clickAnElement(EmailLogsLocators.NAV_NEXT, DriverBase.ElementType.Xpath);
			System.out.println("Navigation next clicked");

			Thread.sleep(1000);
			String NextPageRow = webDB.driver.findElement(By.xpath(EmailLogsLocators.FIRST_ROW)).getText();

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
		String TIMESTAMP = webDB.driver.findElement(By.xpath(EmailLogsLocators.TIMESTAMP_FIRST_ROW)).getText();

		SimpleDateFormat format = new SimpleDateFormat("MMMMM d,y h:mm a");
		;

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
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(EmailLogsLocators.SEARCH_BY_ORDER)));
		flag = webDB.isElementDisplayed(EmailLogsLocators.MESSAGE_LOGS_TABLE_COLUMN + "'" + "Amount" + "']",
				DriverBase.ElementType.Xpath);
		if (flag) {
			flag = webDB.isElementDisplayed(EmailLogsLocators.CURRENCY_CELL, DriverBase.ElementType.Xpath);
			if (flag) {
				System.out.println("Amount cell is displayed");
			}
		}
		return flag;
	}

	public static boolean VerifyLatestSortedTimestamp() throws InterruptedException, ParseException {
		elements = webDB.driver.findElements(By.xpath(EmailLogsLocators.TABLE_ROWS));

		if (elements.size() > 1) {
			String TIMESTAMP1 = webDB.driver.findElement(By.xpath(EmailLogsLocators.ATTEMPTED_CELL + "[1]" + "/td[5]"))
					.getText();
			System.out.println(TIMESTAMP1);
			String TIMESTAMP2 = webDB.driver
					.findElement(By.xpath(EmailLogsLocators.ATTEMPTED_CELL + "[" + elements.size() + "]" + "/td[5]"))
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
		Dimension d = new Dimension(1920, 1000);
		webDB.driver.manage().window().setSize(d);
		ShopifyStore.POIaddItemAndCheckout();

		// Enter shipment details
		ShopifyStore.enterShippingDetailsWithPhone();
		phone = phone;

		Thread.sleep(1000);
		// Enter card details
		ShopifyStore.enterCardDetails();

		// Click on Pay Now
		webDB.clickAnElement(ShopifyStoreLocators.CONTINUE_BTN, DriverBase.ElementType.Xpath);

		Thread.sleep(500);
		// Verify thankyou page
		webDB.waitForElement(ShopifyStoreLocators.THANKYOU_MSG, DriverBase.ElementType.Xpath);
		flag = webDB.isElementDisplayed(ShopifyStoreLocators.THANKYOU_MSG, DriverBase.ElementType.Xpath);
		String Address;
		if (flag) {
			text = webDB.driver.findElement(By.xpath(ShopifyStoreLocators.THANKYOU_MSG)).getText();
			System.out.println(text);
			Address = webDB.driver.findElement(By.xpath(ShopifyStoreLocators.NAME_IN_ADDRESS)).getText();
			String[] AddressArr = Address.split("\n");
			System.out.println(AddressArr[0]);
			Name = AddressArr[0];
			if (text.contains(Name)) {
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
		it.next();
		webDB.driver.close();
		// Move to Message logs page
		webDB.driver.switchTo().window(parentWindow);

		CommonFunctions.verifyEmailLogsPage();

		// Filter name and search
		Thread.sleep(3000);
		//		webDB.driver.manage().window().setSize(d);
		webDB.waitForElement(EmailLogsLocators.NAME_DROPDOWN, DriverBase.ElementType.Xpath);
		webDB.clickAnElement(EmailLogsLocators.NAME_DROPDOWN, ElementType.Xpath);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(EmailLogsLocators.SEARCH_BY_NAME)));
//		webDB.waitForElement("(//label[contains(text(),'Search by name')])", ElementType.Xpath);
		webDB.clearText(EmailLogsLocators.SEARCH_BY_NAME);
		webDB.sendTextToAnElement(EmailLogsLocators.SEARCH_BY_NAME, Name, DriverBase.ElementType.Xpath);
		Thread.sleep(4000);
		text = webDB.driver.findElement(By.xpath(EmailLogsLocators.NAME_CELL)).getText().toString();
		System.out.println("Text = " + text);
		if (Name.equalsIgnoreCase(text)) {
			Thread.sleep(3000);
			text = webDB.driver.findElement(By.xpath(EmailLogsLocators.TYPE_CELL)).getText();
			if (text.equalsIgnoreCase("Order Confirmation")) {
				System.out.println("2nd Text = " + text);
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
		CommonFunctions.verifyEmailLogsPage();

		// click on order ID
		webDB.waitForElement(EmailLogsLocators.ORDERID_CELL, DriverBase.ElementType.Xpath);
		flag = webDB.clickAnElement(EmailLogsLocators.ORDERID_CELL, DriverBase.ElementType.Xpath);

		flag = webDB.isElementDisplayed(ShopifyStoreLocators.SHOPIFY_EMAIL, DriverBase.ElementType.Id);
		if (flag) {
			webDB.sendTextToAnElement(ShopifyStoreLocators.SHOPIFY_EMAIL, EMAIL, DriverBase.ElementType.Id);
			flag = webDB.clickAnElement(ShopifyStoreLocators.SHOPIFY_NEXT_BTN, DriverBase.ElementType.Xpath);

			flag = webDB.isElementDisplayed(ShopifyStoreLocators.SHOPIFY_PASSWORD, DriverBase.ElementType.Xpath);
			webDB.sendTextToAnElement(ShopifyStoreLocators.SHOPIFY_PASSWORD, PASSWORD, DriverBase.ElementType.Id);

			flag = webDB.driver.findElement(By.xpath(ShopifyStoreLocators.SHOPIFY_LOGIN_BTN)).isEnabled();
			if (flag) {
				flag = webDB.clickAnElement(ShopifyStoreLocators.SHOPIFY_LOGIN_BTN, DriverBase.ElementType.Xpath);
				flag = webDB.isElementDisplayed(ShopifyStoreLocators.USERNAME_LABEL, DriverBase.ElementType.Xpath);
			} else {
				flag = false;
			}

		} else {
			flag = false;
		}

		return flag;
	}

	public static boolean verifyfilterforcolumns() throws InterruptedException {
		Thread.sleep(1000);
		String orderid = webDB.driver.findElement(By.xpath("//tbody/tr[1]/th[1]/a[1]")).getText();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(EmailLogsLocators.SEARCH_BY_ORDER)));
		element = webDB.driver.findElement(By.xpath(EmailLogsLocators.SEARCH_BY_ORDER));
		actions.moveToElement(element).click().build().perform();
		webDB.driver.findElement(By.xpath(EmailLogsLocators.SEARCH_BY_ORDER))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.sendTextToAnElement(EmailLogsLocators.SEARCH_BY_ORDER, orderid, DriverBase.ElementType.Xpath);
		flag = webDB.isElementDisplayed(EmailLogsLocators.ORDERID_COLUMN + "'" + orderid + "']",
				DriverBase.ElementType.Xpath);
		if (flag = true) {
			log.info("Order is displayed");
		}
		webDB.driver.findElement(By.xpath(EmailLogsLocators.SEARCH_BY_ORDER))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Thread.sleep(1200);
		String Name = "AutoUser";
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			webDB.clickAnElement(CommonLocators.MOREFILTERSBTN, ElementType.Xpath);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(EmailLogsLocators.NAME_DROPDOWN)));
			flag = webDB.clickAnElement(EmailLogsLocators.NAME_DROPDOWN, DriverBase.ElementType.Xpath);
			webDB.waitForElement(EmailLogsLocators.SEARCH_BY_NAME, DriverBase.ElementType.Xpath);
			webDB.driver.findElement(By.xpath(EmailLogsLocators.SEARCH_BY_NAME))
					.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			webDB.sendTextToAnElement(EmailLogsLocators.SEARCH_BY_NAME, Name, DriverBase.ElementType.Xpath);
			webDB.clickAnElement(CommonLocators.DONEBTN, ElementType.Xpath);
		} else {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(EmailLogsLocators.NAME_DROPDOWN)));
			flag = webDB.clickAnElement(EmailLogsLocators.NAME_DROPDOWN, DriverBase.ElementType.Xpath);
			webDB.waitForElement(EmailLogsLocators.SEARCH_BY_NAME, DriverBase.ElementType.Xpath);
			webDB.driver.findElement(By.xpath(EmailLogsLocators.SEARCH_BY_NAME))
					.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			webDB.sendTextToAnElement(EmailLogsLocators.SEARCH_BY_NAME, Name, DriverBase.ElementType.Xpath);
			Thread.sleep(1000);
		}
		flag = webDB.isElementDisplayed(EmailLogsLocators.NAME_DISPLAY1 + Name + "')]", DriverBase.ElementType.Xpath);
		flag = webDB.isElementDisplayed(EmailLogsLocators.NAME_CELL, DriverBase.ElementType.Xpath);
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
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(EmailLogsLocators.TYPE_DROPDOWN)));
			flag = webDB.clickAnElement(EmailLogsLocators.TYPE_DROPDOWN, DriverBase.ElementType.Xpath);

			// Order Confirmation
			// Cash on Delivery
			webDB.waitForElement(EmailLogsLocators.TYPE_CHECKBOX + "'" + TypeCheckbox + "')]",
					DriverBase.ElementType.Xpath);
			flag = webDB.clickAnElement(EmailLogsLocators.TYPE_CHECKBOX + "'" + TypeCheckbox + "')]",
					DriverBase.ElementType.Xpath);
			flag = webDB.isElementDisplayed(EmailLogsLocators.TYPE_CHECKBOX + "'" + TypeCheckbox + "')]",
					DriverBase.ElementType.Xpath);
		}
		if (flag = true) {
			log.info("Order Type displayed");
		}
		return flag;
	}

}
