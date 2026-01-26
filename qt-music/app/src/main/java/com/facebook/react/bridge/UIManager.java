package com.facebook.react.bridge;

import android.view.View;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.react.uimanager.events.EventDispatcher;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;

/* compiled from: UIManager.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J+\u0010\u0006\u001a\u00020\u0007\"\n\b\u0000\u0010\b*\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u0002H\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH'¢\u0006\u0002\u0010\rJ\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H&J\"\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H&J\"\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00172\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H&J\b\u0010\u0018\u001a\u00020\u000fH&J\b\u0010\u0019\u001a\u00020\u000fH&J\u0018\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0007H&J*\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u00172\b\u0010\u001e\u001a\u0004\u0018\u00010\fH&J\"\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u00172\b\u0010\u001e\u001a\u0004\u0018\u00010\fH'J\u0012\u0010\u001f\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H&J\u0012\u0010 \u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001d\u001a\u00020\u0017H'J\u0012\u0010!\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0013\u001a\u00020\u0007H&J\u0018\u0010\"\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u0007H&JC\u0010$\u001a\u00020\u0007\"\n\b\u0000\u0010\b*\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u0002H\b2\u0006\u0010%\u001a\u00020\u00172\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010&\u001a\u00020\u00072\u0006\u0010'\u001a\u00020\u0007H'¢\u0006\u0002\u0010(J\u0010\u0010)\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u0007H'J\u0018\u0010*\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0007H&J\u001a\u0010+\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00072\b\u0010,\u001a\u0004\u0018\u00010-H'J0\u0010.\u001a\u00020\u000f2\u0006\u0010/\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u00072\u0006\u0010'\u001a\u00020\u00072\u0006\u00100\u001a\u00020\u00072\u0006\u00101\u001a\u00020\u0007H'R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u00062À\u0006\u0001"}, d2 = {"Lcom/facebook/react/bridge/UIManager;", "Lcom/facebook/react/bridge/PerformanceCounter;", "eventDispatcher", "Lcom/facebook/react/uimanager/events/EventDispatcher;", "getEventDispatcher", "()Lcom/facebook/react/uimanager/events/EventDispatcher;", "addRootView", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/view/View;", "rootView", "initialProps", "Lcom/facebook/react/bridge/WritableMap;", "(Landroid/view/View;Lcom/facebook/react/bridge/WritableMap;)I", "addUIManagerEventListener", "", "listener", "Lcom/facebook/react/bridge/UIManagerListener;", "dispatchCommand", "reactTag", "commandId", "commandArgs", "Lcom/facebook/react/bridge/ReadableArray;", "", "initialize", "invalidate", "markActiveTouchForTag", "surfaceId", "receiveEvent", "eventName", "event", "removeUIManagerEventListener", "resolveCustomDirectEventName", "resolveView", "sendAccessibilityEvent", "eventType", "startSurface", "moduleName", "widthMeasureSpec", "heightMeasureSpec", "(Landroid/view/View;Ljava/lang/String;Lcom/facebook/react/bridge/WritableMap;II)I", "stopSurface", "sweepActiveTouchForTag", "synchronouslyUpdateViewOnUIThread", "props", "Lcom/facebook/react/bridge/ReadableMap;", "updateRootLayoutSpecs", "rootTag", "offsetX", "offsetY", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface UIManager extends PerformanceCounter {
    @Deprecated(message = "")
    <T extends View> int addRootView(T rootView, WritableMap initialProps);

    void addUIManagerEventListener(UIManagerListener listener);

    void dispatchCommand(int reactTag, int commandId, ReadableArray commandArgs);

    void dispatchCommand(int reactTag, String commandId, ReadableArray commandArgs);

    EventDispatcher getEventDispatcher();

    void initialize();

    void invalidate();

    void markActiveTouchForTag(int surfaceId, int reactTag);

    void receiveEvent(int surfaceId, int reactTag, String eventName, WritableMap event);

    @Deprecated(message = "", replaceWith = @ReplaceWith(expression = "receiveEvent(surfaceId, reactTag, eventName, event)", imports = {}))
    void receiveEvent(int reactTag, String eventName, WritableMap event);

    void removeUIManagerEventListener(UIManagerListener listener);

    @Deprecated(message = "")
    String resolveCustomDirectEventName(String eventName);

    View resolveView(int reactTag);

    void sendAccessibilityEvent(int reactTag, int eventType);

    <T extends View> int startSurface(T rootView, String moduleName, WritableMap initialProps, int widthMeasureSpec, int heightMeasureSpec);

    void stopSurface(int surfaceId);

    void sweepActiveTouchForTag(int surfaceId, int reactTag);

    void synchronouslyUpdateViewOnUIThread(int reactTag, ReadableMap props);

    void updateRootLayoutSpecs(int rootTag, int widthMeasureSpec, int heightMeasureSpec, int offsetX, int offsetY);
}
