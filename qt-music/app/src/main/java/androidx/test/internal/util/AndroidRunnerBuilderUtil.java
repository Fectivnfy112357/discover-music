package androidx.test.internal.util;

import androidx.test.internal.runner.listener.InstrumentationResultPrinter;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.runner.BaseTestRunner;

/* loaded from: classes.dex */
public class AndroidRunnerBuilderUtil {
    public static boolean isJUnit3Test(Class<?> testClass) {
        return TestCase.class.isAssignableFrom(testClass);
    }

    public static boolean isJUnit3TestSuite(Class<?> testClass) {
        return TestSuite.class.isAssignableFrom(testClass);
    }

    public static boolean hasSuiteMethod(Class<?> testClass) throws NoSuchMethodException, SecurityException {
        try {
            testClass.getMethod(BaseTestRunner.SUITE_METHODNAME, new Class[0]);
            return true;
        } catch (NoSuchMethodException unused) {
            return false;
        }
    }

    public static boolean hasJUnit3TestMethod(Class<?> loadedClass) throws SecurityException {
        for (Method method : loadedClass.getMethods()) {
            if (isPublicTestMethod(method)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isPublicTestMethod(Method m) {
        return isTestMethod(m) && Modifier.isPublic(m.getModifiers());
    }

    private static boolean isTestMethod(Method m) {
        return m.getParameterTypes().length == 0 && m.getName().startsWith(InstrumentationResultPrinter.REPORT_KEY_NAME_TEST) && m.getReturnType().equals(Void.TYPE);
    }
}
