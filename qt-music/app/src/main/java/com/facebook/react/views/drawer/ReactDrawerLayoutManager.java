package com.facebook.react.views.drawer;

import android.view.View;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.viewmanagers.AndroidDrawerLayoutManagerDelegate;
import com.facebook.react.viewmanagers.AndroidDrawerLayoutManagerInterface;
import com.facebook.react.views.drawer.events.DrawerClosedEvent;
import com.facebook.react.views.drawer.events.DrawerOpenedEvent;
import com.facebook.react.views.drawer.events.DrawerSlideEvent;
import com.facebook.react.views.drawer.events.DrawerStateChangedEvent;
import com.umeng.analytics.pro.f;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReactDrawerLayoutManager.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\b\b\u0007\u0018\u0000 62\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0003:\u000267B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0014J \u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016J\u0010\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\nH\u0014J\u0014\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00110\u0016H\u0016J\u000e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006H\u0016J\u0014\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u001a0\u0016H\u0016J\u0014\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u001a0\u0016H\u0016J\b\u0010\u001c\u001a\u00020\u0017H\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016J\"\u0010 \u001a\u00020\b2\u0006\u0010!\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020\u00112\b\u0010#\u001a\u0004\u0018\u00010$H\u0017J\"\u0010 \u001a\u00020\b2\u0006\u0010!\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020\u00172\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\u001f\u0010%\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010&\u001a\u0004\u0018\u00010\u0011H\u0017¢\u0006\u0002\u0010'J\u001a\u0010(\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010)\u001a\u0004\u0018\u00010\u0017H\u0017J\u0018\u0010*\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010+\u001a\u00020,H\u0007J\u001a\u0010*\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010&\u001a\u0004\u0018\u00010\u0017H\u0016J\u0018\u0010-\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010+\u001a\u00020\u0017H\u0002J\u0018\u0010.\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010/\u001a\u000200H\u0007J\u001f\u0010.\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010/\u001a\u0004\u0018\u000100H\u0016¢\u0006\u0002\u00101J\u0018\u00102\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u00103\u001a\u000200H\u0016J\u001a\u00104\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010&\u001a\u0004\u0018\u00010\u0017H\u0017J\u001f\u00105\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010&\u001a\u0004\u0018\u00010\u0011H\u0017¢\u0006\u0002\u0010'R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Lcom/facebook/react/views/drawer/ReactDrawerLayoutManager;", "Lcom/facebook/react/uimanager/ViewGroupManager;", "Lcom/facebook/react/views/drawer/ReactDrawerLayout;", "Lcom/facebook/react/viewmanagers/AndroidDrawerLayoutManagerInterface;", "()V", "delegate", "Lcom/facebook/react/uimanager/ViewManagerDelegate;", "addEventEmitters", "", "reactContext", "Lcom/facebook/react/uimanager/ThemedReactContext;", "view", "addView", "parent", "child", "Landroid/view/View;", "index", "", ReactDrawerLayoutManager.COMMAND_CLOSE_DRAWER, "createViewInstance", f.X, "getCommandsMap", "", "", "getDelegate", "getExportedCustomDirectEventTypeConstants", "", "getExportedViewConstants", "getName", "needsCustomLayoutForChildren", "", ReactDrawerLayoutManager.COMMAND_OPEN_DRAWER, "receiveCommand", "root", "commandId", "args", "Lcom/facebook/react/bridge/ReadableArray;", "setDrawerBackgroundColor", "value", "(Lcom/facebook/react/views/drawer/ReactDrawerLayout;Ljava/lang/Integer;)V", "setDrawerLockMode", "drawerLockMode", "setDrawerPosition", "drawerPosition", "Lcom/facebook/react/bridge/Dynamic;", "setDrawerPositionInternal", "setDrawerWidth", "width", "", "(Lcom/facebook/react/views/drawer/ReactDrawerLayout;Ljava/lang/Float;)V", "setElevation", ViewProps.ELEVATION, "setKeyboardDismissMode", "setStatusBarBackgroundColor", "Companion", "DrawerEventEmitter", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@ReactModule(name = ReactDrawerLayoutManager.REACT_CLASS)
/* loaded from: classes2.dex */
public final class ReactDrawerLayoutManager extends ViewGroupManager<ReactDrawerLayout> implements AndroidDrawerLayoutManagerInterface<ReactDrawerLayout> {
    public static final int CLOSE_DRAWER = 2;
    public static final String COMMAND_CLOSE_DRAWER = "closeDrawer";
    public static final String COMMAND_OPEN_DRAWER = "openDrawer";
    private static final String DRAWER_POSITION = "DrawerPosition";
    private static final String DRAWER_POSITION_LEFT = "Left";
    private static final String DRAWER_POSITION_RIGHT = "Right";
    public static final int OPEN_DRAWER = 1;
    public static final String REACT_CLASS = "AndroidDrawerLayout";
    private final ViewManagerDelegate<ReactDrawerLayout> delegate = new AndroidDrawerLayoutManagerDelegate(this);

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.IViewManagerWithChildren
    public boolean needsCustomLayoutForChildren() {
        return true;
    }

    @Override // com.facebook.react.viewmanagers.AndroidDrawerLayoutManagerInterface
    @ReactProp(customType = "Color", name = "drawerBackgroundColor")
    public void setDrawerBackgroundColor(ReactDrawerLayout view, Integer value) {
        Intrinsics.checkNotNullParameter(view, "view");
    }

    @Override // com.facebook.react.viewmanagers.AndroidDrawerLayoutManagerInterface
    @ReactProp(name = "keyboardDismissMode")
    public void setKeyboardDismissMode(ReactDrawerLayout view, String value) {
        Intrinsics.checkNotNullParameter(view, "view");
    }

    @Override // com.facebook.react.viewmanagers.AndroidDrawerLayoutManagerInterface
    @ReactProp(customType = "Color", name = "statusBarBackgroundColor")
    public void setStatusBarBackgroundColor(ReactDrawerLayout view, Integer value) {
        Intrinsics.checkNotNullParameter(view, "view");
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public void addEventEmitters(ThemedReactContext reactContext, ReactDrawerLayout view) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(view, "view");
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(reactContext, view.getId());
        if (eventDispatcherForReactTag == null) {
            return;
        }
        view.addDrawerListener(new DrawerEventEmitter(view, eventDispatcherForReactTag));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public ReactDrawerLayout createViewInstance(ThemedReactContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ReactDrawerLayout(context);
    }

    @Override // com.facebook.react.viewmanagers.AndroidDrawerLayoutManagerInterface
    public void setDrawerPosition(ReactDrawerLayout view, String value) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (value == null) {
            view.setDrawerPosition$ReactAndroid_release(GravityCompat.START);
        } else {
            setDrawerPositionInternal(view, value);
        }
    }

    @ReactProp(name = "drawerPosition")
    public final void setDrawerPosition(ReactDrawerLayout view, Dynamic drawerPosition) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(drawerPosition, "drawerPosition");
        if (drawerPosition.isNull()) {
            view.setDrawerPosition$ReactAndroid_release(GravityCompat.START);
            return;
        }
        if (drawerPosition.getType() == ReadableType.Number) {
            int iAsInt = drawerPosition.asInt();
            if (8388611 == iAsInt || 8388613 == iAsInt) {
                view.setDrawerPosition$ReactAndroid_release(iAsInt);
                return;
            } else {
                FLog.w(ReactConstants.TAG, "Unknown drawerPosition " + iAsInt);
                view.setDrawerPosition$ReactAndroid_release(GravityCompat.START);
                return;
            }
        }
        if (drawerPosition.getType() == ReadableType.String) {
            setDrawerPositionInternal(view, drawerPosition.asString());
        } else {
            FLog.w(ReactConstants.TAG, "drawerPosition must be a string or int");
            view.setDrawerPosition$ReactAndroid_release(GravityCompat.START);
        }
    }

    private final void setDrawerPositionInternal(ReactDrawerLayout view, String drawerPosition) {
        if (Intrinsics.areEqual(drawerPosition, "left")) {
            view.setDrawerPosition$ReactAndroid_release(GravityCompat.START);
        } else if (Intrinsics.areEqual(drawerPosition, "right")) {
            view.setDrawerPosition$ReactAndroid_release(GravityCompat.END);
        } else {
            FLog.w(ReactConstants.TAG, "drawerPosition must be 'left' or 'right', received" + drawerPosition);
            view.setDrawerPosition$ReactAndroid_release(GravityCompat.START);
        }
    }

    @ReactProp(defaultFloat = Float.NaN, name = "drawerWidth")
    public final void setDrawerWidth(ReactDrawerLayout view, float width) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setDrawerWidth$ReactAndroid_release(Float.isNaN(width) ? -1 : Math.round(PixelUtil.INSTANCE.dpToPx(width)));
    }

    @Override // com.facebook.react.viewmanagers.AndroidDrawerLayoutManagerInterface
    public void setDrawerWidth(ReactDrawerLayout view, Float width) {
        int iRound;
        Intrinsics.checkNotNullParameter(view, "view");
        if (width != null) {
            iRound = Math.round(PixelUtil.INSTANCE.dpToPx(width.floatValue()));
        } else {
            iRound = -1;
        }
        view.setDrawerWidth$ReactAndroid_release(iRound);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0032, code lost:
    
        if (r5.equals("unlocked") != false) goto L22;
     */
    @Override // com.facebook.react.viewmanagers.AndroidDrawerLayoutManagerInterface
    @com.facebook.react.uimanager.annotations.ReactProp(name = "drawerLockMode")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setDrawerLockMode(com.facebook.react.views.drawer.ReactDrawerLayout r4, java.lang.String r5) {
        /*
            r3 = this;
            java.lang.String r0 = "view"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            r0 = 0
            if (r5 == 0) goto L5a
            int r1 = r5.hashCode()
            r2 = -1292600945(0xffffffffb2f4798f, float:-2.8460617E-8)
            if (r1 == r2) goto L35
            r2 = -210949405(0xfffffffff36d2ae3, float:-1.8790347E31)
            if (r1 == r2) goto L2b
            r2 = 168848173(0xa106b2d, float:6.953505E-33)
            if (r1 == r2) goto L1d
            goto L3d
        L1d:
            java.lang.String r1 = "locked-open"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L26
            goto L3d
        L26:
            r5 = 2
            r4.setDrawerLockMode(r5)
            goto L5d
        L2b:
            java.lang.String r1 = "unlocked"
            boolean r1 = r5.equals(r1)
            if (r1 == 0) goto L3d
            goto L5a
        L35:
            java.lang.String r1 = "locked-closed"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L55
        L3d:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Unknown drawerLockMode "
            r1.<init>(r2)
            java.lang.StringBuilder r5 = r1.append(r5)
            java.lang.String r5 = r5.toString()
            java.lang.String r1 = "ReactNative"
            com.facebook.common.logging.FLog.w(r1, r5)
            r4.setDrawerLockMode(r0)
            goto L5d
        L55:
            r5 = 1
            r4.setDrawerLockMode(r5)
            goto L5d
        L5a:
            r4.setDrawerLockMode(r0)
        L5d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.drawer.ReactDrawerLayoutManager.setDrawerLockMode(com.facebook.react.views.drawer.ReactDrawerLayout, java.lang.String):void");
    }

    @Override // com.facebook.react.viewmanagers.AndroidDrawerLayoutManagerInterface
    public void openDrawer(ReactDrawerLayout view) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.openDrawer$ReactAndroid_release();
    }

    @Override // com.facebook.react.viewmanagers.AndroidDrawerLayoutManagerInterface
    public void closeDrawer(ReactDrawerLayout view) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.closeDrawer$ReactAndroid_release();
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
    public void setElevation(ReactDrawerLayout view, float elevation) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setDrawerElevation(PixelUtil.INSTANCE.dpToPx(elevation));
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Integer> getCommandsMap() {
        return MapsKt.mapOf(TuplesKt.to(COMMAND_OPEN_DRAWER, 1), TuplesKt.to(COMMAND_CLOSE_DRAWER, 2));
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Deprecated(message = "This method is deprecated. Use receiveCommand(ReactDrawerLayout, String, ReadableArray) instead", replaceWith = @ReplaceWith(expression = "receiveCommand(ReactDrawerLayout, String, ReadableArray)", imports = {}))
    public void receiveCommand(ReactDrawerLayout root, int commandId, ReadableArray args) {
        Intrinsics.checkNotNullParameter(root, "root");
        if (commandId == 1) {
            root.openDrawer$ReactAndroid_release();
        } else {
            if (commandId != 2) {
                return;
            }
            root.closeDrawer$ReactAndroid_release();
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(ReactDrawerLayout root, String commandId, ReadableArray args) {
        Intrinsics.checkNotNullParameter(root, "root");
        Intrinsics.checkNotNullParameter(commandId, "commandId");
        if (Intrinsics.areEqual(commandId, COMMAND_OPEN_DRAWER)) {
            root.openDrawer$ReactAndroid_release();
        } else if (Intrinsics.areEqual(commandId, COMMAND_CLOSE_DRAWER)) {
            root.closeDrawer$ReactAndroid_release();
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedViewConstants() {
        return MapsKt.mapOf(TuplesKt.to(DRAWER_POSITION, MapsKt.mapOf(TuplesKt.to(DRAWER_POSITION_LEFT, Integer.valueOf(GravityCompat.START)), TuplesKt.to(DRAWER_POSITION_RIGHT, Integer.valueOf(GravityCompat.END)))));
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        LinkedHashMap exportedCustomDirectEventTypeConstants = super.getExportedCustomDirectEventTypeConstants();
        if (exportedCustomDirectEventTypeConstants == null) {
            exportedCustomDirectEventTypeConstants = new LinkedHashMap();
        }
        exportedCustomDirectEventTypeConstants.put(DrawerSlideEvent.EVENT_NAME, MapsKt.mapOf(TuplesKt.to("registrationName", "onDrawerSlide")));
        exportedCustomDirectEventTypeConstants.put(DrawerOpenedEvent.EVENT_NAME, MapsKt.mapOf(TuplesKt.to("registrationName", "onDrawerOpen")));
        exportedCustomDirectEventTypeConstants.put(DrawerClosedEvent.EVENT_NAME, MapsKt.mapOf(TuplesKt.to("registrationName", "onDrawerClose")));
        exportedCustomDirectEventTypeConstants.put(DrawerStateChangedEvent.EVENT_NAME, MapsKt.mapOf(TuplesKt.to("registrationName", "onDrawerStateChanged")));
        return exportedCustomDirectEventTypeConstants;
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void addView(ReactDrawerLayout parent, View child, int index) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(child, "child");
        if (getChildCount((ReactDrawerLayoutManager) parent) >= 2) {
            throw new JSApplicationIllegalArgumentException("The Drawer cannot have more than two children");
        }
        if (index != 0 && index != 1) {
            throw new JSApplicationIllegalArgumentException("The only valid indices for drawer's child are 0 or 1. Got " + index + " instead.");
        }
        parent.addView(child, index);
        parent.setDrawerProperties$ReactAndroid_release();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ViewManagerDelegate<ReactDrawerLayout> getDelegate() {
        return this.delegate;
    }

    /* compiled from: ReactDrawerLayoutManager.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/facebook/react/views/drawer/ReactDrawerLayoutManager$DrawerEventEmitter;", "Landroidx/drawerlayout/widget/DrawerLayout$DrawerListener;", "drawerLayout", "Landroidx/drawerlayout/widget/DrawerLayout;", "eventDispatcher", "Lcom/facebook/react/uimanager/events/EventDispatcher;", "(Landroidx/drawerlayout/widget/DrawerLayout;Lcom/facebook/react/uimanager/events/EventDispatcher;)V", "onDrawerClosed", "", "view", "Landroid/view/View;", "onDrawerOpened", "onDrawerSlide", "v", "", "onDrawerStateChanged", "i", "", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class DrawerEventEmitter implements DrawerLayout.DrawerListener {
        private final DrawerLayout drawerLayout;
        private final EventDispatcher eventDispatcher;

        public DrawerEventEmitter(DrawerLayout drawerLayout, EventDispatcher eventDispatcher) {
            Intrinsics.checkNotNullParameter(drawerLayout, "drawerLayout");
            Intrinsics.checkNotNullParameter(eventDispatcher, "eventDispatcher");
            this.drawerLayout = drawerLayout;
            this.eventDispatcher = eventDispatcher;
        }

        @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerSlide(View view, float v) {
            Intrinsics.checkNotNullParameter(view, "view");
            this.eventDispatcher.dispatchEvent(new DrawerSlideEvent(UIManagerHelper.getSurfaceId(this.drawerLayout), this.drawerLayout.getId(), v));
        }

        @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerOpened(View view) {
            Intrinsics.checkNotNullParameter(view, "view");
            this.eventDispatcher.dispatchEvent(new DrawerOpenedEvent(UIManagerHelper.getSurfaceId(this.drawerLayout), this.drawerLayout.getId()));
        }

        @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerClosed(View view) {
            Intrinsics.checkNotNullParameter(view, "view");
            this.eventDispatcher.dispatchEvent(new DrawerClosedEvent(UIManagerHelper.getSurfaceId(this.drawerLayout), this.drawerLayout.getId()));
        }

        @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerStateChanged(int i) {
            this.eventDispatcher.dispatchEvent(new DrawerStateChangedEvent(UIManagerHelper.getSurfaceId(this.drawerLayout), this.drawerLayout.getId(), i));
        }
    }
}
