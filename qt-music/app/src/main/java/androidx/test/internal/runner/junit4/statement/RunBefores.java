package androidx.test.internal.runner.junit4.statement;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

/* loaded from: classes.dex */
public class RunBefores extends UiThreadStatement {
    private final List<FrameworkMethod> befores;
    private final Statement next;
    private final Object target;

    public RunBefores(FrameworkMethod method, Statement next, List<FrameworkMethod> befores, Object target) {
        super(next, shouldRunOnUiThread(method));
        this.next = next;
        this.befores = befores;
        this.target = target;
    }

    @Override // androidx.test.internal.runner.junit4.statement.UiThreadStatement, org.junit.runners.model.Statement
    public void evaluate() throws Throwable {
        final AtomicReference atomicReference = new AtomicReference();
        for (final FrameworkMethod frameworkMethod : this.befores) {
            if (shouldRunOnUiThread(frameworkMethod)) {
                runOnUiThread(new Runnable() { // from class: androidx.test.internal.runner.junit4.statement.RunBefores.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            frameworkMethod.invokeExplosively(RunBefores.this.target, new Object[0]);
                        } catch (Throwable th) {
                            atomicReference.set(th);
                        }
                    }
                });
                Throwable th = (Throwable) atomicReference.get();
                if (th != null) {
                    throw th;
                }
            } else {
                frameworkMethod.invokeExplosively(this.target, new Object[0]);
            }
        }
        this.next.evaluate();
    }
}
