package org.api.automation.foundation.utils;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;
import lombok.extern.slf4j.Slf4j;
import org.api.automation.foundation.steps.Hooks;

import static org.api.automation.foundation.constants.Constants.*;
import static org.api.automation.foundation.steps.GenericSteps.getEndpoint;

@Slf4j
public class TestListener implements ConcurrentEventListener {

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseStarted.class, this::handleTestCaseStarted);
        publisher.registerHandlerFor(TestCaseFinished.class, this::handleTestCaseFinished);
    }

    private void handleTestCaseStarted(TestCaseStarted event) {
        TestCase scenario = event.getTestCase();

        log.info(LOG_INFO_TEST_DIVISOR);
        log.info(LOG_INFO_TEST_START + scenario.getName());
    }

    private void handleTestCaseFinished(TestCaseFinished event) {
        TestCase scenario = event.getTestCase();
        Result result = event.getResult();
        Throwable error = result.getError();

         if (!scenario.getTags().contains(TAG_MULTIPLE)) {
             logSingleTestContextInfo();
         } else {
             logMultipleTestContextInfo();
         }

        log.info(LOG_INFO_TEST_FINISH + scenario.getName());
        log.info(LOG_INFO_TEST_STATUS + result.getStatus().name());

        if(error != null) {
            if (error.getMessage() == null) {
                log.error(LOG_INFO_TEST_REASON + error.toString().toUpperCase());
            } else {
                log.error(LOG_INFO_TEST_REASON + error.getMessage().toUpperCase());
            }
        }
        log.info(LOG_INFO_TEST_DIVISOR);
    }

    private void logSingleTestContextInfo() {
        if (Hooks.context.response != null) {
            log.info(LOG_INFO_TEST_DIVISOR);
            log.info(LOG_INFO_ENDPOINT + getEndpoint());
            log.info(LOG_INFO_RESPONSE_STATUS + Hooks.context.response.getStatusLine());
            log.info(LOG_INFO_RESPONSE_HEADERS);
            log.info(Hooks.context.response.getHeaders().toString());
            if (Hooks.context.session.get(SAVED_REQUEST) != null) {
                log.info(LOG_INFO_REQUEST);
                log.info(Hooks.context.session.get(SAVED_REQUEST).toString());
            }
            log.info(LOG_INFO_RESPONSE);
            log.info(Hooks.context.response.asPrettyString());
            log.info(LOG_INFO_TEST_DIVISOR);
        } else {
            log.error(LOG_ERROR_RESPONSE);
        }
    }

    private void logMultipleTestContextInfo() {
        if (Hooks.context.response != null) {
            log.info(LOG_INFO_TEST_DIVISOR);
            log.info(LOG_INFO_ENDPOINT + getEndpoint());
        } else {
            log.error(LOG_ERROR_RESPONSE);
        }

        for (int i = 0; i < Hooks.context.session.size(); i++) {
            String request = SAVED_REQUEST + i;
            String response = SAVED_RESPONSE + i;

            if (Hooks.context.session.containsKey(response)) {
                log.info(LOG_INFO_TEST_STATUS_MULTIPLE + i + ": " + Hooks.context.session.get(SAVED_STATUS + i).toString());
                log.info(LOG_INFO_RESPONSE_HEADERS_MULTIPLE + i + LOG_INFO_TEST_DIVISOR_HALF);
                log.info(Hooks.context.session.get(SAVED_HEADERS + i).toString());
                if (Hooks.context.session.containsKey(request)) {
                    log.info(LOG_INFO_REQUEST_MULTIPLE + i + LOG_INFO_TEST_DIVISOR_HALF);
                    log.info(Hooks.context.session.get(request).toString());
                }
                log.info(LOG_INFO_RESPONSE_MULTIPLE + i + LOG_INFO_TEST_DIVISOR_HALF);
                log.info(Hooks.context.session.get(response).toString());
                log.info(LOG_INFO_TEST_DIVISOR);
            }
        }
    }
}