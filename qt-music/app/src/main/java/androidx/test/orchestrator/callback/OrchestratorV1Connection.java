package androidx.test.orchestrator.callback;

import android.os.RemoteException;
import androidx.test.internal.events.client.TestDiscoveryEventService;
import androidx.test.internal.events.client.TestEventClientConnectListener;
import androidx.test.internal.events.client.TestEventClientException;
import androidx.test.internal.events.client.TestEventServiceConnectionBase;
import androidx.test.internal.events.client.TestRunEventService;
import androidx.test.internal.util.Checks;
import androidx.test.services.events.discovery.TestDiscoveryEvent;
import androidx.test.services.events.discovery.TestFoundEvent;
import androidx.test.services.events.run.TestRunEvent;

/* loaded from: classes.dex */
public final class OrchestratorV1Connection extends TestEventServiceConnectionBase<OrchestratorCallback> implements TestRunEventService, TestDiscoveryEventService {
    private static final String ORCHESTRATOR_SERVICE = "androidx.test.orchestrator/.OrchestratorService";

    public OrchestratorV1Connection(TestEventClientConnectListener listener) {
        super(ORCHESTRATOR_SERVICE, OrchestratorV1Connection$$Lambda$0.$instance, listener);
    }

    @Override // androidx.test.internal.events.client.TestRunEventService
    public void send(TestRunEvent event) throws TestEventClientException {
        Checks.checkNotNull(event, "event cannot be null");
        if (this.service == 0) {
            throw new TestEventClientException("Unable to send notification, Orchestrator callback is null");
        }
        try {
            ((OrchestratorCallback) this.service).sendTestNotification(BundleConverter.getBundleFromTestRunEvent(event));
        } catch (RemoteException e) {
            String strValueOf = String.valueOf(event.getClass());
            throw new TestEventClientException(new StringBuilder(String.valueOf(strValueOf).length() + 32).append("Unable to send test run event [").append(strValueOf).append("]").toString(), e);
        }
    }

    @Override // androidx.test.internal.events.client.TestDiscoveryEventService
    public void send(TestDiscoveryEvent event) throws TestEventClientException {
        Checks.checkNotNull(event, "event cannot be null");
        if (this.service == 0) {
            throw new TestEventClientException("Unable to add test, Orchestrator callback is null");
        }
        if (event instanceof TestFoundEvent) {
            String classAndMethodName = ((TestFoundEvent) event).testCase.getClassAndMethodName();
            try {
                ((OrchestratorCallback) this.service).addTest(classAndMethodName);
            } catch (RemoteException e) {
                throw new TestEventClientException(new StringBuilder(String.valueOf(classAndMethodName).length() + 21).append("Failed to add test [").append(classAndMethodName).append("]").toString(), e);
            }
        }
    }
}
