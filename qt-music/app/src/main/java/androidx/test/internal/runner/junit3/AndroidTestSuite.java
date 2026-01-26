package androidx.test.internal.runner.junit3;

import android.os.Looper;
import android.util.Log;
import androidx.test.internal.util.AndroidRunnerParams;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import junit.framework.Test;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import org.junit.Ignore;

@Ignore
/* loaded from: classes.dex */
class AndroidTestSuite extends DelegatingFilterableTestSuite {
    private static final String TAG = "AndroidTestSuite";
    private final AndroidRunnerParams androidRunnerParams;

    public AndroidTestSuite(Class<?> testClass, AndroidRunnerParams runnerParams) {
        this(new NonLeakyTestSuite(testClass), runnerParams);
    }

    public AndroidTestSuite(TestSuite s, AndroidRunnerParams runnerParams) {
        super(s);
        this.androidRunnerParams = runnerParams;
    }

    @Override // androidx.test.internal.runner.junit3.DelegatingTestSuite, junit.framework.TestSuite, junit.framework.Test
    public void run(TestResult result) throws ExecutionException, InterruptedException, TimeoutException {
        AndroidTestResult androidTestResult = new AndroidTestResult(this.androidRunnerParams.getBundle(), this.androidRunnerParams.getInstrumentation(), result);
        long perTestTimeout = this.androidRunnerParams.getPerTestTimeout();
        if (perTestTimeout > 0) {
            runTestsWithTimeout(perTestTimeout, androidTestResult);
        } else {
            super.run(androidTestResult);
        }
    }

    private void runTestsWithTimeout(long timeout, AndroidTestResult result) throws ExecutionException, InterruptedException, TimeoutException {
        int iTestCount = testCount();
        for (int i = 0; i < iTestCount; i++) {
            runTestWithTimeout(testAt(i), result, timeout);
        }
    }

    private void runTestWithTimeout(final Test test, final AndroidTestResult androidTestResult, final long timeout) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor(new ThreadFactory(this) { // from class: androidx.test.internal.runner.junit3.AndroidTestSuite.1
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable r) {
                Thread threadNewThread = Executors.defaultThreadFactory().newThread(r);
                threadNewThread.setName(AndroidTestSuite.TAG);
                return threadNewThread;
            }
        });
        Runnable runnable = new Runnable(this) { // from class: androidx.test.internal.runner.junit3.AndroidTestSuite.2
            @Override // java.lang.Runnable
            public void run() {
                test.run(androidTestResult);
            }
        };
        androidTestResult.setCurrentTimeout(timeout);
        Future<?> futureSubmit = executorServiceNewSingleThreadExecutor.submit(runnable);
        executorServiceNewSingleThreadExecutor.shutdown();
        try {
            if (executorServiceNewSingleThreadExecutor.awaitTermination(timeout, TimeUnit.MILLISECONDS)) {
                return;
            }
            executorServiceNewSingleThreadExecutor.shutdownNow();
            if (executorServiceNewSingleThreadExecutor.awaitTermination(1L, TimeUnit.MINUTES)) {
                return;
            }
            Log.e(TAG, "Failed to to stop test execution thread, the correctness of the test runner is at risk. Abort all execution!");
            try {
                try {
                    futureSubmit.get(0L, TimeUnit.MILLISECONDS);
                } catch (ExecutionException e) {
                    Log.e(TAG, "Exception from the execution thread", e.getCause());
                }
            } catch (TimeoutException e2) {
                Log.e(TAG, "Exception from the execution thread", e2);
            }
            terminateAllRunnerExecution(new IllegalStateException(String.format("Test timed out after %d milliseconds but execution thread failed to terminate\nDumping instr and main threads:\n%s", Long.valueOf(timeout), getStackTraces())));
        } catch (InterruptedException e3) {
            Log.e(TAG, "The correctness of the test runner is at risk. Abort all execution!");
            terminateAllRunnerExecution(new IllegalStateException(String.format("Test execution thread got interrupted:\n%s\nDumping instr and main threads:\n%s", e3, getStackTraces())));
        }
    }

    private void terminateAllRunnerExecution(final RuntimeException exception) throws InterruptedException {
        Thread thread = new Thread(new Runnable(this) { // from class: androidx.test.internal.runner.junit3.AndroidTestSuite.3
            @Override // java.lang.Runnable
            public void run() {
                throw exception;
            }
        }, "Terminator");
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException unused) {
        }
    }

    private String getStackTraces() {
        StringBuilder sb = new StringBuilder();
        Thread threadCurrentThread = Thread.currentThread();
        sb.append(threadCurrentThread.toString()).append('\n');
        for (StackTraceElement stackTraceElement : threadCurrentThread.getStackTrace()) {
            sb.append("\tat ").append(stackTraceElement.toString()).append('\n');
        }
        sb.append('\n');
        Thread thread = Looper.getMainLooper().getThread();
        sb.append(thread.toString()).append('\n');
        for (StackTraceElement stackTraceElement2 : thread.getStackTrace()) {
            sb.append("\tat ").append(stackTraceElement2.toString()).append('\n');
        }
        sb.append('\n');
        return sb.toString();
    }
}
