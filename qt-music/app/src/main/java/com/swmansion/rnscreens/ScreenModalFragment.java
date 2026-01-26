package com.swmansion.rnscreens;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.widget.FrameLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.swmansion.rnscreens.ScreenFragment;
import com.swmansion.rnscreens.bottomsheet.BottomSheetDialogRootView;
import com.swmansion.rnscreens.bottomsheet.BottomSheetDialogScreen;
import com.swmansion.rnscreens.bottomsheet.SheetUtils;
import com.swmansion.rnscreens.events.ScreenDismissedEvent;
import com.swmansion.rnscreens.ext.ViewExtKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ScreenModalFragment.kt */
@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 S2\u00020\u00012\u00020\u0002:\u0001SB\u0007\b\u0016¢\u0006\u0002\u0010\u0003B\u000f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010 \u001a\u00020!2\u0006\u0010\u0013\u001a\u00020\u000fH\u0016J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0016J\b\u0010&\u001a\u00020#H\u0016J\b\u0010'\u001a\u00020!H\u0002J\b\u0010(\u001a\u00020\u001fH\u0002J\b\u0010)\u001a\u00020!H\u0016J\b\u0010*\u001a\u00020!H\u0016J\u0018\u0010+\u001a\u00020!2\u0006\u0010$\u001a\u00020%2\u0006\u0010,\u001a\u00020-H\u0016J\u0010\u0010.\u001a\u00020!2\u0006\u0010$\u001a\u00020%H\u0016J\u0018\u0010/\u001a\u00020!2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u00020#H\u0016J\b\u00103\u001a\u00020#H\u0016J\b\u00104\u001a\u00020!H\u0016J\u0012\u00105\u001a\u00020!2\b\u00106\u001a\u0004\u0018\u000107H\u0016J\u0012\u00108\u001a\u0002092\b\u00106\u001a\u0004\u0018\u000107H\u0016J&\u0010:\u001a\u0004\u0018\u00010;2\u0006\u0010<\u001a\u00020=2\b\u0010\u0013\u001a\u0004\u0018\u00010>2\b\u00106\u001a\u0004\u0018\u000107H\u0016J\b\u0010?\u001a\u00020!H\u0016J\b\u0010@\u001a\u00020!H\u0016J\b\u0010A\u001a\u00020!H\u0016J\u0010\u0010B\u001a\u00020!2\u0006\u0010\u0013\u001a\u00020\u000fH\u0016J\b\u0010C\u001a\u00020!H\u0016J\u0010\u0010D\u001a\u00020!2\u0006\u0010E\u001a\u00020FH\u0016J\u0010\u0010G\u001a\u00020!2\u0006\u0010H\u001a\u00020#H\u0016J\u0010\u0010I\u001a\u00020!2\u0006\u0010J\u001a\u00020#H\u0016J\n\u0010K\u001a\u0004\u0018\u00010LH\u0016J\n\u0010M\u001a\u0004\u0018\u00010NH\u0016J\u000f\u0010O\u001a\u0004\u0018\u00010PH\u0002¢\u0006\u0002\u0010QJ\u0010\u0010R\u001a\u00020!2\u0006\u0010$\u001a\u00020%H\u0016R\"\u0010\u0007\u001a\u0010\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t0\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR$\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0013\u001a\u0004\u0018\u00010\u00148BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u001a\u0010\u0004\u001a\u00020\u0005X\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u0006R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082.¢\u0006\u0002\n\u0000¨\u0006T"}, d2 = {"Lcom/swmansion/rnscreens/ScreenModalFragment;", "Lcom/google/android/material/bottomsheet/BottomSheetDialogFragment;", "Lcom/swmansion/rnscreens/ScreenStackFragmentWrapper;", "()V", "screen", "Lcom/swmansion/rnscreens/Screen;", "(Lcom/swmansion/rnscreens/Screen;)V", "behavior", "Lcom/google/android/material/bottomsheet/BottomSheetBehavior;", "Landroid/widget/FrameLayout;", "kotlin.jvm.PlatformType", "getBehavior", "()Lcom/google/android/material/bottomsheet/BottomSheetBehavior;", "childScreenContainers", "Ljava/util/ArrayList;", "Lcom/swmansion/rnscreens/ScreenContainer;", "Lkotlin/collections/ArrayList;", "getChildScreenContainers", "()Ljava/util/ArrayList;", "container", "Lcom/swmansion/rnscreens/ScreenStack;", "getContainer", "()Lcom/swmansion/rnscreens/ScreenStack;", "fragment", "Landroidx/fragment/app/Fragment;", "getFragment", "()Landroidx/fragment/app/Fragment;", "getScreen", "()Lcom/swmansion/rnscreens/Screen;", "setScreen", "sheetDialog", "Lcom/google/android/material/bottomsheet/BottomSheetDialog;", "addChildScreenContainer", "", "canDispatchLifecycleEvent", "", "event", "Lcom/swmansion/rnscreens/ScreenFragment$ScreenLifecycleEvent;", "canNavigateBack", "configureBehaviour", "configureDialogAndBehaviour", "dismissFromContainer", "dispatchHeaderBackButtonClickedEvent", "dispatchLifecycleEvent", "fragmentWrapper", "Lcom/swmansion/rnscreens/ScreenFragmentWrapper;", "dispatchLifecycleEventInChildContainers", "dispatchTransitionProgressEvent", "alpha", "", "closing", "isTranslucent", "onContainerUpdate", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateDialog", "Landroid/app/Dialog;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "Landroid/view/ViewGroup;", "onDestroy", "onViewAnimationEnd", "onViewAnimationStart", "removeChildScreenContainer", "removeToolbar", "setToolbar", "toolbar", "Landroidx/appcompat/widget/Toolbar;", "setToolbarShadowHidden", ViewProps.HIDDEN, "setToolbarTranslucent", "translucent", "tryGetActivity", "Landroid/app/Activity;", "tryGetContext", "Lcom/facebook/react/bridge/ReactContext;", "tryResolveContainerHeight", "", "()Ljava/lang/Integer;", "updateLastEventDispatched", "Companion", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ScreenModalFragment extends BottomSheetDialogFragment implements ScreenStackFragmentWrapper {
    public static final String TAG = "ScreenModalFragment";
    private final ArrayList<ScreenContainer> childScreenContainers;
    public Screen screen;
    private BottomSheetDialog sheetDialog;

    @Override // com.swmansion.rnscreens.ScreenStackFragmentWrapper
    public boolean canNavigateBack() {
        return true;
    }

    @Override // com.swmansion.rnscreens.ScreenFragmentWrapper
    public boolean isTranslucent() {
        return true;
    }

    @Override // com.swmansion.rnscreens.ScreenFragmentWrapper
    public void onContainerUpdate() {
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return null;
    }

    @Override // com.swmansion.rnscreens.ScreenFragmentWrapper
    public void onViewAnimationEnd() {
    }

    @Override // com.swmansion.rnscreens.ScreenFragmentWrapper
    public void onViewAnimationStart() {
    }

    @Override // com.swmansion.rnscreens.ScreenFragmentWrapper
    public Screen getScreen() {
        Screen screen = this.screen;
        if (screen != null) {
            return screen;
        }
        Intrinsics.throwUninitializedPropertyAccessException("screen");
        return null;
    }

    @Override // com.swmansion.rnscreens.ScreenFragmentWrapper
    public void setScreen(Screen screen) {
        Intrinsics.checkNotNullParameter(screen, "<set-?>");
        this.screen = screen;
    }

    @Override // com.swmansion.rnscreens.ScreenFragmentWrapper
    public ArrayList<ScreenContainer> getChildScreenContainers() {
        return this.childScreenContainers;
    }

    private final ScreenStack getContainer() {
        ScreenContainer container = getScreen().getContainer();
        if (container instanceof ScreenStack) {
            return (ScreenStack) container;
        }
        return null;
    }

    private final BottomSheetBehavior<FrameLayout> getBehavior() {
        BottomSheetDialog bottomSheetDialog = this.sheetDialog;
        if (bottomSheetDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sheetDialog");
            bottomSheetDialog = null;
        }
        BottomSheetBehavior<FrameLayout> behavior = bottomSheetDialog.getBehavior();
        Intrinsics.checkNotNullExpressionValue(behavior, "getBehavior(...)");
        return behavior;
    }

    @Override // com.swmansion.rnscreens.FragmentHolder
    public Fragment getFragment() {
        return this;
    }

    public ScreenModalFragment() {
        this.childScreenContainers = new ArrayList<>();
        throw new IllegalStateException("Screen fragments should never be restored. Follow instructions from https://github.com/software-mansion/react-native-screens/issues/17#issuecomment-424704067 to properly configure your main activity.");
    }

    public ScreenModalFragment(Screen screen) {
        Intrinsics.checkNotNullParameter(screen, "screen");
        this.childScreenContainers = new ArrayList<>();
        setScreen(screen);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setShowsDialog(true);
    }

    @Override // com.google.android.material.bottomsheet.BottomSheetDialogFragment, androidx.appcompat.app.AppCompatDialogFragment, androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        configureDialogAndBehaviour();
        EventDispatcher reactEventDispatcher = getScreen().getReactEventDispatcher();
        if (reactEventDispatcher == null) {
            throw new IllegalStateException("[RNScreens] No ReactEventDispatcher attached to screen while creating modal fragment".toString());
        }
        BottomSheetDialogRootView bottomSheetDialogRootView = new BottomSheetDialogRootView(getScreen().getReactContext(), reactEventDispatcher);
        bottomSheetDialogRootView.addView(ViewExtKt.recycle(getScreen()));
        BottomSheetDialog bottomSheetDialog = this.sheetDialog;
        BottomSheetDialog bottomSheetDialog2 = null;
        if (bottomSheetDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sheetDialog");
            bottomSheetDialog = null;
        }
        BottomSheetDialogRootView bottomSheetDialogRootView2 = bottomSheetDialogRootView;
        bottomSheetDialog.setContentView(bottomSheetDialogRootView2);
        View viewParentAsView = ViewExtKt.parentAsView(bottomSheetDialogRootView2);
        if (viewParentAsView != null) {
            viewParentAsView.setClipToOutline(true);
        }
        BottomSheetDialog bottomSheetDialog3 = this.sheetDialog;
        if (bottomSheetDialog3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sheetDialog");
        } else {
            bottomSheetDialog2 = bottomSheetDialog3;
        }
        return bottomSheetDialog2;
    }

    @Override // com.swmansion.rnscreens.ScreenStackFragmentWrapper
    public void dismissFromContainer() {
        if (getContainer() == null) {
            throw new IllegalStateException("Check failed.".toString());
        }
        ScreenStack container = getContainer();
        Intrinsics.checkNotNull(container, "null cannot be cast to non-null type com.swmansion.rnscreens.ScreenStack");
        container.dismiss(this);
    }

    @Override // com.swmansion.rnscreens.ScreenFragmentWrapper
    public void addChildScreenContainer(ScreenContainer container) {
        Intrinsics.checkNotNullParameter(container, "container");
        getChildScreenContainers().add(container);
    }

    @Override // com.swmansion.rnscreens.ScreenFragmentWrapper
    public void removeChildScreenContainer(ScreenContainer container) {
        Intrinsics.checkNotNullParameter(container, "container");
        getChildScreenContainers().remove(container);
    }

    @Override // com.swmansion.rnscreens.ScreenFragmentWrapper
    public Activity tryGetActivity() {
        return requireActivity();
    }

    @Override // com.swmansion.rnscreens.ScreenFragmentWrapper
    public ReactContext tryGetContext() {
        if (getContext() instanceof ReactContext) {
            Context context = getContext();
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
            return (ReactContext) context;
        }
        if (getScreen().getContext() instanceof ReactContext) {
            Context context2 = getScreen().getContext();
            Intrinsics.checkNotNull(context2, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
            return (ReactContext) context2;
        }
        for (ScreenContainer container = getScreen().getContainer(); container != null; container = container.getParent()) {
            if (container instanceof Screen) {
                Screen screen = (Screen) container;
                if (screen.getContext() instanceof ReactContext) {
                    Context context3 = screen.getContext();
                    Intrinsics.checkNotNull(context3, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
                    return (ReactContext) context3;
                }
            }
        }
        return null;
    }

    @Override // com.swmansion.rnscreens.ScreenEventDispatcher
    public boolean canDispatchLifecycleEvent(ScreenFragment.ScreenLifecycleEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.swmansion.rnscreens.ScreenEventDispatcher
    public void updateLastEventDispatched(ScreenFragment.ScreenLifecycleEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.swmansion.rnscreens.ScreenEventDispatcher
    public void dispatchLifecycleEvent(ScreenFragment.ScreenLifecycleEvent event, ScreenFragmentWrapper fragmentWrapper) {
        Intrinsics.checkNotNullParameter(event, "event");
        Intrinsics.checkNotNullParameter(fragmentWrapper, "fragmentWrapper");
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.swmansion.rnscreens.ScreenEventDispatcher
    public void dispatchLifecycleEventInChildContainers(ScreenFragment.ScreenLifecycleEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.swmansion.rnscreens.ScreenEventDispatcher
    public void dispatchHeaderBackButtonClickedEvent() {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.swmansion.rnscreens.ScreenEventDispatcher
    public void dispatchTransitionProgressEvent(float alpha, boolean closing) {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        ScreenStack container = getContainer();
        if (container == null || !container.hasScreen(this)) {
            Context context = getScreen().getContext();
            if (context instanceof ReactContext) {
                int surfaceId = UIManagerHelper.getSurfaceId(context);
                EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag((ReactContext) context, getScreen().getId());
                if (eventDispatcherForReactTag != null) {
                    eventDispatcherForReactTag.dispatchEvent(new ScreenDismissedEvent(surfaceId, getScreen().getId()));
                }
            }
        }
        getChildScreenContainers().clear();
    }

    @Override // com.swmansion.rnscreens.ScreenStackFragmentWrapper
    public void removeToolbar() {
        throw new IllegalStateException("[RNScreens] Modal screens on Android do not support header right now");
    }

    @Override // com.swmansion.rnscreens.ScreenStackFragmentWrapper
    public void setToolbar(Toolbar toolbar) {
        Intrinsics.checkNotNullParameter(toolbar, "toolbar");
        throw new IllegalStateException("[RNScreens] Modal screens on Android do not support header right now");
    }

    @Override // com.swmansion.rnscreens.ScreenStackFragmentWrapper
    public void setToolbarShadowHidden(boolean hidden) {
        throw new IllegalStateException("[RNScreens] Modal screens on Android do not support header right now");
    }

    @Override // com.swmansion.rnscreens.ScreenStackFragmentWrapper
    public void setToolbarTranslucent(boolean translucent) {
        throw new IllegalStateException("[RNScreens] Modal screens on Android do not support header right now");
    }

    private final BottomSheetDialog configureDialogAndBehaviour() {
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        BottomSheetDialogScreen bottomSheetDialogScreen = new BottomSheetDialogScreen(contextRequireContext, this);
        this.sheetDialog = bottomSheetDialogScreen;
        bottomSheetDialogScreen.setDismissWithAnimation(true);
        BottomSheetDialog bottomSheetDialog = this.sheetDialog;
        if (bottomSheetDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sheetDialog");
            bottomSheetDialog = null;
        }
        bottomSheetDialog.setCanceledOnTouchOutside(getScreen().getSheetClosesOnTouchOutside());
        configureBehaviour();
        BottomSheetDialog bottomSheetDialog2 = this.sheetDialog;
        if (bottomSheetDialog2 != null) {
            return bottomSheetDialog2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("sheetDialog");
        return null;
    }

    private final Integer tryResolveContainerHeight() {
        WindowMetrics currentWindowMetrics;
        Rect bounds;
        Resources resources;
        DisplayMetrics displayMetrics;
        ScreenContainer container = getScreen().getContainer();
        if (container != null) {
            return Integer.valueOf(container.getHeight());
        }
        Context context = getContext();
        if (context != null && (resources = context.getResources()) != null && (displayMetrics = resources.getDisplayMetrics()) != null) {
            return Integer.valueOf(displayMetrics.heightPixels);
        }
        if (Build.VERSION.SDK_INT >= 30) {
            Context context2 = getContext();
            Object systemService = context2 != null ? context2.getSystemService("window") : null;
            WindowManager windowManager = systemService instanceof WindowManager ? (WindowManager) systemService : null;
            if (windowManager != null && (currentWindowMetrics = windowManager.getCurrentWindowMetrics()) != null && (bounds = currentWindowMetrics.getBounds()) != null) {
                return Integer.valueOf(bounds.height());
            }
        }
        return null;
    }

    private final void configureBehaviour() {
        if (tryResolveContainerHeight() == null) {
            throw new IllegalStateException("[RNScreens] Failed to find window height during bottom sheet behaviour configuration".toString());
        }
        BottomSheetBehavior<FrameLayout> behavior = getBehavior();
        behavior.setHideable(true);
        behavior.setDraggable(true);
        int size = getScreen().getSheetDetents().size();
        if (size == 1) {
            BottomSheetBehavior<FrameLayout> behavior2 = getBehavior();
            behavior2.setState(3);
            behavior2.setSkipCollapsed(true);
            behavior2.setFitToContents(true);
            behavior2.setMaxHeight((int) (((Number) CollectionsKt.first((List) getScreen().getSheetDetents())).doubleValue() * r0.intValue()));
            return;
        }
        if (size == 2) {
            BottomSheetBehavior<FrameLayout> behavior3 = getBehavior();
            behavior3.setState(SheetUtils.INSTANCE.sheetStateFromDetentIndex(getScreen().getSheetInitialDetentIndex(), getScreen().getSheetDetents().size()));
            behavior3.setSkipCollapsed(false);
            behavior3.setFitToContents(true);
            behavior3.setPeekHeight((int) (getScreen().getSheetDetents().get(0).doubleValue() * r0.intValue()));
            behavior3.setMaxHeight((int) (getScreen().getSheetDetents().get(1).doubleValue() * r0.intValue()));
            return;
        }
        if (size == 3) {
            BottomSheetBehavior<FrameLayout> behavior4 = getBehavior();
            behavior4.setState(SheetUtils.INSTANCE.sheetStateFromDetentIndex(getScreen().getSheetInitialDetentIndex(), getScreen().getSheetDetents().size()));
            behavior4.setSkipCollapsed(false);
            behavior4.setFitToContents(false);
            behavior4.setPeekHeight((int) (getScreen().getSheetDetents().get(0).doubleValue() * r0.intValue()));
            behavior4.setExpandedOffset((int) ((1 - getScreen().getSheetDetents().get(2).doubleValue()) * r0.intValue()));
            behavior4.setHalfExpandedRatio((float) (getScreen().getSheetDetents().get(1).doubleValue() / getScreen().getSheetDetents().get(2).doubleValue()));
            return;
        }
        throw new IllegalStateException("[RNScreens] Invalid detent count " + getScreen().getSheetDetents().size() + ". Expected at most 3.");
    }
}
