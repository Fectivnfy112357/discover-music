package com.facebook.react.devsupport;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.common.SurfaceDelegate;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.util.RNLog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LogBoxDialogSurfaceDelegate.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\nH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\b\u0010\u0012\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/facebook/react/devsupport/LogBoxDialogSurfaceDelegate;", "Lcom/facebook/react/common/SurfaceDelegate;", "devSupportManager", "Lcom/facebook/react/devsupport/interfaces/DevSupportManager;", "(Lcom/facebook/react/devsupport/interfaces/DevSupportManager;)V", "dialog", "Lcom/facebook/react/devsupport/LogBoxDialog;", "reactRootView", "Landroid/view/View;", "createContentView", "", "appKey", "", "destroyContentView", "hide", "isContentViewReady", "", "isShowing", "show", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LogBoxDialogSurfaceDelegate implements SurfaceDelegate {
    private final DevSupportManager devSupportManager;
    private LogBoxDialog dialog;
    private View reactRootView;

    public LogBoxDialogSurfaceDelegate(DevSupportManager devSupportManager) {
        Intrinsics.checkNotNullParameter(devSupportManager, "devSupportManager");
        this.devSupportManager = devSupportManager;
    }

    @Override // com.facebook.react.common.SurfaceDelegate
    public void createContentView(String appKey) {
        Intrinsics.checkNotNullParameter(appKey, "appKey");
        Assertions.assertCondition(Intrinsics.areEqual(appKey, "LogBox"), "This surface manager can only create LogBox React application");
        View viewCreateRootView = this.devSupportManager.createRootView("LogBox");
        this.reactRootView = viewCreateRootView;
        if (viewCreateRootView == null) {
            RNLog.e("Unable to launch logbox because react was unable to create the root view");
        }
    }

    @Override // com.facebook.react.common.SurfaceDelegate
    public boolean isContentViewReady() {
        return this.reactRootView != null;
    }

    @Override // com.facebook.react.common.SurfaceDelegate
    public void destroyContentView() {
        View view = this.reactRootView;
        if (view != null) {
            this.devSupportManager.destroyRootView(view);
            this.reactRootView = null;
        }
    }

    @Override // com.facebook.react.common.SurfaceDelegate
    public void show() {
        if (isShowing() || !isContentViewReady()) {
            return;
        }
        Activity currentActivity = this.devSupportManager.getCurrentActivity();
        if (currentActivity == null || currentActivity.isFinishing()) {
            RNLog.e("Unable to launch logbox because react activity is not available, here is the error that logbox would've displayed: ");
            return;
        }
        LogBoxDialog logBoxDialog = new LogBoxDialog(currentActivity, this.reactRootView);
        this.dialog = logBoxDialog;
        logBoxDialog.setCancelable(false);
        logBoxDialog.show();
    }

    @Override // com.facebook.react.common.SurfaceDelegate
    public void hide() {
        LogBoxDialog logBoxDialog;
        if (isShowing() && (logBoxDialog = this.dialog) != null) {
            logBoxDialog.dismiss();
        }
        View view = this.reactRootView;
        ViewGroup viewGroup = (ViewGroup) (view != null ? view.getParent() : null);
        if (viewGroup != null) {
            viewGroup.removeView(this.reactRootView);
        }
        this.dialog = null;
    }

    @Override // com.facebook.react.common.SurfaceDelegate
    public boolean isShowing() {
        LogBoxDialog logBoxDialog = this.dialog;
        if (logBoxDialog != null) {
            return logBoxDialog.isShowing();
        }
        return false;
    }
}
