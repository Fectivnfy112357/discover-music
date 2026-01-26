package androidx.test.runner;

import android.util.Log;
import androidx.test.internal.runner.RunnerArgs;
import androidx.test.internal.runner.tracker.UsageTracker;
import androidx.test.internal.runner.tracker.UsageTrackerRegistry;
import androidx.test.internal.util.Checks;

/* loaded from: classes.dex */
public class UsageTrackerFacilitator implements UsageTracker {
    private static final String TAG = "UsageTrackerFacilitator";
    private final boolean shouldTrackUsage;

    public UsageTrackerFacilitator(RunnerArgs runnerArgs) {
        Checks.checkNotNull(runnerArgs, "runnerArgs cannot be null!");
        if (runnerArgs.orchestratorService != null) {
            this.shouldTrackUsage = !runnerArgs.disableAnalytics && runnerArgs.listTestsForOrchestrator;
        } else {
            this.shouldTrackUsage = !runnerArgs.disableAnalytics;
        }
    }

    public UsageTrackerFacilitator(boolean shouldTrackUsage) {
        this.shouldTrackUsage = shouldTrackUsage;
    }

    public boolean shouldTrackUsage() {
        return this.shouldTrackUsage;
    }

    public void registerUsageTracker(UsageTracker usageTracker) {
        if (usageTracker == null || !shouldTrackUsage()) {
            Log.i(TAG, "Usage tracking disabled");
            UsageTrackerRegistry.registerInstance(new UsageTracker.NoOpUsageTracker());
        } else {
            Log.i(TAG, "Usage tracking enabled");
            UsageTrackerRegistry.registerInstance(usageTracker);
        }
    }

    @Override // androidx.test.internal.runner.tracker.UsageTracker
    public void trackUsage(String usage, String version) {
        if (shouldTrackUsage()) {
            UsageTrackerRegistry.getInstance().trackUsage(usage, version);
        }
    }

    @Override // androidx.test.internal.runner.tracker.UsageTracker
    public void sendUsages() {
        if (shouldTrackUsage()) {
            UsageTrackerRegistry.getInstance().sendUsages();
        }
    }
}
