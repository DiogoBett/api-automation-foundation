package org.api.automation.foundation.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.api.automation.foundation.utils.TestContext;
import org.junit.Assert;

import static com.vodafone.gfp.automation.steps.GenericSteps.getEndpoint;
import static com.vodafone.gfp.automation.steps.GenericSteps.hasTag;
import static org.api.automation.foundation.constants.Constants.*;

@Slf4j
public class Hooks {

    public static TestContext context;

    @Before
    public void setup() {
        context = new TestContext();
    }

    @After()
    public void tearDown(Scenario scenario) {
        if (context == null) {
            log.error(LOG_ERROR_CONTEXT);
            Assert.fail(LOG_ERROR_CONTEXT);
        }

        if (hasTag(scenario, TAG_MULTIPLE)) {
            scenario.attach(getEndpoint(), ContentType.JSON.toString(), TEST_REPORT_ENDPOINT);

            for (int i = 0; i < context.session.size(); i++) {
                String request = SAVED_REQUEST + i;
                String response = SAVED_RESPONSE + i;

                if (context.session.containsKey(response)) {
                    scenario.attach(context.session.get(SAVED_STATUS + i).toString(), ContentType.JSON.toString(), TEST_REPORT_STATUS + EMPTY_SPACE + i);
                    scenario.attach(context.session.get(SAVED_HEADERS + i).toString(), ContentType.JSON.toString(), TEST_REPORT_HEADERS + EMPTY_SPACE + i);
                    if (context.session.containsKey(request)) {
                        scenario.attach(context.session.get(request).toString(), ContentType.JSON.toString(), TEST_REPORT_REQUEST + EMPTY_SPACE + i);
                    }
                    scenario.attach(context.session.get(response).toString(), ContentType.JSON.toString(), TEST_REPORT_RESPONSE + EMPTY_SPACE + i);
                }
            }
            return;
        }

        if (context.response != null) {
            scenario.attach(getEndpoint(), ContentType.JSON.toString(), TEST_REPORT_ENDPOINT);
            scenario.attach(context.response.getStatusLine(), ContentType.JSON.toString(), TEST_REPORT_STATUS);
            scenario.attach(context.response.getHeaders().toString(), ContentType.JSON.toString(), TEST_REPORT_HEADERS);
            if (context.session.get(SAVED_REQUEST) != null) {
                scenario.attach(context.session.get(SAVED_REQUEST).toString(), ContentType.JSON.toString(), TEST_REPORT_REQUEST);
            }
            scenario.attach(context.response.asPrettyString(), ContentType.JSON.toString(), TEST_REPORT_RESPONSE);
        } else {
            log.error(LOG_ERROR_RESPONSE);
            Assert.fail(LOG_ERROR_RESPONSE);
        }
    }
}