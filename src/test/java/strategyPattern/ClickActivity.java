package strategyPattern;

import lombok.Setter;

@Setter
public class ClickActivity {
    IClickOnSearchType iClickOnSearchType;

    public ClickActivity(IClickOnSearchType clickOnSearchType) {
        this.iClickOnSearchType = clickOnSearchType;
    }

    public void executeActivity() {
        iClickOnSearchType.executeAction();
    }
}
