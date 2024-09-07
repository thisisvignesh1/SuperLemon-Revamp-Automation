package locators;

public class ShopifyStoreLocators {
	/* xpath */public static final String STORE_PASS = "//*[@type='password']";
	/* xpath */public static final String STORE_PASS_ENTER = "//button[@type='submit']";
	/* xpath */public static final String HOMEPAGE_SHARE_WIDGET = "//p[contains(@class,'share')]";
	/* css */ public static final String OPTIN_WIDGET_POPUP = ".wa-optin-widget-right-sec";
	/* xpath */ public static final String CART_ICON_HOMEPAGE = "//*[contains(@class,'cart ')]";
	/* xpath */public static final String CHAT_WIDGET_LEFT = "//div[contains(@class,'pos-left')]";
	/* xpath */public static final String CSTMZED_CHAT_WIDGET_BTN_COLOR = "//div[@style='background: #111111;']";
	/* xpath */public static final String CALLOUT_CONTAINER = "//div[@class='callout-text-container']";
	/* xpath */public static final String SHARE_WIDGET_POS = "//p[contains(text(),'Share Button')]";
///*xpath*/public static final String CSTMZED_CHAT_WIDGET_BTN_COLOR="//div[@style='background: #231a1a;']";
	/* css */ public static final String STORE_OFFLINE_MSG_TEXT = "div.wa-chat-bubble-profile-name";
	/* CSS */public static final String GREETINGS_HEADER_TITLE = "div.wa-chat-bubble-header-title";
	/* CSS */public static final String GREETINGS_DESC_TEXT = "div.wa-chat-bubble-header-desc";
	/* CSS */public static final String GREETINGS_DESC_COLOR = "//div[@style='color: rgb(49, 22, 16);']";
	/* xpath */public static final String EMPTY_CART_ICON = "//*[@class='icon icon-cart-empty']";
	/* id */public static final String CONFIRM_BUTTON_OPTIN_POPUP = "wa-optin-widget-confirm-btn";
	///* id */public static final String OPTIN_POPUP_PHONE_FIELD = "wa-optin-phone-number";
	///* id */public static final String OPTIN_POPUP_COUNTRY_CODE_FIELD = "wa-optin-country-code";
	/* id */public static final String THANK_U_PAGE_OPTIN_COUNTRY_CODE = "wa-optin-widget-thankyou-countryCode";
	/* id */public static final String THANK_U_PAGE_PHONE_FIELD = "wa-optin-widget-thankyou-phone";
	/* linkText */public static final String CATALOG_TAB = "Catalog";
	/* xpath */public static final String ITEM_SELECT = "//a[@class='full-unstyled-link']";
	/* xpath */public static final String BUY_NOW = "//button[contains(text(),'Buy it now')]";
	/* CSS */public static final String OPTIN_POPUP_CLOSE_ICON = ".wa-optin-widget-close-btn";
	/* xpath */public static final String ADDRESS_SELECT_FROM_SUGGESTIONS = "(//div[contains(@class,'combo-box__conten')])[1]";
	/* xpath */public static final String CARD_NUMBER_PAYMENTS = "//input[@placeholder='Card number']";
	/* xpath */public static final String CARD_CVV = "//input[@placeholder='Security code']";
	/* id */public static final String OPTIN_PHONE_THANKYOU_PAGE = "wa-optin-widget-thankyou-phone";
	/* id */public static final String OTIN_COUNTRY_CODE_THANKU_PAGE = "wa-optin-widget-thankyou-countryCode";
	/* id */public static final String OPTIN_CONFIRM_THANKU_PAGE = "wa-optin-widget-thankyou-confirm-btn";
	/* xpath */public static final String NAME_IN_ADDRESS = "//address[@class='address']";
	/* id */public static final String SHOPIFY_EMAIL = "//input[@id='account_email']";
	/* xpath */public static final String SHOPIFY_PASSWORD = "//input[@label='Password']";
	/* xpath */public static final String SHOPIFY_NEXT_BTN = "//button[contains(text(),'Next')]";
	/* xpath */public static final String SHOPIFY_LOGIN_BTN = "//button[contains(text(),'Log in')]";
	/* xpath */public static final String USERNAME_LABEL = "//p[contains(text(),'Samriddha Banik')]";
	/* xpath */public static final String SUCCESS_MSG_PHONENO = "//p[@id='optin-confirmed-success-true']/b";
	/* xpath */public static final String THANKYOU_MSG = "//h2[@id='main-header']";
	/* xpath */public static final String ORDER_CONFIRMED_MESSAGE = "//h2[@class='heading-2 os-step__title']";
	/* xpath */public static final String SHIPPING_ADDRESS_PHONE = "//input[@id='checkout_shipping_address_phone']";
	/* xpath */public static final String OPTIN_BUTTON_THANKYOU_PAGE = "wa-optin-widget-thankyou-confirm-btn";
	/* xpath */public static final String FIRST_CHAT_AGENT = "(//div[@class='wa-chat-bubble-profile-name'])[1]";
	/* xpath */public static final String ADD_TO_CART = "//button[contains(@class,'button--secondary')]";
	/* xpath */public static final String CHECKOUT_BUTTON = "//button[@class='button button--primary button--full-width']";
	/* xpath */public static final String POISTORECATALOG_TAB = "(//a[contains(text(),'Catalog')])[2]";
	/* xpath */public static final String ORDERNO = ".os-order-number";

	/* xpath */public static final String OPTIN_CONFIRM_SUCCESS_MSG = "//h2[@class='heading-2 os-step__title']";
	/// * xpath */public static final String CHECKOUT_OPTIN_CHECKBOX =
	/// "//input[@id='marketing_opt_in']";
	/* xpath */public static final String CONTINUE_BTN = "(//button[@type='submit'])[1]";
	/* xpath */public static final String CARD_EXPIRY = "//input[@data-current-field='expiry']";

	/* ID */public static final String OPTIN_NUMBER_INPUT = "wa-optin-widget-thankyou-phone";
	/* ID */public static final String OPTIN_NUMBER_COUNTRYCODE = "wa-optin-widget-thankyou-countryCode";
	///* xpath */public static final String CHECKOUT_EMAIL_PHONE = "(//h2[contains(text(),'Contact information')])/following::input[1]";

	/* xpath */public static final String COUNTRY_DROPDOWN = "//select[@autocomplete='shipping country']";
	/* xpath */public static final String LAST_NAME = "//select[@autocomplete='shipping country']/following::input[2]";
	/* xpath */public static final String FIRST_NAME = "//select[@autocomplete='shipping country']/following::input[1]";
	/* xpath */public static final String SHIPPING_ADDRESS_CITY = "//select[@autocomplete='shipping country']/following::input[5]";
	/* xpath */public static final String SHIPPINGADDRESS = "//h2[contains(text(),'Shipping address')]";
	/* xpath */public static final String SHIPPING_ADDRESS_PINCODE = "//h2[contains(text(),'Shipping address')]/following::select[2]/following::input[1]";
	/* xpath */public static final String SHIPPINGMETHOD = "//h2[contains(text(),'Shipping method')]";
	/* CSS */public static final String ADDRESS_LINE_1 = "input[autocomplete='shipping address-line1'][autocorrect='off']";
	/* xpath */public static final String NAME_ON_CARD = "//input[@id='name' and @data-current-field='name']";
	/* xpath */public static final String CREDITCARDTEXT = "//h3[contains(text(),'Credit card')]";
	/* xpath */public static final String PAYNOW = "//h2[contains(text(),'Billing address')]/following::button[1]";
	/* xpath */public static final String SHIPPING_ADDRESS_STATE = "//h2[contains(text(),'Shipping address')]/following::select[2]";
	///* xpath */public static final String CONTACTNOCOUNTRYREGION = "(//h2[contains(text(),'Contact information')])/following::select[1]";

	/* xpath */public static final String HOMEPAGE_CHAT_WIDGET = "(//*[contains(@class,'chat')])[1]";
//	/* xpath */public static final String CHATWIDGET_TEXT = "//div[@class='wa-chat-button-cta-text']";
	/* xpath */public static final String CSTMZED_CHAT_WIDGET_TEXT_COLOR = "//div[starts-with(@style,'color: #231')]";

	/* xpath */public static final String CONFIRMBUTTON = "//button[@class='confirmBtn']";
	/* xpath */public static final String POIITEM_SELECT = "(//div[@class='product-card__name'])[1]";

	/* CSS */public static final String DESIGN_SECTION = ".Polaris-DisplayText.Polaris-DisplayText--sizeSmall";
	/* CSS */public static final String GREETINGS_CHATBOX = "#wa-greeting-bubble";
	/* CSS */public static final String OPTIN_POPUP = ".wa-optin-widget-container.wa-optin-widget-z-index";
	/* CSS */public static final String ADDTOCART_NOTIFICATION = "#cart-notification";
	/* CSS */public static final String ADDTOCART_CHECKOUT_BTN = ".button.button--primary.button--full-width";
	/* CSS */public static final String CART_CHECKOUT_BTN = ".cart__checkout-button.button";
	/* CSS */public static final String CART_ICON = ".icon.icon-cart";

	/* CSS */public static final String OPTINVIA_CHATWIDGET_BACKROUND = ".wa-chat-bubble-header-common";
	/* CSS */public static final String OPTINVIA_CHATWIDGET_HEADER = ".wa-chat-bubble-header-title";
	/* CSS */public static final String OPTINVIA_CHATWIDGET_DESC = ".wa-chat-bubble-header-desc";

	/* xpath */public static final String CONTACT = "//h2[@id='step-section-primary-header']";
	/* xpath */public static final String EMAIL_OR_PHONE = "//input[@placeholder='Email or mobile phone number']";

	/* xpath */public static final String AGENT = "//div[@class='wa-chat-bubble-cs-profile']";
	/* xpath */public static final String CHAT_PHONE = "//input[@class='splmn-phone']";
	/* CSS */public static final String CHAT_ENTER = "#wa-optin-clickButton";
	/* xpath */public static final String CHECKOUT_OPTIN_CHECKBOX = "//input[@id='sms_marketing_opt_in']";
	
	/* xpath */public static final String SPIN_WHEEL_STORE = "//div[@id='spinwheel-btn-root']//img";
	/* xpath */public static final String SPIN_WHEEL = "(//button[normalize-space()='Spin the Wheel'])[1]";
	/* xpath */public static final String SPIN_WHEEL_TEXT = "//p[@class='game-status-subtxt']";
	/* xpath */public static final String SPINWHEEL_COUNTRY_CODE = "(//input[@id='wa-optin-country-code'])[1]";	
	
	/* xpath */public static final String OPTIN_COUNTRYCODE = "//div[@class='iti__selected-dial-code']"; 
	///* xpath */public static final String OPTIN_COUNTRYCODE = "//ul[@id='iti-0__country-listbox']";
	/* xpath */public static final String OPTIN_POPUP_PHONE_FIELD = "//input[@class='splmn-phone-storefront']";
	
//	/* className */public static final String OPTIN_POPUP_COUNTRY_CODE_FIELD = "iti__selected-flag";
	/* xpath */public static final String CHECKOUT_EMAIL_PHONE = "(//h2[contains(text(),'Contact')])/following::input[1]";
	
	/* xpath */public static final String CONTACTNOCOUNTRYREGION = "(//h2[contains(text(),'Contact')])/following::select[1]";
	/* xpath */public static final String CHATWIDGET_TEXT = "(//div[@id='wa-chat-btn-root'])[1]";
	
	/* xpath */public static final String OPTIN_POPUP_COUNTRY_CODE_FIELD = "//div[@class='iti__selected-flag']";
	
//	/* xpath */public static final String OPTINVIA_CHATWIDGET_BACKROUND = "//div[@class='wa-chat-bubble-header-common wa-chat-bubble-header-302 wavy']";
//	/* xpath */public static final String FIRST_CHAT_AGENT = "(//div[@class='wa-chat-bubble-cs-profile'])[1]";
}
