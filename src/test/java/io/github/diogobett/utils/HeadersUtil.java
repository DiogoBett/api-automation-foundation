package io.github.diogobett.utils;

import static io.github.diogobett.constants.Constants.*;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import lombok.Getter;

public class HeadersUtil {

  @Getter private final Headers headers;

  public HeadersUtil() {
    this.headers =
        new Headers(
            new Header(HEADER_ACCEPT, CONTENT_TYPE_JSON),
            new Header(HEADER_CONTENT_TYPE, CONTENT_TYPE_JSON));
  }
}
