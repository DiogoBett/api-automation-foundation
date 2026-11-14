package io.github.diogobett.runner;

import static io.cucumber.junit.platform.engine.Constants.ANSI_COLORS_DISABLED_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.FILTER_TAGS_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import static io.github.diogobett.constants.Constants.*;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource(CUCUMBER_FEATURES)
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = CUCUMBER_GLUE)
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = CUCUMBER_PLUGINS)
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = CUCUMBER_TAGS)
@ConfigurationParameter(key = ANSI_COLORS_DISABLED_PROPERTY_NAME, value = "true")
public class TestRunner {}
