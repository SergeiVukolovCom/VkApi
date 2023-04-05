package com.a1qa.projectVK.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import org.openqa.selenium.By;

public class NewsPage {
    private IButton myPageButton = AqualityServices.getElementFactory().getButton
            (By.xpath("//span[@class='LeftMenu__itemLabel'][contains(text(),'Моя') or contains(text(),'My')]"), "My Page Button");

    public void myWallPageClick() {
        myPageButton.clickAndWait();
    }

}
