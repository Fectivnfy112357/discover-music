package androidx.test.internal.platform.util;

import androidx.test.internal.util.Checks;
import androidx.test.platform.app.InstrumentationRegistry;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class InstrumentationParameterUtil {
    public static long getTimeoutMillis(String key, long defaultValue) throws NumberFormatException {
        Checks.checkArgument(defaultValue != 0, "default timeout value cannot be zero");
        long j = Long.parseLong(InstrumentationRegistry.getArguments().getString(key, SessionDescription.SUPPORTED_SDP_VERSION));
        if (j != 0) {
            defaultValue = j;
        }
        return defaultValue < 0 ? TimeUnit.DAYS.toMillis(1L) : defaultValue;
    }
}
