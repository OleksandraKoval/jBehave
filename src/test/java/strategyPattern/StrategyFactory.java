package strategyPattern;

import com.codeborne.selenide.SelenideElement;
import factoryPattern.PageTypeEnums;

public class StrategyFactory {

    public static IClickOnSearchType getStrategy(PageTypeEnums type, SelenideElement window) {
        return switch (type) {
            case GOOGLE_STRATEGY -> new GoogleStrategy(window);
            case BING_STRATEGY -> new BingStrategy(window);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
