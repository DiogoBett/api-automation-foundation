package org.api.automation.foundation.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static org.api.automation.foundation.constants.Constants.*;
import static org.api.automation.foundation.utils.PropertiesUtil.getProperty;

public final class TestContext {

    public Response response;

    public Map<String, Object> session;

    private final HeadersUtil headers;

    private int counter;

    public TestContext() {
        this.session = new HashMap<String, Object>();
        this.headers = new HeadersUtil();
    }

    public RequestSpecification requestSetup(String scope) {
        RestAssured.reset();
        RestAssured.baseURI = setupBaseUri(scope);
        headers.setupScope(scope);
        headers.setupAuth();
        return RestAssured.given().headers(headers.getHeaders());
    }

    public void saveTestContext() {
        counter++;

        if (response != null) {
            session.put(SAVED_ID + counter, session.get(SAVED_ID));
            session.put(SAVED_STATUS + counter, response.getStatusLine());
            session.put(SAVED_HEADERS + counter, response.getHeaders().toString());
            if (session.get(SAVED_REQUEST) != null) {
                session.put(SAVED_REQUEST + counter, session.get(SAVED_REQUEST).toString());
            }
            session.put(SAVED_RESPONSE + counter, response.asPrettyString());
        }
    }

    private String setupBaseUri(String scope) {
        switch (scope) {
            case SCOPE_EXAMPLE_2:
                return getProperty(PROPERTY_URL_2);
            case SCOPE_EXAMPLE_3:
                return getProperty(PROPERTY_URL_3);
            default:
                return getProperty(PROPERTY_URL_1);
        }
    }
}