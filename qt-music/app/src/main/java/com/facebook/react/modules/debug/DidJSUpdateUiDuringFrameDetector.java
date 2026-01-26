package com.facebook.react.modules.debug;

import com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener;
import com.facebook.react.uimanager.debug.NotThreadSafeViewHierarchyUpdateDebugListener;
import com.umeng.analytics.AnalyticsConfig;
import java.util.ArrayList;
import kotlin.Metadata;

/* compiled from: DidJSUpdateUiDuringFrameDetector.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0002J\u0016\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0006J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0014H\u0016J\b\u0010\u0016\u001a\u00020\u0014H\u0016J\b\u0010\u0017\u001a\u00020\u0014H\u0016J\b\u0010\u0018\u001a\u00020\u0014H\u0016R\u001e\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/facebook/react/modules/debug/DidJSUpdateUiDuringFrameDetector;", "Lcom/facebook/react/bridge/NotThreadSafeBridgeIdleDebugListener;", "Lcom/facebook/react/uimanager/debug/NotThreadSafeViewHierarchyUpdateDebugListener;", "()V", "transitionToBusyEvents", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "transitionToIdleEvents", "viewHierarchyUpdateEnqueuedEvents", "viewHierarchyUpdateFinishedEvents", "wasIdleAtEndOfLastFrame", "", "didEndFrameIdle", AnalyticsConfig.RTD_START_TIME, "endTime", "getDidJSHitFrameAndCleanup", "frameStartTimeNanos", "frameEndTimeNanos", "onBridgeDestroyed", "", "onTransitionToBridgeBusy", "onTransitionToBridgeIdle", "onViewHierarchyUpdateEnqueued", "onViewHierarchyUpdateFinished", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DidJSUpdateUiDuringFrameDetector implements NotThreadSafeBridgeIdleDebugListener, NotThreadSafeViewHierarchyUpdateDebugListener {
    private final ArrayList<Long> transitionToIdleEvents = new ArrayList<>(20);
    private final ArrayList<Long> transitionToBusyEvents = new ArrayList<>(20);
    private final ArrayList<Long> viewHierarchyUpdateEnqueuedEvents = new ArrayList<>(20);
    private final ArrayList<Long> viewHierarchyUpdateFinishedEvents = new ArrayList<>(20);
    private volatile boolean wasIdleAtEndOfLastFrame = true;

    @Override // com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener
    public synchronized void onTransitionToBridgeIdle() {
        this.transitionToIdleEvents.add(Long.valueOf(System.nanoTime()));
    }

    @Override // com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener
    public synchronized void onTransitionToBridgeBusy() {
        this.transitionToBusyEvents.add(Long.valueOf(System.nanoTime()));
    }

    @Override // com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener
    public synchronized void onBridgeDestroyed() {
    }

    @Override // com.facebook.react.uimanager.debug.NotThreadSafeViewHierarchyUpdateDebugListener
    public synchronized void onViewHierarchyUpdateEnqueued() {
        this.viewHierarchyUpdateEnqueuedEvents.add(Long.valueOf(System.nanoTime()));
    }

    @Override // com.facebook.react.uimanager.debug.NotThreadSafeViewHierarchyUpdateDebugListener
    public synchronized void onViewHierarchyUpdateFinished() {
        this.viewHierarchyUpdateFinishedEvents.add(Long.valueOf(System.nanoTime()));
    }

    public final synchronized boolean getDidJSHitFrameAndCleanup(long frameStartTimeNanos, long frameEndTimeNanos) {
        boolean z;
        boolean zHasEventBetweenTimestamps = DidJSUpdateUiDuringFrameDetectorKt.hasEventBetweenTimestamps(this.viewHierarchyUpdateFinishedEvents, frameStartTimeNanos, frameEndTimeNanos);
        boolean zDidEndFrameIdle = didEndFrameIdle(frameStartTimeNanos, frameEndTimeNanos);
        z = true;
        if (!zHasEventBetweenTimestamps && (!zDidEndFrameIdle || DidJSUpdateUiDuringFrameDetectorKt.hasEventBetweenTimestamps(this.viewHierarchyUpdateEnqueuedEvents, frameStartTimeNanos, frameEndTimeNanos))) {
            z = false;
        }
        DidJSUpdateUiDuringFrameDetectorKt.cleanUp(this.transitionToIdleEvents, frameEndTimeNanos);
        DidJSUpdateUiDuringFrameDetectorKt.cleanUp(this.transitionToBusyEvents, frameEndTimeNanos);
        DidJSUpdateUiDuringFrameDetectorKt.cleanUp(this.viewHierarchyUpdateEnqueuedEvents, frameEndTimeNanos);
        DidJSUpdateUiDuringFrameDetectorKt.cleanUp(this.viewHierarchyUpdateFinishedEvents, frameEndTimeNanos);
        this.wasIdleAtEndOfLastFrame = zDidEndFrameIdle;
        return z;
    }

    private final boolean didEndFrameIdle(long startTime, long endTime) {
        long lastEventBetweenTimestamps = DidJSUpdateUiDuringFrameDetectorKt.getLastEventBetweenTimestamps(this.transitionToIdleEvents, startTime, endTime);
        long lastEventBetweenTimestamps2 = DidJSUpdateUiDuringFrameDetectorKt.getLastEventBetweenTimestamps(this.transitionToBusyEvents, startTime, endTime);
        if (lastEventBetweenTimestamps == -1 && lastEventBetweenTimestamps2 == -1) {
            return this.wasIdleAtEndOfLastFrame;
        }
        return lastEventBetweenTimestamps > lastEventBetweenTimestamps2;
    }
}
