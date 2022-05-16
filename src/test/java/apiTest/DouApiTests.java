package apiTest;

import lombok.SneakyThrows;
import org.testng.annotations.Test;
import steps.ApiDouSteps;

import static org.assertj.core.api.Assertions.assertThat;

public class DouApiTests extends BaseTestAPI {

    @Test
    public void test1() {
        assertThat(ApiDouSteps.getDouRequest().getStatusCode()).as("200 status code expected").isEqualTo(200);
    }

    @SneakyThrows
    @Test
    public void test2() {
        assertThat(ApiDouSteps.getDouRequest().asString()).as("Gamedev should be present on the Main page").contains(
                "Gamedev");
    }
}
