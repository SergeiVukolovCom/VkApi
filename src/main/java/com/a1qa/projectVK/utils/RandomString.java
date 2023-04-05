package com.a1qa.projectVK.utils;

public class RandomString {

    public String getRandomString() {
        StringBuilder randString = new StringBuilder();
        String latinSymbols = "abcdefghijklmnopqrstuvwxyz1234567890";
        int count = 10;
        for (int i = 0; i<count; i++) {
            randString.append(latinSymbols.charAt((int) (Math.random() * latinSymbols.length())));
        }
        return randString.toString();
    }

}
