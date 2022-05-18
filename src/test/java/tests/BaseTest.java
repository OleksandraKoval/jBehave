package tests;

import common.DriverBinariesSetter;
import config.ConfigurationManager;
import java.io.IOException;
import lombok.SneakyThrows;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.HomePage;
import steps.BaseSteps;
import utils.logs.Log;

import static common.DriverBinariesSetter.driver;

public class BaseTest extends BaseSteps {

    @SneakyThrows
    @BeforeSuite
    protected void setUpProperties() {
        ConfigurationManager.loadProperties();
    }

    @BeforeClass
    public void classLevelSetup() throws IOException {
        Log.info("Tests are starting!");
        DriverBinariesSetter
                .setBinariesForUsedWebDriver(System.getProperty("driverManager"));
    }

    @AfterMethod
    public void teardown(ITestResult result) {
        if (System.getProperty("driverManager").equals("sauceLab")) {
            ((RemoteWebDriver) driver).executeScript("sauce:job-name=" + result.getName());
            String status = result.isSuccess() ? "passed" : "failed";
            ((RemoteWebDriver) driver).executeScript("sauce:job-result=" + status);
        }
    }

    @AfterClass
    public void closeDriver() {
        Log.info("Tests are ending!");
        driver.quit();
    }
}