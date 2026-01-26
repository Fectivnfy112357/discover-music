package androidx.test.internal.runner;

import android.app.Instrumentation;
import android.os.Bundle;
import android.util.Log;
import androidx.test.internal.runner.listener.InstrumentationRunListener;
import androidx.test.internal.util.Checks;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

/* loaded from: classes.dex */
public final class TestExecutor {
    private static final String LOG_TAG = "TestExecutor";
    private final Instrumentation instr;
    private final List<RunListener> listeners;

    private TestExecutor(Builder builder) {
        this.listeners = (List) Checks.checkNotNull(builder.listeners);
        this.instr = builder.instr;
    }

    public Bundle execute(Request request) {
        String str;
        Bundle bundle = new Bundle();
        Result result = new Result();
        try {
            JUnitCore jUnitCore = new JUnitCore();
            setUpListeners(jUnitCore);
            Result resultRun = jUnitCore.run(request);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(byteArrayOutputStream);
            reportRunEnded(this.listeners, printStream, bundle, resultRun);
            printStream.close();
            str = String.format("\n%s", byteArrayOutputStream.toString());
        } catch (Throwable th) {
            try {
                Log.e(LOG_TAG, "Fatal exception when running tests", th);
                result.getFailures().add(new Failure(Description.createSuiteDescription("Fatal exception when running tests", new Annotation[0]), th));
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                PrintStream printStream2 = new PrintStream(byteArrayOutputStream2);
                reportRunEnded(this.listeners, printStream2, bundle, result);
                printStream2.close();
                str = String.format("\n%s", byteArrayOutputStream2.toString());
            } catch (Throwable th2) {
                ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
                PrintStream printStream3 = new PrintStream(byteArrayOutputStream3);
                reportRunEnded(this.listeners, printStream3, bundle, result);
                printStream3.close();
                bundle.putString("stream", String.format("\n%s", byteArrayOutputStream3.toString()));
                throw th2;
            }
        }
        bundle.putString("stream", str);
        return bundle;
    }

    private void setUpListeners(JUnitCore testRunner) {
        for (RunListener runListener : this.listeners) {
            String strValueOf = String.valueOf(runListener.getClass().getName());
            Log.d(LOG_TAG, strValueOf.length() != 0 ? "Adding listener ".concat(strValueOf) : new String("Adding listener "));
            testRunner.addListener(runListener);
            if (runListener instanceof InstrumentationRunListener) {
                ((InstrumentationRunListener) runListener).setInstrumentation(this.instr);
            }
        }
    }

    private void reportRunEnded(List<RunListener> listeners, PrintStream summaryWriter, Bundle resultBundle, Result jUnitResults) {
        for (RunListener runListener : listeners) {
            if (runListener instanceof InstrumentationRunListener) {
                ((InstrumentationRunListener) runListener).instrumentationRunFinished(summaryWriter, resultBundle, jUnitResults);
            }
        }
    }

    public static class Builder {
        private final Instrumentation instr;
        private final List<RunListener> listeners = new ArrayList();

        public Builder(Instrumentation instr) {
            this.instr = instr;
        }

        public Builder addRunListener(RunListener listener) {
            this.listeners.add(listener);
            return this;
        }

        public TestExecutor build() {
            return new TestExecutor(this);
        }
    }
}
