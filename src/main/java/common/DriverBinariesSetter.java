package common;

import com.codeborne.selenide.WebDriverRunner;
import config.ConfigurationManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;

import static common.DriverBinariesSetter.Drivers.*;
import static common.helper.EncodingManager.decryptString;


public final class DriverBinariesSetter {

    private static final DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    static String key = System.getProperty("secretKey");
    private static final String sauceLabUserName =
            decryptString(ConfigurationManager.getProperty("sauceLab.username"), key);
    private static final String sauceLabAccessKey =
            decryptString(ConfigurationManager.getProperty("sauceLab.access.key"), key);
    private DriverBinariesSetter() {
    }

    public static WebDriver driver;

    public static void setBinariesForUsedWebDriver(String browser) throws IOException,
            GeneralSecurityException {
        //Java 14 switch
        switch (Drivers.valueOf(browser)) {
            case EDGE -> setBinaryByName(EDGE);
            case FIREFOX -> setBinaryByName(FIREFOX);
            case SAUCE_LAB_CHROME -> setBinaryByName(SAUCE_LAB_CHROME);
            case SAUCE_LAB_FIREFOX -> setBinaryByName(SAUCE_LAB_FIREFOX);
            case SAUCE_LAB_EDGE -> setBinaryByName(SAUCE_LAB_EDGE);
            case CHROME -> setBinaryByName(CHROME);
            default -> throw new IllegalStateException("Unexpected value: " + browser);
        }
    }

    private static void setBinaryByName(Drivers name) throws IOException,
            GeneralSecurityException {
        //Java 14 switch
        switch (name) {
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            case EDGE -> {
                WebDriverManager.edgedriver()
                        .driverVersion(System.getProperty("edge.driver.version",
                                StringUtils.EMPTY)).setup();
                driver = new EdgeDriver();
            }
            case SAUCE_LAB_CHROME -> setSauceLabRemoteDriver();
            case SAUCE_LAB_FIREFOX -> callSauceLabPropertiesFirefox();
            case SAUCE_LAB_EDGE -> callSauceLabPropertiesEdge();
            default -> {
                WebDriverManager.chromedriver()
                        .driverVersion(System.getProperty("chrome.driver.version",
                                StringUtils.EMPTY)).setup();
                driver = new ChromeDriver();
            }
        }
        WebDriverRunner.setWebDriver(driver);
    }

    private static void setSauceLabRemoteDriver() throws IOException,
            GeneralSecurityException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--lang=ukr");
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        desiredCapabilities.setCapability("browserName", CHROME);
        desiredCapabilities.setCapability("browser", ConfigurationManager.getProperty("sauceLab.chrome.version"));
        setDesiredCapabilities();
    }

    private static void callSauceLabPropertiesFirefox() throws IOException,
            GeneralSecurityException {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--lang=ukr");
        desiredCapabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
        desiredCapabilities.setCapability("browserName", FIREFOX);
        desiredCapabilities.setCapability("browser", ConfigurationManager.getProperty("sauceLab.firefox.version"));
        setDesiredCapabilities();
    }

    private static void callSauceLabPropertiesEdge() throws IOException,
            GeneralSecurityException {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--lang=ukr");
        desiredCapabilities.setCapability(EdgeOptions.CAPABILITY, edgeOptions);
        desiredCapabilities.setCapability("browserName", EDGE);
        desiredCapabilities.setCapability("browser", ConfigurationManager.getProperty("sauceLab.edge.version"));
        setDesiredCapabilities();
    }


    private static void setDesiredCapabilities() throws IOException, GeneralSecurityException {
        desiredCapabilities.setCapability("platform", ConfigurationManager.getProperty("sauceLab.platform.version"));
        desiredCapabilities.setCapability("avoidProxy", true);
        desiredCapabilities.setCapability("username", sauceLabUserName);
        desiredCapabilities.setCapability("accessKey", sauceLabAccessKey);
        driver = new RemoteWebDriver(getSauceLabUrl(), desiredCapabilities);
    }

    private static URL getSauceLabUrl() throws MalformedURLException {
        return new URL(ConfigurationManager.getProperty("sauceLab.protocol") + sauceLabUserName + ":" + sauceLabAccessKey +
                ConfigurationManager.getProperty("sauceLab.url"));

    }


    public enum Drivers {
        EDGE("edge"),
        FIREFOX("firefox"),
        CHROME("chrome"),
        SAUCE_LAB_CHROME("sauceLabChrome"),
        SAUCE_LAB_FIREFOX("sauceLabFirefox"),
        SAUCE_LAB_EDGE("sauceLabEdge");

        private final String drivers;

        Drivers(String driver) {
            this.drivers = driver;
        }

        public String getDriver() {
            return drivers;
        }

    }
}
