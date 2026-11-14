package io.github.diogobett.constants;

public class Constants {
  public static final String CHARSET_UTF = "UTF-8";
  public static final String CONTENT_TYPE_JSON = "application/json";
  public static final String CUCUMBER_FEATURES = "features";
  public static final String CUCUMBER_GLUE = "io.github.diogobett.steps";
  public static final String CUCUMBER_PLUGINS =
      "pretty:target/cucumber-report/cucumber.txt,html:target/generated-report.html,"
          + "json:target/cucumber-report/cucumber.json,io.github.diogobett.utils.TestListener";
  public static final String CUCUMBER_TAGS = "@GET or @POST or @PUT or @PATCH or @DELETE";
  public static final String EMPTY_SPACE = " ";
  public static final String ENDPOINT_USERS = "/api/users";
  public static final String HEADER_ACCEPT = "Accept";
  public static final String HEADER_CONTENT_TYPE = "Content-Type";
  public static final String LOG_ERROR_CHECKSUM = "IO EXCEPTION WHILE GENERATING CHECKSUM: ";
  public static final String LOG_ERROR_CONTEXT = "TEST CONTEXT HASN'T BEEN INITIALIZED";
  public static final String LOG_ERROR_IO_COMPARE = "IO EXCEPTION WHILE COMPARING FILES";
  public static final String LOG_ERROR_IO_FILE = "IO EXCEPTION WHILE CREATING FILE";
  public static final String LOG_ERROR_IO_FILE_STREAM =
      "IO EXCEPTION WHILE READING BYTES FROM FILE: ";
  public static final String LOG_ERROR_IO_OVERWRITE = "IO EXCEPTION WHILE OVERWRITING FILE";
  public static final String LOG_ERROR_IO_PROPERTIES =
      "IO EXCEPTION WHILE SETTING UP PROPERTIES FILE";
  public static final String LOG_ERROR_IO_ZIP = "IO EXCEPTION WHILE ZIPPING FILE";
  public static final String LOG_ERROR_RESPONSE = "RESPONSE FROM TEST CONTEXT DOESN'T EXIST";
  // public static final String LOG_ERROR_WAIT = "UNABLE TO WAIT (X) SECONDS: ";
  public static final String LOG_INFO_ENDPOINT = "  ENDPOINT: ";
  public static final String LOG_INFO_REQUEST =
      "**********************REQUEST**************************";
  public static final String LOG_INFO_REQUEST_MULTIPLE = "**********************REQUEST #";
  public static final String LOG_INFO_RESPONSE =
      "**********************RESPONSE*************************";
  public static final String LOG_INFO_RESPONSE_HEADERS =
      "**********************HEADERS**************************";
  public static final String LOG_INFO_RESPONSE_HEADERS_MULTIPLE = "**********************HEADERS #";
  public static final String LOG_INFO_RESPONSE_MULTIPLE = "**********************RESPONSE #";
  public static final String LOG_INFO_RESPONSE_STATUS = "  STATUS: ";
  public static final String LOG_INFO_TEST_DIVISOR =
      "*******************************************************";
  public static final String LOG_INFO_TEST_DIVISOR_HALF = "***********************";
  public static final String LOG_INFO_TEST_FINISH = "  FINISH - Scenario: ";
  public static final String LOG_INFO_TEST_REASON = " REASON --> ";
  public static final String LOG_INFO_TEST_START = "  START - Scenario: ";
  public static final String LOG_INFO_TEST_STATUS = "  STATUS --> ";
  public static final String LOG_INFO_TEST_STATUS_MULTIPLE = "  STATUS #";
  public static final String LOG_WARN_INVALID_PROPERTY = "Property OR Value doesn't exist: \n";
  public static final String PARAMETER_ID = "{id}";
  public static final String PATH_PROPERTIES = "src/test/resources/config.properties";
  public static final String PROPERTY_BASE_URL_PREFIX = "base.url.";
  public static final String PROPERTY_ENVIRONMENT = "environment";
  public static final String SAVED_ENDPOINT = "savedEndpoint";
  public static final String SAVED_HEADERS = "savedHeaders";
  public static final String SAVED_ID = "savedId";
  public static final String SAVED_REQUEST = "savedRequest";
  public static final String SAVED_RESPONSE = "savedResponse";
  public static final String SAVED_STATUS = "savedStatus";
  public static final String TAG_MULTIPLE = "@Multiple";
  public static final String TEST_REPORT_ENDPOINT = "Endpoint";
  public static final String TEST_REPORT_HEADERS = "Headers";
  public static final String TEST_REPORT_REQUEST = "Request";
  public static final String TEST_REPORT_RESPONSE = "Response";
  public static final String TEST_REPORT_STATUS = "Status";
  // public static final int TIME_ONE_SECOND_IN_MILLIS = 1000;
}
