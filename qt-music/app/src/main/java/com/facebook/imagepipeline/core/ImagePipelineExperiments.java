package com.facebook.imagepipeline.core;

import android.content.Context;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.Suppliers;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteStreams;
import com.facebook.common.webp.WebpBitmapFactory;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.platform.PlatformDecoderOptions;
import com.umeng.analytics.pro.f;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImagePipelineExperiments.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 U2\u00020\u0001:\u0004TUVWB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\bR\u0011\u0010\u0011\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\u0013\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0011\u0010\u0015\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\bR\u0011\u0010\u0017\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\bR\u0011\u0010\u0019\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\bR\u0011\u0010\u001b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\bR\u0011\u0010\u001d\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\bR\u0011\u0010\u001e\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\bR\u0011\u0010\u001f\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\bR\u0011\u0010 \u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b \u0010\bR\u0011\u0010!\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\bR\u0011\u0010\"\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\bR\u0011\u0010#\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\bR\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00060%¢\u0006\b\n\u0000\u001a\u0004\b$\u0010&R\u0011\u0010'\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\bR\u0011\u0010(\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\bR\u0011\u0010)\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\bR\u0011\u0010*\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\bR\u0011\u0010,\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u000eR\u0011\u0010.\u001a\u00020/¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0011\u00102\u001a\u000203¢\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u0011\u00106\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b7\u0010\bR\u0011\u00108\u001a\u000209¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u0011\u0010<\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b=\u0010\bR\u0011\u0010>\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b?\u0010\bR\u0011\u0010@\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\bA\u0010\bR\u0017\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00060%¢\u0006\b\n\u0000\u001a\u0004\bC\u0010&R\u0011\u0010D\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\bE\u0010\u000eR\u0011\u0010F\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\bG\u0010\bR\u0011\u0010H\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\bI\u0010\bR\u0011\u0010J\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\bK\u0010\bR\u0013\u0010L\u001a\u0004\u0018\u00010M¢\u0006\b\n\u0000\u001a\u0004\bN\u0010OR\u0013\u0010P\u001a\u0004\u0018\u00010Q¢\u0006\b\n\u0000\u001a\u0004\bR\u0010S¨\u0006X"}, d2 = {"Lcom/facebook/imagepipeline/core/ImagePipelineExperiments;", "", "builder", "Lcom/facebook/imagepipeline/core/ImagePipelineExperiments$Builder;", "(Lcom/facebook/imagepipeline/core/ImagePipelineExperiments$Builder;)V", "allowDelay", "", "getAllowDelay", "()Z", "allowProgressiveOnPrefetch", "getAllowProgressiveOnPrefetch", "animationRenderFpsLimit", "", "getAnimationRenderFpsLimit", "()I", "bitmapPrepareToDrawForPrefetch", "getBitmapPrepareToDrawForPrefetch", "bitmapPrepareToDrawMaxSizeBytes", "getBitmapPrepareToDrawMaxSizeBytes", "bitmapPrepareToDrawMinSizeBytes", "getBitmapPrepareToDrawMinSizeBytes", "cancelDecodeOnCacheMiss", "getCancelDecodeOnCacheMiss", "downsampleIfLargeBitmap", "getDownsampleIfLargeBitmap", "downscaleFrameToDrawableDimensions", "getDownscaleFrameToDrawableDimensions", "handOffOnUiThreadOnly", "getHandOffOnUiThreadOnly", "isDecodeCancellationEnabled", "isDiskCacheProbingEnabled", "isEncodedCacheEnabled", "isEncodedMemoryCacheProbingEnabled", "isEnsureTranscoderLibraryLoaded", "isExperimentalThreadHandoffQueueEnabled", "isGingerbreadDecoderEnabled", "isLazyDataSource", "Lcom/facebook/common/internal/Supplier;", "()Lcom/facebook/common/internal/Supplier;", "isNativeCodeDisabled", "isPartialImageCachingEnabled", "isWebpSupportEnabled", "keepCancelledFetchAsLowPriority", "getKeepCancelledFetchAsLowPriority", "maxBitmapSize", "getMaxBitmapSize", "memoryType", "", "getMemoryType", "()J", "platformDecoderOptions", "Lcom/facebook/imagepipeline/platform/PlatformDecoderOptions;", "getPlatformDecoderOptions", "()Lcom/facebook/imagepipeline/platform/PlatformDecoderOptions;", "prefetchShortcutEnabled", "getPrefetchShortcutEnabled", "producerFactoryMethod", "Lcom/facebook/imagepipeline/core/ImagePipelineExperiments$ProducerFactoryMethod;", "getProducerFactoryMethod", "()Lcom/facebook/imagepipeline/core/ImagePipelineExperiments$ProducerFactoryMethod;", "shouldIgnoreCacheSizeMismatch", "getShouldIgnoreCacheSizeMismatch", "shouldStoreCacheEntrySize", "getShouldStoreCacheEntrySize", "shouldUseDecodingBufferHelper", "getShouldUseDecodingBufferHelper", "suppressBitmapPrefetchingSupplier", "getSuppressBitmapPrefetchingSupplier", "trackedKeysSize", "getTrackedKeysSize", "useBalancedAnimationStrategy", "getUseBalancedAnimationStrategy", "useBitmapPrepareToDraw", "getUseBitmapPrepareToDraw", "useDownsamplingRatioForResizing", "getUseDownsamplingRatioForResizing", "webpBitmapFactory", "Lcom/facebook/common/webp/WebpBitmapFactory;", "getWebpBitmapFactory", "()Lcom/facebook/common/webp/WebpBitmapFactory;", "webpErrorLogger", "Lcom/facebook/common/webp/WebpBitmapFactory$WebpErrorLogger;", "getWebpErrorLogger", "()Lcom/facebook/common/webp/WebpBitmapFactory$WebpErrorLogger;", "Builder", "Companion", "DefaultProducerFactoryMethod", "ProducerFactoryMethod", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ImagePipelineExperiments {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final boolean allowDelay;
    private final boolean allowProgressiveOnPrefetch;
    private final int animationRenderFpsLimit;
    private final boolean bitmapPrepareToDrawForPrefetch;
    private final int bitmapPrepareToDrawMaxSizeBytes;
    private final int bitmapPrepareToDrawMinSizeBytes;
    private final boolean cancelDecodeOnCacheMiss;
    private final boolean downsampleIfLargeBitmap;
    private final boolean downscaleFrameToDrawableDimensions;
    private final boolean handOffOnUiThreadOnly;
    private final boolean isDecodeCancellationEnabled;
    private final boolean isDiskCacheProbingEnabled;
    private final boolean isEncodedCacheEnabled;
    private final boolean isEncodedMemoryCacheProbingEnabled;
    private final boolean isEnsureTranscoderLibraryLoaded;
    private final boolean isExperimentalThreadHandoffQueueEnabled;
    private final boolean isGingerbreadDecoderEnabled;
    private final Supplier<Boolean> isLazyDataSource;
    private final boolean isNativeCodeDisabled;
    private final boolean isPartialImageCachingEnabled;
    private final boolean isWebpSupportEnabled;
    private final boolean keepCancelledFetchAsLowPriority;
    private final int maxBitmapSize;
    private final long memoryType;
    private final PlatformDecoderOptions platformDecoderOptions;
    private final boolean prefetchShortcutEnabled;
    private final ProducerFactoryMethod producerFactoryMethod;
    private final boolean shouldIgnoreCacheSizeMismatch;
    private final boolean shouldStoreCacheEntrySize;
    private final boolean shouldUseDecodingBufferHelper;
    private final Supplier<Boolean> suppressBitmapPrefetchingSupplier;
    private final int trackedKeysSize;
    private final boolean useBalancedAnimationStrategy;
    private final boolean useBitmapPrepareToDraw;
    private final boolean useDownsamplingRatioForResizing;
    private final WebpBitmapFactory webpBitmapFactory;
    private final WebpBitmapFactory.WebpErrorLogger webpErrorLogger;

    /* compiled from: ImagePipelineExperiments.kt */
    @Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001Jö\u0001\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0016\u0010\u0017\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u00182\u0016\u0010\u001b\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u00182\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001e2\u0014\u0010 \u001a\u0010\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u001e\u0018\u00010!2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020(2\u0006\u0010*\u001a\u00020\u000f2\u0006\u0010+\u001a\u00020(2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u000f2\u0006\u0010/\u001a\u00020(H&¨\u00060"}, d2 = {"Lcom/facebook/imagepipeline/core/ImagePipelineExperiments$ProducerFactoryMethod;", "", "createProducerFactory", "Lcom/facebook/imagepipeline/core/ProducerFactory;", f.X, "Landroid/content/Context;", "byteArrayPool", "Lcom/facebook/common/memory/ByteArrayPool;", "imageDecoder", "Lcom/facebook/imagepipeline/decoder/ImageDecoder;", "progressiveJpegConfig", "Lcom/facebook/imagepipeline/decoder/ProgressiveJpegConfig;", "downsampleMode", "Lcom/facebook/imagepipeline/core/DownsampleMode;", "resizeAndRotateEnabledForNetwork", "", "decodeCancellationEnabled", "executorSupplier", "Lcom/facebook/imagepipeline/core/ExecutorSupplier;", "pooledByteBufferFactory", "Lcom/facebook/common/memory/PooledByteBufferFactory;", "pooledByteStreams", "Lcom/facebook/common/memory/PooledByteStreams;", "bitmapMemoryCache", "Lcom/facebook/imagepipeline/cache/MemoryCache;", "Lcom/facebook/cache/common/CacheKey;", "Lcom/facebook/imagepipeline/image/CloseableImage;", "encodedMemoryCache", "Lcom/facebook/common/memory/PooledByteBuffer;", "defaultBufferedDiskCache", "Lcom/facebook/imagepipeline/cache/BufferedDiskCache;", "smallImageBufferedDiskCache", "dynamicBufferedDiskCaches", "", "", "cacheKeyFactory", "Lcom/facebook/imagepipeline/cache/CacheKeyFactory;", "platformBitmapFactory", "Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;", "bitmapPrepareToDrawMinSizeBytes", "", "bitmapPrepareToDrawMaxSizeBytes", "bitmapPrepareToDrawForPrefetch", "maxBitmapSize", "closeableReferenceFactory", "Lcom/facebook/imagepipeline/core/CloseableReferenceFactory;", "keepCancelledFetchAsLowPriority", "trackedKeysSize", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface ProducerFactoryMethod {
        ProducerFactory createProducerFactory(Context context, ByteArrayPool byteArrayPool, ImageDecoder imageDecoder, ProgressiveJpegConfig progressiveJpegConfig, DownsampleMode downsampleMode, boolean resizeAndRotateEnabledForNetwork, boolean decodeCancellationEnabled, ExecutorSupplier executorSupplier, PooledByteBufferFactory pooledByteBufferFactory, PooledByteStreams pooledByteStreams, MemoryCache<CacheKey, CloseableImage> bitmapMemoryCache, MemoryCache<CacheKey, PooledByteBuffer> encodedMemoryCache, BufferedDiskCache defaultBufferedDiskCache, BufferedDiskCache smallImageBufferedDiskCache, Map<String, BufferedDiskCache> dynamicBufferedDiskCaches, CacheKeyFactory cacheKeyFactory, PlatformBitmapFactory platformBitmapFactory, int bitmapPrepareToDrawMinSizeBytes, int bitmapPrepareToDrawMaxSizeBytes, boolean bitmapPrepareToDrawForPrefetch, int maxBitmapSize, CloseableReferenceFactory closeableReferenceFactory, boolean keepCancelledFetchAsLowPriority, int trackedKeysSize);
    }

    public /* synthetic */ ImagePipelineExperiments(Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder);
    }

    @JvmStatic
    public static final Builder newBuilder(ImagePipelineConfig.Builder builder) {
        return INSTANCE.newBuilder(builder);
    }

    private ImagePipelineExperiments(Builder builder) {
        this.isWebpSupportEnabled = builder.webpSupportEnabled;
        this.webpErrorLogger = builder.webpErrorLogger;
        this.isDecodeCancellationEnabled = builder.decodeCancellationEnabled;
        this.webpBitmapFactory = builder.webpBitmapFactory;
        this.useDownsamplingRatioForResizing = builder.useDownsamplingRatioForResizing;
        this.useBitmapPrepareToDraw = builder.useBitmapPrepareToDraw;
        this.useBalancedAnimationStrategy = builder.useBalancedAnimationStrategy;
        this.bitmapPrepareToDrawMinSizeBytes = builder.bitmapPrepareToDrawMinSizeBytes;
        this.bitmapPrepareToDrawMaxSizeBytes = builder.bitmapPrepareToDrawMaxSizeBytes;
        this.bitmapPrepareToDrawForPrefetch = builder.bitmapPrepareToDrawForPrefetch;
        this.maxBitmapSize = builder.maxBitmapSize;
        this.isNativeCodeDisabled = builder.nativeCodeDisabled;
        this.isPartialImageCachingEnabled = builder.isPartialImageCachingEnabled;
        DefaultProducerFactoryMethod defaultProducerFactoryMethod = builder.producerFactoryMethod;
        this.producerFactoryMethod = defaultProducerFactoryMethod == null ? new DefaultProducerFactoryMethod() : defaultProducerFactoryMethod;
        Supplier<Boolean> BOOLEAN_FALSE = builder.lazyDataSource;
        if (BOOLEAN_FALSE == null) {
            BOOLEAN_FALSE = Suppliers.BOOLEAN_FALSE;
            Intrinsics.checkNotNullExpressionValue(BOOLEAN_FALSE, "BOOLEAN_FALSE");
        }
        this.isLazyDataSource = BOOLEAN_FALSE;
        this.isGingerbreadDecoderEnabled = builder.gingerbreadDecoderEnabled;
        this.downscaleFrameToDrawableDimensions = builder.downscaleFrameToDrawableDimensions;
        this.suppressBitmapPrefetchingSupplier = builder.suppressBitmapPrefetchingSupplier;
        this.isExperimentalThreadHandoffQueueEnabled = builder.experimentalThreadHandoffQueueEnabled;
        this.memoryType = builder.memoryType;
        this.keepCancelledFetchAsLowPriority = builder.keepCancelledFetchAsLowPriority;
        this.downsampleIfLargeBitmap = builder.downsampleIfLargeBitmap;
        this.isEncodedCacheEnabled = builder.encodedCacheEnabled;
        this.isEnsureTranscoderLibraryLoaded = builder.ensureTranscoderLibraryLoaded;
        this.isEncodedMemoryCacheProbingEnabled = builder.isEncodedMemoryCacheProbingEnabled;
        this.isDiskCacheProbingEnabled = builder.isDiskCacheProbingEnabled;
        this.trackedKeysSize = builder.trackedKeysSize;
        this.allowProgressiveOnPrefetch = builder.allowProgressiveOnPrefetch;
        this.animationRenderFpsLimit = builder.animationRenderFpsLimit;
        this.allowDelay = builder.allowDelay;
        this.handOffOnUiThreadOnly = builder.handOffOnUiThreadOnly;
        this.shouldStoreCacheEntrySize = builder.shouldStoreCacheEntrySize;
        this.shouldIgnoreCacheSizeMismatch = builder.shouldIgnoreCacheSizeMismatch;
        this.shouldUseDecodingBufferHelper = builder.shouldUseDecodingBufferHelper;
        this.cancelDecodeOnCacheMiss = builder.cancelDecodeOnCacheMiss;
        this.prefetchShortcutEnabled = builder.prefetchShortcutEnabled;
        this.platformDecoderOptions = builder.platformDecoderOptions;
    }

    /* renamed from: isWebpSupportEnabled, reason: from getter */
    public final boolean getIsWebpSupportEnabled() {
        return this.isWebpSupportEnabled;
    }

    public final WebpBitmapFactory.WebpErrorLogger getWebpErrorLogger() {
        return this.webpErrorLogger;
    }

    /* renamed from: isDecodeCancellationEnabled, reason: from getter */
    public final boolean getIsDecodeCancellationEnabled() {
        return this.isDecodeCancellationEnabled;
    }

    public final WebpBitmapFactory getWebpBitmapFactory() {
        return this.webpBitmapFactory;
    }

    public final boolean getUseDownsamplingRatioForResizing() {
        return this.useDownsamplingRatioForResizing;
    }

    public final boolean getUseBitmapPrepareToDraw() {
        return this.useBitmapPrepareToDraw;
    }

    public final boolean getUseBalancedAnimationStrategy() {
        return this.useBalancedAnimationStrategy;
    }

    public final int getBitmapPrepareToDrawMinSizeBytes() {
        return this.bitmapPrepareToDrawMinSizeBytes;
    }

    public final int getBitmapPrepareToDrawMaxSizeBytes() {
        return this.bitmapPrepareToDrawMaxSizeBytes;
    }

    public final boolean getBitmapPrepareToDrawForPrefetch() {
        return this.bitmapPrepareToDrawForPrefetch;
    }

    public final int getMaxBitmapSize() {
        return this.maxBitmapSize;
    }

    /* renamed from: isNativeCodeDisabled, reason: from getter */
    public final boolean getIsNativeCodeDisabled() {
        return this.isNativeCodeDisabled;
    }

    /* renamed from: isPartialImageCachingEnabled, reason: from getter */
    public final boolean getIsPartialImageCachingEnabled() {
        return this.isPartialImageCachingEnabled;
    }

    public final ProducerFactoryMethod getProducerFactoryMethod() {
        return this.producerFactoryMethod;
    }

    public final Supplier<Boolean> isLazyDataSource() {
        return this.isLazyDataSource;
    }

    /* renamed from: isGingerbreadDecoderEnabled, reason: from getter */
    public final boolean getIsGingerbreadDecoderEnabled() {
        return this.isGingerbreadDecoderEnabled;
    }

    public final boolean getDownscaleFrameToDrawableDimensions() {
        return this.downscaleFrameToDrawableDimensions;
    }

    public final Supplier<Boolean> getSuppressBitmapPrefetchingSupplier() {
        return this.suppressBitmapPrefetchingSupplier;
    }

    /* renamed from: isExperimentalThreadHandoffQueueEnabled, reason: from getter */
    public final boolean getIsExperimentalThreadHandoffQueueEnabled() {
        return this.isExperimentalThreadHandoffQueueEnabled;
    }

    public final long getMemoryType() {
        return this.memoryType;
    }

    public final boolean getKeepCancelledFetchAsLowPriority() {
        return this.keepCancelledFetchAsLowPriority;
    }

    public final boolean getDownsampleIfLargeBitmap() {
        return this.downsampleIfLargeBitmap;
    }

    /* renamed from: isEncodedCacheEnabled, reason: from getter */
    public final boolean getIsEncodedCacheEnabled() {
        return this.isEncodedCacheEnabled;
    }

    /* renamed from: isEnsureTranscoderLibraryLoaded, reason: from getter */
    public final boolean getIsEnsureTranscoderLibraryLoaded() {
        return this.isEnsureTranscoderLibraryLoaded;
    }

    /* renamed from: isEncodedMemoryCacheProbingEnabled, reason: from getter */
    public final boolean getIsEncodedMemoryCacheProbingEnabled() {
        return this.isEncodedMemoryCacheProbingEnabled;
    }

    /* renamed from: isDiskCacheProbingEnabled, reason: from getter */
    public final boolean getIsDiskCacheProbingEnabled() {
        return this.isDiskCacheProbingEnabled;
    }

    public final int getTrackedKeysSize() {
        return this.trackedKeysSize;
    }

    public final boolean getAllowDelay() {
        return this.allowDelay;
    }

    public final boolean getHandOffOnUiThreadOnly() {
        return this.handOffOnUiThreadOnly;
    }

    public final boolean getShouldStoreCacheEntrySize() {
        return this.shouldStoreCacheEntrySize;
    }

    public final boolean getShouldIgnoreCacheSizeMismatch() {
        return this.shouldIgnoreCacheSizeMismatch;
    }

    public final boolean getShouldUseDecodingBufferHelper() {
        return this.shouldUseDecodingBufferHelper;
    }

    public final boolean getAllowProgressiveOnPrefetch() {
        return this.allowProgressiveOnPrefetch;
    }

    public final boolean getCancelDecodeOnCacheMiss() {
        return this.cancelDecodeOnCacheMiss;
    }

    public final int getAnimationRenderFpsLimit() {
        return this.animationRenderFpsLimit;
    }

    public final boolean getPrefetchShortcutEnabled() {
        return this.prefetchShortcutEnabled;
    }

    public final PlatformDecoderOptions getPlatformDecoderOptions() {
        return this.platformDecoderOptions;
    }

    /* compiled from: ImagePipelineExperiments.kt */
    @Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b(\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u00103\u001a\u00020\u00002\f\u00104\u001a\b\u0012\u0004\u0012\u00020605H\u0002J\u0006\u00107\u001a\u000208J\u000e\u00109\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010:\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010;\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010<\u001a\u00020\u00002\u0006\u0010+\u001a\u00020\u0006J&\u0010=\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\u00062\u0006\u0010>\u001a\u00020\t2\u0006\u0010?\u001a\u00020\t2\u0006\u0010@\u001a\u00020\u0006J\u000e\u0010A\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u0006J\u000e\u0010B\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0006J\u000e\u0010C\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0006J\u000e\u0010D\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0006J\u000e\u0010E\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0006J\u000e\u0010F\u001a\u00020\u00002\u0006\u0010G\u001a\u00020\u001eJ\u000e\u0010H\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0006J\u000e\u0010I\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0006J\u000e\u0010J\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0006J\u000e\u0010K\u001a\u00020\u00002\u0006\u0010%\u001a\u00020\u0006J\u000e\u0010L\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0006J\u000e\u0010M\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0006J\u000e\u0010N\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0006J\u0016\u0010O\u001a\u00020\u00002\u000e\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u001bJ\u000e\u0010P\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\tJ\u000e\u0010Q\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u0006J\u000e\u0010R\u001a\u00020\u00002\u0006\u0010S\u001a\u00020\u0006J\u000e\u0010T\u001a\u00020\u00002\u0006\u0010 \u001a\u00020!J\u000e\u0010U\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020\u0006J\u0010\u0010V\u001a\u00020\u00002\b\u0010#\u001a\u0004\u0018\u00010$J\u000e\u0010W\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0006J\u000e\u0010X\u001a\u00020\u00002\u0006\u0010'\u001a\u00020\u0006J\u000e\u0010Y\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0006J\u0014\u0010Z\u001a\u00020\u00002\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00060\u001bJ\u000e\u0010[\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\tJ\u000e\u0010\\\u001a\u00020\u00002\u0006\u0010-\u001a\u00020\u0006J\u0010\u0010]\u001a\u00020\u00002\b\u0010.\u001a\u0004\u0018\u00010/J\u0010\u0010^\u001a\u00020\u00002\b\u00100\u001a\u0004\u0018\u000101J\u000e\u0010_\u001a\u00020\u00002\u0006\u00102\u001a\u00020\u0006J\u0006\u0010'\u001a\u00020\u0006R\u0012\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00020\t8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u00020\t8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u00020\t8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0011\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0013\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0014\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0015\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0016\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0017\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0018\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0019\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u001b8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001c\u001a\u00020\t8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001d\u001a\u00020\u001e8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001f\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010 \u001a\u00020!8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\"\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0014\u0010#\u001a\u0004\u0018\u00010$8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010%\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010&\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010'\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R \u0010(\u001a\u0010\u0012\f\u0012\n )*\u0004\u0018\u00010\u00060\u00060\u001b8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010*\u001a\u00020\t8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010+\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010,\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010-\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0014\u0010.\u001a\u0004\u0018\u00010/8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0014\u00100\u001a\u0004\u0018\u0001018\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u00102\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006`"}, d2 = {"Lcom/facebook/imagepipeline/core/ImagePipelineExperiments$Builder;", "", "configBuilder", "Lcom/facebook/imagepipeline/core/ImagePipelineConfig$Builder;", "(Lcom/facebook/imagepipeline/core/ImagePipelineConfig$Builder;)V", "allowDelay", "", "allowProgressiveOnPrefetch", "animationRenderFpsLimit", "", "bitmapPrepareToDrawForPrefetch", "bitmapPrepareToDrawMaxSizeBytes", "bitmapPrepareToDrawMinSizeBytes", "cancelDecodeOnCacheMiss", "decodeCancellationEnabled", "downsampleIfLargeBitmap", "downscaleFrameToDrawableDimensions", "encodedCacheEnabled", "ensureTranscoderLibraryLoaded", "experimentalThreadHandoffQueueEnabled", "gingerbreadDecoderEnabled", "handOffOnUiThreadOnly", "isDiskCacheProbingEnabled", "isEncodedMemoryCacheProbingEnabled", "isPartialImageCachingEnabled", "keepCancelledFetchAsLowPriority", "lazyDataSource", "Lcom/facebook/common/internal/Supplier;", "maxBitmapSize", "memoryType", "", "nativeCodeDisabled", "platformDecoderOptions", "Lcom/facebook/imagepipeline/platform/PlatformDecoderOptions;", "prefetchShortcutEnabled", "producerFactoryMethod", "Lcom/facebook/imagepipeline/core/ImagePipelineExperiments$ProducerFactoryMethod;", "shouldIgnoreCacheSizeMismatch", "shouldStoreCacheEntrySize", "shouldUseDecodingBufferHelper", "suppressBitmapPrefetchingSupplier", "kotlin.jvm.PlatformType", "trackedKeysSize", "useBalancedAnimationStrategy", "useBitmapPrepareToDraw", "useDownsamplingRatioForResizing", "webpBitmapFactory", "Lcom/facebook/common/webp/WebpBitmapFactory;", "webpErrorLogger", "Lcom/facebook/common/webp/WebpBitmapFactory$WebpErrorLogger;", "webpSupportEnabled", "asBuilder", "block", "Lkotlin/Function0;", "", "build", "Lcom/facebook/imagepipeline/core/ImagePipelineExperiments;", "setAllowDelay", "setAllowProgressiveOnPrefetch", "setAnimationRenderFpsLimit", "setBalancedAnimationStrategy", "setBitmapPrepareToDraw", "minBitmapSizeBytes", "maxBitmapSizeBytes", "preparePrefetch", "setCancelDecodeOnCacheMiss", "setDecodeCancellationEnabled", "setDownsampleIfLargeBitmap", "setEncodedCacheEnabled", "setEnsureTranscoderLibraryLoaded", "setExperimentalMemoryType", "MemoryType", "setExperimentalThreadHandoffQueueEnabled", "setGingerbreadDecoderEnabled", "setHandOffOnUiThreadOnly", "setIgnoreCacheSizeMismatch", "setIsDiskCacheProbingEnabled", "setIsEncodedMemoryCacheProbingEnabled", "setKeepCancelledFetchAsLowPriority", "setLazyDataSource", "setMaxBitmapSize", "setNativeCodeDisabled", "setPartialImageCachingEnabled", "partialImageCachingEnabled", "setPlatformDecoderOptions", "setPrefetchShortcutEnabled", "setProducerFactoryMethod", "setShouldDownscaleFrameToDrawableDimensions", "setShouldUseDecodingBufferHelper", "setStoreCacheEntrySize", "setSuppressBitmapPrefetchingSupplier", "setTrackedKeysSize", "setUseDownsampligRatioForResizing", "setWebpBitmapFactory", "setWebpErrorLogger", "setWebpSupportEnabled", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Builder {
        public boolean allowDelay;
        public boolean allowProgressiveOnPrefetch;
        public int animationRenderFpsLimit;
        public boolean bitmapPrepareToDrawForPrefetch;
        public int bitmapPrepareToDrawMaxSizeBytes;
        public int bitmapPrepareToDrawMinSizeBytes;
        public boolean cancelDecodeOnCacheMiss;
        private final ImagePipelineConfig.Builder configBuilder;
        public boolean decodeCancellationEnabled;
        public boolean downsampleIfLargeBitmap;
        public boolean downscaleFrameToDrawableDimensions;
        public boolean encodedCacheEnabled;
        public boolean ensureTranscoderLibraryLoaded;
        public boolean experimentalThreadHandoffQueueEnabled;
        public boolean gingerbreadDecoderEnabled;
        public boolean handOffOnUiThreadOnly;
        public boolean isDiskCacheProbingEnabled;
        public boolean isEncodedMemoryCacheProbingEnabled;
        public boolean isPartialImageCachingEnabled;
        public boolean keepCancelledFetchAsLowPriority;
        public Supplier<Boolean> lazyDataSource;
        public int maxBitmapSize;
        public long memoryType;
        public boolean nativeCodeDisabled;
        public PlatformDecoderOptions platformDecoderOptions;
        public boolean prefetchShortcutEnabled;
        public ProducerFactoryMethod producerFactoryMethod;
        public boolean shouldIgnoreCacheSizeMismatch;
        public boolean shouldStoreCacheEntrySize;
        public boolean shouldUseDecodingBufferHelper;
        public Supplier<Boolean> suppressBitmapPrefetchingSupplier;
        public int trackedKeysSize;
        public boolean useBalancedAnimationStrategy;
        public boolean useBitmapPrepareToDraw;
        public boolean useDownsamplingRatioForResizing;
        public WebpBitmapFactory webpBitmapFactory;
        public WebpBitmapFactory.WebpErrorLogger webpErrorLogger;
        public boolean webpSupportEnabled;

        public Builder(ImagePipelineConfig.Builder configBuilder) {
            Intrinsics.checkNotNullParameter(configBuilder, "configBuilder");
            this.configBuilder = configBuilder;
            this.maxBitmapSize = 2048;
            Supplier<Boolean> supplierOf = Suppliers.of(false);
            Intrinsics.checkNotNullExpressionValue(supplierOf, "of(false)");
            this.suppressBitmapPrefetchingSupplier = supplierOf;
            this.encodedCacheEnabled = true;
            this.ensureTranscoderLibraryLoaded = true;
            this.trackedKeysSize = 20;
            this.animationRenderFpsLimit = 30;
            this.platformDecoderOptions = new PlatformDecoderOptions(false, false, 3, null);
        }

        private final Builder asBuilder(Function0<Unit> block) {
            block.invoke();
            return this;
        }

        public final Builder setHandOffOnUiThreadOnly(final boolean handOffOnUiThreadOnly) {
            return asBuilder(new Function0<Unit>() { // from class: com.facebook.imagepipeline.core.ImagePipelineExperiments$Builder$setHandOffOnUiThreadOnly$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.handOffOnUiThreadOnly = handOffOnUiThreadOnly;
                }
            });
        }

        public final Builder setStoreCacheEntrySize(final boolean shouldStoreCacheEntrySize) {
            return asBuilder(new Function0<Unit>() { // from class: com.facebook.imagepipeline.core.ImagePipelineExperiments$Builder$setStoreCacheEntrySize$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.shouldStoreCacheEntrySize = shouldStoreCacheEntrySize;
                }
            });
        }

        public final Builder setIgnoreCacheSizeMismatch(final boolean shouldIgnoreCacheSizeMismatch) {
            return asBuilder(new Function0<Unit>() { // from class: com.facebook.imagepipeline.core.ImagePipelineExperiments$Builder$setIgnoreCacheSizeMismatch$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.shouldIgnoreCacheSizeMismatch = shouldIgnoreCacheSizeMismatch;
                }
            });
        }

        public final Builder setWebpSupportEnabled(final boolean webpSupportEnabled) {
            return asBuilder(new Function0<Unit>() { // from class: com.facebook.imagepipeline.core.ImagePipelineExperiments$Builder$setWebpSupportEnabled$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.webpSupportEnabled = webpSupportEnabled;
                }
            });
        }

        public final Builder setPrefetchShortcutEnabled(final boolean prefetchShortcutEnabled) {
            return asBuilder(new Function0<Unit>() { // from class: com.facebook.imagepipeline.core.ImagePipelineExperiments$Builder$setPrefetchShortcutEnabled$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.prefetchShortcutEnabled = prefetchShortcutEnabled;
                }
            });
        }

        /* renamed from: shouldUseDecodingBufferHelper, reason: from getter */
        public final boolean getShouldUseDecodingBufferHelper() {
            return this.shouldUseDecodingBufferHelper;
        }

        public final Builder setShouldUseDecodingBufferHelper(final boolean shouldUseDecodingBufferHelper) {
            return asBuilder(new Function0<Unit>() { // from class: com.facebook.imagepipeline.core.ImagePipelineExperiments$Builder$setShouldUseDecodingBufferHelper$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.shouldUseDecodingBufferHelper = shouldUseDecodingBufferHelper;
                }
            });
        }

        public final Builder setUseDownsampligRatioForResizing(final boolean useDownsamplingRatioForResizing) {
            return asBuilder(new Function0<Unit>() { // from class: com.facebook.imagepipeline.core.ImagePipelineExperiments$Builder$setUseDownsampligRatioForResizing$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.useDownsamplingRatioForResizing = useDownsamplingRatioForResizing;
                }
            });
        }

        public final Builder setPartialImageCachingEnabled(final boolean partialImageCachingEnabled) {
            return asBuilder(new Function0<Unit>() { // from class: com.facebook.imagepipeline.core.ImagePipelineExperiments$Builder$setPartialImageCachingEnabled$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.isPartialImageCachingEnabled = partialImageCachingEnabled;
                }
            });
        }

        public final Builder setDecodeCancellationEnabled(final boolean decodeCancellationEnabled) {
            return asBuilder(new Function0<Unit>() { // from class: com.facebook.imagepipeline.core.ImagePipelineExperiments$Builder$setDecodeCancellationEnabled$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.decodeCancellationEnabled = decodeCancellationEnabled;
                }
            });
        }

        public final Builder setWebpErrorLogger(final WebpBitmapFactory.WebpErrorLogger webpErrorLogger) {
            return asBuilder(new Function0<Unit>() { // from class: com.facebook.imagepipeline.core.ImagePipelineExperiments$Builder$setWebpErrorLogger$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.webpErrorLogger = webpErrorLogger;
                }
            });
        }

        public final Builder setWebpBitmapFactory(final WebpBitmapFactory webpBitmapFactory) {
            return asBuilder(new Function0<Unit>() { // from class: com.facebook.imagepipeline.core.ImagePipelineExperiments$Builder$setWebpBitmapFactory$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.webpBitmapFactory = webpBitmapFactory;
                }
            });
        }

        public final Builder setBitmapPrepareToDraw(final boolean useBitmapPrepareToDraw, final int minBitmapSizeBytes, final int maxBitmapSizeBytes, final boolean preparePrefetch) {
            return asBuilder(new Function0<Unit>() { // from class: com.facebook.imagepipeline.core.ImagePipelineExperiments$Builder$setBitmapPrepareToDraw$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.useBitmapPrepareToDraw = useBitmapPrepareToDraw;
                    this.this$0.bitmapPrepareToDrawMinSizeBytes = minBitmapSizeBytes;
                    this.this$0.bitmapPrepareToDrawMaxSizeBytes = maxBitmapSizeBytes;
                    this.this$0.bitmapPrepareToDrawForPrefetch = preparePrefetch;
                }
            });
        }

        public final Builder setBalancedAnimationStrategy(final boolean useBalancedAnimationStrategy) {
            return asBuilder(new Function0<Unit>() { // from class: com.facebook.imagepipeline.core.ImagePipelineExperiments$Builder$setBalancedAnimationStrategy$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.useBalancedAnimationStrategy = useBalancedAnimationStrategy;
                }
            });
        }

        public final Builder setMaxBitmapSize(final int maxBitmapSize) {
            return asBuilder(new Function0<Unit>() { // from class: com.facebook.imagepipeline.core.ImagePipelineExperiments$Builder$setMaxBitmapSize$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.maxBitmapSize = maxBitmapSize;
                }
            });
        }

        public final Builder setNativeCodeDisabled(final boolean nativeCodeDisabled) {
            return asBuilder(new Function0<Unit>() { // from class: com.facebook.imagepipeline.core.ImagePipelineExperiments$Builder$setNativeCodeDisabled$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.nativeCodeDisabled = nativeCodeDisabled;
                }
            });
        }

        public final Builder setProducerFactoryMethod(final ProducerFactoryMethod producerFactoryMethod) {
            return asBuilder(new Function0<Unit>() { // from class: com.facebook.imagepipeline.core.ImagePipelineExperiments$Builder$setProducerFactoryMethod$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.producerFactoryMethod = producerFactoryMethod;
                }
            });
        }

        public final Builder setLazyDataSource(final Supplier<Boolean> lazyDataSource) {
            return asBuilder(new Function0<Unit>() { // from class: com.facebook.imagepipeline.core.ImagePipelineExperiments$Builder$setLazyDataSource$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.lazyDataSource = lazyDataSource;
                }
            });
        }

        public final Builder setGingerbreadDecoderEnabled(final boolean gingerbreadDecoderEnabled) {
            return asBuilder(new Function0<Unit>() { // from class: com.facebook.imagepipeline.core.ImagePipelineExperiments$Builder$setGingerbreadDecoderEnabled$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.gingerbreadDecoderEnabled = gingerbreadDecoderEnabled;
                }
            });
        }

        public final Builder setShouldDownscaleFrameToDrawableDimensions(final boolean downscaleFrameToDrawableDimensions) {
            return asBuilder(new Function0<Unit>() { // from class: com.facebook.imagepipeline.core.ImagePipelineExperiments$Builder$setShouldDownscaleFrameToDrawableDimensions$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.downscaleFrameToDrawableDimensions = downscaleFrameToDrawableDimensions;
                }
            });
        }

        public final Builder setSuppressBitmapPrefetchingSupplier(final Supplier<Boolean> suppressBitmapPrefetchingSupplier) {
            Intrinsics.checkNotNullParameter(suppressBitmapPrefetchingSupplier, "suppressBitmapPrefetchingSupplier");
            return asBuilder(new Function0<Unit>() { // from class: com.facebook.imagepipeline.core.ImagePipelineExperiments$Builder$setSuppressBitmapPrefetchingSupplier$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.suppressBitmapPrefetchingSupplier = suppressBitmapPrefetchingSupplier;
                }
            });
        }

        public final Builder setExperimentalThreadHandoffQueueEnabled(final boolean experimentalThreadHandoffQueueEnabled) {
            return asBuilder(new Function0<Unit>() { // from class: com.facebook.imagepipeline.core.ImagePipelineExperiments$Builder$setExperimentalThreadHandoffQueueEnabled$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.experimentalThreadHandoffQueueEnabled = experimentalThreadHandoffQueueEnabled;
                }
            });
        }

        public final Builder setExperimentalMemoryType(final long MemoryType) {
            return asBuilder(new Function0<Unit>() { // from class: com.facebook.imagepipeline.core.ImagePipelineExperiments$Builder$setExperimentalMemoryType$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.memoryType = MemoryType;
                }
            });
        }

        public final Builder setKeepCancelledFetchAsLowPriority(final boolean keepCancelledFetchAsLowPriority) {
            return asBuilder(new Function0<Unit>() { // from class: com.facebook.imagepipeline.core.ImagePipelineExperiments$Builder$setKeepCancelledFetchAsLowPriority$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.keepCancelledFetchAsLowPriority = keepCancelledFetchAsLowPriority;
                }
            });
        }

        public final Builder setDownsampleIfLargeBitmap(final boolean downsampleIfLargeBitmap) {
            return asBuilder(new Function0<Unit>() { // from class: com.facebook.imagepipeline.core.ImagePipelineExperiments$Builder$setDownsampleIfLargeBitmap$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.downsampleIfLargeBitmap = downsampleIfLargeBitmap;
                }
            });
        }

        public final Builder setEncodedCacheEnabled(final boolean encodedCacheEnabled) {
            return asBuilder(new Function0<Unit>() { // from class: com.facebook.imagepipeline.core.ImagePipelineExperiments$Builder$setEncodedCacheEnabled$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.encodedCacheEnabled = encodedCacheEnabled;
                }
            });
        }

        public final Builder setEnsureTranscoderLibraryLoaded(final boolean ensureTranscoderLibraryLoaded) {
            return asBuilder(new Function0<Unit>() { // from class: com.facebook.imagepipeline.core.ImagePipelineExperiments$Builder$setEnsureTranscoderLibraryLoaded$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.ensureTranscoderLibraryLoaded = ensureTranscoderLibraryLoaded;
                }
            });
        }

        public final Builder setIsDiskCacheProbingEnabled(final boolean isDiskCacheProbingEnabled) {
            return asBuilder(new Function0<Unit>() { // from class: com.facebook.imagepipeline.core.ImagePipelineExperiments$Builder$setIsDiskCacheProbingEnabled$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.isDiskCacheProbingEnabled = isDiskCacheProbingEnabled;
                }
            });
        }

        public final Builder setIsEncodedMemoryCacheProbingEnabled(final boolean isEncodedMemoryCacheProbingEnabled) {
            return asBuilder(new Function0<Unit>() { // from class: com.facebook.imagepipeline.core.ImagePipelineExperiments$Builder$setIsEncodedMemoryCacheProbingEnabled$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.isEncodedMemoryCacheProbingEnabled = isEncodedMemoryCacheProbingEnabled;
                }
            });
        }

        public final Builder setTrackedKeysSize(final int trackedKeysSize) {
            return asBuilder(new Function0<Unit>() { // from class: com.facebook.imagepipeline.core.ImagePipelineExperiments$Builder$setTrackedKeysSize$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.trackedKeysSize = trackedKeysSize;
                }
            });
        }

        public final Builder setAllowDelay(final boolean allowDelay) {
            return asBuilder(new Function0<Unit>() { // from class: com.facebook.imagepipeline.core.ImagePipelineExperiments$Builder$setAllowDelay$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.allowDelay = allowDelay;
                }
            });
        }

        public final Builder setAllowProgressiveOnPrefetch(final boolean allowProgressiveOnPrefetch) {
            return asBuilder(new Function0<Unit>() { // from class: com.facebook.imagepipeline.core.ImagePipelineExperiments$Builder$setAllowProgressiveOnPrefetch$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.allowProgressiveOnPrefetch = allowProgressiveOnPrefetch;
                }
            });
        }

        public final Builder setAnimationRenderFpsLimit(final int animationRenderFpsLimit) {
            return asBuilder(new Function0<Unit>() { // from class: com.facebook.imagepipeline.core.ImagePipelineExperiments$Builder$setAnimationRenderFpsLimit$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.animationRenderFpsLimit = animationRenderFpsLimit;
                }
            });
        }

        public final Builder setCancelDecodeOnCacheMiss(final boolean cancelDecodeOnCacheMiss) {
            return asBuilder(new Function0<Unit>() { // from class: com.facebook.imagepipeline.core.ImagePipelineExperiments$Builder$setCancelDecodeOnCacheMiss$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.cancelDecodeOnCacheMiss = cancelDecodeOnCacheMiss;
                }
            });
        }

        public final Builder setPlatformDecoderOptions(final PlatformDecoderOptions platformDecoderOptions) {
            Intrinsics.checkNotNullParameter(platformDecoderOptions, "platformDecoderOptions");
            return asBuilder(new Function0<Unit>() { // from class: com.facebook.imagepipeline.core.ImagePipelineExperiments$Builder$setPlatformDecoderOptions$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.platformDecoderOptions = platformDecoderOptions;
                }
            });
        }

        public final ImagePipelineExperiments build() {
            return new ImagePipelineExperiments(this, null);
        }
    }

    /* compiled from: ImagePipelineExperiments.kt */
    @Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002Jö\u0001\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0016\u0010\u0018\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u00192\u0016\u0010\u001c\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001d0\u00192\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\u0014\u0010!\u001a\u0010\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u001f\u0018\u00010\"2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020\u00102\u0006\u0010,\u001a\u00020)2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u00102\u0006\u00100\u001a\u00020)H\u0016¨\u00061"}, d2 = {"Lcom/facebook/imagepipeline/core/ImagePipelineExperiments$DefaultProducerFactoryMethod;", "Lcom/facebook/imagepipeline/core/ImagePipelineExperiments$ProducerFactoryMethod;", "()V", "createProducerFactory", "Lcom/facebook/imagepipeline/core/ProducerFactory;", f.X, "Landroid/content/Context;", "byteArrayPool", "Lcom/facebook/common/memory/ByteArrayPool;", "imageDecoder", "Lcom/facebook/imagepipeline/decoder/ImageDecoder;", "progressiveJpegConfig", "Lcom/facebook/imagepipeline/decoder/ProgressiveJpegConfig;", "downsampleMode", "Lcom/facebook/imagepipeline/core/DownsampleMode;", "resizeAndRotateEnabledForNetwork", "", "decodeCancellationEnabled", "executorSupplier", "Lcom/facebook/imagepipeline/core/ExecutorSupplier;", "pooledByteBufferFactory", "Lcom/facebook/common/memory/PooledByteBufferFactory;", "pooledByteStreams", "Lcom/facebook/common/memory/PooledByteStreams;", "bitmapMemoryCache", "Lcom/facebook/imagepipeline/cache/MemoryCache;", "Lcom/facebook/cache/common/CacheKey;", "Lcom/facebook/imagepipeline/image/CloseableImage;", "encodedMemoryCache", "Lcom/facebook/common/memory/PooledByteBuffer;", "defaultBufferedDiskCache", "Lcom/facebook/imagepipeline/cache/BufferedDiskCache;", "smallImageBufferedDiskCache", "dynamicBufferedDiskCaches", "", "", "cacheKeyFactory", "Lcom/facebook/imagepipeline/cache/CacheKeyFactory;", "platformBitmapFactory", "Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;", "bitmapPrepareToDrawMinSizeBytes", "", "bitmapPrepareToDrawMaxSizeBytes", "bitmapPrepareToDrawForPrefetch", "maxBitmapSize", "closeableReferenceFactory", "Lcom/facebook/imagepipeline/core/CloseableReferenceFactory;", "keepCancelledFetchAsLowPriority", "trackedKeysSize", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultProducerFactoryMethod implements ProducerFactoryMethod {
        @Override // com.facebook.imagepipeline.core.ImagePipelineExperiments.ProducerFactoryMethod
        public ProducerFactory createProducerFactory(Context context, ByteArrayPool byteArrayPool, ImageDecoder imageDecoder, ProgressiveJpegConfig progressiveJpegConfig, DownsampleMode downsampleMode, boolean resizeAndRotateEnabledForNetwork, boolean decodeCancellationEnabled, ExecutorSupplier executorSupplier, PooledByteBufferFactory pooledByteBufferFactory, PooledByteStreams pooledByteStreams, MemoryCache<CacheKey, CloseableImage> bitmapMemoryCache, MemoryCache<CacheKey, PooledByteBuffer> encodedMemoryCache, BufferedDiskCache defaultBufferedDiskCache, BufferedDiskCache smallImageBufferedDiskCache, Map<String, BufferedDiskCache> dynamicBufferedDiskCaches, CacheKeyFactory cacheKeyFactory, PlatformBitmapFactory platformBitmapFactory, int bitmapPrepareToDrawMinSizeBytes, int bitmapPrepareToDrawMaxSizeBytes, boolean bitmapPrepareToDrawForPrefetch, int maxBitmapSize, CloseableReferenceFactory closeableReferenceFactory, boolean keepCancelledFetchAsLowPriority, int trackedKeysSize) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(byteArrayPool, "byteArrayPool");
            Intrinsics.checkNotNullParameter(imageDecoder, "imageDecoder");
            Intrinsics.checkNotNullParameter(progressiveJpegConfig, "progressiveJpegConfig");
            Intrinsics.checkNotNullParameter(downsampleMode, "downsampleMode");
            Intrinsics.checkNotNullParameter(executorSupplier, "executorSupplier");
            Intrinsics.checkNotNullParameter(pooledByteBufferFactory, "pooledByteBufferFactory");
            Intrinsics.checkNotNullParameter(pooledByteStreams, "pooledByteStreams");
            Intrinsics.checkNotNullParameter(bitmapMemoryCache, "bitmapMemoryCache");
            Intrinsics.checkNotNullParameter(encodedMemoryCache, "encodedMemoryCache");
            Intrinsics.checkNotNullParameter(defaultBufferedDiskCache, "defaultBufferedDiskCache");
            Intrinsics.checkNotNullParameter(smallImageBufferedDiskCache, "smallImageBufferedDiskCache");
            Intrinsics.checkNotNullParameter(cacheKeyFactory, "cacheKeyFactory");
            Intrinsics.checkNotNullParameter(platformBitmapFactory, "platformBitmapFactory");
            Intrinsics.checkNotNullParameter(closeableReferenceFactory, "closeableReferenceFactory");
            return new ProducerFactory(context, byteArrayPool, imageDecoder, progressiveJpegConfig, downsampleMode, resizeAndRotateEnabledForNetwork, decodeCancellationEnabled, executorSupplier, pooledByteBufferFactory, bitmapMemoryCache, encodedMemoryCache, defaultBufferedDiskCache, smallImageBufferedDiskCache, dynamicBufferedDiskCaches, cacheKeyFactory, platformBitmapFactory, bitmapPrepareToDrawMinSizeBytes, bitmapPrepareToDrawMaxSizeBytes, bitmapPrepareToDrawForPrefetch, maxBitmapSize, closeableReferenceFactory, keepCancelledFetchAsLowPriority, trackedKeysSize);
        }
    }

    /* compiled from: ImagePipelineExperiments.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/facebook/imagepipeline/core/ImagePipelineExperiments$Companion;", "", "()V", "newBuilder", "Lcom/facebook/imagepipeline/core/ImagePipelineExperiments$Builder;", "configBuilder", "Lcom/facebook/imagepipeline/core/ImagePipelineConfig$Builder;", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Builder newBuilder(ImagePipelineConfig.Builder configBuilder) {
            Intrinsics.checkNotNullParameter(configBuilder, "configBuilder");
            return new Builder(configBuilder);
        }
    }
}
