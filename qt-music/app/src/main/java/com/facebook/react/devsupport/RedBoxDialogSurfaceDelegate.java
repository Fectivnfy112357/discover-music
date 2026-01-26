package com.facebook.react.devsupport;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.facebook.common.logging.FLog;
import com.facebook.fbreact.specs.NativeRedBoxSpec;
import com.facebook.react.R;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.SurfaceDelegate;
import com.facebook.react.devsupport.RedBoxDialogSurfaceDelegate;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.devsupport.interfaces.RedBoxHandler;
import java.util.Objects;

/* loaded from: classes2.dex */
class RedBoxDialogSurfaceDelegate implements SurfaceDelegate {
    private final DevSupportManager mDevSupportManager;
    private Dialog mDialog;
    private final DoubleTapReloadRecognizer mDoubleTapReloadRecognizer = new DoubleTapReloadRecognizer();
    private RedBoxContentView mRedBoxContentView;

    public RedBoxDialogSurfaceDelegate(DevSupportManager devSupportManager) {
        this.mDevSupportManager = devSupportManager;
    }

    @Override // com.facebook.react.common.SurfaceDelegate
    public void createContentView(String str) {
        RedBoxHandler redBoxHandler = this.mDevSupportManager.getRedBoxHandler();
        Activity currentActivity = this.mDevSupportManager.getCurrentActivity();
        if (currentActivity == null || currentActivity.isFinishing()) {
            String lastErrorTitle = this.mDevSupportManager.getLastErrorTitle();
            StringBuilder sb = new StringBuilder("Unable to launch redbox because react activity is not available, here is the error that redbox would've displayed: ");
            if (lastErrorTitle == null) {
                lastErrorTitle = "N/A";
            }
            FLog.e(ReactConstants.TAG, sb.append(lastErrorTitle).toString());
            return;
        }
        RedBoxContentView redBoxContentView = new RedBoxContentView(currentActivity);
        this.mRedBoxContentView = redBoxContentView;
        redBoxContentView.setDevSupportManager(this.mDevSupportManager).setRedBoxHandler(redBoxHandler).init();
    }

    @Override // com.facebook.react.common.SurfaceDelegate
    public boolean isContentViewReady() {
        return this.mRedBoxContentView != null;
    }

    @Override // com.facebook.react.common.SurfaceDelegate
    public void destroyContentView() {
        this.mRedBoxContentView = null;
    }

    @Override // com.facebook.react.common.SurfaceDelegate
    public void show() {
        String lastErrorTitle = this.mDevSupportManager.getLastErrorTitle();
        Activity currentActivity = this.mDevSupportManager.getCurrentActivity();
        if (currentActivity == null || currentActivity.isFinishing()) {
            StringBuilder sb = new StringBuilder("Unable to launch redbox because react activity is not available, here is the error that redbox would've displayed: ");
            if (lastErrorTitle == null) {
                lastErrorTitle = "N/A";
            }
            FLog.e(ReactConstants.TAG, sb.append(lastErrorTitle).toString());
            return;
        }
        RedBoxContentView redBoxContentView = this.mRedBoxContentView;
        if (redBoxContentView == null || redBoxContentView.getContext() != currentActivity) {
            createContentView(NativeRedBoxSpec.NAME);
        }
        this.mRedBoxContentView.refreshContentView();
        if (this.mDialog == null) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(currentActivity, R.style.Theme_Catalyst_RedBox);
            this.mDialog = anonymousClass1;
            anonymousClass1.requestWindowFeature(1);
            this.mDialog.setContentView(this.mRedBoxContentView);
        }
        this.mDialog.show();
    }

    /* renamed from: com.facebook.react.devsupport.RedBoxDialogSurfaceDelegate$1, reason: invalid class name */
    class AnonymousClass1 extends Dialog {
        AnonymousClass1(Context context, int i) {
            super(context, i);
        }

        @Override // android.app.Dialog, android.view.KeyEvent.Callback
        public boolean onKeyUp(int i, KeyEvent keyEvent) {
            if (i == 82) {
                RedBoxDialogSurfaceDelegate.this.mDevSupportManager.showDevOptionsDialog();
                return true;
            }
            if (RedBoxDialogSurfaceDelegate.this.mDoubleTapReloadRecognizer.didDoubleTapR(i, getCurrentFocus())) {
                RedBoxDialogSurfaceDelegate.this.mDevSupportManager.handleReloadJS();
            }
            return super.onKeyUp(i, keyEvent);
        }

        @Override // android.app.Dialog
        protected void onCreate(Bundle bundle) {
            Objects.requireNonNull(getWindow());
            getWindow().setBackgroundDrawable(new ColorDrawable(ViewCompat.MEASURED_STATE_MASK));
            final int iSystemBars = WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout();
            ViewCompat.setOnApplyWindowInsetsListener(RedBoxDialogSurfaceDelegate.this.mRedBoxContentView, new OnApplyWindowInsetsListener() { // from class: com.facebook.react.devsupport.RedBoxDialogSurfaceDelegate$1$$ExternalSyntheticLambda0
                @Override // androidx.core.view.OnApplyWindowInsetsListener
                public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                    return RedBoxDialogSurfaceDelegate.AnonymousClass1.lambda$onCreate$0(iSystemBars, view, windowInsetsCompat);
                }
            });
        }

        static /* synthetic */ WindowInsetsCompat lambda$onCreate$0(int i, View view, WindowInsetsCompat windowInsetsCompat) {
            Insets insets = windowInsetsCompat.getInsets(i);
            ((FrameLayout.LayoutParams) view.getLayoutParams()).setMargins(insets.left, insets.top, insets.right, insets.bottom);
            return WindowInsetsCompat.CONSUMED;
        }
    }

    @Override // com.facebook.react.common.SurfaceDelegate
    public void hide() {
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            dialog.dismiss();
            destroyContentView();
            this.mDialog = null;
        }
    }

    @Override // com.facebook.react.common.SurfaceDelegate
    public boolean isShowing() {
        Dialog dialog = this.mDialog;
        return dialog != null && dialog.isShowing();
    }
}
