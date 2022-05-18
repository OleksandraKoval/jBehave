package pages;

import com.codeborne.selenide.SelenideElement;
import decoratorPattern.IGetFoundResults;
import factoryPattern.TestedPage;

import static com.codeborne.selenide.Selenide.$x;

public class HomePage extends TestedPage implements IGetFoundResults {

    private SelenideElement elementUnderTest;
    public HomePage() {
        super();
    }

    SelenideElement signInButtonClass = $x("//div[@class='header-top']//div[@data-popup-handler='auth']");

    @Override
    public String getFoundResults() {
        return elementUnderTest.getText();
    }
}
