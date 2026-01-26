package androidx.test.rule;

import android.os.Debug;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/* loaded from: classes.dex */
public class DisableOnAndroidDebug implements TestRule {
    private final TestRule rule;

    public DisableOnAndroidDebug(TestRule rule) {
        this.rule = rule;
    }

    @Override // org.junit.rules.TestRule
    public final Statement apply(final Statement base, Description description) {
        return isDebugging() ? base : this.rule.apply(base, description);
    }

    public boolean isDebugging() {
        return Debug.isDebuggerConnected();
    }
}
