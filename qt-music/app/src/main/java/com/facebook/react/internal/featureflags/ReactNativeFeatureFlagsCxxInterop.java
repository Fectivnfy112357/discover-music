package com.facebook.react.internal.featureflags;

import com.facebook.soloader.SoLoader;
import com.umeng.analytics.pro.f;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* compiled from: ReactNativeFeatureFlagsCxxInterop.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b/\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004H\u0087 J\t\u0010\u0005\u001a\u00020\u0004H\u0087 J\t\u0010\u0006\u001a\u00020\u0004H\u0087 J\t\u0010\u0007\u001a\u00020\u0004H\u0087 J\t\u0010\b\u001a\u00020\tH\u0087 J\t\u0010\n\u001a\u00020\u0004H\u0087 J\t\u0010\u000b\u001a\u00020\u0004H\u0087 J\t\u0010\f\u001a\u00020\u0004H\u0087 J\t\u0010\r\u001a\u00020\u0004H\u0087 J\t\u0010\u000e\u001a\u00020\u0004H\u0087 J\t\u0010\u000f\u001a\u00020\u0004H\u0087 J\t\u0010\u0010\u001a\u00020\u0004H\u0087 J\t\u0010\u0011\u001a\u00020\u0004H\u0087 J\t\u0010\u0012\u001a\u00020\u0004H\u0087 J\t\u0010\u0013\u001a\u00020\u0004H\u0087 J\t\u0010\u0014\u001a\u00020\u0004H\u0087 J\t\u0010\u0015\u001a\u00020\u0004H\u0087 J\t\u0010\u0016\u001a\u00020\u0004H\u0087 J\t\u0010\u0017\u001a\u00020\u0004H\u0087 J\t\u0010\u0018\u001a\u00020\u0004H\u0087 J\t\u0010\u0019\u001a\u00020\u0004H\u0087 J\t\u0010\u001a\u001a\u00020\u0004H\u0087 J\t\u0010\u001b\u001a\u00020\u0004H\u0087 J\t\u0010\u001c\u001a\u00020\u0004H\u0087 J\t\u0010\u001d\u001a\u00020\u0004H\u0087 J\t\u0010\u001e\u001a\u00020\u0004H\u0087 J\t\u0010\u001f\u001a\u00020\u0004H\u0087 J\t\u0010 \u001a\u00020\u0004H\u0087 J\t\u0010!\u001a\u00020\u0004H\u0087 J\t\u0010\"\u001a\u00020\u0004H\u0087 J\t\u0010#\u001a\u00020\u0004H\u0087 J\t\u0010$\u001a\u00020\u0004H\u0087 J\t\u0010%\u001a\u00020\u0004H\u0087 J\t\u0010&\u001a\u00020\u0004H\u0087 J\t\u0010'\u001a\u00020\u0004H\u0087 J\t\u0010(\u001a\u00020\u0004H\u0087 J\u0011\u0010)\u001a\u00020\t2\u0006\u0010*\u001a\u00020\u0001H\u0087 J\t\u0010+\u001a\u00020\u0004H\u0087 J\t\u0010,\u001a\u00020\u0004H\u0087 J\t\u0010-\u001a\u00020\u0004H\u0087 J\t\u0010.\u001a\u00020\u0004H\u0087 J\t\u0010/\u001a\u00020\u0004H\u0087 J\t\u00100\u001a\u00020\u0004H\u0087 J\t\u00101\u001a\u00020\u0004H\u0087 J\t\u00102\u001a\u00020\u0004H\u0087 J\t\u00103\u001a\u00020\u0004H\u0087 J\t\u00104\u001a\u00020\u0004H\u0087 J\t\u00105\u001a\u00020\u0004H\u0087 J\t\u00106\u001a\u00020\u0004H\u0087 J\t\u00107\u001a\u00020\u0004H\u0087 ¨\u00068"}, d2 = {"Lcom/facebook/react/internal/featureflags/ReactNativeFeatureFlagsCxxInterop;", "", "()V", "allowRecursiveCommitsWithSynchronousMountOnAndroid", "", "batchRenderingUpdatesInEventLoop", "commonTestFlag", "completeReactInstanceCreationOnBgThreadOnAndroid", "dangerouslyReset", "", "destroyFabricSurfacesInReactInstanceManager", "enableAlignItemsBaselineOnFabricIOS", "enableAndroidMixBlendModeProp", "enableBackgroundStyleApplicator", "enableCleanTextInputYogaNode", "enableEagerRootViewAttachment", "enableEventEmitterRetentionDuringGesturesOnAndroid", "enableFabricLogs", "enableFabricRendererExclusively", "enableGranularShadowTreeStateReconciliation", "enableIOSViewClipToPaddingBox", "enableLayoutAnimationsOnIOS", "enableLongTaskAPI", "enableMicrotasks", "enablePropsUpdateReconciliationAndroid", "enableReportEventPaintTime", "enableSynchronousStateUpdates", "enableUIConsistency", "enableViewRecycling", "excludeYogaFromRawProps", "fetchImagesInViewPreallocation", "fixIncorrectScrollViewStateUpdateOnAndroid", "fixMappingOfEventPrioritiesBetweenFabricAndReact", "fixMissedFabricStateUpdatesOnAndroid", "fixMountingCoordinatorReportedPendingTransactionsOnAndroid", "forceBatchingMountItemsOnAndroid", "fuseboxEnabledDebug", "fuseboxEnabledRelease", "initEagerTurboModulesOnNativeModulesQueueAndroid", "lazyAnimationCallbacks", "loadVectorDrawablesOnImages", "override", f.M, "setAndroidLayoutDirection", "traceTurboModulePromiseRejectionsOnAndroid", "useFabricInterop", "useImmediateExecutorInAndroidBridgeless", "useModernRuntimeScheduler", "useNativeViewConfigsInBridgelessMode", "useNewReactImageViewBackgroundDrawing", "useOptimisedViewPreallocationOnAndroid", "useOptimizedEventBatchingOnAndroid", "useRuntimeShadowNodeReferenceUpdate", "useRuntimeShadowNodeReferenceUpdateOnLayout", "useStateAlignmentMechanism", "useTurboModuleInterop", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ReactNativeFeatureFlagsCxxInterop {
    public static final ReactNativeFeatureFlagsCxxInterop INSTANCE = new ReactNativeFeatureFlagsCxxInterop();

    @JvmStatic
    public static final native boolean allowRecursiveCommitsWithSynchronousMountOnAndroid();

    @JvmStatic
    public static final native boolean batchRenderingUpdatesInEventLoop();

    @JvmStatic
    public static final native boolean commonTestFlag();

    @JvmStatic
    public static final native boolean completeReactInstanceCreationOnBgThreadOnAndroid();

    @JvmStatic
    public static final native void dangerouslyReset();

    @JvmStatic
    public static final native boolean destroyFabricSurfacesInReactInstanceManager();

    @JvmStatic
    public static final native boolean enableAlignItemsBaselineOnFabricIOS();

    @JvmStatic
    public static final native boolean enableAndroidMixBlendModeProp();

    @JvmStatic
    public static final native boolean enableBackgroundStyleApplicator();

    @JvmStatic
    public static final native boolean enableCleanTextInputYogaNode();

    @JvmStatic
    public static final native boolean enableEagerRootViewAttachment();

    @JvmStatic
    public static final native boolean enableEventEmitterRetentionDuringGesturesOnAndroid();

    @JvmStatic
    public static final native boolean enableFabricLogs();

    @JvmStatic
    public static final native boolean enableFabricRendererExclusively();

    @JvmStatic
    public static final native boolean enableGranularShadowTreeStateReconciliation();

    @JvmStatic
    public static final native boolean enableIOSViewClipToPaddingBox();

    @JvmStatic
    public static final native boolean enableLayoutAnimationsOnIOS();

    @JvmStatic
    public static final native boolean enableLongTaskAPI();

    @JvmStatic
    public static final native boolean enableMicrotasks();

    @JvmStatic
    public static final native boolean enablePropsUpdateReconciliationAndroid();

    @JvmStatic
    public static final native boolean enableReportEventPaintTime();

    @JvmStatic
    public static final native boolean enableSynchronousStateUpdates();

    @JvmStatic
    public static final native boolean enableUIConsistency();

    @JvmStatic
    public static final native boolean enableViewRecycling();

    @JvmStatic
    public static final native boolean excludeYogaFromRawProps();

    @JvmStatic
    public static final native boolean fetchImagesInViewPreallocation();

    @JvmStatic
    public static final native boolean fixIncorrectScrollViewStateUpdateOnAndroid();

    @JvmStatic
    public static final native boolean fixMappingOfEventPrioritiesBetweenFabricAndReact();

    @JvmStatic
    public static final native boolean fixMissedFabricStateUpdatesOnAndroid();

    @JvmStatic
    public static final native boolean fixMountingCoordinatorReportedPendingTransactionsOnAndroid();

    @JvmStatic
    public static final native boolean forceBatchingMountItemsOnAndroid();

    @JvmStatic
    public static final native boolean fuseboxEnabledDebug();

    @JvmStatic
    public static final native boolean fuseboxEnabledRelease();

    @JvmStatic
    public static final native boolean initEagerTurboModulesOnNativeModulesQueueAndroid();

    @JvmStatic
    public static final native boolean lazyAnimationCallbacks();

    @JvmStatic
    public static final native boolean loadVectorDrawablesOnImages();

    @JvmStatic
    public static final native void override(Object provider);

    @JvmStatic
    public static final native boolean setAndroidLayoutDirection();

    @JvmStatic
    public static final native boolean traceTurboModulePromiseRejectionsOnAndroid();

    @JvmStatic
    public static final native boolean useFabricInterop();

    @JvmStatic
    public static final native boolean useImmediateExecutorInAndroidBridgeless();

    @JvmStatic
    public static final native boolean useModernRuntimeScheduler();

    @JvmStatic
    public static final native boolean useNativeViewConfigsInBridgelessMode();

    @JvmStatic
    public static final native boolean useNewReactImageViewBackgroundDrawing();

    @JvmStatic
    public static final native boolean useOptimisedViewPreallocationOnAndroid();

    @JvmStatic
    public static final native boolean useOptimizedEventBatchingOnAndroid();

    @JvmStatic
    public static final native boolean useRuntimeShadowNodeReferenceUpdate();

    @JvmStatic
    public static final native boolean useRuntimeShadowNodeReferenceUpdateOnLayout();

    @JvmStatic
    public static final native boolean useStateAlignmentMechanism();

    @JvmStatic
    public static final native boolean useTurboModuleInterop();

    private ReactNativeFeatureFlagsCxxInterop() {
    }

    static {
        SoLoader.loadLibrary("react_featureflagsjni");
    }
}
