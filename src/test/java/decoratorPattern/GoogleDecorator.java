package decoratorPattern;

import org.apache.commons.lang3.StringUtils;

public class GoogleDecorator extends PageDecorator {

    public GoogleDecorator(IGetFoundResults foundResults) {
        super(foundResults);
    }

    public String getRegex() {
        return "[^0-9?!.]";
    }

    @Override
    public String getFoundResults() {
        return super.getFoundResults().replaceAll(getRegex(), StringUtils.EMPTY);
    }
}
