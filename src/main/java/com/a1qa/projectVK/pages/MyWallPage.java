package com.a1qa.projectVK.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import com.a1qa.projectVK.requests.VkApiRequests;
import org.openqa.selenium.By;

public class MyWallPage {

    public String getMessageFromWall(String text) {
        ITextBox messageInPost = AqualityServices.getElementFactory()
                .getTextBox(By.xpath(String.format("//div[contains(@id,'%s')]//div[contains(@class,'wall_post_text')]", VkApiRequests.postTextToWall(text))), "Message in post");
        return messageInPost.getText();
    }

    public String getUsersNameFromPost(String text) {
        ITextBox postFromUser = AqualityServices.getElementFactory()
                .getTextBox(By.xpath(String.format("//div[contains(@id,'%s')]//a[contains(@class,'author')]", VkApiRequests.postTextToWall(text))), "User's name");
        return postFromUser.getAttribute("href");
    }

    public void clickLikeButton(String text) {
        IButton likeButton = AqualityServices.getElementFactory().getButton
                (By.xpath(String.format("//div[contains(@data-reaction-target-object,'%s')]//div[@class='PostButtonReactions__icon ']",
                VkApiRequests.postTextToWall(text))), "Like button");
        likeButton.getJsActions().scrollToTheCenter();
        likeButton.clickAndWait();
    }

    public void clickToWrapComments(String text) {
        IButton wrapComments = AqualityServices.getElementFactory().getButton
                (By.xpath(String.format("//div[contains(@id,'%s')]//span[@class='js-replies_next_label']", VkApiRequests.postTextToWall(text))), "Wrap list comments");
        wrapComments.getJsActions().scrollIntoView();
        wrapComments.getJsActions().click();
    }

    public String getMessageFromComment(String text) {
        ITextBox commentMessage = AqualityServices.getElementFactory().getTextBox
                (By.xpath(String.format("//div[contains(@id,'%s')]//div[@class='wall_reply_text']", VkApiRequests.postTextToWall(text))), "Comment message");
        return commentMessage.getText();
    }

    public boolean isPostDisplayed(String text) {
        ITextBox post = AqualityServices.getElementFactory().getTextBox
                (By.xpath(String.format("//div[contains(@id,'%s')]//div[contains(@class,'wall_post_cont')]", VkApiRequests.postTextToWall(text))), "Post");
        return post.state().isDisplayed();
    }

}
