package androidx.test.services.events.internal;

import android.util.Log;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import org.junit.runner.notification.Failure;

/* loaded from: classes.dex */
public final class StackTrimmer {
    static final int MAX_TRACE_SIZE = 65536;
    private static final String TAG = "StackTrimmer";

    private StackTrimmer() {
    }

    public static String getTrimmedStackTrace(Failure failure) {
        String trimmedStackTrace = Throwables.getTrimmedStackTrace(failure.getException());
        if (trimmedStackTrace.length() <= 65536) {
            return trimmedStackTrace;
        }
        Log.w(TAG, String.format("Stack trace too long, trimmed to first %s characters.", 65536));
        return String.valueOf(trimmedStackTrace.substring(0, 65536)).concat(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
    }
}
