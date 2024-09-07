package utils;

//import com.ssts.pcloudy.Connector;
//import com.ssts.pcloudy.Version;
//import com.ssts.pcloudy.dto.device.MobileDevice;
import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Sumit
 * @company Galaxyweblinks
 * October 11, 2021
 */

//import org.openqa.selenium.android.AndroidDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ISuite;
import org.testng.ITestNGMethod;
import org.testng.Reporter;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.api.services.sheets.v4.model.Request;

import commonFunctions.CommonFunctions;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

@SuppressWarnings({ "unused" })
public class DriverBase {
	private static Logger log = Logger.getLogger(DriverBase.class);
	public static WebDriver driver;
	static SessionController sessionController = new SessionController();
	protected static WebDriverWait wait;
	public static JavascriptExecutor js = (JavascriptExecutor) driver;
	String platformName = System.getProperty("platformName");
	String extensionCrxPath = System.getProperty("user.dir");
	static String environment = System.getProperty("env");
	static Boolean flag = true;
	public static RequestSpecification request;

	private static String propertyFilePath;
	public static Properties prop = new Properties();
	public static JsonPath jsonPathEvaluator;
	private static DriverBase webDB = new DriverBase();
	private static long epoch;
//	private static  String filePath =(System.getProperty("user.dir") + File.separator + "drivers"
//			+ File.separator + "geckodriver";
	private static String url;
	public static String starttime;
	public static String executiontime;
	public static String endtime;
	public static String shopid;
	public static final String USERNAME = "praveenkumar_gCN3XW";
	public static final String AUTOMATE_KEY = "qavoSkiusLaX4KksFjpK";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	public static Map<String, String> commonCapabilities1;

	public enum ElementType {
		Id, CssSelector, ClassName, Name, LinkText, Xpath, Text, PartialLinkText
	}

	public static String token;

	public void browserOpen() throws FileNotFoundException, IOException, Exception {
		if (System.getProperty("platformName").equals("Firefox") || System.getProperty("platformName").equals("firefox")
				|| System.getProperty("platformName").equals("Chrome")
				|| System.getProperty("platformName").equals("chrome")) {
			if (System.getProperty("platformName").equals("Firefox")
					|| System.getProperty("platformName").equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				DesiredCapabilities capabilities = DesiredCapabilities.firefox();
				FirefoxOptions options = new FirefoxOptions();
//				options.addArguments("-disable-dev-shm-usage");
//				options.addArguments("headless");
				options.addArguments("start-maximized");
//				options.addArguments("--start-fullscreen");
				options.addArguments("test-type");
				capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
				capabilities.setCapability("applicationCacheenabled", true);
				options.merge(capabilities);
				sessionController.setDriver(new FirefoxDriver(options));
				driver = sessionController.getDriver();
			} else if (System.getProperty("platformName").equals("chrome")
					|| System.getProperty("platformName").equalsIgnoreCase("CHROME")) {
				WebDriverManager.chromedriver().setup();
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				ChromeOptions options = new ChromeOptions();
				// options.addExtensions(new
				// File(extensionCrxPath+"/paknhbpffcejbimandchnlbgcbbclabd.crx"));
				// options.addArguments("user-data-dir=/home/sumit/.config/google-chrome/default");
				options.addArguments("-disable-dev-shm-usage");
//				options.addArguments("headless");
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("download.default_directory", System.getProperty("user.dir") + File.separator + "Resources");
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("start-maximized");
//				options.addArguments("--start-fullscreen");
				options.addArguments("test-type");
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				capabilities.setCapability("applicationCacheenabled", false);
				options.merge(capabilities);
//				sessionController.setDriver(new ChromeDriver(options));
//				driver = sessionController.getDriver();
				driver = new ChromeDriver(options);
			}
			sessionController.getInstance().setDriver(driver);
			String waitTime = "30";
			driver.manage().timeouts().implicitlyWait(Long.parseLong(waitTime), TimeUnit.SECONDS);
			driver.manage().window().maximize();
			// Dimension d = new Dimension(1920,1080);
			// driver.manage().window().setSize(d);
		} else if (System.getProperty("platformName").equals("browserstack")) {
			getMobileBrowserStackDriver();
		}
	}

	@SuppressWarnings("unchecked")
	public static void getMobileBrowserStackDriver()
			throws FileNotFoundException, IOException, ParseException, Exception {
		JSONParser parser = new JSONParser();
		JSONObject config = (JSONObject) parser
				.parse(new FileReader(System.getProperty("user.dir") + File.separator + "parallel.conf.json"));
		if (System.getProperty("platformName").equals("browserstack")) {
			String osName = System.getProperty("os");
			String deviceIndex = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("deviceIndex");
			commonCapabilities1 = (Map<String, String>) config.get(osName + deviceIndex);
			commonCapabilities1.put("buildName", "Superlemon-" + osName);
			String key;
			DesiredCapabilities caps = new DesiredCapabilities();
			Set<String> keys = commonCapabilities1.keySet();
			Iterator<String> itr = keys.iterator();
			while (itr.hasNext()) {
				key = itr.next();
				caps.setCapability(key, commonCapabilities1.get(key));
			}
			try {
				System.out.println(caps);
				driver = new RemoteWebDriver(new URL(URL), caps);
				String waitTime = "15";
				driver.manage().timeouts().implicitlyWait(Long.parseLong(waitTime), TimeUnit.SECONDS);
				driver.manage().window().maximize();
				;
				wait = new WebDriverWait(driver, 50);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

		}
	}
	/**
	 * Method will get the pCloudy available device and select based on the brand
	 * provided
	 *
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws Exception
	 */
//    public static void getMobilePCloudyDriver() throws FileNotFoundException, IOException, Exception {
//        String deviceName = null, version = null;
//        // Select the available Device from pcloudy
//        String deviceIndex = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
//                .getParameter("deviceIndex");
//        JSONParser parser = new JSONParser();
//        JSONObject config = (JSONObject) parser
//                .parse(new FileReader(System.getProperty("user.dir") + File.separator + "parallel.conf.json"));
//        Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
//        JSONArray envs = (JSONArray) config.get("environments");
//        Connector con = new Connector("https://device.pcloudy.com/api/");
//        String pcloudy_username = commonCapabilities.get("pCloudy_Username");
//        String pcloudy_apikey = commonCapabilities.get("pCloudy_ApiKey");
//        String authToken = con.authenticateUser(pcloudy_username, pcloudy_apikey);
//        // Set the capabilities from json file
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        Map<String, String> envCapabilities = (Map<String, String>) envs.get(Integer.parseInt(deviceIndex));
//        Iterator<Map.Entry<String, String>> it = envCapabilities.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry pair = (Map.Entry) it.next();
//            String platform = envCapabilities.get("platformName");
//            capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
//            if (pair.getKey().toString().equals("brand")) {
//                String brandName = pair.getValue().toString();
//                ArrayList<MobileDevice> availableDevices = new ArrayList<>();
//                availableDevices.addAll(
//                        con.chooseDevices(authToken, platform, new Version("10.*.*"), new Version("11.*.*"), 7));
//                for (int i = 0; i < 7; i++) {
//                    String brand1 = availableDevices.get(i).manufacturer;
//                    if (brand1.equals(brandName)) {
//                        deviceName = availableDevices.get(i).full_name;
//                        version = availableDevices.get(i).version;
//                        break;
//                    } else {
//                        if (i == 6) {
//                            System.out.println("Specified Device not found, selecting the available device");
//                            deviceName = availableDevices.get(i).full_name;
//                            System.out.println(deviceName);
//                            version = availableDevices.get(i).version;
//                            System.out.println(version);
//                            break;
//                        } else
//                            continue;
//                    }
//                }
//            }
//        }
//        it = commonCapabilities.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry pair = (Map.Entry) it.next();
//            if (capabilities.getCapability(pair.getKey().toString()) == null) {
//                capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
//            }
//        }
//        capabilities.setCapability("pCloudy_DeviceFullName", deviceName);
//        capabilities.setCapability("pCloudy_DeviceVersion", version);
//        capabilities.setCapability("unlockKey", "pin");
//        String URL = "https://device.pcloudy.com/appiumcloud/wd/hub";
//        System.out.println(capabilities);
//        Reporter.log("Executed Device Name: " + deviceName);
//        sessionController.setDriver(new RemoteWebDriver(new URL(URL), capabilities));
//        driver = sessionController.getDriver();
//        String waitTime = "15";
//        driver.manage().timeouts().implicitlyWait(Long.parseLong(waitTime), TimeUnit.SECONDS);
//        FileReader reader = new FileReader("Config.properties");
//        Properties prop = new Properties();
//        prop.load(reader);
//        String environment = System.getProperty("environment");
//        if (environment.contains("QAenvironment")) {
//            String url = prop.getProperty(environment);
//            driver.get(url);
//        } else if (environment.contains("Devenvironment")) {
//            String url = prop.getProperty(environment);
//            driver.get(url);
//        } else if (environment.contains("Prodenvironment")) {
//            String url = prop.getProperty(environment);
//            driver.get(url);
//        } else {
//            System.out.println("Please check the environment");
//        }
//        wait = new WebDriverWait(driver, 50);
//    }

	/**
	 * Verify API response for given request
	 *
	 * @param filename
	 * @return flag
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
//    @SuppressWarnings({"unchecked", "rawtypes"})
//    public boolean verifyAPIResponse(String filename) throws FileNotFoundException, IOException {
//        propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
//        prop.load(new FileInputStream(propertyFilePath));
//        String token = prop.getProperty("token");
//        try {
//            JSONParser parser = new JSONParser();
//            JSONObject config = (JSONObject) parser.parse(new FileReader(
//                    System.getProperty("user.dir") + File.separator + "payload" + File.separator + filename + ".json"));
//            Map<String, String> requestParams = (Map<String, String>) config.get("payload");
//            System.out.println(requestParams);
//            RestAssured.baseURI = config.get("baseurl").toString();
//            request = RestAssured.given();
//            request.header("Content-type", "application/json");
//            request.header("Authorization", token);
//            request.header("Cookie", "");
//            request.body(((JSONObject) requestParams).toJSONString());
//            Response response = request.get();
//            ResponseBody body = response.getBody();
//            Cookies cook = response.getDetailedCookies();
//            System.out.println(cook);
//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);
//            Reporter.log(bodyAsString);
//            System.out.println("Response: " + response.getStatusLine());
//            CookieStore cookieStore = new BasicCookieStore();
//            List<org.apache.http.cookie.Cookie> cookies = cookieStore.getCookies();
//            for (int i = 0; i < cookies.size(); i++) {
//                System.out.println("Local cookie: " + cookies.get(i));
//            }
//            jsonPathEvaluator = response.jsonPath();
//
//            flag = bodyAsString != null;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return flag;
//    }

	public static void enterURL() throws InterruptedException, IOException, FileNotFoundException {

		url = null;
		if (environment.equals("QA")) {
			propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
			prop.load(new FileInputStream(propertyFilePath));
			url = prop.getProperty("QA_url");
		} else if (environment.equals("Prod")) {
			propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
			prop.load(new FileInputStream(propertyFilePath));
			url = prop.getProperty("Prod_url");
		}
		driver.get(url);
		wait = new WebDriverWait(driver, 30);

	}

	/**
	 * Quit the browser
	 *
	 * @author Gokul S
	 */
	public void tearDown() {
		log.info("Quitting driver");
		driver.quit();
	}

	// Wait for an Element
	// Params(Element & it's type)
	public Boolean waitForElement(String element, ElementType elementType) {
		try {
			switch (elementType) {
			case Id: {
				flag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element))).isDisplayed();
				break;
			}

			case Name: {
				flag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(element))).isDisplayed();
			}

			case Xpath: {
				flag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element))).isDisplayed();
				break;
			}

			case CssSelector: {
				flag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(element))).isDisplayed();
				break;
			}

			case ClassName: {
				flag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(element))).isDisplayed();
				break;
			}

			case LinkText: {
				flag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(element))).isDisplayed();
				break;
			}
			default:
				break;
			}
		} catch (Exception e) {
			Reporter.log("Element is not found in the page " + element + " Exception " + e, true);
			return false;
		}
		return true;
	}

	// click an element
	// Params(Element & it's type)
	public Boolean clickAnElement(String element, ElementType elementType) {
		try {
			switch (elementType) {
			case Id: {
				driver.findElement(By.id(element)).click();
				break;
			}

			case Name: {
				driver.findElement(By.name(element)).click();
				break;
			}

			case Xpath: {
				driver.findElement(By.xpath(element)).click();
				break;
			}

			case CssSelector: {
				driver.findElement(By.cssSelector(element)).click();
				break;
			}

			case ClassName: {
				driver.findElement(By.className(element)).click();
				break;
			}

			case LinkText: {
				driver.findElement(By.linkText(element)).click();
				break;
			}

			case Text: {
				WebElement element1 = driver.findElement(By.xpath("//*[text()='" + element + "']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element1);
				break;
			}
			default:
				break;
			}
		} catch (Exception e) {
			Reporter.log("Unable to click the element: " + element + " in the page, with the Exception: " + e, true);
			return false;
		}
		return true;
	}

	// Enter Text
	// Params (Element, Value & it's Type)
	public static Boolean sendTextToAnElement(String element, String value, ElementType elementType) {
		try {
			switch (elementType) {
			case Id: {
				driver.findElement(By.id(element)).sendKeys(value);
				break;
			}

			case Name: {
				driver.findElement(By.name(element)).sendKeys(value);
				break;
			}

			case Xpath: {
				driver.findElement(By.xpath(element)).sendKeys(value);
				break;
			}

			case CssSelector: {
				driver.findElement(By.cssSelector(element)).sendKeys(value);
				break;
			}

			case ClassName: {
				driver.findElement(By.className(element)).sendKeys(value);
				break;
			}

			case LinkText: {
				driver.findElement(By.linkText(element)).sendKeys(value);
				break;
			}
			default:
				break;
			}
		} catch (Exception e) {
			Reporter.log("Unable to send text to an element: " + element + " in the page, with the Exception: " + e,
					true);
			return false;
		}
		return true;
	}

	public Boolean isElementDisplayed(String element, ElementType elementType) {
		try {
			switch (elementType) {
			case Id: {
				driver.findElement(By.id(element)).isDisplayed();
				break;
			}

			case Name: {
				driver.findElement(By.name(element)).isDisplayed();
				break;
			}

			case Xpath: {
				driver.findElement(By.xpath(element)).isDisplayed();
				break;
			}

			case CssSelector: {
				driver.findElement(By.cssSelector(element)).isDisplayed();
				break;
			}

			case ClassName: {
				driver.findElement(By.className(element)).isDisplayed();
				break;
			}

			case LinkText: {
				driver.findElement(By.linkText(element)).isDisplayed();
				break;
			}

			case Text: {
				WebElement element1 = driver.findElement(By.xpath("//*[text()='" + element + "']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].isDisplayed();", element1);
				break;
			}
			default:
				break;
			}
		} catch (Exception e) {
			Reporter.log("Element: " + element + " Not Displayed in the page, with the Exception: " + e, true);
			return false;
		}
		return true;
	}

	public void takeScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = ((TakesScreenshot) driver);
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
		String destFile = System.getProperty("user.dir") + "/test-output/FailedScreenShots/Screenshots" + testCaseName
				+ timestamp + ".png";
		FileUtils.copyFile(sourceFile, new File(destFile));

	}

	public static void getTestCaseExecutionTime(String startTime, String endTime) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date1 = simpleDateFormat.parse(startTime);
		Date date2 = simpleDateFormat.parse(endTime);
		long timeTaken = Math.abs(date2.getTime() - date1.getTime());
		long timeTakenInMinutes = (timeTaken / (60 * 1000)) % 60;
		long differenceInSeconds = (timeTaken / 1000) % 60;
		log.info("Time taken for test execution:" + " " + timeTakenInMinutes + "minutes amd" + differenceInSeconds
				+ "seconds");
	}

	public static void executeSuccessApi(String instanceId) {
//		instanceId = "48654581";
		RestAssured.baseURI = "https://api.practitest.com";
		given().header("Content-Type", "application/json").auth().preemptive()
				.basic("atul.shukla@gupshup.io", "bdf593a512483058b88807d033fdb51fa1bf0a9f")
				.body("{\"data\": { \"type\": \"instances\", \"attributes\": {\"instance-id\": " + instanceId
						+ ",\"exit-code\": 0}}}\n")
				.when().post("/api/v2/projects/21190/runs.json").then().assertThat().statusCode(200);
	}

	public static String getInstance(String methodName) {
		String instanceId = null;
		String setId = "1531623";
		System.out.println("reached here");
		RestAssured.baseURI = "https://api.practitest.com";
		Response response = given().header("Content-Type", "application/json").auth().preemptive()
				.basic("atul.shukla@gupshup.io", "bdf593a512483058b88807d033fdb51fa1bf0a9f")
				.queryParam("name_like", methodName).queryParam("set-ids", setId).when()
				.get("/api/v2/projects/21190/instances.json").then().extract().response();
//
		JsonPath j = new JsonPath(response.asString());
//		int s = j.getInt("data.size()");
//		System.out.println("size is"+s);
//		for (int i = 0; i < s; i++) {
//			String name = j.getString("data.attributes[" + i + "].name");
//			System.out.println(name);
//			System.out.println(methodName);
//			if (name.equalsIgnoreCase(methodName)) {
		instanceId = j.getString("data[0].id");
//				System.out.println(name);
		System.out.println(instanceId);
//				break;
//			}
//		}
		return instanceId;
	}

	public static String getTestName(String methodName) {
		RestAssured.baseURI = "https://api.practitest.com";
		Response response = given().header("Content-Type", "application/json").auth().preemptive()
				.basic("atul.shukla@gupshup.io", "bdf593a512483058b88807d033fdb51fa1bf0a9f")
				.queryParam("name", methodName).when().get("/api/v2/projects/21190/instances.json").then().extract()
				.response();
		String name = response.path("name");
		return name;
	}

	public static void executeFailedApi(String instanceId) {
//		instanceId = "48654581";
		RestAssured.baseURI = "https://api.practitest.com";
		given().header("Content-Type", "application/json").auth().preemptive()
				.basic("atul.shukla@gupshup.io", "bdf593a512483058b88807d033fdb51fa1bf0a9f")
				.body("{\"data\": {\"type\": \"instances\", \"attributes\": {\"instance-id\": " + instanceId
						+ "}, \"steps\": {\"data\": [{\"name\": \"step one\", \"expected-results\": \"result\", \"status\": \"FAILED\"}] }}}")
				.when().post("/api/v2/projects/21190/runs.json").then().assertThat().statusCode(200);
	}

	public static Connection setDBConnection() throws ClassNotFoundException, SQLException {
		String dbURL = "jdbc:mysql://10.80.12.58:3306/AutomationRunAnalyics";
		String username = "superlemon";
		String password = "superL@m0n";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(dbURL, username, password);
		return con;
	}

	public static void updateTestCaseExecutedCount(long epoch) throws SQLException, ClassNotFoundException {
		int testCasesExecuted = 0;
		try {
			Connection con = setDBConnection();
			Statement st = con.createStatement();
			String testCaseExecutedCountQuery = "select sum(Passed+Failed) from automation_stats where  testStartedAt = "
					+ epoch + "";
			ResultSet rs1;
			log.info("here");
			rs1 = st.executeQuery(testCaseExecutedCountQuery);
			if (rs1.next()) {
				testCasesExecuted = rs1.getInt(1);
			}
			log.info(testCasesExecuted);
			String selectquery = "update automation_stats set testCasesExecuted =" + testCasesExecuted
					+ " where testStartedAt=" + epoch + "";
			log.info("reached here");
			st.executeUpdate(selectquery);
			log.info("reached here as well");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updatePassedCasesCount(long epoch) throws SQLException, ClassNotFoundException {
		int testCasePassedCount = 0;
		try {
			Connection con = setDBConnection();
			Statement st = con.createStatement();
			String getPassedCountQuery = "select Passed from automation_stats where testStartedAt = '" + epoch + "'";
			ResultSet rs1;
			rs1 = st.executeQuery(getPassedCountQuery);
			if (rs1.next()) {
				testCasePassedCount = rs1.getInt(1);
			}
			testCasePassedCount = testCasePassedCount + 1;
			log.info(testCasePassedCount);
			String selectquery = "update automation_stats set Passed =" + testCasePassedCount
					+ " where  testStartedAt = '" + epoch + "'";
			log.info("reached here");
			st.executeUpdate(selectquery);
			log.info("reached here as well");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateFailedCasesCount(long epoch) throws SQLException, ClassNotFoundException {
		int testCaseFailedCount = 0;
		try {
			Connection con = setDBConnection();
			Statement st = con.createStatement();
			String getPassedCountQuery = "select Failed from automation_stats where  testStartedAt = '" + epoch + "'";
			ResultSet rs1;
			rs1 = st.executeQuery(getPassedCountQuery);
			if (rs1.next()) {
				testCaseFailedCount = rs1.getInt(1);
			}
			testCaseFailedCount = testCaseFailedCount + 1;
			log.info(testCaseFailedCount);
			String selectquery = "update automation_stats set Failed =" + testCaseFailedCount
					+ " where  testStartedAt = '" + epoch + "'";
			st.executeUpdate(selectquery);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//    public int getTestCount() {
//        int count = 0;
//        for (XmlSuite suite : ) {
//            List<XmlTest> xmlTests = suite.getTests();
//            for (XmlTest test : xmlTests) {
//                count += test.getClasses().size();
//            }
//        }
//        return count;
//    }

	public static void updateTotalTestCasesCount(long epoch) throws SQLException, ClassNotFoundException {
		int count = 0;
		String testType = System.getProperty("testType");
		log.info(testType);
		if (testType.equals("Sanity")) {
			count = 30;
		} else if (testType.equals("Regression")) {
			count = 212;
		}
		try {
			Connection con = setDBConnection();
			Statement st = con.createStatement();
			String totalTestCasesCountQuery = "update automation_stats set totalTestCases =" + count
					+ " where  testStartedAt = '" + epoch + "'";
			st.executeUpdate(totalTestCasesCountQuery);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateAllColumnsValueToZero() throws SQLException, ClassNotFoundException {
		try {
			Connection con = setDBConnection();
			Statement st = con.createStatement();
			String updateFailedCountQuery = "update automation_stats set Failed =0 where testStartedAt = " + epoch + "";
			String passedCountQuery = "update automation_stats set Passed =0 where testStartedAt = " + epoch + "";
			st.executeUpdate(updateFailedCountQuery);
			st.executeUpdate(passedCountQuery);
//            int rs3 = st.executeUpdate(updateTestCaseExecutedQuery);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int count(ISuite suite) {
		int testCount = 0;
		List<ITestNGMethod> testMethods = null;
		testMethods = suite.getAllMethods();
		testCount = testMethods.size();
		return testCount;

	}

	public void setUpConfiguration() {
		String reportFileName = "SanityTest" + ".xml";
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		Document doc = null;
		DocumentBuilder docBuilder;
		try {
			docBuilder = docFactory.newDocumentBuilder();
			String xmlFilePath = System.getProperty("user.dir") + "/testSuite/" + reportFileName;
			log.info(xmlFilePath);
			doc = docBuilder.parse(xmlFilePath); // path to your testng.xml
			NodeList parameterNodes = doc.getElementsByTagName("test");
			log.info("Total Number of tests to be executed are : " + parameterNodes.getLength());
		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.out.println("Exception while reading counting tests in testng.xml");
		}

	}

	@SuppressWarnings("rawtypes")
	public static int getMethodCount(String methodName) throws ClassNotFoundException {
		Class cls = Class.forName(methodName);
		Method[] methlist = cls.getDeclaredMethods();
		int size = methlist.length;
		return size;
	}

	public static void setTestType(long epoch) {
		String testType = System.getProperty("testType");
		try {
			Connection con = setDBConnection();
			Statement st = con.createStatement();
			String testTypeQuery = "update automation_stats set suiteName ='" + testType + "' where  testStartedAt = '"
					+ epoch + "'";
			st.executeUpdate(testTypeQuery);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static long updateStartTimeOfExecution() throws ParseException {
		String startTime = new SimpleDateFormat("MMM dd yyyy HH:mm:ss.SSS zzz").format(new Date());
		SimpleDateFormat df = new SimpleDateFormat("MMM dd yyyy HH:mm:ss.SSS zzz");
		Date date = df.parse(startTime);
		epoch = date.getTime();
		try {
			Connection con = setDBConnection();
			Statement st = con.createStatement();
			String startTimeQuery = "insert into automation_stats  (testStartedAt)  values(" + epoch + ")";
			st.executeUpdate(startTimeQuery);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return epoch;
	}

	public static void updateEndTimeOfExecution(long epoch) throws ParseException {
		String endTime = new SimpleDateFormat("MMM dd yyyy HH:mm:ss.SSS zzz").format(new Date());
		SimpleDateFormat df = new SimpleDateFormat("MMM dd yyyy HH:mm:ss.SSS zzz");
		Date date = df.parse(endTime);
		long epoch1 = date.getTime();
		try {
			Connection con = setDBConnection();
			Statement st = con.createStatement();
			String endTimeQuery = "update automation_stats set testCompletedAt ='" + epoch1
					+ "' where  testStartedAt = '" + epoch + "' ";
			st.executeUpdate(endTimeQuery);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateProjectName() throws ParseException {
		String projectName = "SuperLemon";
		try {
			Connection con = setDBConnection();
			Statement st = con.createStatement();
			String projecNameQuery = "update automation_stats set ProjectName ='" + projectName
					+ "' where testStartedAt = " + epoch + "";
			st.executeUpdate(projecNameQuery);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Move to Element
	 *
	 * @author Praveen Kumar R
	 * @throws InterruptedException
	 */
	public void moveToElement(String element, ElementType elementType) throws InterruptedException {
		switch (elementType) {
		case Id: {
			Actions action = new Actions(driver);
			WebElement Element = driver.findElement(By.id(element));
			action.moveToElement(Element).perform();
			Thread.sleep(2500);
			break;
		}

		case Name: {
			Actions action = new Actions(driver);
			WebElement Element = driver.findElement(By.name(element));
			action.moveToElement(Element).perform();
			Thread.sleep(2500);
			break;
		}

		case Xpath: {
			Actions action = new Actions(driver);
			WebElement Element = driver.findElement(By.xpath(element));
			action.moveToElement(Element).perform();
			Thread.sleep(2500);
			break;
		}

		case CssSelector: {
			Actions action = new Actions(driver);
			WebElement Element = driver.findElement(By.cssSelector(element));
			action.moveToElement(Element).perform();
			Thread.sleep(2500);
			break;
		}

		case ClassName: {
			Actions action = new Actions(driver);
			WebElement Element = driver.findElement(By.className(element));
			action.moveToElement(Element).perform();
			Thread.sleep(2500);
			break;
		}

		case LinkText: {
			Actions action = new Actions(driver);
			WebElement Element = driver.findElement(By.linkText(element));
			action.moveToElement(Element).perform();
			Thread.sleep(2500);
			break;
		}
		default:
			break;
		}
	}

	/**
	 * Verify the Toggle is Enabled or not
	 *
	 * @param element
	 * @return
	 */
	public boolean IsEnabled(String element, ElementType elementtype) {
		try {
			switch (elementtype) {
			case Id: {
				flag = driver.findElement(By.id(element)).isSelected();
				break;
			}

			case Name: {
				flag = driver.findElement(By.name(element)).isSelected();
				break;
			}

			case Xpath: {
				flag = driver.findElement(By.xpath(element)).isEnabled();
				break;
			}

			case CssSelector: {
				flag = driver.findElement(By.cssSelector(element)).isSelected();
				break;
			}

			case ClassName: {
				flag = driver.findElement(By.className(element)).isSelected();
				break;
			}

			case LinkText: {
				flag = driver.findElement(By.linkText(element)).isSelected();
				break;
			}
			default:
				break;
			}
		} catch (Exception e) {
			Reporter.log("Element is not found in the page " + element + " Exception " + e, true);
			return false;
		}
		return flag;
	}

	public Boolean switchToChildWindowAndVerifyURL(String element, String data)
			throws InterruptedException, FileNotFoundException, IOException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
		Thread.sleep(3000);
		driver.findElement(By.xpath(element)).click();
		String MainWindow = driver.getWindowHandle();
		Set<String> s1 = driver.getWindowHandles();
		for (String a : s1) {
			if (!a.equalsIgnoreCase(MainWindow)) {
				driver.switchTo().window(a);
				Thread.sleep(5000);
				String geturl = getDataFromConfig(data);
				Thread.sleep(5000);
				flag = driver.getCurrentUrl().contains(geturl);
				System.out.println(flag);
				Thread.sleep(3000);
				driver.close();
			}
		}
		driver.switchTo().window(MainWindow);
		Thread.sleep(5000);
		return flag;
	}

	public Boolean switchToChildWindowjavaScriptClickAndVerifyURL(String element, String data)
			throws InterruptedException, FileNotFoundException, IOException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath(element));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element1);
		String MainWindow = driver.getWindowHandle();
		Set<String> s1 = driver.getWindowHandles();
		for (String a : s1) {
			if (!a.equalsIgnoreCase(MainWindow)) {
				driver.switchTo().window(a);
				Thread.sleep(5000);
				String geturl = getDataFromConfig(data);
				Thread.sleep(5000);
				flag = driver.getCurrentUrl().contains(geturl);
				System.out.println(flag);
				Thread.sleep(3000);
				driver.close();
			}
		}
		driver.switchTo().window(MainWindow);
		Thread.sleep(5000);
		return flag;
	}

	public static String getDataFromConfig(String data)
			throws InterruptedException, FileNotFoundException, IOException {
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String info = prop.getProperty(data);
		return info;
	}

	/**
	 * Will return the Text
	 *
	 * @param element
	 * @param elementtype
	 * @return string
	 */
	public String getText(String element, ElementType elementtype) {
		String temp = null;
		try {
			if (elementtype == ElementType.Id)
				temp = driver.findElement(By.id(element)).getText();
			else if (elementtype == ElementType.Name)
				temp = driver.findElement(By.name(element)).getText();
			else if (elementtype == ElementType.Xpath)
				temp = driver.findElement(By.xpath(element)).getText();
			else if (elementtype == ElementType.CssSelector)
				temp = driver.findElement(By.cssSelector(element)).getText();
			else if (elementtype == ElementType.ClassName)
				temp = driver.findElement(By.className(element)).getText();
		} catch (Exception e) {
			System.out.println(element);
//			reportLog("Unable to get the text from the Element: " + element + " Exception: " + e);
//			Reporter.log("Unable to get the text from the Element: " + element + " Exception: " + e);
		}
		return temp;
	}

	/**
	 * Clear the text fields
	 *
	 * @param element
	 * @param elementtype
	 */
	public void clearTextField(String element, ElementType elementtype) {
		try {
			if (elementtype == ElementType.Id)
				driver.findElement(By.id(element)).clear();
			else if (elementtype == ElementType.Name)
				driver.findElement(By.name(element)).clear();
			else if (elementtype == ElementType.Xpath) {
				driver.findElement(By.xpath(element)).click();
				driver.findElement(By.xpath(element)).clear();
			} else if (elementtype == ElementType.CssSelector) {
				driver.findElement(By.cssSelector(element)).click();
				driver.findElement(By.cssSelector(element)).clear();
			} else if (elementtype == ElementType.ClassName)
				driver.findElement(By.className(element)).clear();
		} catch (Exception e) {
//			reportLog("Unable to Clear the Text field to the Element: " + element + " Exception: " + e);
//			Reporter.log("Unable to Clear the Text field to the Element: " + element + " Exception: " + e);
			System.out.println(element);
		}
	}

	/**
	 * Scroll To Element
	 *
	 * @param element
	 */
	public void scrollToAnElement(String element) {
		try {
			WebElement scrollElement = driver.findElement(By.cssSelector(element));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", scrollElement);
		} catch (Exception e) {
			System.out.println(element);
//			reportLog("Unable to scroll to an element: " + element + " in the page, with the Exception: " + e);
//			Reporter.log("Unable to scroll to an element: " + element + " in the page, with the Exception: " + e, true);
		}
	}

	/**
	 * JavaScript click an element
	 *
	 * @param element
	 * @param elementType
	 * @return boolean - element present/not
	 */
	public Boolean javaScriptClickAnElement(String element, ElementType elementType) {
		try {
			switch (elementType) {
			case Id: {
				WebElement element1 = driver.findElement(By.id(element));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element1);
			}

			case Name: {
				WebElement element1 = driver.findElement(By.name(element));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element1);
				break;
			}

			case Xpath: {
				WebElement element1 = driver.findElement(By.xpath(element));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element1);
				break;
			}

			case CssSelector: {
				WebElement element1 = driver.findElement(By.cssSelector(element));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element1);
				break;
			}

			case ClassName: {
				WebElement element1 = driver.findElement(By.className(element));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element1);
				break;
			}

			case LinkText: {
				WebElement element1 = driver.findElement(By.linkText(element));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element1);
				break;
			}

			case Text: {
				WebElement element1 = driver.findElement(By.xpath("//*[text()='" + element + "']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element1);
				break;
			}
			default:
				break;
			}
		} catch (Exception e) {
			System.out.println(element);
//			reportLog("Unable to click : " + elementname + element + " in the page, with the Exception: " + e);
//			Reporter.log("Unable to click : " + elementname + element + " in the page, with the Exception: " + e, true);
			return false;
		}
		return true;
	}

	/**
	 * Clear text
	 *
	 * @param element
	 */
	public void clearTextCSS(String element) {
		WebElement toClear = driver.findElement(By.cssSelector(element));
		toClear.sendKeys(Keys.CONTROL + "a");
		toClear.sendKeys(Keys.DELETE);
	}

	/**
	 * Select the value from dropdown by using option value
	 *
	 * @param element
	 * @param value
	 * @return
	 */
	public boolean selectDropDownOptions(String element, String value, ElementType elementType) {
		try {
			switch (elementType) {
			case Id: {
				Select dropdown = new Select(driver.findElement(By.id(element)));
				dropdown.selectByValue(value);
				break;
			}

			case Name: {
				Select dropdown = new Select(driver.findElement(By.name(element)));
				dropdown.selectByValue(value);
				break;
			}

			case Xpath: {
				Select dropdown = new Select(driver.findElement(By.xpath(element)));
				dropdown.selectByValue(value);
				break;
			}

			case CssSelector: {
				Select dropdown = new Select(driver.findElement(By.cssSelector(element)));
				dropdown.selectByValue(value);
				break;
			}

			case ClassName: {
				Select dropdown = new Select(driver.findElement(By.className(element)));
				dropdown.selectByValue(value);
				break;
			}

			case LinkText: {
				Select dropdown = new Select(driver.findElement(By.linkText(element)));
				dropdown.selectByValue(value);
				break;
			}
			default:
				break;
			}
		} catch (Exception e) {
			System.out.println(element);
			Reporter.log("Element is not found in the page " + element + " Exception " + e, true);
			return false;
		}
		return true;
	}

	/**
	 * Verify Drop down values
	 *
	 * @param element
	 * @return
	 */
	public boolean verifyDropdownValues(String element, String[] expected) {
		String[] exp = expected;
		System.out.println(exp);
		Select select = new Select(driver.findElement(By.xpath(element)));
		List<WebElement> options = select.getOptions();
		System.out.println(options.size() - 1);
		System.out.println(exp.length);
		flag = options.size() - 1 == exp.length;
		if (flag) {
			for (int i = 0; i < options.size() - 1; i++) {
				System.out.println("if for");
				System.out.println(options.get(i + 1).getText());
				System.out.println(exp[i]);
				flag = exp[i].contains(options.get(i + 1).getText());
				System.out.println(flag);
			}
		} else {
			System.out.println("Dropdown Values are Mismatched");
			flag = false;
		}
		return flag;
	}

	/**
	 * Switch to Child Window and Send text,Click Element
	 *
	 * @author Praveen Kumar R
	 * @param element
	 * @return
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public Boolean switchToChildWindowAndTitle(String element, String element1, String element2, String data,
			String data1, String data2) throws InterruptedException, FileNotFoundException, IOException {
		driver.switchTo().defaultContent();
		Thread.sleep(5000);
		String MainWindow = driver.getWindowHandle();
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();
		while (i1.hasNext()) {
			String ChildWindow = i1.next();
			if (!MainWindow.equalsIgnoreCase(ChildWindow)) {
				driver.switchTo().window(ChildWindow);
				Thread.sleep(7000);
				driver.findElement(By.cssSelector(element)).sendKeys(data);
				Thread.sleep(15000);
				driver.findElement(By.cssSelector(element1)).click();
				Thread.sleep(5000);
				driver.findElement(By.cssSelector(element2)).sendKeys(data1);
				Thread.sleep(5000);
				driver.findElement(By.cssSelector(element1)).click();
				Thread.sleep(7000);
				String temp = driver.getCurrentUrl();
				flag = temp.contains(data2);
				Thread.sleep(3000);
				CommonFunctions.verifyAdminLogout();
				driver.close();
				break;
			}
		}
		driver.switchTo().window(MainWindow);
		Thread.sleep(3000);
		System.out.println("Switch to Main");
		return flag;
	}

	/**
	 * Verify Element is not displayed
	 *
	 * @author Gokul S
	 * @param element
	 * @param elementType
	 * @return
	 */
	public Boolean verifyElementIsNotDisplayed(String element, ElementType elementType) {
		int loop = 0;
		switch (elementType) {
		case CssSelector: {
			for (int count = 0; count < 5 && loop == 0; count++) {
				// Thread.sleep(2000);
				loop = driver.findElements(By.cssSelector(element)).size() > 0
						&& driver.findElement(By.cssSelector(element)).isDisplayed() ? 0 : 1;
			}
			if (loop == 1)
				flag = true;
			else {
				flag = false;
				Reporter.log("Element is displayed in this page." + element, true);
			}
			break;
		}
		case Id: {
			for (int count = 0; count < 5 && loop == 0; count++) {
				// Thread.sleep(2000);
				loop = driver.findElements(By.id(element)).size() > 0
						&& driver.findElement(By.id(element)).isDisplayed() ? 0 : 1;
			}
			if (loop == 1)
				flag = true;
			else {
				flag = false;
				Reporter.log("Element is displayed in this page." + element, true);
			}
			break;
		}
		case Xpath: {
			for (int count = 0; count < 5 && loop == 0; count++) {
				// Thread.sleep(2000);
				loop = driver.findElements(By.xpath(element)).size() > 0
						&& driver.findElement(By.xpath(element)).isDisplayed() ? 0 : 1;
			}
			if (loop == 1)
				flag = true;
			else {
				flag = false;
				Reporter.log("Element is displayed in this page." + element, true);
			}
			break;
		}
		default:
			break;
		}
		return flag;
	}

//    public static void connectPcloudyDevice() {
//       WebDriver driver;
//         DesiredCapabilities capabilities;
//        try {
//            capabilities = new DesiredCapabilities();
//            capabilities.setCapability("pCloudy_Username", "sumit.kumar@gupshup.io");
//            capabilities.setCapability("pCloudy_ApiKey", "gprtbq44dgvv8tyrwt5knxx9");
//            capabilities.setCapability("pCloudy_ApplicationName", "pCloudyAppiumDemo.apk");
//            capabilities.setCapability("pCloudy_DurationInMinutes", 5);
//            capabilities.setCapability("pCloudy_DeviceFullName", "Samsung_GalaxyTabA_Android_7.1.1");
//            driver = new AndroidDriver(new URL("https://device.pcloudy.com/appiumcloud/wd/hub"), capabilities);
//            System.out.println("reached here");
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//    }

	/**
	 * Execution Time Start
	 *
	 * @throws Exception
	 */
	public void executionTimeStart() throws Exception {
		DateTimeFormatter datetimeformat = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		starttime = datetimeformat.format(now);
	}

	/**
	 * Execution Time End
	 *
	 * @throws Exception
	 */
	public void executionTimeEnd() throws Exception {
		DateTimeFormatter datetimeformat = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalDateTime localtime = LocalDateTime.now();
		endtime = datetimeformat.format(localtime);
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date startdate = format.parse(starttime);
		Date enddate = format.parse(endtime);
		long difference = enddate.getTime() - startdate.getTime();
		Date timeDifference = new Date(difference);
		SimpleDateFormat simpledateformat = new SimpleDateFormat("HH:mm:ss:sss");
		simpledateformat.setTimeZone(TimeZone.getTimeZone("GMT"));
		executiontime = simpledateformat.format(timeDifference);
	}

	public void switchToAlertAccept() throws InterruptedException {
		driver.switchTo().alert().accept();
	}

	/**
	 * Press Space Key
	 *
	 * @author Praveen Kumar
	 */
	public void pressSpaceKey() {
		Actions builder = new Actions(driver);
		builder.sendKeys(Keys.SPACE).build().perform();
	}

	/**
	 * Press Escape Key
	 *
	 * @author Praveen Kumar
	 */
	public void pressEscapeKey() {
		Actions builder = new Actions(driver);
		builder.sendKeys(Keys.ESCAPE).build().perform();
	}

	/**
	 * Verify All Response API
	 *
	 * @param filename
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public static boolean verifyResponseAPI(String filename) throws FileNotFoundException, IOException {
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String access_Token = prop.getProperty("QAtoken");
		// System.out.println(access_Token);
		// String access_Token = "sat_e8e3f92cafa24f21ae46187953e255b4";
		DateTimeFormatter datetimestamp = DateTimeFormatter.ofPattern("ddMMyy");
		LocalDateTime stamp = LocalDateTime.now();
		String cronTodaydate = datetimestamp.format(stamp);
		DateTimeFormatter datetimestamp1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fromTodaydate = datetimestamp1.format(stamp);
		try {
			propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
			prop.load(new FileInputStream(propertyFilePath));
			JSONParser parser = new JSONParser();
			JSONObject config = (JSONObject) parser.parse(new FileReader(
					System.getProperty("user.dir") + File.separator + "payload" + File.separator + filename + ".json"));

			Map<String, String> requestParams = (Map<String, String>) config.get("payload");
			if (filename == "CronGetClickCount") {
				RestAssured.baseURI = config.get("baseurl").toString();
				request = RestAssured.given();
				Response response = request.queryParam("date", cronTodaydate).get();
				ResponseBody body = response.getBody();
				String bodyAsString = body.asString();
				Reporter.log(bodyAsString);
				jsonPathEvaluator = response.jsonPath();
				String temp = jsonPathEvaluator.get("status").toString();
				flag = temp.equals("success");
			} else if (filename == "GetClickCount") {
//				RestAssured.baseURI = config.get("baseurl").toString() + "from=" + fromTodaydate + "&to="
//						+ fromTodaydate;
				RestAssured.baseURI = config.get("baseurl").toString();
				System.out.println(RestAssured.baseURI);
				request = RestAssured.given().queryParam("from=", fromTodaydate).queryParam("&to=", fromTodaydate);
//				request.header("token", access_Token);
//				Response response = request.get();
				request.header("token", access_Token);
				Response response = request.when().get();

//				Response response = request.queryParam("from=", fromTodaydate).queryParam("&to=", fromTodaydate).get();
				ResponseBody body = response.getBody();
				String bodyAsString = body.asString();
				System.out.println(bodyAsString);
				Reporter.log(bodyAsString);
				jsonPathEvaluator = response.jsonPath();
				String totalChatCount = jsonPathEvaluator.get("analytics.totalChatCount").toString();
				String totalShareCount = jsonPathEvaluator.get("analytics.totalShareCount").toString();
				String totalSpinWheelCount = jsonPathEvaluator.get("analytics.totalSpinWheelCount").toString();
				flag = !totalChatCount.equals("0");
				if (flag) {
					flag = !totalShareCount.equals("0");
					if (flag) {
						flag = !totalSpinWheelCount.equals("0");
					}
				}
			} else if (filename == "GetShopID") {
				RestAssured.baseURI = config.get("baseurl").toString();
				request = RestAssured.given();
				request.header("token", access_Token);
//				Response response = request.queryParam("from=", fromTodaydate).queryParam("&to=", fromTodaydate).get();
				Response response = request.get();
				ResponseBody body = response.getBody();
				String bodyAsString = body.asString();
				Reporter.log(bodyAsString);
//				System.out.println(bodyAsString);
				jsonPathEvaluator = response.jsonPath();
				String temp = jsonPathEvaluator.get("status").toString();
				shopid = jsonPathEvaluator.get("shop.id").toString();
				// System.out.println("Shop id = " + shopid);
				flag = temp.equals("success");
			} else if (filename == "ChangeUltimatePlan") {
				RestAssured.baseURI = config.get("baseurl").toString() + shopid + "/plan-updated/3";
				System.out.println(RestAssured.baseURI);
				request = RestAssured.given();
				request.header("token", access_Token);
				request.header("Content-Type", "application/x-www-form-urlencoded");
				request.config(RestAssured.config().encoderConfig(
						EncoderConfig.encoderConfig().encodeContentTypeAs("x-www-form-urlencoded", ContentType.URLENC)))
						.contentType("application/x-www-form-urlencoded; charset=UTF-8").formParams("from", "settings");
				Response response = request.post();
			} else if (filename == "ChangeSmallBusinessPlan") {
				RestAssured.baseURI = config.get("baseurl").toString() + shopid + "/plan-updated/5";
				System.out.println(RestAssured.baseURI);
				request = RestAssured.given();
				request.header("token", access_Token);
				request.config(RestAssured.config().encoderConfig(
						EncoderConfig.encoderConfig().encodeContentTypeAs("x-www-form-urlencoded", ContentType.URLENC)))
						.contentType("application/x-www-form-urlencoded; charset=UTF-8").formParams("from", "settings");
				Response response = request.post();
			} else if (filename == "ChangeSmalltoFreePlan") {
				RestAssured.baseURI = config.get("baseurl").toString() + shopid + "/plan-updated/4";
				System.out.println(RestAssured.baseURI);
				request = RestAssured.given();
				request.header("token", access_Token);
				request.header("Content-Type", "application/x-www-form-urlencoded");
				request.config(RestAssured.config().encoderConfig(
						EncoderConfig.encoderConfig().encodeContentTypeAs("x-www-form-urlencoded", ContentType.URLENC)))
						.contentType("application/x-www-form-urlencoded; charset=UTF-8").formParams("from", "settings");
				Response response = request.post();
			} else if (filename == "ChangeWinbackInterval") {
				RestAssured.baseURI = config.get("baseurl").toString() + shopid + "/settings";
				System.out.println(RestAssured.baseURI);
				request = RestAssured.given();
				request.header("token", access_Token);
				request.header("Content-Type", "application/x-www-form-urlencoded");
				request.config(RestAssured.config().encoderConfig(
						EncoderConfig.encoderConfig().encodeContentTypeAs("x-www-form-urlencoded", ContentType.URLENC)))
						.contentType("application/x-www-form-urlencoded; charset=UTF-8").formParams("enabled", "true")
						.formParams("language", "ENGLISH").formParams("interval", "7").formParams("discount", "true")
						.formParams("discountType", "PERCENT").formParams("discountRatio", "20")
						.formParams("couponCode", "SUPSS10");
				Response response = request.put();
				ResponseBody body = response.getBody();
				String bodyAsString = body.asString();
				Reporter.log(bodyAsString);
//				System.out.println(bodyAsString);
				jsonPathEvaluator = response.jsonPath();
				String temp = jsonPathEvaluator.get("status").toString();
				flag = temp.equals("success");
				if (flag) {
					verifyResponseAPI("WinbackCrone");
				}
			} else if (filename == "ChangeFeedbackInterval") {
				RestAssured.baseURI = config.get("baseurl").toString() + shopid + "/settings";
				System.out.println(RestAssured.baseURI);
				request = RestAssured.given();
				request.header("token", access_Token);
				request.header("Content-Type", "application/x-www-form-urlencoded");
				request.config(RestAssured.config().encoderConfig(
						EncoderConfig.encoderConfig().encodeContentTypeAs("x-www-form-urlencoded", ContentType.URLENC)))
						.contentType("application/x-www-form-urlencoded; charset=UTF-8").formParams("enabled", "true")
						.formParams("language", "ENGLISH").formParams("interval", "7").formParams("discount", "true")
						.formParams("discountType", "PERCENT").formParams("discountRatio", "20")
						.formParams("couponCode", "SUPSS10");
				Response response = request.put();
				ResponseBody body = response.getBody();
				String bodyAsString = body.asString();
				Reporter.log(bodyAsString);
//				System.out.println(bodyAsString);
				jsonPathEvaluator = response.jsonPath();
				String temp = jsonPathEvaluator.get("status").toString();
				flag = temp.equals("success");
				if (flag) {
					verifyResponseAPI("FeedbackCrone");
				}
			} else if (filename == "ChangeCrosssellInterval") {
				RestAssured.baseURI = config.get("baseurl").toString() + shopid + "/settings";
				System.out.println(RestAssured.baseURI);
				request = RestAssured.given();
				request.header("token", access_Token);
				request.header("Content-Type", "application/x-www-form-urlencoded");
				request.config(RestAssured.config().encoderConfig(
						EncoderConfig.encoderConfig().encodeContentTypeAs("x-www-form-urlencoded", ContentType.URLENC)))
						.contentType("application/x-www-form-urlencoded; charset=UTF-8").formParams("enabled", "true")
						.formParams("language", "ENGLISH").formParams("interval", "7").formParams("discount", "true")
						.formParams("discountType", "PERCENT").formParams("discountRatio", "20")
						.formParams("couponCode", "SUPSS10");
				Response response = request.put();
				ResponseBody body = response.getBody();
				String bodyAsString = body.asString();
				Reporter.log(bodyAsString);
//				System.out.println(bodyAsString);
				jsonPathEvaluator = response.jsonPath();
				String temp = jsonPathEvaluator.get("status").toString();
				flag = temp.equals("success");
				if (flag) {
					verifyResponseAPI("CrosssellCrone");
				}
			} else if (filename == "WinbackCrone") {
				RestAssured.baseURI = config.get("baseurl").toString();
				System.out.println(RestAssured.baseURI);
				request = RestAssured.given();
				Response response = request.get();
			} else if (filename == "FeedbackCrone") {
				RestAssured.baseURI = config.get("baseurl").toString();
				System.out.println(RestAssured.baseURI);
				request = RestAssured.given();
				Response response = request.get();
			} else if (filename == "CrosssellCrone") {
				RestAssured.baseURI = config.get("baseurl").toString();
				System.out.println(RestAssured.baseURI);
				request = RestAssured.given();
				Response response = request.get();
			} else if (filename == "abcart") {
				RestAssured.baseURI = config.get("baseurl").toString();
				request = RestAssured.given();
				request.headers("Cookie", "IDESESSION=tvvmkldjkcbkkukb28hivacoa7").header("Content-Type", "text/plain")
//						.header("Content-Length","<calculated when request is sent>")
//						.header("Host", "<calculated when request is sent>")
						.header("User-Agent", "PostmanRuntime/7.32.2").header("Accept", "*/*")
						.header("Accept-Encoding", "gzip, deflate, br").header("Connection", "keep-alive")
						.header("Content-Type", "application/json").header("Cookie",
								"AWSALB=t/acsvp7zC1w1axHYLur8ThmVxpjPl1jxQ6212YN/M9QiOMxX1DWcM4AwmeVVC8I/3KfdmKhW+T2TVm/gz2jc/fDV5MC4RHnzAycsQzzvzxUdlklEkvQt9nTjY+a; AWSALBCORS=t/acsvp7zC1w1axHYLur8ThmVxpjPl1jxQ6212YN/M9QiOMxX1DWcM4AwmeVVC8I/3KfdmKhW+T2TVm/gz2jc/fDV5MC4RHnzAycsQzzvzxUdlklEkvQt9nTjY+a");
				request.body(((JSONObject) requestParams).toJSONString());
				Response response = request.post();
				ResponseBody body = response.getBody();
				int status = response.statusCode();
				System.out.println(status);
				if (status == 200) {
					flag = true;
				} else {
					flag = false;
				}
			} else if (filename == "GenerateToken") {
				RestAssured.baseURI = config.get("baseurl").toString() + "/test/token";
				request = RestAssured.given();
				request.headers("Cookie",
						"AWSALB=1zuBqAThPCST7gzbOJk1za2ZLxK+ZwAK4Kf+6cFOZ+ISrdsQdZVaWF7z5x3oKDVDGw+PxLBOEeun2xaHJStiPpUvCCHbAaIzYybykx17WCMMy2Z8z9IEKghP/kuz; AWSALBCORS=1zuBqAThPCST7gzbOJk1za2ZLxK+ZwAK4Kf+6cFOZ+ISrdsQdZVaWF7z5x3oKDVDGw+PxLBOEeun2xaHJStiPpUvCCHbAaIzYybykx17WCMMy2Z8z9IEKghP/kuz")
						.header("User-Agent", "PostmanRuntime/7.32.3").header("Accept", "*/*")
						.header("Accept-Encoding", "gzip, deflate, br").header("Connection", "keep-alive");
				Response response = request.get();
				ResponseBody body = response.getBody();
				String bodyAsString = body.asString();
				Reporter.log(bodyAsString);
				// System.out.println("token = " + bodyAsString);
				jsonPathEvaluator = response.jsonPath();
				token = jsonPathEvaluator.get("token").toString();
				System.out.println(token);
				int status = response.statusCode();
				// System.out.println("status = " + status);
				if (status == 200) {
					flag = true;
				} else {
					flag = false;
				}
			} else if (filename == "GenerateToken_Wix") {
				RestAssured.baseURI = config.get("baseurl").toString() + "/test/token";
				request = RestAssured.given();
				request.headers("Cookie",
						"AWSALB=1zuBqAThPCST7gzbOJk1za2ZLxK+ZwAK4Kf+6cFOZ+ISrdsQdZVaWF7z5x3oKDVDGw+PxLBOEeun2xaHJStiPpUvCCHbAaIzYybykx17WCMMy2Z8z9IEKghP/kuz; AWSALBCORS=1zuBqAThPCST7gzbOJk1za2ZLxK+ZwAK4Kf+6cFOZ+ISrdsQdZVaWF7z5x3oKDVDGw+PxLBOEeun2xaHJStiPpUvCCHbAaIzYybykx17WCMMy2Z8z9IEKghP/kuz")
						.header("User-Agent", "PostmanRuntime/7.32.3").header("Accept", "*/*")
						.header("Accept-Encoding", "gzip, deflate, br").header("Connection", "keep-alive");
				Response response = request.get();
				ResponseBody body = response.getBody();
				String bodyAsString = body.asString();
				Reporter.log(bodyAsString);
				// System.out.println("token = " + bodyAsString);
				jsonPathEvaluator = response.jsonPath();
				token = jsonPathEvaluator.get("token").toString();
				System.out.println(token);
				int status = response.statusCode();
				// System.out.println("status = " + status);
				if (status == 200) {
					flag = true;
				} else {
					flag = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public static boolean verifyChangePlanResponseAPI(String filename, int Planid)
			throws FileNotFoundException, IOException {
		propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
		prop.load(new FileInputStream(propertyFilePath));
		String access_Token = prop.getProperty("QAtoken");
		System.out.println(access_Token);
		try {
			propertyFilePath = System.getProperty("user.dir") + File.separator + "config.properties";
			prop.load(new FileInputStream(propertyFilePath));
			JSONParser parser = new JSONParser();
			JSONObject config = (JSONObject) parser.parse(new FileReader(
					System.getProperty("user.dir") + File.separator + "payload" + File.separator + filename + ".json"));
			if (filename == "ChangePlan") {
				RestAssured.baseURI = config.get("baseurl").toString() + shopid + "/plan-updated/" + Planid;
				System.out.println(RestAssured.baseURI);
				request = RestAssured.given();
				request.header("token", access_Token);
				request.config(RestAssured.config().encoderConfig(
						EncoderConfig.encoderConfig().encodeContentTypeAs("x-www-form-urlencoded", ContentType.URLENC)))
						.contentType("application/x-www-form-urlencoded; charset=UTF-8").formParams("from", "settings");
				Response response = request.post();
				driver.navigate().refresh();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * Scroll To Element
	 *
	 * @author Gokul S
	 * @param element
	 */
	public static void scrollElement(String element) {
		try {
			WebElement scrollElement = driver.findElement(By.xpath(element));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", scrollElement);
			js.executeScript("window.scrollBy(0,-180)", "");
			System.out.println("Scrolled to element " + element);
			// WebElement scrollElement = driver.findElement(By.xpath(element));
			// ((JavascriptExecutor)
			// driver).executeScript("arguments[0].scrollIntoView(false)", scrollElement);
		} catch (Exception e) {
			Reporter.log("Unable to scroll to an element: " + element + " in the page, with the Exception: " + e, true);
		}
	}

	/**
	 *
	 * @param element
	 * @return
	 * @throws InterruptedException
	 */
	public Boolean VerifyListOfElement(String element) throws InterruptedException {
		List<WebElement> Elements = driver.findElements(By.xpath(element));
		for (WebElement sample : Elements) {
			flag = sample.isDisplayed();
		}
		return flag;
	}

	/**
	 * Select the text from dropdown by using text
	 *
	 * @author Praveen Kumar R
	 * @param element
	 * @param value
	 * @return
	 */
	public boolean selectDropDownOptionsByText(String element, String value, ElementType elementtype) {
		try {
			switch (elementtype) {
			case Id: {
				Select dropdown = new Select(driver.findElement(By.id(element)));
				dropdown.selectByVisibleText(value);
				break;
			}

			case Name: {
				Select dropdown = new Select(driver.findElement(By.name(element)));
				dropdown.selectByVisibleText(value);
				break;
			}

			case Xpath: {
				Select dropdown = new Select(driver.findElement(By.xpath(element)));
				dropdown.selectByVisibleText(value);
				break;
			}

			case CssSelector: {
				Select dropdown = new Select(driver.findElement(By.cssSelector(element)));
				dropdown.selectByVisibleText(value);
				break;
			}

			case ClassName: {
				Select dropdown = new Select(driver.findElement(By.className(element)));
				dropdown.selectByVisibleText(value);
				break;
			}

			case LinkText: {
				Select dropdown = new Select(driver.findElement(By.linkText(element)));
				dropdown.selectByVisibleText(value);
				break;
			}
			default:
				break;
			}
		} catch (Exception e) {
//			reportLog("Element is not found in the page " + element + " Exception " + e);
//			Reporter.log("Element is not found in the page " + element + " Exception " + e, true);
			return false;
		}
		return flag;
	}

	/**
	 * Scroll To Element
	 *
	 * @author Gokul S
	 * @param element
	 * @throws InterruptedException
	 */
	public void scrollToAnElement(String element, ElementType elementType) throws InterruptedException {
		switch (elementType) {
		case Id: {
			WebElement scrollElement = driver.findElement(By.id(element));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false)", scrollElement);
			Thread.sleep(2500);
			break;
		}

		case Name: {
			WebElement scrollElement = driver.findElement(By.name(element));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false)", scrollElement);
			Thread.sleep(2500);
			break;
		}

		case Xpath: {
			WebElement scrollElement = driver.findElement(By.xpath(element));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false)", scrollElement);
			Thread.sleep(2500);
			break;
		}

		case CssSelector: {
			WebElement scrollElement = driver.findElement(By.cssSelector(element));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false)", scrollElement);
			Thread.sleep(2500);
			break;
		}

		case ClassName: {
			WebElement scrollElement = driver.findElement(By.className(element));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false)", scrollElement);
			Thread.sleep(2500);
			break;
		}

		case LinkText: {
			WebElement scrollElement = driver.findElement(By.linkText(element));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false)", scrollElement);
			Thread.sleep(2500);
			break;
		}
		default:
			break;
		}
	}

	/**
	 * Press Tab Key
	 *
	 * @author Praveen Kumar
	 */
	public void pressTabKey() {
		Actions builder = new Actions(driver);
		builder.sendKeys(Keys.TAB).build().perform();
	}

	/**
	 * Press Enter Key
	 *
	 * @author Praveen Kumar
	 */
	public void pressEnterKey() {
		Actions builder = new Actions(driver);
		builder.sendKeys(Keys.ENTER).build().perform();
	}

	/**
	 * Switch to Child Window
	 *
	 * @author Gokul S
	 * @param element
	 * @throws InterruptedException
	 */
	public void switchToChildWindow() throws InterruptedException {
		driver.switchTo().defaultContent();
		Thread.sleep(5000);
		String MainWindow = driver.getWindowHandle();
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();
		while (i1.hasNext()) {
			String ChildWindow = i1.next();
			if (!MainWindow.equalsIgnoreCase(ChildWindow)) {
				driver.switchTo().window(ChildWindow);
				Thread.sleep(5000);
				// driver.findElement(By.linkText(element)).click();
				break;
			}
		}
	}

	public Boolean switchToChildWindowAndVerifyURLText(String element, String data)
			throws InterruptedException, FileNotFoundException, IOException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
		Thread.sleep(3000);
		driver.findElement(By.xpath(element)).click();
		String MainWindow = driver.getWindowHandle();
		Set<String> s1 = driver.getWindowHandles();
		for (String a : s1) {
			if (!a.equalsIgnoreCase(MainWindow)) {
				driver.switchTo().window(a);
				Thread.sleep(8000);
				flag = driver.getCurrentUrl().contains(data);
				Thread.sleep(3000);
				driver.close();
			}
		}
		driver.switchTo().window(MainWindow);
		Thread.sleep(5000);
		return flag;
	}

	public boolean verifyElementIsDisplayed(String element, ElementType elementType) {
		try {
			switch (elementType) {
			case Id: {
				flag = driver.findElement(By.id(element)).isDisplayed();
				break;
			}

			case Name: {
				flag = driver.findElement(By.name(element)).isDisplayed();
				break;
			}

			case Xpath: {
				flag = driver.findElement(By.xpath(element)).isDisplayed();
				break;
			}

			case CssSelector: {
				flag = driver.findElement(By.cssSelector(element)).isDisplayed();
				break;
			}

			case ClassName: {
				flag = driver.findElement(By.className(element)).isDisplayed();
				break;
			}

			case LinkText: {
				flag = driver.findElement(By.linkText(element)).isDisplayed();
				break;
			}
			default:
				break;
			}
		} catch (Exception e) {
//			reportLog("Element is not found in the page " + element + " Exception " + e);
//          Reporter.log("Element is not found in the page " + element + " Exception " + e, true);

			return false;
		}
		return flag;
	}

	/**
	 * Clear text
	 *
	 * @param element
	 */
	public void clearText(String element) {
		Actions actions = new Actions(driver);
		WebElement toClear = driver.findElement(By.xpath(element));
		actions.moveToElement(toClear).click().keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE)
				.keyUp(Keys.CONTROL).build().perform();
	}

	/**
	 * Copy text from one element to another
	 *
	 * @param element
	 */

	public void copyPaste(String element, String value, String element2) {
		Actions actions = new Actions(driver);
		WebElement ele = driver.findElement(By.xpath(element));
		ele.sendKeys(value);
		actions.moveToElement(ele).click().keyDown(Keys.CONTROL).sendKeys("a").sendKeys("c").keyUp(Keys.CONTROL).build()
				.perform();

		WebElement ele2 = driver.findElement(By.xpath(element2));
		actions.moveToElement(ele2).click().keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();
	}

	public String getAttribute(String element, String Attriburevalue) {
		WebElement element1 = driver.findElement(By.xpath(element));
		String value = element1.getAttribute(Attriburevalue);
		return value;

	}
}
