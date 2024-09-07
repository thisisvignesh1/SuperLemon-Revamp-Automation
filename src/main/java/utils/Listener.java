package utils;

import java.util.Objects;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

/**
 * @author Sumit
 * @company Galaxyweblinks
 * October 11, 2021
 */

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

@SuppressWarnings({ "unused" })
public class Listener extends DriverBase implements ITestListener {

	private static Logger log = Logger.getLogger(Listener.class);
	ExtentReports extent = ExtentManager.getInstance();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	private static DriverBase webDB = new DriverBase();
	private static long epoch;
	private static String startTime;
	private static String endTime;
	public static int Testcase_passed;
	public static int Testcase_failed;
	public static int Testcase_skipped;


	/**
	 * While test is started
	 *
	 * @author Sumit
	 */
	@Override
	public synchronized void onStart(ITestContext context) {
//        try {
//            epoch = updateStartTimeOfExecution();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        try{
//            setTestType(epoch);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
		System.out.println("Extent Report started!");
	}

	/**
	 * While test is finished
	 *
	 * @author Sumit
	 */
	@Override
	public synchronized void onFinish(ITestContext context) {
		log.info(("Extent Report ends!"));
//        try {
//            updateTestCaseExecutedCount(epoch);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//		 endTime =  new SimpleDateFormat("HH:mm:ss").format(new Date());
//		try {
//			DriverBase.getTestCaseExecutionTime(startTime,endTime);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//        try {
//            updateEndTimeOfExecution(epoch);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
		extent.flush();
	}

	/**
	 * When the test begins
	 *
	 * @author Sumit
	 */
	@Override
	public synchronized void onTestStart(ITestResult result) {
		log.info(result.getMethod().getMethodName() + " started!");
//        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
//                result.getMethod().getDescription());
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
//		startTime =  new SimpleDateFormat("HH:mm:ss").format(new Date());
		test.set(extentTest);
//        try {
//            updateTotalTestCasesCount(epoch);
//            updateProjectName();
//        }catch (ParseException e) {
//            e.printStackTrace();
//        }
	}

	/**
	 * While test is passed
	 *
	 * @author Sumit
	 */
	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		Testcase_passed++;
		log.info((result.getMethod().getMethodName() + " passed!"));
		String methodName = result.getMethod().getMethodName();
		log.info("methodName " + methodName);
//        try {
//            updatePassedCasesCount(epoch);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//		String instanceId = DriverBase.getInstance(methodName);
//		log.info("methodName"+instanceId);
//		DriverBase.executeSuccessApi(instanceId);
//		test.get().pass(result.getMethod().getMethodName() + " passed!");
//		try {
//			DriverBase.getScreenshot(webDB, "PassedCaseScreenshot");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		DriverBase.executeFailedApi();
	}

	/**
	 * While test is failure
	 *
	 * @author R.Praveen Kumar
	 */
	@Override
	public synchronized void onTestFailure(ITestResult result) {
		Testcase_failed++;
		WebDriver driver = DriverBase.driver;
		String base64Screenshot = "data:image/png;base64,"
				+ ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
		log.info((result.getMethod().getMethodName() + " failed!"));
		test.get().log(Status.FAIL, result.getMethod().getMethodName() + " failed!");
		String methodName = result.getMethod().getMethodName();
		log.info("methodName" + methodName);
//		String instanceId = DriverBase.getInstance(methodName);
//		log.info("methodName"+instanceId);
//		DriverBase.executeFailedApi(instanceId);
		test.get().fail(result.getThrowable());
		test.get().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0);

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (Exception e) {
//		e.printStackTrace();
//			test.get().fail(result.getThrowable());
		}
//        try {
//            takeScreenShotPath(methodName, driver);
//        } catch (Exception e) {
//            e.printStackTrace();
//            test.get().fail(result.getThrowable());
//        }
//        try {
//            updateFailedCasesCount(epoch);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
	}

	/**
	 * While test is skipped
	 *
	 * @author Sumit
	 */
	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		Testcase_skipped++;
		System.out.println((result.getMethod().getMethodName() + " skipped!"));
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
				result.getMethod().getDescription());
		test.set(extentTest);
		test.get().skip(result.getThrowable());
		test.get().log(Status.SKIP, result.getMethod().getDescription());
		extent.flush();
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (Exception e) {
//		e.printStackTrace();
//			test.get().fail(result.getThrowable());
		}
	}

	/**
	 * While test on Test Failed But With in Success Percentage
	 *
	 * @author Sumit
	 */
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
	}

	/**
	 * Method for adding logs passed from test cases
	 *
	 * @param message
	 * @author Sumit
	 */
	public static void reportLog(String message) {
		test.get().log(Status.INFO, message);// For extentTest HTML report
		Reporter.log(message);
	}
}