@GET
Feature: GET Book
  I want to be able to fetch a list of books and also details of specific books by ID;
  I should receive appropriate responses for valid and invalid requests.

  @Positive
  Scenario: Get a List of Books
    When User makes a GET request to view all books
    Then User should get a status code 200 from the request
    And User should verify that the Book list is not empty

#  @Positive
#  Scenario: Get a List of Books with a certain Genre
#    When User makes a GET request to view books with Genre "fiction"
#    Then User should get a status code 200 from the request
#    And User should verify that all books in the list have Genre "fiction"
#
#  @Positive
#  Scenario: Get a Book by a valid ID
#    Given User has an existing book with ID "12345"
#    When User makes a GET request to view the book with ID "12345"
#    Then User should get a status code 200 from the request
#    And User should verify the details of the book with ID "12345"
#
#  @Negative
#  Scenario: Get a Book by an invalid ID
#    When User makes a GET request to view the book with ID "9999999999"
#    Then User should get a status code 404 from the request
#    And User should verify the error message indicates the book was not found
#
#  @Negative
#  Scenario: Get a List of Books with an invalid Genre
#    When User makes a GET request to view books with Genre "unknown"
#    Then User should get a status code 200 from the request
#    And User should verify that the Book list is empty