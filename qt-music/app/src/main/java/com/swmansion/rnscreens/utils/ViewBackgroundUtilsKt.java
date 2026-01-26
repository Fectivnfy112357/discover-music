package com.swmansion.rnscreens.utils;

import android.graphics.drawable.Drawable;
import com.facebook.react.uimanager.drawable.CSSBackgroundDrawable;
import com.facebook.react.views.view.ReactViewGroup;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ViewBackgroundUtils.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0013\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0000¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"resolveBackgroundColor", "", "Lcom/facebook/react/views/view/ReactViewGroup;", "(Lcom/facebook/react/views/view/ReactViewGroup;)Ljava/lang/Integer;", "react-native-screens_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ViewBackgroundUtilsKt {
    public static final Integer resolveBackgroundColor(ReactViewGroup reactViewGroup) {
        Intrinsics.checkNotNullParameter(reactViewGroup, "<this>");
        Drawable background = reactViewGroup.getBackground();
        CSSBackgroundDrawable cSSBackgroundDrawable = background instanceof CSSBackgroundDrawable ? (CSSBackgroundDrawable) background : null;
        if (cSSBackgroundDrawable != null) {
            return Integer.valueOf(cSSBackgroundDrawable.getColor());
        }
        return null;
    }
}
