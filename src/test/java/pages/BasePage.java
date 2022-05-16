package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class BasePage {
    public void waitVisibility(SelenideElement element) {
        Selenide.Wait().until(webDriver -> element.isEnabled());
    }
}
