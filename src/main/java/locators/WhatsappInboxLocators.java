package locators;import utils.DriverBase.ElementType;

public class WhatsappInboxLocators {
	
	/* XPATH */public static final String WHATSAPP_INBOX_PAGE = "//div[@class='Polaris-Navigation__ItemWrapper']//span[contains(text(),'WhatsApp Inbox')]";
	/* XPATH */public static final String SUPERLEMON_STORE_HEADING = "//div[@class='storeDetailsLeft']//span[1]";
	/* XPATH */public static final String CONFIGURATION_TAB = "//li[2]//span[contains(text(),'Configuration')]";
	/* XPATH */public static final String CONFIGURATION_HEADING = "//h1[contains(text(),'Configuration')]";
	/* XPATH */public static final String TAGS_TAB = "//button[@id='Tags']";
	/* XPATH */public static final String QUICK_RESPONSE_TAB = "//button[@id='QuickResponse']";
	/* XPATH */public static final String SET_WORKING_HOURS_TAB = "//button[@id='SetWorkingHours']";
	/* XPATH */public static final String ADD_NEW_TAG_BUTTON = "//button[@type='button']//span//span[contains(text(),'Add New Tag')]";
	/* XPATH */public static final String POPUP = "//section[@class='Polaris-Modal-Section']";
	/* XPATH */public static final String TAG_NAME_TEXT_FIELD = "//input[@id='PolarisTextField1']";
	/* XPATH */public static final String SAVE_TAG = "//button[@type='button']//span//span[contains(text(),'Save Tag')]";
	/* XPATH */public static final String LAST_ROW_TAG_NAME = "//table/tbody/tr[last()]//p";
	/* XPATH */public static final String NAME_COLUMN = "//th[contains(text(),'Name')]";
	/* XPATH */public static final String LAST_UPDATED_TIME_COLUMN = "//th[contains(text(),'Last Updated Time')]";
	/* XPATH */public static final String ACTIONS_COLUMN = "//th[contains(text(),'Actions')]";
	/* XPATH */public static final String FIRST_TAG_EDIT_ICON = "//tbody/tr[1]/td[2]/div[1]/div[1]/div[1]/span[1]/span[1]//*[name()='svg']";
	/* XPATH */public static final String UPDATE_EDIT_TAG_BUTTON = "//div[@class='chat__ConfigurationButtonSave']//button[@type='button']";
	/* XPATH */public static final String FIRST_TAG_NAME = "//tbody/tr[1]//th//p";
	/* XPATH */public static final String FIRST_DELETE_ICON = "//tbody/tr[1]/td[2]/div[1]/div[1]/div[1]//span[2]//span";
	/* XPATH */public static final String DELETE_POPUP_YES = "//button[@type='button']//span//span[contains(text(),'YES')]";

}
