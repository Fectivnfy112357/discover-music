package coil3.size;

import coil3.size.Dimension;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* compiled from: Dimension.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0013\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004\u001a\u001b\u0010\u0005\u001a\u00020\u0003*\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\bH\u0086\b¨\u0006\t"}, d2 = {"Dimension", "Lcoil3/size/Dimension$Pixels;", "px", "", "(I)I", "pxOrElse", "Lcoil3/size/Dimension;", "block", "Lkotlin/Function0;", "coil-core_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class DimensionKt {
    public static final int Dimension(int i) {
        return Dimension.Pixels.m263constructorimpl(i);
    }

    public static final int pxOrElse(Dimension dimension, Function0<Integer> function0) {
        return dimension instanceof Dimension.Pixels ? ((Dimension.Pixels) dimension).m268unboximpl() : function0.invoke().intValue();
    }
}
