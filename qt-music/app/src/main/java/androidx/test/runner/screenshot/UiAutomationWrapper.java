package androidx.test.runner.screenshot;

import android.graphics.Bitmap;
import androidx.test.InstrumentationRegistry;

/* loaded from: classes.dex */
public class UiAutomationWrapper {
    UiAutomationWrapper() {
    }

    public Bitmap takeScreenshot() {
        return InstrumentationRegistry.getInstrumentation().getUiAutomation().takeScreenshot();
    }
}
