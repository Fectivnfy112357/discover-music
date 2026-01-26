package com.facebook.react.views.modal;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStructure;
import android.view.Window;
import android.view.WindowInsetsController;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.facebook.common.logging.FLog;
import com.facebook.react.R;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.uimanager.JSPointerDispatcher;
import com.facebook.react.uimanager.JSTouchDispatcher;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.common.ContextUtils;
import com.facebook.react.views.view.ReactViewGroup;
import com.facebook.react.views.view.WindowUtilKt;
import com.umeng.analytics.pro.bm;
import com.umeng.analytics.pro.f;
import java.util.ArrayList;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jaudiotagger.tag.id3.AbstractID3v1Tag;

/* compiled from: ReactModalHostView.kt */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0016\b\u0007\u0018\u0000 c2\u00020\u00012\u00020\u0002:\u0003cdeB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J \u0010=\u001a\u00020>2\u0016\u0010?\u001a\u0012\u0012\u0004\u0012\u00020\u000e0@j\b\u0012\u0004\u0012\u00020\u000e`AH\u0016J\u001a\u0010B\u001a\u00020>2\b\u0010C\u001a\u0004\u0018\u00010\u000e2\u0006\u0010D\u001a\u00020EH\u0016J\b\u0010F\u001a\u00020>H\u0002J\u0010\u0010G\u001a\u00020\u00122\u0006\u0010H\u001a\u00020IH\u0016J\u0010\u0010J\u001a\u00020>2\u0006\u0010K\u001a\u00020LH\u0016J\u0012\u0010M\u001a\u0004\u0018\u00010\u000e2\u0006\u0010D\u001a\u00020EH\u0016J\b\u0010N\u001a\u00020EH\u0016J\n\u0010O\u001a\u0004\u0018\u00010PH\u0002J\b\u0010Q\u001a\u00020>H\u0014J\u0006\u0010R\u001a\u00020>J\b\u0010S\u001a\u00020>H\u0016J\b\u0010T\u001a\u00020>H\u0016J\b\u0010U\u001a\u00020>H\u0016J0\u0010V\u001a\u00020>2\u0006\u0010W\u001a\u00020\u00122\u0006\u0010X\u001a\u00020E2\u0006\u0010Y\u001a\u00020E2\u0006\u0010Z\u001a\u00020E2\u0006\u0010[\u001a\u00020EH\u0014J\u0012\u0010\\\u001a\u00020>2\b\u0010C\u001a\u0004\u0018\u00010\u000eH\u0016J\u0010\u0010]\u001a\u00020>2\u0006\u0010D\u001a\u00020EH\u0016J\u0010\u0010^\u001a\u00020>2\u0006\u0010_\u001a\u00020EH\u0016J\u0006\u0010`\u001a\u00020>J\b\u0010a\u001a\u00020>H\u0002J\b\u0010b\u001a\u00020>H\u0002R(\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0015\u001a\u0004\u0018\u00010\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u00148G@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR$\u0010 \u001a\u00020\u00122\u0006\u0010\u0006\u001a\u00020\u0012@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001c\u0010%\u001a\u0004\u0018\u00010&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001c\u0010+\u001a\u0004\u0018\u00010,X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R(\u00101\u001a\u0004\u0018\u0001022\b\u00101\u001a\u0004\u0018\u0001028F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b3\u00104\"\u0004\b5\u00106R$\u00107\u001a\u00020\u00122\u0006\u0010\u0006\u001a\u00020\u0012@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\"\"\u0004\b9\u0010$R\u001a\u0010:\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\"\"\u0004\b<\u0010$¨\u0006f"}, d2 = {"Lcom/facebook/react/views/modal/ReactModalHostView;", "Landroid/view/ViewGroup;", "Lcom/facebook/react/bridge/LifecycleEventListener;", f.X, "Lcom/facebook/react/uimanager/ThemedReactContext;", "(Lcom/facebook/react/uimanager/ThemedReactContext;)V", "value", "", "animationType", "getAnimationType", "()Ljava/lang/String;", "setAnimationType", "(Ljava/lang/String;)V", "contentView", "Landroid/view/View;", "getContentView", "()Landroid/view/View;", "createNewDialog", "", "<set-?>", "Landroid/app/Dialog;", "dialog", "getDialog", "()Landroid/app/Dialog;", "dialogRootViewGroup", "Lcom/facebook/react/views/modal/ReactModalHostView$DialogRootViewGroup;", "eventDispatcher", "Lcom/facebook/react/uimanager/events/EventDispatcher;", "getEventDispatcher", "()Lcom/facebook/react/uimanager/events/EventDispatcher;", "setEventDispatcher", "(Lcom/facebook/react/uimanager/events/EventDispatcher;)V", "hardwareAccelerated", "getHardwareAccelerated", "()Z", "setHardwareAccelerated", "(Z)V", "onRequestCloseListener", "Lcom/facebook/react/views/modal/ReactModalHostView$OnRequestCloseListener;", "getOnRequestCloseListener", "()Lcom/facebook/react/views/modal/ReactModalHostView$OnRequestCloseListener;", "setOnRequestCloseListener", "(Lcom/facebook/react/views/modal/ReactModalHostView$OnRequestCloseListener;)V", "onShowListener", "Landroid/content/DialogInterface$OnShowListener;", "getOnShowListener", "()Landroid/content/DialogInterface$OnShowListener;", "setOnShowListener", "(Landroid/content/DialogInterface$OnShowListener;)V", "stateWrapper", "Lcom/facebook/react/uimanager/StateWrapper;", "getStateWrapper", "()Lcom/facebook/react/uimanager/StateWrapper;", "setStateWrapper", "(Lcom/facebook/react/uimanager/StateWrapper;)V", "statusBarTranslucent", "getStatusBarTranslucent", "setStatusBarTranslucent", "transparent", "getTransparent", "setTransparent", "addChildrenForAccessibility", "", "outChildren", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "addView", "child", "index", "", "dismiss", "dispatchPopulateAccessibilityEvent", "event", "Landroid/view/accessibility/AccessibilityEvent;", "dispatchProvideStructure", "structure", "Landroid/view/ViewStructure;", "getChildAt", "getChildCount", "getCurrentActivity", "Landroid/app/Activity;", "onDetachedFromWindow", "onDropInstance", "onHostDestroy", "onHostPause", "onHostResume", ViewProps.ON_LAYOUT, "changed", CmcdData.Factory.STREAM_TYPE_LIVE, bm.aM, "r", "b", "removeView", "removeViewAt", "setId", "id", "showOrUpdate", "updateProperties", "updateSystemAppearance", "Companion", "DialogRootViewGroup", "OnRequestCloseListener", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ReactModalHostView extends ViewGroup implements LifecycleEventListener {
    private static final Companion Companion = new Companion(null);
    private static final String TAG = "ReactModalHost";
    private String animationType;
    private boolean createNewDialog;
    private Dialog dialog;
    private final DialogRootViewGroup dialogRootViewGroup;
    private boolean hardwareAccelerated;
    private OnRequestCloseListener onRequestCloseListener;
    private DialogInterface.OnShowListener onShowListener;
    private boolean statusBarTranslucent;
    private boolean transparent;

    /* compiled from: ReactModalHostView.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Lcom/facebook/react/views/modal/ReactModalHostView$OnRequestCloseListener;", "", "onRequestClose", "", "dialog", "Landroid/content/DialogInterface;", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface OnRequestCloseListener {
        void onRequestClose(DialogInterface dialog);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addChildrenForAccessibility(ArrayList<View> outChildren) {
        Intrinsics.checkNotNullParameter(outChildren, "outChildren");
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        return false;
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ReactModalHostView(ThemedReactContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ThemedReactContext themedReactContext = context;
        super(themedReactContext);
        context.addLifecycleEventListener(this);
        this.dialogRootViewGroup = new DialogRootViewGroup(themedReactContext);
    }

    @VisibleForTesting
    public final Dialog getDialog() {
        return this.dialog;
    }

    public final boolean getTransparent() {
        return this.transparent;
    }

    public final void setTransparent(boolean z) {
        this.transparent = z;
    }

    public final DialogInterface.OnShowListener getOnShowListener() {
        return this.onShowListener;
    }

    public final void setOnShowListener(DialogInterface.OnShowListener onShowListener) {
        this.onShowListener = onShowListener;
    }

    public final OnRequestCloseListener getOnRequestCloseListener() {
        return this.onRequestCloseListener;
    }

    public final void setOnRequestCloseListener(OnRequestCloseListener onRequestCloseListener) {
        this.onRequestCloseListener = onRequestCloseListener;
    }

    public final boolean getStatusBarTranslucent() {
        return this.statusBarTranslucent;
    }

    public final void setStatusBarTranslucent(boolean z) {
        this.statusBarTranslucent = z;
        this.createNewDialog = true;
    }

    public final String getAnimationType() {
        return this.animationType;
    }

    public final void setAnimationType(String str) {
        this.animationType = str;
        this.createNewDialog = true;
    }

    public final boolean getHardwareAccelerated() {
        return this.hardwareAccelerated;
    }

    public final void setHardwareAccelerated(boolean z) {
        this.hardwareAccelerated = z;
        this.createNewDialog = true;
    }

    public final StateWrapper getStateWrapper() {
        return this.dialogRootViewGroup.getStateWrapper();
    }

    public final void setStateWrapper(StateWrapper stateWrapper) {
        this.dialogRootViewGroup.setStateWrapper$ReactAndroid_release(stateWrapper);
    }

    public final EventDispatcher getEventDispatcher() {
        return this.dialogRootViewGroup.getEventDispatcher();
    }

    public final void setEventDispatcher(EventDispatcher eventDispatcher) {
        this.dialogRootViewGroup.setEventDispatcher$ReactAndroid_release(eventDispatcher);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchProvideStructure(ViewStructure structure) {
        Intrinsics.checkNotNullParameter(structure, "structure");
        this.dialogRootViewGroup.dispatchProvideStructure(structure);
    }

    @Override // android.view.View
    public void setId(int id) {
        super.setId(id);
        this.dialogRootViewGroup.setId(id);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dismiss();
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int index) {
        UiThreadUtil.assertOnUiThread();
        this.dialogRootViewGroup.addView(child, index);
    }

    @Override // android.view.ViewGroup
    public int getChildCount() {
        return this.dialogRootViewGroup.getChildCount();
    }

    @Override // android.view.ViewGroup
    public View getChildAt(int index) {
        return this.dialogRootViewGroup.getChildAt(index);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View child) {
        UiThreadUtil.assertOnUiThread();
        if (child != null) {
            this.dialogRootViewGroup.removeView(child);
        }
    }

    @Override // android.view.ViewGroup
    public void removeViewAt(int index) {
        UiThreadUtil.assertOnUiThread();
        this.dialogRootViewGroup.removeView(getChildAt(index));
    }

    public final void onDropInstance() {
        Context context = getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.uimanager.ThemedReactContext");
        ((ThemedReactContext) context).removeLifecycleEventListener(this);
        dismiss();
    }

    private final void dismiss() {
        Activity activity;
        UiThreadUtil.assertOnUiThread();
        Dialog dialog = this.dialog;
        if (dialog != null) {
            if (dialog.isShowing() && ((activity = (Activity) ContextUtils.findContextOfType(dialog.getContext(), Activity.class)) == null || !activity.isFinishing())) {
                dialog.dismiss();
            }
            this.dialog = null;
            this.createNewDialog = true;
            ViewParent parent = this.dialogRootViewGroup.getParent();
            ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            if (viewGroup != null) {
                viewGroup.removeViewAt(0);
            }
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        showOrUpdate();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        onDropInstance();
    }

    private final Activity getCurrentActivity() {
        Context context = getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.uimanager.ThemedReactContext");
        return ((ThemedReactContext) context).getCurrentActivity();
    }

    public final void showOrUpdate() {
        int i;
        Window window;
        UiThreadUtil.assertOnUiThread();
        if (this.createNewDialog) {
            dismiss();
            this.createNewDialog = false;
            String str = this.animationType;
            if (Intrinsics.areEqual(str, "fade")) {
                i = R.style.Theme_FullScreenDialogAnimatedFade;
            } else {
                i = Intrinsics.areEqual(str, "slide") ? R.style.Theme_FullScreenDialogAnimatedSlide : R.style.Theme_FullScreenDialog;
            }
            Activity currentActivity = getCurrentActivity();
            Dialog dialog = new Dialog(currentActivity != null ? currentActivity : getContext(), i);
            this.dialog = dialog;
            ((Window) Objects.requireNonNull(dialog.getWindow())).setFlags(8, 8);
            dialog.setContentView(getContentView());
            updateProperties();
            dialog.setOnShowListener(this.onShowListener);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.facebook.react.views.modal.ReactModalHostView.showOrUpdate.1
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialog2, int keyCode, KeyEvent event) {
                    Intrinsics.checkNotNullParameter(dialog2, "dialog");
                    Intrinsics.checkNotNullParameter(event, "event");
                    if (event.getAction() != 1) {
                        return false;
                    }
                    if (keyCode == 4 || keyCode == 111) {
                        OnRequestCloseListener onRequestCloseListener = ReactModalHostView.this.getOnRequestCloseListener();
                        if (onRequestCloseListener == null) {
                            throw new IllegalStateException("onRequestClose callback must be set if back key is expected to close the modal".toString());
                        }
                        onRequestCloseListener.onRequestClose(dialog2);
                        return true;
                    }
                    Context context = ReactModalHostView.this.getContext();
                    Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
                    Activity currentActivity2 = ((ReactContext) context).getCurrentActivity();
                    if (currentActivity2 != null) {
                        return currentActivity2.onKeyUp(keyCode, event);
                    }
                    return false;
                }
            });
            Window window2 = dialog.getWindow();
            if (window2 != null) {
                window2.setSoftInputMode(16);
            }
            if (this.hardwareAccelerated && (window = dialog.getWindow()) != null) {
                window.addFlags(16777216);
            }
            if (currentActivity == null || currentActivity.isFinishing()) {
                return;
            }
            dialog.show();
            updateSystemAppearance();
            Window window3 = dialog.getWindow();
            if (window3 != null) {
                window3.clearFlags(8);
                return;
            }
            return;
        }
        updateProperties();
    }

    private final View getContentView() {
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.addView(this.dialogRootViewGroup);
        if (!this.statusBarTranslucent) {
            frameLayout.setFitsSystemWindows(true);
        }
        return frameLayout;
    }

    private final void updateProperties() {
        Dialog dialog = this.dialog;
        if (dialog == null) {
            throw new IllegalStateException("dialog must exist when we call updateProperties".toString());
        }
        Window window = dialog.getWindow();
        if (window == null) {
            throw new IllegalStateException("dialog must have window when we call updateProperties".toString());
        }
        Intrinsics.checkNotNullExpressionValue(window, "checkNotNull(...)");
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null || currentActivity.isFinishing() || currentActivity.isDestroyed()) {
            return;
        }
        try {
            Window window2 = currentActivity.getWindow();
            if (window2 != null) {
                if ((window2.getAttributes().flags & 1024) != 0) {
                    window.addFlags(1024);
                } else {
                    window.clearFlags(1024);
                }
            }
            WindowUtilKt.setStatusBarTranslucency(window, this.statusBarTranslucent);
            if (this.transparent) {
                window.clearFlags(2);
            } else {
                window.setDimAmount(0.5f);
                window.setFlags(2, 2);
            }
        } catch (IllegalArgumentException e) {
            FLog.e(ReactConstants.TAG, "ReactModalHostView: error while setting window flags: ", e.getMessage());
        }
    }

    private final void updateSystemAppearance() {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            return;
        }
        Dialog dialog = this.dialog;
        if (dialog == null) {
            throw new IllegalStateException("dialog must exist when we call updateProperties".toString());
        }
        Window window = dialog.getWindow();
        if (window == null) {
            throw new IllegalStateException("dialog must have window when we call updateProperties".toString());
        }
        Intrinsics.checkNotNullExpressionValue(window, "checkNotNull(...)");
        Window window2 = currentActivity.getWindow();
        if (Build.VERSION.SDK_INT > 30) {
            WindowInsetsController insetsController = window2.getInsetsController();
            if (insetsController == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            int systemBarsAppearance = insetsController.getSystemBarsAppearance() & 8;
            WindowInsetsController insetsController2 = window.getInsetsController();
            if (insetsController2 != null) {
                insetsController2.setSystemBarsAppearance(systemBarsAppearance, 8);
                return;
            }
            return;
        }
        window.getDecorView().setSystemUiVisibility(window2.getDecorView().getSystemUiVisibility());
    }

    /* compiled from: ReactModalHostView.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/facebook/react/views/modal/ReactModalHostView$Companion;", "", "()V", AbstractID3v1Tag.TAG, "", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* compiled from: ReactModalHostView.kt */
    @Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000f\u0018\u00002\u00020\u00012\u00020\u0002B\u0011\b\u0000\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0018\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0016J\u0018\u0010&\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0016J\u0010\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020%H\u0016J\u0010\u0010*\u001a\u00020(2\u0006\u0010)\u001a\u00020%H\u0016J\u0010\u0010+\u001a\u00020(2\u0006\u0010)\u001a\u00020%H\u0016J(\u0010,\u001a\u00020\u001e2\u0006\u0010-\u001a\u00020\u001b2\u0006\u0010.\u001a\u00020\u001b2\u0006\u0010/\u001a\u00020\u001b2\u0006\u00100\u001a\u00020\u001bH\u0014J\u0010\u00101\u001a\u00020(2\u0006\u0010)\u001a\u00020%H\u0017J\u0010\u00102\u001a\u00020\u001e2\u0006\u00103\u001a\u00020(H\u0016J\u0018\u00104\u001a\u00020\u001e2\u0006\u00105\u001a\u00020\u001b2\u0006\u00106\u001a\u00020\u001bH\u0007R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u00118BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"Lcom/facebook/react/views/modal/ReactModalHostView$DialogRootViewGroup;", "Lcom/facebook/react/views/view/ReactViewGroup;", "Lcom/facebook/react/uimanager/RootView;", f.X, "Landroid/content/Context;", "(Landroid/content/Context;)V", "eventDispatcher", "Lcom/facebook/react/uimanager/events/EventDispatcher;", "getEventDispatcher$ReactAndroid_release", "()Lcom/facebook/react/uimanager/events/EventDispatcher;", "setEventDispatcher$ReactAndroid_release", "(Lcom/facebook/react/uimanager/events/EventDispatcher;)V", "jSPointerDispatcher", "Lcom/facebook/react/uimanager/JSPointerDispatcher;", "jSTouchDispatcher", "Lcom/facebook/react/uimanager/JSTouchDispatcher;", "reactContext", "Lcom/facebook/react/uimanager/ThemedReactContext;", "getReactContext", "()Lcom/facebook/react/uimanager/ThemedReactContext;", "stateWrapper", "Lcom/facebook/react/uimanager/StateWrapper;", "getStateWrapper$ReactAndroid_release", "()Lcom/facebook/react/uimanager/StateWrapper;", "setStateWrapper$ReactAndroid_release", "(Lcom/facebook/react/uimanager/StateWrapper;)V", "viewHeight", "", "viewWidth", "handleException", "", bm.aM, "", "onChildEndedNativeGesture", "childView", "Landroid/view/View;", "ev", "Landroid/view/MotionEvent;", "onChildStartedNativeGesture", "onHoverEvent", "", "event", "onInterceptHoverEvent", "onInterceptTouchEvent", "onSizeChanged", "w", "h", "oldw", "oldh", "onTouchEvent", "requestDisallowInterceptTouchEvent", "disallowIntercept", "updateState", "width", "height", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class DialogRootViewGroup extends ReactViewGroup implements RootView {
        private EventDispatcher eventDispatcher;
        private JSPointerDispatcher jSPointerDispatcher;
        private final JSTouchDispatcher jSTouchDispatcher;
        private StateWrapper stateWrapper;
        private int viewHeight;
        private int viewWidth;

        @Override // android.view.ViewGroup, android.view.ViewParent
        public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }

        public DialogRootViewGroup(Context context) {
            super(context);
            DialogRootViewGroup dialogRootViewGroup = this;
            this.jSTouchDispatcher = new JSTouchDispatcher(dialogRootViewGroup);
            if (ReactFeatureFlags.dispatchPointerEvents) {
                this.jSPointerDispatcher = new JSPointerDispatcher(dialogRootViewGroup);
            }
        }

        /* renamed from: getStateWrapper$ReactAndroid_release, reason: from getter */
        public final StateWrapper getStateWrapper() {
            return this.stateWrapper;
        }

        public final void setStateWrapper$ReactAndroid_release(StateWrapper stateWrapper) {
            this.stateWrapper = stateWrapper;
        }

        /* renamed from: getEventDispatcher$ReactAndroid_release, reason: from getter */
        public final EventDispatcher getEventDispatcher() {
            return this.eventDispatcher;
        }

        public final void setEventDispatcher$ReactAndroid_release(EventDispatcher eventDispatcher) {
            this.eventDispatcher = eventDispatcher;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final ThemedReactContext getReactContext() {
            Context context = getContext();
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.uimanager.ThemedReactContext");
            return (ThemedReactContext) context;
        }

        @Override // com.facebook.react.views.view.ReactViewGroup, android.view.View
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            this.viewWidth = w;
            this.viewHeight = h;
            updateState(w, h);
        }

        public final void updateState(int width, int height) {
            Unit unit;
            float fPxToDp = PixelUtil.INSTANCE.pxToDp(width);
            float fPxToDp2 = PixelUtil.INSTANCE.pxToDp(height);
            StateWrapper stateWrapper = this.stateWrapper;
            if (stateWrapper != null) {
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                writableNativeMap.putDouble("screenWidth", fPxToDp);
                writableNativeMap.putDouble("screenHeight", fPxToDp2);
                stateWrapper.updateState(writableNativeMap);
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            if (unit == null) {
                ThemedReactContext reactContext = getReactContext();
                final ThemedReactContext reactContext2 = getReactContext();
                reactContext.runOnNativeModulesQueueThread(new GuardedRunnable(reactContext2) { // from class: com.facebook.react.views.modal.ReactModalHostView$DialogRootViewGroup$updateState$2$1
                    {
                        super(reactContext2);
                    }

                    @Override // com.facebook.react.bridge.GuardedRunnable
                    public void runGuarded() {
                        UIManagerModule uIManagerModule = (UIManagerModule) this.$this_run.getReactContext().getReactApplicationContext().getNativeModule(UIManagerModule.class);
                        if (uIManagerModule != null) {
                            uIManagerModule.updateNodeSize(this.$this_run.getId(), this.$this_run.viewWidth, this.$this_run.viewHeight);
                        }
                    }
                });
            }
        }

        @Override // com.facebook.react.uimanager.RootView
        public void handleException(Throwable t) {
            Intrinsics.checkNotNullParameter(t, "t");
            getReactContext().getReactApplicationContext().handleException(new RuntimeException(t));
        }

        @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent event) {
            Intrinsics.checkNotNullParameter(event, "event");
            EventDispatcher eventDispatcher = this.eventDispatcher;
            if (eventDispatcher != null) {
                this.jSTouchDispatcher.handleTouchEvent(event, eventDispatcher, getReactContext());
                JSPointerDispatcher jSPointerDispatcher = this.jSPointerDispatcher;
                if (jSPointerDispatcher != null) {
                    jSPointerDispatcher.handleMotionEvent(event, eventDispatcher, true);
                }
            }
            return super.onInterceptTouchEvent(event);
        }

        @Override // com.facebook.react.views.view.ReactViewGroup, android.view.View
        public boolean onTouchEvent(MotionEvent event) {
            Intrinsics.checkNotNullParameter(event, "event");
            EventDispatcher eventDispatcher = this.eventDispatcher;
            if (eventDispatcher != null) {
                this.jSTouchDispatcher.handleTouchEvent(event, eventDispatcher, getReactContext());
                JSPointerDispatcher jSPointerDispatcher = this.jSPointerDispatcher;
                if (jSPointerDispatcher != null) {
                    jSPointerDispatcher.handleMotionEvent(event, eventDispatcher, false);
                }
            }
            super.onTouchEvent(event);
            return true;
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptHoverEvent(MotionEvent event) {
            JSPointerDispatcher jSPointerDispatcher;
            Intrinsics.checkNotNullParameter(event, "event");
            EventDispatcher eventDispatcher = this.eventDispatcher;
            if (eventDispatcher != null && (jSPointerDispatcher = this.jSPointerDispatcher) != null) {
                jSPointerDispatcher.handleMotionEvent(event, eventDispatcher, true);
            }
            return super.onHoverEvent(event);
        }

        @Override // android.view.View
        public boolean onHoverEvent(MotionEvent event) {
            JSPointerDispatcher jSPointerDispatcher;
            Intrinsics.checkNotNullParameter(event, "event");
            EventDispatcher eventDispatcher = this.eventDispatcher;
            if (eventDispatcher != null && (jSPointerDispatcher = this.jSPointerDispatcher) != null) {
                jSPointerDispatcher.handleMotionEvent(event, eventDispatcher, false);
            }
            return super.onHoverEvent(event);
        }

        @Override // com.facebook.react.uimanager.RootView
        public void onChildStartedNativeGesture(View childView, MotionEvent ev) {
            Intrinsics.checkNotNullParameter(childView, "childView");
            Intrinsics.checkNotNullParameter(ev, "ev");
            EventDispatcher eventDispatcher = this.eventDispatcher;
            if (eventDispatcher != null) {
                this.jSTouchDispatcher.onChildStartedNativeGesture(ev, eventDispatcher);
                JSPointerDispatcher jSPointerDispatcher = this.jSPointerDispatcher;
                if (jSPointerDispatcher != null) {
                    jSPointerDispatcher.onChildStartedNativeGesture(childView, ev, eventDispatcher);
                }
            }
        }

        @Override // com.facebook.react.uimanager.RootView
        public void onChildEndedNativeGesture(View childView, MotionEvent ev) {
            Intrinsics.checkNotNullParameter(childView, "childView");
            Intrinsics.checkNotNullParameter(ev, "ev");
            EventDispatcher eventDispatcher = this.eventDispatcher;
            if (eventDispatcher != null) {
                this.jSTouchDispatcher.onChildEndedNativeGesture(ev, eventDispatcher);
            }
            JSPointerDispatcher jSPointerDispatcher = this.jSPointerDispatcher;
            if (jSPointerDispatcher != null) {
                jSPointerDispatcher.onChildEndedNativeGesture();
            }
        }
    }
}
