package factoryPattern;

import com.codeborne.selenide.Selenide;

public class TestedPage{

    public void openTestedPage(String url) {
        Selenide.open(url);
    }
}
