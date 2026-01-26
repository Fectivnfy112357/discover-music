package com.facebook.imagepipeline.animated.factory;

import android.content.Context;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import com.umeng.analytics.pro.f;
import kotlin.Metadata;

/* compiled from: AnimatedFactory.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0014\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH&R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005¨\u0006\f"}, d2 = {"Lcom/facebook/imagepipeline/animated/factory/AnimatedFactory;", "", "gifDecoder", "Lcom/facebook/imagepipeline/decoder/ImageDecoder;", "getGifDecoder", "()Lcom/facebook/imagepipeline/decoder/ImageDecoder;", "webPDecoder", "getWebPDecoder", "getAnimatedDrawableFactory", "Lcom/facebook/imagepipeline/drawable/DrawableFactory;", f.X, "Landroid/content/Context;", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public interface AnimatedFactory {
    DrawableFactory getAnimatedDrawableFactory(Context context);

    ImageDecoder getGifDecoder();

    ImageDecoder getWebPDecoder();
}
