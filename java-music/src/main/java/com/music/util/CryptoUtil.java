package com.music.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CryptoUtil {

    private static final String WY_AES_KEY = "e82ckenh8dichen8";

    public static String md5(String input) {
        return md5(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String md5(byte[] input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input);
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException("MD5 error", e);
        }
    }

    public static String aesEncrypt(String text, String key) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encryptedBytes = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : encryptedBytes) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString().toUpperCase(Locale.ROOT);
        } catch (Exception e) {
            throw new RuntimeException("AES encrypt error", e);
        }
    }

    public static String wyEapiEncrypt(String url, String data) {
        String text = "nobody" + url + "use" + data + "md5forencrypt";
        String digest = md5(text);
        String content = url + "-36cd479b6b5-" + data + "-36cd479b6b5-" + digest;
        return aesEncrypt(content, WY_AES_KEY);
    }

    public static Map<String, String> createMgSignature(String time, String text) {
        String deviceId = "963B7AA0D21511ED807EE5846EC87D20";
        String str = text + "6cdc72a439cef99a3418d2a78aa28c73yyapp2d16148780a1dcc7408e06336b98cfd50963B7AA0D21511ED807EE5846EC87D20" + time;
        String sign = md5(str);
        Map<String, String> map = new HashMap<>();
        map.put("sign", sign);
        map.put("deviceId", deviceId);
        return map;
    }
}
