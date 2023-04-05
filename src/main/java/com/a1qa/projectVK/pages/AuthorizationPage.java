package com.a1qa.projectVK.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ITextBox;
import com.a1qa.projectVK.utils.JsonHelper;
import org.openqa.selenium.By;

public class AuthorizationPage {
    private ITextBox loginField = AqualityServices.getElementFactory().getTextBox(By.xpath("//input[@id='index_email']"), "Login Field");
    private ITextBox passwordField = AqualityServices.getElementFactory().getTextBox(By.xpath("//input[@name='password']"), "Password Field");
    private ICheckBox checkSaveUser = AqualityServices.getElementFactory().getCheckBox(By.xpath("//span[@class='VkIdCheckbox__checkboxOn']"), "CheckBox Save User");
    private IButton signInButton = AqualityServices.getElementFactory().getButton(By.xpath("//span[contains(text(),'Sign in')]"), "Sign In Button");
    private IButton changeAuthorizationMethod = AqualityServices.getElementFactory().getButton(By.xpath("//button[contains(@class,'Password')]"), "Change Autorization Method");
    private IButton continueButton = AqualityServices.getElementFactory().getButton(By.xpath("//span[@class='vkuiButton__in']"), "Continue Button");

    JsonHelper jsonHelper = new JsonHelper();
    public void enterUserData() {
        loginField.type(jsonHelper.getValueFromJson("login"));
        checkSaveUser.check();
        signInButton.click();
        changeAuthorizationMethod.click();
        passwordField.type(jsonHelper.getValueFromJson("pass"));
        continueButton.click();
    }

}
