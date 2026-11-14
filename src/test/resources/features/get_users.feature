@GET
Feature: Retrieve users from ReqRes

  @Positive
  Scenario: List users
    When User makes a GET request to view the user list
    Then User should get a status code 200 from the request
    And User should verify the user list is not empty

  @Positive
  Scenario: Retrieve an existing user
    When User makes a GET request to view user with ID "2"
    Then User should get a status code 200 from the request
    And User should verify the response field "data.email" equals "janet.weaver@reqres.in"

  @Negative
  Scenario: Retrieve a user that does not exist
    When User makes a GET request to view user with ID "23"
    Then User should get a status code 404 from the request
    And User should verify the response body is an empty JSON object
