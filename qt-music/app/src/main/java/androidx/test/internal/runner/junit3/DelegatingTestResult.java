package androidx.test.internal.runner.junit3;

import java.util.Enumeration;
import junit.framework.AssertionFailedError;
import junit.framework.Protectable;
import junit.framework.Test;
import junit.framework.TestFailure;
import junit.framework.TestListener;
import junit.framework.TestResult;

/* loaded from: classes.dex */
class DelegatingTestResult extends TestResult {
    private TestResult wrappedResult;

    DelegatingTestResult(TestResult wrappedResult) {
        this.wrappedResult = wrappedResult;
    }

    @Override // junit.framework.TestResult
    public void addError(Test test, Throwable t) {
        this.wrappedResult.addError(test, t);
    }

    @Override // junit.framework.TestResult
    public void addFailure(Test test, AssertionFailedError t) {
        this.wrappedResult.addFailure(test, t);
    }

    @Override // junit.framework.TestResult
    public void addListener(TestListener listener) {
        this.wrappedResult.addListener(listener);
    }

    @Override // junit.framework.TestResult
    public void removeListener(TestListener listener) {
        this.wrappedResult.removeListener(listener);
    }

    @Override // junit.framework.TestResult
    public void endTest(Test test) {
        this.wrappedResult.endTest(test);
    }

    @Override // junit.framework.TestResult
    public int errorCount() {
        return this.wrappedResult.errorCount();
    }

    @Override // junit.framework.TestResult
    public Enumeration<TestFailure> errors() {
        return this.wrappedResult.errors();
    }

    @Override // junit.framework.TestResult
    public int failureCount() {
        return this.wrappedResult.failureCount();
    }

    @Override // junit.framework.TestResult
    public Enumeration<TestFailure> failures() {
        return this.wrappedResult.failures();
    }

    @Override // junit.framework.TestResult
    public int runCount() {
        return this.wrappedResult.runCount();
    }

    @Override // junit.framework.TestResult
    public void runProtected(final Test test, Protectable p) {
        this.wrappedResult.runProtected(test, p);
    }

    @Override // junit.framework.TestResult
    public boolean shouldStop() {
        return this.wrappedResult.shouldStop();
    }

    @Override // junit.framework.TestResult
    public void startTest(Test test) {
        this.wrappedResult.startTest(test);
    }

    @Override // junit.framework.TestResult
    public void stop() {
        this.wrappedResult.stop();
    }

    @Override // junit.framework.TestResult
    public boolean wasSuccessful() {
        return this.wrappedResult.wasSuccessful();
    }
}
