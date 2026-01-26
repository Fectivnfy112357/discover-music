package androidx.test.internal.runner;

import androidx.test.internal.runner.junit3.JUnit38ClassRunner;
import androidx.test.internal.runner.junit3.NonExecutingTestSuite;
import androidx.test.internal.util.AndroidRunnerBuilderUtil;
import androidx.test.internal.util.AndroidRunnerParams;
import androidx.test.internal.util.Checks;
import java.util.List;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.junit.internal.runners.ErrorReportingRunner;
import org.junit.internal.runners.SuiteMethod;
import org.junit.runner.Runner;
import org.junit.runners.model.RunnerBuilder;

/* loaded from: classes.dex */
class AndroidLogOnlyBuilder extends RunnerBuilder {
    private final AndroidRunnerBuilder builder;
    private int runnerCount = 0;
    private final AndroidRunnerParams runnerParams;
    private final boolean scanningPath;

    AndroidLogOnlyBuilder(AndroidRunnerParams runnerParams, boolean scanningPath, List<Class<? extends RunnerBuilder>> customRunnerBuilderClasses) {
        this.runnerParams = (AndroidRunnerParams) Checks.checkNotNull(runnerParams, "runnerParams cannot be null!");
        this.scanningPath = scanningPath;
        this.builder = new AndroidRunnerBuilder(this, runnerParams, scanningPath, customRunnerBuilderClasses);
    }

    @Override // org.junit.runners.model.RunnerBuilder
    public Runner runnerForClass(Class<?> testClass) throws Exception {
        this.runnerCount++;
        if (AndroidRunnerBuilderUtil.isJUnit3Test(testClass)) {
            if (!this.scanningPath || AndroidRunnerBuilderUtil.hasJUnit3TestMethod(testClass)) {
                return new JUnit38ClassRunner(new NonExecutingTestSuite(testClass));
            }
            return null;
        }
        if (AndroidRunnerBuilderUtil.hasSuiteMethod(testClass)) {
            if (this.runnerParams.isIgnoreSuiteMethods()) {
                return null;
            }
            Test testTestFromSuiteMethod = SuiteMethod.testFromSuiteMethod(testClass);
            if (!(testTestFromSuiteMethod instanceof TestSuite)) {
                throw new IllegalArgumentException(String.valueOf(testClass.getName()).concat("#suite() did not return a TestSuite"));
            }
            return new JUnit38ClassRunner(new NonExecutingTestSuite((TestSuite) testTestFromSuiteMethod));
        }
        int i = this.runnerCount;
        Runner runnerRunnerForClass = this.builder.runnerForClass(testClass);
        if (runnerRunnerForClass == null) {
            return null;
        }
        return (!(runnerRunnerForClass instanceof ErrorReportingRunner) && this.runnerCount <= i) ? new NonExecutingRunner(runnerRunnerForClass) : runnerRunnerForClass;
    }
}
