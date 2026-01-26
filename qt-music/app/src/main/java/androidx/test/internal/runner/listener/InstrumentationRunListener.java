package androidx.test.internal.runner.listener;

import android.app.Instrumentation;
import android.os.Bundle;
import java.io.PrintStream;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

/* loaded from: classes.dex */
public abstract class InstrumentationRunListener extends RunListener {
    private Instrumentation instr;

    public void instrumentationRunFinished(PrintStream streamResult, Bundle resultBundle, Result junitResults) {
    }

    public Instrumentation getInstrumentation() {
        return this.instr;
    }

    public void setInstrumentation(Instrumentation instr) {
        this.instr = instr;
    }

    public void sendStatus(int code, Bundle bundle) {
        getInstrumentation().sendStatus(code, bundle);
    }

    public void sendString(String msg) {
        Bundle bundle = new Bundle();
        bundle.putString("stream", msg);
        sendStatus(0, bundle);
    }
}
