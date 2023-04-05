package com.a1qa.projectVK.utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;

public class VkApiUtils {

    public static HttpResponse<JsonNode> getRequest(String url) {
        try {
            return Unirest.get(url).asJson();
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }

    public static HttpRequestWithBody postRequest(String url) {
        return Unirest.post(url);
    }

}
