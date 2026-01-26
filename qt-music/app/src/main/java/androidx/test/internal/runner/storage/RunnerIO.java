package androidx.test.internal.runner.storage;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes.dex */
public interface RunnerIO {
    InputStream openInputStream(String pathname) throws FileNotFoundException;

    OutputStream openOutputStream(String pathname) throws FileNotFoundException;
}
