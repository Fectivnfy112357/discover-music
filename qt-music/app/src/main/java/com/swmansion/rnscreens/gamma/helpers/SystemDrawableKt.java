package com.swmansion.rnscreens.gamma.helpers;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import androidx.appcompat.content.res.AppCompatResources;
import com.doublesymmetry.trackplayer.service.MusicService;
import com.swmansion.rnscreens.gamma.tabs.TabScreen;
import com.umeng.analytics.pro.f;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SystemDrawable.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u001c\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0000¨\u0006\u0006"}, d2 = {"getSystemDrawableResource", "Landroid/graphics/drawable/Drawable;", f.X, "Landroid/content/Context;", "iconName", "", "react-native-screens_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SystemDrawableKt {
    public static final Drawable getSystemDrawableResource(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (str == null) {
            return null;
        }
        int identifier = context.getResources().getIdentifier(str, "drawable", context.getPackageName());
        if (identifier > 0) {
            return AppCompatResources.getDrawable(context, identifier);
        }
        int identifier2 = context.getResources().getIdentifier(str, "drawable", MusicService.ANDROID_OPTIONS_KEY);
        if (identifier2 > 0) {
            return AppCompatResources.getDrawable(context, identifier2);
        }
        Log.w(TabScreen.TAG, "TabScreen could not resolve drawable resource with the name " + str);
        return null;
    }
}
