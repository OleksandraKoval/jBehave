package factoryPattern;

import pages.AbstractPage;
import steps.Bing;
import steps.Google;

public final class PageFactory extends AbstractPage {

    public static TestedPage createPage(PageTypeEnums.PageType type) {
        return switch (type) {
            case Google -> new Google();
            case Bing -> new Bing();
        };
    }
}
