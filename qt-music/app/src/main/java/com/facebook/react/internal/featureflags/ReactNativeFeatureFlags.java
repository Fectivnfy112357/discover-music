package com.facebook.react.internal.featureflags;

import com.umeng.analytics.pro.f;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReactNativeFeatureFlags.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u0011\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0007J\b\u0010\t\u001a\u00020\bH\u0007J\b\u0010\n\u001a\u00020\bH\u0007J\b\u0010\u000b\u001a\u00020\bH\u0007J\b\u0010\f\u001a\u00020\rH\u0007J\b\u0010\u000e\u001a\u00020\bH\u0007J\b\u0010\u000f\u001a\u00020\bH\u0007J\b\u0010\u0010\u001a\u00020\bH\u0007J\b\u0010\u0011\u001a\u00020\bH\u0007J\b\u0010\u0012\u001a\u00020\bH\u0007J\b\u0010\u0013\u001a\u00020\bH\u0007J\b\u0010\u0014\u001a\u00020\bH\u0007J\b\u0010\u0015\u001a\u00020\bH\u0007J\b\u0010\u0016\u001a\u00020\bH\u0007J\b\u0010\u0017\u001a\u00020\bH\u0007J\b\u0010\u0018\u001a\u00020\bH\u0007J\b\u0010\u0019\u001a\u00020\bH\u0007J\b\u0010\u001a\u001a\u00020\bH\u0007J\b\u0010\u001b\u001a\u00020\bH\u0007J\b\u0010\u001c\u001a\u00020\bH\u0007J\b\u0010\u001d\u001a\u00020\bH\u0007J\b\u0010\u001e\u001a\u00020\bH\u0007J\b\u0010\u001f\u001a\u00020\bH\u0007J\b\u0010 \u001a\u00020\bH\u0007J\b\u0010!\u001a\u00020\bH\u0007J\b\u0010\"\u001a\u00020\bH\u0007J\b\u0010#\u001a\u00020\bH\u0007J\b\u0010$\u001a\u00020\bH\u0007J\b\u0010%\u001a\u00020\bH\u0007J\b\u0010&\u001a\u00020\bH\u0007J\b\u0010'\u001a\u00020\bH\u0007J\b\u0010(\u001a\u00020\bH\u0007J\b\u0010)\u001a\u00020\bH\u0007J\b\u0010*\u001a\u00020\bH\u0007J\b\u0010+\u001a\u00020\bH\u0007J\b\u0010,\u001a\u00020\bH\u0007J\u0010\u0010-\u001a\u00020\r2\u0006\u0010.\u001a\u00020/H\u0007J\u001b\u00100\u001a\u00020\r2\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0000¢\u0006\u0002\b2J\b\u00103\u001a\u00020\bH\u0007J\b\u00104\u001a\u00020\bH\u0007J\b\u00105\u001a\u00020\bH\u0007J\b\u00106\u001a\u00020\bH\u0007J\b\u00107\u001a\u00020\bH\u0007J\b\u00108\u001a\u00020\bH\u0007J\b\u00109\u001a\u00020\bH\u0007J\b\u0010:\u001a\u00020\bH\u0007J\b\u0010;\u001a\u00020\bH\u0007J\b\u0010<\u001a\u00020\bH\u0007J\b\u0010=\u001a\u00020\bH\u0007J\b\u0010>\u001a\u00020\bH\u0007J\b\u0010?\u001a\u00020\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006@"}, d2 = {"Lcom/facebook/react/internal/featureflags/ReactNativeFeatureFlags;", "", "()V", "accessor", "Lcom/facebook/react/internal/featureflags/ReactNativeFeatureFlagsAccessor;", "accessorProvider", "Lkotlin/Function0;", "allowRecursiveCommitsWithSynchronousMountOnAndroid", "", "batchRenderingUpdatesInEventLoop", "commonTestFlag", "completeReactInstanceCreationOnBgThreadOnAndroid", "dangerouslyReset", "", "destroyFabricSurfacesInReactInstanceManager", "enableAlignItemsBaselineOnFabricIOS", "enableAndroidMixBlendModeProp", "enableBackgroundStyleApplicator", "enableCleanTextInputYogaNode", "enableEagerRootViewAttachment", "enableEventEmitterRetentionDuringGesturesOnAndroid", "enableFabricLogs", "enableFabricRendererExclusively", "enableGranularShadowTreeStateReconciliation", "enableIOSViewClipToPaddingBox", "enableLayoutAnimationsOnIOS", "enableLongTaskAPI", "enableMicrotasks", "enablePropsUpdateReconciliationAndroid", "enableReportEventPaintTime", "enableSynchronousStateUpdates", "enableUIConsistency", "enableViewRecycling", "excludeYogaFromRawProps", "fetchImagesInViewPreallocation", "fixIncorrectScrollViewStateUpdateOnAndroid", "fixMappingOfEventPrioritiesBetweenFabricAndReact", "fixMissedFabricStateUpdatesOnAndroid", "fixMountingCoordinatorReportedPendingTransactionsOnAndroid", "forceBatchingMountItemsOnAndroid", "fuseboxEnabledDebug", "fuseboxEnabledRelease", "initEagerTurboModulesOnNativeModulesQueueAndroid", "lazyAnimationCallbacks", "loadVectorDrawablesOnImages", "override", f.M, "Lcom/facebook/react/internal/featureflags/ReactNativeFeatureFlagsProvider;", "setAccessorProvider", "newAccessorProvider", "setAccessorProvider$ReactAndroid_release", "setAndroidLayoutDirection", "traceTurboModulePromiseRejectionsOnAndroid", "useFabricInterop", "useImmediateExecutorInAndroidBridgeless", "useModernRuntimeScheduler", "useNativeViewConfigsInBridgelessMode", "useNewReactImageViewBackgroundDrawing", "useOptimisedViewPreallocationOnAndroid", "useOptimizedEventBatchingOnAndroid", "useRuntimeShadowNodeReferenceUpdate", "useRuntimeShadowNodeReferenceUpdateOnLayout", "useStateAlignmentMechanism", "useTurboModuleInterop", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ReactNativeFeatureFlags {
    public static final ReactNativeFeatureFlags INSTANCE = new ReactNativeFeatureFlags();
    private static ReactNativeFeatureFlagsAccessor accessor;
    private static Function0<? extends ReactNativeFeatureFlagsAccessor> accessorProvider;

    private ReactNativeFeatureFlags() {
    }

    static {
        ReactNativeFeatureFlags$accessorProvider$1 reactNativeFeatureFlags$accessorProvider$1 = new Function0<ReactNativeFeatureFlagsCxxAccessor>() { // from class: com.facebook.react.internal.featureflags.ReactNativeFeatureFlags$accessorProvider$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ReactNativeFeatureFlagsCxxAccessor invoke() {
                return new ReactNativeFeatureFlagsCxxAccessor();
            }
        };
        accessorProvider = reactNativeFeatureFlags$accessorProvider$1;
        accessor = reactNativeFeatureFlags$accessorProvider$1.invoke();
    }

    @JvmStatic
    public static final boolean commonTestFlag() {
        return accessor.commonTestFlag();
    }

    @JvmStatic
    public static final boolean allowRecursiveCommitsWithSynchronousMountOnAndroid() {
        return accessor.allowRecursiveCommitsWithSynchronousMountOnAndroid();
    }

    @JvmStatic
    public static final boolean batchRenderingUpdatesInEventLoop() {
        return accessor.batchRenderingUpdatesInEventLoop();
    }

    @JvmStatic
    public static final boolean completeReactInstanceCreationOnBgThreadOnAndroid() {
        return accessor.completeReactInstanceCreationOnBgThreadOnAndroid();
    }

    @JvmStatic
    public static final boolean destroyFabricSurfacesInReactInstanceManager() {
        return accessor.destroyFabricSurfacesInReactInstanceManager();
    }

    @JvmStatic
    public static final boolean enableAlignItemsBaselineOnFabricIOS() {
        return accessor.enableAlignItemsBaselineOnFabricIOS();
    }

    @JvmStatic
    public static final boolean enableAndroidMixBlendModeProp() {
        return accessor.enableAndroidMixBlendModeProp();
    }

    @JvmStatic
    public static final boolean enableBackgroundStyleApplicator() {
        return accessor.enableBackgroundStyleApplicator();
    }

    @JvmStatic
    public static final boolean enableCleanTextInputYogaNode() {
        return accessor.enableCleanTextInputYogaNode();
    }

    @JvmStatic
    public static final boolean enableEagerRootViewAttachment() {
        return accessor.enableEagerRootViewAttachment();
    }

    @JvmStatic
    public static final boolean enableEventEmitterRetentionDuringGesturesOnAndroid() {
        return accessor.get$fabricEnabled();
    }

    @JvmStatic
    public static final boolean enableFabricLogs() {
        return accessor.enableFabricLogs();
    }

    @JvmStatic
    public static final boolean enableFabricRendererExclusively() {
        return accessor.enableFabricRendererExclusively();
    }

    @JvmStatic
    public static final boolean enableGranularShadowTreeStateReconciliation() {
        return accessor.enableGranularShadowTreeStateReconciliation();
    }

    @JvmStatic
    public static final boolean enableIOSViewClipToPaddingBox() {
        return accessor.enableIOSViewClipToPaddingBox();
    }

    @JvmStatic
    public static final boolean enableLayoutAnimationsOnIOS() {
        return accessor.enableLayoutAnimationsOnIOS();
    }

    @JvmStatic
    public static final boolean enableLongTaskAPI() {
        return accessor.enableLongTaskAPI();
    }

    @JvmStatic
    public static final boolean enableMicrotasks() {
        return accessor.enableMicrotasks();
    }

    @JvmStatic
    public static final boolean enablePropsUpdateReconciliationAndroid() {
        return accessor.enablePropsUpdateReconciliationAndroid();
    }

    @JvmStatic
    public static final boolean enableReportEventPaintTime() {
        return accessor.enableReportEventPaintTime();
    }

    @JvmStatic
    public static final boolean enableSynchronousStateUpdates() {
        return accessor.enableSynchronousStateUpdates();
    }

    @JvmStatic
    public static final boolean enableUIConsistency() {
        return accessor.enableUIConsistency();
    }

    @JvmStatic
    public static final boolean enableViewRecycling() {
        return accessor.enableViewRecycling();
    }

    @JvmStatic
    public static final boolean excludeYogaFromRawProps() {
        return accessor.excludeYogaFromRawProps();
    }

    @JvmStatic
    public static final boolean fetchImagesInViewPreallocation() {
        return accessor.fetchImagesInViewPreallocation();
    }

    @JvmStatic
    public static final boolean fixIncorrectScrollViewStateUpdateOnAndroid() {
        return accessor.fixIncorrectScrollViewStateUpdateOnAndroid();
    }

    @JvmStatic
    public static final boolean fixMappingOfEventPrioritiesBetweenFabricAndReact() {
        return accessor.fixMappingOfEventPrioritiesBetweenFabricAndReact();
    }

    @JvmStatic
    public static final boolean fixMissedFabricStateUpdatesOnAndroid() {
        return accessor.fixMissedFabricStateUpdatesOnAndroid();
    }

    @JvmStatic
    public static final boolean fixMountingCoordinatorReportedPendingTransactionsOnAndroid() {
        return accessor.fixMountingCoordinatorReportedPendingTransactionsOnAndroid();
    }

    @JvmStatic
    public static final boolean forceBatchingMountItemsOnAndroid() {
        return accessor.forceBatchingMountItemsOnAndroid();
    }

    @JvmStatic
    public static final boolean fuseboxEnabledDebug() {
        return accessor.fuseboxEnabledDebug();
    }

    @JvmStatic
    public static final boolean fuseboxEnabledRelease() {
        return accessor.fuseboxEnabledRelease();
    }

    @JvmStatic
    public static final boolean initEagerTurboModulesOnNativeModulesQueueAndroid() {
        return accessor.initEagerTurboModulesOnNativeModulesQueueAndroid();
    }

    @JvmStatic
    public static final boolean lazyAnimationCallbacks() {
        return accessor.lazyAnimationCallbacks();
    }

    @JvmStatic
    public static final boolean loadVectorDrawablesOnImages() {
        return accessor.loadVectorDrawablesOnImages();
    }

    @JvmStatic
    public static final boolean setAndroidLayoutDirection() {
        return accessor.setAndroidLayoutDirection();
    }

    @JvmStatic
    public static final boolean traceTurboModulePromiseRejectionsOnAndroid() {
        return accessor.traceTurboModulePromiseRejectionsOnAndroid();
    }

    @JvmStatic
    public static final boolean useFabricInterop() {
        return accessor.useFabricInterop();
    }

    @JvmStatic
    public static final boolean useImmediateExecutorInAndroidBridgeless() {
        return accessor.useImmediateExecutorInAndroidBridgeless();
    }

    @JvmStatic
    public static final boolean useModernRuntimeScheduler() {
        return accessor.useModernRuntimeScheduler();
    }

    @JvmStatic
    public static final boolean useNativeViewConfigsInBridgelessMode() {
        return accessor.useNativeViewConfigsInBridgelessMode();
    }

    @JvmStatic
    public static final boolean useNewReactImageViewBackgroundDrawing() {
        return accessor.useNewReactImageViewBackgroundDrawing();
    }

    @JvmStatic
    public static final boolean useOptimisedViewPreallocationOnAndroid() {
        return accessor.useOptimisedViewPreallocationOnAndroid();
    }

    @JvmStatic
    public static final boolean useOptimizedEventBatchingOnAndroid() {
        return accessor.useOptimizedEventBatchingOnAndroid();
    }

    @JvmStatic
    public static final boolean useRuntimeShadowNodeReferenceUpdate() {
        return accessor.useRuntimeShadowNodeReferenceUpdate();
    }

    @JvmStatic
    public static final boolean useRuntimeShadowNodeReferenceUpdateOnLayout() {
        return accessor.useRuntimeShadowNodeReferenceUpdateOnLayout();
    }

    @JvmStatic
    public static final boolean useStateAlignmentMechanism() {
        return accessor.useStateAlignmentMechanism();
    }

    @JvmStatic
    public static final boolean useTurboModuleInterop() {
        return accessor.useTurboModuleInterop();
    }

    @JvmStatic
    public static final void override(ReactNativeFeatureFlagsProvider provider) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        accessor.override(provider);
    }

    @JvmStatic
    public static final void dangerouslyReset() {
        accessor.dangerouslyReset();
        accessor = accessorProvider.invoke();
    }

    public final void setAccessorProvider$ReactAndroid_release(Function0<? extends ReactNativeFeatureFlagsAccessor> newAccessorProvider) {
        Intrinsics.checkNotNullParameter(newAccessorProvider, "newAccessorProvider");
        accessorProvider = newAccessorProvider;
        accessor = newAccessorProvider.invoke();
    }
}
