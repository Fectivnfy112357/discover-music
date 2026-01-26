package androidx.test.internal.events.client;

import androidx.test.services.events.TestEventException;

/* loaded from: classes.dex */
public final class TestEventClientException extends TestEventException {
    public TestEventClientException(String message) {
        super(message);
    }

    public TestEventClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
