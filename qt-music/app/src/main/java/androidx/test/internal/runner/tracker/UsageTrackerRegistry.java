package androidx.test.internal.runner.tracker;

import androidx.test.internal.runner.tracker.UsageTracker;
import androidx.test.internal.util.Checks;

/* loaded from: classes.dex */
public final class UsageTrackerRegistry {
    private static volatile UsageTracker instance = new UsageTracker.NoOpUsageTracker();

    public interface AxtVersions {
        public static final String ESPRESSO_VERSION = "3.4.0";
        public static final String RUNNER_VERSION = "1.4.0";
        public static final String SERVICES_VERSION = "1.4.0";
    }

    public static void registerInstance(UsageTracker tracker) {
        instance = (UsageTracker) Checks.checkNotNull(tracker);
    }

    public static UsageTracker getInstance() {
        return instance;
    }

    private UsageTrackerRegistry() {
    }
}
