package org.api.automation.foundation.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.api.automation.foundation.model.BookDTO;

import java.util.Collections;
import java.util.List;

import static org.api.automation.foundation.constants.Constants.*;
import static org.api.automation.foundation.steps.GenericSteps.generateParameters;
import static org.api.automation.foundation.steps.Hooks.context;
import static org.api.automation.foundation.utils.JSONUtil.jsonToDto;
import static org.junit.Assert.*;

@Slf4j
public class ViewBookSteps {

    @When("User makes a GET request to view all books")
    public void viewAllBooks() {
        getBookList(context.requestSetup(SCOPE_EXAMPLE_1));
    }

    @When("User makes a GET request to view the book with ID {string}")
    public void viewBookWithId(String bookId) {
        getBook(context.requestSetup(SCOPE_EXAMPLE_1), bookId);
    }

    @When("User makes a GET request to view books with Name {string}")
    public void viewBookWithName(String bookTitle) {
        RequestSpecification request = context.requestSetup(SCOPE_EXAMPLE_1);
        request.queryParam(QUERY_SEARCH, bookTitle);

        context.session.put(SAVED_PARAMETERS, generateParameters(Collections.singletonMap(QUERY_SEARCH, bookTitle)));
        getBookList(request);
    }

    @When("User makes a GET request to view books with Genre {string}")
    public void viewBookWithGenre(String bookGenre) {
        RequestSpecification request = context.requestSetup(SCOPE_EXAMPLE_1);
        request.queryParam(QUERY_GENRE, bookGenre);

        context.session.put(SAVED_PARAMETERS, generateParameters(Collections.singletonMap(QUERY_GENRE, bookGenre)));
        getBookList(request);
    }

    @When("User makes a GET request to view books with {string} Status {string}")
    public void viewBookWithStatus(String bookStatus) {
        RequestSpecification request = context.requestSetup(SCOPE_EXAMPLE_1);
        request.queryParam(QUERY_STATUS, bookStatus);

        context.session.put(SAVED_PARAMETERS, generateParameters(Collections.singletonMap(QUERY_STATUS, bookStatus)));
        getBookList(request);
    }

    @Then("User should verify the title of the book is {string}")
    public void verifyBookTitle(String bookTitle) {
        BookDTO book = (BookDTO) jsonToDto(context.response.asPrettyString(), DTO_BOOK);
        assertEquals(bookTitle, book.getTitle());
    }

    @Then("User should verify that all books in the list have Genre {string}")
    public void userShouldVerifyThatAllBooksInTheListHaveGenre(String bookGenre) {
        List<BookDTO> bookList = (List<BookDTO>) jsonToDto(context.response.asPrettyString(), DTO_BOOK_LIST);
        for (BookDTO book : bookList) {
            assertEquals(bookGenre, book.getGenre());
        }
    }

    @Then("User should verify that the Book list is not empty")
    public void verifyBookListIsNotEmpty() {
        List<BookDTO> bookList = (List<BookDTO>) jsonToDto(context.response.asPrettyString(), DTO_BOOK_LIST);
        assertFalse(bookList.isEmpty());
    }

    @Then("User should verify that the Book list is empty")
    public void verifyBookListIsEmpty() {
        List<BookDTO> bookList = (List<BookDTO>) jsonToDto(context.response.asPrettyString(), DTO_BOOK_LIST);
        assertTrue(bookList.isEmpty());
    }

    private void getBookList(RequestSpecification request) {
        request.basePath(ENDPOINT_EXAMPLE_1);

        context.session.put(SAVED_ENDPOINT, ENDPOINT_EXAMPLE_1);
        context.response = request.get();
    }

    private void getBook(RequestSpecification request, String bookId) {
        String endpoint = ENDPOINT_EXAMPLE_2.replace(PARAMETER_ID, bookId);
        request.basePath(endpoint);

        context.session.put(SAVED_ENDPOINT, endpoint);
        context.response = request.get();
    }
}