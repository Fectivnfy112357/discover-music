package androidx.test.orchestrator.listeners;

import android.app.Instrumentation;
import androidx.test.orchestrator.junit.ParcelableDescription;
import androidx.test.orchestrator.junit.ParcelableFailure;
import androidx.test.orchestrator.junit.ParcelableResult;

/* loaded from: classes.dex */
public abstract class OrchestrationRunListener {
    private Instrumentation instrumentation;

    public void orchestrationRunStarted(int testCount) {
    }

    public void testAssumptionFailure(ParcelableFailure failure) {
    }

    public void testFailure(ParcelableFailure failure) {
    }

    public void testFinished(ParcelableDescription description) {
    }

    public void testIgnored(ParcelableDescription description) {
    }

    public void testProcessFinished(String message) {
    }

    public void testRunFinished(ParcelableResult result) {
    }

    public void testRunStarted(ParcelableDescription description) {
    }

    public void testStarted(ParcelableDescription description) {
    }

    public void setInstrumentation(Instrumentation instrumentation) {
        if (instrumentation == null) {
            throw new IllegalArgumentException("Instrumentation should not be null");
        }
        this.instrumentation = instrumentation;
    }

    public Instrumentation getInstrumentation() {
        return this.instrumentation;
    }
}
