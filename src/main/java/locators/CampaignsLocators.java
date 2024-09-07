package locators;

public class CampaignsLocators {
	/* XPATH */public static final String MARKETCAMPAIGNS_TAB = "(//span[.='Campaigns'])";
	/* XPATH */public static final String CREATEMARKETCAMPAIGNS_BTN = "(//span[contains(text(),'Create new Campaign')])[1]";
	/* XPATH */public static final String CAMPAIGNSNAME_INPUT = "//span[.='Campaign Name']/../following::input[1]";
	/* XPATH */public static final String SELECTTEMPLATE = "//span[contains(text(),'Select Template')]";
	/* XPATH */public static final String RECEIPTENTSNAME = "#file-upload";
	/* XPATH */public static final String INVALIDCAMPAIGNSNAME_INPUT = "//div[contains(text(),'Campaign name should be less than 50 characters')]";
	/* xpath */public static final String ERRORPOP = "(//div[@class='Polaris-Frame-Toast Polaris-Frame-Toast--error'])[1]";
	/* xpath */public static final String SUBMITBTN = "//span[contains(text(),'Submit')]";
	/* xpath */public static final String CANCELBTN = "//span[contains(text(),'Cancel')]";
	/* xpath */public static final String CHOOSETEMPLATEBTN = "//span[contains(text(),'Choose Template')]";
	/* xpath */public static final String DOWNLOADEXCELBUTTON = "//span[normalize-space()='Recipients']/following::a[1]";
	/* xpath */public static final String DOWNLOADCSVBUTTON = "//span[normalize-space()='Recipients']/following::a[2]";
	/* xpath */public static final String TEMPLATENAME = "//span[normalize-space()='Message']/../following::input[1]";
	/* CSS */public static final String PREVIEWTEMPLATE = ".whatsapp-message.received";
	/* XPATH */public static final String CAMPAIGNSNAME_INPUTERROR = "//span[.='Campaign Name']/../following::div[8]";
	/* XPATH */public static final String FIRSTCREATEDCAMPAIGNS = "(//tr)[2]";
	/* CSS */public static final String GUIDE = "img[title='Guide']";
	/* XPATH */public static final String NEXTSTEPBTN = "//span[contains(text(),'Next step')]";
	/* XPATH */public static final String GUIDECOMPLETEDBTN = "//span[contains(text(),'Guide Completed')]";
	/* XPATH */public static final String SECONDTEMPLATEBTN = "//tbody/tr[2]";
	/* XPATH */public static final String STATUS = "//th[normalize-space()='Status']/../following::td[2]";
	/* CSS */public static final String FROMFILE = "label[for='From file']";
	/* XPATH */public static final String LAST_TEMPLATE_VALUE = "(//tr)[last()]/th";
	/* XPATH */public static final String AUTOMATION_TESTING_SEGEMENT_DROPDOWN = "//span[text()='From file']/following::select[1]";
//	/* XPATH */public static final String FIRSTTEMPLATEBTN = "//tbody/tr[1]";
	/* XPATH */public static final String MESSAGETEMPLATENAME = "//input[@placeholder='Template name']";

	/* XPATH */public static final String CONFIRMATION_CHECKBOX = "//span[text()='Recipients']/following::input[4]";
	/* XPATH */public static final String CONFIRMATION_CHECKBOX_TXT = "//span[contains(text(),'I confirm that I have collected customer opt-in fo')]";
//	/* XPATH */public static final String TEMPLATESELECT = "//tbody/tr/th/label";
	/* XPATH */public static final String CAMPAIGNTEMPLATE_DEFAULT_FILTER = "//span[contains(text(),'Default')]";
	/* XPATH */public static final String CAMPAIGNTEMPLATE_CREATED_FILTER = "//span[contains(text(),'Created by me')]";
	/* XPATH */public static final String CAMPAIGNTEMPLATE_DEFAULT_FILTER_INPUT = "(//input[@name='template-select'])[2]";
	/* XPATH */public static final String CAMPAIGNTEMPLATE_CREATED_FILTER_INPUT = "(//input[@name='template-select'])[3]";
	/* XPATH */public static final String FIRST_RUNCAMPAIGN_BTN = "(//span[contains(text(),'Run Campaign')])[1]";
	/* XPATH */public static final String FIRSTROW_STATUS = "//tr[1]/descendant::p[3]";
	/* XPATH */public static final String FIRSTROW_RECEPIENTSCOUNT = "//tr[1]/descendant::p[4]";
	/* XPATH */public static final String FIRSTROW_OPTINCOUNT = "//tr[1]/descendant::p[5]";
	/* XPATH */public static final String FIRSTROW_DELIVEREDCOUNT = "//tr[1]/descendant::p[6]";
	/* XPATH */public static final String FIRSTROW_OPTINSOURCE = "//tr[1]/descendant::p[6]";
	/* CSS */public static final String LOADSPINNER = ".loader-spinner";
	/* XPATH */public static final String LEARNMORE = "//span[@class='Polaris-Checkbox__Backdrop']/following::span[5]";
	/* XPATH */public static final String VIEW_CAMPAIGN_BTN = "//span[contains(text(),'View Campaigns')]";
	/* XPATH */public static final String DOWNLOADREPORT = "((//tr[1])/following::span[1])[1]";
	/* CSS */public static final String ERRORPOPUP = ".errorModalContent";
	/* XPATH */public static final String ERRORPOPUP_LEARNMORE = "(//span[@class='Polaris-Link__IconLockup'])[3]";

	/* XPath */ public static final String TUTORIAL_BTN = "//span[contains(text(),'Tutorial video')]";
	/* XPath */ public static final String WHATSAPPCAMPAIGN_TUTORIAL = "//span[text()='Why WhatsApp campaign is important for your business?']";
	/* XPath */ public static final String CREATECAMPAIGN_TUTORIAL = "//span[text()='How to create a campaign?']";
	/* CSS */ public static final String WHATSAPPCAMPAIGN_IFRAME = "#ytplayer";
	/* XPath */ public static final String TUTORIAL_CLOSEBTN = "//button[@class='Polaris-Modal-CloseButton']";
	/* CSS public static final String CAMPAIGN_TEMPLATES = "#custom-templates"; */
	/* XPath */ public static final String CREATE_TEMPLATES = "//span[text()='Create Templates']";
	/* XPath */ public static final String TEMPLATE_TUTORIAL = "//span[text()='What is WhatsApp template?']";
	/* XPath */ public static final String CREATETEMPLATE_TUTORIAL = "//span[text()='How to create a WhatsApp template?']";
	/* xpath */public static final String CAMPAIGN_TEMPLATES = "//button[@id='custom-templates']";
	/* css */public static final String GUIDE_SCREENSHOT = "img[alt='create_template_img']";
	/* css */public static final String GUIDE_TEMPLATESCREENSHOT = "img[alt='template_status_img']";
	/* XPATH */public static final String VIDEOTEXT = "//span[contains(text(),'Supported media type: .mp4 with a maximum size of ')]";
	/* XPATH */public static final String IMAGETEXT = "//span[contains(text(),'Supported media type: .jpeg and .png with a maximum size of 2MB')]";
	/* XPATH */public static final String DOCS_TEXT = "//span[contains(text(),'Supported media type: .pdf with a maximum size of 2MB')]";

	/* XPATH */public static final String MEDIA_FILE = "//input[@id='uploadMediaFileRunCampg']";
	/* XPATH */public static final String ERROR_MESSAGE = "//div[@class='Polaris-Frame-ToastManager']"
			+ "//div[@class='Polaris-Frame-ToastManager__ToastWrapper Polaris-Frame-ToastManager--toastWrapperEnterDone']"
			+ "//div[@class='Polaris-Frame-Toast Polaris-Frame-Toast--error']";
	/* XPATH */public static final String CANCEL_POPUP = "//button[@class='Polaris-Frame-Toast__CloseButton']//span[@class='Polaris-Icon']//*[name()='svg']";
	/* XPATH */public static final String MEDIA_FILE_SELECTED_IMAGE = "//label[normalize-space()='Upload.png']";
	/* XPATH */public static final String MEDIA_FILE_SELECTED_VIDEO = "//label[normalize-space()='videosample.mp4']";
	/* XPATH */public static final String MEDIA_FILE_SELECTED_DOCS = "//label[normalize-space()='sample.pdf']";
	
	/* XPATH */public static final String START_GUIDE = "//button[normalize-space()='Start Guide']";
	/* XPATH */public static final String NEXT_STEP = "//span[contains(text(),'Next step')]";
	/* XPATH */public static final String GUIDE_IMG = "//div[@class='Polaris-TextContainer']//div//img";
	/* XPATH */public static final String GUIDE_COMPLETED = "//span[contains(text(),'Guide Completed')]";
	
	/* XPATH */public static final String FIRSTTEMPLATEBTN = "//div[contains(@class,'chat_templatesSection  chat__selectTemplateHeight')]/div[last()]//div//strong";
	/* XPATH */public static final String CHECKBOX = "(//span[@class='Polaris-Checkbox__Backdrop'])[1]";
	
	/* XPATH */public static final String TEMPLATESELECT = "//div[@class='template']//p[1]";
	/* XPATH */public static final String CLICKVIDEOTEMPLATE = "//div[@class='template']//p[contains(text(), 'offer789')]/following-sibling::div//button";
//	/* XPATH */public static final String CLICKIMAGETEMPLATE = "//div[@class='template']//p[contains(text(), 'order_template')]/following-sibling::div//button";
	/* XPATH */public static final String CLICKDOCUMENTTEMPLATE = "//div[@class='template']//p[contains(text(), 'document_template')]/following-sibling::div//button";
	
	/* XPATH */public static final String ALL_CATEGORY_TEMPLATE = "(//span[@class='Polaris-RadioButton__Backdrop'])[3]";
	/* XPATH */public static final String CLICKIMAGETEMPLATE = "//div[@class='template']//p[contains(text(), 'order_update')]/following-sibling::div//button";
	/* XPATH */public static final String CLICKSPECIALOFFERTEMPLATE = "//div[@class='template']//p[contains(text(), 'special_offer')]/following-sibling::div//button";
	
	
	public static final String CAMPAIGNSINPUTPOPUP = "Campaign name can not be empty";
	public static final String SELECTTEMPLATEPOPUP = "Please select a template to proceed";
	public static final String RECEPTENTSINPUTPOPUP = "Receipients file is not found";
	public static final String CAMPAIGNSINPUTVALUE = "Campaigns";
	public static final String CAMPAIGNSFILENAMEVALUE = "Campaigndata.xlsx";
	public static final String CAMPAIGNSINVALIDFILENAMEVALUE = "GetClickCount.json";
	public static final String CAMPAIGNSINVALIDINPUTVALUE = "CampaignCampaignCampaignCampaignCampaignCampaignCampaign";
//	public static final String SEGMENTDROPDOWNVALUE = "AutomationTesting";
	public static final String CAMPAIGNTEMPLATE = "specialoffer1";
	public static final String CAMPAIGNSFILENAME = "campaigndatasheet.xlsx";
	public static final String MESSAGETEMPLATE_VALUE = "end_of_season_v1";
	public static final String CHOOSETEMPLATENAME = "ab_cart";
	public static final String MULTIPLECAMPAIGNSFILENAMEVALUE = "MultiCampaign.xlsx";
	public static final String SELECTCONFIRMBTN_POPUP = "Please give us your consent by checking the checkbox.";
	public static final String MAXIMUMCAMPAIGNSFILENAME = "Maximumcount_Campaign.xlsx";
	public static final String CAMPAIGNTEMPLATE1 = "special_offer";
	public static final String CAMPAIGNTEMPLATEVIDEO = "offer789";
//	public static final String CAMPAIGN_TEMPLATE_IMAGE = "offer666";
	public static final String CAMPAIGN_TEMPLATE_PHOTO = "Upload.png";
	public static final String CAMPAIGN_TEMPLATE_VIDEO_LARGESIZE = "sample_video.mp4";
	public static final String CAMPAIGN_TEMPLATE_IMAGE_LARGESIZE = "Greaterthan2Mb.jpg";
	public static final String VIDEO_SAMPLE = "videosample.mp4";
//	public static final String CAMPAIGN_TEMPLATE_DOCS = "offer456";
	public static final String DOCS_SAMPLE = "sample.pdf";
	public static final String CAMPAIGN_TEMPLATE_DOCS_LARGESIZE = "Document_with_largesize.pdf";
	
	public static final String SEGMENTDROPDOWNVALUE = "aa";
	public static final String CAMPAIGN_TEMPLATE_DOCS = "document_template";
	public static final String CAMPAIGN_TEMPLATE_IMAGE = "order_update";
	
	
}