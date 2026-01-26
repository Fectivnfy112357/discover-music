package com.localmediametadata;

import android.net.Uri;
import android.util.Base64;
import androidx.documentfile.provider.DocumentFile;
import com.facebook.react.bridge.ReactApplicationContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import org.mozilla.universalchardet.UniversalDetector;

/* loaded from: classes3.dex */
public class Utils {
    public static final String LOG = "Metadata";

    public static boolean isContentUri(String str) {
        return str.startsWith("content://");
    }

    public static boolean isTreeUri(Uri uri) {
        return "tree".equals(uri.getPathSegments().get(0));
    }

    public static File parsePathToFile(String str) {
        if (str.contains("://")) {
            try {
                return new File(Uri.parse(str).getPath());
            } catch (Throwable unused) {
                return new File(str);
            }
        }
        return new File(str);
    }

    public static InputStream createInputStream(File file) throws FileNotFoundException {
        return new FileInputStream(file);
    }

    public static InputStream createInputStream(ReactApplicationContext reactApplicationContext, DocumentFile documentFile) throws FileNotFoundException {
        return reactApplicationContext.getContentResolver().openInputStream(documentFile.getUri());
    }

    public static OutputStream createOutputStream(ReactApplicationContext reactApplicationContext, Uri uri, boolean z) throws IOException {
        DocumentFile parentFile = DocumentFile.fromSingleUri(reactApplicationContext, uri).getParentFile();
        if (parentFile != null && !parentFile.exists()) {
            ((DocumentFile) Objects.requireNonNull(parentFile.getParentFile())).createDirectory(parentFile.getName());
        }
        return reactApplicationContext.getContentResolver().openOutputStream(uri, z ? "wa" : "w");
    }

    public static OutputStream createOutputStream(File file, boolean z) throws FileNotFoundException {
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists()) {
            parentFile.mkdirs();
        }
        return new FileOutputStream(file, z);
    }

    public static OutputStream createOutputStream(File file) throws FileNotFoundException {
        return createOutputStream(file, false);
    }

    public static OutputStream createOutputStream(ReactApplicationContext reactApplicationContext, Uri uri) throws IOException {
        return createOutputStream(reactApplicationContext, uri, false);
    }

    public static String getName(String str) {
        int iLastIndexOf = str.lastIndexOf(".");
        return iLastIndexOf != -1 ? str.substring(0, iLastIndexOf) : str;
    }

    public static String getFileExtension(String str) {
        int iLastIndexOf = str.lastIndexOf(".");
        if (iLastIndexOf != -1 && iLastIndexOf < str.length() - 1) {
            return str.substring(iLastIndexOf + 1);
        }
        return "";
    }

    private static String encodeBase64(byte[] bArr) {
        return new String(Base64.encode(bArr, 2), StandardCharsets.UTF_8);
    }

    public static String decodeString(byte[] bArr) {
        UniversalDetector universalDetector = new UniversalDetector(null);
        universalDetector.handleData(bArr, 0, bArr.length);
        universalDetector.dataEnd();
        String detectedCharset = universalDetector.getDetectedCharset();
        universalDetector.reset();
        if (detectedCharset == null) {
            detectedCharset = "UTF-8";
        }
        try {
            return new String(bArr, detectedCharset);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
