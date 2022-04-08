package decoratorPattern;

import org.apache.commons.lang3.StringUtils;

public class BingDecorator extends PageDecorator {

    private static final String FIRST_SYMBOL = ":";
    private static final String SECOND_SYMBOL = "(";

    public BingDecorator(IGetFoundResults foundResults) {
        super(foundResults);
    }

    @Override
    public String getFoundResults() {
        return StringUtils.substringBetween(super.getFoundResults(), FIRST_SYMBOL, SECOND_SYMBOL).replaceAll("\\s",
                StringUtils.EMPTY);
    }
}
