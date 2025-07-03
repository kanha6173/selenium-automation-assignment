package AutomationProject.i2vsys.Utils;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "loginData")
    public static Object[][] loginDataProvider() {
        return new Object[][]{
            { "http://172.24.0.67:4777", "admin", "Admin@12345", "Success" },
            { "http://172.24.0.67:4777", "kanha", "Admin@12345", "Fail" },
            { "http://172.24.0.67:4777", "admin", "aa", "Fail" },
            { "http://172.24.0.67:4777", "", "", "Fail" }
        };
    }
}
