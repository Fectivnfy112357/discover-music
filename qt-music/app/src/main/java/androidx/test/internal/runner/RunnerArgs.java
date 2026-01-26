package androidx.test.internal.runner;

import android.app.Instrumentation;
import android.app.UiAutomation;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Log;
import androidx.test.runner.lifecycle.ApplicationLifecycleCallback;
import androidx.test.runner.screenshot.ScreenCaptureProcessor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.runner.manipulation.Filter;
import org.junit.runner.notification.RunListener;
import org.junit.runners.model.RunnerBuilder;

/* loaded from: classes.dex */
public class RunnerArgs {
    static final String ARGUMENT_ANNOTATION = "annotation";
    static final String ARGUMENT_APP_LISTENER = "appListener";
    static final String ARGUMENT_CLASSPATH_TO_SCAN = "classpathToScan";
    static final String ARGUMENT_CLASS_LOADER = "classLoader";
    static final String ARGUMENT_COVERAGE = "coverage";
    static final String ARGUMENT_COVERAGE_PATH = "coverageFile";
    static final String ARGUMENT_DEBUG = "debug";
    static final String ARGUMENT_DELAY_IN_MILLIS = "delay_msec";
    static final String ARGUMENT_DISABLE_ANALYTICS = "disableAnalytics";
    static final String ARGUMENT_FILTER = "filter";
    static final String ARGUMENT_LISTENER = "listener";
    static final String ARGUMENT_LIST_TESTS_FOR_ORCHESTRATOR = "listTestsForOrchestrator";
    static final String ARGUMENT_LOG_ONLY = "log";
    static final String ARGUMENT_NOT_ANNOTATION = "notAnnotation";
    static final String ARGUMENT_NOT_TEST_CLASS = "notClass";
    static final String ARGUMENT_NOT_TEST_FILE = "notTestFile";
    static final String ARGUMENT_NOT_TEST_PACKAGE = "notPackage";
    static final String ARGUMENT_NUM_SHARDS = "numShards";
    static final String ARGUMENT_ORCHESTRATOR_DISCOVERY_SERVICE = "testDiscoveryService";
    static final String ARGUMENT_ORCHESTRATOR_RUN_EVENTS_SERVICE = "testRunEventsService";
    static final String ARGUMENT_ORCHESTRATOR_SERVICE = "orchestratorService";
    static final String ARGUMENT_REMOTE_INIT_METHOD = "remoteMethod";
    static final String ARGUMENT_RUNNER_BUILDER = "runnerBuilder";
    static final String ARGUMENT_RUN_LISTENER_NEW_ORDER = "newRunListenerMode";
    static final String ARGUMENT_SCREENSHOT_PROCESSORS = "screenCaptureProcessors";
    static final String ARGUMENT_SHARD_INDEX = "shardIndex";
    static final String ARGUMENT_SHELL_EXEC_BINDER_KEY = "shellExecBinderKey";
    static final String ARGUMENT_SUITE_ASSIGNMENT = "suiteAssignment";
    static final String ARGUMENT_TARGET_PROCESS = "targetProcess";
    static final String ARGUMENT_TESTS_REGEX = "tests_regex";
    static final String ARGUMENT_TEST_CLASS = "class";
    static final String ARGUMENT_TEST_FILE = "testFile";
    static final String ARGUMENT_TEST_PACKAGE = "package";
    static final String ARGUMENT_TEST_PLATFORM_MIGRATION = "temporary_testPlatformMigration";
    static final String ARGUMENT_TEST_SIZE = "size";
    static final String ARGUMENT_TIMEOUT = "timeout_msec";
    static final String ARGUMENT_USE_TEST_STORAGE_SERVICE = "useTestStorageService";
    private static final String CLASSPATH_SEPARATOR = ":";
    private static final String CLASS_SEPARATOR = ",";
    private static final String LOG_TAG = "RunnerArgs";
    private static final char METHOD_SEPARATOR = '#';
    public final List<String> annotations;
    public final List<ApplicationLifecycleCallback> appListeners;
    public final ClassLoader classLoader;
    public final Set<String> classpathToScan;
    public final boolean codeCoverage;
    public final String codeCoveragePath;
    public final boolean debug;
    public final int delayInMillis;
    public final boolean disableAnalytics;
    public final List<Filter> filters;
    public final boolean listTestsForOrchestrator;
    public final List<RunListener> listeners;
    public final boolean logOnly;
    public final boolean newRunListenerMode;
    public final List<String> notAnnotations;
    public final List<String> notTestPackages;
    public final List<TestArg> notTests;
    public final int numShards;
    public final String orchestratorService;
    public final TestArg remoteMethod;
    public final List<Class<? extends RunnerBuilder>> runnerBuilderClasses;
    public final List<ScreenCaptureProcessor> screenCaptureProcessors;
    public final int shardIndex;
    public final String shellExecBinderKey;
    public final boolean suiteAssignment;
    public final String targetProcess;
    public final String testDiscoveryService;
    public final List<String> testPackages;
    public final boolean testPlatformMigration;
    public final String testRunEventsService;
    public final String testSize;
    public final long testTimeout;
    public final List<TestArg> tests;
    public final String testsRegEx;
    public final boolean useTestStorageService;

    public static class TestArg {
        public final String methodName;
        public final String testClassName;

        TestArg(String className, String methodName) {
            this.testClassName = className;
            this.methodName = methodName;
        }

        TestArg(String className) {
            this(className, null);
        }

        public String toString() {
            String str = this.methodName;
            if (str == null) {
                return this.testClassName;
            }
            String str2 = this.testClassName;
            return new StringBuilder(String.valueOf(str2).length() + 1 + String.valueOf(str).length()).append(str2).append(RunnerArgs.METHOD_SEPARATOR).append(str).toString();
        }
    }

    private static final class TestFileArgs {
        private final List<String> packages;
        private final List<TestArg> tests;

        private TestFileArgs() {
            this.tests = new ArrayList();
            this.packages = new ArrayList();
        }
    }

    private RunnerArgs(Builder builder) {
        this.debug = builder.debug;
        this.suiteAssignment = builder.suiteAssignment;
        this.codeCoverage = builder.codeCoverage;
        this.codeCoveragePath = builder.codeCoveragePath;
        this.delayInMillis = builder.delayInMillis;
        this.logOnly = builder.logOnly;
        this.testPackages = builder.testPackages;
        this.notTestPackages = builder.notTestPackages;
        this.testSize = builder.testSize;
        this.annotations = Collections.unmodifiableList(builder.annotations);
        this.notAnnotations = Collections.unmodifiableList(builder.notAnnotations);
        this.testTimeout = builder.testTimeout;
        this.listeners = Collections.unmodifiableList(builder.listeners);
        this.filters = Collections.unmodifiableList(builder.filters);
        this.runnerBuilderClasses = Collections.unmodifiableList(builder.runnerBuilderClasses);
        this.tests = Collections.unmodifiableList(builder.tests);
        this.notTests = Collections.unmodifiableList(builder.notTests);
        this.numShards = builder.numShards;
        this.shardIndex = builder.shardIndex;
        this.disableAnalytics = builder.disableAnalytics;
        this.appListeners = Collections.unmodifiableList(builder.appListeners);
        this.classLoader = builder.classLoader;
        this.classpathToScan = builder.classpathToScan;
        this.remoteMethod = builder.remoteMethod;
        this.orchestratorService = builder.orchestratorService;
        this.listTestsForOrchestrator = builder.listTestsForOrchestrator;
        this.testDiscoveryService = builder.testDiscoveryService;
        this.testRunEventsService = builder.testRunEventsService;
        this.useTestStorageService = builder.useTestStorageService;
        this.screenCaptureProcessors = Collections.unmodifiableList(builder.screenCaptureProcessors);
        this.targetProcess = builder.targetProcess;
        this.shellExecBinderKey = builder.shellExecBinderKey;
        this.newRunListenerMode = builder.newRunListenerMode;
        this.testsRegEx = builder.testsRegEx;
        this.testPlatformMigration = builder.testPlatformMigration;
    }

    public static class Builder {
        public String shellExecBinderKey;
        private boolean debug = false;
        private boolean suiteAssignment = false;
        private boolean codeCoverage = false;
        private String codeCoveragePath = null;
        private int delayInMillis = -1;
        private boolean logOnly = false;
        private List<String> testPackages = new ArrayList();
        private List<String> notTestPackages = new ArrayList();
        private String testSize = null;
        private final List<String> annotations = new ArrayList();
        private final List<String> notAnnotations = new ArrayList();
        private long testTimeout = -1;
        private List<RunListener> listeners = new ArrayList();
        private List<Filter> filters = new ArrayList();
        private List<Class<? extends RunnerBuilder>> runnerBuilderClasses = new ArrayList();
        private List<TestArg> tests = new ArrayList();
        private List<TestArg> notTests = new ArrayList();
        private int numShards = 0;
        private int shardIndex = 0;
        private boolean disableAnalytics = false;
        private List<ApplicationLifecycleCallback> appListeners = new ArrayList();
        private ClassLoader classLoader = null;
        private Set<String> classpathToScan = new HashSet();
        private TestArg remoteMethod = null;
        private String orchestratorService = null;
        private boolean listTestsForOrchestrator = false;
        private String testDiscoveryService = null;
        private String testRunEventsService = null;
        private boolean useTestStorageService = false;
        private String targetProcess = null;
        private List<ScreenCaptureProcessor> screenCaptureProcessors = new ArrayList();
        private boolean newRunListenerMode = false;
        private String testsRegEx = null;
        private boolean testPlatformMigration = false;

        public Builder fromBundle(Instrumentation instr, Bundle bundle) throws IOException {
            this.debug = parseBoolean(bundle.getString(RunnerArgs.ARGUMENT_DEBUG));
            this.delayInMillis = parseUnsignedInt(bundle.get(RunnerArgs.ARGUMENT_DELAY_IN_MILLIS), RunnerArgs.ARGUMENT_DELAY_IN_MILLIS);
            this.tests.addAll(parseTestClasses(bundle.getString("class")));
            this.notTests.addAll(parseTestClasses(bundle.getString(RunnerArgs.ARGUMENT_NOT_TEST_CLASS)));
            this.testPackages.addAll(parseTestPackages(bundle.getString(RunnerArgs.ARGUMENT_TEST_PACKAGE)));
            this.notTestPackages.addAll(parseTestPackages(bundle.getString(RunnerArgs.ARGUMENT_NOT_TEST_PACKAGE)));
            TestFileArgs fromFile = parseFromFile(instr, bundle.getString(RunnerArgs.ARGUMENT_TEST_FILE));
            this.tests.addAll(fromFile.tests);
            this.testPackages.addAll(fromFile.packages);
            TestFileArgs fromFile2 = parseFromFile(instr, bundle.getString(RunnerArgs.ARGUMENT_NOT_TEST_FILE));
            this.notTests.addAll(fromFile2.tests);
            this.notTestPackages.addAll(fromFile2.packages);
            this.listeners.addAll(parseLoadAndInstantiateClasses(bundle.getString(RunnerArgs.ARGUMENT_LISTENER), RunListener.class, null));
            this.filters.addAll(parseLoadAndInstantiateClasses(bundle.getString("filter"), Filter.class, bundle));
            this.runnerBuilderClasses.addAll(parseAndLoadClasses(bundle.getString(RunnerArgs.ARGUMENT_RUNNER_BUILDER), RunnerBuilder.class));
            this.testSize = bundle.getString(RunnerArgs.ARGUMENT_TEST_SIZE);
            this.annotations.addAll(parseStrings(bundle.getString(RunnerArgs.ARGUMENT_ANNOTATION)));
            this.notAnnotations.addAll(parseStrings(bundle.getString(RunnerArgs.ARGUMENT_NOT_ANNOTATION)));
            this.testTimeout = parseUnsignedLong(bundle.getString(RunnerArgs.ARGUMENT_TIMEOUT), RunnerArgs.ARGUMENT_TIMEOUT);
            this.numShards = parseUnsignedInt(bundle.get(RunnerArgs.ARGUMENT_NUM_SHARDS), RunnerArgs.ARGUMENT_NUM_SHARDS);
            this.shardIndex = parseUnsignedInt(bundle.get(RunnerArgs.ARGUMENT_SHARD_INDEX), RunnerArgs.ARGUMENT_SHARD_INDEX);
            this.logOnly = parseBoolean(bundle.getString(RunnerArgs.ARGUMENT_LOG_ONLY));
            this.disableAnalytics = parseBoolean(bundle.getString(RunnerArgs.ARGUMENT_DISABLE_ANALYTICS));
            this.appListeners.addAll(parseLoadAndInstantiateClasses(bundle.getString(RunnerArgs.ARGUMENT_APP_LISTENER), ApplicationLifecycleCallback.class, null));
            this.codeCoverage = parseBoolean(bundle.getString(RunnerArgs.ARGUMENT_COVERAGE));
            this.codeCoveragePath = bundle.getString(RunnerArgs.ARGUMENT_COVERAGE_PATH);
            this.suiteAssignment = parseBoolean(bundle.getString(RunnerArgs.ARGUMENT_SUITE_ASSIGNMENT));
            this.classLoader = (ClassLoader) parseLoadAndInstantiateClass(bundle.getString(RunnerArgs.ARGUMENT_CLASS_LOADER), ClassLoader.class);
            this.classpathToScan = parseClasspath(bundle.getString(RunnerArgs.ARGUMENT_CLASSPATH_TO_SCAN));
            if (bundle.containsKey(RunnerArgs.ARGUMENT_REMOTE_INIT_METHOD)) {
                this.remoteMethod = parseTestClass(bundle.getString(RunnerArgs.ARGUMENT_REMOTE_INIT_METHOD));
            }
            this.orchestratorService = bundle.getString(RunnerArgs.ARGUMENT_ORCHESTRATOR_SERVICE);
            this.listTestsForOrchestrator = parseBoolean(bundle.getString(RunnerArgs.ARGUMENT_LIST_TESTS_FOR_ORCHESTRATOR));
            this.testDiscoveryService = bundle.getString(RunnerArgs.ARGUMENT_ORCHESTRATOR_DISCOVERY_SERVICE);
            this.testRunEventsService = bundle.getString(RunnerArgs.ARGUMENT_ORCHESTRATOR_RUN_EVENTS_SERVICE);
            this.useTestStorageService = parseBoolean(bundle.getString(RunnerArgs.ARGUMENT_USE_TEST_STORAGE_SERVICE));
            this.targetProcess = bundle.getString(RunnerArgs.ARGUMENT_TARGET_PROCESS);
            this.screenCaptureProcessors.addAll(parseLoadAndInstantiateClasses(bundle.getString(RunnerArgs.ARGUMENT_SCREENSHOT_PROCESSORS), ScreenCaptureProcessor.class, null));
            this.shellExecBinderKey = bundle.getString(RunnerArgs.ARGUMENT_SHELL_EXEC_BINDER_KEY);
            this.newRunListenerMode = parseBoolean(bundle.getString(RunnerArgs.ARGUMENT_RUN_LISTENER_NEW_ORDER));
            this.testsRegEx = bundle.getString(RunnerArgs.ARGUMENT_TESTS_REGEX);
            this.testPlatformMigration = parseBoolean(bundle.getString(RunnerArgs.ARGUMENT_TEST_PLATFORM_MIGRATION));
            return this;
        }

        public Builder fromManifest(Instrumentation instr) {
            try {
                Bundle bundle = instr.getContext().getPackageManager().getInstrumentationInfo(instr.getComponentName(), 128).metaData;
                return bundle == null ? this : fromBundle(instr, bundle);
            } catch (PackageManager.NameNotFoundException unused) {
                Log.wtf(RunnerArgs.LOG_TAG, String.format("Could not find component %s", instr.getComponentName()));
                return this;
            }
        }

        private static List<String> parseStrings(String value) {
            if (value == null) {
                return Collections.emptyList();
            }
            return Arrays.asList(value.split(RunnerArgs.CLASS_SEPARATOR));
        }

        private static boolean parseBoolean(String booleanValue) {
            return booleanValue != null && Boolean.parseBoolean(booleanValue);
        }

        private static int parseUnsignedInt(Object value, String name) throws NumberFormatException {
            if (value == null) {
                return -1;
            }
            int i = Integer.parseInt(value.toString());
            if (i >= 0) {
                return i;
            }
            throw new NumberFormatException(String.valueOf(name).concat(" can not be negative"));
        }

        private static long parseUnsignedLong(Object value, String name) throws NumberFormatException {
            if (value == null) {
                return -1L;
            }
            long j = Long.parseLong(value.toString());
            if (j >= 0) {
                return j;
            }
            throw new NumberFormatException(String.valueOf(name).concat(" can not be negative"));
        }

        private static List<String> parseTestPackages(String packagesArg) {
            ArrayList arrayList = new ArrayList();
            if (packagesArg != null) {
                for (String str : packagesArg.split(RunnerArgs.CLASS_SEPARATOR)) {
                    arrayList.add(str);
                }
            }
            return arrayList;
        }

        private List<TestArg> parseTestClasses(String classesArg) {
            ArrayList arrayList = new ArrayList();
            if (classesArg != null) {
                for (String str : classesArg.split(RunnerArgs.CLASS_SEPARATOR)) {
                    arrayList.add(parseTestClass(str));
                }
            }
            return arrayList;
        }

        private static Set<String> parseClasspath(String classpath) {
            if (classpath == null || classpath.isEmpty()) {
                return new HashSet();
            }
            return new HashSet(Arrays.asList(classpath.split(RunnerArgs.CLASSPATH_SEPARATOR, -1)));
        }

        private static TestArg parseTestClass(String testClassName) {
            if (TextUtils.isEmpty(testClassName)) {
                return null;
            }
            int iIndexOf = testClassName.indexOf(35);
            if (iIndexOf > 0) {
                return new TestArg(testClassName.substring(0, iIndexOf), testClassName.substring(iIndexOf + 1));
            }
            return new TestArg(testClassName);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v0, types: [androidx.test.internal.runner.RunnerArgs$1] */
        /* JADX WARN: Type inference failed for: r1v1, types: [java.io.BufferedReader] */
        /* JADX WARN: Type inference failed for: r1v2, types: [java.io.BufferedReader] */
        /* JADX WARN: Type inference failed for: r1v3 */
        private TestFileArgs parseFromFile(Instrumentation instrumentation, String str) throws IOException {
            ?? OpenFile = 0;
            OpenFile = 0;
            TestFileArgs testFileArgs = new TestFileArgs();
            try {
                if (str == null) {
                    return testFileArgs;
                }
                try {
                    OpenFile = openFile(instrumentation, str);
                    while (true) {
                        String line = OpenFile.readLine();
                        if (line == null) {
                            break;
                        }
                        if (isClassOrMethod(line)) {
                            testFileArgs.tests.add(parseTestClass(line));
                        } else {
                            testFileArgs.packages.addAll(parseTestPackages(line));
                        }
                    }
                    if (OpenFile != 0) {
                        try {
                            OpenFile.close();
                        } catch (IOException unused) {
                        }
                    }
                    return testFileArgs;
                } catch (FileNotFoundException e) {
                    String strValueOf = String.valueOf(str);
                    throw new IllegalArgumentException(strValueOf.length() != 0 ? "testfile not found: ".concat(strValueOf) : new String("testfile not found: "), e);
                } catch (IOException e2) {
                    String strValueOf2 = String.valueOf(str);
                    throw new IllegalArgumentException(strValueOf2.length() != 0 ? "Could not read test file ".concat(strValueOf2) : new String("Could not read test file "), e2);
                }
            } catch (Throwable th) {
                if (OpenFile != 0) {
                    try {
                        OpenFile.close();
                    } catch (IOException unused2) {
                    }
                }
                throw th;
            }
        }

        private BufferedReader openFile(Instrumentation instr, String filePath) throws IOException {
            Reader fileReader;
            if (Build.VERSION.SDK_INT >= 26 && instr.getContext().getPackageManager().isInstantApp()) {
                UiAutomation uiAutomation = instr.getUiAutomation();
                String strValueOf = String.valueOf(filePath);
                fileReader = new InputStreamReader(new ParcelFileDescriptor.AutoCloseInputStream(uiAutomation.executeShellCommand(strValueOf.length() != 0 ? "cat ".concat(strValueOf) : new String("cat "))));
            } else {
                fileReader = new FileReader(new File(filePath));
            }
            return new BufferedReader(fileReader);
        }

        static boolean isClassOrMethod(String line) {
            for (int i = 0; i < line.length(); i++) {
                char cCharAt = line.charAt(i);
                if (cCharAt == '#' || Character.isUpperCase(cCharAt)) {
                    return true;
                }
            }
            return false;
        }

        private <T> List<T> parseLoadAndInstantiateClasses(String classString, Class<T> type, Bundle bundle) throws NoSuchMethodException, ClassNotFoundException, SecurityException {
            ArrayList arrayList = new ArrayList();
            if (classString != null) {
                for (String str : classString.split(RunnerArgs.CLASS_SEPARATOR)) {
                    loadClassByNameInstantiateAndAdd(arrayList, str, type, bundle);
                }
            }
            return arrayList;
        }

        private <T> T parseLoadAndInstantiateClass(String classString, Class<T> type) throws NoSuchMethodException, ClassNotFoundException, SecurityException {
            List<T> loadAndInstantiateClasses = parseLoadAndInstantiateClasses(classString, type, null);
            if (loadAndInstantiateClasses.isEmpty()) {
                return null;
            }
            if (loadAndInstantiateClasses.size() > 1) {
                throw new IllegalArgumentException(String.format("Expected 1 class loader, %d given", Integer.valueOf(loadAndInstantiateClasses.size())));
            }
            return loadAndInstantiateClasses.get(0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        private <T> void loadClassByNameInstantiateAndAdd(List<T> objects, String className, Class<T> type, Bundle bundle) throws NoSuchMethodException, ClassNotFoundException, SecurityException {
            Object[] objArr;
            Constructor<?> constructor;
            if (className == null || className.length() == 0) {
                return;
            }
            try {
                try {
                    Class<?> cls = Class.forName(className);
                    try {
                        constructor = cls.getConstructor(new Class[0]);
                        objArr = new Object[0];
                    } catch (NoSuchMethodException e) {
                        if (bundle != null) {
                            try {
                                objArr = new Object[]{bundle};
                                constructor = cls.getConstructor(Bundle.class);
                            } catch (NoSuchMethodException e2) {
                                e2.initCause(e);
                                throw e2;
                            }
                        } else {
                            throw e;
                        }
                    }
                    constructor.setAccessible(true);
                    objects.add(constructor.newInstance(objArr));
                } catch (ClassCastException unused) {
                    String name = type.getName();
                    throw new IllegalArgumentException(new StringBuilder(String.valueOf(className).length() + 17 + String.valueOf(name).length()).append(className).append(" does not extend ").append(name).toString());
                } catch (ClassNotFoundException unused2) {
                    String strValueOf = String.valueOf(className);
                    throw new IllegalArgumentException(strValueOf.length() != 0 ? "Could not find extra class ".concat(strValueOf) : new String("Could not find extra class "));
                } catch (IllegalAccessException e3) {
                    String strValueOf2 = String.valueOf(className);
                    throw new IllegalArgumentException(strValueOf2.length() != 0 ? "Failed to create listener: ".concat(strValueOf2) : new String("Failed to create listener: "), e3);
                } catch (InstantiationException e4) {
                    String strValueOf3 = String.valueOf(className);
                    throw new IllegalArgumentException(strValueOf3.length() != 0 ? "Failed to create: ".concat(strValueOf3) : new String("Failed to create: "), e4);
                } catch (InvocationTargetException e5) {
                    String strValueOf4 = String.valueOf(className);
                    throw new IllegalArgumentException(strValueOf4.length() != 0 ? "Failed to create: ".concat(strValueOf4) : new String("Failed to create: "), e5);
                }
            } catch (NoSuchMethodException unused3) {
                String strValueOf5 = String.valueOf(className);
                throw new IllegalArgumentException(strValueOf5.length() != 0 ? "Must have no argument constructor for class ".concat(strValueOf5) : new String("Must have no argument constructor for class "));
            }
        }

        private <T> List<Class<? extends T>> parseAndLoadClasses(String classString, Class<T> type) throws ClassNotFoundException {
            ArrayList arrayList = new ArrayList();
            if (classString != null) {
                for (String str : classString.split(RunnerArgs.CLASS_SEPARATOR)) {
                    loadClassByNameAndAdd(arrayList, str, type);
                }
            }
            return arrayList;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private <T> void loadClassByNameAndAdd(List<Class<? extends T>> classes, String className, Class<T> type) throws ClassNotFoundException {
            if (className == null || className.length() == 0) {
                return;
            }
            try {
                Class<?> cls = Class.forName(className);
                if (!type.isAssignableFrom(cls)) {
                    String name = type.getName();
                    throw new IllegalArgumentException(new StringBuilder(String.valueOf(className).length() + 17 + String.valueOf(name).length()).append(className).append(" does not extend ").append(name).toString());
                }
                classes.add(cls);
            } catch (ClassCastException unused) {
                String name2 = type.getName();
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(className).length() + 17 + String.valueOf(name2).length()).append(className).append(" does not extend ").append(name2).toString());
            } catch (ClassNotFoundException unused2) {
                String strValueOf = String.valueOf(className);
                throw new IllegalArgumentException(strValueOf.length() != 0 ? "Could not find extra class ".concat(strValueOf) : new String("Could not find extra class "));
            }
        }

        public RunnerArgs build() {
            return new RunnerArgs(this);
        }
    }
}
