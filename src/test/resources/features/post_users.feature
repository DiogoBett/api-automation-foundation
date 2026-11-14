@POST
Feature: Create users with ReqRes

  @Positive
  Scenario: Create a user
    When User makes a POST request to create a valid user
    Then User should get a status code 201 from the request
    And User should verify the response field "name" equals "morpheus"
    And User should verify the response field "job" equals "leader"
    And User should verify the response field "id" is present
    And User should verify the response field "createdAt" is present

  @Negative
  Scenario: Reject malformed JSON when creating a user
    When User makes a POST request with invalid JSON
    Then User should get a status code 400 from the request
    And User should verify the response field "error" equals "invalid_json"
