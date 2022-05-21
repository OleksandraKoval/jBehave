package factoryPattern;

import pages.Bing;
import pages.Google;
import pages.HomePage;
import pages.LoginPage;
import strategyPattern.BingStrategy;
import strategyPattern.GoogleStrategy;
import strategyPattern.LoginStrategy;

public final class PageFactory {

    public static TestedPage createPage(PageTypeEnums type) {
        return switch (type) {
            case GOOGLE -> new Google();
            case BING -> new Bing();
            case HOME_PAGE -> new HomePage();
            case LOGIN_PAGE -> new LoginPage();
            case LOGIN_STRATEGY -> new LoginStrategy();
            case GOOGLE_STRATEGY -> new GoogleStrategy();
            case BING_STRATEGY -> new BingStrategy();
        };
    }
}
