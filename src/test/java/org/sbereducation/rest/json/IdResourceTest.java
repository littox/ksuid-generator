package org.sbereducation.rest.json;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class IdResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/id")
          .then()
             .statusCode(200)
             .body(is("Hello RESTEasy"));
    }

}