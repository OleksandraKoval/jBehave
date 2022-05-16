package factoryPattern;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static common.DriverBinariesSetter.driver;

public class TestedPage {

    private final static SelenideElement POP_UP = $x("//button[@id='L2AGLb']//div[@class='QS5gu sy4vM']");

    public void openTestedPage(String url) {
        driver.get(url);
        if (POP_UP.exists()) {
            POP_UP.click();
        }
    }
}
