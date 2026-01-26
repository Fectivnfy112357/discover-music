package com.facebook.imagepipeline.postprocessors;

import android.content.Context;
import android.graphics.Bitmap;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.filter.IterativeBoxBlurFilter;
import com.facebook.imagepipeline.filter.RenderScriptBlurFilter;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.umeng.analytics.pro.f;
import com.umeng.commonsdk.framework.UMModuleRegister;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: BlurPostProcessor.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\b\u0010\u000f\u001a\u00020\u000bH\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0013H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\t¨\u0006\u0017"}, d2 = {"Lcom/facebook/imagepipeline/postprocessors/BlurPostProcessor;", "Lcom/facebook/imagepipeline/request/BasePostprocessor;", "blurRadius", "", f.X, "Landroid/content/Context;", "iterations", "(ILandroid/content/Context;I)V", "getBlurRadius", "()I", "cacheKey", "Lcom/facebook/cache/common/CacheKey;", "getContext", "()Landroid/content/Context;", "getIterations", "getPostprocessorCacheKey", UMModuleRegister.PROCESS, "", "bitmap", "Landroid/graphics/Bitmap;", "destBitmap", "sourceBitmap", "Companion", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BlurPostProcessor extends BasePostprocessor {
    private static final int DEFAULT_ITERATIONS = 3;
    private final int blurRadius;
    private final CacheKey cacheKey;
    private final Context context;
    private final int iterations;
    private static final boolean canUseRenderScript = RenderScriptBlurFilter.canUseRenderScript();

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BlurPostProcessor(int i, Context context) {
        this(i, context, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ BlurPostProcessor(int i, Context context, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, context, (i3 & 4) != 0 ? 3 : i2);
    }

    public final int getBlurRadius() {
        return this.blurRadius;
    }

    public final Context getContext() {
        return this.context;
    }

    public final int getIterations() {
        return this.iterations;
    }

    public BlurPostProcessor(int i, Context context, int i2) {
        String str;
        Intrinsics.checkNotNullParameter(context, "context");
        this.blurRadius = i;
        this.context = context;
        this.iterations = i2;
        Preconditions.checkArgument(Boolean.valueOf(i > 0 && i <= 25));
        Preconditions.checkArgument(Boolean.valueOf(i2 > 0));
        if (canUseRenderScript) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            str = String.format(null, "IntrinsicBlur;%d", Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(locale, format, *args)");
        } else {
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            str = String.format(null, "IterativeBoxBlur;%d;%d", Arrays.copyOf(new Object[]{Integer.valueOf(i2), Integer.valueOf(i)}, 2));
            Intrinsics.checkNotNullExpressionValue(str, "format(locale, format, *args)");
        }
        this.cacheKey = new SimpleCacheKey(str);
    }

    @Override // com.facebook.imagepipeline.request.BasePostprocessor, com.facebook.imagepipeline.request.Postprocessor
    /* renamed from: getPostprocessorCacheKey, reason: from getter */
    public CacheKey getCacheKey() {
        return this.cacheKey;
    }

    @Override // com.facebook.imagepipeline.request.BasePostprocessor
    public void process(Bitmap destBitmap, Bitmap sourceBitmap) throws Throwable {
        Intrinsics.checkNotNullParameter(destBitmap, "destBitmap");
        Intrinsics.checkNotNullParameter(sourceBitmap, "sourceBitmap");
        if (canUseRenderScript) {
            RenderScriptBlurFilter.blurBitmap(destBitmap, sourceBitmap, this.context, this.blurRadius);
        } else {
            super.process(destBitmap, sourceBitmap);
        }
    }

    @Override // com.facebook.imagepipeline.request.BasePostprocessor
    public void process(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        IterativeBoxBlurFilter.boxBlurBitmapInPlace(bitmap, this.iterations, this.blurRadius);
    }
}
