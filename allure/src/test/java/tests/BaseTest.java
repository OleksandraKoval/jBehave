package tests;

import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import utils.logs.Log;

public class BaseTest {
    public HomePage homePage;

    @BeforeClass
    public void classLevelSetup() {
        Log.info("Tests are starting!");
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void methodLevelSetup() {
        homePage = new HomePage();
    }

    @AfterClass
    public void teardown() {
        Log.info("Tests are ending!");
        Selenide.closeWebDriver();
    }
}