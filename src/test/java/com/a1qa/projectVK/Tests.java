package com.a1qa.projectVK;

import com.a1qa.projectVK.pages.AuthorizationPage;
import com.a1qa.projectVK.pages.MyWallPage;
import com.a1qa.projectVK.pages.NewsPage;
import com.a1qa.projectVK.utils.ComparePhotos;
import com.a1qa.projectVK.utils.GetScreenShot;
import com.a1qa.projectVK.utils.JsonHelper;
import com.a1qa.projectVK.utils.VkApiRequests;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.a1qa.projectVK.utils.VkApiRequests.*;

public class Tests extends BaseTest {
    AuthorizationPage mainPage = new AuthorizationPage();
    NewsPage newsPage = new NewsPage();
    MyWallPage myWallPage = new MyWallPage();
    GetScreenShot getScreenShot = new GetScreenShot();
    ComparePhotos comparePhotos = new ComparePhotos();

    @Test
    public void tests() {
        mainPage.enterUserData();
        newsPage.myWallPageClick();
        VkApiRequests.postTextToWall();
        Assert.assertEquals(myWallPage.getMessageFromWall(), stringRandom, "Another text");
        Assert.assertTrue(myWallPage.getUsersNameFromPost().contains(JsonHelper.getValueFromJson("owner_id")), "Another user");
        VkApiRequests.getDataFromUploadUrl();
        VkApiRequests.saveUploadWallPhoto();
        VkApiRequests.editPostOnWall();
        getScreenShot.getScreenShot();
        Assert.assertTrue(comparePhotos.compareTwoPhoto(), "Different Images");
        Assert.assertEquals(myWallPage.getMessageFromWall(), stringRand, "Another text");
        VkApiRequests.createComment();
        myWallPage.clickToWrapComments();
        Assert.assertEquals(myWallPage.getMessageFromComment(), stringComment, "Another text");
        myWallPage.clickLikeButton();
        Assert.assertTrue(VkApiRequests.getLike().contains(JsonHelper.getValueFromJson("owner_id")), "Like from another user");
        VkApiRequests.deletePostOnWall();
        Assert.assertFalse(myWallPage.isPostDisplayed(), "Post doesn't delete");
    }

}
