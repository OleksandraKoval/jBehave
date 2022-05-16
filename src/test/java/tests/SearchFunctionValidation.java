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
import static session.SessionKey.REMEMBERED_VALUE_FOR_BING;
import static session.SessionKey.REMEMBERED_VALUE_FOR_GOOGLE;
import static utils.extentreports.ExtentTestManager.startTest;

@Listeners({TestListener.class})
@Epic("Search function validation")
@Feature("Search function validation")
public class SearchFunctionValidation extends BaseTest {

    @DataProvider(name = "data-provider")
    public Object[][] dpMethod() {
        return new Object[][]{{GOOGLE, "Ukraine", "input", "result", BING}};
    }

    @Test(priority = 0, description = "Search function validation", dataProvider = "data-provider")
    @Severity(SeverityLevel.MINOR)
    public void verifySearchFunction(final PageTypeEnums pageToOpen, final String searchedText, final String elementName,
                                     final String result, final PageTypeEnums secondPageToOpen) {
        startTest("Search function validation", "Search function validation");
        openPageByParameter(pageToOpen);
        typeTextToInputTextBox(searchedText, elementName, pageToOpen);
        userSearchTextAndGetFoundResults(result, pageToOpen);

        openPageByParameter(secondPageToOpen);
        typeTextToInputTextBox(searchedText, elementName, secondPageToOpen);
        userSearchTextAndGetFoundResults(result, secondPageToOpen);

        userComparedRememberedValues(REMEMBERED_VALUE_FOR_GOOGLE, REMEMBERED_VALUE_FOR_BING);

    }
}
