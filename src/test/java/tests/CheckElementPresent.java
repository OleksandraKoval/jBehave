package tests;

import factoryPattern.PageTypeEnums;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.listeners.TestListener;

import static factoryPattern.PageTypeEnums.BING;
import static factoryPattern.PageTypeEnums.GOOGLE;
import static utils.extentreports.ExtentTestManager.startTest;

@Listeners({TestListener.class})
@Epic("Check Element Present")
@Feature("Check Element Present")
public class CheckElementPresent extends BaseTest {

    @DataProvider(name = "data-provider")
    public Object[][] dpMethod() {
        return new Object[][]{{BING, "Ukraine", "input"},
                {GOOGLE, "", "input"},
                {GOOGLE, "Ukraine", "input"}};
    }

    @Test(priority = 0, description = "Check Element Present", dataProvider = "data-provider")
    @Severity(SeverityLevel.MINOR)
    public void checkSearchResult(final PageTypeEnums pageToOpen, final String searchedText, final String elementName) {
        startTest("Check Element Present", "Check Element Present");
        baseSteps.openPageByParameter(pageToOpen);
        baseSteps.typeTextToInputTextBox(searchedText, elementName, pageToOpen);
        baseSteps.userCheckElementContainsText(elementName, searchedText, pageToOpen);
    }
}
