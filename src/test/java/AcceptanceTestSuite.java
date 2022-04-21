import common.DriverBinariesSetter;
import config.ConfigurationManager;
import net.serenitybdd.jbehave.SerenityStories;
import org.jbehave.core.annotations.BeforeStories;

import java.io.IOException;

public class AcceptanceTestSuite extends SerenityStories {
    private static final String CURRENTLY_USED_WEB_DRIVER = "webdriver.driver";

    public AcceptanceTestSuite() {
        DriverBinariesSetter
                .setBinariesForUsedWebDriver(System.getProperty(CURRENTLY_USED_WEB_DRIVER));
    }

    @BeforeStories
    public void setUp() throws IOException {
        ConfigurationManager.loadProperties();
    }
}
