package com.facebook.react.views.debuggingoverlay;

import android.graphics.RectF;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TraceUpdate.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/facebook/react/views/debuggingoverlay/TraceUpdate;", "", "id", "", "rectangle", "Landroid/graphics/RectF;", "color", "(ILandroid/graphics/RectF;I)V", "getColor", "()I", "getId", "getRectangle", "()Landroid/graphics/RectF;", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class TraceUpdate {
    private final int color;
    private final int id;
    private final RectF rectangle;

    public TraceUpdate(int i, RectF rectangle, int i2) {
        Intrinsics.checkNotNullParameter(rectangle, "rectangle");
        this.id = i;
        this.rectangle = rectangle;
        this.color = i2;
    }

    public final int getColor() {
        return this.color;
    }

    public final int getId() {
        return this.id;
    }

    public final RectF getRectangle() {
        return this.rectangle;
    }
}
