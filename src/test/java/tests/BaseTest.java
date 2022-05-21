package tests;

import common.helper.DriverManager;
import config.ConfigurationManager;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import steps.BaseSteps;
import utils.logs.Log;

import java.io.IOException;

import static common.DriverBinariesSetter.driver;

public class BaseTest {

    protected BaseSteps baseSteps = new BaseSteps();

    @SneakyThrows
    @BeforeSuite
    protected void setUpProperties() {
        ConfigurationManager.loadProperties();
    }

    @BeforeClass
    public void classLevelSetup() throws IOException {
        Log.info("Tests are starting!");
        DriverManager.setBinariesForUsedWebDriver();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        DriverManager.setSauceLabJobNameAndStatus(result);
    }

    @AfterClass
    public void closeDriver() {
        Log.info("Tests are ending!");
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }
}