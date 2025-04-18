package org.api.automation.foundation.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.api.automation.foundation.model.BookDTO;

import java.util.List;

import static org.api.automation.foundation.constants.Constants.*;
import static org.api.automation.foundation.steps.Hooks.context;
import static org.api.automation.foundation.utils.JSONUtil.jsonToDto;
import static org.junit.Assert.assertFalse;

@Slf4j
public class ViewBookSteps {

    @When("User makes a GET request to view all books")
    public void viewAllBooks() {
        getBookList(context.requestSetup(SCOPE_EXAMPLE_1));
    }

    @Then("User should verify that the Book list is not empty")
    public void verifyBookListIsNotEmpty() {
        List<BookDTO> bookList = (List<BookDTO>) jsonToDto(context.response.asPrettyString(), DTO_BOOK_LIST);
        assertFalse(bookList.isEmpty());
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