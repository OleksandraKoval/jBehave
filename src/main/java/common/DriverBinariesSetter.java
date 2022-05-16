package common;

import com.codeborne.selenide.WebDriverRunner;
import config.ConfigurationManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public final class DriverBinariesSetter {

    private DriverBinariesSetter() {
    }

    public static WebDriver driver;

    private static final String EDGE = "edge";
    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";
    private static final String SAUCE_LAB = "sauceLab";


    public static void setBinariesForUsedWebDriver(String browser) throws MalformedURLException {
        //Java 14 switch
        switch (browser) {
            case EDGE -> setBinaryByName(EDGE);
            case FIREFOX -> setBinaryByName(FIREFOX);
            case SAUCE_LAB -> setBinaryByName(SAUCE_LAB);
            default -> setBinaryByName(CHROME);
        }
    }

    private static void setBinaryByName(String name) throws MalformedURLException {
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
            case SAUCE_LAB -> callSauceLabProperties();
            default -> {
                WebDriverManager.chromedriver()
                        .driverVersion(System.getProperty("chrome.driver.version",
                                StringUtils.EMPTY)).setup();
                driver = new ChromeDriver();
            }
        }
        WebDriverRunner.setWebDriver(driver);
    }

    private static void callSauceLabProperties() throws MalformedURLException {
        URL url = new URL("https://oauth-kulyasaleksandra-cb8f8:5805de78-2c54-423a-bf5e-bbd008467f6f@ondemand" +
                ".eu-central-1.saucelabs.com:443/wd/hub");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--lang=ukr");
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        desiredCapabilities.setCapability("browserName", "chrome");
        desiredCapabilities.setCapability("browser", "latest");
        desiredCapabilities.setCapability("platform", Platform.WIN10);
        desiredCapabilities.setCapability("avoidProxy", true);
        desiredCapabilities.setCapability("username", ConfigurationManager.getProperty("SAUCE_USERNAME"));
        desiredCapabilities.setCapability("accessKey", ConfigurationManager.getProperty("SAUCE_ACCESS_KEY"));
        driver = new RemoteWebDriver(url, desiredCapabilities);
    }
}
