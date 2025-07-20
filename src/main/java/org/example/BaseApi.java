package org.example;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public abstract class BaseApi {
    static  String BASE_URL = "https://api.trello.com/1";
    static  String KEY      = "test_key";;
    static  String TOKEN    = "test_token";;

    public static final RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri(BASE_URL)
            .addQueryParam("key",   KEY)
            .addQueryParam("token", TOKEN)
            .build();
}
