package androidx.test.orchestrator.listeners.result;

import java.util.Arrays;
import java.util.Map;

/* loaded from: classes.dex */
public class TestResult {
    private Map<String, String> metrics;
    private String stackTrace;
    private long startTime;
    private long endTime = 0;
    private TestStatus status = TestStatus.INCOMPLETE;

    public enum TestStatus {
        FAILURE,
        PASSED,
        INCOMPLETE,
        ASSUMPTION_FAILURE,
        IGNORED
    }

    public TestResult() {
        this.startTime = 0L;
        this.startTime = System.currentTimeMillis();
    }

    public TestStatus getStatus() {
        return this.status;
    }

    public String getStackTrace() {
        return this.stackTrace;
    }

    public Map<String, String> getMetrics() {
        return this.metrics;
    }

    public void setMetrics(Map<String, String> metrics) {
        this.metrics = metrics;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public TestResult setStatus(TestStatus status) {
        this.status = status;
        return this;
    }

    public void setStackTrace(String trace) {
        this.stackTrace = trace;
    }

    public void setEndTime(long currentTimeMillis) {
        this.endTime = currentTimeMillis;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.metrics, this.stackTrace, this.status});
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TestResult testResult = (TestResult) obj;
        return equal(this.metrics, testResult.metrics) && equal(this.stackTrace, testResult.stackTrace) && equal(this.status, testResult.status);
    }

    private static boolean equal(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }
}
