package androidx.test.runner.screenshot;

import java.io.IOException;

/* loaded from: classes.dex */
public interface ScreenCaptureProcessor {
    String process(ScreenCapture capture) throws IOException;
}
