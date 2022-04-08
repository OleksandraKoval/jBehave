package steps;


import decoratorPattern.IGetFoundResults;
import factoryPattern.TestedPage;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.Steps;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("https://www.google.com")
public class Google extends TestedPage implements IGetFoundResults{

private WebElementFacade elementUnderTest;

    public Google() {
        super();
    }

    public Google(WebElementFacade elementUnderTest) {
        super();
        this.elementUnderTest=elementUnderTest;
    }

    @FindBy(name = "q")
    private WebElementFacade input;


    @FindBy(xpath = "//div[@id='result-stats']")
    private WebElementFacade result;

    @Override
    public String getFoundResults() {
        return elementUnderTest.getText();
    }
}
