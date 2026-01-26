package com.facebook.react.packagerconnection;

import com.facebook.common.logging.FLog;
import kotlin.Metadata;

/* compiled from: RequestOnlyHandler.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u001a\u0010\u0007\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lcom/facebook/react/packagerconnection/RequestOnlyHandler;", "Lcom/facebook/react/packagerconnection/RequestHandler;", "()V", "onNotification", "", "params", "", "onRequest", "responder", "Lcom/facebook/react/packagerconnection/Responder;", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class RequestOnlyHandler implements RequestHandler {
    @Override // com.facebook.react.packagerconnection.RequestHandler
    public abstract void onRequest(Object params, Responder responder);

    @Override // com.facebook.react.packagerconnection.RequestHandler
    public final void onNotification(Object params) {
        FLog.e("JSPackagerClient", "Notification is not supported");
    }
}
