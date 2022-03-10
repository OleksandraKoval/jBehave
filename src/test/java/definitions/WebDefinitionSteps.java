package definitions;

import common.AbstractDefinitionSteps;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import steps.WebSteps;

public class WebDefinitionSteps extends AbstractDefinitionSteps {

    @Steps
    private WebSteps webSteps;

    @Given("User open '$page' page")
    public void userOpenPage(final String page) {
        webSteps.openPageByParameter(page);
    }

    @When("User search '$searchedText' to '$elementName' on '$page' page")
    public void userTypeTextToInputTextBox(final String searchedText, final String elementName, final String page) {
        webSteps.typeTextToInputTextBox(searchedText, elementName, page);
    }

    @Then("User see '$elementName' contains '$searchedText' on '$page' page")
    public void userCheckElementContainsText(final String elementName, final String searchedText, final String page) {
        webSteps.userCheckElementContainsText(elementName, searchedText, page);
    }
}
