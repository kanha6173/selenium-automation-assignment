package AutomationProject.i2vsys.Pages;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

public class ReportPage  {
	private WebDriver driver;
	private WebDriverWait wait;

	private final By reportIcon = By.xpath("//ul[@class='nav navbar-nav']/li[@title='Report']");
	private final By dropdownLocator = By.xpath("//select[@id='selectedInterval']");

	public ReportPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	public void generateReport() throws TimeoutException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(reportIcon)).click();

			WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownLocator));
			Select select = new Select(dropdown);
			select.selectByValue("Last Month");

			System.out.println("Report for 'Last Month' generated.");
			By noRecordLocator = By.xpath("//tr[@class='k-grid-norecords ng-star-inserted']/td");
			WebElement noRecordElement = wait.until(ExpectedConditions.presenceOfElementLocated(noRecordLocator));

			String noRecordText = noRecordElement.getText().trim();

			if (noRecordText.equalsIgnoreCase("No records available.")) {
				System.out.println("Report Result: " + noRecordText);
			} else {
				System.out.println("Data is present in the report table.");
			}
		} catch (NoSuchElementException e) {
			System.out.println("Element not found: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Unexpected error: " + e.getMessage());
		}

	}
}
