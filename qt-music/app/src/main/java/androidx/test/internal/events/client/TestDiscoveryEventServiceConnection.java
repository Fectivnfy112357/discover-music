package androidx.test.internal.events.client;

import android.os.RemoteException;
import androidx.test.internal.util.Checks;
import androidx.test.services.events.discovery.ITestDiscoveryEvent;
import androidx.test.services.events.discovery.TestDiscoveryEvent;

/* loaded from: classes.dex */
public class TestDiscoveryEventServiceConnection extends TestEventServiceConnectionBase<ITestDiscoveryEvent> implements TestDiscoveryEventService {
    TestDiscoveryEventServiceConnection(String serviceName, TestEventClientConnectListener listener) {
        super(serviceName, TestDiscoveryEventServiceConnection$$Lambda$0.$instance, listener);
    }

    @Override // androidx.test.internal.events.client.TestDiscoveryEventService
    public void send(TestDiscoveryEvent testDiscoveryEvent) throws TestEventClientException {
        Checks.checkNotNull(testDiscoveryEvent, "testDiscoveryEvent cannot be null");
        if (this.service == 0) {
            throw new TestEventClientException("Can't add test, service not connected");
        }
        try {
            ((ITestDiscoveryEvent) this.service).send(testDiscoveryEvent);
        } catch (RemoteException e) {
            throw new TestEventClientException("Failed to send test case", e);
        }
    }
}
