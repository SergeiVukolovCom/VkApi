package com.a1qa.projectVK.utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class JsonHelper {

    public static String getValueFromJson(String key) {
        ISettingsFile environment = new JsonSettingsFile("testsdata.json");
        return environment.getValue(String.format("/%s", key)).toString();
    }

    public static String getValueFromRequestJson(String key) {
        ISettingsFile environment = new JsonSettingsFile("requests.json");
        return environment.getValue(String.format("/%s", key)).toString();
    }

}
