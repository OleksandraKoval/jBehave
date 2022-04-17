package steps;

import net.thucydides.core.steps.ScenarioSteps;
import session.TestSession;

public class BaseDefinitionSteps extends ScenarioSteps {

    private final TestSession testSession;

    protected BaseDefinitionSteps() {
        testSession = TestSession.getInstance();
    }

    public TestSession getTestSession() {
        return testSession;
    }
}
