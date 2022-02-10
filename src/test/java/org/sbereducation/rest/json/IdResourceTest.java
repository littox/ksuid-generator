package org.sbereducation.rest.json;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
class IdResourceTest {

    @Test
    @DisplayName("Успешно получить KSUID")
    void successfullyGetKSUID() {
        given()
            .when().get("/id")
            .then()
            .statusCode(200)
            .body("id", notNullValue());
    }

    @Test
    @DisplayName("Должен вернуть 404, если URL не существует")
    void shouldReturnNotFoundWhenUrlDoesNotExist() {
        given()
            .when().get("/fail")
            .then()
            .statusCode(404);
    }


}