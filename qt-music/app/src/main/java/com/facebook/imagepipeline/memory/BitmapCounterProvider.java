package com.facebook.imagepipeline.memory;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BitmapCounterProvider.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\tH\u0007J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0013"}, d2 = {"Lcom/facebook/imagepipeline/memory/BitmapCounterProvider;", "", "()V", "KB", "", "MAX_BITMAP_TOTAL_SIZE", "", "MB", "bitmapCounter", "Lcom/facebook/imagepipeline/memory/BitmapCounter;", "maxBitmapCount", "maxSizeHardCap", "getMaxSizeHardCap", "()I", "get", "initialize", "", "bitmapCounterConfig", "Lcom/facebook/imagepipeline/memory/BitmapCounterConfig;", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BitmapCounterProvider {
    public static final BitmapCounterProvider INSTANCE;
    private static final long KB = 1024;
    public static final int MAX_BITMAP_TOTAL_SIZE;
    private static final long MB = 1048576;
    private static volatile BitmapCounter bitmapCounter;
    private static int maxBitmapCount;

    private BitmapCounterProvider() {
    }

    static {
        BitmapCounterProvider bitmapCounterProvider = new BitmapCounterProvider();
        INSTANCE = bitmapCounterProvider;
        MAX_BITMAP_TOTAL_SIZE = bitmapCounterProvider.getMaxSizeHardCap();
        maxBitmapCount = 384;
    }

    private final int getMaxSizeHardCap() {
        int iMin = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (iMin > 16777216) {
            return (iMin / 4) * 3;
        }
        return iMin / 2;
    }

    @JvmStatic
    public static final void initialize(BitmapCounterConfig bitmapCounterConfig) {
        Intrinsics.checkNotNullParameter(bitmapCounterConfig, "bitmapCounterConfig");
        if (bitmapCounter != null) {
            throw new IllegalStateException("BitmapCounter has already been created! `BitmapCounterProvider.initialize(...)` should only be called before `BitmapCounterProvider.get()` or not at all!".toString());
        }
        maxBitmapCount = bitmapCounterConfig.getMaxBitmapCount();
    }

    @JvmStatic
    public static final BitmapCounter get() {
        if (bitmapCounter == null) {
            synchronized (BitmapCounterProvider.class) {
                if (bitmapCounter == null) {
                    bitmapCounter = new BitmapCounter(maxBitmapCount, MAX_BITMAP_TOTAL_SIZE);
                }
                Unit unit = Unit.INSTANCE;
            }
        }
        BitmapCounter bitmapCounter2 = bitmapCounter;
        Intrinsics.checkNotNull(bitmapCounter2);
        return bitmapCounter2;
    }
}
