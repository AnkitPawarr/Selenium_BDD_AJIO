package com.Jio.restResources;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;

public class RestResouce {

    // Define a ResponseSpecification for responses status code
    private ResponseSpecification responseSpec200 = expect().log().all().statusCode(200);

    private ResponseSpecification responseSpec201 = expect().log().all().statusCode(201);

    private ResponseSpecification responseSpec200Or201 =
            expect().log().all().statusCode(anyOf(equalTo(200), equalTo(201)));

    private ResponseSpecification responseSpec404 = expect().log().all().statusCode(404);

    private ResponseSpecification responseSpec400 = expect().log().all().statusCode(400);

    private ResponseSpecification responseSpec401 = expect().log().all().statusCode(401);

    private ResponseSpecification responseSpec403 = expect().log().all().statusCode(403);


    private RequestSpecification setUpRequest(String baseUri, ContentType contentType, Map<String, Object> headers,
                                              Map<String, Object> queryParams, Map<String, Object> pathParams) {
        RequestSpecification request = RestAssured.given().log().all()
                .baseUri(baseUri)
                .contentType(contentType)
                .accept(contentType);
        if (headers != null) {
            request.headers(headers);
        }
        if (queryParams != null) {
            request.queryParams(queryParams);
        }
        if (pathParams != null) {
            request.pathParams(pathParams);
        }

        return request;
    }


    public Response get(RequestSpecification request, String baseUri, String endPoints, Map<String, Object> headers, Map<String, Object> queryParams,
                        Map<String, Object> pathParams, ContentType contentType) {
        return request.get(endPoints).then().spec(responseSpec200).extract().response();

    }

    public Response post(RequestSpecification request, String baseUri, String endPoints, File body, Map<String, Object> headers, Map<String, Object> queryParams,
                         Map<String, Object> pathParams, ContentType contentType) {
        return request.body(body).post(endPoints).then().spec(responseSpec201).extract().response();

    }

}