package coil3.svg.internal;

import android.graphics.RectF;
import coil3.Image;
import coil3.request.Options;
import coil3.svg.ImageRequests_androidKt;
import coil3.svg.SvgImage;
import com.caverock.androidsvg.RenderOptions;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import kotlin.Metadata;

/* compiled from: Svg.android.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\tH\u0016J\u0010\u0010\f\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0010\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\f\u001a\u00020\u00192\u0006\u0010\u0010\u001a\u00020\u0019H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\u0004\u0018\u00010\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcoil3/svg/internal/AndroidSvg;", "Lcoil3/svg/internal/Svg;", "svg", "Lcom/caverock/androidsvg/SVG;", "<init>", "(Lcom/caverock/androidsvg/SVG;)V", "renderOptions", "Lcom/caverock/androidsvg/RenderOptions;", "viewBox", "", "getViewBox", "()[F", "width", "", "getWidth", "()F", "height", "getHeight", "", "value", "", "options", "Lcoil3/request/Options;", "asImage", "Lcoil3/Image;", "", "coil-svg_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
final class AndroidSvg implements Svg {
    private RenderOptions renderOptions;
    private final SVG svg;

    public AndroidSvg(SVG svg) {
        this.svg = svg;
    }

    @Override // coil3.svg.internal.Svg
    public float[] getViewBox() {
        RectF documentViewBox = this.svg.getDocumentViewBox();
        if (documentViewBox != null) {
            return new float[]{documentViewBox.left, documentViewBox.top, documentViewBox.right, documentViewBox.bottom};
        }
        return null;
    }

    @Override // coil3.svg.internal.Svg
    public float getWidth() {
        return this.svg.getDocumentWidth();
    }

    @Override // coil3.svg.internal.Svg
    public float getHeight() {
        return this.svg.getDocumentHeight();
    }

    @Override // coil3.svg.internal.Svg
    public void viewBox(float[] value) {
        SVG svg = this.svg;
        float f = value[0];
        float f2 = value[1];
        svg.setDocumentViewBox(f, f2, value[2] - f, value[3] - f2);
    }

    @Override // coil3.svg.internal.Svg
    public void width(String value) throws SVGParseException {
        this.svg.setDocumentWidth(value);
    }

    @Override // coil3.svg.internal.Svg
    public void height(String value) throws SVGParseException {
        this.svg.setDocumentHeight(value);
    }

    @Override // coil3.svg.internal.Svg
    public void options(Options options) {
        String css = ImageRequests_androidKt.getCss(options);
        if (css != null) {
            RenderOptions renderOptions = new RenderOptions();
            renderOptions.css(css);
            this.renderOptions = renderOptions;
        }
    }

    @Override // coil3.svg.internal.Svg
    public Image asImage(int width, int height) {
        return new SvgImage(this.svg, this.renderOptions, width, height);
    }
}
