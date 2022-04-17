package steps;

import factoryPattern.PageFactory;
import factoryPattern.PageTypeEnums;
import factoryPattern.TestedPage;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.Keys;

import static org.assertj.core.api.Assertions.assertThat;

public class WebSteps extends BaseSteps {

    @Step
    public void openPageByParameter(final PageTypeEnums nameOfPage) {
        PageFactory.createPage(nameOfPage).openTestedPage();
    }

    @Step
    public void typeTextToInputTextBox(final String searchedText, final String elementName, final PageTypeEnums page) {
        WebElementFacade elementUnderTest = getElementUnderTest(elementName, page);
        elementUnderTest.type(searchedText);
        elementUnderTest.sendKeys(Keys.ENTER);
    }

    @Step
    public void userCheckElementContainsText(final String elementName, final String searchedText, final PageTypeEnums page) {
        WebElementFacade elementUnderTest = getElementUnderTest(elementName, page);
        if (searchedText.isEmpty()) {
            //Java 14 NullPointerException
            elementUnderTest = null;
        }
        String elementText = elementUnderTest.getValue();
        assertThat(elementText).as("Tested element %s is null", elementText).contains(searchedText);
    }

    public WebElementFacade getElementUnderTest(final String elementName, final PageTypeEnums page) {
        TestedPage pageForSearch = PageFactory.createPage(page);
        return getWebElementByFieldName(elementName, pageForSearch);
    }
}
