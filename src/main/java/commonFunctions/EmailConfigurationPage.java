package commonFunctions;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import locators.CommonLocators;
import locators.EmailConfigurationLocators;
import locators.EmailTemplatesLocators;
import locators.WidgetLocators;
import utils.DriverBase;

@SuppressWarnings("static-access")
public class EmailConfigurationPage {
	private static Logger log = Logger.getLogger(WidgetPage.class);
	private static boolean flag;
	public static Properties prop = new Properties();
	private static DriverBase webDB = new DriverBase();;
	private static String text;
	@SuppressWarnings("unused")
	private static Actions actions = new Actions(webDB.driver);
	private static List<WebElement> elements;
	@SuppressWarnings("unused")
	private static CommonFunctions HeaderText = new CommonFunctions();
	private static WebDriverWait wait = new WebDriverWait(webDB.driver, 10);
	@SuppressWarnings("unused")
	private static List<WebElement> radioGeneric = webDB.driver
			.findElements(By.xpath(CommonLocators.RADIO_ATTRIBUTE_GENERIC));
	@SuppressWarnings("unused")
	private static List<WebElement> radioClickable = webDB.driver.findElements(By.xpath(CommonLocators.RADIO_GENERIC));
	@SuppressWarnings("unused")
	private static List<WebElement> toggleButton = webDB.driver
			.findElements(By.xpath(WidgetLocators.CHECKBOX_GENERIC));
	@SuppressWarnings("unused")
	private static List<WebElement> checkboxClickable = webDB.driver
			.findElements(By.xpath(CommonLocators.CHECKBOX_COMMON));

	public static boolean verifyEmptySupportEmailOnSave() throws InterruptedException {
		String emailErrorMessageText = "Enter a valid email address";
		String validSupportEmailMessageOnSave = "A valid email is neccessary";
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(CommonLocators.CHECKBOX_COMMON)));
		selectIncludeSupportEmailInEmailTemplates();
		Thread.sleep(3000);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(EmailConfigurationLocators.SUPPORT_EMAIL_FIELD)));
		webDB.driver.findElement(By.xpath(EmailConfigurationLocators.SUPPORT_EMAIL_FIELD))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		flag = webDB.isElementDisplayed(EmailConfigurationLocators.ERROR_TEXT, DriverBase.ElementType.Xpath);
		if (flag) {
			log.info("Email empty error message is displayed");
			log.info(webDB.driver.findElement(By.xpath(EmailConfigurationLocators.ERROR_TEXT)).getText());
			flag = webDB.driver.findElement(By.xpath(EmailConfigurationLocators.ERROR_TEXT)).getText()
					.equalsIgnoreCase(emailErrorMessageText);
		}
		if (flag) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CommonLocators.SAVE_SETTINGS)));
			webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, DriverBase.ElementType.ClassName.Xpath);
			flag = webDB.isElementDisplayed(EmailConfigurationLocators.INVALID_ERROR_SUPPORT_MAIL_ON_SAVE,
					DriverBase.ElementType.Xpath);
			if (flag) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath(EmailConfigurationLocators.INVALID_ERROR_SUPPORT_MAIL_ON_SAVE)));
				log.info(webDB.driver
						.findElement(By.xpath(EmailConfigurationLocators.INVALID_ERROR_SUPPORT_MAIL_ON_SAVE))
						.getText());
				flag = webDB.driver.findElement(By.xpath(EmailConfigurationLocators.INVALID_ERROR_SUPPORT_MAIL_ON_SAVE))
						.getText().equalsIgnoreCase(validSupportEmailMessageOnSave);
				log.info(flag);
				log.info("Invalid error message on save is displayed");
			}
		}
		return flag;
	}

	public static void selectIncludeShopifyBrandName() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(CommonLocators.CHECKBOX_COMMON)));
		elements = webDB.driver.findElements(By.xpath(CommonLocators.CHECKBOX_SELECTION_CHECK));
		for (int i = 0; i < elements.size(); i++) {
			if (!elements.get(i).isSelected()) {
				List<WebElement> checkBox = webDB.driver.findElements(By.xpath(CommonLocators.CHECKBOX_COMMON));
				checkBox.get(i).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CommonLocators.SAVE_SETTINGS)));
				webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, DriverBase.ElementType.ClassName.Xpath);
				log.debug("Include Brand Name checkbox selected");
				break;

			}
		}
		elements = webDB.driver.findElements(By.xpath(CommonLocators.RADIO_ATTRIBUTE_GENERIC));
		if (!elements.get(0).isSelected()) {
			List<WebElement> radio = webDB.driver.findElements(By.xpath(CommonLocators.RADIO_GENERIC));
			radio.get(0).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CommonLocators.SAVE_SETTINGS)));
			webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, DriverBase.ElementType.ClassName.Xpath);
			log.debug("Brand Name RadioBox selected");
		}
	}

	public static boolean selectAndVerifyShopifyBrandNameOnEmailTemplatesSubject()
			throws InterruptedException, IOException {
		selectIncludeShopifyBrandName();
		log.info("Brand Name ahs been selected");
		CommonFunctions.verifyEmailTemplatesPage();
		Thread.sleep(3000);
		elements = webDB.driver.findElements(By.xpath(EmailTemplatesLocators.EMAIL_SUBJECT_TEXT_BOX));
		log.info("size is " + elements.size());
		int i = 0;
		while (i < 4) {
			String text = elements.get(i).getText();
			log.info("BrandName is present for " + elements.get(i));
			flag = text.contains("{brandName}");
			i++;
		}
		return flag;
	}

	public static void selectIncludeCustomBrandName() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(CommonLocators.CHECKBOX_COMMON)));
		elements = webDB.driver.findElements(By.xpath(CommonLocators.CHECKBOX_SELECTION_CHECK));
		for (int i = 0; i < elements.size(); i++) {
			if (!elements.get(i).isSelected()) {
				List<WebElement> checkBox = webDB.driver.findElements(By.xpath(CommonLocators.CHECKBOX_COMMON));
				checkBox.get(i).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CommonLocators.SAVE_SETTINGS)));
				webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, DriverBase.ElementType.ClassName.Xpath);
				log.debug("Include Brand Name checkbox selected");
				break;

			}
		}
		elements = webDB.driver.findElements(By.xpath(CommonLocators.RADIO_ATTRIBUTE_GENERIC));
		if (!elements.get(1).isSelected()) {
			List<WebElement> radio = webDB.driver.findElements(By.xpath(CommonLocators.RADIO_GENERIC));
			radio.get(1).click();
		}
		String customBrandName = "CustomStore" + RandomStringUtils.randomAlphanumeric(2);
		webDB.driver.findElement(By.xpath(EmailConfigurationLocators.CUSTOM_BRANDNAME_FIELD))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.driver.findElement(By.xpath(EmailConfigurationLocators.CUSTOM_BRANDNAME_FIELD)).sendKeys(customBrandName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CommonLocators.SAVE_SETTINGS)));
		webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, DriverBase.ElementType.ClassName.Xpath);
		log.debug("Brand Name RadioBox selected");
	}

	public static boolean selectAndVerifyCustomBrandNameOnEmailTemplatesSubject()
			throws InterruptedException, IOException {
		selectIncludeCustomBrandName();
		log.info("Custom Brand Name ahs been selected");
		CommonFunctions.verifyEmailTemplatesPage();
		webDB.driver.navigate().refresh();
		Thread.sleep(2000);
		elements = webDB.driver.findElements(By.xpath(EmailTemplatesLocators.EMAIL_SUBJECT_TEXT_BOX));
		Thread.sleep(3000);
		int i = 0;
		System.out.println(elements.size());
		while (i < elements.size()) {
			String text = elements.get(i).getText();
			System.out.println(text);
			log.info("BrandName is present for " + elements.get(i));
			flag = text.contains("{brandName}");
			i++;
		}
		return flag;
	}

	public static void selectIncludeSupportEmailInEmailTemplates() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(CommonLocators.CHECKBOX_COMMON)));
		elements = webDB.driver.findElements(By.xpath(CommonLocators.CHECKBOX_SELECTION_CHECK));
		for (int i = 1; i < elements.size(); i++) {
			if (!elements.get(i).isSelected()) {
				List<WebElement> checkBox = webDB.driver.findElements(By.xpath(CommonLocators.CHECKBOX_COMMON));
				checkBox.get(i).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CommonLocators.SAVE_SETTINGS)));
				webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, DriverBase.ElementType.ClassName.Xpath);
				log.debug("Include Brand Name checkbox selected");
				break;
			}
		}
	}

	public static boolean addAndVerifySupportEmailInTemplates() throws InterruptedException, IOException {
		String emailId = "qatestemail@gupshup.io" + RandomStringUtils.randomAlphabetic(2);
		selectIncludeSupportEmailInEmailTemplates();
		Thread.sleep(1000);
		webDB.driver.findElement(By.xpath(EmailConfigurationLocators.SUPPORT_EMAIL_FIELD))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDB.driver.findElement(By.xpath(EmailConfigurationLocators.SUPPORT_EMAIL_FIELD)).sendKeys(emailId);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CommonLocators.SAVE_SETTINGS)));
		webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, DriverBase.ElementType.ClassName.Xpath);
		CommonFunctions.verifyEmailTemplatesPage();
		elements = webDB.driver.findElements(By.xpath(EmailTemplatesLocators.EMAIL_BODY_CONTAINER));
		for (int i = 0; i < elements.size(); i++) {
			text = elements.get(i).getText();
			flag = text.contains(emailId);
			log.info(flag);
		}
		return flag;
	}

	public static boolean verifyEmptyCustomBrandNameValidation() throws InterruptedException {
		String emptyBrandNameErrorText = "Name Cannot Be Empty";
		String emptyValidationMessageOnSave = "Name cannot be empty";
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(CommonLocators.CHECKBOX_COMMON)));
		elements = webDB.driver.findElements(By.xpath(CommonLocators.CHECKBOX_SELECTION_CHECK));
		if (!elements.get(0).isSelected()) {
			List<WebElement> checkBox = webDB.driver.findElements(By.xpath(CommonLocators.CHECKBOX_COMMON));
			checkBox.get(0).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CommonLocators.SAVE_SETTINGS)));
			webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, DriverBase.ElementType.ClassName.Xpath);
			log.debug("Include Brand Name checkbox selected");
		}
		elements = webDB.driver.findElements(By.xpath(CommonLocators.RADIO_ATTRIBUTE_GENERIC));
		if (!elements.get(1).isSelected()) {
			List<WebElement> radio = webDB.driver.findElements(By.xpath(CommonLocators.RADIO_GENERIC));
			radio.get(1).click();
		}
		webDB.driver.findElement(By.xpath(EmailConfigurationLocators.CUSTOM_BRANDNAME_FIELD))
				.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CommonLocators.SAVE_SETTINGS)));
		webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, DriverBase.ElementType.ClassName.Xpath);
		flag = webDB.driver.findElement(By.xpath(EmailConfigurationLocators.ERROR_TEXT)).getText()
				.equalsIgnoreCase(emptyBrandNameErrorText);
		log.info("Error Message displayed below brand name field");
		if (flag) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CommonLocators.SAVE_SETTINGS)));
			webDB.clickAnElement(CommonLocators.SAVE_SETTINGS, DriverBase.ElementType.ClassName.Xpath);
			flag = webDB.isElementDisplayed(EmailConfigurationLocators.INVALID_ERROR_SUPPORT_MAIL_ON_SAVE,
					DriverBase.ElementType.Xpath);
			if (flag) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath(EmailConfigurationLocators.INVALID_ERROR_SUPPORT_MAIL_ON_SAVE)));
				log.info(webDB.driver
						.findElement(By.xpath(EmailConfigurationLocators.INVALID_ERROR_SUPPORT_MAIL_ON_SAVE))
						.getText());
				flag = webDB.driver.findElement(By.xpath(EmailConfigurationLocators.INVALID_ERROR_SUPPORT_MAIL_ON_SAVE))
						.getText().equalsIgnoreCase(emptyValidationMessageOnSave);
				log.info(flag);
				log.info("Invalid error message on save is displayed");
			}
		}
		return flag;
	}
}
