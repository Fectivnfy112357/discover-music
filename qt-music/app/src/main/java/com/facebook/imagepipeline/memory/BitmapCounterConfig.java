package com.facebook.imagepipeline.memory;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: BitmapCounterConfig.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/facebook/imagepipeline/memory/BitmapCounterConfig;", "", "maxBitmapCount", "", "(I)V", "getMaxBitmapCount", "()I", "Companion", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BitmapCounterConfig {
    public static final int DEFAULT_MAX_BITMAP_COUNT = 384;
    private final int maxBitmapCount;

    public BitmapCounterConfig() {
        this(0, 1, null);
    }

    public BitmapCounterConfig(int i) {
        this.maxBitmapCount = i;
    }

    public /* synthetic */ BitmapCounterConfig(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 384 : i);
    }

    public final int getMaxBitmapCount() {
        return this.maxBitmapCount;
    }
}
