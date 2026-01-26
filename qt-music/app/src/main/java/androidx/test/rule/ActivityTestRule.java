package androidx.test.rule;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import androidx.test.internal.runner.junit4.statement.UiThreadStatement;
import androidx.test.internal.util.Checks;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.MonitoringInstrumentation;
import androidx.test.runner.intercepting.SingleActivityFactory;
import androidx.test.runner.lifecycle.ActivityLifecycleCallback;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.Stage;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

@Deprecated
/* loaded from: classes.dex */
public class ActivityTestRule<T extends Activity> implements TestRule {
    private static final String FIELD_RESULT_CODE = "mResultCode";
    private static final String FIELD_RESULT_DATA = "mResultData";
    private static final int NO_FLAGS_SET = 0;
    private static final String TAG = "ActivityTestRule";
    volatile WeakReference<T> activity;
    private final Class<T> activityClass;
    private SingleActivityFactory<T> activityFactory;
    private volatile Instrumentation.ActivityResult activityResult;
    private boolean initialTouchMode;
    private Instrumentation instrumentation;
    private boolean launchActivity;
    private final int launchFlags;
    private final ActivityLifecycleCallback lifecycleCallback;
    private final String targetPackage;

    protected void afterActivityFinished() {
    }

    protected void afterActivityLaunched() {
    }

    protected void beforeActivityLaunched() {
    }

    protected Intent getActivityIntent() {
        return null;
    }

    public ActivityTestRule(Class<T> activityClass) {
        this(activityClass, false);
    }

    public ActivityTestRule(Class<T> activityClass, boolean initialTouchMode) {
        this((Class) activityClass, initialTouchMode, true);
    }

    public ActivityTestRule(Class<T> activityClass, boolean initialTouchMode, boolean launchActivity) {
        this(activityClass, InstrumentationRegistry.getInstrumentation().getTargetContext().getPackageName(), 268435456, initialTouchMode, launchActivity);
    }

    public ActivityTestRule(SingleActivityFactory<T> activityFactory, boolean initialTouchMode, boolean launchActivity) {
        this(activityFactory.getActivityClassToIntercept(), initialTouchMode, launchActivity);
        this.activityFactory = activityFactory;
    }

    public ActivityTestRule(Class<T> activityClass, String targetPackage, int launchFlags, boolean initialTouchMode, boolean launchActivity) {
        this.lifecycleCallback = new LifecycleCallback();
        this.initialTouchMode = false;
        this.launchActivity = false;
        this.activity = makeWeakReference(null);
        this.instrumentation = InstrumentationRegistry.getInstrumentation();
        this.activityClass = activityClass;
        this.targetPackage = (String) Checks.checkNotNull(targetPackage, "targetPackage cannot be null!");
        this.launchFlags = launchFlags;
        this.initialTouchMode = initialTouchMode;
        this.launchActivity = launchActivity;
    }

    public T getActivity() {
        T t = this.activity.get();
        if (t == null) {
            Log.w(TAG, "Activity wasn't created yet or already stopped");
        }
        return t;
    }

    @Override // org.junit.rules.TestRule
    public Statement apply(final Statement base, Description description) {
        return new ActivityStatement(base);
    }

    public T launchActivity(Intent startIntent) {
        this.instrumentation.setInTouchMode(this.initialTouchMode);
        if (startIntent == null && (startIntent = getActivityIntent()) == null) {
            Log.w(TAG, "getActivityIntent() returned null using default: Intent(Intent.ACTION_MAIN)");
            startIntent = new Intent("android.intent.action.MAIN");
        }
        if (startIntent.getComponent() == null) {
            startIntent.setClassName(this.targetPackage, this.activityClass.getName());
        }
        if (startIntent.getFlags() == 0) {
            startIntent.addFlags(this.launchFlags);
        }
        beforeActivityLaunched();
        T tCast = this.activityClass.cast(this.instrumentation.startActivitySync(startIntent));
        this.activity = makeWeakReference(tCast);
        this.instrumentation.waitForIdleSync();
        if (tCast != null) {
            ActivityLifecycleMonitorRegistry.getInstance().addLifecycleCallback(this.lifecycleCallback);
            afterActivityLaunched();
        } else {
            String str = String.format("Activity %s, failed to launch", startIntent.getComponent());
            Bundle bundle = new Bundle();
            String strValueOf = String.valueOf("ActivityTestRule ");
            String strValueOf2 = String.valueOf(str);
            bundle.putString("stream", strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf));
            this.instrumentation.sendStatus(0, bundle);
            Log.e(TAG, str);
        }
        return tCast;
    }

    void setInstrumentation(Instrumentation instrumentation) {
        this.instrumentation = (Instrumentation) Checks.checkNotNull(instrumentation, "instrumentation cannot be null!");
    }

    public void finishActivity() {
        try {
            if (this.activity.get() != null) {
                callFinishOnMainSync();
            }
        } finally {
            this.activity = makeWeakReference(null);
            afterActivityFinished();
        }
    }

    void callFinishOnMainSync() {
        try {
            final T t = this.activity.get();
            runOnUiThread(new Runnable() { // from class: androidx.test.rule.ActivityTestRule.1
                @Override // java.lang.Runnable
                public void run() throws NoSuchFieldException {
                    Checks.checkState(t != null, "Activity was not launched. If you manually finished it, you must launch it again before finishing it. ");
                    t.finish();
                    ActivityTestRule.this.setActivityResultForActivity(t);
                }
            });
            this.instrumentation.waitForIdleSync();
        } catch (Throwable th) {
            Log.e(TAG, "Failed to execute activity.finish() on the main thread", th);
            throw new IllegalStateException("Failed to execute activity.finish() on the main thread", th);
        }
    }

    public Instrumentation.ActivityResult getActivityResult() {
        if (this.activityResult == null) {
            final T t = this.activity.get();
            Checks.checkNotNull(t, "Activity wasn't created yet or already destroyed!");
            try {
                runOnUiThread(new Runnable() { // from class: androidx.test.rule.ActivityTestRule.2
                    @Override // java.lang.Runnable
                    public void run() throws NoSuchFieldException {
                        Checks.checkState(t.isFinishing(), "Activity is not finishing!");
                        ActivityTestRule.this.setActivityResultForActivity(t);
                    }
                });
            } catch (Throwable th) {
                throw new IllegalStateException(th);
            }
        }
        return this.activityResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setActivityResultForActivity(final T activity) throws NoSuchFieldException {
        Checks.checkState(Looper.myLooper() == Looper.getMainLooper(), "Must be called on the main thread!");
        Checks.checkNotNull(activity, "Activity wasn't created yet or already destroyed!");
        try {
            Field declaredField = Activity.class.getDeclaredField(FIELD_RESULT_CODE);
            declaredField.setAccessible(true);
            Field declaredField2 = Activity.class.getDeclaredField(FIELD_RESULT_DATA);
            declaredField2.setAccessible(true);
            this.activityResult = new Instrumentation.ActivityResult(((Integer) declaredField.get(activity)).intValue(), (Intent) declaredField2.get(activity));
        } catch (IllegalAccessException e) {
            Log.e(TAG, "Field mResultCode or mResultData is not accessible", e);
            throw new RuntimeException("Field mResultCode or mResultData is not accessible", e);
        } catch (NoSuchFieldException e2) {
            Log.e(TAG, "Looks like the Android Activity class has changed itsprivate fields for mResultCode or mResultData. Time to update the reflection code.", e2);
            throw new RuntimeException("Looks like the Android Activity class has changed itsprivate fields for mResultCode or mResultData. Time to update the reflection code.", e2);
        }
    }

    public void runOnUiThread(final Runnable runnable) throws Throwable {
        UiThreadStatement.runOnUiThread(runnable);
    }

    private class ActivityStatement extends Statement {
        private final Statement base;

        public ActivityStatement(Statement base) {
            this.base = base;
        }

        @Override // org.junit.runners.model.Statement
        public void evaluate() throws Throwable {
            MonitoringInstrumentation monitoringInstrumentation = ActivityTestRule.this.instrumentation instanceof MonitoringInstrumentation ? (MonitoringInstrumentation) ActivityTestRule.this.instrumentation : null;
            try {
                if (ActivityTestRule.this.activityFactory != null && monitoringInstrumentation != null) {
                    monitoringInstrumentation.interceptActivityUsing(ActivityTestRule.this.activityFactory);
                }
                if (ActivityTestRule.this.launchActivity) {
                    ActivityTestRule activityTestRule = ActivityTestRule.this;
                    activityTestRule.launchActivity(activityTestRule.getActivityIntent());
                }
                this.base.evaluate();
            } finally {
                if (monitoringInstrumentation != null) {
                    monitoringInstrumentation.useDefaultInterceptingActivityFactory();
                }
                if (ActivityTestRule.this.activity.get() != null) {
                    ActivityTestRule.this.finishActivity();
                }
                ActivityTestRule.this.activityResult = null;
                ActivityLifecycleMonitorRegistry.getInstance().removeLifecycleCallback(ActivityTestRule.this.lifecycleCallback);
            }
        }
    }

    WeakReference<T> makeWeakReference(T activity) {
        return new WeakReference<>(activity);
    }

    private class LifecycleCallback implements ActivityLifecycleCallback {
        private LifecycleCallback() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.test.runner.lifecycle.ActivityLifecycleCallback
        public void onActivityLifecycleChanged(Activity activity, Stage stage) throws NoSuchFieldException {
            if (ActivityTestRule.this.activityClass.isInstance(activity)) {
                if (Stage.RESUMED == stage) {
                    ActivityTestRule activityTestRule = ActivityTestRule.this;
                    activityTestRule.activity = activityTestRule.makeWeakReference((Activity) activityTestRule.activityClass.cast(activity));
                } else if (Stage.PAUSED == stage && activity.isFinishing() && ActivityTestRule.this.activityResult != null) {
                    ActivityTestRule activityTestRule2 = ActivityTestRule.this;
                    activityTestRule2.setActivityResultForActivity((Activity) activityTestRule2.activityClass.cast(activity));
                }
            }
        }
    }
}
