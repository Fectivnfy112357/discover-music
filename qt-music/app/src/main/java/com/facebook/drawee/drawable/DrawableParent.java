package com.facebook.drawee.drawable;

import android.graphics.drawable.Drawable;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public interface DrawableParent {
    @Nullable
    Drawable getDrawable();

    @Nullable
    Drawable setDrawable(@Nullable Drawable drawable);
}
