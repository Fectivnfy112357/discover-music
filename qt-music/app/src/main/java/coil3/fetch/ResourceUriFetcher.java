package coil3.fetch;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import coil3.ImageLoader;
import coil3.Image_androidKt;
import coil3.Uri;
import coil3.UriKt;
import coil3.decode.DataSource;
import coil3.decode.ImageSourceKt;
import coil3.decode.ResourceMetadata;
import coil3.fetch.Fetcher;
import coil3.request.ImageRequests_androidKt;
import coil3.request.Options;
import coil3.size.Precision;
import coil3.util.ContextsKt;
import coil3.util.DrawableUtils;
import coil3.util.MimeTypeMap;
import coil3.util.UtilsKt;
import coil3.util.Utils_androidKt;
import com.facebook.common.util.UriUtil;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okio.Okio;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: ResourceUriFetcher.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001:\u0001\rB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\b\u001a\u00020\tH\u0096@¢\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcoil3/fetch/ResourceUriFetcher;", "Lcoil3/fetch/Fetcher;", "data", "Lcoil3/Uri;", "options", "Lcoil3/request/Options;", "<init>", "(Lcoil3/Uri;Lcoil3/request/Options;)V", "fetch", "Lcoil3/fetch/FetchResult;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "throwInvalidUriException", "", "Factory", "coil-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ResourceUriFetcher implements Fetcher {
    private final Uri data;
    private final Options options;

    public ResourceUriFetcher(Uri uri, Options options) {
        this.data = uri;
        this.options = options;
    }

    @Override // coil3.fetch.Fetcher
    public Object fetch(Continuation<? super FetchResult> continuation) throws XmlPullParserException, Resources.NotFoundException, PackageManager.NameNotFoundException {
        Integer intOrNull;
        Resources resourcesForApplication;
        Drawable xmlDrawableCompat;
        String authority = this.data.getAuthority();
        if (authority != null) {
            if (StringsKt.isBlank(authority)) {
                authority = null;
            }
            if (authority != null) {
                String str = (String) CollectionsKt.lastOrNull((List) UriKt.getPathSegments(this.data));
                if (str == null || (intOrNull = StringsKt.toIntOrNull(str)) == null) {
                    throwInvalidUriException(this.data);
                    throw new KotlinNothingValueException();
                }
                int iIntValue = intOrNull.intValue();
                Context context = this.options.getContext();
                if (Intrinsics.areEqual(authority, context.getPackageName())) {
                    resourcesForApplication = context.getResources();
                } else {
                    resourcesForApplication = context.getPackageManager().getResourcesForApplication(authority);
                }
                TypedValue typedValue = new TypedValue();
                resourcesForApplication.getValue(iIntValue, typedValue, true);
                String mimeTypeFromUrl = MimeTypeMap.INSTANCE.getMimeTypeFromUrl(typedValue.string.toString());
                if (Intrinsics.areEqual(mimeTypeFromUrl, UtilsKt.MIME_TYPE_XML)) {
                    if (Intrinsics.areEqual(authority, context.getPackageName())) {
                        xmlDrawableCompat = ContextsKt.getDrawableCompat(context, iIntValue);
                    } else {
                        xmlDrawableCompat = ContextsKt.getXmlDrawableCompat(context, resourcesForApplication, iIntValue);
                    }
                    BitmapDrawable bitmapDrawable = xmlDrawableCompat;
                    boolean zIsVector = Utils_androidKt.isVector(bitmapDrawable);
                    if (zIsVector) {
                        bitmapDrawable = new BitmapDrawable(context.getResources(), DrawableUtils.INSTANCE.convertToBitmap(bitmapDrawable, ImageRequests_androidKt.getBitmapConfig(this.options), this.options.getSize(), this.options.getScale(), this.options.getPrecision() == Precision.INEXACT));
                    }
                    return new ImageFetchResult(Image_androidKt.asImage(bitmapDrawable), zIsVector, DataSource.DISK);
                }
                TypedValue typedValue2 = new TypedValue();
                return new SourceFetchResult(ImageSourceKt.ImageSource(Okio.buffer(Okio.source(resourcesForApplication.openRawResource(iIntValue, typedValue2))), this.options.getFileSystem(), new ResourceMetadata(authority, iIntValue, typedValue2.density)), mimeTypeFromUrl, DataSource.DISK);
            }
        }
        throwInvalidUriException(this.data);
        throw new KotlinNothingValueException();
    }

    private final Void throwInvalidUriException(Uri data) {
        throw new IllegalStateException("Invalid android.resource URI: " + data);
    }

    /* compiled from: ResourceUriFetcher.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\"\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\u0002H\u0002¨\u0006\u000e"}, d2 = {"Lcoil3/fetch/ResourceUriFetcher$Factory;", "Lcoil3/fetch/Fetcher$Factory;", "Lcoil3/Uri;", "<init>", "()V", "create", "Lcoil3/fetch/Fetcher;", "data", "options", "Lcoil3/request/Options;", "imageLoader", "Lcoil3/ImageLoader;", "isApplicable", "", "coil-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Factory implements Fetcher.Factory<Uri> {
        @Override // coil3.fetch.Fetcher.Factory
        public Fetcher create(Uri data, Options options, ImageLoader imageLoader) {
            if (isApplicable(data)) {
                return new ResourceUriFetcher(data, options);
            }
            return null;
        }

        private final boolean isApplicable(Uri data) {
            return Intrinsics.areEqual(data.getScheme(), UriUtil.QUALIFIED_RESOURCE_SCHEME);
        }
    }
}
