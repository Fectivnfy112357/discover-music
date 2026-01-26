package com.facebook.imagepipeline.core;

import android.content.Context;
import android.graphics.Bitmap;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.callercontext.CallerContextVerifier;
import com.facebook.common.executors.SerialExecutorService;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.cache.BitmapMemoryCacheFactory;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.cache.ImageCacheStatsTracker;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.debug.CloseableReferenceLeakTracker;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.decoder.ImageDecoderConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestListener2;
import com.facebook.imagepipeline.memory.PoolFactory;
import com.facebook.imagepipeline.producers.CustomProducerSequenceFactory;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.facebook.imagepipeline.transcoder.ImageTranscoderFactory;
import com.umeng.analytics.pro.f;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;

/* compiled from: ImagePipelineConfigInterface.kt */
@Metadata(d1 = {"\u0000\u009a\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001R \u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u0004\u0018\u00010\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0012\u0010\u0010\u001a\u00020\u0011X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0012\u0010\u0019\u001a\u00020\u001aX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0012\u0010\u001d\u001a\u00020\u001eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u0004\u0018\u00010\"X¦\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0012\u0010%\u001a\u00020&X¦\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(R\u0012\u0010)\u001a\u00020*X¦\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0018\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.X¦\u0004¢\u0006\u0006\u001a\u0004\b0\u00101R\u0012\u00102\u001a\u000203X¦\u0004¢\u0006\u0006\u001a\u0004\b4\u00105R \u00106\u001a\u0010\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u000209\u0018\u000107X¦\u0004¢\u0006\u0006\u001a\u0004\b:\u0010;R\u0018\u0010<\u001a\b\u0012\u0004\u0012\u00020=0\u0015X¦\u0004¢\u0006\u0006\u001a\u0004\b>\u0010\u0018R \u0010?\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020@\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\bA\u0010\u0007R\u0018\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X¦\u0004¢\u0006\u0006\u001a\u0004\bC\u0010\u0018R\u0012\u0010D\u001a\u00020\u001aX¦\u0004¢\u0006\u0006\u001a\u0004\bE\u0010\u001cR\u0014\u0010F\u001a\u0004\u0018\u00010GX¦\u0004¢\u0006\u0006\u001a\u0004\bH\u0010IR\u0012\u0010J\u001a\u00020KX¦\u0004¢\u0006\u0006\u001a\u0004\bL\u0010MR\u0012\u0010N\u001a\u00020OX¦\u0004¢\u0006\u0006\u001a\u0004\bP\u0010QR\u0012\u0010R\u001a\u00020SX¦\u0004¢\u0006\u0006\u001a\u0004\bT\u0010UR\u0012\u0010V\u001a\u00020WX¦\u0004¢\u0006\u0006\u001a\u0004\bX\u0010YR\u0014\u0010Z\u001a\u0004\u0018\u00010[X¦\u0004¢\u0006\u0006\u001a\u0004\b\\\u0010]R\u0014\u0010^\u001a\u0004\u0018\u00010_X¦\u0004¢\u0006\u0006\u001a\u0004\b`\u0010aR\u0014\u0010b\u001a\u0004\u0018\u00010cX¦\u0004¢\u0006\u0006\u001a\u0004\bd\u0010eR\u0016\u0010f\u001a\u0004\u0018\u00010g8fX¦\u0004¢\u0006\u0006\u001a\u0004\bh\u0010iR\u0012\u0010j\u001a\u00020=X¦\u0004¢\u0006\u0006\u001a\u0004\bj\u0010kR\u0018\u0010l\u001a\b\u0012\u0004\u0012\u00020=0\u0015X¦\u0004¢\u0006\u0006\u001a\u0004\bl\u0010\u0018R\u0012\u0010m\u001a\u00020=X¦\u0004¢\u0006\u0006\u001a\u0004\bm\u0010kR\u0012\u0010n\u001a\u000209X¦\u0004¢\u0006\u0006\u001a\u0004\bo\u0010pR\u0014\u0010q\u001a\u00020g8fX¦\u0004¢\u0006\u0006\u001a\u0004\br\u0010sR\u0012\u0010t\u001a\u00020uX¦\u0004¢\u0006\u0006\u001a\u0004\bv\u0010wR\u0016\u0010x\u001a\u0006\u0012\u0002\b\u00030yX¦\u0004¢\u0006\u0006\u001a\u0004\bz\u0010{R\u0014\u0010|\u001a\u0004\u0018\u00010}X¦\u0004¢\u0006\u0006\u001a\u0004\b~\u0010\u007fR\u0016\u0010\u0080\u0001\u001a\u00030\u0081\u0001X¦\u0004¢\u0006\b\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001R\u0016\u0010\u0084\u0001\u001a\u00030\u0085\u0001X¦\u0004¢\u0006\b\u001a\u0006\b\u0086\u0001\u0010\u0087\u0001R\u001d\u0010\u0088\u0001\u001a\u000b\u0012\u0007\u0012\u0005\u0018\u00010\u0089\u00010.X¦\u0004¢\u0006\u0007\u001a\u0005\b\u008a\u0001\u00101R\u001d\u0010\u008b\u0001\u001a\u000b\u0012\u0007\u0012\u0005\u0018\u00010\u008c\u00010.X¦\u0004¢\u0006\u0007\u001a\u0005\b\u008d\u0001\u00101R\u0014\u0010\u008e\u0001\u001a\u000209X¦\u0004¢\u0006\u0007\u001a\u0005\b\u008f\u0001\u0010p¨\u0006\u0090\u0001"}, d2 = {"Lcom/facebook/imagepipeline/core/ImagePipelineConfigInterface;", "", "bitmapCacheOverride", "Lcom/facebook/imagepipeline/cache/MemoryCache;", "Lcom/facebook/cache/common/CacheKey;", "Lcom/facebook/imagepipeline/image/CloseableImage;", "getBitmapCacheOverride", "()Lcom/facebook/imagepipeline/cache/MemoryCache;", "bitmapConfig", "Landroid/graphics/Bitmap$Config;", "getBitmapConfig", "()Landroid/graphics/Bitmap$Config;", "bitmapMemoryCacheEntryStateObserver", "Lcom/facebook/imagepipeline/cache/CountingMemoryCache$EntryStateObserver;", "getBitmapMemoryCacheEntryStateObserver", "()Lcom/facebook/imagepipeline/cache/CountingMemoryCache$EntryStateObserver;", "bitmapMemoryCacheFactory", "Lcom/facebook/imagepipeline/cache/BitmapMemoryCacheFactory;", "getBitmapMemoryCacheFactory", "()Lcom/facebook/imagepipeline/cache/BitmapMemoryCacheFactory;", "bitmapMemoryCacheParamsSupplier", "Lcom/facebook/common/internal/Supplier;", "Lcom/facebook/imagepipeline/cache/MemoryCacheParams;", "getBitmapMemoryCacheParamsSupplier", "()Lcom/facebook/common/internal/Supplier;", "bitmapMemoryCacheTrimStrategy", "Lcom/facebook/imagepipeline/cache/MemoryCache$CacheTrimStrategy;", "getBitmapMemoryCacheTrimStrategy", "()Lcom/facebook/imagepipeline/cache/MemoryCache$CacheTrimStrategy;", "cacheKeyFactory", "Lcom/facebook/imagepipeline/cache/CacheKeyFactory;", "getCacheKeyFactory", "()Lcom/facebook/imagepipeline/cache/CacheKeyFactory;", "callerContextVerifier", "Lcom/facebook/callercontext/CallerContextVerifier;", "getCallerContextVerifier", "()Lcom/facebook/callercontext/CallerContextVerifier;", "closeableReferenceLeakTracker", "Lcom/facebook/imagepipeline/debug/CloseableReferenceLeakTracker;", "getCloseableReferenceLeakTracker", "()Lcom/facebook/imagepipeline/debug/CloseableReferenceLeakTracker;", f.X, "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "customProducerSequenceFactories", "", "Lcom/facebook/imagepipeline/producers/CustomProducerSequenceFactory;", "getCustomProducerSequenceFactories", "()Ljava/util/Set;", "downsampleMode", "Lcom/facebook/imagepipeline/core/DownsampleMode;", "getDownsampleMode", "()Lcom/facebook/imagepipeline/core/DownsampleMode;", "dynamicDiskCacheConfigMap", "", "", "Lcom/facebook/cache/disk/DiskCacheConfig;", "getDynamicDiskCacheConfigMap", "()Ljava/util/Map;", "enableEncodedImageColorSpaceUsage", "", "getEnableEncodedImageColorSpaceUsage", "encodedMemoryCacheOverride", "Lcom/facebook/common/memory/PooledByteBuffer;", "getEncodedMemoryCacheOverride", "encodedMemoryCacheParamsSupplier", "getEncodedMemoryCacheParamsSupplier", "encodedMemoryCacheTrimStrategy", "getEncodedMemoryCacheTrimStrategy", "executorServiceForAnimatedImages", "Lcom/facebook/common/executors/SerialExecutorService;", "getExecutorServiceForAnimatedImages", "()Lcom/facebook/common/executors/SerialExecutorService;", "executorSupplier", "Lcom/facebook/imagepipeline/core/ExecutorSupplier;", "getExecutorSupplier", "()Lcom/facebook/imagepipeline/core/ExecutorSupplier;", "experiments", "Lcom/facebook/imagepipeline/core/ImagePipelineExperiments;", "getExperiments", "()Lcom/facebook/imagepipeline/core/ImagePipelineExperiments;", "fileCacheFactory", "Lcom/facebook/imagepipeline/core/FileCacheFactory;", "getFileCacheFactory", "()Lcom/facebook/imagepipeline/core/FileCacheFactory;", "imageCacheStatsTracker", "Lcom/facebook/imagepipeline/cache/ImageCacheStatsTracker;", "getImageCacheStatsTracker", "()Lcom/facebook/imagepipeline/cache/ImageCacheStatsTracker;", "imageDecoder", "Lcom/facebook/imagepipeline/decoder/ImageDecoder;", "getImageDecoder", "()Lcom/facebook/imagepipeline/decoder/ImageDecoder;", "imageDecoderConfig", "Lcom/facebook/imagepipeline/decoder/ImageDecoderConfig;", "getImageDecoderConfig", "()Lcom/facebook/imagepipeline/decoder/ImageDecoderConfig;", "imageTranscoderFactory", "Lcom/facebook/imagepipeline/transcoder/ImageTranscoderFactory;", "getImageTranscoderFactory", "()Lcom/facebook/imagepipeline/transcoder/ImageTranscoderFactory;", "imageTranscoderType", "", "getImageTranscoderType", "()Ljava/lang/Integer;", "isDiskCacheEnabled", "()Z", "isPrefetchEnabledSupplier", "isResizeAndRotateEnabledForNetwork", "mainDiskCacheConfig", "getMainDiskCacheConfig", "()Lcom/facebook/cache/disk/DiskCacheConfig;", "memoryChunkType", "getMemoryChunkType", "()I", "memoryTrimmableRegistry", "Lcom/facebook/common/memory/MemoryTrimmableRegistry;", "getMemoryTrimmableRegistry", "()Lcom/facebook/common/memory/MemoryTrimmableRegistry;", "networkFetcher", "Lcom/facebook/imagepipeline/producers/NetworkFetcher;", "getNetworkFetcher", "()Lcom/facebook/imagepipeline/producers/NetworkFetcher;", "platformBitmapFactory", "Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;", "getPlatformBitmapFactory", "()Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;", "poolFactory", "Lcom/facebook/imagepipeline/memory/PoolFactory;", "getPoolFactory", "()Lcom/facebook/imagepipeline/memory/PoolFactory;", "progressiveJpegConfig", "Lcom/facebook/imagepipeline/decoder/ProgressiveJpegConfig;", "getProgressiveJpegConfig", "()Lcom/facebook/imagepipeline/decoder/ProgressiveJpegConfig;", "requestListener2s", "Lcom/facebook/imagepipeline/listener/RequestListener2;", "getRequestListener2s", "requestListeners", "Lcom/facebook/imagepipeline/listener/RequestListener;", "getRequestListeners", "smallImageDiskCacheConfig", "getSmallImageDiskCacheConfig", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public interface ImagePipelineConfigInterface {
    MemoryCache<CacheKey, CloseableImage> getBitmapCacheOverride();

    Bitmap.Config getBitmapConfig();

    CountingMemoryCache.EntryStateObserver<CacheKey> getBitmapMemoryCacheEntryStateObserver();

    BitmapMemoryCacheFactory getBitmapMemoryCacheFactory();

    Supplier<MemoryCacheParams> getBitmapMemoryCacheParamsSupplier();

    MemoryCache.CacheTrimStrategy getBitmapMemoryCacheTrimStrategy();

    CacheKeyFactory getCacheKeyFactory();

    CallerContextVerifier getCallerContextVerifier();

    CloseableReferenceLeakTracker getCloseableReferenceLeakTracker();

    Context getContext();

    Set<CustomProducerSequenceFactory> getCustomProducerSequenceFactories();

    DownsampleMode getDownsampleMode();

    Map<String, DiskCacheConfig> getDynamicDiskCacheConfigMap();

    Supplier<Boolean> getEnableEncodedImageColorSpaceUsage();

    MemoryCache<CacheKey, PooledByteBuffer> getEncodedMemoryCacheOverride();

    Supplier<MemoryCacheParams> getEncodedMemoryCacheParamsSupplier();

    MemoryCache.CacheTrimStrategy getEncodedMemoryCacheTrimStrategy();

    SerialExecutorService getExecutorServiceForAnimatedImages();

    ExecutorSupplier getExecutorSupplier();

    ImagePipelineExperiments getExperiments();

    FileCacheFactory getFileCacheFactory();

    ImageCacheStatsTracker getImageCacheStatsTracker();

    ImageDecoder getImageDecoder();

    ImageDecoderConfig getImageDecoderConfig();

    ImageTranscoderFactory getImageTranscoderFactory();

    Integer getImageTranscoderType();

    DiskCacheConfig getMainDiskCacheConfig();

    int getMemoryChunkType();

    MemoryTrimmableRegistry getMemoryTrimmableRegistry();

    NetworkFetcher<?> getNetworkFetcher();

    PlatformBitmapFactory getPlatformBitmapFactory();

    PoolFactory getPoolFactory();

    ProgressiveJpegConfig getProgressiveJpegConfig();

    Set<RequestListener2> getRequestListener2s();

    Set<RequestListener> getRequestListeners();

    DiskCacheConfig getSmallImageDiskCacheConfig();

    /* renamed from: isDiskCacheEnabled */
    boolean getIsDiskCacheEnabled();

    Supplier<Boolean> isPrefetchEnabledSupplier();

    /* renamed from: isResizeAndRotateEnabledForNetwork */
    boolean getIsResizeAndRotateEnabledForNetwork();
}
