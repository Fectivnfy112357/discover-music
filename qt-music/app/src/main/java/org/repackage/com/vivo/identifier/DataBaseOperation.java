package org.repackage.com.vivo.identifier;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

/* loaded from: classes3.dex */
public class DataBaseOperation {
    private static final String a = "VMS_SDK_DB";
    private static final String b = "content://com.vivo.vms.IdProvider/IdentifierId";
    private static final String c = "content://com.vivo.abe.exidentifier/guid";
    private static final String d = "value";
    private static final String e = "OAID";
    private static final String f = "AAID";
    private static final String g = "VAID";
    private static final String h = "OAIDBLACK";
    private static final String i = "OAIDSTATUS";
    private static final String j = "STATISTICS";
    private static final int k = 0;
    private static final int l = 1;
    private static final int m = 2;
    private static final int n = 3;
    private static final int o = 4;
    private static final int p = 5;
    private static final int q = 6;
    private static final int r = 7;
    private static final String s = "UDID";
    private Context t;

    DataBaseOperation(Context context) {
        this.t = context;
    }

    boolean a(int i2, String str, ContentValues[] contentValuesArr) {
        int iBulkInsert;
        Uri uri = i2 != 6 ? i2 != 7 ? null : Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/STATISTICS_" + str) : Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/OAIDBLACK_" + str);
        if (uri == null) {
            return false;
        }
        try {
            iBulkInsert = this.t.getContentResolver().bulkInsert(uri, contentValuesArr);
            Log.d(a, "insert:" + iBulkInsert);
        } catch (Exception unused) {
            Log.e(a, "return insert is error");
        }
        return iBulkInsert != 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0069 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0093 A[PHI: r1 r9
  0x0093: PHI (r1v5 java.lang.String) = (r1v3 java.lang.String), (r1v7 java.lang.String) binds: [B:36:0x009f, B:30:0x0091] A[DONT_GENERATE, DONT_INLINE]
  0x0093: PHI (r9v7 android.database.Cursor) = (r9v6 android.database.Cursor), (r9v9 android.database.Cursor) binds: [B:36:0x009f, B:30:0x0091] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x006a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r9v26 */
    /* JADX WARN: Type inference failed for: r9v27 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    java.lang.String a(int r9, java.lang.String r10) {
        /*
            r8 = this;
            java.lang.String r0 = "VMS_SDK_DB"
            r1 = 0
            if (r9 == 0) goto L60
            r2 = 1
            if (r9 == r2) goto L4c
            r2 = 2
            if (r9 == r2) goto L38
            r2 = 3
            if (r9 == r2) goto L31
            r2 = 4
            if (r9 == r2) goto L1d
            r10 = 5
            if (r9 == r10) goto L16
            r3 = r1
            goto L67
        L16:
            java.lang.String r9 = "content://com.vivo.abe.exidentifier/guid"
            android.net.Uri r9 = android.net.Uri.parse(r9)
            goto L66
        L1d:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r2 = "content://com.vivo.vms.IdProvider/IdentifierId/OAIDSTATUS_"
            r9.<init>(r2)
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.String r9 = r9.toString()
            android.net.Uri r9 = android.net.Uri.parse(r9)
            goto L66
        L31:
            java.lang.String r9 = "content://com.vivo.vms.IdProvider/IdentifierId/UDID"
            android.net.Uri r9 = android.net.Uri.parse(r9)
            goto L66
        L38:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r2 = "content://com.vivo.vms.IdProvider/IdentifierId/AAID_"
            r9.<init>(r2)
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.String r9 = r9.toString()
            android.net.Uri r9 = android.net.Uri.parse(r9)
            goto L66
        L4c:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r2 = "content://com.vivo.vms.IdProvider/IdentifierId/VAID_"
            r9.<init>(r2)
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.String r9 = r9.toString()
            android.net.Uri r9 = android.net.Uri.parse(r9)
            goto L66
        L60:
            java.lang.String r9 = "content://com.vivo.vms.IdProvider/IdentifierId/OAID"
            android.net.Uri r9 = android.net.Uri.parse(r9)
        L66:
            r3 = r9
        L67:
            if (r3 != 0) goto L6a
            return r1
        L6a:
            android.content.Context r9 = r8.t     // Catch: java.lang.Throwable -> L97 java.lang.Exception -> L99
            android.content.ContentResolver r2 = r9.getContentResolver()     // Catch: java.lang.Throwable -> L97 java.lang.Exception -> L99
            r6 = 0
            r7 = 0
            r4 = 0
            r5 = 0
            android.database.Cursor r9 = r2.query(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L97 java.lang.Exception -> L99
            if (r9 == 0) goto L8c
            boolean r10 = r9.moveToNext()     // Catch: java.lang.Exception -> L9a java.lang.Throwable -> La3
            if (r10 == 0) goto L91
            java.lang.String r10 = "value"
            int r10 = r9.getColumnIndex(r10)     // Catch: java.lang.Exception -> L9a java.lang.Throwable -> La3
            java.lang.String r10 = r9.getString(r10)     // Catch: java.lang.Exception -> L9a java.lang.Throwable -> La3
            r1 = r10
            goto L91
        L8c:
            java.lang.String r10 = "return cursor is null,return"
            android.util.Log.d(r0, r10)     // Catch: java.lang.Exception -> L9a java.lang.Throwable -> La3
        L91:
            if (r9 == 0) goto La2
        L93:
            r9.close()
            goto La2
        L97:
            r10 = move-exception
            goto La5
        L99:
            r9 = r1
        L9a:
            java.lang.String r10 = "return cursor is error"
            android.util.Log.e(r0, r10)     // Catch: java.lang.Throwable -> La3
            if (r9 == 0) goto La2
            goto L93
        La2:
            return r1
        La3:
            r10 = move-exception
            r1 = r9
        La5:
            if (r1 == 0) goto Laa
            r1.close()
        Laa:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.repackage.com.vivo.identifier.DataBaseOperation.a(int, java.lang.String):java.lang.String");
    }

    boolean a(int i2, String str, String str2, String str3) {
        int iDelete;
        Uri uri = i2 != 6 ? null : Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/OAIDBLACK_" + str);
        if (uri == null) {
            return false;
        }
        try {
            iDelete = this.t.getContentResolver().delete(uri, "packageName=? and uid=?", new String[]{str2, str3});
            Log.d(a, "delete:" + iDelete);
        } catch (Exception unused) {
            Log.e(a, "return delete is error");
        }
        return iDelete != 0;
    }
}
