package coil3.util;

import coil3.RealImageLoader;
import kotlin.Metadata;

/* compiled from: SystemCallbacks.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¨\u0006\u0004"}, d2 = {"SystemCallbacks", "Lcoil3/util/SystemCallbacks;", "imageLoader", "Lcoil3/RealImageLoader;", "coil-core_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SystemCallbacksKt {
    public static final SystemCallbacks SystemCallbacks(RealImageLoader realImageLoader) {
        return new AndroidSystemCallbacks(realImageLoader);
    }
}
