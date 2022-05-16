package tests;

import factoryPattern.PageTypeEnums;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static factoryPattern.PageTypeEnums.*;
import static utils.extentreports.ExtentTestManager.startTest;

@Epic("Search function validation")
@Feature("Search function validation")
public class FindValuesAndCompare extends BaseTest {

    @DataProvider(name = "data-provider")
    public Object[][] dpMethod() {
        return new Object[][]{{GOOGLE, "Ukraine", "input", GOOGLE_STRATEGY, "window", "searchType"},
                {BING, "EPAM", "input", BING_STRATEGY, "window", "searchType"}};
    }

    @Test(priority = 0, description = "Search function validation", dataProvider = "data-provider")
    @Severity(SeverityLevel.MINOR)
    public void verifySearchFunction(final PageTypeEnums pageToOpen, final String searchedText, final String elementName,
                                     final PageTypeEnums pageStrategy, final String expectedWindow, final String searchType) {
        startTest("Search function validation", "Search function validation");
        openPageByParameter(pageToOpen);
        typeTextToInputTextBox(searchedText, elementName, pageToOpen);
        clickOnSearchTypeButton(searchType, pageStrategy);
        checkRequiredWindows(pageStrategy, expectedWindow);
    }
}
