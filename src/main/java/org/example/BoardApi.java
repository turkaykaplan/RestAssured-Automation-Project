package org.example;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class BoardApi extends BaseApi {




    public String createBoard(String name) {
        return given(spec)
                .contentType(ContentType.JSON)
                .queryParam("name", name)
                .post("/boards")
                .then().statusCode(200)
                .extract().path("id");
    }

    public void deleteBoard(String boardId) {
        given(spec)
                .delete("/boards/{id}", boardId)
                .then().statusCode(200);
    }
}
