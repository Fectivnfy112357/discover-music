package coil3.size;

import kotlin.Metadata;

/* compiled from: SizeResolver.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0004"}, d2 = {"SizeResolver", "Lcoil3/size/SizeResolver;", "size", "Lcoil3/size/Size;", "coil-core_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SizeResolverKt {
    public static final SizeResolver SizeResolver(Size size) {
        return new RealSizeResolver(size);
    }
}
