package commonFunctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import locators.AnalyticsLocators;
import locators.AutomationLocators;
import locators.CampaignsLocators;
import locators.CommonLocators;
import locators.CustomersLocators;
import locators.LoginPageLocators;
import locators.MessagingLocators;
import locators.MyWhatsAppLocators;
import locators.PlansPageLocators;
import locators.SettingsLocators;
import utils.DriverBase;
import utils.DriverBase.ElementType;

@SuppressWarnings({ "static-access", "unused" })
public class PlansPage {
	private static Logger log = Logger.getLogger(PlansPage.class);
	private static boolean flag;
	private static String propertyFilePath;
	private static String url;
	public static Properties prop = new Properties();
	private static DriverBase webDB = new DriverBase();;
	protected static WebDriverWait wait = new WebDriverWait(webDB.driver, 30);
	private static String Text;
	private static JavascriptExecutor js = (JavascriptExecutor) webDB.driver;

	/**
	 * verify Description of plans
	 *
	 * @return throws IOException
	 ** @throws InterruptedException
	 */
	public static boolean verifyPlanDescription() throws InterruptedException {
		webDB.moveToElement(PlansPageLocators.VIEWALLPLANS, ElementType.Xpath);
		webDB.waitForElement(PlansPageLocators.VIEWALLPLANS, ElementType.Xpath);
		webDB.clickAnElement(PlansPageLocators.VIEWALLPLANS, ElementType.Xpath);
		flag = webDB.isElementDisplayed(PlansPageLocators.PLANS_DESCRIPTION, ElementType.CssSelector);
//		if (flag) {
//			int count = webDB.driver.findElements(By.xpath(PlansPageLocators.CURRENT_PLAN)).size();
//			if (count == 2) {
//				flag = true;
//				log.info("Current plan selected is visible on top and Bottom");
//			}
//		} else {
//			log.info("On Plans page but something went weong on accessing features");
//			flag = false;
//		}
		return flag;
	}

	public static boolean loginShopify() throws InterruptedException {
		flag = webDB.waitForElement(LoginPageLocators.USERNAME, ElementType.CssSelector);
		if (flag) {
			Thread.sleep(5000);
			webDB.sendTextToAnElement(LoginPageLocators.USERNAME, LoginPageLocators.USERNAMEVALUE,
					ElementType.CssSelector);
			flag = webDB.waitForElement(LoginPageLocators.NEXTBTN, ElementType.CssSelector);
			if (flag) {
				Thread.sleep(4000);
				webDB.clickAnElement(LoginPageLocators.NEXTBTN, ElementType.CssSelector);
				flag = webDB.waitForElement(LoginPageLocators.PASSWORD, ElementType.CssSelector);
				if (flag) {
					Thread.sleep(6000);
					webDB.sendTextToAnElement(LoginPageLocators.PASSWORD, LoginPageLocators.PASSWORDVALUE,
							ElementType.CssSelector);
					flag = webDB.waitForElement(LoginPageLocators.NEXTBTN, ElementType.CssSelector);
					if (flag) {
						Thread.sleep(4000);
						flag = webDB.clickAnElement(LoginPageLocators.NEXTBTN, ElementType.CssSelector);
					}
				}
			}
		} else {
			System.out.println("already login");
		}
		return flag;
	}

	/**
	 * Change Basic plan
	 *
	 * @return
	 * @throws InterruptedException
	 */
	public static boolean changeSmallBusinessPlan() throws InterruptedException {
		flag = webDB.waitForElement(PlansPageLocators.SMALLBUSINESSPLANSUBSCRIBEDBTN, ElementType.Xpath);
		if (flag) {
			Thread.sleep(1500);
			flag = webDB.clickAnElement(PlansPageLocators.SMALLBUSINESSPLANSUBSCRIBEDBTN, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2500);
				loginShopify();
				webDB.moveToElement(PlansPageLocators.ULTIMATE_PLAN_APPROVEBUTTON, ElementType.Xpath);
				flag = webDB.waitForElement(PlansPageLocators.ULTIMATE_PLAN_APPROVEBUTTON, ElementType.Xpath);
				if (flag) {
					Thread.sleep(2500);
					webDB.clickAnElement(PlansPageLocators.ULTIMATE_PLAN_APPROVEBUTTON, ElementType.Xpath);
				} else {
					System.out.println("done");
					flag = true;
				}
				flag = webDB.waitForElement(PlansPageLocators.VIEWALLPLANS, ElementType.Xpath);
			} else {
				System.out.println("Already U have in Free Plan");
				flag = true;
			}
			flag = webDB.waitForElement(PlansPageLocators.VIEWALLPLANS, ElementType.Xpath);
		}
		return flag;

	}

	/**
	 * Change Basic plan
	 *
	 * @return
	 * @throws InterruptedException
	 */
	public static boolean enableUltimatePlan() throws InterruptedException {
		flag = webDB.waitForElement(LoginPageLocators.USERNAME, ElementType.CssSelector);
		if (flag) {
			Thread.sleep(5000);
			webDB.sendTextToAnElement(LoginPageLocators.USERNAME, LoginPageLocators.USERNAMEVALUE,
					ElementType.CssSelector);
			flag = webDB.waitForElement(LoginPageLocators.NEXTBTN, ElementType.CssSelector);
			if (flag) {
				Thread.sleep(4000);
				webDB.clickAnElement(LoginPageLocators.NEXTBTN, ElementType.CssSelector);
				flag = webDB.waitForElement(LoginPageLocators.PASSWORD, ElementType.CssSelector);
				if (flag) {
					Thread.sleep(6000);
					webDB.sendTextToAnElement(LoginPageLocators.PASSWORD, LoginPageLocators.PASSWORDVALUE,
							ElementType.CssSelector);
					flag = webDB.waitForElement(LoginPageLocators.NEXTBTN, ElementType.CssSelector);
					if (flag) {
						Thread.sleep(4000);
						flag = webDB.clickAnElement(LoginPageLocators.NEXTBTN, ElementType.CssSelector);
					}
				}
			}
		} else {
			System.out.println("already login");
		}
		Thread.sleep(7000);
		webDB.moveToElement(PlansPageLocators.ADD_CREDIT_CARD, ElementType.Xpath);
		flag = webDB.waitForElement(PlansPageLocators.ULTIMATE_PLAN_APPROVEBUTTON, ElementType.Xpath);
		if (flag) {
			Thread.sleep(3000);
			flag = webDB.clickAnElement(PlansPageLocators.ULTIMATE_PLAN_APPROVEBUTTON, ElementType.Xpath);
			Thread.sleep(6000);
			if (flag) {
				flag = webDB.isElementDisplayed(PlansPageLocators.ULTIMATE_PLAN_BUYBUTTON, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PlansPageLocators.ULTIMATE_PLAN_BUYBUTTON, ElementType.Xpath);
					Thread.sleep(6000);
					flag = webDB.waitForElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(AutomationLocators.TEMPLATES, ElementType.Xpath);
						Thread.sleep(2000);
						flag = webDB.waitForElement(AutomationLocators.AUTOMATEDPROMOTIONAL_TAB, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(AutomationLocators.AUTOMATEDPROMOTIONAL_TAB, ElementType.Xpath);
							webDB.moveToElement(AutomationLocators.CROSSSELL_TOGGLE, ElementType.Xpath);
							flag = webDB.waitForElement(AutomationLocators.CROSSSELL_SETTINGS, ElementType.Xpath);
							if (flag) {
								webDB.moveToElement(AutomationLocators.CUSTOMERWINBACK_SETTINGS, ElementType.Xpath);
								Thread.sleep(2000);
								flag = webDB.waitForElement(AutomationLocators.CUSTOMERWINBACK_SETTINGS,
										ElementType.Xpath);
								if (flag) {
									webDB.moveToElement(AutomationLocators.FEEDBACK_SETTINGS, ElementType.Xpath);
									Thread.sleep(2000);
									flag = webDB.waitForElement(AutomationLocators.FEEDBACK_SETTINGS,
											ElementType.Xpath);
								}
							}
						}
					}
				}
			}
		}
		return flag;
	}

	/**
	 * Verify Free plan
	 *
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static boolean verifyPlanUiSection() throws InterruptedException, FileNotFoundException, IOException {
		CommonFunctions.verifyPlansPage();
		webDB.moveToElement(PlansPageLocators.VIEWALLPLANS, ElementType.Xpath);
		flag = webDB.waitForElement(PlansPageLocators.VIEWALLPLANS, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			webDB.clickAnElement(PlansPageLocators.VIEWALLPLANS, ElementType.Xpath);
			Thread.sleep(2000);
			webDB.moveToElement(PlansPageLocators.VIEWALLPLANS, ElementType.Xpath);
			flag = webDB.waitForElement(PlansPageLocators.FREEPLANS, ElementType.Xpath);
			if (flag) {
				flag = webDB.isElementDisplayed(PlansPageLocators.FREEPLANS, ElementType.Xpath);
				if (flag) {
					flag = webDB.isElementDisplayed(PlansPageLocators.SMALLBUSINESSPLANS, ElementType.Xpath);
					if (flag) {
						webDB.moveToElement(PlansPageLocators.OTHERSTITLE, ElementType.Xpath);
						flag = webDB.isElementDisplayed(PlansPageLocators.FAQTITLE, ElementType.Xpath);
						if (flag) {
							Thread.sleep(2000);
							webDB.clickAnElement(PlansPageLocators.SUBSCRIPTIONTITLE, ElementType.Xpath);
							flag = webDB.isElementDisplayed(PlansPageLocators.SUBSCRIPTIONCONTENT, ElementType.Xpath);
							if (flag) {
								Thread.sleep(2000);
								webDB.clickAnElement(PlansPageLocators.SUBSCRIPTIONTITLE, ElementType.Xpath);
								webDB.clickAnElement(PlansPageLocators.PRIVATEACCOUNTTITE, ElementType.Xpath);
								flag = webDB.isElementDisplayed(PlansPageLocators.PRIVATEACCOUNTCONTENT,
										ElementType.Xpath);
								if (flag) {
									Thread.sleep(2000);
									webDB.clickAnElement(PlansPageLocators.PRIVATEACCOUNTTITE, ElementType.Xpath);
									webDB.clickAnElement(PlansPageLocators.MESSAGETITLE, ElementType.Xpath);
									flag = webDB.isElementDisplayed(PlansPageLocators.MESSAGECONTENT,
											ElementType.Xpath);
									if (flag) {
										Thread.sleep(2000);
										webDB.clickAnElement(PlansPageLocators.PRIVATEACCOUNTTITE, ElementType.Xpath);
										webDB.clickAnElement(PlansPageLocators.OTHERSTITLE, ElementType.Xpath);
										flag = webDB.isElementDisplayed(PlansPageLocators.OTHERSCONTENT,
												ElementType.Xpath);
										if (flag) {
											Thread.sleep(2000);
											webDB.clickAnElement(PlansPageLocators.OTHERSTITLE, ElementType.Xpath);
											webDB.moveToElement(PlansPageLocators.UPGRADEBUTTON, ElementType.Xpath);
											flag = webDB.waitForElement(PlansPageLocators.FREEPLANS, ElementType.Xpath);
											if (flag) {
												Thread.sleep(2000);
												String text = webDB.getText(PlansPageLocators.UPGRADETEXT,
														ElementType.Xpath);
												flag = text.contains(PlansPageLocators.UPGRADETEXTVALUE);
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

	/**
	 * Verify Free plan
	 *
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static boolean verifyCustomTemplatesPageNavigate()
			throws InterruptedException, FileNotFoundException, IOException {
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
			Thread.sleep(1500);
			webDB.clickAnElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
			if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
					|| (System.getProperty("os").equals("Android"))) {
				webDB.clickAnElement(CommonLocators.CLOSEICON, ElementType.Xpath);
			}
			flag = webDB.waitForElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
			if (flag) {
				Thread.sleep(1500);
				webDB.clickAnElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
				Thread.sleep(1500);
				webDB.moveToElement(PlansPageLocators.CLICKHEREBTN, ElementType.Xpath);
				flag = webDB.waitForElement(PlansPageLocators.CLICKHEREBTN, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PlansPageLocators.CLICKHEREBTN, ElementType.Xpath);
					Thread.sleep(2000);
					String text = webDB.getText(PlansPageLocators.FREECOSTPLAN, ElementType.Xpath);
					flag = text.contains("0");
					url = webDB.driver.getCurrentUrl();
					propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
					prop.load(new FileInputStream(propertyFilePath));
					String planPageUrl = prop.getProperty("PlanspageURL");
					if (url == planPageUrl) {
						log.info("Landed on Plans page");
					}
				}
			}

		}
		return flag;
	}

	/**
	 * Verify Free plan
	 *
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static boolean verifyExtensionTemplatesPageNavigate()
			throws InterruptedException, FileNotFoundException, IOException {
		if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
				|| (System.getProperty("os").equals("Android"))) {
			flag = webDB.waitForElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			if (flag) {
				Thread.sleep(1500);
				webDB.clickAnElement(CommonLocators.SIDENAV, ElementType.CssSelector);
			}
		}
		flag = webDB.waitForElement(AutomationLocators.MESSAGINGTAB, ElementType.Xpath);
		if (flag) {
			Thread.sleep(2000);
			webDB.clickAnElement(AutomationLocators.MESSAGINGTAB, ElementType.Xpath);
			if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
					|| (System.getProperty("os").equals("Android"))) {
				webDB.clickAnElement(CommonLocators.CLOSEICON, ElementType.Xpath);
			}
			flag = webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2000);
				webDB.clickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
				Thread.sleep(2000);
				webDB.moveToElement(PlansPageLocators.CLICKHEREBTN, ElementType.Xpath);
				flag = webDB.waitForElement(PlansPageLocators.CLICKHEREBTN, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PlansPageLocators.CLICKHEREBTN, ElementType.Xpath);
					Thread.sleep(2500);
					url = webDB.driver.getCurrentUrl();
					propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
					prop.load(new FileInputStream(propertyFilePath));
					String planPageUrl = prop.getProperty("PlanspageURL");
					if (url == planPageUrl) {
						log.info("Landed on Plans page");
					}
				}
			}
		}
		return flag;
	}

	/**
	 * Verify Privatewaba page Navigate
	 *
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static boolean verifyPrivatewabaPageNavigate()
			throws InterruptedException, FileNotFoundException, IOException {
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
			Thread.sleep(2000);
			webDB.clickAnElement(MyWhatsAppLocators.WHATSAPPAPI_PAGE_TAB, ElementType.Xpath);
			if ((System.getProperty("platformName").equals("browserstack")) && (System.getProperty("os").equals("Ios"))
					|| (System.getProperty("os").equals("Android"))) {
				webDB.clickAnElement(CommonLocators.CLOSEICON, ElementType.Xpath);
			}
			Thread.sleep(2000);
			webDB.moveToElement(PlansPageLocators.CLICKHEREBTN, ElementType.Xpath);
			flag = webDB.waitForElement(PlansPageLocators.CLICKHEREBTN, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PlansPageLocators.CLICKHEREBTN, ElementType.Xpath);
				Thread.sleep(2500);
				url = webDB.driver.getCurrentUrl();
				propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
				prop.load(new FileInputStream(propertyFilePath));
				String planPageUrl = prop.getProperty("PlanspageURL");
				if (url == planPageUrl) {
					log.info("Landed on Plans page");
				}
			}
		}
		return flag;
	}

	/**
	 * Verify Upgrade Plan Validations
	 *
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static boolean verifyUpgradePlanValidations()
			throws InterruptedException, FileNotFoundException, IOException {
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String planPageUrl = prop.getProperty("PlanspageURL");
		CommonFunctions.verifyPlansPage();
		webDB.moveToElement(PlansPageLocators.RECOMMENDEDPLAN, ElementType.Xpath);
		flag = webDB.isElementDisplayed(PlansPageLocators.RECOMMENDEDPLAN, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PlansPageLocators.UPGRADEBUTTON, ElementType.Xpath);
			flag = webDB.waitForElement(PlansPageLocators.UPGRADEBUTTON, ElementType.Xpath);
//			if (flag) {
//				flag = webDB.clickAnElement(PlansPageLocators.UPGRADEBUTTON, ElementType.Xpath);
//				if (flag) {
//					Thread.sleep(2500);
//					loginShopify();
//					url = webDB.driver.getCurrentUrl();
//					propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
//					prop.load(new FileInputStream(propertyFilePath));
//					String PageUrl = prop.getProperty("ShopifyAdminURL");
//					flag = url.contains(PageUrl);
//					if (flag) {
//						log.info("Landed on Shopify Admin page");
//						Thread.sleep(2500);
//						String text = webDB.getText(PlansPageLocators.SHOPIFYSUBTITLE, ElementType.Xpath);
//						flag = text.contains("This app bills you for usage charges");
//						if (flag) {
//							log.info("Verified subtitles");
//						}
//						String text1 = webDB.getText(PlansPageLocators.SHOPIFYSUBSCRIPTIONDETAILS, ElementType.Xpath);
//						flag = text1.contains("New subscription: Free");
//						if (flag) {
//							log.info("Verified Subscription details");
//						}
//						flag = webDB.isElementDisplayed(PlansPageLocators.BILLDETAILS, ElementType.Xpath);
//						if (flag) {
//							flag = webDB.isElementDisplayed(PlansPageLocators.SUBCRIPTIONBILLDETAILS,
//									ElementType.Xpath);
//							if (flag) {
//								flag = webDB.waitForElement(PlansPageLocators.ULTIMATE_PLAN_APPROVEBUTTON,
//										ElementType.Xpath);
//								if (flag) {
//									webDB.driver.navigate().to(planPageUrl);
//									Thread.sleep(2500);
//									flag = webDB.waitForElement(PlansPageLocators.PLAN_PAGE, ElementType.Xpath);
//								}
//							}
//						}
//					}
//				}
//			}
		}
		return flag;
	}

	/**
	 * Verify Subscription Navigation
	 *
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static boolean verifySubscriptionNavigation()
			throws InterruptedException, FileNotFoundException, IOException {
		flag = verifyCustomTemplateSubscriptionNavigation();
		if (flag) {
			log.info("Custom templates navigation");
			flag = verifyExtensionTemplateSubscriptionNavigation();
			if (flag) {
				log.info("Extension templates navigation");
				flag = verifyPrivatewabaSubscriptionNavigation();
				if (flag) {
					log.info("private waba navigation");
					flag = verifyMarketCampaignsSubscriptionNavigation();
				}
			}
		}
		return flag;
	}

	/**
	 * Verify MarketCampaigns Subscription Navigation
	 *
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static boolean verifyMarketCampaignsSubscriptionNavigation()
			throws InterruptedException, FileNotFoundException, IOException {
		CommonFunctions.verifyMarketCampaignsPage();
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String planPageUrl = prop.getProperty("PlanspageURL");
		flag = webDB.waitForElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CampaignsLocators.MARKETCAMPAIGNS_TAB, ElementType.Xpath);
			Thread.sleep(2000);
			webDB.scrollToAnElement(PlansPageLocators.SMALLBUSINESSPLANSSUBSCRIBED, ElementType.Xpath);
			flag = webDB.isElementDisplayed(PlansPageLocators.DESCRIPTION, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2500);
				flag = webDB.isElementDisplayed(PlansPageLocators.SMALLBUSINESSPLANDESCRIPTION, ElementType.Xpath)
						&& webDB.isElementDisplayed(PlansPageLocators.RECOMMENDEDPLAN, ElementType.Xpath);
//				if (flag) {
//					flag = webDB.clickAnElement(PlansPageLocators.SMALLBUSINESSPLANSSUBSCRIBED, ElementType.Xpath);
//					if (flag) {
//						Thread.sleep(2500);
//						loginShopify();
//						url = webDB.driver.getCurrentUrl();
//						propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
//						prop.load(new FileInputStream(propertyFilePath));
//						String PageUrl = prop.getProperty("ShopifyAdminURL");
//						flag = url.contains(PageUrl);
//						if (flag) {
//							log.info("Landed on Shopify Admin page");
//							Thread.sleep(2500);
//							flag = webDB.isElementDisplayed(PlansPageLocators.BILLDETAILS, ElementType.Xpath);
//							if (flag) {
//								Thread.sleep(2500);
//								flag = webDB.isElementDisplayed(PlansPageLocators.SUBCRIPTIONBILLDETAILS,
//										ElementType.Xpath);
//								if (flag) {
//									flag = webDB.isElementDisplayed(PlansPageLocators.ULTIMATE_PLAN_APPROVEBUTTON,
//											ElementType.Xpath);
//									if (flag) {
//										webDB.driver.navigate().to(planPageUrl);
//										Thread.sleep(2500);
//										flag = webDB.waitForElement(PlansPageLocators.PLAN_PAGE, ElementType.Xpath);
//									}
//								}
//							}
//						}
//					}
//				}
			}
		}
		return flag;
	}

	/**
	 * Verify ExtensionTemplate Subscription Navigation
	 *
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static boolean verifyExtensionTemplateSubscriptionNavigation()
			throws InterruptedException, FileNotFoundException, IOException {
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String planPageUrl = prop.getProperty("PlanspageURL");
		PlansPage.changePlanAPI(PlansPageLocators.FREE);
		webDB.driver.navigate().refresh();
		CommonFunctions.verifyMessagingPage();
		Thread.sleep(2500);
		webDB.moveToElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		flag = webDB.waitForElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
		if (flag) {
//			webDB.clickAnElement(TemplatesLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
//			flag = webDB.isElementDisplayed(TemplatesLocators.CREATE_TEMPLATES, ElementType.Xpath);
//			if (flag) {
//				CommonFunctions.verifyPlansPage();
//				log.info("Switch plan page");
//				changeSmallBusinessPlan();
//				log.info("Switch Business plan page");
//				changeFreePlan();
//				log.info("Free plan page");
//			} else {
//				log.info("Already in Free plan page");
//				CommonFunctions.verifyTemplatesPage();
//				webDB.clickAnElement(TemplatesLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
//				flag = true;
//			}
//			CommonFunctions.verifyTemplatesPage();
			webDB.clickAnElement(AutomationLocators.EXTENSION_TEMPLATES, ElementType.Xpath);
			Thread.sleep(2500);
			webDB.moveToElement(PlansPageLocators.SMALLBUSINESSPLANSSUBSCRIBED, ElementType.Xpath);
			flag = webDB.isElementDisplayed(PlansPageLocators.DESCRIPTION, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2500);
				flag = webDB.isElementDisplayed(PlansPageLocators.SMALLBUSINESSPLANDESCRIPTION, ElementType.Xpath)
						&& webDB.isElementDisplayed(PlansPageLocators.RECOMMENDEDPLAN, ElementType.Xpath);
//				if (flag) {
//					flag = webDB.clickAnElement(PlansPageLocators.SMALLBUSINESSPLANSSUBSCRIBED, ElementType.Xpath);
//					if (flag) {
//						Thread.sleep(2500);
//						loginShopify();
//						url = webDB.driver.getCurrentUrl();
//						propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
//						prop.load(new FileInputStream(propertyFilePath));
//						String PageUrl = prop.getProperty("ShopifyAdminURL");
//						flag = url.contains(PageUrl);
//						if (flag) {
//							log.info("Landed on Shopify Admin page");
//							Thread.sleep(2500);
//							flag = webDB.isElementDisplayed(PlansPageLocators.BILLDETAILS, ElementType.Xpath);
//							if (flag) {
//								Thread.sleep(2500);
//								flag = webDB.isElementDisplayed(PlansPageLocators.SUBCRIPTIONBILLDETAILS,
//										ElementType.Xpath);
//								if (flag) {
//									flag = webDB.isElementDisplayed(PlansPageLocators.ULTIMATE_PLAN_APPROVEBUTTON,
//											ElementType.Xpath);
//									if (flag) {
//										webDB.driver.navigate().to(planPageUrl);
//										Thread.sleep(2500);
//										flag = webDB.waitForElement(PlansPageLocators.PLAN_PAGE, ElementType.Xpath);
//									}
//								}
//
//							}
//						}
//					}
//				}
			}
		}
		return flag;
	}

	/**
	 * Verify Privatewaba Subscription Navigation
	 *
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static boolean verifyPrivatewabaSubscriptionNavigation()
			throws InterruptedException, FileNotFoundException, IOException {
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String planPageUrl = prop.getProperty("PlanspageURL");
		CommonFunctions.VerifyWhatsAPPAPIPage();
		flag = webDB.waitForElement(MyWhatsAppLocators.WHATSAPPAPI_PAGE_TAB, ElementType.Xpath);
		if (flag) {
			flag = webDB.isElementDisplayed(MessagingLocators.MANUAL_MESSAGING_TAB, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(MyWhatsAppLocators.WHATSAPPAPI_PAGE_TAB, ElementType.Xpath);
				flag = webDB.isElementDisplayed(PlansPageLocators.DESCRIPTION, ElementType.Xpath);
				if (flag) {
					Thread.sleep(2500);
					flag = webDB.isElementDisplayed(PlansPageLocators.SMALLBUSINESSPLANDESCRIPTION, ElementType.Xpath)
							&& webDB.isElementDisplayed(PlansPageLocators.RECOMMENDEDPLAN, ElementType.Xpath);
//					if (flag) {
//						flag = webDB.clickAnElement(PlansPageLocators.SMALLBUSINESSPLANSSUBSCRIBED, ElementType.Xpath);
//						if (flag) {
//							Thread.sleep(2500);
//							loginShopify();
//							url = webDB.driver.getCurrentUrl();
//							propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
//							prop.load(new FileInputStream(propertyFilePath));
//							String PageUrl = prop.getProperty("ShopifyAdminURL");
//							flag = url.contains(PageUrl);
//							if (flag) {
//								log.info("Landed on Shopify Admin page");
//								Thread.sleep(2500);
//								flag = webDB.isElementDisplayed(PlansPageLocators.BILLDETAILS, ElementType.Xpath);
//								if (flag) {
//									Thread.sleep(2500);
//									flag = webDB.isElementDisplayed(PlansPageLocators.SUBCRIPTIONBILLDETAILS,
//											ElementType.Xpath);
//									if (flag) {
//										flag = webDB.isElementDisplayed(PlansPageLocators.ULTIMATE_PLAN_APPROVEBUTTON,
//												ElementType.Xpath);
//										if (flag) {
//											webDB.driver.navigate().to(planPageUrl);
//											Thread.sleep(2500);
//											flag = webDB.waitForElement(PlansPageLocators.PLAN_PAGE, ElementType.Xpath);
//										}
//									}
//								}
//							}
//						}
//					}
				}
			}
		}
		return flag;
	}

	/**
	 * Verify CustomTemplate Subscription Navigation
	 *
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static boolean verifyCustomTemplateSubscriptionNavigation()
			throws InterruptedException, FileNotFoundException, IOException {
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String planPageUrl = prop.getProperty("PlanspageURL");
		webDB.driver.navigate().refresh();
		CommonFunctions.verifyMarketCampaignsPage();
		webDB.moveToElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
		flag = webDB.waitForElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AutomationLocators.CUSTOMTEMPLATES_TAB, ElementType.Xpath);
			Thread.sleep(2500);
			webDB.scrollToAnElement(PlansPageLocators.SMALLBUSINESSPLANSSUBSCRIBED, ElementType.Xpath);
			flag = webDB.isElementDisplayed(PlansPageLocators.DESCRIPTION, ElementType.Xpath);
			if (flag) {
				Thread.sleep(2500);
				flag = webDB.isElementDisplayed(PlansPageLocators.SMALLBUSINESSPLANDESCRIPTION, ElementType.Xpath)
						&& webDB.isElementDisplayed(PlansPageLocators.RECOMMENDEDPLAN, ElementType.Xpath);
//				if (flag) {
//					flag = webDB.clickAnElement(PlansPageLocators.SMALLBUSINESSPLANSSUBSCRIBED, ElementType.Xpath);
//					if (flag) {
//						Thread.sleep(2500);
//						loginShopify();
//						url = webDB.driver.getCurrentUrl();
//						propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
//						prop.load(new FileInputStream(propertyFilePath));
//						String PageUrl = prop.getProperty("ShopifyAdminURL");
//						flag = url.contains(PageUrl);
//						if (flag) {
//							log.info("Landed on Shopify Admin page");
//							webDB.waitForElement(PlansPageLocators.BILLDETAILS, ElementType.Xpath);
//							Thread.sleep(3500);
//							flag = webDB.isElementDisplayed(PlansPageLocators.BILLDETAILS, ElementType.Xpath);
//							if (flag) {
//								Thread.sleep(2500);
//								flag = webDB.isElementDisplayed(PlansPageLocators.SUBCRIPTIONBILLDETAILS,
//										ElementType.Xpath);
//								if (flag) {
//									flag = webDB.isElementDisplayed(PlansPageLocators.ULTIMATE_PLAN_APPROVEBUTTON,
//											ElementType.Xpath);
//									if (flag) {
//										webDB.driver.navigate().to(planPageUrl);
//										Thread.sleep(2500);
//										flag = webDB.waitForElement(PlansPageLocators.PLAN_PAGE, ElementType.Xpath);
//									}
//								}
//							}
//						}
//					}
//				}
			}
		}
		return flag;
	}

	/**
	 * Verify Visible section in Free user
	 *
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static boolean verifyVisibleSection() throws InterruptedException, FileNotFoundException, IOException {
		flag = webDB.waitForElement(CommonLocators.ADMIN_PROFILE, ElementType.CssSelector);
		if (flag) {
			webDB.clickAnElement(CommonLocators.ADMIN_PROFILE, ElementType.CssSelector);
			flag = webDB.waitForElement(PlansPageLocators.PLAN_PAGE, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PlansPageLocators.PLAN_PAGE, ElementType.Xpath);
				flag = webDB.isElementDisplayed(CustomersLocators.OPTIN_NUMBERS_TAB, ElementType.Xpath);
				if (flag) {
					flag = webDB.isElementDisplayed(SettingsLocators.ADMIN_CONFIGURATION_TAB, ElementType.Xpath);
					if (flag) {
						flag = webDB.isElementDisplayed(AnalyticsLocators.ANALYTICS_TAB, ElementType.Xpath);
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyChangePlanVerifyRecommended()
			throws FileNotFoundException, IOException, InterruptedException {
		flag = webDB.waitForElement(CommonLocators.ADMIN_PROFILE, ElementType.CssSelector);
		if (flag) {
			webDB.clickAnElement(CommonLocators.ADMIN_PROFILE, ElementType.CssSelector);
			flag = webDB.waitForElement(PlansPageLocators.PLAN_PAGE, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PlansPageLocators.PLAN_PAGE, ElementType.Xpath);
				flag = webDB.verifyResponseAPI("GetShopID");
				if (flag) {
					webDB.driver.navigate().refresh();
					Thread.sleep(2000);
					webDB.driver.navigate().refresh();
					flag = webDB.verifyResponseAPI("ChangeUltimatePlan");
					if (flag) {
						System.out.println("Switch to Ultimate Plan");
						webDB.driver.navigate().refresh();
						Thread.sleep(4000);
						webDB.driver.navigate().refresh();
						webDB.moveToElement(PlansPageLocators.ULTIMATEPLANSUBSCRIBEDBTN, ElementType.Xpath);
						flag = webDB.isElementDisplayed(PlansPageLocators.ULTIMATEPLAN, ElementType.Xpath);
						if (flag) {
							flag = webDB.isElementDisplayed(PlansPageLocators.ULTIMATEPLANSUBSCRIBEDBTN,
									ElementType.Xpath);
							if (flag) {
								flag = webDB.isElementDisplayed(PlansPageLocators.SMALLBUSINESSRECOMMENDEDPLAN,
										ElementType.Xpath);
								if (flag) {
									flag = webDB.verifyResponseAPI("ChangeSmallBusinessPlan");
									if (flag) {
										System.out.println("Switch to Small Business Plan");
										CommonFunctions.clearLocalStorage();
										webDB.driver.navigate().refresh();
										CommonFunctions.verifyPlansPage();
										Thread.sleep(4000);
										webDB.driver.navigate().refresh();
										webDB.moveToElement(PlansPageLocators.VIEWALLPLANSBUTTON, ElementType.Xpath);
										flag = webDB.waitForElement(PlansPageLocators.VIEWALLPLANSBUTTON,
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

	/**
	 * Change Free plan
	 *
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static boolean changeFreePlanAPI() throws InterruptedException, FileNotFoundException, IOException {
		flag = webDB.waitForElement(PlansPageLocators.FREEPLANSUBSCRIBED, ElementType.Xpath);
		if (flag) {
			Thread.sleep(1500);
			webDB.verifyResponseAPI("GetShopID");
			flag = webDB.verifyResponseAPI("ChangeSmalltoFreePlan");
			if (flag) {
				System.out.println("Changed Free plan");
				CommonFunctions.clearLocalStorage();
				CommonFunctions.clearCookies();
				Thread.sleep(1500);
				webDB.driver.navigate().refresh();
				CommonFunctions.verifyPlansPage();
				webDB.moveToElement(PlansPageLocators.UPGRADEBUTTON, ElementType.Xpath);
				flag = webDB.waitForElement(PlansPageLocators.UPGRADEBUTTON, ElementType.Xpath);
			}
		}
		return flag;
	}

	/**
	 * Change Small business plan
	 *
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static boolean changeSmallBusinessPlanAPI() throws InterruptedException, FileNotFoundException, IOException {
		webDB.verifyResponseAPI("GetShopID");
		flag = webDB.verifyResponseAPI("ChangeSmallBusinessPlan");
		if (flag) {
			System.out.println("Changed Small business plan");
			CommonFunctions.clearLocalStorage();
			CommonFunctions.clearCookies();
			Thread.sleep(1500);
			webDB.driver.navigate().refresh();
			Thread.sleep(1500);
			webDB.pressEscapeKey();
		}
		return flag;

	}

	/**
	 * Change Basic plan
	 *
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static boolean changeFreePlan() throws InterruptedException, FileNotFoundException, IOException {
		System.out.println("hello");
		Thread.sleep(1500);
		PlansPage.changePlanAPI(PlansPageLocators.FREE);
		webDB.moveToElement(PlansPageLocators.UPGRADEBUTTON, ElementType.Xpath);
//			flag = webDB.waitForElement(PlansPageLocators.ULTIMATE_PLAN_APPROVEBUTTON, ElementType.Xpath);
//			if (flag) {
//				Thread.sleep(2500);
//				webDB.clickAnElement(PlansPageLocators.ULTIMATE_PLAN_APPROVEBUTTON, ElementType.Xpath);
//			} else {
//				System.out.println("done");
//				flag = true;
//			}
		flag = webDB.waitForElement(PlansPageLocators.UPGRADEBUTTON, ElementType.Xpath);
		return flag;

	}

	public static boolean verifySubmitRequest() throws InterruptedException {
		webDB.moveToElement(PlansPageLocators.OTHERSTITLE, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.waitForElement(PlansPageLocators.OTHERSTITLE, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PlansPageLocators.OTHERSTITLE, ElementType.Xpath);
			webDB.moveToElement(PlansPageLocators.CONTACTUSHERE, ElementType.Xpath);
			Thread.sleep(1000);
			flag = webDB.waitForElement(PlansPageLocators.CONTACTUSHERE, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PlansPageLocators.CONTACTUSHERE, ElementType.Xpath);
				flag = webDB.waitForElement(PlansPageLocators.CONTACTUSHERE, ElementType.Xpath);
				if (flag) {
					webDB.sendTextToAnElement(PlansPageLocators.REQUESTDESCRIPTION, PlansPageLocators.CHATBOT_VALUE,
							ElementType.Xpath);
					flag = webDB.waitForElement(PlansPageLocators.SUBMIT_BUTTON, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(PlansPageLocators.SUBMIT_BUTTON, ElementType.Xpath);
						flag = webDB.waitForElement(PlansPageLocators.CONTACTUSHERE, ElementType.Xpath);
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyContactUsButton() throws InterruptedException {
		webDB.moveToElement(PlansPageLocators.OTHERSTITLE, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.waitForElement(PlansPageLocators.OTHERSTITLE, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PlansPageLocators.OTHERSTITLE, ElementType.Xpath);
			webDB.moveToElement(PlansPageLocators.CONTACTUSHERE, ElementType.Xpath);
			Thread.sleep(1000);
			flag = webDB.waitForElement(PlansPageLocators.CONTACTUSHERE, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PlansPageLocators.CONTACTUSHERE, ElementType.Xpath);
				flag = webDB.waitForElement(PlansPageLocators.CONTACTUSHERE, ElementType.Xpath);
				if (flag) {
					webDB.sendTextToAnElement(PlansPageLocators.REQUESTDESCRIPTION, PlansPageLocators.CHATBOT_VALUE,
							ElementType.Xpath);
					webDB.waitForElement(PlansPageLocators.FIRST_RADIOBUTTON, ElementType.Xpath);
					if (flag) {
						webDB.javaScriptClickAnElement(PlansPageLocators.FIRST_RADIOBUTTON, ElementType.Xpath);
						Thread.sleep(1000);
						webDB.waitForElement(PlansPageLocators.SECOND_RADIOBUTTON, ElementType.Xpath);
						if (flag) {
							webDB.javaScriptClickAnElement(PlansPageLocators.SECOND_RADIOBUTTON, ElementType.Xpath);
							Thread.sleep(1000);
							webDB.waitForElement(PlansPageLocators.THIRD_RADIOBUTTON, ElementType.Xpath);
							if (flag) {
								webDB.javaScriptClickAnElement(PlansPageLocators.THIRD_RADIOBUTTON, ElementType.Xpath);
								Thread.sleep(1000);
								webDB.waitForElement(PlansPageLocators.FOURTH_RADIOBUTTON, ElementType.Xpath);
								if (flag) {
									webDB.javaScriptClickAnElement(PlansPageLocators.FOURTH_RADIOBUTTON,
											ElementType.Xpath);
									Thread.sleep(1000);
									flag = webDB.waitForElement(PlansPageLocators.SUBMIT_BUTTON, ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(PlansPageLocators.SUBMIT_BUTTON, ElementType.Xpath);
										flag = webDB.waitForElement(PlansPageLocators.CONTACTUSHERE, ElementType.Xpath);
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

	public static boolean verifyContactUsCloseButton() throws InterruptedException {
		webDB.moveToElement(PlansPageLocators.OTHERSTITLE, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.waitForElement(PlansPageLocators.OTHERSTITLE, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PlansPageLocators.OTHERSTITLE, ElementType.Xpath);
			webDB.moveToElement(PlansPageLocators.CONTACTUSHERE, ElementType.Xpath);
			Thread.sleep(1000);
			flag = webDB.waitForElement(PlansPageLocators.CONTACTUSHERE, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PlansPageLocators.CONTACTUSHERE, ElementType.Xpath);
				flag = webDB.waitForElement(PlansPageLocators.CLOSE_BUTTON, ElementType.CssSelector);
				if (flag) {
					webDB.clickAnElement(PlansPageLocators.CLOSE_BUTTON, ElementType.CssSelector);
					flag = webDB.waitForElement(PlansPageLocators.CONTACTUSHERE, ElementType.Xpath);
				}
			}
		}
		return flag;
	}

	/**
	 * Change plan
	 *
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static boolean changePlanAPI(int planId) throws InterruptedException, FileNotFoundException, IOException {
		webDB.verifyResponseAPI("GetShopID");
		flag = webDB.verifyChangePlanResponseAPI("ChangePlan", planId);
		if (flag) {
			System.out.println("Changed plan Successfully");
			System.out.println(planId);
			Thread.sleep(2500);
			webDB.driver.navigate().refresh();
			flag = verifyPlansChanges(planId);
		}
		return flag;
	}

	public static boolean verifyPlansChanges(int planid) throws InterruptedException {
		webDB.clickAnElement(CommonLocators.HOME_BTN, ElementType.Xpath);
		Thread.sleep(1000);
		String temp = webDB.getText(CommonLocators.PLAN_NAME, ElementType.Xpath);
		System.out.println(temp + "plan name");
		System.out.println(planid + "id number");
		if ((planid == 1) || (temp.contains("Old FREE"))) {
			System.out.println("Switched to Old free plan");
			flag = true;
		} else if (temp.equals("Old PRO") && planid == 2) {
			System.out.println("Switched to Old pro plan");
			flag = true;
		} else if (temp.equals("Old ULTIMATE") && planid == 3) {
			System.out.println("Switched to Old ultimate plan");
			flag = true;
		} else if (temp.equals("FREE") && planid == 4) {
			System.out.println("Switched to  free plan");
			flag = true;
		} else if (temp.equals("GROWTH") && planid == 5) {
			System.out.println("Switched to growth  plan");
			flag = true;
		} else if (temp.equals("ENGAGE") && planid == 6) {
			System.out.println("Switched to engage plan");
			flag = true;
		} else {
			System.out.println("Invalid Plan");
			flag = false;
		}
		return flag;

	}

	public static boolean verifyPlansFeatureList(int planid)
			throws FileNotFoundException, IOException, InterruptedException {
		if (planid == 1) {
			flag = verifyOldFreeFeatureList();
		} else if (planid == 2) {
			flag = verifyOldProFeatureList();
		} else if (planid == 3) {
			flag = verifyOldUlitimateFeatureList();
		} else if (planid == 4) {
			flag = verifyNewFreeFeatureList();
		} else if (planid == 5) {
			flag = verifyGrowthFeatureList();
		} else if (planid == 6) {
			flag = verifyEngageFeatureList();
		} else {
			System.out.println("Invalid Plan");
			flag = false;
		}
		return flag;

	}

	public static boolean verifyOldFreeFeatureList() throws FileNotFoundException, IOException, InterruptedException {
		webDB.moveToElement(PlansPageLocators.AUTOMATED_MESSAGE, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.waitForElement(PlansPageLocators.AUTOMATED_MESSAGE, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PlansPageLocators.SPIN_WHEEL, ElementType.Xpath);
			Thread.sleep(1000);
			flag = webDB.waitForElement(PlansPageLocators.SPIN_WHEEL, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(PlansPageLocators.CHAT_WIDGET, ElementType.Xpath);
				Thread.sleep(1000);
				flag = webDB.waitForElement(PlansPageLocators.CHAT_WIDGET, ElementType.Xpath);
				if (flag) {
					webDB.moveToElement(PlansPageLocators.SHARE_WIDGET, ElementType.Xpath);
					Thread.sleep(1000);
					flag = webDB.waitForElement(PlansPageLocators.SHARE_WIDGET, ElementType.Xpath);
					if (flag) {
						log.info("Verified Old Free Plan Features List");
					}
				}
			}
		}
		return flag;

	}

	public static boolean verifyOldProFeatureList() throws FileNotFoundException, IOException, InterruptedException {
		webDB.moveToElement(PlansPageLocators.AUTOMATED_MESSAGE, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.waitForElement(PlansPageLocators.AUTOMATED_MESSAGE, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PlansPageLocators.WBA_WITH_GREENTICK, ElementType.Xpath);
			Thread.sleep(1000);
			flag = webDB.waitForElement(PlansPageLocators.WBA_WITH_GREENTICK, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(PlansPageLocators.MARKETING_CAMPAIGN, ElementType.Xpath);
				Thread.sleep(1000);
				flag = webDB.waitForElement(PlansPageLocators.MARKETING_CAMPAIGN, ElementType.Xpath);
				if (flag) {
					webDB.moveToElement(PlansPageLocators.CUSTOMER_GROUPS, ElementType.Xpath);
					Thread.sleep(1000);
					flag = webDB.waitForElement(PlansPageLocators.CUSTOMER_GROUPS, ElementType.Xpath);
					if (flag) {
						log.info("Verified Old Pro Plan Features List");
					}
				}
			}
		}
		return flag;

	}

	public static boolean verifyOldUlitimateFeatureList()
			throws FileNotFoundException, IOException, InterruptedException {
		webDB.moveToElement(PlansPageLocators.WBA_WITH_GREENTICK, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.waitForElement(PlansPageLocators.WBA_WITH_GREENTICK, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PlansPageLocators.AUTOMATED_MESSAGE, ElementType.Xpath);
			Thread.sleep(1000);
			flag = webDB.waitForElement(PlansPageLocators.AUTOMATED_MESSAGE, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(PlansPageLocators.MARKETING_CAMPAIGN, ElementType.Xpath);
				Thread.sleep(1000);
				flag = webDB.waitForElement(PlansPageLocators.MARKETING_CAMPAIGN, ElementType.Xpath);
				if (flag) {
					webDB.moveToElement(PlansPageLocators.CUSTOMER_GROUPS, ElementType.Xpath);
					Thread.sleep(1000);
					flag = webDB.waitForElement(PlansPageLocators.CUSTOMER_GROUPS, ElementType.Xpath);
					if (flag) {
						log.info("Verified Old Ultimate Plan Features List");
					}
				}
			}
		}
		return flag;

	}

	public static boolean verifyNewFreeFeatureList() throws FileNotFoundException, IOException, InterruptedException {
		webDB.moveToElement(PlansPageLocators.AUTOMATED_MESSAGE, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.waitForElement(PlansPageLocators.AUTOMATED_MESSAGE, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PlansPageLocators.SPIN_WHEEL, ElementType.Xpath);
			Thread.sleep(1000);
			flag = webDB.waitForElement(PlansPageLocators.SPIN_WHEEL, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(PlansPageLocators.CHAT_WIDGET, ElementType.Xpath);
				Thread.sleep(1000);
				flag = webDB.waitForElement(PlansPageLocators.CHAT_WIDGET, ElementType.Xpath);
				if (flag) {
					webDB.moveToElement(PlansPageLocators.SHARE_WIDGET, ElementType.Xpath);
					Thread.sleep(1000);
					flag = webDB.waitForElement(PlansPageLocators.SHARE_WIDGET, ElementType.Xpath);
					if (flag) {
						log.info("Verified Free Plan Features List");
					}
				}
			}
		}
		return flag;

	}

	public static boolean verifyGrowthFeatureList() throws FileNotFoundException, IOException, InterruptedException {
		webDB.moveToElement(PlansPageLocators.AUTOMATED_MESSAGE, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.waitForElement(PlansPageLocators.AUTOMATED_MESSAGE, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PlansPageLocators.SPIN_WHEEL, ElementType.Xpath);
			Thread.sleep(1000);
			flag = webDB.waitForElement(PlansPageLocators.SPIN_WHEEL, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(PlansPageLocators.CHAT_WIDGET, ElementType.Xpath);
				Thread.sleep(1000);
				flag = webDB.waitForElement(PlansPageLocators.CHAT_WIDGET, ElementType.Xpath);
				if (flag) {
					webDB.moveToElement(PlansPageLocators.SHARE_WIDGET, ElementType.Xpath);
					Thread.sleep(1000);
					flag = webDB.waitForElement(PlansPageLocators.SHARE_WIDGET, ElementType.Xpath);
					if (flag) {
						webDB.moveToElement(PlansPageLocators.MARKETING_CAMPAIGN, ElementType.Xpath);
						Thread.sleep(1000);
						flag = webDB.waitForElement(PlansPageLocators.MARKETING_CAMPAIGN, ElementType.Xpath);
						if (flag) {
							webDB.moveToElement(PlansPageLocators.CUSTOMER_GROUPS, ElementType.Xpath);
							Thread.sleep(1000);
							flag = webDB.waitForElement(PlansPageLocators.CUSTOMER_GROUPS, ElementType.Xpath);
							if (flag) {
								log.info("Verified Growth Plan Features List");
							}
						}
					}
				}
			}
		}
		return flag;

	}

	public static boolean verifyEngageFeatureList() throws FileNotFoundException, IOException, InterruptedException {
		webDB.moveToElement(PlansPageLocators.SHAREDTEAM_INBOX_FEATURES, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.waitForElement(PlansPageLocators.SHAREDTEAM_INBOX_FEATURES, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PlansPageLocators.SHAREDTEAM_INBOX_FEATURES_DESCRIPTION, ElementType.Xpath);
			String text = webDB.getText(PlansPageLocators.SHAREDTEAM_INBOX_FEATURES_DESCRIPTION, ElementType.Xpath)
					.trim();
			System.out.println(text);
			flag = text.contains(PlansPageLocators.SHAREDTEAM_INBOX_FEATURES_DESCRIPTION_VALUE);
			if (flag) {
				log.info("Verified Shared Team Inbox and Description");
			}
		}
		return flag;

	}

	public static boolean verifyKnowmore() throws FileNotFoundException, IOException, InterruptedException {
		flag = webDB.waitForElement(PlansPageLocators.KNOW_MORE_BUTTON, ElementType.Xpath);
		if (flag) {
			Thread.sleep(1000);
			flag = webDB.isElementDisplayed(PlansPageLocators.UPGRADEBUTTON, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PlansPageLocators.KNOW_MORE_BUTTON, ElementType.Xpath);
				url = webDB.driver.getCurrentUrl();
				propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
				prop.load(new FileInputStream(propertyFilePath));
				String PageUrl = prop.getProperty("PlanspageURL");
				flag = url.contains(PageUrl);
				if (flag) {
					log.info("Verified on Plans page and Know More Button");
				}
			}
		}
		return flag;
	}

	public static boolean verifyPromtionalKnowmore() throws FileNotFoundException, IOException, InterruptedException {
		flag = webDB.waitForElement(PlansPageLocators.PROMOTIONAL_KNOW_MORE_BUTTON, ElementType.Xpath);
		if (flag) {
			Thread.sleep(1000);
			webDB.clickAnElement(PlansPageLocators.PROMOTIONAL_KNOW_MORE_BUTTON, ElementType.Xpath);
			url = webDB.driver.getCurrentUrl();
			propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
			prop.load(new FileInputStream(propertyFilePath));
			String PageUrl = prop.getProperty("PlanspageURL");
			flag = url.contains(PageUrl);
			if (flag) {
				log.info("Verified on Plans page and Know More Button");
			}
		}
		return flag;
	}

	public static boolean verifyPlanNote() throws FileNotFoundException, IOException, InterruptedException {
		flag = webDB.waitForElement(PlansPageLocators.GROWTHPLANTEXT, ElementType.Xpath);
		if (flag) {
			Thread.sleep(1000);
			String text = webDB.getText(PlansPageLocators.GROWTHPLANTEXT, ElementType.Xpath);
			flag = text.equals(PlansPageLocators.GROWTHPLANTEXT_VALUE);
			if (flag) {
				log.info("Verified on Growth Plans Text and Release note");
				Thread.sleep(1000);
				flag = webDB.isElementDisplayed(PlansPageLocators.GROWTHPLANNOTE, ElementType.Xpath);
			}
		}
		return flag;
	}

	public static boolean verifyUpdatePlan() throws InterruptedException {
		flag = webDB.waitForElement(PlansPageLocators.UPDATE_PLAN, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PlansPageLocators.UPDATE_PLAN, ElementType.Xpath);
			Thread.sleep(2000);
			flag = webDB.waitForElement(LoginPageLocators.USERNAME, ElementType.CssSelector);
			String url = webDB.driver.getCurrentUrl();
			flag = url.contains(PlansPageLocators.SHOPIFY);

		}
		return flag;
	}

	public static boolean verifyGetStartedButton() throws Exception {
		flag = verifyEngagePlanPromotionalTile();
		if (flag) {
			webDB.moveToElement(PlansPageLocators.GET_STARTED, ElementType.Xpath);
			Thread.sleep(1000);
			flag = webDB.waitForElement(PlansPageLocators.GET_STARTED, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PlansPageLocators.GET_STARTED, ElementType.Xpath);
				flag = webDB.waitForElement(LoginPageLocators.USERNAME, ElementType.CssSelector);
				String url = webDB.driver.getCurrentUrl();
				flag = url.contains(PlansPageLocators.SHOPIFY);
			}

		}
		return flag;
	}

	public static boolean verifyEngagePlanPromotionalTile() throws Exception {
		webDB.moveToElement(PlansPageLocators.ENGAGEPLAN_DETAIL, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.waitForElement(PlansPageLocators.ENGAGEPLAN_DETAIL, ElementType.Xpath);
		if (flag) {
			flag = webDB.waitForElement(PlansPageLocators.ENGAGEPLAN_PROMOTILE, ElementType.Xpath);
		}
		return flag;
	}

	public static boolean verifySharedInboxPlans() throws Exception {
		flag = CommonFunctions.verifySharedTeamInboxPage();
		if (flag) {
			flag = webDB.waitForElement(PlansPageLocators.ENGAGEPLAN_SUBSCRIBE_BTN, ElementType.Xpath);
		}
		return flag;
	}

	public static boolean verifyNewStoreFeatureList() throws Exception {
		webDB.moveToElement(PlansPageLocators.AUTOMATED_MESSAGE, ElementType.Xpath);
		Thread.sleep(1000);
		flag = webDB.waitForElement(PlansPageLocators.AUTOMATED_MESSAGE, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PlansPageLocators.SPIN_WHEEL, ElementType.Xpath);
			Thread.sleep(1000);
			flag = webDB.waitForElement(PlansPageLocators.SPIN_WHEEL, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(PlansPageLocators.CHAT_WIDGET, ElementType.Xpath);
				Thread.sleep(1000);
				flag = webDB.waitForElement(PlansPageLocators.CHAT_WIDGET, ElementType.Xpath);
				if (flag) {
					webDB.moveToElement(PlansPageLocators.SHARE_WIDGET, ElementType.Xpath);
					Thread.sleep(1000);
					flag = webDB.waitForElement(PlansPageLocators.SHARE_WIDGET, ElementType.Xpath);
					if (flag) {
						webDB.moveToElement(PlansPageLocators.MARKETING_CAMPAIGN, ElementType.Xpath);
						Thread.sleep(1000);
						flag = webDB.waitForElement(PlansPageLocators.MARKETING_CAMPAIGN, ElementType.Xpath);
						if (flag) {
							webDB.moveToElement(PlansPageLocators.CUSTOMER_GROUP, ElementType.Xpath);
							Thread.sleep(1000);
							flag = webDB.waitForElement(PlansPageLocators.CUSTOMER_GROUP, ElementType.Xpath);
							if (flag) {
								webDB.moveToElement(PlansPageLocators.WBA_WITH_GREENTICK, ElementType.Xpath);
								Thread.sleep(1000);
								flag = webDB.waitForElement(PlansPageLocators.WBA_WITH_GREENTICK, ElementType.Xpath);
								if (flag) {
									webDB.moveToElement(PlansPageLocators.WHATSAPPCHATBOT, ElementType.Xpath);
									Thread.sleep(1000);
									flag = webDB.waitForElement(PlansPageLocators.WHATSAPPCHATBOT, ElementType.Xpath);
									if (flag) {
										webDB.moveToElement(PlansPageLocators.SHAREDTEAMINBOX, ElementType.Xpath);
										Thread.sleep(1000);
										flag = webDB.waitForElement(PlansPageLocators.SHAREDTEAMINBOX,
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

	public static boolean verifyEngagePlanContent() throws FileNotFoundException, IOException, InterruptedException {

		webDB.clickAnElement(PlansPageLocators.plansdropdown, ElementType.Xpath);
		webDB.clickAnElement(PlansPageLocators.planspage, ElementType.Xpath);
		String url = webDB.driver.getCurrentUrl();
		flag = url.contains("pricing");
		if (flag) {
			flag = webDB.isElementDisplayed(PlansPageLocators.Engagetext, ElementType.Xpath);
			log.info(flag);
			if (!flag) {
				flag = PlansPage.changePlanAPI(PlansPageLocators.ENGAGE);
				if (flag) {
					webDB.clickAnElement(PlansPageLocators.plansdropdown, ElementType.Xpath);
					webDB.clickAnElement(PlansPageLocators.planspage, ElementType.Xpath);
				}
			}
			webDB.moveToElement(PlansPageLocators.Contenttext, ElementType.Xpath);
			Thread.sleep(1000);
			flag = webDB.waitForElement(PlansPageLocators.Contenttext, ElementType.Xpath);
			if (flag) {
				String text = webDB.getText(PlansPageLocators.Contenttext, ElementType.Xpath);
				log.info(text);
				flag = text.contains(PlansPageLocators.texttoverify);
			}
		}
		return flag;
	}

	public static boolean verifySubscribeButtonRedirection()
			throws FileNotFoundException, IOException, InterruptedException {

		webDB.clickAnElement(PlansPageLocators.plansdropdown, ElementType.Xpath);
		webDB.clickAnElement(PlansPageLocators.planspage, ElementType.Xpath);

		String url = webDB.driver.getCurrentUrl();
		flag = url.contains("pricing");
		if (flag) {
			flag = webDB.isElementDisplayed(PlansPageLocators.Engagetext, ElementType.Xpath);
			log.info(flag);

			if (!flag) {
				flag = PlansPage.changePlanAPI(PlansPageLocators.GROWTH);
				if (flag) {
					webDB.clickAnElement(PlansPageLocators.plansdropdown, ElementType.Xpath);
					webDB.clickAnElement(PlansPageLocators.planspage, ElementType.Xpath);
				}
			}

			String text = webDB.getText(PlansPageLocators.Engageplansubscribe, ElementType.Xpath);
			log.info(text);

			if (text.contains("Subscribed")) {
				log.info("Changing to growtsharedinboxh plan");
				flag = PlansPage.changePlanAPI(PlansPageLocators.GROWTH);
				webDB.clickAnElement(PlansPageLocators.plansdropdown, ElementType.Xpath);
				webDB.clickAnElement(PlansPageLocators.planspage, ElementType.Xpath);
			}

			webDB.clickAnElement(PlansPageLocators.Engageplansubscribe, ElementType.Xpath);
			Thread.sleep(1000);
			wait.until(ExpectedConditions.urlContains("accounts.shopify.com"));
			url = webDB.driver.getCurrentUrl();
			log.info(url);
			flag = url.contains("accounts.shopify.com");
			log.info(flag);
			if (flag) {
				webDB.driver.navigate().back();

				flag = PlansPage.changePlanAPI(PlansPageLocators.ENGAGE);
				if (flag) {
					webDB.clickAnElement(PlansPageLocators.plansdropdown, ElementType.Xpath);
					webDB.clickAnElement(PlansPageLocators.planspage, ElementType.Xpath);
					text = webDB.getText(PlansPageLocators.Engageplansubscribe, ElementType.Xpath);
					flag = text.contains("Subscribed");
				}
			}
		}
		return flag;
	}

	public static boolean verifyEngagePlanTile() throws FileNotFoundException, IOException, InterruptedException {

		String text = webDB.getText(CommonLocators.PLAN_NAME, ElementType.Xpath);
		flag = text.contains("ENGAGE");
		if (!flag) {
			flag = PlansPage.changePlanAPI(PlansPageLocators.ENGAGE);
		}
		text = webDB.getText(PlansPageLocators.ENGAGE_PLAN_GREETING_TILE, ElementType.Xpath);
		log.info(text);
		flag = text.contains(PlansPageLocators.ENGAGEGREETINGTILETEXT);
		log.info("Greeting tile for engage plan is showing");

		return flag;

	}

	public static boolean verifyEnableWabaPopUp() throws FileNotFoundException, IOException, InterruptedException {
		CommonFunctions.verifyNewStoreLoginPage("QA-SeptToken");
		String text = webDB.getText(CommonLocators.PLAN_NAME, ElementType.Xpath);
		flag = text.contains("ENGAGE");
		if (!flag) {
			flag = PlansPage.changePlanAPI(PlansPageLocators.ENGAGE);
		}
		text = webDB.getText(PlansPageLocators.ENGAGE_PLAN_GREETING_TILE, ElementType.Xpath);
		log.info(text);
		CommonFunctions.verifySharedTeamInboxPage();
		flag = webDB.waitForElement(PlansPageLocators.EMAILINPUT, ElementType.CssSelector);
		if (flag) {
			webDB.sendTextToAnElement(PlansPageLocators.EMAILINPUT, PlansPageLocators.EMAILID, ElementType.CssSelector);
			webDB.clickAnElement(PlansPageLocators.SUBMIT_BUTTON, ElementType.Xpath);
			flag = webDB.isElementDisplayed(PlansPageLocators.POPUPERROR, ElementType.CssSelector);
			log.info(flag);
			if (flag) {
				webDB.clickAnElement(PlansPageLocators.CLOSEPOPUPERROR, ElementType.CssSelector);
			}
		}
		return flag;
	}

	public static boolean verifyEngagePlanProductTour()
			throws FileNotFoundException, InterruptedException, IOException {

		Text = webDB.getText(CommonLocators.PLAN_NAME, ElementType.Xpath);
		flag = Text.contains("ENGAGE");
		log.info("Current plan is " + Text);
		log.info("Flag found is" + flag);
		if (!flag) {
			log.info("Current plan is changing to enage ");
			flag = PlansPage.changePlanAPI(PlansPageLocators.ENGAGE);
		}
		if (flag) {
			js.executeScript("sessionStorage.clear();");
			webDB.driver.navigate().refresh();
			flag = webDB.isElementDisplayed(CommonLocators.TOURPOPUP, ElementType.Xpath);

			if (flag) {
				webDB.clickAnElement(PlansPageLocators.START_TOUR, ElementType.Xpath);
				flag = webDB.isElementDisplayed(PlansPageLocators.PRODUCT_TOUR_DESCRIPTION, ElementType.Xpath);
				if (flag) {
					Text = webDB.getText(PlansPageLocators.TOUR_COUNT, ElementType.Xpath);
					flag = Text.contains("1/7");
					if (flag) {
						Text = webDB.getText(PlansPageLocators.PRODUCT_TOUR_DESCRIPTION, ElementType.Xpath);
						flag = Text.contains(PlansPageLocators.HOME_PAGE_TOUR);
					}
				}
				if (flag) {
					webDB.clickAnElement(PlansPageLocators.NEXT_BUTTON, ElementType.Xpath);
					Text = webDB.getText(PlansPageLocators.TOUR_COUNT, ElementType.Xpath);
					flag = Text.contains("2/7");
					if (flag) {
						Text = webDB.getText(PlansPageLocators.PRODUCT_TOUR_DESCRIPTION, ElementType.Xpath);
						flag = Text.contains(PlansPageLocators.MY_WHATSAPP_TOUR);
					}
				}
				if (flag) {
					webDB.clickAnElement(PlansPageLocators.NEXT_BUTTON, ElementType.Xpath);
					Text = webDB.getText(PlansPageLocators.TOUR_COUNT, ElementType.Xpath);
					flag = Text.contains("3/7");
					if (flag) {
						Text = webDB.getText(PlansPageLocators.PRODUCT_TOUR_DESCRIPTION, ElementType.Xpath);
						flag = Text.contains(PlansPageLocators.AUTOMATION_TOUR);
					}
				}
				if (flag) {
					webDB.clickAnElement(PlansPageLocators.NEXT_BUTTON, ElementType.Xpath);
					Text = webDB.getText(PlansPageLocators.TOUR_COUNT, ElementType.Xpath);
					flag = Text.contains("4/7");
					if (flag) {
						Text = webDB.getText(PlansPageLocators.PRODUCT_TOUR_DESCRIPTION, ElementType.Xpath);
						flag = Text.contains(PlansPageLocators.CAMPAIGNS_TOUR);
					}
				}
				if (flag) {
					webDB.clickAnElement(PlansPageLocators.NEXT_BUTTON, ElementType.Xpath);
					Text = webDB.getText(PlansPageLocators.TOUR_COUNT, ElementType.Xpath);
					flag = Text.contains("5/7");
					if (flag) {
						Text = webDB.getText(PlansPageLocators.PRODUCT_TOUR_DESCRIPTION, ElementType.Xpath);
						flag = Text.contains(PlansPageLocators.CUSTOMERS_TOUR);
					}
				}
				if (flag) {
					webDB.clickAnElement(PlansPageLocators.NEXT_BUTTON, ElementType.Xpath);
					Text = webDB.getText(PlansPageLocators.TOUR_COUNT, ElementType.Xpath);
					flag = Text.contains("6/7");
					if (flag) {
						Text = webDB.getText(PlansPageLocators.PRODUCT_TOUR_DESCRIPTION, ElementType.Xpath);
						flag = Text.contains(PlansPageLocators.SHAREDINBOX_TOUR);
					}
				}
				if (flag) {
					webDB.clickAnElement(PlansPageLocators.NEXT_BUTTON, ElementType.Xpath);
					Text = webDB.getText(PlansPageLocators.TOUR_COUNT, ElementType.Xpath);
					flag = Text.contains("7/7");
					if (flag) {
						Text = webDB.getText(PlansPageLocators.PRODUCT_TOUR_DESCRIPTION, ElementType.Xpath);
						flag = Text.contains(PlansPageLocators.WIDGETS_TOUR);
					}
				}
				if (flag) {
					flag = webDB.clickAnElement(PlansPageLocators.NEXT_BUTTON, ElementType.Xpath);
					Text = webDB.getText(PlansPageLocators.PRODUCT_TOUR_DESCRIPTION, ElementType.Xpath);
					flag = Text.contains(PlansPageLocators.FEEDBACK_TOUR);
					if (flag) {
						flag = webDB.clickAnElement(PlansPageLocators.FEEDBACK, ElementType.Xpath);
						log.info("Engage plan tour verified sucessfully ");
						Thread.sleep(2000);
					}
				}

			} else {
				System.out.println("Product tour pop up not display on home page ");
			}
		} else {
			System.out.println("Plan is not change to engage plan ");
		}
		return flag;

	}

	public static boolean verifyGrowthPlanProductTour()
			throws FileNotFoundException, InterruptedException, IOException {

		Text = webDB.getText(CommonLocators.PLAN_NAME, ElementType.Xpath);
		flag = Text.contains("GROWTH");
		log.info("Current plan is " + Text);
		log.info("Flag found is " + flag);
		if (!flag) {
			log.info("Current plan is changing to GROWTH ");
			flag = PlansPage.changePlanAPI(PlansPageLocators.GROWTH);
		}
		if (flag) {
			js.executeScript("sessionStorage.clear();");
			webDB.driver.navigate().refresh();
			log.info("Found growth plan and  clearing session storage " + flag);
			flag = webDB.isElementDisplayed(CommonLocators.TOURPOPUP, ElementType.Xpath);
			log.info("Tour pop up displayed " + flag);

			if (flag) {
				webDB.clickAnElement(PlansPageLocators.START_TOUR, ElementType.Xpath);
				Thread.sleep(1000);
				flag = webDB.isElementDisplayed(PlansPageLocators.PRODUCT_TOUR_DESCRIPTION, ElementType.Xpath);
				if (flag) {
					Text = webDB.getText(PlansPageLocators.TOUR_COUNT, ElementType.Xpath);
					flag = Text.contains("1/6");
					if (flag) {
						Text = webDB.getText(PlansPageLocators.PRODUCT_TOUR_DESCRIPTION, ElementType.Xpath);
						flag = Text.contains(PlansPageLocators.GROWTH_HOME_PAGE);
					}
				}
				if (flag) {
					webDB.clickAnElement(PlansPageLocators.NEXT_BUTTON, ElementType.Xpath);
					Text = webDB.getText(PlansPageLocators.TOUR_COUNT, ElementType.Xpath);
					flag = Text.contains("2/6");

				}
				if (flag) {
					webDB.clickAnElement(PlansPageLocators.NEXT_BUTTON, ElementType.Xpath);
					Text = webDB.getText(PlansPageLocators.TOUR_COUNT, ElementType.Xpath);
					flag = Text.contains("3/6");

				}
				if (flag) {
					webDB.clickAnElement(PlansPageLocators.NEXT_BUTTON, ElementType.Xpath);
					Text = webDB.getText(PlansPageLocators.TOUR_COUNT, ElementType.Xpath);
					flag = Text.contains("4/6");

				}
				if (flag) {
					webDB.clickAnElement(PlansPageLocators.NEXT_BUTTON, ElementType.Xpath);
					Text = webDB.getText(PlansPageLocators.TOUR_COUNT, ElementType.Xpath);
					flag = Text.contains("5/6");

				}
				if (flag) {
					webDB.clickAnElement(PlansPageLocators.NEXT_BUTTON, ElementType.Xpath);
					Text = webDB.getText(PlansPageLocators.TOUR_COUNT, ElementType.Xpath);
					flag = Text.contains("6/6");
					if (flag) {
						Text = webDB.getText(PlansPageLocators.PRODUCT_TOUR_DESCRIPTION, ElementType.Xpath);
						flag = Text.contains(PlansPageLocators.GROWTH_widget);
					}
				}

				if (flag) {
					flag = webDB.clickAnElement(PlansPageLocators.NEXT_BUTTON, ElementType.Xpath);
					Text = webDB.getText(PlansPageLocators.PRODUCT_TOUR_DESCRIPTION, ElementType.Xpath);
					flag = Text.contains(PlansPageLocators.FEEDBACK_TOUR);
					if (flag) {
						flag = webDB.clickAnElement(PlansPageLocators.FEEDBACK, ElementType.Xpath);
						log.info("Growth plan tour verified sucessfully ");
						Thread.sleep(2000);
					}
				}

			} else {
				System.out.println("Product tour pop up not display on home page ");
			}
		} else {
			System.out.println("Plan is not change to Growth plan ");
		}
		return flag;

	}

	public static boolean verifyNewFreePlanProductTour()
			throws FileNotFoundException, InterruptedException, IOException {

		Text = webDB.getText(CommonLocators.PLAN_NAME, ElementType.Xpath);
		flag = Text.contains("FREE");
		log.info("Current plan is " + Text);
		log.info("Flag found is " + flag);
		if (!flag) {
			log.info("Current plan is changing to FREE ");
			flag = PlansPage.changePlanAPI(PlansPageLocators.FREE);
		}
		if (flag) {
			js.executeScript("sessionStorage.clear();");
			webDB.driver.navigate().refresh();
			log.info("Found FREE plan and  clearing session storage " + flag);
			flag = webDB.isElementDisplayed(CommonLocators.TOURPOPUP, ElementType.Xpath);
			log.info("Tour pop up displayed " + flag);
			if (flag) {
				Thread.sleep(3000);
				webDB.clickAnElement(PlansPageLocators.START_TOUR, ElementType.Xpath);
				Thread.sleep(1000);
				flag = webDB.isElementDisplayed(PlansPageLocators.PRODUCT_TOUR_DESCRIPTION, ElementType.Xpath);
				if (flag) {
					Text = webDB.getText(PlansPageLocators.TOUR_COUNT, ElementType.Xpath);
					flag = Text.contains("1/3");
					if (flag) {
						Text = webDB.getText(PlansPageLocators.PRODUCT_TOUR_DESCRIPTION, ElementType.Xpath);
						flag = Text.contains(PlansPageLocators.GROWTH_HOME_PAGE);
					}
				}
				if (flag) {
					webDB.clickAnElement(PlansPageLocators.NEXT_BUTTON, ElementType.Xpath);
					Text = webDB.getText(PlansPageLocators.TOUR_COUNT, ElementType.Xpath);
					flag = Text.contains("2/3");
				}
				if (flag) {
					webDB.clickAnElement(PlansPageLocators.NEXT_BUTTON, ElementType.Xpath);
					Text = webDB.getText(PlansPageLocators.TOUR_COUNT, ElementType.Xpath);
					flag = Text.contains("3/3");
					if (flag) {
						Text = webDB.getText(PlansPageLocators.PRODUCT_TOUR_DESCRIPTION, ElementType.Xpath);
						flag = Text.contains(PlansPageLocators.GROWTH_widget);
					}
				}
				if (flag) {
					flag = webDB.clickAnElement(PlansPageLocators.NEXT_BUTTON, ElementType.Xpath);
					Text = webDB.getText(PlansPageLocators.PRODUCT_TOUR_DESCRIPTION, ElementType.Xpath);
					flag = Text.contains(PlansPageLocators.FEEDBACK_TOUR);
					if (flag) {
						flag = webDB.clickAnElement(PlansPageLocators.FEEDBACK, ElementType.Xpath);
						log.info("New free plan tour verified sucessfully ");
						Thread.sleep(2000);
					}
				}
			} else {
				System.out.println("Product tour pop up not display on home page ");
			}
		} else {
			System.out.println("Plan is not change to Free plan ");
		}
		return flag;

	}

	public static boolean verifySkipProductTour() throws FileNotFoundException, InterruptedException, IOException {
		Text = webDB.getText(CommonLocators.PLAN_NAME, ElementType.Xpath);
		String[] keywords = { "ENGAGE", "GROWTH", "FREE" };
		for (String keyword : keywords) {
			if (Text.contains(keyword)) {
				flag = true;
			}
		}
		if (!flag) {
			log.info("Current plan is changing to Engage ");
			flag = PlansPage.changePlanAPI(PlansPageLocators.ENGAGE);
		}
		if (flag) {
			js.executeScript("sessionStorage.clear();");
			webDB.driver.navigate().refresh();
			log.info("Cleared session storage flag found is: " + flag);
			flag = webDB.isElementDisplayed(CommonLocators.TOURPOPUP, ElementType.Xpath);
			log.info("Tour pop up displayed " + flag);
			if (flag) {
				flag = webDB.clickAnElement(PlansPageLocators.SKIP_BUTTON, ElementType.Xpath);
				if (flag) {
					flag = webDB.isElementDisplayed(CommonLocators.TOURPOPUP, ElementType.Xpath);
					if (!flag) {
						flag = true;
						log.info("Tour is not showing when we click on skip button and flag is" + flag);
					}
				}
			}
		}
		if (flag) {
			js.executeScript("sessionStorage.clear();");
			webDB.driver.navigate().refresh();
			log.info("Cleared session storage flag found is: " + flag);
			flag = webDB.isElementDisplayed(CommonLocators.TOURPOPUP, ElementType.Xpath);
			if (flag) {
				log.info("Tour pop up displayed " + flag);
				webDB.clickAnElement(PlansPageLocators.START_TOUR, ElementType.Xpath);
				Thread.sleep(1000);
				Text = webDB.getText(PlansPageLocators.TOUR_COUNT, ElementType.Xpath);
				flag = Text.contains("1");
				log.info("clicked on start tour and get 1 as count and flag is  " + flag);
				if (flag) {
					webDB.clickAnElement(PlansPageLocators.NEXT_BUTTON, ElementType.Xpath);
					Text = webDB.getText(PlansPageLocators.TOUR_COUNT, ElementType.Xpath);
					flag = Text.contains("2");
					log.info("clicked on next  and get 2 as count and flag is  " + flag);
					if (flag) {
						webDB.clickAnElement(PlansPageLocators.SKIP_BUTTON, ElementType.Xpath);
						Text = webDB.getText(PlansPageLocators.TOUR_COUNT, ElementType.Xpath);
						flag = Text.contains("1");
						log.info("clicked on back  and get 1 as count and flag is  " + flag);
						if (flag) {
							flag = webDB.clickAnElement(PlansPageLocators.CLOSE_BUTTON_POPUP, ElementType.Xpath);
							log.info("clicked on close button  " + flag);
							Thread.sleep(2000);
						}
					}
				}
			}
		}
		return flag;
	}

}
