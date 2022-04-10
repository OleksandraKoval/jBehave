package strategyPattern;

import lombok.Setter;

@Setter
public class ClickActivity {
    IClickOnSearchType iClickOnSearchType;

    public void executeActivity() {
        iClickOnSearchType.clickOnAction();
    }
}
