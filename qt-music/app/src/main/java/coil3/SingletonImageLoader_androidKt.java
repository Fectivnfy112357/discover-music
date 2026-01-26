package coil3;

import android.content.Context;
import coil3.SingletonImageLoader;
import kotlin.Metadata;

/* compiled from: SingletonImageLoader.android.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00060\u0002j\u0002`\u0003H\u0000¨\u0006\u0004"}, d2 = {"applicationImageLoaderFactory", "Lcoil3/SingletonImageLoader$Factory;", "Landroid/content/Context;", "Lcoil3/PlatformContext;", "coil_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SingletonImageLoader_androidKt {
    public static final SingletonImageLoader.Factory applicationImageLoaderFactory(Context context) {
        Object applicationContext = context.getApplicationContext();
        if (applicationContext instanceof SingletonImageLoader.Factory) {
            return (SingletonImageLoader.Factory) applicationContext;
        }
        return null;
    }
}
