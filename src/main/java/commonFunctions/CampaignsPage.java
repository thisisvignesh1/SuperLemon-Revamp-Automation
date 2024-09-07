package commonFunctions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import locators.AutomationLocators;
import locators.CampaignsLocators;
import locators.CommonLocators;
import utils.DriverBase;
import utils.DriverBase.ElementType;

/**
 *
 * @author Praveen Kumar
 * @date September 09, 2022
 *
 */
public class CampaignsPage {
	private static Logger log = Logger.getLogger(CampaignsPage.class.getName());
	public static String random = RandomStringUtils.randomNumeric(2);
	private static boolean flag;
	private static String propertyFilePath;
	public static Properties prop = new Properties();
	private static DriverBase webDB = new DriverBase();
	private static WebDriverWait wait = new WebDriverWait(webDB.driver, 10);
	private static Actions actions = new Actions(webDB.driver);
	public static XSSFWorkbook wb;
	public static int rowCount;
	public static String[][] data = null;
	public static String output_filename;
	public static String filepath;
	public static String Text;
	public static int columnCount;
	public static FileOutputStream outputStream;
	private static JavascriptExecutor js = (JavascriptExecutor) webDB.driver;

	public static boolean verifyInputMarketCampign() throws InterruptedException {
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			flag = webDB.waitForElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			if (flag) {
				Thread.sleep(1500);
				webDB.clickAnElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			}
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CampaignsLocators.MARKETCAMPAIGNS_TAB)));
		webDB.clickAnElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, DriverBase.ElementType.Xpath);
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			webDB.clickAnElement(CommonLocators.CLOSEICON, ElementType.Xpath);
		}
		flag = webDB.waitForElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			webDB.javaScriptClickAnElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
			flag = webDB.isElementDisplayed(CampaignsLocators.CAMPAIGNSNAME_INPUT, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				flag = webDB.isElementDisplayed(CampaignsLocators.SELECTTEMPLATE, ElementType.Xpath);
				if (flag) {
					Thread.sleep(2500);
					webDB.clickAnElement(CampaignsLocators.FROMFILE, ElementType.CssSelector);
					flag = webDB.isElementDisplayed(CampaignsLocators.RECEIPTENTSNAME, ElementType.CssSelector);
					if (flag) {
						Thread.sleep(2000);
						flag = webDB.clickAnElement(CampaignsLocators.DOWNLOADEXCELBUTTON,
								DriverBase.ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(CampaignsLocators.SELECTTEMPLATE, ElementType.Xpath);
							Thread.sleep(2000);
							webDB.clickAnElement(CampaignsLocators.CHOOSETEMPLATEBTN, ElementType.Xpath);
							Thread.sleep(2000);
							flag = webDB.isElementDisplayed(CampaignsLocators.TEMPLATENAME, ElementType.Xpath);
							if (flag) {
								Thread.sleep(2000);
								webDB.scrollToAnElement(CampaignsLocators.PREVIEWTEMPLATE);
								Thread.sleep(2000);
								flag = webDB.isElementDisplayed(CampaignsLocators.PREVIEWTEMPLATE,
										ElementType.CssSelector);
								if (flag) {
									flag = webDB.clickAnElement(CampaignsLocators.DOWNLOADCSVBUTTON, ElementType.Xpath);
									System.out.println("Verified Input Campaign");
								}
							}
						}
					}
				}
			}
		}
		return flag;

	}

	@SuppressWarnings("static-access")
	public static boolean verifyInvalidInputMarketCampign() throws InterruptedException {
		webDB.driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CampaignsLocators.MARKETCAMPAIGNS_TAB)));
		webDB.clickAnElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
		Thread.sleep(2000);
		webDB.moveToElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
		flag = webDB.waitForElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			webDB.javaScriptClickAnElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
			flag = webDB.waitForElement(CampaignsLocators.CAMPAIGNSNAME_INPUT, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				webDB.clickAnElement(CampaignsLocators.SELECTTEMPLATE, ElementType.Xpath);
				Thread.sleep(2000);
				webDB.clickAnElement(CampaignsLocators.CHOOSETEMPLATEBTN, ElementType.Xpath);
				webDB.clickAnElement(CampaignsLocators.SUBMITBTN, ElementType.Xpath);
				flag = webDB.isElementDisplayed(CampaignsLocators.ERRORPOP, ElementType.Xpath);
				if (flag) {
					Thread.sleep(500);
					String text = webDB.getText(CampaignsLocators.ERRORPOP, ElementType.Xpath);
					System.out.println(text);
					flag = text.contains(CampaignsLocators.CAMPAIGNSINPUTPOPUP);
					if (flag) {
						Thread.sleep(1500);
						webDB.clickAnElement(CampaignsLocators.CANCELBTN, ElementType.Xpath);
						Thread.sleep(1500);
						webDB.javaScriptClickAnElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
						flag = webDB.waitForElement(CampaignsLocators.CAMPAIGNSNAME_INPUT, ElementType.Xpath);
						if (flag) {
							Thread.sleep(2000);
							propertyFilePath = (System.getProperty("user.dir") + File.separator + "payload"
									+ File.separator + CampaignsLocators.CAMPAIGNSFILENAMEVALUE);
							webDB.sendTextToAnElement(CampaignsLocators.CAMPAIGNSNAME_INPUT,
									CampaignsLocators.CAMPAIGNSINPUTVALUE + random, ElementType.Xpath);
							Thread.sleep(2500);
							webDB.clickAnElement(CampaignsLocators.FROMFILE, ElementType.CssSelector);
							Thread.sleep(2000);
							webDB.sendTextToAnElement(CampaignsLocators.RECEIPTENTSNAME, propertyFilePath,
									ElementType.CssSelector);
							webDB.clickAnElement(CampaignsLocators.SUBMITBTN, ElementType.Xpath);
							flag = webDB.isElementDisplayed(CampaignsLocators.ERRORPOP, ElementType.Xpath);
							if (flag) {

								Thread.sleep(500);
								String popuptext = webDB.getText(CampaignsLocators.ERRORPOP, ElementType.Xpath);
								System.out.println(popuptext);
								flag = popuptext.contains(CampaignsLocators.SELECTTEMPLATEPOPUP);
								if (flag) {
									Thread.sleep(2000);
									webDB.clickAnElement(CampaignsLocators.CANCELBTN, ElementType.Xpath);
									Thread.sleep(2000);
									webDB.javaScriptClickAnElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN,
											ElementType.Xpath);
									webDB.clearText(CampaignsLocators.CAMPAIGNSNAME_INPUT);
									webDB.sendTextToAnElement(CampaignsLocators.CAMPAIGNSNAME_INPUT,
											CampaignsLocators.CAMPAIGNSINVALIDINPUTVALUE, ElementType.Xpath);
									flag = webDB.isElementDisplayed(CampaignsLocators.CAMPAIGNSNAME_INPUTERROR,
											ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(CampaignsLocators.CAMPAIGNSNAME_INPUT, ElementType.Xpath);
										webDB.clearText(CampaignsLocators.CAMPAIGNSNAME_INPUT);
										Thread.sleep(2000);
										webDB.sendTextToAnElement(CampaignsLocators.CAMPAIGNSNAME_INPUT,
												CampaignsLocators.CAMPAIGNSINPUTVALUE + random, ElementType.Xpath);
										webDB.clickAnElement(CampaignsLocators.SELECTTEMPLATE, ElementType.Xpath);
										Thread.sleep(2000);
										webDB.clickAnElement(CampaignsLocators.CHOOSETEMPLATEBTN, ElementType.Xpath);
										webDB.clickAnElement(CampaignsLocators.FROMFILE, ElementType.CssSelector);
										Thread.sleep(2000);
										flag = webDB.waitForElement(CampaignsLocators.SUBMITBTN, ElementType.Xpath);
										if (flag) {
											Thread.sleep(2000);
											webDB.clickAnElement(CampaignsLocators.SUBMITBTN, ElementType.Xpath);
											flag = webDB.isElementDisplayed(CampaignsLocators.ERRORPOP,
													ElementType.Xpath);
											if (flag) {
												Thread.sleep(500);
												String popup = webDB.getText(CampaignsLocators.ERRORPOP,
														ElementType.Xpath);
												System.out.println(popup);
												flag = popup.contains(CampaignsLocators.RECEPTENTSINPUTPOPUP);
												if (flag) {
													Thread.sleep(2000);
													webDB.clickAnElement(CampaignsLocators.CANCELBTN,
															ElementType.Xpath);
													webDB.javaScriptClickAnElement(
															CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN,
															ElementType.Xpath);
													flag = webDB.waitForElement(CampaignsLocators.CAMPAIGNSNAME_INPUT,
															ElementType.Xpath);
													if (flag) {
														Thread.sleep(2000);
														propertyFilePath = (System.getProperty("user.dir")
																+ File.separator + "payload" + File.separator
																+ CampaignsLocators.CAMPAIGNSINVALIDFILENAMEVALUE);
														webDB.sendTextToAnElement(CampaignsLocators.CAMPAIGNSNAME_INPUT,
																CampaignsLocators.CAMPAIGNSINPUTVALUE + random,
																ElementType.Xpath);
														Thread.sleep(1500);
														webDB.clickAnElement(CampaignsLocators.FROMFILE,
																ElementType.CssSelector);
														Thread.sleep(2000);
														webDB.sendTextToAnElement(CampaignsLocators.RECEIPTENTSNAME,
																propertyFilePath, ElementType.CssSelector);
														Thread.sleep(1500);
														webDB.clickAnElement(CampaignsLocators.SELECTTEMPLATE,
																ElementType.Xpath);
														Thread.sleep(2000);
														webDB.clickAnElement(CampaignsLocators.CHOOSETEMPLATEBTN,
																ElementType.Xpath);
														Thread.sleep(2000);
														flag = webDB.waitForElement(CampaignsLocators.SUBMITBTN,
																ElementType.Xpath);
														if (flag) {
															Thread.sleep(2000);
															webDB.clickAnElement(CampaignsLocators.SUBMITBTN,
																	ElementType.Xpath);
															Thread.sleep(2500);
															flag = webDB.isElementDisplayed(CampaignsLocators.ERRORPOP,
																	ElementType.Xpath);
															if (flag) {
																String popup1 = webDB.getText(
																		CampaignsLocators.ERRORPOP, ElementType.Xpath);
																System.out.println(popup1);
																flag = popup1.contains(
																		CampaignsLocators.SELECTCONFIRMBTN_POPUP);
																if (flag) {
																	log.info(
																			"Verified warning message without selecting checkbox");
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

	@SuppressWarnings("static-access")
	public static boolean verifyCreateMarketCampign() throws InterruptedException {
		webDB.driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CampaignsLocators.MARKETCAMPAIGNS_TAB)));
		webDB.clickAnElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
		Thread.sleep(5000);
		flag = webDB.waitForElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			webDB.javaScriptClickAnElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
			flag = webDB.waitForElement(CampaignsLocators.CAMPAIGNSNAME_INPUT, ElementType.Xpath);
			if (flag) {
				webDB.clearText(CampaignsLocators.CAMPAIGNSNAME_INPUT);
				Thread.sleep(2000);
				webDB.sendTextToAnElement(CampaignsLocators.CAMPAIGNSNAME_INPUT,
						CampaignsLocators.CAMPAIGNSINPUTVALUE + random, ElementType.Xpath);
				webDB.clickAnElement(CampaignsLocators.SELECTTEMPLATE, ElementType.Xpath);
				Thread.sleep(2000);

				List<WebElement> element = webDB.driver.findElements(By.xpath(CampaignsLocators.TEMPLATESELECT));

				for (int i = 0; i < element.size(); i++) {
					actions.moveToElement(element.get(i)).build().perform();
					Thread.sleep(500);
					String TemplateName = element.get(i).getText();
					if (TemplateName.contains(CampaignsLocators.CAMPAIGNTEMPLATE)) {
						element.get(i).click();
						break;
					}
				}
//				webDB.clickAnElement(MarketCampaignsLocators.CHOOSETEMPLATEBTN, ElementType.Xpath);
//				Thread.sleep(2000);
//				webDB.clickAnElement(MarketCampaignsLocators.TEMPLATENAME, ElementType.Xpath);
//				Thread.sleep(2000);
//				webDB.clickAnElement(MarketCampaignsLocators.SECONDTEMPLATEBTN, ElementType.Xpath);
//				Thread.sleep(2000);
//				webDB.clickAnElement(MarketCampaignsLocators.CHOOSETEMPLATEBTN, ElementType.Xpath);
//				Thread.sleep(2000);
//				webDB.clickAnElement(MarketCampaignsLocators.TEMPLATENAME, ElementType.Xpath);
//				Thread.sleep(2000);
//				webDB.clickAnElement(MarketCampaignsLocators.FIRSTTEMPLATEBTN, ElementType.Xpath);
				Thread.sleep(2000);
				webDB.clickAnElement(CampaignsLocators.CHOOSETEMPLATEBTN, ElementType.Xpath);
				propertyFilePath = (System.getProperty("user.dir") + File.separator + "payload" + File.separator
						+ CampaignsLocators.CAMPAIGNSFILENAME);
				webDB.clickAnElement(CampaignsLocators.FROMFILE, ElementType.CssSelector);
				Thread.sleep(2000);
				webDB.sendTextToAnElement(CampaignsLocators.RECEIPTENTSNAME, propertyFilePath, ElementType.CssSelector);
				Thread.sleep(2000);
				webDB.moveToElement(CampaignsLocators.SUBMITBTN, ElementType.Xpath);
				flag = webDB.driver.findElement(By.xpath(CampaignsLocators.CONFIRMATION_CHECKBOX)).isSelected();
				System.out.println(flag);
				if (!flag) {
					Thread.sleep(2000);
					webDB.clickAnElement(CampaignsLocators.CONFIRMATION_CHECKBOX_TXT, ElementType.Xpath);
				}
				System.out.println("enabled");
				Thread.sleep(4000);
				flag = webDB.waitForElement(CampaignsLocators.SUBMITBTN, ElementType.Xpath);
				if (flag) {
					Thread.sleep(3000);
					webDB.clickAnElement(CampaignsLocators.SUBMITBTN, ElementType.Xpath);
					Thread.sleep(800);
					webDB.waitForElement(CampaignsLocators.LOADSPINNER, ElementType.CssSelector);
					flag = webDB.verifyElementIsDisplayed(CampaignsLocators.LOADSPINNER, ElementType.CssSelector);
					if (flag) {
						log.info("Verified campaign is submitted with checkbox selected");
						Thread.sleep(4000);
						flag = webDB.waitForElement(CampaignsLocators.FIRSTCREATEDCAMPAIGNS, ElementType.Xpath);
						if (flag) {
							flag = verifyStatus();
							System.out.println("Created");
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyGuideMarketCampign() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CampaignsLocators.MARKETCAMPAIGNS_TAB)));
		webDB.clickAnElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
		flag = webDB.waitForElement(CampaignsLocators.GUIDE, ElementType.CssSelector);
		if (flag) {
			Thread.sleep(2000);
			webDB.clickAnElement(CampaignsLocators.GUIDE, ElementType.CssSelector);
			Thread.sleep(2000);
			webDB.clickAnElement(CampaignsLocators.NEXTSTEPBTN, ElementType.Xpath);
			Thread.sleep(2000);
			webDB.clickAnElement(CampaignsLocators.NEXTSTEPBTN, ElementType.Xpath);
			Thread.sleep(2000);
			webDB.clickAnElement(CampaignsLocators.NEXTSTEPBTN, ElementType.Xpath);
			Thread.sleep(2000);
			webDB.clickAnElement(CampaignsLocators.NEXTSTEPBTN, ElementType.Xpath);
			Thread.sleep(2000);
			webDB.clickAnElement(CampaignsLocators.GUIDECOMPLETEDBTN, ElementType.Xpath);
			flag = webDB.isElementDisplayed(CampaignsLocators.GUIDE, ElementType.CssSelector);
		}

		return flag;
	}

	public static boolean verifyStatus() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CampaignsLocators.MARKETCAMPAIGNS_TAB)));
		webDB.clickAnElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
		flag = webDB.waitForElement(CampaignsLocators.FIRSTCREATEDCAMPAIGNS, ElementType.Xpath);
		if (flag) {
			Thread.sleep(5000);
			String text = webDB.getText(CampaignsLocators.STATUS, ElementType.Xpath);
			flag = ((text.contains("CREATED")) || (text.contains("PUBLISHED")) || (text.contains("COMPLETED")));
		}
		return flag;
	}

	public static boolean verifyCustomTemplateInMarketCampign() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CampaignsLocators.MARKETCAMPAIGNS_TAB)));
		webDB.clickAnElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
		flag = webDB.waitForElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			webDB.clickAnElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
			flag = webDB.waitForElement(CampaignsLocators.CAMPAIGNSNAME_INPUT, ElementType.Xpath);
			if (flag) {
				webDB.clearText(CampaignsLocators.CAMPAIGNSNAME_INPUT);
				Thread.sleep(2000);
				webDB.sendTextToAnElement(CampaignsLocators.CAMPAIGNSNAME_INPUT,
						CampaignsLocators.CAMPAIGNSINPUTVALUE + random, ElementType.Xpath);
				webDB.clickAnElement(CampaignsLocators.SELECTTEMPLATE, ElementType.Xpath);
				Thread.sleep(2000);
				webDB.moveToElement(CampaignsLocators.LAST_TEMPLATE_VALUE, ElementType.Xpath);
				Thread.sleep(1000);
				flag = webDB.waitForElement("//span[text()='" + AutomationPage.random + "']", ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement("//span[text()='" + AutomationPage.random + "']", ElementType.Xpath);
					Thread.sleep(1000);
					webDB.clickAnElement(CampaignsLocators.CHOOSETEMPLATEBTN, ElementType.Xpath);
					flag = webDB.waitForElement("//input[@value='" + AutomationPage.random + "']", ElementType.Xpath);

				}
			}
		}
		return flag;
	}

	@SuppressWarnings("static-access")
	public static boolean verifyCreateMarketCampaignwithPredefined() throws Exception {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CampaignsLocators.MARKETCAMPAIGNS_TAB)));
		webDB.clickAnElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
		flag = webDB.waitForElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			webDB.clickAnElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
			flag = webDB.waitForElement(CampaignsLocators.CAMPAIGNSNAME_INPUT, ElementType.Xpath);
			if (flag) {
				webDB.clearText(CampaignsLocators.CAMPAIGNSNAME_INPUT);
				Thread.sleep(2000);
				webDB.sendTextToAnElement(CampaignsLocators.CAMPAIGNSNAME_INPUT,
						CampaignsLocators.CAMPAIGNSINPUTVALUE + random, ElementType.Xpath);
				webDB.clickAnElement(CampaignsLocators.SELECTTEMPLATE, ElementType.Xpath);
				Thread.sleep(2000);
				webDB.moveToElement(CampaignsLocators.LAST_TEMPLATE_VALUE, ElementType.Xpath);
				Thread.sleep(1000);
				flag = webDB.waitForElement("//span[text()='" + AutomationPage.random + "']", ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement("//span[text()='" + AutomationPage.random + "']", ElementType.Xpath);
					Thread.sleep(1000);
					webDB.clickAnElement(CampaignsLocators.CHOOSETEMPLATEBTN, ElementType.Xpath);
					flag = webDB.waitForElement("//input[@value='" + AutomationPage.random + "']", ElementType.Xpath);
					webDB.clickAnElement(CampaignsLocators.CHOOSETEMPLATEBTN, ElementType.Xpath);
					Thread.sleep(2000);
					webDB.clickAnElement(CampaignsLocators.AUTOMATION_TESTING_SEGEMENT_DROPDOWN, ElementType.Xpath);
					webDB.selectDropDownOptionsByText(CampaignsLocators.AUTOMATION_TESTING_SEGEMENT_DROPDOWN,
							CampaignsLocators.SEGMENTDROPDOWNVALUE, ElementType.Xpath);
					Thread.sleep(2000);
					flag = webDB.waitForElement(CampaignsLocators.SUBMITBTN, ElementType.Xpath);
					if (flag) {
						Thread.sleep(1000);
						webDB.clickAnElement(CampaignsLocators.SUBMITBTN, ElementType.Xpath);
						Thread.sleep(2000);
						flag = webDB.waitForElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
					}
				}
			}
		}
		return flag;
	}

	@SuppressWarnings("static-access")
	public static boolean verifyCreateMarketCampaignwithPredefinedAndCustomUploadFile() throws Exception {
		CustomersPage.deleteexsistfile();
		webDB.driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CampaignsLocators.MARKETCAMPAIGNS_TAB)));
		webDB.clickAnElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
		// modified - added sleep time
		Thread.sleep(5000);
		webDB.scrollToAnElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
		flag = webDB.waitForElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);

			webDB.clickAnElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
			flag = webDB.waitForElement(CampaignsLocators.CAMPAIGNSNAME_INPUT, ElementType.Xpath);
			if (flag) {
				webDB.clearText(CampaignsLocators.CAMPAIGNSNAME_INPUT);
				Thread.sleep(2000);
				webDB.sendTextToAnElement(CampaignsLocators.CAMPAIGNSNAME_INPUT,
						CampaignsLocators.CAMPAIGNSINPUTVALUE + random, ElementType.Xpath);
				System.out.println("The template name is * " + random);
				webDB.clickAnElement(CampaignsLocators.SELECTTEMPLATE, ElementType.Xpath);
				Thread.sleep(2000);
				// ***************
				webDB.moveToElement(CampaignsLocators.FIRSTTEMPLATEBTN, ElementType.Xpath);
				Thread.sleep(1000);
				flag = webDB.waitForElement(CampaignsLocators.FIRSTTEMPLATEBTN, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CampaignsLocators.FIRSTTEMPLATEBTN, ElementType.Xpath);
					Thread.sleep(2000);
//					webDB.clickAnElement(CampaignsLocators.CHOOSETEMPLATEBTN, ElementType.Xpath);
//					Thread.sleep(2000);
					webDB.clickAnElement(CampaignsLocators.AUTOMATION_TESTING_SEGEMENT_DROPDOWN, ElementType.Xpath);
					// modified - changed segment value
					webDB.selectDropDownOptionsByText(CampaignsLocators.AUTOMATION_TESTING_SEGEMENT_DROPDOWN,
							CampaignsLocators.SEGMENTDROPDOWNVALUE, ElementType.Xpath);
					webDB.waitForElement(CampaignsLocators.DOWNLOADEXCELBUTTON, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(CampaignsLocators.DOWNLOADEXCELBUTTON, ElementType.Xpath);
						Thread.sleep(10000);
						String inputfilepath = CustomersPage.writeDataInSheet();
						flag = webDB.waitForElement(CampaignsLocators.FROMFILE, ElementType.CssSelector);
						if (flag) {
							webDB.clickAnElement(CampaignsLocators.FROMFILE, ElementType.CssSelector);
							webDB.sendTextToAnElement(CampaignsLocators.RECEIPTENTSNAME, inputfilepath,
									ElementType.CssSelector);
							Thread.sleep(2000);
							// modified - added checkbox clicking.
							webDB.clickAnElement(CampaignsLocators.CHECKBOX, ElementType.Xpath);
							flag = webDB.waitForElement(CampaignsLocators.SUBMITBTN, ElementType.Xpath);
							if (flag) {
								Thread.sleep(1000);
								webDB.clickAnElement(CampaignsLocators.SUBMITBTN, ElementType.Xpath);
								Thread.sleep(2000);
								flag = webDB.waitForElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN,
										ElementType.Xpath);
								Thread.sleep(1000);
							}
						}
					}
				}
			}
		}
		CustomersPage.deleteexsistfile();
		return flag;
	}

	public static boolean verifyFilteroptionCampaign() throws Exception {
		flag = webDB.waitForElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			webDB.clickAnElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
			flag = webDB.isElementDisplayed(CampaignsLocators.CAMPAIGNTEMPLATE_DEFAULT_FILTER, ElementType.Xpath);
			if (flag) {
				flag = webDB.isElementDisplayed(CampaignsLocators.CAMPAIGNTEMPLATE_CREATED_FILTER, ElementType.Xpath);
			}
		}
		return flag;
	}

	public static boolean verifyDefaultFilteroptionCampaign() throws Exception {
		flag = webDB.waitForElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			webDB.clickAnElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
			Thread.sleep(2000);
			flag = webDB.isElementDisplayed(CampaignsLocators.CAMPAIGNTEMPLATE_DEFAULT_FILTER, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CampaignsLocators.CAMPAIGNTEMPLATE_DEFAULT_FILTER, ElementType.Xpath);
				flag = webDB.IsEnabled(CampaignsLocators.CAMPAIGNTEMPLATE_DEFAULT_FILTER_INPUT, ElementType.Xpath);
				if (flag) {
					System.out.println("Verified select default");
				} else {
					webDB.clickAnElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
					System.out.println("Verified select default");
				}
			}
		}
		return flag;
	}

	public static boolean verifyCreatedFilteroptionCampaign() throws Exception {
		flag = webDB.waitForElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			webDB.clickAnElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
			flag = webDB.isElementDisplayed(CampaignsLocators.CAMPAIGNTEMPLATE_CREATED_FILTER, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CampaignsLocators.CAMPAIGNTEMPLATE_CREATED_FILTER, ElementType.Xpath);
				flag = webDB.IsEnabled(CampaignsLocators.CAMPAIGNTEMPLATE_CREATED_FILTER_INPUT, ElementType.Xpath);
				if (flag) {
					System.out.println("Verified selected Created by me");
				} else {
					webDB.clickAnElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
					System.out.println("Verified selected Created by me");
				}
			}
		}
		return flag;
	}

	public static boolean verifyRunCampaign() throws Exception {
		flag = webDB.waitForElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			webDB.clickAnElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
			Thread.sleep(2000);
			webDB.clickAnElement(CampaignsLocators.CAMPAIGNTEMPLATE_DEFAULT_FILTER, ElementType.Xpath);
			Thread.sleep(2000);
			flag = webDB.IsEnabled(CampaignsLocators.CAMPAIGNTEMPLATE_DEFAULT_FILTER_INPUT, ElementType.Xpath);
			if (flag) {
				System.out.println("flag:" + flag);
				System.out.println("Verified select default");
			} else {
				webDB.clickAnElement(CampaignsLocators.CAMPAIGNTEMPLATE_DEFAULT_FILTER, ElementType.Xpath);
				System.out.println("Verified select default");
			}
			Thread.sleep(2000);
			flag = webDB.waitForElement(AutomationLocators.RUNCAMPAIGN_BTN, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				webDB.clickAnElement(AutomationLocators.RUNCAMPAIGN_BTN, ElementType.Xpath);
				flag = webDB.waitForElement(CampaignsLocators.MESSAGETEMPLATENAME, ElementType.Xpath);
				if (flag) {
					String text = webDB.driver.findElement(By.xpath(CampaignsLocators.MESSAGETEMPLATENAME))
							.getAttribute("value");
					flag = text.equals(CampaignsLocators.MESSAGETEMPLATE_VALUE);
				}
			}
		}
		return flag;
	}

	public static boolean verifycreateCutomtemplateWithSuffixandDelete() throws Exception {
		random = AutomationLocators.TEMPLATENAMEVALUE + RandomStringUtils.randomNumeric(3);
		webDB.moveToElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
		flag = webDB.waitForElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
		if (flag) {
			Thread.sleep(3000);
			webDB.javaScriptClickAnElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
			Thread.sleep(2000);
			flag = webDB.waitForElement(AutomationLocators.CREATECUSTOMTEMPLATESBTN, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				webDB.javaScriptClickAnElement(AutomationLocators.CREATECUSTOMTEMPLATESBTN, ElementType.Xpath);
				webDB.sendTextToAnElement(AutomationLocators.TEMPLATESNAMEINPUT, random, ElementType.CssSelector);
				flag = webDB.clickAnElement(AutomationLocators.CATEGORYDROPDOWN, ElementType.Xpath);
				if (flag) {
					webDB.selectDropDownOptions(AutomationLocators.CATEGORYDROPDOWN,
							AutomationLocators.CATEGORYDROPDOWNVALUE, ElementType.Xpath);
					Thread.sleep(1500);
					flag = webDB.clickAnElement(AutomationLocators.TYPEDROPDOWN, ElementType.Xpath);
					if (flag) {
						webDB.selectDropDownOptions(AutomationLocators.TYPEDROPDOWN,
								AutomationLocators.TYPEDROPDOWNVALUE, ElementType.Xpath);
						Thread.sleep(1500);
						flag = webDB.clickAnElement(AutomationLocators.LANGUAGEDROPDOWN, ElementType.Xpath);
						if (flag) {
							webDB.selectDropDownOptions(AutomationLocators.LANGUAGEDROPDOWN,
									AutomationLocators.LANGUAGEDROPDOWNVALUE, ElementType.Xpath);
							webDB.moveToElement(AutomationLocators.CREATEBTN, ElementType.Xpath);
							Thread.sleep(2000);
							webDB.sendTextToAnElement(AutomationLocators.BODYINPUT,
									AutomationLocators.BODYVARIABLE_FIRST_VALUE, ElementType.Xpath);
							flag = webDB.waitForElement(AutomationLocators.ADD_VARIABLE, ElementType.Xpath);
							if (flag) {
								Thread.sleep(2000);
								webDB.clickAnElement(AutomationLocators.ADD_VARIABLE, ElementType.Xpath);
								webDB.moveToElement(AutomationLocators.CUSTOM_VARIABLE, ElementType.Xpath);
								webDB.javaScriptClickAnElement(AutomationLocators.CUSTOM_VARIABLE, ElementType.Xpath);
								Thread.sleep(1000);
								if (flag) {
									Thread.sleep(1000);
									webDB.moveToElement(AutomationLocators.CREATEBTN, ElementType.Xpath);
									String temp3 = "qastore";
									Thread.sleep(2500);
//								webDB.sendTextToAnElement(TemplatesLocators.HEADERINPUT, TemplatesLocators.HEADERVALUE,
//										ElementType.Xpath);
//								Thread.sleep(2500);
//								webDB.sendTextToAnElement(TemplatesLocators.FOOTERINPUT, TemplatesLocators.FOOTERVALUE,
//										ElementType.Xpath);
									AutomationPage.verifyCustomTemplateCallActionsSuffix();
									webDB.sendTextToAnElement(AutomationLocators.SAMPLEMESSAGEINPUT, temp3,
											ElementType.Xpath);
									String text1 = webDB.getText(AutomationLocators.SAMPLEMESSAGEINPUT,
											ElementType.Xpath);
									System.out.println(text1);
									String text2 = webDB.driver
											.findElement(By.xpath(AutomationLocators.SAMPLEMESSAGEINPUT))
											.getAttribute("value");
									System.out.println(text2);
									webDB.clickAnElement(AutomationLocators.SAMPLEMESSAGELABEL, ElementType.Xpath);
									Thread.sleep(2500);
									log.info("Values are Entered" + random);
									flag = AutomationPage.verifyViewCustomTemplateName();
									if (flag) {
										flag = AutomationPage.verifyDeleteCustomTemplateName();
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

	public static Connection setDBConnection() throws ClassNotFoundException, SQLException {
		String dbURL = "jdbc:mysql://10.80.12.58:3306/tadpole";
		String username = "superlemon";
		String password = "superL@m0n";
		Connection con = DriverManager.getConnection(dbURL, username, password);
		return con;
	}

	public static void updateresultsinDB() throws Exception {
		webDB.verifyResponseAPI("GetShopID");
		String shopid = webDB.shopid;
		System.out.println(shopid);
		try {
			Connection con = setDBConnection();
			Statement st = con.createStatement();
			String query = String
					.format("Delete from customers where shop_id = " + shopid + " and phone = '918693858861';");
			System.out.println(query);
			int rs1;
			rs1 = st.executeUpdate(query);
			System.out.println("Record deleted Successfully");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean createMultiCampaignOptin() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CampaignsLocators.MARKETCAMPAIGNS_TAB)));
		webDB.clickAnElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
		flag = webDB.waitForElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			webDB.clickAnElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
			flag = webDB.waitForElement(CampaignsLocators.CAMPAIGNSNAME_INPUT, ElementType.Xpath);
			if (flag) {
				webDB.clearText(CampaignsLocators.CAMPAIGNSNAME_INPUT);
				Thread.sleep(2000);
				webDB.sendTextToAnElement(CampaignsLocators.CAMPAIGNSNAME_INPUT,
						CampaignsLocators.CAMPAIGNSINPUTVALUE + random, ElementType.Xpath);
				webDB.clickAnElement(CampaignsLocators.SELECTTEMPLATE, ElementType.Xpath);
				Thread.sleep(2000);
				webDB.moveToElement("//span[text()='" + CampaignsLocators.CHOOSETEMPLATENAME + "']", ElementType.Xpath);
				Thread.sleep(1000);
				flag = webDB.waitForElement("//span[text()='" + CampaignsLocators.CHOOSETEMPLATENAME + "']",
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement("//span[text()='" + CampaignsLocators.CHOOSETEMPLATENAME + "']",
							ElementType.Xpath);
					Thread.sleep(1000);
					webDB.clickAnElement(CampaignsLocators.CHOOSETEMPLATEBTN, ElementType.Xpath);

					flag = webDB.waitForElement(CampaignsLocators.FROMFILE, ElementType.CssSelector);
					if (flag) {
						propertyFilePath = (System.getProperty("user.dir") + File.separator + "payload" + File.separator
								+ CampaignsLocators.MULTIPLECAMPAIGNSFILENAMEVALUE);
						webDB.clickAnElement(CampaignsLocators.FROMFILE, ElementType.CssSelector);
						webDB.sendTextToAnElement(CampaignsLocators.RECEIPTENTSNAME, propertyFilePath,
								ElementType.CssSelector);
						Thread.sleep(2000);
						webDB.moveToElement(CampaignsLocators.SUBMITBTN, ElementType.Xpath);
						flag = webDB.driver.findElement(By.xpath(CampaignsLocators.CONFIRMATION_CHECKBOX)).isSelected();
						System.out.println(flag);
						if (!flag) {
							Thread.sleep(2000);
							webDB.clickAnElement(CampaignsLocators.CONFIRMATION_CHECKBOX_TXT, ElementType.Xpath);
						}
						System.out.println("enabled");
						Thread.sleep(4000);
						flag = webDB.waitForElement(CampaignsLocators.SUBMITBTN, ElementType.Xpath);
						if (flag) {
							Thread.sleep(3000);
							webDB.clickAnElement(CampaignsLocators.SUBMITBTN, ElementType.Xpath);
							Thread.sleep(4000);
						}
					}
				}
			}
		}
		return flag;

	}

	public static boolean verifyMultiCampaignOptin() throws Exception {
		webDB.driver.navigate().refresh();
		Thread.sleep(7000);
		flag = webDB.waitForElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
		if (flag) {
			webDB.driver.navigate().refresh();
			Thread.sleep(7000);
			webDB.waitForElement(CampaignsLocators.FIRSTROW_STATUS, ElementType.Xpath);
			String temp = webDB.getText(CampaignsLocators.FIRSTROW_STATUS, ElementType.Xpath);
			System.out.println("First status is: "+temp);
			flag = temp.contains("PUBLISHED");
			if (flag) {
				String text = webDB.getText(CampaignsLocators.FIRSTROW_RECEPIENTSCOUNT, ElementType.Xpath)
						.replace(" contacts", "");
				System.out.println(text);
				//modified - changed from 2
				flag = text.contains("3");
				if (flag) {
					webDB.driver.navigate().refresh();
					Thread.sleep(5000);
					System.out.println("count matched");
					String text1 = webDB.getText(CampaignsLocators.FIRSTROW_OPTINCOUNT, ElementType.Xpath);
					System.out.println(text1);
					flag = text1.contains("3");
					if (flag) {
						System.out.println("count matched");
						webDB.driver.navigate().refresh();
						String temp1 = webDB.getText(CampaignsLocators.FIRSTROW_STATUS, ElementType.Xpath);
						System.out.println(temp1);
						String text2 = webDB.getText(CampaignsLocators.FIRSTROW_DELIVEREDCOUNT, ElementType.Xpath);
						System.out.println(text2);
						//modified - changed from 3
						flag = text2.contains("2") && temp1.contains("COMPLETED");
						if (flag) {
							System.out.println("count matched");
						} else {
							System.out.println("count is  mismatched");
						}
					}
				}
			} else {
				System.out.println("Status is mismatched");
			}
		}
		return flag;
	}

	public static boolean verifyOptinCampaignOptinCustomer() throws Exception {
		CommonFunctions.verifyOptinNumbersPage();
		webDB.moveToElement(CampaignsLocators.FIRSTROW_OPTINSOURCE, ElementType.Xpath);
		webDB.waitForElement(CampaignsLocators.FIRSTROW_OPTINSOURCE, ElementType.Xpath);
		String temp = webDB.getText(CampaignsLocators.FIRSTROW_OPTINSOURCE, ElementType.Xpath);
		System.out.println(temp);
		flag = temp.contains("CAMPAIGN");
		if (flag) {
			System.out.println("Status is matched");
		} else {
			System.out.println("Status is mismatched");
		}
		return flag;
	}

	@SuppressWarnings("static-access")
	public static boolean verifyLearnmoreRedirection() throws InterruptedException, FileNotFoundException, IOException {
		webDB.driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CampaignsLocators.MARKETCAMPAIGNS_TAB)));
		webDB.clickAnElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
		String MainWindow = webDB.driver.getWindowHandle();
		Thread.sleep(5000);
		flag = webDB.waitForElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			webDB.javaScriptClickAnElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
			flag = webDB.waitForElement(CampaignsLocators.CAMPAIGNSNAME_INPUT, ElementType.Xpath);
			webDB.scrollToAnElement(CampaignsLocators.LEARNMORE, ElementType.Xpath);
			webDB.clickAnElement(CampaignsLocators.LEARNMORE, ElementType.Xpath);
			propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
			prop.load(new FileInputStream(propertyFilePath));
			String FBDev_url = prop.getProperty("FacebookdeveloperURL");
			webDB.switchToChildWindow();
			Thread.sleep(2000);
			String currentUrl = webDB.driver.getCurrentUrl();
			if (currentUrl.contains(FBDev_url)) {
				log.info("Verified page redirection");
				Thread.sleep(2000);
				webDB.driver.close();
				webDB.driver.switchTo().window(MainWindow);
			}
		}
		return flag;
	}

	@SuppressWarnings("static-access")
	public static boolean verifyViewCampaignandRedirection()
			throws InterruptedException, FileNotFoundException, IOException {
		webDB.driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CampaignsLocators.MARKETCAMPAIGNS_TAB)));
		webDB.clickAnElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
		flag = webDB.waitForElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
			Thread.sleep(5000);
			flag = webDB.waitForElement(CampaignsLocators.VIEW_CAMPAIGN_BTN, ElementType.Xpath);
			webDB.clickAnElement(CampaignsLocators.VIEW_CAMPAIGN_BTN, ElementType.Xpath);
			log.info("Verified view campaign is clickable");
			Thread.sleep(3000);
			flag = webDB.isElementDisplayed(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
			if (flag) {
				log.info("Verified redirection to my campaign list after clicked view campaign");
			}
		}
		return flag;
	}

	public static String getfilename() {
		String path = System.getProperty("user.dir") + File.separator + "Resources";
		File file = new File(path);
		File[] files = file.listFiles();
		for (File f : files) {
			if (f.isFile() && f.exists()) {
				filepath = f.getAbsolutePath();
				System.out.println("successfully deleted");
			} else {
				System.out.println("cant delete a file due to open or error");
			}
		}
		System.out.println(filepath);
		return filepath;
	}

	public static void getAllSheetData() {
		try {
			String[] COLUMNVALUES = { "Customer Number", "Status", "Reason for Failure", "Message ID" };
			String input_sheetname = getfilename();

//			prop.load(new FileInputStream(propertyFilePath));
//			String input_filename = prop.getProperty("input_filename");
//			output_filename = prop.getProperty("output_filename");

			StringBuffer buf = new StringBuffer();
			String[] data;
			String headers;
			try (BufferedReader br = new BufferedReader(new FileReader(input_sheetname))) {
				headers = br.readLine().replace("ï»¿", "");
				System.out.println(headers);
				data = headers.toString().split(",");
				for (int i = 0; i < data.length; i++) {
					try {
						String cellValue = "";
						try {
							cellValue = data[i];
						} catch (NullPointerException e) {

						}
						System.out.println(data[i] + " ");
						Assert.assertEquals(data[i], cellValue);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static boolean verifyDownloadReport() throws InterruptedException, FileNotFoundException, IOException {
		webDB.driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CampaignsLocators.MARKETCAMPAIGNS_TAB)));
		webDB.clickAnElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
		flag = webDB.waitForElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
		if (flag) {
			flag = webDB.waitForElement(CampaignsLocators.DOWNLOADREPORT, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CampaignsLocators.DOWNLOADREPORT, ElementType.Xpath);
				Thread.sleep(1500);
				getAllSheetData();
				flag = true;
			}
		}
		return flag;
	}

	@SuppressWarnings("static-access")
	public static boolean verifyUserlimitPopup() throws InterruptedException, FileNotFoundException, IOException {
		webDB.driver.navigate().refresh();
		String MainWindow = webDB.driver.getWindowHandle();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CampaignsLocators.MARKETCAMPAIGNS_TAB)));
		webDB.clickAnElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
		Thread.sleep(5000);
		flag = webDB.waitForElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			webDB.javaScriptClickAnElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
			flag = webDB.waitForElement(CampaignsLocators.CAMPAIGNSNAME_INPUT, ElementType.Xpath);
			if (flag) {
				webDB.clearText(CampaignsLocators.CAMPAIGNSNAME_INPUT);
				Thread.sleep(2000);
				webDB.sendTextToAnElement(CampaignsLocators.CAMPAIGNSNAME_INPUT,
						CampaignsLocators.CAMPAIGNSINPUTVALUE + random, ElementType.Xpath);
				webDB.clickAnElement(CampaignsLocators.SELECTTEMPLATE, ElementType.Xpath);
				Thread.sleep(2000);
				// modified - added clicking All category - by default it is in utility
				webDB.waitForElement(CampaignsLocators.ALL_CATEGORY_TEMPLATE, ElementType.Xpath);
				webDB.clickAnElement(CampaignsLocators.ALL_CATEGORY_TEMPLATE, ElementType.Xpath);
				Thread.sleep(2000);
				
				List<WebElement> element = webDB.driver.findElements(By.xpath(CampaignsLocators.TEMPLATESELECT));

				for (int i = 0; i < element.size(); i++) {
					actions.moveToElement(element.get(i)).build().perform();
					Thread.sleep(500);
					String TemplateName = element.get(i).getText();
					if (TemplateName.contains(CampaignsLocators.CAMPAIGNTEMPLATEVIDEO)) {
						// modified - commented below and added locator
						// element.get(i).click();
						Thread.sleep(2000);
						// modified - Added new locator
						webDB.clickAnElement(CampaignsLocators.CLICKSPECIALOFFERTEMPLATE, ElementType.Xpath);
						break;
					}
				}
				Thread.sleep(2000);
				//modified - commented below - not available
//				webDB.clickAnElement(CampaignsLocators.CHOOSETEMPLATEBTN, ElementType.Xpath);
				propertyFilePath = (System.getProperty("user.dir") + File.separator + "payload" + File.separator
						+ CampaignsLocators.MAXIMUMCAMPAIGNSFILENAME);
				webDB.clickAnElement(CampaignsLocators.FROMFILE, ElementType.CssSelector);
				Thread.sleep(2000);
				webDB.sendTextToAnElement(CampaignsLocators.RECEIPTENTSNAME, propertyFilePath, ElementType.CssSelector);
				Thread.sleep(2000);
				webDB.moveToElement(CampaignsLocators.SUBMITBTN, ElementType.Xpath);
				flag = webDB.driver.findElement(By.xpath(CampaignsLocators.CONFIRMATION_CHECKBOX)).isSelected();
				System.out.println(flag);
				if (!flag) {
					Thread.sleep(2000);
					webDB.clickAnElement(CampaignsLocators.CONFIRMATION_CHECKBOX_TXT, ElementType.Xpath);
				}
				System.out.println("enabled");
				Thread.sleep(4000);
				flag = webDB.waitForElement(CampaignsLocators.SUBMITBTN, ElementType.Xpath);
				if (flag) {
					Thread.sleep(3000);
					webDB.clickAnElement(CampaignsLocators.SUBMITBTN, ElementType.Xpath);
					Thread.sleep(800);
					flag = webDB.verifyElementIsDisplayed(CampaignsLocators.ERRORPOPUP, ElementType.CssSelector);
					if (flag) {
						System.out.println(flag);
						log.info("Verified popup appered for user limit");
						Thread.sleep(800);
						webDB.clickAnElement(CampaignsLocators.ERRORPOPUP_LEARNMORE, ElementType.Xpath);
						Thread.sleep(800);
						propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
						prop.load(new FileInputStream(propertyFilePath));
						String FBDev_url = prop.getProperty("SuperlemonUserlimitURL");
						webDB.switchToChildWindow();
						Thread.sleep(2000);
						String currentUrl = webDB.driver.getCurrentUrl();
						if (currentUrl.contains(FBDev_url)) {
							log.info("Verified page redirection to superlemon after click learn more");
							Thread.sleep(2000);
							webDB.driver.close();
							webDB.driver.switchTo().window(MainWindow);
							Thread.sleep(2000);
							webDB.clickAnElement(AutomationLocators.SETTINGSCLOSEICON, ElementType.CssSelector);
							flag = webDB.waitForElement(CampaignsLocators.VIEW_CAMPAIGN_BTN, ElementType.Xpath);
						}

					}

				}
			}
		}
		return flag;
	}

	public static boolean verifyMyCampaignsTutorial() throws InterruptedException {
		flag = webDB.waitForElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
		if (flag) {
			flag = webDB.waitForElement(CampaignsLocators.TUTORIAL_BTN, ElementType.Xpath);
			if (flag) {
				webDB.javaScriptClickAnElement(CampaignsLocators.TUTORIAL_BTN, ElementType.Xpath);
				flag = webDB.waitForElement(CampaignsLocators.WHATSAPPCAMPAIGN_TUTORIAL, ElementType.Xpath);
				if (flag) {
					Thread.sleep(2000);
					// modified - commented below and added new
					// webDB.clickAnElement(CampaignsLocators.WHATSAPPCAMPAIGN_TUTORIAL,
					// ElementType.Xpath);
					webDB.javaScriptClickAnElement(CampaignsLocators.WHATSAPPCAMPAIGN_TUTORIAL, ElementType.Xpath);
					Thread.sleep(2000);
					flag = webDB.waitForElement(CampaignsLocators.WHATSAPPCAMPAIGN_IFRAME, ElementType.CssSelector);
					if (flag) {
						flag = webDB.waitForElement(CampaignsLocators.TUTORIAL_CLOSEBTN, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(CampaignsLocators.TUTORIAL_CLOSEBTN, ElementType.Xpath);
							flag = webDB.waitForElement(CampaignsLocators.TUTORIAL_BTN, ElementType.Xpath);
							if (flag) {
								webDB.javaScriptClickAnElement(CampaignsLocators.TUTORIAL_BTN, ElementType.Xpath);
								flag = webDB.waitForElement(CampaignsLocators.CREATECAMPAIGN_TUTORIAL,
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(CampaignsLocators.CREATECAMPAIGN_TUTORIAL, ElementType.Xpath);
									Thread.sleep(2000);
									flag = webDB.waitForElement(CampaignsLocators.WHATSAPPCAMPAIGN_IFRAME,
											ElementType.CssSelector);
									if (flag) {
										flag = webDB.waitForElement(CampaignsLocators.TUTORIAL_CLOSEBTN,
												ElementType.Xpath);
										if (flag) {
											webDB.clickAnElement(CampaignsLocators.TUTORIAL_CLOSEBTN,
													ElementType.Xpath);
											flag = webDB.waitForElement(CampaignsLocators.TUTORIAL_BTN,
													ElementType.Xpath);
											if (flag) {
												System.out.println("Verified tutorial videos in MyCampaigns");
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

	@SuppressWarnings("static-access")
	public static boolean verifyErrorMessageForVideoCampaign()
			throws InterruptedException, FileNotFoundException, IOException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CampaignsLocators.MARKETCAMPAIGNS_TAB)));
		webDB.clickAnElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
		Thread.sleep(5000);
		flag = webDB.waitForElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			webDB.javaScriptClickAnElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
			flag = webDB.waitForElement(CampaignsLocators.CAMPAIGNSNAME_INPUT, ElementType.Xpath);
			if (flag) {
				webDB.clearText(CampaignsLocators.CAMPAIGNSNAME_INPUT);
				Thread.sleep(2000);
				webDB.sendTextToAnElement(CampaignsLocators.CAMPAIGNSNAME_INPUT,
						CampaignsLocators.CAMPAIGNSINPUTVALUE + random, ElementType.Xpath);
				webDB.clickAnElement(CampaignsLocators.SELECTTEMPLATE, ElementType.Xpath);
				Thread.sleep(2000);
				// modified - added clicking All category - by default it is in utility
				webDB.waitForElement(CampaignsLocators.ALL_CATEGORY_TEMPLATE, ElementType.Xpath);
				webDB.clickAnElement(CampaignsLocators.ALL_CATEGORY_TEMPLATE, ElementType.Xpath);
				Thread.sleep(2000);

				// modified - below locator
				List<WebElement> element = webDB.driver.findElements(By.xpath(CampaignsLocators.TEMPLATESELECT));

				for (int i = 0; i < element.size(); i++) {
					actions.moveToElement(element.get(i)).build().perform();
					Thread.sleep(500);
					String TemplateName = element.get(i).getText();
					if (TemplateName.contains(CampaignsLocators.CAMPAIGNTEMPLATEVIDEO)) {
						// modified - commented and added below
//						element.get(i).click();
						Thread.sleep(2000);
						webDB.clickAnElement(CampaignsLocators.CLICKVIDEOTEMPLATE, ElementType.Xpath);

//						webDB.clickAnElement("//div[@class='template']//p[contains(text(), "+TemplateName+")]/following-sibling::div//button", ElementType.Xpath);
						break;
					}
				}
				Thread.sleep(2000);
				webDB.clickAnElement(CampaignsLocators.CHOOSETEMPLATEBTN, ElementType.Xpath);
				Text = webDB.getText(CampaignsLocators.VIDEOTEXT, ElementType.Xpath);
				flag = Text.contains("mp4 with a maximum size of 16MB");
				log.info("Video Text found " + Text);
				if (flag) {
					propertyFilePath = (System.getProperty("user.dir") + File.separator + "payload" + File.separator
							+ CampaignsLocators.CAMPAIGN_TEMPLATE_PHOTO);

					webDB.sendTextToAnElement(CampaignsLocators.MEDIA_FILE, propertyFilePath, ElementType.Xpath);
					flag = webDB.waitForElement(CampaignsLocators.ERROR_MESSAGE, ElementType.Xpath);
					if (flag) {
						Text = webDB.getText(CampaignsLocators.ERROR_MESSAGE, ElementType.Xpath);
						flag = Text.contains("Please upload video file of type .mp4 with a maximum size of 16MB");
						log.info("Error message is " + Text + " flag found is" + flag);
						log.info("Error message is verified for video campaign with image as media ");
						Thread.sleep(1000);
					}
					if (flag) {
						propertyFilePath = (System.getProperty("user.dir") + File.separator + "payload" + File.separator
								+ CampaignsLocators.CAMPAIGN_TEMPLATE_VIDEO_LARGESIZE);
						Thread.sleep(2000);
						webDB.sendTextToAnElement(CampaignsLocators.MEDIA_FILE, propertyFilePath, ElementType.Xpath);
						Text = webDB.getText(CampaignsLocators.ERROR_MESSAGE, ElementType.Xpath);
						flag = Text.contains("Please upload video file of type .mp4 with a maximum size of 16MB");
						log.info("Error message is " + Text);
						log.info("Error message is verified for video campaign with large size ");
					}
				}
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CampaignsLocators.CANCEL_POPUP)));

				if (flag) {
					flag = webDB.clickAnElement(CampaignsLocators.CANCEL_POPUP, ElementType.Xpath);
					log.info("Cancel pop up is show and flag is: " + flag);
					if (flag) {
						Thread.sleep(5000);
						webDB.clickAnElement(CampaignsLocators.CANCELBTN, ElementType.Xpath);
						Thread.sleep(5000);
						flag = webDB.waitForElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);

					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyErrorMessageForImageCampaign()
			throws InterruptedException, FileNotFoundException, IOException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CampaignsLocators.MARKETCAMPAIGNS_TAB)));
		webDB.clickAnElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
		Thread.sleep(5000);
		flag = webDB.waitForElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			webDB.javaScriptClickAnElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
			flag = webDB.waitForElement(CampaignsLocators.CAMPAIGNSNAME_INPUT, ElementType.Xpath);
			if (flag) {
				webDB.clearText(CampaignsLocators.CAMPAIGNSNAME_INPUT);
				Thread.sleep(2000);
				webDB.sendTextToAnElement(CampaignsLocators.CAMPAIGNSNAME_INPUT,
						CampaignsLocators.CAMPAIGNSINPUTVALUE + random, ElementType.Xpath);
				webDB.clickAnElement(CampaignsLocators.SELECTTEMPLATE, ElementType.Xpath);
				Thread.sleep(2000);
				// modified - added clicking All category - by default it is in utility
				webDB.waitForElement(CampaignsLocators.ALL_CATEGORY_TEMPLATE, ElementType.Xpath);
				webDB.clickAnElement(CampaignsLocators.ALL_CATEGORY_TEMPLATE, ElementType.Xpath);
				Thread.sleep(2000);

				List<WebElement> element = webDB.driver.findElements(By.xpath(CampaignsLocators.TEMPLATESELECT));

				for (int i = 0; i < element.size(); i++) {
					actions.moveToElement(element.get(i)).build().perform();
					Thread.sleep(500);
					String TemplateName = element.get(i).getText();
					if (TemplateName.contains(CampaignsLocators.CAMPAIGN_TEMPLATE_IMAGE)) {
						// modified - commented below and added locator
						// element.get(i).click();
						Thread.sleep(2000);
						// modified - changed template
						webDB.clickAnElement(CampaignsLocators.CLICKIMAGETEMPLATE, ElementType.Xpath);
						break;
					}
				}
				Thread.sleep(2000);
				webDB.clickAnElement(CampaignsLocators.CHOOSETEMPLATEBTN, ElementType.Xpath);
				Text = webDB.getText(CampaignsLocators.IMAGETEXT, ElementType.Xpath);
				flag = Text.contains("Supported media type: .jpeg and .png with a maximum size of 2MB");
				log.info("Image Text found " + Text);
				if (flag) {
					propertyFilePath = (System.getProperty("user.dir") + File.separator + "payload" + File.separator
							+ CampaignsLocators.CAMPAIGN_TEMPLATE_VIDEO_LARGESIZE);

					webDB.sendTextToAnElement(CampaignsLocators.MEDIA_FILE, propertyFilePath, ElementType.Xpath);
					flag = webDB.waitForElement(CampaignsLocators.ERROR_MESSAGE, ElementType.Xpath);
					if (flag) {
						Text = webDB.getText(CampaignsLocators.ERROR_MESSAGE, ElementType.Xpath);
						flag = Text
								.contains("Please upload image file of type .jpeg or .png with a maximum size of 2MB");
						log.info("Error message is " + Text + " flag found is" + flag);
						log.info("Error message is verified for image campaign with video as media ");
						Thread.sleep(1000);
					}
					if (flag) {
						propertyFilePath = (System.getProperty("user.dir") + File.separator + "payload" + File.separator
								+ CampaignsLocators.CAMPAIGN_TEMPLATE_IMAGE_LARGESIZE);
						Thread.sleep(2000);
						webDB.sendTextToAnElement(CampaignsLocators.MEDIA_FILE, propertyFilePath, ElementType.Xpath);
						Text = webDB.getText(CampaignsLocators.ERROR_MESSAGE, ElementType.Xpath);
						flag = Text
								.contains("Please upload image file of type .jpeg or .png with a maximum size of 2MB");
						log.info("Error message is " + Text);
						log.info("Error message is verified for image campaign with large size ");
					}
				}
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CampaignsLocators.CANCEL_POPUP)));

				if (flag) {
					flag = webDB.clickAnElement(CampaignsLocators.CANCEL_POPUP, ElementType.Xpath);
					log.info("Cancel pop up is show and flag is: " + flag);
					if (flag) {
						Thread.sleep(5000);
						webDB.clickAnElement(CampaignsLocators.CANCELBTN, ElementType.Xpath);
						Thread.sleep(5000);
						flag = webDB.waitForElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);

					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyImageCampaignWithFile()
			throws InterruptedException, FileNotFoundException, IOException {
		CustomersPage.deleteexsistfile();
		webDB.driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CampaignsLocators.MARKETCAMPAIGNS_TAB)));
		webDB.clickAnElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
		Thread.sleep(5000);
		flag = webDB.waitForElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			webDB.javaScriptClickAnElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
			flag = webDB.waitForElement(CampaignsLocators.CAMPAIGNSNAME_INPUT, ElementType.Xpath);
			if (flag) {
				webDB.clearText(CampaignsLocators.CAMPAIGNSNAME_INPUT);
				Thread.sleep(2000);
				webDB.sendTextToAnElement(CampaignsLocators.CAMPAIGNSNAME_INPUT,
						CampaignsLocators.CAMPAIGNSINPUTVALUE + random, ElementType.Xpath);
				webDB.clickAnElement(CampaignsLocators.SELECTTEMPLATE, ElementType.Xpath);
				Thread.sleep(2000);
				// modified - added clicking All category - by default it is in utility
				webDB.waitForElement(CampaignsLocators.ALL_CATEGORY_TEMPLATE, ElementType.Xpath);
				webDB.clickAnElement(CampaignsLocators.ALL_CATEGORY_TEMPLATE, ElementType.Xpath);
				Thread.sleep(2000);

				List<WebElement> element = webDB.driver.findElements(By.xpath(CampaignsLocators.TEMPLATESELECT));

				for (int i = 0; i < element.size(); i++) {
					actions.moveToElement(element.get(i)).build().perform();
					Thread.sleep(500);
					String TemplateName = element.get(i).getText();
					if (TemplateName.contains(CampaignsLocators.CAMPAIGN_TEMPLATE_IMAGE)) {
						// modified - commented below and added locator
						// element.get(i).click();
						Thread.sleep(2000);
						webDB.clickAnElement(CampaignsLocators.CLICKIMAGETEMPLATE, ElementType.Xpath);
						break;
					}
				}
				Thread.sleep(2000);
				webDB.clickAnElement(CampaignsLocators.CHOOSETEMPLATEBTN, ElementType.Xpath);
				propertyFilePath = (System.getProperty("user.dir") + File.separator + "payload" + File.separator
						+ CampaignsLocators.CAMPAIGN_TEMPLATE_PHOTO);
				webDB.sendTextToAnElement(CampaignsLocators.MEDIA_FILE, propertyFilePath, ElementType.Xpath);
				Thread.sleep(5000);
				flag = webDB.waitForElement(CampaignsLocators.MEDIA_FILE_SELECTED_IMAGE, ElementType.Xpath);
				log.info("Media file is visible and flag is " + flag);
				if (flag) {
					Text = webDB.getText(CampaignsLocators.MEDIA_FILE_SELECTED_IMAGE, ElementType.Xpath);
					log.info("Text is " + Text);
					flag = Text.contains("Upload.png");

					if (flag) {
						webDB.clickAnElement(CampaignsLocators.FROMFILE, ElementType.CssSelector);
						flag = webDB.waitForElement(CampaignsLocators.DOWNLOADEXCELBUTTON, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(CampaignsLocators.DOWNLOADEXCELBUTTON, ElementType.Xpath);
							Thread.sleep(10000);
							String inputfilepath = writeDataInSheet();
							log.info(inputfilepath);
							Thread.sleep(2000);
							webDB.sendTextToAnElement(CampaignsLocators.RECEIPTENTSNAME, inputfilepath,
									ElementType.CssSelector);
							Thread.sleep(2000);
							webDB.clickAnElement(CampaignsLocators.CONFIRMATION_CHECKBOX_TXT, ElementType.Xpath);
							flag = webDB.waitForElement(CampaignsLocators.SUBMITBTN, ElementType.Xpath);
							if (flag) {
								Thread.sleep(1000);
								webDB.clickAnElement(CampaignsLocators.SUBMITBTN, ElementType.Xpath);
								Thread.sleep(2000);
								flag = webDB.waitForElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN,
										ElementType.Xpath);
								Thread.sleep(1000);
							}
						}
					}
				}
			}
		}

		return flag;
	}

	@SuppressWarnings("resource")
	public static String writeDataInSheet() throws IOException {
		String input_sheetname = getfilename();
		ArrayList<String> sheetvalue = new ArrayList<String>(
				Arrays.asList("918693858861", "fisrtvalue", "secondvalue", "thirdvalue", "fourthvalue"));

		File F = new File(input_sheetname);
		FileInputStream fis = new FileInputStream(F);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		int sheetCount = wb.getNumberOfSheets();
		System.out.println("sheet count is :" + sheetCount);
		XSSFSheet sh1 = wb.getSheet("customers");

		int lastrow = sh1.getLastRowNum();
		System.out.println("get last row number :" + lastrow);

		int value_of_row = lastrow + 1;

		XSSFRow lastRow = sh1.getRow(lastrow);
		int lastCellNum = lastRow.getLastCellNum();
		System.out.println("getlast cell number: " + lastCellNum);
		XSSFRow row = sh1.createRow(value_of_row);

		for (int i = 0; i < lastCellNum; i++) {

			System.out.println(sheetvalue.get(i));
			row.createCell(i).setCellValue(sheetvalue.get(i));
			outputStream = new FileOutputStream(input_sheetname);
			wb.write(outputStream);

		}

		outputStream.close();

		return input_sheetname;
	}

	public static boolean verifyVideoCampaignWithFile()
			throws InterruptedException, FileNotFoundException, IOException {
		CustomersPage.deleteexsistfile();
		webDB.driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CampaignsLocators.MARKETCAMPAIGNS_TAB)));
		webDB.clickAnElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
		Thread.sleep(5000);
		flag = webDB.waitForElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			webDB.javaScriptClickAnElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
			flag = webDB.waitForElement(CampaignsLocators.CAMPAIGNSNAME_INPUT, ElementType.Xpath);
			if (flag) {
				webDB.clearText(CampaignsLocators.CAMPAIGNSNAME_INPUT);
				Thread.sleep(2000);
				webDB.sendTextToAnElement(CampaignsLocators.CAMPAIGNSNAME_INPUT,
						CampaignsLocators.CAMPAIGNSINPUTVALUE + random, ElementType.Xpath);
				webDB.clickAnElement(CampaignsLocators.SELECTTEMPLATE, ElementType.Xpath);
				Thread.sleep(2000);
				// modified - added clicking All category - by default it is in utility
				webDB.waitForElement(CampaignsLocators.ALL_CATEGORY_TEMPLATE, ElementType.Xpath);
				webDB.clickAnElement(CampaignsLocators.ALL_CATEGORY_TEMPLATE, ElementType.Xpath);
				Thread.sleep(2000);

				List<WebElement> element = webDB.driver.findElements(By.xpath(CampaignsLocators.TEMPLATESELECT));

				for (int i = 0; i < element.size(); i++) {
					actions.moveToElement(element.get(i)).build().perform();
					Thread.sleep(500);
					String TemplateName = element.get(i).getText();
					if (TemplateName.contains(CampaignsLocators.CAMPAIGNTEMPLATEVIDEO)) {
						// modified - commented and added below
//						element.get(i).click();
						Thread.sleep(2000);
						webDB.clickAnElement(CampaignsLocators.CLICKVIDEOTEMPLATE, ElementType.Xpath);
						break;
					}
				}
				Thread.sleep(2000);
				webDB.clickAnElement(CampaignsLocators.CHOOSETEMPLATEBTN, ElementType.Xpath);
				propertyFilePath = (System.getProperty("user.dir") + File.separator + "payload" + File.separator
						+ CampaignsLocators.VIDEO_SAMPLE);
				webDB.sendTextToAnElement(CampaignsLocators.MEDIA_FILE, propertyFilePath, ElementType.Xpath);
				Thread.sleep(5000);
				flag = webDB.waitForElement(CampaignsLocators.MEDIA_FILE_SELECTED_VIDEO, ElementType.Xpath);
				log.info("Media file is visible and flag is " + flag);
				if (flag) {
					Text = webDB.getText(CampaignsLocators.MEDIA_FILE_SELECTED_VIDEO, ElementType.Xpath);
					log.info("Text is " + Text);
					flag = Text.contains("videosample.mp4");

					if (flag) {
						webDB.clickAnElement(CampaignsLocators.FROMFILE, ElementType.CssSelector);
						flag = webDB.waitForElement(CampaignsLocators.DOWNLOADEXCELBUTTON, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(CampaignsLocators.DOWNLOADEXCELBUTTON, ElementType.Xpath);
							Thread.sleep(10000);
							String inputfilepath = writeDataInSheet();
							log.info(inputfilepath);
							Thread.sleep(2000);
							webDB.sendTextToAnElement(CampaignsLocators.RECEIPTENTSNAME, inputfilepath,
									ElementType.CssSelector);
							Thread.sleep(2000);
							webDB.clickAnElement(CampaignsLocators.CONFIRMATION_CHECKBOX_TXT, ElementType.Xpath);
							flag = webDB.waitForElement(CampaignsLocators.SUBMITBTN, ElementType.Xpath);
							if (flag) {
								Thread.sleep(1000);
								webDB.clickAnElement(CampaignsLocators.SUBMITBTN, ElementType.Xpath);
								Thread.sleep(2000);
								flag = webDB.waitForElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN,
										ElementType.Xpath);
								Thread.sleep(1000);
							}
						}
					}
				}
			}
		}

		return flag;
	}

	public static boolean verifyDocsCampaignWithFile() throws InterruptedException, FileNotFoundException, IOException {
		CustomersPage.deleteexsistfile();
		webDB.driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CampaignsLocators.MARKETCAMPAIGNS_TAB)));
		webDB.clickAnElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
		Thread.sleep(5000);
		flag = webDB.waitForElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			webDB.javaScriptClickAnElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
			flag = webDB.waitForElement(CampaignsLocators.CAMPAIGNSNAME_INPUT, ElementType.Xpath);
			if (flag) {
				webDB.clearText(CampaignsLocators.CAMPAIGNSNAME_INPUT);
				Thread.sleep(2000);
				webDB.sendTextToAnElement(CampaignsLocators.CAMPAIGNSNAME_INPUT,
						CampaignsLocators.CAMPAIGNSINPUTVALUE + random, ElementType.Xpath);
				webDB.clickAnElement(CampaignsLocators.SELECTTEMPLATE, ElementType.Xpath);
				Thread.sleep(2000);
				// modified - added clicking All category - by default it is in utility
				webDB.waitForElement(CampaignsLocators.ALL_CATEGORY_TEMPLATE, ElementType.Xpath);
				webDB.clickAnElement(CampaignsLocators.ALL_CATEGORY_TEMPLATE, ElementType.Xpath);
				Thread.sleep(2000);

				List<WebElement> element = webDB.driver.findElements(By.xpath(CampaignsLocators.TEMPLATESELECT));

				for (int i = 0; i < element.size(); i++) {
					actions.moveToElement(element.get(i)).build().perform();
					Thread.sleep(500);
					String TemplateName = element.get(i).getText();
					// modified below locator
					if (TemplateName.contains(CampaignsLocators.CAMPAIGN_TEMPLATE_DOCS)) {
						// modified - commented and added below
//						element.get(i).click();
						Thread.sleep(2000);
						webDB.clickAnElement(CampaignsLocators.CLICKDOCUMENTTEMPLATE, ElementType.Xpath);
						break;
					}
				}
				Thread.sleep(2000);
				webDB.clickAnElement(CampaignsLocators.CHOOSETEMPLATEBTN, ElementType.Xpath);
				propertyFilePath = (System.getProperty("user.dir") + File.separator + "payload" + File.separator
						+ CampaignsLocators.DOCS_SAMPLE);
				webDB.sendTextToAnElement(CampaignsLocators.MEDIA_FILE, propertyFilePath, ElementType.Xpath);
				Thread.sleep(5000);
				flag = webDB.waitForElement(CampaignsLocators.MEDIA_FILE_SELECTED_DOCS, ElementType.Xpath);
				log.info("Media file is visible and flag is " + flag);
				if (flag) {
					Text = webDB.getText(CampaignsLocators.MEDIA_FILE_SELECTED_DOCS, ElementType.Xpath);
					log.info("Text is " + Text);
					flag = Text.contains("sample.pdf");

					if (flag) {
						webDB.clickAnElement(CampaignsLocators.FROMFILE, ElementType.CssSelector);
						flag = webDB.waitForElement(CampaignsLocators.DOWNLOADEXCELBUTTON, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(CampaignsLocators.DOWNLOADEXCELBUTTON, ElementType.Xpath);
							Thread.sleep(10000);
							String inputfilepath = writeDataInSheet();
							log.info(inputfilepath);
							Thread.sleep(2000);
							webDB.sendTextToAnElement(CampaignsLocators.RECEIPTENTSNAME, inputfilepath,
									ElementType.CssSelector);
							Thread.sleep(2000);
							webDB.clickAnElement(CampaignsLocators.CONFIRMATION_CHECKBOX_TXT, ElementType.Xpath);
							flag = webDB.waitForElement(CampaignsLocators.SUBMITBTN, ElementType.Xpath);
							if (flag) {
								Thread.sleep(1000);
								webDB.clickAnElement(CampaignsLocators.SUBMITBTN, ElementType.Xpath);
								Thread.sleep(2000);
								flag = webDB.waitForElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN,
										ElementType.Xpath);
								Thread.sleep(1000);
							}
						}
					}
				}
			}
		}

		return flag;
	}

	public static boolean verifyErrorMessageForDocsCampaign()
			throws InterruptedException, FileNotFoundException, IOException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CampaignsLocators.MARKETCAMPAIGNS_TAB)));
		webDB.clickAnElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
		Thread.sleep(5000);
		flag = webDB.waitForElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			webDB.javaScriptClickAnElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);
			flag = webDB.waitForElement(CampaignsLocators.CAMPAIGNSNAME_INPUT, ElementType.Xpath);
			if (flag) {
				webDB.clearText(CampaignsLocators.CAMPAIGNSNAME_INPUT);
				Thread.sleep(2000);
				webDB.sendTextToAnElement(CampaignsLocators.CAMPAIGNSNAME_INPUT,
						CampaignsLocators.CAMPAIGNSINPUTVALUE + random, ElementType.Xpath);
				webDB.clickAnElement(CampaignsLocators.SELECTTEMPLATE, ElementType.Xpath);
				Thread.sleep(2000);
				// modified - added clicking All category - by default it is in utility
				webDB.waitForElement(CampaignsLocators.ALL_CATEGORY_TEMPLATE, ElementType.Xpath);
				webDB.clickAnElement(CampaignsLocators.ALL_CATEGORY_TEMPLATE, ElementType.Xpath);
				Thread.sleep(2000);

				List<WebElement> element = webDB.driver.findElements(By.xpath(CampaignsLocators.TEMPLATESELECT));

				for (int i = 0; i < element.size(); i++) {
					actions.moveToElement(element.get(i)).build().perform();
					Thread.sleep(500);
					String TemplateName = element.get(i).getText();
					// modified below locator
					if (TemplateName.contains(CampaignsLocators.CAMPAIGN_TEMPLATE_DOCS)) {
						// modified - commented and added below
//						element.get(i).click();
						Thread.sleep(2000);
						webDB.clickAnElement(CampaignsLocators.CLICKDOCUMENTTEMPLATE, ElementType.Xpath);
						break;
					}
				}
				Thread.sleep(2000);
				webDB.clickAnElement(CampaignsLocators.CHOOSETEMPLATEBTN, ElementType.Xpath);
				Text = webDB.getText(CampaignsLocators.DOCS_TEXT, ElementType.Xpath);
				flag = Text.contains("Supported media type: .pdf with a maximum size of 2MB");
				log.info("Video Text found " + Text);
				if (flag) {
					propertyFilePath = (System.getProperty("user.dir") + File.separator + "payload" + File.separator
							+ CampaignsLocators.CAMPAIGN_TEMPLATE_PHOTO);

					webDB.sendTextToAnElement(CampaignsLocators.MEDIA_FILE, propertyFilePath, ElementType.Xpath);
					flag = webDB.waitForElement(CampaignsLocators.ERROR_MESSAGE, ElementType.Xpath);
					if (flag) {
						Text = webDB.getText(CampaignsLocators.ERROR_MESSAGE, ElementType.Xpath);
						flag = Text.contains("Please upload document file of type .pdf with a maximum size of 2MB");
						log.info("Error message is " + Text + " flag found is" + flag);
						log.info("Error message is verified for docs campaign with image as media ");
						Thread.sleep(1000);
					}
					if (flag) {
						propertyFilePath = (System.getProperty("user.dir") + File.separator + "payload" + File.separator
								+ CampaignsLocators.CAMPAIGN_TEMPLATE_DOCS_LARGESIZE);
						Thread.sleep(2000);
						webDB.sendTextToAnElement(CampaignsLocators.MEDIA_FILE, propertyFilePath, ElementType.Xpath);
						Text = webDB.getText(CampaignsLocators.ERROR_MESSAGE, ElementType.Xpath);
						flag = Text.contains("Please upload document file of type .pdf with a maximum size of 2MB");
						log.info("Error message is " + Text);
						log.info("Error message is verified for docs campaign with large size ");
					}
				}

				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CampaignsLocators.CANCEL_POPUP)));

				if (flag) {
					flag = webDB.clickAnElement(CampaignsLocators.CANCEL_POPUP, ElementType.Xpath);
					log.info("Cancel pop up is show and flag is: " + flag);
					if (flag) {
						Thread.sleep(5000);
						webDB.clickAnElement(CampaignsLocators.CANCELBTN, ElementType.Xpath);
						Thread.sleep(5000);
						flag = webDB.waitForElement(CampaignsLocators.CREATEMARKETCAMPAIGNS_BTN, ElementType.Xpath);

					}
				}
			}
		}
		return flag;
	}

}
