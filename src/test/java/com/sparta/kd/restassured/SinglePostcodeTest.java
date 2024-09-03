package com.sparta.kd.restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SinglePostcodeTest {

    private static Response response;

    @BeforeAll
    static void beforeAll() {
        response = RestAssured
                .given()
                .baseUri("https://api.postcodes.io")
                .basePath("/postcodes")
                .header("Accept", "application/json")
                .when()
                .get("/EC2Y5AS")
                .thenReturn();
    }

    @Test
    @DisplayName("Status code 200 returned")
    void testStatusCode200() {
        RestAssured
                .given()
                    .baseUri("https://api.postcodes.io")
                    .basePath("/postcodes")
                    .header("Accept", "application/json")
                    .log().all()
                .when()
                    .get("/EC2Y5AS")
                .then()
                    .log().all()
                    .assertThat()
                    .statusCode(200);
    }

    @Test
    @DisplayName("Status code 200 returned")
    void testStatusCode200_v2() {
        RestAssured
                .get("https://api.postcodes.io/postcodes/EC2Y5AS")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    @DisplayName("Status code 200 returned")
    void testStatusCode200_v3() {
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(200));
    }

    @Test
    @DisplayName("The server name in the headers should be cloudflare")
    void testServerNameIsCloudFlare() {
        MatcherAssert.assertThat(response.header("Server"), Matchers.is("cloudflare"));
    }

}
