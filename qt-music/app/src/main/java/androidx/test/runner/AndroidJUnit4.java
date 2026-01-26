package androidx.test.runner;

import android.util.Log;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.internal.util.AndroidRunnerParams;
import java.lang.reflect.InvocationTargetException;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.manipulation.Filter;
import org.junit.runner.manipulation.Filterable;
import org.junit.runner.manipulation.NoTestsRemainException;
import org.junit.runner.manipulation.Sortable;
import org.junit.runner.manipulation.Sorter;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

@Deprecated
/* loaded from: classes.dex */
public final class AndroidJUnit4 extends Runner implements Filterable, Sortable {
    private static final String TAG = "AndroidJUnit4";
    private final Runner delegate;

    public AndroidJUnit4(Class<?> klass, AndroidRunnerParams runnerParams) throws InitializationError {
        this.delegate = new AndroidJUnit4ClassRunner(klass, runnerParams);
    }

    public AndroidJUnit4(Class<?> klass) throws InitializationError {
        this.delegate = loadRunner(klass);
    }

    private static Runner loadRunner(Class<?> testClass) throws InitializationError {
        return loadRunner(testClass, System.getProperty("android.junit.runner", "org.robolectric.RobolectricTestRunner"));
    }

    private static Runner loadRunner(Class<?> testClass, String className) throws InitializationError {
        try {
            return (Runner) Class.forName(className).getConstructor(Class.class).newInstance(testClass);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, String.valueOf(className).concat(" could not be loaded"), e);
            throw new InitializationError(String.format("Attempted to use AndroidJUnit4 with standard JUnit runner and delegate runner '%s'could not be loaded. Check your build configuration.", className));
        } catch (IllegalAccessException e2) {
            Log.e(TAG, String.valueOf(className).concat(" could not be loaded"), e2);
            throw new InitializationError(String.format("Attempted to use AndroidJUnit4 with standard JUnit runner and delegate runner '%s'could not be loaded. Check your build configuration.", className));
        } catch (InstantiationException e3) {
            Log.e(TAG, String.valueOf(className).concat(" could not be loaded"), e3);
            throw new InitializationError(String.format("Attempted to use AndroidJUnit4 with standard JUnit runner and delegate runner '%s'could not be loaded. Check your build configuration.", className));
        } catch (NoSuchMethodException e4) {
            Log.e(TAG, String.valueOf(className).concat(" could not be loaded"), e4);
            throw new InitializationError(String.format("Attempted to use AndroidJUnit4 with standard JUnit runner and delegate runner '%s'could not be loaded. Check your build configuration.", className));
        } catch (InvocationTargetException e5) {
            Log.e(TAG, String.valueOf(className).concat(" could not be loaded"), e5);
            throw new InitializationError(String.format("Attempted to use AndroidJUnit4 with standard JUnit runner and delegate runner '%s'could not be loaded. Check your build configuration.", className));
        }
    }

    @Override // org.junit.runner.Runner, org.junit.runner.Describable
    public Description getDescription() {
        return this.delegate.getDescription();
    }

    @Override // org.junit.runner.Runner
    public void run(RunNotifier runNotifier) {
        this.delegate.run(runNotifier);
    }

    @Override // org.junit.runner.manipulation.Filterable
    public void filter(Filter filter) throws NoTestsRemainException {
        ((Filterable) this.delegate).filter(filter);
    }

    @Override // org.junit.runner.manipulation.Sortable
    public void sort(Sorter sorter) {
        ((Sortable) this.delegate).sort(sorter);
    }
}
