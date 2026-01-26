package com.doublesymmetry.kotlinaudio.utils;

import android.net.Uri;
import com.facebook.common.util.UriUtil;
import com.facebook.react.modules.systeminfo.AndroidInfoHelpers;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Utils.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¨\u0006\u0004"}, d2 = {"isUriLocalFile", "", "uri", "Landroid/net/Uri;", "kotlin-audio_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class UtilsKt {
    public static final boolean isUriLocalFile(Uri uri) {
        if (uri == null) {
            return false;
        }
        String scheme = uri.getScheme();
        String host = uri.getHost();
        if ((Intrinsics.areEqual(scheme, UriUtil.HTTP_SCHEME) || Intrinsics.areEqual(scheme, UriUtil.HTTPS_SCHEME)) && (Intrinsics.areEqual(host, AndroidInfoHelpers.DEVICE_LOCALHOST) || Intrinsics.areEqual(host, "127.0.0.1") || Intrinsics.areEqual(host, "[::1]"))) {
            return false;
        }
        return scheme == null || Intrinsics.areEqual(scheme, "file") || Intrinsics.areEqual(scheme, UriUtil.QUALIFIED_RESOURCE_SCHEME) || Intrinsics.areEqual(scheme, "content") || Intrinsics.areEqual(scheme, "rawresource") || Intrinsics.areEqual(scheme, UriUtil.LOCAL_RESOURCE_SCHEME) || host == null;
    }
}
