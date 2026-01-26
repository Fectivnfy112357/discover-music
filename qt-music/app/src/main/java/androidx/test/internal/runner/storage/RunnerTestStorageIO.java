package androidx.test.internal.runner.storage;

import androidx.test.services.storage.internal.InternalTestStorage;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes.dex */
public final class RunnerTestStorageIO implements RunnerIO {
    private final InternalTestStorage testStorage = new InternalTestStorage();

    @Override // androidx.test.internal.runner.storage.RunnerIO
    public InputStream openInputStream(String pathname) throws FileNotFoundException {
        return this.testStorage.openInternalInputStream(pathname);
    }

    @Override // androidx.test.internal.runner.storage.RunnerIO
    public OutputStream openOutputStream(String pathname) throws FileNotFoundException {
        return this.testStorage.openInternalOutputStream(pathname);
    }
}
