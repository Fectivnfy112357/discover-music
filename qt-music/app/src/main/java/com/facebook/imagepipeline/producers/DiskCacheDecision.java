package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.react.devsupport.StackTraceHelper;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DiskCacheDecision.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\fB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J<\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u00042\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0004\u0018\u00010\nH\u0007¨\u0006\r"}, d2 = {"Lcom/facebook/imagepipeline/producers/DiskCacheDecision;", "", "()V", "chooseDiskCacheForRequest", "Lcom/facebook/imagepipeline/cache/BufferedDiskCache;", "imageRequest", "Lcom/facebook/imagepipeline/request/ImageRequest;", "smallDiskCache", "defaultDiskCache", "dynamicDiskCaches", "", "", "DiskCacheDecisionNoDiskCacheChosenException", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DiskCacheDecision {
    public static final DiskCacheDecision INSTANCE = new DiskCacheDecision();

    private DiskCacheDecision() {
    }

    @JvmStatic
    public static final BufferedDiskCache chooseDiskCacheForRequest(ImageRequest imageRequest, BufferedDiskCache smallDiskCache, BufferedDiskCache defaultDiskCache, Map<String, BufferedDiskCache> dynamicDiskCaches) {
        String diskCacheId;
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        if (imageRequest.getCacheChoice() == ImageRequest.CacheChoice.SMALL) {
            return smallDiskCache;
        }
        if (imageRequest.getCacheChoice() == ImageRequest.CacheChoice.DEFAULT) {
            return defaultDiskCache;
        }
        if (imageRequest.getCacheChoice() != ImageRequest.CacheChoice.DYNAMIC || dynamicDiskCaches == null || (diskCacheId = imageRequest.getDiskCacheId()) == null) {
            return null;
        }
        return dynamicDiskCaches.get(diskCacheId);
    }

    /* compiled from: DiskCacheDecision.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u00002\u00060\u0001j\u0002`\u0002B\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/facebook/imagepipeline/producers/DiskCacheDecision$DiskCacheDecisionNoDiskCacheChosenException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", StackTraceHelper.MESSAGE_KEY, "", "(Ljava/lang/String;)V", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class DiskCacheDecisionNoDiskCacheChosenException extends Exception {
        public DiskCacheDecisionNoDiskCacheChosenException(String str) {
            super(str);
        }
    }
}
