package Activites;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Activity2 {
    RequestSpecification requestSpec;
    ResponseSpecification responseSpec;
    String Userid;
    @BeforeClass
    public void setUp() {
        requestSpec = new RequestSpecBuilder().
                setBaseUri("https://petstore.swagger.io/v2/user").addHeader("Content-Type", "application/json").
                build();
        responseSpec = new ResponseSpecBuilder().expectStatusCode(200).
                expectResponseTime(lessThan(5000L)).build();

    }

    @Test(priority = 1)
    public void postRequestTest() {
        Map<String, Object> reqBody = new HashMap<>();
        reqBody.put("id", 037127);
        reqBody.put("username", "Komaram");
        reqBody.put("firstName", "Komaram");
        reqBody.put("lastName", "Bheem");
        reqBody.put("email", "Komaram@gmail.com");
        reqBody.put("password", "password123");
        reqBody.put("phone", "9812763450");



        Response response = given().spec(requestSpec).body(reqBody).when().post();
        System.out.println(response.getBody());
        Userid = response.then().extract().path("id");
        response.then().spec(responseSpec).body("username", equalTo("Komaram"));



    }

}
