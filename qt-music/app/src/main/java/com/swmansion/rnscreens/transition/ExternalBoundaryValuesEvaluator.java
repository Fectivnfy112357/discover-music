package com.swmansion.rnscreens.transition;

import android.animation.FloatEvaluator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExternalBoundaryValuesEvaluator.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0004\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0015\u0018\u00002\u00020\u0001B=\u0012\u001a\u0010\u0002\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003j\u0002`\u0006\u0012\u001a\u0010\u0007\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003j\u0002`\u0006¢\u0006\u0002\u0010\bJ+\u0010\u0014\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0015\u001a\u00020\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u00042\b\u0010\u0017\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0002\u0010\u0018J\u0014\u0010\u0019\u001a\u0004\u0018\u00010\u00042\b\u0010\u0017\u001a\u0004\u0018\u00010\u0004H\u0002J\u0014\u0010\u001a\u001a\u0004\u0018\u00010\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u0004H\u0002R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR%\u0010\u0007\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003j\u0002`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000b\"\u0004\b\u0012\u0010\rR%\u0010\u0002\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003j\u0002`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/swmansion/rnscreens/transition/ExternalBoundaryValuesEvaluator;", "Landroid/animation/FloatEvaluator;", "startValueProvider", "Lkotlin/Function1;", "", "", "Lcom/swmansion/rnscreens/transition/BoundaryValueProviderFn;", "endValueProvider", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "endValueCache", "getEndValueCache", "()Ljava/lang/Number;", "setEndValueCache", "(Ljava/lang/Number;)V", "getEndValueProvider", "()Lkotlin/jvm/functions/Function1;", "startValueCache", "getStartValueCache", "setStartValueCache", "getStartValueProvider", "evaluate", "fraction", "startValue", "endValue", "(FLjava/lang/Number;Ljava/lang/Number;)Ljava/lang/Float;", "getEndValue", "getStartValue", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ExternalBoundaryValuesEvaluator extends FloatEvaluator {
    private Number endValueCache;
    private final Function1<Number, Float> endValueProvider;
    private Number startValueCache;
    private final Function1<Number, Float> startValueProvider;

    public final Function1<Number, Float> getStartValueProvider() {
        return this.startValueProvider;
    }

    public final Function1<Number, Float> getEndValueProvider() {
        return this.endValueProvider;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ExternalBoundaryValuesEvaluator(Function1<? super Number, Float> startValueProvider, Function1<? super Number, Float> endValueProvider) {
        Intrinsics.checkNotNullParameter(startValueProvider, "startValueProvider");
        Intrinsics.checkNotNullParameter(endValueProvider, "endValueProvider");
        this.startValueProvider = startValueProvider;
        this.endValueProvider = endValueProvider;
    }

    public final Number getStartValueCache() {
        return this.startValueCache;
    }

    public final void setStartValueCache(Number number) {
        this.startValueCache = number;
    }

    public final Number getEndValueCache() {
        return this.endValueCache;
    }

    public final void setEndValueCache(Number number) {
        this.endValueCache = number;
    }

    private final Number getStartValue(Number startValue) {
        if (this.startValueCache == null) {
            this.startValueCache = this.startValueProvider.invoke(startValue);
        }
        return this.startValueCache;
    }

    private final Number getEndValue(Number endValue) {
        if (this.endValueCache == null) {
            this.endValueCache = this.endValueProvider.invoke(endValue);
        }
        return this.endValueCache;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.animation.TypeEvaluator
    public Float evaluate(float fraction, Number startValue, Number endValue) {
        Number startValue2 = getStartValue(startValue);
        Number endValue2 = getEndValue(endValue);
        if (startValue2 == null || endValue2 == null) {
            return null;
        }
        return super.evaluate(fraction, startValue2, endValue2);
    }
}
