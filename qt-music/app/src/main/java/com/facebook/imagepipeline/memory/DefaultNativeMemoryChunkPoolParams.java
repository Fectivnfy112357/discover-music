package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* compiled from: DefaultNativeMemoryChunkPoolParams.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\b¨\u0006\r"}, d2 = {"Lcom/facebook/imagepipeline/memory/DefaultNativeMemoryChunkPoolParams;", "", "()V", "LARGE_BUCKET_LENGTH", "", "SMALL_BUCKET_LENGTH", "maxSizeHardCap", "getMaxSizeHardCap", "()I", "maxSizeSoftCap", "getMaxSizeSoftCap", "get", "Lcom/facebook/imagepipeline/memory/PoolParams;", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DefaultNativeMemoryChunkPoolParams {
    public static final DefaultNativeMemoryChunkPoolParams INSTANCE = new DefaultNativeMemoryChunkPoolParams();
    private static final int LARGE_BUCKET_LENGTH = 2;
    private static final int SMALL_BUCKET_LENGTH = 5;

    private DefaultNativeMemoryChunkPoolParams() {
    }

    @JvmStatic
    public static final PoolParams get() {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sparseIntArray.put(1024, 5);
        sparseIntArray.put(2048, 5);
        sparseIntArray.put(4096, 5);
        sparseIntArray.put(8192, 5);
        sparseIntArray.put(16384, 5);
        sparseIntArray.put(32768, 5);
        sparseIntArray.put(65536, 5);
        sparseIntArray.put(131072, 5);
        sparseIntArray.put(262144, 2);
        sparseIntArray.put(524288, 2);
        sparseIntArray.put(1048576, 2);
        DefaultNativeMemoryChunkPoolParams defaultNativeMemoryChunkPoolParams = INSTANCE;
        return new PoolParams(defaultNativeMemoryChunkPoolParams.getMaxSizeSoftCap(), defaultNativeMemoryChunkPoolParams.getMaxSizeHardCap(), sparseIntArray);
    }

    private final int getMaxSizeSoftCap() {
        int iMin = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (iMin < 16777216) {
            return 3145728;
        }
        return iMin < 33554432 ? 6291456 : 12582912;
    }

    private final int getMaxSizeHardCap() {
        int iMin = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (iMin < 16777216) {
            return iMin / 2;
        }
        return (iMin / 4) * 3;
    }
}
