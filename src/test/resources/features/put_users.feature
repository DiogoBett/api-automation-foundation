@PUT
Feature: Replace users with ReqRes

  @Positive
  Scenario: Replace a user
    When User makes a PUT request to replace user with ID "2"
    Then User should get a status code 200 from the request
    And User should verify the response field "name" equals "morpheus"
    And User should verify the response field "job" equals "zion resident"
    And User should verify the response field "updatedAt" is present

  @Negative
  Scenario: Reject malformed JSON when replacing a user
    When User makes a PUT request with invalid JSON for user "2"
    Then User should get a status code 400 from the request
    And User should verify the response field "error" equals "invalid_json"
