package Project;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.oauth2;
import static org.hamcrest.Matchers.lessThan;

import static org.hamcrest.Matchers.equalTo;

import java.util.*;

import java.util.HashMap;

public class RestAssured_Assignment1 {

    RequestSpecification requestSpec;
    ResponseSpecification responseSpec;
    String SSH_Key;
    int Key_Id;

    @BeforeClass
    public void setUp() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://api.github.com/user/keys")
                .setContentType(ContentType.JSON)
                .setAuth(oauth2("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")).build();
        responseSpec = new ResponseSpecBuilder().expectResponseTime(lessThan(5000L)).build();
    }

    @Test
    public void PostRequestTest() {
        //Forming request body
        Map<String, Object> reqBody = new HashMap<>();
        reqBody.put("title", "TestAPIKey");
        reqBody.put("key", "ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIPQWj1L+0StHylOT+xJkhWytKXFT1IDW9pMbrDyUjRVC");

        //send the request and save the response
        Response response = RestAssured.given().spec(requestSpec).body(reqBody).
                log().all().when().post();

        Key_Id = response.then().extract().path("id");
        // System.out.println("Key_Id is: " + Key_Id);
        response.then().log().all().statusCode(201);
        // System.out.println(ValidatableResponse);

//Assertion
        response.then().body("title", equalTo("TestAPIKey"));
    }

    @Test
    public void GetRequestTest() {
        given().spec(requestSpec).pathParam("keyId", Key_Id).when().get("/{keyId}")
                .then().log().all().spec(responseSpec).statusCode(200).log().all();

    }

    @Test
    public void DeleteRequestTest() {
        given().spec(requestSpec).pathParam("keyId", SSH_Key).
                when().delete("/{keyId}").then().
                spec(responseSpec).statusCode(204).log().all();
    }
}
