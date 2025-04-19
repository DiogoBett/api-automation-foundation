@GET
Feature: GET Book
  I want to be able to fetch a list of books and also details of specific books by ID;
  I should receive appropriate responses for valid and invalid requests.

  @Positive
  Scenario: Get a List of Books
    When User makes a GET request to view all books
    Then User should get a status code 200 from the request
    And User should verify that the Book list is not empty

  @Positive
  Scenario: Get a List of Books with a certain Name
    Given User makes a POST request to create a book with the Title "Example #1"
    When User makes a GET request to view books with Title "Example #1"
    Then User should get a status code 200 from the request
    And User should verify that all books in the list have Title "Example #1"

  @Positive
  Scenario: Get a List of Books with a certain 'checkedOut' Status
    When User makes a GET request to view books with Checked Out Status "true"
    Then User should get a status code 200 from the request
    And User should verify that all books in the list have Checked Out Status "true"

  @Positive
  Scenario: Get a List of Books with a certain Genre
    When User makes a GET request to view books with Genre "fiction"
    Then User should get a status code 200 from the request
    And User should verify that all books in the list have Genre "fiction"

  @Positive
  Scenario: Get a Book with a valid ID
    When User makes a GET request to view the book with ID "ZUST9JFx-Sd9X0k"
    Then User should get a status code 200 from the request
    And User should verify the title of the book is "Ficciones"

  @Negative
  Scenario: Get a Book with an invalid ID
    When User makes a GET request to view the book with ID "9999999999"
    Then User should get a status code 404 from the request
    And User should get a response with an "not found" message

  @Negative
  Scenario: Get a List of Books with an invalid Genre
    When User makes a GET request to view books with Genre "unknown"
    Then User should get a status code 200 from the request
    And User should verify that the Book list is empty