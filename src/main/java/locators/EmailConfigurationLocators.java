package locators;

public class EmailConfigurationLocators {
    /*xpath*/public static final String CONFIGURATION_TAB="(//span[.='Email'])/../following::span[2]";
    /*xpath*/public static final String CUSTOM_BRANDNAME_FIELD="//input[@name='brand_name']";
    /*xpath*/public static final String SUPPORT_EMAIL_FIELD="//input[@name='email']";
    /*xpath*/public static final String ERROR_TEXT="//div[contains(@id,'Error')]";
    /*path*/public static final String INVALID_ERROR_SUPPORT_MAIL_ON_SAVE="//div[@class='Polaris-Frame-Toast Polaris-Frame-Toast--error']";

}
