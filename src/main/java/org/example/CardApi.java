package org.example;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

public class CardApi extends BaseApi {

    public String createCard(String listId, String name) {
        return given(spec)
                .contentType(ContentType.JSON)
                .queryParam("idList", listId)
                .queryParam("name", name)
                .post("/cards")
                .then().statusCode(200)
                .extract().path("id");
    }

    public void updateCard(String cardId, String newName) {
        given(spec)
                .contentType(ContentType.JSON)
                .queryParam("name", newName)
                .put("/cards/{id}", cardId)
                .then().statusCode(200);
    }

    public void deleteCard(String cardId) {
        given(spec)
                .delete("/cards/{id}", cardId)
                .then().statusCode(200);
    }
}
