package com.facebook.react.interfaces.fabric;

import android.content.Context;
import android.view.ViewGroup;
import com.facebook.react.interfaces.TaskInterface;
import com.umeng.analytics.pro.f;
import kotlin.Metadata;

/* compiled from: ReactSurface.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0015\u001a\u00020\u0016H&J\b\u0010\u0017\u001a\u00020\u0016H&J\u000e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H&J\u000e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H&J\u000e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\bR\u0012\u0010\t\u001a\u00020\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\r\u001a\u00020\u000eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u0004\u0018\u00010\u0012X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u001dÀ\u0006\u0001"}, d2 = {"Lcom/facebook/react/interfaces/fabric/ReactSurface;", "", f.X, "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "isRunning", "", "()Z", "moduleName", "", "getModuleName", "()Ljava/lang/String;", "surfaceID", "", "getSurfaceID", "()I", "view", "Landroid/view/ViewGroup;", "getView", "()Landroid/view/ViewGroup;", "clear", "", "detach", "prerender", "Lcom/facebook/react/interfaces/TaskInterface;", "Ljava/lang/Void;", "start", "stop", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface ReactSurface {
    void clear();

    void detach();

    Context getContext();

    String getModuleName();

    int getSurfaceID();

    ViewGroup getView();

    boolean isRunning();

    TaskInterface<Void> prerender();

    TaskInterface<Void> start();

    TaskInterface<Void> stop();
}
