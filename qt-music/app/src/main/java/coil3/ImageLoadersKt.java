package coil3;

import android.content.Context;
import coil3.Extras;
import coil3.ImageLoader;
import coil3.RealImageLoader;
import com.umeng.analytics.pro.f;
import kotlin.Metadata;

/* compiled from: imageLoaders.kt */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\u0017\u0010\u0000\u001a\u00020\u00012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0002\u0010\u0005\u001a\u0012\u0010\u0006\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\b\u001a\u00020\t\"\u0018\u0010\u0006\u001a\u00020\t*\u00020\n8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\"\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"ImageLoader", "Lcoil3/ImageLoader;", f.X, "Landroid/content/Context;", "Lcoil3/PlatformContext;", "(Landroid/content/Context;)Lcoil3/ImageLoader;", "serviceLoaderEnabled", "Lcoil3/ImageLoader$Builder;", "enable", "", "Lcoil3/RealImageLoader$Options;", "getServiceLoaderEnabled", "(Lcoil3/RealImageLoader$Options;)Z", "serviceLoaderEnabledKey", "Lcoil3/Extras$Key;", "coil-core_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ImageLoadersKt {
    private static final Extras.Key<Boolean> serviceLoaderEnabledKey = new Extras.Key<>(true);

    public static final ImageLoader ImageLoader(Context context) {
        return new ImageLoader.Builder(context).build();
    }

    public static final ImageLoader.Builder serviceLoaderEnabled(ImageLoader.Builder builder, boolean z) {
        builder.getExtras().set(serviceLoaderEnabledKey, Boolean.valueOf(z));
        return builder;
    }

    public static final boolean getServiceLoaderEnabled(RealImageLoader.Options options) {
        return ((Boolean) ExtrasKt.getOrDefault(options.getDefaults().getExtras(), serviceLoaderEnabledKey)).booleanValue();
    }
}
