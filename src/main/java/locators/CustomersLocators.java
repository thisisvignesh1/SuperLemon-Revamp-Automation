package locators;

import commonFunctions.CustomersPage;

public class CustomersLocators {
	/* xpath */public static final String OPTIN_NUMBERS_TAB = "//span[.='Customers']";
	/* xpath */public static final String STORE_PASS_ENTER = "//button[@type='submit']";
	/* xpath */ public static final String SAVE_SUCCESS_MSG = "//div[contains(@class,'Polaris-Frame-Toast')][contains(text(),'Data Saved')]";
	/* xpath */public static final String PHONE_COLUMBER_COLUMN = "//tbody//tr//th[1]";
	/* xpath */public static final String SOURCE_COLUMN = "//tbody//tr//td[7]";
	/* xpath */public static final String EMAIL_EMPTY_VALIDATION_MESSAGE_POPUP = "//div[@class='Polaris-Frame-Toast Polaris-Frame-Toast--error'][contains(text(),'Please provide an email address.')]";
	/* xpath */public static final String EMAIL_INVALID_VALIDATION_MESSAGE_POPUP = "//div[@class='Polaris-Frame-Toast Polaris-Frame-Toast--error'][contains(text(),'Please provide a valid email address.')]";
	/* xpath */public static final String EXPORT_LIST_SUCCESS_MESSAGE = "//div[@class='Polaris-Frame-Toast']";
	/* xpath */public static final String EXPORT_OPTIN_LIST = "(//span[@class='Polaris-Button__Text'])[contains(text(),'Download')]";
	/* xpath */public static final String SUBMIT_EXPORT_OPTIN_LIST = "//span[@class='Polaris-Button__Text'][contains(text(),'Submit')]";
	/* xpath */public static final String VALIDATION_MESSAGE_POPUP = "//div[@class='Polaris-Frame-Toast Polaris-Frame-Toast--error']";
	/* id */public static final String EMAIL_TEXTBOX = "PolarisTextField1";
	/* xpath */public static final String DATE_STARTING = "PolarisTextField2";
	/* xpath */public static final String DATE_ENDING = "PolarisTextField3";
	/* xpath */public static final String DATE_RANGE_VALIDATION_MESSAGE = ".Polaris-TextStyle--variationNegative";
	/* xpath */public static final String FIRST_DATE_CALENDAR = "(//button[contains(@class,'Polaris-DatePicker__Day')])[1]";
	/* xpath */public static final String CALENDAR_BACK_BUTTON = "(//*[contains(@class,'Polaris-Button Polaris-Button--plain Polaris-Button--iconOnly')])[3]";
	/* xpath */public static final String FIRSTSENTMESSAGEBTN = "(//th[.='Whatsapp'])/following::button[1]";
	/* xpath */public static final String CUSTOMER_TABLE_HEADER = "//table/thead/tr/th";
	/* xpath */public static final String NAME_COLUMN = "//tbody//tr//td[1]";
	/* xpath */public static final String OPTEDIN_COLUMN = "//tbody//tr//td[5]";
	/* xpath */public static final String TOTAL_ORDER_VALUE_COLUMN = "//tbody//tr//td[3]";
	/* xpath */public static final String ORDER_DATE_COLUMN = "//tbody//tr//td[6]";
	/* xpath */public static final String NO_OF_ORDER_COLUMN = "//tbody//tr//td[2]";
	/* xpath */public static final String GRPNAMEINPUT = "//input[@name='name']";
	/* xpath */public static final String PARAMETERDROPDOWN = "//div[@class='conditionContainer']/../descendant::select[1]";
	/* xpath */public static final String CONDITIONDROPDOWN = "//div[@class='conditionContainer']/../descendant::select[2]";
	/* xpath */public static final String VALUEDROPDOWN = "//div[@class='conditionContainer']/../descendant::select[3]";
	/* xpath */public static final String VALUEINPUT = "//div[@class='conditionContainer']/../descendant::input[1]";
	/* xpath */public static final String ADDCONDITIONBTN = "//div[@class='d-flex-center']//div[1]";
	/* CSS */ public static final String ANDBUTTON = ".andContainer";
	/* CSS */ public static final String ORBUTTON = ".pl-2";
	/* CSS */ public static final String DELETECONDITIONROW = ".deleteIcon";
	/* xpath */public static final String SAVEBTN = "//span[contains(text(),'Save')]";
	/* xpath */public static final String ERRORTOAST = "//div[@class='Polaris-Frame-Toast Polaris-Frame-Toast--error']";
	/* xpath */public static final String GRPEDITBUTTON = "//div[contains(text(),'" + CustomersPage.grpname
			+ "')]/child::div[1]/div[2]";
	/* xpath */public static final String GRPDELETEBUTTON = "//div[contains(text(),'" + CustomersPage.grpname
			+ "')]/child::div[1]/div[3]";
	/* xpath */public static final String GRPNODE = "//div[contains(text(),'" + CustomersPage.grpname
			+ "')]/../child::div[1]";
	/* xpath */public static final String CONFIRMDELETE = "//span[text()='Delete']";
	/* xpath */public static final String NOTIF = "//div[@class='Polaris-Frame-Toast']";
	/* xpath */public static final String VALUEFIELD = "//input[@id='conditionValue']";
	/* xpath */public static final String GRPDOWNLOADBUTTON = "(//div[@class='Polaris-Card'])[1]/../descendant::div[4]/span";
	/* XPATH */public static final String CLOSEGRP = "//button[@class='Polaris-Modal-CloseButton']";

	/* xpath */public static final String USER_GROUP = "(//span[.='Customer Groups'])[last()]";
	/* xpath */public static final String USERGRPTAB = "(//span[.='Customer Groups'])[last()]";
	/* xpath */public static final String CREATEUSERGRPBTN = "(//span[.='Create Customer Groups'])[last()]";

	/* xpath */public static final String CUSTOMERTUTORIAL_TITLE = "//h2[text()='How to create customer group?']";

	/* xpath */public static final String WIDGET = "//span[normalize-space()='Widgets']";
	/* xpath */public static final String STORELINK = "//a[normalize-space()='Click here to view it on your store']";
	/* xpath */public static final String PHONE_NUMBER = "(//h2[contains(text(),'Contact information')])/following::input[1]";
	/* xpath */public static final String THANK_YOU_OPTIN = "(//button[normalize-space()='Opt-in'])[1]";
	/* xpath */public static final String CART = "(//*[name()='svg'][@class='icon icon-cart-empty'])[1]";
	///* xpath */public static final String COUNTRY_CODE = "(//input[@id='wa-optin-country-code'])[1]";
	/* xpath */public static final String CLOSE_BUTTON = "//img[@class='wa-optin-widget-close-img']";
	///* xpath */public static final String CONFIGURE_WIDGET = "(//div[@class='stats'])[4]//child::button";
	/* xpath */public static final String OPTIN_WIDGET = "//button[@id='Optin Widgets']//span[@class='Polaris-Tabs__Title'][normalize-space()='Optin Widgets']";
	///* xpath */public static final String DISABLE_TEXT = "//div[contains(text(),'Spin The Wheel is currently')]//child::span";
	///* xpath */public static final String STORE_LINK_SPINWHEEL = "//a[normalize-space()='Click to see it live on your store.']";
	///* xpath */public static final String ENABLE_BUTTON = "//span[contains(text(),'Enable')]";
	///* xpath */public static final String SPIN_WHEEL_STORE = "//div[@id='spinwheel-btn-root']//img";
	///* xpath */public static final String SPIN_WHEEL = "(//button[normalize-space()='Spin the Wheel'])[1]";
	///* xpath */public static final String SPIN_WHEEL_TEXT = "//p[@class='game-status-subtxt']";

	/* xpath */public static final String NUM_OF_ORDER = "(//tbody//th[contains(text(),'17854227434')])/following::td[2]";
	/* xpath */public static final String PHONE_NUMBER_COLUMN = "(//tbody//th[1])[1]";
	/* xpath */public static final String SECOND_PARAMETERDROPDOWN = "(//div[@class='conditionContainer']/../descendant::select[1])[2]";
	/* xpath */public static final String SECOND_CONDITIONDROPDOWN = "(//div[@class='conditionContainer']/../descendant::select[2])[2]";
	/* xpath */public static final String SECOND_VALUEINPUT = "(//div[@class='conditionContainer']/../descendant::input[1])[2]";
	/* xpath */public static final String CONDITION = "//div[contains(text(),'" + CustomersPage.grpname
			+ "')]/child::div[2]/div[1]";
	/* xpath */public static final String CREATED_ON = "//div[contains(text(),'" + CustomersPage.grpname
			+ "')]/child::div[3]/div[1]";
	/* xpath */public static final String SEND_MESSAGE = "//div[contains(text(),'" + CustomersPage.grpname
			+ "')]/child::div[3]/div[2]";
	/* xpath */public static final String SELECT_VALUE = "//div[@class='conditionContainer']/div[3]/div/button";
//	/* xpath */public static final String DATE = "//div[@class='Polaris-DatePicker__MonthContainer']//tbody/tr//td[5]";
	/* xpath */public static final String ABCART_VALUE = "//tbody//tr//td[4]";
	/* xpath */public static final String NAME = "(//div[text()='Conditions:']/../..)[1]";
	/* xpath */public static final String FIRST_NUM = "(//tbody//tr//th[1])[1]";
	
	/* xpath */public static final String CONFIGURE_WIDGET = "(//div[@class='stats'])[2]//child::button";
	/* xpath */public static final String DATE = "//div[@class='Polaris-DatePicker__MonthContainer']//tbody/tr//td[6]";
	
	public static final String GROUPNAME = "GROUP 1";
	public static final String EDITPARAMETER = "total_order_value";
	public static final String EDITCONDITION = "IS GREATER THAN";
	public static final String EDITVALUE = "10";
	public static final String OPTEDIN = "Opted-in";
	public static final String ORDERVALUE = "1180";
	public static final String ORDERNUMBER = "1";
//	public static final String ERRORVALUE1 = "Please select the parameter before selecting condition";
	public static final String ERRORVALUE2 = "Please select the condition before selecting value";
//	public static final String ERRORVALUE3 = "Please select the parameter before selecting value";
	public static final String GRPNAMEVALUE = "CustomerGrp";
	public static final String PARAMETERVALUE = "phone";
	public static final String CONDITIONVALUE = "IS";
	public static final String VALUE = "917854241095";
	public static final String[] COLUMNVALUES = { "Phone Number", "Customer Name", "Number of Orders",
			"Total Order Value", "Abandoned Cart Value", "Optin", "Optin Date", "Optin Source" };
	public static final String EDITSUCCESSNOTIF = "Customer Group updated successfully";
	public static final String DELETESUCCESSNOTIF = "Customer Group deleted successfully";
	public static final String TUTORIAL_TITLE = "Why customer group is important?";
	public static final String CUSTOMERTUTORIAL_TEXT = "How to create customer group?";

	public static final String SECOND_PARAMETERVALUE = "last_ordered_on"; // "no_of_orders";
	public static final String SECOND_CONDITIONVALUE = "IS BEFORE"; // "IS LESS THAN";
	public static final String SECOND_VALUE = "1";
	
	public static final String ERRORVALUE1 = "Condition is Required";
	public static final String ERRORVALUE3 = "Condition Value is Required";
	public static final String ERRORVALUE4 = "Group Name is Required";
}
