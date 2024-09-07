package locators;

public class WixLocators {

	/* xpath */public static final String GREETINGS_TITLE_WIX = "//label[contains(text(),'Title to be displayed on top of the greeting Widget.')]/following::div[3]/input";
	/* xpath */public static final String GREETINGS_HELP_TEXT = "//label[contains(text(),'Help text to be displayed inside the greeting Widget')]/following::div[3]/input";
	
	/* xpath */public static final String TEMPLATE_EDIT_SAVE = "//span[contains(text(),'Save')]";
	/* XPath */ public static final String USERCHECK_INPUT = "(//input[@class='Polaris-Checkbox__Input'])";
	/* XPath */ public static final String USERCHECKBOX = "(//span[@class='Polaris-Checkbox'])";
	
//	/*xpath*/public static final String SETTINGS_BTN = "(//button[@id='Abandoned Cart'])/following::button[2]";
	
	/* XPATH */public static final String SETTINGS_BTN = "(//button[@id='Abandoned Cart'])/following::button[3]";
	/* XPATH */public static final String CLICKTEMPLATE = "//div[@class='template']//p[contains(text(), 'utility_temp')]/following-sibling::div//button";
	
	
//	public static final String USERNAME = "AutoUser 142536";
	public static final String USERNAME = "AutoUser 05277";
	
	public static final String CHOOSETEMPLATENAME = "utility_temp";
}
