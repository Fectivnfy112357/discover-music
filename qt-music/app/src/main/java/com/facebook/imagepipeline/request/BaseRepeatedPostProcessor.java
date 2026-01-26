package com.facebook.imagepipeline.request;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BaseRepeatedPostProcessor.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0016J\u0006\u0010\t\u001a\u00020\u0007R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00058BX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/facebook/imagepipeline/request/BaseRepeatedPostProcessor;", "Lcom/facebook/imagepipeline/request/BasePostprocessor;", "Lcom/facebook/imagepipeline/request/RepeatedPostprocessor;", "()V", "callback", "Lcom/facebook/imagepipeline/request/RepeatedPostprocessorRunner;", "setCallback", "", "runner", "update", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class BaseRepeatedPostProcessor extends BasePostprocessor implements RepeatedPostprocessor {
    private RepeatedPostprocessorRunner callback;

    @Override // com.facebook.imagepipeline.request.RepeatedPostprocessor
    public synchronized void setCallback(RepeatedPostprocessorRunner runner) {
        Intrinsics.checkNotNullParameter(runner, "runner");
        this.callback = runner;
    }

    public final void update() {
        RepeatedPostprocessorRunner repeatedPostprocessorRunner = this.callback;
        if (repeatedPostprocessorRunner != null) {
            repeatedPostprocessorRunner.update();
        }
    }
}
