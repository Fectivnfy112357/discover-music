package com.facebook.react.uimanager.drawable;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import com.facebook.react.uimanager.style.BorderInsets;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;

/* compiled from: CompositeBackgroundDrawable.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0000\u0018\u00002\u00020\u0001BI\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\u0010\nJ\u0010\u0010\u0019\u001a\u00020\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007J\u0010\u0010\u001a\u001a\u00020\u00002\b\u0010\u001b\u001a\u0004\u0018\u00010\u0003J\"\u0010\u001c\u001a\u00020\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016¨\u0006\u001d"}, d2 = {"Lcom/facebook/react/uimanager/drawable/CompositeBackgroundDrawable;", "Landroid/graphics/drawable/LayerDrawable;", "originalBackground", "Landroid/graphics/drawable/Drawable;", "outerShadows", "", "cssBackground", "Lcom/facebook/react/uimanager/drawable/CSSBackgroundDrawable;", "feedbackUnderlay", "innerShadows", "(Landroid/graphics/drawable/Drawable;Ljava/util/List;Lcom/facebook/react/uimanager/drawable/CSSBackgroundDrawable;Landroid/graphics/drawable/Drawable;Ljava/util/List;)V", "borderInsets", "Lcom/facebook/react/uimanager/style/BorderInsets;", "getBorderInsets", "()Lcom/facebook/react/uimanager/style/BorderInsets;", "setBorderInsets", "(Lcom/facebook/react/uimanager/style/BorderInsets;)V", "getCssBackground", "()Lcom/facebook/react/uimanager/drawable/CSSBackgroundDrawable;", "getFeedbackUnderlay", "()Landroid/graphics/drawable/Drawable;", "getInnerShadows", "()Ljava/util/List;", "getOriginalBackground", "getOuterShadows", "withNewCssBackground", "withNewFeedbackUnderlay", "newUnderlay", "withNewShadows", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CompositeBackgroundDrawable extends LayerDrawable {
    private BorderInsets borderInsets;
    private final CSSBackgroundDrawable cssBackground;
    private final Drawable feedbackUnderlay;
    private final List<Drawable> innerShadows;
    private final Drawable originalBackground;
    private final List<Drawable> outerShadows;

    public CompositeBackgroundDrawable() {
        this(null, null, null, null, null, 31, null);
    }

    public final Drawable getOriginalBackground() {
        return this.originalBackground;
    }

    public /* synthetic */ CompositeBackgroundDrawable(Drawable drawable, List list, CSSBackgroundDrawable cSSBackgroundDrawable, Drawable drawable2, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : drawable, (i & 2) != 0 ? CollectionsKt.emptyList() : list, (i & 4) != 0 ? null : cSSBackgroundDrawable, (i & 8) == 0 ? drawable2 : null, (i & 16) != 0 ? CollectionsKt.emptyList() : list2);
    }

    public final List<Drawable> getOuterShadows() {
        return this.outerShadows;
    }

    public final CSSBackgroundDrawable getCssBackground() {
        return this.cssBackground;
    }

    public final Drawable getFeedbackUnderlay() {
        return this.feedbackUnderlay;
    }

    public final List<Drawable> getInnerShadows() {
        return this.innerShadows;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CompositeBackgroundDrawable(Drawable drawable, List<? extends Drawable> outerShadows, CSSBackgroundDrawable cSSBackgroundDrawable, Drawable drawable2, List<? extends Drawable> innerShadows) {
        Intrinsics.checkNotNullParameter(outerShadows, "outerShadows");
        Intrinsics.checkNotNullParameter(innerShadows, "innerShadows");
        SpreadBuilder spreadBuilder = new SpreadBuilder(5);
        spreadBuilder.add(drawable);
        spreadBuilder.addSpread(CollectionsKt.asReversed(outerShadows).toArray(new Drawable[0]));
        spreadBuilder.add(cSSBackgroundDrawable);
        spreadBuilder.add(drawable2);
        spreadBuilder.addSpread(CollectionsKt.asReversed(innerShadows).toArray(new Drawable[0]));
        super((Drawable[]) CollectionsKt.listOfNotNull(spreadBuilder.toArray(new Drawable[spreadBuilder.size()])).toArray(new Drawable[0]));
        this.originalBackground = drawable;
        this.outerShadows = outerShadows;
        this.cssBackground = cSSBackgroundDrawable;
        this.feedbackUnderlay = drawable2;
        this.innerShadows = innerShadows;
        setPaddingMode(1);
    }

    public final BorderInsets getBorderInsets() {
        return this.borderInsets;
    }

    public final void setBorderInsets(BorderInsets borderInsets) {
        this.borderInsets = borderInsets;
    }

    public final CompositeBackgroundDrawable withNewCssBackground(CSSBackgroundDrawable cssBackground) {
        return new CompositeBackgroundDrawable(this.originalBackground, this.outerShadows, cssBackground, this.feedbackUnderlay, this.innerShadows);
    }

    public final CompositeBackgroundDrawable withNewShadows(List<? extends Drawable> outerShadows, List<? extends Drawable> innerShadows) {
        Intrinsics.checkNotNullParameter(outerShadows, "outerShadows");
        Intrinsics.checkNotNullParameter(innerShadows, "innerShadows");
        return new CompositeBackgroundDrawable(this.originalBackground, outerShadows, this.cssBackground, this.feedbackUnderlay, innerShadows);
    }

    public final CompositeBackgroundDrawable withNewFeedbackUnderlay(Drawable newUnderlay) {
        return new CompositeBackgroundDrawable(this.originalBackground, this.outerShadows, this.cssBackground, newUnderlay, this.innerShadows);
    }
}
