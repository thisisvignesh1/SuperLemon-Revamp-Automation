package com.superlemon;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import commonFunctions.SettingsPage;
import commonFunctions.AnalyticsPage;
import commonFunctions.ChatBotPage;
import commonFunctions.CommonFunctions;
import commonFunctions.EmailConfigurationPage;
import commonFunctions.EmailLogsPage;
import commonFunctions.EmailTemplatesPage;
import commonFunctions.MessagingPage;
import commonFunctions.CampaignsPage;
import commonFunctions.MessageLogsPage;
import commonFunctions.CustomersPage;
import commonFunctions.WidgetPage;
import locators.LoginPageLocators;
import commonFunctions.PlansPage;
import commonFunctions.SharedTeamInboxPage;
import commonFunctions.AutomationPage;
import commonFunctions.MyWhatsAppPage;
import utils.Dbresults;
import utils.DriverBase;
import utils.Listener;
import utils.Mailer;

@SuppressWarnings({ "static-access" })
public class SanityTest extends Listener {
	boolean flag = false;
	String templateName;
	private Mailer mailer;
	static String suiteName;
	private DriverBase webDB = new DriverBase();
	// private static Logger log = Logger.getLogger(RegressionTest.class.getName());

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		String path = System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "ExtentReport";
		File file = new File(path);
		File[] files = file.listFiles();
		for (File f : files) {
			if (f.isFile() && f.exists()) {
				f.delete();
				System.out.println("successfully deleted");
			} else {
				System.out.println("cant delete a file due to open or error");
			}
		}
	}

	@BeforeTest(alwaysRun = true)
	public void openBrowser() throws FileNotFoundException, IOException, Exception {
//		webDB.updateAllColumnsValueToZero();
//		webDB.updateTotalTestCasesCount();
//		webDB.setTestType();
//		webDB.updateStartTimeOfExecution();
		webDB.executionTimeStart();
		webDB.browserOpen();
	}

	@BeforeMethod(alwaysRun = true)
	public void enterUrlAndLogin() throws FileNotFoundException, IOException, Exception {
		Thread.sleep(2000);
		try {
			webDB.enterURL();
//			Dimension d = new Dimension(1940, 1400);
//			webDB.driver.manage().window().setSize(d);
			//CommonFunctions.verifyLoginPage("QAtoken");
			CommonFunctions.verifyLoginPageWithToken();
//			CommonFunctions.verifyPlansPage();
//			PlansPage.changeSmallBusinessPlan();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	 --------------------------------------Sanity Cases----------------------------------------------

	@Test(description = "Verify that  the pop gets closed and user lands on the plan page once the user gets successfully logged in.", groups = {
			"group1" }, priority = 1)
	public void VerifyLoginPage() throws Exception {
		//PlansPage.changeSmallBusinessPlanAPI();
		flag = CommonFunctions.verifyPlansPage();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that on clicking the Personal Widget tab the user is navigated  to the Widget Settings page with settings and share setting sections.", groups = {
			"group1" }, priority = 2)
	public void verifyChatAndShare() throws Exception {
		flag = CommonFunctions.verifyPersonalWidgetPage();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user is able to disable the chat and chat settings and same should be reflecting on store", groups = {
			"group1" }, priority = 3)
	public void checkChatDisabled() throws FileNotFoundException, InterruptedException, IOException {

		flag = CommonFunctions.verifyPersonalWidgetPage();
		if (flag) {
			flag = WidgetPage.verifyDisableChatWidget();
			assertEquals(true, flag);
		}

	}

	@Test(description = "Verify that user is able to enable enable the share settings and same should be reflecting on store", groups = {
			"group1" }, priority = 4)
	public void checkShareDisabled() throws FileNotFoundException, InterruptedException, IOException {

		flag = CommonFunctions.verifyPersonalWidgetPage();
		if (flag) {
			flag = WidgetPage.verifyDisableShareWidget();
			assertEquals(true, flag);
		}

	}

	@Test(description = "Verify that user is able to enable disable the share settings and same should be reflecting on store", groups = {
			"group1" }, priority = 5)
	public void checkShareEnabled() throws FileNotFoundException, InterruptedException, IOException {

		flag = CommonFunctions.verifyPersonalWidgetPage();
		if (flag) {
			flag = WidgetPage.verifyEnableShareWidget();
		}
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user is able to enable the chat and chat settings and same should be reflecting on store", groups = {
			"group1" }, priority = 6)
	public void checkChatEnabled() throws Exception {

		flag = CommonFunctions.verifyPersonalWidgetPage();
		if (flag) {
			flag = WidgetPage.verifyEnableChatWidget();
			assertEquals(true, flag);
		}
	}

	@Test(description = "Verify that user is able to enable disable the Abcart Automated Messaging .", groups = {
			"group1" }, priority = 7)
	public void checkEnableDisableAbcartTemplateMessaging()
			throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyTemplatesPage();
		flag = AutomationPage.verifyEnableDisableAbcartAutomatedMessaging();
		assertEquals(true, flag);

	}

	@Test(description = "Verify that chat and share widget should only be displayed on the device selected.", groups = {
			"group1" }, priority = 8)
	public void checkChatOnMobileDeviceOnlySelected() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.selectMobileDeviceOnly();
		assertEquals(true, flag);

	}

	@Test(description = "Verify that user is able to enable disable the COD Automated Messaging .", groups = {
			"group1" }, priority = 9)
	public void checkEnableDisableCODTemplateMessaging()
			throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyTemplatesPage();
		flag = AutomationPage.verifyEnableDisableCODAutomatedMessaging();
		assertEquals(true, flag);

	}

	@Test(description = "Verify that user is able to enable disable the Order Confirmation Automated Messaging .", groups = {
			"group1" }, priority = 10)
	public void checkEnableDisableOrderConfirmationTemplateMessaging() throws Exception {

		CommonFunctions.verifyTemplatesPage();
		flag = AutomationPage.verifyEnableDisableOrderConfirmationAutomatedMessaging();
		assertEquals(true, flag);

	}

	@Test(description = "Verify that user is able to enable disable the Order Shipment Automated Messaging .", groups = {
			"group1" }, priority = 11)
	public void checkEnableDisableShipmentTemplateMessaging()
			throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyTemplatesPage();
		flag = AutomationPage.verifyEnableDisableShipmentAutomatedMessaging();
		assertEquals(true, flag);

	}

	@Test(description = "Verify that opt in pop up is dispklayed on the store for the options selected in optin Numbers settings.", groups = {
			"group1" }, priority = 12)
	public void checkUnselectOptinCheckBoxes() throws Throwable {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = SettingsPage.enableOptinsAndCheckOnstore();
		assertEquals(true, flag);

	}

	@Test(description = "Verify that user is able to change the language as per choice.", groups = {
			"group1" }, priority = 13)
	public void checkGoogleTranslate() throws FileNotFoundException, InterruptedException, IOException {

		flag = CommonFunctions.verifyGoogleTranslate();
		assertEquals(true, flag);

	}

	@Test(description = "Verify that user is able to add and delete templates.", groups = { "group1" }, priority = 14)
	public void checkAddandDeleteTemplate() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyMessagingPage();
		flag = AutomationPage.createAndDeleteTemplate();
		assertEquals(true, flag);

	}

	@Test(description = "Verify that user is able to enable/disable the greetings widget.", groups = {
			"group1" }, priority = 15)
	public void checkEnableDisableGreetings() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyEnableDisableGreetingWidget();
		assertEquals(true, flag);

	}

	@Test(description = "Verify that store offline message should be displayed on the store on making the store offline.", groups = {
			"group1" }, priority = 16)
	public void checkStoreOffline() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyStoreOffline();
		assertEquals(true, flag);

	}

	@Test(description = "Verify that user is able to edit agent on the personal widget page and same should reflect in the store chat widget.", groups = {
			"group1" }, priority = 17)
	public void checkEditDetailsAgent() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyEditDetailsAgent();
		assertEquals(true, flag);

	}

	@Test(description = "Verify that user is able to filter order,name and type column.", groups = {
			"group1" }, priority = 18)
	public void verifyfilterforcolumns() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyMessageLogsPage();
		flag = MessageLogsPage.verifyfilterforcolumns();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user is able to export optin list successfully with all valid information provided.", groups = {
			"group1" }, priority = 19)
	public void checkExportSuccess() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyOptinNumbersPage();
		flag = CustomersPage.verifyExportSuccessful();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to see 2 buttons - 1. View Tag and 2. Create Tag in Tags section.", groups = {
			"group1" }, priority = 20)
	public void verifyTagsButtons() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyTagsButtons();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user is able to edit agent on the personal widget page and same should reflect in the store chat widget.", groups = {
			"group1" }, priority = 21)
	public void checkEditDetailsAgent1() throws Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyEditDetailsAgent();
		assertEquals(true, flag);
	}

	@Test(description = "verify create custom grps and verify grp inputs validations", groups = {
			"group1" }, priority = 22)
	public void verifyCreateCustomGrpsAndValidation() throws Exception {
		CommonFunctions.verifyOptinNumbersPage();
		flag = CustomersPage.VerifyCreateCustomerGrp();
		if (flag) {
			flag = CustomersPage.verifyValidationGrpTab();
		}
		assertEquals(true, flag);
	}

	@Test(description = "Verify the user group is editable", groups = { "group1" }, priority = 23)
	public void VerifyUserGroupEdit() throws Exception {
		CommonFunctions.verifyOptinNumbersPage();
		flag = CustomersPage.editUserGroup();
		assertEquals(true, flag);

	}

	@Test(description = "verify deleting user group", groups = { "group1" }, priority = 24)
	public void VerifyDeleteUserGroup() throws Exception {
		CommonFunctions.verifyOptinNumbersPage();
		flag = CustomersPage.verifyDeleteUserGroup();
		assertEquals(true, flag);

	}

	@Test(description = "verify create custom template with predefined and custom variables", groups = {
			"group1" }, priority = 25)

	public void verifyCustomerPredefinedVariableAndVerifyMarketCampaigns() throws Exception {
		CommonFunctions.verifyMarketCampaignsPage();
		flag = AutomationPage.verifyCreateCustomTemplatesWithAddCustomVariable();
//if (flag) {
//	CommonFunctions.verifyMarketCampaignsPage();
//	flag = MarketCampaignsPage.verifyCustomTemplateInMarketCampign();
//}
		assertEquals(true, flag);
	}

	@Test(description = "verify create custom template with predefined and custom variables", groups = {
			"group1" }, priority = 26)
	public void verifyCustomerPredefinedVariable() throws Exception, Exception, IOException {
		CommonFunctions.verifyOptinNumbersPage();
		flag = CustomersPage.verifyCustomerPredefinedVariable();
		assertEquals(true, flag);
	}

	@Test(description = "verify create custom template with out custom variable", groups = { "group1" }, priority = 27)

	public void verifyCustomTemplateExceptCustomVariable() throws Exception {
		CommonFunctions.verifyMarketCampaignsPage();
		flag = AutomationPage.verifyCreateCustomTemplatesExceptCustomVariable();
		assertEquals(true, flag);
	}

	@Test(description = "Verify create custom template,create user grp then download excel file,write the excel data and upload the file the create Market campagins", groups = {
			"group1" }, priority = 28)

	public void verifyCustomTemplateWithfileUpload() throws Exception {
		CommonFunctions.verifyMarketCampaignsPage();
		flag = AutomationPage.verifyCreateCustomTemplatesWithAddCustomVariable();
		if (flag) {
			flag = CampaignsPage.verifyCreateMarketCampaignwithPredefinedAndCustomUploadFile();
		}
		assertEquals(true, flag);
	}

	@Test(description = "Verify Upload photo,update about field,update business details and change language", groups = {
			"group1" }, priority = 29)
	public void verifyWabadetails() throws Exception {
		CommonFunctions.VerifyWhatsAPPAPIPage();
		flag = MyWhatsAppPage.verifyLanguageDropdown();
		if (flag) {
			flag = MyWhatsAppPage.verifyProfileDetails();
			if (flag) {
				flag = MyWhatsAppPage.verifyWAUploadPhoto();
			}
		}
		assertEquals(true, flag);
	}

	@Test(description = "Create Templates and Delete template with maximum", groups = { "group1" }, priority = 30)

	public void verifyCreateExtensionTemplateMaximum() throws Exception {
		CommonFunctions.verifyMessagingPage();
		flag = AutomationPage.deleteExtensionTemplate();
		if (flag) {
			flag = AutomationPage.createExtensiontemplateWithMaximum();
			if (flag) {
				flag = AutomationPage.createOrderTemplateWithMaximum();
				if (flag) {
					flag = AutomationPage.deleteExtensionTemplate();
				}
			}
		}
		assertEquals(true, flag);
	}

	@Test(description = "Change Ultimate plan and Verify Recommended plan", groups = { "group1" }, priority = 31)

	public void verifyChangeplansRecommened() throws Exception {
		CommonFunctions.verifyPlansPage();
		flag = PlansPage.verifyChangePlanVerifyRecommended();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that when we changes design of wheel same design wheel is showing on store or not ", groups = {
			"group1" }, priority = 32)

	public void verifySelectDesign() throws Exception, Exception, Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyAtleastSelectOneDesign();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that when we select display position as destkop it is showing only on  desktop  or not ", groups = {
			"group1" }, priority = 33)
	public void verifyDesktopSpinwheel() throws Exception {
		flag = WidgetPage.verifyDesktopSpinWheelIcon();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that if we select any language and then click on save that language is saved or not ", groups = {
			"group1" }, priority = 34)

	public void verifyMessagelanguageSection() throws Exception, Exception, Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyMessagelanguageSection();
		assertEquals(true, flag);
	}

	@Test(description = "verify that if we select any page to display then on that page spin the wheel widget is showing or not ", groups = {
			"group1" }, priority = 35)
	public void verifyGetDiscountSpinWheelMsg() throws Exception {
		flag = WidgetPage.verifyDiscountMsgSpinWSheel();
		if (flag) {
			flag = WidgetPage.verifyDisablePageCheckStore();
		}
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user is able to spin the wheel just once", groups = { "group1" }, priority = 36)

	public void verifySpinWheelOnce() throws Exception, Exception, Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.VerifyWheelSpinsOnce();
		assertEquals(true, flag);
	}

	@Test(description = "Verify the message when user doesn't win any discount", groups = { "group1" }, priority = 37)

	public void verifyUserNotWinDiscount() throws Exception, Exception, Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.VerifyUserNotWinDiscount();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to enable / disable any extension template as per requirement.", groups = {
			"group1" }, priority = 38)
	public void VerifyEnableDisableOfTemplate() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyMessagingPage();
		Thread.sleep(2000);
		flag = AutomationPage.VerifyEnableDisableOfTemplate();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that source should be displayed a scheckout for the optins made through checkout page.", groups = {
			"group1" }, priority = 39)
	public void verifyOptinSourceAsCheckout() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = CustomersPage.checkOptinCheckoutSourceInOptinList();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that siource should be displayed as widget for the opt ins made through optin pop up...", groups = {
			"group1" }, priority = 40)
	public void verifyOptinSourceAsWidget() throws Throwable {
		CommonFunctions.verifyAdminConfigurtionPage();
		flag = CustomersPage.checkOptinWidgetSourceInOptinList();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that when we select customer section all the required option is showing or not", groups = {
			"group2" }, priority = 41)

	public void verifyCustomerOptions() throws Exception {
		CommonFunctions.verifyOptinNumbersPage();
		flag = CustomersPage.verifyCustomersRequiredOptions();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that what ever name user gives while placing order same name is showing or not ", groups = {
			"group2" }, priority = 42)

	public void verifyCustomerOrderDetails() throws Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = CustomersPage.checkOptinDetailsInOptinList();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that correct opt in source is  showing or not", groups = { "group2" }, priority = 43)

	public void verifyOptinSourceAsSpinWheel() throws Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = CustomersPage.VerifyOptinSourcesAsSpinWheel();
		assertEquals(true, flag);
	}

	@Test(description = "Verify Maximum and Minium for all Date filter", groups = { "group2" }, priority = 44)

	public void verfiyMaxMonthsDateFilerAnalyticsPage()
			throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyAnalyticsPage();
		flag = AnalyticsPage.verifyWidgetMaxMonthsDateFiler();
		if (flag) {
			reportLog("Verfied Widget Max month");
			flag = AnalyticsPage.verifySpinWheelMaxMonthsDateFiler();
			if (flag) {
				reportLog("Verfied Spin Max month");
				flag = AnalyticsPage.verifyAutomatedMessageMaxMonthsDateFiler();
				if (flag) {
					reportLog("Verfied Automated Max month");
					flag = AnalyticsPage.verifyOptsInMaxMonthsDateFiler();
					if (flag) {
						reportLog("Verfied Optins Max month");
					}
				}
			}
		}
		assertEquals(true, flag);
	}

	@Test(description = "Verify Analytics Page Section of All Graphs", groups = { "group2" }, priority = 45)
	public void verfiyAnalyticsPageSections() throws Exception {
		CommonFunctions.verifyTemplatesPage();
		AutomationPage.verifyEnableShipmentAutomatedMessaging();
		AutomationPage.verifyEnableOrderConfirmationAutomatedMessaging();
		AutomationPage.verifyEnableCODAutomatedMessaging();
		AutomationPage.verifyEnableAbcartAutomatedMessaging();
		CommonFunctions.verifyAnalyticsPage();
		flag = AnalyticsPage.verifyAnalyticsPageOtherMessageSections();
		if (flag) {
			reportLog("verfied Analytics page");
			flag = AnalyticsPage.verifyOtherMessageGraph();
			if (flag) {
				reportLog("verfied Other Message Graph");
				flag = AnalyticsPage.verifyAbandedcartOthermsgGraphShows();
			}
		}
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to see whatsapp configuration settings on Settings page - Send messages from your computer using - 1.Whatsapp web  and 2. Whatsapp Desktop App.", groups = {
			"group2" }, priority = 46)
	public void verifyWhatsAPPConfigSettings() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyWhatsAPPConfigSettings();
		assertEquals(true, flag);
	}

	@Test(description = "verify that user should redirect properly whatsapp desktop site after clicking on hyperlink \"WhatsApp Desktop application\"", groups = {
			"group2" }, priority = 47)
	public void verifyWAdesktopAppHyperlinkSettings() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyWAdesktopAppHyperlinkSettings();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to see 2 sub-sections below settings on settings page - 1. Tags 2. Messaging Templates.", groups = {
			"group2" }, priority = 48)
	public void verifySubSectionUnderSettings() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifySubSectionUnderSettings();
		assertEquals(true, flag);
	}

	@Test(description = "Verify user should be able to view all created tags after clicking on \"View tags\" button.", groups = {
			"group2" }, priority = 49)
	public void verifyCreatedTags() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyCreatedTags();
		assertEquals(true, flag);
	}

	@Test(description = "Verify user should be able to see all created templates after clicking on \"View Temlates\" button.", groups = {
			"group2" }, priority = 50)
	public void verifyCreatedTemplate() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyCreatedTemplate();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to see Type of order - Cart Recovery , Order CRM , Cash on Delivery and Order tracking. ", groups = {
			"group2" }, priority = 51)
	public void VerifyTypeOfOrderDisplay() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyMessageLogsPage();
		flag = MessageLogsPage.VerifyTypeOfOrderDisplay();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that when select template and click on send it is redirecting to selected customer whats app number or not ", groups = {
			"group2" }, priority = 52)

	public void verifyMarketCampaignsValidations() throws Exception {
		CommonFunctions.verifyNewStoreLoginPage("NewSharedTeamInbox_Token");
		CommonFunctions.verifyMarketCampaignsPage();
		flag = CampaignsPage.verifyInputMarketCampign();
		if (flag) {
			reportLog("Verified InputMarket Campaign");
			flag = CampaignsPage.verifyInvalidInputMarketCampign();
			if (flag) {
				reportLog("Verified Invalid Inputs");
				flag = CampaignsPage.verifyCreateMarketCampign();
				if (flag) {
					reportLog("Verified Create Market campaign");
					flag = CampaignsPage.verifyGuideMarketCampign();
				}
			}
		}
		assertEquals(true, flag);
	}

	@Test(description = "Verify that correct opt in source is  showing or not", groups = { "group2" }, priority = 53)

	public void verifyCustomsTemplates() throws Exception {
		CommonFunctions.verifyMarketCampaignsPage();
		flag = AutomationPage.verifyCustomTemplateValidation();
		if (flag) {
			reportLog("Verified Inputs Validations");
			flag = AutomationPage.verifyCustomTemplateValidationwithCallAction();
			if (flag) {
				reportLog("Verified Call to Actions Inputs Validations");
				flag = AutomationPage.verifyCreateCustomTemplateswithoutFooterHeader();
				if (flag) {
					reportLog("Veried Create Custom templates without FooterHeader");
					flag = AutomationPage.verifyCreateCustomTemplateswithFooterHeader();
					if (flag) {
						reportLog("Veried Create Custom templates with FooterHeader");
						flag = AutomationPage.verifyOptionalMandatoryInput();
						if (flag) {
							reportLog("Veried Custom templates Optional Mandatory Input");
//							flag = TemplatesPage.verifyGuideCustomTemplates();
//							if (flag) {
//								reportLog("Veried Custom templates Optional Mandatory Input");
						}
//						}
					}
				}
			}
		}
		assertEquals(true, flag);
	}

	@Test(description = "", groups = { "group2" }, priority = 54)
	public void verifyUpdateStatusFulfilmentOrder() throws Exception {
		flag = CustomersPage.verifyOrderWithPhoneno();
		if (flag) {
			webDB.verifyResponseAPI("GetShopID");
			flag = DriverBase.verifyResponseAPI("ChangeCrosssellInterval");
			if (flag) {
				webDB.verifyResponseAPI("GetShopID");
				flag = DriverBase.verifyResponseAPI("ChangeFeedbackInterval");
				if (flag) {
					webDB.verifyResponseAPI("GetShopID");
					flag = DriverBase.verifyResponseAPI("ChangeWinbackInterval");
				}
			}
		}
		assertEquals(true, flag);
	}

	@Test(description = "", groups = { "group2" }, priority = 55)
	public void verifyFreePlansPage() throws Exception {
		CommonFunctions.verifyPlansPage();
		flag = PlansPage.changeFreePlanAPI();
		if (flag) {
			reportLog("Change plan");
			flag = PlansPage.verifyPlanUiSection();
			if (flag) {
				reportLog("verified Plan UI section");
				flag = PlansPage.verifyCustomTemplatesPageNavigate();
				if (flag) {
					reportLog("verified Customs templates");
					flag = PlansPage.verifyExtensionTemplatesPageNavigate();
					if (flag) {
						reportLog("verified Extension templates");
						flag = PlansPage.verifyPrivatewabaPageNavigate();
						PlansPage.changeSmallBusinessPlanAPI();
					}
				}
			}
		}
		assertEquals(true, flag);
	}

	// @Test(description = "Upgrade Plans", groups = { "group2" }, priority = 55)
//	public void verifyUpgradePlans() throws Exception {
//		CommonFunctions.verifyPlansPage();
//		PlansPage.changeSmallBusinessPlanAPI();
//		flag = PlansPage.changeFreePlan();
//		if (flag) {
//			flag = PlansPage.verifyUpgradePlanValidations();
//			PlansPage.changeSmallBusinessPlanAPI();
//		}
//		assertEquals(true, flag);
//	}

	//@Test(description = "Verify that chat bot section is visble or not ", groups = { "group2" }, priority = 56)

	public void verifyChatBotSectionVisible() throws Exception {
		flag = CommonFunctions.verifyChatBotPage();
		assertEquals(true, flag);
	}

	//@Test(description = "Verify that if chat bot request is submitteted then correct message is showing or not ", groups = {
			//"group2" }, priority = 57)

	public void verifyChatBotRequestStatus() throws Exception {
		flag = CommonFunctions.verifyChatBotPage();
		if (flag) {
			flag = ChatBotPage.verifyRequestStatus();
		}
		assertEquals(true, flag);
	}

	@Test(description = "Verify that  user is able to submit  request from contact us here section or not", groups = {
			"group2" }, priority = 58)

	public void verifyContactUsSubmitRequest() throws Exception {
		flag = CommonFunctions.verifyPlansPage();
		if (flag) {
			flag = PlansPage.verifySubmitRequest();
		}

		assertEquals(true, flag);
	}

	@Test(description = "Verify that in contact us here section all the radio button user is able to select or not ", groups = {
			"group2" }, priority = 59)

	public void verifyContactUsButtons() throws Exception {
		flag = CommonFunctions.verifyPlansPage();
		if (flag) {
			flag = PlansPage.verifyContactUsButton();
		}
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should able to close the request section by clicking close button or not ", groups = {
			"group2" }, priority = 60)

	public void verifyContactUsCloseButtons() throws Exception {
		flag = CommonFunctions.verifyPlansPage();
		if (flag) {
			flag = PlansPage.verifyContactUsCloseButton();
		}
		assertEquals(true, flag);
	}

	//@Test(description = "Verify that settings gets disabled on disabling the email template for the respective type", groups = {
		//	"group2" }, priority = 61)
	public void checkDisableSettingsOnDisablingEmailTemplate()
			throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyNewStoreLoginPage("POI45QAtoken");
		flag = CommonFunctions.verifyEmailTemplatesPage();
		if (flag) {
			flag = EmailTemplatesPage.disableAllEmailTemplatesAndCheckSettings();
		}
		assertEquals(true, flag);
	}

//	@Test(description = "Verify that settings gets enabled on enabling the email template for the respective type", groups = {
//			"group2" }, priority = 62)
	public void checkSettingsEnabledOnEnablingEmailTemplate()
			throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyNewStoreLoginPage("POI45QAtoken");
		flag = CommonFunctions.verifyEmailTemplatesPage();
		if (flag) {
			flag = EmailTemplatesPage.enableAllEmailTemplatesAndCheckSettings();
		}
		assertEquals(true, flag);
	}

//	@Test(description = "Verify that user is able to add shopify brandName in the email templates", groups = {
//			"group2" }, priority = 63)
	public void checkShopifyBrandNameAdditionInEmailSubject()
			throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyNewStoreLoginPage("POI45QAtoken");
		flag = CommonFunctions.verifyEmailConfigurationPage();
		if (flag) {
			flag = EmailConfigurationPage.selectAndVerifyShopifyBrandNameOnEmailTemplatesSubject();
		}
		assertEquals(true, flag);
	}

//	@Test(description = "Verify that user is able to add custom brandName in the email templates", groups = {
//			"group2" }, priority = 64)
	public void checkCustomBrandNameAdditionInEmailSubject()
			throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyNewStoreLoginPage("POI45QAtoken");
		flag = CommonFunctions.verifyEmailConfigurationPage();
		if (flag) {
			flag = EmailConfigurationPage.selectAndVerifyCustomBrandNameOnEmailTemplatesSubject();
		}
		assertEquals(true, flag);
	}

//	@Test(description = "Verify that user is able to search by order in orderid searchbar in Email Logs.", groups = {
//			"group2" }, priority = 65)
	public void verifySearchByOrderIDinEmailLogs() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyNewStoreLoginPage("POI45QAtoken");
		CommonFunctions.verifyEmailLogsPage();
		flag = EmailLogsPage.verifySearchByOrderID();
		assertEquals(true, flag);
	}

//	@Test(description = "Verify that user gets navigated to the Email page on clicking on Email Logs tab.", groups = {
//			"group2" }, priority = 66)
	public void checkEmailLogsPage() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyNewStoreLoginPage("POI45QAtoken");
		flag = CommonFunctions.verifyEmailLogsPage();
		assertEquals(true, flag);
	}

	//@Test(description = "Verify that user is able to view names in table.", groups = { "group2" }, priority = 67)
	public void verifyNamesDisplayInEmailLogs() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyNewStoreLoginPage("POI45QAtoken");
		CommonFunctions.verifyEmailLogsPage();
		flag = EmailLogsPage.verifyNamesDisplay();
		assertEquals(true, flag);
	}

	//@Test(description = "Verify that user is able to filter order,name and type column  in Email Logs Page.", groups = {
	//		"group2" }, priority = 68)
	public void checkFilterForColumnsInEmailLogs() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyNewStoreLoginPage("POI45QAtoken");
		CommonFunctions.verifyEmailLogsPage();
		flag = EmailLogsPage.verifyfilterforcolumns();
		assertEquals(true, flag);
	}

//	@Test(description = "Verify that user is able to search by name in Name Textbox in Email Logs.", groups = {
//			"group2" }, priority = 69)
	public void verifySearchByNameInEmailLogs() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyNewStoreLoginPage("POI45QAtoken");
		CommonFunctions.verifyEmailLogsPage();
		flag = EmailLogsPage.verifySearchByName();
		assertEquals(true, flag);
	}

//	@Test(description = "Verify that user is able to see timestamp in Attempted column in Email Logs Page.", groups = {
//			"group2" }, priority = 71)
	public void checkTimeStampColumn() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyNewStoreLoginPage("POI45QAtoken");
		CommonFunctions.verifyEmailLogsPage();
		flag = EmailLogsPage.verifyTimeStampColumn();
		assertEquals(true, flag);
	}

//	@Test(description = "Verify that user should be able to see Amount as per selected Currency in Email Logs.", groups = {
//			"group2" }, priority = 72)
	public void VerifyAmountDisplayinEmailLogs() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyNewStoreLoginPage("POI45QAtoken");
		CommonFunctions.verifyEmailLogsPage();
		flag = EmailLogsPage.VerifyAmountDisplay();
		assertEquals(true, flag);
	}

	@Test(description = "Verify on selecting Image as type, Image file should be uploaded in sample media", priority = 73)
	public void VerifyImageMediaType() throws FileNotFoundException, InterruptedException, IOException {
		//CommonFunctions.verifyMarketCampaignsPage();
		flag = AutomationPage.verifyImageMediaType();
		assertEquals(true, flag);
	}

	@Test(description = "Verify on selecting video as type, video file should be uploaded in sample media", priority = 74)
	public void VerifyVideoMediaType() throws FileNotFoundException, InterruptedException, IOException {
		//CommonFunctions.verifyMarketCampaignsPage();
		flag = AutomationPage.verifyVideoMediaType();
		assertEquals(true, flag);
	}

	@Test(description = "Verify on selecting Document as type, document file should be uploaded in sample media", priority = 75)
	public void VerifyDocumentMediaType() throws FileNotFoundException, InterruptedException, IOException {
		//CommonFunctions.verifyMarketCampaignsPage();
		flag = AutomationPage.verifyDocumentMediaType();
		assertEquals(true, flag);
	}

	//@Test(description = "Verify Enage plan Tour", groups = { "group2" }, priority = 76)
	public void VerifyEngagePlanTour() throws Exception {
		flag = PlansPage.verifyEngagePlanProductTour();
		assertEquals(true, flag);
	}

	//@Test(description = "Verify Growth plan Tour", groups = { "group2" }, priority = 77)
	public void VerifyGrowthPlanTour() throws Exception {
		flag = PlansPage.verifyGrowthPlanProductTour();
		assertEquals(true, flag);
	}

	//@Test(description = "Verify newfree plan Tour", groups = { "group2" }, priority = 78)
	public void VerifyNewFreePlanPlanTour() throws Exception {
		flag = PlansPage.verifyNewFreePlanProductTour();
		assertEquals(true, flag);
	}

	@Test(description = "verify that in message template when quick reply option is selected then quick reply extension template is showing or not"
			+ "verify that in message template when all  option is selected then all the template is showing or not"
			+ "Verify that when user selects quick reply category then quick reply and order template category is showing or not", priority = 79)
	public void VerifyQuickReplytemplate() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyQuickReplytemplate();
		assertEquals(true, flag);
	}

	@Test(description = "verify that in message template when ab cart  option is selected then ab cart manual message template is showing or not "
			+ "Verify that when user is creating  manual message ab cart  template then user is able to create or not  ", priority = 80)
	public void VerifyABcarttemplate() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyABcarttemplate();
		assertEquals(true, flag);
	}

	@Test(description = "verify that in message template when order crm  option is selected then order crm manual message and extension  template is showing or not "
			+ "Verify that when user is creating  manual message order template then user is able to create or not  ", priority = 81)
	public void VerifyOrderCRMtemplate() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyOrderCRMtemplate();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that when user clicks on send message all the crm order template is showing or not", priority = 82)
	public void VerifySendMessageinOrderCRM() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifySendmessageinOrderCRM();
		assertEquals(true, flag);
	}

	@Test(description = "Verify on clicking Preview, the preview of widget opens in new tab or not"
			+ "Verify on clicking Edit configuration, the configuration options is showing or not", groups = {
					"group2" }, priority = 83)
	public void VerifyOptinpreview() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyPreview();
		if(flag) {
			WidgetPage.verifyOptinpopupLanguage();
		}
		assertEquals(true, flag);
	}

	@Test(description = "Verify on selecting Add to cart, the widget is shown when we click Add to cart button in store or not", groups = {
			"group2" }, priority = 84)
	public void VerifyOptinPopupAddCart() throws Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyAddCartOptinWidget();
		assertEquals(true, flag);
	}

	@Test(description = "Verify on selecting Cart page, the widget is shown in cart page of store or not", priority = 85)
	public void VerifyCartpageCheck() throws Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyCartpageCheckbox();
		assertEquals(true, flag);
	}

	@Test(description = "Verify on selecting Checkout, the widget is shown when we click Check out button in store or not", priority = 86)
	public void VerifyCheckoutCheckbox() throws Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyCheckoutCheckbox();
		assertEquals(true, flag);
	}

	@Test(description = "Verify on clicking View on store in Spin the wheel, it redirects to the specific store or not", priority = 87)
	public void VerifyViewonStoreSpinwheel() throws Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyViewonStoreSpinwheel();
		assertEquals(true, flag);
	}

	@Test(description = "Verify on enabling optin via chat widget in edit config, it reflects on store or not", priority = 88)
	public void VerifyOptinvia_ChatwidgetEnableonStore() throws Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyOptinvia_ChatwidgetEnableonStore();
//		if (flag) {
//			flag = WidgetPage.verifyOptinvia_ChatwidgetDisableonStore();
//		}
		assertEquals(true, flag);
	}

	@Test(description = "Verify if we can enable Opt-in chat widget with toggle button and it reflects on store or not", priority = 89)
	public void VerifyOptinviaChatwidgetEnable() throws Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyOptinviaChatwidgetEnableonStore();
		assertEquals(true, flag);
	}

	@Test(description = "Verify if we can disable Opt-in chat widget with toggle button and it reflects on store or not", priority = 90)
	public void VerifyOptinviaChatwidgetDisable() throws Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyOptinviaChatwidgetDisableonStore();
		assertEquals(true, flag);
	}

	@Test(description = "Verify optin pop up country code", groups = { "group2" }, priority = 91)
	public void VerifyOptinPopUp() throws Exception {
		//CommonFunctions.verifyNewStoreLoginPage("NewSharedTeamInbox_Token");
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyOptinPopUp();
		assertEquals(true, flag);
	}

	@Test(description = "Verify Country Code For Spinthewheel", priority = 92)
	public void VerifyCountryCodeOfSpinTheWheel() throws Exception {
		//CommonFunctions.verifyNewStoreLoginPage("NewSharedTeamInbox_Token");
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.countryCodeForSpinWheel();
		assertEquals(true, flag);
	}

	//@Test(description = "Verify Campaign For Image", priority = 93)
	public void VerifyImageCampaign() throws InterruptedException, FileNotFoundException, IOException {
		CommonFunctions.verifyNewStoreLoginPage("NewSharedTeamInbox_Token");
		CommonFunctions.verifyMarketCampaignsPage();
		flag = CampaignsPage.verifyImageCampaignWithFile();
		assertEquals(true, flag);
	}

	//@Test(description = "Verify Campaign For Video", priority = 94)
	public void VerifyVideoCampaign() throws InterruptedException, FileNotFoundException, IOException {
		CommonFunctions.verifyNewStoreLoginPage("NewSharedTeamInbox_Token");
		CommonFunctions.verifyMarketCampaignsPage();
		flag = CampaignsPage.verifyVideoCampaignWithFile();
		assertEquals(true, flag);
	}

	//@Test(description = "Verify Campaign For Docs", priority = 95)
	public void VerifyDocsCampaign() throws InterruptedException, FileNotFoundException, IOException {
		CommonFunctions.verifyNewStoreLoginPage("NewSharedTeamInbox_Token");
		CommonFunctions.verifyMarketCampaignsPage();
		flag = CampaignsPage.verifyDocsCampaignWithFile();
		assertEquals(true, flag);
	}
	
	@Test(description = "Verify on disabling optin via chat widget in edit config, it reflects on store or not", priority = 96)
	public void VerifyOptinvia_ChatwidgetDisableonStore() throws Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		//flag = WidgetPage.verifyOptinvia_ChatwidgetEnableonStore();
		//if (flag) {
			flag = WidgetPage.verifyOptinvia_ChatwidgetDisableonStore();
		//}
		assertEquals(true, flag);
	}

//	@Test
//	public void wixlogin() throws FileNotFoundException, InterruptedException, IOException {
//		flag = CommonFunctions.verifywixStoreLoginPage("wix_token");
//		assertEquals(true, flag);
//	}

//--------------------------------------------------------------------------------------------------------------------------------------------------------------

	@AfterSuite(alwaysRun = true)
	public void sendReport(ITestContext ctx) throws Exception {
		//PlansPage.changeSmallBusinessPlanAPI();
		webDB.executionTimeEnd();
		webDB.tearDown();
		webDB.browserOpen();
		Dbresults.executedb();
		suiteName = ctx.getCurrentXmlTest().getSuite().getName();
		mailer.execute(suiteName);
		webDB.tearDown();
		try {
			String path = System.getProperty("user.dir") + File.separator + "Resources";
			File file = new File(path);
			File[] files = file.listFiles();
			for (File f : files) {
				if (f.isFile() && f.exists()) {
					f.delete();
					System.out.println("successfully deleted");
				} else {
					System.out.println("cant delete a file due to open or error");
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@AfterMethod(alwaysRun = true)
	public void logout() throws FileNotFoundException, IOException, Exception {
		CommonFunctions.verifyLogout();
		CommonFunctions.closeAllChildWindows();
		CommonFunctions.clearLocalStorage();
		CommonFunctions.clearCookies();
		webDB.driver.navigate().refresh();
//		webDB.updateTestCaseExecutedCount();
	}

}
