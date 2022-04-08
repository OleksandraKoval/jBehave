package decoratorPattern;

public class PageDecorator implements IGetFoundResults{
    IGetFoundResults foundResults;

    public PageDecorator(IGetFoundResults foundResults)
    {
        this.foundResults = foundResults;
    }

    @Override
    public String getFoundResults() {
        return foundResults.getFoundResults();
    }
}
