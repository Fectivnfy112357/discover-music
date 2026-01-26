package androidx.test.services.storage.internal;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import androidx.test.internal.util.Checks;
import androidx.test.services.storage.TestStorageException;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes.dex */
public final class TestStorageUtil {
    public static InputStream getInputStream(Uri uri, ContentResolver contentResolver) throws FileNotFoundException {
        Checks.checkNotNull(uri);
        ContentProviderClient contentProviderClientMakeContentProviderClient = null;
        try {
            try {
                contentProviderClientMakeContentProviderClient = makeContentProviderClient(contentResolver, uri);
                return new BufferedInputStream(new ParcelFileDescriptor.AutoCloseInputStream(contentProviderClientMakeContentProviderClient.openFile(uri, "r")));
            } catch (RemoteException e) {
                String strValueOf = String.valueOf(uri);
                throw new TestStorageException(new StringBuilder(String.valueOf(strValueOf).length() + 35).append("Unable to access content provider: ").append(strValueOf).toString(), e);
            }
        } finally {
            if (contentProviderClientMakeContentProviderClient != null) {
                contentProviderClientMakeContentProviderClient.release();
            }
        }
    }

    public static OutputStream getOutputStream(Uri uri, ContentResolver contentResolver) throws FileNotFoundException {
        Checks.checkNotNull(uri);
        ContentProviderClient contentProviderClientMakeContentProviderClient = null;
        try {
            try {
                contentProviderClientMakeContentProviderClient = makeContentProviderClient(contentResolver, uri);
                return new ParcelFileDescriptor.AutoCloseOutputStream(contentProviderClientMakeContentProviderClient.openFile(uri, "w"));
            } catch (RemoteException e) {
                String strValueOf = String.valueOf(uri);
                throw new TestStorageException(new StringBuilder(String.valueOf(strValueOf).length() + 35).append("Unable to access content provider: ").append(strValueOf).toString(), e);
            }
        } finally {
            if (contentProviderClientMakeContentProviderClient != null) {
                contentProviderClientMakeContentProviderClient.release();
            }
        }
    }

    private static ContentProviderClient makeContentProviderClient(ContentResolver resolver, Uri uri) {
        Checks.checkNotNull(resolver);
        ContentProviderClient contentProviderClientAcquireContentProviderClient = resolver.acquireContentProviderClient(uri);
        if (contentProviderClientAcquireContentProviderClient != null) {
            return contentProviderClientAcquireContentProviderClient;
        }
        throw new TestStorageException(String.format("No content provider registered for: %s. Are all test services apks installed?", uri));
    }

    private TestStorageUtil() {
    }
}
