package com.facebook.imagepipeline.image;

import android.graphics.Bitmap;

/* loaded from: classes2.dex */
public interface CloseableBitmap extends CloseableImage {
    Bitmap getUnderlyingBitmap();
}
