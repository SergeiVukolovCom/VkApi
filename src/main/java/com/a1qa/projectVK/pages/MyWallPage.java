package com.a1qa.projectVK.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import org.openqa.selenium.By;

import static com.a1qa.projectVK.utils.VkApiRequests.postId;

public class MyWallPage {

    public String getMessageFromWall() {
        ITextBox messageInPost = AqualityServices.getElementFactory()
                .getTextBox(By.xpath(String.format("//div[contains(@id,'%s')]//div[contains(@class,'wall_post_text')]", postId)), "Message in post");
        return messageInPost.getText();
    }

    public String getUsersNameFromPost() {
        ITextBox postFromUser = AqualityServices.getElementFactory()
                .getTextBox(By.xpath(String.format("//div[contains(@id,'%s')]//a[contains(@class,'author')]", postId)), "User's name");
        return postFromUser.getAttribute("href");
    }

    public void clickLikeButton() {
        IButton likeButton = AqualityServices.getElementFactory().getButton
                (By.xpath(String.format("//div[contains(@data-reaction-target-object,'%s')]//div[@class='PostButtonReactions__icon ']", postId)), "Like button");
        likeButton.getJsActions().scrollToTheCenter();
        likeButton.clickAndWait();
    }

    public void clickToWrapComments() {
        IButton wrapComments = AqualityServices.getElementFactory().getButton
                (By.xpath(String.format("//div[contains(@id,'%s')]//span[@class='js-replies_next_label']", postId)), "Wrap list comments");
        wrapComments.getJsActions().scrollIntoView();
        wrapComments.getJsActions().click();
    }

    public String getMessageFromComment() {
        ITextBox commentMessage = AqualityServices.getElementFactory().getTextBox
                (By.xpath(String.format("//div[contains(@id,'%s')]//div[@class='wall_reply_text']", postId)), "Comment message");
        return commentMessage.getText();
    }

    public boolean isPostDisplayed() {
        ITextBox post = AqualityServices.getElementFactory().getTextBox
                (By.xpath(String.format("//div[contains(@id,'%s')]//div[contains(@class,'wall_post_cont')]", postId)), "Post");
        return post.state().isDisplayed();
    }

}
