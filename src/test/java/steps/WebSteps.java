package steps;

import common.AbstractPage;
import generic.PagesDictionary;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.junit.Test;
import org.openqa.selenium.Keys;

import static org.assertj.core.api.Assertions.assertThat;

public class WebSteps extends AbstractSteps {

    @Step
    @Test
    public void openPageByParameter(final String nameOfPage) {
        AbstractPage pageForSearch = getPages().getPage(PagesDictionary.getPageClass(nameOfPage));
        pageForSearch.open();
    }

    @Step
    public void typeTextToInputTextBox(final String searchedText, final String elementName, final String page) {
        WebElementFacade elementUnderTest = getElementUnderTest(elementName, page);
        elementUnderTest.type(searchedText);
        elementUnderTest.sendKeys(Keys.ENTER);
    }

    @Step
    public void userCheckElementContainsText(final String elementName, final String searchedText, final String page) {
        WebElementFacade elementUnderTest = getElementUnderTest(elementName, page);
        if (searchedText.isEmpty()) {
            //Java 14 NullPointerException
            elementUnderTest = null;
        }
        String elementText = elementUnderTest.getValue();
        assertThat(elementText).as("Tested element %s is null", elementText).contains(searchedText);
    }

    public WebElementFacade getElementUnderTest(final String elementName, final String page) {
        AbstractPage pageForSearch = getPages().getPage(PagesDictionary.getPageClass(page));
        return getWebElementByFieldName(elementName, pageForSearch);
    }
}
