package com.a1qa.projectVK;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import com.a1qa.projectVK.utils.JsonHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    private JsonHelper jsonHelper = new JsonHelper();
    private Browser browser = AqualityServices.getBrowser();

    @BeforeMethod
    public void setup() {
        browser.maximize();
        browser.goTo(jsonHelper.getValueFromJson("url"));
        browser.waitForPageToLoad();
    }

    @AfterMethod
    public void teardown() {
        browser.quit();
    }

}
