package org.api.automation.foundation.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.api.automation.foundation.model.BookDTO;

import static org.api.automation.foundation.constants.Constants.*;
import static org.api.automation.foundation.steps.CreateBookSteps.generateBook;
import static org.api.automation.foundation.steps.Hooks.context;
import static org.api.automation.foundation.utils.JSONUtil.dtoToString;
import static org.api.automation.foundation.utils.JSONUtil.jsonToDto;
import static org.junit.Assert.assertEquals;

@Slf4j
public class UpdateBookSteps {

    @When("User makes a PATCH request to update the book with ID {string}")
    public void updateValidBook(String bookId) {
        BookDTO book = generateBook();
        book.setCheckedOut(Math.random() < 0.5);
        patchBook(book, bookId);
    }

    @When("User makes a PATCH request to update the created book")
    public void updateSavedValidBook() {
        updateValidBook(context.session.get(SAVED_BOOK_ID).toString());
    }

    @When("User makes a PATCH request to update the created book with an invalid Content Type")
    public void updateSavedBookWithInvalidContentType() {
        RequestSpecification request = context.requestSetup(SCOPE_EXAMPLE_1);
        String endpoint = ENDPOINT_EXAMPLE_2.replace(PARAMETER_ID, context.session.get(SAVED_BOOK_ID).toString());
        String body = dtoToString(generateBook());

        request.contentType(ContentType.TEXT);
        request.basePath(endpoint);
        request.body(body);

        context.session.put(SAVED_ENDPOINT, endpoint);
        context.session.put(SAVED_REQUEST, body);
        context.response = request.patch();
    }

    @Then("User should verify the book information has been updated successfully")
    public void verifyBookHasBeenUpdated() {
        RequestSpecification request = context.requestSetup(SCOPE_EXAMPLE_1);
        String endpoint = ENDPOINT_EXAMPLE_2.replace(PARAMETER_ID, context.session.get(SAVED_BOOK_ID).toString());
        request.basePath(endpoint);

        BookDTO expectedDto = (BookDTO) context.session.get(SAVED_BOOK_DTO);
        BookDTO actualDto = (BookDTO) jsonToDto(request.get().asPrettyString(), DTO_BOOK);

        assertEquals(expectedDto.getTitle(), actualDto.getTitle());
        assertEquals(expectedDto.getAuthor(), actualDto.getAuthor());
        assertEquals(expectedDto.getGenre(), actualDto.getGenre());
        assertEquals(expectedDto.isCheckedOut(), actualDto.isCheckedOut());
    }

    public static void patchBook(BookDTO dto, String bookId) {
        RequestSpecification request = context.requestSetup(SCOPE_EXAMPLE_1);
        String endpoint = ENDPOINT_EXAMPLE_2.replace(PARAMETER_ID, bookId);

        request.basePath(endpoint);
        request.body(dtoToString(dto));

        context.session.put(SAVED_ENDPOINT, endpoint);
        context.session.put(SAVED_REQUEST, dtoToString(dto));
        context.session.put(SAVED_BOOK_DTO, dto);
        context.response = request.patch();
    }
}