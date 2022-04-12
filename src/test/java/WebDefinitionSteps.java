import decoratorPattern.BingDecorator;
import decoratorPattern.GoogleDecorator;
import decoratorPattern.IGetFoundResults;
import factoryPattern.PageTypeEnums;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import session.SessionKey;
import steps.AbstractDefinitionSteps;
import pages.Bing;
import pages.Google;
import steps.WebSteps;
import strategyPattern.ClickActivity;
import strategyPattern.IClickOnSearchType;
import strategyPattern.StrategyFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class WebDefinitionSteps extends AbstractDefinitionSteps {

    @Steps
    private WebSteps webSteps;

    private IGetFoundResults result;

    @Given("User open '$page' page")
    @When("User open '$page' page")
    public void userOpenPage(final PageTypeEnums page) {
        webSteps.openPageByParameter(page);
    }

    @When("User search '$searchedText' to '$elementName' on '$page' page")
    public void userTypeTextToInputTextBox(final String searchedText, final String elementName,
                                           final PageTypeEnums page) {
        webSteps.typeTextToInputTextBox(searchedText, elementName, page);
    }

    @When("User click on '$searchType' button on '$pageStrategy' page")
    public void ClickOnSearchTypeButton(final String searchType, final PageTypeEnums pageStrategy) {
        WebElementFacade window = webSteps.getElementUnderTest(searchType, pageStrategy);
        IClickOnSearchType clickOnSearchType = StrategyFactory.getStrategy(pageStrategy, window);
        ClickActivity clickActivity = new ClickActivity(clickOnSearchType);
        clickActivity.executeActivity();
    }

    @Then("User check required '<$page>' windows has window '$window'")
    public void checkRequiredWindows(final PageTypeEnums page, final String window) {
        WebElementFacade elementUnderTest = webSteps.getElementUnderTest(window, page);
        elementUnderTest.isVisible();

    }

    @Then("User see '$elementName' contains '$searchedText' on '$page' page")
    public void userCheckElementContainsText(final String elementName, final String searchedText,
                                             final PageTypeEnums page) {
        webSteps.userCheckElementContainsText(elementName, searchedText, page);
    }

    @When("User remember value for '$elementName' on '$page'")
    public void userSearchTextAndGetFoundResults(final String elementName, final PageTypeEnums page) {
        WebElementFacade elementUnderTest = webSteps.getElementUnderTest(elementName, page);

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

    @Then("User compare remembered '$firstValue' and '$secondValue' values")
    public void userComparedRememberedValues(final String firstValue, final String secondValue) {
        long valueFromGoogle = getTestSession().getL(SessionKey.valueOf(firstValue));
        long valueFromBing = getTestSession().getL(SessionKey.valueOf(secondValue));
        assertThat(valueFromGoogle).as("Result from Google is always bigger").isGreaterThan(valueFromBing);
    }
}
