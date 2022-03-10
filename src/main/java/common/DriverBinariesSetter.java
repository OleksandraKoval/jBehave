package common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.StringUtils;

public final class DriverBinariesSetter {

    private DriverBinariesSetter() {
    }

    private static final String EDGE = "edge";
    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";
    private static final String INTERNET_EXPLORER = "iexplorer";
    private static final String PROVIDED = "provided";
    private static final String PROVIDED_TYPE = System.getProperty("webdriver.provided.type", CHROME);

    public static void setBinariesForUsedWebDriver(String browser) {
        //Java 14 switch
        switch (browser) {
            case INTERNET_EXPLORER -> setBinaryByName(INTERNET_EXPLORER);
            case EDGE -> setBinaryByName(EDGE);
            case PROVIDED -> setBinaryByName(PROVIDED_TYPE);
            default -> setBinaryByName(CHROME);
        }
    }

    private static void setBinaryByName(String name) {
        //Java 14 switch
        switch (name) {
            case FIREFOX -> WebDriverManager.firefoxdriver().setup();
            case EDGE -> WebDriverManager.edgedriver()
                    .driverVersion(System.getProperty("edge.driver.version",
                            StringUtils.EMPTY)).setup();
            default -> WebDriverManager.chromedriver()
                    .driverVersion(System.getProperty("chrome.driver.version",
                            StringUtils.EMPTY)).setup();
        }
    }
}
