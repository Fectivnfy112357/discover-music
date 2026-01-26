package com.facebook.react.internal.featureflags;

import kotlin.Metadata;

/* compiled from: ReactNativeFeatureFlagsProvider.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b0\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H'J\b\u0010\u0004\u001a\u00020\u0003H'J\b\u0010\u0005\u001a\u00020\u0003H'J\b\u0010\u0006\u001a\u00020\u0003H'J\b\u0010\u0007\u001a\u00020\u0003H'J\b\u0010\b\u001a\u00020\u0003H'J\b\u0010\t\u001a\u00020\u0003H'J\b\u0010\n\u001a\u00020\u0003H'J\b\u0010\u000b\u001a\u00020\u0003H'J\b\u0010\f\u001a\u00020\u0003H'J\b\u0010\r\u001a\u00020\u0003H'J\b\u0010\u000e\u001a\u00020\u0003H'J\b\u0010\u000f\u001a\u00020\u0003H'J\b\u0010\u0010\u001a\u00020\u0003H'J\b\u0010\u0011\u001a\u00020\u0003H'J\b\u0010\u0012\u001a\u00020\u0003H'J\b\u0010\u0013\u001a\u00020\u0003H'J\b\u0010\u0014\u001a\u00020\u0003H'J\b\u0010\u0015\u001a\u00020\u0003H'J\b\u0010\u0016\u001a\u00020\u0003H'J\b\u0010\u0017\u001a\u00020\u0003H'J\b\u0010\u0018\u001a\u00020\u0003H'J\b\u0010\u0019\u001a\u00020\u0003H'J\b\u0010\u001a\u001a\u00020\u0003H'J\b\u0010\u001b\u001a\u00020\u0003H'J\b\u0010\u001c\u001a\u00020\u0003H'J\b\u0010\u001d\u001a\u00020\u0003H'J\b\u0010\u001e\u001a\u00020\u0003H'J\b\u0010\u001f\u001a\u00020\u0003H'J\b\u0010 \u001a\u00020\u0003H'J\b\u0010!\u001a\u00020\u0003H'J\b\u0010\"\u001a\u00020\u0003H'J\b\u0010#\u001a\u00020\u0003H'J\b\u0010$\u001a\u00020\u0003H'J\b\u0010%\u001a\u00020\u0003H'J\b\u0010&\u001a\u00020\u0003H'J\b\u0010'\u001a\u00020\u0003H'J\b\u0010(\u001a\u00020\u0003H'J\b\u0010)\u001a\u00020\u0003H'J\b\u0010*\u001a\u00020\u0003H'J\b\u0010+\u001a\u00020\u0003H'J\b\u0010,\u001a\u00020\u0003H'J\b\u0010-\u001a\u00020\u0003H'J\b\u0010.\u001a\u00020\u0003H'J\b\u0010/\u001a\u00020\u0003H'J\b\u00100\u001a\u00020\u0003H'J\b\u00101\u001a\u00020\u0003H'J\b\u00102\u001a\u00020\u0003H'ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u00063À\u0006\u0001"}, d2 = {"Lcom/facebook/react/internal/featureflags/ReactNativeFeatureFlagsProvider;", "", "allowRecursiveCommitsWithSynchronousMountOnAndroid", "", "batchRenderingUpdatesInEventLoop", "commonTestFlag", "completeReactInstanceCreationOnBgThreadOnAndroid", "destroyFabricSurfacesInReactInstanceManager", "enableAlignItemsBaselineOnFabricIOS", "enableAndroidMixBlendModeProp", "enableBackgroundStyleApplicator", "enableCleanTextInputYogaNode", "enableEagerRootViewAttachment", "enableEventEmitterRetentionDuringGesturesOnAndroid", "enableFabricLogs", "enableFabricRendererExclusively", "enableGranularShadowTreeStateReconciliation", "enableIOSViewClipToPaddingBox", "enableLayoutAnimationsOnIOS", "enableLongTaskAPI", "enableMicrotasks", "enablePropsUpdateReconciliationAndroid", "enableReportEventPaintTime", "enableSynchronousStateUpdates", "enableUIConsistency", "enableViewRecycling", "excludeYogaFromRawProps", "fetchImagesInViewPreallocation", "fixIncorrectScrollViewStateUpdateOnAndroid", "fixMappingOfEventPrioritiesBetweenFabricAndReact", "fixMissedFabricStateUpdatesOnAndroid", "fixMountingCoordinatorReportedPendingTransactionsOnAndroid", "forceBatchingMountItemsOnAndroid", "fuseboxEnabledDebug", "fuseboxEnabledRelease", "initEagerTurboModulesOnNativeModulesQueueAndroid", "lazyAnimationCallbacks", "loadVectorDrawablesOnImages", "setAndroidLayoutDirection", "traceTurboModulePromiseRejectionsOnAndroid", "useFabricInterop", "useImmediateExecutorInAndroidBridgeless", "useModernRuntimeScheduler", "useNativeViewConfigsInBridgelessMode", "useNewReactImageViewBackgroundDrawing", "useOptimisedViewPreallocationOnAndroid", "useOptimizedEventBatchingOnAndroid", "useRuntimeShadowNodeReferenceUpdate", "useRuntimeShadowNodeReferenceUpdateOnLayout", "useStateAlignmentMechanism", "useTurboModuleInterop", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface ReactNativeFeatureFlagsProvider {
    boolean allowRecursiveCommitsWithSynchronousMountOnAndroid();

    boolean batchRenderingUpdatesInEventLoop();

    boolean commonTestFlag();

    boolean completeReactInstanceCreationOnBgThreadOnAndroid();

    boolean destroyFabricSurfacesInReactInstanceManager();

    boolean enableAlignItemsBaselineOnFabricIOS();

    boolean enableAndroidMixBlendModeProp();

    boolean enableBackgroundStyleApplicator();

    boolean enableCleanTextInputYogaNode();

    boolean enableEagerRootViewAttachment();

    /* renamed from: enableEventEmitterRetentionDuringGesturesOnAndroid */
    boolean get$fabricEnabled();

    boolean enableFabricLogs();

    boolean enableFabricRendererExclusively();

    boolean enableGranularShadowTreeStateReconciliation();

    boolean enableIOSViewClipToPaddingBox();

    boolean enableLayoutAnimationsOnIOS();

    boolean enableLongTaskAPI();

    boolean enableMicrotasks();

    boolean enablePropsUpdateReconciliationAndroid();

    boolean enableReportEventPaintTime();

    boolean enableSynchronousStateUpdates();

    boolean enableUIConsistency();

    boolean enableViewRecycling();

    boolean excludeYogaFromRawProps();

    boolean fetchImagesInViewPreallocation();

    boolean fixIncorrectScrollViewStateUpdateOnAndroid();

    boolean fixMappingOfEventPrioritiesBetweenFabricAndReact();

    boolean fixMissedFabricStateUpdatesOnAndroid();

    boolean fixMountingCoordinatorReportedPendingTransactionsOnAndroid();

    boolean forceBatchingMountItemsOnAndroid();

    boolean fuseboxEnabledDebug();

    boolean fuseboxEnabledRelease();

    boolean initEagerTurboModulesOnNativeModulesQueueAndroid();

    boolean lazyAnimationCallbacks();

    boolean loadVectorDrawablesOnImages();

    boolean setAndroidLayoutDirection();

    boolean traceTurboModulePromiseRejectionsOnAndroid();

    boolean useFabricInterop();

    boolean useImmediateExecutorInAndroidBridgeless();

    boolean useModernRuntimeScheduler();

    boolean useNativeViewConfigsInBridgelessMode();

    boolean useNewReactImageViewBackgroundDrawing();

    boolean useOptimisedViewPreallocationOnAndroid();

    boolean useOptimizedEventBatchingOnAndroid();

    boolean useRuntimeShadowNodeReferenceUpdate();

    boolean useRuntimeShadowNodeReferenceUpdateOnLayout();

    boolean useStateAlignmentMechanism();

    boolean useTurboModuleInterop();
}
