package org.sbereducation.rest.json;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

import io.quarkus.test.junit.QuarkusTest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
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

    @Disabled
    @Test
    @DisplayName("Сгенерированные KSUIDы сортируются")
    synchronized void generatedIdsAreSorted() {
        TreeMap<Integer, String> testMap = new TreeMap<>();
        List<String> listString = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            String responseId =
                given()
                    .when().get("/id").then().extract().path("id");
            testMap.put(i, responseId);
            listString.add(responseId);
        }
        Collections.sort(listString);
        for (Entry<Integer, String> integerStringEntry : testMap.entrySet()) {
            assert integerStringEntry.getValue().equals(listString.get(integerStringEntry.getKey()));
        }
    }
}