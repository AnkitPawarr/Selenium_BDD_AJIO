package com.Jio.utilities;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import static com.Jio.factory.DriverFactory.log;

public class SchemaValidator {

    public static void validateSchema(Response response, String schemaFilePath) {
        try {
            response.then().assertThat()
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaFilePath));
            log.info("JSON Schema validation PASSED.");
        } catch (RuntimeException e) {
            log.error("JSON Schema validation FAILED.", e.getCause());
        }
    }
}