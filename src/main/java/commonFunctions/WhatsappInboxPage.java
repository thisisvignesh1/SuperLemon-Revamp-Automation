package commonFunctions;

import java.util.Properties;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import locators.AnalyticsLocators;
import locators.AutomationLocators;
import locators.CommonLocators;
import locators.WhatsappInboxLocators;
import utils.DriverBase;
import utils.DriverBase.ElementType;

public class WhatsappInboxPage {
	private static Logger log = Logger.getLogger(WhatsappExtensionPage.class);
	private static String propertyFilePath;
	private static boolean flag;
	public static Properties prop = new Properties();
	private static DriverBase webDB = new DriverBase();
	private static WebDriverWait wait = new WebDriverWait(webDB.driver, 10);
	private static JavascriptExecutor js = (JavascriptExecutor) webDB.driver;
	private static Select category;
	private static Actions actions = new Actions(webDB.driver);
	private static WebElement element;
	private static String tagName = "AutoTagName" + RandomStringUtils.randomNumeric(2);

	public static boolean verifyWhatsappConfigurationPageOptions() {
		flag = webDB.waitForElement(WhatsappInboxLocators.CONFIGURATION_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WhatsappInboxLocators.CONFIGURATION_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(WhatsappInboxLocators.CONFIGURATION_HEADING, ElementType.Xpath);
			if (flag) {
				webDB.isElementDisplayed(WhatsappInboxLocators.CONFIGURATION_HEADING, ElementType.Xpath);
				log.info("Clicked on Configuration page");
				flag = webDB.waitForElement(WhatsappInboxLocators.TAGS_TAB, ElementType.Xpath);
				if (flag) {
					webDB.isElementDisplayed(WhatsappInboxLocators.TAGS_TAB, ElementType.Xpath);
					log.info("Tags section is visible");
					flag = webDB.waitForElement(WhatsappInboxLocators.QUICK_RESPONSE_TAB, ElementType.Xpath);
					if (flag) {
						webDB.isElementDisplayed(WhatsappInboxLocators.QUICK_RESPONSE_TAB, ElementType.Xpath);
						log.info("Quick Response section is visible");
						flag = webDB.waitForElement(WhatsappInboxLocators.SET_WORKING_HOURS_TAB, ElementType.Xpath);
						if (flag) {
							webDB.isElementDisplayed(WhatsappInboxLocators.SET_WORKING_HOURS_TAB, ElementType.Xpath);
							log.info("Set Working Hours section is visible");
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyCreatedTagsAreListed() throws InterruptedException {
		flag = webDB.waitForElement(WhatsappInboxLocators.CONFIGURATION_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WhatsappInboxLocators.CONFIGURATION_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(WhatsappInboxLocators.CONFIGURATION_HEADING, ElementType.Xpath);
			if (flag) {
				log.info("Clicked on Configuration page");
				flag = webDB.waitForElement(WhatsappInboxLocators.TAGS_TAB, ElementType.Xpath);
				if (flag) {
					log.info("Tags section is visible");
					flag = webDB.waitForElement(WhatsappInboxLocators.ADD_NEW_TAG_BUTTON, ElementType.Xpath);
					if (flag) {
						log.info("Add new tag button is visible");
						webDB.clickAnElement(WhatsappInboxLocators.ADD_NEW_TAG_BUTTON, ElementType.Xpath);
						flag = webDB.waitForElement(WhatsappInboxLocators.POPUP, ElementType.Xpath);
						if (flag) {
							log.info("Add new tag pop up is visible");
							flag = webDB.waitForElement(WhatsappInboxLocators.TAG_NAME_TEXT_FIELD, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(WhatsappInboxLocators.TAG_NAME_TEXT_FIELD, ElementType.Xpath);
								webDB.sendTextToAnElement(WhatsappInboxLocators.TAG_NAME_TEXT_FIELD, tagName,
										ElementType.Xpath);
								flag = webDB.waitForElement(WhatsappInboxLocators.SAVE_TAG, ElementType.Xpath);
								if (flag) {
									log.info("Save tag button is visible");
									webDB.clickAnElement(WhatsappInboxLocators.SAVE_TAG, ElementType.Xpath);
									Thread.sleep(2000);
//									flag = webDB.waitForElement("//div[@class='Polaris-Stack__Item']//p[contains(text(),'"+tagName+"')]", ElementType.Xpath);
									String lastTagName = webDB.getText(WhatsappInboxLocators.LAST_ROW_TAG_NAME,
											ElementType.Xpath);
									log.info("The tagName is " + lastTagName);
									if (lastTagName.equalsIgnoreCase(tagName)) {
										log.info("Created Tag Name is visible in last row");
									} else {
										log.info("Tag Name is not visible");
										flag = false;
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

	public static boolean verifyTagsTabHasThreeColumns() {
		flag = webDB.waitForElement(WhatsappInboxLocators.CONFIGURATION_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WhatsappInboxLocators.CONFIGURATION_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(WhatsappInboxLocators.CONFIGURATION_HEADING, ElementType.Xpath);
			if (flag) {
				log.info("Clicked on Configuration page");
				flag = webDB.waitForElement(WhatsappInboxLocators.TAGS_TAB, ElementType.Xpath);
				if (flag) {
					log.info("Tags tab is visible");
					webDB.clickAnElement(WhatsappInboxLocators.TAGS_TAB, ElementType.Xpath);
					flag = webDB.waitForElement(WhatsappInboxLocators.NAME_COLUMN, ElementType.Xpath);
					if (flag) {
						webDB.isElementDisplayed(WhatsappInboxLocators.NAME_COLUMN, ElementType.Xpath);
						log.info("Name column is visible.");
						flag = webDB.waitForElement(WhatsappInboxLocators.LAST_UPDATED_TIME_COLUMN, ElementType.Xpath);
						if (flag) {
							webDB.isElementDisplayed(WhatsappInboxLocators.LAST_UPDATED_TIME_COLUMN, ElementType.Xpath);
							log.info("Last updated time is visible");
							flag = webDB.waitForElement(WhatsappInboxLocators.ACTIONS_COLUMN, ElementType.Xpath);
							if (flag) {
								webDB.isElementDisplayed(WhatsappInboxLocators.ACTIONS_COLUMN, ElementType.Xpath);
								log.info("Actions column is visible");
							}
						}
					}
				}
			}
		}

		return flag;
	}

	public static boolean verifyEditDeleteTags() throws InterruptedException {
		flag = webDB.waitForElement(WhatsappInboxLocators.CONFIGURATION_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(WhatsappInboxLocators.CONFIGURATION_TAB, ElementType.Xpath);
			flag = webDB.waitForElement(WhatsappInboxLocators.CONFIGURATION_HEADING, ElementType.Xpath);
			if (flag) {
				log.info("Clicked on Configuration page");
				flag = webDB.waitForElement(WhatsappInboxLocators.TAGS_TAB, ElementType.Xpath);
				if (flag) {
					log.info("Tags tab is visible");
					webDB.clickAnElement(WhatsappInboxLocators.TAGS_TAB, ElementType.Xpath);
					flag = webDB.waitForElement(WhatsappInboxLocators.FIRST_TAG_EDIT_ICON, ElementType.Xpath);
					if (flag) {
						log.info("Tag edit icon is visible");
						webDB.clickAnElement(WhatsappInboxLocators.FIRST_TAG_EDIT_ICON, ElementType.Xpath);
						flag = webDB.waitForElement(WhatsappInboxLocators.POPUP, ElementType.Xpath);
						if (flag) {
							log.info("Popup is visible");
							flag = webDB.waitForElement(WhatsappInboxLocators.TAG_NAME_TEXT_FIELD, ElementType.Xpath);
							if (flag) {
								webDB.clearText(WhatsappInboxLocators.TAG_NAME_TEXT_FIELD);
								webDB.sendTextToAnElement(WhatsappInboxLocators.TAG_NAME_TEXT_FIELD, tagName + "Edited",
										ElementType.Xpath);
								String editedName = tagName + "Edited";
								flag = webDB.waitForElement(WhatsappInboxLocators.UPDATE_EDIT_TAG_BUTTON,
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(WhatsappInboxLocators.UPDATE_EDIT_TAG_BUTTON,
											ElementType.Xpath);
									log.info("Tag name edited");
									Thread.sleep(2000);
									flag = webDB.waitForElement(WhatsappInboxLocators.FIRST_TAG_NAME,
											ElementType.Xpath);
									if (flag) {
										String EditedTagName = webDB.getText(WhatsappInboxLocators.FIRST_TAG_NAME,
												ElementType.Xpath);
										log.info("Edited Tag Name is " + EditedTagName);
										if (editedName.equalsIgnoreCase(EditedTagName)) {
											log.info("Changes reflected on table");
										} else {
											log.info("Changes are not refected on table");
											flag = false;
										}
										flag = webDB.waitForElement(WhatsappInboxLocators.FIRST_DELETE_ICON,
												ElementType.Xpath);
										if (flag) {
											log.info("Delete icon is visible");
											webDB.isElementDisplayed(WhatsappInboxLocators.FIRST_DELETE_ICON,
													ElementType.Xpath);
											webDB.clickAnElement(WhatsappInboxLocators.FIRST_DELETE_ICON,
													ElementType.Xpath);
											flag = webDB.waitForElement(WhatsappInboxLocators.POPUP, ElementType.Xpath);
											if (flag) {
												log.info("Delete pop up is visible");
												webDB.waitForElement(WhatsappInboxLocators.DELETE_POPUP_YES,
														ElementType.Xpath);
												webDB.clickAnElement(WhatsappInboxLocators.DELETE_POPUP_YES,
														ElementType.Xpath);
												Thread.sleep(2000);
												flag = webDB.waitForElement(WhatsappInboxLocators.FIRST_TAG_NAME,
														ElementType.Xpath);
												if (flag) {
													log.info("Checking stared with first name in list");
													String name = webDB.getText(WhatsappInboxLocators.FIRST_TAG_NAME,
															ElementType.Xpath);
													log.info("Name is " + name);
													if (name.equalsIgnoreCase(EditedTagName)) {
														log.info("Tag is not deleted");
														flag = false;
													} else {
														log.info("Tag deleted successfully");
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

}
