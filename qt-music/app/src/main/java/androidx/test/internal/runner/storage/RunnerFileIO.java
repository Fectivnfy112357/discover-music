package androidx.test.internal.runner.storage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes.dex */
public final class RunnerFileIO implements RunnerIO {
    @Override // androidx.test.internal.runner.storage.RunnerIO
    public InputStream openInputStream(String pathname) throws FileNotFoundException {
        return new FileInputStream(pathname);
    }

    @Override // androidx.test.internal.runner.storage.RunnerIO
    public OutputStream openOutputStream(String pathname) throws FileNotFoundException {
        return new FileOutputStream(pathname);
    }
}
