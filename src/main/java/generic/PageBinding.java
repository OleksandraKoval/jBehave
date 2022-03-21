package generic;

import pages.AbstractPage;
import lombok.Getter;

@Getter
public final class PageBinding {

    private UiName uiName;
    private Class<? extends AbstractPage> clazz;

    PageBinding(final String name, final Class<? extends AbstractPage> clazz) {
        uiName = new UiName(name);
        this.clazz = clazz;
    }

    Class<? extends AbstractPage> getClazz() {
        return clazz;
    }
}
