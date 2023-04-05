package com.a1qa.projectVK.utils;

import aquality.selenium.browser.AqualityServices;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import java.io.File;
import java.io.IOException;

public class GetScreenShot {

    public void getScreenShot() {
        File scrFile = AqualityServices.getBrowser().getDriver().getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("src/test/resources/screenshot.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
