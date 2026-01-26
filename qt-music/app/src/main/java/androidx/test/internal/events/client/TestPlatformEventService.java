package androidx.test.internal.events.client;

import androidx.test.services.events.platform.TestPlatformEvent;

/* loaded from: classes.dex */
public interface TestPlatformEventService {
    void send(TestPlatformEvent event) throws TestEventClientException;
}
