@PATCH
Feature: Partially update users with ReqRes

  @Positive
  Scenario: Partially update a user
    When User makes a PATCH request to update user with ID "2"
    Then User should get a status code 200 from the request
    And User should verify the response field "job" equals "zion resident"
    And User should verify the response field "updatedAt" is present

  @Negative
  Scenario: Reject malformed JSON when partially updating a user
    When User makes a PATCH request with invalid JSON for user "2"
    Then User should get a status code 400 from the request
    And User should verify the response field "error" equals "invalid_json"
