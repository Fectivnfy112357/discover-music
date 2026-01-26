package androidx.test.internal.events.client;

import android.util.Log;
import androidx.test.internal.util.Checks;
import androidx.test.services.events.ParcelableConverter;
import androidx.test.services.events.TestEventException;
import androidx.test.services.events.discovery.TestDiscoveryFinishedEvent;
import androidx.test.services.events.discovery.TestDiscoveryStartedEvent;
import androidx.test.services.events.discovery.TestFoundEvent;
import java.util.Iterator;
import org.junit.runner.Description;

/* loaded from: classes.dex */
public final class TestDiscovery {
    private static final String TAG = "TestDiscovery";
    private final TestDiscoveryEventService testDiscoveryEventService;

    public TestDiscovery(TestDiscoveryEventService testDiscoveryEventService) {
        this.testDiscoveryEventService = (TestDiscoveryEventService) Checks.checkNotNull(testDiscoveryEventService, "testDiscoveryEventService can't be null");
    }

    public void addTests(Description description) throws TestEventClientException {
        Checks.checkNotNull(description, "description cannot be null");
        this.testDiscoveryEventService.send(new TestDiscoveryStartedEvent());
        addTest(description);
        this.testDiscoveryEventService.send(new TestDiscoveryFinishedEvent());
    }

    private void addTest(Description description) {
        if (description.isEmpty()) {
            Log.d(TAG, "addTest called with an empty test description");
            return;
        }
        if (description.isTest()) {
            if (!JUnitValidator.validateDescription(description)) {
                String className = description.getClassName();
                String methodName = description.getMethodName();
                Log.w(TAG, new StringBuilder(String.valueOf(className).length() + 38 + String.valueOf(methodName).length()).append("JUnit reported ").append(className).append("#").append(methodName).append("; discarding as bogus.").toString());
                return;
            } else {
                try {
                    this.testDiscoveryEventService.send(new TestFoundEvent(ParcelableConverter.getTestCaseFromDescription(description)));
                    return;
                } catch (TestEventException e) {
                    Log.e(TAG, "Failed to get test description", e);
                    return;
                }
            }
        }
        Iterator<Description> it = description.getChildren().iterator();
        while (it.hasNext()) {
            addTest(it.next());
        }
    }
}
