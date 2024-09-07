package locators;

public class AnalyticsLocators {
	/* css */public static final String CLICK_COUNT_HEADER = ".Polaris-TextStyle--variationSubdued";
	/* xpath */public static final String CHAT_SHARE_CLICK_FILTER = "//h2[.='Widget Analytics']/following::span[1]";
	/* xpath */public static final String FROM_DATE_FIELD = "//label[.='Starting date']/following::input[1]";
	/* xpath */public static final String STARTING_DATE_VALIDATION_MESSAGE = "(//span[contains(@class,'Polaris-TextStyle--variationNegative')])";
	/* xpath */public static final String CHAT_CLICK_COUNT = "(//div[contains(@class,'analytics_metric_container')])[1]";
	/* xpath */public static final String SHARE_CLICK_COUNT = "(//div[contains(@class,'analytics_metric_container')])[2]";
	/* xpath */public static final String WIDGET_ANALYTICS_SECTION = "//h2[.='Widget Analytics']";
	/* xpath */public static final String SPINWHEEL_ANALYTICS_SECTION = "//h2[.='Spin Wheel Analytics']";
	/* xpath */public static final String AUTOMATED_MESSAGE_REPORT_SECTION = "//h2[.='Automated Message Report']";
	/* xpath */public static final String OTHER_MESSAGES_SECTION = "//div[normalize-space()='Other messages']";
	/* xpath */public static final String OPT_INS_SECTION = "//h2[.='Opt Ins']";
	/* xpath */public static final String ABANDONEDCARDTITLE = "//div[normalize-space()='Abandoned Cart']";
	/* xpath */public static final String ABANDONEDCARD_TOTALREAD = "(//div[contains(text(),'Total Read')])[1]";
	/* xpath */public static final String ABANDONEDCARD_TOTALSENT = "(//div[contains(text(),'Total Sent')])[1]";
	/* xpath */public static final String ABANDONEDCARD_TOTALRECEIVED = "(//div[contains(text(),'Total Received')])[1]";
	/* xpath */public static final String ABANDONEDCARD_NO_OF_ABANDONEDCART = "//p[.='No. of abandoned Carts Converted']";
	/* xpath */public static final String ABANDONEDCARD_REVENUEGENERATED = "//p[.='Revenue Generated from abandoned Carts']";
	/* xpath */public static final String OTHER_MESSAGES_TOTALREAD = "(//div[contains(text(),'Total Read')])[2]";
	/* xpath */public static final String OTHER_MESSAGES_TOTALSENT = "(//div[contains(text(),'Total Sent')])[2]";
	/* xpath */public static final String OTHER_MESSAGES_TOTALRECEIVED = "(//div[contains(text(),'Total Received')])[2]";
	/* xpath */public static final String ORDERCONFIRMATION_GRAPH = "//h2[.='Order Confirmation']/../parent::div";
	/* xpath */public static final String ORDERSHIPMENT_GRAPH = "//h2[.='Order Shipment']/../parent::div";
	/* xpath */public static final String CASHONDELIVERY_GRAPH = "//h2[.='Cash on Delivery']/../parent::div";
	/* xpath */public static final String PAYMENTSUCCESSFUL_GRAPH = "//h2[.='Payment Successful']/../parent::div";
	/* xpath */public static final String ORDERCANCELLATION_GRAPH = "//h2[.='Order Cancellation']/../parent::div";
	/* xpath */public static final String CROSSSELL_GRAPH = "//h2[.='Cross Sell']/../parent::div";
	/* xpath */public static final String CUSTOMERWINBACK_GRAPH = "//h2[.='Customer Winback']/../parent::div";
	/* xpath */public static final String FEEDBACKGRAPH = "//h2[.='Feedback']/../parent::div";
	/* xpath */public static final String WIDGETANALYTICS_CUSTOMIZE = "//h2[.='Widget Analytics']/following::div[1]/button";
	/* xpath */public static final String LASTWEEKRADIOBTN = "//input[@id='Last week']";
	/* css */public static final String CUSTOMIZERADIOBTN = "#Customize";
	/* xpath */public static final String STARTINGDATE = "//label[.='Starting date']/following::input[1]";
	/* xpath */public static final String SPINWHEEL_CUSTOMIZE = "//h2[.='Spin Wheel Analytics']/following::div[1]/button";
	/* xpath */public static final String AUTOMATED_MESSAGE_REPORT_CUSTOMIZE = "//h2[.='Automated Message Report']/following::div[1]/button";
	/* xpath */public static final String OPT_INS_CUSTOMIZE = "//h2[.='Opt Ins']/following::div[1]/button";
	/* css */public static final String QUESTIONMARKICON = ".icon";
	/* xpath */public static final String STARTGUIDEBTN = "//button[contains(.,'Start Guide')]";
	/* xpath */public static final String NEXTBTN = "//span[contains(text(),'Next')]";
	/* xpath */public static final String GUIDECOMPLETEDBTN = "//span[contains(text(),'Guide Completed')]";
	/* xpath */public static final String CURRENTLYDISABLED = "//p[contains(text(),'Currently Disabled')]";
	/* xpath */public static final String CURRENTLYDISABLEDCLICKHERE = "//p[contains(text(),'Currently Disabled')]/a";
	/* xpath */public static final String OTHERMESSAGECURRENTLYDISABLED = "//p[contains(text(),'Currently disabled')]";
	/* xpath */public static final String OTHERMESSAGECURRENTLYDISABLEDCLICKHERE = "//p[contains(text(),'Currently disabled')]/a";
	/* xpath */public static final String CASHONDELIVERY_TITLE = "//h2[.='Cash on Delivery']";
	/* xpath */public static final String ANALYTICS_TAB = "//span[.='Analytics & Logs']";

	public static final String STARTINGDATEVALUE = "01012026";
	public static final String STARTINGDATEERRORVALUE = "Data should be fetchable upto last 6 months";

}
