package io.github.diogobett.steps;

import static io.github.diogobett.constants.Constants.*;
import static io.github.diogobett.steps.Hooks.context;

import io.cucumber.java.en.When;
import io.restassured.specification.RequestSpecification;

public class ReqResSteps {

  private static final String NEW_USER_BODY = "{\"name\":\"morpheus\",\"job\":\"leader\"}";
  private static final String REPLACEMENT_USER_BODY =
      "{\"name\":\"morpheus\",\"job\":\"zion resident\"}";
  private static final String PARTIAL_USER_BODY = "{\"job\":\"zion resident\"}";
  private static final String INVALID_JSON_BODY = "{not-json";

  @When("User makes a GET request to view the user list")
  public void viewUserList() {
    sendRequest("GET", ENDPOINT_USERS + "?page=2", null);
  }

  @When("User makes a GET request to view user with ID {string}")
  public void viewUser(String userId) {
    sendRequest("GET", ENDPOINT_USERS + "/" + userId, null);
  }

  @When("User makes a POST request to create a valid user")
  public void createUser() {
    sendRequest("POST", ENDPOINT_USERS, NEW_USER_BODY);
  }

  @When("User makes a POST request with invalid JSON")
  public void createUserWithInvalidJson() {
    sendRequest("POST", ENDPOINT_USERS, INVALID_JSON_BODY);
  }

  @When("User makes a PUT request to replace user with ID {string}")
  public void replaceUser(String userId) {
    sendRequest("PUT", ENDPOINT_USERS + "/" + userId, REPLACEMENT_USER_BODY);
  }

  @When("User makes a PUT request with invalid JSON for user {string}")
  public void replaceUserWithInvalidJson(String userId) {
    sendRequest("PUT", ENDPOINT_USERS + "/" + userId, INVALID_JSON_BODY);
  }

  @When("User makes a PATCH request to update user with ID {string}")
  public void updateUser(String userId) {
    sendRequest("PATCH", ENDPOINT_USERS + "/" + userId, PARTIAL_USER_BODY);
  }

  @When("User makes a PATCH request with invalid JSON for user {string}")
  public void updateUserWithInvalidJson(String userId) {
    sendRequest("PATCH", ENDPOINT_USERS + "/" + userId, INVALID_JSON_BODY);
  }

  @When("User makes a DELETE request to delete user with ID {string}")
  public void deleteUser(String userId) {
    sendRequest("DELETE", ENDPOINT_USERS + "/" + userId, null);
  }

  private void sendRequest(String method, String endpoint, String body) {
    RequestSpecification request = context.requestSetup();
    if (body != null) {
      request.body(body);
      context.session.put(SAVED_REQUEST, body);
    } else {
      context.session.remove(SAVED_REQUEST);
    }

    context.session.put(SAVED_ENDPOINT, endpoint);
    context.response = request.request(method, endpoint);
  }
}
