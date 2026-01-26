package coil3.svg.internal;

import android.content.Context;
import kotlin.Metadata;

/* compiled from: utils.android.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u001c\u0010\u0000\u001a\u00020\u0001*\u00060\u0002j\u0002`\u00038@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"density", "", "Landroid/content/Context;", "Lcoil3/PlatformContext;", "getDensity", "(Landroid/content/Context;)F", "coil-svg_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Utils_androidKt {
    public static final float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }
}
