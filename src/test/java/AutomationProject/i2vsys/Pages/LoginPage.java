package AutomationProject.i2vsys.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class LoginPage {

	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(id = "mat-input-0")
	private WebElement usernameField;

	@FindBy(id = "mat-input-1")
	private WebElement passwordField;

	@FindBy(xpath = "//button[@aria-label='LOGIN']")
	private WebElement loginButton;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void login(String username, String password) {
		wait.until(ExpectedConditions.visibilityOf(usernameField));
		usernameField.clear();
		usernameField.sendKeys(username);
		passwordField.clear();
		passwordField.sendKeys(password);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(loginButton));
			loginButton.click();
		} catch (TimeoutException e) {
			System.out.println("Login button not clickable. Skipping click.");
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
