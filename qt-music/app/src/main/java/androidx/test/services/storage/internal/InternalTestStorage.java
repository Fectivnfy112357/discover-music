package androidx.test.services.storage.internal;

import android.content.ContentResolver;
import androidx.test.internal.util.Checks;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.services.storage.file.HostedFile;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.annotation.Nonnull;

/* loaded from: classes.dex */
public final class InternalTestStorage {
    private final ContentResolver contentResolver = InstrumentationRegistry.getInstrumentation().getTargetContext().getContentResolver();

    public InputStream openInternalInputStream(@Nonnull String pathname) throws FileNotFoundException {
        Checks.checkNotNull(pathname);
        return TestStorageUtil.getInputStream(HostedFile.buildUri(HostedFile.FileHost.INTERNAL_USE_ONLY, pathname), this.contentResolver);
    }

    public OutputStream openInternalOutputStream(@Nonnull String pathname) throws FileNotFoundException {
        Checks.checkNotNull(pathname);
        return TestStorageUtil.getOutputStream(HostedFile.buildUri(HostedFile.FileHost.INTERNAL_USE_ONLY, pathname), this.contentResolver);
    }
}
