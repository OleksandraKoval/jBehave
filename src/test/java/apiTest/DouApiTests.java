package apiTest;

import common.annotation.TestType;
import lombok.SneakyThrows;
import org.junit.runner.RunWith;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import steps.ApiDouSteps;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(TestTypeRunner.class)
public class DouApiTests extends BaseTest {

    @Test
    @TestType(testType = "UI")
    public void test1() {
        assertThat(ApiDouSteps.getDouRequest().getStatusCode()).as("200 status code expected").isEqualTo(200);
    }

    @SneakyThrows
    @Test
    @TestType(testType = "TEST_UI")
    public void test2() {
        //java -jar selenium-server-4.1.4.jar standalone
        //java -jar selenium-server-4.1.4.jar node --detect-drivers true
        WebDriver driver;
        String url = "http://localhost:4444/wd/hub";
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName("chrome");
        desiredCapabilities.setPlatform(Platform.WIN10);
        driver = new RemoteWebDriver((new URL(url)), desiredCapabilities);
        driver.get("https://dou.ua/");
        assertThat(ApiDouSteps.getDouRequest().asString()).as("Gamedev should be present on the Main page").contains(
                "Gamedev");
        driver.quit();
    }
}
