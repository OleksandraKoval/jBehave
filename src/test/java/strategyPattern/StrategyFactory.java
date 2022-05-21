package strategyPattern;

import com.codeborne.selenide.SelenideElement;
import factoryPattern.PageTypeEnums;
import net.serenitybdd.core.pages.WebElementFacade;

public class StrategyFactory {

    public static IClickOnSearchType getStrategy(PageTypeEnums type, SelenideElement window) {
        return switch (type) {
            case GOOGLE_STRATEGY -> new GoogleStrategy(window);
            case BING_STRATEGY -> new BingStrategy(window);
            case LOGIN_STRATEGY -> new LoginStrategy(window);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
