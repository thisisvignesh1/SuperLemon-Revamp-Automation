package commonFunctions;

import java.util.logging.Logger;

import locators.CampaignsLocators;
import locators.SharedTeamInboxLocators;
import utils.DriverBase;
import utils.DriverBase.ElementType;

public class SharedTeamInboxPage {
	private static Logger log = Logger.getLogger(SharedTeamInboxPage.class.getName());
	private static DriverBase webDB = new DriverBase();
	private static boolean flag;

	public static boolean verifySharedTeamInboxTutorial() throws InterruptedException {
		flag = webDB.waitForElement(CampaignsLocators.TUTORIAL_BTN, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(CampaignsLocators.TUTORIAL_BTN, ElementType.Xpath);
			flag = webDB.waitForElement(SharedTeamInboxLocators.SHAREDTEAM_TUTORIAL, ElementType.Xpath);
			if (flag) {
				Thread.sleep(1000);
				webDB.clickAnElement(SharedTeamInboxLocators.SHAREDTEAM_TUTORIAL, ElementType.Xpath);
				Thread.sleep(2000);
				flag = webDB.waitForElement(CampaignsLocators.WHATSAPPCAMPAIGN_IFRAME, ElementType.CssSelector);
				if (flag) {
					flag = webDB.waitForElement(CampaignsLocators.TUTORIAL_CLOSEBTN, ElementType.Xpath);
					if (flag) {
						Thread.sleep(1000);
						webDB.clickAnElement(CampaignsLocators.TUTORIAL_CLOSEBTN, ElementType.Xpath);
						flag = webDB.waitForElement(CampaignsLocators.TUTORIAL_BTN, ElementType.Xpath);
						if (flag) {
							webDB.javaScriptClickAnElement(CampaignsLocators.TUTORIAL_BTN, ElementType.Xpath);
							flag = webDB.waitForElement(SharedTeamInboxLocators.SHAREDTEAM_CONFIGURETUTORIAL,
									ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(SharedTeamInboxLocators.SHAREDTEAM_CONFIGURETUTORIAL,
										ElementType.Xpath);
								Thread.sleep(2000);
								flag = webDB.waitForElement(CampaignsLocators.WHATSAPPCAMPAIGN_IFRAME,
										ElementType.CssSelector);
								if (flag) {
									flag = webDB.waitForElement(CampaignsLocators.TUTORIAL_CLOSEBTN,
											ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(CampaignsLocators.TUTORIAL_CLOSEBTN,
												ElementType.Xpath);
										flag = webDB.waitForElement(CampaignsLocators.TUTORIAL_BTN,
												ElementType.Xpath);
										if (flag) {
											System.out.println("Verified tutorial videos in Shared Team Inbox");
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return flag;
	}

}
