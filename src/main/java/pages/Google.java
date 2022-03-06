package pages;


import common.AbstractPage;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("https://www.google.com")
public class Google extends AbstractPage {

    public Google() {
        super();
    }

    @FindBy(name = "q")
    private WebElementFacade input;
}
