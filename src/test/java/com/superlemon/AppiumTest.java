package com.superlemon;

import commonFunctions.CommonFunctions;
import commonFunctions.ExtensionAndroidApp;
import commonFunctions.WidgetPage;
import org.apache.log4j.Logger;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import utils.DriverBase;
import utils.Mailer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.expectThrows;


public class AppiumTest {
    private static Logger log = Logger.getLogger(AppiumTest.class.getName());
    boolean flag = false;
    String templateName;
    private Mailer mailer;
    private static String startTime;
    private static String endTime;
    
    @BeforeTest
    public void openAppAndLogin() throws MalformedURLException {
        ExtensionAndroidApp.initBrowser();
        ExtensionAndroidApp.loginToApp();
    }

    @Test(description = "Verify that user is able to add Tag to the orders",priority = 1)
    public void checkTagAdditionToOrders() throws FileNotFoundException, InterruptedException, IOException {
        ExtensionAndroidApp.navigateToSettingsTab();
        String tagName =ExtensionAndroidApp.createNewTag();
        ExtensionAndroidApp.navigateToOrdersPage();
        flag = ExtensionAndroidApp.verifyTagAdditionToOrders(tagName);
        assertEquals(flag, true);
    }


    @Test(description = "Verify that user is able to remove the tags added to orders", groups = "ExtensionApp",priority =  2)
    public void checkTagRemovalFromOrders() throws FileNotFoundException, InterruptedException, IOException {
        ExtensionAndroidApp.navigateToSettingsTab();
        String tagName =ExtensionAndroidApp.createNewTag();
        ExtensionAndroidApp.navigateToOrdersPage();
        ExtensionAndroidApp.verifyTagAdditionToOrders(tagName);
        flag = ExtensionAndroidApp.verifyRemoveTagsFromOrders(tagName);
        assertEquals(flag, false);
    }

    @Test(description = "Verify that user is able to create new tags in the settings sections fo the app", groups = "ExtensionApp",priority = 3)
    public void checkNewTagCreation() throws FileNotFoundException, InterruptedException, IOException {
        flag = ExtensionAndroidApp.verifyTagNameCreation();
        assertEquals(flag, true);
    }

    @Test(description = "Verify that user is able to cancel the creation of a new tag and navigate back to settings successfully", groups = "ExtensionApp",priority = 4)
    public void checkCancelNewTagCreation() throws FileNotFoundException, InterruptedException, IOException {
        ExtensionAndroidApp.navigateToSettingsTab();
        ExtensionAndroidApp.cancelNewTagCreation();
//        flag = ExtensionAndroidApp.verifyRemoveTagsFromOrders();
        assertEquals(flag, false);
    }

    @Test(description = "Verify that Add button  gets disabled with a message Too Long on adding more than 40 characters more  while new tag creation  ", groups = "ExtensionApp",priority =  5)
    public void checkTagNameCharactersLimit() throws FileNotFoundException, InterruptedException, IOException {
        ExtensionAndroidApp.navigateToSettingsTab();
       flag =  ExtensionAndroidApp.verifyTagNameCharactersLimit();
        assertEquals(flag, true);
    }

    @Test(description = "Verify that user is able to search a partocular order by the  name associated with the order..", groups = "ExtensionApp",priority =  6)
    public void checkSearchOrder() throws FileNotFoundException, InterruptedException, IOException {

        flag =  ExtensionAndroidApp.verifySearchOrderByCustomerName();
        assertEquals(flag, true);
    }

    @Test(description = "Verify that user is able to filter the orders with the tag name added to the orders. ", groups = "ExtensionApp",priority =  7)
    public void checkFilterOrdersWithTagName() throws FileNotFoundException, InterruptedException, IOException {

        flag =  ExtensionAndroidApp.verifyFilterOrdersWithTagName();
        assertEquals(flag, true);
    }

    @Test(description = "Verify that user is able to set the expanded view from the settings and view the same on the orders section", groups = "ExtensionApp",priority =   8)
    public void checkExpandedView() throws FileNotFoundException, InterruptedException, IOException {

        flag =  ExtensionAndroidApp.verifyExpandedView();
        assertEquals(flag, true);
    }

    @Test(description = "Verify that user is able to clear the applied tag name filter applied to the orders.", groups = "ExtensionApp",priority =9)
    public void verifyCancelAppliedFilter() throws FileNotFoundException, InterruptedException, IOException {

        flag =  ExtensionAndroidApp.cancelAppliedFilter();
        assertEquals(flag, true);
    }

    @Test(description = "Verify that user is able to delete the created tag from the settings section", groups = "ExtensionApp",priority =10)
    public void verifyDeleteTag() throws FileNotFoundException, InterruptedException, IOException {

        flag =  ExtensionAndroidApp.deleteTagName();
        assertEquals(flag, false);
    }

    @Test(description = "Verify that user is able to edit and update the tag name of the already created tags.", groups = "ExtensionApp",priority = 11)
    public void editTagName() throws FileNotFoundException, InterruptedException, IOException {

        flag =  ExtensionAndroidApp.editTagName();
        assertEquals(flag, false);
    }

//    @AfterMethod
//    public void logOut() throws InterruptedException {
//    ExtensionAndroidApp.verifyLogOut();
//    }
 //   @AfterSuite
//    public void sendEmail() throws Exception {
//        mailer.execute();
//    }

    @AfterTest(alwaysRun = true)
    public void checkLogOut() throws FileNotFoundException, InterruptedException, IOException {
        ExtensionAndroidApp.verifyLogOut();
    }
}
