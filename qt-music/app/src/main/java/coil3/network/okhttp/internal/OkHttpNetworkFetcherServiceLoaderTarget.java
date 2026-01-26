package coil3.network.okhttp.internal;

import coil3.Uri;
import coil3.fetch.Fetcher;
import coil3.network.okhttp.OkHttpNetworkFetcher;
import coil3.util.FetcherServiceLoaderTarget;
import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* compiled from: OkHttpNetworkFetcherServiceLoaderTarget.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lcoil3/network/okhttp/internal/OkHttpNetworkFetcherServiceLoaderTarget;", "Lcoil3/util/FetcherServiceLoaderTarget;", "Lcoil3/Uri;", "<init>", "()V", "factory", "Lcoil3/network/NetworkFetcher$Factory;", "type", "Lkotlin/reflect/KClass;", "priority", "", "coil-network-okhttp"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class OkHttpNetworkFetcherServiceLoaderTarget implements FetcherServiceLoaderTarget<Uri> {
    @Override // coil3.util.FetcherServiceLoaderTarget
    public int priority() {
        return 2;
    }

    @Override // coil3.util.FetcherServiceLoaderTarget
    public KClass<Uri> type() {
        return Reflection.getOrCreateKotlinClass(Uri.class);
    }

    @Override // coil3.util.FetcherServiceLoaderTarget
    public Fetcher.Factory<Uri> factory() {
        return OkHttpNetworkFetcher.factory();
    }
}
