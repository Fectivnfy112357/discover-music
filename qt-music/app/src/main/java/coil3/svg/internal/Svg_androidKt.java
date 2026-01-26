package coil3.svg.internal;

import com.caverock.androidsvg.SVG;
import kotlin.Metadata;
import okio.BufferedSource;

/* compiled from: Svg.android.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¨\u0006\u0004"}, d2 = {"parseSvg", "Lcoil3/svg/internal/Svg;", "source", "Lokio/BufferedSource;", "coil-svg_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Svg_androidKt {
    public static final Svg parseSvg(BufferedSource bufferedSource) {
        return new AndroidSvg(SVG.getFromInputStream(bufferedSource.inputStream()));
    }
}
