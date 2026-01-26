package com.facebook.react.uimanager.style;

import android.graphics.Rect;
import android.graphics.Shader;
import com.facebook.react.bridge.ReadableMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BackgroundImageLayer.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nR\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/facebook/react/uimanager/style/BackgroundImageLayer;", "", "gradientMap", "Lcom/facebook/react/bridge/ReadableMap;", "(Lcom/facebook/react/bridge/ReadableMap;)V", "gradient", "Lcom/facebook/react/uimanager/style/Gradient;", "getShader", "Landroid/graphics/Shader;", "bounds", "Landroid/graphics/Rect;", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BackgroundImageLayer {
    private final Gradient gradient;

    public BackgroundImageLayer(ReadableMap readableMap) {
        Gradient gradient = null;
        if (readableMap != null) {
            try {
                gradient = new Gradient(readableMap);
            } catch (IllegalArgumentException unused) {
            }
        }
        this.gradient = gradient;
    }

    public final Shader getShader(Rect bounds) {
        Intrinsics.checkNotNullParameter(bounds, "bounds");
        Gradient gradient = this.gradient;
        if (gradient != null) {
            return gradient.getShader(bounds);
        }
        return null;
    }
}
