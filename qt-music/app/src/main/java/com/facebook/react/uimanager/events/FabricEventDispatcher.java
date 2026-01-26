package com.facebook.react.uimanager.events;

import android.os.Handler;
import android.view.Choreographer;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.systrace.Systrace;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes2.dex */
public class FabricEventDispatcher implements EventDispatcher, LifecycleEventListener {
    private static final Handler uiThreadHandler = UiThreadUtil.getUiThreadHandler();
    private final ReactApplicationContext mReactContext;
    private final ReactEventEmitter mReactEventEmitter;
    private final CopyOnWriteArrayList<EventDispatcherListener> mListeners = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<BatchEventDispatchedListener> mPostEventDispatchListeners = new CopyOnWriteArrayList<>();
    private final ScheduleDispatchFrameCallback mCurrentFrameCallback = new ScheduleDispatchFrameCallback();
    private boolean mIsDispatchScheduled = false;
    private final Runnable mDispatchEventsRunnable = new Runnable() { // from class: com.facebook.react.uimanager.events.FabricEventDispatcher.1
        @Override // java.lang.Runnable
        public void run() {
            FabricEventDispatcher.this.mIsDispatchScheduled = false;
            Systrace.beginSection(0L, "BatchEventDispatchedListeners");
            try {
                Iterator it = FabricEventDispatcher.this.mPostEventDispatchListeners.iterator();
                while (it.hasNext()) {
                    ((BatchEventDispatchedListener) it.next()).onBatchEventDispatched();
                }
            } finally {
                Systrace.endSection(0L);
            }
        }
    };

    public FabricEventDispatcher(ReactApplicationContext reactApplicationContext) {
        this.mReactContext = reactApplicationContext;
        reactApplicationContext.addLifecycleEventListener(this);
        this.mReactEventEmitter = new ReactEventEmitter(reactApplicationContext);
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void dispatchEvent(Event event) {
        Iterator<EventDispatcherListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onEventDispatch(event);
        }
        if (event.getExperimental_isSynchronous()) {
            dispatchSynchronous(event);
        } else {
            event.dispatchModern(this.mReactEventEmitter);
        }
        event.dispose();
        scheduleDispatchOfBatchedEvents();
    }

    private void dispatchSynchronous(Event event) {
        Systrace.beginSection(0L, "FabricEventDispatcher.dispatchSynchronous('" + event.getEventName() + "')");
        try {
            UIManager uIManager = UIManagerHelper.getUIManager(this.mReactContext, 2);
            if (uIManager instanceof SynchronousEventReceiver) {
                ((SynchronousEventReceiver) uIManager).receiveEvent(event.getSurfaceId(), event.getViewTag(), event.getEventName(), event.canCoalesce(), event.getEventData(), event.getEventCategory(), true);
            } else {
                ReactSoftExceptionLogger.logSoftException("FabricEventDispatcher", new ReactNoCrashSoftException("Fabric UIManager expected to implement SynchronousEventReceiver."));
            }
        } finally {
            Systrace.endSection(0L);
        }
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void dispatchAllEvents() {
        scheduleDispatchOfBatchedEvents();
    }

    private void scheduleDispatchOfBatchedEvents() {
        if (ReactNativeFeatureFlags.useOptimizedEventBatchingOnAndroid()) {
            if (this.mIsDispatchScheduled) {
                return;
            }
            this.mIsDispatchScheduled = true;
            uiThreadHandler.postAtFrontOfQueue(this.mDispatchEventsRunnable);
            return;
        }
        this.mCurrentFrameCallback.maybeScheduleDispatchOfBatchedEvents();
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void addListener(EventDispatcherListener eventDispatcherListener) {
        this.mListeners.add(eventDispatcherListener);
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void removeListener(EventDispatcherListener eventDispatcherListener) {
        this.mListeners.remove(eventDispatcherListener);
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void addBatchEventDispatchedListener(BatchEventDispatchedListener batchEventDispatchedListener) {
        this.mPostEventDispatchListeners.add(batchEventDispatchedListener);
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void removeBatchEventDispatchedListener(BatchEventDispatchedListener batchEventDispatchedListener) {
        this.mPostEventDispatchListeners.remove(batchEventDispatchedListener);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        scheduleDispatchOfBatchedEvents();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        cancelDispatchOfBatchedEvents();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        cancelDispatchOfBatchedEvents();
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void onCatalystInstanceDestroyed() {
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.uimanager.events.FabricEventDispatcher.2
            @Override // java.lang.Runnable
            public void run() {
                FabricEventDispatcher.this.cancelDispatchOfBatchedEvents();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelDispatchOfBatchedEvents() {
        UiThreadUtil.assertOnUiThread();
        if (ReactNativeFeatureFlags.useOptimizedEventBatchingOnAndroid()) {
            this.mIsDispatchScheduled = false;
            uiThreadHandler.removeCallbacks(this.mDispatchEventsRunnable);
        } else {
            this.mCurrentFrameCallback.stop();
        }
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void registerEventEmitter(int i, RCTEventEmitter rCTEventEmitter) {
        this.mReactEventEmitter.register(i, rCTEventEmitter);
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void registerEventEmitter(int i, RCTModernEventEmitter rCTModernEventEmitter) {
        this.mReactEventEmitter.register(i, rCTModernEventEmitter);
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void unregisterEventEmitter(int i) {
        this.mReactEventEmitter.unregister(i);
    }

    private class ScheduleDispatchFrameCallback implements Choreographer.FrameCallback {
        private volatile boolean mIsDispatchScheduled;
        private boolean mShouldStop;

        private ScheduleDispatchFrameCallback() {
            this.mIsDispatchScheduled = false;
            this.mShouldStop = false;
        }

        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long j) {
            UiThreadUtil.assertOnUiThread();
            if (this.mShouldStop) {
                this.mIsDispatchScheduled = false;
            } else {
                dispatchBatchedEvents();
            }
            Systrace.beginSection(0L, "BatchEventDispatchedListeners");
            try {
                Iterator it = FabricEventDispatcher.this.mPostEventDispatchListeners.iterator();
                while (it.hasNext()) {
                    ((BatchEventDispatchedListener) it.next()).onBatchEventDispatched();
                }
            } finally {
                Systrace.endSection(0L);
            }
        }

        public void stop() {
            this.mShouldStop = true;
        }

        public void maybeDispatchBatchedEvents() {
            if (this.mIsDispatchScheduled) {
                return;
            }
            this.mIsDispatchScheduled = true;
            dispatchBatchedEvents();
        }

        private void dispatchBatchedEvents() {
            ReactChoreographer.getInstance().postFrameCallback(ReactChoreographer.CallbackType.TIMERS_EVENTS, FabricEventDispatcher.this.mCurrentFrameCallback);
        }

        public void maybeScheduleDispatchOfBatchedEvents() {
            if (this.mIsDispatchScheduled) {
                return;
            }
            if (FabricEventDispatcher.this.mReactContext.isOnUiQueueThread()) {
                maybeDispatchBatchedEvents();
            } else {
                FabricEventDispatcher.this.mReactContext.runOnUiQueueThread(new Runnable() { // from class: com.facebook.react.uimanager.events.FabricEventDispatcher.ScheduleDispatchFrameCallback.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ScheduleDispatchFrameCallback.this.maybeDispatchBatchedEvents();
                    }
                });
            }
        }
    }
}
