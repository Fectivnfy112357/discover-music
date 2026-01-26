package com.facebook.react.views.scroll;

import android.view.MotionEvent;
import android.view.VelocityTracker;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VelocityHelper.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001e\u0010\n\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0010"}, d2 = {"Lcom/facebook/react/views/scroll/VelocityHelper;", "", "()V", "velocityTracker", "Landroid/view/VelocityTracker;", "<set-?>", "", "xVelocity", "getXVelocity", "()F", "yVelocity", "getYVelocity", "calculateVelocity", "", "ev", "Landroid/view/MotionEvent;", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class VelocityHelper {
    private VelocityTracker velocityTracker;
    private float xVelocity;
    private float yVelocity;

    public final float getXVelocity() {
        return this.xVelocity;
    }

    public final float getYVelocity() {
        return this.yVelocity;
    }

    public final void calculateVelocity(MotionEvent ev) {
        Intrinsics.checkNotNullParameter(ev, "ev");
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker != null) {
            velocityTracker.addMovement(ev);
            int action = ev.getAction() & 255;
            if (action == 1 || action == 3) {
                velocityTracker.computeCurrentVelocity(1);
                this.xVelocity = velocityTracker.getXVelocity();
                this.yVelocity = velocityTracker.getYVelocity();
                velocityTracker.recycle();
                this.velocityTracker = null;
            }
        }
    }
}
