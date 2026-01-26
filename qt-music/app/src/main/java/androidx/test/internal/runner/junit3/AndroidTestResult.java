package androidx.test.internal.runner.junit3;

import android.app.Instrumentation;
import android.os.Bundle;
import android.test.AndroidTestCase;
import android.test.InstrumentationTestCase;
import java.util.concurrent.TimeoutException;
import junit.framework.AssertionFailedError;
import junit.framework.Protectable;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;

/* loaded from: classes.dex */
class AndroidTestResult extends DelegatingTestResult {
    private final Bundle bundle;
    private final Instrumentation instr;
    private long timeout;

    AndroidTestResult(Bundle bundle, Instrumentation instr, TestResult result) {
        super(result);
        this.bundle = bundle;
        this.instr = instr;
    }

    @Override // junit.framework.TestResult
    protected void run(final TestCase test) {
        if (test instanceof AndroidTestCase) {
            ((AndroidTestCase) test).setContext(this.instr.getTargetContext());
        }
        if (test instanceof InstrumentationTestCase) {
            ((InstrumentationTestCase) test).injectInstrumentation(this.instr);
        }
        super.run(test);
    }

    void setCurrentTimeout(long timeout) {
        this.timeout = timeout;
    }

    @Override // androidx.test.internal.runner.junit3.DelegatingTestResult, junit.framework.TestResult
    public void runProtected(final Test test, Protectable p) {
        try {
            p.protect();
        } catch (InterruptedException unused) {
            super.addError(test, new TimeoutException(String.format("Test timed out after %d milliseconds", Long.valueOf(this.timeout))));
        } catch (ThreadDeath e) {
            throw e;
        } catch (AssertionFailedError e2) {
            super.addFailure(test, e2);
        } catch (Throwable th) {
            super.addError(test, th);
        }
    }
}
