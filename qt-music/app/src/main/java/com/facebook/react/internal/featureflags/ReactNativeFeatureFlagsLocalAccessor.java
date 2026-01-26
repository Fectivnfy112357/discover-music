package com.facebook.react.internal.featureflags;

import com.umeng.analytics.pro.f;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReactNativeFeatureFlagsLocalAccessor.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b1\n\u0002\u0010\u0002\n\u0002\b/\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010:\u001a\u00020\u0007H\u0016J\b\u0010;\u001a\u00020\u0007H\u0016J\b\u0010<\u001a\u00020\u0007H\u0016J\b\u0010=\u001a\u00020\u0007H\u0016J\b\u0010>\u001a\u00020?H\u0016J\b\u0010@\u001a\u00020\u0007H\u0016J\b\u0010A\u001a\u00020\u0007H\u0016J\b\u0010B\u001a\u00020\u0007H\u0016J\b\u0010C\u001a\u00020\u0007H\u0016J\b\u0010D\u001a\u00020\u0007H\u0016J\b\u0010E\u001a\u00020\u0007H\u0016J\b\u0010F\u001a\u00020\u0007H\u0016J\b\u0010G\u001a\u00020\u0007H\u0016J\b\u0010H\u001a\u00020\u0007H\u0016J\b\u0010I\u001a\u00020\u0007H\u0016J\b\u0010J\u001a\u00020\u0007H\u0016J\b\u0010K\u001a\u00020\u0007H\u0016J\b\u0010L\u001a\u00020\u0007H\u0016J\b\u0010M\u001a\u00020\u0007H\u0016J\b\u0010N\u001a\u00020\u0007H\u0016J\b\u0010O\u001a\u00020\u0007H\u0016J\b\u0010P\u001a\u00020\u0007H\u0016J\b\u0010Q\u001a\u00020\u0007H\u0016J\b\u0010R\u001a\u00020\u0007H\u0016J\b\u0010S\u001a\u00020\u0007H\u0016J\b\u0010T\u001a\u00020\u0007H\u0016J\b\u0010U\u001a\u00020\u0007H\u0016J\b\u0010V\u001a\u00020\u0007H\u0016J\b\u0010W\u001a\u00020\u0007H\u0016J\b\u0010X\u001a\u00020\u0007H\u0016J\b\u0010Y\u001a\u00020\u0007H\u0016J\b\u0010Z\u001a\u00020\u0007H\u0016J\b\u0010[\u001a\u00020\u0007H\u0016J\b\u0010\\\u001a\u00020\u0007H\u0016J\b\u0010]\u001a\u00020\u0007H\u0016J\b\u0010^\u001a\u00020\u0007H\u0016J\u0010\u0010_\u001a\u00020?2\u0006\u0010`\u001a\u00020\rH\u0016J\b\u0010a\u001a\u00020\u0007H\u0016J\b\u0010b\u001a\u00020\u0007H\u0016J\b\u0010c\u001a\u00020\u0007H\u0016J\b\u0010d\u001a\u00020\u0007H\u0016J\b\u0010e\u001a\u00020\u0007H\u0016J\b\u0010f\u001a\u00020\u0007H\u0016J\b\u0010g\u001a\u00020\u0007H\u0016J\b\u0010h\u001a\u00020\u0007H\u0016J\b\u0010i\u001a\u00020\u0007H\u0016J\b\u0010j\u001a\u00020\u0007H\u0016J\b\u0010k\u001a\u00020\u0007H\u0016J\b\u0010l\u001a\u00020\u0007H\u0016J\b\u0010m\u001a\u00020\u0007H\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010\t\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010\n\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010\u000b\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010\u001f\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010 \u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010!\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010\"\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010#\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010$\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010%\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010&\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010'\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010(\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010)\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010*\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010+\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010,\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010-\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010.\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010/\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u00100\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u00101\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u00102\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u00103\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u00104\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u00105\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u00106\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u00107\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u00108\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u00109\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\b¨\u0006n"}, d2 = {"Lcom/facebook/react/internal/featureflags/ReactNativeFeatureFlagsLocalAccessor;", "Lcom/facebook/react/internal/featureflags/ReactNativeFeatureFlagsAccessor;", "()V", "accessedFeatureFlags", "", "", "allowRecursiveCommitsWithSynchronousMountOnAndroidCache", "", "Ljava/lang/Boolean;", "batchRenderingUpdatesInEventLoopCache", "commonTestFlagCache", "completeReactInstanceCreationOnBgThreadOnAndroidCache", "currentProvider", "Lcom/facebook/react/internal/featureflags/ReactNativeFeatureFlagsProvider;", "destroyFabricSurfacesInReactInstanceManagerCache", "enableAlignItemsBaselineOnFabricIOSCache", "enableAndroidMixBlendModePropCache", "enableBackgroundStyleApplicatorCache", "enableCleanTextInputYogaNodeCache", "enableEagerRootViewAttachmentCache", "enableEventEmitterRetentionDuringGesturesOnAndroidCache", "enableFabricLogsCache", "enableFabricRendererExclusivelyCache", "enableGranularShadowTreeStateReconciliationCache", "enableIOSViewClipToPaddingBoxCache", "enableLayoutAnimationsOnIOSCache", "enableLongTaskAPICache", "enableMicrotasksCache", "enablePropsUpdateReconciliationAndroidCache", "enableReportEventPaintTimeCache", "enableSynchronousStateUpdatesCache", "enableUIConsistencyCache", "enableViewRecyclingCache", "excludeYogaFromRawPropsCache", "fetchImagesInViewPreallocationCache", "fixIncorrectScrollViewStateUpdateOnAndroidCache", "fixMappingOfEventPrioritiesBetweenFabricAndReactCache", "fixMissedFabricStateUpdatesOnAndroidCache", "fixMountingCoordinatorReportedPendingTransactionsOnAndroidCache", "forceBatchingMountItemsOnAndroidCache", "fuseboxEnabledDebugCache", "fuseboxEnabledReleaseCache", "initEagerTurboModulesOnNativeModulesQueueAndroidCache", "lazyAnimationCallbacksCache", "loadVectorDrawablesOnImagesCache", "setAndroidLayoutDirectionCache", "traceTurboModulePromiseRejectionsOnAndroidCache", "useFabricInteropCache", "useImmediateExecutorInAndroidBridgelessCache", "useModernRuntimeSchedulerCache", "useNativeViewConfigsInBridgelessModeCache", "useNewReactImageViewBackgroundDrawingCache", "useOptimisedViewPreallocationOnAndroidCache", "useOptimizedEventBatchingOnAndroidCache", "useRuntimeShadowNodeReferenceUpdateCache", "useRuntimeShadowNodeReferenceUpdateOnLayoutCache", "useStateAlignmentMechanismCache", "useTurboModuleInteropCache", "allowRecursiveCommitsWithSynchronousMountOnAndroid", "batchRenderingUpdatesInEventLoop", "commonTestFlag", "completeReactInstanceCreationOnBgThreadOnAndroid", "dangerouslyReset", "", "destroyFabricSurfacesInReactInstanceManager", "enableAlignItemsBaselineOnFabricIOS", "enableAndroidMixBlendModeProp", "enableBackgroundStyleApplicator", "enableCleanTextInputYogaNode", "enableEagerRootViewAttachment", "enableEventEmitterRetentionDuringGesturesOnAndroid", "enableFabricLogs", "enableFabricRendererExclusively", "enableGranularShadowTreeStateReconciliation", "enableIOSViewClipToPaddingBox", "enableLayoutAnimationsOnIOS", "enableLongTaskAPI", "enableMicrotasks", "enablePropsUpdateReconciliationAndroid", "enableReportEventPaintTime", "enableSynchronousStateUpdates", "enableUIConsistency", "enableViewRecycling", "excludeYogaFromRawProps", "fetchImagesInViewPreallocation", "fixIncorrectScrollViewStateUpdateOnAndroid", "fixMappingOfEventPrioritiesBetweenFabricAndReact", "fixMissedFabricStateUpdatesOnAndroid", "fixMountingCoordinatorReportedPendingTransactionsOnAndroid", "forceBatchingMountItemsOnAndroid", "fuseboxEnabledDebug", "fuseboxEnabledRelease", "initEagerTurboModulesOnNativeModulesQueueAndroid", "lazyAnimationCallbacks", "loadVectorDrawablesOnImages", "override", f.M, "setAndroidLayoutDirection", "traceTurboModulePromiseRejectionsOnAndroid", "useFabricInterop", "useImmediateExecutorInAndroidBridgeless", "useModernRuntimeScheduler", "useNativeViewConfigsInBridgelessMode", "useNewReactImageViewBackgroundDrawing", "useOptimisedViewPreallocationOnAndroid", "useOptimizedEventBatchingOnAndroid", "useRuntimeShadowNodeReferenceUpdate", "useRuntimeShadowNodeReferenceUpdateOnLayout", "useStateAlignmentMechanism", "useTurboModuleInterop", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ReactNativeFeatureFlagsLocalAccessor implements ReactNativeFeatureFlagsAccessor {
    private Boolean allowRecursiveCommitsWithSynchronousMountOnAndroidCache;
    private Boolean batchRenderingUpdatesInEventLoopCache;
    private Boolean commonTestFlagCache;
    private Boolean completeReactInstanceCreationOnBgThreadOnAndroidCache;
    private Boolean destroyFabricSurfacesInReactInstanceManagerCache;
    private Boolean enableAlignItemsBaselineOnFabricIOSCache;
    private Boolean enableAndroidMixBlendModePropCache;
    private Boolean enableBackgroundStyleApplicatorCache;
    private Boolean enableCleanTextInputYogaNodeCache;
    private Boolean enableEagerRootViewAttachmentCache;
    private Boolean enableEventEmitterRetentionDuringGesturesOnAndroidCache;
    private Boolean enableFabricLogsCache;
    private Boolean enableFabricRendererExclusivelyCache;
    private Boolean enableGranularShadowTreeStateReconciliationCache;
    private Boolean enableIOSViewClipToPaddingBoxCache;
    private Boolean enableLayoutAnimationsOnIOSCache;
    private Boolean enableLongTaskAPICache;
    private Boolean enableMicrotasksCache;
    private Boolean enablePropsUpdateReconciliationAndroidCache;
    private Boolean enableReportEventPaintTimeCache;
    private Boolean enableSynchronousStateUpdatesCache;
    private Boolean enableUIConsistencyCache;
    private Boolean enableViewRecyclingCache;
    private Boolean excludeYogaFromRawPropsCache;
    private Boolean fetchImagesInViewPreallocationCache;
    private Boolean fixIncorrectScrollViewStateUpdateOnAndroidCache;
    private Boolean fixMappingOfEventPrioritiesBetweenFabricAndReactCache;
    private Boolean fixMissedFabricStateUpdatesOnAndroidCache;
    private Boolean fixMountingCoordinatorReportedPendingTransactionsOnAndroidCache;
    private Boolean forceBatchingMountItemsOnAndroidCache;
    private Boolean fuseboxEnabledDebugCache;
    private Boolean fuseboxEnabledReleaseCache;
    private Boolean initEagerTurboModulesOnNativeModulesQueueAndroidCache;
    private Boolean lazyAnimationCallbacksCache;
    private Boolean loadVectorDrawablesOnImagesCache;
    private Boolean setAndroidLayoutDirectionCache;
    private Boolean traceTurboModulePromiseRejectionsOnAndroidCache;
    private Boolean useFabricInteropCache;
    private Boolean useImmediateExecutorInAndroidBridgelessCache;
    private Boolean useModernRuntimeSchedulerCache;
    private Boolean useNativeViewConfigsInBridgelessModeCache;
    private Boolean useNewReactImageViewBackgroundDrawingCache;
    private Boolean useOptimisedViewPreallocationOnAndroidCache;
    private Boolean useOptimizedEventBatchingOnAndroidCache;
    private Boolean useRuntimeShadowNodeReferenceUpdateCache;
    private Boolean useRuntimeShadowNodeReferenceUpdateOnLayoutCache;
    private Boolean useStateAlignmentMechanismCache;
    private Boolean useTurboModuleInteropCache;
    private ReactNativeFeatureFlagsProvider currentProvider = new ReactNativeFeatureFlagsDefaults();
    private final Set<String> accessedFeatureFlags = new LinkedHashSet();

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsAccessor
    public void dangerouslyReset() {
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean commonTestFlag() {
        Boolean boolValueOf = this.commonTestFlagCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.commonTestFlag());
            this.accessedFeatureFlags.add("commonTestFlag");
            this.commonTestFlagCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean allowRecursiveCommitsWithSynchronousMountOnAndroid() {
        Boolean boolValueOf = this.allowRecursiveCommitsWithSynchronousMountOnAndroidCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.allowRecursiveCommitsWithSynchronousMountOnAndroid());
            this.accessedFeatureFlags.add("allowRecursiveCommitsWithSynchronousMountOnAndroid");
            this.allowRecursiveCommitsWithSynchronousMountOnAndroidCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean batchRenderingUpdatesInEventLoop() {
        Boolean boolValueOf = this.batchRenderingUpdatesInEventLoopCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.batchRenderingUpdatesInEventLoop());
            this.accessedFeatureFlags.add("batchRenderingUpdatesInEventLoop");
            this.batchRenderingUpdatesInEventLoopCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean completeReactInstanceCreationOnBgThreadOnAndroid() {
        Boolean boolValueOf = this.completeReactInstanceCreationOnBgThreadOnAndroidCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.completeReactInstanceCreationOnBgThreadOnAndroid());
            this.accessedFeatureFlags.add("completeReactInstanceCreationOnBgThreadOnAndroid");
            this.completeReactInstanceCreationOnBgThreadOnAndroidCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean destroyFabricSurfacesInReactInstanceManager() {
        Boolean boolValueOf = this.destroyFabricSurfacesInReactInstanceManagerCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.destroyFabricSurfacesInReactInstanceManager());
            this.accessedFeatureFlags.add("destroyFabricSurfacesInReactInstanceManager");
            this.destroyFabricSurfacesInReactInstanceManagerCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableAlignItemsBaselineOnFabricIOS() {
        Boolean boolValueOf = this.enableAlignItemsBaselineOnFabricIOSCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.enableAlignItemsBaselineOnFabricIOS());
            this.accessedFeatureFlags.add("enableAlignItemsBaselineOnFabricIOS");
            this.enableAlignItemsBaselineOnFabricIOSCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableAndroidMixBlendModeProp() {
        Boolean boolValueOf = this.enableAndroidMixBlendModePropCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.enableAndroidMixBlendModeProp());
            this.accessedFeatureFlags.add("enableAndroidMixBlendModeProp");
            this.enableAndroidMixBlendModePropCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableBackgroundStyleApplicator() {
        Boolean boolValueOf = this.enableBackgroundStyleApplicatorCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.enableBackgroundStyleApplicator());
            this.accessedFeatureFlags.add("enableBackgroundStyleApplicator");
            this.enableBackgroundStyleApplicatorCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableCleanTextInputYogaNode() {
        Boolean boolValueOf = this.enableCleanTextInputYogaNodeCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.enableCleanTextInputYogaNode());
            this.accessedFeatureFlags.add("enableCleanTextInputYogaNode");
            this.enableCleanTextInputYogaNodeCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableEagerRootViewAttachment() {
        Boolean boolValueOf = this.enableEagerRootViewAttachmentCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.enableEagerRootViewAttachment());
            this.accessedFeatureFlags.add("enableEagerRootViewAttachment");
            this.enableEagerRootViewAttachmentCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    /* renamed from: enableEventEmitterRetentionDuringGesturesOnAndroid */
    public boolean get$fabricEnabled() {
        Boolean boolValueOf = this.enableEventEmitterRetentionDuringGesturesOnAndroidCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.get$fabricEnabled());
            this.accessedFeatureFlags.add("enableEventEmitterRetentionDuringGesturesOnAndroid");
            this.enableEventEmitterRetentionDuringGesturesOnAndroidCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableFabricLogs() {
        Boolean boolValueOf = this.enableFabricLogsCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.enableFabricLogs());
            this.accessedFeatureFlags.add("enableFabricLogs");
            this.enableFabricLogsCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableFabricRendererExclusively() {
        Boolean boolValueOf = this.enableFabricRendererExclusivelyCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.enableFabricRendererExclusively());
            this.accessedFeatureFlags.add("enableFabricRendererExclusively");
            this.enableFabricRendererExclusivelyCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableGranularShadowTreeStateReconciliation() {
        Boolean boolValueOf = this.enableGranularShadowTreeStateReconciliationCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.enableGranularShadowTreeStateReconciliation());
            this.accessedFeatureFlags.add("enableGranularShadowTreeStateReconciliation");
            this.enableGranularShadowTreeStateReconciliationCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableIOSViewClipToPaddingBox() {
        Boolean boolValueOf = this.enableIOSViewClipToPaddingBoxCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.enableIOSViewClipToPaddingBox());
            this.accessedFeatureFlags.add("enableIOSViewClipToPaddingBox");
            this.enableIOSViewClipToPaddingBoxCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableLayoutAnimationsOnIOS() {
        Boolean boolValueOf = this.enableLayoutAnimationsOnIOSCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.enableLayoutAnimationsOnIOS());
            this.accessedFeatureFlags.add("enableLayoutAnimationsOnIOS");
            this.enableLayoutAnimationsOnIOSCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableLongTaskAPI() {
        Boolean boolValueOf = this.enableLongTaskAPICache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.enableLongTaskAPI());
            this.accessedFeatureFlags.add("enableLongTaskAPI");
            this.enableLongTaskAPICache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableMicrotasks() {
        Boolean boolValueOf = this.enableMicrotasksCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.enableMicrotasks());
            this.accessedFeatureFlags.add("enableMicrotasks");
            this.enableMicrotasksCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enablePropsUpdateReconciliationAndroid() {
        Boolean boolValueOf = this.enablePropsUpdateReconciliationAndroidCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.enablePropsUpdateReconciliationAndroid());
            this.accessedFeatureFlags.add("enablePropsUpdateReconciliationAndroid");
            this.enablePropsUpdateReconciliationAndroidCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableReportEventPaintTime() {
        Boolean boolValueOf = this.enableReportEventPaintTimeCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.enableReportEventPaintTime());
            this.accessedFeatureFlags.add("enableReportEventPaintTime");
            this.enableReportEventPaintTimeCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableSynchronousStateUpdates() {
        Boolean boolValueOf = this.enableSynchronousStateUpdatesCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.enableSynchronousStateUpdates());
            this.accessedFeatureFlags.add("enableSynchronousStateUpdates");
            this.enableSynchronousStateUpdatesCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableUIConsistency() {
        Boolean boolValueOf = this.enableUIConsistencyCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.enableUIConsistency());
            this.accessedFeatureFlags.add("enableUIConsistency");
            this.enableUIConsistencyCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableViewRecycling() {
        Boolean boolValueOf = this.enableViewRecyclingCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.enableViewRecycling());
            this.accessedFeatureFlags.add("enableViewRecycling");
            this.enableViewRecyclingCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean excludeYogaFromRawProps() {
        Boolean boolValueOf = this.excludeYogaFromRawPropsCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.excludeYogaFromRawProps());
            this.accessedFeatureFlags.add("excludeYogaFromRawProps");
            this.excludeYogaFromRawPropsCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean fetchImagesInViewPreallocation() {
        Boolean boolValueOf = this.fetchImagesInViewPreallocationCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.fetchImagesInViewPreallocation());
            this.accessedFeatureFlags.add("fetchImagesInViewPreallocation");
            this.fetchImagesInViewPreallocationCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean fixIncorrectScrollViewStateUpdateOnAndroid() {
        Boolean boolValueOf = this.fixIncorrectScrollViewStateUpdateOnAndroidCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.fixIncorrectScrollViewStateUpdateOnAndroid());
            this.accessedFeatureFlags.add("fixIncorrectScrollViewStateUpdateOnAndroid");
            this.fixIncorrectScrollViewStateUpdateOnAndroidCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean fixMappingOfEventPrioritiesBetweenFabricAndReact() {
        Boolean boolValueOf = this.fixMappingOfEventPrioritiesBetweenFabricAndReactCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.fixMappingOfEventPrioritiesBetweenFabricAndReact());
            this.accessedFeatureFlags.add("fixMappingOfEventPrioritiesBetweenFabricAndReact");
            this.fixMappingOfEventPrioritiesBetweenFabricAndReactCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean fixMissedFabricStateUpdatesOnAndroid() {
        Boolean boolValueOf = this.fixMissedFabricStateUpdatesOnAndroidCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.fixMissedFabricStateUpdatesOnAndroid());
            this.accessedFeatureFlags.add("fixMissedFabricStateUpdatesOnAndroid");
            this.fixMissedFabricStateUpdatesOnAndroidCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean fixMountingCoordinatorReportedPendingTransactionsOnAndroid() {
        Boolean boolValueOf = this.fixMountingCoordinatorReportedPendingTransactionsOnAndroidCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.fixMountingCoordinatorReportedPendingTransactionsOnAndroid());
            this.accessedFeatureFlags.add("fixMountingCoordinatorReportedPendingTransactionsOnAndroid");
            this.fixMountingCoordinatorReportedPendingTransactionsOnAndroidCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean forceBatchingMountItemsOnAndroid() {
        Boolean boolValueOf = this.forceBatchingMountItemsOnAndroidCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.forceBatchingMountItemsOnAndroid());
            this.accessedFeatureFlags.add("forceBatchingMountItemsOnAndroid");
            this.forceBatchingMountItemsOnAndroidCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean fuseboxEnabledDebug() {
        Boolean boolValueOf = this.fuseboxEnabledDebugCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.fuseboxEnabledDebug());
            this.accessedFeatureFlags.add("fuseboxEnabledDebug");
            this.fuseboxEnabledDebugCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean fuseboxEnabledRelease() {
        Boolean boolValueOf = this.fuseboxEnabledReleaseCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.fuseboxEnabledRelease());
            this.accessedFeatureFlags.add("fuseboxEnabledRelease");
            this.fuseboxEnabledReleaseCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean initEagerTurboModulesOnNativeModulesQueueAndroid() {
        Boolean boolValueOf = this.initEagerTurboModulesOnNativeModulesQueueAndroidCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.initEagerTurboModulesOnNativeModulesQueueAndroid());
            this.accessedFeatureFlags.add("initEagerTurboModulesOnNativeModulesQueueAndroid");
            this.initEagerTurboModulesOnNativeModulesQueueAndroidCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean lazyAnimationCallbacks() {
        Boolean boolValueOf = this.lazyAnimationCallbacksCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.lazyAnimationCallbacks());
            this.accessedFeatureFlags.add("lazyAnimationCallbacks");
            this.lazyAnimationCallbacksCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean loadVectorDrawablesOnImages() {
        Boolean boolValueOf = this.loadVectorDrawablesOnImagesCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.loadVectorDrawablesOnImages());
            this.accessedFeatureFlags.add("loadVectorDrawablesOnImages");
            this.loadVectorDrawablesOnImagesCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean setAndroidLayoutDirection() {
        Boolean boolValueOf = this.setAndroidLayoutDirectionCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.setAndroidLayoutDirection());
            this.accessedFeatureFlags.add("setAndroidLayoutDirection");
            this.setAndroidLayoutDirectionCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean traceTurboModulePromiseRejectionsOnAndroid() {
        Boolean boolValueOf = this.traceTurboModulePromiseRejectionsOnAndroidCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.traceTurboModulePromiseRejectionsOnAndroid());
            this.accessedFeatureFlags.add("traceTurboModulePromiseRejectionsOnAndroid");
            this.traceTurboModulePromiseRejectionsOnAndroidCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean useFabricInterop() {
        Boolean boolValueOf = this.useFabricInteropCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.useFabricInterop());
            this.accessedFeatureFlags.add("useFabricInterop");
            this.useFabricInteropCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean useImmediateExecutorInAndroidBridgeless() {
        Boolean boolValueOf = this.useImmediateExecutorInAndroidBridgelessCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.useImmediateExecutorInAndroidBridgeless());
            this.accessedFeatureFlags.add("useImmediateExecutorInAndroidBridgeless");
            this.useImmediateExecutorInAndroidBridgelessCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean useModernRuntimeScheduler() {
        Boolean boolValueOf = this.useModernRuntimeSchedulerCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.useModernRuntimeScheduler());
            this.accessedFeatureFlags.add("useModernRuntimeScheduler");
            this.useModernRuntimeSchedulerCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean useNativeViewConfigsInBridgelessMode() {
        Boolean boolValueOf = this.useNativeViewConfigsInBridgelessModeCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.useNativeViewConfigsInBridgelessMode());
            this.accessedFeatureFlags.add("useNativeViewConfigsInBridgelessMode");
            this.useNativeViewConfigsInBridgelessModeCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean useNewReactImageViewBackgroundDrawing() {
        Boolean boolValueOf = this.useNewReactImageViewBackgroundDrawingCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.useNewReactImageViewBackgroundDrawing());
            this.accessedFeatureFlags.add("useNewReactImageViewBackgroundDrawing");
            this.useNewReactImageViewBackgroundDrawingCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean useOptimisedViewPreallocationOnAndroid() {
        Boolean boolValueOf = this.useOptimisedViewPreallocationOnAndroidCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.useOptimisedViewPreallocationOnAndroid());
            this.accessedFeatureFlags.add("useOptimisedViewPreallocationOnAndroid");
            this.useOptimisedViewPreallocationOnAndroidCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean useOptimizedEventBatchingOnAndroid() {
        Boolean boolValueOf = this.useOptimizedEventBatchingOnAndroidCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.useOptimizedEventBatchingOnAndroid());
            this.accessedFeatureFlags.add("useOptimizedEventBatchingOnAndroid");
            this.useOptimizedEventBatchingOnAndroidCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean useRuntimeShadowNodeReferenceUpdate() {
        Boolean boolValueOf = this.useRuntimeShadowNodeReferenceUpdateCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.useRuntimeShadowNodeReferenceUpdate());
            this.accessedFeatureFlags.add("useRuntimeShadowNodeReferenceUpdate");
            this.useRuntimeShadowNodeReferenceUpdateCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean useRuntimeShadowNodeReferenceUpdateOnLayout() {
        Boolean boolValueOf = this.useRuntimeShadowNodeReferenceUpdateOnLayoutCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.useRuntimeShadowNodeReferenceUpdateOnLayout());
            this.accessedFeatureFlags.add("useRuntimeShadowNodeReferenceUpdateOnLayout");
            this.useRuntimeShadowNodeReferenceUpdateOnLayoutCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean useStateAlignmentMechanism() {
        Boolean boolValueOf = this.useStateAlignmentMechanismCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.useStateAlignmentMechanism());
            this.accessedFeatureFlags.add("useStateAlignmentMechanism");
            this.useStateAlignmentMechanismCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean useTurboModuleInterop() {
        Boolean boolValueOf = this.useTurboModuleInteropCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.currentProvider.useTurboModuleInterop());
            this.accessedFeatureFlags.add("useTurboModuleInterop");
            this.useTurboModuleInteropCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsAccessor
    public void override(ReactNativeFeatureFlagsProvider provider) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        if (!this.accessedFeatureFlags.isEmpty()) {
            throw new IllegalStateException("Feature flags were accessed before being overridden: " + CollectionsKt.joinToString$default(this.accessedFeatureFlags, ", ", null, null, 0, null, new Function1<String, CharSequence>() { // from class: com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsLocalAccessor$override$accessedFeatureFlagsStr$1
                @Override // kotlin.jvm.functions.Function1
                public final CharSequence invoke(String it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return it;
                }
            }, 30, null));
        }
        this.currentProvider = provider;
    }
}
