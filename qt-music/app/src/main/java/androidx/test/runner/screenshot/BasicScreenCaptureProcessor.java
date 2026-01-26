package androidx.test.runner.screenshot;

import android.os.Build;
import android.os.Environment;
import android.util.Log;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/* loaded from: classes.dex */
public class BasicScreenCaptureProcessor implements ScreenCaptureProcessor {
    protected String mDefaultFilenamePrefix;
    protected File mDefaultScreenshotPath;
    protected String mFileNameDelimiter;
    protected String mTag;
    private static int sAndroidRuntimeVersion = Build.VERSION.SDK_INT;
    private static String sAndroidDeviceName = Build.DEVICE;

    public BasicScreenCaptureProcessor() {
        this(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "screenshots"));
    }

    BasicScreenCaptureProcessor(File defaultScreenshotPath) {
        this.mTag = "BasicScreenCaptureProcessor";
        this.mFileNameDelimiter = "-";
        this.mDefaultFilenamePrefix = "screenshot";
        this.mDefaultScreenshotPath = defaultScreenshotPath;
    }

    @Override // androidx.test.runner.screenshot.ScreenCaptureProcessor
    public String process(ScreenCapture capture) throws Throwable {
        BufferedOutputStream bufferedOutputStream;
        String strValueOf = String.valueOf(capture.getName() == null ? getDefaultFilename() : getFilename(capture.getName()));
        String lowerCase = capture.getFormat().toString().toLowerCase();
        String string = new StringBuilder(String.valueOf(strValueOf).length() + 1 + String.valueOf(lowerCase).length()).append(strValueOf).append(".").append(lowerCase).toString();
        File file = this.mDefaultScreenshotPath;
        file.mkdirs();
        if (!file.isDirectory() && !file.canWrite()) {
            throw new IOException(String.format("The directory %s does not exist and could not be created or is not writable.", file));
        }
        File file2 = new File(file, string);
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
        } catch (Throwable th) {
            th = th;
        }
        try {
            capture.getBitmap().compress(capture.getFormat(), 100, bufferedOutputStream);
            bufferedOutputStream.flush();
            try {
                bufferedOutputStream.close();
            } catch (IOException e) {
                Log.e(this.mTag, "Could not close output steam.", e);
            }
            return string;
        } catch (Throwable th2) {
            th = th2;
            bufferedOutputStream2 = bufferedOutputStream;
            if (bufferedOutputStream2 != null) {
                try {
                    bufferedOutputStream2.close();
                } catch (IOException e2) {
                    Log.e(this.mTag, "Could not close output steam.", e2);
                }
            }
            throw th;
        }
    }

    protected String getDefaultFilename() {
        String str = this.mDefaultFilenamePrefix;
        String str2 = this.mFileNameDelimiter;
        String str3 = sAndroidDeviceName;
        return getFilename(new StringBuilder(String.valueOf(str).length() + 11 + String.valueOf(str2).length() + String.valueOf(str3).length() + String.valueOf(str2).length()).append(str).append(str2).append(str3).append(str2).append(sAndroidRuntimeVersion).toString());
    }

    protected String getFilename(String prefix) {
        String str = this.mFileNameDelimiter;
        String strValueOf = String.valueOf(UUID.randomUUID());
        return new StringBuilder(String.valueOf(prefix).length() + String.valueOf(str).length() + String.valueOf(strValueOf).length()).append(prefix).append(str).append(strValueOf).toString();
    }

    static void setAndroidDeviceName(String deviceName) {
        sAndroidDeviceName = deviceName;
    }

    static void setAndroidRuntimeVersion(int sdkInt) {
        sAndroidRuntimeVersion = sdkInt;
    }
}
