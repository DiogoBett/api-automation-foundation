@POST
Feature: POST Book
  I want to be able to add new books to the system;
  I should receive appropriate responses for valid and invalid requests.

  @Positive
  Scenario: Add a new Book with valid data
    When User makes a POST request to create a valid book
    Then User should get a status code 201 from the request
    And User should get a response with an "OK" message

  @Negative
  Scenario: Add a new Book with missing fields
    When User makes a POST request to create an invalid book
    Then User should get a status code 400 from the request
    And User should verify the error message indicates all missing fields

  @Negative
  Scenario: Add a new Book with invalid content type
    When User makes a POST request to create a book with an invalid Content Type
    Then User should get a status code 415 from the request
    And User should get a response with an "unsupported media type" message