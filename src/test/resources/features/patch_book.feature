@PATCH
Feature: PATCH Book
  I want to be able to update book information;
  I should also receive appropriate responses for valid and invalid requests.

  Background:
    Given User has created a book previously and saved its information

  @Positive
  Scenario: Update a Book with valid data
    When User makes a PATCH request to update the created book
    Then User should get a status code 200 from the request
    And User should get a response with an "OK" message
    And User should verify the book information has been updated successfully

  @Negative
  Scenario: Update a Book with invalid ID
    When User makes a PATCH request to update the book with ID "9999999999"
    Then User should get a status code 404 from the request
    And User should get a response with an "not found" message

  @Negative
  Scenario: Update a Book with invalid content type
    When User makes a PATCH request to update the created book with an invalid Content Type
    Then User should get a status code 500 from the request
    And User should get a response with an "Empty" message