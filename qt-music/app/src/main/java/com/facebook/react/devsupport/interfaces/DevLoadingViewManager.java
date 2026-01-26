package com.facebook.react.devsupport.interfaces;

import androidx.core.app.NotificationCompat;
import com.facebook.react.devsupport.StackTraceHelper;
import kotlin.Metadata;

/* compiled from: DevLoadingViewManager.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J+\u0010\u0007\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\u00062\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH&¢\u0006\u0002\u0010\fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Lcom/facebook/react/devsupport/interfaces/DevLoadingViewManager;", "", "hide", "", "showMessage", StackTraceHelper.MESSAGE_KEY, "", "updateProgress", NotificationCompat.CATEGORY_STATUS, "done", "", "total", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)V", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface DevLoadingViewManager {
    void hide();

    void showMessage(String message);

    void updateProgress(String status, Integer done, Integer total);
}
