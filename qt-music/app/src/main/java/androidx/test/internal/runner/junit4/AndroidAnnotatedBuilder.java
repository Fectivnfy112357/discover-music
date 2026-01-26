package androidx.test.internal.runner.junit4;

import android.util.Log;
import androidx.test.internal.util.AndroidRunnerParams;
import androidx.test.runner.AndroidJUnit4;
import org.junit.internal.builders.AnnotatedBuilder;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.junit.runners.model.RunnerBuilder;

/* loaded from: classes.dex */
public class AndroidAnnotatedBuilder extends AnnotatedBuilder {
    private static final String LOG_TAG = "AndroidAnnotatedBuilder";
    private final AndroidRunnerParams androidRunnerParams;

    public AndroidAnnotatedBuilder(RunnerBuilder suiteBuilder, AndroidRunnerParams runnerParams) {
        super(suiteBuilder);
        this.androidRunnerParams = runnerParams;
    }

    @Override // org.junit.internal.builders.AnnotatedBuilder, org.junit.runners.model.RunnerBuilder
    public Runner runnerForClass(Class<?> testClass) throws Exception {
        try {
            RunWith runWith = (RunWith) testClass.getAnnotation(RunWith.class);
            if (runWith != null && AndroidJUnit4.class.equals(runWith.value())) {
                Class<? extends Runner> clsValue = runWith.value();
                try {
                    Runner runnerBuildAndroidRunner = buildAndroidRunner(clsValue, testClass);
                    if (runnerBuildAndroidRunner != null) {
                        return runnerBuildAndroidRunner;
                    }
                } catch (NoSuchMethodException unused) {
                    return super.buildRunner(clsValue, testClass);
                }
            }
            return super.runnerForClass(testClass);
        } catch (Throwable th) {
            Log.e(LOG_TAG, "Error constructing runner", th);
            throw th;
        }
    }

    public Runner buildAndroidRunner(Class<? extends Runner> runnerClass, Class<?> testClass) throws Exception {
        return runnerClass.getConstructor(Class.class, AndroidRunnerParams.class).newInstance(testClass, this.androidRunnerParams);
    }
}
