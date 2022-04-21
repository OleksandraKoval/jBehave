package steps;

import factoryPattern.TestedPage;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.steps.ScenarioSteps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class BaseSteps extends ScenarioSteps {

    private static final Logger LOG = LoggerFactory.getLogger(BaseSteps.class);

    public WebElementFacade getWebElementByFieldName(String fieldName, TestedPage page) {
        try {
            Field field = page.getClass().getDeclaredField(fieldName);
            field.trySetAccessible();
            return (WebElementFacade) field.get(this.pages().get(page.getClass()));
        } catch (IllegalAccessException | NoSuchFieldException e) {
            LOG.error(String.format("Cannot get element %s on page %s", fieldName, page.getClass().getSimpleName()), e);
            throw new RuntimeException(String.format("Element '%s' is not declared for class '%s'", fieldName,
                    page.getClass().getSimpleName()));
        }
    }
}
