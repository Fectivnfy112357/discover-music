package com.facebook.react.devsupport.inspector;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* loaded from: classes2.dex */
public class InspectorNetworkHelper {
    private static OkHttpClient client;

    private InspectorNetworkHelper() {
    }

    public static void loadNetworkResource(String str, final InspectorNetworkRequestListener inspectorNetworkRequestListener) {
        if (client == null) {
            client = new OkHttpClient.Builder().connectTimeout(10L, TimeUnit.SECONDS).writeTimeout(10L, TimeUnit.SECONDS).readTimeout(0L, TimeUnit.MINUTES).build();
        }
        try {
            client.newCall(new Request.Builder().url(str).build()).enqueue(new Callback() { // from class: com.facebook.react.devsupport.inspector.InspectorNetworkHelper.1
                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException iOException) {
                    if (call.getCanceled()) {
                        return;
                    }
                    inspectorNetworkRequestListener.onError(iOException.getMessage());
                }

                @Override // okhttp3.Callback
                public void onResponse(Call call, Response response) {
                    Headers headers = response.headers();
                    HashMap map = new HashMap();
                    for (String str2 : headers.names()) {
                        map.put(str2, headers.get(str2));
                    }
                    inspectorNetworkRequestListener.onHeaders(response.code(), map);
                    try {
                        ResponseBody responseBodyBody = response.body();
                        if (responseBodyBody != null) {
                            try {
                                InputStream inputStreamByteStream = responseBodyBody.byteStream();
                                byte[] bArr = new byte[1024];
                                while (true) {
                                    try {
                                        int i = inputStreamByteStream.read(bArr);
                                        if (i == -1) {
                                            break;
                                        }
                                        inspectorNetworkRequestListener.onData(new String(bArr, 0, i));
                                    } catch (Throwable th) {
                                        inputStreamByteStream.close();
                                        throw th;
                                    }
                                }
                                inputStreamByteStream.close();
                            } finally {
                            }
                        }
                        inspectorNetworkRequestListener.onCompletion();
                        if (responseBodyBody != null) {
                            responseBodyBody.close();
                        }
                    } catch (IOException e) {
                        inspectorNetworkRequestListener.onError(e.getMessage());
                    }
                }
            });
        } catch (IllegalArgumentException unused) {
            inspectorNetworkRequestListener.onError("Not a valid URL: " + str);
        }
    }
}
