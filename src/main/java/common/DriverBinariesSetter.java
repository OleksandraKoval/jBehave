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

import java.net.MalformedURLException;
import java.net.URL;

import static common.DriverBinariesSetter.Drivers.*;


public final class DriverBinariesSetter {

    private static final String SAUCE_URL =
            "https://" + ConfigurationManager.getProperty("sauceLab.username") + ":" + ConfigurationManager.getProperty("sauceLab.access.key") +
            ConfigurationManager.getProperty("sauceLab.url");

    private DriverBinariesSetter() {
    }

    public static WebDriver driver;

    public static void setBinariesForUsedWebDriver(String browser) throws MalformedURLException {
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

    private static void setBinaryByName(Drivers name) throws MalformedURLException {
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

    private static void setSauceLabRemoteDriver() throws MalformedURLException {
        URL url = new URL(SAUCE_URL);
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--lang=ukr");
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        desiredCapabilities.setCapability("browserName", CHROME);
        desiredCapabilities.setCapability("browser", ConfigurationManager.getProperty("sauceLab.chrome.version"));
        setDesiredCapabilities();
        driver = new RemoteWebDriver(url, desiredCapabilities);
    }

    private static void callSauceLabPropertiesFirefox() throws MalformedURLException {
        URL url = new URL(SAUCE_URL);
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--lang=ukr");
        desiredCapabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
        desiredCapabilities.setCapability("browserName", FIREFOX);
        desiredCapabilities.setCapability("browser", ConfigurationManager.getProperty("sauceLab.firefox.version"));
        setDesiredCapabilities();
        driver = new RemoteWebDriver(url, desiredCapabilities);
    }

    private static void callSauceLabPropertiesEdge() throws MalformedURLException {
        URL url = new URL(SAUCE_URL);
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--lang=ukr");
        desiredCapabilities.setCapability(EdgeOptions.CAPABILITY, edgeOptions);
        desiredCapabilities.setCapability("browserName", EDGE);
        desiredCapabilities.setCapability("browser", ConfigurationManager.getProperty("sauceLab.edge.version"));
        setDesiredCapabilities();
        driver = new RemoteWebDriver(url, desiredCapabilities);
    }


    private static void setDesiredCapabilities() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platform", ConfigurationManager.getProperty("sauceLab.platform.version"));
        desiredCapabilities.setCapability("avoidProxy", true);
        desiredCapabilities.setCapability("username", ConfigurationManager.getProperty("sauceLab.username"));
        desiredCapabilities.setCapability("accessKey", ConfigurationManager.getProperty("sauceLab.access.key"));
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
