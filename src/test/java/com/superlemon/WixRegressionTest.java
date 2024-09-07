package com.superlemon;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import commonFunctions.SettingsPage;
import commonFunctions.SharedTeamInboxPage;
import commonFunctions.AnalyticsPage;
import commonFunctions.CommonFunctions;
import commonFunctions.EmailConfigurationPage;
import commonFunctions.EmailLogsPage;
import commonFunctions.EmailTemplatesPage;
import commonFunctions.MessagingPage;
import commonFunctions.CampaignsPage;
import commonFunctions.MessageLogsPage;
import commonFunctions.CustomersPage;
import commonFunctions.WidgetPage;
import commonFunctions.WixFunctions;
import commonFunctions.WixStore;
import commonFunctions.PlansPage;
import commonFunctions.ShopifyStore;
import commonFunctions.WhatsappInboxPage;
import commonFunctions.AutomationPage;
import commonFunctions.MyWhatsAppPage;
import locators.PlansPageLocators;
import utils.Dbresults;
import utils.DriverBase;
import utils.Listener;
import utils.Mailer;

@SuppressWarnings({ "static-access", "unused" })
public class WixRegressionTest extends Listener {
	private static Logger log = Logger.getLogger(RegressionTest.class.getName());
	boolean flag = false;
	String templateName;
	private Mailer mailer;
	// public static WebDriver driver;
	private DriverBase webDB = new DriverBase();
	static String suiteName;
	private static String startTime;
	private static String endTime;

	@BeforeSuite(alwaysRun = true)
	public void removeReports() throws FileNotFoundException, IOException, Exception {
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

	@Override
	@BeforeTest
	public void browserOpen() throws FileNotFoundException, IOException, Exception {
//		webDB.updateAllColumnsValueToZero();
//		webDB.updateTotalTestCasesCount(long epoch);
//		webDB.setTestType( epoch);
//		webDB.updateStartTimeOfExecution();
		webDB.executionTimeStart();
		webDB.browserOpen();
	}

	@BeforeMethod(alwaysRun = true)
	public void login() throws FileNotFoundException, IOException, Exception {
		Thread.sleep(1000);
		webDB.enterURL();
		// CommonFunctions.verifyLoginPage("QAtoken");
		CommonFunctions.verifywixStoreLoginPage();
//		CommonFunctions.verifyPlansPage();
//		PlansPage.changeSmallBusinessPlan();
		startTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
		log.info("Test case started at" + " " + startTime);
		Dimension d = new Dimension(1940, 1400);
//		webDB.driver.manage().window().setSize(d);
	}
	// ---------------------------------------------------------------------------------------------------------------------------------------------------

	@Test(description = "Verify that user is able to change and update the Button Display & Position of the chat widget and also set display option formobile/web application.", groups = "PersonalWidget", priority = 1)
	public void checkButtonPositionChat() throws Exception {

		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyChatWidgetPosition();
		assertEquals(true, flag);

	}

	@Test(description = "Verify that on clicking the Personal Widget hyperlink the user is navigated to the Widget Settings page with settings and sharesetting sections.", groups = "PersonalWidget", priority = 2)
	public void checkPersonalWidgetPageNavigation() throws FileNotFoundException, InterruptedException, IOException {

		flag = CommonFunctions.verifyPersonalWidgetPage();
		assertEquals(true, flag);

	}

	@Test(description = "Verify that User is able to change the color,design of the Chat Widget and same should reflect on the store.", groups = "PersonalWidget", priority = 3, invocationCount = 1)
	public void checkChangeDesignChatButton() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.changeDesignChatBuutton();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that User is able to change the color,txt and message body of the Chat Widget and same should reflect on the store.", groups = "PersonalWidget", priority = 4)
	public void checkChatIconMessage() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.changeChatIconAndMessageBodyText();
		assertEquals(true, flag);
	}

	@Test(description = "Verify user is able to edit and update the store on/off hours from the settings and offline message shouldbe displayed on store ongreetings card", groups = "PersonalWidget", priority = 5)
	public void checkStoreOfflineMessageOnStoreOnGreetingsCard()
			throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.markStoreOfflineAndVerifyOfflineMessageOnGreetingsCard();
		assertEquals(true, flag);
	}

	@Test(description = "Verify user is able to edit and update the store on/off hours from the settings and offline message shouldbe displayed on store onalert pop up", groups = "PersonalWidget", priority = 6)
	public void checkStoreOfflineMessageOnStoreOnAlertPopup()
			throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.markStoreOfflineAndVerifyOfflineMessageOnAlertPopup();
		assertEquals(true, flag);
	}

	@Test(description = "Verify user is able to edit and update the store on/off hours from the settings and offline message shouldbe displayed on store onalert pop up", groups = "PersonalWidget", priority = 7)
	public void checkAgentOfflineMessageAsAlert() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.markAgentOfflineAndCheckOfflineMessageOnAlertsPopup();
		assertEquals(true, flag);
	}

	@Test(description = "Verify user is able to edit and update the store on/off hours from the settings and offline message shouldbe displayed on store onalert pop up", groups = "PersonalWidget", priority = 8)
	public void checkAgentOfflineMessageongreetings() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.markAgentOfflineAndCheckOfflineMessageOnGreetings();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user is able to update and save the below for Greetings Widget and same should reflect on store:", groups = "PersonalWidget", priority = 9)
	public void checkGreetingsCardSettings() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyPersonalWidgetPage();
		flag = WixFunctions.checkupdatedGreetingsSettingOnStore();
		assertEquals(true, flag);
	}

	@Test(description = "Verify user is able to change the height and edge offset of the chat widget and same is reflected o n the store", groups = "PersonalWidget", priority = 10)
	public void checkChatWidgetHeightAndEdgeOffset() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.checkChatWidgetOffsetOnStore();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that the chat should not get displayed if the 'Show button when store or agents are offline.' is not selected and storeor agent is offline", groups = "PersonalWidget", priority = 11)
	public void verifyChatButtonWhenStoreOffline() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.checkChatButtonDisplayedWhenAgentsOffline();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user is able to randomize the order of chat agents by selecting the checkbox for the same.", groups = "PersonalWidget", priority = 12)
	public void checkRandomizeAgentOption() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.checkSelectDeselectRandomizeAgentOption();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user is able to enable and disable the Greetings Widget andsame setting should reflect on the store.", groups = "PersonalWidget", priority = 13)
	public void checkEnableDisableGreetings() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyEnableDisableGreetingWidget();
		assertEquals(true, flag);
	}

	@Test(description = "Change text and message for share button", groups = "PersonalWidget", priority = 14)
	public void ChangeTextandMessageShareBtn() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.ChangeTextandMessageShareBtn();
		assertEquals(true, flag);

	}

	@Test(description = "Verify Subheaders in Chat Settings", groups = "PersonalWidget", priority = 15)
	public void verifySubsectionUnderChatSetting() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifySubsectionUnderChatSetting();
		assertEquals(true, flag);

	}

	@Test(description = "Verify that user is able to set the share offset height as per choice", groups = "PersonalWidget", priority = 16)
	public void checkDesktopHeightOffsetShareButton() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyPersonalWidgetPage();
		// modified - commented below and added new wix method
//		flag = WidgetPage.verifyDesktopHeightOffsetShareButton();
		flag = WixFunctions.verifyDesktopHeightOffsetShareButton();
		assertEquals(true, flag);

	}

	@Test(description = "Verify Subheaders in Share Settings", groups = "PersonalWidget", priority = 17)
	public void verifySubsectionUnderShareSetting() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifySubsectionUnderShareSetting();
		assertEquals(true, flag);

	}

	@Test(description = "Verify pages to display Share widget", groups = "PersonalWidget", priority = 18)
	public void verifysharewidgetdisplay() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifypagestodisplaysharewidget();
		flag = WidgetPage.verifyEnableShareWidget();
		assertEquals(true, flag);

	}

	@Test(description = "Verify pages to display Chat Button", groups = "PersonalWidget", priority = 19)
	public void verifychatbuttondisplay() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifypagestodisplaychatbutton();
		// flag = PersonalWidgetPage.verifyDisableChatWidget();
		assertEquals(true, flag);

	}

	@Test(description = "Verify display position share button", groups = "PersonalWidget", priority = 20)
	public void verifydisplaypostionsharebutton() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyPersonalWidgetPage();
		// param 1 is for Display
		// for Desktop + Mobile Radio = 3 //for Desktop Only Radio = 2 ////for Mobile
		// Only Radio = 1
		// param 2 is for position // Left = 1 // Right = 2
		flag = WidgetPage.verifysharebuttondisplayposition("2", "1");
		assertEquals(true, flag);

	}

	@Test(description = "Verify Share button design change", groups = "PersonalWidget", priority = 21)
	public void verifyShareBtnDesign() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyPersonalWidgetPage();
		// param 1 is for pre-configured button design selection
		flag = WidgetPage.verifyShareBtnDesign("1");
		assertEquals(true, flag);

	}

	@Test(description = "Verify Callout", groups = "PersonalWidget", priority = 22)
	public void checkcallout() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifycallout();
		assertEquals(true, flag);

	}

////--------------------------------------OptinNumberPage-------------------------------------------------------

	@Test(description = "Verify that siource should be displayed as widget for the opt ins made through optin pop up...", groups = "OptinNumber", priority = 23)
	public void verifyOptinSourceAsWidget() throws Throwable {
		// CommonFunctions.verifyAdminConfigurtionPage();
		// flag = CustomersPage.checkOptinWidgetSourceInOptinList();
		flag = WixFunctions.checkOptinWidgetSourceInOptinList();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that source should be displayed a scheckout for the optins made through checkout page.", groups = "OptinNumber", priority = 24)
	public void verifyOptinSourceAsCheckout() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = CustomersPage.WixcheckOptinCheckoutSourceInOptinList();
		assertEquals(true, flag);
	}

	// @Test(description = "Verify that user should be shown a validation for empty
	// email id if user tries to submit without email id..", groups = "OptinNumber",
	// priority = 25)
	public void checkEmptyEmailValidationOptinExport() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyOptinNumbersPage();
		flag = CustomersPage.verifyExportEmailEmptyValidation();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user is able to export optin list successfully with all valid information provided.", groups = "OptinNumber", priority = 26)
	public void checkExportSuccess() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyOptinNumbersPage();
		flag = CustomersPage.verifyExportSuccessful();
		assertEquals(true, flag);
	}

	// @Test(description = "Verify that user is given validation for the invalid
	// email Id provide while exporting the optin list...", groups = "OptinNumber",
	// priority = 27)
	public void checkInvalidEmailValidationOptinExport()
			throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyOptinNumbersPage();
		flag = CustomersPage.verifyExportEmailInvalidValidation();
		assertEquals(true, flag);
	}

	// @Test(description = "Verify that only 90 days optin list data can be exported
	// ...", groups = "OptinNumber", priority = 28)
	public void checkDateRangeValidation() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyOptinNumbersPage();
		flag = CustomersPage.verifyDateRangevalidation();
		assertEquals(true, flag);
	}

	// @Test(description = "Verify that user is able to confirm optin from thank you
	// page for number added in the checkout details ...", groups = "OptinNumber",
	// priority = 29)
	public void checkConfirmOptInOnThankYouPageForNumberAddedInCheckout() throws Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = CustomersPage.verifyAddNumberInCheckoutAndConfirmOptInFromThankYouPage();
		assertEquals(true, flag);
	}

	// -------------------------------------------AdminConfigPage----------------------------------------------

	@Test(description = "Verify that user should be able to enter valid country code and whatsapp support number in \"Whatsapp Support Number\" textbox..", groups = "AdminConfig", priority = 30)
	public void checkSupportNumberUpdate() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyAdminConfigurtionPage();
		flag = SettingsPage.updateAndVerifySupportNumber();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that validation should be given for empty support number and country code.", groups = "AdminConfig", priority = 31)
	public void checkSupportNumberEmptyValidation() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyAdminConfigurtionPage();
		flag = SettingsPage.validateEmptySupportNumber();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that validation should be given for invalid support number and country code..", groups = "AdminConfig", priority = 32)
	public void checkSupportNumberInvalidValidation() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyAdminConfigurtionPage();
		flag = SettingsPage.validateInvalidSupportNumber();
		assertEquals(true, flag);
	}

	// @Test(description = "Verify That all optin checkboxes should be disabled if
	// user selects the DIsable optin from Storefront option.", groups =
	// "AdminConfig", priority = 33)
	public void checkDisableOptinStoreFront() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = SettingsPage.selectDisableOptinOptionAndCheckifOptinCheckboxesDisabled();
		assertEquals(true, flag);
	}

	// @Test(description = "Verify That all optin checkboxes should be enabled if
	// user has ewnabled the checkboxes form the Admin configuration Page", priority
	// = 34)
	public void checkEnableOptinStoreFront() throws Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = SettingsPage.selectEnableOptinOptionAndCheckifOptinCheckboxesEnabled();
		assertEquals(true, flag);
	}

	// @Test(description = "Verify that user us able to see the optin pop up on the
	// store on the pages selected on the store.", groups = "AdminConfig", priority
	// = 35)
	public void checkOptinPopupDisplayedOnStore() throws Exception {
		// CommonFunctions.verifyAdminConfigurtionPage();
		flag = SettingsPage.verifyOptinOnStore();
		assertEquals(true, flag);
	}

	// @Test(description = "Verify that user is able to opt in successfully via
	// widget on the storefront.", groups = "AdminConfig", priority = 36)
	public void checkOptInUsingPopUp() throws Exception {
		CommonFunctions.verifyAdminConfigurtionPage();
		flag = SettingsPage.enableOptinsAndSubmitOptinOnStore();
		assertEquals(true, flag);
	}

	// @Test(description = "verify that user should be able to change \"Storefront
	// Opt-in Widget Language\" as per requirement..", groups = "AdminConfig",
	// priority = 37)
	public void checkOptinLanguage() throws Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = SettingsPage.checkLanguageOptinPopupOnStore();
		assertEquals(true, flag);
	}

	// @Test(description = "Verify that country code validations should be present
	// for empty country code entered in the opt in pop up.", groups =
	// "AdminConfig", priority = 38)
	public void checkOptinPopUpCountryCodeEmptyValidations() throws Throwable {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = ShopifyStore.verifyCountryCodeEmptyOptinPopupValidationAlert();
		assertEquals(true, flag);
	}

	// @Test(description = "Verify that country code validations should be present
	// for empty phone entered in the opt in pop up..", groups = "AdminConfig",
	// priority = 39)
	public void checkOptinPopUpPhoneNumberEmptyValidations() throws Throwable {
		CommonFunctions.verifyAdminConfigurtionPage();
		flag = ShopifyStore.verifyPhoneNumberEmptyOptinPopupValidationAlert();
		assertEquals(true, flag);
	}

	// @Test(description = "Verify that country code validations should be present
	// for invalid country code and phone number entered in the opt in pop up.",
	// groups = "AdminConfig", priority = 40)
	public void checkInvalidPhoneAlertMessage() throws Throwable {
		CommonFunctions.verifyAdminConfigurtionPage();
		flag = ShopifyStore.verifyInvalidPhoneAlertMessage();
		assertEquals(true, flag);
	}

	// @Test(description = "Verify that user is able to opt in successfully via
	// widget from the thank you page.", groups = "AdminConfig", priority = 41)
	public void checkThankYouPageOptin() throws Exception {
		CommonFunctions.verifyAdminConfigurtionPage();
		flag = SettingsPage.verifyOptinOnThankyouPage();
		assertEquals(true, flag);
	}

	// @Test(description = "verify that the note is visible on UI - \"Note: Opt-in
	// widget is enabled by default for the “Thank you page“ if you enable
	// anyAutomated Orders CRM template.", priority = 42)
	public void checkThankYouPageOptinEnabledByDefaultNote()
			throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = SettingsPage.checkThankYouPageEnabledByDefaultMessage();
		assertEquals(true, flag);
	}

	@Test(description = "verify that \"Disable opt-in widget from storefront\" option should be disabled before user gets approval for collect opt-in fromcheckout..", groups = "AdminConfig", priority = 43)
	public void checkCheckoutOptinApproved() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = SettingsPage.checkCheckoutOptinApproved();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to navigate properly on respective hyperlinks - \"phone number\" , \"buisness name\" ,\"whatsappsupport number\".", groups = "AdminConfig", priority = 44)
	public void VerifyHyperlinkNavigation() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyAdminConfigurtionPage();
		flag = SettingsPage.VerifyHyperlinkNavigation();
		assertEquals(true, flag);
	}

	// @Test(description = "Verify that user should be able to navigate to pricing
	// sheet after clicking on ? beside country code.", groups = "AdminConfig",
	// priority = 45)
	public void VerifyPricingPageNavigation() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyAdminConfigurtionPage();
		flag = SettingsPage.VerifyPricingPageNavigation();
		assertEquals(true, flag);
	}

	// @Test(description = "verify that user should be able to see preview of thank
	// you page widget after clicking on \"Preview thank you page
	// widget\"hyperlink", groups = "AdminConfig", priority = 46)
	public void VerifyThankyouWidgetHyperlink() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = SettingsPage.VerifyThankyouWidgetHyperlinkENG();
		Thread.sleep(1500);
		flag = SettingsPage.VerifyThankyouWidgetHyperlinkPOR();
		flag = SettingsPage.VerifyThankyouWidgetHyperlinkPOR();
		Thread.sleep(1500);
		flag = SettingsPage.VerifyThankyouWidgetHyperlinkITA();
		Thread.sleep(1500);
		flag = SettingsPage.VerifyThankyouWidgetHyperlinkSPA();
		assertEquals(true, flag);
	}

	// @Test(description = "verify that user should be able to see preiew of opt-in
	// widget after clicking on \"Preview opt-in widget\" hyperlink as perselected
	// langauge.", groups = "AdminConfig", priority = 47)
	public void VerifyPreviewOptInHyperlink() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = SettingsPage.VerifyPreviewOptInHyperlinkPOR();
		Thread.sleep(1500);
		flag = SettingsPage.VerifyPreviewOptInHyperlinkSPA();
		Thread.sleep(1500);
		flag = SettingsPage.VerifyPreviewOptInHyperlinkENG();
		Thread.sleep(1500);
		flag = SettingsPage.VerifyPreviewOptInHyperlinkITA();
		assertEquals(true, flag);
	}

	//// -------------------------------------------MessageLogPage------------------------------------------------
	@Test(description = "Verify that user gets navigated to the MessageLogs page on clicking on MessageLogs tab.", groups = "MessageLogs", priority = 48)
	public void checkMessageLogsPage() throws FileNotFoundException, InterruptedException, IOException {

		flag = CommonFunctions.verifyMessageLogsPage();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user is able to see notes under Message logs title.", groups = "MessageLogs", priority = 49)
	public void verifyNote() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyMessageLogsPage();
		flag = MessageLogsPage.verifyNote();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user is able to view columns headers on Message logs page.", groups = "MessageLogs", priority = 50)
	public void verifyColumnsHeader() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyMessageLogsPage();
		flag = MessageLogsPage.verifyColumnsHeader();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user is able to view names in table.", groups = "MessageLogs", priority = 51)
	public void verifyNamesDisplay() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyMessageLogsPage();
		flag = MessageLogsPage.verifyNamesDisplay();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user is able to filter order,name and type column.", groups = "MessageLogs", priority = 52)
	public void verifyfilterforcolumns() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyMessageLogsPage();
		flag = MessageLogsPage.verifyfilterforcolumns();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user is able to see timestamp in Attempted column.", groups = "MessageLogs", priority = 53)
	public void verifyTimestampcolumn() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyMessageLogsPage();
		flag = MessageLogsPage.verifyTimestampcolumn();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user is able to search by name in Name Textbox.", groups = "MessageLogs", priority = 54)
	public void verifySearchByName() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyMessageLogsPage();
		flag = MessageLogsPage.verifySearchByName();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user is able to search by order in orderid searchbar.", groups = "MessageLogs", priority = 55)
	public void verifySearchByOrderID() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyMessageLogsPage();
		flag = MessageLogsPage.verifySearchByOrderID();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to clear all the filters checked in \"Type\" field by clicking on \"Clear\" in type field.", groups = "MessageLogs", priority = 56)
	public void verifyClearAllButtonOnFilter() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyMessageLogsPage();
		flag = MessageLogsPage.verifyClearAllButtonOnFilter();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to see status of the automated messages in status column.", groups = "MessageLogs", priority = 57)
	public void verifyStatusColumn() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyMessageLogsPage();
		flag = MessageLogsPage.verifyStatusColumn();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to see Type of order - Cart Recovery , Order CRM , Cash on Delivery and Order tracking. ", groups = "MessageLogs", priority = 58)
	public void VerifyTypeOfOrderDisplay() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyMessageLogsPage();
		flag = MessageLogsPage.VerifyTypeOfOrderDisplay();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that when no filters are selected the \"Clear\" should be disabled and it should be enabled when any of field id checked inType field.", groups = "MessageLogs", priority = 59)
	public void VerifyClearButton() throws FileNotFoundException, InterruptedException, IOException {
		// flag = CommonFunctions.verifyLoginPage("accesstoken");
		// if (flag) {
		CommonFunctions.verifyMessageLogsPage();
		flag = MessageLogsPage.VerifyClearButton();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to see all the selected filters below search by order id textbox.", groups = "MessageLogs", priority = 60)
	public void VerifyFilterTextBelowOrderID() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyMessageLogsPage();
		flag = MessageLogsPage.VerifyFilterTextBelowOrderID();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to dis-select filters appearing below search by order id textbox by clicking on X icon.", groups = "MessageLogs", priority = 61)
	public void VerifyClearFilterTags() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyMessageLogsPage();
		flag = MessageLogsPage.VerifyClearFilterTags();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to see filtered results as per selected filters.", groups = "MessageLogs", priority = 62)
	public void VerifyFilterResult() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyMessageLogsPage();
		flag = MessageLogsPage.VerifyFilterResult();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to clear the name in\"Search by Name\" field by clicking on \"Clear\" in Search by namefield.", groups = "MessageLogs", priority = 63)
	public void VerifyClearSearchByNameTxtbox() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyMessageLogsPage();
		flag = MessageLogsPage.ClearSearchByNameTxtbox();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that \"Clear\" should be disabled by default and it should be enabled when you enter text in Search by Name textbox.", groups = "MessageLogs", priority = 64)
	public void VerifyClearSearchByNameTxtboxEnableDisable()
			throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyMessageLogsPage();
		flag = MessageLogsPage.VerifyClearSearchByNameTxtboxEnableDisable();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to sort Message logs by Ascending or Descending on Order , Name , Amount Columns. ", groups = "MessageLogs", priority = 65)
	public void VerifySortingByAscAndDesc() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyMessageLogsPage();
		flag = MessageLogsPage.VerifyOrderSorting();
		Thread.sleep(1000);
		flag = MessageLogsPage.VerifyNameSorting();
		Thread.sleep(1000);
		flag = MessageLogsPage.VerifyAmountSorting();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to see 10 message logs records on one page.", groups = "MessageLogs", priority = 66)
	public void VerifyDataTableRowCount() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyMessageLogsPage();
		flag = MessageLogsPage.VerifyDataTableRowCount();
		assertEquals(true, flag);
	}

	@Test(description = "verify that user should be able to navigate to next page by clicking on \">\" to see more message logs history.", groups = "MessageLogs", priority = 67)
	public void VerifyPageNavigation() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyMessageLogsPage();
		flag = MessageLogsPage.VerifyPageNavigation();
		assertEquals(true, flag);
	}

	@Test(description = "Verify That user is able to see the timestamp for the attempted message logs in Attempted section.", groups = "MessageLogs", priority = 68)
	public void VerifyTimestampInAttemptedSection() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyMessageLogsPage();
		flag = MessageLogsPage.VerifyTimestamp();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to see Amount as per selected Currency.", groups = "MessageLogs", priority = 69)
	public void VerifyAmountDisplay() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyMessageLogsPage();
		flag = MessageLogsPage.VerifyAmountDisplay();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that message logs by default are sorted as per the latest timestamp.", groups = "MessageLogs", priority = 70)
	public void VerifyLatestSortedTimestamp()
			throws FileNotFoundException, InterruptedException, IOException, ParseException {
		CommonFunctions.verifyMessageLogsPage();
		flag = MessageLogsPage.VerifyLatestSortedTimestamp();
		assertEquals(true, flag);
	}

	@Test(description = "Verify Order Confirmation is displayed in Message Logs", groups = "MessageLogs", priority = 71)
	public void VerifyOrderConfirmationMessageLog() throws FileNotFoundException, InterruptedException, IOException {
		// flag = MessageLogsPage.VerifyOrderConfirmationMessageLog();
		flag = WixFunctions.VerifyOrderConfirmationMessageLog();
		assertEquals(true, flag);
	}

	// ////--------------------------------------------WhatsAppAPIPage-----------------------------------------------
	// @Test(description = "Verify Validations on WhatsApp API Page", groups =
	// "WhatsAppAPI", priority = 72)
	@Ignore
	public void VerifyValidationsApplicationForm() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyNewStoreLoginPage("Qaautomationstore");
		CommonFunctions.VerifyWhatsAPPAPIPage();
		flag = MyWhatsAppPage.VerifyValidationsApplicationForm();
		assertEquals(true, flag);
	}

	// @Test(description = "Verify Facebook Business Manager ID", groups =
	// "WhatsAppAPI", priority = 73)
	@Ignore
	public void VerifyFaceBookBusinessManagerID() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyNewStoreLoginPage("Qaautomationstore");
		CommonFunctions.VerifyWhatsAPPAPIPage();
		flag = MyWhatsAppPage.VerifyFaceBookBusinessManagerID();
		assertEquals(true, flag);
	}

	// @Test(description = "Verify WhatsApp Number", groups = "WhatsAppAPI",
	// priority = 74)
	@Ignore
	public void VerifyWhatsAppNoAlreadyExists() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyNewStoreLoginPage("Qaautomationstore");
		CommonFunctions.VerifyWhatsAPPAPIPage();
		flag = MyWhatsAppPage.VerifyWhatsAppNoAlreadyExists();
		assertEquals(true, flag);
	}

	@Test(description = "Verify ContactUS and Rating feature", groups = "Templates", priority = 75)
	public void verifyContactusRatingFeature() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyContactRatingfeature();
		log.info("verifed personal widget page contact us rating feature");
		CommonFunctions.verifyTemplatesPage();
		flag = AutomationPage.verifyContactRatingfeature();
		log.info("verifed templates page contact us rating feature");
		CommonFunctions.verifyOptinNumbersPage();
		flag = CustomersPage.verifyContactRatingfeature();
		log.info("verifed customers page contact us rating feature");
		CommonFunctions.VerifyWhatsAPPAPIPage();
		flag = MyWhatsAppPage.verifyContactRatingfeature();
		assertEquals(true, flag);

	}

	@Test(description = "Verify that user is able to update template language for Order Shipment Automated template..", groups = "Templates", priority = 76)
	public void checkOrderShipmentLanguageUpdation() throws Exception {
		CommonFunctions.verifyTemplatesPage();
		flag = AutomationPage.verifyChangeLanguageOrderShipmentAutomatedTemplate();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user is able to update template language for COD Automated template.", groups = "Templates", priority = 77)
	public void checkCODLanguageUpdation() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyTemplatesPage();
		flag = AutomationPage.verifyChangeLanguageCODAutomatedTemplate();
		assertEquals(true, flag);
	}

	// @Test(description = "Verify that user is able to add Percentage Discount in
	// CART Template.", groups = "Templates", priority = 78)
	public void checkAddDiscountABCART() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyTemplatesPage();
		flag = AutomationPage.addDiscountToABCartTemplate();
		assertEquals(true, flag);
	}

	// @Test(description = "Verify that user is able to add Percentage Discount in
	// Order Confirmation Template..", groups = "Templates", priority = 79)
	public void checkAddDiscountOrderConfirmation() throws Exception {
		CommonFunctions.verifyTemplatesPage();
		flag = AutomationPage.addDiscountTypeToOrderConfirmationTemplate();
		assertEquals(true, flag);
	}

	// @Test(description = "Verify that user is able to add Fixed Discount in
	// Abandoned Cart Template.", groups = "Templates", priority = 80)
	public void checkAddFixedDiscountABCARTTemplate() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyTemplatesPage();
		flag = AutomationPage.addCouponToABCartTemplate();
		assertEquals(true, flag);
	}

	// @Test(description = "Verify that user is able to add Fixed Discount in Order
	// COnfirmation Template.", groups = "Templates", priority = 81)
	public void checkAddFixedDiscountOderConfirmationTemplate() throws Exception {
		CommonFunctions.verifyTemplatesPage();
		flag = AutomationPage.addCouponToOrderConfirmationTemplate();
		assertEquals(true, flag);
	}

	// @Test(description = "Verify that user is able to add Fixed Discount in Order
	// Shipment Template.", groups = "Templates", priority = 82)
	public void checkTrackingUrlConfigurationOrderShipment()
			throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyTemplatesPage();
		flag = AutomationPage.verifyOrderShipmentTrackingUrlConfiguration();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that validation is given for invalid country code provided for COD confirmation page..", groups = "Templates", priority = 83)
	public void checkCountryCodeValidationCODCallSetting()
			throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyTemplatesPage();
		flag = AutomationPage.verifyInvalidCountryCodeOnCodConfirmationPage();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that validation is given for invalid phone Number Provided for COD confirmation page..", groups = "Templates", priority = 84)
	public void checkPhoneNumberValidationCODCallSetting()
			throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyTemplatesPage();
		flag = AutomationPage.verifyInvalidPhoneOnCodConfirmationPage();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that validation is given for invalid email id provided for COD confirmation page..", groups = "Templates", priority = 85)
	public void checkEmailValidationCODCallSetting() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyTemplatesPage();
		flag = AutomationPage.verifyInvalidEmailOnCodConfirmationPage();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user is able to update the call and Email to be provided on COD Confirmation page..", groups = "Templates", priority = 86)
	public void checkCODConfirmationPageCallAndEmailDetailsAddition()
			throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyTemplatesPage();
		flag = AutomationPage.verifyCallAndEmailOptionSelectionCODSettings();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user is able to update the COD tags in COD settings of Automated Templates.", groups = "Templates", priority = 87)
	public void checkCODTagsUpdation() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyTemplatesPage();
		flag = AutomationPage.updateCODTags();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user is select COD order cancellation on either cancellation or No response or both", groups = "Templates", priority = 88)
	public void checkSelectUnselectCODCancellationCheckboxes()
			throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyTemplatesPage();
		flag = AutomationPage.selectUnselectCancellationAndNoResponseCheckBox();
		log.info("Value of flag is " + flag);
		assertEquals(true, flag);
	}

	@Test(description = "Verify that settings button gets disabled when user disables the Automated Template Messaging..", groups = "Templates", priority = 89)
	public void checkSettingsDisabledOnDisablingTemplate()
			throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyTemplatesPage();
		flag = AutomationPage.disableAllAutomatedTemplatesAndCheckSettings();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that settings button gets enabled when user enables the Automated Template Messaging..", groups = "Templates", priority = 90)
	public void checkSettingsEnableOnEnablingTemplate()
			throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyTemplatesPage();
		flag = AutomationPage.enableAllAutomatedTemplatesAndCheckSettings();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user is able to update template language for ABCART Automated template.", groups = "Templates", priority = 91)
	public void checkABCARTLanguageUpdation() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyTemplatesPage();
		flag = AutomationPage.verifyChangeLanguageABCARTAutomatedTemplate();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user is able to update template language for Order Confirmation Automated template.", groups = "Templates", priority = 92)
	public void checkOrderCRMLanguageUpdation() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyTemplatesPage();
		flag = AutomationPage.verifyChangeLanguageOrderCRMAutomatedTemplate();
		assertEquals(true, flag);
	}

	// @Test(description = "Verify that user should be able to enable / disable any
	// extension template as per requirement.", groups = "Templates", priority = 93)
	public void VerifyEnableDisableOfTemplate() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyMessagingPage();
		Thread.sleep(2000);
		flag = AutomationPage.VerifyEnableDisableOfTemplate();
		assertEquals(true, flag);
	}

	@Test(description = "verify that user should not be able to create template keeping the textboxes empty,Template name can not be empty error message shoul be displayed.", groups = "Templates", priority = 94)
	public void VerifyTemplateNameNotEmpty() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyMessagingPage();
		flag = WixFunctions.VerifyTemplateNameNotEmpty();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should not be able to create two templates with same name in same category,'Template With Same Name Already Exists' error message should be displayed.", groups = "Templates", priority = 95)
	public void VerifyDuplicateTemplateName() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyMessagingPage();
		flag = WixFunctions.VerifyDuplicateTemplateName();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to see template's Category , "
			+ "Name and message text on templates page after creating the templates.", groups = "Templates", priority = 96)
	public void VerifyTemplateContents() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyMessagingPage();
		flag = WixFunctions.VerifyTemplateContents();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to see all the placeholders at the bottom of page.", groups = "Templates", priority = 97)
	public void VerifyPagePlaceholders() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyMessagingPage();
		flag = AutomationPage.VerifyPagePlaceholders();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to cancel all the changes made while editing template by clicking on \"Discard\" button.", groups = "Templates", priority = 98)
	public void VerifyDiscardBtnOnCreateTemplate() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyMessagingPage();
		flag = WixFunctions.VerifyDiscardBtnOnCreateTemplate();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to go back to extension templates page after clicking on \"Go Back\".", groups = "Templates", priority = 99)
	public void VerifyGOBackBtnOnCreateTemplate() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyMessagingPage();
		flag = AutomationPage.VerifyGOBackBtnOnCreateTemplate();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to edit tempates name as per requirement.", groups = "Templates", priority = 100)
	public void VerifyEditTemplateName() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyMessagingPage();
		flag = WixFunctions.VerifyEditTemplateName();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user is able to edit Template that are already created .", groups = "Templates", priority = 101)
	public void checkEditTemplate() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyMessagingPage();
		flag = WixFunctions.createAndEditTemplateName();
		assertEquals(true, flag);

	}

	@Test(description = "Verify that user should be able to select category as per requirement.", groups = "Templates", priority = 102)
	public void VerifyCategoryForTemplate() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyMessagingPage();
		flag = WixFunctions.VerifyCatogeryForTemplate();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that after clicking on cancel button the new changes made should not be saved "
			+ "and the pop-up should get close.", groups = "Templates", priority = 103)
	public void VerifyCancelSettingButton() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyTemplatesPage();
		flag = AutomationPage.VerifyCancelSettingButton();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to close Settings pop-up after clicking on \"X\".", groups = "Templates", priority = 104)
	public void VerifyCloseSettingPopup() throws FileNotFoundException, InterruptedException, IOException {

		flag = CommonFunctions.verifyTemplatesPage();
		if (flag)
			flag = AutomationPage.VerifyCloseSettingPopup();
		assertEquals(true, flag);

	}

	// @Test(description = "Verify that \"pricing chart\" hyperlink should navigate
	// properly to pricing chart.", groups = "Templates", priority = 105)
	public void VerifyPricingChart() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyTemplatesPage();
		flag = AutomationPage.VerifyPricingChart();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that when user enables order shipment template the note should appear below template - \"\r\n"
			+ "Note: Order Shipment template language is based on language set in Order Confirmation.\".", groups = "Templates", priority = 106)
	public void VerifyNoteOnOrderShipmentTemplate() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyTemplatesPage();
		flag = AutomationPage.VerifyNoteOnOrderShipmentTemplate();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that when user enables cod template the note should appear below template - \"\r\n"
			+ "Note: If both COD Order Confirmation and regular Order Confirmation templates are enabled, "
			+ "then the regular Order Confirmation message will be sent after your customer confirms a COD order.\".", groups = "Templates", priority = 107)
	public void VerifyNoteOnCODTemplate() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyTemplatesPage();
		flag = AutomationPage.VerifyNoteOnCODTemplate();
		assertEquals(true, flag);
	}

	//// -----------------------------------------------ManualMessaging-----------------------------------------------
	//
	@Test(description = "Verify Manual Messaging Page", groups = "ManualMessaging", priority = 108)
	public void verifyManualMessagingPage() throws FileNotFoundException, InterruptedException, IOException {

		flag = CommonFunctions.verifyManualMessagingPage();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to see 3 sub-tabs in Manual Messages - 1. Abandoned Cart  2. Orders CRM 3. Settings ", groups = "ManualMessaging", priority = 109)
	public void verifySubTabsManualMessagingPage() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifySubTabsManualMessagingPage();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to see 2 Notes regarding WhatsApp's spam detection.", groups = "ManualMessaging", priority = 110)
	public void verifyNotesManualMessagingPage() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyNotesManualMessagingPage();
		assertEquals(true, flag);
	}

	@Test(description = "Verify user should be able to see Note - \"Send manual messages faster by opening our app on your mobile phone browser, or by using WhatsApp Desktop app. Go to Settings tab to configure.\" below sub-tabs on Abandoned Cart.", groups = "ManualMessaging", priority = 111)
	public void verifyAbandonedNoteManualMessagingPage()
			throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyAbandonedNoteManualMessagingPage();
		assertEquals(true, flag);
	}

	@Test(description = "Verify user should be able to see Note - \"Send manual messages faster by opening our app on your mobile phone browser, or by using WhatsApp Desktop app. Go to Settings tab to configure.\" below sub-tabs on Order CRM Cart.", groups = "ManualMessaging", priority = 112)
	public void verifyOrderCRMNoteManualMessagingPage()
			throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyOrderCRMNoteManualMessagingPage();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to see whatsapp configuration settings on Settings page - Send messages from your computer using - 1.Whatsapp web  and 2. Whatsapp Desktop App.", groups = "ManualMessaging", priority = 113)
	public void verifyWhatsAPPConfigSettings() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyWhatsAPPConfigSettings();
		assertEquals(true, flag);
	}

	@Test(description = "verify that user should redirect properly whatsapp desktop site after clicking on hyperlink \"WhatsApp Desktop application\"", groups = "ManualMessaging", priority = 115)
	public void verifyWAdesktopAppHyperlinkSettings() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyWAdesktopAppHyperlinkSettings();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to see 2 buttons - 1. View Tag and 2. Create Tag in Tags section.", groups = "ManualMessaging", priority = 117)
	public void verifyTagsButtons() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyTagsButtons();
		assertEquals(true, flag);
	}

	@Test(description = "Verify user should be able to view all created tags after clicking on \"View tags\" button.", groups = "ManualMessaging", priority = 119)
	public void verifyCreatedTags() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyCreatedTags();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that pop-up should appear when user clicks on create tag button on settings page ; on this pop-up user should be able to add tag name and category.", groups = "ManualMessaging", priority = 121)
	public void verifyTagPopup() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyTagPopup();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to close the create tag pop-up by clicking on X button w/o saving the changes.", groups = "ManualMessaging", priority = 122)
	public void verifyClosePopup() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyClosePopup();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to save the changes made in create tag pop-up by clicking on save button and should close the pop-up.", groups = "ManualMessaging", priority = 123)
	public void verifySavedTag() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifySavedTag();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to close the create tag pop-up by clicking on Cancel button w/o saving the changes.", groups = "ManualMessaging", priority = 124)
	public void verifyCancelPopup() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyCancelPopup();
		assertEquals(true, flag);
	}

	@Test(description = "Verify user should not be able to create 2 tags with same name irrespective of  category  ( tag name should be unique). ", groups = "ManualMessaging", priority = 125)
	public void verifyDuplicateTag() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyDuplicateTag();
		assertEquals(true, flag);
	}

	@Test(description = "Verify user should not be able to create tag keeping either of the  textboxes (\"Name\" , \"Select Category\") empty.", groups = "ManualMessaging", priority = 126)
	public void verifyEmptyTagFields() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyEmptyTagFields();
		assertEquals(true, flag);
	}

	@Test(description = "Verify user should be able to see error message when user tries to save tag kepping the Name textbox empty\r\n"
			+ "\"Tag name cannot be empty\".", groups = "ManualMessaging", priority = 127)
	public void verifyEmptyTagNameField() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyEmptyTagNameField();
		assertEquals(true, flag);
	}

	@Test(description = "Verify user should be able to see error message when user tries to save tag w/o selecting category.\r\n"
			+ "\"Please Select a Categoy\". ", groups = "ManualMessaging", priority = 128)
	public void verifyEmptyTagCategoryField() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyEmptyTagCategoryField();
		assertEquals(true, flag);
	}

	@Test(description = "Verify user should be able to see error message when user tries to save template w/o adding message.\r\n"
			+ "\"Template message cannot be empty\".", groups = "ManualMessaging", priority = 132)
	public void verifyEmptyTemplateMessageField() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyEmptyTemplateMessageField();
		assertEquals(true, flag);
	}

	@Test(description = "Verify user should be able to see error message when user tries to save template w/o selecting category.\r\n"
			+ "\"Please select a category\"", groups = "ManualMessaging", priority = 133)
	public void verifyEmptyTemplateCategoryField() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyEmptyTemplateCategoryField();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to close the create template pop-up by clicking on \" X \" w/o saving the changes.", groups = "ManualMessaging", priority = 135)
	public void verifyClosePopupTemplate() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyClosePopupTemplate();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to close the create template pop-up by clicking on Cancel button w/o saving the changes.", groups = "ManualMessaging", priority = 136)
	public void verifyCancelPopupTemplate() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyCancelPopupTemplate();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to save the changes made in create template pop-up by clicking on save button and should close the pop-up.", groups = "ManualMessaging", priority = 137)
	public void verifySavedTemplate() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifySavedTemplate();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to see Filters  on Abandoned cart and Order crm - 1. order id  2. Tag  3. Name. ", groups = "ManualMessaging", priority = 138)
	public void VerifyFilters() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.VerifyFilters();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to see all checkouts in abandoned cart.", groups = "ManualMessaging", priority = 139)
	public void VerifyDataTableAbandonedCart() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.VerifyDataTableAbandonedCart();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to see all Order crm orders in Order crm.", groups = "ManualMessaging", priority = 140)
	public void VerifyDataTableOrderCRM() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.VerifyDataTableOrderCRM();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to apply filter as per requirement and should be able to remove filters by clicking on \"clear\"", groups = "ManualMessaging", priority = 141)
	public void VerifyClearFilterAbandonedCart() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.VerifyClearFilterAbandonedCart();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to apply filter as per requirement and should be able to remove filters by clicking on \"clear\"", groups = "ManualMessaging", priority = 142)
	public void VerifyClearFilterORDERCRM() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.VerifyClearFilterORDERCRM();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to apply ordering (ascending or descending) on Amount and Order in list table.", groups = "ManualMessaging", priority = 143)
	public void VerifyOrderDataTable() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.VerifyOrderDataTable();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to see respective order status in \"status\" column in order list table.", groups = "ManualMessaging", priority = 144)
	public void VerifyOrderStatusDataTable() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.VerifyOrderStatusDataTable();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to see 10 orders on one page.", groups = "ManualMessaging", priority = 145)
	public void verifyOrderCountDisplay() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyOrderCountDisplay();
		assertEquals(true, flag);
	}

	@Test(description = " Verify that user should be able to navigate to the Previous page by clicking on \">\" button (left bottom side of the table).", groups = "ManualMessaging", priority = 146)
	public void verifyNextNavigation() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyNextNavigation();
		assertEquals(true, flag);
	}

	@Test(description = " Verify that user should be able to navigate to the next page by clicking on \"<\" button (left bottom side of the table).", groups = "ManualMessaging", priority = 147)
	public void verifyPrevtNavigation() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyPrevtNavigation();
		assertEquals(true, flag);
	}

	@Test(description = " Verify that user should be able to see Search box to search message logs by checkout id/ Order id. ", groups = "ManualMessaging", priority = 148)
	public void verifyOrderIDSearchBox() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyOrderIDSearchBox();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that all placeholders are working template message.", groups = "ManualMessaging", priority = 149)
	public void verifyPlaceholderMessageTemplate() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		// modified - created wix method
//		flag = MessagingPage.verifyPlaceholderMessageTemplate();
		flag = WixFunctions.verifyPlaceholderMessageTemplate();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to edit / delete tags in tags pop-up after clicking on view tags button.", groups = "ManualMessaging", priority = 150)
	public void verifyEditTag() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyEditTag();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to edit / delete templates in message templates pop-up after clicking on view templates button.", groups = "ManualMessaging", priority = 151)
	public void verifyEditTemplate() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = WixFunctions.verifyEditTemplate();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that If no tags are created usershould be able to see this message on UI - Please add tags from Settings Tab", groups = "ManualMessaging", priority = 152)
	public void verifyNoTagMsg() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyNoTagMsg();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that If no Templates are created usershould be able to see this message on UI - Please add message templates from Settings Tab", groups = "ManualMessaging", priority = 153)
	public void verifyNoTemplateMsg() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyNoTemplateMsg();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to see \"send message' pop-up after clicking on Send Message button.", groups = "ManualMessaging", priority = 154)
	public void verifySendMsgPopup() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifySendMsgPopup();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that by clicking on \"X\"  on send message pop-up should get close.", groups = "ManualMessaging", priority = 155)
	public void verifyCloseSendMsgPopup() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyCloseSendMsgPopup();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that by clicking on \"cancel\" button on send message pop-up should get close.", groups = "ManualMessaging", priority = 156)
	public void verifyCancelSendMsgPopup() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyCancelSendMsgPopup();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that the send button is disabled until the user selects any template to send it to the customer.", groups = "ManualMessaging", priority = 157)
	public void verifySendButtonDisabledOnSendMsgPopup()
			throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifySendButtonDisabledOnSendMsgPopup();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to edit tag name.", groups = "ManualMessaging", priority = 158)
	public void verifyEditTagName() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyEditTagName();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able edit following things in templates - 1. Name 2. Message 3. Category 4.Add / Remoove discount.", groups = "ManualMessaging", priority = 159)
	public void verifyEditTemplatePlaceholders() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyEditTemplatePlaceholders();
		assertEquals(true, flag);
	}

	@Test(description = "Verify Templates limit - 15 ", groups = "ManualMessaging", priority = 160)
	public void verifyTemplateLimit() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyTemplateLimit();
		assertEquals(true, flag);
	}

	@Test(description = "Tags limit - 20 | 10 each category ", groups = "ManualMessaging", priority = 161)
	public void verifyTagLimit() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyTagLimit();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to send message to customers by clicking on send message button.", groups = "ManualMessaging", priority = 162)
	public void verifySendMessage() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		// modified - created new wix method
//		flag = MessagingPage.verifySendMessage();
		flag = WixFunctions.verifySendMessage();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user redirects to selected option (whatsapp web) after clicking on send button on send message pop-up.", groups = "ManualMessaging", priority = 163)
	public void verifySendTemplateRedirectWApage() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		// modified - created new wix method
//		flag = MessagingPage.verifySendTemplateRedirectWApage();
		flag = WixFunctions.verifySendTemplateRedirectWApage();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user redirects to selected option (whatsapp desktop app) after clicking on send button on send message pop-up.", groups = "ManualMessaging", priority = 164)
	public void verifySendTemplateRedirectWAapp() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyManualMessagingPage();
		// modified - created new wix method
//		flag = MessagingPage.verifySendTemplateRedirectWAapp();
		flag = WixFunctions.verifySendTemplateRedirectWAapp();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that validation is given if the user enters a past date in from date field", groups = "ManualMessaging", priority = 168)
	public void checkStartingDateValidationClickAnalytics()
			throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyAnalyticsPage();
		flag = AnalyticsPage.verifyAutomatedMessageMaxMonthsDateFiler();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that 2 Transaction templates are added in automated templates - 1. Payment Successful 2. Order Cancellation. ", groups = {
			"regression" }, priority = 213)
	public void verifyEnableDisableAndSettings()
			throws FileNotFoundException, InterruptedException, IOException, ParseException {
		flag = AutomationPage.verifyEnableDisablePaymentSucessfullTemplate();
		if (flag) {
			flag = AutomationPage.verifyEnableDisableOderCancellationTemplate();
		}
		assertEquals(true, flag);
	}

	@Test(description = "Verify that as per selected langauge in settings pop-up template should reflect.", groups = {
			"regression" }, priority = 215)
	public void verifyLanguageInTemplate()
			throws FileNotFoundException, InterruptedException, IOException, ParseException {
		flag = AutomationPage.verifyTemplateLanguage();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that 3 templates are available in Automated Promotional - 1. Cross Sell 2. Customer Winback 3. Feedback.", groups = {
			"regression" }, priority = 217)
	public void verifyAutomatedPromotionalTabNote()
			throws FileNotFoundException, InterruptedException, IOException, ParseException {
		flag = AutomationPage.verifyAutomatedPromotionalTabAndNote();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user should be able to Enable or disable promotional tempalates.", groups = {
			"regression" }, priority = 218)
	public void verifyPromotionalEnableDisableAndSettings()
			throws FileNotFoundException, InterruptedException, IOException, ParseException {
		flag = AutomationPage.verifyEnableDisableCrossSellTemplate();
		if (flag) {
			flag = AutomationPage.verifyEnableDisableCustomerWinbackTemplate();
			if (flag) {
				flag = AutomationPage.verifyEnableDisableFeedbackTemplate();
			}
		}
		assertEquals(true, flag);
	}

	@Test(description = "Verify that pricing chart hyperlink works properlly in all promotional templates notes.", groups = {
			"regression" }, priority = 219)

	public void verifyPromotionalHyperlinks()
			throws FileNotFoundException, InterruptedException, IOException, ParseException {
		flag = CommonFunctions.verifyTemplatesPage();
		if (flag) {
			log.info("verified templates page");
			flag = AutomationPage.verifyCrossSellHyperLinks();
			if (flag) {
				log.info("verified Crosssell hyperlink");
				flag = AutomationPage.verifyCustomerWinbackHyperLinks();
				// commented bcz not available in wix
//				if (flag) {
//					log.info("verified customer winback hyperlink");
//					flag = AutomationPage.verifyPromotionalShopifyHyperLink();
				if (flag) {
					log.info("verified feedback hyper links");
					flag = AutomationPage.verifyFeedbackHyperLinks();
				}
			}
		}
//		}
		assertEquals(true, flag);
	}

	@Test(description = "Verify that user is able to spin the wheel just once", groups = {
			"regression" }, priority = 226)

	public void verifySpinWheelOnce() throws Exception, Exception, Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.VerifyWheelSpinsOnce();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that after user spins the wheel, he wins the specified discount and a field for entering whatsapp number appears ", groups = {
			"regression" }, priority = 227)
	public void verifySpinWheelAfterWins() throws Exception, Exception, Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.VerifyWheelSpinsAfterWins();
		assertEquals(true, flag);

	}

	@Test(description = "Verify the message when user doesn't win any discount", groups = {
			"regression" }, priority = 228)

	public void verifyUserNotWinDiscount() throws Exception, Exception, Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.VerifyUserNotWinDiscount();
		assertEquals(true, flag);
	}

	@Test(description = "Verify the message when user doesn't win any discount", groups = {
			"regression" }, priority = 229)

	public void verifyMinMaxDiscountSection() throws Exception, Exception, Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyMinimumMaximumDiscountSection();
		assertEquals(true, flag);
	}

	@Test(description = "Verify user is able to select design in spin wheel", groups = { "regression" }, priority = 230)

	public void verifySelectDesign() throws Exception, Exception, Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyAtleastSelectOneDesign();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that if we select any language and then click on save that language is saved or not ", groups = {
			"regression" }, priority = 231)

	public void verifyMessagelanguageSection() throws Exception, Exception, Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyMessagelanguageSection();
		assertEquals(true, flag);
	}

	@Test(description = "Verify the message when user doesn't win any discount in spin wheel", groups = {
			"regression" }, priority = 232)

	public void verifyMaxMinDiscountText() throws Exception, Exception, Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyMaxMinDiscountText();
		assertEquals(true, flag);
	}

	@Test(description = "Verify Analytics Page Section of All Graphs", priority = 233)
	public void verfiyAnalyticsPageSections() throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyAnalyticsPage();
		// modified - added new method for below
		flag = WixFunctions.verifyAnalyticsPageOtherMessageSections();
		if (flag) {
			log.info("verfied Analytics page");
			flag = AnalyticsPage.verifyOtherMessageGraph();
			if (flag) {
				log.info("verfied Other Message Graph");
				// modified - added new method for below
				flag = WixFunctions.verifyAbandedcartOthermsgGraphShows();
			}
		}
		assertEquals(true, flag);
	}

	@Test(description = "Verify Maximum and Minium for all Date filter", priority = 234)

	public void verfiyMaxMonthsDateFilerAnalyticsPage()
			throws FileNotFoundException, InterruptedException, IOException {

		CommonFunctions.verifyAnalyticsPage();
		flag = AnalyticsPage.verifyWidgetMaxMonthsDateFiler();
		if (flag) {
			flag = AnalyticsPage.verifySpinWheelMaxMonthsDateFiler();
			if (flag) {
				flag = AnalyticsPage.verifyAutomatedMessageMaxMonthsDateFiler();
				if (flag) {
					flag = AnalyticsPage.verifyOptsInMaxMonthsDateFiler();
				}
			}
		}
		assertEquals(true, flag);
	}

	@Test(description = "Verify Question Icon", priority = 235)

	public void verifyQuestionMarkIconStartGuide() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyAnalyticsPage();
		flag = AnalyticsPage.verifyStartGuideIcon();
		assertEquals(true, flag);
	}

	@Test(description = "Verify Disable Analytics", priority = 236)

	public void verifyDisableAnalytics() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyAnalyticsPage();
		flag = AnalyticsPage.verifySpinWheelCurrentlyDisableEnable();
		if (flag) {
			// modified - changed to wix
			flag = WixFunctions.verifyAbandonedCurrentlyDisableEnable();
			if (flag) {
				flag = AnalyticsPage.verifyOtherMessageCurrentlyDisableEnable();
			}
		}
		assertEquals(true, flag);

	}

	@Test(description = "Verify Chat count for Spin wheel,Share and Chat widget", priority = 237)

	public void verifyChatShareSpinCount() throws Exception {
		flag = AnalyticsPage.verifyClickcount();
		if (flag) {
			flag = DriverBase.verifyResponseAPI("CronGetClickCount");
			if (flag) {
				flag = DriverBase.verifyResponseAPI("GetClickCount");
			}
		}
		assertEquals(true, flag);
	}

	@Test(description = "Verify that when we select customer section all the required option is showing or not", priority = 238)

	public void verifyCustomerOptions() throws Exception {
		CommonFunctions.verifyOptinNumbersPage();
		flag = CustomersPage.verifyCustomersRequiredOptions();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that what ever name user gives while placing order same name is showing or not ", priority = 239)

	public void verifyCustomerOrderDetails() throws Exception {
		CommonFunctions.verifyPersonalWidgetPage();
//		flag = CustomersPage.checkOptinDetailsInOptinList();
		flag = WixStore.checkOptinDetailsInOptinList();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that correct opt in source is  showing or not", priority = 240)

	public void verifyOptinSourceAsSpinWheel() throws Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = CustomersPage.VerifyOptinSourcesAsSpinWheel();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that when select template and click on send it is redirecting to selected customer whats app number or not ", priority = 241)

	public void verifyRedirectWhatsappweb() throws Exception {
		CommonFunctions.verifyOptinNumbersPage();
		flag = CustomersPage.verifyRedirectWhatswebURL();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that correct opt in source is  showing or not", priority = 243)

	public void verifyCustomsTemplates() throws Exception {
//		CommonFunctions.verifyMarketCampaignsPage();
		flag = AutomationPage.verifyCustomTemplateValidation();
		if (flag) {
			log.info("Verified Inputs Validations");
			flag = AutomationPage.verifyCustomTemplateValidationwithCallAction();
			if (flag) {
				log.info("Verified Call to Actions Inputs Validations");
				flag = AutomationPage.verifyCreateCustomTemplateswithoutFooterHeader();
				if (flag) {
					log.info("Veried Create Custom templates without FooterHeader");
					flag = AutomationPage.verifyCreateCustomTemplateswithFooterHeader();
					if (flag) {
						log.info("Veried Create Custom templates with FooterHeader");
						flag = AutomationPage.verifyOptionalMandatoryInput();
						if (flag) {
							log.info("Veried Custom templates Optional Mandatory Input");
//							flag = TemplatesPage.verifyGuideCustomTemplates();
//							if (flag) {
//								log.info("Veried Custom templates Optional Mandatory Input");
//								flag = WhatsAppAPIPage.verifyWApageGuide();
//							}
						}
					}
				}
			}
		}
		assertEquals(true, flag);
	}

//	@Test(description = "Verify Free plans page", priority = 244)
	public void verifyFreePlansPage() throws Exception {
		CommonFunctions.verifyPlansPage();
		flag = PlansPage.changeFreePlan();
		if (flag) {
			log.info("Change plan");
			flag = PlansPage.verifyPlanUiSection();
			if (flag) {
				log.info("verified Plan UI section");
				flag = PlansPage.verifyCustomTemplatesPageNavigate();
				if (flag) {
					log.info("verified Customs templates");
					flag = PlansPage.verifyExtensionTemplatesPageNavigate();
					if (flag) {
						log.info("verified Extension templates");
						flag = PlansPage.verifyPrivatewabaPageNavigate();
					}
				}
			}
		}

		assertEquals(true, flag);
	}

//	@Test(description = "Verify subscription validation", priority = 245)
	public void verifySubscriptionValidations() throws Exception {
		CommonFunctions.verifyPlansPage();
		flag = PlansPage.changeFreePlan();
		if (flag) {
			log.info("Change plan");
			flag = PlansPage.verifySubscriptionNavigation();
			if (flag) {
				log.info("Subscription Navigation");
				flag = PlansPage.verifyVisibleSection();
				if (flag) {
					log.info("Visible section");
					flag = AutomationPage.verifyWhatsapptoolSubscribed();
					if (flag) {
						log.info("Whats app Tool Verified");
						flag = AutomationPage.verifyGuideAutomatedTemplates();
						if (flag) {
							log.info("Verified Guide Templates");
							flag = AutomationPage.verifyGuideAutomatedPromotionalTemplates();
						}
					}
				}
			}
		}
		assertEquals(true, flag);
	}

//	@Test(description = "Upgrade Plans", priority = 246)
	public void verifyUpgradePlans() throws Exception {
		CommonFunctions.verifyPlansPage();
		flag = PlansPage.changeFreePlan();
		if (flag) {
			flag = PlansPage.verifyUpgradePlanValidations();
		}
		PlansPage.changePlanAPI(PlansPageLocators.GROWTH);
		assertEquals(true, flag);
	}

	@Test(description = "verify create custom grps and verify grp inputs validations", priority = 247)
	public void verifyCreateCustomGrpsAndValidation() throws Exception {
		CommonFunctions.verifyOptinNumbersPage();
		flag = CustomersPage.VerifyCreateCustomerGrp();
		if (flag) {
			flag = CustomersPage.verifyValidationGrpTab();
		}
		assertEquals(true, flag);
	}

	@Test(description = "Verify the user group is editable", priority = 248)
	public void VerifyUserGroupEdit() throws Exception {
		CommonFunctions.verifyOptinNumbersPage();
		flag = CustomersPage.editUserGroup();
		assertEquals(true, flag);

	}

	@Test(description = "verify deleting user group", priority = 249)
	public void VerifyDeleteUserGroup() throws Exception {
		CommonFunctions.verifyOptinNumbersPage();
		flag = CustomersPage.verifyDeleteUserGroup();
		assertEquals(true, flag);

	}

	@Test(description = "verify create custom template with predefined and add custom variables", priority = 250)

	public void verifyCustomerPredefinedVariableAndVerifyMarketCampaigns() throws Exception {
		CommonFunctions.verifyMarketCampaignsPage();
		flag = AutomationPage.verifyCreateCustomTemplatesWithAddCustomVariable();
//		if (flag) {
//			CommonFunctions.verifyMarketCampaignsPage();
//			flag = MarketCampaignsPage.verifyCustomTemplateInMarketCampign();
//		}
		assertEquals(true, flag);
	}

	@Test(description = "verify create custom template with predefined and custom variables", priority = 251)
	public void verifyCustomerPredefinedVariable() throws Exception, Exception, IOException {
		CommonFunctions.verifyOptinNumbersPage();
		flag = CustomersPage.verifyCustomerPredefinedVariable();
		assertEquals(true, flag);
	}

	@Test(description = "verify create custom template with out custom variable", priority = 252)
	public void verifyCustomTemplateExceptCustomVariable() throws Exception {
		CommonFunctions.verifyMarketCampaignsPage();
		flag = AutomationPage.verifyCreateCustomTemplatesExceptCustomVariable();
		assertEquals(true, flag);
	}

	@Test(description = "Verify create custom template,create user grp then download excel file,write the excel data and upload the file the create Market campagins", priority = 253)

	public void verifyCustomTemplateWithfileUpload() throws Exception {
//		CommonFunctions.verifyMarketCampaignsPage();
		flag = AutomationPage.verifyCreateCustomTemplatesWithAddCustomVariable();
		if (flag) {
			flag = CampaignsPage.verifyCreateMarketCampaignwithPredefinedAndCustomUploadFile();
		}
		assertEquals(true, flag);
	}

	@Test(description = "Verify that when user try to add new whats app widget then correct country code is showing or not ", priority = 280)
	public void verifyAgentAssistCountryCode() throws Exception {
		flag = CommonFunctions.verifyPersonalWidgetPage();
		if (flag) {
			flag = WidgetPage.verifyAgentAssistCountryCode();
		}
		assertEquals(true, flag);
	}

	/*
	 * @Test(description =
	 * "verify that when we go to campaign template section  all template ,created by me , default all this filter option is showing or not "
	 * , priority = 290) public void VerifyFilterOption() throws Exception { flag =
	 * CommonFunctions.verifyMarketCampaignsPage(); if (flag) { flag =
	 * CampaignsPage.verifyFilteroptionCampaign(); } assertEquals(true, flag); }
	 * 
	 * @Test(description =
	 * "verify that all the default template is showing or not when we select default filter "
	 * , priority = 291) public void VerifyDefaultFilteroptionCampaign() throws
	 * Exception { flag = CommonFunctions.verifyMarketCampaignsPage(); if (flag) {
	 * flag = CampaignsPage.verifyDefaultFilteroptionCampaign(); }
	 * assertEquals(true, flag); }
	 * 
	 * @Test(description =
	 * "verify that all the created by me  template is showing or not when we select created by me  filter "
	 * , priority = 292) public void VerifyCreatedFilteroptionCampaign() throws
	 * Exception { flag = CommonFunctions.verifyMarketCampaignsPage(); if (flag) {
	 * flag = CampaignsPage.verifyCreatedFilteroptionCampaign(); }
	 * assertEquals(true, flag); }
	 * 
	 * @Test(description = "verify Run campaign ", priority = 293) public void
	 * VerifyRunCampaign() throws Exception { flag =
	 * CommonFunctions.verifyMarketCampaignsPage(); if (flag) { flag =
	 * CampaignsPage.verifyRunCampaign(); } assertEquals(true, flag); }
	 * 
	 * @Test(description =
	 * "verify that when user is creating a template and if template contains custom variable and button url with suffix then sample variable section is showing or not "
	 * , priority = 294) public void VerifycreateCutomtemplateWithSuffixandDelete()
	 * throws Exception { flag = CommonFunctions.verifyMarketCampaignsPage(); if
	 * (flag) { flag = CampaignsPage.verifycreateCutomtemplateWithSuffixandDelete();
	 * } assertEquals(true, flag); }
	 * 
	 * 
	 * @Test(description =
	 * "Verify whether View campaigns button is clickable or not" +
	 * "Verify on clicking view campaigns button, it is redirecting to my campaigns list or not"
	 * , priority = 296) public void VerifyViewCampaignandRedirection() throws
	 * InterruptedException, FileNotFoundException, IOException {
	 * CommonFunctions.verifyMarketCampaignsPage(); flag =
	 * CampaignsPage.verifyViewCampaignandRedirection(); assertEquals(true, flag); }
	 * 
	 * @Test(description =
	 * "Verify on clicking download report we are able to get the respective campaign reports or not"
	 * +
	 * "Verify if there are all the fields with the details of the customer in the downloaded report or not"
	 * , priority = 297) public void VerifyDownloadReport() throws
	 * InterruptedException, FileNotFoundException, IOException {
	 * CommonFunctions.verifyMarketCampaignsPage(); flag =
	 * CampaignsPage.verifyDownloadReport(); assertEquals(true, flag); }
	 */

	@Test(description = "Verify a warning is shown on clicking submit while a campaigns status is still published"
			+ "Verify message is delivered to all contacts or not after running the campaign"
			+ "Verify that non opted in number if we add in sheet while running campaign then message is sending to that number or not "
			+ "verify When any non opted in number in added in sheet while creating campaign then afterruning campaign that number is there or not in customer table with campaign as source  ", priority = 295)
	public void VerifycreateMarketCampaignsstatus() throws Exception {
//		CampaignsPage.updateresultsinDB();
		flag = CommonFunctions.verifyMarketCampaignsPage();
		if (flag) {
//			flag = CampaignsPage.createMultiCampaignOptin();
			flag = WixFunctions.createMultiCampaignOptin();
			if (flag) {
				flag = CampaignsPage.verifyMultiCampaignOptin();
				if (flag) {
					flag = CampaignsPage.verifyOptinCampaignOptinCustomer();
				}
			}
		}
//		CampaignsPage.updateresultsinDB();
		assertEquals(true, flag);
	}

	@Test(description = "Verify when we click on submit button a pop-up should appear if the num of contacts in compaign is less than the user limit "
			+ "Verify on clicking learn more in the pop-up window, user should be redirected to SuperLemon page.", priority = 298)
	public void VerifyUserlimitPopup() throws InterruptedException, FileNotFoundException, IOException {
//		CommonFunctions.verifyNewStoreLoginPage("SharedTeamInbox3");
		CommonFunctions.verifyMarketCampaignsPage();
		flag = CampaignsPage.verifyUserlimitPopup();
		assertEquals(true, flag);
	}

	@Test(description = "when user checked the check box for Abandoned cart setting and save that setting then that check box should checked if user open the setting", priority = 299)

	public void VerifyAbcartCheckBoxChecked() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyTemplatesPage();
		flag = AutomationPage.verifyAbcartUserSettingsCheck();
		assertEquals(true, flag);
	}

	/*
	 * @Test(description =
	 * "when user checked the uncheck checkbox for Abandoned cart setting and save that setting then that check box should unchecked if user open the setting"
	 * , priority = 300) public void VerifyAbcartCheckBoxUnchecked() throws
	 * FileNotFoundException, InterruptedException, IOException {
	 * CommonFunctions.verifyTemplatesPage(); flag =
	 * AutomationPage.verifyAbcartUserSettingsUncheck(); assertEquals(true, flag); }
	 */

	@Test(description = "when user checked the check box for crosssell confirmation setting and save that setting then that check box should checked if user open the setting", priority = 301)
	public void VerifyCrossSellCheckBoxChecked() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyTemplatesPage();
		// modified - added new wix method
		flag = WixFunctions.verifyCrossSellUserSettingsCheck();
		assertEquals(true, flag);
	}

	@Test(description = "when user checked the uncheck checkbox for crosssell  confirmation setting and save that setting then that check box should unchecked if user open the setting", priority = 302)
	public void VerifyCrossSellCheckBoxUnchecked() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyTemplatesPage();
		flag = WixFunctions.verifyCrossSellUserSettingsUncheck();
		assertEquals(true, flag);
	}

	@Test(description = "Verify Error message for video campaign", priority = 303)
	public void VerifyErrorMessageForVideoCampaign() throws InterruptedException, FileNotFoundException, IOException {
//		CommonFunctions.verifyNewStoreLoginPage("NewSharedTeamInbox_Token");
		CommonFunctions.verifyMarketCampaignsPage();
		flag = CampaignsPage.verifyErrorMessageForVideoCampaign();
		assertEquals(true, flag);
	}

	@Test(description = "Verify Error message for image campaign", priority = 304)
	public void VerifyErrorMessageForImageCampaign() throws InterruptedException, FileNotFoundException, IOException {
//		CommonFunctions.verifyNewStoreLoginPage("NewSharedTeamInbox_Token");
		CommonFunctions.verifyMarketCampaignsPage();
		flag = CampaignsPage.verifyErrorMessageForImageCampaign();
		assertEquals(true, flag);
	}

	@Test(description = "Verify Campaign For Image", priority = 305)
	public void VerifyImageCampaign() throws InterruptedException, FileNotFoundException, IOException {
//		CommonFunctions.verifyNewStoreLoginPage("NewSharedTeamInbox_Token");
		CommonFunctions.verifyMarketCampaignsPage();
		flag = CampaignsPage.verifyImageCampaignWithFile();
		assertEquals(true, flag);
	}

	@Test(description = "Verify Campaign For Video", priority = 306)
	public void VerifyVideoCampaign() throws InterruptedException, FileNotFoundException, IOException {
//		CommonFunctions.verifyNewStoreLoginPage("NewSharedTeamInbox_Token");
		CommonFunctions.verifyMarketCampaignsPage();
		flag = CampaignsPage.verifyVideoCampaignWithFile();
		assertEquals(true, flag);
	}

	@Test(description = "Verify Campaign For Docs", priority = 307)
	public void VerifyDocsCampaign() throws InterruptedException, FileNotFoundException, IOException {
		// CommonFunctions.verifyNewStoreLoginPage("NewSharedTeamInbox_Token");
		CommonFunctions.verifyMarketCampaignsPage();
		flag = CampaignsPage.verifyDocsCampaignWithFile();
		assertEquals(true, flag);
	}

	@Test(description = "Verify Error Message For Docs", priority = 308)
	public void VerifyErrorDocsCampaign() throws InterruptedException, FileNotFoundException, IOException {
//		CommonFunctions.verifyNewStoreLoginPage("NewSharedTeamInbox_Token");
		CommonFunctions.verifyMarketCampaignsPage();
		flag = CampaignsPage.verifyErrorMessageForDocsCampaign();
		assertEquals(true, flag);
	}

	@Test(description = "Verify Country Code For Spinthewheel", priority = 309)
	public void VerifyCountryCodeOfSpinTheWheel() throws Exception {
//		CommonFunctions.verifyNewStoreLoginPage("NewSharedTeamInbox_Token");
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.countryCodeForSpinWheel();
		assertEquals(true, flag);
	}

	@Test(description = "verify that if optin widget is enable it is showing on store or not ", priority = 310)
	public void VerifyOptinviaChatwidgetEnable() throws Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyOptinviaChatwidgetEnableonStore();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that if optin widget is disable then it is showing or not on store", priority = 311)
	public void VerifyOptinviaChatwidgetDisable() throws Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyOptinviaChatwidgetDisableonStore();
		assertEquals(true, flag);
	}

	@Test(description = "verify that user is able to select different design for optin widget or not   "
			+ "Verify on clicking Edit configuration, the settings for opt-in chat widget is showing or not", priority = 312)
	public void VerifySelectdesignforOptinviachatwidget() throws Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifySelectdesignforOptinviachatwidget();
		assertEquals(true, flag);
	}

	@Test(description = "verify that what ever color user is updating for background  and text  same color is updating or not for store ", priority = 313)
	public void VerifyColourofOptinviachatwidget() throws Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyColourofOptinviachatwidget();
		assertEquals(true, flag);
	}

	@Test(description = "verify that what ever color user is updating for background  and text  same color is updating or not for store ", priority = 314)
	public void VerifyHeaderColourofOptinviachatwidget() throws Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyHeaderColourofOptinviachatwidget();
		assertEquals(true, flag);
	}

	@Test(description = "verify that user is able to update heading and help text or not "
			+ "Verify that if help text and heading is updated then same text is showing or not on store ", priority = 315)
	public void VerifyTextandHelptextinOptinviaChatwidget() throws Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyTextandHelptextinOptinviaChatwidget();
		assertEquals(true, flag);
	}

	@Test(description = "Verify on clicking Widgets on the side navigation bar, Chat widgets,Share widgets and Optin widgets are shown"
			+ "Verify on clicking Optin widgets all the options are shown", priority = 316)
	public void VerifyWidgetsection() throws Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyWidgetsection();
		assertEquals(true, flag);
	}

	@Test(description = "Verify if we change any settings, it can be saved."
			+ "Verify if we change any settings, it can be discarded.", priority = 317)
	public void VerifySettingsChange() throws Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifySettingsChange();
		assertEquals(true, flag);
	}

	@Test(description = "Verify on selecting Cart page, the widget is shown in cart page of store or not", priority = 318)
	public void VerifyCartpageCheck() throws Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		// modified - added wix store function
		flag = WixStore.verifyCartpageCheckbox();
		assertEquals(true, flag);
	}

	@Test(description = "Verify on selecting Checkout, the widget is shown when we click Check out button in store or not", priority = 319)
	public void VerifyCheckoutCheckbox() throws Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		// modified - added wix store function
		flag = WixStore.verifyCheckoutCheckbox();
		assertEquals(true, flag);
	}

	@Test(description = "Verify on deslecting all the options, the widget should not be shown in store", priority = 320)
	public void VerifyDeselectCheckboxes() throws Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		// modified - added new wix functon
		flag = WixFunctions.verifyDeselectCheckboxes();

		assertEquals(true, flag);
	}

	@Test(description = "Verify on clicking Back, it redirects to the Option widgets or not", priority = 321)
	public void VerifyClickBack() throws Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyClickBack();
		assertEquals(true, flag);
	}

	@Test(description = "Verify on clicking Preview in Thank you page widget option, the preview of the Thank you page widget is opened or not", priority = 322)
	public void VerifyThankyouPreview() throws Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyThankyouPreview();
		assertEquals(true, flag);
	}

	@Test(description = "Verify on clicking View on store in Spin the wheel, it redirects to the specific store or not", priority = 323)
	public void VerifyViewonStoreSpinwheel() throws Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyViewonStoreSpinwheel();
		assertEquals(true, flag);
	}

	@Test(description = "Verify on clicking Preview, the preview of widget opens in new tab or not", priority = 324)
	public void VerifyOptinviaChatPreview() throws Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyOptinviaChatPreview();
		assertEquals(true, flag);
	}

	@Test(description = "Verify on enabling or disabling the chat widget, it reflects on store or not", priority = 325)
	public void VerifyOptinvia_ChatwidgetDisableonStore() throws Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyOptinvia_ChatwidgetEnableonStore();
		log.info("Verified optin via chat widget enable on store");
		if (flag) {
			flag = WidgetPage.verifyOptinvia_ChatwidgetDisableonStore();
		}
		assertEquals(true, flag);
	}

	@Test(description = "Verify that once user select tag in ab cart section user is able  see all the created tag or not  ", priority = 326)
	public void VerifyTagsinAB_CART() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyManualMessagingPage();
//		flag = WixFunctions.verifyTagsinAB_CART();
		flag = MessagingPage.verifyTagsinAB_CART();
		assertEquals(true, flag);
	}

	@Test(description = "verify that in ab cart section user is able to apply tag or not for ab cart order"
			+ "verify that user is able to filter ab cart order using tag or not ", priority = 327)
	public void VerifyApplyTagsinAB_CART() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyManualMessagingPage();
		flag = MessagingPage.verifyApplyTagsinAB_CART();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that when user click on send message all the cart template is showing or not", priority = 328)
	public void VerifySendMessageinAB_CART() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyManualMessagingPage();
		// modified - wix store
		flag = WixFunctions.verifySendmessageinAB_CART();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that once user select tag in order crm section user is able  see all the created tag or not  ", priority = 329)
	public void VerifyTagsinOrderCRM() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyManualMessagingPage();
//	flag = WixFunctions.verifyTagsinOrderCRM();
		flag = MessagingPage.verifyTagsinOrderCRM();
		assertEquals(true, flag);
	}

	@Test(description = "verify that user is able to filter order in crm order using tag or not ", priority = 330)
	public void VerifyApplyTagsinOrderCRM() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyManualMessagingPage();
		// modified - added new wix method
//		flag = WixFunctions.verifyApplyTagsinOrderCRM();
		flag = MessagingPage.verifyApplyTagsinOrderCRM();
		assertEquals(true, flag);
	}

	@Test(description = "Verify that when user clicks on send message all the crm order template is showing or not", priority = 331)
	public void VerifySendMessageinOrderCRM() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyManualMessagingPage();
		// modified - added new wix method
		flag = WixFunctions.verifySendmessageinOrderCRM();
		assertEquals(true, flag);
	}

	@Test(description = "verify that in message template when ab cart  option is selected then ab cart manual message template is showing or not "
			+ "Verify that when user is creating manual message ab cart template then user is able to create or not ", priority = 332)
	public void VerifyABcarttemplate() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyManualMessagingPage();
		// modified - added new wix method
		flag = WixFunctions.verifyABcarttemplate();
		assertEquals(true, flag);
	}

	@Test(description = "verify that in message template when order crm  option is selected then order crm manual message and extension  template is showing or not "
			+ "Verify that when user is creating  manual message order template then user is able to create or not  ", priority = 333)
	public void VerifyOrderCRMtemplate() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyManualMessagingPage();
		// modified - added new wix method
		flag = WixFunctions.verifyOrderCRMtemplate();
		assertEquals(true, flag);
	}

//	@Test(description = "verify that in message template when quick reply option is selected then quick reply extension  template is showing or not "
//			+ "verify that in message template when all  option is selected then all the template is showing or not "
//			+ "Verify that when user selects quick reply category then quick reply and order template category is showing or not  ", priority = 334)
	public void VerifyQuickReplytemplate() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyManualMessagingPage();
		// modified - added new wix method
		flag = MessagingPage.verifyQuickReplytemplate();
		assertEquals(true, flag);
	}

	@Test(description = "Verify on clicking Preview, the preview of widget opens in new tab or not"
			+ "Verify on clicking Edit configuration, the configuration options is showing or not", groups = {
					"group2" }, priority = 335)
	public void VerifyOptinpreview() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyPreview();
		if (flag) {
			WidgetPage.verifyOptinpopupLanguage();
		}
		assertEquals(true, flag);
	}

	@Test(description = "Verify on selecting Add to cart, the widget is shown when we click Add to cart button in store or not", groups = {
			"group2" }, priority = 336)
	public void VerifyOptinPopupAddCart() throws Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		// modified - added existing wix function
		flag = WixStore.verifyCartpageCheckbox();
		assertEquals(true, flag);
	}

	@Test(description = "Verify config checkout widget", groups = { "group2" }, priority = 337)
	public void VerifyCheckoutConfig() throws Exception {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyConfigCheckoutWidget();
		assertEquals(true, flag);
	}

	@Test(description = "verify that in messaging section ab cart ,order crm ,message templates,settings this sub section is showing or not "
			+ "Verify that in messaging section extension promotional tile is showing or not", priority = 338)
	public void VerifyMessageSubSection() throws Exception {
		CommonFunctions.verifyMessagingPage();
		flag = MessagingPage.verifyMessageSubSection();
		assertEquals(flag, true);
	}

//	@Test(description = "Verify that once user click on install button it is redirecting to play store or not "
//			+ "Verify that once user click on see tutorial then tutorial is showing or not "
//			+ "Verify that once user close the extension promotional tile then it is closing or not ", priority = 339)
	public void VerifyMessageInstallbuttontutorial() throws Exception {
		CommonFunctions.verifyMessagingPage();
		flag = MessagingPage.verifyMessageInstallbuttontutorial();
		if (flag) {
			flag = MessagingPage.verifyClosePromotionaltile();
		}
		assertEquals(flag, true);
	}

	@Test(description = "Verify if there are 2 tutorial videos available for My campaigns subsection", priority = 340)
	public void VerifyMyCampaignsTutorial() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyMarketCampaignsPage();
		flag = CampaignsPage.verifyMyCampaignsTutorial();
		assertEquals(true, flag);

	}

	@Test(description = "Verify if there are 2 tutorial videos available for Campaign Templates subsection", priority = 341)
	public void VerifyCampaignTemplatesTutorial() throws FileNotFoundException, InterruptedException, IOException {
		// commented below and added new templates page method.
		// CommonFunctions.verifyMarketCampaignsPage();
		CommonFunctions.verifyNewTemplatesPage();
		flag = AutomationPage.verifyCampaignTemplatesTutorial();
		assertEquals(true, flag);

	}

	@Test(description = "Verify if there is a tutorial video available for Automation section", priority = 342)
	public void VerifyAutomationTutorial() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyTemplatesPage();
		flag = AutomationPage.verifyAutomationTutorial();
		assertEquals(true, flag);
	}

	@Test(description = "Verify if there is a tutorial video available for My Whatsapp section", priority = 343)
	public void VerifyMyWhatsappTutorial() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyMyWhatsappPage();
		flag = MyWhatsAppPage.verifyMyWhatsappTutorial();
		assertEquals(true, flag);
	}

	@Test(description = "Verify if there is a tutorial video available for Optin widgets  subsection", priority = 344)
	public void VerifyOptinWidgetsTutorial() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyPersonalWidgetPage();
		flag = WidgetPage.verifyOptinWidgetTutorial();
		assertEquals(true, flag);
	}

	@Test(description = "Verify if there is a tutorial video available for All Customers subsection", priority = 345)
	public void VerifyAllCustomersTutorial() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyOptinNumbersPage();
		flag = CustomersPage.verifyAllCustomersTutorial();
		assertEquals(true, flag);
	}

	@Test(description = "Verify if there is a tutorial video available for Customer Groups subsection", priority = 346)
	public void VerifyCustomerGroupsTutorial() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyOptinNumbersPage();
		flag = CustomersPage.verifyCustomerGroupsTutorial();
		assertEquals(true, flag);
	}

	// @Test(description = "Verify if there are 2 tutorial videos available for
	// Shared Team Inbox section", priority = 347)
	public void VerifySharedTeamInboxTutorial() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifySharedTeamInboxPage();
		flag = SharedTeamInboxPage.verifySharedTeamInboxTutorial();
		assertEquals(true, flag);
	}

	@Test(description = "Verify if screenshots are added to the guide in the campaigns template section", priority = 348)
	public void VerifyCampaignTemplatesGuide() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyMarketCampaignsPage();
		flag = AutomationPage.verifyCampaignTemplatesGuide();
		assertEquals(true, flag);

	}

	@Test(description = "Verify in media Type, it should display all the media templates - Text, Image, Video and Document", priority = 349)
	public void VerifyMediaTypeinCampaignTemplates() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyMarketCampaignsPage();
		flag = AutomationPage.verifyMediaType();
		assertEquals(true, flag);
	}

	@Test(description = "Verify on selecting Image as type, Image file should be uploaded in sample media", priority = 350)
	public void VerifyImageMediaType() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyMarketCampaignsPage();
		flag = AutomationPage.verifyImageMediaType();
		assertEquals(true, flag);
	}

	@Test(description = "Verify on selecting video as type, video file should be uploaded in sample media", priority = 351)
	public void VerifyVideoMediaType() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyMarketCampaignsPage();
		flag = AutomationPage.verifyVideoMediaType();
		assertEquals(true, flag);
	}

	@Test(description = "Verify on selecting Document as type, document file should be uploaded in sample media", priority = 352)
	public void VerifyDocumentMediaType() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyMarketCampaignsPage();
		flag = AutomationPage.verifyDocumentMediaType();
		assertEquals(true, flag);
	}

	@Test(description = "Verify a warning is shown if the uploaded media file for the document is not a .pdf file", priority = 353)
	public void VerifyDocumentWarning() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyMarketCampaignsPage();
		flag = AutomationPage.verifyDocumentWarning();
		assertEquals(true, flag);
	}

	@Test(description = "Verify a warning is shown if the uploaded media file for the image is not a .png and .jpeg/jpg file", priority = 354)
	public void VerifyImageWarning() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyMarketCampaignsPage();
		flag = AutomationPage.verifyImageWarning();
		assertEquals(true, flag);
	}

	@Test(description = "Verify a warning is shown if the uploaded media file for the video type is not a .mp4 file", priority = 355)
	public void VerifyVideoWarning() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyMarketCampaignsPage();
		flag = AutomationPage.verifyVideoWarning();
		assertEquals(true, flag);
	}

	@Test(description = "Verify a warning is shown if the uploaded image file exceeds the limit of 2MB", priority = 356)
	public void VerifyImagefileSizeLimit() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyMarketCampaignsPage();
		flag = AutomationPage.verifyImageSizeLimit();
		assertEquals(true, flag);
	}

	@Test(description = "Verify a warning is shown if the uploaded video file exceeds the limit of 16MB", priority = 357)
	public void VerifyVideofileSizeLimit() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyMarketCampaignsPage();
		flag = AutomationPage.verifyVideoSizeLimit();
		assertEquals(true, flag);
	}

	@Test(description = "Verify a warning is shown if the uploaded document exceeds the limit of 2MB", priority = 358)
	public void VerifyDocumentfileSizeLimit() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyMarketCampaignsPage();
		flag = AutomationPage.verifyDocumentSizeLimit();
		assertEquals(true, flag);
	}

	@Test(description = "Verify supported Media types are shown below the Choose file button", priority = 359)
	public void VerifySupportMediaTypes() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyMarketCampaignsPage();
		flag = AutomationPage.verifySupportMediaTypes();
		// flag = TemplatesPage.verifyAllInOne();
		assertEquals(true, flag);
	}

	@Test(description = "Verify thank you page optin", groups = { "group2" }, priority = 360)
	public void VerifyThankyouPageOptin() throws Exception {
		// CommonFunctions.verifyNewStoreLoginPage("NewSharedTeamInbox_Token");
		// flag = CustomersPage.verifyThankyouPageOptin();
		// modified - added new method on wix
		flag = WixFunctions.verifyThankyouPageOptin();
		assertEquals(true, flag);
	}

	@Test(description = "Verify optin pop up country code", groups = { "group2" }, priority = 361)
	public void VerifyOptinPopUp() throws Exception {
		// CommonFunctions.verifyNewStoreLoginPage("NewSharedTeamInbox_Token");
		CommonFunctions.verifyPersonalWidgetPage();
		// modified - changed below method to existing wix store method
		flag = WixStore.verifyCartpageCheckbox();
		assertEquals(true, flag);
	}

	@Test(description = "Verify whats app verification status ", groups = { "group2" }, priority = 362)
	public void VerifyWhatsAppDetails() throws Exception {
		// CommonFunctions.verifyNewStoreLoginPage("NewSharedTeamInbox_Token");
		CommonFunctions.VerifyWhatsAPPAPIPage();
		flag = MyWhatsAppPage.verificationDetails();
		assertEquals(true, flag);
	}

	@Test(description = "verify that ab cart order is who is recoverd those order is also included in number of order or not", priority = 367)
	public void VerifyAbcartRecoveredisinNumofOrder() throws Exception {
		flag = CommonFunctions.verifyPersonalWidgetPage();
		if (flag) {
			flag = WixStore.verifyAbcartRecovered();
		}
		assertEquals(true, flag);
	}

	@Test(description = "verify that ab cart order is who is not recoverd those order is included or not  in number of order section"
			+ "Verify that ab cart value whose not recovered is not included in total order value ", priority = 368)
	public void VerifyAbcartNotRecoveredisNotinNumofOrderAndTotalOrder() throws Exception {
		flag = CommonFunctions.verifyPersonalWidgetPage();
		if (flag) {
			flag = CustomersPage.verifyAbcartNotRecovered();
		}
		assertEquals(true, flag);
	}

	@Test(description = "Verify that in ab cart value only ab cart order value showing or not ", priority = 369)
	public void VerifyAbcartValue() throws Exception {
		flag = CommonFunctions.verifyPersonalWidgetPage();
		if (flag) {
			flag = CustomersPage.verifyAbcartValue();
		}
		assertEquals(true, flag);
	}

	@Test(description = "Verify that the user can add multiple conditions by clicking on “add-condition” button.", priority = 370)
	public void VerifyCustomerGroupCondition() throws Exception {
		flag = CommonFunctions.verifyOptinNumbersPage();
		if (flag) {
			flag = CustomersPage.VerifyCustomerGroupCondition();
		}
		assertEquals(true, flag);
	}

	@Test(description = "Verify that following details are shown on the user groups ticket: Group Name, Group conditions, Creation date, Download icon, Edit Button,Delete Button, Send Message", priority = 371)
	public void VerifyCustomerGroupDetails() throws Exception {
		flag = CommonFunctions.verifyOptinNumbersPage();
		if (flag) {
			flag = CustomersPage.VerifyCustomerGroupDetails();
		}
		assertEquals(true, flag);
	}

	@Test(description = "Verify if there are 3 options (Tags, Quick response and set\r\n"
			+ "working hours) available in this section")
	public void verifyOptionsInConfigurationPage() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyWhatsappInboxPage();
		flag = WhatsappInboxPage.verifyWhatsappConfigurationPageOptions();
		assertEquals(true, flag);

	}

	@Test(description = "Verify once we create new tags, it is listed in Tags")
	public void verifyCreatedTagsAreListed() throws FileNotFoundException, InterruptedException, IOException {
		CommonFunctions.verifyWhatsappInboxPage();
		flag = WhatsappInboxPage.verifyCreatedTagsAreListed();
		assertEquals(true, flag);
	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------------

	@AfterMethod(alwaysRun = true)
	public void logout() throws FileNotFoundException, IOException, Exception {
		CommonFunctions.verifyLogout();
		CommonFunctions.closeAllChildWindows();
		CommonFunctions.clearLocalStorage();
		endTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
		log.info("Test case run completed at" + " " + endTime);
//			DriverBase.getTestCaseExecutionTime(startTime, endTime);
//			webDB.updateTestCaseExecutedCount();
		CommonFunctions.clearCookies();
	}

	@AfterSuite(alwaysRun = true)
	public void sendReport(ITestContext ctx) throws Exception {
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
		webDB.executionTimeEnd();
		webDB.tearDown();
//			webDB.browserOpen();
//		Dbresults.executedb();
//			suiteName = ctx.getCurrentXmlTest().getSuite().getName();
//			mailer.execute(suiteName);
//			webDB.tearDown();
	}

}