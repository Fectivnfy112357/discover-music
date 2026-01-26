package androidx.test.orchestrator.listeners.result;

import android.util.Log;
import androidx.test.orchestrator.listeners.result.TestResult;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class TestRunResult implements ITestRunListener {
    private static final String LOG_TAG = "TestRunResult";
    private Map<TestIdentifier, TestResult> testResults = new LinkedHashMap();
    private Map<String, String> runMetrics = new HashMap();
    private boolean isRunComplete = false;
    private long elapsedTime = 0;
    private int[] statusCounts = new int[TestResult.TestStatus.values().length];
    private boolean isCountDirty = true;
    private String runFailureError = null;
    private boolean aggregateMetrics = false;
    private String testRunName = "not started";

    public void setAggregateMetrics(boolean metricAggregation) {
        this.aggregateMetrics = metricAggregation;
    }

    public String getName() {
        return this.testRunName;
    }

    public Map<TestIdentifier, TestResult> getTestResults() {
        return this.testResults;
    }

    public Map<String, String> getRunMetrics() {
        return this.runMetrics;
    }

    public Set<TestIdentifier> getCompletedTests() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Map.Entry<TestIdentifier, TestResult> entry : getTestResults().entrySet()) {
            if (!entry.getValue().getStatus().equals(TestResult.TestStatus.INCOMPLETE)) {
                linkedHashSet.add(entry.getKey());
            }
        }
        return linkedHashSet;
    }

    public boolean isRunFailure() {
        return this.runFailureError != null;
    }

    public boolean isRunComplete() {
        return this.isRunComplete;
    }

    public void setRunComplete(boolean runComplete) {
        this.isRunComplete = runComplete;
    }

    public int getNumTestsInState(TestResult.TestStatus status) {
        if (this.isCountDirty) {
            int i = 0;
            while (true) {
                int[] iArr = this.statusCounts;
                if (i >= iArr.length) {
                    break;
                }
                iArr[i] = 0;
                i++;
            }
            for (TestResult testResult : this.testResults.values()) {
                int[] iArr2 = this.statusCounts;
                int iOrdinal = testResult.getStatus().ordinal();
                iArr2[iOrdinal] = iArr2[iOrdinal] + 1;
            }
            this.isCountDirty = false;
        }
        return this.statusCounts[status.ordinal()];
    }

    public int getNumTests() {
        return this.testResults.size();
    }

    public int getNumCompleteTests() {
        return getNumTests() - getNumTestsInState(TestResult.TestStatus.INCOMPLETE);
    }

    public boolean hasFailedTests() {
        return getNumAllFailedTests() > 0;
    }

    public int getNumAllFailedTests() {
        return getNumTestsInState(TestResult.TestStatus.FAILURE);
    }

    public long getElapsedTime() {
        return this.elapsedTime;
    }

    public String getRunFailureMessage() {
        return this.runFailureError;
    }

    @Override // androidx.test.orchestrator.listeners.result.ITestRunListener
    public void testRunStarted(String runName, int testCount) {
        this.testRunName = runName;
        this.isRunComplete = false;
        this.runFailureError = null;
    }

    @Override // androidx.test.orchestrator.listeners.result.ITestRunListener
    public void testStarted(TestIdentifier test) {
        addTestResult(test, new TestResult());
    }

    private void addTestResult(TestIdentifier test, TestResult testResult) {
        this.isCountDirty = true;
        this.testResults.put(test, testResult);
    }

    private void updateTestResult(TestIdentifier test, TestResult.TestStatus status, String trace) {
        TestResult testResult = this.testResults.get(test);
        if (testResult == null) {
            Log.w(LOG_TAG, String.format("received test event %s without test start for %s. trace: %s", status.name(), test, trace));
            testResult = new TestResult();
        }
        testResult.setStatus(status);
        testResult.setStackTrace(trace);
        addTestResult(test, testResult);
    }

    @Override // androidx.test.orchestrator.listeners.result.ITestRunListener
    public void testFailed(TestIdentifier test, String trace) {
        updateTestResult(test, TestResult.TestStatus.FAILURE, trace);
    }

    @Override // androidx.test.orchestrator.listeners.result.ITestRunListener
    public void testAssumptionFailure(TestIdentifier test, String trace) {
        updateTestResult(test, TestResult.TestStatus.ASSUMPTION_FAILURE, trace);
    }

    @Override // androidx.test.orchestrator.listeners.result.ITestRunListener
    public void testIgnored(TestIdentifier test) {
        updateTestResult(test, TestResult.TestStatus.IGNORED, null);
    }

    @Override // androidx.test.orchestrator.listeners.result.ITestRunListener
    public void testEnded(TestIdentifier test, Map<String, String> testMetrics) {
        TestResult testResult = this.testResults.get(test);
        if (testResult == null) {
            testResult = new TestResult();
        }
        if (testResult.getStatus().equals(TestResult.TestStatus.INCOMPLETE)) {
            testResult.setStatus(TestResult.TestStatus.PASSED);
        }
        testResult.setEndTime(System.currentTimeMillis());
        testResult.setMetrics(testMetrics);
        addTestResult(test, testResult);
    }

    @Override // androidx.test.orchestrator.listeners.result.ITestRunListener
    public void testRunFailed(String errorMessage) {
        this.runFailureError = errorMessage;
    }

    @Override // androidx.test.orchestrator.listeners.result.ITestRunListener
    public void testRunStopped(long elapsedTime) {
        this.elapsedTime += elapsedTime;
        this.isRunComplete = true;
    }

    @Override // androidx.test.orchestrator.listeners.result.ITestRunListener
    public void testRunEnded(long elapsedTime, Map<String, String> runMetrics) {
        if (this.aggregateMetrics) {
            for (Map.Entry<String, String> entry : runMetrics.entrySet()) {
                this.runMetrics.put(entry.getKey(), combineValues(this.runMetrics.get(entry.getKey()), entry.getValue()));
            }
        } else {
            this.runMetrics.putAll(runMetrics);
        }
        this.elapsedTime += elapsedTime;
        this.isRunComplete = true;
    }

    private String combineValues(String existingValue, String newValue) {
        if (existingValue != null) {
            try {
                try {
                    return Long.toString(Long.valueOf(Long.parseLong(existingValue)).longValue() + Long.valueOf(Long.parseLong(newValue)).longValue());
                } catch (NumberFormatException unused) {
                    return Double.toString(Double.valueOf(Double.parseDouble(existingValue)).doubleValue() + Double.valueOf(Double.parseDouble(newValue)).doubleValue());
                }
            } catch (NumberFormatException unused2) {
            }
        }
        return newValue;
    }

    public String getTextSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Total tests %d, ", Integer.valueOf(getNumTests())));
        for (TestResult.TestStatus testStatus : TestResult.TestStatus.values()) {
            int numTestsInState = getNumTestsInState(testStatus);
            if (numTestsInState > 0) {
                sb.append(String.format("%s %d, ", testStatus.toString().toLowerCase(), Integer.valueOf(numTestsInState)));
            }
        }
        return sb.toString();
    }
}
