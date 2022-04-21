package strategyPattern;

import factoryPattern.TestedPage;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

public class BingStrategy extends TestedPage implements IClickOnSearchType {

    WebElementFacade elementUnderTest;

    public BingStrategy() {
        super();
    }

    public BingStrategy(WebElementFacade window) {
        super();
        this.elementUnderTest = window;
    }

    @FindBy(xpath = "//div[@class='b_icon tooltip']")
    private WebElementFacade searchType;

    @FindBy(xpath = "//div[@class='b_overlayCont']//div[@class='b_ov_close']")
    private WebElementFacade window;

    @Override
    public void executeAction() {
        elementUnderTest.click();
    }
}
