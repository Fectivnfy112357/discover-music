package androidx.test.internal.util;

import android.app.Instrumentation;
import android.os.Bundle;

/* loaded from: classes.dex */
public class AndroidRunnerParams {
    private final Bundle bundle;
    private final boolean ignoreSuiteMethods;
    private final Instrumentation instrumentation;
    private final long perTestTimeout;
    private final boolean skipExecution;

    @Deprecated
    public AndroidRunnerParams(Instrumentation instrumentation, Bundle bundle, boolean skipExecution, long perTestTimeout, boolean ignoreSuiteMethods) {
        this.instrumentation = instrumentation;
        this.bundle = bundle;
        this.skipExecution = skipExecution;
        this.perTestTimeout = perTestTimeout;
        this.ignoreSuiteMethods = ignoreSuiteMethods;
    }

    public AndroidRunnerParams(Instrumentation instrumentation, Bundle bundle, long perTestTimeout, boolean ignoreSuiteMethods) {
        this.instrumentation = instrumentation;
        this.bundle = bundle;
        this.skipExecution = false;
        this.perTestTimeout = perTestTimeout;
        this.ignoreSuiteMethods = ignoreSuiteMethods;
    }

    public Instrumentation getInstrumentation() {
        return this.instrumentation;
    }

    public Bundle getBundle() {
        return this.bundle;
    }

    @Deprecated
    public boolean isSkipExecution() {
        return this.skipExecution;
    }

    public long getPerTestTimeout() {
        return this.perTestTimeout;
    }

    public boolean isIgnoreSuiteMethods() {
        return this.ignoreSuiteMethods;
    }
}
