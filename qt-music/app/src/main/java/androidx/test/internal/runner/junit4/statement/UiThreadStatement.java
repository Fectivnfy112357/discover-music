package androidx.test.internal.runner.junit4.statement;

import android.os.Looper;
import android.util.Log;
import androidx.test.platform.app.InstrumentationRegistry;
import java.lang.annotation.Annotation;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

/* loaded from: classes.dex */
public class UiThreadStatement extends Statement {
    private static final String TAG = "UiThreadStatement";
    private final Statement base;
    private final boolean runOnUiThread;

    public UiThreadStatement(Statement base, boolean runOnUiThread) {
        this.base = base;
        this.runOnUiThread = runOnUiThread;
    }

    public boolean isRunOnUiThread() {
        return this.runOnUiThread;
    }

    @Override // org.junit.runners.model.Statement
    public void evaluate() throws Throwable {
        if (this.runOnUiThread) {
            final AtomicReference atomicReference = new AtomicReference();
            runOnUiThread(new Runnable() { // from class: androidx.test.internal.runner.junit4.statement.UiThreadStatement.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        UiThreadStatement.this.base.evaluate();
                    } catch (Throwable th) {
                        atomicReference.set(th);
                    }
                }
            });
            Throwable th = (Throwable) atomicReference.get();
            if (th != null) {
                throw th;
            }
            return;
        }
        this.base.evaluate();
    }

    public static boolean shouldRunOnUiThread(FrameworkMethod method) {
        Class<? extends Annotation> clsLoadUiThreadClass = loadUiThreadClass("android.test.UiThreadTest");
        if (hasAnnotation(method, clsLoadUiThreadClass)) {
            return true;
        }
        return hasAnnotation(method, clsLoadUiThreadClass) || hasAnnotation(method, loadUiThreadClass("androidx.test.annotation.UiThreadTest"));
    }

    private static boolean hasAnnotation(FrameworkMethod method, Class<? extends Annotation> annotationClass) {
        return annotationClass != null && (method.getAnnotation(annotationClass) != null || classHasAnnotation(method, annotationClass));
    }

    private static boolean classHasAnnotation(FrameworkMethod method, Class<? extends Annotation> annotationClass) {
        Class<?> declaringClass = method.getDeclaringClass();
        for (Class<?> cls : declaringClass.getInterfaces()) {
            if (cls.isAnnotationPresent(annotationClass)) {
                return true;
            }
        }
        while (declaringClass != null) {
            if (declaringClass.isAnnotationPresent(annotationClass)) {
                return true;
            }
            declaringClass = declaringClass.getSuperclass();
        }
        return false;
    }

    private static Class<? extends Annotation> loadUiThreadClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static void runOnUiThread(final Runnable runnable) throws Throwable {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Log.w(TAG, "Already on the UI thread, this method should not be called from the main application thread");
            runnable.run();
            return;
        }
        FutureTask futureTask = new FutureTask(runnable, null);
        InstrumentationRegistry.getInstrumentation().runOnMainSync(futureTask);
        try {
            futureTask.get();
        } catch (ExecutionException e) {
            throw e.getCause();
        }
    }
}
