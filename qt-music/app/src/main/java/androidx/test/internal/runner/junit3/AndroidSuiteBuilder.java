package androidx.test.internal.runner.junit3;

import android.util.Log;
import androidx.test.internal.util.AndroidRunnerParams;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.junit.internal.builders.SuiteMethodBuilder;
import org.junit.internal.runners.SuiteMethod;
import org.junit.runner.Runner;

/* loaded from: classes.dex */
public class AndroidSuiteBuilder extends SuiteMethodBuilder {
    private static final String LOG_TAG = "AndroidSuiteBuilder";
    private final AndroidRunnerParams androidRunnerParams;

    public AndroidSuiteBuilder(AndroidRunnerParams runnerParams) {
        this.androidRunnerParams = runnerParams;
    }

    @Override // org.junit.internal.builders.SuiteMethodBuilder, org.junit.runners.model.RunnerBuilder
    public Runner runnerForClass(Class<?> testClass) throws Throwable {
        if (this.androidRunnerParams.isIgnoreSuiteMethods()) {
            return null;
        }
        try {
            if (!hasSuiteMethod(testClass)) {
                return null;
            }
            Test testTestFromSuiteMethod = SuiteMethod.testFromSuiteMethod(testClass);
            if (!(testTestFromSuiteMethod instanceof TestSuite)) {
                throw new IllegalArgumentException(String.valueOf(testClass.getName()).concat("#suite() did not return a TestSuite"));
            }
            return new JUnit38ClassRunner(new AndroidTestSuite((TestSuite) testTestFromSuiteMethod, this.androidRunnerParams));
        } catch (Throwable th) {
            Log.e(LOG_TAG, "Error constructing runner", th);
            throw th;
        }
    }
}
