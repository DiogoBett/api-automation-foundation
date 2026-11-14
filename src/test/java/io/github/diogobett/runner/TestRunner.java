package io.github.diogobett.runner;

import static io.github.diogobett.constants.Constants.*;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {
      CUCUMBER_PLUGIN_PRETTY,
      CUCUMBER_PLUGIN_HTML,
      CUCUMBER_PLUGIN_JSON,
      CUCUMBER_PLUGIN_LISTENER
    },
    features = {CUCUMBER_FEATURES},
    glue = {CUCUMBER_GLUE},
    monochrome = true,
    tags = CUCUMBER_TAGS)
public class TestRunner {}
