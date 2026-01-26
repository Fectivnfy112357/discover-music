package coil3.key;

import coil3.Uri;
import coil3.request.Options;
import coil3.util.Utils_androidKt;
import com.facebook.common.util.UriUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AndroidResourceUriKeyer.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lcoil3/key/AndroidResourceUriKeyer;", "Lcoil3/key/Keyer;", "Lcoil3/Uri;", "<init>", "()V", "key", "", "data", "options", "Lcoil3/request/Options;", "coil-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AndroidResourceUriKeyer implements Keyer<Uri> {
    @Override // coil3.key.Keyer
    public String key(Uri data, Options options) {
        if (Intrinsics.areEqual(data.getScheme(), UriUtil.QUALIFIED_RESOURCE_SCHEME)) {
            return new StringBuilder().append(data).append(':').append(Utils_androidKt.getNightMode(options.getContext().getResources().getConfiguration())).toString();
        }
        return null;
    }
}
