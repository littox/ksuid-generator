package org.sbereducation.rest.json;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import io.quarkus.test.junit.QuarkusTest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
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
            .body("id", notNullValue()).body("id.size()", is(27));
    }

    @Test
    @DisplayName("Должен вернуть 404, если URL не существует")
    void shouldReturnNotFoundWhenUrlDoesNotExist() {
        given()
            .when().get("/fail")
            .then()
            .statusCode(404);
    }

    @Disabled("Отсортированный список не совпадает")
    @Test
    @DisplayName("Сгенерированные KSUIDы сортируются")
    void generatedIdsAreSorted() {
        Map<Integer, Object> testMap = new HashMap<>();
        List<String> listString = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            String responseId =
                given()
                    .when().get("/id").then().extract().path("id");
            testMap.put(i, responseId);
            listString.add(responseId);
        }
        Collections.sort(listString);
        for (Entry<Integer, Object> integerStringEntry : testMap.entrySet()) {
            Assertions.assertEquals(integerStringEntry.getValue(), listString.get(integerStringEntry.getKey()));
        }

    }
}