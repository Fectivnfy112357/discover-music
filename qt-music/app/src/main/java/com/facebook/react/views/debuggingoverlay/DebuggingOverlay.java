package com.facebook.react.views.debuggingoverlay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import com.facebook.react.bridge.UiThreadUtil;
import com.umeng.analytics.pro.f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DebuggingOverlay.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0012\u001a\u00020\u0013H\u0007J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0016\u0010\u0017\u001a\u00020\u00132\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0007J\u0016\u0010\u0019\u001a\u00020\u00132\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00110\u001bH\u0007R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000bj\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u0010\u001a\u001e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00110\u000bj\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u0011`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/facebook/react/views/debuggingoverlay/DebuggingOverlay;", "Landroid/view/View;", f.X, "Landroid/content/Context;", "(Landroid/content/Context;)V", "highlightedElementsPaint", "Landroid/graphics/Paint;", "highlightedElementsRectangles", "", "Landroid/graphics/RectF;", "traceUpdateIdToCleanupRunnableMap", "Ljava/util/HashMap;", "", "Ljava/lang/Runnable;", "Lkotlin/collections/HashMap;", "traceUpdatePaint", "traceUpdatesToDisplayMap", "Lcom/facebook/react/views/debuggingoverlay/TraceUpdate;", "clearElementsHighlights", "", "onDraw", "canvas", "Landroid/graphics/Canvas;", "setHighlightedElementsRectangles", "elementsRectangles", "setTraceUpdates", "traceUpdates", "", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DebuggingOverlay extends View {
    private final Paint highlightedElementsPaint;
    private List<RectF> highlightedElementsRectangles;
    private final HashMap<Integer, Runnable> traceUpdateIdToCleanupRunnableMap;
    private final Paint traceUpdatePaint;
    private final HashMap<Integer, TraceUpdate> traceUpdatesToDisplayMap;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DebuggingOverlay(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Paint paint = new Paint();
        this.traceUpdatePaint = paint;
        this.traceUpdatesToDisplayMap = new HashMap<>();
        this.traceUpdateIdToCleanupRunnableMap = new HashMap<>();
        Paint paint2 = new Paint();
        this.highlightedElementsPaint = paint2;
        this.highlightedElementsRectangles = new ArrayList();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(6.0f);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setColor(-859248897);
    }

    public final void setTraceUpdates(List<TraceUpdate> traceUpdates) {
        Intrinsics.checkNotNullParameter(traceUpdates, "traceUpdates");
        for (TraceUpdate traceUpdate : traceUpdates) {
            int id = traceUpdate.getId();
            if (this.traceUpdateIdToCleanupRunnableMap.containsKey(Integer.valueOf(id))) {
                UiThreadUtil.removeOnUiThread(this.traceUpdateIdToCleanupRunnableMap.get(Integer.valueOf(id)));
                this.traceUpdateIdToCleanupRunnableMap.remove(Integer.valueOf(id));
            }
            this.traceUpdatesToDisplayMap.put(Integer.valueOf(id), traceUpdate);
        }
        invalidate();
    }

    public final void setHighlightedElementsRectangles(List<RectF> elementsRectangles) {
        Intrinsics.checkNotNullParameter(elementsRectangles, "elementsRectangles");
        this.highlightedElementsRectangles = elementsRectangles;
        invalidate();
    }

    public final void clearElementsHighlights() {
        this.highlightedElementsRectangles.clear();
        invalidate();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        for (TraceUpdate traceUpdate : this.traceUpdatesToDisplayMap.values()) {
            this.traceUpdatePaint.setColor(traceUpdate.getColor());
            canvas.drawRect(traceUpdate.getRectangle(), this.traceUpdatePaint);
            final int id = traceUpdate.getId();
            Runnable runnable = new Runnable() { // from class: com.facebook.react.views.debuggingoverlay.DebuggingOverlay$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DebuggingOverlay.onDraw$lambda$0(this.f$0, id);
                }
            };
            if (!this.traceUpdateIdToCleanupRunnableMap.containsKey(Integer.valueOf(id))) {
                this.traceUpdateIdToCleanupRunnableMap.put(Integer.valueOf(id), runnable);
                UiThreadUtil.runOnUiThread(runnable, 2000L);
            }
        }
        Iterator<RectF> it = this.highlightedElementsRectangles.iterator();
        while (it.hasNext()) {
            canvas.drawRect(it.next(), this.highlightedElementsPaint);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onDraw$lambda$0(DebuggingOverlay this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.traceUpdatesToDisplayMap.remove(Integer.valueOf(i));
        this$0.traceUpdateIdToCleanupRunnableMap.remove(Integer.valueOf(i));
        this$0.invalidate();
    }
}
