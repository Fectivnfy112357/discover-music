package androidx.test.internal.events.client;

import android.util.Log;
import com.umeng.ccg.c;

/* loaded from: classes.dex */
public final class TestEventClientArgs {
    public final ConnectionFactory connectionFactory;
    public final boolean isOrchestrated;
    public final boolean isPrimaryInstrProcess;
    public final boolean isTestDiscoveryRequested;
    public final boolean isTestRunEventsRequested;
    public final int orchestratorVersion;
    public final String testDiscoveryService;
    public final boolean testPlatformMigration;
    public final String testRunEventService;

    public interface ConnectionFactory {
        TestEventServiceConnection create(TestEventClientConnectListener listener);
    }

    private TestEventClientArgs(boolean isOrchestrated, int orchestratorVersion, Builder builder) {
        this.isOrchestrated = isOrchestrated;
        this.isPrimaryInstrProcess = builder.isPrimaryInstProcess;
        this.isTestDiscoveryRequested = builder.testDiscoveryRequested;
        this.isTestRunEventsRequested = builder.testRunEventsRequested;
        this.testDiscoveryService = builder.testDiscoveryService;
        this.testRunEventService = builder.testRunEventService;
        this.connectionFactory = builder.connectionFactory;
        this.orchestratorVersion = orchestratorVersion;
        this.testPlatformMigration = builder.testPlatformMigration;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private static final String TAG = "TestEventClient";
        private ConnectionFactory connectionFactory;
        private String orchestratorService;
        private String testDiscoveryService;
        private String testRunEventService;
        boolean isPrimaryInstProcess = true;
        boolean testDiscoveryRequested = false;
        boolean testRunEventsRequested = false;
        boolean testPlatformMigration = false;

        public Builder setPrimaryInstProcess(boolean isPrimaryInstProcess) {
            this.isPrimaryInstProcess = isPrimaryInstProcess;
            return this;
        }

        public Builder setOrchestratorService(String orchestratorService) {
            this.orchestratorService = orchestratorService;
            return this;
        }

        public Builder setTestDiscoveryRequested(boolean discoveryRequested) {
            this.testDiscoveryRequested = discoveryRequested;
            return this;
        }

        public Builder setTestRunEventsRequested(boolean runEventsRequested) {
            this.testRunEventsRequested = runEventsRequested;
            return this;
        }

        public Builder setTestPlatformMigration(boolean testPlatformMigration) {
            this.testPlatformMigration = testPlatformMigration;
            return this;
        }

        public Builder setTestDiscoveryService(String testDiscoveryService) {
            this.testDiscoveryService = testDiscoveryService;
            return this;
        }

        public Builder setTestRunEventService(String testRunEventService) {
            this.testRunEventService = testRunEventService;
            return this;
        }

        public Builder setConnectionFactory(ConnectionFactory connectionFactory) {
            this.connectionFactory = connectionFactory;
            return this;
        }

        public TestEventClientArgs build() {
            String str = this.testDiscoveryService;
            int i = 2;
            if (str != null && !str.isEmpty()) {
                this.testDiscoveryRequested = true;
                this.testRunEventsRequested = false;
            } else {
                String str2 = this.testRunEventService;
                if (str2 != null && !str2.isEmpty()) {
                    this.testRunEventsRequested = true;
                    this.testDiscoveryRequested = false;
                } else {
                    String str3 = this.orchestratorService;
                    if (str3 != null) {
                        if (this.connectionFactory == null) {
                            Log.w(TAG, new StringBuilder(String.valueOf(str3).length() + 96).append("Orchestrator service [").append(str3).append("] argument given, but no connectionFactory was provided for the v1 service").toString());
                        } else if (this.testDiscoveryRequested || this.testRunEventsRequested) {
                            i = 1;
                        } else {
                            Log.w(TAG, new StringBuilder(String.valueOf(str3).length() + c.c).append("Orchestrator service [").append(str3).append("] argument given, but neither test discovery nor run event services was requested").toString());
                        }
                    } else {
                        Log.v(TAG, "No service name argument was given (testDiscoveryService, testRunEventService or orchestratorService)");
                        this.testDiscoveryRequested = false;
                        this.testRunEventsRequested = false;
                    }
                    i = 0;
                }
            }
            if (this.testDiscoveryRequested && this.testRunEventsRequested) {
                Log.w(TAG, "Can't use both the test discovery and run event services simultaneously");
                this.testRunEventsRequested = false;
            }
            if (i > 0) {
                Log.v(TAG, new StringBuilder(39).append("Connecting to Orchestrator v").append(i).toString());
            }
            return new TestEventClientArgs(i > 0, i, this);
        }
    }
}
