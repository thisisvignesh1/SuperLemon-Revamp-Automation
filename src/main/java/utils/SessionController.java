package utils;

/**
 * @author Gokul S
 * @company Galaxyweblinks
 * October 11, 2021
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SessionController {

	private static SessionController instance = new SessionController();

	public SessionController getInstance() {
		if (instance == null) {
			instance = new SessionController();
		}
		return instance;
	}

	public ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	protected static WebDriverWait wait;

	public enum ElementType {
		Id, CssSelector, ClassName, Name, LinkText, Xpath, Text
	}

	// Getter method for Driver
	public WebDriver getDriver() {
		return driver.get();
	}

	// Setter method for Driver
	public void setDriver(WebDriver driverParam) {
		driver.set(driverParam);
	}

	// Getter method for Wait
	public WebDriverWait getWait() {
		return wait;
	}

	// Setter method for Wait
	public void setWait(WebDriverWait wait) {
		SessionController.wait = wait;
	}
}
