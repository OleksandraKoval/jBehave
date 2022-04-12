package strategyPattern;

import factoryPattern.PageTypeEnums;
import net.serenitybdd.core.pages.WebElementFacade;

public class StrategyFactory {

    public static IClickOnSearchType getStrategy(PageTypeEnums type, WebElementFacade window) {
        return switch (type) {
            case GOOGLE_STRATEGY -> new GoogleStrategy(window);
            case BING_STRATEGY -> new BingStrategy(window);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
