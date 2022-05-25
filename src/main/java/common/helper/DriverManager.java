package common.helper;

import common.DriverBinariesSetter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.security.GeneralSecurityException;

import static common.DriverBinariesSetter.driver;

public class DriverManager {

    public static void setBinariesForUsedWebDriver() throws IOException,
            GeneralSecurityException {
        DriverBinariesSetter
                .setBinariesForUsedWebDriver(System.getProperty("driverManager"));
    }

    public static void setSauceLabJobNameAndStatus(ITestResult result)  {
        if (System.getProperty("driverManager").contains("SAUCE")) {
            ((RemoteWebDriver) driver).executeScript("sauce:job-name=" + result.getName());
            String status = result.isSuccess() ? "passed" : "failed";
            ((RemoteWebDriver) driver).executeScript("sauce:job-result=" + status);
        }
    }
}
