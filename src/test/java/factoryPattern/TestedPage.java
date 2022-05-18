package factoryPattern;

import com.codeborne.selenide.SelenideElement;
import static common.DriverBinariesSetter.driver;
import static com.codeborne.selenide.Selenide.$x;

public class TestedPage{

    private final static SelenideElement POP_UP = $x("//button[@id='L2AGLb']//div[@class='QS5gu sy4vM']");

    public void openTestedPage(String url) {
        driver.get(url);
        driver.manage().window().maximize();
        if (POP_UP.exists()) {
            POP_UP.click();
        }
    }
}
