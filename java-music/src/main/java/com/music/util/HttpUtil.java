package com.music.util;

import okhttp3.*;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class HttpUtil {
    public static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    private static final String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(CHARS.charAt(random.nextInt(CHARS.length())));
        }
        return sb.toString();
    }

    public static String get(String url) throws IOException {
        return get(url, null);
    }

    public static String get(String url, Map<String, String> headers) throws IOException {
        Request.Builder builder = new Request.Builder().url(url);
        if (headers != null) {
            headers.forEach(builder::addHeader);
        }
        // Default headers if not present
        if (headers == null || !headers.containsKey("User-Agent")) {
            builder.addHeader("User-Agent", "okhttp/4.12.0");
        }

        try (Response response = client.newCall(builder.build()).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return response.body() != null ? response.body().string() : "";
        }
    }

    public static String post(String url, String json, Map<String, String> headers) throws IOException {
        RequestBody body = RequestBody.create(json, MediaType.parse("application/json; charset=utf-8"));
        Request.Builder builder = new Request.Builder().url(url).post(body);
        
        if (headers != null) {
            headers.forEach(builder::addHeader);
        }
        
        try (Response response = client.newCall(builder.build()).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return response.body() != null ? response.body().string() : "";
        }
    }

    public static String postForm(String url, Map<String, String> params, Map<String, String> headers) throws IOException {
        FormBody.Builder formBuilder = new FormBody.Builder();
        if (params != null) {
            params.forEach(formBuilder::add);
        }
        Request.Builder builder = new Request.Builder().url(url).post(formBuilder.build());

        if (headers != null) {
            headers.forEach(builder::addHeader);
        }

        try (Response response = client.newCall(builder.build()).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return response.body() != null ? response.body().string() : "";
        }
    }
}
