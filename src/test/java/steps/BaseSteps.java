package steps;

import com.codeborne.selenide.SelenideElement;
import factoryPattern.TestedPage;
import net.thucydides.core.steps.ScenarioSteps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class BaseSteps extends ScenarioSteps {

    private static final Logger LOG = LoggerFactory.getLogger(BaseSteps.class);

    public SelenideElement getSelenideByFieldName(String fieldName, TestedPage page) {
        SelenideElement webElement = null;
        Class<?> validationClass = page.getClass();
        Field[] fields = validationClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType() == SelenideElement.class) {
                field.setAccessible(true);
                if (field.getName().equals(fieldName)) {
                    try {
                        webElement = (SelenideElement) field.get(page);
                    } catch (IllegalAccessException e) {
                        LOG.error(String.format("Cannot get element %s on page %s", fieldName,
                                page.getClass().getSimpleName()), e);
                        throw new RuntimeException(String.format("Element '%s' is not declared for class '%s'",
                                fieldName,
                                page.getClass().getSimpleName()));
                    }
                }
            }
        }
        return webElement;
    }
}
