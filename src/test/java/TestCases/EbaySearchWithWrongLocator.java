package TestCases;

import Base.BaseTest;
import Pages.HomePage;
import Pages.ProductPage;
import Pages.SearchResultsPage;
import Utils.ExcelHandler;
import Utils.TakeErrorScreenShots;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EbaySearchWithWrongLocator extends BaseTest {

    @BeforeTest
    public void setup() {

        setUpBrowser();
    }

    @Test
    public void searchAndBuySamsungPhone() {
        String excelFilePath = "src/test/resources/testdata/TestData.xlsx";
        String sheetName = "Data";
        // Initialize ExcelUtils
        ExcelHandler excel = new ExcelHandler(excelFilePath, sheetName);

        // Read data
        String Brand = excel.getCellData(1, 1); // Row 1, Column 0


        HomePage homePage = new HomePage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        ProductPage productPage = new ProductPage(driver);


        // Step 1: Search for Samsung phone
        homePage.searchFor(Brand);
        setReportName("Wrong Locator Scenario- Test Case 4");
        startTest("Wrong Locator Scenario- Test Case 4");
        // Write data back to the Excel file
        excel.setCellData(1, 2, "Dell laptop selected", excelFilePath);

        // Close workbook
        excel.closeWorkbook();
        test = extent.createTest("Successful Searched", "System Successfully searched the item and get the result");
        String screenshotPath1 = TakeErrorScreenShots.takeScreenshot(driver, "SuccessfulSearch");
        test.pass("System Successfully searched the item and get the result").addScreenCaptureFromPath(screenshotPath1);
        // Step 2: Select the first product
        searchResultsPage.selectFirstProduct();
        test = extent.createTest("First Item Selected", "System Successfully searched the item and get the select the first result");
        String screenshotPath2 = TakeErrorScreenShots.takeScreenshot(driver, "FirstResultTaken");
        System.out.println(screenshotPath2);
        test.pass("System Successfully searched the item and select  the first result").addScreenCaptureFromPath(screenshotPath2);

        try {
            // Intentionally use a wrong locator
            productPage.wrongElementTest();

            // If no exception, log pass (this shouldn't happen here)
            test.pass("Element found and clicked successfully (unexpected).");
        } catch (Exception e) {
            // Capture screenshot on failure
            String screenshotPath3 = TakeErrorScreenShots.takeScreenshot(driver, "wrong_locator_failure");

            // Log the error and attach the screenshot to the Extent Report
            test.fail("Test failed due to incorrect locator.").addScreenCaptureFromPath(screenshotPath3).fail(e.getMessage());
        }
    }


}
