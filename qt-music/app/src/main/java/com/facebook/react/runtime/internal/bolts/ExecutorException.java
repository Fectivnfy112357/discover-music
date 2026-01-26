package com.facebook.react.runtime.internal.bolts;

/* loaded from: classes2.dex */
class ExecutorException extends RuntimeException {
    public ExecutorException(Exception exc) {
        super("An exception was thrown by an Executor", exc);
    }
}
