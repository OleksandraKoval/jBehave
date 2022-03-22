package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("https://www.bing.com")
public class Bing extends AbstractPage {

    public Bing() {
        super();
    }

    @FindBy(id = "sb_form_q")
    private WebElementFacade input;
}
