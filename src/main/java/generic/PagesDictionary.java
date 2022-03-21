package generic;

import com.google.common.collect.ImmutableList;
import pages.AbstractPage;
import pages.Bing;
import pages.Google;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

public final class PagesDictionary {

    private static final List<PageBinding> DICTIONARY = ImmutableList.of(
            new PageBinding("Google", Google.class),
            new PageBinding("Bing", Bing.class));


    public static Class<? extends AbstractPage> getPageClass(String screenName) {
        Optional<Class<? extends AbstractPage>> cls = Optional.empty();
        try {
            for (PageBinding binding : DICTIONARY) {
                if (binding.getUiName().getName().equals(screenName)) {
                    cls = Optional.of(binding.getClazz());
                    break;
                }
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException(format("Page %s wasn't found in dictionary", screenName));
        }
        return cls.get();
    }
}
