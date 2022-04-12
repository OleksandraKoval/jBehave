package factoryPattern;

import pages.Bing;
import pages.Google;
import strategyPattern.BingStrategy;
import strategyPattern.GoogleStrategy;

public final class PageFactory {

    public static TestedPage createPage(PageTypeEnums type) {
        return switch (type) {
            case GOOGLE -> new Google();
            case BING -> new Bing();
            case GOOGLE_STRATEGY -> new GoogleStrategy();
            case BING_STRATEGY -> new BingStrategy();
        };
    }
}
