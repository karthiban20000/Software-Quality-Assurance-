package TestCases;

import Base.BaseTest;
import Utils.ExcelHandler;
import Utils.TakeErrorScreenShots;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Pages.HomePage;
import Pages.SearchResultsPage;
import Pages.ProductPage;

public class EbaySearchTestBuyItNow extends BaseTest {

    @BeforeTest
    public void setup() {
        setUpBrowser();
    }

    @Test
    public void searchAndBuySamsungPhone() {
        HomePage homePage = new HomePage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        ProductPage productPage = new ProductPage(driver);

        // Initialize Excel Information
        String excelFilePath = "src/test/resources/testdata/TestData.xlsx";
        String sheetName = "Data";

        // Initialize ExcelUtils
        ExcelHandler excel = new ExcelHandler(excelFilePath, sheetName);

        // Read data
        String mobileBrand = excel.getCellData(1, 1); // Row 1, Column 1

        // Step 1: Search for Samsung phone
        homePage.searchFor(mobileBrand);
        setReportName("Buy It Now Scenario- Test Case 3");
        startTest("Buy It Now Scenario- Test Case 3");
        test = extent.createTest("Successful Searched", "System Successfully searched the item and get the result");
        String screenshotPath1 = TakeErrorScreenShots.takeScreenshot(driver, "SuccessfulSearch");
        test.pass("System Successfully searched the item and get the result").addScreenCaptureFromPath(screenshotPath1);

        // Step 2: Select the first product
        searchResultsPage.selectFirstProduct();
        test = extent.createTest("First Item Selected", "System Successfully searched the item and get the select the first result");
        String screenshotPath2 = TakeErrorScreenShots.takeScreenshot(driver, "FirstResultTaken");
        test.pass("System Successfully searched the item and select  the first result").addScreenCaptureFromPath(screenshotPath2);

        // Write data back to the Excel file
        excel.setCellData(1, 2, "Dell laptop selected", excelFilePath);

        // Step 3: Proceed to Buy It Now
        productPage.buyItNow();
        test = extent.createTest("Successfully click on Buy It Now", "System Successfully  click on Buy It Now");
        String screenshotPath3 = TakeErrorScreenShots.takeScreenshot(driver, "BuyItNow");
        test.pass("System Successfully  click on Buy It Now").addScreenCaptureFromPath(screenshotPath3);

        // Close workbook
        excel.closeWorkbook();
    }


}
