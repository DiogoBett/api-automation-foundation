package org.api.automation.foundation.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import static org.api.automation.foundation.constants.Constants.*;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {CUCUMBER_PLUGIN_PRETTY,
                CUCUMBER_PLUGIN_HTML,
                CUCUMBER_PLUGIN_JSON,
                CUCUMBER_PLUGIN_LISTENER
        }
        ,features= {CUCUMBER_FEATURES}
        ,glue = {CUCUMBER_GLUE}
        ,monochrome = true
        ,tags = CUCUMBER_TAGS
)
public class TestRunner {
}