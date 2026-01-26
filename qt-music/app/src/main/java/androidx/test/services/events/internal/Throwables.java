package androidx.test.services.events.internal;

import androidx.test.runner.internal.deps.desugar.ThrowableExtension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
final class Throwables {
    private static final String[] TEST_FRAMEWORK_METHOD_NAME_PREFIXES = {"org.junit.runner.", "org.junit.runners.", "org.junit.experimental.runners.", "org.junit.internal.", "junit."};
    private static final String[] TEST_FRAMEWORK_TEST_METHOD_NAME_PREFIXES = {"org.junit.internal.StackTracesTest"};
    private static final String[] REFLECTION_METHOD_NAME_PREFIXES = {"sun.reflect.", "java.lang.reflect.", "jdk.internal.reflect.", "org.junit.rules.RunRules.<init>(", "org.junit.rules.RunRules.applyAll(", "org.junit.runners.BlockJUnit4ClassRunner.withMethodRules(", "junit.framework.TestCase.runBare("};

    private Throwables() {
    }

    public static String getTrimmedStackTrace(Throwable exception) {
        List<String> trimmedStackTraceLines = getTrimmedStackTraceLines(exception);
        if (trimmedStackTraceLines.isEmpty()) {
            return getFullStackTrace(exception);
        }
        StringBuilder sb = new StringBuilder(exception.toString());
        appendStackTraceLines(trimmedStackTraceLines, sb);
        appendStackTraceLines(getCauseStackTraceLines(exception), sb);
        return sb.toString();
    }

    private static List<String> getTrimmedStackTraceLines(Throwable exception) {
        List listAsList = Arrays.asList(exception.getStackTrace());
        int size = listAsList.size();
        State stateProcessStackTraceElement = State.PROCESSING_OTHER_CODE;
        Iterator it = asReversedList(listAsList).iterator();
        while (it.hasNext()) {
            stateProcessStackTraceElement = stateProcessStackTraceElement.processStackTraceElement((StackTraceElement) it.next());
            if (stateProcessStackTraceElement == State.DONE) {
                ArrayList arrayList = new ArrayList(size + 2);
                arrayList.add("");
                Iterator it2 = listAsList.subList(0, size).iterator();
                while (it2.hasNext()) {
                    String strValueOf = String.valueOf((StackTraceElement) it2.next());
                    arrayList.add(new StringBuilder(String.valueOf(strValueOf).length() + 4).append("\tat ").append(strValueOf).toString());
                }
                if (exception.getCause() != null) {
                    arrayList.add(new StringBuilder(24).append("\t... ").append(listAsList.size() - arrayList.size()).append(" trimmed").toString());
                }
                return arrayList;
            }
            size--;
        }
        return Collections.emptyList();
    }

    private static List<String> getCauseStackTraceLines(Throwable exception) throws IOException {
        String line;
        if (exception.getCause() != null) {
            BufferedReader bufferedReader = new BufferedReader(new StringReader(getFullStackTrace(exception).substring(exception.toString().length())));
            ArrayList arrayList = new ArrayList();
            do {
                try {
                    line = bufferedReader.readLine();
                    if (line != null) {
                    }
                } catch (IOException unused) {
                }
            } while (!line.startsWith("Caused by: "));
            arrayList.add(line);
            while (true) {
                String line2 = bufferedReader.readLine();
                if (line2 == null) {
                    return arrayList;
                }
                arrayList.add(line2);
            }
        }
        return Collections.emptyList();
    }

    private static String getFullStackTrace(Throwable exception) {
        StringWriter stringWriter = new StringWriter();
        ThrowableExtension.printStackTrace(exception, new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    private static void appendStackTraceLines(List<String> stackTraceLines, StringBuilder destBuilder) {
        Iterator<String> it = stackTraceLines.iterator();
        while (it.hasNext()) {
            destBuilder.append(String.format("%s%n", it.next()));
        }
    }

    private static <T> List<T> asReversedList(final List<T> list) {
        return new AbstractList<T>() { // from class: androidx.test.services.events.internal.Throwables.1
            @Override // java.util.AbstractList, java.util.List
            public T get(int i) {
                return (T) list.get((r0.size() - i) - 1);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
            public int size() {
                return list.size();
            }
        };
    }

    private enum State {
        PROCESSING_OTHER_CODE { // from class: androidx.test.services.events.internal.Throwables.State.1
            @Override // androidx.test.services.events.internal.Throwables.State
            public State processLine(String methodName) {
                return Throwables.isTestFrameworkMethod(methodName) ? PROCESSING_TEST_FRAMEWORK_CODE : this;
            }
        },
        PROCESSING_TEST_FRAMEWORK_CODE { // from class: androidx.test.services.events.internal.Throwables.State.2
            @Override // androidx.test.services.events.internal.Throwables.State
            public State processLine(String methodName) {
                if (Throwables.isReflectionMethod(methodName)) {
                    return PROCESSING_REFLECTION_CODE;
                }
                return Throwables.isTestFrameworkMethod(methodName) ? this : PROCESSING_OTHER_CODE;
            }
        },
        PROCESSING_REFLECTION_CODE { // from class: androidx.test.services.events.internal.Throwables.State.3
            @Override // androidx.test.services.events.internal.Throwables.State
            public State processLine(String methodName) {
                if (Throwables.isReflectionMethod(methodName)) {
                    return this;
                }
                if (Throwables.isTestFrameworkMethod(methodName)) {
                    return PROCESSING_TEST_FRAMEWORK_CODE;
                }
                return DONE;
            }
        },
        DONE { // from class: androidx.test.services.events.internal.Throwables.State.4
            @Override // androidx.test.services.events.internal.Throwables.State
            public State processLine(String methodName) {
                return this;
            }
        };

        protected abstract State processLine(String methodName);

        public final State processStackTraceElement(StackTraceElement element) {
            String className = element.getClassName();
            String methodName = element.getMethodName();
            return processLine(new StringBuilder(String.valueOf(className).length() + 3 + String.valueOf(methodName).length()).append(className).append(".").append(methodName).append("()").toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isTestFrameworkMethod(String methodName) {
        return isMatchingMethod(methodName, TEST_FRAMEWORK_METHOD_NAME_PREFIXES) && !isMatchingMethod(methodName, TEST_FRAMEWORK_TEST_METHOD_NAME_PREFIXES);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isReflectionMethod(String methodName) {
        return isMatchingMethod(methodName, REFLECTION_METHOD_NAME_PREFIXES);
    }

    private static boolean isMatchingMethod(String methodName, String[] methodNamePrefixes) {
        for (String str : methodNamePrefixes) {
            if (methodName.startsWith(str)) {
                return true;
            }
        }
        return false;
    }
}
