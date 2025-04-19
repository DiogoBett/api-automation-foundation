package org.api.automation.foundation.steps;

import com.github.javafaker.Faker;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.api.automation.foundation.model.BookDTO;
import org.api.automation.foundation.model.MessageDTO;
import org.junit.Assert;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static org.api.automation.foundation.constants.Constants.*;
import static org.api.automation.foundation.steps.CreateBookSteps.generateBook;
import static org.api.automation.foundation.steps.CreateBookSteps.postBook;
import static org.api.automation.foundation.steps.Hooks.context;
import static org.api.automation.foundation.utils.JSONUtil.dtoToString;
import static org.api.automation.foundation.utils.JSONUtil.jsonToDto;
import static org.api.automation.foundation.utils.PropertiesUtil.getProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Slf4j
public class GenericSteps {

    @Given("User has created a book previously and saved its information")
    public static void createAndSaveBook() {
        postBook(dtoToString(generateBook()));
        assertEquals(201, context.response.getStatusCode());
        saveBookInformation((BookDTO) jsonToDto(context.session.get(SAVED_REQUEST).toString(), DTO_BOOK));
    }

    @Then("User should get a status code {int} from the request")
    public void validateSingleStatusCode(int statusCode) {
        assertEquals(statusCode, context.response.getStatusCode());
    }

    @Then("User should get a status code {int} from the requests")
    public void validateMultipleStatusCodes(int statusCode) {
        for (int i = 0; i < context.session.size(); i++) {
            String status = SAVED_STATUS + i;
            if (context.session.containsKey(status)) {
                assertTrue(context.session.get(status).toString().contains(String.valueOf(statusCode)));
            }
        }
    }

    @Then("User should get a response with an {string} message")
    public void validateResponseMessage(String errorMessage) {
        MessageDTO responseDto = (MessageDTO) jsonToDto(context.response.asPrettyString(), DTO_MESSAGE);
        String assertionError = LOG_ERROR_EXPECTED + errorMessage + LOG_ERROR_ACTUAL + responseDto.getMessage();
        assertTrue(assertionError, responseDto.getMessage().contains(errorMessage));
    }

    @Then("User should verify that the book with id {string} checked status is {string}")
    public void verifyBookCheckedStatus(String bookId, String checkedStatus) {
        validateBookStatus(bookId, checkedStatus);
    }

    private void validateBookStatus(String bookId, String checkedStatus) {
        BookDTO bookDto = (BookDTO) jsonToDto(context.response.asPrettyString(), DTO_BOOK);
        assertEquals(checkedStatus, Boolean.toString(bookDto.isCheckedOut()));
        assertEquals(bookId, bookDto.getId());
    }

    private static void saveBookInformation(BookDTO dto) {
        if (!(dto.getId() == null)) {
            context.session.put(SAVED_BOOK_ID, dto.getId());
            context.session.put(SAVED_BOOK_CREATED_AT, dto.getCreatedAt());
            context.session.put(SAVED_BOOK_CHECKED_OUT, dto.isCheckedOut());
        }
        context.session.put(SAVED_BOOK_NAME, dto.getTitle());
        context.session.put(SAVED_BOOK_GENRE, dto.getGenre());
        context.session.put(SAVED_BOOK_AUTHOR, dto.getAuthor());
        context.session.put(SAVED_BOOK_YEAR_PUBLISHED, dto.getYear());
    }

    public static void wait(int seconds) {
        try {
            Thread.sleep((long) seconds * TIME_ONE_SECOND_IN_MILLIS);
        } catch (InterruptedException e) {
            log.error(LOG_ERROR_WAIT + seconds);
            Assert.fail(LOG_ERROR_WAIT + seconds);
        }
    }

    public static boolean hasTag(Scenario scenario, String tag) {
        return scenario.getSourceTagNames().contains(tag);
    }

    public static int generateNumber(int from, int to) {
        return ThreadLocalRandom.current().nextInt(from, to + 1);
    }

    public static String generateParameters(Map<String, String> parameters) {
        StringBuilder paramString = new StringBuilder("?");
        parameters.forEach((key, value) -> paramString.append(key).append("=").append(value).append("&"));
        return paramString.substring(0, paramString.length() - 1);
    }

    public static Faker getValue() {
        Faker faker;
        if (context.session.containsKey(SAVED_FAKER)) {
            faker = (Faker) context.session.get(SAVED_FAKER);
        } else {
            faker = new Faker(Locale.GERMAN);
            context.session.put(SAVED_FAKER, faker);
        }
        return faker;
    }

    public static String getEndpoint() {
        if (context.session.containsKey(SAVED_PARAMETERS)) {
            return getProperty(PROPERTY_URL_1) + context.session.get(SAVED_ENDPOINT) + context.session.get(SAVED_PARAMETERS).toString();
        }
        return getProperty(PROPERTY_URL_1) + context.session.get(SAVED_ENDPOINT).toString();
    }
}