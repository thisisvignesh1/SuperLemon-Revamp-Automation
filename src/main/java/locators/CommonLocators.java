package locators;

public class CommonLocators {
	/* xpath */public static final String ENABLE_DISABLE_BTN = "//a[contains(.,'Click')]/../following::span[1]";
	/* xpath */public static final String TEMPLATE_MSGNG_ENABLED_MSG = "//div[contains(@class,'Polaris-Frame-Toast')][contains(text(),'ENABLED')]";
	/* xpath */public static final String TEMPLATE_MSGNG_DISABLED_MSG = "//div[@class='Polaris-Frame-Toast'][contains(text(),'DISABLED')]";
	/* css */public static final String SAVE_SETTINGS = "//span[contains(@class,'Polaris-Button__Text')][contains(text(),'Save Settings')]";
	/* xpath */public static final String GOOGLE_TRANSLATE = ".goog-te-combo";
	/* xpath */public static final String LOG_OUT = "//span[contains(@class,'Polaris-ActionList__Text')][contains(text(),'Logout')]";
	/* xpath */public static final String RADIO_GENERIC = "//span[contains(@class,'Polaris-RadioButton__Backdrop')]";
	/* text */ public static final String CONTACT_US_BTN = "//a[contains(text(),'Contact Us')]";
	/* text */ public static final String HEADER_TEXT = "//h1[contains(text(),";
	/* text */ public static final String Rating_APP_BTN = "//span[text()='Rate our app']";
	/* xpath */ public static final String RADIO_ATTRIBUTE_GENERIC = "//input[@type='radio']";
	/* xpath */public static final String ACTION_SUCCESS_POPUP = "//*[contains(@class,'Polaris-Frame-Toast')]";
	/* xpath */public static final String CHECKBOX_COMMON = "//span[contains(@class,'Polaris-Checkbox__Backdrop')]";
	/* xpath */public static final String ERROR_POPUP_ON_SAVE = "//*[@class='Polaris-Frame-Toast Polaris-Frame-Toast--error']";
	/* xpath */public static final String CHECKBOX_SELECTION_CHECK = "//input[contains(@id,'PolarisCheckbox')]";
	/* CSS */public static final String ADMIN_PROFILE = ".Polaris-Avatar__Initials";
	/* xpath */public static final String ADMIN_LOGOUT = "//button[.='Log out']";
	/* CSS */public static final String ADMIN_LOGIN = ".marketing-nav__item";
	/* xpath */public static final String PROFILENAME = "//button[@aria-controls='Polarispopover2']";
	/* xpath */public static final String HOME_BTN = "(//span[text()='Home'])";
	/* xpath */public static final String PLAN_NAME = "//h1[@class='Polaris-Header-Title']/following::b[1]";
	/* xpath */public static final String SHAREDTEAMINBOX = "//span[text()='Shared Team Inbox']";
	/* css */public static final String SIDENAV = ".Polaris-TopBar__NavigationIcon";
	/* XPath */ public static final String TOURPOPUP = "//div[@role='dialog']";

	/* xpath */public static final String THREEDOTSMENU = "(//button[@aria-label='More tabs'])[2]";
	/* xpath */public static final String MOREFILTERSBTN = "//span[text()='More filters']";
	/* xpath */public static final String DONEBTN = "//span[text()='Done']";
	/* xpath */public static final String CLOSEICON = "//button[@aria-label='Close navigation']/span";

	/* xpath */public static final String DISCARD_SETTINGS = "//span[contains(@class,'Polaris-Button__Text')][contains(text(),'Discard')]";
	
	/* XPATH */public static final String TEMPLATES_PAGE = "//span[normalize-space()='Templates']";
	

}