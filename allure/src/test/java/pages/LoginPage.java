package pages;

import com.codeborne.selenide.SelenideElement;
import utils.logs.Log;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage extends BasePage {

    SelenideElement userNameId = $x("//div[@class='input-wrap']//input[@name='user_login']");
    SelenideElement passwordId = $x("//div[@class='input-wrap']//input[@name='user_pw']");
    SelenideElement loginButtonId = $x("//div[@class='form-inner-wrap']//button[@type='submit'][1]");

    public void verifyPopUpAppear() {
        Log.info("Trying to wait login pop up");

        waitVisibility(userNameId);
        waitVisibility(userNameId);
    }
}