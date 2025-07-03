package AutomationProject.i2vsys.testcases;

import AutomationProject.i2vsys.Pages.HomePage;
import AutomationProject.i2vsys.Pages.LoginPage;
import AutomationProject.i2vsys.Pages.ReportPage;
import AutomationProject.i2vsys.Utils.DataProviders;
import AutomationProject.i2vsys.Utils.ScreenshotUtils;

import java.util.concurrent.TimeoutException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

	@Test(dataProvider = "loginData", dataProviderClass = DataProviders.class)
	public void loginData(String baseUrl, String username, String password, String expectedResult)
			throws TimeoutException {
		driver.get(baseUrl);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);

		wait.until(driver -> driver.getCurrentUrl().contains("/home") || driver.getCurrentUrl().contains("/login"));

		String currentUrl = driver.getCurrentUrl();
		// System.out.println("URL after login attempt: " + currentUrl);

		if (expectedResult.equalsIgnoreCase("Success")) {
			Assert.assertTrue(currentUrl.contains("/home"),
					"Expected successful login but didn't reach /home. Actual URL: " + currentUrl);
			if (username.equals("admin") && password.equals("Admin@12345")) {
				HomePage homePage = new HomePage(driver);
				homePage.verifyHomePageLoaded();

				ReportPage reportPage = new ReportPage(driver);
				reportPage.generateReport();
			}

		} else {
			Assert.assertTrue(currentUrl.contains("/login"),
					"Expected failed login but navigated away from /login. Actual URL: " + currentUrl);
		}

	}

	@AfterMethod
    public void captureFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotUtils.captureScreenshot(driver, result.getName());
        }
	}
}
