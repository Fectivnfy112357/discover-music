package coil3.size;

import coil3.size.Dimension;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Size.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003\"\u0015\u0010\u0006\u001a\u00020\u0007*\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\b¨\u0006\t"}, d2 = {"Size", "Lcoil3/size/Size;", "width", "", "height", "Lcoil3/size/Dimension;", "isOriginal", "", "(Lcoil3/size/Size;)Z", "coil-core_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SizeKt {
    public static final Size Size(int i, Dimension dimension) {
        return new Size(Dimension.Pixels.m262boximpl(DimensionKt.Dimension(i)), dimension);
    }

    public static final Size Size(Dimension dimension, int i) {
        return new Size(dimension, Dimension.Pixels.m262boximpl(DimensionKt.Dimension(i)));
    }

    public static final Size Size(int i, int i2) {
        return new Size(Dimension.Pixels.m262boximpl(DimensionKt.Dimension(i)), Dimension.Pixels.m262boximpl(DimensionKt.Dimension(i2)));
    }

    public static final boolean isOriginal(Size size) {
        return Intrinsics.areEqual(size, Size.ORIGINAL);
    }
}
