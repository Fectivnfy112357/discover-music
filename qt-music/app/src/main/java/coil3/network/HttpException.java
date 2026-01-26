package coil3.network;

import kotlin.Metadata;

/* compiled from: HttpException.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00060\u0001j\u0002`\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcoil3/network/HttpException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "response", "Lcoil3/network/NetworkResponse;", "<init>", "(Lcoil3/network/NetworkResponse;)V", "getResponse", "()Lcoil3/network/NetworkResponse;", "coil-network-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class HttpException extends RuntimeException {
    private final NetworkResponse response;

    public HttpException(NetworkResponse networkResponse) {
        super("HTTP " + networkResponse.getCode());
        this.response = networkResponse;
    }

    public final NetworkResponse getResponse() {
        return this.response;
    }
}
