package coil3;

import coil3.Extras;
import coil3.ImageLoader;
import coil3.RealImageLoader;
import coil3.decode.ExifOrientationStrategy;
import kotlin.Metadata;

/* compiled from: imageLoaders.android.kt */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0012\u0010\t\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b\"\u0018\u0010\u0000\u001a\u00020\u0003*\u00020\u00048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\"\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\bX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0018\u0010\t\u001a\u00020\u000b*\u00020\u00048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r\"\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"bitmapFactoryMaxParallelism", "Lcoil3/ImageLoader$Builder;", "maxParallelism", "", "Lcoil3/RealImageLoader$Options;", "getBitmapFactoryMaxParallelism", "(Lcoil3/RealImageLoader$Options;)I", "bitmapFactoryMaxParallelismKey", "Lcoil3/Extras$Key;", "bitmapFactoryExifOrientationStrategy", "strategy", "Lcoil3/decode/ExifOrientationStrategy;", "getBitmapFactoryExifOrientationStrategy", "(Lcoil3/RealImageLoader$Options;)Lcoil3/decode/ExifOrientationStrategy;", "bitmapFactoryExifOrientationStrategyKey", "coil-core_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ImageLoaders_androidKt {
    private static final Extras.Key<Integer> bitmapFactoryMaxParallelismKey = new Extras.Key<>(4);
    private static final Extras.Key<ExifOrientationStrategy> bitmapFactoryExifOrientationStrategyKey = new Extras.Key<>(ExifOrientationStrategy.RESPECT_PERFORMANCE);

    public static final ImageLoader.Builder bitmapFactoryMaxParallelism(ImageLoader.Builder builder, int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxParallelism must be > 0.".toString());
        }
        builder.getExtras().set(bitmapFactoryMaxParallelismKey, Integer.valueOf(i));
        return builder;
    }

    public static final int getBitmapFactoryMaxParallelism(RealImageLoader.Options options) {
        return ((Number) ExtrasKt.getOrDefault(options.getDefaults().getExtras(), bitmapFactoryMaxParallelismKey)).intValue();
    }

    public static final ImageLoader.Builder bitmapFactoryExifOrientationStrategy(ImageLoader.Builder builder, ExifOrientationStrategy exifOrientationStrategy) {
        builder.getExtras().set(bitmapFactoryExifOrientationStrategyKey, exifOrientationStrategy);
        return builder;
    }

    public static final ExifOrientationStrategy getBitmapFactoryExifOrientationStrategy(RealImageLoader.Options options) {
        return (ExifOrientationStrategy) ExtrasKt.getOrDefault(options.getDefaults().getExtras(), bitmapFactoryExifOrientationStrategyKey);
    }
}
