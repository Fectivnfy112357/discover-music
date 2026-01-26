package com.facebook.react.interfaces;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;

/* compiled from: TaskInterface.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0010\u0010\u0003\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u0005H&J\u000f\u0010\u0006\u001a\u0004\u0018\u00018\u0000H&¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\tH&J\b\u0010\u000b\u001a\u00020\tH&J\b\u0010\f\u001a\u00020\rH&J\u001a\u0010\f\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0012À\u0006\u0001"}, d2 = {"Lcom/facebook/react/interfaces/TaskInterface;", "TResult", "", "getError", "Ljava/lang/Exception;", "Lkotlin/Exception;", "getResult", "()Ljava/lang/Object;", "isCancelled", "", "isCompleted", "isFaulted", "waitForCompletion", "", "duration", "", "timeUnit", "Ljava/util/concurrent/TimeUnit;", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface TaskInterface<TResult> {
    Exception getError();

    TResult getResult();

    boolean isCancelled();

    boolean isCompleted();

    boolean isFaulted();

    void waitForCompletion() throws InterruptedException;

    boolean waitForCompletion(long duration, TimeUnit timeUnit) throws InterruptedException;
}
