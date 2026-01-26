package com.facebook.react.modules.debug;

import android.view.Choreographer;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.uimanager.UIManagerModule;
import java.util.Map;
import java.util.TreeMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FpsDebugFrameCallback.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\u0018\u0000 22\u00020\u0001:\u000223B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u000fH\u0016J\u0006\u0010+\u001a\u00020\nJ\u0010\u0010,\u001a\u0004\u0018\u00010#2\u0006\u0010-\u001a\u00020\u000fJ\u0006\u0010.\u001a\u00020)J\u0012\u0010/\u001a\u00020)2\b\b\u0002\u0010 \u001a\u00020\u0012H\u0007J\u0006\u00100\u001a\u00020)J\u0006\u00101\u001a\u00020)R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0017\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0014R\u000e\u0010\u0019\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u001c\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\fR\u0011\u0010\u001e\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020#\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010$\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b%\u0010\fR\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lcom/facebook/react/modules/debug/FpsDebugFrameCallback;", "Landroid/view/Choreographer$FrameCallback;", "reactContext", "Lcom/facebook/react/bridge/ReactContext;", "(Lcom/facebook/react/bridge/ReactContext;)V", "choreographer", "Landroid/view/Choreographer;", "didJSUpdateUiDuringFrameDetector", "Lcom/facebook/react/modules/debug/DidJSUpdateUiDuringFrameDetector;", "expectedNumFrames", "", "getExpectedNumFrames", "()I", "expectedNumFramesPrev", "firstFrameTime", "", "fourPlusFrameStutters", "fps", "", "getFps", "()D", "isRecordingFpsInfoAtEachFrame", "", "jsFPS", "getJsFPS", "lastFrameTime", "numFrameCallbacks", "numFrameCallbacksWithBatchDispatches", "numFrames", "getNumFrames", "numJSFrames", "getNumJSFrames", "targetFps", "timeToFps", "Ljava/util/TreeMap;", "Lcom/facebook/react/modules/debug/FpsDebugFrameCallback$FpsInfo;", "totalTimeMS", "getTotalTimeMS", "uiManagerModule", "Lcom/facebook/react/uimanager/UIManagerModule;", "doFrame", "", CmcdData.Factory.STREAM_TYPE_LIVE, "get4PlusFrameStutters", "getFpsInfo", "upToTimeMs", "reset", "start", "startAndRecordFpsAtEachFrame", "stop", "Companion", "FpsInfo", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FpsDebugFrameCallback implements Choreographer.FrameCallback {
    private static final Companion Companion = new Companion(null);
    private static final double DEFAULT_FPS = 60.0d;
    private Choreographer choreographer;
    private final DidJSUpdateUiDuringFrameDetector didJSUpdateUiDuringFrameDetector;
    private int expectedNumFramesPrev;
    private long firstFrameTime;
    private int fourPlusFrameStutters;
    private boolean isRecordingFpsInfoAtEachFrame;
    private long lastFrameTime;
    private int numFrameCallbacks;
    private int numFrameCallbacksWithBatchDispatches;
    private final ReactContext reactContext;
    private double targetFps;
    private TreeMap<Long, FpsInfo> timeToFps;
    private final UIManagerModule uiManagerModule;

    public final void start() {
        start$default(this, 0.0d, 1, null);
    }

    public FpsDebugFrameCallback(ReactContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        this.reactContext = reactContext;
        this.uiManagerModule = (UIManagerModule) reactContext.getNativeModule(UIManagerModule.class);
        this.didJSUpdateUiDuringFrameDetector = new DidJSUpdateUiDuringFrameDetector();
        this.firstFrameTime = -1L;
        this.lastFrameTime = -1L;
        this.targetFps = DEFAULT_FPS;
    }

    /* compiled from: FpsDebugFrameCallback.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\r\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0002\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010¨\u0006\u0015"}, d2 = {"Lcom/facebook/react/modules/debug/FpsDebugFrameCallback$FpsInfo;", "", "totalFrames", "", "totalJsFrames", "totalExpectedFrames", "total4PlusFrameStutters", "fps", "", "jsFps", "totalTimeMs", "(IIIIDDI)V", "getFps", "()D", "getJsFps", "getTotal4PlusFrameStutters", "()I", "getTotalExpectedFrames", "getTotalFrames", "getTotalJsFrames", "getTotalTimeMs", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class FpsInfo {
        private final double fps;
        private final double jsFps;
        private final int total4PlusFrameStutters;
        private final int totalExpectedFrames;
        private final int totalFrames;
        private final int totalJsFrames;
        private final int totalTimeMs;

        public FpsInfo(int i, int i2, int i3, int i4, double d, double d2, int i5) {
            this.totalFrames = i;
            this.totalJsFrames = i2;
            this.totalExpectedFrames = i3;
            this.total4PlusFrameStutters = i4;
            this.fps = d;
            this.jsFps = d2;
            this.totalTimeMs = i5;
        }

        public final int getTotalFrames() {
            return this.totalFrames;
        }

        public final int getTotalJsFrames() {
            return this.totalJsFrames;
        }

        public final int getTotalExpectedFrames() {
            return this.totalExpectedFrames;
        }

        public final int getTotal4PlusFrameStutters() {
            return this.total4PlusFrameStutters;
        }

        public final double getFps() {
            return this.fps;
        }

        public final double getJsFps() {
            return this.jsFps;
        }

        public final int getTotalTimeMs() {
            return this.totalTimeMs;
        }
    }

    @Override // android.view.Choreographer.FrameCallback
    public void doFrame(long l) {
        if (this.firstFrameTime == -1) {
            this.firstFrameTime = l;
        }
        long j = this.lastFrameTime;
        this.lastFrameTime = l;
        if (this.didJSUpdateUiDuringFrameDetector.getDidJSHitFrameAndCleanup(j, l)) {
            this.numFrameCallbacksWithBatchDispatches++;
        }
        this.numFrameCallbacks++;
        int expectedNumFrames = getExpectedNumFrames();
        if ((expectedNumFrames - this.expectedNumFramesPrev) - 1 >= 4) {
            this.fourPlusFrameStutters++;
        }
        if (this.isRecordingFpsInfoAtEachFrame) {
            Assertions.assertNotNull(this.timeToFps);
            FpsInfo fpsInfo = new FpsInfo(getNumFrames(), getNumJSFrames(), expectedNumFrames, this.fourPlusFrameStutters, getFps(), getJsFPS(), getTotalTimeMS());
            TreeMap<Long, FpsInfo> treeMap = this.timeToFps;
            if (treeMap != null) {
                treeMap.put(Long.valueOf(System.currentTimeMillis()), fpsInfo);
            }
        }
        this.expectedNumFramesPrev = expectedNumFrames;
        Choreographer choreographer = this.choreographer;
        if (choreographer != null) {
            choreographer.postFrameCallback(this);
        }
    }

    public static /* synthetic */ void start$default(FpsDebugFrameCallback fpsDebugFrameCallback, double d, int i, Object obj) {
        if ((i & 1) != 0) {
            d = fpsDebugFrameCallback.targetFps;
        }
        fpsDebugFrameCallback.start(d);
    }

    public final void start(double targetFps) {
        if (!this.reactContext.isBridgeless()) {
            this.reactContext.getCatalystInstance().addBridgeIdleDebugListener(this.didJSUpdateUiDuringFrameDetector);
        }
        UIManagerModule uIManagerModule = this.uiManagerModule;
        if (uIManagerModule != null) {
            uIManagerModule.setViewHierarchyUpdateDebugListener(this.didJSUpdateUiDuringFrameDetector);
        }
        this.targetFps = targetFps;
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.modules.debug.FpsDebugFrameCallback$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                FpsDebugFrameCallback.start$lambda$0(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void start$lambda$0(FpsDebugFrameCallback this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Choreographer choreographer = Choreographer.getInstance();
        this$0.choreographer = choreographer;
        if (choreographer != null) {
            choreographer.postFrameCallback(this$0);
        }
    }

    public final void startAndRecordFpsAtEachFrame() {
        this.timeToFps = new TreeMap<>();
        this.isRecordingFpsInfoAtEachFrame = true;
        start$default(this, 0.0d, 1, null);
    }

    public final void stop() {
        if (!this.reactContext.isBridgeless()) {
            this.reactContext.getCatalystInstance().removeBridgeIdleDebugListener(this.didJSUpdateUiDuringFrameDetector);
        }
        UIManagerModule uIManagerModule = this.uiManagerModule;
        if (uIManagerModule != null) {
            uIManagerModule.setViewHierarchyUpdateDebugListener(null);
        }
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.modules.debug.FpsDebugFrameCallback$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                FpsDebugFrameCallback.stop$lambda$1(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void stop$lambda$1(FpsDebugFrameCallback this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Choreographer choreographer = Choreographer.getInstance();
        this$0.choreographer = choreographer;
        if (choreographer != null) {
            choreographer.removeFrameCallback(this$0);
        }
    }

    public final double getFps() {
        if (this.lastFrameTime == this.firstFrameTime) {
            return 0.0d;
        }
        return (getNumFrames() * 1.0E9d) / (this.lastFrameTime - this.firstFrameTime);
    }

    public final double getJsFPS() {
        if (this.lastFrameTime == this.firstFrameTime) {
            return 0.0d;
        }
        return (getNumJSFrames() * 1.0E9d) / (this.lastFrameTime - this.firstFrameTime);
    }

    public final int getNumFrames() {
        return this.numFrameCallbacks - 1;
    }

    public final int getNumJSFrames() {
        return this.numFrameCallbacksWithBatchDispatches - 1;
    }

    public final int getExpectedNumFrames() {
        return (int) (((this.targetFps * getTotalTimeMS()) / 1000) + 1);
    }

    /* renamed from: get4PlusFrameStutters, reason: from getter */
    public final int getFourPlusFrameStutters() {
        return this.fourPlusFrameStutters;
    }

    public final int getTotalTimeMS() {
        return ((int) (this.lastFrameTime - this.firstFrameTime)) / 1000000;
    }

    public final FpsInfo getFpsInfo(long upToTimeMs) {
        Assertions.assertNotNull(this.timeToFps, "FPS was not recorded at each frame!");
        TreeMap<Long, FpsInfo> treeMap = this.timeToFps;
        Map.Entry<Long, FpsInfo> entryFloorEntry = treeMap != null ? treeMap.floorEntry(Long.valueOf(upToTimeMs)) : null;
        if (entryFloorEntry == null) {
            return null;
        }
        return entryFloorEntry.getValue();
    }

    public final void reset() {
        this.firstFrameTime = -1L;
        this.lastFrameTime = -1L;
        this.numFrameCallbacks = 0;
        this.fourPlusFrameStutters = 0;
        this.numFrameCallbacksWithBatchDispatches = 0;
        this.isRecordingFpsInfoAtEachFrame = false;
        this.timeToFps = null;
    }

    /* compiled from: FpsDebugFrameCallback.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/facebook/react/modules/debug/FpsDebugFrameCallback$Companion;", "", "()V", "DEFAULT_FPS", "", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
