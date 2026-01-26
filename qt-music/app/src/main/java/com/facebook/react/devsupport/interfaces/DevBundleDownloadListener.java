package com.facebook.react.devsupport.interfaces;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;

/* compiled from: DevBundleDownloadListener.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u0006H&J+\u0010\u0007\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH&¢\u0006\u0002\u0010\rJ\b\u0010\u000e\u001a\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0001"}, d2 = {"Lcom/facebook/react/devsupport/interfaces/DevBundleDownloadListener;", "", "onFailure", "", "cause", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onProgress", NotificationCompat.CATEGORY_STATUS, "", "done", "", "total", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)V", "onSuccess", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface DevBundleDownloadListener {
    void onFailure(Exception cause);

    void onProgress(String status, Integer done, Integer total);

    void onSuccess();
}
