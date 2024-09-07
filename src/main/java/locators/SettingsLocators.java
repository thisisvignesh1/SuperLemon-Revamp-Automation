package locators;

public class SettingsLocators {
	/* css */public static final String OPTIN_SELECT_CHECKBOXES = ".Polaris-Checkbox__Backdrop";
	/* xpath */ public static final String SAVE_SUCCESS_MSG = "//div[contains(@class,'Polaris-Frame-Toast')][contains(text(),'Data Saved')]";
	/* xpath to click */public static final String ADMIN_PAGE_CHECKBOXES_GENERIC = "//span[contains(@class,'Polaris-Checkbox__Backdrop')]";
	/* xpath to check enabled or disabled */public static final String OPTIN_CHECKBOXES = "//input[contains(@class,'Polaris-Checkbox__Input')]";
	/* xpath */public static final String DISABLE_OPTIN_FROM_STOREFRONT = "(//*[contains(@class,'Polaris-Checkbox__Backdrop')])[1]";
	/* xpath */public static final String DISABLE_STOREFRONT_OPTIN_ENABLE_CHECK = "//h2[contains(text(),'Collect WhatsApp opt-in from Storefront widget')]/following::input[1]";
	/* xoath */public static final String RADIO_CLICKABLE_GENERIC = "//span[contains(@class,'Polaris-RadioButton__Backdrop')]";
	/* xpath */public static final String COUNTRY_CODE_VALIDATION_ERROR = "//div[@id='PolarisTextField1Error']";
	/* xpath */public static final String COUNTRY_CODE_VALIDATION_MSG_ON_SAVE = "//div[@class='Polaris-Frame-Toast Polaris-Frame-Toast--error'][contains(text(),'country code')]";
	/* xpath */public static final String SUPPORT_NUMBER_INVALID_ERROR = "//div[@id='PolarisTextField2Error']";
	/* xpath */public static final String SUPPORT_NUMBER_VALIDATION_0N_SAVE_MSG = "//div[@class='Polaris-Frame-Toast Polaris-Frame-Toast--error'][contains(text(),'phone number ')]";
	/* xpath */public static final String WHATSAPP_CONFIGURATION_HEADING = "//h2[@class='Polaris-Heading'][contains(text(),'WhatsApp Configuration')]";
	/* xpath */public static final String PHONE_NO_HYPERLINK = "//a[contains(text(),'phone number')]";
	/* xpath */public static final String BUSINESS_NAME_HYPERLINK = "//a[contains(text(),'business name')]";
	/* xpath */public static final String WHATSAPP_SUPPORT_NUMBER = "//a[contains(text(),'WhatsApp support number')]";
	/* xpath */public static final String PRICING_PAGE_HYPERLINK = "//img[@alt='Question mark']";
	/* xpath */public static final String LANGUAGE_RADIO = "//input[@id='PolarisRadioButton1']";
	/* xpath */public static final String POPUP_CLOSE_BTN = "//button[@class='Polaris-Frame-Toast__CloseButton']";

	/* xpath */ public static final String ADMIN_CONFIGURATION_TAB = "//span[text()='Settings']";
	/* xpath */public static final String WA_SUPPORT_NUM_COUNTRY_CODE = "//div[contains(text(),'Please select your country code')]/following::input[1]";
	/* xpath */public static final String WA_SUPPORT_NUM_TEXT_FEILD = "//div[contains(text(),'Please select your country code')]/following::input[2]";

	/* xpath */public static final String OPTIN_STOREFRONT_DESCRIPTION = "//b[text()='Storefront Widget']/following::div[@class='Polaris-TextContainer'][1]";
	/* xpath */public static final String COLLECT_OPTIN_FROM_CHCKOUT_APPROVED_MSG = "//p[contains(text(),'Your request')]";

	/* xpath */public static final String THANKYOU_WIDGET_PAGE_HYPERLINK = "(//div[contains(text(),'Preview')])[2]";
	/* xpath */public static final String PREVIEW_OPTIN_HYPERLINK = "(//div[contains(text(),'Preview')])[1]";
	/* xpath */public static final String SPANISH_LANGUAGE_RADIO = "(//input[@class='Polaris-RadioButton__Input'])[3]";
	/* xpath */public static final String SPAPNISH_LANGUAGE_SELECTION_CHECK = "//span[contains(text(),'Storefront Opt-in Widget Language')]/following::span[12]";

	/* xpath */public static final String ITALIAN_LANGUAGE_RADIO = "(//input[@class='Polaris-RadioButton__Input'])[4]";
	/* xpath */public static final String ITALIAN_LANGUAGE_SELECTION_CHECK = "//span[contains(text(),'Storefront Opt-in Widget Language')]/following::span[13]";
	
	/* xpath */public static final String MARKETING_MONTHLY_USAGE = "//div[contains(text(),'Marketing campaign monthly usage limit')]/following::input[1]";
	/* XPath */public static final String MARKETING_MONTHLY_USAGE_UP = "//div[@tabindex='-1'][1]";
	/* XPath */public static final String MARKETING_MONTHLY_USAGE_DOWN = "//div[@tabindex='-1'][2]";

	public static final String SUPPORTNUMBER_VALUE = "7977861093";
	public static final String INVALIDSUPPORTNUMBER_VALUE = "7977861094";
}
