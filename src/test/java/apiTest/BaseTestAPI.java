package apiTest;

import config.ConfigurationManager;
import lombok.SneakyThrows;
import org.testng.annotations.BeforeSuite;

public class BaseTestAPI {

    @SneakyThrows
    @BeforeSuite
    protected void setUpProperties() {
        ConfigurationManager.loadProperties();
    }
}
