package com.localmediametadata;

import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import androidx.documentfile.provider.DocumentFile;
import com.facebook.react.bridge.ReactApplicationContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

/* loaded from: classes3.dex */
public class MediaFile {
    final ReactApplicationContext context;
    private DocumentFile dFile;
    private File file;
    private boolean isWrite;
    private ParcelFileDescriptor parcelFileDescriptor = null;
    private File tempFile;

    MediaFile(ReactApplicationContext reactApplicationContext, String str) {
        DocumentFile documentFileFromSingleUri;
        this.file = null;
        this.dFile = null;
        this.context = reactApplicationContext;
        if (Utils.isContentUri(str)) {
            try {
                Uri uri = Uri.parse(str);
                if (Utils.isTreeUri(uri)) {
                    documentFileFromSingleUri = DocumentFile.fromTreeUri(reactApplicationContext, uri);
                } else {
                    documentFileFromSingleUri = DocumentFile.fromSingleUri(reactApplicationContext, uri);
                }
                if (documentFileFromSingleUri != null) {
                    this.dFile = documentFileFromSingleUri;
                    return;
                }
            } catch (Exception unused) {
            }
        }
        this.file = Utils.parsePathToFile(str);
    }

    public boolean isDocFile() {
        return this.file == null;
    }

    private String createPath(String str) {
        return this.context.getCacheDir().getAbsolutePath() + "/local-media-metadata-temp-file/" + UUID.randomUUID() + "." + Utils.getFileExtension(str);
    }

    private File createTempFile() throws IOException {
        this.tempFile = new File(createPath(isDocFile() ? this.dFile.getName() : this.file.getName()));
        Log.d("MediaFile", "creating temp file: " + this.tempFile.getAbsolutePath());
        InputStream inputStreamCreateInputStream = isDocFile() ? Utils.createInputStream(this.context, this.dFile) : Utils.createInputStream(this.file);
        try {
            OutputStream outputStreamCreateOutputStream = Utils.createOutputStream(this.tempFile);
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int i = inputStreamCreateInputStream.read(bArr);
                    if (i <= 0) {
                        break;
                    }
                    outputStreamCreateOutputStream.write(bArr, 0, i);
                }
                if (outputStreamCreateOutputStream != null) {
                    outputStreamCreateOutputStream.close();
                }
                if (inputStreamCreateInputStream != null) {
                    inputStreamCreateInputStream.close();
                }
                return this.tempFile;
            } finally {
            }
        } catch (Throwable th) {
            if (inputStreamCreateInputStream != null) {
                try {
                    inputStreamCreateInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x004b, code lost:
    
        if (r2.canWrite() != false) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.io.File createFileFromDocumentFile(boolean r6) throws java.io.IOException {
        /*
            r5 = this;
            java.lang.String r0 = "/proc/self/fd/"
            androidx.documentfile.provider.DocumentFile r1 = r5.dFile
            boolean r1 = r1.exists()
            if (r1 != 0) goto Lc
            r6 = 0
            return r6
        Lc:
            androidx.documentfile.provider.DocumentFile r1 = r5.dFile
            java.lang.String r1 = r1.getName()
            com.facebook.react.bridge.ReactApplicationContext r2 = r5.context     // Catch: java.lang.Exception -> L4e
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch: java.lang.Exception -> L4e
            androidx.documentfile.provider.DocumentFile r3 = r5.dFile     // Catch: java.lang.Exception -> L4e
            android.net.Uri r3 = r3.getUri()     // Catch: java.lang.Exception -> L4e
            if (r6 == 0) goto L23
            java.lang.String r4 = "rw"
            goto L25
        L23:
            java.lang.String r4 = "r"
        L25:
            android.os.ParcelFileDescriptor r2 = r2.openFileDescriptor(r3, r4)     // Catch: java.lang.Exception -> L4e
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L4e
            r3.<init>(r0)     // Catch: java.lang.Exception -> L4e
            int r0 = r2.getFd()     // Catch: java.lang.Exception -> L4e
            java.lang.StringBuilder r0 = r3.append(r0)     // Catch: java.lang.Exception -> L4e
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> L4e
            com.localmediametadata.DescriptorFile r2 = new com.localmediametadata.DescriptorFile     // Catch: java.lang.Exception -> L4e
            r2.<init>(r0, r1)     // Catch: java.lang.Exception -> L4e
            boolean r0 = r2.canRead()     // Catch: java.lang.Exception -> L4e
            if (r0 == 0) goto L51
            if (r6 == 0) goto L4d
            boolean r6 = r2.canWrite()     // Catch: java.lang.Exception -> L4e
            if (r6 == 0) goto L51
        L4d:
            return r2
        L4e:
            r5.closeFile()
        L51:
            java.io.File r6 = r5.createTempFile()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.localmediametadata.MediaFile.createFileFromDocumentFile(boolean):java.io.File");
    }

    private File createFile(boolean z) throws IOException {
        if (!z || this.file.canWrite()) {
            return this.file;
        }
        return createTempFile();
    }

    public File getFile(boolean z) throws IOException {
        this.isWrite = z;
        if (isDocFile()) {
            return createFileFromDocumentFile(z);
        }
        return createFile(z);
    }

    public File getTempFile() throws IOException {
        return createTempFile();
    }

    public void closeFile() throws IOException {
        OutputStream outputStreamCreateOutputStream;
        ParcelFileDescriptor parcelFileDescriptor = this.parcelFileDescriptor;
        if (parcelFileDescriptor != null) {
            try {
                parcelFileDescriptor.close();
            } catch (Exception unused) {
            }
            this.parcelFileDescriptor = null;
        }
        if (this.tempFile != null) {
            Log.d("MediaFile", "closeFile");
            if (this.isWrite) {
                InputStream inputStreamCreateInputStream = Utils.createInputStream(this.tempFile);
                try {
                    if (isDocFile()) {
                        outputStreamCreateOutputStream = Utils.createOutputStream(this.context, this.dFile.getUri());
                    } else {
                        outputStreamCreateOutputStream = Utils.createOutputStream(this.file);
                    }
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int i = inputStreamCreateInputStream.read(bArr);
                            if (i <= 0) {
                                break;
                            } else {
                                outputStreamCreateOutputStream.write(bArr, 0, i);
                            }
                        }
                        if (outputStreamCreateOutputStream != null) {
                            outputStreamCreateOutputStream.close();
                        }
                        if (inputStreamCreateInputStream != null) {
                            inputStreamCreateInputStream.close();
                        }
                    } finally {
                    }
                } catch (Throwable th) {
                    if (inputStreamCreateInputStream != null) {
                        try {
                            inputStreamCreateInputStream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            }
            try {
                this.tempFile.delete();
            } catch (Exception unused2) {
            }
            this.tempFile = null;
        }
    }

    public boolean exists() {
        if (isDocFile()) {
            return this.dFile.exists();
        }
        return this.file.exists();
    }

    public String getName() {
        if (isDocFile()) {
            return this.dFile.getName();
        }
        return this.file.getName();
    }

    public long size() {
        if (isDocFile()) {
            return this.dFile.length();
        }
        return this.file.length();
    }
}
