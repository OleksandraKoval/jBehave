package apiTest;

import common.annotation.TestType;
import org.junit.runner.RunWith;
import org.testng.annotations.Test;
import steps.ApiDouSteps;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(TestTypeRunner.class)
public class DouApiTests {

    @Test
    @TestType(testType = "UI")
    public void test1() {
        assertThat(ApiDouSteps.getDouRequest().getStatusCode()).as("200 status code expected").isEqualTo(200);
    }

    @Test
    @TestType(testType = "TEST_UI")
    public void test2() {
        assertThat(ApiDouSteps.getDouRequest().asString()).as("Gamedev should be present on the Main page").contains(
                "Gamefsdfsdfsdfsddev");
    }
}
