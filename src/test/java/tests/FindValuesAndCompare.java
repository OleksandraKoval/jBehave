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
        return new Object[][]{{GOOGLE, "Ukraine", GOOGLE_STRATEGY},
                {BING, "EPAM", BING_STRATEGY}};
    }

    @Test(priority = 0, description = "Search function validation", dataProvider = "data-provider")
    @Severity(SeverityLevel.MINOR)
    public void verifySearchFunction(final PageTypeEnums pageToOpen, final String searchedText,
                                     final PageTypeEnums pageStrategy) {
        startTest("Search function validation", "Search function validation");
        baseSteps.openPageByParameter(pageToOpen);
        baseSteps.typeTextToInputTextBox(searchedText, "input", pageToOpen);
        baseSteps.clickOnSearchTypeButton("searchType", pageStrategy);
        baseSteps.checkRequiredWindows(pageStrategy, "window");
    }
}
