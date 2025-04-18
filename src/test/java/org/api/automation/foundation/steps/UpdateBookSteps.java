package org.api.automation.foundation.steps;

import io.cucumber.java.en.When;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.api.automation.foundation.model.BookDTO;

import static org.api.automation.foundation.constants.Constants.*;
import static org.api.automation.foundation.steps.CreateBookSteps.generateBook;
import static org.api.automation.foundation.steps.Hooks.context;
import static org.api.automation.foundation.utils.JSONUtil.dtoToString;

@Slf4j
public class UpdateBookSteps {

    @When("User makes a PATCH request to update the book with ID {string}")
    public void patchEntireBook(String bookId) {
        patchBook(generateBook(), bookId);
    }

    @When("User makes a PATCH request to update the created book")
    public void patchUpdateSavedOrderStatus() {
        patchEntireBook(context.session.get(SAVED_BOOK_ID).toString());
    }

    public static void patchBook(BookDTO dto, String bookId) {
        RequestSpecification request = context.requestSetup(SCOPE_EXAMPLE_1);
        String endpoint = ENDPOINT_EXAMPLE_2.replace(PARAMETER_ID, bookId);

        request.basePath(endpoint);
        request.body(dtoToString(dto));

        context.session.put(SAVED_ENDPOINT, endpoint);
        context.session.put(SAVED_REQUEST, dtoToString(dto));
        context.response = request.patch();
    }
}