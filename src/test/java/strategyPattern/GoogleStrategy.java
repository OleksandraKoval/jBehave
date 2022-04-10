package strategyPattern;

import factoryPattern.TestedPage;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

public class GoogleStrategy extends TestedPage implements IClickOnSearchType {

    WebElementFacade elementUnderTest;

    public GoogleStrategy() {
        super();
    }

    public GoogleStrategy(WebElementFacade window) {
        super();
        this.elementUnderTest = window;
    }

    @FindBy(xpath = "//span[@class='ly0Ckb']")
    private WebElementFacade searchType;

    @FindBy(xpath = "//div[contains(@class,'ita-container')]")
    private WebElementFacade window;

    @Override
    public void clickOnAction() {
        elementUnderTest.click();
    }
}
