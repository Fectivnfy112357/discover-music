package androidx.test.internal.runner.junit4;

import androidx.test.internal.runner.RunnerArgs;
import androidx.test.internal.runner.junit4.statement.RunAfters;
import androidx.test.internal.runner.junit4.statement.RunBefores;
import androidx.test.internal.runner.junit4.statement.UiThreadStatement;
import androidx.test.internal.util.AndroidRunnerParams;
import androidx.test.platform.app.InstrumentationRegistry;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.statements.FailOnTimeout;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

/* loaded from: classes.dex */
public class AndroidJUnit4ClassRunner extends BlockJUnit4ClassRunner {
    private final AndroidRunnerParams androidRunnerParams;

    public AndroidJUnit4ClassRunner(Class<?> klass, AndroidRunnerParams runnerParams) throws InitializationError {
        super(klass);
        this.androidRunnerParams = runnerParams;
    }

    public AndroidJUnit4ClassRunner(Class<?> klass) throws InitializationError {
        this(klass, createRunnerParams());
    }

    private static AndroidRunnerParams createRunnerParams() {
        return new AndroidRunnerParams(InstrumentationRegistry.getInstrumentation(), InstrumentationRegistry.getArguments(), new RunnerArgs.Builder().fromBundle(InstrumentationRegistry.getInstrumentation(), InstrumentationRegistry.getArguments()).build().testTimeout, false);
    }

    @Override // org.junit.runners.BlockJUnit4ClassRunner
    protected Statement methodInvoker(FrameworkMethod method, Object test) {
        if (UiThreadStatement.shouldRunOnUiThread(method)) {
            return new UiThreadStatement(super.methodInvoker(method, test), true);
        }
        return super.methodInvoker(method, test);
    }

    @Override // org.junit.runners.BlockJUnit4ClassRunner
    protected Statement withBefores(FrameworkMethod method, Object target, Statement statement) {
        List<FrameworkMethod> annotatedMethods = getTestClass().getAnnotatedMethods(Before.class);
        return annotatedMethods.isEmpty() ? statement : new RunBefores(method, statement, annotatedMethods, target);
    }

    @Override // org.junit.runners.BlockJUnit4ClassRunner
    protected Statement withAfters(FrameworkMethod method, Object target, Statement statement) {
        List<FrameworkMethod> annotatedMethods = getTestClass().getAnnotatedMethods(After.class);
        return annotatedMethods.isEmpty() ? statement : new RunAfters(method, statement, annotatedMethods, target);
    }

    @Override // org.junit.runners.BlockJUnit4ClassRunner
    protected Statement withPotentialTimeout(FrameworkMethod method, Object test, Statement next) {
        long timeout = getTimeout((Test) method.getAnnotation(Test.class));
        if (timeout <= 0 && this.androidRunnerParams.getPerTestTimeout() > 0) {
            timeout = this.androidRunnerParams.getPerTestTimeout();
        }
        return timeout <= 0 ? next : new FailOnTimeout(next, timeout);
    }

    private long getTimeout(Test annotation) {
        if (annotation == null) {
            return 0L;
        }
        return annotation.timeout();
    }
}
