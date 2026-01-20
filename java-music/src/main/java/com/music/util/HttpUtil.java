package com.music.util;

import okhttp3.*;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class HttpUtil {
    private static final OkHttpClient client = new OkHttpClient.Builder()
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
        String timestamp = String.valueOf(System.currentTimeMillis());
        String nonce = generateRandomString(8);

        // 如果 URL 已经包含参数，用 & 连接，否则用 ?
        String separator = url.contains("?") ? "&" : "?";
        String finalUrl = url + separator + "_ts=" + timestamp + "&_nc=" + nonce;

        Request request = new Request.Builder()
                .url(finalUrl)
                .addHeader("X-Timestamp", timestamp)
                .addHeader("X-Nonce", nonce)
                .addHeader("User-Agent", "okhttp/4.12.0")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return response.body() != null ? response.body().string() : "";
        }
    }
}
