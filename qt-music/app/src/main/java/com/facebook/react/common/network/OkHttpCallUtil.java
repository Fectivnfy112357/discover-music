package com.facebook.react.common.network;

import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;

/* compiled from: OkHttpCallUtil.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0001H\u0007¨\u0006\b"}, d2 = {"Lcom/facebook/react/common/network/OkHttpCallUtil;", "", "()V", "cancelTag", "", "client", "Lokhttp3/OkHttpClient;", "tag", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OkHttpCallUtil {
    public static final OkHttpCallUtil INSTANCE = new OkHttpCallUtil();

    private OkHttpCallUtil() {
    }

    @JvmStatic
    public static final void cancelTag(OkHttpClient client, Object tag) {
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(tag, "tag");
        Method method = OkHttpClient.class.getMethod("dispatcher", new Class[0]);
        method.setAccessible(true);
        Object objInvoke = method.invoke(client, new Object[0]);
        Intrinsics.checkNotNull(objInvoke, "null cannot be cast to non-null type okhttp3.Dispatcher");
        Dispatcher dispatcher = (Dispatcher) objInvoke;
        for (Call call : dispatcher.queuedCalls()) {
            if (Intrinsics.areEqual(tag, call.request().tag())) {
                call.cancel();
                return;
            }
        }
        for (Call call2 : dispatcher.runningCalls()) {
            if (Intrinsics.areEqual(tag, call2.request().tag())) {
                call2.cancel();
                return;
            }
        }
    }
}
