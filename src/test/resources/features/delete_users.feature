@DELETE
Feature: Delete users with ReqRes

  @Positive
  Scenario: Delete a user
    When User makes a DELETE request to delete user with ID "2"
    Then User should get a status code 204 from the request
    And User should verify the response body is empty

  @Negative
  Scenario: Delete a user that does not exist
    When User makes a DELETE request to delete user with ID "23"
    Then User should get a status code 204 from the request
    And User should verify the response body is empty
