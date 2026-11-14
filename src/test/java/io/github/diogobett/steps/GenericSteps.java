package io.github.diogobett.steps;

import static io.github.diogobett.constants.Constants.*;
import static io.github.diogobett.steps.Hooks.context;
import static io.github.diogobett.utils.PropertiesUtil.getBaseUrl;
import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import java.util.List;

public class GenericSteps {

  @Then("User should get a status code {int} from the request")
  public void validateStatusCode(int statusCode) {
    assertEquals(statusCode, context.response.getStatusCode());
  }

  @Then("User should verify the response field {string} equals {string}")
  public void verifyResponseField(String field, String expectedValue) {
    assertEquals(expectedValue, context.response.jsonPath().getString(field));
  }

  @Then("User should verify the response field {string} is present")
  public void verifyResponseFieldIsPresent(String field) {
    assertNotNull(context.response.jsonPath().get(field));
  }

  @Then("User should verify the user list is not empty")
  public void verifyUserListIsNotEmpty() {
    List<?> users = context.response.jsonPath().getList("data");
    assertNotNull(users);
    assertFalse(users.isEmpty());
  }

  @Then("User should verify the response body is empty")
  public void verifyResponseBodyIsEmpty() {
    assertTrue(context.response.asString().isBlank());
  }

  @Then("User should verify the response body is an empty JSON object")
  public void verifyResponseBodyIsEmptyJsonObject() {
    assertEquals("{}", context.response.asString());
  }

  public static boolean hasTag(Scenario scenario, String tag) {
    return scenario.getSourceTagNames().contains(tag);
  }

  public static String getEndpoint() {
    return getBaseUrl() + context.session.get(SAVED_ENDPOINT);
  }
}
