package pages;

import com.codeborne.selenide.SelenideElement;
import decoratorPattern.IGetFoundResults;
import factoryPattern.TestedPage;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Bing extends TestedPage implements IGetFoundResults {

    private SelenideElement elementUnderTest;

    public Bing() {
        super();
    }
    public SelenideElement input = $x("//input[@id='sb_form_q']");
    public SelenideElement result = $x("//span[@class='sb_count']");

    public Bing(SelenideElement elementUnderTest) {
        super();
        this.elementUnderTest = elementUnderTest;
    }

    @Override
    public String getFoundResults() {
        return elementUnderTest.getText();
    }
}
