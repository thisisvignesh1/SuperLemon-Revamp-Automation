package locators;

public class WixStoreLocators {

	/* xpath */public static final String PRODUCT = "//h3[contains(text(),'Tote bag')]";
	/* xpath */public static final String BUY_NOW = "//span[contains(text(),'Buy Now')]";
	/// * xpath */public static final String CHECKOUT_PAGE =
	/// "//h2[contains(text(),'Shipping details')]";
	/* xpath */ public static final String EMAIL = "(//input[@type='email'])[1]";
	/* xpath */public static final String FIRST_NAME = "//input[@aria-label='First name']";
	/* xpath */public static final String LAST_NAME = "//input[@aria-label='Last name']";
	/* xpath */public static final String PHONE = "//input[@aria-label='Phone']";
	/// * xpath */public static final String COUNTRY = "//input[@value='India']";
	///* xpath */public static final String ADDRESS = "(//input[@class='saiCKFU'])[2]";
	/* xpath */public static final String CITY = "//input[@aria-label='City']";
	/* xpath */public static final String STATE = "(//input[@class='saiCKFU'])[3]";
	/* xpath */public static final String PINCODE = "//input[@aria-label='Zip / Postal code']";
	/* xpath */public static final String CONTINUE = "//div[@class='shlGd4v']";
	/* xpath */public static final String DELIVERY_CONTINUE = "//div[@class='sMxJiDl']";
	/* xpath */public static final String COD = "(//div[@class='wm0agN'])[2]";
	/* xpath */public static final String PAYMENT_CONTINUE = "//div[@class='shlGd4v']";
	/* xpath */public static final String CHECKOUT_OPTIN = "//input[@class='sNqZH7Z']";
	/* xpath */public static final String PLACE_ORDER = "(//button[@data-hook='place-order-button'])[1]";
	///* css */ public static final String THANK_YOU = "h1.Lurwtr";
	/* xpath */public static final String ORDER_SUMMARY = "//div[@class='s_9Myu2']";
	/// * xpath */public static final String CHAT_WIDGET =
	/// "(//div[@class='wa-chat-button-cta-text'])[2]";
	/* xpath */public static final String CHAT_WIDGET = "(//div[text()='Click on Widget'])[1]";
	/* xpath */public static final String ADD_TO_CART = "//span[contains(text(),'Add to Cart')]";

	/* XPath */public static final String CHECKOUT_PAGE = "//h2[contains(text(),'Delivery details')]";
	/* XPath */public static final String CARD = "//button[@method='card']";
	/* CSS */ public static final String CARD_NUM = "#card_number";
	
	/* CSS */ public static final String CARD_EXPIRY = "#card_expiry";
	/* CSS */ public static final String CARD_NAME = "#card_name";
	/* CSS */ public static final String CARD_CVV = "#card_cvv";
	/* XPath */ public static final String INR = "(//label[@class='native-radio-control svelte-be55ba'])[2]"; //label[@for='id_MG0tJEgg1ejB4o']";
	///* CSS */ public static final String PAY_NOW = "#redesign-v15-cta";
	/* XPath */ public static final String PAY_NOW = "(//button[@class='svelte-13mgn3i'])[2]";
	/* XPath */ public static final String SAVE_CARD = "//button[@class='cta-button btn svelte-1wnplxx']";
	/* XPath */ public static final String RAZOR_SUCCESS = "//button[@class='success']";
	/* XPath */ public static final String THANK_YOU = "//span[@data-hook='HeaderDataHook.title']";
	
	///* xpath */public static final String ADDRESS = "(//input[@class='sIrVfKT'])[2]";
	
	/* xpath */public static final String ADDRESS = "//span[contains(text(),'Address')]/following::input[1]";
	/* xpath */public static final String VIEW_CART = "//a[@id='widget-view-cart-button']";
	/* xpath */public static final String OPTIN_POPUP_CLOSE_ICON = "//img[@class='wa-optin-widget-close-img']";
	/* xpath */public static final String CHECKOUT = "//span[@class='sC5KCfT']";
	
	/* xpath */public static final String PHONE_NUMBER_FIELD = "//input[@type='tel']";
	/* XPATH */public static final String CONFIRM_BTN = "//button[@id='wa-optin-widget-confirm-btn']";
	
	public static final String EMAIL_ID = "autouser@gmail.com";
	public static final String CARD_NUMBER = "4242 4242 4242 4242";
	
}
