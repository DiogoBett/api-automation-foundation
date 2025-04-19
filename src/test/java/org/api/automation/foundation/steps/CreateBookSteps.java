package org.api.automation.foundation.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.api.automation.foundation.model.BookDTO;
import org.api.automation.foundation.model.MessageDTO;

import java.time.LocalDate;

import static org.api.automation.foundation.constants.Constants.*;
import static org.api.automation.foundation.steps.GenericSteps.generateNumber;
import static org.api.automation.foundation.steps.GenericSteps.getValue;
import static org.api.automation.foundation.steps.Hooks.context;
import static org.api.automation.foundation.utils.JSONUtil.dtoToString;
import static org.api.automation.foundation.utils.JSONUtil.jsonToDto;
import static org.junit.Assert.assertTrue;

@Slf4j
public class CreateBookSteps {

    @When("User makes a POST request to create a valid book")
    public void createValidBook() {
        postBook(dtoToString(generateBook()));
    }

    @When("User makes several POST requests to create {int} valid books")
    public void createMultipleValidBooks(int size) {
        for (int i = 0; i < size; i++) {
            createValidBook();
            context.saveTestContext();
        }
    }

    @When("User makes a POST request to create an invalid book")
    public void createInvalidBook() {
        postBook(EMPTY_JSON);
    }

    @When("User makes several POST requests to create {int} invalid books")
    public void createMultipleInvalidBooks(int size) {
        for (int i = 0; i < size; i++) {
            createInvalidBook();
            context.saveTestContext();
        }
    }

    @When("User makes a POST request to create a book with an invalid Content Type")
    public void createBookWithInvalidContentType() {
        RequestSpecification request = context.requestSetup(SCOPE_EXAMPLE_1);
        request.basePath(ENDPOINT_EXAMPLE_1);
        request.contentType(ContentType.TEXT);
        request.body(dtoToString(generateBook()));

        context.session.put(SAVED_ENDPOINT, ENDPOINT_EXAMPLE_1);
        context.session.put(SAVED_REQUEST, EMPTY_JSON);
        context.response = request.post();
    }

    @Then("User should verify the error message indicates all missing fields")
    public void verifyMissingFieldsErrors() {
        MessageDTO message = (MessageDTO) jsonToDto(context.response.asPrettyString(), DTO_MESSAGE);
        for (MessageDTO.ErrorDTO error : message.getErrors()) {
            assertTrue(error.getMessage().matches(REGEX_MISSING_PROPERTY));
        }
    }

    public static void postBook(String body) {
        RequestSpecification request = context.requestSetup(SCOPE_EXAMPLE_1);
        request.basePath(ENDPOINT_EXAMPLE_1);
        request.body(body);

        context.session.put(SAVED_ENDPOINT, ENDPOINT_EXAMPLE_1);
        context.session.put(SAVED_REQUEST, body);
        context.response = request.post();
    }

    public static BookDTO generateBook() {
        BookDTO requestDto = new BookDTO();
        requestDto.setTitle(getValue().book().title());
        requestDto.setAuthor(getValue().book().author());
        requestDto.setGenre(getValue().book().genre());
        requestDto.setYear(generateNumber(1500, LocalDate.now().getYear()));
        return requestDto;
    }
}