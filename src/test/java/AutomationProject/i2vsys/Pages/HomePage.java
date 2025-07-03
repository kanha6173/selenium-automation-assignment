package AutomationProject.i2vsys.Pages;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AutomationProject.i2vsys.testcases.BaseTest;

public class HomePage  {
	private WebDriver driver;
    private WebDriverWait wait;

    private final By searchIcon = By.xpath("//mat-icon[@aria-label='Search']");
    private final By searchInput = By.xpath("//input[@id='search']");
    private final By resultLocator = By.xpath("//div[@class='ztree']/child::li[@id='HomeTreeComponenttreeID_2']");
    private final By cameraStatusLocator = By.id("errorMessagevideoContainer_4");
    private final By closeButton = By.xpath("//div[@id='close_btn4']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void verifyHomePageLoaded() {
        try {
           
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchIcon)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput)).sendKeys("Test1");

            
            WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(resultLocator));
            Actions actions = new Actions(driver);
            actions.doubleClick(resultElement).perform();

            
            WebElement cameraStatusElement = new WebDriverWait(driver, Duration.ofSeconds(20))
                    .until(ExpectedConditions.presenceOfElementLocated(cameraStatusLocator));

           
            String statusText = cameraStatusElement.getText().trim();
            if (statusText.equalsIgnoreCase("Trying to Connect......")) {
                System.out.println(" Bug Detected: Camera stuck at 'Trying to Connect......'");
            } else {
                System.out.println("Camera stream is working fine.");
            }

            // Click close
            wait.until(ExpectedConditions.elementToBeClickable(closeButton)).click();

        } catch (TimeoutException e) {
            System.out.println("Timeout waiting for camera status or element not found.");
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}
