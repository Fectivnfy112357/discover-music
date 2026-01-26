package androidx.test.rule;

import android.test.UiThreadTest;
import android.util.Log;
import androidx.test.internal.runner.junit4.statement.UiThreadStatement;
import org.junit.internal.runners.statements.FailOnTimeout;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

@Deprecated
/* loaded from: classes.dex */
public class UiThreadTestRule implements TestRule {
    private static final String TAG = "UiThreadTestRule";

    @Override // org.junit.rules.TestRule
    public Statement apply(final Statement base, Description description) {
        return ((base instanceof FailOnTimeout) || ((base instanceof UiThreadStatement) && !((UiThreadStatement) base).isRunOnUiThread())) ? base : new UiThreadStatement(base, shouldRunOnUiThread(description));
    }

    protected boolean shouldRunOnUiThread(Description description) {
        if (description.getAnnotation(UiThreadTest.class) == null) {
            return description.getAnnotation(androidx.test.annotation.UiThreadTest.class) != null;
        }
        Log.w(TAG, "Deprecated android.test.UiThreadTest annotation is used! please switch to using androidx.test.annotation.UiThreadTest instead.");
        return true;
    }

    public void runOnUiThread(final Runnable runnable) throws Throwable {
        UiThreadStatement.runOnUiThread(runnable);
    }
}
