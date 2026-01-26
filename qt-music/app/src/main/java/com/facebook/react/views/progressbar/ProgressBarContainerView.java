package com.facebook.react.views.progressbar;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.umeng.analytics.pro.f;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ProgressBarContainerView.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 %2\u00020\u0001:\u0001%B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\r\u0010\u001d\u001a\u00020\u001eH\u0000¢\u0006\u0002\b\u001fJ\u0010\u0010 \u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0017\u0010!\u001a\u00020\u001e2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0000¢\u0006\u0002\b$R\u001a\u0010\u0005\u001a\u00020\u0006X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0080\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0012\u001a\u00020\u0006X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\b\"\u0004\b\u0014\u0010\nR\u001a\u0010\u0015\u001a\u00020\u0016X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/facebook/react/views/progressbar/ProgressBarContainerView;", "Landroid/widget/FrameLayout;", f.X, "Landroid/content/Context;", "(Landroid/content/Context;)V", ReactProgressBarViewManager.PROP_ANIMATING, "", "getAnimating$ReactAndroid_release", "()Z", "setAnimating$ReactAndroid_release", "(Z)V", "color", "", "getColor$ReactAndroid_release", "()Ljava/lang/Integer;", "setColor$ReactAndroid_release", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", ReactProgressBarViewManager.PROP_INDETERMINATE, "getIndeterminate$ReactAndroid_release", "setIndeterminate$ReactAndroid_release", "progress", "", "getProgress$ReactAndroid_release", "()D", "setProgress$ReactAndroid_release", "(D)V", "progressBar", "Landroid/widget/ProgressBar;", "apply", "", "apply$ReactAndroid_release", "setColor", "setStyle", "styleName", "", "setStyle$ReactAndroid_release", "Companion", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ProgressBarContainerView extends FrameLayout {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final int MAX_PROGRESS = 1000;
    private boolean animating;
    private Integer color;
    private boolean indeterminate;
    private double progress;
    private ProgressBar progressBar;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProgressBarContainerView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.indeterminate = true;
        this.animating = true;
    }

    /* renamed from: getColor$ReactAndroid_release, reason: from getter */
    public final Integer getColor() {
        return this.color;
    }

    public final void setColor$ReactAndroid_release(Integer num) {
        this.color = num;
    }

    /* renamed from: getIndeterminate$ReactAndroid_release, reason: from getter */
    public final boolean getIndeterminate() {
        return this.indeterminate;
    }

    public final void setIndeterminate$ReactAndroid_release(boolean z) {
        this.indeterminate = z;
    }

    /* renamed from: getAnimating$ReactAndroid_release, reason: from getter */
    public final boolean getAnimating() {
        return this.animating;
    }

    public final void setAnimating$ReactAndroid_release(boolean z) {
        this.animating = z;
    }

    /* renamed from: getProgress$ReactAndroid_release, reason: from getter */
    public final double getProgress() {
        return this.progress;
    }

    public final void setProgress$ReactAndroid_release(double d) {
        this.progress = d;
    }

    public final void apply$ReactAndroid_release() {
        Unit unit;
        ProgressBar progressBar = this.progressBar;
        if (progressBar != null) {
            progressBar.setIndeterminate(this.indeterminate);
            setColor(progressBar);
            progressBar.setProgress((int) (this.progress * 1000));
            progressBar.setVisibility(this.animating ? 0 : 4);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            throw new JSApplicationIllegalArgumentException("setStyle() not called");
        }
    }

    public final void setStyle$ReactAndroid_release(String styleName) {
        ProgressBar progressBarCreateProgressBar = ReactProgressBarViewManager.INSTANCE.createProgressBar(getContext(), ReactProgressBarViewManager.INSTANCE.getStyleFromString$ReactAndroid_release(styleName));
        progressBarCreateProgressBar.setMax(1000);
        this.progressBar = progressBarCreateProgressBar;
        removeAllViews();
        addView(this.progressBar, new ViewGroup.LayoutParams(-1, -1));
    }

    private final void setColor(ProgressBar progressBar) {
        Drawable progressDrawable;
        Unit unit;
        if (progressBar.isIndeterminate()) {
            progressDrawable = progressBar.getIndeterminateDrawable();
        } else {
            progressDrawable = progressBar.getProgressDrawable();
        }
        if (progressDrawable == null) {
            return;
        }
        Integer num = this.color;
        if (num != null) {
            progressDrawable.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            progressDrawable.clearColorFilter();
        }
    }

    /* compiled from: ProgressBarContainerView.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/facebook/react/views/progressbar/ProgressBarContainerView$Companion;", "", "()V", "MAX_PROGRESS", "", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
