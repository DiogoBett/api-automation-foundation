package org.api.automation.foundation.steps;

import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.api.automation.foundation.model.CreateBookDTO;

import java.time.LocalDate;

import static org.api.automation.foundation.constants.Constants.*;
import static org.api.automation.foundation.steps.GenericSteps.generateNumber;
import static org.api.automation.foundation.steps.GenericSteps.getValue;
import static org.api.automation.foundation.steps.Hooks.context;
import static org.api.automation.foundation.utils.JSONUtil.dtoToString;

@Slf4j
public class CreateBookSteps {

    @When("User makes a POST request to create a valid book")
    public void userMakesARequestToCreateAValidBook() {
        postBook(dtoToString(generateBook()));
    }

    @When("User makes several POST requests to create {int} valid books")
    public void userMakesARequestToCreateMultipleValidOrders(int size) {
        for (int i = 0; i < size; i++) {
            userMakesARequestToCreateAValidBook();
            context.saveTestContext();
        }
    }

    @When("User makes a POST request to create an invalid book")
    public void userMakesARequestToCreateAInvalidBook() {
        postBook(EMPTY_JSON);
    }

    @When("User makes several POST requests to create {int} invalid books")
    public void userMakesARequestToCreateXNumberInvalidOrders(int size) {
        for (int i = 0; i < size; i++) {
            userMakesARequestToCreateAInvalidBook();
            context.saveTestContext();
        }
    }

    @When("User makes a POST request to create a valid order with invalid Content Type")
    public void userMakesACreateOrderRequestWithInvalidContentType() {
        RequestSpecification request = context.requestSetup(SCOPE_EXAMPLE_1);
        request.basePath(ENDPOINT_EXAMPLE_1);
        request.contentType(ContentType.TEXT);
        request.body(EMPTY_JSON);

        context.session.put(SAVED_ENDPOINT, ENDPOINT_EXAMPLE_1);
        context.session.put(SAVED_REQUEST, EMPTY_JSON);
        context.response = request.post();
    }

    public static void postBook(String body) {
        RequestSpecification request = context.requestSetup(SCOPE_EXAMPLE_1);
        request.basePath(ENDPOINT_EXAMPLE_1);
        request.body(body);

        context.session.put(SAVED_ENDPOINT, ENDPOINT_EXAMPLE_1);
        context.session.put(SAVED_REQUEST, body);
        context.response = request.post();
    }

    public static CreateBookDTO generateBook() {
        CreateBookDTO requestDto = new CreateBookDTO();
        requestDto.setTitle(getValue().book().title());
        requestDto.setAuthor(getValue().book().author());
        requestDto.setGenre(getValue().book().genre());
        requestDto.setYear(generateNumber(1500, LocalDate.now().getYear()));
        return requestDto;
    }
}