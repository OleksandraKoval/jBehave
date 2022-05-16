package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import utils.logs.Log;

import static com.codeborne.selenide.Selenide.$x;

public class HomePage extends BasePage {
    String baseURL = "https://makeup.com.ua/";

    SelenideElement signInButtonClass = $x("//div[@class='header-top']//div[@data-popup-handler='auth']");

    public HomePage goToMakeUp() {
        Log.info("Opening Make up Website.");
        Selenide.open(baseURL);
        return this;
    }

    public LoginPage goToLoginPage() {
        Log.info("Going to Login Pop up..");
        signInButtonClass.click();
        return new LoginPage();
    }

}