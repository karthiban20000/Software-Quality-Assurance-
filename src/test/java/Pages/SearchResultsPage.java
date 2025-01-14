package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.Set;

public class SearchResultsPage {
    WebDriver driver;

    // Locator for the first product in the search results
    @FindBy(xpath = "//*[@id=\"item1b16bedd77\"]/div/div[2]/a/div/span")
    WebElement firstProduct;

    @FindBy(xpath = "//*[@id=\"mainContent\"]/div[1]/div[3]/div/div/div[1]/span")
    WebElement itemPrice;

    // Constructor to initialize elements
    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method Used In Products Page
    public void selectFirstProduct() {

        String mainWindowHandle = driver.getWindowHandle();
        firstProduct.click();
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String handle : allWindowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }




    public double assertPrice() {
        String priceText = itemPrice.getText();
        System.out.println("Price of the first item: " + priceText);

        // Remove any currency code and extra characters
        String numericPrice = priceText.replaceAll("[^0-9.,]", "").replace(",", "");

        try {
            double actualPrice = Double.parseDouble(numericPrice);
            System.out.println("Parsed Price: " + actualPrice);
            return actualPrice;
        } catch (NumberFormatException e) {
            System.err.println("Failed to parse price: " + priceText);
            throw new NumberFormatException("Unable to parse the price: " + priceText);
        }
    }



    }

