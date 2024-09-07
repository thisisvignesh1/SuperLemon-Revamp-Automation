package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.Platform;

/**
 * @author Sumit
 * @company Galaxyweblinks
 * July 23, 2021
 */

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	public static String fileName = null;
	private static ExtentReports extent;
	public static String timeStamp = new SimpleDateFormat("yyyy.MM.dd .HH").format(new Date());
	private static String releaseVersion = System.getProperty("release");
	private static Platform platform;
	private static String testType = System.getProperty("testType");
	private static String reportFileName = "SuperLemon.html";
	private static String macPath = System.getProperty("user.dir") + "/test-output/ExtentReport";
	private static String windowsPath = System.getProperty("user.dir") + "\\test-output\\ExtentReport";
	private static String macReportFileLoc = macPath + "/" + reportFileName;
	private static String winReportFileLoc = windowsPath + "\\" + reportFileName;
	private static String env = System.getProperty("env");

	public static ExtentReports getInstance() {
		if (extent == null)
			extent = createInstance();
		return extent;
	}

	public static ExtentSparkReporter htmlReporter;

	/**
	 * Create an extent report instance
	 *
	 * @author Sumit
	 * @return extent report
	 */
	public static ExtentReports createInstance() {
		platform = getCurrentPlatform();
		fileName = getReportFileLocation(platform);
		htmlReporter = new ExtentSparkReporter(fileName);
		htmlReporter.config().setReportName("Gupshup SuperLemon Automation Report");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Build", releaseVersion);
		extent.setSystemInfo("Environment", env);
		extent.setSystemInfo("testType", testType);
		extent.setSystemInfo("Platform_Name", " Windows");
		extent.setSystemInfo("User Name", "Praveen Kumar");
		htmlReporter.config().setDocumentTitle("Gupshup SuperLemon Extent Report");
		htmlReporter.config().setTheme(Theme.STANDARD);
		return extent;
	}

	/**
	 * Select the extent report file location based on platform
	 *
	 * @author Sumit
	 * @param platform
	 * @return file location
	 */
	private static String getReportFileLocation(Platform platform) {
		String reportFileLocation = null;
		switch (platform) {
		case MAC:
			reportFileLocation = macReportFileLoc;
			createReportPath(macPath);
			System.out.println("ExtentReport Path for MAC: " + macPath + "\n");
			break;
		case LINUX:
			reportFileLocation = macReportFileLoc;
			createReportPath(macPath);
			System.out.println("ExtentReport Path for Linux: " + macPath + "\n");
			break;

		case WINDOWS:
			reportFileLocation = winReportFileLoc;
			createReportPath(windowsPath);
			System.out.println("ExtentReport Path for WINDOWS: " + windowsPath + "\n");
			break;
		default:
			System.out.println("ExtentReport path has not been set! There is a problem!\n");
			break;
		}
		return reportFileLocation;
	}

	/**
	 * Create the report path if it does not exist
	 *
	 * @author Sumit
	 * @param path
	 */
	private static void createReportPath(String path) {
		File testDirectory = new File(path);
		if (!testDirectory.exists()) {
			if (testDirectory.mkdir()) {
				System.out.println("Directory: " + path + " is created!");
			} else {
				System.out.println("Failed to create directory: " + path);
			}
		} else {
			System.out.println("Directory already exists: " + path);
		}
	}

	/**
	 * Get current platform
	 *
	 * @author Sumit
	 * @return platformName
	 */
	private static Platform getCurrentPlatform() {
		if (platform == null) {
			String operSys = System.getProperty("os.name").toLowerCase();
			if (operSys.contains("win")) {
				platform = Platform.WINDOWS;
			} else if (operSys.contains("nix") || operSys.contains("nux") || operSys.contains("aix")) {
				platform = Platform.LINUX;
			} else if (operSys.contains("mac")) {
				platform = Platform.MAC;
			}
		}
		return platform;
	}
}