package com.facebook.react.views.text.internal.span;

import android.graphics.Paint;
import android.text.style.LineHeightSpan;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CustomLineHeightSpan.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J:\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0014"}, d2 = {"Lcom/facebook/react/views/text/internal/span/CustomLineHeightSpan;", "Landroid/text/style/LineHeightSpan;", "Lcom/facebook/react/views/text/internal/span/ReactSpan;", "height", "", "(F)V", ViewProps.LINE_HEIGHT, "", "getLineHeight", "()I", "chooseHeight", "", "text", "", "start", "end", "spanstartv", "v", "fm", "Landroid/graphics/Paint$FontMetricsInt;", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CustomLineHeightSpan implements LineHeightSpan, ReactSpan {
    private final int lineHeight;

    public CustomLineHeightSpan(float f) {
        this.lineHeight = (int) Math.ceil(f);
    }

    public final int getLineHeight() {
        return this.lineHeight;
    }

    @Override // android.text.style.LineHeightSpan
    public void chooseHeight(CharSequence text, int start, int end, int spanstartv, int v, Paint.FontMetricsInt fm) {
        Intrinsics.checkNotNullParameter(fm, "fm");
        int i = fm.descent;
        int i2 = this.lineHeight;
        if (i > i2) {
            fm.descent = (int) Math.min(i2, fm.descent);
            fm.bottom = fm.descent;
            fm.ascent = 0;
            fm.top = fm.ascent;
            return;
        }
        if ((-fm.ascent) + fm.descent > this.lineHeight) {
            fm.bottom = fm.descent;
            fm.ascent = (-this.lineHeight) + fm.descent;
            fm.top = fm.ascent;
            return;
        }
        if ((-fm.ascent) + fm.bottom > this.lineHeight) {
            fm.top = fm.ascent;
            fm.bottom = fm.ascent + this.lineHeight;
            return;
        }
        if ((-fm.top) + fm.bottom > this.lineHeight) {
            fm.top = fm.bottom - this.lineHeight;
            return;
        }
        double d = (r2 - ((-fm.top) + fm.bottom)) / 2.0f;
        int iCeil = (int) (fm.top - ((float) Math.ceil(d)));
        int iFloor = (int) (fm.bottom + ((float) Math.floor(d)));
        fm.top = iCeil;
        fm.ascent = iCeil;
        fm.descent = iFloor;
        fm.bottom = iFloor;
    }
}
