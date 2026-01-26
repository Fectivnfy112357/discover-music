package com.facebook.imagepipeline.instrumentation;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrescoInstrumenter.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0017B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0007J\u001a\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0010\u001a\u00020\u0011H\u0007J\u0014\u0010\u0012\u001a\u0004\u0018\u00010\u00012\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0007J\u001e\u0010\u0013\u001a\u0004\u0018\u00010\u00012\b\u0010\u000f\u001a\u0004\u0018\u00010\u00012\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0007J\u0012\u0010\u0014\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u0007J\u0012\u0010\u0015\u001a\u00020\u000e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0004H\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u00068G¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0007¨\u0006\u0018"}, d2 = {"Lcom/facebook/imagepipeline/instrumentation/FrescoInstrumenter;", "", "()V", "instance", "Lcom/facebook/imagepipeline/instrumentation/FrescoInstrumenter$Instrumenter;", "isTracing", "", "()Z", "decorateRunnable", "Ljava/lang/Runnable;", "runnable", "tag", "", "markFailure", "", "token", "th", "", "onBeforeSubmitWork", "onBeginWork", "onEndWork", "provide", "instrumenter", "Instrumenter", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FrescoInstrumenter {
    public static final FrescoInstrumenter INSTANCE = new FrescoInstrumenter();
    private static volatile Instrumenter instance;

    /* compiled from: FrescoInstrumenter.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH&J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH&J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00012\u0006\u0010\b\u001a\u00020\tH&J\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u00012\u0006\u0010\f\u001a\u00020\u00012\b\u0010\b\u001a\u0004\u0018\u00010\tH&J\u0010\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0001H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004¨\u0006\u0012"}, d2 = {"Lcom/facebook/imagepipeline/instrumentation/FrescoInstrumenter$Instrumenter;", "", "isTracing", "", "()Z", "decorateRunnable", "Ljava/lang/Runnable;", "runnable", "tag", "", "markFailure", "", "token", "th", "", "onBeforeSubmitWork", "onBeginWork", "onEndWork", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface Instrumenter {
        Runnable decorateRunnable(Runnable runnable, String tag);

        boolean isTracing();

        void markFailure(Object token, Throwable th);

        Object onBeforeSubmitWork(String tag);

        Object onBeginWork(Object token, String tag);

        void onEndWork(Object token);
    }

    private FrescoInstrumenter() {
    }

    @JvmStatic
    public static final void provide(Instrumenter instrumenter) {
        instance = instrumenter;
    }

    @JvmStatic
    public static final boolean isTracing() {
        Instrumenter instrumenter = instance;
        if (instrumenter == null) {
            return false;
        }
        return instrumenter.isTracing();
    }

    @JvmStatic
    public static final Object onBeforeSubmitWork(String tag) {
        Instrumenter instrumenter = instance;
        if (instrumenter == null || tag == null) {
            return null;
        }
        return instrumenter.onBeforeSubmitWork(tag);
    }

    @JvmStatic
    public static final Object onBeginWork(Object token, String tag) {
        Instrumenter instrumenter = instance;
        if (instrumenter == null || token == null) {
            return null;
        }
        return instrumenter.onBeginWork(token, tag);
    }

    @JvmStatic
    public static final void onEndWork(Object token) {
        Instrumenter instrumenter = instance;
        if (instrumenter == null || token == null) {
            return;
        }
        instrumenter.onEndWork(token);
    }

    @JvmStatic
    public static final void markFailure(Object token, Throwable th) {
        Intrinsics.checkNotNullParameter(th, "th");
        Instrumenter instrumenter = instance;
        if (instrumenter == null || token == null) {
            return;
        }
        instrumenter.markFailure(token, th);
    }

    @JvmStatic
    public static final Runnable decorateRunnable(Runnable runnable, String tag) {
        Instrumenter instrumenter = instance;
        if (instrumenter == null || runnable == null) {
            return runnable;
        }
        if (tag == null) {
            tag = "";
        }
        return instrumenter.decorateRunnable(runnable, tag);
    }
}
