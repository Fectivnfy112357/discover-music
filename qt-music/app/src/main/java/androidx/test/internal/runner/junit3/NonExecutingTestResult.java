package androidx.test.internal.runner.junit3;

import junit.framework.Protectable;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;

/* loaded from: classes.dex */
class NonExecutingTestResult extends DelegatingTestResult {
    @Override // androidx.test.internal.runner.junit3.DelegatingTestResult, junit.framework.TestResult
    public void runProtected(Test test, Protectable p) {
    }

    NonExecutingTestResult(TestResult wrappedResult) {
        super(wrappedResult);
    }

    @Override // junit.framework.TestResult
    protected void run(final TestCase test) {
        startTest(test);
        endTest(test);
    }
}
