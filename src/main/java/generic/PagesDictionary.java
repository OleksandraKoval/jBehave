package generic;

import com.google.common.collect.ImmutableList;
import common.AbstractPage;
import pages.Bing;
import pages.Google;

import java.util.List;
import java.util.Optional;

public final class PagesDictionary {

    private static final List<PageBinding> DICTIONARY = ImmutableList.of(
            new PageBinding("Google", Google.class),
            new PageBinding("Bing", Bing.class));


    public static Class<? extends AbstractPage> getPageClass(String screenName) {
        Optional<Class<? extends AbstractPage>> cls = Optional.empty();
        for (PageBinding binding : DICTIONARY) {
            if (binding.getUiName().getName().equals(screenName)) {
                cls = Optional.of(binding.getClazz());
                break;
            }
        }
        return cls.orElseThrow(NullPointerException::new);
    }
}
