package tests;

import factoryPattern.PageTypeEnums;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.listeners.TestListener;

import static factoryPattern.PageTypeEnums.*;
import static utils.extentreports.ExtentTestManager.startTest;

@Listeners({TestListener.class})
@Epic("Make Up login Pop up")
@Feature("Login Tests")
public class LoginTest extends BaseTest {

    @DataProvider(name = "data-provider")
    public Object[][] dpMethod() {
        return new Object[][]{{HOME_PAGE,LOGIN_STRATEGY,"signInButton","userNameId","passwordId",LOGIN_PAGE}};
    }

    @Test(priority = 0, description = "Make Up login Pop up", dataProvider = "data-provider")
    @Severity(SeverityLevel.MINOR)
    public void verifyLoginPopUpAppears(final PageTypeEnums pageToOpen,final PageTypeEnums pageStrategy, final String searchType,
                                        final String firstElement,final String secondElement,final PageTypeEnums elementOnPage) {

        openPageByParameter(pageToOpen);
        clickOnSearchTypeButton(searchType, pageStrategy);
        waitVisibility(firstElement,elementOnPage);
        waitVisibility(secondElement,elementOnPage);
    }
}