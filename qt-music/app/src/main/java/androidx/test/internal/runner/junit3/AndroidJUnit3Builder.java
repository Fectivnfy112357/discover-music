package androidx.test.internal.runner.junit3;

import android.util.Log;
import androidx.test.internal.util.AndroidRunnerBuilderUtil;
import androidx.test.internal.util.AndroidRunnerParams;
import org.junit.internal.builders.JUnit3Builder;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

/* loaded from: classes.dex */
public class AndroidJUnit3Builder extends JUnit3Builder {
    public static final Runner NOT_A_VALID_TEST = new Runner() { // from class: androidx.test.internal.runner.junit3.AndroidJUnit3Builder.1
        @Override // org.junit.runner.Runner
        public void run(RunNotifier notifier) {
        }

        @Override // org.junit.runner.Runner, org.junit.runner.Describable
        public Description getDescription() {
            return Description.EMPTY;
        }
    };
    private static final String TAG = "AndroidJUnit3Builder";
    private final AndroidRunnerParams androidRunnerParams;
    private final boolean scanningPath;

    public AndroidJUnit3Builder(AndroidRunnerParams runnerParams, boolean scanningPath) {
        this.androidRunnerParams = runnerParams;
        this.scanningPath = scanningPath;
    }

    @Deprecated
    public AndroidJUnit3Builder(AndroidRunnerParams runnerParams) {
        this(runnerParams, false);
    }

    @Override // org.junit.internal.builders.JUnit3Builder, org.junit.runners.model.RunnerBuilder
    public Runner runnerForClass(Class<?> testClass) throws Throwable {
        try {
            if (!AndroidRunnerBuilderUtil.isJUnit3Test(testClass)) {
                return null;
            }
            if (this.scanningPath && !AndroidRunnerBuilderUtil.hasJUnit3TestMethod(testClass)) {
                return NOT_A_VALID_TEST;
            }
            return new JUnit38ClassRunner(new AndroidTestSuite(testClass, this.androidRunnerParams));
        } catch (Throwable th) {
            Log.e(TAG, "Error constructing runner", th);
            throw th;
        }
    }
}
