package steps;

import io.restassured.response.Response;

public class ApiDouSteps extends AbstractDefinitionSteps {


    public static Response getDouRequest() {
        return ApiRequestManager.request
                .header("Accept", "*/*")
                .header("Connection", "keep-alive")
                .header("Content-Type", "text/html; charset=us-ascii")
                .log().all().relaxedHTTPSValidation().get("https://dou.ua/");
    }
}
