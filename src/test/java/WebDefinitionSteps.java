import decoratorPattern.BingDecorator;
import decoratorPattern.GoogleDecorator;
import decoratorPattern.IGetFoundResults;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import session.SessionKey;
import steps.AbstractDefinitionSteps;
import steps.Bing;
import steps.Google;
import steps.WebSteps;
import strategyPattern.BingStrategy;
import strategyPattern.ClickActivity;
import strategyPattern.GoogleStrategy;

import static org.assertj.core.api.Assertions.assertThat;

public class WebDefinitionSteps extends AbstractDefinitionSteps {

    @Steps
    private WebSteps webSteps;

    private IGetFoundResults result;

    @Given("User open '$page' page")
    public void userOpenPage(final String page) {
        webSteps.openPageByParameter(page);
    }

    @When("User search '$searchedText' to '$elementName' on '$page' page")
    public void userTypeTextToInputTextBox(final String searchedText, final String elementName, final String page) {
        webSteps.typeTextToInputTextBox(searchedText, elementName, page);
    }

    @When("User click on '$searchType' button on '$pageStrategy' page")
    public void ClickOnSearchTypeButton(final String searchType, final String pageStrategy) {
        ClickActivity clickActivity = new ClickActivity();
        WebElementFacade window = webSteps.getElementUnderTest(searchType, pageStrategy);

        switch (pageStrategy) {
            case "GoogleStrategy" -> clickActivity.setIClickOnSearchType(new GoogleStrategy(window));
            case "BingStrategy" -> clickActivity.setIClickOnSearchType(new BingStrategy(window));
        }
        clickActivity.executeActivity();
    }

    @Then("User check required '<$page>' windows has window '$window'")
    public void checkRequiredWindows(final String page, final String window) {
        WebElementFacade elementUnderTest = webSteps.getElementUnderTest(window, page);
        elementUnderTest.isVisible();

    }

    @Then("User see '$elementName' contains '$searchedText' on '$page' page")
    public void userCheckElementContainsText(final String elementName, final String searchedText, final String page) {
        webSteps.userCheckElementContainsText(elementName, searchedText, page);
    }

    @When("User remember value for '$elementName' on '$page'")
    public void userSearchTextAndGetFoundResults(final String elementName, final String page) {
        WebElementFacade elementUnderTest = webSteps.getElementUnderTest(elementName, page);

        switch (page) {
            case "Google": {
                IGetFoundResults result = new BingDecorator(new Google(elementUnderTest));
                getTestSession().put(SessionKey.REMEMBERED_VALUE_FOR_GOOGLE, result.getFoundResults());
            }
            case "Bing": {
                IGetFoundResults result = new GoogleDecorator(new Bing(elementUnderTest));
                getTestSession().put(SessionKey.REMEMBERED_VALUE_FOR_BING, result.getFoundResults());
            }
        }

    }

    @Then("User compare remembered values")
    public void dealerRememberResult() {
        long valueFromGoogle = getTestSession().getL(SessionKey.REMEMBERED_VALUE_FOR_GOOGLE);
        long valueFromBing = getTestSession().getL(SessionKey.REMEMBERED_VALUE_FOR_BING);
        assertThat(valueFromGoogle).as("Result from Google is always bigger").isGreaterThan(valueFromBing);
    }
}
