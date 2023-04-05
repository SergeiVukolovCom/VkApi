package com.a1qa.projectVK.requests;

import com.a1qa.projectVK.utils.JsonHelper;
import com.a1qa.projectVK.utils.RandomString;
import com.a1qa.projectVK.utils.VkApiUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.File;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

public class VkApiRequests {

    public static int postTextToWall(String text) {
        String request = String.format(JsonHelper.getValueFromRequestJson("wall.post"), JsonHelper.getValueFromJson("owner_id"),
                text, JsonHelper.getValueFromJson("token"), JsonHelper.getValueFromJson("api_version"));
        try {
            return VkApiUtils.postRequest(request).asJson().getBody().getObject().getJSONObject("response").getInt("post_id");
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }

    public static int editPostOnWall(String text, String editText) {
        String request = String.format(JsonHelper.getValueFromRequestJson("wall.edit"), JsonHelper.getValueFromJson("owner_id"),
                postTextToWall(text), JsonHelper.getValueFromJson("owner_id"), saveUploadWallPhoto(), editText,
                JsonHelper.getValueFromJson("token"), JsonHelper.getValueFromJson("api_version"));
        try {
            return VkApiUtils.postRequest(request).asJson().getStatus();
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }

    public static int deletePostOnWall(String text) {
        String request = String.format(JsonHelper.getValueFromRequestJson("wall.delete"), JsonHelper.getValueFromJson("owner_id"),
                postTextToWall(text), JsonHelper.getValueFromJson("token"), JsonHelper.getValueFromJson("api_version"));
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
        List<String> dataFromUrl = new LinkedList<>();
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
                JsonHelper.getValueFromJson("owner_id"), URLEncoder.encode(getDataFromUploadUrl().get(0), StandardCharsets.UTF_8), getDataFromUploadUrl().get(1),
                getDataFromUploadUrl().get(2), JsonHelper.getValueFromJson("token"), JsonHelper.getValueFromJson("api_version"));
        try {
            return VkApiUtils.postRequest(request).asJson().getBody().getObject().getJSONArray("response").getJSONObject(0).getInt("id");
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }

    public static int createComment(String text, String comment) {
        String request = String.format(JsonHelper.getValueFromRequestJson("wall.createComment"),
                JsonHelper.getValueFromJson("owner_id"), postTextToWall(text), comment, JsonHelper.getValueFromJson("token"),
                JsonHelper.getValueFromJson("api_version"));
        try {
            return VkApiUtils.postRequest(request).asJson().getStatus();
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getLike(String text) {
        String request = String.format(JsonHelper.getValueFromRequestJson("likes.getList"), JsonHelper.getValueFromJson("owner_id"),
                postTextToWall(text), JsonHelper.getValueFromJson("token"), JsonHelper.getValueFromJson("api_version"));
        return VkApiUtils.getRequest(request).getBody().getObject().getJSONObject("response").getJSONArray("items").get(0).toString();
    }

}
