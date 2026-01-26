package com.google.common.eventbus;

@ElementTypesAreNonnullByDefault
/* loaded from: classes2.dex */
public interface SubscriberExceptionHandler {
    void handleException(Throwable exception, SubscriberExceptionContext context);
}
