package coil3.util;

import android.os.SystemClock;
import coil3.util.Logger;
import java.io.File;
import kotlin.Metadata;
import org.jaudiotagger.tag.id3.AbstractID3v1Tag;

/* compiled from: hardwareBitmaps.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0007J\b\u0010\u0013\u001a\u00020\u0010H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcoil3/util/FileDescriptorCounter;", "", "<init>", "()V", AbstractID3v1Tag.TAG, "", "FILE_DESCRIPTOR_LIMIT", "", "FILE_DESCRIPTOR_CHECK_INTERVAL_DECODES", "FILE_DESCRIPTOR_CHECK_INTERVAL_MILLIS", "fileDescriptorList", "Ljava/io/File;", "decodesSinceLastFileDescriptorCheck", "lastFileDescriptorCheckTimestamp", "", "hasAvailableFileDescriptors", "", "logger", "Lcoil3/util/Logger;", "checkFileDescriptors", "coil-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
final class FileDescriptorCounter {
    private static final int FILE_DESCRIPTOR_CHECK_INTERVAL_DECODES = 30;
    private static final int FILE_DESCRIPTOR_CHECK_INTERVAL_MILLIS = 30000;
    private static final int FILE_DESCRIPTOR_LIMIT = 800;
    private static final String TAG = "FileDescriptorCounter";
    public static final FileDescriptorCounter INSTANCE = new FileDescriptorCounter();
    private static final File fileDescriptorList = new File("/proc/self/fd");
    private static int decodesSinceLastFileDescriptorCheck = 30;
    private static long lastFileDescriptorCheckTimestamp = SystemClock.uptimeMillis();
    private static boolean hasAvailableFileDescriptors = true;

    private FileDescriptorCounter() {
    }

    public final synchronized boolean hasAvailableFileDescriptors(Logger logger) {
        if (checkFileDescriptors()) {
            decodesSinceLastFileDescriptorCheck = 0;
            lastFileDescriptorCheckTimestamp = SystemClock.uptimeMillis();
            String[] list = fileDescriptorList.list();
            if (list == null) {
                list = new String[0];
            }
            int length = list.length;
            boolean z = length < FILE_DESCRIPTOR_LIMIT;
            hasAvailableFileDescriptors = z;
            if (!z && logger != null) {
                Logger.Level level = Logger.Level.Warn;
                if (logger.getMinLevel().compareTo(level) <= 0) {
                    logger.log(TAG, level, "Unable to allocate more hardware bitmaps. Number of used file descriptors: " + length, null);
                }
            }
        }
        return hasAvailableFileDescriptors;
    }

    private final boolean checkFileDescriptors() {
        int i = decodesSinceLastFileDescriptorCheck;
        decodesSinceLastFileDescriptorCheck = i + 1;
        return i >= 30 || SystemClock.uptimeMillis() > lastFileDescriptorCheckTimestamp + ((long) 30000);
    }
}
