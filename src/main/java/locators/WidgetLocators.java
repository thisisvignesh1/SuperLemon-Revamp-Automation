package locators;

public class WidgetLocators {

	/* CSS */public static final String PERSONAL_WIDGET_TAB = "[href='/widget']";
	/* xpath PERSONAL_WIDGET_TAB */ public static final String PERSONAL_WIDGET = "//*[contains(@class,'Polaris-Navigation__Text')][contains(text(),'Personal')]";
//  /*id*/ public static final String CHAT_SETTING_TAB="//button[@id='Chat Settings']";
//  /*xpath*/ public static final String SHARE_SETTINGS_TAB ="//button[@id='Share Settings']";
	/* xpath */public static final String MOBILE_ONLY_RADIO = "//span[text()='Mobile only']";
	/* id */public static final String MOBILE_RADIO = "//label[@for='PolarisRadioButton2']";
	/* xpath */ public static final String DESKTOP_MOBILE_RADIO = "//span[text()='Mobile + Desktop']";
	/* Xpath */ public static final String STORE_TIMINGS_EDIT_OPTION = "//span[contains(@class,'Polaris-Button__Text')][contains(text(),'Edit on/off hours')]";
	/* xpath */ public static final String ENABLE_DISABLE_AGENT_BUTTON = "//*[contains(@class,'Polaris-DataTable')]/td[4]";
	/* xpath */ public static final String AGENT_STATUS = "//*[contains(@class,'Polaris-DataTable')]/td[2]";
	/* xpath */public static final String AGENT_ENABLED_MESSAGE = "//div[contains(@class,'Polaris-Frame-Toast')][contains(text(),'enabled')]";
	/* xpath */public static final String AGENT_DISABLED_MESSAGE = "//div[contains(@class,'Polaris-Frame-Toast')][contains(text(),'disabled')]";
	/* xpath */public static final String GREETINGS_ENABLED_MSG = "//*[contains(@class,'Polaris-Frame-Toast')][contains(text(),'Enabled Greeting')]";
	/* xpath */public static final String ATLEAST_ONE_AGENT_ENABLED_MSG = "//*[contains(@class,'Frame-Toast--error')][contains(text(),'At least one agent should be enabled')]";
	/* text */public static final String GREETINGS_WIDGET_SECTION = "//*[contains(@class,'Polaris-Heading')][contains(text(),'Greetings Widget')]";
	/* xpath */public static final String SAVE = "//span[contains(@class,'Polaris-Button__Text')][contains(text(),'Save')]";
	/* xpath */ public static final String AGENT_NAME = "//*[contains(@class,'Polaris-DataTable')]/td[1]";
	/* xpath */ public static final String EDIT_DETAILS = "//*[contains(@class,'Polaris-DataTable')]/td[3]";
	/* xpath */public static final String AGENT_NAME_EDITABLE = "//label[contains(text(),'Agent Name')]/following::input[1]";
	/* css */ public static final String AGENT_NAME_FILL = "input[placeholder='Example: John Doe']";
	/* id */ public static final String HEIGHT_OFFSET_DESKTOP = "(//label[text()='HEIGHT offset']/following::input[1])[2]";
	/* id */ public static final String EDGE_OFFSET = "(//label[text()='EDGE offset']/following::input[1])[2]";
	/* id */ public static final String PRODUCT_HEIGHT_OFFSET = "//label[text()='Product HEIGHT offset']/following::input[1]";
	/* xPath */ public static final String DESKTOP_POS_RIGHT = "(//input[@name='btnpositiondesktop'])[2]";
	/* xpath */ public static final String DESKTOP_POS_LEFT = "(//input[@name='btnpositiondesktop'])[1]";
	/* id */ public static final String CHAT_DSK_POS_LEFT = "d-left";
	/* xpath */public static final String GREETINGS_DISABLED_MSG = "//*[contains(@class,'Polaris-Frame-Toast')][contains(text(),'Disabled Greeting')]";
	/* xpath */ public static final String DISABLE_AGENT_BTN = "//*[contains(@class,'Polaris-Link--removeUnderline')][contains(text(),'Disable')]";
	/* xpath */public static final String CHAT_CHANGE_DESIGN_BTN = "(//*[contains(@class,'Polaris-Button__Text')][contains(text(),'Select Design')])[1]";
	/* xpath */public static final String GREETINGS_CHANGE_DESIGN = "(//*[contains(@class,'Polaris-Button__Text')][contains(text(),'Select Design')])[2]";
	/* xpath */public static final String CUSTMZED_CHT_BTN = "(//div[contains(@class,'wa-chat-button')])[4]";
	/* id */public static final String ICON_COLOR_CHAT_BTN_SINGLE_COLOR = "PolarisTextField3";
	/* id */ public static final String CALLOUT_CARD_CHECKBOX = "//input[@id='PolarisCheckbox3']";
	/* xpath */public static final String CALLOUT_CHECKBOX_SELECT = "(//span[@class='Polaris-Checkbox__Backdrop'])[3]";
	///* id */ public static final String CALLOUT_TEXTBOX_FIELD = "//input[@id='PolarisTextField7']";
	///* id */ public static final String CALLOUT_CARD_DELAY = "//input[@id='PolarisTextField8']";
	/* xpath */ public static final String SUCCESS_POPUP = "//div[@class='Polaris-Frame-ToastManager']";
	/* id */ public static final String CHECKBOX = "//span[@class='Polaris-Checkbox']//input[@id=";
	/* id */ public static final String COLLECTIONS_CHECKBOX = "(//span[normalize-space()='Collections'])[1]";
	/* id */ public static final String PRODUCT_PAGES_CHECKBOX = "(//span[normalize-space()='Product pages'])[1]";
	/* id */ public static final String CART_DESKTOP_CHECKBOX = "//span[@class='Polaris-Checkbox']//input[@id='PolarisCheckbox20']";
	/* id */ public static final String CART_MOBILE_CHECKBOX = "//span[@class='Polaris-Checkbox']//input[@id='PolarisCheckbox21']";
	/* id */ public static final String THANKYOU_PAGE_CHECKBOX = "//span[@class='Polaris-Checkbox']//input[@id='PolarisCheckbox22']";
	/* id */ public static final String BLOG_POST_PAGE_CHECKBOX = "//span[@class='Polaris-Checkbox']//input[@id='PolarisCheckbox23']";
	/* id */ public static final String URL_ENDING_WITH_CHECKBOX = "//span[@class='Polaris-Checkbox']//input[@id='PolarisCheckbox24']";
	/* xpath */ public static final String PAGES_CHECKBOX_CLICK = "//span[@class='Polaris-Checkbox__Backdrop']";
	/* xpath */public static final String ALLCHECKBOX = "//div[@class='Polaris-Stack Polaris-Stack--vertical']//input[@type='checkbox']";
	/* xpath */ public static final String PAGES_TO_DISPLAY_CHECKBOX = "//div[@class='Polaris-Stack__Item']//span[@class='Polaris-Checkbox']";
	/* xpath */ public static final String SHARE_BTN_DISPLAY_RADIO = "//div//input[@id=";

//	 /*xpath*/ public static final String HOMEPAGE_CHECKBOX = "//span[@class='Polaris-Checkbox']//input[@id=";
	/* xpath */ public static final String DESKTOP_POS = "(//input[@name='btnpositiondesktop'])";
	/* xpath */ public static final String SHARE_DESIGN_CHANGE_BTN = "//span[text()='Select Design']";
	/* id */ public static final String SHARE_BTN_TEMPLATE_RADIO = "//input[@type='radio' and @value=";
	/* text */ public static final String SAVE_BTN = "//span[contains(text(),'Save')]";
	/* xpath */ public static final String SHARE_BTN_TEXT = "//input[@placeholder='This text will display beside the WhatsApp share button']";
	/* xpath */ public static final String SHARE_MSG_TXTAREA = "//textarea[@placeholder='Pre-filled message body, can be edited by store visitor before sending']";
	/* xpath */ public static final String SUBSECTION_HEADERS = "//h2[contains(text(),";
	/* xpath */ public static final String ADD_ANOTHER_AGENT_BTN = "//span[contains(text(),'+ Add another number')]";
	/* xpath */ public static final String PINCODE_TXTBOX = "//input[@placeholder='Click ? for country codes']";
	/* xpath */ public static final String WA_PHONE_NO_TXTBOX = "//input[@placeholder=\"Example: 9035026993\"]";
	/* xpath */ public static final String AGENT_ROLE = "//input[@placeholder='Example: Customer Support']";
	/* xpath */ public static final String AGENT_AVATAR_RADIO_BTN = "//input[@type='radio' and @value=";
	/* xpath */ public static final String AGENT_HRS_TXTBOX = "//input[@id='PolarisTextField";
	/* xpath */ public static final String AGENT_NAME_TXTBOX = "//input[@placeholder='Example: John Doe']";
	/* xpath */ public static final String CHAT_WIDGET_AGENT_NAMES = "//div[contains(text(),";

	/* xpath */public static final String FIRST_CUSTOM_GREETING_CARDS = "//input[@value='301']";
	/* xpath */public static final String SINGLE_COLOR_RADIO_GREETINGS = "(//span[contains(@class,'Polaris-RadioButton__Backdrop')])[3]";
	/* id */public static final String GREETINGS_HEADING_TXT__COLOR = "PolarisTextField22";
	/* id */public static final String GREETINGS_DESC_TXT_COLOR = "PolarisTextField23";
	/* CSS */public static final String GREETINGS_TITLE_TXT_FIELD = "input[id='PolarisTextField16']";
	/* CSS */public static final String GREETINGS_HELPTEXT_FIELD = "input[id='PolarisTextField17']";
//	/* xpath */ public static final String SAVE_SUCCESS_MSG = "//*[contains(@class,'Polaris-Frame-Toast')][contains(text(),'Data Saved Successfully')]";
	///* xpath */public static final String SHOW_CHT_BTN_WHEN_OFFLINE_CHKBOX = "//input[@id='PolarisCheckbox1']";
	/* xpath */public static final String TIMINGS_UPDATED_SUCCESS_MSG = "//*[contains(@class,'Polaris-Frame-Toast')][contains(text(),'Timings updated successfully')]";
	/* xpath */public static final String SHOW_CHAT_WHEN_OFFLINE_CHECKBOX = "(//span[@class='Polaris-Checkbox__Backdrop'])[1]";
	/* xpath */public static final String AGENT_RANDOMIZE_CHECKBOX = "(//span[@class='Polaris-Checkbox__Backdrop'])[5]";
	/* xpath */ public static final String CHAT_ENABLED_SUCCESS_MSG = "//div[contains(@class,'Polaris-Frame-Toast')][contains(text(),'Enabled Chat')]";
	/* xpath */ public static final String CHAT_DISABLED_SUCCESS_MSG = "//div[contains(@class,'Polaris-Frame-Toast')][contains(text(),'Disabled Chat')]";
	/* xpath */ public static final String CALL_OUT_HEADER = "//h2[contains(text(),'Callout Card')]";
	/* xpath */public static final String DESKTOP_PRODUCT_OFFSET_CHECKBOX = "//input[@id='PolarisCheckbox6']";

	/* xpath */public static final String AGENT_TABLE_ROWS = "//table/tbody/tr";
	/* xpath */public static final String DESKTOP_ONLY_RADIO = "//div//input[@id='PolarisRadioButton3']";
	/* xpath */public static final String CHECKBOX_GENERIC = "(//input[@role='checkbox'])";
	/* xpath */public static final String HOMEPAGE_CHECKBOX = "(//span[@class='Polaris-Checkbox__Backdrop'])[8]";
	/* xpath */public static final String MOBILE_PLUS_DESKTOP_RADIO_SELECTION_CHECK_SHARE = "//input[@id='3']";
	/* xpath */public static final String MOBILE_DESKTOP_SHARE_CLICKABLE = "(//span[@class='Polaris-RadioButton__Backdrop'])[1]";
	/* xpath */public static final String HEIGHT_OFFSET_SHARE_BTN_MOBILE_DESKTOP_SELECTED = "(//input[@max='100'])[2]";
	/* id */public static final String MOBILE_PRODUCT_OFFSET_CHECKBOX_SELECTION_CHECK = "PolarisCheckbox5";
	/* xpath */public static final String MOBILE_PRODUCT_HEIGHT_OFFSET_CHECKBOX_CLICKABLE = "(//span[@class='Polaris-Checkbox__Backdrop'])[5]";
	/* xpath */public static final String TO_TIMING_FIELD_STORE_TIMINGS = "//input[contains(@id,'PolarisTextField')]";
	/* xpath */public static final String ACCOUNTPAGESTEXT_CHECKBOX = "//span[contains(.,'Please note that by default we show the Spin the wheel on all other pages that a')]";
	/* xpath */public static final String ACCOUNTPAGES_CHECKBOX = "//span[@class='Polaris-Checkbox']//input[@id='PolarisCheckbox25']";
	/* xpath */public static final String ERRORPOP = "(//div[@class='Polaris-Frame-Toast Polaris-Frame-Toast--error'])[1]";
	/* xpath */public static final String MINIMUMHEIGHTERROR = "(//div[contains(text(),'Minimum')])[1]";
	/* xpath */public static final String MINIMUMEDGEERROR = "(//div[contains(text(),'Minimum')])[2]";
	/* xpath */public static final String MINIMUMHEIGHTINPUT = "(//label[contains(text(),'HEIGHT')])[2]/following::input[1]";
	/* xpath */public static final String MINIMUMEDGEINPUT = "(//label[contains(text(),'HEIGHT')])[2]/following::input[2]";
	/* xpath */public static final String EMPTYHEIGHTERROR = "(//div[contains(text(),'Desktop height offset cannot be empty')])[1]";
	/* xpath */public static final String EMPTYEDGEERROR = "(//div[contains(text(),'Desktop edge offset cannot be empty')])[1]";
	/* xpath */ public static final String SPINWHEELDESKTOPONLYRADIOBTN = "//span[contains(text(),'Desktop only')]/preceding-sibling::span[1]";
	/* xpath */ public static final String SPINWHEELMOBILEDESKTOPRADIOBTN = "//span[contains(text(),'Mobile + Desktop')]/preceding-sibling::span[1]";
	/* xpath */public static final String STORE_DISABLE_BUTTON = "//span[text()='Disable']";
	/* xpath */public static final String STORE_ENABLE_BUTTON = "//span[text()='Enable']";
	/* CSS */public static final String VIEW_STORE_SPIN_WHEEL = "a[href$='shopify.com'][class*='Link']";
	/* CSS */public static final String SPIN_WHEEL_LOGO = "img[src*='spin-wheel.svg']";
	/* xpath */public static final String SPIN_WHEEL_BUTTON = "//button[text()='Spin the Wheel']";
	/* CSS */public static final String SPIN_WHEEL_PHONE_NUMBER = "#spin-wheel-optin-number-input";
	/* CSS */public static final String SPIN_WHEEL_CONFIRM_BUTTON = "#spin-wheel-confirm-btn";
	/* xpath */public static final String COPY_COUPON_CODE = "//button[text()='Copy coupon code']";
	/* xpath */public static final String FIRST_DISPLAY_CODE = "(//input[contains(@id,'PolarisTextField')])[2]";
	/* xpath */public static final String SECOND_DISPLAY_CODE = "(//input[contains(@id,'PolarisTextField')])[5]";
	/* xpath */public static final String THIRD_DISPLAY_CODE = "(//input[contains(@id,'PolarisTextField')])[8]";
	/* xpath */public static final String FOURTH_DISPLAY_CODE = "(//input[contains(@id,'PolarisTextField')])[11]";
	/* xpath */public static final String MESSAGE_TITLE = "//span[contains(text(),'Language')]";
	/* CSS */public static final String BETER_LUCK_NEXT_TIME = ".game-status-subtxt";
	/* xpath */ public static final String DELETEDISCOUNTCODE = "(//span[@class='Polaris-Button__Text'][normalize-space()='Delete'])[4]";
	/* xpath */ public static final String ADDDISCOUNTCODE = "//span[contains(text(),'Add More')]";
//	/* xpath */ public static final String MINIMUMDISCOUNTCODEERROR = "(//div[contains(text(),'You have reached minimum limit of 4')])[1]";
	/* xpath */ public static final String MAXIMUMDISCOUNTCODEERROR = "(//div[contains(text(),'You have reached maximum limit of 12')])[1]";
	/* xpath */ public static final String MESSAGELANGUAGESECTION = "(//span[normalize-space()='Language'])/parent::div";
	/* xpath */ public static final String SECONDSPINWHEELRADIOBUTTON = "(//div[@class='templateItem share-template-btns'])[2]";
	/* xpath */ public static final String SECONDSPINWHEELRADIOINPUT = "(//div[@class='templateItem share-template-btns'])[2]/descendant::input";
	/* xpath */ public static final String SELECTDESIGNBTN = "//span[contains(text(),'Select Design')]";
	/* xpath */ public static final String DISCOUNTCODETITLE = "//a[contains(text(),'Discount Code')]";
	/* xpath */ public static final String ENGLISHLANGUAGERADIOBTN = "(//span[normalize-space()='English'])[1]/preceding-sibling::span";
	/* xpath */ public static final String GERMANLANGUAGERADIOBTN = "(//span[normalize-space()='German'])[1]/preceding-sibling::span";
	/* xpath */ public static final String DISCARDBTN = "//span[contains(text(),'Discard')]";
	/* xpath */ public static final String FIRSTDISCOUNDCODETEXT = "//span[normalize-space()='Text']/following::input[1]";
	/* xpath */ public static final String MINIMUMDISCOUNTCODETEXTERROR = "(//div[contains(text(),'Invalid wheel config values')])[1]";
	/* xpath */ public static final String MAXIMUMDISCOUNTCODETEXTERROR = "(//div[contains(text(),'Invalid Text Length')])[1]";
	/* xpath */ public static final String HEIGHTINCREASEINDENT = "(//label[contains(text(),'HEIGHT')])[2]/following::span[1]";
	/* xpath */ public static final String HEIGHTDECREASEINDENT = "(//label[contains(text(),'HEIGHT')])[2]/following::span[2]";
	/* xpath */ public static final String EDGEINCREASEINDENT = "(//label[contains(text(),'EDGE')])[2]/following::span[1]";
	/* xpath */ public static final String EDGEDECREASEINDENT = "(//label[contains(text(),'EDGE')])[2]/following::span[2]";
	/* xpath */ public static final String VIEW_STORE = "//h1[text()='Widget Settings']/following::a[1]";
	/* CSS */public static final String CALLOUTCLOSEICON = ".wa-callout-card-close-btn";
	/* xpath */ public static final String HOMEPAGECHECKBOX = "//h2[.='Pages to display']/following::input[1]";
	/* xpath */ public static final String HOMEPAGECHECKBOXCLICK = "//span[text()='Homepage']";
	/* xpath */ public static final String ADDAGENTBUTTON = "//span[text()='+ Add new Agent']";
	/* xpath */public static final String COUNTRYCODEAA = "div[class*='indicatorContainer']";
	/* CSS */public static final String INDIACODE = "div[id*='option-97']";
	/* CSS */public static final String CLOSEICON = "button[aria-label='Close']";

	/* id */ public static final String SHARE_SETTINGS_TAB = "Share Widgets";
	/* linkText */ public static final String CHAT_SETTING_TAB = "Chat Widgets";
	/* xpath */ public static final String PERSONALWIDGETTAB = "//span[normalize-space()='Widgets']";

	/* xpath */ public static final String SPINWHEEL_SETTINGS_TAB = "//button[@id='Optin Widgets']";
	///* xpath */public static final String CONFIGURESPINWHEEL = "(//span[text()='Configure Widget'])[2]";
	/* CSS */public static final String COUNTRY_CODE = "#wa-optin-country-code";

	/* CSS */public static final String CLOSE_BUTTON = "//div[@class='spinwheel-bubble-close-btn']";
	/* CSS */public static final String SPINWHEELTEXT = ".game-status-txt";

	/* xpath */public static final String ENABLE_DISABLE_GREETINGS = "//h2[text()='Greetings Widget']/following::button[1]";
	/* xpath */public static final String OPTINWIDGETSTAB = "(//span[contains(text(),'Optin Widgets')])[2]";
	/* xpath */public static final String ENABLE_DISABLE_STOREFRONT_WIDGET = "//b[text()='Storefront Widget']/following::div[4]";
	/* xpath */public static final String ENABLE_DISABLE_STOREFRONT_WIDGET_TXT = "(//b[text()='Storefront Widget']/following::span[@class='Polaris-TextStyle--variationSubdued'])[2]";

	/* xpath */public static final String SECOND_DESIGN_CHATWIDGET = "(//div[@class='templateItem'])[2]/descendant::input";
	/* xpath */public static final String BG_COLOR_CHAT_BTN_SINGLE_COLOR = "//label[contains(text(),'Button text colour')]/following::input[1]";
	/* xpath */public static final String BTN_TEXT_COLOR_CHAT_BTN_SINGLE_COLOR = "//label[contains(text(),'Background colour 1')]/following::input[1]";
	/* xpath */public static final String LEFT_POS_DESKTOP = "//h2[contains(text(),'Desktop')]/following::input[1]";
	/* xpath */public static final String CHAT_ICON_TEXT_FEILD = "//label[contains(text(),'Chat Button Text')]/following::input[1]";
	/* xpath */public static final String CHAT_MSG_BODY_TEXT_FEILD = "//label[contains(text(),'WhatsApp Message Body')]/following::textarea[1]";
	/* xpath */public static final String STORE_TO_TIME = "//span[text()='To']/following::input[1]";
	// xpath need to change
	/* xpath */public static final String CHECKOUTCONFIGURE_WIDGET = "//b[text()='Checkout Widget']/following::span[text()='Configure Widget'][1]";
	/* xpath */public static final String EDIT_CONFIG = "(//div[contains(text(),'Edit Configuration')])[1]";
	/* xpath */public static final String BACK = "//div[text()='Back']";

	/* xpath */public static final String STOREFRONT_WIDGET_LANGUGAE_HEADER = "//span[contains(text(),'Storefront Opt-in Widget Language')]";
	/* xpath */public static final String WIDGET_SETTING_HEADER = "//h1[text()='Widget Settings']";

	/* xpath */public static final String STOREFRONT_PREVIEW_BTN = "(//div[text()='Preview'])[1]";
	/* xpath */public static final String CHECKOUT_CONFIG_REQUEST = "//h2[.='Collect WhatsApp opt-in from Checkout Page']";
	/* xpath */public static final String CHATWIDGET_SETTING = "//button[@id='Chat Widgets']";

	/* xpath */public static final String SHAREWIDGET_SETTING = "//button[@id='Share Widgets']";
	/* xpath */public static final String OPTIN_CHATWIDGET_ENABLE_DISABLE_TXT = "//b[contains(text(),'Opt-in Via Chat Widget')]/following::span[2]";
	/* xpath */public static final String OPTIN_CHATWIDGET_ENABLE_DISABLE_BTN = "//b[contains(text(),'Opt-in Via Chat Widget')]/following::div[2]";
	/* xpath */public static final String OPTIN_CHATWIDGET_EDIT_CONFIG = "//b[contains(text(),'Opt-in Via Chat Widget')]/following::span[4]";
	/* xpath */public static final String OPTIN_CHATWIDGET_DESIGN = "(//div[@class='Polaris-Stack__Item'])[11]";
	/* xpath */public static final String OPTIN_CHATWIDGET_DEFAULTDESIGN = "//b[contains(text(),'Customizable Designs ')]/following::div[4]";
//	/* xpath */public static final String OPTIN_CHATWIDGET_HEADING_TEXT_INPUT = "//span[contains(text(),'Select Design')]/following::input[1]";
//	/* xpath */public static final String OPTIN_CHATWIDGET_HELP_TEXT_INPUT = "//span[contains(text(),'Select Design')]/following::input[2]";
	/* xpath */public static final String STOREFRONT_WIDGET = "//b[contains(text(),'Storefront Widget')]";
	/* xpath */public static final String THANKYOU_WIDGET = "//b[contains(text(),'Thank you Page Widget')]";
	/* xpath */public static final String CHECKOUT_WIDGET = "//b[contains(text(),'Checkout Widget')]";
	/* xpath */public static final String SPINTHEWHEEL = "//b[contains(text(),'Spin the Wheel')]";
	/* xpath */public static final String OPTIN_CHATWIDGET = "//b[contains(text(),'Opt-in Via Chat Widget')]";
	/* xpath */public static final String RATE_OUR_APP = "//span[text()='Rate our app']";
	/* xpath */public static final String THANKYOU_WIDGET_PREVIEW = "//b[contains(text(),'Thank you Page Widget')]/following::div[12]";
	/* xpath */public static final String SPINWHEEL_VIEWON_STORE = "(//a[@class='Polaris-Link'])[1]";
	/* xpath */public static final String OPTINVIACHAT_WIDGET_PREVIEW = "//b[contains(text(),'Opt-in Via Chat Widget')]/following::div[15]";
	/* xpath */public static final String OPTINVIACHAT_WIDGET_ENABLE_DISABLE = "(//span[contains(@class,'Polaris-TextStyle--variation')])[4]";
	/* xpath */public static final String OPTINVIACHAT_WIDGET_ENABLE_DISABLE_BTN = "//h2[contains(text(),'Opt-in Settings')]/following::button[1]";
	/* CSS */public static final String OPTINVIA_CHATWIDGET_CHATBOX_COLOUR = ".wa-chat-bubble-header-common";
	/* xpath */public static final String OPTINVIACHAT_WIDGET_SINGLECOLOR_BTN = "(//span[@class='Polaris-RadioButton__Backdrop'])[1]";
	/* CSS to check btn selected or not */public static final String SINGLECOLOUR = "#optin-solid";
	/* xpath */public static final String OPTINVIACHAT_WIDGET_COLOR_TEXTFIELD = "(//input[@id='optin-solid'])/following::input[2]";
//	/* xpath */public static final String OPTINVIACHAT_WIDGET_HEADERCOLOR_TEXTFIELD = "(//input[@id='optin-solid'])/following::input[3]";
//	/* xpath */public static final String OPTINVIACHAT_WIDGET_DESCRIPTIONCOLOR_TEXTFIELD = "(//input[@id='optin-solid'])/following::input[4]";

	/* xpath */public static final String DISPLAY_TEXT_TUTORIAL = "(//div[@class='Polaris-Modal-Header'])//h2";
	/* xpath */ public static final String SAVE_SUCCESS_MSG = "//*[contains(@class,'Polaris-Frame-Toast')][contains(text(),'Data Saved')]";

	/* xpath */ public static final String SPINWHEEL_ENG = "//input[@id='English']";
	/* xpath */ public static final String CONFIGURESPINWHEEL = "(//*[text()='Spin the Wheel'])/following::button[1]/span";
	/* xpath */public static final String DISABLE_TEXT = "//div[contains(text(),'Spin The Wheel is currently')]//child::span";
	/* xpath */public static final String ENABLE_BUTTON = "(//div[contains(text(),'Spin The Wheel is currently')]/following::button)[1]";
	/* xpath */public static final String STORE_LINK_SPINWHEEL = "//a[normalize-space()='Click to see it live on your store.']";
	/* xpath */public static final String SPINWHEEL_MESSAGE = "//span[contains(text(),'Message')]";
	
	/* xpath */public static final String LAND_ON_CART_INPUT = "//span[contains(text(),'Events for Displaying Storefront opt-in widget:')]/following::div[8]//input";
	
	/* xpath */public static final String STOREFRONT_LANGUAGE = "//span[contains(text(),'English')]";
	/* xpath */public static final String STOREFRONT_ALLOPTIONS = "(//span[@class='Polaris-Choice__Label'])";
	/* xpath */public static final String LAND_ON_CART = "//span[contains(text(),'Landing on Cart page')]";
	
	/* xpath */public static final String SHOW_CHT_BTN_WHEN_OFFLINE_CHKBOX = "(//span[@class='Polaris-Checkbox__Backdrop'])[1]";
	/* xpath */ public static final String CALLOUT_CARD_DELAY = "//label[contains(text(),'Callout Card Message Delay')]/following::input[1]";
	/* xpath */ public static final String CALLOUT_TEXTBOX_FIELD ="//label[contains(text(),'Callout Card Message Text')]/following::input[1]";
	
	/* xpath */public static final String OPTINVIACHAT_WIDGET_HEADERCOLOR_TEXTFIELD = "(//input[@id='optin-solid'])/following::input[3]";
	/* xpath */public static final String OPTINVIACHAT_WIDGET_DESCRIPTIONCOLOR_TEXTFIELD = "(//input[@id='optin-solid'])/following::input[4]";
	/* xpath */public static final String OPTIN_CHATWIDGET_HEADING_TEXT_INPUT = "//label[contains(text(),'Text to be displayed on')]/following::div[1]//input";
	/* xpath */public static final String OPTIN_CHATWIDGET_HELP_TEXT_INPUT = "//label[contains(text(),'Help text to be')]/following::div[1]//input";
	
	/* xpath */ public static final String MINIMUMDISCOUNTCODEERROR = "(//div[contains(text(),'Minimum 4 slots are required')])[1]";
	/* xpath */public static final String CHAT_WIDGET_TAB = "//button[@id='Chat Widgets']";
	
	public static final String PHONE_NUMBER = "9894198941";
	public static final String INDIA_CODE = "91";
	public static final String FIRST_DISPLAY_VALUE = "1234";
	public static final String SECOND_DISPLAY_VALUE = "564";
	public static final String THIRD_DISPLAY_VALUE = "159";
	public static final String FOURTH_DISPLAY_VALUE = "357";
//	public static final String BETTER_LUCK_VALUE = "Better luck next time";
	public static final String MINIMUMVALUE = "4";
	public static final String OFFSETVALUE = "100";
//	public static final String MINIMUMDISCOUNTCODEERRORVALUE = "You have reached minimum limit of 4";
	public static final String MAXIMUMDISCOUNTCODEERRORVALUE = "You have reached maximum limit of 12";
	public static final String MINIMUMDISCOUNTCODEFIRSTERRORVALUE = "Invalid wheel config values";
	public static final String MAXIMUMDISCOUNTCODEFIRSTERRORVALUE = "Invalid Text Length";
	public static final String FIRSTDISCOUNDCODETEXTINVALIDVALUE = "hellohellohellohellohellohello";
	public static final String STOREURL = "https://qaautomationstore2.myshopify.com/";

	public static final String DEFAULT_HEADINGTXT = "Hi there!";
	public static final String DEFAULT_HELPTEXT = "We are here to help. Chat with us on WhatsApp for any queries. Please provide your whatsapp number to start the chat";
	public static final String HEADINGTXT = "Helloguys";
	public static final String HELPTEXT = "Give us your number please..";
	public static final String REDCOLOR = "#ea2020";
	public static final String GREENCOLOR = "#20802C";
	public static final String DEFAULTHEADRCOLOR = "#FFFFFF";
	public static final String COLOR = "#3ab9dc";
	public static final String TUTORIAL_TITLE = "Why customer opt-in is important?";
	public static final String BETTER_LUCK_VALUE = "We’ll send the discount code on your WhatsApp number";
	
	public static final String MINIMUMDISCOUNTCODEERRORVALUE = "Minimum 4 slots are required";
}