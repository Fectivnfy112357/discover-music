package coil3;

import kotlin.Metadata;

/* compiled from: Uri.android.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0002*\u00020\u0001¨\u0006\u0004"}, d2 = {"toCoilUri", "Lcoil3/Uri;", "Landroid/net/Uri;", "toAndroidUri", "coil-core_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Uri_androidKt {
    public static final Uri toCoilUri(android.net.Uri uri) {
        return UriKt.toUri$default(uri.toString(), null, 1, null);
    }

    public static final android.net.Uri toAndroidUri(Uri uri) {
        return android.net.Uri.parse(uri.getData());
    }
}
