package androidx.test.internal.events.client;

import org.junit.runner.Description;

/* loaded from: classes.dex */
final class JUnitValidator {
    private static final String INIT_ERROR_METHOD_NAME = "initializationError";

    private JUnitValidator() {
    }

    static boolean validateDescription(Description description) {
        return !INIT_ERROR_METHOD_NAME.equals(description.getMethodName());
    }
}
