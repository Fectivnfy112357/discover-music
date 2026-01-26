package androidx.test.internal.events.client;

import android.content.Context;
import android.util.Log;
import androidx.test.internal.util.Checks;
import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;

/* loaded from: classes.dex */
public final class TestEventClient {
    public static final TestEventClient NO_OP_CLIENT = new TestEventClient();
    private static final String TAG = "TestEventClient";
    private final OrchestratedInstrumentationListener notificationRunListener;
    private final TestDiscovery testDiscovery;
    private final TestPlatformListener testPlatformListener;

    private TestEventClient() {
        this.testDiscovery = null;
        this.notificationRunListener = null;
        this.testPlatformListener = null;
    }

    private TestEventClient(TestDiscovery testDiscovery) {
        Checks.checkNotNull(testDiscovery, "testDiscovery cannot be null");
        this.testDiscovery = testDiscovery;
        this.notificationRunListener = null;
        this.testPlatformListener = null;
    }

    private TestEventClient(OrchestratedInstrumentationListener runListener) {
        Checks.checkNotNull(runListener, "runListener cannot be null");
        this.testDiscovery = null;
        this.notificationRunListener = runListener;
        this.testPlatformListener = null;
    }

    private TestEventClient(TestPlatformListener runListener) {
        Checks.checkNotNull(runListener, "runListener cannot be null");
        this.testDiscovery = null;
        this.notificationRunListener = null;
        this.testPlatformListener = runListener;
    }

    public static TestEventClient connect(Context context, TestEventClientConnectListener listener, TestEventClientArgs args) {
        TestEventClient testEventClient;
        Checks.checkNotNull(context, "context parameter cannot be null!");
        Checks.checkNotNull(listener, "listener parameter cannot be null!");
        Checks.checkNotNull(args, "args parameter cannot be null!");
        if (!args.isOrchestrated) {
            return NO_OP_CLIENT;
        }
        if (!args.isPrimaryInstrProcess) {
            Log.w(TAG, "Orchestration requested, but this isn't the primary instrumentation");
            return NO_OP_CLIENT;
        }
        TestEventServiceConnection connection = getConnection(listener, args);
        TestEventClient testEventClient2 = NO_OP_CLIENT;
        if (args.isTestDiscoveryRequested) {
            Log.v(TAG, "Test discovery events requested");
            testEventClient = new TestEventClient(new TestDiscovery((TestDiscoveryEventService) connection));
        } else {
            if (args.isTestRunEventsRequested) {
                Log.v(TAG, "Test run events requested");
                if (args.testPlatformMigration) {
                    testEventClient2 = new TestEventClient(new TestPlatformListener((TestPlatformEventService) connection));
                } else {
                    testEventClient = new TestEventClient(new OrchestratedInstrumentationListener((TestRunEventService) connection));
                }
            }
            connection.connect(context);
            return testEventClient2;
        }
        testEventClient2 = testEventClient;
        connection.connect(context);
        return testEventClient2;
    }

    public boolean isTestDiscoveryEnabled() {
        return this.testDiscovery != null;
    }

    public boolean isTestRunEventsEnabled() {
        return (this.notificationRunListener == null && this.testPlatformListener == null) ? false : true;
    }

    public RunListener getRunListener() {
        if (!isTestRunEventsEnabled()) {
            Log.e(TAG, "Orchestrator service not connected - can't send test run notifications");
        }
        OrchestratedInstrumentationListener orchestratedInstrumentationListener = this.notificationRunListener;
        return orchestratedInstrumentationListener != null ? orchestratedInstrumentationListener : this.testPlatformListener;
    }

    public void addTests(Description description) {
        if (!isTestDiscoveryEnabled()) {
            Log.e(TAG, "Orchestrator service not connected - can't send tests");
            return;
        }
        try {
            this.testDiscovery.addTests(description);
        } catch (TestEventClientException e) {
            String strValueOf = String.valueOf(description);
            Log.e(TAG, new StringBuilder(String.valueOf(strValueOf).length() + 21).append("Failed to add test [").append(strValueOf).append("]").toString(), e);
        }
    }

    private static TestEventServiceConnection getConnection(TestEventClientConnectListener listener, TestEventClientArgs args) {
        if (args.orchestratorVersion == 1) {
            if (args.connectionFactory != null) {
                return args.connectionFactory.create(listener);
            }
            throw new IllegalArgumentException("Orchestrator v1 connectionFactory must be provided by TestEventClientArgs.Builder#setConnectionFactory()");
        }
        if (args.orchestratorVersion == 2) {
            if (args.isTestDiscoveryRequested) {
                return new TestDiscoveryEventServiceConnection((String) Checks.checkNotNull(args.testDiscoveryService), listener);
            }
            if (args.isTestRunEventsRequested) {
                if (args.testPlatformMigration) {
                    return new TestPlatformEventServiceConnection((String) Checks.checkNotNull(args.testRunEventService), listener);
                }
                return new TestRunEventServiceConnection((String) Checks.checkNotNull(args.testRunEventService), listener);
            }
        }
        throw new IllegalArgumentException("TestEventClientArgs misconfiguration - can't determine which service connection to use.");
    }

    public void reportProcessCrash(Throwable t, long timeoutMillis) {
        if (isTestRunEventsEnabled()) {
            OrchestratedInstrumentationListener orchestratedInstrumentationListener = this.notificationRunListener;
            if (orchestratedInstrumentationListener != null) {
                orchestratedInstrumentationListener.reportProcessCrash(t, timeoutMillis);
            }
            TestPlatformListener testPlatformListener = this.testPlatformListener;
            if (testPlatformListener != null) {
                testPlatformListener.reportProcessCrash(t);
            }
        }
    }
}
