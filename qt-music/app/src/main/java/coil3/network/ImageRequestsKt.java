package coil3.network;

import coil3.Extras;
import coil3.ExtrasKt;
import coil3.network.internal.UtilsKt;
import coil3.request.ImageRequest;
import coil3.request.Options;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: imageRequests.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0012\u0010\r\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000f\u001a\u0012\u0010\u0014\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0016\"\u0015\u0010\u0000\u001a\u00020\u0003*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\"\u0015\u0010\u0000\u001a\u00020\u0003*\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\b\"\u001b\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00030\t*\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u000b\"\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\tX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0015\u0010\r\u001a\u00020\u000f*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011\"\u0015\u0010\r\u001a\u00020\u000f*\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0012\"\u001b\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\t*\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000b\"\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000f0\tX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0017\u0010\u0014\u001a\u0004\u0018\u00010\u0016*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018\"\u0017\u0010\u0014\u001a\u0004\u0018\u00010\u0016*\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0019\"\u001d\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\t*\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u000b\"\u0016\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"httpMethod", "Lcoil3/request/ImageRequest$Builder;", "method", "", "Lcoil3/request/ImageRequest;", "getHttpMethod", "(Lcoil3/request/ImageRequest;)Ljava/lang/String;", "Lcoil3/request/Options;", "(Lcoil3/request/Options;)Ljava/lang/String;", "Lcoil3/Extras$Key;", "Lcoil3/Extras$Key$Companion;", "(Lcoil3/Extras$Key$Companion;)Lcoil3/Extras$Key;", "httpMethodKey", "httpHeaders", "headers", "Lcoil3/network/NetworkHeaders;", "getHttpHeaders", "(Lcoil3/request/ImageRequest;)Lcoil3/network/NetworkHeaders;", "(Lcoil3/request/Options;)Lcoil3/network/NetworkHeaders;", "httpHeadersKey", "httpBody", "body", "Lcoil3/network/NetworkRequestBody;", "getHttpBody", "(Lcoil3/request/ImageRequest;)Lcoil3/network/NetworkRequestBody;", "(Lcoil3/request/Options;)Lcoil3/network/NetworkRequestBody;", "httpBodyKey", "coil-network-core_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ImageRequestsKt {
    private static final Extras.Key<String> httpMethodKey = new Extras.Key<>(UtilsKt.HTTP_METHOD_GET);
    private static final Extras.Key<NetworkHeaders> httpHeadersKey = new Extras.Key<>(NetworkHeaders.EMPTY);
    private static final Extras.Key<NetworkRequestBody> httpBodyKey = new Extras.Key<>(null);

    public static final ImageRequest.Builder httpMethod(ImageRequest.Builder builder, String str) {
        Extras.Builder extras = builder.getExtras();
        Extras.Key<String> key = httpMethodKey;
        String upperCase = str.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        extras.set(key, upperCase);
        return builder;
    }

    public static final String getHttpMethod(ImageRequest imageRequest) {
        return (String) ExtrasKt.getExtra(imageRequest, httpMethodKey);
    }

    public static final String getHttpMethod(Options options) {
        return (String) ExtrasKt.getExtra(options, httpMethodKey);
    }

    public static final Extras.Key<String> getHttpMethod(Extras.Key.Companion companion) {
        return httpMethodKey;
    }

    public static final ImageRequest.Builder httpHeaders(ImageRequest.Builder builder, NetworkHeaders networkHeaders) {
        builder.getExtras().set(httpHeadersKey, networkHeaders);
        return builder;
    }

    public static final NetworkHeaders getHttpHeaders(ImageRequest imageRequest) {
        return (NetworkHeaders) ExtrasKt.getExtra(imageRequest, httpHeadersKey);
    }

    public static final NetworkHeaders getHttpHeaders(Options options) {
        return (NetworkHeaders) ExtrasKt.getExtra(options, httpHeadersKey);
    }

    public static final Extras.Key<NetworkHeaders> getHttpHeaders(Extras.Key.Companion companion) {
        return httpHeadersKey;
    }

    public static final ImageRequest.Builder httpBody(ImageRequest.Builder builder, NetworkRequestBody networkRequestBody) {
        builder.getExtras().set(httpBodyKey, networkRequestBody);
        return builder;
    }

    public static final NetworkRequestBody getHttpBody(ImageRequest imageRequest) {
        return (NetworkRequestBody) ExtrasKt.getExtra(imageRequest, httpBodyKey);
    }

    public static final NetworkRequestBody getHttpBody(Options options) {
        return (NetworkRequestBody) ExtrasKt.getExtra(options, httpBodyKey);
    }

    public static final Extras.Key<NetworkRequestBody> getHttpBody(Extras.Key.Companion companion) {
        return httpBodyKey;
    }
}
