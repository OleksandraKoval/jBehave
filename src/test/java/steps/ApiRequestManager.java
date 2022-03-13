package steps;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiRequestManager {

    public static RequestSpecification request = given().log().all();

    public static void reset() {
        request = given().log().all();
    }
}



