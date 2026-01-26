package androidx.test.internal.runner;

import android.util.Log;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runner.notification.StoppedByUserException;
import org.junit.runners.model.RunnerBuilder;

/* loaded from: classes.dex */
class TestLoader {
    private static final String LOG_TAG = "TestLoader";
    private final ClassLoader classLoader;
    private final RunnerBuilder runnerBuilder;
    private final Map<String, Runner> runnersMap = new LinkedHashMap();

    static TestLoader testLoader(ClassLoader classLoader, RunnerBuilder runnerBuilder, boolean scanningPath) {
        if (scanningPath) {
            runnerBuilder = new ScanningRunnerBuilder(runnerBuilder);
        }
        if (classLoader == null) {
            classLoader = TestLoader.class.getClassLoader();
        }
        return new TestLoader(classLoader, runnerBuilder);
    }

    private TestLoader(ClassLoader classLoader, RunnerBuilder runnerBuilder) {
        this.classLoader = classLoader;
        this.runnerBuilder = runnerBuilder;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r3v4, types: [org.junit.runner.Runner] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void doCreateRunner(java.lang.String r6, boolean r7) throws java.lang.ClassNotFoundException {
        /*
            r5 = this;
            java.util.Map<java.lang.String, org.junit.runner.Runner> r0 = r5.runnersMap
            boolean r0 = r0.containsKey(r6)
            if (r0 == 0) goto L9
            return
        L9:
            r0 = 0
            r1 = 0
            java.lang.ClassLoader r2 = r5.classLoader     // Catch: java.lang.LinkageError -> L43 java.lang.ClassNotFoundException -> L45
            java.lang.Class r2 = java.lang.Class.forName(r6, r1, r2)     // Catch: java.lang.LinkageError -> L43 java.lang.ClassNotFoundException -> L45
            org.junit.runners.model.RunnerBuilder r3 = r5.runnerBuilder     // Catch: java.lang.LinkageError -> L43 java.lang.ClassNotFoundException -> L45
            org.junit.runner.Runner r3 = r3.safeRunnerForClass(r2)     // Catch: java.lang.LinkageError -> L43 java.lang.ClassNotFoundException -> L45
            if (r3 != 0) goto L2b
            java.lang.String r4 = "Skipping class %s: not a test"
            java.lang.String r2 = r2.getName()     // Catch: java.lang.LinkageError -> L43 java.lang.ClassNotFoundException -> L45
            java.lang.Object[] r2 = new java.lang.Object[]{r2}     // Catch: java.lang.LinkageError -> L43 java.lang.ClassNotFoundException -> L45
            java.lang.String r2 = java.lang.String.format(r4, r2)     // Catch: java.lang.LinkageError -> L43 java.lang.ClassNotFoundException -> L45
            logDebug(r2)     // Catch: java.lang.LinkageError -> L43 java.lang.ClassNotFoundException -> L45
            goto L41
        L2b:
            org.junit.runner.Runner r4 = androidx.test.internal.runner.junit3.AndroidJUnit3Builder.NOT_A_VALID_TEST     // Catch: java.lang.LinkageError -> L43 java.lang.ClassNotFoundException -> L45
            if (r3 != r4) goto L41
            java.lang.String r3 = "Skipping class %s: not a valid test"
            java.lang.String r2 = r2.getName()     // Catch: java.lang.LinkageError -> L43 java.lang.ClassNotFoundException -> L45
            java.lang.Object[] r2 = new java.lang.Object[]{r2}     // Catch: java.lang.LinkageError -> L43 java.lang.ClassNotFoundException -> L45
            java.lang.String r2 = java.lang.String.format(r3, r2)     // Catch: java.lang.LinkageError -> L43 java.lang.ClassNotFoundException -> L45
            logDebug(r2)     // Catch: java.lang.LinkageError -> L43 java.lang.ClassNotFoundException -> L45
            goto L67
        L41:
            r0 = r3
            goto L67
        L43:
            r2 = move-exception
            goto L46
        L45:
            r2 = move-exception
        L46:
            java.lang.String r3 = "Could not find class: %s"
            java.lang.Object[] r4 = new java.lang.Object[]{r6}
            java.lang.String r3 = java.lang.String.format(r3, r4)
            java.lang.String r4 = "TestLoader"
            android.util.Log.e(r4, r3)
            java.lang.annotation.Annotation[] r1 = new java.lang.annotation.Annotation[r1]
            org.junit.runner.Description r1 = org.junit.runner.Description.createSuiteDescription(r6, r1)
            org.junit.runner.notification.Failure r3 = new org.junit.runner.notification.Failure
            r3.<init>(r1, r2)
            if (r7 != 0) goto L67
            androidx.test.internal.runner.TestLoader$UnloadableClassRunner r0 = new androidx.test.internal.runner.TestLoader$UnloadableClassRunner
            r0.<init>(r1, r3)
        L67:
            if (r0 == 0) goto L6e
            java.util.Map<java.lang.String, org.junit.runner.Runner> r7 = r5.runnersMap
            r7.put(r6, r0)
        L6e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.test.internal.runner.TestLoader.doCreateRunner(java.lang.String, boolean):void");
    }

    List<Runner> getRunnersFor(Collection<String> classNames, boolean isScanningPath) throws ClassNotFoundException {
        Iterator<String> it = classNames.iterator();
        while (it.hasNext()) {
            doCreateRunner(it.next(), isScanningPath);
        }
        return new ArrayList(this.runnersMap.values());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void logDebug(String msg) {
        if (Log.isLoggable(LOG_TAG, 3)) {
            Log.d(LOG_TAG, msg);
        }
    }

    private static class ScanningRunnerBuilder extends RunnerBuilder {
        private final RunnerBuilder runnerBuilder;

        ScanningRunnerBuilder(RunnerBuilder runnerBuilder) {
            this.runnerBuilder = runnerBuilder;
        }

        @Override // org.junit.runners.model.RunnerBuilder
        public Runner runnerForClass(Class<?> testClass) throws Throwable {
            if (Modifier.isAbstract(testClass.getModifiers())) {
                TestLoader.logDebug(String.format("Skipping abstract class %s: not a test", testClass.getName()));
                return null;
            }
            return this.runnerBuilder.runnerForClass(testClass);
        }
    }

    static class UnloadableClassRunner extends Runner {
        private final Description description;
        private final Failure failure;

        UnloadableClassRunner(Description description, Failure failure) {
            this.description = description;
            this.failure = failure;
        }

        @Override // org.junit.runner.Runner, org.junit.runner.Describable
        public Description getDescription() {
            return this.description;
        }

        @Override // org.junit.runner.Runner
        public void run(RunNotifier notifier) throws StoppedByUserException {
            notifier.fireTestStarted(this.description);
            notifier.fireTestFailure(this.failure);
            notifier.fireTestFinished(this.description);
        }
    }
}
