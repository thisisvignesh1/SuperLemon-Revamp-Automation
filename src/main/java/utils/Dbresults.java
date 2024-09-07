package utils;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import locators.dbUpdateLocators;
import utils.DriverBase.ElementType;

public class Dbresults {

	public static WebDriver driver;
	private static DriverBase webdb = new DriverBase();
	private static String ProjectName = null;
	public static String tag;
	// private static String suiteName =
	// System.getProperty("cucumber.filter.tags").replaceAll("@", "");
	public static String testCasesExecuted = null;
	public static String Passed = null;
	public static String Failed = null;
	public static String Skipped = null;
	public static String date6;
	public static String date7;
	public static String testStarted;
	public static String testCompleted;
	public static String testStartedAt = null;
	public static String testCompletedAt = null;
	private static String totalTestCaseCount = null;
	private static String executedOn = null;
	public static Logger log = LogManager.getLogger(Dbresults.class);
	public static long executionStartTime;
	//public static Session session = null;
	
	public static Connection setDBConnection() throws ClassNotFoundException, SQLException {
		String dbURL = "jdbc:mysql://10.80.12.58:3306/AutomationRunAnalyics";
		String username = "superlemon";
		String password = "superL@m0n";
		Connection con = DriverManager.getConnection(dbURL, username, password);
		return con;
	}

//	public static Connection setDBConnection() throws ClassNotFoundException, SQLException {
//		//String dbURL = "jdbc:mysql://10.224.121.24";
//		String dbURL = "sl-mysql.servicesdbmq.svc.cluster.local:3306";
//		String username = "root";
//		String password = "gupshup";
//		Connection con = DriverManager.getConnection(dbURL, username, password);
//		return con;
//	}

//	public static Connection setDBConnection() throws ClassNotFoundException, SQLException, JSchException {
//		//Class.forName("com.mysql.cj.jdbc.Driver");
//		String dbURL = "jdbc:mysql://local:3306";
//		//String dbURL = "sl-mysql.servicesdbmq.svc.cluster.local:3306";
//		String username = "root";
//		String password = "gupshup";
//		Connection con = DriverManager.getConnection(dbURL, username, password);
//		// return con;
//		String rdsHostname = "mydb.123456789012.us-east-1.rds.amazonaws.com";
//		int rdsPort = 3306;
//		String rdsDbName = "tadpoleNew";
//		int forwardedPort = session.setPortForwardingL(0, rdsHostname, rdsPort);
//        System.out.println("SSH Connected. Port forwarding: " + forwardedPort);
//        // RDS database connection setup
//        String jdbcUrl = "jdbc:mysql://localhost:" + forwardedPort + "/" + rdsDbName;
//        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
//		Statement st = con.createStatement();
//		String query = "select * from customers where user_id='206'";
//		System.out.println(query);
//		ResultSet rs1;
//		rs1 = st.executeQuery(query);
//		con.close();
//		return con;
//	}

	public static void executedb() throws ParseException, InterruptedException {
		tag = System.getProperty("testType");
		if (System.getProperty("DBupdate").equalsIgnoreCase("Yes"))
			if (System.getProperty("Jenkins").equalsIgnoreCase("jenkins")) {
				if (tag.equalsIgnoreCase("Regression") || tag.equalsIgnoreCase("Sanity")) {
					try {
						// DriverBase.initBrowser();
						Thread.sleep(5000);
						execute();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else
				log.info("DB update is off");
	}

	public static void execute() throws InterruptedException, ParseException {
		String deviceOS = System.getProperty("os");
		String file = null;
		String Path = System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "ExtentReport"
				+ File.separator + "SuperLemon.html";
		if (deviceOS.contains("Linux")) {
			file = "file:///" + Path;
		} else if (deviceOS.contains("Windows") || deviceOS.contains("Android")) {
			file = Path;
		}
		log.info(file);
//		String path = "file:///" + System.getProperty("user.dir") + File.separator + "Test-Output" + File.separator
//				+ "ExtentReport" + File.separator + file;
//            log.info("-----------------------------------------------------------------------------------");
//            log.info("This is the path" + path);
//            log.info("-----------------------------------------------------------------------------------");
		DriverBase.driver.get(file);
		if (webdb.isElementDisplayed(dbUpdateLocators.dashboardPageButton, ElementType.Xpath)) {
			webdb.clickAnElement(dbUpdateLocators.dashboardPageButton, ElementType.Xpath);
			Thread.sleep(500);
			if (webdb.isElementDisplayed(dbUpdateLocators.testCasesExecuted, ElementType.Xpath)) {
				Passed = DriverBase.driver.findElement(By.xpath(dbUpdateLocators.Passed)).getText().toString();
				Thread.sleep(3000);
				Failed = DriverBase.driver.findElement(By.xpath(dbUpdateLocators.Failed)).getText().toString();
				Thread.sleep(3000);
				Skipped = DriverBase.driver.findElement(By.xpath(dbUpdateLocators.Skipped)).getText().toString();
				testCasesExecuted = String.valueOf(Integer.valueOf(Passed) + Integer.valueOf(Failed));
				Thread.sleep(3000);
//                    Others = sqldriver.findElement(By.xpath(dbUpdateLocators.Others)).getText().toString();
				String testStarted = DriverBase.driver.findElement(By.xpath(dbUpdateLocators.testStartedAt)).getText()
						.toString();
				Thread.sleep(1000);
				String testCompleted = DriverBase.driver.findElement(By.xpath(dbUpdateLocators.testCompletedAt))
						.getText().toString();
				Thread.sleep(3000);
				executedOn = DriverBase.driver.findElement(By.xpath(dbUpdateLocators.executedOn)).getText().toString();
				ProjectName = DriverBase.driver.findElement(By.xpath(dbUpdateLocators.ProjectName)).getText()
						.toString();
				Thread.sleep(3000);
				SimpleDateFormat formatter6 = new SimpleDateFormat("MMM d, yyyy hh:mm:ss a");
				SimpleDateFormat formatter7 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String date6 = formatter7.format(formatter6.parse(testStarted));
				SimpleDateFormat formatter8 = new SimpleDateFormat("MMM d, yyyy hh:mm:ss a");
				SimpleDateFormat formatter9 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String date7 = formatter9.format(formatter8.parse(testCompleted));
				Date date1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date6);
				testStartedAt = date6;
				testCompletedAt = date7;
				executionStartTime = date1.getTime();
				// Date date1 = new SimpleDateFormat("yyyy-MMM-dd
				// HH:mm:ss").parse(testStartedAt);
				// executionStartTime = date6.getTime();
				if (tag.equalsIgnoreCase("Sanity")) {
					totalTestCaseCount = "72";
				} else if (tag.equalsIgnoreCase("Regression")) {
					totalTestCaseCount = "254";
				} else {
					totalTestCaseCount = String.valueOf(Integer.valueOf(Passed) + Integer.valueOf(Failed));
				}
			}
		}
		log.info(testCasesExecuted);
		log.info(Passed);
		log.info(Failed);
		log.info(date6);
		log.info(date7);
		log.info(executedOn);
		log.info(totalTestCaseCount);
		log.info("start timestamp is" + executionStartTime);

		DriverBase.driver.close();
		updateresultsinDB();
	}

	@SuppressWarnings("unused")
	private static void updateresultsinDB() {
		try {
			Connection con = setDBConnection();
			Statement st = con.createStatement();
			String query = String.format(
					"INSERT INTO automation_progress(ProjectName,suiteName,testCasesExecuted,Passed,Failed,testStartedAt,testCompletedAt,totalTestCaseCount,executionStartTime) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s');",
					ProjectName, tag, testCasesExecuted, Passed, Failed, testStartedAt, testCompletedAt,
					totalTestCaseCount, executionStartTime);
//                String query = String.format("INSERT INTO automation_stats_self_serve(ProjectName,suiteName,testCasesExecuted,Passed,Failed,testStartedAt,testCompletedAt) VALUES ('%s','%s','%s','%s','%s','%s','%s');",ProjectName,suiteName,testCasesExecuted,Passed,Failed,testStartedAt,testCompletedAt);
			System.out.println(query);
			int rs1;
			rs1 = st.executeUpdate(query);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
