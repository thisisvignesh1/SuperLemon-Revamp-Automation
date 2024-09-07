package locators;

public class MessagingLocators {
	/* xpath */ public static final String ABANDONED_CART_SUBTAB = "//button[@id='Abandoned Cart']";
	/* xpath */ public static final String CRM_CART_SUBTAB = "//button[@id='Order CRM']";
	/* id */ public static final String SETTINGS_SUBTAB = "//button[@id='settings']";
	/* xpath */ public static final String WA_SPAM_NOTE1 = "//p[contains(text(),'We strongly recommend that you do not send more th')]";
	/* xpath */ public static final String WA_SPAM_NOTE2 = "//p[contains(text(),'If you are using a new number, wait a few days bef')]";
	/* xpath */ public static final String SUBTAB_NOTE = "//div[@class='Polaris-Header-Title__SubTitle-light Polaris-Header-Title__SubtitleCompact mt-2']/p";
	/* xpath */ public static final String RADIO_ATTRIBUTE_GENERIC = "//input[@type='radio']";
	/* xpath */ public static final String RADIO_GENERIC = "//span[contains(@class,'Polaris-RadioButton__Backdrop')]";
	/* xpath */ public static final String LOGIN_HYPERLINK = "//a[@href='https://beta-app.superlemon.xyz/']";
	/* xpath */ public static final String Buttons = "(//span[contains(@class,'Polaris-Button__Text')])";
	/* xpath */ public static final String TAG_NAME_FIELD = "//input[@class='Polaris-TextField__Input']";
	/* xpath */ public static final String CATEGORY_DROPDOWN = "//select[@class='Polaris-Select__Input']";
	/* xpath */ public static final String SAVE_BTN = "//span[contains(text(),'Save')]";
	/* xpath */ public static final String SUCCESS = "//div[contains(@class,'Polaris-Frame-Toast')]";
	/* xpath */ public static final String TAG_NAMES_COMMON = "//*[contains(@class,'spacingTight')]";
	/* xpath */ public static final String CLOSE_VIEW_TAG_MODAL = "//button[@class='Polaris-Modal-CloseButton']";
	/* xpath */ public static final String DELETE_BTN = "//div[contains(text(),'Delete')]";
	/* xpath */ public static final String CONFIRM_BTN = "//span[contains(text(),'Confirm')]";
	/* xpath */ public static final String TEMPLATE_NAME_FIELD = "//input[@class='Polaris-TextField__Input']";
	/* xpath */ public static final String TEMPLATE_MSG_FIELD = "//textarea[@class='Polaris-TextField__Input']";
	/* xpath */ public static final String DISCOUNT_VALUE = "//input[@type='number']";
	/* xpath */ public static final String TEMPLATE_NAMES_COMMON = "//*[contains(@class,'spacingTight')]";
	/* xpath */ public static final String CANCEL_BTN = "//span[contains(text(),'Cancel')]";
	/* xpath */ public static final String WA_DESKTOP_APP_HYPERLINK = "//a[text()='WhatsApp Desktop application.']";
	/* xpath */ public static final String ABANDONED_ORDERID_FILTER = "//input[@placeholder='Search by order id']";
	/* xpath */ public static final String ORDERCRM_ORDERID_FILTER = "//input[@placeholder='Search by id']";
	/* xpath */ public static final String TAG_FILTER = "//button[@id='Activator-templateType']";
	/* xpath */ public static final String NAME_FILTER_TEXTBOX = "(//input[contains(@class,'Polaris-TextField__Input')])[2]";
	/* xpath */ public static final String DATATABLE_ROWS = "//tbody/tr";
	/* xpath */ public static final String ORDER_FILTER_CLEAR_BTN = "//button[@class='Polaris-TextField__ClearButton']";
	/* xpath */ public static final String TAG_FILTER_CLEAR_BTN = "//button[@aria-label='Clear Tag']";
	/* xpath */ public static final String NAME_FILTER_CLEAR_BTN = "//button[@aria-label='Clear Name']";
	/* xpath */ public static final String TABLE_COLUMN = "//button[text()=";
	/* xpath */ public static final String STATIC_COLUMN = "//th[contains(text(),'Status')]";
	/* xpath */ public static final String STATIC_ROWS = "//tbody/tr/td[3]";
	/* xpath */ public static final String NEXT_NAV = "//button[@id='nextURL']";
	/* xpath */ public static final String PREV_NAV = "//button[@id='previousURL']";
	/* xpath */ public static final String ADD_TAG_GENERIC = "//button[@type='button']//span[text()='Add tag']";
	/* xpath */ public static final String SEND_MESSAGE_GENERIC = "//button[@type='button']//span[text()='Send Message']";
	/* xpath */ public static final String TEMPLATE_PLACEHOLDERS = "//span[@class='Polaris-TextStyle--variationSubdued']";
	/* xpath */ public static final String EDIT_BTN = "//div[contains(text(),'Edit')]";
	/* xpath */ public static final String NO_TAG_MSG = "//tbody/tr[1]/td[4]";
	/* xpath */ public static final String NO_TEMPLATE_MSG = "//tbody/tr[1]/td[5]";
	/* xpath */ public static final String SEND_MESSAGE_SEND_BTN = "//button[@type='button']//span[text()='Send']";
	/* xpath */ public static final String NO_TAG = "//p[contains(text(),'No Tags found')]";
	/* xpath */ public static final String NO_TEMPLATE = "//p[contains(text(),'No templates found')]";
	/* xpath */ public static final String WA_TEMPLATE = "//div[contains(text(),'This is automated message')]";
	/* xpath */public static final String ORDER_COLUMN_FIRST_VALUE = "//tbody/tr[1]/th[1]";

	///* xpath */ public static final String MANUAL_MESSAGING_TAB = "//span[normalize-space()='Messaging']";
	/* xpath */ public static final String SETTINGS_BTN = "(//button[@id='Abandoned Cart'])/following::button[3]";

	/* xpath */ public static final String NAME_FILTER = "(//span[text()='Name'])[2]";
	/* xpath */ public static final String CREATETAG = "//span[text()='Create Tag']";
	/* xpath */ public static final String VIEWTAG = "//span[text()='View Tags']";
	/* xpath */ public static final String MESSAGE_TEMPLATE_SUBTAB = "(//button[@id='Abandoned Cart'])/following::button[2]";
	/* css */ public static final String MESSAGING_PROMOTIONAL_TILE = ".msg-tile";
	/* xpath */ public static final String INSTALLNOW = "//span[text()='Install Now']";
	/* xpath */ public static final String INSTALLEXTENSION = "//span[text()='Install Extension']";
	/* xpath */ public static final String SEETUTORIALBTN = "//span[text()='See Tutorial']";
	/* CSS */ public static final String IFRAMEYOUTUBEPLAYER = "#ytplayer";
	/* CSS */ public static final String CLOSEPROMOTIONALTILE = ".close";
	/* CSS */public static final String TAG_DROPDOWN = "#Activator-templateType";
	/* xpath */ public static final String CLEAR = "//button[contains(@aria-label,'Clear')]";
	/* xpath */ public static final String CLOSEPOPUP = "//button[(@class='Polaris-Modal-CloseButton')]";
	/* xpath */ public static final String CREATEDTAGS = "//span[@class='Polaris-Choice__Label']";
	/* xpath */ public static final String ADDTAG = "(//span[text()='Add tag'])[1]";
	/* CSS */public static final String POUUP = ".Polaris-Modal-Dialog__Modal";
	/* xpath */ public static final String SELECTVALUE = "//select[@class='Polaris-Select__Input']";
	/* xpath */ public static final String ADDTAG_BTN = "//h2[text()='Add tag']/following::button[3]";
	/* xpath */ public static final String TAGSCOLOUMN = "//tr/td[4]";
	/* xpath */ public static final String REMOVETAG = "(//tr/td[3]/following::button)[1]";
	/* xpath */ public static final String SENDMESSAGE1 = "(//tr/td[3]/following::button)[1]";
	///* xpath */ public static final String SENDMESSAGE = "(//tr/td[3]/following::button)[2]";
	/* xpath */ public static final String SENDMESSAGE_BTN = "//h2[text()='Send message']/following::button[3]";
	/* xpath */ public static final String ALLTEMPLATE_BTN = "(//span[@class='Polaris-RadioButton__Backdrop'])[1]";
	/* xpath */ public static final String ABCART_TEMPLATE_BTN = "(//span[@class='Polaris-RadioButton__Backdrop'])[2]";
	/* xpath */ public static final String ORDERCRM_TEMPLATE_BTN = "(//span[@class='Polaris-RadioButton__Backdrop'])[3]";
	/* xpath */ public static final String QUICKREPLY_TEMPLATE_BTN = "(//span[@class='Polaris-RadioButton__Backdrop'])[4]";
	/* xpath */ public static final String QUICKREPLY_2ndCATEGORY_DROPDOWN = "//label[contains(text(),'Select Quick Reply Category')]/following::select";
	
	/* xpath */ public static final String EDITTEMPLATE_NAME = "//button[@title='Edit Template Name']";
	/* xpath */ public static final String NAME_INPUT = "//input[@placeholder='Give a name to your template']";
	
	/* xpath */ public static final String MANUAL_MESSAGING_TAB = "//span[contains(text(),'CRM Tool')]";
	
	/* xpath */ public static final String SENDMESSAGE = "//span[contains(text(),'Send Message')]";
	/* xpath */ public static final String SEARCH_NAME = "//span[contains(text(),'Search')]";
	
//	/* xpath */ public static final String NEW_CRMTOOL_INPUT = "//input[@type='checkbox']";
	/* xpath */ public static final String NEW_CRMTOOL_TOGGLE = "//div[@class='react-switch-bg']";
	
	/* xpath */ public static final String NEW_CRMTOOL_INPUT = "//input[@role='switch']";
	
	public static final String CHROMEURL = "https://chrome.google.com/webstore/";
	public static final String TAGNAME = "AutoTag0.8738982349377947";
//	public static final String USERNAME = "AutoUser 142536";
	public static final String TEMPLATE_CREATED = "Template created successfully";
	public static final String TEMPLATE_DELETED = "Template deleted successfully";
	public static final String TEMPLATE_EDITED = "Template edited successfully";
	public static final String USERNAME = "AutoUser 5689";

}