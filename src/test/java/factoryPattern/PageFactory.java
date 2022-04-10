package factoryPattern;

import pages.AbstractPage;
import steps.Bing;
import steps.Google;
import strategyPattern.BingStrategy;
import strategyPattern.GoogleStrategy;

public final class PageFactory extends AbstractPage {

    public static TestedPage createPage(PageTypeEnums.PageType type) {
        return switch (type) {
            case Google -> new Google();
            case Bing -> new Bing();
            case GoogleStrategy -> new GoogleStrategy();
            case BingStrategy -> new BingStrategy();
        };
    }
}
