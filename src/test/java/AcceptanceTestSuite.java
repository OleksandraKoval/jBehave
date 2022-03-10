import common.DriverBinariesSetter;
import net.serenitybdd.jbehave.SerenityStories;

public class AcceptanceTestSuite extends SerenityStories {
    public final String CURRENTLY_USED_WEB_DRIVER = "webdriver.driver";

    public AcceptanceTestSuite() {
        DriverBinariesSetter
                .setBinariesForUsedWebDriver(System.getProperty(CURRENTLY_USED_WEB_DRIVER));
    }
}
