package strategyPattern;

import com.codeborne.selenide.SelenideElement;
import factoryPattern.TestedPage;

import static com.codeborne.selenide.Selenide.$x;

public class BingStrategy extends TestedPage implements IClickOnSearchType {

    SelenideElement elementUnderTest;
    public SelenideElement searchType = $x("//div[@class='b_icon tooltip']");
    public SelenideElement window = $x("//div[@class='b_overlayCont']//div[@class='b_ov_close']");
    public BingStrategy() {
        super();
    }

    public BingStrategy(SelenideElement window) {
        super();
        this.elementUnderTest = window;
    }

    @Override
    public void executeAction() {
        elementUnderTest.click();
    }
}
