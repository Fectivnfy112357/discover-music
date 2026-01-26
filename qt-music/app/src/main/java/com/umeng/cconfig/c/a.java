package com.umeng.cconfig.c;

import android.text.TextUtils;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

/* loaded from: classes3.dex */
public final class a {
    private static HostnameVerifier a;

    public static String a(String str, String str2) throws InterruptedException, IOException {
        for (int i = 0; i < 3; i++) {
            try {
                HttpsURLConnection httpsURLConnectionB = b(str, str2);
                if (httpsURLConnectionB == null || httpsURLConnectionB.getResponseCode() != 200) {
                    return null;
                }
                InputStreamReader inputStreamReader = new InputStreamReader(httpsURLConnectionB.getInputStream());
                StringBuffer stringBuffer = new StringBuffer();
                while (true) {
                    int i2 = inputStreamReader.read();
                    if (i2 == -1) {
                        return stringBuffer.toString();
                    }
                    stringBuffer.append((char) i2);
                }
            } catch (Exception unused) {
                if (i == 2) {
                    return null;
                }
                try {
                    Thread.sleep(20000L);
                } catch (InterruptedException unused2) {
                }
            }
        }
        return null;
    }

    private static HttpsURLConnection b(String str, String str2) throws NoSuchAlgorithmException, IOException, KeyManagementException {
        HttpsURLConnection httpsURLConnection = null;
        try {
            HttpsURLConnection httpsURLConnection2 = (HttpsURLConnection) new URL(str).openConnection();
            try {
                SSLContext sSLContext = SSLContext.getInstance("TLS");
                sSLContext.init(null, null, new SecureRandom());
                httpsURLConnection2.setSSLSocketFactory(sSLContext.getSocketFactory());
                if (a == null) {
                    a = new HostnameVerifier() { // from class: com.umeng.cconfig.c.a.1
                        @Override // javax.net.ssl.HostnameVerifier
                        public final boolean verify(String str3, SSLSession sSLSession) {
                            if (TextUtils.isEmpty(str3)) {
                                return false;
                            }
                            return "ucc.umeng.com".equalsIgnoreCase(str3) || "pslog.umeng.com".equalsIgnoreCase(str3);
                        }
                    };
                }
                httpsURLConnection2.setHostnameVerifier(a);
                httpsURLConnection2.setRequestMethod("POST");
                httpsURLConnection2.setConnectTimeout(15000);
                httpsURLConnection2.setDoOutput(true);
                httpsURLConnection2.setDoInput(true);
                httpsURLConnection2.setReadTimeout(15000);
                httpsURLConnection2.setRequestProperty("Content-Type", "application/json");
                httpsURLConnection2.connect();
                OutputStream outputStream = httpsURLConnection2.getOutputStream();
                outputStream.write(str2.getBytes());
                outputStream.flush();
                outputStream.close();
                return httpsURLConnection2;
            } catch (Exception e) {
                e = e;
                httpsURLConnection = httpsURLConnection2;
                e.printStackTrace();
                return httpsURLConnection;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }
}
