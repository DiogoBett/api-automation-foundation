package io.github.diogobett.steps;

import static io.github.diogobett.constants.Constants.*;
import static io.github.diogobett.steps.GenericSteps.generateNumber;
import static io.github.diogobett.steps.GenericSteps.getValue;
import static io.github.diogobett.steps.Hooks.context;
import static io.github.diogobett.utils.JSONUtil.dtoToString;
import static io.github.diogobett.utils.JSONUtil.jsonToDto;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.diogobett.model.BookDTO;
import io.github.diogobett.model.MessageDTO;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateBookSteps {

  @Given("User makes a POST request to create a book with the Title {string}")
  public void createBookWithSpecificTitle(String bookTitle) {
    BookDTO book = generateBook();
    book.setTitle(bookTitle);
    postBook(dtoToString(book));
  }

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
