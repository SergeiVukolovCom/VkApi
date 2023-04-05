package com.a1qa.projectVK;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import com.a1qa.projectVK.pages.AuthorizationPage;
import com.a1qa.projectVK.pages.MyWallPage;
import com.a1qa.projectVK.pages.NewsPage;
import com.a1qa.projectVK.utils.ComparePhotos;
import com.a1qa.projectVK.utils.GetScreenShot;
import com.a1qa.projectVK.utils.JsonHelper;
import com.a1qa.projectVK.requests.VkApiRequests;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.a1qa.projectVK.requests.VkApiRequests.*;

public class Tests extends BaseTest {
    AuthorizationPage mainPage = new AuthorizationPage();
    NewsPage newsPage = new NewsPage();
    MyWallPage myWallPage = new MyWallPage();
    GetScreenShot getScreenShot = new GetScreenShot();
    ComparePhotos comparePhotos = new ComparePhotos();
    Logger logger = AqualityServices.getLogger();

    @Test
    public void tests() {
        mainPage.enterUserData();
        newsPage.myWallPageClick();
        VkApiRequests.postTextToWall();
        logger.info("Checking post on text and user");
        Assert.assertEquals(myWallPage.getMessageFromWall(), stringRandom, "Another text");
        Assert.assertTrue(myWallPage.getUsersNameFromPost().contains(JsonHelper.getValueFromJson("owner_id")), "Another user");
        logger.info("Post on text and user is checked");
        VkApiRequests.getDataFromUploadUrl();
        VkApiRequests.saveUploadWallPhoto();
        VkApiRequests.editPostOnWall();
        getScreenShot.getScreenShot();
        logger.info("Checking post on changing text and uploading image");
        Assert.assertTrue(comparePhotos.compareTwoPhoto(), "Different Images");
        Assert.assertEquals(myWallPage.getMessageFromWall(), stringRand, "Another text");
        logger.info("Post on changing text and uploading image is checked");
        VkApiRequests.createComment();
        myWallPage.clickToWrapComments();
        logger.info("Checking post on adding comment");
        Assert.assertEquals(myWallPage.getMessageFromComment(), stringComment, "Another text");
        logger.info("Post on adding comment is checked");
        myWallPage.clickLikeButton();
        logger.info("Checking post on adding like");
        Assert.assertTrue(VkApiRequests.getLike().contains(JsonHelper.getValueFromJson("owner_id")), "Like from another user");
        logger.info("Post on adding comment is checked");
        VkApiRequests.deletePostOnWall();
        logger.info("Checking post on deleting");
        Assert.assertFalse(myWallPage.isPostDisplayed(), "Post doesn't delete");
        logger.info("post is deleted");
    }

}
