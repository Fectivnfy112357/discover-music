package androidx.test.internal.runner.tracker;

/* loaded from: classes.dex */
public interface UsageTracker {

    public static class NoOpUsageTracker implements UsageTracker {
        @Override // androidx.test.internal.runner.tracker.UsageTracker
        public void sendUsages() {
        }

        @Override // androidx.test.internal.runner.tracker.UsageTracker
        public void trackUsage(String unused, String unusedVersion) {
        }
    }

    void sendUsages();

    void trackUsage(String usage, String version);
}
