package coil3;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import kotlin.Metadata;

/* compiled from: Image.android.kt */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0004\u001a\u00020\u0005*\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007\u001a \u0010\b\u001a\u00020\u0001*\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000bH\u0007\u001a\"\u0010\b\u001a\u00020\u0001*\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000e\u001a\n\u0010\u0004\u001a\u00020\t*\u00020\u000f\u001a\u0012\u0010\u0004\u001a\u00020\t*\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u0012\u0010\u0010\u001a\u00020\u000f*\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0012*\n\u0010\u0000\"\u00020\u00012\u00020\u0001*\n\u0010\u0002\"\u00020\u00032\u00020\u0003¨\u0006\u0013"}, d2 = {"Bitmap", "Landroid/graphics/Bitmap;", "Canvas", "Landroid/graphics/Canvas;", "asImage", "Lcoil3/BitmapImage;", "shareable", "", "toBitmap", "Lcoil3/Image;", "width", "", "height", "config", "Landroid/graphics/Bitmap$Config;", "Landroid/graphics/drawable/Drawable;", "asDrawable", "resources", "Landroid/content/res/Resources;", "coil-core_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Image_androidKt {
    public static /* synthetic */ void Bitmap$annotations() {
    }

    public static final BitmapImage asImage(Bitmap bitmap) {
        return asImage$default(bitmap, false, 1, null);
    }

    public static final Bitmap toBitmap(Image image) {
        return toBitmap$default(image, 0, 0, 3, null);
    }

    public static final Bitmap toBitmap(Image image, int i) {
        return toBitmap$default(image, i, 0, 2, null);
    }

    public static /* synthetic */ BitmapImage asImage$default(Bitmap bitmap, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return asImage(bitmap, z);
    }

    public static final BitmapImage asImage(Bitmap bitmap, boolean z) {
        return new BitmapImage(bitmap, z);
    }

    public static final Bitmap toBitmap(Image image, int i, int i2) {
        Bitmap.Config config = image instanceof BitmapImage ? ((BitmapImage) image).getBitmap().getConfig() : null;
        if (config == null) {
            config = Bitmap.Config.ARGB_8888;
        }
        return toBitmap(image, i, i2, config);
    }

    public static final Bitmap toBitmap(Image image, int i, int i2, Bitmap.Config config) {
        if (image instanceof BitmapImage) {
            BitmapImage bitmapImage = (BitmapImage) image;
            if (bitmapImage.getBitmap().getWidth() == i && bitmapImage.getBitmap().getHeight() == i2 && bitmapImage.getBitmap().getConfig() == config) {
                return bitmapImage.getBitmap();
            }
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, config);
        image.draw(new Canvas(bitmapCreateBitmap));
        return bitmapCreateBitmap;
    }

    public static /* synthetic */ Bitmap toBitmap$default(Image image, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = image.getWidth();
        }
        if ((i3 & 2) != 0) {
            i2 = image.getHeight();
        }
        return toBitmap(image, i, i2);
    }

    public static final Image asImage(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return asImage$default(((BitmapDrawable) drawable).getBitmap(), false, 1, null);
        }
        return new DrawableImage(drawable, false);
    }

    public static final Image asImage(Drawable drawable, boolean z) {
        if (drawable instanceof BitmapDrawable) {
            return asImage(((BitmapDrawable) drawable).getBitmap(), z);
        }
        return new DrawableImage(drawable, z);
    }

    public static final Drawable asDrawable(Image image, Resources resources) {
        return image instanceof DrawableImage ? ((DrawableImage) image).getDrawable() : image instanceof BitmapImage ? new BitmapDrawable(resources, ((BitmapImage) image).getBitmap()) : new ImageDrawable(image);
    }
}
