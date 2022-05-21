package steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import decoratorPattern.BingDecorator;
import decoratorPattern.GoogleDecorator;
import decoratorPattern.IGetFoundResults;
import factoryPattern.PageFactory;
import factoryPattern.PageTypeEnums;
import factoryPattern.TestedPage;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.AvailableHomePages;
import pages.Bing;
import pages.Google;
import session.SessionKey;
import session.TestSession;
import strategyPattern.ClickActivity;
import strategyPattern.IClickOnSearchType;
import strategyPattern.StrategyFactory;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseSteps {

    private static final Logger LOG = LoggerFactory.getLogger(BaseSteps.class);

    private final TestSession testSession;

    public BaseSteps() {
        testSession = TestSession.getInstance();
    }

    public TestSession getTestSession() {
        return testSession;
    }

    public SelenideElement getSelenideByFieldName(String fieldName, TestedPage page) {
        SelenideElement webElement = null;
        Class<?> validationClass = page.getClass();
        Field[] fields = validationClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType() == SelenideElement.class) {
                field.setAccessible(true);
                if (field.getName().equals(fieldName)) {
                    try {
                        webElement = (SelenideElement) field.get(page);
                    } catch (IllegalAccessException e) {
                        LOG.error(String.format("Cannot get element %s on page %s", fieldName,
                                page.getClass().getSimpleName()), e);
                        throw new RuntimeException(String.format("Element '%s' is not declared for class '%s'",
                                fieldName,
                                page.getClass().getSimpleName()));
                    }
                }
            }
        }
        return webElement;
    }

    public void openPageByParameter(final PageTypeEnums nameOfPage) {
        PageFactory.createPage(nameOfPage).openTestedPage(AvailableHomePages.valueOf(nameOfPage.toString()).getPath());
    }

    public void typeTextToInputTextBox(final String searchedText, final String elementName,
                                          final PageTypeEnums page) {
        SelenideElement elementUnderTest = getElementUnderTest(elementName, page);
        elementUnderTest.val(searchedText);
        elementUnderTest.sendKeys(Keys.ENTER);
    }

    public void clickOnSearchTypeButton(final String searchType, final PageTypeEnums pageStrategy) {
        SelenideElement window = getElementUnderTest(searchType, pageStrategy);
        IClickOnSearchType clickOnSearchType = StrategyFactory.getStrategy(pageStrategy, window);
        ClickActivity clickActivity = new ClickActivity(clickOnSearchType);
        clickActivity.executeActivity();
    }

    public void userCheckElementContainsText(final String elementName, final String searchedText,
                                                final PageTypeEnums page) {
        SelenideElement elementUnderTest = getElementUnderTest(elementName, page);
        String elementText = elementUnderTest.getValue();
        assertThat(elementText).as("Tested element %s is null", elementText).contains(searchedText);
    }

    public void checkRequiredWindows(final PageTypeEnums page, final String window) {
        SelenideElement elementUnderTest = getElementUnderTest(window, page);
        elementUnderTest.shouldBe(Condition.visible);
    }

    public void userSearchTextAndGetFoundResults(final String elementName, final PageTypeEnums page) {
        SelenideElement elementUnderTest = getElementUnderTest(elementName, page);

        switch (page) {
            case GOOGLE: {
                IGetFoundResults result = new BingDecorator(new Google(elementUnderTest));
                getTestSession().put(SessionKey.REMEMBERED_VALUE_FOR_GOOGLE, result.getFoundResults());
            }
            case BING: {
                IGetFoundResults result = new GoogleDecorator(new Bing(elementUnderTest));
                getTestSession().put(SessionKey.REMEMBERED_VALUE_FOR_BING, result.getFoundResults());
            }
        }
    }

    public SelenideElement getElementUnderTest(final String elementName, final PageTypeEnums page) {
        TestedPage pageForSearch = PageFactory.createPage(page);
        return getSelenideByFieldName(elementName, pageForSearch);
    }

    public void userComparedRememberedValues(final SessionKey firstValue, final SessionKey secondValue) {
        long valueFromGoogle = getTestSession().getL(firstValue);
        long valueFromBing = getTestSession().getL(secondValue);
        Assertions.assertThat(valueFromGoogle).as("Result from Google is always bigger").isGreaterThan(valueFromBing);
    }

    public void waitVisibility(final String elementName, final PageTypeEnums page) {
        SelenideElement elementUnderTest = getElementUnderTest(elementName, page);
        Selenide.Wait().until(webDriver -> elementUnderTest.isEnabled());
    }
}
