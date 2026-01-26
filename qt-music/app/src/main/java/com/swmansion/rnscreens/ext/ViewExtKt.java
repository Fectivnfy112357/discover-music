package com.swmansion.rnscreens.ext;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ViewExt.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u001a\u000e\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0000\u001a\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004*\u00020\u0002H\u0000¢\u0006\u0002\u0010\u0005\u001a\u000e\u0010\u0006\u001a\u0004\u0018\u00010\u0002*\u00020\u0002H\u0000\u001a\u000e\u0010\u0007\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0000\u001a\f\u0010\b\u001a\u00020\u0002*\u00020\u0002H\u0000¨\u0006\t"}, d2 = {"asViewGroupOrNull", "Landroid/view/ViewGroup;", "Landroid/view/View;", "maybeBgColor", "", "(Landroid/view/View;)Ljava/lang/Integer;", "parentAsView", "parentAsViewGroup", "recycle", "react-native-screens_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ViewExtKt {
    public static final View parentAsView(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Object parent = view.getParent();
        if (parent instanceof View) {
            return (View) parent;
        }
        return null;
    }

    public static final ViewGroup parentAsViewGroup(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            return (ViewGroup) parent;
        }
        return null;
    }

    public static final View recycle(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        ViewGroup viewGroupParentAsViewGroup = parentAsViewGroup(view);
        if (viewGroupParentAsViewGroup != null) {
            viewGroupParentAsViewGroup.endViewTransition(view);
            viewGroupParentAsViewGroup.removeView(view);
        }
        view.setVisibility(0);
        view.setTranslationY(0.0f);
        return view;
    }

    public static final Integer maybeBgColor(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Drawable background = view.getBackground();
        if (background instanceof ColorDrawable) {
            return Integer.valueOf(((ColorDrawable) background).getColor());
        }
        return null;
    }

    public static final ViewGroup asViewGroupOrNull(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        if (view instanceof ViewGroup) {
            return (ViewGroup) view;
        }
        return null;
    }
}
