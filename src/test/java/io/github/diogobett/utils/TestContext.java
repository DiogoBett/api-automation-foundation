package io.github.diogobett.utils;

import static io.github.diogobett.constants.Constants.*;
import static io.github.diogobett.utils.PropertiesUtil.getBaseUrl;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import java.util.Map;

public final class TestContext {

  public Response response;

  public Map<String, Object> session;

  private final HeadersUtil headers;

  private int counter;

  public TestContext() {
    this.session = new HashMap<String, Object>();
    this.headers = new HeadersUtil();
  }

  public RequestSpecification requestSetup() {
    RestAssured.reset();
    RestAssured.baseURI = getBaseUrl();
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
}
