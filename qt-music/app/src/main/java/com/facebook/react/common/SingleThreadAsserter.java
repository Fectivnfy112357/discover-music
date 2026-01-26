package com.facebook.react.common;

import com.facebook.infer.annotation.Assertions;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SingleThreadAsserter.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/facebook/react/common/SingleThreadAsserter;", "", "()V", "thread", "Ljava/lang/Thread;", "assertNow", "", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SingleThreadAsserter {
    private Thread thread;

    public final void assertNow() {
        Thread threadCurrentThread = Thread.currentThread();
        if (this.thread == null) {
            this.thread = threadCurrentThread;
        }
        Assertions.assertCondition(Intrinsics.areEqual(this.thread, threadCurrentThread));
    }
}
