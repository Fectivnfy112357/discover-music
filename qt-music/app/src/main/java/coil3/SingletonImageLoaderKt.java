package coil3;

import android.content.Context;
import coil3.Extras;
import coil3.ImageLoader;
import coil3.SingletonImageLoader;
import kotlin.Metadata;
import kotlin.Unit;

/* compiled from: SingletonImageLoader.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0018\u0010\u0002\u001a\u00020\u0003*\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0005\"\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"DefaultSingletonImageLoaderFactory", "Lcoil3/SingletonImageLoader$Factory;", "isDefault", "", "Lcoil3/ImageLoader;", "(Lcoil3/ImageLoader;)Z", "DefaultSingletonImageLoaderKey", "Lcoil3/Extras$Key;", "", "coil_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SingletonImageLoaderKt {
    private static final SingletonImageLoader.Factory DefaultSingletonImageLoaderFactory = new SingletonImageLoader.Factory() { // from class: coil3.SingletonImageLoaderKt$$ExternalSyntheticLambda0
        @Override // coil3.SingletonImageLoader.Factory
        public final ImageLoader newImageLoader(Context context) {
            return SingletonImageLoaderKt.DefaultSingletonImageLoaderFactory$lambda$1(context);
        }
    };
    private static final Extras.Key<Unit> DefaultSingletonImageLoaderKey = new Extras.Key<>(Unit.INSTANCE);

    /* JADX INFO: Access modifiers changed from: private */
    public static final ImageLoader DefaultSingletonImageLoaderFactory$lambda$1(Context context) {
        ImageLoader.Builder builder = new ImageLoader.Builder(context);
        builder.getExtras().set(DefaultSingletonImageLoaderKey, Unit.INSTANCE);
        return builder.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isDefault(ImageLoader imageLoader) {
        return imageLoader.getDefaults().getExtras().get(DefaultSingletonImageLoaderKey) != null;
    }
}
