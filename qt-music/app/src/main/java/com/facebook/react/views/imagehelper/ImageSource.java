package com.facebook.react.views.imagehelper;

import android.content.Context;
import android.net.Uri;
import com.umeng.analytics.pro.f;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImageSource.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\b\u0016\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB-\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\u0010\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0013\u0010\u0019\u001a\u00020\u000b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u0014X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001e"}, d2 = {"Lcom/facebook/react/views/imagehelper/ImageSource;", "", f.X, "Landroid/content/Context;", "source", "", "width", "", "height", "(Landroid/content/Context;Ljava/lang/String;DD)V", "_isResource", "", "isResource", "()Z", "size", "getSize", "()D", "getSource", "()Ljava/lang/String;", "uri", "Landroid/net/Uri;", "getUri", "()Landroid/net/Uri;", "computeLocalUri", "computeUri", "equals", "other", "hashCode", "", "Companion", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class ImageSource {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TRANSPARENT_BITMAP_URI = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNkYAAAAAYAAjCB0C8AAAAASUVORK5CYII=";
    private boolean _isResource;
    private final double size;
    private final String source;
    private final Uri uri;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ImageSource(Context context, String str) {
        this(context, str, 0.0d, 0.0d, 12, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ImageSource(Context context, String str, double d) {
        this(context, str, d, 0.0d, 8, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @JvmStatic
    public static final ImageSource getTransparentBitmapImageSource(Context context) {
        return INSTANCE.getTransparentBitmapImageSource(context);
    }

    public ImageSource(Context context, String str, double d, double d2) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.source = str;
        this.uri = computeUri(context);
        this.size = d * d2;
    }

    public /* synthetic */ ImageSource(Context context, String str, double d, double d2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, str, (i & 4) != 0 ? 0.0d : d, (i & 8) != 0 ? 0.0d : d2);
    }

    public final String getSource() {
        return this.source;
    }

    public Uri getUri() {
        return this.uri;
    }

    public final double getSize() {
        return this.size;
    }

    /* renamed from: isResource, reason: from getter */
    public boolean get_isResource() {
        return this._isResource;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !Intrinsics.areEqual(getClass(), other.getClass())) {
            return false;
        }
        ImageSource imageSource = (ImageSource) other;
        return Double.compare(imageSource.size, this.size) == 0 && get_isResource() == imageSource.get_isResource() && Intrinsics.areEqual(getUri(), imageSource.getUri()) && Intrinsics.areEqual(this.source, imageSource.source);
    }

    public int hashCode() {
        return Objects.hash(getUri(), this.source, Double.valueOf(this.size), Boolean.valueOf(get_isResource()));
    }

    private final Uri computeUri(Context context) {
        try {
            Uri uriComputeLocalUri = Uri.parse(this.source);
            if (uriComputeLocalUri.getScheme() == null) {
                uriComputeLocalUri = computeLocalUri(context);
            }
            Intrinsics.checkNotNull(uriComputeLocalUri);
            return uriComputeLocalUri;
        } catch (NullPointerException unused) {
            return computeLocalUri(context);
        }
    }

    private final Uri computeLocalUri(Context context) {
        this._isResource = true;
        return ResourceDrawableIdHelper.INSTANCE.getInstance().getResourceDrawableUri(context, this.source);
    }

    /* compiled from: ImageSource.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/facebook/react/views/imagehelper/ImageSource$Companion;", "", "()V", "TRANSPARENT_BITMAP_URI", "", "getTransparentBitmapImageSource", "Lcom/facebook/react/views/imagehelper/ImageSource;", f.X, "Landroid/content/Context;", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final ImageSource getTransparentBitmapImageSource(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return new ImageSource(context, ImageSource.TRANSPARENT_BITMAP_URI, 0.0d, 0.0d, 12, null);
        }
    }
}
