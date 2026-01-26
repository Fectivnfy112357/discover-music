package com.facebook.react.views.scroll;

import android.os.SystemClock;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: OnScrollDispatchHelper.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\r\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u0014"}, d2 = {"Lcom/facebook/react/views/scroll/OnScrollDispatchHelper;", "", "()V", "lastScrollEventTimeMs", "", "prevX", "", "prevY", "<set-?>", "", "xFlingVelocity", "getXFlingVelocity", "()F", "yFlingVelocity", "getYFlingVelocity", "onScrollChanged", "", "x", "y", "Companion", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OnScrollDispatchHelper {
    private static final Companion Companion = new Companion(null);
    private static final int MIN_EVENT_SEPARATION_MS = 10;
    private float xFlingVelocity;
    private float yFlingVelocity;
    private int prevX = Integer.MIN_VALUE;
    private int prevY = Integer.MIN_VALUE;
    private long lastScrollEventTimeMs = -11;

    public final float getXFlingVelocity() {
        return this.xFlingVelocity;
    }

    public final float getYFlingVelocity() {
        return this.yFlingVelocity;
    }

    public final boolean onScrollChanged(int x, int y) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        long j = this.lastScrollEventTimeMs;
        boolean z = (jUptimeMillis - j <= 10 && this.prevX == x && this.prevY == y) ? false : true;
        if (jUptimeMillis - j != 0) {
            this.xFlingVelocity = (x - this.prevX) / (jUptimeMillis - j);
            this.yFlingVelocity = (y - this.prevY) / (jUptimeMillis - j);
        }
        this.lastScrollEventTimeMs = jUptimeMillis;
        this.prevX = x;
        this.prevY = y;
        return z;
    }

    /* compiled from: OnScrollDispatchHelper.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/facebook/react/views/scroll/OnScrollDispatchHelper$Companion;", "", "()V", "MIN_EVENT_SEPARATION_MS", "", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
