package androidx.test.internal.runner.junit4.statement;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.MultipleFailureException;
import org.junit.runners.model.Statement;

/* loaded from: classes.dex */
public class RunAfters extends UiThreadStatement {
    private final List<FrameworkMethod> afters;
    private final Statement next;
    private final Object target;

    public RunAfters(FrameworkMethod method, Statement next, List<FrameworkMethod> afters, Object target) {
        super(next, shouldRunOnUiThread(method));
        this.next = next;
        this.afters = afters;
        this.target = target;
    }

    @Override // androidx.test.internal.runner.junit4.statement.UiThreadStatement, org.junit.runners.model.Statement
    public void evaluate() throws Exception {
        final CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        try {
            this.next.evaluate();
            for (final FrameworkMethod frameworkMethod : this.afters) {
                if (shouldRunOnUiThread(frameworkMethod)) {
                    runOnUiThread(new Runnable() { // from class: androidx.test.internal.runner.junit4.statement.RunAfters.1
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                frameworkMethod.invokeExplosively(RunAfters.this.target, new Object[0]);
                            } catch (Throwable th) {
                                copyOnWriteArrayList.add(th);
                            }
                        }
                    });
                } else {
                    try {
                        frameworkMethod.invokeExplosively(this.target, new Object[0]);
                    } catch (Throwable th) {
                        copyOnWriteArrayList.add(th);
                    }
                }
            }
        } catch (Throwable th2) {
            try {
                copyOnWriteArrayList.add(th2);
                for (final FrameworkMethod frameworkMethod2 : this.afters) {
                    if (shouldRunOnUiThread(frameworkMethod2)) {
                        runOnUiThread(new Runnable() { // from class: androidx.test.internal.runner.junit4.statement.RunAfters.1
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    frameworkMethod2.invokeExplosively(RunAfters.this.target, new Object[0]);
                                } catch (Throwable th3) {
                                    copyOnWriteArrayList.add(th3);
                                }
                            }
                        });
                    } else {
                        try {
                            frameworkMethod2.invokeExplosively(this.target, new Object[0]);
                        } catch (Throwable th3) {
                            copyOnWriteArrayList.add(th3);
                        }
                    }
                }
            } catch (Throwable th4) {
                for (final FrameworkMethod frameworkMethod3 : this.afters) {
                    if (shouldRunOnUiThread(frameworkMethod3)) {
                        runOnUiThread(new Runnable() { // from class: androidx.test.internal.runner.junit4.statement.RunAfters.1
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    frameworkMethod3.invokeExplosively(RunAfters.this.target, new Object[0]);
                                } catch (Throwable th32) {
                                    copyOnWriteArrayList.add(th32);
                                }
                            }
                        });
                    } else {
                        try {
                            frameworkMethod3.invokeExplosively(this.target, new Object[0]);
                        } catch (Throwable th5) {
                            copyOnWriteArrayList.add(th5);
                        }
                    }
                }
                throw th4;
            }
        }
        MultipleFailureException.assertEmpty(copyOnWriteArrayList);
    }
}
