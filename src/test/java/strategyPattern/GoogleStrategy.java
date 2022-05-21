package strategyPattern;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import factoryPattern.TestedPage;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$x;

public class GoogleStrategy extends TestedPage implements IClickOnSearchType {

    SelenideElement elementUnderTest;
    public SelenideElement searchType = $x("//span[@class='ly0Ckb']");
    public SelenideElement window = $x("//div[contains(@class,'ita-container')]");
    public SelenideElement instruments = $x("//div[@class='t2vtad']");

    public GoogleStrategy() {
        super();
    }

    public GoogleStrategy(SelenideElement window) {
        super();
        this.elementUnderTest = window;
    }

    @Override
    public void executeAction() {
        Selenide.$(instruments).shouldBe(Condition.visible);
        elementUnderTest.click();
    }
}
