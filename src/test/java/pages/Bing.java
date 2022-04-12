package pages;

import decoratorPattern.IGetFoundResults;
import factoryPattern.TestedPage;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("https://www.bing.com")
public class Bing extends TestedPage implements IGetFoundResults {

    private WebElementFacade elementUnderTest;

    public Bing() {
        super();
    }


    @FindBy(id = "sb_form_q")
    private WebElementFacade input;

    @FindBy(xpath = "//span[@class='sb_count']")
    private WebElementFacade result;

    public Bing(WebElementFacade elementUnderTest) {
        super();
        this.elementUnderTest = elementUnderTest;
    }

    @Override
    public String getFoundResults() {
        return elementUnderTest.getText();
    }
}
