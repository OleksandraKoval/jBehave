package steps;

import net.thucydides.core.steps.ScenarioSteps;
import session.TestSession;

public class AbstractDefinitionSteps extends ScenarioSteps {

    private final TestSession testSession;

    protected AbstractDefinitionSteps() {
        testSession = TestSession.getInstance();
    }

    public TestSession getTestSession() {
        return testSession;
    }
}
