package org.api.automation.foundation.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

import static org.api.automation.foundation.constants.Constants.*;
import static org.api.automation.foundation.utils.PropertiesUtil.getProperty;
import static org.api.automation.foundation.utils.PropertiesUtil.setProperty;

@Slf4j
public class AuthenticationUtil {

    private final String accessTokenUrl;
    private final String clientId;
    private final String clientSecret;
    private final String scope;

    public AuthenticationUtil(String scope) {
        String accessTokenUrl;
        switch (scope) {
            case SCOPE_EXAMPLE_2:
                accessTokenUrl = getProperty(PROPERTY_URL_2);
                clientId = getProperty(PROPERTY_API_ID);
                clientSecret = getProperty(PROPERTY_API_SC);
                break;
            case SCOPE_EXAMPLE_3:
                accessTokenUrl = getProperty(PROPERTY_URL_3);
                clientId = getProperty(PROPERTY_API_ID);
                clientSecret = getProperty(PROPERTY_API_SC);
                break;
            default:
                accessTokenUrl = getProperty(PROPERTY_URL_1);
                clientId = getProperty(PROPERTY_API_ID);
                clientSecret = getProperty(PROPERTY_API_SC);
        }
        this.accessTokenUrl = accessTokenUrl + ENDPOINT_OAUTH2;
        this.scope = scope;
    }

    public String getAuthenticationToken() {
        RequestSpecification request = RestAssured.given()
                .contentType(ContentType.URLENC.withCharset(CHARSET_UTF))
                .accept(ContentType.JSON)
                .formParam(AUTH_GRANT, AUTH_CREDENTIALS)
                .formParam(AUTH_SCOPE, scope);

        Response response = request
                .auth().preemptive().basic(clientId, clientSecret)
                .post(accessTokenUrl);

        String accessToken = response.jsonPath().getString(AUTH_TOKEN);
        setProperty(PROPERTY_TOKEN_OAUTH, accessToken);
        return accessToken;
    }
}