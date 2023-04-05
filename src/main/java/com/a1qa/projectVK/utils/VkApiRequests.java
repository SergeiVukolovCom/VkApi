package com.a1qa.projectVK.utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.File;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

public class VkApiRequests {
    public static int postId;
    private static List<String> dataFromUrl = new LinkedList<>();
    private static RandomString randomString = new RandomString();
    public static String stringRandom;
    public static String stringRand;
    public static String stringComment;

    public static void postTextToWall() {
        stringRandom = randomString.getRandomString();
        String request = String.format(JsonHelper.getValueFromRequestJson("wall.post"), JsonHelper.getValueFromJson("owner_id"),
                stringRandom, JsonHelper.getValueFromJson("token"), JsonHelper.getValueFromJson("api_version"));
        try {
            postId = VkApiUtils.postRequest(request).asJson().getBody().getObject().getJSONObject("response").getInt("post_id");
            System.out.println(postId);
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }

    public static int editPostOnWall() {
        stringRand = randomString.getRandomString();
        String request = String.format(JsonHelper.getValueFromRequestJson("wall.edit"), JsonHelper.getValueFromJson("owner_id"),
                postId, JsonHelper.getValueFromJson("owner_id"), saveUploadWallPhoto(), stringRand,
                JsonHelper.getValueFromJson("token"), JsonHelper.getValueFromJson("api_version"));
        try {
            return VkApiUtils.postRequest(request).asJson().getStatus();
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }

    public static int deletePostOnWall() {
        String request = String.format(JsonHelper.getValueFromRequestJson("wall.delete"), JsonHelper.getValueFromJson("owner_id"),
                postId, JsonHelper.getValueFromJson("token"), JsonHelper.getValueFromJson("api_version"));
        try {
            return VkApiUtils.postRequest(request).asJson().getStatus();
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getWallPhotoUploadServer() {
        String request = String.format(JsonHelper.getValueFromRequestJson("photos.getWallUploadServer"), JsonHelper.getValueFromJson("owner_id"),
                JsonHelper.getValueFromJson("token"), JsonHelper.getValueFromJson("api_version"));
        try {
            return VkApiUtils.postRequest(request).asJson().getBody().getObject().getJSONObject("response").getString("upload_url");
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> getDataFromUploadUrl() {
        try {
             HttpResponse<JsonNode> response = VkApiUtils.postRequest(getWallPhotoUploadServer()).field("photo", new File("src/test/resources/avatar.png")).asJson();
             dataFromUrl.add(response.getBody().getObject().getString("photo"));
             dataFromUrl.add(String.valueOf(response.getBody().getObject().getInt("server")));
             dataFromUrl.add(response.getBody().getObject().getString("hash"));
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
        return dataFromUrl;
    }

    public static int saveUploadWallPhoto() {
        String request = String.format(JsonHelper.getValueFromRequestJson("photos.saveWallPhoto"),
                JsonHelper.getValueFromJson("owner_id"), URLEncoder.encode(dataFromUrl.get(0), StandardCharsets.UTF_8), dataFromUrl.get(1),
                dataFromUrl.get(2), JsonHelper.getValueFromJson("token"), JsonHelper.getValueFromJson("api_version"));
        try {
            return VkApiUtils.postRequest(request).asJson().getBody().getObject().getJSONArray("response").getJSONObject(0).getInt("id");
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }

    public static int createComment() {
        stringComment = randomString.getRandomString();
        String request = String.format(JsonHelper.getValueFromRequestJson("wall.createComment"),
                JsonHelper.getValueFromJson("owner_id"), postId, stringComment, JsonHelper.getValueFromJson("token"),
                JsonHelper.getValueFromJson("api_version"));
        try {
            return VkApiUtils.postRequest(request).asJson().getStatus();
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getLike() {
        String request = String.format(JsonHelper.getValueFromRequestJson("likes.getList"), JsonHelper.getValueFromJson("owner_id"),
                postId, JsonHelper.getValueFromJson("token"), JsonHelper.getValueFromJson("api_version"));
        return VkApiUtils.getRequest(request).getBody().getObject().getJSONObject("response").getJSONArray("items").get(0).toString();
    }

}
