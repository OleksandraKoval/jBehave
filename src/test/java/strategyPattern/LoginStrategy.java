package strategyPattern;

import com.codeborne.selenide.SelenideElement;
import factoryPattern.TestedPage;

import static com.codeborne.selenide.Selenide.$x;

public class LoginStrategy extends TestedPage implements IClickOnSearchType {

    SelenideElement elementUnderTest;
    SelenideElement signInButton = $x("//div[@class='header-top']//div[@data-popup-handler='auth']");

    public LoginStrategy() {
        super();
    }

    public LoginStrategy(SelenideElement window) {
        super();
        this.elementUnderTest = window;
    }

    @Override
    public void executeAction() {
        elementUnderTest.click();
    }
}
