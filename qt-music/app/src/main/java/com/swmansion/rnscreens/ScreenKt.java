package com.swmansion.rnscreens;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Screen.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"asScreen", "Lcom/swmansion/rnscreens/Screen;", "Landroid/view/View;", "react-native-screens_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ScreenKt {
    public static final Screen asScreen(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return (Screen) view;
    }
}
