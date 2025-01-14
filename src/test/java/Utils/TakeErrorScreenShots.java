package Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TakeErrorScreenShots {

    // Method to capture a screenshot
    public static String takeScreenshot(WebDriver driver, String screenshotName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        //String filePath = "src/test/resources/ScreenShots/" + screenshotName + "_" + timestamp + ".png";
        String filePath = "C:/Users/Home/OneDrive/Desktop/AssignmentThreeWithPOM/AssignmentThreeWithPOM/src/test/resources/ScreenShots" + screenshotName + "_" + timestamp + ".png";
        // Take the screenshot
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(filePath);

        try {
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot saved at: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save screenshot: " + e.getMessage());
        }

        return filePath; // Return the screenshot path for reporting purposes
    }
}
