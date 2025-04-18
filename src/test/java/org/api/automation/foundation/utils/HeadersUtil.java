package org.api.automation.foundation.utils;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.OptionalInt;
import java.util.stream.IntStream;

import static java.lang.System.arraycopy;
import static org.api.automation.foundation.constants.Constants.*;
import static org.api.automation.foundation.utils.PropertiesUtil.getProperty;

@Slf4j
public class HeadersUtil {

    @Getter
    private Headers headers;

    private Header[] headerArray;

    private String authToken;

    private String scope;

    public HeadersUtil() {
        this.headerArray = new Header[]{
                new Header(HEADER_ACCEPT, CONTENT_TYPE_JSON),
                new Header(HEADER_CONTENT_TYPE, CONTENT_TYPE_JSON),
                new Header(HEADER_ENVIRONMENT, getProperty(PROPERTY_ENVIRONMENT))
        };
        this.headers = new Headers(headerArray);
    }

    public void setupScope(String scope) {
        if (this.scope == null || !this.scope.equals(scope)) {
            if (scope.equals(SCOPE_EXAMPLE_2)) {
                log.info(LOG_INFO_HEADERS_SCOPE + scope);
            } else {
                log.info(LOG_INFO_HEADERS_DEFAULT + scope);
            }
            AuthenticationUtil auth = new AuthenticationUtil(scope);
            this.authToken = auth.getAuthenticationToken();
            this.scope = scope;
        }
    }

    public void setupAuth() {
        Header authenticationHeader = new Header(HEADER_AUTH, AUTH_BEARER + authToken);
        addHeader(authenticationHeader);
    }

    private void addHeader(Header header) {
        if (!headers.hasHeaderWithName(header.getName())) {
            Header[] updatedHeadersArray = new Header[headerArray.length + 1];

            arraycopy(headerArray, 0, updatedHeadersArray, 0, headerArray.length);
            updatedHeadersArray[headerArray.length] = header;

            this.headerArray = updatedHeadersArray;
            this.headers = new Headers(updatedHeadersArray);
        } else {
            removeHeader(header.getName());
            addHeader(header);
        }
    }

    private void removeHeader(String key) {
        OptionalInt indexToRemove = IntStream.range(0, headerArray.length)
                .filter(i -> headerArray[i].getName().equals(key))
                .findFirst();

        if (indexToRemove.isPresent()) {
            int removeIndex = indexToRemove.getAsInt();
            Header[] updatedHeadersArray = new Header[headerArray.length - 1];

            System.arraycopy(headerArray, 0, updatedHeadersArray, 0, removeIndex);
            System.arraycopy(headerArray, removeIndex + 1, updatedHeadersArray, removeIndex, headerArray.length - removeIndex - 1);

            this.headerArray = updatedHeadersArray;
            this.headers = new Headers(updatedHeadersArray);
        }
    }
}