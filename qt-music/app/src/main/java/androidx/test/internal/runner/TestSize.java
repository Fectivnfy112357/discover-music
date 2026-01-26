package androidx.test.internal.runner;

import androidx.test.filters.LargeTest;
import androidx.test.filters.MediumTest;
import androidx.test.filters.SmallTest;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.junit.runner.Description;

/* loaded from: classes.dex */
public final class TestSize {
    private static final Set<TestSize> ALL_SIZES;
    public static final TestSize LARGE;
    public static final TestSize MEDIUM;
    public static final TestSize NONE;
    public static final TestSize SMALL;
    private final Class<? extends Annotation> platformAnnotationClass;
    private final Class<? extends Annotation> runnerFilterAnnotationClass;
    private final String sizeQualifierName;
    private final float testSizeRunTimeThreshold;

    static {
        TestSize testSize = new TestSize("small", SmallTest.class, "android.test.suitebuilder.annotation.SmallTest", 200.0f);
        SMALL = testSize;
        TestSize testSize2 = new TestSize("medium", MediumTest.class, "android.test.suitebuilder.annotation.MediumTest", 1000.0f);
        MEDIUM = testSize2;
        TestSize testSize3 = new TestSize("large", LargeTest.class, "android.test.suitebuilder.annotation.LargeTest", Float.MAX_VALUE);
        LARGE = testSize3;
        NONE = new TestSize("", null, null, 0.0f);
        ALL_SIZES = Collections.unmodifiableSet(new HashSet(Arrays.asList(testSize, testSize2, testSize3)));
    }

    public TestSize(String sizeQualifierName, Class<? extends Annotation> runnerFilterAnnotationClass, String legacyPlatformAnnotationClassName, float testSizeRuntimeThreshold) {
        this.sizeQualifierName = sizeQualifierName;
        this.platformAnnotationClass = loadPlatformAnnotationClass(legacyPlatformAnnotationClassName);
        this.runnerFilterAnnotationClass = runnerFilterAnnotationClass;
        this.testSizeRunTimeThreshold = testSizeRuntimeThreshold;
    }

    private static Class<? extends Annotation> loadPlatformAnnotationClass(String legacyPlatformAnnotationClassName) {
        if (legacyPlatformAnnotationClassName == null) {
            return null;
        }
        try {
            return Class.forName(legacyPlatformAnnotationClassName);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public String getSizeQualifierName() {
        return this.sizeQualifierName;
    }

    public boolean testMethodIsAnnotatedWithTestSize(Description description) {
        return (description.getAnnotation(this.runnerFilterAnnotationClass) == null && description.getAnnotation(this.platformAnnotationClass) == null) ? false : true;
    }

    public boolean testClassIsAnnotatedWithTestSize(Description description) {
        Class<?> testClass = description.getTestClass();
        if (testClass == null) {
            return false;
        }
        return hasAnnotation(testClass, this.runnerFilterAnnotationClass) || hasAnnotation(testClass, this.platformAnnotationClass);
    }

    private static boolean hasAnnotation(Class<?> testClass, Class<? extends Annotation> annotationClass) {
        return annotationClass != null && testClass.isAnnotationPresent(annotationClass);
    }

    public float getRunTimeThreshold() {
        return this.testSizeRunTimeThreshold;
    }

    public static TestSize getTestSizeForRunTime(float testRuntime) {
        TestSize testSize = SMALL;
        if (runTimeSmallerThanThreshold(testRuntime, testSize.getRunTimeThreshold())) {
            return testSize;
        }
        TestSize testSize2 = MEDIUM;
        return runTimeSmallerThanThreshold(testRuntime, testSize2.getRunTimeThreshold()) ? testSize2 : LARGE;
    }

    public static boolean isAnyTestSize(Class<? extends Annotation> annotationClass) {
        for (TestSize testSize : ALL_SIZES) {
            if (testSize.getRunnerAnnotation() == annotationClass || testSize.getFrameworkAnnotation() == annotationClass) {
                return true;
            }
        }
        return false;
    }

    public static TestSize fromString(final String testSize) {
        TestSize testSize2 = NONE;
        for (TestSize testSize3 : ALL_SIZES) {
            if (testSize3.getSizeQualifierName().equals(testSize)) {
                testSize2 = testSize3;
            }
        }
        return testSize2;
    }

    public static TestSize fromDescription(Description description) {
        TestSize testSize = NONE;
        Iterator<TestSize> it = ALL_SIZES.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            TestSize next = it.next();
            if (next.testMethodIsAnnotatedWithTestSize(description)) {
                testSize = next;
                break;
            }
        }
        if (!NONE.equals(testSize)) {
            return testSize;
        }
        for (TestSize testSize2 : ALL_SIZES) {
            if (testSize2.testClassIsAnnotatedWithTestSize(description)) {
                return testSize2;
            }
        }
        return testSize;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return this.sizeQualifierName.equals(((TestSize) o).sizeQualifierName);
    }

    public int hashCode() {
        return this.sizeQualifierName.hashCode();
    }

    private static boolean runTimeSmallerThanThreshold(float testRuntime, float runtimeThreshold) {
        return Float.compare(testRuntime, runtimeThreshold) < 0;
    }

    private Class<? extends Annotation> getFrameworkAnnotation() {
        return this.platformAnnotationClass;
    }

    private Class<? extends Annotation> getRunnerAnnotation() {
        return this.runnerFilterAnnotationClass;
    }
}
