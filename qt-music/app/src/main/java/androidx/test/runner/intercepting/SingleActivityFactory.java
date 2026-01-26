package androidx.test.runner.intercepting;

import android.app.Activity;
import android.content.Intent;
import androidx.test.internal.util.Checks;

/* loaded from: classes.dex */
public abstract class SingleActivityFactory<T extends Activity> implements InterceptingActivityFactory {
    private final Class<T> activityClassToIntercept;

    protected abstract T create(Intent intent);

    public SingleActivityFactory(Class<T> activityClassToIntercept) {
        Checks.checkNotNull(activityClassToIntercept);
        this.activityClassToIntercept = (Class) Checks.checkNotNull(activityClassToIntercept);
    }

    @Override // androidx.test.runner.intercepting.InterceptingActivityFactory
    public final boolean shouldIntercept(ClassLoader classLoader, String className, Intent intent) {
        return this.activityClassToIntercept.getName().equals(className);
    }

    @Override // androidx.test.runner.intercepting.InterceptingActivityFactory
    public final Activity create(ClassLoader classLoader, String className, Intent intent) {
        if (!shouldIntercept(classLoader, className, intent)) {
            throw new UnsupportedOperationException(String.format("Can't create instance of %s", className));
        }
        return create(intent);
    }

    public final Class<T> getActivityClassToIntercept() {
        return this.activityClassToIntercept;
    }
}
