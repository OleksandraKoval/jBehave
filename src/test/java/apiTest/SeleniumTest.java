package apiTest;

import config.ConfigurationManager;
import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

@RunWith(TestTypeRunner.class)
public class SeleniumTest extends BaseTest {
    public RemoteWebDriver driver;

    @BeforeTest
    public void setup() throws MalformedURLException {
        URL url = new URL("https://oauth-kulyasaleksandra-cb8f8:5805de78-2c54-423a-bf5e-bbd008467f6f@ondemand" +
                ".eu-central-1.saucelabs.com:443/wd/hub");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("browserName", "chrome");
        desiredCapabilities.setCapability("browser", "latest");
        desiredCapabilities.setCapability("platform", Platform.WIN10);
        desiredCapabilities.setCapability("avoidProxy", true);
        desiredCapabilities.setCapability("username", ConfigurationManager.getProperty("SAUCE_USERNAME"));
        desiredCapabilities.setCapability("accessKey", ConfigurationManager.getProperty("SAUCE_ACCESS_KEY"));
        driver = new RemoteWebDriver(url, desiredCapabilities);


    }

    @Test
    public void navigateAndClose() {
        driver.navigate().to("https://www.makeup.com/");
        Assertions.assertThat(driver.getTitle()).contains("Makeup");
        driver.quit();
    }
}
