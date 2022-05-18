package pages;

import com.codeborne.selenide.SelenideElement;
import decoratorPattern.IGetFoundResults;
import factoryPattern.TestedPage;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage extends TestedPage implements IGetFoundResults {
    private SelenideElement elementUnderTest;


    SelenideElement userNameId = $x("//div[@class='input-wrap']//input[@name='user_login']");
    SelenideElement passwordId = $x("//div[@class='input-wrap']//input[@name='user_pw']");

    @Override
    public String getFoundResults() {
        return elementUnderTest.getText();
    }
}
