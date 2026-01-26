package androidx.test.runner;

import android.app.Instrumentation;
import android.content.Context;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import androidx.test.internal.events.client.TestEventClient;
import androidx.test.internal.events.client.TestEventClientArgs;
import androidx.test.internal.events.client.TestEventClientConnectListener;
import androidx.test.internal.runner.ClassPathScanner;
import androidx.test.internal.runner.RunnerArgs;
import androidx.test.internal.runner.TestExecutor;
import androidx.test.internal.runner.TestRequestBuilder;
import androidx.test.internal.runner.listener.ActivityFinisherRunListener;
import androidx.test.internal.runner.listener.CoverageListener;
import androidx.test.internal.runner.listener.DelayInjector;
import androidx.test.internal.runner.listener.InstrumentationResultPrinter;
import androidx.test.internal.runner.listener.LogRunListener;
import androidx.test.internal.runner.listener.SuiteAssignmentPrinter;
import androidx.test.internal.runner.storage.RunnerFileIO;
import androidx.test.internal.runner.storage.RunnerIO;
import androidx.test.internal.runner.storage.RunnerTestStorageIO;
import androidx.test.internal.runner.tracker.AnalyticsBasedUsageTracker;
import androidx.test.internal.util.ReflectionUtil;
import androidx.test.runner.MonitoringInstrumentation;
import androidx.test.runner.lifecycle.ApplicationLifecycleCallback;
import androidx.test.runner.lifecycle.ApplicationLifecycleMonitorRegistry;
import androidx.test.runner.screenshot.Screenshot;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.junit.runner.Request;
import org.junit.runner.notification.RunListener;

/* loaded from: classes.dex */
public class AndroidJUnitRunner extends MonitoringInstrumentation implements TestEventClientConnectListener {
    private static final String LOG_TAG = "AndroidJUnitRunner";
    private static final long MILLIS_TO_WAIT_FOR_TEST_FINISH = TimeUnit.SECONDS.toMillis(20);
    private Bundle arguments;
    private RunnerArgs runnerArgs;
    private UsageTrackerFacilitator usageTrackerFacilitator;
    private InstrumentationResultPrinter instrumentationResultPrinter = new InstrumentationResultPrinter();
    private TestEventClient testEventClient = TestEventClient.NO_OP_CLIENT;
    private RunnerIO runnerIO = new RunnerFileIO();

    @Override // androidx.test.runner.MonitoringInstrumentation, android.app.Instrumentation
    public void onCreate(Bundle arguments) {
        super.onCreate(arguments);
        this.arguments = arguments;
        parseRunnerArgs(arguments);
        if (waitForDebugger(this.runnerArgs)) {
            Log.i("AndroidJUnitRunner", "Waiting for debugger to connect...");
            Debug.waitForDebugger();
            Log.i("AndroidJUnitRunner", "Debugger connected.");
        }
        if (isPrimaryInstrProcess(this.runnerArgs.targetProcess)) {
            this.usageTrackerFacilitator = new UsageTrackerFacilitator(this.runnerArgs);
        } else {
            this.usageTrackerFacilitator = new UsageTrackerFacilitator(false);
        }
        Iterator<ApplicationLifecycleCallback> it = this.runnerArgs.appListeners.iterator();
        while (it.hasNext()) {
            ApplicationLifecycleMonitorRegistry.getInstance().addLifecycleCallback(it.next());
        }
        addScreenCaptureProcessors(this.runnerArgs);
        if (isOrchestratorServiceProvided()) {
            Log.v("AndroidJUnitRunner", "Waiting to connect to the Orchestrator service...");
        } else {
            start();
        }
    }

    private boolean isOrchestratorServiceProvided() {
        TestEventClient testEventClientConnect = TestEventClient.connect(getContext(), this, TestEventClientArgs.builder().setConnectionFactory(AndroidJUnitRunner$$Lambda$0.$instance).setOrchestratorService(this.runnerArgs.orchestratorService).setPrimaryInstProcess(isPrimaryInstrProcess(this.runnerArgs.targetProcess)).setTestDiscoveryRequested(this.runnerArgs.listTestsForOrchestrator).setTestRunEventsRequested(!this.runnerArgs.listTestsForOrchestrator).setTestDiscoveryService(this.runnerArgs.testDiscoveryService).setTestRunEventService(this.runnerArgs.testRunEventsService).setTestPlatformMigration(this.runnerArgs.testPlatformMigration).build());
        this.testEventClient = testEventClientConnect;
        return testEventClientConnect.isTestDiscoveryEnabled() || this.testEventClient.isTestRunEventsEnabled();
    }

    private boolean waitForDebugger(RunnerArgs arguments) {
        return arguments.debug && !arguments.listTestsForOrchestrator;
    }

    @Deprecated
    public void onOrchestratorConnect() {
        onTestEventClientConnect();
    }

    @Override // androidx.test.internal.events.client.TestEventClientConnectListener
    public void onTestEventClientConnect() {
        start();
    }

    private void parseRunnerArgs(Bundle arguments) {
        this.runnerArgs = new RunnerArgs.Builder().fromManifest(this).fromBundle(this, arguments).build();
    }

    private Bundle getArguments() {
        return this.arguments;
    }

    InstrumentationResultPrinter getInstrumentationResultPrinter() {
        return this.instrumentationResultPrinter;
    }

    @Override // androidx.test.runner.MonitoringInstrumentation, android.app.Instrumentation
    public void onStart() throws ExecutionException, IllegalAccessException, InterruptedException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        setJsBridgeClassName("androidx.test.espresso.web.bridge.JavaScriptBridge");
        super.onStart();
        Request requestBuildRequest = buildRequest(this.runnerArgs, getArguments());
        if (this.testEventClient.isTestDiscoveryEnabled()) {
            this.testEventClient.addTests(requestBuildRequest.getRunner().getDescription());
            finish(-1, new Bundle());
            return;
        }
        if (this.runnerArgs.remoteMethod != null) {
            ReflectionUtil.reflectivelyInvokeRemoteMethod(this.runnerArgs.remoteMethod.testClassName, this.runnerArgs.remoteMethod.methodName);
        }
        if (!isPrimaryInstrProcess(this.runnerArgs.targetProcess)) {
            Log.i("AndroidJUnitRunner", "Runner is idle...");
            return;
        }
        if (this.runnerArgs.useTestStorageService) {
            this.runnerIO = new RunnerTestStorageIO();
        }
        Bundle bundle = new Bundle();
        try {
            TestExecutor.Builder builder = new TestExecutor.Builder(this);
            addListeners(this.runnerArgs, builder);
            bundle = builder.build().execute(requestBuildRequest);
        } catch (RuntimeException e) {
            Log.e("AndroidJUnitRunner", "Fatal exception when running tests", e);
            String strValueOf = String.valueOf("Fatal exception when running tests\n");
            String strValueOf2 = String.valueOf(Log.getStackTraceString(e));
            bundle.putString("stream", strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf));
        }
        finish(-1, bundle);
    }

    @Override // androidx.test.runner.MonitoringInstrumentation, android.app.Instrumentation
    public void finish(int resultCode, Bundle results) throws InterruptedException {
        try {
            this.usageTrackerFacilitator.trackUsage("AndroidJUnitRunner", "1.4.0");
            this.usageTrackerFacilitator.sendUsages();
        } catch (RuntimeException e) {
            Log.w("AndroidJUnitRunner", "Failed to send analytics.", e);
        }
        super.finish(resultCode, results);
    }

    final void addListeners(RunnerArgs args, TestExecutor.Builder builder) {
        if (args.newRunListenerMode) {
            addListenersNewOrder(args, builder);
        } else {
            addListenersLegacyOrder(args, builder);
        }
    }

    private void addListenersLegacyOrder(RunnerArgs args, TestExecutor.Builder builder) {
        if (args.logOnly) {
            builder.addRunListener(getInstrumentationResultPrinter());
        } else if (args.suiteAssignment) {
            builder.addRunListener(new SuiteAssignmentPrinter());
        } else {
            builder.addRunListener(new LogRunListener());
            if (this.testEventClient.isTestRunEventsEnabled()) {
                builder.addRunListener(this.testEventClient.getRunListener());
            } else {
                builder.addRunListener(getInstrumentationResultPrinter());
            }
            if (shouldWaitForActivitiesToComplete()) {
                builder.addRunListener(new ActivityFinisherRunListener(this, new MonitoringInstrumentation.ActivityFinisher(), new Runnable() { // from class: androidx.test.runner.AndroidJUnitRunner.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AndroidJUnitRunner.this.waitForActivitiesToComplete();
                    }
                }));
            }
            addDelayListener(args, builder);
            addCoverageListener(args, builder);
        }
        addListenersFromClasspath(builder);
        addListenersFromArg(args, builder);
    }

    private void addListenersNewOrder(RunnerArgs args, TestExecutor.Builder builder) {
        addListenersFromClasspath(builder);
        addListenersFromArg(args, builder);
        if (args.logOnly) {
            builder.addRunListener(getInstrumentationResultPrinter());
            return;
        }
        if (args.suiteAssignment) {
            builder.addRunListener(new SuiteAssignmentPrinter());
            return;
        }
        builder.addRunListener(new LogRunListener());
        addDelayListener(args, builder);
        addCoverageListener(args, builder);
        if (this.testEventClient.isTestRunEventsEnabled()) {
            builder.addRunListener(this.testEventClient.getRunListener());
        } else {
            builder.addRunListener(getInstrumentationResultPrinter());
        }
        if (shouldWaitForActivitiesToComplete()) {
            builder.addRunListener(new ActivityFinisherRunListener(this, new MonitoringInstrumentation.ActivityFinisher(), new Runnable() { // from class: androidx.test.runner.AndroidJUnitRunner.2
                @Override // java.lang.Runnable
                public void run() {
                    AndroidJUnitRunner.this.waitForActivitiesToComplete();
                }
            }));
        }
    }

    private void addScreenCaptureProcessors(RunnerArgs args) {
        Screenshot.addScreenCaptureProcessors(new HashSet(args.screenCaptureProcessors));
    }

    private void addCoverageListener(RunnerArgs args, TestExecutor.Builder builder) {
        if (args.codeCoverage) {
            builder.addRunListener(new CoverageListener(args.codeCoveragePath, this.runnerIO));
        }
    }

    private void addDelayListener(RunnerArgs args, TestExecutor.Builder builder) {
        if (args.delayInMillis > 0) {
            builder.addRunListener(new DelayInjector(args.delayInMillis));
        } else {
            boolean z = args.logOnly;
        }
    }

    private static void addListenersFromClasspath(TestExecutor.Builder builder) {
        Iterator it = ServiceLoader.load(RunListener.class).iterator();
        while (it.hasNext()) {
            builder.addRunListener((RunListener) it.next());
        }
    }

    private void addListenersFromArg(RunnerArgs args, TestExecutor.Builder builder) {
        Iterator<RunListener> it = args.listeners.iterator();
        while (it.hasNext()) {
            builder.addRunListener(it.next());
        }
    }

    @Override // androidx.test.runner.MonitoringInstrumentation, android.app.Instrumentation
    public boolean onException(Object obj, Throwable e) {
        Log.e("AndroidJUnitRunner", "An unhandled exception was thrown by the app.");
        InstrumentationResultPrinter instrumentationResultPrinter = getInstrumentationResultPrinter();
        if (instrumentationResultPrinter != null) {
            instrumentationResultPrinter.reportProcessCrash(e);
        }
        if (this.testEventClient.isTestRunEventsEnabled()) {
            this.testEventClient.reportProcessCrash(e, MILLIS_TO_WAIT_FOR_TEST_FINISH);
        }
        Log.i("AndroidJUnitRunner", "Bringing down the entire Instrumentation process.");
        return super.onException(obj, e);
    }

    Request buildRequest(RunnerArgs runnerArgs, Bundle bundleArgs) {
        TestRequestBuilder testRequestBuilderCreateTestRequestBuilder = createTestRequestBuilder(this, bundleArgs);
        testRequestBuilderCreateTestRequestBuilder.addPathsToScan(runnerArgs.classpathToScan);
        if (runnerArgs.classpathToScan.isEmpty()) {
            testRequestBuilderCreateTestRequestBuilder.addPathsToScan(ClassPathScanner.getDefaultClasspaths(this));
        }
        testRequestBuilderCreateTestRequestBuilder.addFromRunnerArgs(runnerArgs);
        registerUserTracker();
        return testRequestBuilderCreateTestRequestBuilder.build();
    }

    private void registerUserTracker() {
        Context targetContext = getTargetContext();
        if (targetContext != null) {
            this.usageTrackerFacilitator.registerUsageTracker(new AnalyticsBasedUsageTracker.Builder(targetContext).buildIfPossible());
        }
    }

    TestRequestBuilder createTestRequestBuilder(Instrumentation instr, Bundle arguments) {
        return new TestRequestBuilder(instr, arguments);
    }
}
