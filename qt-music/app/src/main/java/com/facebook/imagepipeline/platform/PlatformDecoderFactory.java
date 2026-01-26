package com.facebook.imagepipeline.platform;

import android.os.Build;
import androidx.core.util.Pools;
import com.facebook.common.memory.DecodeBufferHelper;
import com.facebook.imagepipeline.memory.BitmapPool;
import com.facebook.imagepipeline.memory.PoolFactory;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PlatformDecoderFactory.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J*\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0007¨\u0006\u000f"}, d2 = {"Lcom/facebook/imagepipeline/platform/PlatformDecoderFactory;", "", "()V", "buildPlatformDecoder", "Lcom/facebook/imagepipeline/platform/PlatformDecoder;", "poolFactory", "Lcom/facebook/imagepipeline/memory/PoolFactory;", "gingerbreadDecoderEnabled", "", "useDecodeBufferHelper", "platformDecoderOptions", "Lcom/facebook/imagepipeline/platform/PlatformDecoderOptions;", "createPool", "Landroidx/core/util/Pools$Pool;", "Ljava/nio/ByteBuffer;", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PlatformDecoderFactory {
    public static final PlatformDecoderFactory INSTANCE = new PlatformDecoderFactory();

    @JvmStatic
    public static final PlatformDecoder buildPlatformDecoder(PoolFactory poolFactory, boolean z, PlatformDecoderOptions platformDecoderOptions) {
        Intrinsics.checkNotNullParameter(poolFactory, "poolFactory");
        Intrinsics.checkNotNullParameter(platformDecoderOptions, "platformDecoderOptions");
        return buildPlatformDecoder$default(poolFactory, z, false, platformDecoderOptions, 4, null);
    }

    private PlatformDecoderFactory() {
    }

    public static /* synthetic */ PlatformDecoder buildPlatformDecoder$default(PoolFactory poolFactory, boolean z, boolean z2, PlatformDecoderOptions platformDecoderOptions, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = false;
        }
        return buildPlatformDecoder(poolFactory, z, z2, platformDecoderOptions);
    }

    @JvmStatic
    public static final PlatformDecoder buildPlatformDecoder(PoolFactory poolFactory, boolean gingerbreadDecoderEnabled, boolean useDecodeBufferHelper, PlatformDecoderOptions platformDecoderOptions) {
        Intrinsics.checkNotNullParameter(poolFactory, "poolFactory");
        Intrinsics.checkNotNullParameter(platformDecoderOptions, "platformDecoderOptions");
        if (Build.VERSION.SDK_INT >= 26) {
            BitmapPool bitmapPool = poolFactory.getBitmapPool();
            Intrinsics.checkNotNullExpressionValue(bitmapPool, "poolFactory.bitmapPool");
            return new OreoDecoder(bitmapPool, createPool(poolFactory, useDecodeBufferHelper), platformDecoderOptions);
        }
        BitmapPool bitmapPool2 = poolFactory.getBitmapPool();
        Intrinsics.checkNotNullExpressionValue(bitmapPool2, "poolFactory.bitmapPool");
        return new ArtDecoder(bitmapPool2, createPool(poolFactory, useDecodeBufferHelper), platformDecoderOptions);
    }

    @JvmStatic
    public static final Pools.Pool<ByteBuffer> createPool(PoolFactory poolFactory, boolean useDecodeBufferHelper) {
        Intrinsics.checkNotNullParameter(poolFactory, "poolFactory");
        if (useDecodeBufferHelper) {
            DecodeBufferHelper INSTANCE2 = DecodeBufferHelper.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(INSTANCE2, "INSTANCE");
            return INSTANCE2;
        }
        int flexByteArrayPoolMaxNumThreads = poolFactory.getFlexByteArrayPoolMaxNumThreads();
        Pools.SynchronizedPool synchronizedPool = new Pools.SynchronizedPool(flexByteArrayPoolMaxNumThreads);
        for (int i = 0; i < flexByteArrayPoolMaxNumThreads; i++) {
            synchronizedPool.release(ByteBuffer.allocate(DecodeBufferHelper.getRecommendedDecodeBufferSize()));
        }
        return synchronizedPool;
    }
}
