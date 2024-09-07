package commonFunctions;

import java.util.Properties;

import org.apache.log4j.Logger;

import locators.ChatBotLocators;
import utils.DriverBase;
import utils.DriverBase.ElementType;

public class ChatBotPage {
	private static Logger log = Logger.getLogger(AutomationPage.class.getName());
	private static boolean flag;
	private static String propertyFilePath;
	private static String url;
	public static Properties prop = new Properties();
	private static DriverBase webDB = new DriverBase();

	public static boolean verifyRequestStatus() throws InterruptedException {
		flag = webDB.waitForElement(ChatBotLocators.REQUEST_SUBMITTED, ElementType.Xpath);
		if (flag) {
			String temp = webDB.getText(ChatBotLocators.REQUEST_SUBMITTED, ElementType.Xpath);
			flag = temp.contains(ChatBotLocators.REQUEST_TEXT);
		}
		return flag;

	}

}
