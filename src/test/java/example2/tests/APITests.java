package example2.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class APITests {

    @Test
    public void testCreateUser() {
        RestAssured.baseURI = "https://reqres.in/api";

        String requestBody = "{ \"name\": \"Noa\", \"job\": \"leader\" }";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract().response();

        System.out.println("Response: " + response.getBody().asString());
        Assert.assertTrue(response.getBody().asString().contains("id"), "Response does not contain user ID");
    }
}
